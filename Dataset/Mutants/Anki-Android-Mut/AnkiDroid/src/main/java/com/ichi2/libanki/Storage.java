/**
 * ************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki;

import android.content.ContentValues;
import android.content.Context;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.libanki.exception.UnknownDatabaseVersionException;
import com.ichi2.libanki.utils.SystemTime;
import com.ichi2.libanki.utils.Time;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import androidx.annotation.NonNull;
import timber.log.Timber;
import static com.ichi2.libanki.Consts.DECK_STD;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.ExcessiveMethodLength", "PMD.OneDeclarationPerLine", "PMD.SwitchStmtsShouldHaveDefault", "PMD.EmptyIfStmt", "PMD.SimplifyBooleanReturns", "PMD.CollapsibleIfStatements" })
public class Storage {

    /* Open a new or existing collection. Path must be unicode */
    public static Collection Collection(Context context, String path) {
        return Collection(context, path, false, false);
    }

    /**
     * Helper method for when the collection can't be opened
     */
    public static int getDatabaseVersion(String path) throws UnknownDatabaseVersionException {
        try {
            DB db = new DB(path);
            return db.queryScalar("SELECT ver FROM col");
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(23840)) {
                Timber.w(e, "Can't open database");
            }
            throw new UnknownDatabaseVersionException(e);
        }
    }

    public static Collection Collection(Context context, String path, boolean server, boolean log) {
        return Collection(context, path, server, log, new SystemTime());
    }

    public static Collection Collection(Context context, String path, boolean server, boolean log, @NonNull Time time) {
        assert path.endsWith(".anki2");
        File dbFile = new File(path);
        boolean create = !dbFile.exists();
        // connect
        DB db = new DB(path);
        try {
            // initialize
            int ver;
            if (create) {
                ver = _createDB(db, time);
            } else {
                ver = _upgradeSchema(db, time);
            }
            if (!ListenerUtil.mutListener.listen(23843)) {
                db.execute("PRAGMA temp_store = memory");
            }
            // add db to col and do any remaining upgrades
            Collection col = new Collection(context, db, path, server, log, time);
            if (!ListenerUtil.mutListener.listen(23867)) {
                if ((ListenerUtil.mutListener.listen(23848) ? (ver >= Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23847) ? (ver <= Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23846) ? (ver > Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23845) ? (ver != Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23844) ? (ver == Consts.SCHEMA_VERSION) : (ver < Consts.SCHEMA_VERSION))))))) {
                    if (!ListenerUtil.mutListener.listen(23866)) {
                        _upgrade(col, ver);
                    }
                } else if ((ListenerUtil.mutListener.listen(23853) ? (ver >= Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23852) ? (ver <= Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23851) ? (ver < Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23850) ? (ver != Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23849) ? (ver == Consts.SCHEMA_VERSION) : (ver > Consts.SCHEMA_VERSION))))))) {
                    throw new RuntimeException("This file requires a newer version of Anki.");
                } else if (create) {
                    if (!ListenerUtil.mutListener.listen(23864)) {
                        {
                            long _loopCounter612 = 0;
                            // add in reverse order so basic is default
                            for (int i = (ListenerUtil.mutListener.listen(23863) ? (StdModels.stdModels.length % 1) : (ListenerUtil.mutListener.listen(23862) ? (StdModels.stdModels.length / 1) : (ListenerUtil.mutListener.listen(23861) ? (StdModels.stdModels.length * 1) : (ListenerUtil.mutListener.listen(23860) ? (StdModels.stdModels.length + 1) : (StdModels.stdModels.length - 1))))); (ListenerUtil.mutListener.listen(23859) ? (i <= 0) : (ListenerUtil.mutListener.listen(23858) ? (i > 0) : (ListenerUtil.mutListener.listen(23857) ? (i < 0) : (ListenerUtil.mutListener.listen(23856) ? (i != 0) : (ListenerUtil.mutListener.listen(23855) ? (i == 0) : (i >= 0)))))); i--) {
                                ListenerUtil.loopListener.listen("_loopCounter612", ++_loopCounter612);
                                if (!ListenerUtil.mutListener.listen(23854)) {
                                    StdModels.stdModels[i].add(col);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23865)) {
                        col.save();
                    }
                }
            }
            return col;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(23841)) {
                Timber.e(e, "Error opening collection; closing database");
            }
            if (!ListenerUtil.mutListener.listen(23842)) {
                db.close();
            }
            throw e;
        }
    }

    private static int _upgradeSchema(DB db, @NonNull Time time) {
        int ver = db.queryScalar("SELECT ver FROM col");
        if (!ListenerUtil.mutListener.listen(23873)) {
            if ((ListenerUtil.mutListener.listen(23872) ? (ver >= Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23871) ? (ver <= Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23870) ? (ver > Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23869) ? (ver < Consts.SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(23868) ? (ver != Consts.SCHEMA_VERSION) : (ver == Consts.SCHEMA_VERSION))))))) {
                return ver;
            }
        }
        if (!ListenerUtil.mutListener.listen(23880)) {
            // add odid to cards, edue->odue
            if (db.queryScalar("SELECT ver FROM col") == 1) {
                if (!ListenerUtil.mutListener.listen(23874)) {
                    db.execute("ALTER TABLE cards RENAME TO cards2");
                }
                if (!ListenerUtil.mutListener.listen(23875)) {
                    _addSchema(db, false, time);
                }
                if (!ListenerUtil.mutListener.listen(23876)) {
                    db.execute("insert into cards select id, nid, did, ord, mod, usn, type, queue, due, ivl, factor, reps, lapses, left, edue, 0, flags, data from cards2");
                }
                if (!ListenerUtil.mutListener.listen(23877)) {
                    db.execute("DROP TABLE cards2");
                }
                if (!ListenerUtil.mutListener.listen(23878)) {
                    db.execute("UPDATE col SET ver = 2");
                }
                if (!ListenerUtil.mutListener.listen(23879)) {
                    _updateIndices(db);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23887)) {
            // remove did from notes
            if (db.queryScalar("SELECT ver FROM col") == 2) {
                if (!ListenerUtil.mutListener.listen(23881)) {
                    db.execute("ALTER TABLE notes RENAME TO notes2");
                }
                if (!ListenerUtil.mutListener.listen(23882)) {
                    _addSchema(db, time);
                }
                if (!ListenerUtil.mutListener.listen(23883)) {
                    db.execute("insert into notes select id, guid, mid, mod, usn, tags, flds, sfld, csum, flags, data from notes2");
                }
                if (!ListenerUtil.mutListener.listen(23884)) {
                    db.execute("DROP TABLE notes2");
                }
                if (!ListenerUtil.mutListener.listen(23885)) {
                    db.execute("UPDATE col SET ver = 3");
                }
                if (!ListenerUtil.mutListener.listen(23886)) {
                    _updateIndices(db);
                }
            }
        }
        return ver;
    }

    private static void _upgrade(Collection col, int ver) {
        try {
            if (!ListenerUtil.mutListener.listen(23897)) {
                if ((ListenerUtil.mutListener.listen(23892) ? (ver >= 3) : (ListenerUtil.mutListener.listen(23891) ? (ver <= 3) : (ListenerUtil.mutListener.listen(23890) ? (ver > 3) : (ListenerUtil.mutListener.listen(23889) ? (ver != 3) : (ListenerUtil.mutListener.listen(23888) ? (ver == 3) : (ver < 3))))))) {
                    if (!ListenerUtil.mutListener.listen(23896)) {
                        {
                            long _loopCounter613 = 0;
                            // new deck properties
                            for (Deck d : col.getDecks().all()) {
                                ListenerUtil.loopListener.listen("_loopCounter613", ++_loopCounter613);
                                if (!ListenerUtil.mutListener.listen(23893)) {
                                    d.put("dyn", DECK_STD);
                                }
                                if (!ListenerUtil.mutListener.listen(23894)) {
                                    d.put("collapsed", false);
                                }
                                if (!ListenerUtil.mutListener.listen(23895)) {
                                    col.getDecks().save(d);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23911)) {
                if ((ListenerUtil.mutListener.listen(23902) ? (ver >= 4) : (ListenerUtil.mutListener.listen(23901) ? (ver <= 4) : (ListenerUtil.mutListener.listen(23900) ? (ver > 4) : (ListenerUtil.mutListener.listen(23899) ? (ver != 4) : (ListenerUtil.mutListener.listen(23898) ? (ver == 4) : (ver < 4))))))) {
                    if (!ListenerUtil.mutListener.listen(23903)) {
                        col.modSchemaNoCheck();
                    }
                    ArrayList<Model> models = col.getModels().all();
                    ArrayList<Model> clozes = new ArrayList<>(models);
                    if (!ListenerUtil.mutListener.listen(23907)) {
                        {
                            long _loopCounter614 = 0;
                            for (Model m : models) {
                                ListenerUtil.loopListener.listen("_loopCounter614", ++_loopCounter614);
                                if (!ListenerUtil.mutListener.listen(23906)) {
                                    if (!m.getJSONArray("tmpls").getJSONObject(0).getString("qfmt").contains("{{cloze:")) {
                                        if (!ListenerUtil.mutListener.listen(23905)) {
                                            m.put("type", Consts.MODEL_STD);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(23904)) {
                                            clozes.add(m);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23909)) {
                        {
                            long _loopCounter615 = 0;
                            for (Model m : clozes) {
                                ListenerUtil.loopListener.listen("_loopCounter615", ++_loopCounter615);
                                try {
                                    if (!ListenerUtil.mutListener.listen(23908)) {
                                        _upgradeClozeModel(col, m);
                                    }
                                } catch (ConfirmModSchemaException e) {
                                    // Will never be reached as we already set modSchemaNoCheck()
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23910)) {
                        col.getDb().execute("UPDATE col SET ver = 4");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23919)) {
                if ((ListenerUtil.mutListener.listen(23916) ? (ver >= 5) : (ListenerUtil.mutListener.listen(23915) ? (ver <= 5) : (ListenerUtil.mutListener.listen(23914) ? (ver > 5) : (ListenerUtil.mutListener.listen(23913) ? (ver != 5) : (ListenerUtil.mutListener.listen(23912) ? (ver == 5) : (ver < 5))))))) {
                    if (!ListenerUtil.mutListener.listen(23917)) {
                        col.getDb().execute("UPDATE cards SET odue = 0 WHERE queue = 2");
                    }
                    if (!ListenerUtil.mutListener.listen(23918)) {
                        col.getDb().execute("UPDATE col SET ver = 5");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23934)) {
                if ((ListenerUtil.mutListener.listen(23924) ? (ver >= 6) : (ListenerUtil.mutListener.listen(23923) ? (ver <= 6) : (ListenerUtil.mutListener.listen(23922) ? (ver > 6) : (ListenerUtil.mutListener.listen(23921) ? (ver != 6) : (ListenerUtil.mutListener.listen(23920) ? (ver == 6) : (ver < 6))))))) {
                    if (!ListenerUtil.mutListener.listen(23925)) {
                        col.modSchemaNoCheck();
                    }
                    if (!ListenerUtil.mutListener.listen(23932)) {
                        {
                            long _loopCounter617 = 0;
                            for (Model m : col.getModels().all()) {
                                ListenerUtil.loopListener.listen("_loopCounter617", ++_loopCounter617);
                                if (!ListenerUtil.mutListener.listen(23926)) {
                                    m.put("css", new JSONObject(Models.defaultModel).getString("css"));
                                }
                                JSONArray ar = m.getJSONArray("tmpls");
                                if (!ListenerUtil.mutListener.listen(23930)) {
                                    {
                                        long _loopCounter616 = 0;
                                        for (JSONObject t : ar.jsonObjectIterable()) {
                                            ListenerUtil.loopListener.listen("_loopCounter616", ++_loopCounter616);
                                            if (!ListenerUtil.mutListener.listen(23927)) {
                                                if (!t.has("css")) {
                                                    continue;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(23928)) {
                                                m.put("css", m.getString("css") + "\n" + t.getString("css").replace(".card ", ".card" + t.getInt("ord") + 1));
                                            }
                                            if (!ListenerUtil.mutListener.listen(23929)) {
                                                t.remove("css");
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(23931)) {
                                    col.getModels().save(m);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23933)) {
                        col.getDb().execute("UPDATE col SET ver = 6");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23943)) {
                if ((ListenerUtil.mutListener.listen(23939) ? (ver >= 7) : (ListenerUtil.mutListener.listen(23938) ? (ver <= 7) : (ListenerUtil.mutListener.listen(23937) ? (ver > 7) : (ListenerUtil.mutListener.listen(23936) ? (ver != 7) : (ListenerUtil.mutListener.listen(23935) ? (ver == 7) : (ver < 7))))))) {
                    if (!ListenerUtil.mutListener.listen(23940)) {
                        col.modSchemaNoCheck();
                    }
                    if (!ListenerUtil.mutListener.listen(23941)) {
                        col.getDb().execute("UPDATE cards SET odue = 0 WHERE (type = " + Consts.CARD_TYPE_LRN + " OR queue = 2) AND NOT odid");
                    }
                    if (!ListenerUtil.mutListener.listen(23942)) {
                        col.getDb().execute("UPDATE col SET ver = 7");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23952)) {
                if ((ListenerUtil.mutListener.listen(23948) ? (ver >= 8) : (ListenerUtil.mutListener.listen(23947) ? (ver <= 8) : (ListenerUtil.mutListener.listen(23946) ? (ver > 8) : (ListenerUtil.mutListener.listen(23945) ? (ver != 8) : (ListenerUtil.mutListener.listen(23944) ? (ver == 8) : (ver < 8))))))) {
                    if (!ListenerUtil.mutListener.listen(23949)) {
                        col.modSchemaNoCheck();
                    }
                    if (!ListenerUtil.mutListener.listen(23950)) {
                        col.getDb().execute("UPDATE cards SET due = due / 1000 WHERE due > 4294967296");
                    }
                    if (!ListenerUtil.mutListener.listen(23951)) {
                        col.getDb().execute("UPDATE col SET ver = 8");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23959)) {
                if ((ListenerUtil.mutListener.listen(23957) ? (ver >= 9) : (ListenerUtil.mutListener.listen(23956) ? (ver <= 9) : (ListenerUtil.mutListener.listen(23955) ? (ver > 9) : (ListenerUtil.mutListener.listen(23954) ? (ver != 9) : (ListenerUtil.mutListener.listen(23953) ? (ver == 9) : (ver < 9))))))) {
                    if (!ListenerUtil.mutListener.listen(23958)) {
                        col.getDb().execute("UPDATE col SET ver = 9");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23967)) {
                if ((ListenerUtil.mutListener.listen(23964) ? (ver >= 10) : (ListenerUtil.mutListener.listen(23963) ? (ver <= 10) : (ListenerUtil.mutListener.listen(23962) ? (ver > 10) : (ListenerUtil.mutListener.listen(23961) ? (ver != 10) : (ListenerUtil.mutListener.listen(23960) ? (ver == 10) : (ver < 10))))))) {
                    if (!ListenerUtil.mutListener.listen(23965)) {
                        col.getDb().execute("UPDATE cards SET left = left + left * 1000 WHERE queue = " + Consts.QUEUE_TYPE_LRN);
                    }
                    if (!ListenerUtil.mutListener.listen(23966)) {
                        col.getDb().execute("UPDATE col SET ver = 10");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24006)) {
                if ((ListenerUtil.mutListener.listen(23972) ? (ver >= 11) : (ListenerUtil.mutListener.listen(23971) ? (ver <= 11) : (ListenerUtil.mutListener.listen(23970) ? (ver > 11) : (ListenerUtil.mutListener.listen(23969) ? (ver != 11) : (ListenerUtil.mutListener.listen(23968) ? (ver == 11) : (ver < 11))))))) {
                    if (!ListenerUtil.mutListener.listen(23973)) {
                        col.modSchemaNoCheck();
                    }
                    if (!ListenerUtil.mutListener.listen(23993)) {
                        {
                            long _loopCounter618 = 0;
                            for (Deck d : col.getDecks().all()) {
                                ListenerUtil.loopListener.listen("_loopCounter618", ++_loopCounter618);
                                if (!ListenerUtil.mutListener.listen(23991)) {
                                    if (d.isDyn()) {
                                        int order = d.getInt("order");
                                        if (!ListenerUtil.mutListener.listen(23983)) {
                                            // failed order was removed
                                            if ((ListenerUtil.mutListener.listen(23981) ? (order <= 5) : (ListenerUtil.mutListener.listen(23980) ? (order > 5) : (ListenerUtil.mutListener.listen(23979) ? (order < 5) : (ListenerUtil.mutListener.listen(23978) ? (order != 5) : (ListenerUtil.mutListener.listen(23977) ? (order == 5) : (order >= 5))))))) {
                                                if (!ListenerUtil.mutListener.listen(23982)) {
                                                    order -= 1;
                                                }
                                            }
                                        }
                                        JSONArray terms = new JSONArray(Arrays.asList(d.getString("search"), d.getInt("limit"), order));
                                        if (!ListenerUtil.mutListener.listen(23984)) {
                                            d.put("terms", new JSONArray());
                                        }
                                        if (!ListenerUtil.mutListener.listen(23985)) {
                                            d.getJSONArray("terms").put(0, terms);
                                        }
                                        if (!ListenerUtil.mutListener.listen(23986)) {
                                            d.remove("search");
                                        }
                                        if (!ListenerUtil.mutListener.listen(23987)) {
                                            d.remove("limit");
                                        }
                                        if (!ListenerUtil.mutListener.listen(23988)) {
                                            d.remove("order");
                                        }
                                        if (!ListenerUtil.mutListener.listen(23989)) {
                                            d.put("resched", true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(23990)) {
                                            d.put("return", true);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(23976)) {
                                            if (!d.has("extendNew")) {
                                                if (!ListenerUtil.mutListener.listen(23974)) {
                                                    d.put("extendNew", 10);
                                                }
                                                if (!ListenerUtil.mutListener.listen(23975)) {
                                                    d.put("extendRev", 50);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(23992)) {
                                    col.getDecks().save(d);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23999)) {
                        {
                            long _loopCounter619 = 0;
                            for (DeckConfig c : col.getDecks().allConf()) {
                                ListenerUtil.loopListener.listen("_loopCounter619", ++_loopCounter619);
                                JSONObject r = c.getJSONObject("rev");
                                if (!ListenerUtil.mutListener.listen(23994)) {
                                    r.put("ivlFct", r.optDouble("ivlFct", 1));
                                }
                                if (!ListenerUtil.mutListener.listen(23996)) {
                                    if (r.has("ivlfct")) {
                                        if (!ListenerUtil.mutListener.listen(23995)) {
                                            r.remove("ivlfct");
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(23997)) {
                                    r.put("maxIvl", 36500);
                                }
                                if (!ListenerUtil.mutListener.listen(23998)) {
                                    col.getDecks().save(c);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24004)) {
                        {
                            long _loopCounter621 = 0;
                            for (Model m : col.getModels().all()) {
                                ListenerUtil.loopListener.listen("_loopCounter621", ++_loopCounter621);
                                JSONArray tmpls = m.getJSONArray("tmpls");
                                if (!ListenerUtil.mutListener.listen(24002)) {
                                    {
                                        long _loopCounter620 = 0;
                                        for (JSONObject t : tmpls.jsonObjectIterable()) {
                                            ListenerUtil.loopListener.listen("_loopCounter620", ++_loopCounter620);
                                            if (!ListenerUtil.mutListener.listen(24000)) {
                                                t.put("bqfmt", "");
                                            }
                                            if (!ListenerUtil.mutListener.listen(24001)) {
                                                t.put("bafmt", "");
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(24003)) {
                                    col.getModels().save(m);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24005)) {
                        col.getDb().execute("update col set ver = 11");
                    }
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static void _upgradeClozeModel(Collection col, Model m) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(24007)) {
            m.put("type", Consts.MODEL_CLOZE);
        }
        // convert first template
        JSONObject t = m.getJSONArray("tmpls").getJSONObject(0);
        if (!ListenerUtil.mutListener.listen(24009)) {
            {
                long _loopCounter622 = 0;
                for (String type : new String[] { "qfmt", "afmt" }) {
                    ListenerUtil.loopListener.listen("_loopCounter622", ++_loopCounter622);
                    if (!ListenerUtil.mutListener.listen(24008)) {
                        // noinspection RegExpRedundantEscape            // In Android, } should be escaped
                        t.put(type, t.getString(type).replaceAll("\\{\\{cloze:1:(.+?)\\}\\}", "{{cloze:$1}}"));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24010)) {
            t.put("name", "Cloze");
        }
        // delete non-cloze cards for the model
        JSONArray tmpls = m.getJSONArray("tmpls");
        ArrayList<JSONObject> rem = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(24013)) {
            {
                long _loopCounter623 = 0;
                for (JSONObject ta : tmpls.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter623", ++_loopCounter623);
                    if (!ListenerUtil.mutListener.listen(24012)) {
                        if (!ta.getString("afmt").contains("{{cloze:")) {
                            if (!ListenerUtil.mutListener.listen(24011)) {
                                rem.add(ta);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24015)) {
            {
                long _loopCounter624 = 0;
                for (JSONObject r : rem) {
                    ListenerUtil.loopListener.listen("_loopCounter624", ++_loopCounter624);
                    if (!ListenerUtil.mutListener.listen(24014)) {
                        col.getModels().remTemplate(m, r);
                    }
                }
            }
        }
        JSONArray newTmpls = new JSONArray();
        if (!ListenerUtil.mutListener.listen(24016)) {
            newTmpls.put(tmpls.getJSONObject(0));
        }
        if (!ListenerUtil.mutListener.listen(24017)) {
            m.put("tmpls", newTmpls);
        }
        if (!ListenerUtil.mutListener.listen(24018)) {
            Models._updateTemplOrds(m);
        }
        if (!ListenerUtil.mutListener.listen(24019)) {
            col.getModels().save(m);
        }
    }

    private static int _createDB(DB db, @NonNull Time time) {
        if (!ListenerUtil.mutListener.listen(24020)) {
            db.execute("PRAGMA page_size = 4096");
        }
        if (!ListenerUtil.mutListener.listen(24021)) {
            db.execute("PRAGMA legacy_file_format = 0");
        }
        if (!ListenerUtil.mutListener.listen(24022)) {
            db.execute("VACUUM");
        }
        if (!ListenerUtil.mutListener.listen(24023)) {
            _addSchema(db, time);
        }
        if (!ListenerUtil.mutListener.listen(24024)) {
            _updateIndices(db);
        }
        if (!ListenerUtil.mutListener.listen(24025)) {
            db.execute("ANALYZE");
        }
        return Consts.SCHEMA_VERSION;
    }

    private static void _addSchema(DB db, @NonNull Time time) {
        if (!ListenerUtil.mutListener.listen(24026)) {
            _addSchema(db, true, time);
        }
    }

    private static void _addSchema(DB db, boolean setColConf, @NonNull Time time) {
        if (!ListenerUtil.mutListener.listen(24027)) {
            db.execute("create table if not exists col ( " + "id              integer primary key, " + "crt             integer not null," + "mod             integer not null," + "scm             integer not null," + "ver             integer not null," + "dty             integer not null," + "usn             integer not null," + "ls              integer not null," + "conf            text not null," + "models          text not null," + "decks           text not null," + "dconf           text not null," + "tags            text not null" + ");");
        }
        if (!ListenerUtil.mutListener.listen(24028)) {
            db.execute("create table if not exists notes (" + "   id              integer primary key,   /* 0 */" + "  guid            text not null,   /* 1 */" + " mid             integer not null,   /* 2 */" + " mod             integer not null,   /* 3 */" + " usn             integer not null,   /* 4 */" + " tags            text not null,   /* 5 */" + " flds            text not null,   /* 6 */" + " sfld            integer not null,   /* 7 */" + " csum            integer not null,   /* 8 */" + " flags           integer not null,   /* 9 */" + " data            text not null   /* 10 */" + ");");
        }
        if (!ListenerUtil.mutListener.listen(24029)) {
            db.execute("create table if not exists cards (" + "   id              integer primary key,   /* 0 */" + "  nid             integer not null,   /* 1 */" + "  did             integer not null,   /* 2 */" + "  ord             integer not null,   /* 3 */" + "  mod             integer not null,   /* 4 */" + " usn             integer not null,   /* 5 */" + " type            integer not null,   /* 6 */" + " queue           integer not null,   /* 7 */" + "    due             integer not null,   /* 8 */" + "   ivl             integer not null,   /* 9 */" + "  factor          integer not null,   /* 10 */" + " reps            integer not null,   /* 11 */" + "   lapses          integer not null,   /* 12 */" + "   left            integer not null,   /* 13 */" + "   odue            integer not null,   /* 14 */" + "   odid            integer not null,   /* 15 */" + "   flags           integer not null,   /* 16 */" + "   data            text not null   /* 17 */" + ");");
        }
        if (!ListenerUtil.mutListener.listen(24030)) {
            db.execute("create table if not exists revlog (" + "   id              integer primary key," + "   cid             integer not null," + "   usn             integer not null," + "   ease            integer not null," + "   ivl             integer not null," + "   lastIvl         integer not null," + "   factor          integer not null," + "   time            integer not null," + "   type            integer not null" + ");");
        }
        if (!ListenerUtil.mutListener.listen(24031)) {
            db.execute("create table if not exists graves (" + "    usn             integer not null," + "    oid             integer not null," + "    type            integer not null" + ")");
        }
        if (!ListenerUtil.mutListener.listen(24032)) {
            db.execute("INSERT OR IGNORE INTO col VALUES(1,0,0," + time.intTimeMS() + "," + Consts.SCHEMA_VERSION + ",0,0,0,'','{}','','','{}')");
        }
        if (!ListenerUtil.mutListener.listen(24034)) {
            if (setColConf) {
                if (!ListenerUtil.mutListener.listen(24033)) {
                    _setColVars(db, time);
                }
            }
        }
    }

    private static void _setColVars(DB db, @NonNull Time time) {
        JSONObject g = new JSONObject(Decks.defaultDeck);
        if (!ListenerUtil.mutListener.listen(24035)) {
            g.put("id", 1);
        }
        if (!ListenerUtil.mutListener.listen(24036)) {
            g.put("name", "Default");
        }
        if (!ListenerUtil.mutListener.listen(24037)) {
            g.put("conf", 1);
        }
        if (!ListenerUtil.mutListener.listen(24038)) {
            g.put("mod", time.intTime());
        }
        JSONObject gc = new JSONObject(Decks.defaultConf);
        if (!ListenerUtil.mutListener.listen(24039)) {
            gc.put("id", 1);
        }
        JSONObject ag = new JSONObject();
        if (!ListenerUtil.mutListener.listen(24040)) {
            ag.put("1", g);
        }
        JSONObject agc = new JSONObject();
        if (!ListenerUtil.mutListener.listen(24041)) {
            agc.put("1", gc);
        }
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(24042)) {
            values.put("conf", Collection.defaultConf);
        }
        if (!ListenerUtil.mutListener.listen(24043)) {
            values.put("decks", Utils.jsonToString(ag));
        }
        if (!ListenerUtil.mutListener.listen(24044)) {
            values.put("dconf", Utils.jsonToString(agc));
        }
        if (!ListenerUtil.mutListener.listen(24045)) {
            db.update("col", values);
        }
    }

    private static void _updateIndices(DB db) {
        if (!ListenerUtil.mutListener.listen(24046)) {
            db.execute("create index if not exists ix_notes_usn on notes (usn);");
        }
        if (!ListenerUtil.mutListener.listen(24047)) {
            db.execute("create index if not exists ix_cards_usn on cards (usn);");
        }
        if (!ListenerUtil.mutListener.listen(24048)) {
            db.execute("create index if not exists ix_revlog_usn on revlog (usn);");
        }
        if (!ListenerUtil.mutListener.listen(24049)) {
            db.execute("create index if not exists ix_cards_nid on cards (nid);");
        }
        if (!ListenerUtil.mutListener.listen(24050)) {
            db.execute("create index if not exists ix_cards_sched on cards (did, queue, due);");
        }
        if (!ListenerUtil.mutListener.listen(24051)) {
            db.execute("create index if not exists ix_revlog_cid on revlog (cid);");
        }
        if (!ListenerUtil.mutListener.listen(24052)) {
            db.execute("create index if not exists ix_notes_csum on notes (csum);)");
        }
    }

    public static void addIndices(DB db) {
        if (!ListenerUtil.mutListener.listen(24053)) {
            _updateIndices(db);
        }
    }
}

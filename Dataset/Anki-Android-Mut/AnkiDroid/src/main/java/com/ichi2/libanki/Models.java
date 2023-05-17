/**
 * *************************************************************************************
 *  Copyright (c) 2009 Daniel Svärd <daniel.svard@gmail.com>                             *
 *  Copyright (c) 2010 Rick Gruber-Riemer <rick@vanosten.net>                            *
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2011 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
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
import android.database.Cursor;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.libanki.template.ParsedNode;
import com.ichi2.libanki.template.TemplateError;
import com.ichi2.utils.Assert;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import static com.ichi2.libanki.Utils.trimArray;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.ExcessiveClassLength", "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.SwitchStmtsShouldHaveDefault", "PMD.CollapsibleIfStatements", "PMD.EmptyIfStmt" })
public class Models {

    public static final long NOT_FOUND_NOTE_TYPE = -1L;

    @VisibleForTesting
    public static final String REQ_NONE = "none";

    @VisibleForTesting
    public static final String REQ_ANY = "any";

    @VisibleForTesting
    public static final String REQ_ALL = "all";

    // In Android, } should be escaped
    @SuppressWarnings("RegExpRedundantEscape")
    private static final Pattern fClozePattern1 = Pattern.compile("\\{\\{[^}]*?cloze:(?:[^}]?:)*(.+?)\\}\\}");

    private static final Pattern fClozePattern2 = Pattern.compile("<%cloze:(.+?)%>");

    @SuppressWarnings("RegExpRedundantEscape")
    private static final Pattern fClozeOrdPattern = Pattern.compile("(?si)\\{\\{c(\\d+)::.*?\\}\\}");

    public static final String defaultModel = "{'sortf': 0, " + "'did': 1, " + "'latexPre': \"" + "\\\\documentclass[12pt]{article}\\n" + "\\\\special{papersize=3in,5in}\\n" + "\\\\usepackage[utf8]{inputenc}\\n" + "\\\\usepackage{amssymb,amsmath}\\n" + "\\\\pagestyle{empty}\\n" + "\\\\setlength{\\\\parindent}{0in}\\n" + "\\\\begin{document}\\n" + "\", " + "'latexPost': \"\\\\end{document}\", " + "'mod': 0, " + "'usn': 0, " + // FIXME: remove when other clients have caught up
    "'vers': [], " + "'type': " + Consts.MODEL_STD + ", " + "'css': \".card {\\n" + " font-family: arial;\\n" + " font-size: 20px;\\n" + " text-align: center;\\n" + " color: black;\\n" + " background-color: white;\\n" + "}\"" + "}";

    private static final String defaultField = "{'name': \"\", " + "'ord': null, " + "'sticky': False, " + // the following alter editing, and are used as defaults for the template wizard
    "'rtl': False, " + "'font': \"Arial\", " + "'size': 20, " + // reserved for future use
    "'media': [] }";

    private static final String defaultTemplate = "{'name': \"\", " + "'ord': null, " + "'qfmt': \"\", " + "'afmt': \"\", " + "'did': null, " + "'bqfmt': \"\"," + "'bafmt': \"\"," + "'bfont': \"Arial\"," + "'bsize': 12 }";

    private final Collection mCol;

    private boolean mChanged;

    private HashMap<Long, Model> mModels;

    // BEGIN SQL table entries
    private int mId;

    public Models(Collection col) {
        mCol = col;
    }

    /**
     * Load registry from JSON.
     */
    public void load(String json) {
        if (!ListenerUtil.mutListener.listen(23171)) {
            mChanged = false;
        }
        if (!ListenerUtil.mutListener.listen(23172)) {
            mModels = new HashMap<>();
        }
        JSONObject modelarray = new JSONObject(json);
        JSONArray ids = modelarray.names();
        if (!ListenerUtil.mutListener.listen(23175)) {
            if (ids != null) {
                if (!ListenerUtil.mutListener.listen(23174)) {
                    {
                        long _loopCounter569 = 0;
                        for (String id : ids.stringIterable()) {
                            ListenerUtil.loopListener.listen("_loopCounter569", ++_loopCounter569);
                            Model o = new Model(modelarray.getJSONObject(id));
                            if (!ListenerUtil.mutListener.listen(23173)) {
                                mModels.put(o.getLong("id"), o);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Mark M modified if provided, and schedule registry flush.
     */
    public void save() {
        if (!ListenerUtil.mutListener.listen(23176)) {
            save(null, false);
        }
    }

    public void save(Model m) {
        if (!ListenerUtil.mutListener.listen(23177)) {
            save(m, false);
        }
    }

    /**
     * Save a model
     * @param m model to save
     * @param templates flag which (when true) re-generates the cards for each note which uses the model
     */
    public void save(Model m, boolean templates) {
        if (!ListenerUtil.mutListener.listen(23183)) {
            if ((ListenerUtil.mutListener.listen(23178) ? (m != null || m.has("id")) : (m != null && m.has("id")))) {
                if (!ListenerUtil.mutListener.listen(23179)) {
                    m.put("mod", mCol.getTime().intTime());
                }
                if (!ListenerUtil.mutListener.listen(23180)) {
                    m.put("usn", mCol.usn());
                }
                if (!ListenerUtil.mutListener.listen(23182)) {
                    // TODO: fix empty id problem on _updaterequired (needed for model adding)
                    if (templates) {
                        if (!ListenerUtil.mutListener.listen(23181)) {
                            _syncTemplates(m);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23184)) {
            mChanged = true;
        }
    }

    /**
     * Flush the registry if any models were changed.
     */
    public void flush() {
        if (!ListenerUtil.mutListener.listen(23191)) {
            if (mChanged) {
                if (!ListenerUtil.mutListener.listen(23185)) {
                    ensureNotEmpty();
                }
                JSONObject array = new JSONObject();
                if (!ListenerUtil.mutListener.listen(23187)) {
                    {
                        long _loopCounter570 = 0;
                        for (Map.Entry<Long, Model> o : mModels.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter570", ++_loopCounter570);
                            if (!ListenerUtil.mutListener.listen(23186)) {
                                array.put(Long.toString(o.getKey()), o.getValue());
                            }
                        }
                    }
                }
                ContentValues val = new ContentValues();
                if (!ListenerUtil.mutListener.listen(23188)) {
                    val.put("models", Utils.jsonToString(array));
                }
                if (!ListenerUtil.mutListener.listen(23189)) {
                    mCol.getDb().update("col", val);
                }
                if (!ListenerUtil.mutListener.listen(23190)) {
                    mChanged = false;
                }
            }
        }
    }

    public boolean ensureNotEmpty() {
        if (mModels.isEmpty()) {
            if (!ListenerUtil.mutListener.listen(23192)) {
                // TODO: Maybe we want to restore all models if we don't have any
                StdModels.basicModel.add(mCol);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get current model.
     * @return The model, or null if not found in the deck and in the configuration.
     */
    public Model current() {
        return current(true);
    }

    /**
     * Get current model.
     * @param forDeck If true, it tries to get the deck specified in deck by mid, otherwise or if the former is not
     *                found, it uses the configuration`s field curModel.
     * @return The model, or null if not found in the deck and in the configuration.
     */
    public Model current(boolean forDeck) {
        Model m = null;
        if (!ListenerUtil.mutListener.listen(23194)) {
            if (forDeck) {
                if (!ListenerUtil.mutListener.listen(23193)) {
                    m = get(mCol.getDecks().current().optLong("mid", -1));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23196)) {
            if (m == null) {
                if (!ListenerUtil.mutListener.listen(23195)) {
                    m = get(mCol.getConf().optLong("curModel", -1));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23199)) {
            if (m == null) {
                if (!ListenerUtil.mutListener.listen(23198)) {
                    if (!mModels.isEmpty()) {
                        if (!ListenerUtil.mutListener.listen(23197)) {
                            m = mModels.values().iterator().next();
                        }
                    }
                }
            }
        }
        return m;
    }

    public void setCurrent(Model m) {
        if (!ListenerUtil.mutListener.listen(23200)) {
            mCol.getConf().put("curModel", m.getLong("id"));
        }
        if (!ListenerUtil.mutListener.listen(23201)) {
            mCol.setMod();
        }
    }

    /**
     * get model with ID, or null.
     */
    @Nullable
    public Model get(@NonNull Long id) {
        if (mModels.containsKey(id)) {
            return mModels.get(id);
        } else {
            return null;
        }
    }

    /**
     * get all models
     */
    public ArrayList<Model> all() {
        return new ArrayList<>(mModels.values());
    }

    /**
     * get model with NAME.
     */
    public Model byName(String name) {
        if (!ListenerUtil.mutListener.listen(23203)) {
            {
                long _loopCounter571 = 0;
                for (Model m : mModels.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter571", ++_loopCounter571);
                    if (!ListenerUtil.mutListener.listen(23202)) {
                        if (m.getString("name").equals(name)) {
                            return m;
                        }
                    }
                }
            }
        }
        return null;
    }

    // not in python. Thus the method has to be renamed.
    public Model newModel(String name) {
        // caller should call save() after modifying
        Model m = new Model(defaultModel);
        if (!ListenerUtil.mutListener.listen(23204)) {
            m.put("name", name);
        }
        if (!ListenerUtil.mutListener.listen(23205)) {
            m.put("mod", mCol.getTime().intTime());
        }
        if (!ListenerUtil.mutListener.listen(23206)) {
            m.put("flds", new JSONArray());
        }
        if (!ListenerUtil.mutListener.listen(23207)) {
            m.put("tmpls", new JSONArray());
        }
        if (!ListenerUtil.mutListener.listen(23208)) {
            m.put("tags", new JSONArray());
        }
        if (!ListenerUtil.mutListener.listen(23209)) {
            m.put("id", 0);
        }
        return m;
    }

    // not in anki
    public static boolean isModelNew(Model m) {
        return m.getLong("id") == 0;
    }

    /**
     * Delete model, and all its cards/notes.
     * @throws ConfirmModSchemaException
     */
    public void rem(Model m) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23210)) {
            mCol.modSchema();
        }
        long id = m.getLong("id");
        boolean current = current().getLong("id") == id;
        if (!ListenerUtil.mutListener.listen(23211)) {
            // delete notes/cards
            mCol.remCards(mCol.getDb().queryLongList("SELECT id FROM cards WHERE nid IN (SELECT id FROM notes WHERE mid = ?)", id));
        }
        if (!ListenerUtil.mutListener.listen(23212)) {
            // then the model
            mModels.remove(id);
        }
        if (!ListenerUtil.mutListener.listen(23213)) {
            save();
        }
        if (!ListenerUtil.mutListener.listen(23215)) {
            // GUI should ensure last model is not deleted
            if (current) {
                if (!ListenerUtil.mutListener.listen(23214)) {
                    setCurrent(mModels.values().iterator().next());
                }
            }
        }
    }

    public void add(Model m) {
        if (!ListenerUtil.mutListener.listen(23216)) {
            _setID(m);
        }
        if (!ListenerUtil.mutListener.listen(23217)) {
            update(m);
        }
        if (!ListenerUtil.mutListener.listen(23218)) {
            setCurrent(m);
        }
        if (!ListenerUtil.mutListener.listen(23219)) {
            save(m);
        }
    }

    /**
     * Add or update an existing model. Used for syncing and merging.
     */
    public void update(Model m) {
        if (!ListenerUtil.mutListener.listen(23220)) {
            mModels.put(m.getLong("id"), m);
        }
        if (!ListenerUtil.mutListener.listen(23221)) {
            // mark registry changed, but don't bump mod time
            save();
        }
    }

    private void _setID(Model m) {
        long id = mCol.getTime().intTimeMS();
        if (!ListenerUtil.mutListener.listen(23223)) {
            {
                long _loopCounter572 = 0;
                while (mModels.containsKey(id)) {
                    ListenerUtil.loopListener.listen("_loopCounter572", ++_loopCounter572);
                    if (!ListenerUtil.mutListener.listen(23222)) {
                        id = mCol.getTime().intTimeMS();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23224)) {
            m.put("id", id);
        }
    }

    public boolean have(@NonNull Long id) {
        return mModels.containsKey(id);
    }

    public Set<Long> ids() {
        return mModels.keySet();
    }

    /**
     * Note ids for M
     */
    public ArrayList<Long> nids(Model m) {
        return mCol.getDb().queryLongList("SELECT id FROM notes WHERE mid = ?", m.getLong("id"));
    }

    /**
     * Number of notes using m
     * @param m The model to the count the notes of.
     * @return The number of notes with that model.
     */
    public int useCount(Model m) {
        return mCol.getDb().queryScalar("select count() from notes where mid = ?", m.getLong("id"));
    }

    /**
     * Number of notes using m
     * @param m The model to the count the notes of.
     * @param ord The index of the card template
     * @return The number of notes with that model.
     */
    public int tmplUseCount(Model m, int ord) {
        return mCol.getDb().queryScalar("select count() from cards, notes where cards.nid = notes.id and notes.mid = ? and cards.ord = ?", m.getLong("id"), ord);
    }

    /**
     * Copy, save and return.
     */
    public Model copy(Model m) {
        Model m2 = m.deepClone();
        if (!ListenerUtil.mutListener.listen(23225)) {
            m2.put("name", m2.getString("name") + " copy");
        }
        if (!ListenerUtil.mutListener.listen(23226)) {
            add(m2);
        }
        return m2;
    }

    public JSONObject newField(String name) {
        JSONObject f = new JSONObject(defaultField);
        if (!ListenerUtil.mutListener.listen(23227)) {
            f.put("name", name);
        }
        return f;
    }

    /**
     * "Mapping of field name -> (ord, field).
     */
    @NonNull
    public static Map<String, Pair<Integer, JSONObject>> fieldMap(@NonNull Model m) {
        JSONArray flds = m.getJSONArray("flds");
        // TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        Map<String, Pair<Integer, JSONObject>> result = new HashMap<>(flds.length());
        if (!ListenerUtil.mutListener.listen(23229)) {
            {
                long _loopCounter573 = 0;
                for (JSONObject f : flds.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter573", ++_loopCounter573);
                    if (!ListenerUtil.mutListener.listen(23228)) {
                        result.put(f.getString("name"), new Pair<>(f.getInt("ord"), f));
                    }
                }
            }
        }
        return result;
    }

    public int sortIdx(Model m) {
        return m.getInt("sortf");
    }

    public void setSortIdx(Model m, int idx) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23230)) {
            mCol.modSchema();
        }
        if (!ListenerUtil.mutListener.listen(23231)) {
            m.put("sortf", idx);
        }
        if (!ListenerUtil.mutListener.listen(23232)) {
            mCol.updateFieldCache(nids(m));
        }
        if (!ListenerUtil.mutListener.listen(23233)) {
            save(m);
        }
    }

    private void _addField(Model m, JSONObject field) {
        // is not new.
        JSONArray flds = m.getJSONArray("flds");
        if (!ListenerUtil.mutListener.listen(23234)) {
            flds.put(field);
        }
        if (!ListenerUtil.mutListener.listen(23235)) {
            m.put("flds", flds);
        }
        if (!ListenerUtil.mutListener.listen(23236)) {
            _updateFieldOrds(m);
        }
        if (!ListenerUtil.mutListener.listen(23237)) {
            save(m);
        }
        if (!ListenerUtil.mutListener.listen(23238)) {
            _transformFields(m, new TransformFieldAdd());
        }
    }

    public void addField(Model m, JSONObject field) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23240)) {
            // this is Anki's addField.
            if (!isModelNew(m)) {
                if (!ListenerUtil.mutListener.listen(23239)) {
                    mCol.modSchema();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23241)) {
            _addField(m, field);
        }
    }

    public void addFieldInNewModel(Model m, JSONObject field) {
        if (!ListenerUtil.mutListener.listen(23242)) {
            // ConfirmModSchemaException.
            Assert.that(isModelNew(m), "Model was assumed to be new, but is not");
        }
        if (!ListenerUtil.mutListener.listen(23243)) {
            _addField(m, field);
        }
    }

    public void addFieldModChanged(Model m, JSONObject field) {
        if (!ListenerUtil.mutListener.listen(23244)) {
            // ConfirmModSchemaException.
            Assert.that(mCol.schemaChanged(), "Mod was assumed to be already changed, but is not");
        }
        if (!ListenerUtil.mutListener.listen(23245)) {
            _addField(m, field);
        }
    }

    static class TransformFieldAdd implements TransformFieldVisitor {

        @Override
        public String[] transform(String[] fields) {
            String[] f = new String[(ListenerUtil.mutListener.listen(23249) ? (fields.length % 1) : (ListenerUtil.mutListener.listen(23248) ? (fields.length / 1) : (ListenerUtil.mutListener.listen(23247) ? (fields.length * 1) : (ListenerUtil.mutListener.listen(23246) ? (fields.length - 1) : (fields.length + 1)))))];
            if (!ListenerUtil.mutListener.listen(23250)) {
                System.arraycopy(fields, 0, f, 0, fields.length);
            }
            if (!ListenerUtil.mutListener.listen(23251)) {
                f[fields.length] = "";
            }
            return f;
        }
    }

    public void remField(Model m, JSONObject field) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23252)) {
            mCol.modSchema();
        }
        JSONArray flds = m.getJSONArray("flds");
        JSONArray flds2 = new JSONArray();
        int idx = -1;
        if (!ListenerUtil.mutListener.listen(23261)) {
            {
                long _loopCounter574 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23260) ? (i >= flds.length()) : (ListenerUtil.mutListener.listen(23259) ? (i <= flds.length()) : (ListenerUtil.mutListener.listen(23258) ? (i > flds.length()) : (ListenerUtil.mutListener.listen(23257) ? (i != flds.length()) : (ListenerUtil.mutListener.listen(23256) ? (i == flds.length()) : (i < flds.length())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter574", ++_loopCounter574);
                    if (!ListenerUtil.mutListener.listen(23254)) {
                        if (field.equals(flds.getJSONObject(i))) {
                            if (!ListenerUtil.mutListener.listen(23253)) {
                                idx = i;
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23255)) {
                        flds2.put(flds.getJSONObject(i));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23262)) {
            m.put("flds", flds2);
        }
        int sortf = m.getInt("sortf");
        if (!ListenerUtil.mutListener.listen(23273)) {
            if ((ListenerUtil.mutListener.listen(23267) ? (sortf <= m.getJSONArray("flds").length()) : (ListenerUtil.mutListener.listen(23266) ? (sortf > m.getJSONArray("flds").length()) : (ListenerUtil.mutListener.listen(23265) ? (sortf < m.getJSONArray("flds").length()) : (ListenerUtil.mutListener.listen(23264) ? (sortf != m.getJSONArray("flds").length()) : (ListenerUtil.mutListener.listen(23263) ? (sortf == m.getJSONArray("flds").length()) : (sortf >= m.getJSONArray("flds").length()))))))) {
                if (!ListenerUtil.mutListener.listen(23272)) {
                    m.put("sortf", (ListenerUtil.mutListener.listen(23271) ? (sortf % 1) : (ListenerUtil.mutListener.listen(23270) ? (sortf / 1) : (ListenerUtil.mutListener.listen(23269) ? (sortf * 1) : (ListenerUtil.mutListener.listen(23268) ? (sortf + 1) : (sortf - 1))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23274)) {
            _updateFieldOrds(m);
        }
        if (!ListenerUtil.mutListener.listen(23275)) {
            _transformFields(m, new TransformFieldDelete(idx));
        }
        if (!ListenerUtil.mutListener.listen(23282)) {
            if ((ListenerUtil.mutListener.listen(23280) ? (idx >= sortIdx(m)) : (ListenerUtil.mutListener.listen(23279) ? (idx <= sortIdx(m)) : (ListenerUtil.mutListener.listen(23278) ? (idx > sortIdx(m)) : (ListenerUtil.mutListener.listen(23277) ? (idx < sortIdx(m)) : (ListenerUtil.mutListener.listen(23276) ? (idx != sortIdx(m)) : (idx == sortIdx(m)))))))) {
                if (!ListenerUtil.mutListener.listen(23281)) {
                    // need to rebuild
                    mCol.updateFieldCache(nids(m));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23283)) {
            renameField(m, field, null);
        }
    }

    static class TransformFieldDelete implements TransformFieldVisitor {

        private final int idx;

        public TransformFieldDelete(int _idx) {
            idx = _idx;
        }

        @Override
        public String[] transform(String[] fields) {
            ArrayList<String> fl = new ArrayList<>(Arrays.asList(fields));
            if (!ListenerUtil.mutListener.listen(23284)) {
                fl.remove(idx);
            }
            return fl.toArray(new String[fl.size()]);
        }
    }

    public void moveField(Model m, JSONObject field, int idx) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23285)) {
            mCol.modSchema();
        }
        JSONArray flds = m.getJSONArray("flds");
        ArrayList<JSONObject> l = new ArrayList<>(flds.length());
        int oldidx = -1;
        if (!ListenerUtil.mutListener.listen(23300)) {
            {
                long _loopCounter575 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23299) ? (i >= flds.length()) : (ListenerUtil.mutListener.listen(23298) ? (i <= flds.length()) : (ListenerUtil.mutListener.listen(23297) ? (i > flds.length()) : (ListenerUtil.mutListener.listen(23296) ? (i != flds.length()) : (ListenerUtil.mutListener.listen(23295) ? (i == flds.length()) : (i < flds.length())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter575", ++_loopCounter575);
                    if (!ListenerUtil.mutListener.listen(23286)) {
                        l.add(flds.getJSONObject(i));
                    }
                    if (!ListenerUtil.mutListener.listen(23294)) {
                        if (field.equals(flds.getJSONObject(i))) {
                            if (!ListenerUtil.mutListener.listen(23287)) {
                                oldidx = i;
                            }
                            if (!ListenerUtil.mutListener.listen(23293)) {
                                if ((ListenerUtil.mutListener.listen(23292) ? (idx >= oldidx) : (ListenerUtil.mutListener.listen(23291) ? (idx <= oldidx) : (ListenerUtil.mutListener.listen(23290) ? (idx > oldidx) : (ListenerUtil.mutListener.listen(23289) ? (idx < oldidx) : (ListenerUtil.mutListener.listen(23288) ? (idx != oldidx) : (idx == oldidx))))))) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        // remember old sort field
        String sortf = Utils.jsonToString(m.getJSONArray("flds").getJSONObject(m.getInt("sortf")));
        if (!ListenerUtil.mutListener.listen(23301)) {
            // move
            l.remove(oldidx);
        }
        if (!ListenerUtil.mutListener.listen(23302)) {
            l.add(idx, field);
        }
        if (!ListenerUtil.mutListener.listen(23303)) {
            m.put("flds", new JSONArray(l));
        }
        if (!ListenerUtil.mutListener.listen(23304)) {
            // restore sort field
            flds = m.getJSONArray("flds");
        }
        if (!ListenerUtil.mutListener.listen(23312)) {
            {
                long _loopCounter576 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23311) ? (i >= flds.length()) : (ListenerUtil.mutListener.listen(23310) ? (i <= flds.length()) : (ListenerUtil.mutListener.listen(23309) ? (i > flds.length()) : (ListenerUtil.mutListener.listen(23308) ? (i != flds.length()) : (ListenerUtil.mutListener.listen(23307) ? (i == flds.length()) : (i < flds.length())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter576", ++_loopCounter576);
                    if (!ListenerUtil.mutListener.listen(23306)) {
                        if (Utils.jsonToString(flds.getJSONObject(i)).equals(sortf)) {
                            if (!ListenerUtil.mutListener.listen(23305)) {
                                m.put("sortf", i);
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23313)) {
            _updateFieldOrds(m);
        }
        if (!ListenerUtil.mutListener.listen(23314)) {
            save(m);
        }
        if (!ListenerUtil.mutListener.listen(23315)) {
            _transformFields(m, new TransformFieldMove(idx, oldidx));
        }
    }

    static class TransformFieldMove implements TransformFieldVisitor {

        private final int idx;

        private final int oldidx;

        public TransformFieldMove(int _idx, int _oldidx) {
            idx = _idx;
            oldidx = _oldidx;
        }

        @Override
        public String[] transform(String[] fields) {
            String val = fields[oldidx];
            ArrayList<String> fl = new ArrayList<>(Arrays.asList(fields));
            if (!ListenerUtil.mutListener.listen(23316)) {
                fl.remove(oldidx);
            }
            if (!ListenerUtil.mutListener.listen(23317)) {
                fl.add(idx, val);
            }
            return fl.toArray(new String[fl.size()]);
        }
    }

    public void renameField(Model m, JSONObject field, String newName) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23318)) {
            mCol.modSchema();
        }
        String pat = String.format("\\{\\{([^{}]*)([:#^/]|[^:#/^}][^:}]*?:|)%s\\}\\}", Pattern.quote(field.getString("name")));
        if (!ListenerUtil.mutListener.listen(23320)) {
            if (newName == null) {
                if (!ListenerUtil.mutListener.listen(23319)) {
                    newName = "";
                }
            }
        }
        String repl = "{{$1$2" + newName + "}}";
        JSONArray tmpls = m.getJSONArray("tmpls");
        if (!ListenerUtil.mutListener.listen(23325)) {
            {
                long _loopCounter578 = 0;
                for (JSONObject t : tmpls.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter578", ++_loopCounter578);
                    if (!ListenerUtil.mutListener.listen(23324)) {
                        {
                            long _loopCounter577 = 0;
                            for (String fmt : new String[] { "qfmt", "afmt" }) {
                                ListenerUtil.loopListener.listen("_loopCounter577", ++_loopCounter577);
                                if (!ListenerUtil.mutListener.listen(23323)) {
                                    if (!"".equals(newName)) {
                                        if (!ListenerUtil.mutListener.listen(23322)) {
                                            t.put(fmt, t.getString(fmt).replaceAll(pat, repl));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(23321)) {
                                            t.put(fmt, t.getString(fmt).replaceAll(pat, ""));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23326)) {
            field.put("name", newName);
        }
        if (!ListenerUtil.mutListener.listen(23327)) {
            save(m);
        }
    }

    public void _updateFieldOrds(JSONObject m) {
        JSONArray flds = m.getJSONArray("flds");
        if (!ListenerUtil.mutListener.listen(23334)) {
            {
                long _loopCounter579 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23333) ? (i >= flds.length()) : (ListenerUtil.mutListener.listen(23332) ? (i <= flds.length()) : (ListenerUtil.mutListener.listen(23331) ? (i > flds.length()) : (ListenerUtil.mutListener.listen(23330) ? (i != flds.length()) : (ListenerUtil.mutListener.listen(23329) ? (i == flds.length()) : (i < flds.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter579", ++_loopCounter579);
                    JSONObject f = flds.getJSONObject(i);
                    if (!ListenerUtil.mutListener.listen(23328)) {
                        f.put("ord", i);
                    }
                }
            }
        }
    }

    interface TransformFieldVisitor {

        String[] transform(String[] fields);
    }

    public void _transformFields(Model m, TransformFieldVisitor fn) {
        if (!ListenerUtil.mutListener.listen(23335)) {
            // model hasn't been added yet?
            if (isModelNew(m)) {
                return;
            }
        }
        ArrayList<Object[]> r = new ArrayList<>();
        try (Cursor cur = mCol.getDb().query("select id, flds from notes where mid = ?", m.getLong("id"))) {
            if (!ListenerUtil.mutListener.listen(23337)) {
                {
                    long _loopCounter580 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter580", ++_loopCounter580);
                        if (!ListenerUtil.mutListener.listen(23336)) {
                            r.add(new Object[] { Utils.joinFields(fn.transform(Utils.splitFields(cur.getString(1)))), mCol.getTime().intTime(), mCol.usn(), cur.getLong(0) });
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23338)) {
            mCol.getDb().executeMany("update notes set flds=?,mod=?,usn=? where id = ?", r);
        }
    }

    public static JSONObject newTemplate(String name) {
        JSONObject t = new JSONObject(defaultTemplate);
        if (!ListenerUtil.mutListener.listen(23339)) {
            t.put("name", name);
        }
        return t;
    }

    /**
     * Note: should col.genCards() afterwards.
     */
    private void _addTemplate(Model m, JSONObject template) {
        // model is new or not.
        JSONArray tmpls = m.getJSONArray("tmpls");
        if (!ListenerUtil.mutListener.listen(23340)) {
            tmpls.put(template);
        }
        if (!ListenerUtil.mutListener.listen(23341)) {
            m.put("tmpls", tmpls);
        }
        if (!ListenerUtil.mutListener.listen(23342)) {
            _updateTemplOrds(m);
        }
        if (!ListenerUtil.mutListener.listen(23343)) {
            save(m);
        }
    }

    /**
     * @throws ConfirmModSchemaException
     */
    public void addTemplate(Model m, JSONObject template) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23345)) {
            // That is Anki's addTemplate method
            if (!isModelNew(m)) {
                if (!ListenerUtil.mutListener.listen(23344)) {
                    mCol.modSchema();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23346)) {
            _addTemplate(m, template);
        }
    }

    public void addTemplateInNewModel(Model m, JSONObject template) {
        if (!ListenerUtil.mutListener.listen(23347)) {
            // asserting the model is new.
            Assert.that(isModelNew(m), "Model was assumed to be new, but is not");
        }
        if (!ListenerUtil.mutListener.listen(23348)) {
            _addTemplate(m, template);
        }
    }

    public void addTemplateModChanged(Model m, JSONObject template) {
        if (!ListenerUtil.mutListener.listen(23349)) {
            // asserting the model is new.
            Assert.that(mCol.schemaChanged(), "Mod was assumed to be already changed, but is not");
        }
        if (!ListenerUtil.mutListener.listen(23350)) {
            _addTemplate(m, template);
        }
    }

    /**
     * Removing a template
     *
     * @return False if removing template would leave orphan notes.
     * @throws ConfirmModSchemaException
     */
    public boolean remTemplate(Model m, JSONObject template) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23356)) {
            if ((ListenerUtil.mutListener.listen(23355) ? (m.getJSONArray("tmpls").length() >= 1) : (ListenerUtil.mutListener.listen(23354) ? (m.getJSONArray("tmpls").length() > 1) : (ListenerUtil.mutListener.listen(23353) ? (m.getJSONArray("tmpls").length() < 1) : (ListenerUtil.mutListener.listen(23352) ? (m.getJSONArray("tmpls").length() != 1) : (ListenerUtil.mutListener.listen(23351) ? (m.getJSONArray("tmpls").length() == 1) : (m.getJSONArray("tmpls").length() <= 1))))))) {
                return false;
            }
        }
        // find cards using this template
        JSONArray tmpls = m.getJSONArray("tmpls");
        int ord = -1;
        if (!ListenerUtil.mutListener.listen(23364)) {
            {
                long _loopCounter581 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23363) ? (i >= tmpls.length()) : (ListenerUtil.mutListener.listen(23362) ? (i <= tmpls.length()) : (ListenerUtil.mutListener.listen(23361) ? (i > tmpls.length()) : (ListenerUtil.mutListener.listen(23360) ? (i != tmpls.length()) : (ListenerUtil.mutListener.listen(23359) ? (i == tmpls.length()) : (i < tmpls.length())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter581", ++_loopCounter581);
                    if (!ListenerUtil.mutListener.listen(23358)) {
                        if (tmpls.getJSONObject(i).equals(template)) {
                            if (!ListenerUtil.mutListener.listen(23357)) {
                                ord = i;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23370)) {
            if ((ListenerUtil.mutListener.listen(23369) ? (ord >= -1) : (ListenerUtil.mutListener.listen(23368) ? (ord <= -1) : (ListenerUtil.mutListener.listen(23367) ? (ord > -1) : (ListenerUtil.mutListener.listen(23366) ? (ord < -1) : (ListenerUtil.mutListener.listen(23365) ? (ord != -1) : (ord == -1))))))) {
                throw new IllegalArgumentException("Invalid template proposed for delete");
            }
        }
        // the code in "isRemTemplateSafe" was in place here in libanki. It is extracted to a method for reuse
        List<Long> cids = getCardIdsForModel(m.getLong("id"), new int[] { ord });
        if (!ListenerUtil.mutListener.listen(23372)) {
            if (cids == null) {
                if (!ListenerUtil.mutListener.listen(23371)) {
                    Timber.d("remTemplate getCardIdsForModel determined it was unsafe to delete the template");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(23373)) {
            // ok to proceed; remove cards
            Timber.d("remTemplate proceeding to delete the template and %d cards", cids.size());
        }
        if (!ListenerUtil.mutListener.listen(23374)) {
            mCol.modSchema();
        }
        if (!ListenerUtil.mutListener.listen(23375)) {
            mCol.remCards(cids);
        }
        if (!ListenerUtil.mutListener.listen(23376)) {
            // shift ordinals
            mCol.getDb().execute("update cards set ord = ord - 1, usn = ?, mod = ? where nid in (select id from notes where mid = ?) and ord > ?", mCol.usn(), mCol.getTime().intTime(), m.getLong("id"), ord);
        }
        if (!ListenerUtil.mutListener.listen(23377)) {
            tmpls = m.getJSONArray("tmpls");
        }
        JSONArray tmpls2 = new JSONArray();
        if (!ListenerUtil.mutListener.listen(23385)) {
            {
                long _loopCounter582 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23384) ? (i >= tmpls.length()) : (ListenerUtil.mutListener.listen(23383) ? (i <= tmpls.length()) : (ListenerUtil.mutListener.listen(23382) ? (i > tmpls.length()) : (ListenerUtil.mutListener.listen(23381) ? (i != tmpls.length()) : (ListenerUtil.mutListener.listen(23380) ? (i == tmpls.length()) : (i < tmpls.length())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter582", ++_loopCounter582);
                    if (!ListenerUtil.mutListener.listen(23378)) {
                        if (template.equals(tmpls.getJSONObject(i))) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23379)) {
                        tmpls2.put(tmpls.getJSONObject(i));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23386)) {
            m.put("tmpls", tmpls2);
        }
        if (!ListenerUtil.mutListener.listen(23387)) {
            _updateTemplOrds(m);
        }
        if (!ListenerUtil.mutListener.listen(23388)) {
            save(m);
        }
        if (!ListenerUtil.mutListener.listen(23389)) {
            Timber.d("remTemplate done working");
        }
        return true;
    }

    /**
     * Extracted from remTemplate so we can test if removing templates is safe without actually removing them
     * This method will either give you all the card ids for the ordinals sent in related to the model sent in *or*
     * it will return null if the result of deleting the ordinals is unsafe because it would leave notes with no cards
     *
     * @param modelId long id of the JSON model
     * @param ords array of ints, each one is the ordinal a the card template in the given model
     * @return null if deleting ords would orphan notes, long[] of related card ids to delete if it is safe
     */
    @Nullable
    public List<Long> getCardIdsForModel(long modelId, int[] ords) {
        String cardIdsToDeleteSql = "select c2.id from cards c2, notes n2 where c2.nid=n2.id and n2.mid = ? and c2.ord  in " + Utils.ids2str(ords);
        List<Long> cids = mCol.getDb().queryLongList(cardIdsToDeleteSql, modelId);
        if (!ListenerUtil.mutListener.listen(23390)) {
            // Timber.d("cardIdsToDeleteSql was ' %s' and got %s", cardIdsToDeleteSql, Utils.ids2str(cids));
            Timber.d("getCardIdsForModel found %s cards to delete for model %s and ords %s", cids.size(), modelId, Utils.ids2str(ords));
        }
        // all notes with this template must have at least two cards, or we could end up creating orphaned notes
        String noteCountPreDeleteSql = "select count(distinct(nid)) from cards where nid in (select id from notes where mid = ?)";
        int preDeleteNoteCount = mCol.getDb().queryScalar(noteCountPreDeleteSql, modelId);
        if (!ListenerUtil.mutListener.listen(23391)) {
            Timber.d("noteCountPreDeleteSql was '%s'", noteCountPreDeleteSql);
        }
        if (!ListenerUtil.mutListener.listen(23392)) {
            Timber.d("preDeleteNoteCount is %s", preDeleteNoteCount);
        }
        String noteCountPostDeleteSql = "select count(distinct(nid)) from cards where nid in (select id from notes where mid = ?) and ord not in " + Utils.ids2str(ords);
        if (!ListenerUtil.mutListener.listen(23393)) {
            Timber.d("noteCountPostDeleteSql was '%s'", noteCountPostDeleteSql);
        }
        int postDeleteNoteCount = mCol.getDb().queryScalar(noteCountPostDeleteSql, modelId);
        if (!ListenerUtil.mutListener.listen(23394)) {
            Timber.d("postDeleteNoteCount would be %s", postDeleteNoteCount);
        }
        if (!ListenerUtil.mutListener.listen(23401)) {
            if ((ListenerUtil.mutListener.listen(23399) ? (preDeleteNoteCount >= postDeleteNoteCount) : (ListenerUtil.mutListener.listen(23398) ? (preDeleteNoteCount <= postDeleteNoteCount) : (ListenerUtil.mutListener.listen(23397) ? (preDeleteNoteCount > postDeleteNoteCount) : (ListenerUtil.mutListener.listen(23396) ? (preDeleteNoteCount < postDeleteNoteCount) : (ListenerUtil.mutListener.listen(23395) ? (preDeleteNoteCount == postDeleteNoteCount) : (preDeleteNoteCount != postDeleteNoteCount))))))) {
                if (!ListenerUtil.mutListener.listen(23400)) {
                    Timber.d("There will be orphan notes if these cards are deleted.");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(23402)) {
            Timber.d("Deleting these cards will not orphan notes.");
        }
        return cids;
    }

    public static void _updateTemplOrds(Model m) {
        JSONArray tmpls = m.getJSONArray("tmpls");
        if (!ListenerUtil.mutListener.listen(23409)) {
            {
                long _loopCounter583 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23408) ? (i >= tmpls.length()) : (ListenerUtil.mutListener.listen(23407) ? (i <= tmpls.length()) : (ListenerUtil.mutListener.listen(23406) ? (i > tmpls.length()) : (ListenerUtil.mutListener.listen(23405) ? (i != tmpls.length()) : (ListenerUtil.mutListener.listen(23404) ? (i == tmpls.length()) : (i < tmpls.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter583", ++_loopCounter583);
                    JSONObject f = tmpls.getJSONObject(i);
                    if (!ListenerUtil.mutListener.listen(23403)) {
                        f.put("ord", i);
                    }
                }
            }
        }
    }

    public void moveTemplate(Model m, JSONObject template, int idx) {
        JSONArray tmpls = m.getJSONArray("tmpls");
        int oldidx = -1;
        ArrayList<JSONObject> l = new ArrayList<>();
        HashMap<Integer, Integer> oldidxs = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(23425)) {
            {
                long _loopCounter584 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23424) ? (i >= tmpls.length()) : (ListenerUtil.mutListener.listen(23423) ? (i <= tmpls.length()) : (ListenerUtil.mutListener.listen(23422) ? (i > tmpls.length()) : (ListenerUtil.mutListener.listen(23421) ? (i != tmpls.length()) : (ListenerUtil.mutListener.listen(23420) ? (i == tmpls.length()) : (i < tmpls.length())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter584", ++_loopCounter584);
                    if (!ListenerUtil.mutListener.listen(23417)) {
                        if (tmpls.getJSONObject(i).equals(template)) {
                            if (!ListenerUtil.mutListener.listen(23410)) {
                                oldidx = i;
                            }
                            if (!ListenerUtil.mutListener.listen(23416)) {
                                if ((ListenerUtil.mutListener.listen(23415) ? (idx >= oldidx) : (ListenerUtil.mutListener.listen(23414) ? (idx <= oldidx) : (ListenerUtil.mutListener.listen(23413) ? (idx > oldidx) : (ListenerUtil.mutListener.listen(23412) ? (idx < oldidx) : (ListenerUtil.mutListener.listen(23411) ? (idx != oldidx) : (idx == oldidx))))))) {
                                    return;
                                }
                            }
                        }
                    }
                    JSONObject t = tmpls.getJSONObject(i);
                    if (!ListenerUtil.mutListener.listen(23418)) {
                        oldidxs.put(t.hashCode(), t.getInt("ord"));
                    }
                    if (!ListenerUtil.mutListener.listen(23419)) {
                        l.add(t);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23426)) {
            l.remove(oldidx);
        }
        if (!ListenerUtil.mutListener.listen(23427)) {
            l.add(idx, template);
        }
        if (!ListenerUtil.mutListener.listen(23428)) {
            m.put("tmpls", new JSONArray(l));
        }
        if (!ListenerUtil.mutListener.listen(23429)) {
            _updateTemplOrds(m);
        }
        // generate change map - We use StringBuilder
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(23430)) {
            tmpls = m.getJSONArray("tmpls");
        }
        if (!ListenerUtil.mutListener.listen(23443)) {
            {
                long _loopCounter585 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23442) ? (i >= tmpls.length()) : (ListenerUtil.mutListener.listen(23441) ? (i <= tmpls.length()) : (ListenerUtil.mutListener.listen(23440) ? (i > tmpls.length()) : (ListenerUtil.mutListener.listen(23439) ? (i != tmpls.length()) : (ListenerUtil.mutListener.listen(23438) ? (i == tmpls.length()) : (i < tmpls.length())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter585", ++_loopCounter585);
                    JSONObject t = tmpls.getJSONObject(i);
                    if (!ListenerUtil.mutListener.listen(23431)) {
                        sb.append("when ord = ").append(oldidxs.get(t.hashCode())).append(" then ").append(t.getInt("ord"));
                    }
                    if (!ListenerUtil.mutListener.listen(23437)) {
                        if (i != (ListenerUtil.mutListener.listen(23435) ? (tmpls.length() % 1) : (ListenerUtil.mutListener.listen(23434) ? (tmpls.length() / 1) : (ListenerUtil.mutListener.listen(23433) ? (tmpls.length() * 1) : (ListenerUtil.mutListener.listen(23432) ? (tmpls.length() + 1) : (tmpls.length() - 1)))))) {
                            if (!ListenerUtil.mutListener.listen(23436)) {
                                sb.append(" ");
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23444)) {
            // apply
            save(m);
        }
        if (!ListenerUtil.mutListener.listen(23445)) {
            mCol.getDb().execute("update cards set ord = (case " + sb + " end),usn=?,mod=? where nid in (select id from notes where mid = ?)", mCol.usn(), mCol.getTime().intTime(), m.getLong("id"));
        }
    }

    // unused upstream as well
    @SuppressWarnings("PMD.UnusedLocalVariable")
    private void _syncTemplates(Model m) {
        ArrayList<Long> rem = mCol.genCards(nids(m), m);
    }

    /**
     * Change a model
     * @param m The model to change.
     * @param nid The notes that the change applies to.
     * @param newModel For replacing the old model with another one. Should be self if the model is not changing
     * @param fmap Map for switching fields. This is ord->ord and there should not be duplicate targets
     * @param cmap Map for switching cards. This is ord->ord and there should not be duplicate targets
     * @throws ConfirmModSchemaException
     */
    public void change(Model m, long nid, Model newModel, Map<Integer, Integer> fmap, Map<Integer, Integer> cmap) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(23446)) {
            mCol.modSchema();
        }
        assert (ListenerUtil.mutListener.listen(23448) ? ((newModel.getLong("id") == m.getLong("id")) && ((ListenerUtil.mutListener.listen(23447) ? (fmap != null || cmap != null) : (fmap != null && cmap != null)))) : ((newModel.getLong("id") == m.getLong("id")) || ((ListenerUtil.mutListener.listen(23447) ? (fmap != null || cmap != null) : (fmap != null && cmap != null)))));
        if (!ListenerUtil.mutListener.listen(23450)) {
            if (fmap != null) {
                if (!ListenerUtil.mutListener.listen(23449)) {
                    _changeNote(nid, newModel, fmap);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23452)) {
            if (cmap != null) {
                if (!ListenerUtil.mutListener.listen(23451)) {
                    _changeCards(nid, m, newModel, cmap);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23453)) {
            mCol.genCards(nid, newModel);
        }
    }

    private void _changeNote(long nid, Model newModel, Map<Integer, Integer> map) {
        int nfields = newModel.getJSONArray("flds").length();
        long mid = newModel.getLong("id");
        String sflds = mCol.getDb().queryString("select flds from notes where id = ?", nid);
        String[] flds = Utils.splitFields(sflds);
        Map<Integer, String> newflds = new HashMap<>(map.size());
        if (!ListenerUtil.mutListener.listen(23455)) {
            {
                long _loopCounter586 = 0;
                for (Entry<Integer, Integer> entry : map.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter586", ++_loopCounter586);
                    if (!ListenerUtil.mutListener.listen(23454)) {
                        newflds.put(entry.getValue(), flds[entry.getKey()]);
                    }
                }
            }
        }
        List<String> flds2 = new ArrayList<>(nfields);
        if (!ListenerUtil.mutListener.listen(23464)) {
            {
                long _loopCounter587 = 0;
                for (int c = 0; (ListenerUtil.mutListener.listen(23463) ? (c >= nfields) : (ListenerUtil.mutListener.listen(23462) ? (c <= nfields) : (ListenerUtil.mutListener.listen(23461) ? (c > nfields) : (ListenerUtil.mutListener.listen(23460) ? (c != nfields) : (ListenerUtil.mutListener.listen(23459) ? (c == nfields) : (c < nfields)))))); ++c) {
                    ListenerUtil.loopListener.listen("_loopCounter587", ++_loopCounter587);
                    if (!ListenerUtil.mutListener.listen(23458)) {
                        if (newflds.containsKey(c)) {
                            if (!ListenerUtil.mutListener.listen(23457)) {
                                flds2.add(newflds.get(c));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23456)) {
                                flds2.add("");
                            }
                        }
                    }
                }
            }
        }
        String joinedFlds = Utils.joinFields(flds2.toArray(new String[flds2.size()]));
        if (!ListenerUtil.mutListener.listen(23465)) {
            mCol.getDb().execute("update notes set flds=?,mid=?,mod=?,usn=? where id = ?", joinedFlds, mid, mCol.getTime().intTime(), mCol.usn(), nid);
        }
        if (!ListenerUtil.mutListener.listen(23466)) {
            mCol.updateFieldCache(new long[] { nid });
        }
    }

    private void _changeCards(long nid, Model oldModel, Model newModel, Map<Integer, Integer> map) {
        List<Object[]> d = new ArrayList<>();
        List<Long> deleted = new ArrayList<>();
        int omType = oldModel.getInt("type");
        int nmType = newModel.getInt("type");
        int nflds = newModel.getJSONArray("tmpls").length();
        try (Cursor cur = mCol.getDb().query("select id, ord from cards where nid = ?", nid)) {
            if (!ListenerUtil.mutListener.listen(23485)) {
                {
                    long _loopCounter588 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter588", ++_loopCounter588);
                        // support mapping them
                        Integer newOrd;
                        long cid = cur.getLong(0);
                        int ord = cur.getInt(1);
                        if ((ListenerUtil.mutListener.listen(23471) ? (omType >= Consts.MODEL_CLOZE) : (ListenerUtil.mutListener.listen(23470) ? (omType <= Consts.MODEL_CLOZE) : (ListenerUtil.mutListener.listen(23469) ? (omType > Consts.MODEL_CLOZE) : (ListenerUtil.mutListener.listen(23468) ? (omType < Consts.MODEL_CLOZE) : (ListenerUtil.mutListener.listen(23467) ? (omType != Consts.MODEL_CLOZE) : (omType == Consts.MODEL_CLOZE))))))) {
                            newOrd = cur.getInt(1);
                            if ((ListenerUtil.mutListener.listen(23476) ? (nmType >= Consts.MODEL_CLOZE) : (ListenerUtil.mutListener.listen(23475) ? (nmType <= Consts.MODEL_CLOZE) : (ListenerUtil.mutListener.listen(23474) ? (nmType > Consts.MODEL_CLOZE) : (ListenerUtil.mutListener.listen(23473) ? (nmType < Consts.MODEL_CLOZE) : (ListenerUtil.mutListener.listen(23472) ? (nmType == Consts.MODEL_CLOZE) : (nmType != Consts.MODEL_CLOZE))))))) {
                                // the destination ord is valid
                                if ((ListenerUtil.mutListener.listen(23481) ? (nflds >= ord) : (ListenerUtil.mutListener.listen(23480) ? (nflds > ord) : (ListenerUtil.mutListener.listen(23479) ? (nflds < ord) : (ListenerUtil.mutListener.listen(23478) ? (nflds != ord) : (ListenerUtil.mutListener.listen(23477) ? (nflds == ord) : (nflds <= ord))))))) {
                                    newOrd = null;
                                }
                            }
                        } else {
                            // mapping from a regular note, so the map should be valid
                            newOrd = map.get(ord);
                        }
                        if (!ListenerUtil.mutListener.listen(23484)) {
                            if (newOrd != null) {
                                if (!ListenerUtil.mutListener.listen(23483)) {
                                    d.add(new Object[] { newOrd, mCol.usn(), mCol.getTime().intTime(), cid });
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(23482)) {
                                    deleted.add(cid);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23486)) {
            mCol.getDb().executeMany("update cards set ord=?,usn=?,mod=? where id=?", d);
        }
        if (!ListenerUtil.mutListener.listen(23487)) {
            mCol.remCards(deleted);
        }
    }

    /**
     * Return a hash of the schema, to see if models are compatible.
     */
    public String scmhash(Model m) {
        StringBuilder s = new StringBuilder();
        JSONArray flds = m.getJSONArray("flds");
        if (!ListenerUtil.mutListener.listen(23489)) {
            {
                long _loopCounter589 = 0;
                for (JSONObject fld : flds.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter589", ++_loopCounter589);
                    if (!ListenerUtil.mutListener.listen(23488)) {
                        s.append(fld.getString("name"));
                    }
                }
            }
        }
        JSONArray tmpls = m.getJSONArray("tmpls");
        if (!ListenerUtil.mutListener.listen(23491)) {
            {
                long _loopCounter590 = 0;
                for (JSONObject t : tmpls.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter590", ++_loopCounter590);
                    if (!ListenerUtil.mutListener.listen(23490)) {
                        s.append(t.getString("name"));
                    }
                }
            }
        }
        return Utils.checksum(s.toString());
    }

    // 'String f' is unused upstream as well
    @SuppressWarnings("PMD.UnusedLocalVariable")
    private Object[] _reqForTemplate(Model m, List<String> flds, JSONObject t) {
        int nbFields = flds.size();
        String[] a = new String[nbFields];
        String[] b = new String[nbFields];
        if (!ListenerUtil.mutListener.listen(23492)) {
            Arrays.fill(a, "ankiflag");
        }
        if (!ListenerUtil.mutListener.listen(23493)) {
            Arrays.fill(b, "");
        }
        int ord = t.getInt("ord");
        String full = mCol._renderQA(1L, m, 1L, ord, "", a, 0).get("q");
        String empty = mCol._renderQA(1L, m, 1L, ord, "", b, 0).get("q");
        if (!ListenerUtil.mutListener.listen(23494)) {
            // if full and empty are the same, the template is invalid and there is no way to satisfy it
            if (full.equals(empty)) {
                return new Object[] { REQ_NONE, new JSONArray(), new JSONArray() };
            }
        }
        String type = REQ_ALL;
        JSONArray req = new JSONArray();
        if (!ListenerUtil.mutListener.listen(23504)) {
            {
                long _loopCounter591 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23503) ? (i >= flds.size()) : (ListenerUtil.mutListener.listen(23502) ? (i <= flds.size()) : (ListenerUtil.mutListener.listen(23501) ? (i > flds.size()) : (ListenerUtil.mutListener.listen(23500) ? (i != flds.size()) : (ListenerUtil.mutListener.listen(23499) ? (i == flds.size()) : (i < flds.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter591", ++_loopCounter591);
                    if (!ListenerUtil.mutListener.listen(23495)) {
                        a[i] = "";
                    }
                    if (!ListenerUtil.mutListener.listen(23497)) {
                        // if no field content appeared, field is required
                        if (!mCol._renderQA(1L, m, 1L, ord, "", a, 0).get("q").contains("ankiflag")) {
                            if (!ListenerUtil.mutListener.listen(23496)) {
                                req.put(i);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23498)) {
                        a[i] = "ankiflag";
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23510)) {
            if ((ListenerUtil.mutListener.listen(23509) ? (req.length() >= 0) : (ListenerUtil.mutListener.listen(23508) ? (req.length() <= 0) : (ListenerUtil.mutListener.listen(23507) ? (req.length() < 0) : (ListenerUtil.mutListener.listen(23506) ? (req.length() != 0) : (ListenerUtil.mutListener.listen(23505) ? (req.length() == 0) : (req.length() > 0))))))) {
                return new Object[] { type, req };
            }
        }
        if (!ListenerUtil.mutListener.listen(23511)) {
            // if there are no required fields, switch to any mode
            type = REQ_ANY;
        }
        if (!ListenerUtil.mutListener.listen(23512)) {
            req = new JSONArray();
        }
        if (!ListenerUtil.mutListener.listen(23522)) {
            {
                long _loopCounter592 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23521) ? (i >= flds.size()) : (ListenerUtil.mutListener.listen(23520) ? (i <= flds.size()) : (ListenerUtil.mutListener.listen(23519) ? (i > flds.size()) : (ListenerUtil.mutListener.listen(23518) ? (i != flds.size()) : (ListenerUtil.mutListener.listen(23517) ? (i == flds.size()) : (i < flds.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter592", ++_loopCounter592);
                    if (!ListenerUtil.mutListener.listen(23513)) {
                        b[i] = "1";
                    }
                    if (!ListenerUtil.mutListener.listen(23515)) {
                        // if not the same as empty, this field can make the card non-blank
                        if (!mCol._renderQA(1L, m, 1L, ord, "", b, 0).get("q").equals(empty)) {
                            if (!ListenerUtil.mutListener.listen(23514)) {
                                req.put(i);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23516)) {
                        b[i] = "";
                    }
                }
            }
        }
        return new Object[] { type, req };
    }

    /**
     * @param m A model
     * @param ord a card type number of this model
     * @param sfld Fields of a note of this model. (Not trimmed)
     * @return Whether this card is empty
     */
    public static boolean emptyCard(Model m, int ord, String[] sfld) throws TemplateError {
        if (!ListenerUtil.mutListener.listen(23523)) {
            if (m.isCloze()) {
                // So computing the full list is almost as efficient as checking for a particular number
                return !_availClozeOrds(m, sfld, false).contains(ord);
            }
        }
        return emptyStandardCard(m.getJSONArray("tmpls").getJSONObject(ord), m.nonEmptyFields(sfld));
    }

    /**
     * @return Whether the standard card is empty
     */
    public static boolean emptyStandardCard(JSONObject tmpl, Set<String> nonEmptyFields) throws TemplateError {
        return ParsedNode.parse_inner(tmpl.getString("qfmt")).template_is_empty(nonEmptyFields);
    }

    /**
     * @param m A model
     * @param sfld Fields of a note
     * @param nodes Nodes used for parsing the variaous templates. Null for cloze
     * @return The index of the cards that are generated. For cloze cards, if no card is generated, then {0}
     */
    public static ArrayList<Integer> availOrds(Model m, String[] sfld, List<ParsedNode> nodes) {
        if (!ListenerUtil.mutListener.listen(23524)) {
            if (m.getInt("type") == Consts.MODEL_CLOZE) {
                return _availClozeOrds(m, sfld);
            }
        }
        return _availStandardOrds(m, sfld, nodes);
    }

    public static ArrayList<Integer> availOrds(Model m, String[] sfld) {
        if (!ListenerUtil.mutListener.listen(23525)) {
            if (m.isCloze()) {
                return _availClozeOrds(m, sfld);
            }
        }
        return _availStandardOrds(m, sfld);
    }

    public static ArrayList<Integer> _availStandardOrds(Model m, String[] sfld) {
        return _availStandardOrds(m, sfld, m.parsedNodes());
    }

    /**
     * Given a joined field string and a standard note type, return available template ordinals
     */
    public static ArrayList<Integer> _availStandardOrds(Model m, String[] sfld, List<ParsedNode> nodes) {
        Set<String> nonEmptyFields = m.nonEmptyFields(sfld);
        ArrayList<Integer> avail = new ArrayList<>(nodes.size());
        if (!ListenerUtil.mutListener.listen(23534)) {
            {
                long _loopCounter593 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23533) ? (i >= nodes.size()) : (ListenerUtil.mutListener.listen(23532) ? (i <= nodes.size()) : (ListenerUtil.mutListener.listen(23531) ? (i > nodes.size()) : (ListenerUtil.mutListener.listen(23530) ? (i != nodes.size()) : (ListenerUtil.mutListener.listen(23529) ? (i == nodes.size()) : (i < nodes.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter593", ++_loopCounter593);
                    ParsedNode node = nodes.get(i);
                    if (!ListenerUtil.mutListener.listen(23528)) {
                        if ((ListenerUtil.mutListener.listen(23526) ? (node != null || !node.template_is_empty(nonEmptyFields)) : (node != null && !node.template_is_empty(nonEmptyFields)))) {
                            if (!ListenerUtil.mutListener.listen(23527)) {
                                avail.add(i);
                            }
                        }
                    }
                }
            }
        }
        return avail;
    }

    /**
     * @param m A note type with cloze
     * @param sflds The fields of a note of type m. (Assume the size of the array is the number of fields)
     * @return The indexes (in increasing order) of cards that should be generated according to req rules.
     */
    public static ArrayList<Integer> _availClozeOrds(Model m, String[] sflds) {
        return _availClozeOrds(m, sflds, true);
    }

    /**
     * Cache of getNamesOfFieldsContainingCloze
     * Computing hash of string is costly. However, hash is cashed in the string object, so this virtually ensure that
     * given a card type, we don't need to recompute the hash.
     */
    private static final WeakHashMap<String, List<String>> namesOfFieldsContainingClozeCache = new WeakHashMap<>();

    /**
     * The name of all fields that are used as cloze in the question.
     * It is not guaranteed that the field found are actually the name of any field of the note type.
     */
    @VisibleForTesting
    protected static List<String> getNamesOfFieldsContainingCloze(String question) {
        if (!ListenerUtil.mutListener.listen(23539)) {
            if (!namesOfFieldsContainingClozeCache.containsKey(question)) {
                List<String> matches = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(23537)) {
                    {
                        long _loopCounter595 = 0;
                        for (Pattern pattern : new Pattern[] { fClozePattern1, fClozePattern2 }) {
                            ListenerUtil.loopListener.listen("_loopCounter595", ++_loopCounter595);
                            Matcher mm = pattern.matcher(question);
                            if (!ListenerUtil.mutListener.listen(23536)) {
                                {
                                    long _loopCounter594 = 0;
                                    while (mm.find()) {
                                        ListenerUtil.loopListener.listen("_loopCounter594", ++_loopCounter594);
                                        if (!ListenerUtil.mutListener.listen(23535)) {
                                            matches.add(mm.group(1));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23538)) {
                    namesOfFieldsContainingClozeCache.put(question, matches);
                }
            }
        }
        return namesOfFieldsContainingClozeCache.get(question);
    }

    /**
     * @param m A note type with cloze
     * @param sflds The fields of a note of type m. (Assume the size of the array is the number of fields)
     * @param allowEmpty Whether we allow to generate at least one card even if they are all empty
     * @return The indexes (in increasing order) of cards that should be generated according to req rules.
     * If empty is not allowed, it will contains ord 1.
     */
    public static ArrayList<Integer> _availClozeOrds(Model m, String[] sflds, boolean allowEmpty) {
        Map<String, Pair<Integer, JSONObject>> map = fieldMap(m);
        String question = m.getJSONArray("tmpls").getJSONObject(0).getString("qfmt");
        Set<Integer> ords = new HashSet<>();
        List<String> matches = getNamesOfFieldsContainingCloze(question);
        if (!ListenerUtil.mutListener.listen(23547)) {
            {
                long _loopCounter597 = 0;
                for (String fname : matches) {
                    ListenerUtil.loopListener.listen("_loopCounter597", ++_loopCounter597);
                    if (!ListenerUtil.mutListener.listen(23540)) {
                        if (!map.containsKey(fname)) {
                            continue;
                        }
                    }
                    int ord = map.get(fname).first;
                    Matcher mm = fClozeOrdPattern.matcher(sflds[ord]);
                    if (!ListenerUtil.mutListener.listen(23546)) {
                        {
                            long _loopCounter596 = 0;
                            while (mm.find()) {
                                ListenerUtil.loopListener.listen("_loopCounter596", ++_loopCounter596);
                                if (!ListenerUtil.mutListener.listen(23545)) {
                                    ords.add((ListenerUtil.mutListener.listen(23544) ? (Integer.parseInt(mm.group(1)) % 1) : (ListenerUtil.mutListener.listen(23543) ? (Integer.parseInt(mm.group(1)) / 1) : (ListenerUtil.mutListener.listen(23542) ? (Integer.parseInt(mm.group(1)) * 1) : (ListenerUtil.mutListener.listen(23541) ? (Integer.parseInt(mm.group(1)) + 1) : (Integer.parseInt(mm.group(1)) - 1))))));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23548)) {
            ords.remove(-1);
        }
        if (!ListenerUtil.mutListener.listen(23550)) {
            if ((ListenerUtil.mutListener.listen(23549) ? (ords.isEmpty() || allowEmpty) : (ords.isEmpty() && allowEmpty))) {
                // empty clozes use first ord
                return new ArrayList<>(Collections.singletonList(0));
            }
        }
        return new ArrayList<>(ords);
    }

    public void beforeUpload() {
        boolean changed = Utils.markAsUploaded(all());
        if (!ListenerUtil.mutListener.listen(23552)) {
            if (changed) {
                if (!ListenerUtil.mutListener.listen(23551)) {
                    save();
                }
            }
        }
    }

    public void setChanged() {
        if (!ListenerUtil.mutListener.listen(23553)) {
            mChanged = true;
        }
    }

    public HashMap<Long, HashMap<Integer, String>> getTemplateNames() {
        HashMap<Long, HashMap<Integer, String>> result = new HashMap<>(mModels.size());
        if (!ListenerUtil.mutListener.listen(23557)) {
            {
                long _loopCounter599 = 0;
                for (Model m : mModels.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter599", ++_loopCounter599);
                    JSONArray templates = m.getJSONArray("tmpls");
                    HashMap<Integer, String> names = new HashMap<>(templates.length());
                    if (!ListenerUtil.mutListener.listen(23555)) {
                        {
                            long _loopCounter598 = 0;
                            for (JSONObject t : templates.jsonObjectIterable()) {
                                ListenerUtil.loopListener.listen("_loopCounter598", ++_loopCounter598);
                                if (!ListenerUtil.mutListener.listen(23554)) {
                                    names.put(t.getInt("ord"), t.getString("name"));
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23556)) {
                        result.put(m.getLong("id"), names);
                    }
                }
            }
        }
        return result;
    }

    /**
     * @return the ID
     */
    public int getId() {
        return mId;
    }

    public HashMap<Long, Model> getModels() {
        return mModels;
    }

    /**
     * @return Number of models
     */
    public int count() {
        return mModels.size();
    }

    /**
     * Validate model entries.
     */
    public boolean validateModel() {
        if (!ListenerUtil.mutListener.listen(23559)) {
            {
                long _loopCounter600 = 0;
                for (Model model : mModels.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter600", ++_loopCounter600);
                    if (!ListenerUtil.mutListener.listen(23558)) {
                        if (!validateBrackets(model)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Check if there is a right bracket for every left bracket.
     */
    private boolean validateBrackets(JSONObject value) {
        String s = value.toString();
        int count = 0;
        boolean inQuotes = false;
        char[] ar = s.toCharArray();
        if (!ListenerUtil.mutListener.listen(23588)) {
            {
                long _loopCounter601 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23587) ? (i >= ar.length) : (ListenerUtil.mutListener.listen(23586) ? (i <= ar.length) : (ListenerUtil.mutListener.listen(23585) ? (i > ar.length) : (ListenerUtil.mutListener.listen(23584) ? (i != ar.length) : (ListenerUtil.mutListener.listen(23583) ? (i == ar.length) : (i < ar.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter601", ++_loopCounter601);
                    char c = ar[i];
                    if (!ListenerUtil.mutListener.listen(23572)) {
                        // if in quotes, do not count
                        if ((ListenerUtil.mutListener.listen(23570) ? (c == '"' || ((ListenerUtil.mutListener.listen(23569) ? ((ListenerUtil.mutListener.listen(23564) ? (i >= 0) : (ListenerUtil.mutListener.listen(23563) ? (i <= 0) : (ListenerUtil.mutListener.listen(23562) ? (i > 0) : (ListenerUtil.mutListener.listen(23561) ? (i < 0) : (ListenerUtil.mutListener.listen(23560) ? (i != 0) : (i == 0)))))) && (ar[(ListenerUtil.mutListener.listen(23568) ? (i % 1) : (ListenerUtil.mutListener.listen(23567) ? (i / 1) : (ListenerUtil.mutListener.listen(23566) ? (i * 1) : (ListenerUtil.mutListener.listen(23565) ? (i + 1) : (i - 1)))))] != '\\')) : ((ListenerUtil.mutListener.listen(23564) ? (i >= 0) : (ListenerUtil.mutListener.listen(23563) ? (i <= 0) : (ListenerUtil.mutListener.listen(23562) ? (i > 0) : (ListenerUtil.mutListener.listen(23561) ? (i < 0) : (ListenerUtil.mutListener.listen(23560) ? (i != 0) : (i == 0)))))) || (ar[(ListenerUtil.mutListener.listen(23568) ? (i % 1) : (ListenerUtil.mutListener.listen(23567) ? (i / 1) : (ListenerUtil.mutListener.listen(23566) ? (i * 1) : (ListenerUtil.mutListener.listen(23565) ? (i + 1) : (i - 1)))))] != '\\'))))) : (c == '"' && ((ListenerUtil.mutListener.listen(23569) ? ((ListenerUtil.mutListener.listen(23564) ? (i >= 0) : (ListenerUtil.mutListener.listen(23563) ? (i <= 0) : (ListenerUtil.mutListener.listen(23562) ? (i > 0) : (ListenerUtil.mutListener.listen(23561) ? (i < 0) : (ListenerUtil.mutListener.listen(23560) ? (i != 0) : (i == 0)))))) && (ar[(ListenerUtil.mutListener.listen(23568) ? (i % 1) : (ListenerUtil.mutListener.listen(23567) ? (i / 1) : (ListenerUtil.mutListener.listen(23566) ? (i * 1) : (ListenerUtil.mutListener.listen(23565) ? (i + 1) : (i - 1)))))] != '\\')) : ((ListenerUtil.mutListener.listen(23564) ? (i >= 0) : (ListenerUtil.mutListener.listen(23563) ? (i <= 0) : (ListenerUtil.mutListener.listen(23562) ? (i > 0) : (ListenerUtil.mutListener.listen(23561) ? (i < 0) : (ListenerUtil.mutListener.listen(23560) ? (i != 0) : (i == 0)))))) || (ar[(ListenerUtil.mutListener.listen(23568) ? (i % 1) : (ListenerUtil.mutListener.listen(23567) ? (i / 1) : (ListenerUtil.mutListener.listen(23566) ? (i * 1) : (ListenerUtil.mutListener.listen(23565) ? (i + 1) : (i - 1)))))] != '\\'))))))) {
                            if (!ListenerUtil.mutListener.listen(23571)) {
                                inQuotes = !inQuotes;
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23573)) {
                        if (inQuotes) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23582)) {
                        switch(c) {
                            case '{':
                                if (!ListenerUtil.mutListener.listen(23574)) {
                                    count++;
                                }
                                break;
                            case '}':
                                if (!ListenerUtil.mutListener.listen(23575)) {
                                    count--;
                                }
                                if (!ListenerUtil.mutListener.listen(23581)) {
                                    if ((ListenerUtil.mutListener.listen(23580) ? (count >= 0) : (ListenerUtil.mutListener.listen(23579) ? (count <= 0) : (ListenerUtil.mutListener.listen(23578) ? (count > 0) : (ListenerUtil.mutListener.listen(23577) ? (count != 0) : (ListenerUtil.mutListener.listen(23576) ? (count == 0) : (count < 0))))))) {
                                        return false;
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        }
        return ((ListenerUtil.mutListener.listen(23593) ? (count >= 0) : (ListenerUtil.mutListener.listen(23592) ? (count <= 0) : (ListenerUtil.mutListener.listen(23591) ? (count > 0) : (ListenerUtil.mutListener.listen(23590) ? (count < 0) : (ListenerUtil.mutListener.listen(23589) ? (count != 0) : (count == 0)))))));
    }
}

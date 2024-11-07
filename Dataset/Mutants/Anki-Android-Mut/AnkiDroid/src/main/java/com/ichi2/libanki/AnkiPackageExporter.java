/**
 * *************************************************************************************
 *  Copyright (c) 2014 Timothy Rae   <perceptualchaos2@gmail.com>                        *
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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.R;
import com.ichi2.anki.exception.ImportExportException;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import timber.log.Timber;
import static com.ichi2.utils.CollectionUtils.addAll;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class Exporter {

    protected final Collection mCol;

    protected Long mDid;

    protected int mCount;

    public Exporter(Collection col) {
        mCol = col;
        if (!ListenerUtil.mutListener.listen(20792)) {
            mDid = null;
        }
    }

    public Exporter(Collection col, Long did) {
        mCol = col;
        if (!ListenerUtil.mutListener.listen(20793)) {
            mDid = did;
        }
    }

    /**
     * card ids of cards in deck self.did if it is set, all ids otherwise.
     */
    public Long[] cardIds() {
        Long[] cids;
        if (mDid == null) {
            cids = Utils.list2ObjectArray(mCol.getDb().queryLongList("select id from cards"));
        } else {
            cids = mCol.getDecks().cids(mDid, true);
        }
        if (!ListenerUtil.mutListener.listen(20794)) {
            mCount = cids.length;
        }
        return cids;
    }
}

@SuppressWarnings({ "PMD.AvoidReassigningParameters", "PMD.DefaultPackage", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.ExcessiveMethodLength", "PMD.EmptyIfStmt", "PMD.CollapsibleIfStatements" })
class AnkiExporter extends Exporter {

    protected boolean mIncludeSched;

    protected boolean mIncludeMedia;

    private Collection mSrc;

    String mMediaDir;

    // Actual capacity will be set when known, if media are imported.
    final ArrayList<String> mMediaFiles = new ArrayList<>(0);

    boolean _v2sched;

    public AnkiExporter(Collection col) {
        super(col);
        if (!ListenerUtil.mutListener.listen(20795)) {
            mIncludeSched = false;
        }
        if (!ListenerUtil.mutListener.listen(20796)) {
            mIncludeMedia = true;
        }
    }

    public void exportInto(String path, Context context) throws JSONException, IOException, ImportExportException {
        if (!ListenerUtil.mutListener.listen(20797)) {
            // create a new collection at the target
            new File(path).delete();
        }
        Collection dst = Storage.Collection(context, path);
        if (!ListenerUtil.mutListener.listen(20798)) {
            mSrc = mCol;
        }
        // find cards
        Long[] cids = cardIds();
        if (!ListenerUtil.mutListener.listen(20799)) {
            // flexible
            dst.close();
        }
        if (!ListenerUtil.mutListener.listen(20800)) {
            Timber.d("Attach DB");
        }
        if (!ListenerUtil.mutListener.listen(20801)) {
            mSrc.getDb().getDatabase().execSQL("ATTACH '" + path + "' AS DST_DB");
        }
        if (!ListenerUtil.mutListener.listen(20802)) {
            // copy cards, noting used nids (as unique set)
            Timber.d("Copy cards");
        }
        if (!ListenerUtil.mutListener.listen(20803)) {
            mSrc.getDb().getDatabase().execSQL("INSERT INTO DST_DB.cards select * from cards where id in " + Utils.ids2str(cids));
        }
        List<Long> uniqueNids = mSrc.getDb().queryLongList("select distinct nid from cards where id in " + Utils.ids2str(cids));
        if (!ListenerUtil.mutListener.listen(20804)) {
            // notes
            Timber.d("Copy notes");
        }
        String strnids = Utils.ids2str(uniqueNids);
        if (!ListenerUtil.mutListener.listen(20805)) {
            mSrc.getDb().getDatabase().execSQL("INSERT INTO DST_DB.notes select * from notes where id in " + strnids);
        }
        if (!ListenerUtil.mutListener.listen(20817)) {
            // remove system tags if not exporting scheduling info
            if (!mIncludeSched) {
                if (!ListenerUtil.mutListener.listen(20806)) {
                    Timber.d("Stripping system tags from list");
                }
                ArrayList<String> srcTags = mSrc.getDb().queryStringList("select tags from notes where id in " + strnids);
                ArrayList<Object[]> args = new ArrayList<>(srcTags.size());
                Object[] arg = new Object[2];
                if (!ListenerUtil.mutListener.listen(20815)) {
                    {
                        long _loopCounter443 = 0;
                        for (int row = 0; (ListenerUtil.mutListener.listen(20814) ? (row >= srcTags.size()) : (ListenerUtil.mutListener.listen(20813) ? (row <= srcTags.size()) : (ListenerUtil.mutListener.listen(20812) ? (row > srcTags.size()) : (ListenerUtil.mutListener.listen(20811) ? (row != srcTags.size()) : (ListenerUtil.mutListener.listen(20810) ? (row == srcTags.size()) : (row < srcTags.size())))))); row++) {
                            ListenerUtil.loopListener.listen("_loopCounter443", ++_loopCounter443);
                            if (!ListenerUtil.mutListener.listen(20807)) {
                                arg[0] = removeSystemTags(srcTags.get(row));
                            }
                            if (!ListenerUtil.mutListener.listen(20808)) {
                                arg[1] = uniqueNids.get(row);
                            }
                            if (!ListenerUtil.mutListener.listen(20809)) {
                                args.add(row, arg);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20816)) {
                    mSrc.getDb().executeMany("UPDATE DST_DB.notes set tags=? where id=?", args);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20818)) {
            // models used by the notes
            Timber.d("Finding models used by notes");
        }
        ArrayList<Long> mids = mSrc.getDb().queryLongList("select distinct mid from DST_DB.notes where id in " + strnids);
        if (!ListenerUtil.mutListener.listen(20828)) {
            // card history and revlog
            if (mIncludeSched) {
                if (!ListenerUtil.mutListener.listen(20824)) {
                    Timber.d("Copy history and revlog");
                }
                if (!ListenerUtil.mutListener.listen(20825)) {
                    mSrc.getDb().getDatabase().execSQL("insert into DST_DB.revlog select * from revlog where cid in " + Utils.ids2str(cids));
                }
                if (!ListenerUtil.mutListener.listen(20826)) {
                    // reopen collection to destination database (different from original python code)
                    mSrc.getDb().getDatabase().execSQL("DETACH DST_DB");
                }
                if (!ListenerUtil.mutListener.listen(20827)) {
                    dst.reopen();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20819)) {
                    Timber.d("Detaching destination db and reopening");
                }
                if (!ListenerUtil.mutListener.listen(20820)) {
                    // first reopen collection to destination database (different from original python code)
                    mSrc.getDb().getDatabase().execSQL("DETACH DST_DB");
                }
                if (!ListenerUtil.mutListener.listen(20821)) {
                    dst.reopen();
                }
                if (!ListenerUtil.mutListener.listen(20822)) {
                    // then need to reset card state
                    Timber.d("Resetting cards");
                }
                if (!ListenerUtil.mutListener.listen(20823)) {
                    dst.getSched().resetCards(cids);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20829)) {
            // models - start with zero
            Timber.d("Copy models");
        }
        if (!ListenerUtil.mutListener.listen(20832)) {
            {
                long _loopCounter444 = 0;
                for (Model m : mSrc.getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter444", ++_loopCounter444);
                    if (!ListenerUtil.mutListener.listen(20831)) {
                        if (mids.contains(m.getLong("id"))) {
                            if (!ListenerUtil.mutListener.listen(20830)) {
                                dst.getModels().update(m);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20833)) {
            // decks
            Timber.d("Copy decks");
        }
        java.util.Collection<Long> dids = null;
        if (!ListenerUtil.mutListener.listen(20836)) {
            if (mDid != null) {
                if (!ListenerUtil.mutListener.listen(20834)) {
                    dids = new HashSet<>(mSrc.getDecks().children(mDid).values());
                }
                if (!ListenerUtil.mutListener.listen(20835)) {
                    dids.add(mDid);
                }
            }
        }
        JSONObject dconfs = new JSONObject();
        if (!ListenerUtil.mutListener.listen(20847)) {
            {
                long _loopCounter445 = 0;
                for (Deck d : mSrc.getDecks().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter445", ++_loopCounter445);
                    if (!ListenerUtil.mutListener.listen(20837)) {
                        if ("1".equals(d.getString("id"))) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20839)) {
                        if ((ListenerUtil.mutListener.listen(20838) ? (dids != null || !dids.contains(d.getLong("id"))) : (dids != null && !dids.contains(d.getLong("id"))))) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20843)) {
                        if ((ListenerUtil.mutListener.listen(20840) ? (d.isStd() || d.getLong("conf") != 1L) : (d.isStd() && d.getLong("conf") != 1L))) {
                            if (!ListenerUtil.mutListener.listen(20842)) {
                                if (mIncludeSched) {
                                    if (!ListenerUtil.mutListener.listen(20841)) {
                                        dconfs.put(Long.toString(d.getLong("conf")), true);
                                    }
                                }
                            }
                        }
                    }
                    Deck destinationDeck = d.deepClone();
                    if (!ListenerUtil.mutListener.listen(20845)) {
                        if (!mIncludeSched) {
                            if (!ListenerUtil.mutListener.listen(20844)) {
                                // scheduling not included, so reset deck settings to default
                                destinationDeck.put("conf", 1);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20846)) {
                        dst.getDecks().update(destinationDeck);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20848)) {
            // copy used deck confs
            Timber.d("Copy deck options");
        }
        if (!ListenerUtil.mutListener.listen(20851)) {
            {
                long _loopCounter446 = 0;
                for (DeckConfig dc : mSrc.getDecks().allConf()) {
                    ListenerUtil.loopListener.listen("_loopCounter446", ++_loopCounter446);
                    if (!ListenerUtil.mutListener.listen(20850)) {
                        if (dconfs.has(dc.getString("id"))) {
                            if (!ListenerUtil.mutListener.listen(20849)) {
                                dst.getDecks().updateConf(dc);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20852)) {
            // find used media
            Timber.d("Find used media");
        }
        JSONObject media = new JSONObject();
        if (!ListenerUtil.mutListener.listen(20853)) {
            mMediaDir = mSrc.getMedia().dir();
        }
        if (!ListenerUtil.mutListener.listen(20870)) {
            if (mIncludeMedia) {
                ArrayList<Long> mid = mSrc.getDb().queryLongList("select mid from notes where id in " + strnids);
                ArrayList<String> flds = mSrc.getDb().queryStringList("select flds from notes where id in " + strnids);
                if (!ListenerUtil.mutListener.listen(20862)) {
                    {
                        long _loopCounter448 = 0;
                        for (int idx = 0; (ListenerUtil.mutListener.listen(20861) ? (idx >= mid.size()) : (ListenerUtil.mutListener.listen(20860) ? (idx <= mid.size()) : (ListenerUtil.mutListener.listen(20859) ? (idx > mid.size()) : (ListenerUtil.mutListener.listen(20858) ? (idx != mid.size()) : (ListenerUtil.mutListener.listen(20857) ? (idx == mid.size()) : (idx < mid.size())))))); idx++) {
                            ListenerUtil.loopListener.listen("_loopCounter448", ++_loopCounter448);
                            if (!ListenerUtil.mutListener.listen(20856)) {
                                {
                                    long _loopCounter447 = 0;
                                    for (String file : mSrc.getMedia().filesInStr(mid.get(idx), flds.get(idx))) {
                                        ListenerUtil.loopListener.listen("_loopCounter447", ++_loopCounter447);
                                        if (!ListenerUtil.mutListener.listen(20854)) {
                                            // skip files in subdirs
                                            if (file.contains(File.separator)) {
                                                continue;
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(20855)) {
                                            media.put(file, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20869)) {
                    if (mMediaDir != null) {
                        if (!ListenerUtil.mutListener.listen(20868)) {
                            {
                                long _loopCounter450 = 0;
                                for (File f : new File(mMediaDir).listFiles()) {
                                    ListenerUtil.loopListener.listen("_loopCounter450", ++_loopCounter450);
                                    if (!ListenerUtil.mutListener.listen(20863)) {
                                        if (f.isDirectory()) {
                                            continue;
                                        }
                                    }
                                    String fname = f.getName();
                                    if (!ListenerUtil.mutListener.listen(20867)) {
                                        if (fname.startsWith("_")) {
                                            if (!ListenerUtil.mutListener.listen(20866)) {
                                                {
                                                    long _loopCounter449 = 0;
                                                    // Loop through every model that will be exported, and check if it contains a reference to f
                                                    for (JSONObject model : mSrc.getModels().all()) {
                                                        ListenerUtil.loopListener.listen("_loopCounter449", ++_loopCounter449);
                                                        if (!ListenerUtil.mutListener.listen(20865)) {
                                                            if (_modelHasMedia(model, fname)) {
                                                                if (!ListenerUtil.mutListener.listen(20864)) {
                                                                    media.put(fname, true);
                                                                }
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        JSONArray keys = media.names();
        if (!ListenerUtil.mutListener.listen(20873)) {
            if (keys != null) {
                if (!ListenerUtil.mutListener.listen(20871)) {
                    mMediaFiles.ensureCapacity(keys.length());
                }
                if (!ListenerUtil.mutListener.listen(20872)) {
                    addAll(mMediaFiles, keys.stringIterable());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20874)) {
            Timber.d("Cleanup");
        }
        if (!ListenerUtil.mutListener.listen(20875)) {
            dst.setCrt(mSrc.getCrt());
        }
        if (!ListenerUtil.mutListener.listen(20876)) {
            // todo: tags?
            mCount = dst.cardCount();
        }
        if (!ListenerUtil.mutListener.listen(20877)) {
            dst.setMod();
        }
        if (!ListenerUtil.mutListener.listen(20878)) {
            postExport();
        }
        if (!ListenerUtil.mutListener.listen(20879)) {
            dst.close();
        }
    }

    /**
     * Returns whether or not the specified model contains a reference to the given media file.
     * In order to ensure relatively fast operation we only check if the styling, front, back templates *contain* fname,
     * and thus must allow for occasional false positives.
     * @param model the model to scan
     * @param fname the name of the media file to check for
     * @return
     * @throws JSONException
     */
    private boolean _modelHasMedia(JSONObject model, String fname) throws JSONException {
        if (!ListenerUtil.mutListener.listen(20881)) {
            // Don't crash if the model is null
            if (model == null) {
                if (!ListenerUtil.mutListener.listen(20880)) {
                    Timber.w("_modelHasMedia given null model");
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(20882)) {
            // First check the styling
            if (model.getString("css").contains(fname)) {
                return true;
            }
        }
        // If not there then check the templates
        JSONArray tmpls = model.getJSONArray("tmpls");
        if (!ListenerUtil.mutListener.listen(20885)) {
            {
                long _loopCounter451 = 0;
                for (JSONObject tmpl : tmpls.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter451", ++_loopCounter451);
                    if (!ListenerUtil.mutListener.listen(20884)) {
                        if ((ListenerUtil.mutListener.listen(20883) ? (tmpl.getString("qfmt").contains(fname) && tmpl.getString("afmt").contains(fname)) : (tmpl.getString("qfmt").contains(fname) || tmpl.getString("afmt").contains(fname)))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * override to apply customizations to the deck before it's closed, such as update the deck description
     */
    protected void postExport() {
    }

    private String removeSystemTags(String tags) {
        return mSrc.getTags().remFromStr("marked leech", tags);
    }

    public void setIncludeSched(boolean includeSched) {
        if (!ListenerUtil.mutListener.listen(20886)) {
            mIncludeSched = includeSched;
        }
    }

    public void setIncludeMedia(boolean includeMedia) {
        if (!ListenerUtil.mutListener.listen(20887)) {
            mIncludeMedia = includeMedia;
        }
    }

    public void setDid(Long did) {
        if (!ListenerUtil.mutListener.listen(20888)) {
            mDid = did;
        }
    }
}

public final class AnkiPackageExporter extends AnkiExporter {

    public AnkiPackageExporter(Collection col) {
        super(col);
    }

    @Override
    public void exportInto(String path, Context context) throws IOException, JSONException, ImportExportException {
        if (!ListenerUtil.mutListener.listen(20889)) {
            // sched info+v2 scheduler not compatible w/ older clients
            Timber.i("Starting export into %s", path);
        }
        if (!ListenerUtil.mutListener.listen(20896)) {
            _v2sched = (ListenerUtil.mutListener.listen(20895) ? ((ListenerUtil.mutListener.listen(20894) ? (mCol.schedVer() >= 1) : (ListenerUtil.mutListener.listen(20893) ? (mCol.schedVer() <= 1) : (ListenerUtil.mutListener.listen(20892) ? (mCol.schedVer() > 1) : (ListenerUtil.mutListener.listen(20891) ? (mCol.schedVer() < 1) : (ListenerUtil.mutListener.listen(20890) ? (mCol.schedVer() == 1) : (mCol.schedVer() != 1)))))) || mIncludeSched) : ((ListenerUtil.mutListener.listen(20894) ? (mCol.schedVer() >= 1) : (ListenerUtil.mutListener.listen(20893) ? (mCol.schedVer() <= 1) : (ListenerUtil.mutListener.listen(20892) ? (mCol.schedVer() > 1) : (ListenerUtil.mutListener.listen(20891) ? (mCol.schedVer() < 1) : (ListenerUtil.mutListener.listen(20890) ? (mCol.schedVer() == 1) : (mCol.schedVer() != 1)))))) && mIncludeSched));
        }
        // open a zip file
        ZipFile z = new ZipFile(path);
        // if all decks and scheduling included, full export
        JSONObject media;
        if ((ListenerUtil.mutListener.listen(20897) ? (mIncludeSched || mDid == null) : (mIncludeSched && mDid == null))) {
            media = exportVerbatim(z, context);
        } else {
            // otherwise, filter
            media = exportFiltered(z, path, context);
        }
        if (!ListenerUtil.mutListener.listen(20898)) {
            // media map
            z.writeStr("media", Utils.jsonToString(media));
        }
        if (!ListenerUtil.mutListener.listen(20899)) {
            z.close();
        }
    }

    private JSONObject exportVerbatim(ZipFile z, Context context) throws IOException {
        if (!ListenerUtil.mutListener.listen(20900)) {
            // close our deck & write it into the zip file, and reopen
            mCount = mCol.cardCount();
        }
        if (!ListenerUtil.mutListener.listen(20901)) {
            mCol.close();
        }
        if (!ListenerUtil.mutListener.listen(20905)) {
            if (!_v2sched) {
                if (!ListenerUtil.mutListener.listen(20904)) {
                    z.write(mCol.getPath(), CollectionHelper.COLLECTION_FILENAME);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20902)) {
                    _addDummyCollection(z, context);
                }
                if (!ListenerUtil.mutListener.listen(20903)) {
                    z.write(mCol.getPath(), "collection.anki21");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20906)) {
            mCol.reopen();
        }
        // copy all media
        if (!mIncludeMedia) {
            return new JSONObject();
        }
        File mdir = new File(mCol.getMedia().dir());
        if ((ListenerUtil.mutListener.listen(20907) ? (mdir.exists() || mdir.isDirectory()) : (mdir.exists() && mdir.isDirectory()))) {
            File[] mediaFiles = mdir.listFiles();
            return _exportMedia(z, mediaFiles, ValidateFiles.SKIP_VALIDATION);
        } else {
            return new JSONObject();
        }
    }

    private JSONObject _exportMedia(ZipFile z, ArrayList<String> fileNames, String mdir) throws IOException {
        int size = fileNames.size();
        int i = 0;
        File[] files = new File[size];
        if (!ListenerUtil.mutListener.listen(20909)) {
            {
                long _loopCounter452 = 0;
                for (String fileName : fileNames) {
                    ListenerUtil.loopListener.listen("_loopCounter452", ++_loopCounter452);
                    if (!ListenerUtil.mutListener.listen(20908)) {
                        files[i++] = new File(mdir, fileName);
                    }
                }
            }
        }
        return _exportMedia(z, files, ValidateFiles.VALIDATE);
    }

    private JSONObject _exportMedia(ZipFile z, File[] files, ValidateFiles validateFiles) throws IOException {
        int c = 0;
        JSONObject media = new JSONObject();
        if (!ListenerUtil.mutListener.listen(20917)) {
            {
                long _loopCounter453 = 0;
                for (File file : files) {
                    ListenerUtil.loopListener.listen("_loopCounter453", ++_loopCounter453);
                    if (!ListenerUtil.mutListener.listen(20912)) {
                        // todo: deflate SVG files, as in dae/anki@a5b0852360b132c0d04094f5ca8f1933f64d7c7e
                        if ((ListenerUtil.mutListener.listen(20910) ? (validateFiles == ValidateFiles.VALIDATE || !file.exists()) : (validateFiles == ValidateFiles.VALIDATE && !file.exists()))) {
                            if (!ListenerUtil.mutListener.listen(20911)) {
                                // Anki 2.1.30 does the same
                                Timber.d("Skipping missing file %s", file);
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20913)) {
                        z.write(file.getPath(), Integer.toString(c));
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(20915)) {
                            media.put(Integer.toString(c), file.getName());
                        }
                        if (!ListenerUtil.mutListener.listen(20916)) {
                            c++;
                        }
                    } catch (JSONException e) {
                        if (!ListenerUtil.mutListener.listen(20914)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return media;
    }

    private JSONObject exportFiltered(ZipFile z, String path, Context context) throws IOException, JSONException, ImportExportException {
        // export into the anki2 file
        String colfile = path.replace(".apkg", ".anki2");
        if (!ListenerUtil.mutListener.listen(20918)) {
            super.exportInto(colfile, context);
        }
        if (!ListenerUtil.mutListener.listen(20919)) {
            z.write(colfile, CollectionHelper.COLLECTION_FILENAME);
        }
        if (!ListenerUtil.mutListener.listen(20920)) {
            // and media
            prepareMedia();
        }
        JSONObject media = _exportMedia(z, mMediaFiles, mCol.getMedia().dir());
        if (!ListenerUtil.mutListener.listen(20921)) {
            // tidy up intermediate files
            SQLiteDatabase.deleteDatabase(new File(colfile));
        }
        if (!ListenerUtil.mutListener.listen(20922)) {
            SQLiteDatabase.deleteDatabase(new File(path.replace(".apkg", ".media.ad.db2")));
        }
        String tempPath = path.replace(".apkg", ".media");
        File file = new File(tempPath);
        if (!ListenerUtil.mutListener.listen(20924)) {
            if (file.exists()) {
                String deleteCmd = "rm -r " + tempPath;
                Runtime runtime = Runtime.getRuntime();
                try {
                    if (!ListenerUtil.mutListener.listen(20923)) {
                        runtime.exec(deleteCmd);
                    }
                } catch (IOException ignored) {
                }
            }
        }
        return media;
    }

    protected void prepareMedia() {
    }

    // data they don't understand
    private void _addDummyCollection(ZipFile zip, Context context) throws IOException {
        File f = File.createTempFile("dummy", ".anki2");
        String path = f.getAbsolutePath();
        if (!ListenerUtil.mutListener.listen(20925)) {
            f.delete();
        }
        Collection c = Storage.Collection(context, path);
        Note n = c.newNote();
        if (!ListenerUtil.mutListener.listen(20926)) {
            // The field names for those are localised during creation, so we need to consider that when creating dummy note
            n.setItem(context.getString(R.string.front_field_name), context.getString(R.string.export_v2_dummy_note));
        }
        if (!ListenerUtil.mutListener.listen(20927)) {
            c.addNote(n);
        }
        if (!ListenerUtil.mutListener.listen(20928)) {
            c.save();
        }
        if (!ListenerUtil.mutListener.listen(20929)) {
            c.close();
        }
        if (!ListenerUtil.mutListener.listen(20930)) {
            zip.write(f.getAbsolutePath(), CollectionHelper.COLLECTION_FILENAME);
        }
    }

    /**
     * Whether media files should be validated before being added to the zip
     */
    private enum ValidateFiles {

        VALIDATE, SKIP_VALIDATION
    }
}

/**
 * Wrapper around standard Python zip class used in this module for exporting to APKG
 *
 * @author Tim
 */
class ZipFile {

    private final int BUFFER_SIZE = 1024;

    private ZipArchiveOutputStream mZos;

    public ZipFile(String path) throws FileNotFoundException {
        if (!ListenerUtil.mutListener.listen(20931)) {
            mZos = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
        }
    }

    public void write(String path, String entry) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path), BUFFER_SIZE);
        ZipArchiveEntry ze = new ZipArchiveEntry(entry);
        if (!ListenerUtil.mutListener.listen(20932)) {
            writeEntry(bis, ze);
        }
    }

    public void writeStr(String entry, String value) throws IOException {
        // TODO: Does this work with abnormal characters?
        InputStream is = new ByteArrayInputStream(value.getBytes());
        BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);
        ZipArchiveEntry ze = new ZipArchiveEntry(entry);
        if (!ListenerUtil.mutListener.listen(20933)) {
            writeEntry(bis, ze);
        }
    }

    private void writeEntry(BufferedInputStream bis, ZipArchiveEntry ze) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        if (!ListenerUtil.mutListener.listen(20934)) {
            mZos.putArchiveEntry(ze);
        }
        int len;
        if (!ListenerUtil.mutListener.listen(20941)) {
            {
                long _loopCounter454 = 0;
                while ((ListenerUtil.mutListener.listen(20940) ? ((len = bis.read(buf, 0, BUFFER_SIZE)) >= -1) : (ListenerUtil.mutListener.listen(20939) ? ((len = bis.read(buf, 0, BUFFER_SIZE)) <= -1) : (ListenerUtil.mutListener.listen(20938) ? ((len = bis.read(buf, 0, BUFFER_SIZE)) > -1) : (ListenerUtil.mutListener.listen(20937) ? ((len = bis.read(buf, 0, BUFFER_SIZE)) < -1) : (ListenerUtil.mutListener.listen(20936) ? ((len = bis.read(buf, 0, BUFFER_SIZE)) == -1) : ((len = bis.read(buf, 0, BUFFER_SIZE)) != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter454", ++_loopCounter454);
                    if (!ListenerUtil.mutListener.listen(20935)) {
                        mZos.write(buf, 0, len);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20942)) {
            mZos.closeArchiveEntry();
        }
        if (!ListenerUtil.mutListener.listen(20943)) {
            bis.close();
        }
    }

    public void close() {
        try {
            if (!ListenerUtil.mutListener.listen(20945)) {
                mZos.close();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(20944)) {
                e.printStackTrace();
            }
        }
    }
}

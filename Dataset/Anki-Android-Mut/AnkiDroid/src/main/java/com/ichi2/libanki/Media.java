/**
 * *************************************************************************************
 *  Copyright (c) 2011 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2014 Houssam Salem <houssam.salem.au@gmail.com>                        *
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

import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.libanki.exception.EmptyMediaException;
import com.ichi2.libanki.template.TemplateFilters;
import com.ichi2.utils.Assert;
import com.ichi2.utils.ExceptionUtil;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import androidx.annotation.NonNull;
import timber.log.Timber;
import static java.lang.Math.min;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Media manager - handles the addition and removal of media files from the media directory (collection.media) and
 * maintains the media database (collection.media.ad.db2) which is used to determine the state of files for syncing.
 * Note that the media database has an additional prefix for AnkiDroid (.ad) to avoid any potential issues caused by
 * users copying the file to the desktop client and vice versa.
 * <p>
 * Unlike the python version of this module, we do not (and cannot) modify the current working directory (CWD) before
 * performing operations on media files. In python, the CWD is changed to the media directory, allowing it to easily
 * refer to the files in the media directory by name only. In Java, we must be cautious about when to specify the full
 * path to the file and when we need to use the filename only. In general, when we refer to a file on disk (i.e.,
 * creating a new File() object), we must include the full path. Use the dir() method to make this step easier.<br>
 * E.g: new File(dir(), "filename.jpg")
 */
@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.ExcessiveMethodLength", "PMD.OneDeclarationPerLine", "PMD.SwitchStmtsShouldHaveDefault", "PMD.EmptyIfStmt", "PMD.SimplifyBooleanReturns", "PMD.CollapsibleIfStatements" })
public class Media {

    private static final Pattern fIllegalCharReg = Pattern.compile("[><:\"/?*^\\\\|\\x00\\r\\n]");

    private static final Pattern fRemotePattern = Pattern.compile("(https?|ftp)://");

    /**
     * Group 1 = Contents of [sound:] tag <br>
     * Group 2 = "fname"
     */
    private static final Pattern fSoundRegexps = Pattern.compile("(?i)(\\[sound:([^]]+)])");

    /**
     * Group 1 = Contents of <img> tag <br>
     * Group 2 = "str" <br>
     * Group 3 = "fname" <br>
     * Group 4 = Backreference to "str" (i.e., same type of quote character)
     */
    private static final Pattern fImgRegExpQ = Pattern.compile("(?i)(<img[^>]* src=([\"'])([^>]+?)(\\2)[^>]*>)");

    /**
     * Group 1 = Contents of <img> tag <br>
     * Group 2 = "fname"
     */
    private static final Pattern fImgRegExpU = Pattern.compile("(?i)(<img[^>]* src=(?!['\"])([^ >]+)[^>]*?>)");

    public static final List<Pattern> mRegexps = Arrays.asList(fSoundRegexps, fImgRegExpQ, fImgRegExpU);

    private final Collection mCol;

    private final String mDir;

    private DB mDb;

    public Media(Collection col, boolean server) {
        mCol = col;
        if (server) {
            mDir = null;
            return;
        }
        // media directory
        mDir = getCollectionMediaPath(col.getPath());
        File fd = new File(mDir);
        if (!ListenerUtil.mutListener.listen(22840)) {
            if (!fd.exists()) {
                if (!ListenerUtil.mutListener.listen(22839)) {
                    if (!fd.mkdir()) {
                        if (!ListenerUtil.mutListener.listen(22838)) {
                            Timber.e("Cannot create media directory: %s", mDir);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22841)) {
            // change database
            connect();
        }
    }

    @NonNull
    public static String getCollectionMediaPath(String collectionPath) {
        return collectionPath.replaceFirst("\\.anki2$", ".media");
    }

    public void connect() {
        if (!ListenerUtil.mutListener.listen(22842)) {
            if (mCol.getServer()) {
                return;
            }
        }
        // the db to the desktop or vice versa.
        String path = dir() + ".ad.db2";
        File dbFile = new File(path);
        boolean create = !(dbFile.exists());
        if (!ListenerUtil.mutListener.listen(22843)) {
            mDb = new DB(path);
        }
        if (!ListenerUtil.mutListener.listen(22845)) {
            if (create) {
                if (!ListenerUtil.mutListener.listen(22844)) {
                    _initDB();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22846)) {
            maybeUpgrade();
        }
    }

    public void _initDB() {
        String sql = "create table media (\n" + " fname text not null primary key,\n" + " csum text,           -- null indicates deleted file\n" + " mtime int not null,  -- zero if deleted\n" + " dirty int not null\n" + ");\n" + "create index idx_media_dirty on media (dirty);\n" + "create table meta (dirMod int, lastUsn int); insert into meta values (0, 0);";
        if (!ListenerUtil.mutListener.listen(22847)) {
            mDb.executeScript(sql);
        }
    }

    public void maybeUpgrade() {
        String oldpath = dir() + ".db";
        File oldDbFile = new File(oldpath);
        if (!ListenerUtil.mutListener.listen(22859)) {
            if (oldDbFile.exists()) {
                if (!ListenerUtil.mutListener.listen(22848)) {
                    mDb.execute(String.format(Locale.US, "attach \"%s\" as old", oldpath));
                }
                try {
                    String sql = "insert into media\n" + " select m.fname, csum, mod, ifnull((select 1 from log l2 where l2.fname=m.fname), 0) as dirty\n" + " from old.media m\n" + " left outer join old.log l using (fname)\n" + " union\n" + " select fname, null, 0, 1 from old.log where type=" + Consts.CARD_TYPE_LRN + ";";
                    if (!ListenerUtil.mutListener.listen(22851)) {
                        mDb.execute(sql);
                    }
                    if (!ListenerUtil.mutListener.listen(22852)) {
                        mDb.execute("delete from meta");
                    }
                    if (!ListenerUtil.mutListener.listen(22853)) {
                        mDb.execute("insert into meta select dirMod, usn from old.meta");
                    }
                    if (!ListenerUtil.mutListener.listen(22854)) {
                        mDb.commit();
                    }
                } catch (Exception e) {
                    // if we couldn't import the old db for some reason, just start anew
                    StringWriter sw = new StringWriter();
                    if (!ListenerUtil.mutListener.listen(22849)) {
                        e.printStackTrace(new PrintWriter(sw));
                    }
                    if (!ListenerUtil.mutListener.listen(22850)) {
                        mCol.log("failed to import old media db:" + sw.toString());
                    }
                }
                if (!ListenerUtil.mutListener.listen(22855)) {
                    mDb.execute("detach old");
                }
                File newDbFile = new File(oldpath + ".old");
                if (!ListenerUtil.mutListener.listen(22857)) {
                    if (newDbFile.exists()) {
                        if (!ListenerUtil.mutListener.listen(22856)) {
                            newDbFile.delete();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22858)) {
                    oldDbFile.renameTo(newDbFile);
                }
            }
        }
    }

    public void close() {
        if (!ListenerUtil.mutListener.listen(22860)) {
            if (mCol.getServer()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22861)) {
            mDb.close();
        }
        if (!ListenerUtil.mutListener.listen(22862)) {
            mDb = null;
        }
    }

    private void _deleteDB() {
        String path = mDb.getPath();
        if (!ListenerUtil.mutListener.listen(22863)) {
            close();
        }
        if (!ListenerUtil.mutListener.listen(22864)) {
            (new File(path)).delete();
        }
        if (!ListenerUtil.mutListener.listen(22865)) {
            connect();
        }
    }

    public String dir() {
        return mDir;
    }

    /**
     * In AnkiDroid, adding a media file will not only copy it to the media directory, but will also insert an entry
     * into the media database marking it as a new addition.
     */
    public String addFile(File ofile) throws IOException, EmptyMediaException {
        if (!ListenerUtil.mutListener.listen(22872)) {
            if ((ListenerUtil.mutListener.listen(22871) ? (ofile == null && (ListenerUtil.mutListener.listen(22870) ? (ofile.length() >= 0) : (ListenerUtil.mutListener.listen(22869) ? (ofile.length() <= 0) : (ListenerUtil.mutListener.listen(22868) ? (ofile.length() > 0) : (ListenerUtil.mutListener.listen(22867) ? (ofile.length() < 0) : (ListenerUtil.mutListener.listen(22866) ? (ofile.length() != 0) : (ofile.length() == 0))))))) : (ofile == null || (ListenerUtil.mutListener.listen(22870) ? (ofile.length() >= 0) : (ListenerUtil.mutListener.listen(22869) ? (ofile.length() <= 0) : (ListenerUtil.mutListener.listen(22868) ? (ofile.length() > 0) : (ListenerUtil.mutListener.listen(22867) ? (ofile.length() < 0) : (ListenerUtil.mutListener.listen(22866) ? (ofile.length() != 0) : (ofile.length() == 0))))))))) {
                throw new EmptyMediaException();
            }
        }
        String fname = writeData(ofile);
        if (!ListenerUtil.mutListener.listen(22873)) {
            markFileAdd(fname);
        }
        return fname;
    }

    /**
     * Copy a file to the media directory and return the filename it was stored as.
     * <p>
     * Unlike the python version of this method, we don't read the file into memory as a string. All our operations are
     * done on streams opened on the file, so there is no second parameter for the string object here.
     */
    private String writeData(File ofile) throws IOException {
        // get the file name
        String fname = ofile.getName();
        if (!ListenerUtil.mutListener.listen(22874)) {
            // make sure we write it in NFC form and return an NFC-encoded reference
            fname = Utils.nfcNormalized(fname);
        }
        // ensure it's a valid finename
        String base = cleanFilename(fname);
        String[] split = Utils.splitFilename(base);
        String root = split[0];
        String ext = split[1];
        // find the first available name
        String csum = Utils.fileChecksum(ofile);
        {
            long _loopCounter543 = 0;
            while (true) {
                ListenerUtil.loopListener.listen("_loopCounter543", ++_loopCounter543);
                if (!ListenerUtil.mutListener.listen(22875)) {
                    fname = root + ext;
                }
                File path = new File(dir(), fname);
                // if it doesn't exist, copy it directly
                if (!path.exists()) {
                    if (!ListenerUtil.mutListener.listen(22876)) {
                        Utils.copyFile(ofile, path);
                    }
                    return fname;
                }
                // if it's identical, reuse
                if (Utils.fileChecksum(path).equals(csum)) {
                    return fname;
                }
                // otherwise, increment the index in the filename
                Pattern reg = Pattern.compile(" \\((\\d+)\\)$");
                Matcher m = reg.matcher(root);
                if (!ListenerUtil.mutListener.listen(22883)) {
                    if (!m.find()) {
                        if (!ListenerUtil.mutListener.listen(22882)) {
                            root = root + " (1)";
                        }
                    } else {
                        int n = Integer.parseInt(m.group(1));
                        if (!ListenerUtil.mutListener.listen(22881)) {
                            root = String.format(Locale.US, " (%d)", (ListenerUtil.mutListener.listen(22880) ? (n % 1) : (ListenerUtil.mutListener.listen(22879) ? (n / 1) : (ListenerUtil.mutListener.listen(22878) ? (n * 1) : (ListenerUtil.mutListener.listen(22877) ? (n - 1) : (n + 1))))));
                        }
                    }
                }
            }
        }
    }

    public List<String> filesInStr(Long mid, String string) {
        return filesInStr(mid, string, false);
    }

    /**
     * Extract media filenames from an HTML string.
     *
     * @param string The string to scan for media filenames ([sound:...] or <img...>).
     * @param includeRemote If true will also include external http/https/ftp urls.
     * @return A list containing all the sound and image filenames found in the input string.
     */
    public List<String> filesInStr(Long mid, String string, boolean includeRemote) {
        List<String> l = new ArrayList<>();
        Model model = mCol.getModels().get(mid);
        List<String> strings = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22887)) {
            if ((ListenerUtil.mutListener.listen(22884) ? (model.isCloze() || string.contains("{{c")) : (model.isCloze() && string.contains("{{c")))) {
                if (!ListenerUtil.mutListener.listen(22886)) {
                    // possibilities so we can render latex
                    strings = _expandClozes(string);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22885)) {
                    strings.add(string);
                }
            }
        }
        {
            long _loopCounter546 = 0;
            for (String s : strings) {
                ListenerUtil.loopListener.listen("_loopCounter546", ++_loopCounter546);
                // handle latex
                s = LaTeX.mungeQA(s, mCol, model);
                // extract filenames
                Matcher m;
                {
                    long _loopCounter545 = 0;
                    for (Pattern p : mRegexps) {
                        ListenerUtil.loopListener.listen("_loopCounter545", ++_loopCounter545);
                        // the index based on which pattern we are using
                        int fnameIdx = p.equals(fSoundRegexps) ? 2 : p.equals(fImgRegExpU) ? 2 : 3;
                        m = p.matcher(s);
                        if (!ListenerUtil.mutListener.listen(22891)) {
                            {
                                long _loopCounter544 = 0;
                                while (m.find()) {
                                    ListenerUtil.loopListener.listen("_loopCounter544", ++_loopCounter544);
                                    String fname = m.group(fnameIdx);
                                    boolean isLocal = !fRemotePattern.matcher(fname.toLowerCase(Locale.getDefault())).find();
                                    if (!ListenerUtil.mutListener.listen(22890)) {
                                        if ((ListenerUtil.mutListener.listen(22888) ? (isLocal && includeRemote) : (isLocal || includeRemote))) {
                                            if (!ListenerUtil.mutListener.listen(22889)) {
                                                l.add(fname);
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
        return l;
    }

    private List<String> _expandClozes(String string) {
        Set<String> ords = new TreeSet<>();
        // In Android, } should be escaped
        @SuppressWarnings("RegExpRedundantEscape")
        Matcher m = Pattern.compile("\\{\\{c(\\d+)::.+?\\}\\}").matcher(string);
        if (!ListenerUtil.mutListener.listen(22893)) {
            {
                long _loopCounter547 = 0;
                while (m.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter547", ++_loopCounter547);
                    if (!ListenerUtil.mutListener.listen(22892)) {
                        ords.add(m.group(1));
                    }
                }
            }
        }
        ArrayList<String> strings = new ArrayList<>((ListenerUtil.mutListener.listen(22897) ? (ords.size() % 1) : (ListenerUtil.mutListener.listen(22896) ? (ords.size() / 1) : (ListenerUtil.mutListener.listen(22895) ? (ords.size() * 1) : (ListenerUtil.mutListener.listen(22894) ? (ords.size() - 1) : (ords.size() + 1))))));
        String clozeReg = TemplateFilters.clozeReg;
        if (!ListenerUtil.mutListener.listen(22905)) {
            {
                long _loopCounter549 = 0;
                for (String ord : ords) {
                    ListenerUtil.loopListener.listen("_loopCounter549", ++_loopCounter549);
                    StringBuffer buf = new StringBuffer();
                    if (!ListenerUtil.mutListener.listen(22898)) {
                        m = Pattern.compile(String.format(Locale.US, clozeReg, ord)).matcher(string);
                    }
                    if (!ListenerUtil.mutListener.listen(22902)) {
                        {
                            long _loopCounter548 = 0;
                            while (m.find()) {
                                ListenerUtil.loopListener.listen("_loopCounter548", ++_loopCounter548);
                                if (!ListenerUtil.mutListener.listen(22901)) {
                                    if (!TextUtils.isEmpty(m.group(4))) {
                                        if (!ListenerUtil.mutListener.listen(22900)) {
                                            m.appendReplacement(buf, "[$4]");
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(22899)) {
                                            m.appendReplacement(buf, TemplateFilters.CLOZE_DELETION_REPLACEMENT);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22903)) {
                        m.appendTail(buf);
                    }
                    String s = buf.toString().replaceAll(String.format(Locale.US, clozeReg, ".+?"), "$2");
                    if (!ListenerUtil.mutListener.listen(22904)) {
                        strings.add(s);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22906)) {
            strings.add(string.replaceAll(String.format(Locale.US, clozeReg, ".+?"), "$2"));
        }
        return strings;
    }

    /**
     * Strips a string from media references.
     *
     * @param txt The string to be cleared of media references.
     * @return The media-free string.
     */
    public String strip(String txt) {
        if (!ListenerUtil.mutListener.listen(22908)) {
            {
                long _loopCounter550 = 0;
                for (Pattern p : mRegexps) {
                    ListenerUtil.loopListener.listen("_loopCounter550", ++_loopCounter550);
                    if (!ListenerUtil.mutListener.listen(22907)) {
                        txt = p.matcher(txt).replaceAll("");
                    }
                }
            }
        }
        return txt;
    }

    public String escapeImages(String string) {
        return escapeImages(string, false);
    }

    /**
     * Percent-escape UTF-8 characters in local image filenames.
     * @param string The string to search for image references and escape the filenames.
     * @return The string with the filenames of any local images percent-escaped as UTF-8.
     */
    public String escapeImages(String string, boolean unescape) {
        if (!ListenerUtil.mutListener.listen(22914)) {
            {
                long _loopCounter552 = 0;
                for (Pattern p : Arrays.asList(fImgRegExpQ, fImgRegExpU)) {
                    ListenerUtil.loopListener.listen("_loopCounter552", ++_loopCounter552);
                    Matcher m = p.matcher(string);
                    // the index based on which pattern we are using
                    int fnameIdx = p.equals(fImgRegExpU) ? 2 : 3;
                    if (!ListenerUtil.mutListener.listen(22913)) {
                        {
                            long _loopCounter551 = 0;
                            while (m.find()) {
                                ListenerUtil.loopListener.listen("_loopCounter551", ++_loopCounter551);
                                String tag = m.group(0);
                                String fname = m.group(fnameIdx);
                                if (!ListenerUtil.mutListener.listen(22912)) {
                                    if (fRemotePattern.matcher(fname).find()) {
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(22911)) {
                                            if (unescape) {
                                                if (!ListenerUtil.mutListener.listen(22910)) {
                                                    string = string.replace(tag, tag.replace(fname, Uri.decode(fname)));
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(22909)) {
                                                    string = string.replace(tag, tag.replace(fname, Uri.encode(fname, "/")));
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
        return string;
    }

    /**
     * Finds missing, unused and invalid media files
     *
     * @return A list containing three lists of files (missingFiles, unusedFiles, invalidFiles)
     */
    public List<List<String>> check() {
        return check(null);
    }

    private List<List<String>> check(File[] local) {
        File mdir = new File(dir());
        // gather all media references in NFC form
        Set<String> allRefs = new HashSet<>();
        try (Cursor cur = mCol.getDb().query("select id, mid, flds from notes")) {
            if (!ListenerUtil.mutListener.listen(22920)) {
                {
                    long _loopCounter554 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter554", ++_loopCounter554);
                        long nid = cur.getLong(0);
                        long mid = cur.getLong(1);
                        String flds = cur.getString(2);
                        List<String> noteRefs = filesInStr(mid, flds);
                        if (!ListenerUtil.mutListener.listen(22918)) {
                            {
                                long _loopCounter553 = 0;
                                // check the refs are in NFC
                                for (String f : noteRefs) {
                                    ListenerUtil.loopListener.listen("_loopCounter553", ++_loopCounter553);
                                    if (!ListenerUtil.mutListener.listen(22917)) {
                                        // if they're not, we'll need to fix them first
                                        if (!f.equals(Utils.nfcNormalized(f))) {
                                            if (!ListenerUtil.mutListener.listen(22915)) {
                                                _normalizeNoteRefs(nid);
                                            }
                                            if (!ListenerUtil.mutListener.listen(22916)) {
                                                noteRefs = filesInStr(mid, flds);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22919)) {
                            allRefs.addAll(noteRefs);
                        }
                    }
                }
            }
        }
        // loop through media folder
        List<String> unused = new ArrayList<>();
        List<String> invalid = new ArrayList<>();
        File[] files;
        if (local == null) {
            files = mdir.listFiles();
        } else {
            files = local;
        }
        boolean renamedFiles = false;
        {
            long _loopCounter555 = 0;
            for (File file : files) {
                ListenerUtil.loopListener.listen("_loopCounter555", ++_loopCounter555);
                if (!ListenerUtil.mutListener.listen(22922)) {
                    if (local == null) {
                        if (!ListenerUtil.mutListener.listen(22921)) {
                            if (file.isDirectory()) {
                                // ignore directories
                                continue;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22923)) {
                    if (file.getName().startsWith("_")) {
                        // leading _ says to ignore file
                        continue;
                    }
                }
                File nfcFile = new File(dir(), Utils.nfcNormalized(file.getName()));
                // we enforce NFC fs encoding
                if (local == null) {
                    if (!file.getName().equals(nfcFile.getName())) {
                        if (!ListenerUtil.mutListener.listen(22928)) {
                            // delete if we already have the NFC form, otherwise rename
                            if (nfcFile.exists()) {
                                if (!ListenerUtil.mutListener.listen(22926)) {
                                    file.delete();
                                }
                                if (!ListenerUtil.mutListener.listen(22927)) {
                                    renamedFiles = true;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(22924)) {
                                    file.renameTo(nfcFile);
                                }
                                if (!ListenerUtil.mutListener.listen(22925)) {
                                    renamedFiles = true;
                                }
                            }
                        }
                        file = nfcFile;
                    }
                }
                if (!ListenerUtil.mutListener.listen(22931)) {
                    // compare
                    if (!allRefs.contains(nfcFile.getName())) {
                        if (!ListenerUtil.mutListener.listen(22930)) {
                            unused.add(file.getName());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(22929)) {
                            allRefs.remove(nfcFile.getName());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22932)) {
            // to make sure the renamed files are not marked as unused
            if (renamedFiles) {
                return check(local);
            }
        }
        List<String> nohave = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22935)) {
            {
                long _loopCounter556 = 0;
                for (String x : allRefs) {
                    ListenerUtil.loopListener.listen("_loopCounter556", ++_loopCounter556);
                    if (!ListenerUtil.mutListener.listen(22934)) {
                        if (!x.startsWith("_")) {
                            if (!ListenerUtil.mutListener.listen(22933)) {
                                nohave.add(x);
                            }
                        }
                    }
                }
            }
        }
        // make sure the media DB is valid
        try {
            if (!ListenerUtil.mutListener.listen(22937)) {
                findChanges();
            }
        } catch (SQLException ignored) {
            if (!ListenerUtil.mutListener.listen(22936)) {
                _deleteDB();
            }
        }
        List<List<String>> result = new ArrayList<>(3);
        if (!ListenerUtil.mutListener.listen(22938)) {
            result.add(nohave);
        }
        if (!ListenerUtil.mutListener.listen(22939)) {
            result.add(unused);
        }
        if (!ListenerUtil.mutListener.listen(22940)) {
            result.add(invalid);
        }
        return result;
    }

    private void _normalizeNoteRefs(long nid) {
        Note note = mCol.getNote(nid);
        String[] flds = note.getFields();
        if (!ListenerUtil.mutListener.listen(22948)) {
            {
                long _loopCounter557 = 0;
                for (int c = 0; (ListenerUtil.mutListener.listen(22947) ? (c >= flds.length) : (ListenerUtil.mutListener.listen(22946) ? (c <= flds.length) : (ListenerUtil.mutListener.listen(22945) ? (c > flds.length) : (ListenerUtil.mutListener.listen(22944) ? (c != flds.length) : (ListenerUtil.mutListener.listen(22943) ? (c == flds.length) : (c < flds.length)))))); c++) {
                    ListenerUtil.loopListener.listen("_loopCounter557", ++_loopCounter557);
                    String fld = flds[c];
                    String nfc = Utils.nfcNormalized(fld);
                    if (!ListenerUtil.mutListener.listen(22942)) {
                        if (!nfc.equals(fld)) {
                            if (!ListenerUtil.mutListener.listen(22941)) {
                                note.setField(c, nfc);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22949)) {
            note.flush();
        }
    }

    public boolean have(String fname) {
        return new File(dir(), fname).exists();
    }

    public String stripIllegal(String str) {
        Matcher m = fIllegalCharReg.matcher(str);
        return m.replaceAll("");
    }

    public boolean hasIllegal(String str) {
        Matcher m = fIllegalCharReg.matcher(str);
        return m.find();
    }

    public String cleanFilename(String fname) {
        if (!ListenerUtil.mutListener.listen(22950)) {
            fname = stripIllegal(fname);
        }
        if (!ListenerUtil.mutListener.listen(22951)) {
            fname = _cleanWin32Filename(fname);
        }
        if (!ListenerUtil.mutListener.listen(22952)) {
            fname = _cleanLongFilename(fname);
        }
        if (!ListenerUtil.mutListener.listen(22954)) {
            if ("".equals(fname)) {
                if (!ListenerUtil.mutListener.listen(22953)) {
                    fname = "renamed";
                }
            }
        }
        return fname;
    }

    /**
     * This method only change things on windows. So it's the
     * identity here.
     */
    private String _cleanWin32Filename(String fname) {
        return fname;
    }

    private String _cleanLongFilename(String fname) {
        /* a fairly safe limit that should work on typical windows
         paths and on eCryptfs partitions, even with a duplicate
         suffix appended */
        int namemax = 136;
        // 240 for windows
        int pathmax = 1024;
        // ideally, name should be normalized. Without access to nio.Paths library, it's hard to do it really correctly. This is still a better approximation than nothing.
        int dirlen = fname.length();
        int remaining = (ListenerUtil.mutListener.listen(22958) ? (pathmax % dirlen) : (ListenerUtil.mutListener.listen(22957) ? (pathmax / dirlen) : (ListenerUtil.mutListener.listen(22956) ? (pathmax * dirlen) : (ListenerUtil.mutListener.listen(22955) ? (pathmax + dirlen) : (pathmax - dirlen)))));
        if (!ListenerUtil.mutListener.listen(22959)) {
            namemax = min(remaining, namemax);
        }
        if (!ListenerUtil.mutListener.listen(22965)) {
            Assert.that((ListenerUtil.mutListener.listen(22964) ? (namemax >= 0) : (ListenerUtil.mutListener.listen(22963) ? (namemax <= 0) : (ListenerUtil.mutListener.listen(22962) ? (namemax < 0) : (ListenerUtil.mutListener.listen(22961) ? (namemax != 0) : (ListenerUtil.mutListener.listen(22960) ? (namemax == 0) : (namemax > 0)))))), "The media directory is maximally long. There is no more length available for file name.");
        }
        if (!ListenerUtil.mutListener.listen(23000)) {
            if ((ListenerUtil.mutListener.listen(22970) ? (fname.length() >= namemax) : (ListenerUtil.mutListener.listen(22969) ? (fname.length() <= namemax) : (ListenerUtil.mutListener.listen(22968) ? (fname.length() < namemax) : (ListenerUtil.mutListener.listen(22967) ? (fname.length() != namemax) : (ListenerUtil.mutListener.listen(22966) ? (fname.length() == namemax) : (fname.length() > namemax))))))) {
                int lastSlash = fname.indexOf("/");
                int lastDot = fname.indexOf(".");
                if (!ListenerUtil.mutListener.listen(22999)) {
                    if ((ListenerUtil.mutListener.listen(22981) ? ((ListenerUtil.mutListener.listen(22975) ? (lastDot >= -1) : (ListenerUtil.mutListener.listen(22974) ? (lastDot <= -1) : (ListenerUtil.mutListener.listen(22973) ? (lastDot > -1) : (ListenerUtil.mutListener.listen(22972) ? (lastDot < -1) : (ListenerUtil.mutListener.listen(22971) ? (lastDot != -1) : (lastDot == -1)))))) && (ListenerUtil.mutListener.listen(22980) ? (lastDot >= lastSlash) : (ListenerUtil.mutListener.listen(22979) ? (lastDot <= lastSlash) : (ListenerUtil.mutListener.listen(22978) ? (lastDot > lastSlash) : (ListenerUtil.mutListener.listen(22977) ? (lastDot != lastSlash) : (ListenerUtil.mutListener.listen(22976) ? (lastDot == lastSlash) : (lastDot < lastSlash))))))) : ((ListenerUtil.mutListener.listen(22975) ? (lastDot >= -1) : (ListenerUtil.mutListener.listen(22974) ? (lastDot <= -1) : (ListenerUtil.mutListener.listen(22973) ? (lastDot > -1) : (ListenerUtil.mutListener.listen(22972) ? (lastDot < -1) : (ListenerUtil.mutListener.listen(22971) ? (lastDot != -1) : (lastDot == -1)))))) || (ListenerUtil.mutListener.listen(22980) ? (lastDot >= lastSlash) : (ListenerUtil.mutListener.listen(22979) ? (lastDot <= lastSlash) : (ListenerUtil.mutListener.listen(22978) ? (lastDot > lastSlash) : (ListenerUtil.mutListener.listen(22977) ? (lastDot != lastSlash) : (ListenerUtil.mutListener.listen(22976) ? (lastDot == lastSlash) : (lastDot < lastSlash))))))))) {
                        if (!ListenerUtil.mutListener.listen(22998)) {
                            // no dot, or before last slash
                            fname = fname.substring(0, namemax);
                        }
                    } else {
                        String ext = fname.substring((ListenerUtil.mutListener.listen(22985) ? (lastDot % 1) : (ListenerUtil.mutListener.listen(22984) ? (lastDot / 1) : (ListenerUtil.mutListener.listen(22983) ? (lastDot * 1) : (ListenerUtil.mutListener.listen(22982) ? (lastDot - 1) : (lastDot + 1))))));
                        String head = fname.substring(0, lastDot);
                        int headmax = (ListenerUtil.mutListener.listen(22989) ? (namemax % ext.length()) : (ListenerUtil.mutListener.listen(22988) ? (namemax / ext.length()) : (ListenerUtil.mutListener.listen(22987) ? (namemax * ext.length()) : (ListenerUtil.mutListener.listen(22986) ? (namemax + ext.length()) : (namemax - ext.length())))));
                        if (!ListenerUtil.mutListener.listen(22990)) {
                            head = head.substring(0, headmax);
                        }
                        if (!ListenerUtil.mutListener.listen(22991)) {
                            fname = head + ext;
                        }
                        if (!ListenerUtil.mutListener.listen(22997)) {
                            Assert.that((ListenerUtil.mutListener.listen(22996) ? (fname.length() >= namemax) : (ListenerUtil.mutListener.listen(22995) ? (fname.length() > namemax) : (ListenerUtil.mutListener.listen(22994) ? (fname.length() < namemax) : (ListenerUtil.mutListener.listen(22993) ? (fname.length() != namemax) : (ListenerUtil.mutListener.listen(22992) ? (fname.length() == namemax) : (fname.length() <= namemax)))))), "The length of the file is greater than the maximal name value.");
                        }
                    }
                }
            }
        }
        return fname;
    }

    /**
     * Scan the media folder if it's changed, and note any changes.
     */
    public void findChanges() {
        if (!ListenerUtil.mutListener.listen(23001)) {
            findChanges(false);
        }
    }

    /**
     * @param force Unconditionally scan the media folder for changes (i.e., ignore differences in recorded and current
     *            directory mod times). Use this when rebuilding the media database.
     */
    public void findChanges(boolean force) {
        if (!ListenerUtil.mutListener.listen(23004)) {
            if ((ListenerUtil.mutListener.listen(23002) ? (force && _changed() != null) : (force || _changed() != null))) {
                if (!ListenerUtil.mutListener.listen(23003)) {
                    _logChanges();
                }
            }
        }
    }

    public boolean haveDirty() {
        return (ListenerUtil.mutListener.listen(23009) ? (mDb.queryScalar("select 1 from media where dirty=1 limit 1") >= 0) : (ListenerUtil.mutListener.listen(23008) ? (mDb.queryScalar("select 1 from media where dirty=1 limit 1") <= 0) : (ListenerUtil.mutListener.listen(23007) ? (mDb.queryScalar("select 1 from media where dirty=1 limit 1") < 0) : (ListenerUtil.mutListener.listen(23006) ? (mDb.queryScalar("select 1 from media where dirty=1 limit 1") != 0) : (ListenerUtil.mutListener.listen(23005) ? (mDb.queryScalar("select 1 from media where dirty=1 limit 1") == 0) : (mDb.queryScalar("select 1 from media where dirty=1 limit 1") > 0))))));
    }

    /**
     * Returns the number of seconds from epoch since the last modification to the file in path. Important: this method
     * does not automatically append the root media directory to the path; the FULL path of the file must be specified.
     *
     * @param path The path to the file we are checking. path can be a file or a directory.
     * @return The number of seconds (rounded down).
     */
    private long _mtime(String path) {
        File f = new File(path);
        return (ListenerUtil.mutListener.listen(23013) ? (f.lastModified() % 1000) : (ListenerUtil.mutListener.listen(23012) ? (f.lastModified() * 1000) : (ListenerUtil.mutListener.listen(23011) ? (f.lastModified() - 1000) : (ListenerUtil.mutListener.listen(23010) ? (f.lastModified() + 1000) : (f.lastModified() / 1000)))));
    }

    private String _checksum(String path) {
        return Utils.fileChecksum(path);
    }

    /**
     * Return dir mtime if it has changed since the last findChanges()
     * Doesn't track edits, but user can add or remove a file to update
     *
     * @return The modification time of the media directory if it has changed since the last call of findChanges(). If
     *         it hasn't, it returns null.
     */
    public Long _changed() {
        long mod = mDb.queryLongScalar("select dirMod from meta");
        long mtime = _mtime(dir());
        if (!ListenerUtil.mutListener.listen(23025)) {
            if ((ListenerUtil.mutListener.listen(23024) ? ((ListenerUtil.mutListener.listen(23018) ? (mod >= 0) : (ListenerUtil.mutListener.listen(23017) ? (mod <= 0) : (ListenerUtil.mutListener.listen(23016) ? (mod > 0) : (ListenerUtil.mutListener.listen(23015) ? (mod < 0) : (ListenerUtil.mutListener.listen(23014) ? (mod == 0) : (mod != 0)))))) || (ListenerUtil.mutListener.listen(23023) ? (mod >= mtime) : (ListenerUtil.mutListener.listen(23022) ? (mod <= mtime) : (ListenerUtil.mutListener.listen(23021) ? (mod > mtime) : (ListenerUtil.mutListener.listen(23020) ? (mod < mtime) : (ListenerUtil.mutListener.listen(23019) ? (mod != mtime) : (mod == mtime))))))) : ((ListenerUtil.mutListener.listen(23018) ? (mod >= 0) : (ListenerUtil.mutListener.listen(23017) ? (mod <= 0) : (ListenerUtil.mutListener.listen(23016) ? (mod > 0) : (ListenerUtil.mutListener.listen(23015) ? (mod < 0) : (ListenerUtil.mutListener.listen(23014) ? (mod == 0) : (mod != 0)))))) && (ListenerUtil.mutListener.listen(23023) ? (mod >= mtime) : (ListenerUtil.mutListener.listen(23022) ? (mod <= mtime) : (ListenerUtil.mutListener.listen(23021) ? (mod > mtime) : (ListenerUtil.mutListener.listen(23020) ? (mod < mtime) : (ListenerUtil.mutListener.listen(23019) ? (mod != mtime) : (mod == mtime))))))))) {
                return null;
            }
        }
        return mtime;
    }

    private void _logChanges() {
        Pair<List<String>, List<String>> result = _changes();
        List<String> added = result.first;
        List<String> removed = result.second;
        ArrayList<Object[]> media = new ArrayList<>((ListenerUtil.mutListener.listen(23029) ? (added.size() % removed.size()) : (ListenerUtil.mutListener.listen(23028) ? (added.size() / removed.size()) : (ListenerUtil.mutListener.listen(23027) ? (added.size() * removed.size()) : (ListenerUtil.mutListener.listen(23026) ? (added.size() - removed.size()) : (added.size() + removed.size()))))));
        if (!ListenerUtil.mutListener.listen(23031)) {
            {
                long _loopCounter558 = 0;
                for (String f : added) {
                    ListenerUtil.loopListener.listen("_loopCounter558", ++_loopCounter558);
                    String path = new File(dir(), f).getAbsolutePath();
                    long mt = _mtime(path);
                    if (!ListenerUtil.mutListener.listen(23030)) {
                        media.add(new Object[] { f, _checksum(path), mt, 1 });
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23033)) {
            {
                long _loopCounter559 = 0;
                for (String f : removed) {
                    ListenerUtil.loopListener.listen("_loopCounter559", ++_loopCounter559);
                    if (!ListenerUtil.mutListener.listen(23032)) {
                        media.add(new Object[] { f, null, 0, 1 });
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23034)) {
            // update media db
            mDb.executeMany("insert or replace into media values (?,?,?,?)", media);
        }
        if (!ListenerUtil.mutListener.listen(23035)) {
            mDb.execute("update meta set dirMod = ?", _mtime(dir()));
        }
        if (!ListenerUtil.mutListener.listen(23036)) {
            mDb.commit();
        }
    }

    private Pair<List<String>, List<String>> _changes() {
        Map<String, Object[]> cache = new HashMap<>(mDb.queryScalar("SELECT count() FROM media WHERE csum IS NOT NULL"));
        try (Cursor cur = mDb.query("select fname, csum, mtime from media where csum is not null")) {
            if (!ListenerUtil.mutListener.listen(23038)) {
                {
                    long _loopCounter560 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter560", ++_loopCounter560);
                        String name = cur.getString(0);
                        String csum = cur.getString(1);
                        long mod = cur.getLong(2);
                        if (!ListenerUtil.mutListener.listen(23037)) {
                            cache.put(name, new Object[] { csum, mod, false });
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<String> added = new ArrayList<>();
        List<String> removed = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(23079)) {
            {
                long _loopCounter561 = 0;
                // loop through on-disk files
                for (File f : new File(dir()).listFiles()) {
                    ListenerUtil.loopListener.listen("_loopCounter561", ++_loopCounter561);
                    if (!ListenerUtil.mutListener.listen(23039)) {
                        // ignore folders and thumbs.db
                        if (f.isDirectory()) {
                            continue;
                        }
                    }
                    String fname = f.getName();
                    if (!ListenerUtil.mutListener.listen(23040)) {
                        if ("thumbs.db".equalsIgnoreCase(fname)) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23041)) {
                        // and files with invalid chars
                        if (hasIllegal(fname)) {
                            continue;
                        }
                    }
                    // empty files are invalid; clean them up and continue
                    long sz = f.length();
                    if (!ListenerUtil.mutListener.listen(23048)) {
                        if ((ListenerUtil.mutListener.listen(23046) ? (sz >= 0) : (ListenerUtil.mutListener.listen(23045) ? (sz <= 0) : (ListenerUtil.mutListener.listen(23044) ? (sz > 0) : (ListenerUtil.mutListener.listen(23043) ? (sz < 0) : (ListenerUtil.mutListener.listen(23042) ? (sz != 0) : (sz == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(23047)) {
                                f.delete();
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23063)) {
                        if ((ListenerUtil.mutListener.listen(23061) ? (sz >= (ListenerUtil.mutListener.listen(23056) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(23055) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(23054) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(23053) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(23060) ? (sz <= (ListenerUtil.mutListener.listen(23056) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(23055) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(23054) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(23053) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(23059) ? (sz < (ListenerUtil.mutListener.listen(23056) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(23055) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(23054) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(23053) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(23058) ? (sz != (ListenerUtil.mutListener.listen(23056) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(23055) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(23054) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(23053) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(23057) ? (sz == (ListenerUtil.mutListener.listen(23056) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(23055) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(23054) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(23053) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) * 1024)))))) : (sz > (ListenerUtil.mutListener.listen(23056) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(23055) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(23054) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(23053) ? ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(23052) ? (100 % 1024) : (ListenerUtil.mutListener.listen(23051) ? (100 / 1024) : (ListenerUtil.mutListener.listen(23050) ? (100 - 1024) : (ListenerUtil.mutListener.listen(23049) ? (100 + 1024) : (100 * 1024))))) * 1024)))))))))))) {
                            if (!ListenerUtil.mutListener.listen(23062)) {
                                mCol.log("ignoring file over 100MB", f);
                            }
                            continue;
                        }
                    }
                    // check encoding
                    String normf = Utils.nfcNormalized(fname);
                    if (!ListenerUtil.mutListener.listen(23067)) {
                        if (!fname.equals(normf)) {
                            // wrong filename encoding which will cause sync errors
                            File nf = new File(dir(), normf);
                            if (!ListenerUtil.mutListener.listen(23066)) {
                                if (nf.exists()) {
                                    if (!ListenerUtil.mutListener.listen(23065)) {
                                        f.delete();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(23064)) {
                                        f.renameTo(nf);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23078)) {
                        // newly added?
                        if (!cache.containsKey(fname)) {
                            if (!ListenerUtil.mutListener.listen(23077)) {
                                added.add(fname);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23075)) {
                                // modified since last time?
                                if ((ListenerUtil.mutListener.listen(23072) ? (_mtime(f.getAbsolutePath()) >= (Long) cache.get(fname)[1]) : (ListenerUtil.mutListener.listen(23071) ? (_mtime(f.getAbsolutePath()) <= (Long) cache.get(fname)[1]) : (ListenerUtil.mutListener.listen(23070) ? (_mtime(f.getAbsolutePath()) > (Long) cache.get(fname)[1]) : (ListenerUtil.mutListener.listen(23069) ? (_mtime(f.getAbsolutePath()) < (Long) cache.get(fname)[1]) : (ListenerUtil.mutListener.listen(23068) ? (_mtime(f.getAbsolutePath()) == (Long) cache.get(fname)[1]) : (_mtime(f.getAbsolutePath()) != (Long) cache.get(fname)[1]))))))) {
                                    if (!ListenerUtil.mutListener.listen(23074)) {
                                        // and has different checksum?
                                        if (!_checksum(f.getAbsolutePath()).equals(cache.get(fname)[0])) {
                                            if (!ListenerUtil.mutListener.listen(23073)) {
                                                added.add(fname);
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(23076)) {
                                // mark as used
                                cache.get(fname)[2] = true;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23082)) {
            {
                long _loopCounter562 = 0;
                // look for any entries in the cache that no longer exist on disk
                for (Map.Entry<String, Object[]> entry : cache.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter562", ++_loopCounter562);
                    if (!ListenerUtil.mutListener.listen(23081)) {
                        if (!((Boolean) entry.getValue()[2])) {
                            if (!ListenerUtil.mutListener.listen(23080)) {
                                removed.add(entry.getKey());
                            }
                        }
                    }
                }
            }
        }
        return new Pair<>(added, removed);
    }

    public int lastUsn() {
        return mDb.queryScalar("select lastUsn from meta");
    }

    public void setLastUsn(int usn) {
        if (!ListenerUtil.mutListener.listen(23083)) {
            mDb.execute("update meta set lastUsn = ?", usn);
        }
        if (!ListenerUtil.mutListener.listen(23084)) {
            mDb.commit();
        }
    }

    public Pair<String, Integer> syncInfo(String fname) {
        try (Cursor cur = mDb.query("select csum, dirty from media where fname=?", fname)) {
            if (cur.moveToNext()) {
                String csum = cur.getString(0);
                int dirty = cur.getInt(1);
                return new Pair<>(csum, dirty);
            } else {
                return new Pair<>(null, 0);
            }
        }
    }

    public void markClean(List<String> fnames) {
        if (!ListenerUtil.mutListener.listen(23086)) {
            {
                long _loopCounter563 = 0;
                for (String fname : fnames) {
                    ListenerUtil.loopListener.listen("_loopCounter563", ++_loopCounter563);
                    if (!ListenerUtil.mutListener.listen(23085)) {
                        mDb.execute("update media set dirty=0 where fname=?", fname);
                    }
                }
            }
        }
    }

    public void syncDelete(String fname) {
        File f = new File(dir(), fname);
        if (!ListenerUtil.mutListener.listen(23088)) {
            if (f.exists()) {
                if (!ListenerUtil.mutListener.listen(23087)) {
                    f.delete();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23089)) {
            mDb.execute("delete from media where fname=?", fname);
        }
    }

    public int mediacount() {
        return mDb.queryScalar("select count() from media where csum is not null");
    }

    public int dirtyCount() {
        return mDb.queryScalar("select count() from media where dirty=1");
    }

    public void forceResync() {
        if (!ListenerUtil.mutListener.listen(23090)) {
            mDb.execute("delete from media");
        }
        if (!ListenerUtil.mutListener.listen(23091)) {
            mDb.execute("update meta set lastUsn=0,dirMod=0");
        }
        if (!ListenerUtil.mutListener.listen(23092)) {
            mDb.execute("vacuum");
        }
        if (!ListenerUtil.mutListener.listen(23093)) {
            mDb.execute("analyze");
        }
        if (!ListenerUtil.mutListener.listen(23094)) {
            mDb.commit();
        }
    }

    /**
     * Unlike python, our temp zip file will be on disk instead of in memory. This avoids storing
     * potentially large files in memory which is not feasible with Android's limited heap space.
     * <p>
     * Notes:
     * <p>
     * - The maximum size of the changes zip is decided by the constant SYNC_ZIP_SIZE. If a media file exceeds this
     * limit, only that file (in full) will be zipped to be sent to the server.
     * <p>
     * - This method will be repeatedly called from MediaSyncer until there are no more files (marked "dirty" in the DB)
     * to send.
     * <p>
     * - Since AnkiDroid avoids scanning the media folder on every sync, it is possible for a file to be marked as a
     * new addition but actually have been deleted (e.g., with a file manager). In this case we skip over the file
     * and mark it as removed in the database. (This behaviour differs from the desktop client).
     * <p>
     */
    public Pair<File, List<String>> mediaChangesZip() {
        File f = new File(mCol.getPath().replaceFirst("collection\\.anki2$", "tmpSyncToServer.zip"));
        List<String> fnames = new ArrayList<>();
        try (ZipOutputStream z = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            Cursor cur = mDb.query("select fname, csum from media where dirty=1 limit " + Consts.SYNC_ZIP_COUNT)) {
            if (!ListenerUtil.mutListener.listen(23096)) {
                z.setMethod(ZipOutputStream.DEFLATED);
            }
            // serialization step. Instead of a list of tuples, we use JSONArrays of JSONArrays.
            JSONArray meta = new JSONArray();
            int sz = 0;
            byte[] buffer = new byte[2048];
            if (!ListenerUtil.mutListener.listen(23121)) {
                {
                    long _loopCounter565 = 0;
                    for (int c = 0; cur.moveToNext(); c++) {
                        ListenerUtil.loopListener.listen("_loopCounter565", ++_loopCounter565);
                        String fname = cur.getString(0);
                        String csum = cur.getString(1);
                        if (!ListenerUtil.mutListener.listen(23097)) {
                            fnames.add(fname);
                        }
                        String normname = Utils.nfcNormalized(fname);
                        if (!ListenerUtil.mutListener.listen(23114)) {
                            if (!TextUtils.isEmpty(csum)) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(23101)) {
                                        mCol.log("+media zip " + fname);
                                    }
                                    File file = new File(dir(), fname);
                                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file), 2048);
                                    if (!ListenerUtil.mutListener.listen(23102)) {
                                        z.putNextEntry(new ZipEntry(Integer.toString(c)));
                                    }
                                    int count = 0;
                                    if (!ListenerUtil.mutListener.listen(23109)) {
                                        {
                                            long _loopCounter564 = 0;
                                            while ((ListenerUtil.mutListener.listen(23108) ? ((count = bis.read(buffer, 0, 2048)) >= -1) : (ListenerUtil.mutListener.listen(23107) ? ((count = bis.read(buffer, 0, 2048)) <= -1) : (ListenerUtil.mutListener.listen(23106) ? ((count = bis.read(buffer, 0, 2048)) > -1) : (ListenerUtil.mutListener.listen(23105) ? ((count = bis.read(buffer, 0, 2048)) < -1) : (ListenerUtil.mutListener.listen(23104) ? ((count = bis.read(buffer, 0, 2048)) == -1) : ((count = bis.read(buffer, 0, 2048)) != -1))))))) {
                                                ListenerUtil.loopListener.listen("_loopCounter564", ++_loopCounter564);
                                                if (!ListenerUtil.mutListener.listen(23103)) {
                                                    z.write(buffer, 0, count);
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(23110)) {
                                        z.closeEntry();
                                    }
                                    if (!ListenerUtil.mutListener.listen(23111)) {
                                        bis.close();
                                    }
                                    if (!ListenerUtil.mutListener.listen(23112)) {
                                        meta.put(new JSONArray().put(normname).put(Integer.toString(c)));
                                    }
                                    if (!ListenerUtil.mutListener.listen(23113)) {
                                        sz += file.length();
                                    }
                                } catch (FileNotFoundException e) {
                                    if (!ListenerUtil.mutListener.listen(23100)) {
                                        // Skip over it and mark it as removed in the db.
                                        removeFile(fname);
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(23098)) {
                                    mCol.log("-media zip " + fname);
                                }
                                if (!ListenerUtil.mutListener.listen(23099)) {
                                    meta.put(new JSONArray().put(normname).put(""));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(23120)) {
                            if ((ListenerUtil.mutListener.listen(23119) ? (sz <= Consts.SYNC_ZIP_SIZE) : (ListenerUtil.mutListener.listen(23118) ? (sz > Consts.SYNC_ZIP_SIZE) : (ListenerUtil.mutListener.listen(23117) ? (sz < Consts.SYNC_ZIP_SIZE) : (ListenerUtil.mutListener.listen(23116) ? (sz != Consts.SYNC_ZIP_SIZE) : (ListenerUtil.mutListener.listen(23115) ? (sz == Consts.SYNC_ZIP_SIZE) : (sz >= Consts.SYNC_ZIP_SIZE))))))) {
                                break;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23122)) {
                z.putNextEntry(new ZipEntry("_meta"));
            }
            if (!ListenerUtil.mutListener.listen(23123)) {
                z.write(Utils.jsonToString(meta).getBytes());
            }
            if (!ListenerUtil.mutListener.listen(23124)) {
                z.closeEntry();
            }
            if (!ListenerUtil.mutListener.listen(23125)) {
                // Don't leave lingering temp files if the VM terminates.
                f.deleteOnExit();
            }
            return new Pair<>(f, fnames);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(23095)) {
                Timber.e(e, "Failed to create media changes zip: ");
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * Extract zip data; return the number of files extracted. Unlike the python version, this method consumes a
     * ZipFile stored on disk instead of a String buffer. Holding the entire downloaded data in memory is not feasible
     * since some devices can have very limited heap space.
     *
     * This method closes the file before it returns.
     */
    public int addFilesFromZip(ZipFile z) throws IOException {
        try {
            // get meta info first
            JSONObject meta = new JSONObject(Utils.convertStreamToString(z.getInputStream(z.getEntry("_meta"))));
            // then loop through all files
            int cnt = 0;
            ArrayList<? extends ZipEntry> zipEntries = Collections.list(z.entries());
            List<Object[]> media = new ArrayList<>(zipEntries.size());
            if (!ListenerUtil.mutListener.listen(23132)) {
                {
                    long _loopCounter566 = 0;
                    for (ZipEntry i : zipEntries) {
                        ListenerUtil.loopListener.listen("_loopCounter566", ++_loopCounter566);
                        String fileName = i.getName();
                        if (!ListenerUtil.mutListener.listen(23127)) {
                            if ("_meta".equals(fileName)) {
                                // ignore previously-retrieved meta
                                continue;
                            }
                        }
                        String name = meta.getString(fileName);
                        if (!ListenerUtil.mutListener.listen(23128)) {
                            // normalize name for platform
                            name = Utils.nfcNormalized(name);
                        }
                        // save file
                        String destPath = (dir() + File.separator) + name;
                        try (InputStream zipInputStream = z.getInputStream(i)) {
                            if (!ListenerUtil.mutListener.listen(23129)) {
                                Utils.writeToFile(zipInputStream, destPath);
                            }
                        }
                        String csum = Utils.fileChecksum(destPath);
                        if (!ListenerUtil.mutListener.listen(23130)) {
                            // update db
                            media.add(new Object[] { name, csum, _mtime(destPath), 0 });
                        }
                        if (!ListenerUtil.mutListener.listen(23131)) {
                            cnt += 1;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23139)) {
                if ((ListenerUtil.mutListener.listen(23137) ? (media.size() >= 0) : (ListenerUtil.mutListener.listen(23136) ? (media.size() <= 0) : (ListenerUtil.mutListener.listen(23135) ? (media.size() < 0) : (ListenerUtil.mutListener.listen(23134) ? (media.size() != 0) : (ListenerUtil.mutListener.listen(23133) ? (media.size() == 0) : (media.size() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(23138)) {
                        mDb.executeMany("insert or replace into media values (?,?,?,?)", media);
                    }
                }
            }
            return cnt;
        } finally {
            if (!ListenerUtil.mutListener.listen(23126)) {
                z.close();
            }
        }
    }

    /**
     * Used by unit tests only.
     */
    public DB getDb() {
        return mDb;
    }

    /**
     * Used by other classes to determine the index of a regular expression group named "fname"
     * (Anki2Importer needs this). This is needed because we didn't implement the "transformNames"
     * function and have delegated its job to the caller of this class.
     */
    public static int indexOfFname(Pattern p) {
        return p.equals(fSoundRegexps) ? 2 : p.equals(fImgRegExpU) ? 2 : 3;
    }

    /**
     * Add an entry into the media database for file named fname, or update it
     * if it already exists.
     */
    public void markFileAdd(String fname) {
        if (!ListenerUtil.mutListener.listen(23140)) {
            Timber.d("Marking media file addition in media db: %s", fname);
        }
        String path = new File(dir(), fname).getAbsolutePath();
        if (!ListenerUtil.mutListener.listen(23141)) {
            mDb.execute("insert or replace into media values (?,?,?,?)", fname, _checksum(path), _mtime(path), 1);
        }
    }

    /**
     * Remove a file from the media directory if it exists and mark it as removed in the media database.
     */
    public void removeFile(String fname) {
        File f = new File(dir(), fname);
        if (!ListenerUtil.mutListener.listen(23143)) {
            if (f.exists()) {
                if (!ListenerUtil.mutListener.listen(23142)) {
                    f.delete();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23144)) {
            Timber.d("Marking media file removal in media db: %s", fname);
        }
        if (!ListenerUtil.mutListener.listen(23145)) {
            mDb.execute("insert or replace into media values (?,?,?,?)", fname, null, 0, 1);
        }
    }

    /**
     * @return True if the media db has not been populated yet.
     */
    public boolean needScan() {
        long mod = mDb.queryLongScalar("select dirMod from meta");
        return (ListenerUtil.mutListener.listen(23150) ? (mod >= 0) : (ListenerUtil.mutListener.listen(23149) ? (mod <= 0) : (ListenerUtil.mutListener.listen(23148) ? (mod > 0) : (ListenerUtil.mutListener.listen(23147) ? (mod < 0) : (ListenerUtil.mutListener.listen(23146) ? (mod != 0) : (mod == 0))))));
    }

    public void rebuildIfInvalid() throws IOException {
        try {
            if (!ListenerUtil.mutListener.listen(23154)) {
                _changed();
            }
            return;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(23151)) {
                if (!ExceptionUtil.containsMessage(e, "no such table: meta")) {
                    throw e;
                }
            }
            if (!ListenerUtil.mutListener.listen(23152)) {
                AnkiDroidApp.sendExceptionReport(e, "media::rebuildIfInvalid");
            }
            if (!ListenerUtil.mutListener.listen(23153)) {
                // TODO: We don't know the root cause of the missing meta table
                Timber.w(e, "Error accessing media database. Rebuilding");
            }
        }
        if (!ListenerUtil.mutListener.listen(23155)) {
            // Delete and recreate the file
            mDb.getDatabase().close();
        }
        String path = mDb.getPath();
        if (!ListenerUtil.mutListener.listen(23156)) {
            Timber.i("Deleted %s", path);
        }
        if (!ListenerUtil.mutListener.listen(23157)) {
            new File(path).delete();
        }
        if (!ListenerUtil.mutListener.listen(23158)) {
            mDb = new DB(path);
        }
        if (!ListenerUtil.mutListener.listen(23159)) {
            _initDB();
        }
    }
}

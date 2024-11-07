package com.ichi2.libanki.importer;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Models;
import com.ichi2.libanki.template.ParsedNode;
import com.ichi2.libanki.utils.StringUtils;
import com.ichi2.utils.Assert;
import com.ichi2.utils.HtmlUtils;
import com.ichi2.utils.JSONObject;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.annotation.StringRes;
import static com.ichi2.libanki.Consts.NEW_CARDS_RANDOM;
import static com.ichi2.libanki.Utils.fieldChecksum;
import static com.ichi2.libanki.Utils.guid64;
import static com.ichi2.libanki.Utils.joinFields;
import static com.ichi2.libanki.Utils.splitFields;
import static com.ichi2.libanki.importer.NoteImporter.ImportMode.ADD_MODE;
import static com.ichi2.libanki.importer.NoteImporter.ImportMode.IGNORE_MODE;
import static com.ichi2.libanki.importer.NoteImporter.ImportMode.UPDATE_MODE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Aside from 9f676dbe0b2ad9b87a3bf89d7735b4253abd440e, which allows empty notes.
public class NoteImporter extends Importer {

    private boolean mNeedMapper = true;

    private boolean mNeedDelimiter = false;

    private boolean mAllowHTML = false;

    private ImportMode mImportMode = UPDATE_MODE;

    /**
     * Note: elements can be null
     */
    @Nullable
    private List<String> mMapping;

    @Nullable
    private final String mTagModified;

    private final Model mModel;

    /**
     * _tagsMapped in python
     */
    private boolean mTagsMapped;

    /**
     * _fmap in Python
     */
    private Map<String, Pair<Integer, JSONObject>> mFMap;

    /**
     * _nextID in python
     */
    private long mNextId;

    private ArrayList<Long> _ids;

    private boolean mEmptyNotes;

    private int mUpdateCount;

    private List<ParsedNode> mTemplateParsed;

    public NoteImporter(Collection col, String file) {
        super(col, file);
        this.mModel = col.getModels().current();
        if (!ListenerUtil.mutListener.listen(14244)) {
            this.mTemplateParsed = mModel.parsedNodes();
        }
        if (!ListenerUtil.mutListener.listen(14245)) {
            this.mMapping = null;
        }
        this.mTagModified = null;
        if (!ListenerUtil.mutListener.listen(14246)) {
            this.mTagsMapped = false;
        }
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(14247)) {
            Assert.that(mMapping != null);
        }
        if (!ListenerUtil.mutListener.listen(14248)) {
            Assert.that(!mMapping.isEmpty());
        }
        List<ForeignNote> c = foreignNotes();
        if (!ListenerUtil.mutListener.listen(14249)) {
            importNotes(c);
        }
    }

    /**
     * The number of fields.
     */
    protected int fields() {
        return 0;
    }

    public void initMapping() {
        List<String> flds = mModel.getFieldsNames();
        if (!ListenerUtil.mutListener.listen(14250)) {
            // truncate to provided count
            flds = flds.subList(0, Math.min(flds.size(), fields()));
        }
        if (!ListenerUtil.mutListener.listen(14257)) {
            // if there's room left, add tags
            if ((ListenerUtil.mutListener.listen(14255) ? (fields() >= flds.size()) : (ListenerUtil.mutListener.listen(14254) ? (fields() <= flds.size()) : (ListenerUtil.mutListener.listen(14253) ? (fields() < flds.size()) : (ListenerUtil.mutListener.listen(14252) ? (fields() != flds.size()) : (ListenerUtil.mutListener.listen(14251) ? (fields() == flds.size()) : (fields() > flds.size()))))))) {
                if (!ListenerUtil.mutListener.listen(14256)) {
                    flds.add("_tags");
                }
            }
        }
        // and if there's still room left, pad
        int iterations = (ListenerUtil.mutListener.listen(14261) ? (fields() % flds.size()) : (ListenerUtil.mutListener.listen(14260) ? (fields() / flds.size()) : (ListenerUtil.mutListener.listen(14259) ? (fields() * flds.size()) : (ListenerUtil.mutListener.listen(14258) ? (fields() + flds.size()) : (fields() - flds.size())))));
        if (!ListenerUtil.mutListener.listen(14268)) {
            {
                long _loopCounter277 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(14267) ? (i >= iterations) : (ListenerUtil.mutListener.listen(14266) ? (i <= iterations) : (ListenerUtil.mutListener.listen(14265) ? (i > iterations) : (ListenerUtil.mutListener.listen(14264) ? (i != iterations) : (ListenerUtil.mutListener.listen(14263) ? (i == iterations) : (i < iterations)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter277", ++_loopCounter277);
                    if (!ListenerUtil.mutListener.listen(14262)) {
                        flds.add(null);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14269)) {
            mMapping = flds;
        }
    }

    boolean mappingOk() {
        return mMapping.contains(mModel.getJSONArray("flds").getJSONObject(0).getString("name"));
    }

    @NonNull
    protected List<ForeignNote> foreignNotes() {
        return new ArrayList<>();
    }

    /**
     * Open file and ensure it's in the right format.
     */
    protected void open() {
    }

    /**
     * Closes the open file.
     */
    protected void close() {
    }

    /**
     * Convert each card into a note, apply attributes and add to col.
     */
    public void importNotes(List<ForeignNote> notes) {
        if (!ListenerUtil.mutListener.listen(14270)) {
            Assert.that(mappingOk());
        }
        if (!ListenerUtil.mutListener.listen(14271)) {
            // note whether tags are mapped
            mTagsMapped = false;
        }
        if (!ListenerUtil.mutListener.listen(14274)) {
            {
                long _loopCounter278 = 0;
                for (String f : mMapping) {
                    ListenerUtil.loopListener.listen("_loopCounter278", ++_loopCounter278);
                    if (!ListenerUtil.mutListener.listen(14273)) {
                        if ("_tags".equals(f)) {
                            if (!ListenerUtil.mutListener.listen(14272)) {
                                mTagsMapped = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        // gather checks for duplicate comparison
        HashMap<Long, List<Long>> csums = new HashMap<>();
        try (Cursor c = mCol.getDb().query("select csum, id from notes where mid = ?", mModel.getLong("id"))) {
            if (!ListenerUtil.mutListener.listen(14278)) {
                {
                    long _loopCounter279 = 0;
                    while (c.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter279", ++_loopCounter279);
                        long csum = c.getLong(0);
                        long id = c.getLong(1);
                        if (!ListenerUtil.mutListener.listen(14277)) {
                            if (csums.containsKey(csum)) {
                                if (!ListenerUtil.mutListener.listen(14276)) {
                                    csums.get(csum).add(id);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(14275)) {
                                    csums.put(csum, new ArrayList<>(Collections.singletonList(id)));
                                }
                            }
                        }
                    }
                }
            }
        }
        HashSet<String> firsts = new HashSet<>(notes.size());
        int fld0index = mMapping.indexOf(mModel.getJSONArray("flds").getJSONObject(0).getString("name"));
        if (!ListenerUtil.mutListener.listen(14279)) {
            mFMap = Models.fieldMap(mModel);
        }
        if (!ListenerUtil.mutListener.listen(14280)) {
            mNextId = mCol.getTime().timestampID(mCol.getDb(), "notes");
        }
        // loop through the notes
        List<Object[]> updates = new ArrayList<>(notes.size());
        List<String> updateLog = new ArrayList<>(notes.size());
        // PORT: Translations moved closer to their sources
        List<Object[]> _new = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(14281)) {
            _ids = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(14282)) {
            mEmptyNotes = false;
        }
        int dupeCount = 0;
        List<String> dupes = new ArrayList<>(notes.size());
        if (!ListenerUtil.mutListener.listen(14337)) {
            {
                long _loopCounter282 = 0;
                for (ForeignNote n : notes) {
                    ListenerUtil.loopListener.listen("_loopCounter282", ++_loopCounter282);
                    if (!ListenerUtil.mutListener.listen(14293)) {
                        {
                            long _loopCounter280 = 0;
                            for (int c = 0; (ListenerUtil.mutListener.listen(14292) ? (c >= n.mFields.size()) : (ListenerUtil.mutListener.listen(14291) ? (c <= n.mFields.size()) : (ListenerUtil.mutListener.listen(14290) ? (c > n.mFields.size()) : (ListenerUtil.mutListener.listen(14289) ? (c != n.mFields.size()) : (ListenerUtil.mutListener.listen(14288) ? (c == n.mFields.size()) : (c < n.mFields.size())))))); c++) {
                                ListenerUtil.loopListener.listen("_loopCounter280", ++_loopCounter280);
                                if (!ListenerUtil.mutListener.listen(14284)) {
                                    if (!this.mAllowHTML) {
                                        if (!ListenerUtil.mutListener.listen(14283)) {
                                            n.mFields.set(c, HtmlUtils.escape(n.mFields.get(c)));
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(14285)) {
                                    n.mFields.set(c, n.mFields.get(c).trim());
                                }
                                if (!ListenerUtil.mutListener.listen(14287)) {
                                    if (!this.mAllowHTML) {
                                        if (!ListenerUtil.mutListener.listen(14286)) {
                                            n.mFields.set(c, n.mFields.get(c).replace("\n", "<br>"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    String fld0 = n.mFields.get(fld0index);
                    long csum = fieldChecksum(fld0);
                    if (!ListenerUtil.mutListener.listen(14301)) {
                        // first field must exist
                        if ((ListenerUtil.mutListener.listen(14299) ? (fld0 == null && (ListenerUtil.mutListener.listen(14298) ? (fld0.length() >= 0) : (ListenerUtil.mutListener.listen(14297) ? (fld0.length() <= 0) : (ListenerUtil.mutListener.listen(14296) ? (fld0.length() > 0) : (ListenerUtil.mutListener.listen(14295) ? (fld0.length() < 0) : (ListenerUtil.mutListener.listen(14294) ? (fld0.length() != 0) : (fld0.length() == 0))))))) : (fld0 == null || (ListenerUtil.mutListener.listen(14298) ? (fld0.length() >= 0) : (ListenerUtil.mutListener.listen(14297) ? (fld0.length() <= 0) : (ListenerUtil.mutListener.listen(14296) ? (fld0.length() > 0) : (ListenerUtil.mutListener.listen(14295) ? (fld0.length() < 0) : (ListenerUtil.mutListener.listen(14294) ? (fld0.length() != 0) : (fld0.length() == 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(14300)) {
                                getLog().add(getString(R.string.note_importer_error_empty_first_field, TextUtils.join(" ", n.mFields)));
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14304)) {
                        // earlier in import?
                        if ((ListenerUtil.mutListener.listen(14302) ? (firsts.contains(fld0) || mImportMode != ADD_MODE) : (firsts.contains(fld0) && mImportMode != ADD_MODE))) {
                            if (!ListenerUtil.mutListener.listen(14303)) {
                                // duplicates in source file; log and ignore
                                getLog().add(getString(R.string.note_importer_error_appeared_twice, fld0));
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14305)) {
                        firsts.add(fld0);
                    }
                    // already exists?
                    boolean found = false;
                    if (!ListenerUtil.mutListener.listen(14326)) {
                        if (csums.containsKey(csum)) {
                            if (!ListenerUtil.mutListener.listen(14325)) {
                                {
                                    long _loopCounter281 = 0;
                                    // csum is not a guarantee; have to check
                                    for (Long id : csums.get(csum)) {
                                        ListenerUtil.loopListener.listen("_loopCounter281", ++_loopCounter281);
                                        String flds = mCol.getDb().queryString("select flds from notes where id = ?", id);
                                        String[] sflds = splitFields(flds);
                                        if (!ListenerUtil.mutListener.listen(14324)) {
                                            if (fld0.equals(sflds[0])) {
                                                if (!ListenerUtil.mutListener.listen(14306)) {
                                                    // duplicate
                                                    found = true;
                                                }
                                                if (!ListenerUtil.mutListener.listen(14323)) {
                                                    if (mImportMode == UPDATE_MODE) {
                                                        Object[] data = updateData(n, id, sflds);
                                                        if (!ListenerUtil.mutListener.listen(14322)) {
                                                            if ((ListenerUtil.mutListener.listen(14317) ? (data != null || (ListenerUtil.mutListener.listen(14316) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(14315) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(14314) ? (data.length < 0) : (ListenerUtil.mutListener.listen(14313) ? (data.length != 0) : (ListenerUtil.mutListener.listen(14312) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(14316) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(14315) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(14314) ? (data.length < 0) : (ListenerUtil.mutListener.listen(14313) ? (data.length != 0) : (ListenerUtil.mutListener.listen(14312) ? (data.length == 0) : (data.length > 0))))))))) {
                                                                if (!ListenerUtil.mutListener.listen(14318)) {
                                                                    updates.add(data);
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(14319)) {
                                                                    updateLog.add(getString(R.string.note_importer_error_first_field_matched, fld0));
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(14320)) {
                                                                    dupeCount += 1;
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(14321)) {
                                                                    found = true;
                                                                }
                                                            }
                                                        }
                                                    } else if (mImportMode == IGNORE_MODE) {
                                                        if (!ListenerUtil.mutListener.listen(14311)) {
                                                            dupeCount += 1;
                                                        }
                                                    } else if (mImportMode == ADD_MODE) {
                                                        if (!ListenerUtil.mutListener.listen(14309)) {
                                                            // allow duplicates in this case
                                                            if (!dupes.contains(fld0)) {
                                                                if (!ListenerUtil.mutListener.listen(14307)) {
                                                                    // duplicates are in the collection already
                                                                    updateLog.add(getString(R.string.note_importer_error_added_duplicate_first_field, fld0));
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(14308)) {
                                                                    dupes.add(fld0);
                                                                }
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(14310)) {
                                                            found = false;
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
                    if (!ListenerUtil.mutListener.listen(14336)) {
                        // newly add
                        if (!found) {
                            Object[] data = newData(n);
                            if (!ListenerUtil.mutListener.listen(14335)) {
                                if ((ListenerUtil.mutListener.listen(14332) ? (data != null || (ListenerUtil.mutListener.listen(14331) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(14330) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(14329) ? (data.length < 0) : (ListenerUtil.mutListener.listen(14328) ? (data.length != 0) : (ListenerUtil.mutListener.listen(14327) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(14331) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(14330) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(14329) ? (data.length < 0) : (ListenerUtil.mutListener.listen(14328) ? (data.length != 0) : (ListenerUtil.mutListener.listen(14327) ? (data.length == 0) : (data.length > 0))))))))) {
                                    if (!ListenerUtil.mutListener.listen(14333)) {
                                        _new.add(data);
                                    }
                                    if (!ListenerUtil.mutListener.listen(14334)) {
                                        // note that we've seen this note once already
                                        firsts.add(fld0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14338)) {
            addNew(_new);
        }
        if (!ListenerUtil.mutListener.listen(14339)) {
            addUpdates(updates);
        }
        if (!ListenerUtil.mutListener.listen(14340)) {
            // make sure to update sflds, etc
            mCol.updateFieldCache(_ids);
        }
        if (!ListenerUtil.mutListener.listen(14342)) {
            // generate cards
            if (!mCol.genCards(_ids, mModel).isEmpty()) {
                if (!ListenerUtil.mutListener.listen(14341)) {
                    this.getLog().add(0, getString(R.string.note_importer_empty_cards_found));
                }
            }
        }
        // have the same due#
        long did = mCol.getDecks().selected();
        DeckConfig conf = mCol.getDecks().confForDid(did);
        if (!ListenerUtil.mutListener.listen(14344)) {
            // in order due?
            if (conf.getJSONObject("new").getInt("order") == NEW_CARDS_RANDOM) {
                if (!ListenerUtil.mutListener.listen(14343)) {
                    mCol.getSched().randomizeCards(did);
                }
            }
        }
        String part1 = getQuantityString(R.plurals.note_importer_notes_added, _new.size());
        String part2 = getQuantityString(R.plurals.note_importer_notes_updated, mUpdateCount);
        int unchanged;
        if (mImportMode == UPDATE_MODE) {
            unchanged = (ListenerUtil.mutListener.listen(14348) ? (dupeCount % mUpdateCount) : (ListenerUtil.mutListener.listen(14347) ? (dupeCount / mUpdateCount) : (ListenerUtil.mutListener.listen(14346) ? (dupeCount * mUpdateCount) : (ListenerUtil.mutListener.listen(14345) ? (dupeCount + mUpdateCount) : (dupeCount - mUpdateCount)))));
        } else if (mImportMode == IGNORE_MODE) {
            unchanged = dupeCount;
        } else {
            unchanged = 0;
        }
        String part3 = getQuantityString(R.plurals.note_importer_notes_unchanged, unchanged);
        if (!ListenerUtil.mutListener.listen(14349)) {
            mLog.add(String.format("%s, %s, %s.", part1, part2, part3));
        }
        if (!ListenerUtil.mutListener.listen(14350)) {
            mLog.addAll(updateLog);
        }
        if (!ListenerUtil.mutListener.listen(14352)) {
            if (mEmptyNotes) {
                if (!ListenerUtil.mutListener.listen(14351)) {
                    mLog.add(getString(R.string.note_importer_error_empty_notes));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14353)) {
            mTotal = _ids.size();
        }
    }

    @Nullable
    private Object[] newData(ForeignNote n) {
        long id = mNextId;
        if (!ListenerUtil.mutListener.listen(14354)) {
            mNextId++;
        }
        if (!ListenerUtil.mutListener.listen(14355)) {
            _ids.add(id);
        }
        if (!ListenerUtil.mutListener.listen(14356)) {
            if (!processFields(n)) {
                return null;
            }
        }
        return new Object[] { id, guid64(), mModel.getLong("id"), mCol.getTime().intTime(), mCol.usn(), mCol.getTags().join(n.mTags), n.fieldsStr, "", "", 0, "" };
    }

    private void addNew(List<Object[]> rows) {
        if (!ListenerUtil.mutListener.listen(14357)) {
            mCol.getDb().executeMany("insert or replace into notes values (?,?,?,?,?,?,?,?,?,?,?)", rows);
        }
    }

    private Object[] updateData(ForeignNote n, long id, String[] sflds) {
        if (!ListenerUtil.mutListener.listen(14358)) {
            _ids.add(id);
        }
        if (!processFields(n, sflds)) {
            return null;
        }
        String tags;
        if (mTagsMapped) {
            tags = mCol.getTags().join(n.mTags);
            return new Object[] { mCol.getTime().intTime(), mCol.usn(), n.fieldsStr, tags, id, n.fieldsStr, tags };
        } else if (mTagModified != null) {
            tags = mCol.getDb().queryString("select tags from notes where id = ?", id);
            List<String> tagList = mCol.getTags().split(tags);
            if (!ListenerUtil.mutListener.listen(14359)) {
                tagList.addAll(StringUtils.splitOnWhitespace(mTagModified));
            }
            tags = mCol.getTags().join(tagList);
            return new Object[] { mCol.getTime().intTime(), mCol.usn(), n.fieldsStr, tags, id, n.fieldsStr };
        } else {
            // This looks inconsistent but is fine, see: addUpdates
            return new Object[] { mCol.getTime().intTime(), mCol.usn(), n.fieldsStr, id, n.fieldsStr };
        }
    }

    private void addUpdates(List<Object[]> rows) {
        int changes = mCol.getDb().queryScalar("select total_changes()");
        if (!ListenerUtil.mutListener.listen(14363)) {
            if (mTagsMapped) {
                if (!ListenerUtil.mutListener.listen(14362)) {
                    mCol.getDb().executeMany("update notes set mod = ?, usn = ?, flds = ?, tags = ? " + "where id = ? and (flds != ? or tags != ?)", rows);
                }
            } else if (mTagModified != null) {
                if (!ListenerUtil.mutListener.listen(14361)) {
                    mCol.getDb().executeMany("update notes set mod = ?, usn = ?, flds = ?, tags = ? " + "where id = ? and flds != ?", rows);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14360)) {
                    mCol.getDb().executeMany("update notes set mod = ?, usn = ?, flds = ? " + "where id = ? and flds != ?", rows);
                }
            }
        }
        int changes2 = mCol.getDb().queryScalar("select total_changes()");
        if (!ListenerUtil.mutListener.listen(14368)) {
            mUpdateCount = (ListenerUtil.mutListener.listen(14367) ? (changes2 % changes) : (ListenerUtil.mutListener.listen(14366) ? (changes2 / changes) : (ListenerUtil.mutListener.listen(14365) ? (changes2 * changes) : (ListenerUtil.mutListener.listen(14364) ? (changes2 + changes) : (changes2 - changes)))));
        }
    }

    private boolean processFields(ForeignNote note) {
        return processFields(note, null);
    }

    private boolean processFields(ForeignNote note, @Nullable String[] fields) {
        if (!ListenerUtil.mutListener.listen(14377)) {
            if (fields == null) {
                int length = mModel.getJSONArray("flds").length();
                if (!ListenerUtil.mutListener.listen(14369)) {
                    fields = new String[length];
                }
                if (!ListenerUtil.mutListener.listen(14376)) {
                    {
                        long _loopCounter283 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(14375) ? (i >= length) : (ListenerUtil.mutListener.listen(14374) ? (i <= length) : (ListenerUtil.mutListener.listen(14373) ? (i > length) : (ListenerUtil.mutListener.listen(14372) ? (i != length) : (ListenerUtil.mutListener.listen(14371) ? (i == length) : (i < length)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter283", ++_loopCounter283);
                            if (!ListenerUtil.mutListener.listen(14370)) {
                                fields[i] = "";
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14382)) {
            {
                long _loopCounter284 = 0;
                for (Map.Entry<Integer, String> entry : enumerate(mMapping)) {
                    ListenerUtil.loopListener.listen("_loopCounter284", ++_loopCounter284);
                    if (!ListenerUtil.mutListener.listen(14378)) {
                        if (entry.getValue() == null) {
                            continue;
                        }
                    }
                    int c = entry.getKey();
                    if (!ListenerUtil.mutListener.listen(14381)) {
                        if (entry.getValue().equals("_tags")) {
                            if (!ListenerUtil.mutListener.listen(14380)) {
                                note.mTags.addAll(mCol.getTags().split(note.mFields.get(c)));
                            }
                        } else {
                            Integer sidx = mFMap.get(entry.getValue()).first;
                            if (!ListenerUtil.mutListener.listen(14379)) {
                                fields[sidx] = note.mFields.get(c);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14383)) {
            note.fieldsStr = joinFields(fields);
        }
        ArrayList<Integer> ords = Models.availOrds(mModel, fields, mTemplateParsed);
        if (!ListenerUtil.mutListener.listen(14385)) {
            if (ords.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(14384)) {
                    mEmptyNotes = true;
                }
                return false;
            }
        }
        return true;
    }

    private <T> List<Map.Entry<Integer, T>> enumerate(List<T> list) {
        List<Map.Entry<Integer, T>> ret = new ArrayList<>(list.size());
        int index = 0;
        if (!ListenerUtil.mutListener.listen(14388)) {
            {
                long _loopCounter285 = 0;
                for (T el : list) {
                    ListenerUtil.loopListener.listen("_loopCounter285", ++_loopCounter285);
                    if (!ListenerUtil.mutListener.listen(14386)) {
                        ret.add(new AbstractMap.SimpleEntry<>(index, el));
                    }
                    if (!ListenerUtil.mutListener.listen(14387)) {
                        index++;
                    }
                }
            }
        }
        return ret;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setImportMode(ImportMode mode) {
        if (!ListenerUtil.mutListener.listen(14389)) {
            this.mImportMode = mode;
        }
    }

    private String getQuantityString(@PluralsRes int res, int quantity) {
        return AnkiDroidApp.getAppResources().getQuantityString(res, quantity, quantity);
    }

    @NonNull
    protected String getString(@StringRes int res) {
        return AnkiDroidApp.getAppResources().getString(res);
    }

    @NonNull
    protected String getString(int res, @NonNull Object... formatArgs) {
        return AnkiDroidApp.getAppResources().getString(res, formatArgs);
    }

    public void setAllowHtml(boolean allowHtml) {
        if (!ListenerUtil.mutListener.listen(14390)) {
            this.mAllowHTML = allowHtml;
        }
    }

    public enum ImportMode {

        // 0
        UPDATE_MODE,
        // 1
        IGNORE_MODE,
        // 2
        ADD_MODE
    }

    /**
     * A temporary object storing fields and attributes.
     */
    public static class ForeignNote {

        public final List<String> mFields = new ArrayList<>();

        public final List<String> mTags = new ArrayList<>();

        public Object deck = new Object();

        public String fieldsStr = "";
    }

    public static class ForeignCard {

        public final long mDue = 0;

        public final int mIvl = 1;

        public final int mFactor = Consts.STARTING_FACTOR;

        public final int mReps = 0;

        public final int mLapses = 0;
    }

    private static class Triple {

        public final long mNid;

        public final Integer mOrd;

        public final ForeignCard mCard;

        public Triple(long nid, Integer ord, ForeignCard card) {
            this.mNid = nid;
            this.mOrd = ord;
            this.mCard = card;
        }
    }
}

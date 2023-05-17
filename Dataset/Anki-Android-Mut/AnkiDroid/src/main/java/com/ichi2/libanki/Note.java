/**
 * *************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
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
import android.util.Pair;
import com.ichi2.utils.JSONObject;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.MethodNamingConventions" })
public class Note implements Cloneable {

    private final Collection mCol;

    private final long mId;

    private String mGuId;

    private Model mModel;

    private long mMid;

    private ArrayList<String> mTags;

    private String[] mFields;

    private int mFlags;

    private String mData;

    private Map<String, Pair<Integer, JSONObject>> mFMap;

    private long mScm;

    private int mUsn;

    private long mMod;

    private boolean mNewlyAdded;

    public Note(Collection col, Long id) {
        this(col, null, id);
    }

    public Note(Collection col, Model model) {
        this(col, model, null);
    }

    public Note(Collection col, Model model, Long id) {
        assert !((ListenerUtil.mutListener.listen(23594) ? (model != null || id != null) : (model != null && id != null)));
        mCol = col;
        if (id != null) {
            mId = id;
            if (!ListenerUtil.mutListener.listen(23605)) {
                load();
            }
        } else {
            mId = mCol.getTime().timestampID(mCol.getDb(), "notes");
            if (!ListenerUtil.mutListener.listen(23595)) {
                mGuId = Utils.guid64();
            }
            if (!ListenerUtil.mutListener.listen(23596)) {
                mModel = model;
            }
            if (!ListenerUtil.mutListener.listen(23597)) {
                mMid = model.getLong("id");
            }
            if (!ListenerUtil.mutListener.listen(23598)) {
                mTags = new ArrayList<>();
            }
            if (!ListenerUtil.mutListener.listen(23599)) {
                mFields = new String[model.getJSONArray("flds").length()];
            }
            if (!ListenerUtil.mutListener.listen(23600)) {
                Arrays.fill(mFields, "");
            }
            if (!ListenerUtil.mutListener.listen(23601)) {
                mFlags = 0;
            }
            if (!ListenerUtil.mutListener.listen(23602)) {
                mData = "";
            }
            if (!ListenerUtil.mutListener.listen(23603)) {
                mFMap = Models.fieldMap(mModel);
            }
            if (!ListenerUtil.mutListener.listen(23604)) {
                mScm = mCol.getScm();
            }
        }
    }

    public void load() {
        if (!ListenerUtil.mutListener.listen(23606)) {
            Timber.d("load()");
        }
        try (Cursor cursor = mCol.getDb().query("SELECT guid, mid, mod, usn, tags, flds, flags, data FROM notes WHERE id = ?", mId)) {
            if (!ListenerUtil.mutListener.listen(23607)) {
                if (!cursor.moveToFirst()) {
                    throw new WrongId(mId, "note");
                }
            }
            if (!ListenerUtil.mutListener.listen(23608)) {
                mGuId = cursor.getString(0);
            }
            if (!ListenerUtil.mutListener.listen(23609)) {
                mMid = cursor.getLong(1);
            }
            if (!ListenerUtil.mutListener.listen(23610)) {
                mMod = cursor.getLong(2);
            }
            if (!ListenerUtil.mutListener.listen(23611)) {
                mUsn = cursor.getInt(3);
            }
            if (!ListenerUtil.mutListener.listen(23612)) {
                mTags = mCol.getTags().split(cursor.getString(4));
            }
            if (!ListenerUtil.mutListener.listen(23613)) {
                mFields = Utils.splitFields(cursor.getString(5));
            }
            if (!ListenerUtil.mutListener.listen(23614)) {
                mFlags = cursor.getInt(6);
            }
            if (!ListenerUtil.mutListener.listen(23615)) {
                mData = cursor.getString(7);
            }
            if (!ListenerUtil.mutListener.listen(23616)) {
                mModel = mCol.getModels().get(mMid);
            }
            if (!ListenerUtil.mutListener.listen(23617)) {
                mFMap = Models.fieldMap(mModel);
            }
            if (!ListenerUtil.mutListener.listen(23618)) {
                mScm = mCol.getScm();
            }
        }
    }

    public void reloadModel() {
        if (!ListenerUtil.mutListener.listen(23619)) {
            mModel = mCol.getModels().get(mMid);
        }
    }

    /*
     * If fields or tags have changed, write changes to disk.
     */
    public void flush() {
        if (!ListenerUtil.mutListener.listen(23620)) {
            flush(null);
        }
    }

    public void flush(Long mod) {
        if (!ListenerUtil.mutListener.listen(23621)) {
            flush(mod, true);
        }
    }

    public void flush(Long mod, boolean changeUsn) {
        assert (ListenerUtil.mutListener.listen(23626) ? (mScm >= mCol.getScm()) : (ListenerUtil.mutListener.listen(23625) ? (mScm <= mCol.getScm()) : (ListenerUtil.mutListener.listen(23624) ? (mScm > mCol.getScm()) : (ListenerUtil.mutListener.listen(23623) ? (mScm < mCol.getScm()) : (ListenerUtil.mutListener.listen(23622) ? (mScm != mCol.getScm()) : (mScm == mCol.getScm()))))));
        if (!ListenerUtil.mutListener.listen(23627)) {
            _preFlush();
        }
        if (!ListenerUtil.mutListener.listen(23629)) {
            if (changeUsn) {
                if (!ListenerUtil.mutListener.listen(23628)) {
                    mUsn = mCol.usn();
                }
            }
        }
        Pair<String, Long> csumAndStrippedFieldField = Utils.sfieldAndCsum(mFields, getCol().getModels().sortIdx(mModel));
        String sfld = csumAndStrippedFieldField.first;
        String tags = stringTags();
        String fields = joinedFields();
        if (!ListenerUtil.mutListener.listen(23636)) {
            if ((ListenerUtil.mutListener.listen(23635) ? (mod == null || (ListenerUtil.mutListener.listen(23634) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) >= 0) : (ListenerUtil.mutListener.listen(23633) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) <= 0) : (ListenerUtil.mutListener.listen(23632) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) < 0) : (ListenerUtil.mutListener.listen(23631) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) != 0) : (ListenerUtil.mutListener.listen(23630) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) == 0) : (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) > 0))))))) : (mod == null && (ListenerUtil.mutListener.listen(23634) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) >= 0) : (ListenerUtil.mutListener.listen(23633) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) <= 0) : (ListenerUtil.mutListener.listen(23632) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) < 0) : (ListenerUtil.mutListener.listen(23631) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) != 0) : (ListenerUtil.mutListener.listen(23630) ? (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) == 0) : (mCol.getDb().queryScalar("select 1 from notes where id = ? and tags = ? and flds = ?", Long.toString(mId), tags, fields) > 0))))))))) {
                return;
            }
        }
        long csum = csumAndStrippedFieldField.second;
        if (!ListenerUtil.mutListener.listen(23637)) {
            mMod = mod != null ? mod : mCol.getTime().intTime();
        }
        if (!ListenerUtil.mutListener.listen(23638)) {
            mCol.getDb().execute("insert or replace into notes values (?,?,?,?,?,?,?,?,?,?,?)", mId, mGuId, mMid, mMod, mUsn, tags, fields, sfld, csum, mFlags, mData);
        }
        if (!ListenerUtil.mutListener.listen(23639)) {
            mCol.getTags().register(mTags);
        }
        if (!ListenerUtil.mutListener.listen(23640)) {
            _postFlush();
        }
    }

    public String joinedFields() {
        return Utils.joinFields(mFields);
    }

    public int numberOfCards() {
        return (int) mCol.getDb().queryLongScalar("SELECT count() FROM cards WHERE nid = ?", mId);
    }

    public List<Long> cids() {
        return mCol.getDb().queryLongList("SELECT id FROM cards WHERE nid = ? ORDER BY ord", mId);
    }

    public ArrayList<Card> cards() {
        ArrayList<Card> cards = new ArrayList<>(cids().size());
        if (!ListenerUtil.mutListener.listen(23642)) {
            {
                long _loopCounter602 = 0;
                for (long cid : cids()) {
                    ListenerUtil.loopListener.listen("_loopCounter602", ++_loopCounter602);
                    if (!ListenerUtil.mutListener.listen(23641)) {
                        // Not a big trouble since most note have a small number of cards.
                        cards.add(mCol.getCard(cid));
                    }
                }
            }
        }
        return cards;
    }

    /**
     * The first card, assuming it exists.
     */
    public Card firstCard() {
        return mCol.getCard(mCol.getDb().queryLongScalar("SELECT id FROM cards WHERE nid = ? ORDER BY ord LIMIT 1", mId));
    }

    public Model model() {
        return mModel;
    }

    public String[] keys() {
        return (String[]) mFMap.keySet().toArray();
    }

    public String[] values() {
        return mFields;
    }

    public String[][] items() {
        // The items here are only used in the note editor, so it's a low priority.
        String[][] result = new String[mFMap.size()][2];
        if (!ListenerUtil.mutListener.listen(23645)) {
            {
                long _loopCounter603 = 0;
                for (String fname : mFMap.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter603", ++_loopCounter603);
                    int i = mFMap.get(fname).first;
                    if (!ListenerUtil.mutListener.listen(23643)) {
                        result[i][0] = fname;
                    }
                    if (!ListenerUtil.mutListener.listen(23644)) {
                        result[i][1] = mFields[i];
                    }
                }
            }
        }
        return result;
    }

    private int _fieldOrd(String key) {
        Pair<Integer, JSONObject> fieldPair = mFMap.get(key);
        if (!ListenerUtil.mutListener.listen(23646)) {
            if (fieldPair == null) {
                throw new IllegalArgumentException(String.format("No field named '%s' found", key));
            }
        }
        return fieldPair.first;
    }

    public String getItem(String key) {
        return mFields[_fieldOrd(key)];
    }

    public void setItem(String key, String value) {
        if (!ListenerUtil.mutListener.listen(23647)) {
            mFields[_fieldOrd(key)] = value;
        }
    }

    public boolean contains(String key) {
        return mFMap.containsKey(key);
    }

    public boolean hasTag(String tag) {
        return mCol.getTags().inList(tag, mTags);
    }

    public String stringTags() {
        return mCol.getTags().join(mCol.getTags().canonify(mTags));
    }

    public void setTagsFromStr(String str) {
        if (!ListenerUtil.mutListener.listen(23648)) {
            mTags = mCol.getTags().split(str);
        }
    }

    public void delTag(String tag) {
        List<String> rem = new ArrayList<>(mTags.size());
        if (!ListenerUtil.mutListener.listen(23651)) {
            {
                long _loopCounter604 = 0;
                for (String t : mTags) {
                    ListenerUtil.loopListener.listen("_loopCounter604", ++_loopCounter604);
                    if (!ListenerUtil.mutListener.listen(23650)) {
                        if (t.equalsIgnoreCase(tag)) {
                            if (!ListenerUtil.mutListener.listen(23649)) {
                                rem.add(t);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23653)) {
            {
                long _loopCounter605 = 0;
                for (String r : rem) {
                    ListenerUtil.loopListener.listen("_loopCounter605", ++_loopCounter605);
                    if (!ListenerUtil.mutListener.listen(23652)) {
                        mTags.remove(r);
                    }
                }
            }
        }
    }

    /*
     *  duplicates will be stripped on save
     */
    public void addTag(String tag) {
        if (!ListenerUtil.mutListener.listen(23654)) {
            mTags.add(tag);
        }
    }

    public void addTags(AbstractSet<String> tags) {
        if (!ListenerUtil.mutListener.listen(23655)) {
            mTags.addAll(tags);
        }
    }

    public enum DupeOrEmpty {

        CORRECT, EMPTY, DUPE
    }

    /**
     * @return whether it has no content, dupe first field, or nothing remarkable.
     */
    public DupeOrEmpty dupeOrEmpty() {
        String val = mFields[0];
        if (!ListenerUtil.mutListener.listen(23661)) {
            if ((ListenerUtil.mutListener.listen(23660) ? (val.trim().length() >= 0) : (ListenerUtil.mutListener.listen(23659) ? (val.trim().length() <= 0) : (ListenerUtil.mutListener.listen(23658) ? (val.trim().length() > 0) : (ListenerUtil.mutListener.listen(23657) ? (val.trim().length() < 0) : (ListenerUtil.mutListener.listen(23656) ? (val.trim().length() != 0) : (val.trim().length() == 0))))))) {
                return DupeOrEmpty.EMPTY;
            }
        }
        Pair<String, Long> csumAndStrippedFieldField = Utils.sfieldAndCsum(mFields, 0);
        long csum = csumAndStrippedFieldField.second;
        // find any matching csums and compare
        String strippedFirstField = csumAndStrippedFieldField.first;
        if (!ListenerUtil.mutListener.listen(23663)) {
            {
                long _loopCounter606 = 0;
                for (String flds : mCol.getDb().queryStringList("SELECT flds FROM notes WHERE csum = ? AND id != ? AND mid = ?", csum, (mId), mMid)) {
                    ListenerUtil.loopListener.listen("_loopCounter606", ++_loopCounter606);
                    if (!ListenerUtil.mutListener.listen(23662)) {
                        if (Utils.stripHTMLMedia(Utils.splitFields(flds)[0]).equals(strippedFirstField)) {
                            return DupeOrEmpty.DUPE;
                        }
                    }
                }
            }
        }
        return DupeOrEmpty.CORRECT;
    }

    /*
     * have we been added yet?
     */
    private void _preFlush() {
        if (!ListenerUtil.mutListener.listen(23669)) {
            mNewlyAdded = (ListenerUtil.mutListener.listen(23668) ? (mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE nid = ?", mId) >= 0) : (ListenerUtil.mutListener.listen(23667) ? (mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE nid = ?", mId) <= 0) : (ListenerUtil.mutListener.listen(23666) ? (mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE nid = ?", mId) > 0) : (ListenerUtil.mutListener.listen(23665) ? (mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE nid = ?", mId) < 0) : (ListenerUtil.mutListener.listen(23664) ? (mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE nid = ?", mId) != 0) : (mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE nid = ?", mId) == 0))))));
        }
    }

    /*
     * generate missing cards
     */
    private void _postFlush() {
        if (!ListenerUtil.mutListener.listen(23671)) {
            if (!mNewlyAdded) {
                if (!ListenerUtil.mutListener.listen(23670)) {
                    mCol.genCards(mId, mModel);
                }
            }
        }
    }

    public long getMid() {
        return mMid;
    }

    /**
     * @return the mId
     */
    public long getId() {
        // TODO: Conflicting method name and return value. Reconsider.
        return mId;
    }

    public Collection getCol() {
        return mCol;
    }

    public String getSFld() {
        return mCol.getDb().queryString("SELECT sfld FROM notes WHERE id = ?", mId);
    }

    public String[] getFields() {
        return mFields;
    }

    public void setField(int index, String value) {
        if (!ListenerUtil.mutListener.listen(23672)) {
            mFields[index] = value;
        }
    }

    public long getMod() {
        return mMod;
    }

    public Note clone() {
        try {
            return (Note) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getTags() {
        return mTags;
    }

    @Override
    public boolean equals(Object o) {
        if (!ListenerUtil.mutListener.listen(23673)) {
            if (this == o)
                return true;
        }
        if (!ListenerUtil.mutListener.listen(23675)) {
            if ((ListenerUtil.mutListener.listen(23674) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass())))
                return false;
        }
        Note note = (Note) o;
        return (ListenerUtil.mutListener.listen(23680) ? (mId >= note.mId) : (ListenerUtil.mutListener.listen(23679) ? (mId <= note.mId) : (ListenerUtil.mutListener.listen(23678) ? (mId > note.mId) : (ListenerUtil.mutListener.listen(23677) ? (mId < note.mId) : (ListenerUtil.mutListener.listen(23676) ? (mId != note.mId) : (mId == note.mId))))));
    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    public static class ClozeUtils {

        private static final Pattern mClozeRegexPattern = Pattern.compile("\\{\\{c(\\d+)::");

        /**
         * Calculate the next number that should be used if inserting a new cloze deletion.
         * Per the manual the next number should be greater than any existing cloze deletion
         * even if there are gaps in the sequence, and regardless of existing cloze ordering
         *
         * @param fieldValues Iterable of field values that may contain existing cloze deletions
         * @return the next index that a cloze should be inserted at
         */
        public static int getNextClozeIndex(Iterable<String> fieldValues) {
            int highestClozeId = 0;
            if (!ListenerUtil.mutListener.listen(23689)) {
                {
                    long _loopCounter608 = 0;
                    // Begin looping through the fields
                    for (String fieldLiteral : fieldValues) {
                        ListenerUtil.loopListener.listen("_loopCounter608", ++_loopCounter608);
                        // Begin searching in the current field for cloze references
                        Matcher matcher = mClozeRegexPattern.matcher(fieldLiteral);
                        if (!ListenerUtil.mutListener.listen(23688)) {
                            {
                                long _loopCounter607 = 0;
                                while (matcher.find()) {
                                    ListenerUtil.loopListener.listen("_loopCounter607", ++_loopCounter607);
                                    int detectedClozeId = Integer.parseInt(matcher.group(1));
                                    if (!ListenerUtil.mutListener.listen(23687)) {
                                        if ((ListenerUtil.mutListener.listen(23685) ? (detectedClozeId >= highestClozeId) : (ListenerUtil.mutListener.listen(23684) ? (detectedClozeId <= highestClozeId) : (ListenerUtil.mutListener.listen(23683) ? (detectedClozeId < highestClozeId) : (ListenerUtil.mutListener.listen(23682) ? (detectedClozeId != highestClozeId) : (ListenerUtil.mutListener.listen(23681) ? (detectedClozeId == highestClozeId) : (detectedClozeId > highestClozeId))))))) {
                                            if (!ListenerUtil.mutListener.listen(23686)) {
                                                highestClozeId = detectedClozeId;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return (ListenerUtil.mutListener.listen(23693) ? (highestClozeId % 1) : (ListenerUtil.mutListener.listen(23692) ? (highestClozeId / 1) : (ListenerUtil.mutListener.listen(23691) ? (highestClozeId * 1) : (ListenerUtil.mutListener.listen(23690) ? (highestClozeId - 1) : (highestClozeId + 1)))));
        }
    }
}

/**
 * *************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
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

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.ichi2.utils.JSONObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;
import okhttp3.internal.Util;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Anki maintains a cache of used tags so it can quickly present a list of tags
 * for autocomplete and in the browser. For efficiency, deletions are not
 * tracked, so unused tags can only be removed from the list with a DB check.
 *
 * This module manages the tag cache and tags for notes.
 *
 * This class differs from the python version by keeping the in-memory tag cache as a TreeMap
 * instead of a JSONObject. It is much more convenient to work with a TreeMap in Java, but there
 * may be a performance penalty in doing so (on startup and shutdown).
 */
@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes" })
public class Tags {

    private static final Pattern sCanonify = Pattern.compile("[\"']");

    private final Collection mCol;

    private final TreeMap<String, Integer> mTags = new TreeMap<>();

    private boolean mChanged;

    public Tags(Collection col) {
        mCol = col;
    }

    public void load(String json) {
        JSONObject tags = new JSONObject(json);
        if (!ListenerUtil.mutListener.listen(24055)) {
            {
                long _loopCounter625 = 0;
                for (String t : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter625", ++_loopCounter625);
                    if (!ListenerUtil.mutListener.listen(24054)) {
                        mTags.put(t, tags.getInt(t));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24056)) {
            mChanged = false;
        }
    }

    public void flush() {
        if (!ListenerUtil.mutListener.listen(24062)) {
            if (mChanged) {
                JSONObject tags = new JSONObject();
                if (!ListenerUtil.mutListener.listen(24058)) {
                    {
                        long _loopCounter626 = 0;
                        for (Map.Entry<String, Integer> t : mTags.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter626", ++_loopCounter626);
                            if (!ListenerUtil.mutListener.listen(24057)) {
                                tags.put(t.getKey(), t.getValue());
                            }
                        }
                    }
                }
                ContentValues val = new ContentValues();
                if (!ListenerUtil.mutListener.listen(24059)) {
                    val.put("tags", Utils.jsonToString(tags));
                }
                if (!ListenerUtil.mutListener.listen(24060)) {
                    // TODO: the database update call here sets mod = true. Verify if this is intended.
                    mCol.getDb().update("col", val);
                }
                if (!ListenerUtil.mutListener.listen(24061)) {
                    mChanged = false;
                }
            }
        }
    }

    /**
     * Given a list of tags, add any missing ones to tag registry.
     */
    public void register(Iterable<String> tags) {
        if (!ListenerUtil.mutListener.listen(24063)) {
            register(tags, null);
        }
    }

    public void register(Iterable<String> tags, Integer usn) {
        if (!ListenerUtil.mutListener.listen(24067)) {
            {
                long _loopCounter627 = 0;
                // boolean found = false;
                for (String t : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter627", ++_loopCounter627);
                    if (!ListenerUtil.mutListener.listen(24066)) {
                        if (!mTags.containsKey(t)) {
                            if (!ListenerUtil.mutListener.listen(24064)) {
                                mTags.put(t, usn == null ? mCol.usn() : usn);
                            }
                            if (!ListenerUtil.mutListener.listen(24065)) {
                                mChanged = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public List<String> all() {
        return new ArrayList<>(mTags.keySet());
    }

    public void registerNotes() {
        if (!ListenerUtil.mutListener.listen(24068)) {
            registerNotes(null);
        }
    }

    /**
     * Add any missing tags from notes to the tags list.
     */
    public void registerNotes(java.util.Collection<Long> nids) {
        // when called with a null argument, the old list is cleared first.
        String lim;
        if (nids != null) {
            lim = " WHERE id IN " + Utils.ids2str(nids);
        } else {
            lim = "";
            if (!ListenerUtil.mutListener.listen(24069)) {
                mTags.clear();
            }
            if (!ListenerUtil.mutListener.listen(24070)) {
                mChanged = true;
            }
        }
        List<String> tags = new ArrayList<>(mCol.noteCount());
        try (Cursor cursor = mCol.getDb().query("SELECT DISTINCT tags FROM notes" + lim)) {
            if (!ListenerUtil.mutListener.listen(24072)) {
                {
                    long _loopCounter628 = 0;
                    while (cursor.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter628", ++_loopCounter628);
                        if (!ListenerUtil.mutListener.listen(24071)) {
                            tags.add(cursor.getString(0));
                        }
                    }
                }
            }
        }
        HashSet<String> tagSet = new HashSet<>(split(TextUtils.join(" ", tags)));
        if (!ListenerUtil.mutListener.listen(24073)) {
            register(tagSet);
        }
    }

    public Set<Map.Entry<String, Integer>> allItems() {
        return mTags.entrySet();
    }

    public boolean minusOneValue() {
        return mTags.containsValue(-1);
    }

    public void save() {
        if (!ListenerUtil.mutListener.listen(24074)) {
            mChanged = true;
        }
    }

    /**
     * byDeck returns the tags of the cards in the deck
     * @param did the deck id
     * @param children whether to include the deck's children
     * @return a list of the tags
     */
    public ArrayList<String> byDeck(long did, boolean children) {
        List<String> tags;
        if (children) {
            java.util.Collection<Long> values = mCol.getDecks().children(did).values();
            ArrayList<Long> dids = new ArrayList<>(values.size());
            if (!ListenerUtil.mutListener.listen(24075)) {
                dids.add(did);
            }
            if (!ListenerUtil.mutListener.listen(24076)) {
                dids.addAll(values);
            }
            tags = mCol.getDb().queryStringList("SELECT DISTINCT n.tags FROM cards c, notes n WHERE c.nid = n.id AND c.did IN " + Utils.ids2str(dids));
        } else {
            tags = mCol.getDb().queryStringList("SELECT DISTINCT n.tags FROM cards c, notes n WHERE c.nid = n.id AND c.did = ?", did);
        }
        // Use methods used to get all tags to parse tags here as well.
        return new ArrayList<>(new HashSet<>(split(TextUtils.join(" ", tags))));
    }

    /**
     * FIXME: This method must be fixed before it is used. See note below.
     * Add/remove tags in bulk. TAGS is space-separated.
     *
     * @param ids The cards to tag.
     * @param tags List of tags to add/remove. They are space-separated.
     */
    public void bulkAdd(List<Long> ids, String tags) {
        if (!ListenerUtil.mutListener.listen(24077)) {
            bulkAdd(ids, tags, true);
        }
    }

    /**
     * FIXME: This method must be fixed before it is used. Its behaviour is currently incorrect.
     * This method is currently unused in AnkiDroid so it will not cause any errors in its current state.
     *
     * @param ids The cards to tag.
     * @param tags List of tags to add/remove. They are space-separated.
     * @param add True/False to add/remove.
     */
    public void bulkAdd(List<Long> ids, String tags, boolean add) {
        List<String> newTags = split(tags);
        if (!ListenerUtil.mutListener.listen(24079)) {
            if ((ListenerUtil.mutListener.listen(24078) ? (newTags == null && newTags.isEmpty()) : (newTags == null || newTags.isEmpty()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24081)) {
            // cache tag names
            if (add) {
                if (!ListenerUtil.mutListener.listen(24080)) {
                    register(newTags);
                }
            }
        }
        // find notes missing the tags
        String l;
        if (add) {
            l = "tags not ";
        } else {
            l = "tags ";
        }
        StringBuilder lim = new StringBuilder();
        {
            long _loopCounter629 = 0;
            for (String t : newTags) {
                ListenerUtil.loopListener.listen("_loopCounter629", ++_loopCounter629);
                if (!ListenerUtil.mutListener.listen(24088)) {
                    if ((ListenerUtil.mutListener.listen(24086) ? (lim.length() >= 0) : (ListenerUtil.mutListener.listen(24085) ? (lim.length() <= 0) : (ListenerUtil.mutListener.listen(24084) ? (lim.length() > 0) : (ListenerUtil.mutListener.listen(24083) ? (lim.length() < 0) : (ListenerUtil.mutListener.listen(24082) ? (lim.length() == 0) : (lim.length() != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(24087)) {
                            lim.append(" or ");
                        }
                    }
                }
                t = t.replace("*", "%");
                if (!ListenerUtil.mutListener.listen(24089)) {
                    lim.append(l).append("like '% ").append(t).append(" %'");
                }
            }
        }
        ArrayList<Object[]> res = new ArrayList<>(mCol.getDb().queryScalar("select count() from notes where id in " + Utils.ids2str(ids) + " and (" + lim + ")"));
        try (Cursor cur = mCol.getDb().query("select id, tags from notes where id in " + Utils.ids2str(ids) + " and (" + lim + ")")) {
            if (!ListenerUtil.mutListener.listen(24094)) {
                if (add) {
                    if (!ListenerUtil.mutListener.listen(24093)) {
                        {
                            long _loopCounter631 = 0;
                            while (cur.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter631", ++_loopCounter631);
                                if (!ListenerUtil.mutListener.listen(24092)) {
                                    res.add(new Object[] { addToStr(tags, cur.getString(1)), mCol.getTime().intTime(), mCol.usn(), cur.getLong(0) });
                                }
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(24091)) {
                        {
                            long _loopCounter630 = 0;
                            while (cur.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter630", ++_loopCounter630);
                                if (!ListenerUtil.mutListener.listen(24090)) {
                                    res.add(new Object[] { remFromStr(tags, cur.getString(1)), mCol.getTime().intTime(), mCol.usn(), cur.getLong(0) });
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24095)) {
            // update tags
            mCol.getDb().executeMany("update notes set tags=:t,mod=:n,usn=:u where id = :id", res);
        }
    }

    public void bulkRem(List<Long> ids, String tags) {
        if (!ListenerUtil.mutListener.listen(24096)) {
            bulkAdd(ids, tags, false);
        }
    }

    /**
     * Parse a string and return a list of tags.
     */
    public ArrayList<String> split(String tags) {
        ArrayList<String> list = new ArrayList<>(tags.length());
        if (!ListenerUtil.mutListener.listen(24104)) {
            {
                long _loopCounter632 = 0;
                for (String s : tags.replace('\u3000', ' ').split("\\s")) {
                    ListenerUtil.loopListener.listen("_loopCounter632", ++_loopCounter632);
                    if (!ListenerUtil.mutListener.listen(24103)) {
                        if ((ListenerUtil.mutListener.listen(24101) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(24100) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(24099) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(24098) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(24097) ? (s.length() == 0) : (s.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(24102)) {
                                list.add(s);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * Join tags into a single string, with leading and trailing spaces.
     */
    public String join(java.util.Collection<String> tags) {
        if ((ListenerUtil.mutListener.listen(24110) ? (tags == null && (ListenerUtil.mutListener.listen(24109) ? (tags.size() >= 0) : (ListenerUtil.mutListener.listen(24108) ? (tags.size() <= 0) : (ListenerUtil.mutListener.listen(24107) ? (tags.size() > 0) : (ListenerUtil.mutListener.listen(24106) ? (tags.size() < 0) : (ListenerUtil.mutListener.listen(24105) ? (tags.size() != 0) : (tags.size() == 0))))))) : (tags == null || (ListenerUtil.mutListener.listen(24109) ? (tags.size() >= 0) : (ListenerUtil.mutListener.listen(24108) ? (tags.size() <= 0) : (ListenerUtil.mutListener.listen(24107) ? (tags.size() > 0) : (ListenerUtil.mutListener.listen(24106) ? (tags.size() < 0) : (ListenerUtil.mutListener.listen(24105) ? (tags.size() != 0) : (tags.size() == 0))))))))) {
            return "";
        } else {
            String joined = TextUtils.join(" ", tags);
            return String.format(Locale.US, " %s ", joined);
        }
    }

    /**
     * Add tags if they don't exist, and canonify
     */
    public String addToStr(String addtags, String tags) {
        List<String> currentTags = split(tags);
        if (!ListenerUtil.mutListener.listen(24113)) {
            {
                long _loopCounter633 = 0;
                for (String tag : split(addtags)) {
                    ListenerUtil.loopListener.listen("_loopCounter633", ++_loopCounter633);
                    if (!ListenerUtil.mutListener.listen(24112)) {
                        if (!inList(tag, currentTags)) {
                            if (!ListenerUtil.mutListener.listen(24111)) {
                                currentTags.add(tag);
                            }
                        }
                    }
                }
            }
        }
        return join(canonify(currentTags));
    }

    // submethod of remFromStr in anki
    public boolean wildcard(String pat, String str) {
        String pat_replaced = Pattern.quote(pat).replace("\\*", ".*");
        return Pattern.compile(pat_replaced, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(str).matches();
    }

    /**
     * Delete tags if they exist.
     */
    public String remFromStr(String deltags, String tags) {
        List<String> currentTags = split(tags);
        if (!ListenerUtil.mutListener.listen(24120)) {
            {
                long _loopCounter636 = 0;
                for (String tag : split(deltags)) {
                    ListenerUtil.loopListener.listen("_loopCounter636", ++_loopCounter636);
                    // Usually not a lot of tags are removed simultaneously.
                    List<String> remove = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(24117)) {
                        {
                            long _loopCounter634 = 0;
                            // So don't put initial capacity
                            for (String tx : currentTags) {
                                ListenerUtil.loopListener.listen("_loopCounter634", ++_loopCounter634);
                                if (!ListenerUtil.mutListener.listen(24116)) {
                                    if ((ListenerUtil.mutListener.listen(24114) ? (tag.equalsIgnoreCase(tx) && wildcard(tag, tx)) : (tag.equalsIgnoreCase(tx) || wildcard(tag, tx)))) {
                                        if (!ListenerUtil.mutListener.listen(24115)) {
                                            remove.add(tx);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24119)) {
                        {
                            long _loopCounter635 = 0;
                            // remove them
                            for (String r : remove) {
                                ListenerUtil.loopListener.listen("_loopCounter635", ++_loopCounter635);
                                if (!ListenerUtil.mutListener.listen(24118)) {
                                    currentTags.remove(r);
                                }
                            }
                        }
                    }
                }
            }
        }
        return join(currentTags);
    }

    /**
     * Strip duplicates, adjust case to match existing tags, and sort.
     */
    public TreeSet<String> canonify(List<String> tagList) {
        // used here already guarantees uniqueness and sort order, so we return it as-is without those steps.
        TreeSet<String> strippedTags = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        if (!ListenerUtil.mutListener.listen(24125)) {
            {
                long _loopCounter638 = 0;
                for (String t : tagList) {
                    ListenerUtil.loopListener.listen("_loopCounter638", ++_loopCounter638);
                    String s = sCanonify.matcher(t).replaceAll("");
                    if (!ListenerUtil.mutListener.listen(24123)) {
                        {
                            long _loopCounter637 = 0;
                            for (String existingTag : mTags.keySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter637", ++_loopCounter637);
                                if (!ListenerUtil.mutListener.listen(24122)) {
                                    if (s.equalsIgnoreCase(existingTag)) {
                                        if (!ListenerUtil.mutListener.listen(24121)) {
                                            s = existingTag;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24124)) {
                        strippedTags.add(s);
                    }
                }
            }
        }
        return strippedTags;
    }

    /**
     * True if TAG is in TAGS. Ignore case.
     */
    public boolean inList(String tag, Iterable<String> tags) {
        if (!ListenerUtil.mutListener.listen(24127)) {
            {
                long _loopCounter639 = 0;
                for (String t : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter639", ++_loopCounter639);
                    if (!ListenerUtil.mutListener.listen(24126)) {
                        if (t.equalsIgnoreCase(tag)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void beforeUpload() {
        boolean changed = false;
        if (!ListenerUtil.mutListener.listen(24136)) {
            {
                long _loopCounter640 = 0;
                for (Map.Entry<String, Integer> entry : mTags.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter640", ++_loopCounter640);
                    if (!ListenerUtil.mutListener.listen(24135)) {
                        if ((ListenerUtil.mutListener.listen(24132) ? (entry.getValue() >= 0) : (ListenerUtil.mutListener.listen(24131) ? (entry.getValue() <= 0) : (ListenerUtil.mutListener.listen(24130) ? (entry.getValue() > 0) : (ListenerUtil.mutListener.listen(24129) ? (entry.getValue() < 0) : (ListenerUtil.mutListener.listen(24128) ? (entry.getValue() == 0) : (entry.getValue() != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(24133)) {
                                mTags.put(entry.getKey(), 0);
                            }
                            if (!ListenerUtil.mutListener.listen(24134)) {
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24138)) {
            if (changed) {
                if (!ListenerUtil.mutListener.listen(24137)) {
                    save();
                }
            }
        }
    }

    /**
     * Add a tag to the collection. We use this method instead of exposing mTags publicly.
     */
    public void add(String key, Integer value) {
        if (!ListenerUtil.mutListener.listen(24139)) {
            mTags.put(key, value);
        }
    }
}

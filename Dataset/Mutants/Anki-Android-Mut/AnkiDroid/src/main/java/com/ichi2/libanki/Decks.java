/**
 * *************************************************************************************
 *  Copyright (c) 2009 Daniel Svärd <daniel.svard@gmail.com>                             *
 *  Copyright (c) 2009 Casey Link <unnamedrambler@gmail.com>                             *
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2010 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2015 Houssam Salem <houssam.salem.au@gmail.com>                        *
 *  Copyright (c) 2018 Chris Williams <chris@chrispwill.com>                             *
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
import android.text.TextUtils;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.anki.exception.DeckRenameException;
import com.ichi2.libanki.exception.NoSuchDeckException;
import com.ichi2.utils.DeckComparator;
import com.ichi2.utils.DeckNameComparator;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONObject;
import com.ichi2.utils.SyncStatus;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import static com.ichi2.libanki.Consts.DECK_DYN;
import static com.ichi2.libanki.Consts.DECK_STD;
import static com.ichi2.utils.CollectionUtils.addAll;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.MethodNamingConventions", "PMD.AvoidReassigningParameters", "PMD.SimplifyBooleanReturns" })
public class Decks {

    // Invalid id, represents an id on an unfound deck
    public static final long NOT_FOUND_DECK_ID = -1L;

    // not in libAnki
    @SuppressWarnings("WeakerAccess")
    public static final String DECK_SEPARATOR = "::";

    public static final String defaultDeck = "" + "{" + // currentDay, count
    "'newToday': [0, 0]," + "'revToday': [0, 0]," + "'lrnToday': [0, 0]," + // time in ms
    "'timeToday': [0, 0]," + "'conf': 1," + "'usn': 0," + "'desc': \"\"," + // anki uses int/bool interchangably here
    "'dyn': 0," + "'collapsed': False," + // added in beta11
    "'extendNew': 10," + "'extendRev': 50" + "}";

    private static final String defaultDynamicDeck = "" + "{" + "'newToday': [0, 0]," + "'revToday': [0, 0]," + "'lrnToday': [0, 0]," + "'timeToday': [0, 0]," + "'collapsed': False," + "'dyn': 1," + "'desc': \"\"," + "'usn': 0," + "'delays': null," + "'separate': True," + // list of (search, limit, order); we only use first element for now
    "'terms': [[\"\", 100, 0]]," + "'resched': True," + // currently unused
    "'return': True" + "}";

    public static final String defaultConf = "" + "{" + "'name': \"Default\"," + "'new': {" + "'delays': [1, 10]," + // 7 is not currently used
    "'ints': [1, 4, 7]," + "'initialFactor': " + Consts.STARTING_FACTOR + "," + "'separate': True," + "'order': " + Consts.NEW_CARDS_DUE + "," + "'perDay': 20," + // may not be set on old decks
    "'bury': False" + "}," + "'lapse': {" + "'delays': [10]," + "'mult': 0," + "'minInt': 1," + "'leechFails': 8," + // type 0=suspend, 1=tagonly
    "'leechAction': " + Consts.LEECH_SUSPEND + "}," + "'rev': {" + "'perDay': 100," + "'ease4': 1.3," + "'fuzz': 0.05," + // not currently used
    "'minSpace': 1," + "'ivlFct': 1," + "'maxIvl': 36500," + // may not be set on old decks
    "'bury': False" + "}," + "'maxTaken': 60," + "'timer': 0," + "'autoplay': True," + "'replayq': True," + "'mod': 0," + "'usn': 0" + "}";

    private final Collection mCol;

    private HashMap<Long, Deck> mDecks;

    private HashMap<Long, DeckConfig> mDconf;

    // Never access mNameMap directly. Uses byName
    private NameMap mNameMap;

    private boolean mChanged;

    private static class NameMap {

        private final HashMap<String, Deck> mNameMap;

        private NameMap(int size) {
            mNameMap = new HashMap<>(size);
        }

        public static NameMap constructor(java.util.Collection<Deck> decks) {
            NameMap map = new NameMap((ListenerUtil.mutListener.listen(22122) ? (2 % decks.size()) : (ListenerUtil.mutListener.listen(22121) ? (2 / decks.size()) : (ListenerUtil.mutListener.listen(22120) ? (2 - decks.size()) : (ListenerUtil.mutListener.listen(22119) ? (2 + decks.size()) : (2 * decks.size()))))));
            if (!ListenerUtil.mutListener.listen(22124)) {
                {
                    long _loopCounter493 = 0;
                    for (Deck deck : decks) {
                        ListenerUtil.loopListener.listen("_loopCounter493", ++_loopCounter493);
                        if (!ListenerUtil.mutListener.listen(22123)) {
                            map.add(deck);
                        }
                    }
                }
            }
            return map;
        }

        public synchronized Deck get(String name) {
            String normalized = normalizeName(name);
            Deck deck = mNameMap.get(normalized);
            if (!ListenerUtil.mutListener.listen(22125)) {
                if (deck == null) {
                    return null;
                }
            }
            String foundName = deck.getString("name");
            if (!ListenerUtil.mutListener.listen(22127)) {
                if (!equalName(name, foundName)) {
                    if (!ListenerUtil.mutListener.listen(22126)) {
                        AnkiDroidApp.sendExceptionReport("We looked for deck \"" + name + "\" and instead got deck \"" + foundName + "\".", "Decks - byName");
                    }
                }
            }
            return deck;
        }

        public synchronized void add(Deck g) {
            String name = g.getString("name");
            if (!ListenerUtil.mutListener.listen(22128)) {
                mNameMap.put(name, g);
            }
            if (!ListenerUtil.mutListener.listen(22129)) {
                // Non normalized is kept for Parent
                mNameMap.put(normalizeName(name), g);
            }
        }

        /**
         *           Remove name from nameMap if it is equal to expectedDeck.
         *
         *           It is possible that another deck has been given name `name`,
         *           in which case we don't want to remove it from nameMap.
         *
         *           E.g. if A is renamed to A::B and A::B already existed and get
         *           renamed to A::B::B, then we don't want to remove A::B from
         *           nameMap when A::B is renamed to A::B::B, since A::B is
         *           potentially the correct value.
         */
        public synchronized void remove(String name, JSONObject expectedDeck) {
            String[] names = new String[] { name, normalizeName(name) };
            if (!ListenerUtil.mutListener.listen(22138)) {
                {
                    long _loopCounter494 = 0;
                    for (String name_ : names) {
                        ListenerUtil.loopListener.listen("_loopCounter494", ++_loopCounter494);
                        JSONObject currentDeck = mNameMap.get(name_);
                        if (!ListenerUtil.mutListener.listen(22137)) {
                            if ((ListenerUtil.mutListener.listen(22135) ? (currentDeck != null || (ListenerUtil.mutListener.listen(22134) ? (currentDeck.getLong("id") >= expectedDeck.getLong("id")) : (ListenerUtil.mutListener.listen(22133) ? (currentDeck.getLong("id") <= expectedDeck.getLong("id")) : (ListenerUtil.mutListener.listen(22132) ? (currentDeck.getLong("id") > expectedDeck.getLong("id")) : (ListenerUtil.mutListener.listen(22131) ? (currentDeck.getLong("id") < expectedDeck.getLong("id")) : (ListenerUtil.mutListener.listen(22130) ? (currentDeck.getLong("id") != expectedDeck.getLong("id")) : (currentDeck.getLong("id") == expectedDeck.getLong("id")))))))) : (currentDeck != null && (ListenerUtil.mutListener.listen(22134) ? (currentDeck.getLong("id") >= expectedDeck.getLong("id")) : (ListenerUtil.mutListener.listen(22133) ? (currentDeck.getLong("id") <= expectedDeck.getLong("id")) : (ListenerUtil.mutListener.listen(22132) ? (currentDeck.getLong("id") > expectedDeck.getLong("id")) : (ListenerUtil.mutListener.listen(22131) ? (currentDeck.getLong("id") < expectedDeck.getLong("id")) : (ListenerUtil.mutListener.listen(22130) ? (currentDeck.getLong("id") != expectedDeck.getLong("id")) : (currentDeck.getLong("id") == expectedDeck.getLong("id")))))))))) {
                                if (!ListenerUtil.mutListener.listen(22136)) {
                                    /* Remove name from mapping only if it still maps to
                     * expectedDeck. I.e. no other deck had been given this
                     * name yet. */
                                    mNameMap.remove(name_);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Decks(Collection col) {
        mCol = col;
    }

    public void load(String decks, String dconf) {
        JSONObject decksarray = new JSONObject(decks);
        JSONArray ids = decksarray.names();
        if (!ListenerUtil.mutListener.listen(22139)) {
            mDecks = new HashMap<>(decksarray.length());
        }
        if (!ListenerUtil.mutListener.listen(22141)) {
            {
                long _loopCounter495 = 0;
                for (String id : ids.stringIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter495", ++_loopCounter495);
                    Deck o = new Deck(decksarray.getJSONObject(id));
                    long longId = Long.parseLong(id);
                    if (!ListenerUtil.mutListener.listen(22140)) {
                        mDecks.put(longId, o);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22142)) {
            mNameMap = NameMap.constructor(mDecks.values());
        }
        JSONObject confarray = new JSONObject(dconf);
        if (!ListenerUtil.mutListener.listen(22143)) {
            ids = confarray.names();
        }
        if (!ListenerUtil.mutListener.listen(22144)) {
            mDconf = new HashMap<>(confarray.length());
        }
        if (!ListenerUtil.mutListener.listen(22147)) {
            if (ids != null) {
                if (!ListenerUtil.mutListener.listen(22146)) {
                    {
                        long _loopCounter496 = 0;
                        for (String id : ids.stringIterable()) {
                            ListenerUtil.loopListener.listen("_loopCounter496", ++_loopCounter496);
                            if (!ListenerUtil.mutListener.listen(22145)) {
                                mDconf.put(Long.parseLong(id), new DeckConfig(confarray.getJSONObject(id)));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22148)) {
            mChanged = false;
        }
    }

    public void save() {
        if (!ListenerUtil.mutListener.listen(22149)) {
            save(null);
        }
    }

    /**
     * Can be called with either a deck or a deck configuration.
     */
    public void save(JSONObject g) {
        if (!ListenerUtil.mutListener.listen(22152)) {
            if (g != null) {
                if (!ListenerUtil.mutListener.listen(22150)) {
                    g.put("mod", mCol.getTime().intTime());
                }
                if (!ListenerUtil.mutListener.listen(22151)) {
                    g.put("usn", mCol.usn());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22153)) {
            mChanged = true;
        }
    }

    public void flush() {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(22162)) {
            if (mChanged) {
                JSONObject decksarray = new JSONObject();
                if (!ListenerUtil.mutListener.listen(22155)) {
                    {
                        long _loopCounter497 = 0;
                        for (Map.Entry<Long, Deck> d : mDecks.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter497", ++_loopCounter497);
                            if (!ListenerUtil.mutListener.listen(22154)) {
                                decksarray.put(Long.toString(d.getKey()), d.getValue());
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22156)) {
                    values.put("decks", Utils.jsonToString(decksarray));
                }
                JSONObject confarray = new JSONObject();
                if (!ListenerUtil.mutListener.listen(22158)) {
                    {
                        long _loopCounter498 = 0;
                        for (Map.Entry<Long, DeckConfig> d : mDconf.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter498", ++_loopCounter498);
                            if (!ListenerUtil.mutListener.listen(22157)) {
                                confarray.put(Long.toString(d.getKey()), d.getValue());
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22159)) {
                    values.put("dconf", Utils.jsonToString(confarray));
                }
                if (!ListenerUtil.mutListener.listen(22160)) {
                    mCol.getDb().update("col", values);
                }
                if (!ListenerUtil.mutListener.listen(22161)) {
                    mChanged = false;
                }
            }
        }
    }

    public Long id(String name) {
        return id(name, true);
    }

    public Long id(String name, boolean create) {
        return id(name, create, defaultDeck);
    }

    public Long id(String name, String type) {
        return id(name, true, type);
    }

    /**
     * Add a deck with NAME. Reuse deck if already exists. Return id as int.
     */
    public Long id(String name, boolean create, String type) {
        if (!ListenerUtil.mutListener.listen(22163)) {
            name = strip(name);
        }
        if (!ListenerUtil.mutListener.listen(22164)) {
            name = name.replace("\"", "");
        }
        if (!ListenerUtil.mutListener.listen(22165)) {
            name = Normalizer.normalize(name, Normalizer.Form.NFC);
        }
        Deck deck = byName(name);
        if (!ListenerUtil.mutListener.listen(22166)) {
            if (deck != null) {
                return deck.getLong("id");
            }
        }
        if (!ListenerUtil.mutListener.listen(22167)) {
            if (!create) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(22169)) {
            if (name.contains("::")) {
                if (!ListenerUtil.mutListener.listen(22168)) {
                    // not top level; ensure all parents exist
                    name = _ensureParents(name);
                }
            }
        }
        long id;
        Deck g = new Deck(type);
        if (!ListenerUtil.mutListener.listen(22170)) {
            g.put("name", name);
        }
        {
            long _loopCounter499 = 0;
            do {
                ListenerUtil.loopListener.listen("_loopCounter499", ++_loopCounter499);
                id = mCol.getTime().intTimeMS();
            } while (mDecks.containsKey(id));
        }
        if (!ListenerUtil.mutListener.listen(22171)) {
            g.put("id", id);
        }
        if (!ListenerUtil.mutListener.listen(22172)) {
            mDecks.put(id, g);
        }
        if (!ListenerUtil.mutListener.listen(22173)) {
            save(g);
        }
        if (!ListenerUtil.mutListener.listen(22174)) {
            maybeAddToActive();
        }
        if (!ListenerUtil.mutListener.listen(22175)) {
            mNameMap.add(g);
        }
        // runHook("newDeck"); // TODO
        return id;
    }

    public void rem(long did) {
        if (!ListenerUtil.mutListener.listen(22176)) {
            rem(did, true);
        }
    }

    public void rem(long did, boolean cardsToo) {
        if (!ListenerUtil.mutListener.listen(22177)) {
            rem(did, cardsToo, true);
        }
    }

    /**
     * Remove the deck. If cardsToo, delete any cards inside.
     */
    public void rem(long did, boolean cardsToo, boolean childrenToo) {
        JSONObject deck = get(did, false);
        if (!ListenerUtil.mutListener.listen(22187)) {
            if ((ListenerUtil.mutListener.listen(22182) ? (did >= 1) : (ListenerUtil.mutListener.listen(22181) ? (did <= 1) : (ListenerUtil.mutListener.listen(22180) ? (did > 1) : (ListenerUtil.mutListener.listen(22179) ? (did < 1) : (ListenerUtil.mutListener.listen(22178) ? (did != 1) : (did == 1))))))) {
                if (!ListenerUtil.mutListener.listen(22186)) {
                    // child of an existing deck then it needs to be renamed
                    if ((ListenerUtil.mutListener.listen(22183) ? (deck != null || deck.getString("name").contains("::")) : (deck != null && deck.getString("name").contains("::")))) {
                        if (!ListenerUtil.mutListener.listen(22184)) {
                            deck.put("name", "Default");
                        }
                        if (!ListenerUtil.mutListener.listen(22185)) {
                            save(deck);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22188)) {
            // log the removal regardless of whether we have the deck or not
            mCol._logRem(new long[] { did }, Consts.REM_DECK);
        }
        if (!ListenerUtil.mutListener.listen(22189)) {
            // do nothing else if doesn't exist
            if (deck == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22204)) {
            if ((ListenerUtil.mutListener.listen(22194) ? (deck.getInt("dyn") >= DECK_DYN) : (ListenerUtil.mutListener.listen(22193) ? (deck.getInt("dyn") <= DECK_DYN) : (ListenerUtil.mutListener.listen(22192) ? (deck.getInt("dyn") > DECK_DYN) : (ListenerUtil.mutListener.listen(22191) ? (deck.getInt("dyn") < DECK_DYN) : (ListenerUtil.mutListener.listen(22190) ? (deck.getInt("dyn") != DECK_DYN) : (deck.getInt("dyn") == DECK_DYN))))))) {
                if (!ListenerUtil.mutListener.listen(22200)) {
                    // rather than deleting the cards
                    mCol.getSched().emptyDyn(did);
                }
                if (!ListenerUtil.mutListener.listen(22203)) {
                    if (childrenToo) {
                        if (!ListenerUtil.mutListener.listen(22202)) {
                            {
                                long _loopCounter501 = 0;
                                for (long id : children(did).values()) {
                                    ListenerUtil.loopListener.listen("_loopCounter501", ++_loopCounter501);
                                    if (!ListenerUtil.mutListener.listen(22201)) {
                                        rem(id, cardsToo, false);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22197)) {
                    // delete children first
                    if (childrenToo) {
                        if (!ListenerUtil.mutListener.listen(22196)) {
                            {
                                long _loopCounter500 = 0;
                                // we don't want to delete children when syncing
                                for (long id : children(did).values()) {
                                    ListenerUtil.loopListener.listen("_loopCounter500", ++_loopCounter500);
                                    if (!ListenerUtil.mutListener.listen(22195)) {
                                        rem(id, cardsToo, false);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22199)) {
                    // delete cards too?
                    if (cardsToo) {
                        // don't use cids(), as we want cards in cram decks too
                        ArrayList<Long> cids = mCol.getDb().queryLongList("SELECT id FROM cards WHERE did = ? OR odid = ?", did, did);
                        if (!ListenerUtil.mutListener.listen(22198)) {
                            mCol.remCards(cids);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22205)) {
            // delete the deck and add a grave
            mDecks.remove(did);
        }
        if (!ListenerUtil.mutListener.listen(22206)) {
            mNameMap.remove(deck.getString("name"), deck);
        }
        if (!ListenerUtil.mutListener.listen(22208)) {
            // ensure we have an active deck
            if (active().contains(did)) {
                if (!ListenerUtil.mutListener.listen(22207)) {
                    select(mDecks.keySet().iterator().next());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22209)) {
            save();
        }
    }

    public ArrayList<String> allNames() {
        return allNames(true);
    }

    /**
     * An unsorted list of all deck names.
     */
    public ArrayList<String> allNames(boolean dyn) {
        ArrayList<String> list = new ArrayList<>(mDecks.size());
        if (!ListenerUtil.mutListener.listen(22215)) {
            if (dyn) {
                if (!ListenerUtil.mutListener.listen(22214)) {
                    {
                        long _loopCounter503 = 0;
                        for (Deck x : mDecks.values()) {
                            ListenerUtil.loopListener.listen("_loopCounter503", ++_loopCounter503);
                            if (!ListenerUtil.mutListener.listen(22213)) {
                                list.add(x.getString("name"));
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22212)) {
                    {
                        long _loopCounter502 = 0;
                        for (Deck x : mDecks.values()) {
                            ListenerUtil.loopListener.listen("_loopCounter502", ++_loopCounter502);
                            if (!ListenerUtil.mutListener.listen(22211)) {
                                if (x.getInt("dyn") == DECK_STD) {
                                    if (!ListenerUtil.mutListener.listen(22210)) {
                                        list.add(x.getString("name"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * A list of all decks.
     */
    public ArrayList<Deck> all() {
        return new ArrayList<>(mDecks.values());
    }

    /**
     * Return the same deck list from all() but sorted using a comparator that ensures the same
     * sorting order for decks as the desktop client.
     *
     * This method does not exist in the original python module but *must* be used for any user
     * interface components that display a deck list to ensure the ordering is consistent.
     */
    public ArrayList<Deck> allSorted() {
        ArrayList<Deck> decks = all();
        if (!ListenerUtil.mutListener.listen(22216)) {
            Collections.sort(decks, DeckComparator.instance);
        }
        return decks;
    }

    @VisibleForTesting
    public List<String> allSortedNames() {
        List<String> names = allNames();
        if (!ListenerUtil.mutListener.listen(22217)) {
            Collections.sort(names, DeckNameComparator.instance);
        }
        return names;
    }

    public Set<Long> allIds() {
        return mDecks.keySet();
    }

    public void collapse(long did) {
        Deck deck = get(did);
        if (!ListenerUtil.mutListener.listen(22218)) {
            deck.put("collapsed", !deck.getBoolean("collapsed"));
        }
        if (!ListenerUtil.mutListener.listen(22219)) {
            save(deck);
        }
    }

    public void collapseBrowser(long did) {
        Deck deck = get(did);
        boolean collapsed = deck.optBoolean("browserCollapsed", false);
        if (!ListenerUtil.mutListener.listen(22220)) {
            deck.put("browserCollapsed", !collapsed);
        }
        if (!ListenerUtil.mutListener.listen(22221)) {
            save(deck);
        }
    }

    /**
     * Return the number of decks.
     */
    public int count() {
        return mDecks.size();
    }

    /**
     * Obtains the deck from the DeckID, or default if the deck was not found
     */
    @CheckResult
    @NonNull
    public Deck get(long did) {
        return get(did, true);
    }

    @CheckResult
    public Deck get(long did, boolean _default) {
        if (mDecks.containsKey(did)) {
            return mDecks.get(did);
        } else if (_default) {
            return mDecks.get(1L);
        } else {
            return null;
        }
    }

    /**
     * Get deck with NAME, ignoring case.
     */
    @CheckResult
    @Nullable
    public Deck byName(String name) {
        return mNameMap.get(name);
    }

    /**
     * Add or update an existing deck. Used for syncing and merging.
     */
    public void update(Deck g) {
        long id = g.getLong("id");
        JSONObject oldDeck = get(id, false);
        if (!ListenerUtil.mutListener.listen(22223)) {
            if (oldDeck != null) {
                if (!ListenerUtil.mutListener.listen(22222)) {
                    // `oldName`, it would be a mistake to remove it from nameMap
                    mNameMap.remove(oldDeck.getString("name"), oldDeck);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22224)) {
            mNameMap.add(g);
        }
        if (!ListenerUtil.mutListener.listen(22225)) {
            mDecks.put(g.getLong("id"), g);
        }
        if (!ListenerUtil.mutListener.listen(22226)) {
            maybeAddToActive();
        }
        if (!ListenerUtil.mutListener.listen(22227)) {
            // mark registry changed, but don't bump mod time
            save();
        }
    }

    /**
     * Rename deck prefix to NAME if not exists. Updates children.
     */
    public void rename(Deck g, String newName) throws DeckRenameException {
        if (!ListenerUtil.mutListener.listen(22228)) {
            newName = strip(newName);
        }
        // make sure target node doesn't already exist
        Deck deckWithThisName = byName(newName);
        if (!ListenerUtil.mutListener.listen(22230)) {
            if (deckWithThisName != null) {
                if (!ListenerUtil.mutListener.listen(22229)) {
                    if (deckWithThisName.getLong("id") != g.getLong("id")) {
                        throw new DeckRenameException(DeckRenameException.ALREADY_EXISTS);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22231)) {
            // ensure we have parents
            newName = _ensureParents(newName);
        }
        if (!ListenerUtil.mutListener.listen(22237)) {
            // make sure we're not nesting under a filtered deck
            if (newName.contains("::")) {
                List<String> parts = Arrays.asList(path(newName));
                String newParent = TextUtils.join("::", parts.subList(0, (ListenerUtil.mutListener.listen(22235) ? (parts.size() % 1) : (ListenerUtil.mutListener.listen(22234) ? (parts.size() / 1) : (ListenerUtil.mutListener.listen(22233) ? (parts.size() * 1) : (ListenerUtil.mutListener.listen(22232) ? (parts.size() + 1) : (parts.size() - 1)))))));
                if (!ListenerUtil.mutListener.listen(22236)) {
                    if (byName(newParent).getInt("dyn") == DECK_DYN) {
                        throw new DeckRenameException(DeckRenameException.FILTERED_NOSUBDEKCS);
                    }
                }
            }
        }
        // rename children
        String oldName = g.getString("name");
        if (!ListenerUtil.mutListener.listen(22243)) {
            {
                long _loopCounter504 = 0;
                for (Deck grp : all()) {
                    ListenerUtil.loopListener.listen("_loopCounter504", ++_loopCounter504);
                    String grpOldName = grp.getString("name");
                    if (!ListenerUtil.mutListener.listen(22242)) {
                        if (grpOldName.startsWith(oldName + "::")) {
                            String grpNewName = grpOldName.replaceFirst(Pattern.quote(oldName + "::"), newName + "::");
                            if (!ListenerUtil.mutListener.listen(22238)) {
                                // In Java, String.replaceFirst consumes a regex so we need to quote the pattern to be safe
                                mNameMap.remove(grpOldName, grp);
                            }
                            if (!ListenerUtil.mutListener.listen(22239)) {
                                grp.put("name", grpNewName);
                            }
                            if (!ListenerUtil.mutListener.listen(22240)) {
                                mNameMap.add(grp);
                            }
                            if (!ListenerUtil.mutListener.listen(22241)) {
                                save(grp);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22244)) {
            mNameMap.remove(oldName, g);
        }
        if (!ListenerUtil.mutListener.listen(22245)) {
            // adjust name
            g.put("name", newName);
        }
        if (!ListenerUtil.mutListener.listen(22246)) {
            // ensure we have parents again, as we may have renamed parent->child
            newName = _ensureParents(newName);
        }
        if (!ListenerUtil.mutListener.listen(22247)) {
            mNameMap.add(g);
        }
        if (!ListenerUtil.mutListener.listen(22248)) {
            save(g);
        }
        if (!ListenerUtil.mutListener.listen(22249)) {
            // renaming may have altered active did order
            maybeAddToActive();
        }
    }

    private boolean _isParent(String parentDeckName, String childDeckName) {
        String[] parentDeckPath = path(parentDeckName);
        String[] childDeckPath = path(childDeckName);
        if (!ListenerUtil.mutListener.listen(22259)) {
            if ((ListenerUtil.mutListener.listen(22258) ? ((ListenerUtil.mutListener.listen(22253) ? (parentDeckPath.length % 1) : (ListenerUtil.mutListener.listen(22252) ? (parentDeckPath.length / 1) : (ListenerUtil.mutListener.listen(22251) ? (parentDeckPath.length * 1) : (ListenerUtil.mutListener.listen(22250) ? (parentDeckPath.length - 1) : (parentDeckPath.length + 1))))) >= childDeckPath.length) : (ListenerUtil.mutListener.listen(22257) ? ((ListenerUtil.mutListener.listen(22253) ? (parentDeckPath.length % 1) : (ListenerUtil.mutListener.listen(22252) ? (parentDeckPath.length / 1) : (ListenerUtil.mutListener.listen(22251) ? (parentDeckPath.length * 1) : (ListenerUtil.mutListener.listen(22250) ? (parentDeckPath.length - 1) : (parentDeckPath.length + 1))))) <= childDeckPath.length) : (ListenerUtil.mutListener.listen(22256) ? ((ListenerUtil.mutListener.listen(22253) ? (parentDeckPath.length % 1) : (ListenerUtil.mutListener.listen(22252) ? (parentDeckPath.length / 1) : (ListenerUtil.mutListener.listen(22251) ? (parentDeckPath.length * 1) : (ListenerUtil.mutListener.listen(22250) ? (parentDeckPath.length - 1) : (parentDeckPath.length + 1))))) > childDeckPath.length) : (ListenerUtil.mutListener.listen(22255) ? ((ListenerUtil.mutListener.listen(22253) ? (parentDeckPath.length % 1) : (ListenerUtil.mutListener.listen(22252) ? (parentDeckPath.length / 1) : (ListenerUtil.mutListener.listen(22251) ? (parentDeckPath.length * 1) : (ListenerUtil.mutListener.listen(22250) ? (parentDeckPath.length - 1) : (parentDeckPath.length + 1))))) < childDeckPath.length) : (ListenerUtil.mutListener.listen(22254) ? ((ListenerUtil.mutListener.listen(22253) ? (parentDeckPath.length % 1) : (ListenerUtil.mutListener.listen(22252) ? (parentDeckPath.length / 1) : (ListenerUtil.mutListener.listen(22251) ? (parentDeckPath.length * 1) : (ListenerUtil.mutListener.listen(22250) ? (parentDeckPath.length - 1) : (parentDeckPath.length + 1))))) == childDeckPath.length) : ((ListenerUtil.mutListener.listen(22253) ? (parentDeckPath.length % 1) : (ListenerUtil.mutListener.listen(22252) ? (parentDeckPath.length / 1) : (ListenerUtil.mutListener.listen(22251) ? (parentDeckPath.length * 1) : (ListenerUtil.mutListener.listen(22250) ? (parentDeckPath.length - 1) : (parentDeckPath.length + 1))))) != childDeckPath.length))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(22266)) {
            {
                long _loopCounter505 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(22265) ? (i >= parentDeckPath.length) : (ListenerUtil.mutListener.listen(22264) ? (i <= parentDeckPath.length) : (ListenerUtil.mutListener.listen(22263) ? (i > parentDeckPath.length) : (ListenerUtil.mutListener.listen(22262) ? (i != parentDeckPath.length) : (ListenerUtil.mutListener.listen(22261) ? (i == parentDeckPath.length) : (i < parentDeckPath.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter505", ++_loopCounter505);
                    if (!ListenerUtil.mutListener.listen(22260)) {
                        if (!parentDeckPath[i].equals(childDeckPath[i])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean _isAncestor(String ancestorDeckName, String descendantDeckName) {
        String[] ancestorDeckPath = path(ancestorDeckName);
        String[] descendantDeckPath = path(descendantDeckName);
        if (!ListenerUtil.mutListener.listen(22272)) {
            if ((ListenerUtil.mutListener.listen(22271) ? (ancestorDeckPath.length >= descendantDeckPath.length) : (ListenerUtil.mutListener.listen(22270) ? (ancestorDeckPath.length <= descendantDeckPath.length) : (ListenerUtil.mutListener.listen(22269) ? (ancestorDeckPath.length < descendantDeckPath.length) : (ListenerUtil.mutListener.listen(22268) ? (ancestorDeckPath.length != descendantDeckPath.length) : (ListenerUtil.mutListener.listen(22267) ? (ancestorDeckPath.length == descendantDeckPath.length) : (ancestorDeckPath.length > descendantDeckPath.length))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(22279)) {
            {
                long _loopCounter506 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(22278) ? (i >= ancestorDeckPath.length) : (ListenerUtil.mutListener.listen(22277) ? (i <= ancestorDeckPath.length) : (ListenerUtil.mutListener.listen(22276) ? (i > ancestorDeckPath.length) : (ListenerUtil.mutListener.listen(22275) ? (i != ancestorDeckPath.length) : (ListenerUtil.mutListener.listen(22274) ? (i == ancestorDeckPath.length) : (i < ancestorDeckPath.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter506", ++_loopCounter506);
                    if (!ListenerUtil.mutListener.listen(22273)) {
                        if (!Utils.equals(ancestorDeckPath[i], descendantDeckPath[i])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static final HashMap<String, String[]> pathCache = new HashMap<>();

    public static String[] path(String name) {
        if (!ListenerUtil.mutListener.listen(22281)) {
            if (!pathCache.containsKey(name)) {
                if (!ListenerUtil.mutListener.listen(22280)) {
                    pathCache.put(name, name.split("::", -1));
                }
            }
        }
        return pathCache.get(name);
    }

    public static String basename(String name) {
        String[] path = path(name);
        return path[(ListenerUtil.mutListener.listen(22285) ? (path.length % 1) : (ListenerUtil.mutListener.listen(22284) ? (path.length / 1) : (ListenerUtil.mutListener.listen(22283) ? (path.length * 1) : (ListenerUtil.mutListener.listen(22282) ? (path.length + 1) : (path.length - 1)))))];
    }

    /**
     * Ensure parents exist, and return name with case matching parents.
     */
    public String _ensureParents(String name) {
        String s = "";
        String[] path = path(name);
        if (!ListenerUtil.mutListener.listen(22291)) {
            if ((ListenerUtil.mutListener.listen(22290) ? (path.length >= 2) : (ListenerUtil.mutListener.listen(22289) ? (path.length <= 2) : (ListenerUtil.mutListener.listen(22288) ? (path.length > 2) : (ListenerUtil.mutListener.listen(22287) ? (path.length != 2) : (ListenerUtil.mutListener.listen(22286) ? (path.length == 2) : (path.length < 2))))))) {
                return name;
            }
        }
        if (!ListenerUtil.mutListener.listen(22305)) {
            {
                long _loopCounter507 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(22304) ? (i >= (ListenerUtil.mutListener.listen(22299) ? (path.length % 1) : (ListenerUtil.mutListener.listen(22298) ? (path.length / 1) : (ListenerUtil.mutListener.listen(22297) ? (path.length * 1) : (ListenerUtil.mutListener.listen(22296) ? (path.length + 1) : (path.length - 1)))))) : (ListenerUtil.mutListener.listen(22303) ? (i <= (ListenerUtil.mutListener.listen(22299) ? (path.length % 1) : (ListenerUtil.mutListener.listen(22298) ? (path.length / 1) : (ListenerUtil.mutListener.listen(22297) ? (path.length * 1) : (ListenerUtil.mutListener.listen(22296) ? (path.length + 1) : (path.length - 1)))))) : (ListenerUtil.mutListener.listen(22302) ? (i > (ListenerUtil.mutListener.listen(22299) ? (path.length % 1) : (ListenerUtil.mutListener.listen(22298) ? (path.length / 1) : (ListenerUtil.mutListener.listen(22297) ? (path.length * 1) : (ListenerUtil.mutListener.listen(22296) ? (path.length + 1) : (path.length - 1)))))) : (ListenerUtil.mutListener.listen(22301) ? (i != (ListenerUtil.mutListener.listen(22299) ? (path.length % 1) : (ListenerUtil.mutListener.listen(22298) ? (path.length / 1) : (ListenerUtil.mutListener.listen(22297) ? (path.length * 1) : (ListenerUtil.mutListener.listen(22296) ? (path.length + 1) : (path.length - 1)))))) : (ListenerUtil.mutListener.listen(22300) ? (i == (ListenerUtil.mutListener.listen(22299) ? (path.length % 1) : (ListenerUtil.mutListener.listen(22298) ? (path.length / 1) : (ListenerUtil.mutListener.listen(22297) ? (path.length * 1) : (ListenerUtil.mutListener.listen(22296) ? (path.length + 1) : (path.length - 1)))))) : (i < (ListenerUtil.mutListener.listen(22299) ? (path.length % 1) : (ListenerUtil.mutListener.listen(22298) ? (path.length / 1) : (ListenerUtil.mutListener.listen(22297) ? (path.length * 1) : (ListenerUtil.mutListener.listen(22296) ? (path.length + 1) : (path.length - 1))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter507", ++_loopCounter507);
                    String p = path[i];
                    if (!ListenerUtil.mutListener.listen(22294)) {
                        if (TextUtils.isEmpty(s)) {
                            if (!ListenerUtil.mutListener.listen(22293)) {
                                s += p;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(22292)) {
                                s += "::" + p;
                            }
                        }
                    }
                    // fetch or create
                    long did = id(s);
                    if (!ListenerUtil.mutListener.listen(22295)) {
                        // get original case
                        s = name(did);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22310)) {
            name = s + "::" + path[(ListenerUtil.mutListener.listen(22309) ? (path.length % 1) : (ListenerUtil.mutListener.listen(22308) ? (path.length / 1) : (ListenerUtil.mutListener.listen(22307) ? (path.length * 1) : (ListenerUtil.mutListener.listen(22306) ? (path.length + 1) : (path.length - 1)))))];
        }
        return name;
    }

    /**
     * A list of all deck config.
     */
    public ArrayList<DeckConfig> allConf() {
        return new ArrayList<>(mDconf.values());
    }

    public DeckConfig confForDid(long did) {
        Deck deck = get(did, false);
        assert deck != null;
        if (!ListenerUtil.mutListener.listen(22312)) {
            if (deck.has("conf")) {
                DeckConfig conf = getConf(deck.getLong("conf"));
                if (!ListenerUtil.mutListener.listen(22311)) {
                    conf.put("dyn", DECK_STD);
                }
                return conf;
            }
        }
        // dynamic decks have embedded conf
        return new DeckConfig(deck);
    }

    public DeckConfig getConf(long confId) {
        return mDconf.get(confId);
    }

    public void updateConf(DeckConfig g) {
        if (!ListenerUtil.mutListener.listen(22313)) {
            mDconf.put(g.getLong("id"), g);
        }
        if (!ListenerUtil.mutListener.listen(22314)) {
            save();
        }
    }

    public long confId(String name) {
        return confId(name, defaultConf);
    }

    /**
     * Create a new configuration and return id.
     */
    public long confId(String name, String cloneFrom) {
        long id;
        DeckConfig c = new DeckConfig(cloneFrom);
        {
            long _loopCounter508 = 0;
            do {
                ListenerUtil.loopListener.listen("_loopCounter508", ++_loopCounter508);
                id = mCol.getTime().intTimeMS();
            } while (mDconf.containsKey(id));
        }
        if (!ListenerUtil.mutListener.listen(22315)) {
            c.put("id", id);
        }
        if (!ListenerUtil.mutListener.listen(22316)) {
            c.put("name", name);
        }
        if (!ListenerUtil.mutListener.listen(22317)) {
            mDconf.put(id, c);
        }
        if (!ListenerUtil.mutListener.listen(22318)) {
            save(c);
        }
        return id;
    }

    /**
     * Remove a configuration and update all decks using it.
     * @throws ConfirmModSchemaException
     */
    public void remConf(long id) throws ConfirmModSchemaException {
        assert (ListenerUtil.mutListener.listen(22323) ? (id >= 1) : (ListenerUtil.mutListener.listen(22322) ? (id <= 1) : (ListenerUtil.mutListener.listen(22321) ? (id > 1) : (ListenerUtil.mutListener.listen(22320) ? (id < 1) : (ListenerUtil.mutListener.listen(22319) ? (id == 1) : (id != 1))))));
        if (!ListenerUtil.mutListener.listen(22324)) {
            mCol.modSchema();
        }
        if (!ListenerUtil.mutListener.listen(22325)) {
            mDconf.remove(id);
        }
        if (!ListenerUtil.mutListener.listen(22330)) {
            {
                long _loopCounter509 = 0;
                for (Deck g : all()) {
                    ListenerUtil.loopListener.listen("_loopCounter509", ++_loopCounter509);
                    if (!ListenerUtil.mutListener.listen(22326)) {
                        // ignore cram decks
                        if (!g.has("conf")) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22329)) {
                        if (g.getString("conf").equals(Long.toString(id))) {
                            if (!ListenerUtil.mutListener.listen(22327)) {
                                g.put("conf", 1);
                            }
                            if (!ListenerUtil.mutListener.listen(22328)) {
                                save(g);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setConf(Deck grp, long id) {
        if (!ListenerUtil.mutListener.listen(22331)) {
            grp.put("conf", id);
        }
        if (!ListenerUtil.mutListener.listen(22332)) {
            save(grp);
        }
    }

    public List<Long> didsForConf(DeckConfig conf) {
        List<Long> dids = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22336)) {
            {
                long _loopCounter510 = 0;
                for (Deck deck : mDecks.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter510", ++_loopCounter510);
                    if (!ListenerUtil.mutListener.listen(22335)) {
                        if ((ListenerUtil.mutListener.listen(22333) ? (deck.has("conf") || deck.getLong("conf") == conf.getLong("id")) : (deck.has("conf") && deck.getLong("conf") == conf.getLong("id")))) {
                            if (!ListenerUtil.mutListener.listen(22334)) {
                                dids.add(deck.getLong("id"));
                            }
                        }
                    }
                }
            }
        }
        return dids;
    }

    public void restoreToDefault(DeckConfig conf) {
        int oldOrder = conf.getJSONObject("new").getInt("order");
        DeckConfig _new = new DeckConfig(defaultConf);
        if (!ListenerUtil.mutListener.listen(22337)) {
            _new.put("id", conf.getLong("id"));
        }
        if (!ListenerUtil.mutListener.listen(22338)) {
            _new.put("name", conf.getString("name"));
        }
        if (!ListenerUtil.mutListener.listen(22339)) {
            mDconf.put(conf.getLong("id"), _new);
        }
        if (!ListenerUtil.mutListener.listen(22340)) {
            save(_new);
        }
        if (!ListenerUtil.mutListener.listen(22347)) {
            // if it was previously randomized, resort
            if ((ListenerUtil.mutListener.listen(22345) ? (oldOrder >= 0) : (ListenerUtil.mutListener.listen(22344) ? (oldOrder <= 0) : (ListenerUtil.mutListener.listen(22343) ? (oldOrder > 0) : (ListenerUtil.mutListener.listen(22342) ? (oldOrder < 0) : (ListenerUtil.mutListener.listen(22341) ? (oldOrder != 0) : (oldOrder == 0))))))) {
                if (!ListenerUtil.mutListener.listen(22346)) {
                    mCol.getSched().resortConf(_new);
                }
            }
        }
    }

    public String name(long did) {
        return name(did, false);
    }

    public String name(long did, boolean _default) {
        Deck deck = get(did, _default);
        if (!ListenerUtil.mutListener.listen(22348)) {
            if (deck != null) {
                return deck.getString("name");
            }
        }
        return "[no deck]";
    }

    public String nameOrNone(long did) {
        Deck deck = get(did, false);
        if (!ListenerUtil.mutListener.listen(22349)) {
            if (deck != null) {
                return deck.getString("name");
            }
        }
        return null;
    }

    public void setDeck(long[] cids, long did) {
        if (!ListenerUtil.mutListener.listen(22350)) {
            mCol.getDb().execute("update cards set did=?,usn=?,mod=? where id in " + Utils.ids2str(cids), did, mCol.usn(), mCol.getTime().intTime());
        }
    }

    private void maybeAddToActive() {
        // reselect current deck, or default if current has disappeared
        Deck c = current();
        if (!ListenerUtil.mutListener.listen(22351)) {
            select(c.getLong("id"));
        }
    }

    public Long[] cids(long did) {
        return cids(did, false);
    }

    public Long[] cids(long did, boolean children) {
        if (!ListenerUtil.mutListener.listen(22352)) {
            if (!children) {
                return Utils.list2ObjectArray(mCol.getDb().queryLongList("select id from cards where did=?", did));
            }
        }
        java.util.Collection<Long> values = children(did).values();
        List<Long> dids = new ArrayList<>((ListenerUtil.mutListener.listen(22356) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(22355) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(22354) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(22353) ? (values.size() - 1) : (values.size() + 1))))));
        if (!ListenerUtil.mutListener.listen(22357)) {
            dids.add(did);
        }
        if (!ListenerUtil.mutListener.listen(22358)) {
            dids.addAll(values);
        }
        return Utils.list2ObjectArray(mCol.getDb().queryLongList("select id from cards where did in " + Utils.ids2str(dids)));
    }

    private static final Pattern spaceAroundSeparator = Pattern.compile("\\s*::\\s*");

    @VisibleForTesting
    static String strip(String deckName) {
        if (!ListenerUtil.mutListener.listen(22359)) {
            // Deal with all spaces around ::
            deckName = spaceAroundSeparator.matcher(deckName).replaceAll("::");
        }
        if (!ListenerUtil.mutListener.listen(22360)) {
            // Deal with spaces at start/end of the deck name.
            deckName = deckName.trim();
        }
        return deckName;
    }

    private void _recoverOrphans() {
        boolean mod = mCol.getDb().getMod();
        if (!ListenerUtil.mutListener.listen(22361)) {
            SyncStatus.ignoreDatabaseModification(() -> mCol.getDb().execute("update cards set did = 1 where did not in " + Utils.ids2str(allIds())));
        }
        if (!ListenerUtil.mutListener.listen(22362)) {
            mCol.getDb().setMod(mod);
        }
    }

    private void _checkDeckTree() {
        ArrayList<Deck> decks = allSorted();
        Map<String, Deck> names = new HashMap<>(decks.size());
        if (!ListenerUtil.mutListener.listen(22398)) {
            {
                long _loopCounter513 = 0;
                for (Deck deck : decks) {
                    ListenerUtil.loopListener.listen("_loopCounter513", ++_loopCounter513);
                    String deckName = deck.getString("name");
                    /* With 2.1.28, anki started strips whitespace of deck name.  This method paragraph is here for
              compatibility while we wait for rust.  It should be executed before other changes, because both "FOO "
              and "FOO" will be renamed to the same name, and so this will need to be renamed again in case of
              duplicate.*/
                    String strippedName = strip(deckName);
                    if (!ListenerUtil.mutListener.listen(22368)) {
                        if (!deckName.equals(strippedName)) {
                            if (!ListenerUtil.mutListener.listen(22363)) {
                                mNameMap.remove(deckName, deck);
                            }
                            if (!ListenerUtil.mutListener.listen(22364)) {
                                deckName = strippedName;
                            }
                            if (!ListenerUtil.mutListener.listen(22365)) {
                                deck.put("name", deckName);
                            }
                            if (!ListenerUtil.mutListener.listen(22366)) {
                                mNameMap.add(deck);
                            }
                            if (!ListenerUtil.mutListener.listen(22367)) {
                                save(deck);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22375)) {
                        // ensure no sections are blank
                        if ("".equals(deckName)) {
                            if (!ListenerUtil.mutListener.listen(22369)) {
                                Timber.i("Fix deck with empty name");
                            }
                            if (!ListenerUtil.mutListener.listen(22370)) {
                                mNameMap.remove(deckName, deck);
                            }
                            if (!ListenerUtil.mutListener.listen(22371)) {
                                deckName = "blank";
                            }
                            if (!ListenerUtil.mutListener.listen(22372)) {
                                deck.put("name", "blank");
                            }
                            if (!ListenerUtil.mutListener.listen(22373)) {
                                mNameMap.add(deck);
                            }
                            if (!ListenerUtil.mutListener.listen(22374)) {
                                save(deck);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22383)) {
                        if (deckName.contains("::::")) {
                            if (!ListenerUtil.mutListener.listen(22376)) {
                                Timber.i("fix deck with missing sections %s", deck.getString("name"));
                            }
                            if (!ListenerUtil.mutListener.listen(22377)) {
                                mNameMap.remove(deckName, deck);
                            }
                            if (!ListenerUtil.mutListener.listen(22379)) {
                                {
                                    long _loopCounter511 = 0;
                                    do {
                                        ListenerUtil.loopListener.listen("_loopCounter511", ++_loopCounter511);
                                        if (!ListenerUtil.mutListener.listen(22378)) {
                                            deckName = deck.getString("name").replace("::::", "::blank::");
                                        }
                                    } while (deckName.contains("::::"));
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(22380)) {
                                deck.put("name", deckName);
                            }
                            if (!ListenerUtil.mutListener.listen(22381)) {
                                mNameMap.add(deck);
                            }
                            if (!ListenerUtil.mutListener.listen(22382)) {
                                save(deck);
                            }
                        }
                    }
                    // two decks with the same name?
                    Deck homonym = names.get(normalizeName(deckName));
                    if (!ListenerUtil.mutListener.listen(22391)) {
                        if (homonym != null) {
                            if (!ListenerUtil.mutListener.listen(22384)) {
                                Timber.i("fix duplicate deck name %s", deckName);
                            }
                            if (!ListenerUtil.mutListener.listen(22387)) {
                                {
                                    long _loopCounter512 = 0;
                                    do {
                                        ListenerUtil.loopListener.listen("_loopCounter512", ++_loopCounter512);
                                        if (!ListenerUtil.mutListener.listen(22385)) {
                                            deckName += "+";
                                        }
                                        if (!ListenerUtil.mutListener.listen(22386)) {
                                            deck.put("name", deckName);
                                        }
                                    } while (names.containsKey(normalizeName(deckName)));
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(22388)) {
                                mNameMap.add(deck);
                            }
                            if (!ListenerUtil.mutListener.listen(22389)) {
                                // Ensuring both names are correctly in mNameMap
                                mNameMap.add(homonym);
                            }
                            if (!ListenerUtil.mutListener.listen(22390)) {
                                save(deck);
                            }
                        }
                    }
                    // immediate parent must exist
                    String immediateParent = parent(deckName);
                    if (!ListenerUtil.mutListener.listen(22396)) {
                        if ((ListenerUtil.mutListener.listen(22392) ? (immediateParent != null || !names.containsKey(normalizeName(immediateParent))) : (immediateParent != null && !names.containsKey(normalizeName(immediateParent))))) {
                            if (!ListenerUtil.mutListener.listen(22393)) {
                                Timber.i("fix deck with missing parent %s", deckName);
                            }
                            Deck parent = byName(immediateParent);
                            if (!ListenerUtil.mutListener.listen(22394)) {
                                _ensureParents(deckName);
                            }
                            if (!ListenerUtil.mutListener.listen(22395)) {
                                names.put(normalizeName(immediateParent), parent);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22397)) {
                        names.put(normalizeName(deckName), deck);
                    }
                }
            }
        }
    }

    public void checkIntegrity() {
        if (!ListenerUtil.mutListener.listen(22399)) {
            _recoverOrphans();
        }
        if (!ListenerUtil.mutListener.listen(22400)) {
            _checkDeckTree();
        }
    }

    /**
     * The currently active dids. Make sure to copy before modifying.
     */
    public LinkedList<Long> active() {
        JSONArray activeDecks = mCol.getConf().getJSONArray("activeDecks");
        LinkedList<Long> result = new LinkedList<>();
        if (!ListenerUtil.mutListener.listen(22401)) {
            addAll(result, activeDecks.longIterable());
        }
        return result;
    }

    /**
     * The currently selected did.
     */
    public long selected() {
        return mCol.getConf().getLong("curDeck");
    }

    public Deck current() {
        return get(selected());
    }

    /**
     * Select a new branch.
     */
    public void select(long did) {
        String name = mDecks.get(did).getString("name");
        if (!ListenerUtil.mutListener.listen(22402)) {
            // current deck
            mCol.getConf().put("curDeck", Long.toString(did));
        }
        // Note: TreeMap is already sorted
        TreeMap<String, Long> actv = children(did);
        if (!ListenerUtil.mutListener.listen(22403)) {
            actv.put(name, did);
        }
        JSONArray activeDecks = new JSONArray();
        if (!ListenerUtil.mutListener.listen(22405)) {
            {
                long _loopCounter514 = 0;
                for (Long n : actv.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter514", ++_loopCounter514);
                    if (!ListenerUtil.mutListener.listen(22404)) {
                        activeDecks.put(n);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22406)) {
            mCol.getConf().put("activeDecks", activeDecks);
        }
        if (!ListenerUtil.mutListener.listen(22407)) {
            mCol.setMod();
        }
    }

    /**
     * All children of did as nodes of (key:name, value:id)
     *
     * TODO: There is likely no need for this collection to be a TreeMap. This method should not
     * need to sort on behalf of select().
     */
    public TreeMap<String, Long> children(long did) {
        String name = get(did).getString("name");
        TreeMap<String, Long> actv = new TreeMap<>();
        if (!ListenerUtil.mutListener.listen(22410)) {
            {
                long _loopCounter515 = 0;
                for (Deck g : all()) {
                    ListenerUtil.loopListener.listen("_loopCounter515", ++_loopCounter515);
                    if (!ListenerUtil.mutListener.listen(22409)) {
                        if (g.getString("name").startsWith(name + "::")) {
                            if (!ListenerUtil.mutListener.listen(22408)) {
                                actv.put(g.getString("name"), g.getLong("id"));
                            }
                        }
                    }
                }
            }
        }
        return actv;
    }

    public static class Node extends HashMap<Long, Node> {
    }

    private void gather(Node node, List<Long> arr) {
        if (!ListenerUtil.mutListener.listen(22413)) {
            {
                long _loopCounter516 = 0;
                for (Map.Entry<Long, Node> entry : node.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter516", ++_loopCounter516);
                    Node child = entry.getValue();
                    if (!ListenerUtil.mutListener.listen(22411)) {
                        arr.add(entry.getKey());
                    }
                    if (!ListenerUtil.mutListener.listen(22412)) {
                        gather(child, arr);
                    }
                }
            }
        }
    }

    public List<Long> childDids(long did, Node childMap) {
        List<Long> arr = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22414)) {
            gather(childMap.get(did), arr);
        }
        return arr;
    }

    public Node childMap() {
        Node childMap = new Node();
        // Go through all decks, sorted by name
        ArrayList<Deck> decks = all();
        if (!ListenerUtil.mutListener.listen(22415)) {
            Collections.sort(decks, DeckComparator.instance);
        }
        if (!ListenerUtil.mutListener.listen(22428)) {
            {
                long _loopCounter517 = 0;
                for (Deck deck : decks) {
                    ListenerUtil.loopListener.listen("_loopCounter517", ++_loopCounter517);
                    Node node = new Node();
                    if (!ListenerUtil.mutListener.listen(22416)) {
                        childMap.put(deck.getLong("id"), node);
                    }
                    List<String> parts = Arrays.asList(path(deck.getString("name")));
                    if (!ListenerUtil.mutListener.listen(22427)) {
                        if ((ListenerUtil.mutListener.listen(22421) ? (parts.size() >= 1) : (ListenerUtil.mutListener.listen(22420) ? (parts.size() <= 1) : (ListenerUtil.mutListener.listen(22419) ? (parts.size() < 1) : (ListenerUtil.mutListener.listen(22418) ? (parts.size() != 1) : (ListenerUtil.mutListener.listen(22417) ? (parts.size() == 1) : (parts.size() > 1))))))) {
                            String immediateParent = TextUtils.join("::", parts.subList(0, (ListenerUtil.mutListener.listen(22425) ? (parts.size() % 1) : (ListenerUtil.mutListener.listen(22424) ? (parts.size() / 1) : (ListenerUtil.mutListener.listen(22423) ? (parts.size() * 1) : (ListenerUtil.mutListener.listen(22422) ? (parts.size() + 1) : (parts.size() - 1)))))));
                            long pid = byName(immediateParent).getLong("id");
                            if (!ListenerUtil.mutListener.listen(22426)) {
                                childMap.get(pid).put(deck.getLong("id"), node);
                            }
                        }
                    }
                }
            }
        }
        return childMap;
    }

    /**
     * @return Names of ancestors of parents of name.
     */
    private String[] parentsNames(String name) {
        String[] parts = path(name);
        String[] parentsNames = new String[(ListenerUtil.mutListener.listen(22432) ? (parts.length % 1) : (ListenerUtil.mutListener.listen(22431) ? (parts.length / 1) : (ListenerUtil.mutListener.listen(22430) ? (parts.length * 1) : (ListenerUtil.mutListener.listen(22429) ? (parts.length + 1) : (parts.length - 1)))))];
        // So the array size is 1 less than the number of parts.
        String prefix = "";
        if (!ListenerUtil.mutListener.listen(22445)) {
            {
                long _loopCounter518 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(22444) ? (i >= (ListenerUtil.mutListener.listen(22439) ? (parts.length % 1) : (ListenerUtil.mutListener.listen(22438) ? (parts.length / 1) : (ListenerUtil.mutListener.listen(22437) ? (parts.length * 1) : (ListenerUtil.mutListener.listen(22436) ? (parts.length + 1) : (parts.length - 1)))))) : (ListenerUtil.mutListener.listen(22443) ? (i <= (ListenerUtil.mutListener.listen(22439) ? (parts.length % 1) : (ListenerUtil.mutListener.listen(22438) ? (parts.length / 1) : (ListenerUtil.mutListener.listen(22437) ? (parts.length * 1) : (ListenerUtil.mutListener.listen(22436) ? (parts.length + 1) : (parts.length - 1)))))) : (ListenerUtil.mutListener.listen(22442) ? (i > (ListenerUtil.mutListener.listen(22439) ? (parts.length % 1) : (ListenerUtil.mutListener.listen(22438) ? (parts.length / 1) : (ListenerUtil.mutListener.listen(22437) ? (parts.length * 1) : (ListenerUtil.mutListener.listen(22436) ? (parts.length + 1) : (parts.length - 1)))))) : (ListenerUtil.mutListener.listen(22441) ? (i != (ListenerUtil.mutListener.listen(22439) ? (parts.length % 1) : (ListenerUtil.mutListener.listen(22438) ? (parts.length / 1) : (ListenerUtil.mutListener.listen(22437) ? (parts.length * 1) : (ListenerUtil.mutListener.listen(22436) ? (parts.length + 1) : (parts.length - 1)))))) : (ListenerUtil.mutListener.listen(22440) ? (i == (ListenerUtil.mutListener.listen(22439) ? (parts.length % 1) : (ListenerUtil.mutListener.listen(22438) ? (parts.length / 1) : (ListenerUtil.mutListener.listen(22437) ? (parts.length * 1) : (ListenerUtil.mutListener.listen(22436) ? (parts.length + 1) : (parts.length - 1)))))) : (i < (ListenerUtil.mutListener.listen(22439) ? (parts.length % 1) : (ListenerUtil.mutListener.listen(22438) ? (parts.length / 1) : (ListenerUtil.mutListener.listen(22437) ? (parts.length * 1) : (ListenerUtil.mutListener.listen(22436) ? (parts.length + 1) : (parts.length - 1))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter518", ++_loopCounter518);
                    if (!ListenerUtil.mutListener.listen(22433)) {
                        prefix += parts[i];
                    }
                    if (!ListenerUtil.mutListener.listen(22434)) {
                        parentsNames[i] = prefix;
                    }
                    if (!ListenerUtil.mutListener.listen(22435)) {
                        prefix += "::";
                    }
                }
            }
        }
        return parentsNames;
    }

    /**
     * All parents of did.
     */
    public List<Deck> parents(long did) {
        // get parent and grandparent names
        String[] parents = parentsNames(get(did).getString("name"));
        // convert to objects
        List<Deck> oParents = new ArrayList<>(parents.length);
        if (!ListenerUtil.mutListener.listen(22452)) {
            {
                long _loopCounter519 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(22451) ? (i >= parents.length) : (ListenerUtil.mutListener.listen(22450) ? (i <= parents.length) : (ListenerUtil.mutListener.listen(22449) ? (i > parents.length) : (ListenerUtil.mutListener.listen(22448) ? (i != parents.length) : (ListenerUtil.mutListener.listen(22447) ? (i == parents.length) : (i < parents.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter519", ++_loopCounter519);
                    String parentName = parents[i];
                    Deck deck = mNameMap.get(parentName);
                    if (!ListenerUtil.mutListener.listen(22446)) {
                        oParents.add(i, deck);
                    }
                }
            }
        }
        return oParents;
    }

    public void beforeUpload() {
        boolean changed_decks = Utils.markAsUploaded(all());
        boolean changed_conf = Utils.markAsUploaded(allConf());
        if (!ListenerUtil.mutListener.listen(22455)) {
            if ((ListenerUtil.mutListener.listen(22453) ? (changed_decks && changed_conf) : (changed_decks || changed_conf))) {
                if (!ListenerUtil.mutListener.listen(22454)) {
                    // directly applied to the methods.
                    save();
                }
            }
        }
    }

    /**
     * Return a new dynamic deck and set it as the current deck.
     */
    public long newDyn(String name) {
        long did = id(name, defaultDynamicDeck);
        if (!ListenerUtil.mutListener.listen(22456)) {
            select(did);
        }
        return did;
    }

    public boolean isDyn(long did) {
        return get(did).getInt("dyn") == DECK_DYN;
    }

    /*
     * ******************************
     * utils methods
     * **************************************
     */
    private static final HashMap<String, String> normalized = new HashMap<>();

    public static String normalizeName(String name) {
        if (!ListenerUtil.mutListener.listen(22458)) {
            if (!normalized.containsKey(name)) {
                if (!ListenerUtil.mutListener.listen(22457)) {
                    normalized.put(name, Normalizer.normalize(name, Normalizer.Form.NFC).toLowerCase(Locale.ROOT));
                }
            }
        }
        return normalized.get(name);
    }

    public static boolean equalName(String name1, String name2) {
        return normalizeName(name1).equals(normalizeName(name2));
    }

    public static boolean isValidDeckName(@Nullable String deckName) {
        return (ListenerUtil.mutListener.listen(22459) ? (deckName != null || !deckName.trim().isEmpty()) : (deckName != null && !deckName.trim().isEmpty()));
    }

    private static final HashMap<String, String> sParentCache = new HashMap<>();

    public static String parent(String deckName) {
        if (!ListenerUtil.mutListener.listen(22473)) {
            // method parent, from sched's method deckDueList in python
            if (!sParentCache.containsKey(deckName)) {
                List<String> parts = Arrays.asList(path(deckName));
                if (!ListenerUtil.mutListener.listen(22472)) {
                    if ((ListenerUtil.mutListener.listen(22464) ? (parts.size() >= 2) : (ListenerUtil.mutListener.listen(22463) ? (parts.size() <= 2) : (ListenerUtil.mutListener.listen(22462) ? (parts.size() > 2) : (ListenerUtil.mutListener.listen(22461) ? (parts.size() != 2) : (ListenerUtil.mutListener.listen(22460) ? (parts.size() == 2) : (parts.size() < 2))))))) {
                        if (!ListenerUtil.mutListener.listen(22471)) {
                            sParentCache.put(deckName, null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(22469)) {
                            parts = parts.subList(0, (ListenerUtil.mutListener.listen(22468) ? (parts.size() % 1) : (ListenerUtil.mutListener.listen(22467) ? (parts.size() / 1) : (ListenerUtil.mutListener.listen(22466) ? (parts.size() * 1) : (ListenerUtil.mutListener.listen(22465) ? (parts.size() + 1) : (parts.size() - 1))))));
                        }
                        String parentName = TextUtils.join("::", parts);
                        if (!ListenerUtil.mutListener.listen(22470)) {
                            sParentCache.put(deckName, parentName);
                        }
                    }
                }
            }
        }
        return sParentCache.get(deckName);
    }

    public String getActualDescription() {
        return current().optString("desc", "");
    }

    public HashMap<Long, Deck> getDecks() {
        return mDecks;
    }

    public Long[] allDynamicDeckIds() {
        Set<Long> ids = allIds();
        ArrayList<Long> validValues = new ArrayList<>(ids.size());
        if (!ListenerUtil.mutListener.listen(22476)) {
            {
                long _loopCounter520 = 0;
                for (Long did : ids) {
                    ListenerUtil.loopListener.listen("_loopCounter520", ++_loopCounter520);
                    if (!ListenerUtil.mutListener.listen(22475)) {
                        if (isDyn(did)) {
                            if (!ListenerUtil.mutListener.listen(22474)) {
                                validValues.add(did);
                            }
                        }
                    }
                }
            }
        }
        return validValues.toArray(new Long[0]);
    }

    private Deck getDeckOrFail(long deckId) throws NoSuchDeckException {
        Deck deck = get(deckId, false);
        if (!ListenerUtil.mutListener.listen(22477)) {
            if (deck == null) {
                throw new NoSuchDeckException(deckId);
            }
        }
        return deck;
    }

    public boolean hasDeckOptions(long deckId) throws NoSuchDeckException {
        return getDeckOrFail(deckId).has("conf");
    }

    public void removeDeckOptions(long deckId) throws NoSuchDeckException {
        if (!ListenerUtil.mutListener.listen(22478)) {
            getDeckOrFail(deckId).remove("conf");
        }
    }

    public static boolean isDynamic(Collection col, long deckId) {
        return Decks.isDynamic(col.getDecks().get(deckId));
    }

    public static boolean isDynamic(Deck deck) {
        return deck.getInt("dyn") == DECK_DYN;
    }

    /**
     * Retruns the fully qualified name of the subdeck, or null if unavailable
     */
    @Nullable
    public String getSubdeckName(long did, @Nullable String subdeckName) {
        if (!ListenerUtil.mutListener.listen(22479)) {
            if (TextUtils.isEmpty(subdeckName)) {
                return null;
            }
        }
        String newName = subdeckName.replaceAll("\"", "");
        if (!ListenerUtil.mutListener.listen(22480)) {
            if (TextUtils.isEmpty(newName)) {
                return null;
            }
        }
        Deck deck = get(did, false);
        if (!ListenerUtil.mutListener.listen(22481)) {
            if (deck == null) {
                return null;
            }
        }
        return deck.getString("name") + DECK_SEPARATOR + subdeckName;
    }
}

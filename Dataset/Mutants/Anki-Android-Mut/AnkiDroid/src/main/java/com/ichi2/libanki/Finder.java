/**
 * *************************************************************************************
 *  Copyright (c) 2012 Norbert Nagold <norbert.nagold@gmail.com>                         *
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

import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.async.CancelListener;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.ProgressSender;
import com.ichi2.libanki.Deck;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONObject;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.CheckResult;
import timber.log.Timber;
import static com.ichi2.async.CancelListener.isCancelled;
import static com.ichi2.libanki.stats.Stats.SECONDS_PER_DAY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.ExcessiveClassLength", "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions" })
public class Finder {

    private static final Pattern fPropPattern = Pattern.compile("(^.+?)(<=|>=|!=|=|<|>)(.+?$)");

    private static final Pattern fNidsPattern = Pattern.compile("[^0-9,]");

    private static final Pattern fMidPattern = Pattern.compile("[^0-9]");

    private final Collection mCol;

    public Finder(Collection col) {
        mCol = col;
    }

    /**
     * Return a list of card ids for QUERY
     */
    @CheckResult
    public List<Long> findCards(String query, String _order) {
        return _findCards(query, _order);
    }

    @CheckResult
    public List<Long> findCards(String query, boolean _order) {
        return findCards(query, _order, null);
    }

    @CheckResult
    public List<Long> findCards(String query, boolean _order, CollectionTask.PartialSearch task) {
        return _findCards(query, _order, task);
    }

    @CheckResult
    private List<Long> _findCards(String query, Object _order) {
        return _findCards(query, _order, null);
    }

    @CheckResult
    private List<Long> _findCards(String query, Object _order, CollectionTask.PartialSearch task) {
        String[] tokens = _tokenize(query);
        Pair<String, String[]> res1 = _where(tokens);
        String preds = res1.first;
        String[] args = res1.second;
        List<Long> res = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22482)) {
            if (preds == null) {
                return res;
            }
        }
        Pair<String, Boolean> res2 = _order instanceof Boolean ? _order((Boolean) _order) : _order((String) _order);
        String order = res2.first;
        boolean rev = res2.second;
        String sql = _query(preds, order);
        if (!ListenerUtil.mutListener.listen(22483)) {
            Timber.v("Search query '%s' is compiled as '%s'.", query, sql);
        }
        boolean sendProgress = task != null;
        try (Cursor cur = mCol.getDb().getDatabase().query(sql, args)) {
            if (!ListenerUtil.mutListener.listen(22495)) {
                {
                    long _loopCounter521 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter521", ++_loopCounter521);
                        if (!ListenerUtil.mutListener.listen(22484)) {
                            if (isCancelled(task)) {
                                return new ArrayList<>(0);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22485)) {
                            res.add(cur.getLong(0));
                        }
                        if (!ListenerUtil.mutListener.listen(22494)) {
                            if ((ListenerUtil.mutListener.listen(22491) ? (sendProgress || (ListenerUtil.mutListener.listen(22490) ? (res.size() >= task.getNumCardsToRender()) : (ListenerUtil.mutListener.listen(22489) ? (res.size() <= task.getNumCardsToRender()) : (ListenerUtil.mutListener.listen(22488) ? (res.size() < task.getNumCardsToRender()) : (ListenerUtil.mutListener.listen(22487) ? (res.size() != task.getNumCardsToRender()) : (ListenerUtil.mutListener.listen(22486) ? (res.size() == task.getNumCardsToRender()) : (res.size() > task.getNumCardsToRender()))))))) : (sendProgress && (ListenerUtil.mutListener.listen(22490) ? (res.size() >= task.getNumCardsToRender()) : (ListenerUtil.mutListener.listen(22489) ? (res.size() <= task.getNumCardsToRender()) : (ListenerUtil.mutListener.listen(22488) ? (res.size() < task.getNumCardsToRender()) : (ListenerUtil.mutListener.listen(22487) ? (res.size() != task.getNumCardsToRender()) : (ListenerUtil.mutListener.listen(22486) ? (res.size() == task.getNumCardsToRender()) : (res.size() > task.getNumCardsToRender()))))))))) {
                                if (!ListenerUtil.mutListener.listen(22492)) {
                                    task.doProgress(res);
                                }
                                if (!ListenerUtil.mutListener.listen(22493)) {
                                    sendProgress = false;
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // invalid grouping
            return new ArrayList<>(0);
        }
        if (!ListenerUtil.mutListener.listen(22497)) {
            if (rev) {
                if (!ListenerUtil.mutListener.listen(22496)) {
                    Collections.reverse(res);
                }
            }
        }
        return res;
    }

    public List<Long> findNotes(String query) {
        String[] tokens = _tokenize(query);
        Pair<String, String[]> res1 = _where(tokens);
        String preds = res1.first;
        String[] args = res1.second;
        List<Long> res = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22498)) {
            if (preds == null) {
                return res;
            }
        }
        if (!ListenerUtil.mutListener.listen(22501)) {
            if ("".equals(preds)) {
                if (!ListenerUtil.mutListener.listen(22500)) {
                    preds = "1";
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22499)) {
                    preds = "(" + preds + ")";
                }
            }
        }
        String sql = "select distinct(n.id) from cards c, notes n where c.nid=n.id and " + preds;
        try (Cursor cur = mCol.getDb().getDatabase().query(sql, args)) {
            if (!ListenerUtil.mutListener.listen(22503)) {
                {
                    long _loopCounter522 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter522", ++_loopCounter522);
                        if (!ListenerUtil.mutListener.listen(22502)) {
                            res.add(cur.getLong(0));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // invalid grouping
            return new ArrayList<>(0);
        }
        return res;
    }

    public String[] _tokenize(String query) {
        char inQuote = 0;
        List<String> tokens = new ArrayList<>();
        String token = "";
        if (!ListenerUtil.mutListener.listen(22565)) {
            {
                long _loopCounter523 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(22564) ? (i >= query.length()) : (ListenerUtil.mutListener.listen(22563) ? (i <= query.length()) : (ListenerUtil.mutListener.listen(22562) ? (i > query.length()) : (ListenerUtil.mutListener.listen(22561) ? (i != query.length()) : (ListenerUtil.mutListener.listen(22560) ? (i == query.length()) : (i < query.length())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter523", ++_loopCounter523);
                    // quoted text
                    char c = query.charAt(i);
                    if (!ListenerUtil.mutListener.listen(22559)) {
                        if ((ListenerUtil.mutListener.listen(22504) ? (c == '\'' && c == '"') : (c == '\'' || c == '"'))) {
                            if (!ListenerUtil.mutListener.listen(22558)) {
                                if (inQuote != 0) {
                                    if (!ListenerUtil.mutListener.listen(22557)) {
                                        if (c == inQuote) {
                                            if (!ListenerUtil.mutListener.listen(22556)) {
                                                inQuote = 0;
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(22555)) {
                                                token += c;
                                            }
                                        }
                                    }
                                } else if ((ListenerUtil.mutListener.listen(22550) ? (token.length() >= 0) : (ListenerUtil.mutListener.listen(22549) ? (token.length() <= 0) : (ListenerUtil.mutListener.listen(22548) ? (token.length() > 0) : (ListenerUtil.mutListener.listen(22547) ? (token.length() < 0) : (ListenerUtil.mutListener.listen(22546) ? (token.length() == 0) : (token.length() != 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(22554)) {
                                        // quotes are allowed to start directly after a :
                                        if (token.endsWith(":")) {
                                            if (!ListenerUtil.mutListener.listen(22553)) {
                                                inQuote = c;
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(22552)) {
                                                token += c;
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(22551)) {
                                        inQuote = c;
                                    }
                                }
                            }
                        } else if (c == ' ') {
                            if (!ListenerUtil.mutListener.listen(22545)) {
                                if (inQuote != 0) {
                                    if (!ListenerUtil.mutListener.listen(22544)) {
                                        token += c;
                                    }
                                } else if ((ListenerUtil.mutListener.listen(22541) ? (token.length() >= 0) : (ListenerUtil.mutListener.listen(22540) ? (token.length() <= 0) : (ListenerUtil.mutListener.listen(22539) ? (token.length() > 0) : (ListenerUtil.mutListener.listen(22538) ? (token.length() < 0) : (ListenerUtil.mutListener.listen(22537) ? (token.length() == 0) : (token.length() != 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(22542)) {
                                        // space marks token finished
                                        tokens.add(token);
                                    }
                                    if (!ListenerUtil.mutListener.listen(22543)) {
                                        token = "";
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(22505) ? (c == '(' && c == ')') : (c == '(' || c == ')'))) {
                            if (!ListenerUtil.mutListener.listen(22536)) {
                                if (inQuote != 0) {
                                    if (!ListenerUtil.mutListener.listen(22535)) {
                                        token += c;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(22533)) {
                                        if ((ListenerUtil.mutListener.listen(22530) ? (c == ')' || (ListenerUtil.mutListener.listen(22529) ? (token.length() >= 0) : (ListenerUtil.mutListener.listen(22528) ? (token.length() <= 0) : (ListenerUtil.mutListener.listen(22527) ? (token.length() > 0) : (ListenerUtil.mutListener.listen(22526) ? (token.length() < 0) : (ListenerUtil.mutListener.listen(22525) ? (token.length() == 0) : (token.length() != 0))))))) : (c == ')' && (ListenerUtil.mutListener.listen(22529) ? (token.length() >= 0) : (ListenerUtil.mutListener.listen(22528) ? (token.length() <= 0) : (ListenerUtil.mutListener.listen(22527) ? (token.length() > 0) : (ListenerUtil.mutListener.listen(22526) ? (token.length() < 0) : (ListenerUtil.mutListener.listen(22525) ? (token.length() == 0) : (token.length() != 0))))))))) {
                                            if (!ListenerUtil.mutListener.listen(22531)) {
                                                tokens.add(token);
                                            }
                                            if (!ListenerUtil.mutListener.listen(22532)) {
                                                token = "";
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(22534)) {
                                        tokens.add(String.valueOf(c));
                                    }
                                }
                            }
                        } else if (c == '-') {
                            if (!ListenerUtil.mutListener.listen(22524)) {
                                if ((ListenerUtil.mutListener.listen(22511) ? (token.length() >= 0) : (ListenerUtil.mutListener.listen(22510) ? (token.length() <= 0) : (ListenerUtil.mutListener.listen(22509) ? (token.length() > 0) : (ListenerUtil.mutListener.listen(22508) ? (token.length() < 0) : (ListenerUtil.mutListener.listen(22507) ? (token.length() == 0) : (token.length() != 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(22523)) {
                                        token += c;
                                    }
                                } else if ((ListenerUtil.mutListener.listen(22521) ? ((ListenerUtil.mutListener.listen(22516) ? (tokens.size() >= 0) : (ListenerUtil.mutListener.listen(22515) ? (tokens.size() <= 0) : (ListenerUtil.mutListener.listen(22514) ? (tokens.size() > 0) : (ListenerUtil.mutListener.listen(22513) ? (tokens.size() < 0) : (ListenerUtil.mutListener.listen(22512) ? (tokens.size() != 0) : (tokens.size() == 0)))))) && !"-".equals(tokens.get((ListenerUtil.mutListener.listen(22520) ? (tokens.size() % 1) : (ListenerUtil.mutListener.listen(22519) ? (tokens.size() / 1) : (ListenerUtil.mutListener.listen(22518) ? (tokens.size() * 1) : (ListenerUtil.mutListener.listen(22517) ? (tokens.size() + 1) : (tokens.size() - 1)))))))) : ((ListenerUtil.mutListener.listen(22516) ? (tokens.size() >= 0) : (ListenerUtil.mutListener.listen(22515) ? (tokens.size() <= 0) : (ListenerUtil.mutListener.listen(22514) ? (tokens.size() > 0) : (ListenerUtil.mutListener.listen(22513) ? (tokens.size() < 0) : (ListenerUtil.mutListener.listen(22512) ? (tokens.size() != 0) : (tokens.size() == 0)))))) || !"-".equals(tokens.get((ListenerUtil.mutListener.listen(22520) ? (tokens.size() % 1) : (ListenerUtil.mutListener.listen(22519) ? (tokens.size() / 1) : (ListenerUtil.mutListener.listen(22518) ? (tokens.size() * 1) : (ListenerUtil.mutListener.listen(22517) ? (tokens.size() + 1) : (tokens.size() - 1)))))))))) {
                                    if (!ListenerUtil.mutListener.listen(22522)) {
                                        tokens.add("-");
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(22506)) {
                                token += c;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22572)) {
            // if we finished in a token, add it
            if ((ListenerUtil.mutListener.listen(22570) ? (token.length() >= 0) : (ListenerUtil.mutListener.listen(22569) ? (token.length() <= 0) : (ListenerUtil.mutListener.listen(22568) ? (token.length() > 0) : (ListenerUtil.mutListener.listen(22567) ? (token.length() < 0) : (ListenerUtil.mutListener.listen(22566) ? (token.length() == 0) : (token.length() != 0))))))) {
                if (!ListenerUtil.mutListener.listen(22571)) {
                    tokens.add(token);
                }
            }
        }
        return tokens.toArray(new String[tokens.size()]);
    }

    /**
     * LibAnki creates a dictionary and operates on it with an inner function inside _where().
     * AnkiDroid combines the two in this class instead.
     */
    public static class SearchState {

        public boolean isnot;

        public boolean isor;

        public boolean join;

        public String q = "";

        public boolean bad;

        public void add(String txt) {
            if (!ListenerUtil.mutListener.listen(22573)) {
                add(txt, true);
            }
        }

        public void add(String txt, boolean wrap) {
            if (!ListenerUtil.mutListener.listen(22577)) {
                // failed command?
                if (TextUtils.isEmpty(txt)) {
                    if (!ListenerUtil.mutListener.listen(22576)) {
                        // if it was to be negated then we can just ignore it
                        if (isnot) {
                            if (!ListenerUtil.mutListener.listen(22575)) {
                                isnot = false;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(22574)) {
                                bad = true;
                            }
                        }
                    }
                    return;
                } else if ("skip".equals(txt)) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(22582)) {
                // do we need a conjunction?
                if (join) {
                    if (!ListenerUtil.mutListener.listen(22581)) {
                        if (isor) {
                            if (!ListenerUtil.mutListener.listen(22579)) {
                                q += " or ";
                            }
                            if (!ListenerUtil.mutListener.listen(22580)) {
                                isor = false;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(22578)) {
                                q += " and ";
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(22585)) {
                if (isnot) {
                    if (!ListenerUtil.mutListener.listen(22583)) {
                        q += " not ";
                    }
                    if (!ListenerUtil.mutListener.listen(22584)) {
                        isnot = false;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(22587)) {
                if (wrap) {
                    if (!ListenerUtil.mutListener.listen(22586)) {
                        txt = "(" + txt + ")";
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(22588)) {
                q += txt;
            }
            if (!ListenerUtil.mutListener.listen(22589)) {
                join = true;
            }
        }
    }

    private Pair<String, String[]> _where(String[] tokens) {
        // state and query
        SearchState s = new SearchState();
        List<String> args = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22613)) {
            {
                long _loopCounter524 = 0;
                for (String token : tokens) {
                    ListenerUtil.loopListener.listen("_loopCounter524", ++_loopCounter524);
                    if (!ListenerUtil.mutListener.listen(22590)) {
                        if (s.bad) {
                            return new Pair<>(null, null);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22612)) {
                        // special tokens
                        if ("-".equals(token)) {
                            if (!ListenerUtil.mutListener.listen(22611)) {
                                s.isnot = true;
                            }
                        } else if ("or".equalsIgnoreCase(token)) {
                            if (!ListenerUtil.mutListener.listen(22610)) {
                                s.isor = true;
                            }
                        } else if ("(".equals(token)) {
                            if (!ListenerUtil.mutListener.listen(22608)) {
                                s.add(token, false);
                            }
                            if (!ListenerUtil.mutListener.listen(22609)) {
                                s.join = false;
                            }
                        } else if (")".equals(token)) {
                            if (!ListenerUtil.mutListener.listen(22607)) {
                                s.q += ")";
                            }
                        } else if (token.contains(":")) {
                            String[] spl = token.split(":", 2);
                            String cmd = spl[0].toLowerCase(Locale.ROOT);
                            String val = spl[1];
                            if (!ListenerUtil.mutListener.listen(22606)) {
                                switch(cmd) {
                                    case "added":
                                        if (!ListenerUtil.mutListener.listen(22592)) {
                                            s.add(_findAdded(val));
                                        }
                                        break;
                                    case "card":
                                        if (!ListenerUtil.mutListener.listen(22593)) {
                                            s.add(_findTemplate(val));
                                        }
                                        break;
                                    case "deck":
                                        if (!ListenerUtil.mutListener.listen(22594)) {
                                            s.add(_findDeck(val));
                                        }
                                        break;
                                    case "flag":
                                        if (!ListenerUtil.mutListener.listen(22595)) {
                                            s.add(_findFlag(val));
                                        }
                                        break;
                                    case "mid":
                                        if (!ListenerUtil.mutListener.listen(22596)) {
                                            s.add(_findMid(val));
                                        }
                                        break;
                                    case "nid":
                                        if (!ListenerUtil.mutListener.listen(22597)) {
                                            s.add(_findNids(val));
                                        }
                                        break;
                                    case "cid":
                                        if (!ListenerUtil.mutListener.listen(22598)) {
                                            s.add(_findCids(val));
                                        }
                                        break;
                                    case "note":
                                        if (!ListenerUtil.mutListener.listen(22599)) {
                                            s.add(_findModel(val));
                                        }
                                        break;
                                    case "prop":
                                        if (!ListenerUtil.mutListener.listen(22600)) {
                                            s.add(_findProp(val));
                                        }
                                        break;
                                    case "rated":
                                        if (!ListenerUtil.mutListener.listen(22601)) {
                                            s.add(_findRated(val));
                                        }
                                        break;
                                    case "tag":
                                        if (!ListenerUtil.mutListener.listen(22602)) {
                                            s.add(_findTag(val, args));
                                        }
                                        break;
                                    case "dupe":
                                        if (!ListenerUtil.mutListener.listen(22603)) {
                                            s.add(_findDupes(val));
                                        }
                                        break;
                                    case "is":
                                        if (!ListenerUtil.mutListener.listen(22604)) {
                                            s.add(_findCardState(val));
                                        }
                                        break;
                                    default:
                                        if (!ListenerUtil.mutListener.listen(22605)) {
                                            s.add(_findField(cmd, val));
                                        }
                                        break;
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(22591)) {
                                s.add(_findText(token, args));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22614)) {
            if (s.bad) {
                return new Pair<>(null, null);
            }
        }
        return new Pair<>(s.q, args.toArray(new String[args.size()]));
    }

    /**
     * @param preds A sql predicate, or empty string, with c a card, n its note
     * @param order A part of a query, ordering element of table Card, with c a card, n its note
     * @return A query to return all card ids satifying the predicate and in the given order
     */
    private static String _query(String preds, String order) {
        // can we skip the note table?
        String sql;
        if ((ListenerUtil.mutListener.listen(22615) ? (!preds.contains("n.") || !order.contains("n.")) : (!preds.contains("n.") && !order.contains("n.")))) {
            sql = "select c.id from cards c where ";
        } else {
            sql = "select c.id from cards c, notes n where c.nid=n.id and ";
        }
        if (!ListenerUtil.mutListener.listen(22618)) {
            // combine with preds
            if (!TextUtils.isEmpty(preds)) {
                if (!ListenerUtil.mutListener.listen(22617)) {
                    sql += "(" + preds + ")";
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22616)) {
                    sql += "1";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22620)) {
            // order
            if (!TextUtils.isEmpty(order)) {
                if (!ListenerUtil.mutListener.listen(22619)) {
                    sql += " " + order;
                }
            }
        }
        return sql;
    }

    private Pair<String, Boolean> _order(String order) {
        if (TextUtils.isEmpty(order)) {
            return _order(false);
        } else {
            // custom order string provided
            return new Pair<>(" order by " + order, false);
        }
    }

    private Pair<String, Boolean> _order(Boolean order) {
        if (!ListenerUtil.mutListener.listen(22621)) {
            if (!order) {
                return new Pair<>("", false);
            }
        }
        // use deck default
        String type = mCol.getConf().getString("sortType");
        String sort = null;
        if (!ListenerUtil.mutListener.listen(22633)) {
            if (type.startsWith("note")) {
                if (!ListenerUtil.mutListener.listen(22632)) {
                    if (type.startsWith("noteCrt")) {
                        if (!ListenerUtil.mutListener.listen(22631)) {
                            sort = "n.id, c.ord";
                        }
                    } else if (type.startsWith("noteMod")) {
                        if (!ListenerUtil.mutListener.listen(22630)) {
                            sort = "n.mod, c.ord";
                        }
                    } else if (type.startsWith("noteFld")) {
                        if (!ListenerUtil.mutListener.listen(22629)) {
                            sort = "n.sfld COLLATE NOCASE, c.ord";
                        }
                    }
                }
            } else if (type.startsWith("card")) {
                if (!ListenerUtil.mutListener.listen(22628)) {
                    if (type.startsWith("cardMod")) {
                        if (!ListenerUtil.mutListener.listen(22627)) {
                            sort = "c.mod";
                        }
                    } else if (type.startsWith("cardReps")) {
                        if (!ListenerUtil.mutListener.listen(22626)) {
                            sort = "c.reps";
                        }
                    } else if (type.startsWith("cardDue")) {
                        if (!ListenerUtil.mutListener.listen(22625)) {
                            sort = "c.type, c.due";
                        }
                    } else if (type.startsWith("cardEase")) {
                        if (!ListenerUtil.mutListener.listen(22624)) {
                            sort = "c.type == " + Consts.CARD_TYPE_NEW + ", c.factor";
                        }
                    } else if (type.startsWith("cardLapses")) {
                        if (!ListenerUtil.mutListener.listen(22623)) {
                            sort = "c.lapses";
                        }
                    } else if (type.startsWith("cardIvl")) {
                        if (!ListenerUtil.mutListener.listen(22622)) {
                            sort = "c.ivl";
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22635)) {
            if (sort == null) {
                if (!ListenerUtil.mutListener.listen(22634)) {
                    // deck has invalid sort order; revert to noteCrt
                    sort = "n.id, c.ord";
                }
            }
        }
        boolean sortBackwards = mCol.getConf().getBoolean("sortBackwards");
        return new Pair<>(" ORDER BY " + sort, sortBackwards);
    }

    private String _findTag(String val, List<String> args) {
        if (!ListenerUtil.mutListener.listen(22636)) {
            if ("none".equals(val)) {
                return "n.tags = \"\"";
            }
        }
        if (!ListenerUtil.mutListener.listen(22637)) {
            val = val.replace("*", "%");
        }
        if (!ListenerUtil.mutListener.listen(22639)) {
            if (!val.startsWith("%")) {
                if (!ListenerUtil.mutListener.listen(22638)) {
                    val = "% " + val;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22642)) {
            if ((ListenerUtil.mutListener.listen(22640) ? (!val.endsWith("%") && val.endsWith("\\%")) : (!val.endsWith("%") || val.endsWith("\\%")))) {
                if (!ListenerUtil.mutListener.listen(22641)) {
                    val += " %";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22643)) {
            args.add(val);
        }
        return "n.tags like ? escape '\\'";
    }

    private String _findCardState(String val) {
        int n;
        if ((ListenerUtil.mutListener.listen(22645) ? ((ListenerUtil.mutListener.listen(22644) ? ("review".equals(val) && "new".equals(val)) : ("review".equals(val) || "new".equals(val))) && "learn".equals(val)) : ((ListenerUtil.mutListener.listen(22644) ? ("review".equals(val) && "new".equals(val)) : ("review".equals(val) || "new".equals(val))) || "learn".equals(val)))) {
            if ("review".equals(val)) {
                n = 2;
            } else if ("new".equals(val)) {
                n = 0;
            } else {
                return "queue IN (1, " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ")";
            }
            return "type = " + n;
        } else if ("suspended".equals(val)) {
            return "c.queue = " + Consts.QUEUE_TYPE_SUSPENDED;
        } else if ("buried".equals(val)) {
            return "c.queue in (" + Consts.QUEUE_TYPE_SIBLING_BURIED + ", " + Consts.QUEUE_TYPE_MANUALLY_BURIED + ")";
        } else if ("due".equals(val)) {
            return "(c.queue in (" + Consts.QUEUE_TYPE_REV + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ") and c.due <= " + mCol.getSched().getToday() + ") or (c.queue = " + Consts.QUEUE_TYPE_LRN + " and c.due <= " + mCol.getSched().getDayCutoff() + ")";
        } else {
            return null;
        }
    }

    private String _findFlag(String val) {
        int flag;
        switch(val) {
            case "0":
                flag = 0;
                break;
            case "1":
                flag = 1;
                break;
            case "2":
                flag = 2;
                break;
            case "3":
                flag = 3;
                break;
            case "4":
                flag = 4;
                break;
            default:
                return null;
        }
        // 2**3 -1 in Anki
        int mask = 0b111;
        return "(c.flags & " + mask + ") == " + flag;
    }

    private String _findRated(String val) {
        // days(:optional_ease)
        String[] r = val.split(":");
        int days;
        try {
            days = Integer.parseInt(r[0]);
        } catch (NumberFormatException e) {
            return null;
        }
        days = Math.min(days, 31);
        // ease
        String ease = "";
        if (!ListenerUtil.mutListener.listen(22653)) {
            if ((ListenerUtil.mutListener.listen(22650) ? (r.length >= 1) : (ListenerUtil.mutListener.listen(22649) ? (r.length <= 1) : (ListenerUtil.mutListener.listen(22648) ? (r.length < 1) : (ListenerUtil.mutListener.listen(22647) ? (r.length != 1) : (ListenerUtil.mutListener.listen(22646) ? (r.length == 1) : (r.length > 1))))))) {
                if (!ListenerUtil.mutListener.listen(22651)) {
                    if (!Arrays.asList("1", "2", "3", "4").contains(r[1])) {
                        return null;
                    }
                }
                if (!ListenerUtil.mutListener.listen(22652)) {
                    ease = "and ease=" + r[1];
                }
            }
        }
        long cutoff = (ListenerUtil.mutListener.listen(22665) ? (((ListenerUtil.mutListener.listen(22661) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22660) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22659) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22658) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) % 1000) : (ListenerUtil.mutListener.listen(22664) ? (((ListenerUtil.mutListener.listen(22661) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22660) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22659) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22658) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) / 1000) : (ListenerUtil.mutListener.listen(22663) ? (((ListenerUtil.mutListener.listen(22661) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22660) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22659) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22658) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) - 1000) : (ListenerUtil.mutListener.listen(22662) ? (((ListenerUtil.mutListener.listen(22661) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22660) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22659) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22658) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) + 1000) : (((ListenerUtil.mutListener.listen(22661) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22660) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22659) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22658) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22657) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22656) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22655) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22654) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) * 1000)))));
        return "c.id in (select cid from revlog where id>" + cutoff + " " + ease + ")";
    }

    private String _findAdded(String val) {
        int days;
        try {
            days = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return null;
        }
        long cutoff = (ListenerUtil.mutListener.listen(22677) ? (((ListenerUtil.mutListener.listen(22673) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22672) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22671) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22670) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) % 1000) : (ListenerUtil.mutListener.listen(22676) ? (((ListenerUtil.mutListener.listen(22673) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22672) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22671) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22670) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) / 1000) : (ListenerUtil.mutListener.listen(22675) ? (((ListenerUtil.mutListener.listen(22673) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22672) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22671) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22670) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) - 1000) : (ListenerUtil.mutListener.listen(22674) ? (((ListenerUtil.mutListener.listen(22673) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22672) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22671) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22670) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) + 1000) : (((ListenerUtil.mutListener.listen(22673) ? (mCol.getSched().getDayCutoff() % (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22672) ? (mCol.getSched().getDayCutoff() / (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22671) ? (mCol.getSched().getDayCutoff() * (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (ListenerUtil.mutListener.listen(22670) ? (mCol.getSched().getDayCutoff() + (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days)))))) : (mCol.getSched().getDayCutoff() - (ListenerUtil.mutListener.listen(22669) ? (SECONDS_PER_DAY % days) : (ListenerUtil.mutListener.listen(22668) ? (SECONDS_PER_DAY / days) : (ListenerUtil.mutListener.listen(22667) ? (SECONDS_PER_DAY - days) : (ListenerUtil.mutListener.listen(22666) ? (SECONDS_PER_DAY + days) : (SECONDS_PER_DAY * days))))))))))) * 1000)))));
        return "c.id > " + cutoff;
    }

    private String _findProp(String _val) {
        // extract
        Matcher m = fPropPattern.matcher(_val);
        if (!ListenerUtil.mutListener.listen(22678)) {
            if (!m.matches()) {
                return null;
            }
        }
        String prop = m.group(1).toLowerCase(Locale.ROOT);
        String cmp = m.group(2);
        String sval = m.group(3);
        int val;
        // is val valid?
        try {
            if ("ease".equals(prop)) {
                // LibAnki does this below, but we do it here to avoid keeping a separate float value.
                val = (int) ((ListenerUtil.mutListener.listen(22682) ? (Double.parseDouble(sval) % 1000) : (ListenerUtil.mutListener.listen(22681) ? (Double.parseDouble(sval) / 1000) : (ListenerUtil.mutListener.listen(22680) ? (Double.parseDouble(sval) - 1000) : (ListenerUtil.mutListener.listen(22679) ? (Double.parseDouble(sval) + 1000) : (Double.parseDouble(sval) * 1000))))));
            } else {
                val = Integer.parseInt(sval);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        if (!ListenerUtil.mutListener.listen(22683)) {
            // is prop valid?
            if (!Arrays.asList("due", "ivl", "reps", "lapses", "ease").contains(prop)) {
                return null;
            }
        }
        // query
        String q = "";
        if (!ListenerUtil.mutListener.listen(22687)) {
            if ("due".equals(prop)) {
                if (!ListenerUtil.mutListener.listen(22685)) {
                    val += mCol.getSched().getToday();
                }
                if (!ListenerUtil.mutListener.listen(22686)) {
                    // only valid for review/daily learning
                    q = "(c.queue in (" + Consts.QUEUE_TYPE_REV + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ")) and ";
                }
            } else if ("ease".equals(prop)) {
                if (!ListenerUtil.mutListener.listen(22684)) {
                    prop = "factor";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22688)) {
            q += "(" + prop + " " + cmp + " " + val + ")";
        }
        return q;
    }

    private String _findText(String val, List<String> args) {
        if (!ListenerUtil.mutListener.listen(22689)) {
            val = val.replace("*", "%");
        }
        if (!ListenerUtil.mutListener.listen(22690)) {
            args.add("%" + val + "%");
        }
        if (!ListenerUtil.mutListener.listen(22691)) {
            args.add("%" + val + "%");
        }
        return "(n.sfld like ? escape '\\' or n.flds like ? escape '\\')";
    }

    private String _findNids(String val) {
        if (!ListenerUtil.mutListener.listen(22692)) {
            if (fNidsPattern.matcher(val).find()) {
                return null;
            }
        }
        return "n.id in (" + val + ")";
    }

    private String _findCids(String val) {
        if (!ListenerUtil.mutListener.listen(22693)) {
            if (fNidsPattern.matcher(val).find()) {
                return null;
            }
        }
        return "c.id in (" + val + ")";
    }

    private String _findMid(String val) {
        if (!ListenerUtil.mutListener.listen(22694)) {
            if (fMidPattern.matcher(val).find()) {
                return null;
            }
        }
        return "n.mid = " + val;
    }

    private String _findModel(String val) {
        LinkedList<Long> ids = new LinkedList<>();
        if (!ListenerUtil.mutListener.listen(22698)) {
            {
                long _loopCounter525 = 0;
                for (JSONObject m : mCol.getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter525", ++_loopCounter525);
                    String modelName = m.getString("name");
                    if (!ListenerUtil.mutListener.listen(22695)) {
                        modelName = Normalizer.normalize(modelName, Normalizer.Form.NFC);
                    }
                    if (!ListenerUtil.mutListener.listen(22697)) {
                        if (modelName.equalsIgnoreCase(val)) {
                            if (!ListenerUtil.mutListener.listen(22696)) {
                                ids.add(m.getLong("id"));
                            }
                        }
                    }
                }
            }
        }
        return "n.mid in " + Utils.ids2str(ids);
    }

    private List<Long> dids(Long did) {
        if (!ListenerUtil.mutListener.listen(22699)) {
            if (did == null) {
                return null;
            }
        }
        java.util.Collection<Long> children = mCol.getDecks().children(did).values();
        List<Long> res = new ArrayList<>((ListenerUtil.mutListener.listen(22703) ? (children.size() % 1) : (ListenerUtil.mutListener.listen(22702) ? (children.size() / 1) : (ListenerUtil.mutListener.listen(22701) ? (children.size() * 1) : (ListenerUtil.mutListener.listen(22700) ? (children.size() - 1) : (children.size() + 1))))));
        if (!ListenerUtil.mutListener.listen(22704)) {
            res.add(did);
        }
        if (!ListenerUtil.mutListener.listen(22705)) {
            res.addAll(children);
        }
        return res;
    }

    public String _findDeck(String val) {
        if (!ListenerUtil.mutListener.listen(22706)) {
            // if searching for all decks, skip
            if ("*".equals(val)) {
                return "skip";
            } else if ("filtered".equals(val)) {
                return "c.odid";
            }
        }
        List<Long> ids = null;
        if (!ListenerUtil.mutListener.listen(22720)) {
            // current deck?
            if ("current".equalsIgnoreCase(val)) {
                if (!ListenerUtil.mutListener.listen(22719)) {
                    ids = dids(mCol.getDecks().selected());
                }
            } else if (!val.contains("*")) {
                if (!ListenerUtil.mutListener.listen(22718)) {
                    // single deck
                    ids = dids(mCol.getDecks().id(val, false));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22707)) {
                    // wildcard
                    ids = dids(mCol.getDecks().id(val, false));
                }
                if (!ListenerUtil.mutListener.listen(22717)) {
                    if (ids == null) {
                        if (!ListenerUtil.mutListener.listen(22708)) {
                            ids = new ArrayList<>();
                        }
                        if (!ListenerUtil.mutListener.listen(22709)) {
                            val = val.replace("*", ".*");
                        }
                        if (!ListenerUtil.mutListener.listen(22710)) {
                            val = val.replace("+", "\\+");
                        }
                        if (!ListenerUtil.mutListener.listen(22716)) {
                            {
                                long _loopCounter527 = 0;
                                for (Deck d : mCol.getDecks().all()) {
                                    ListenerUtil.loopListener.listen("_loopCounter527", ++_loopCounter527);
                                    String deckName = d.getString("name");
                                    if (!ListenerUtil.mutListener.listen(22711)) {
                                        deckName = Normalizer.normalize(deckName, Normalizer.Form.NFC);
                                    }
                                    if (!ListenerUtil.mutListener.listen(22715)) {
                                        if (deckName.matches("(?i)" + val)) {
                                            if (!ListenerUtil.mutListener.listen(22714)) {
                                                {
                                                    long _loopCounter526 = 0;
                                                    for (long id : dids(d.getLong("id"))) {
                                                        ListenerUtil.loopListener.listen("_loopCounter526", ++_loopCounter526);
                                                        if (!ListenerUtil.mutListener.listen(22713)) {
                                                            if (!ids.contains(id)) {
                                                                if (!ListenerUtil.mutListener.listen(22712)) {
                                                                    ids.add(id);
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
        }
        if (!ListenerUtil.mutListener.listen(22727)) {
            if ((ListenerUtil.mutListener.listen(22726) ? (ids == null && (ListenerUtil.mutListener.listen(22725) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(22724) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(22723) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(22722) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(22721) ? (ids.size() != 0) : (ids.size() == 0))))))) : (ids == null || (ListenerUtil.mutListener.listen(22725) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(22724) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(22723) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(22722) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(22721) ? (ids.size() != 0) : (ids.size() == 0))))))))) {
                return null;
            }
        }
        String sids = Utils.ids2str(ids);
        return "c.did in " + sids + " or c.odid in " + sids;
    }

    private String _findTemplate(String val) {
        // were we given an ordinal number?
        Integer num = null;
        try {
            if (!ListenerUtil.mutListener.listen(22733)) {
                num = (ListenerUtil.mutListener.listen(22732) ? (Integer.parseInt(val) % 1) : (ListenerUtil.mutListener.listen(22731) ? (Integer.parseInt(val) / 1) : (ListenerUtil.mutListener.listen(22730) ? (Integer.parseInt(val) * 1) : (ListenerUtil.mutListener.listen(22729) ? (Integer.parseInt(val) + 1) : (Integer.parseInt(val) - 1)))));
            }
        } catch (NumberFormatException e) {
            if (!ListenerUtil.mutListener.listen(22728)) {
                num = null;
            }
        }
        if (!ListenerUtil.mutListener.listen(22734)) {
            if (num != null) {
                return "c.ord = " + num;
            }
        }
        // search for template names
        List<String> lims = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22741)) {
            {
                long _loopCounter529 = 0;
                for (Model m : mCol.getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter529", ++_loopCounter529);
                    JSONArray tmpls = m.getJSONArray("tmpls");
                    if (!ListenerUtil.mutListener.listen(22740)) {
                        {
                            long _loopCounter528 = 0;
                            for (JSONObject t : tmpls.jsonObjectIterable()) {
                                ListenerUtil.loopListener.listen("_loopCounter528", ++_loopCounter528);
                                String templateName = t.getString("name");
                                if (!ListenerUtil.mutListener.listen(22735)) {
                                    Normalizer.normalize(templateName, Normalizer.Form.NFC);
                                }
                                if (!ListenerUtil.mutListener.listen(22739)) {
                                    if (templateName.equalsIgnoreCase(val)) {
                                        if (!ListenerUtil.mutListener.listen(22738)) {
                                            if (m.isCloze()) {
                                                if (!ListenerUtil.mutListener.listen(22737)) {
                                                    // model instead
                                                    lims.add("(n.mid = " + m.getLong("id") + ")");
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(22736)) {
                                                    lims.add("(n.mid = " + m.getLong("id") + " and c.ord = " + t.getInt("ord") + ")");
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
        return TextUtils.join(" or ", lims.toArray(new String[lims.size()]));
    }

    private String _findField(String field, String val) {
        /*
         * We need two expressions to query the cards: One that will use JAVA REGEX syntax and another
         * that should use SQLITE LIKE clause syntax.
         */
        String sqlVal = val.replace("%", // For SQLITE, we escape all % signs
        "\\%").replace("*", // And then convert the * into non-escaped % signs
        "%");
        /*
         * The following three lines make sure that only _ and * are valid wildcards.
         * Any other characters are enclosed inside the \Q \E markers, which force
         * all meta-characters in between them to lose their special meaning
         */
        String javaVal = val.replace("_", "\\E.\\Q").replace("*", "\\E.*\\Q");
        /*
         * For the pattern, we use the javaVal expression that uses JAVA REGEX syntax
         */
        Pattern pattern = Pattern.compile("\\Q" + javaVal + "\\E", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        // find models that have that field
        Map<Long, Object[]> mods = new HashMap<>(mCol.getModels().count());
        if (!ListenerUtil.mutListener.listen(22746)) {
            {
                long _loopCounter531 = 0;
                for (JSONObject m : mCol.getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter531", ++_loopCounter531);
                    JSONArray flds = m.getJSONArray("flds");
                    if (!ListenerUtil.mutListener.listen(22745)) {
                        {
                            long _loopCounter530 = 0;
                            for (JSONObject f : flds.jsonObjectIterable()) {
                                ListenerUtil.loopListener.listen("_loopCounter530", ++_loopCounter530);
                                String fieldName = f.getString("name");
                                if (!ListenerUtil.mutListener.listen(22742)) {
                                    fieldName = Normalizer.normalize(fieldName, Normalizer.Form.NFC);
                                }
                                if (!ListenerUtil.mutListener.listen(22744)) {
                                    if (fieldName.equalsIgnoreCase(field)) {
                                        if (!ListenerUtil.mutListener.listen(22743)) {
                                            mods.put(m.getLong("id"), new Object[] { m, f.getInt("ord") });
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22747)) {
            if (mods.isEmpty()) {
                // nothing has that field
                return null;
            }
        }
        LinkedList<Long> nids = new LinkedList<>();
        try (Cursor cur = mCol.getDb().query("select id, mid, flds from notes where mid in " + Utils.ids2str(new LinkedList<>(mods.keySet())) + " and flds like ? escape '\\'", "%" + sqlVal + "%")) {
            if (!ListenerUtil.mutListener.listen(22750)) {
                {
                    long _loopCounter532 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter532", ++_loopCounter532);
                        String[] flds = Utils.splitFields(cur.getString(2));
                        int ord = (Integer) mods.get(cur.getLong(1))[1];
                        String strg = flds[ord];
                        if (!ListenerUtil.mutListener.listen(22749)) {
                            if (pattern.matcher(strg).matches()) {
                                if (!ListenerUtil.mutListener.listen(22748)) {
                                    nids.add(cur.getLong(0));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22751)) {
            if (nids.isEmpty()) {
                return "0";
            }
        }
        return "n.id in " + Utils.ids2str(nids);
    }

    private String _findDupes(String val) {
        // caller must call stripHTMLMedia on passed val
        String[] split = val.split(",", 1);
        if (!ListenerUtil.mutListener.listen(22757)) {
            if ((ListenerUtil.mutListener.listen(22756) ? (split.length >= 2) : (ListenerUtil.mutListener.listen(22755) ? (split.length <= 2) : (ListenerUtil.mutListener.listen(22754) ? (split.length > 2) : (ListenerUtil.mutListener.listen(22753) ? (split.length < 2) : (ListenerUtil.mutListener.listen(22752) ? (split.length == 2) : (split.length != 2))))))) {
                return null;
            }
        }
        String mid = split[0];
        if (!ListenerUtil.mutListener.listen(22758)) {
            val = split[1];
        }
        String csum = Long.toString(Utils.fieldChecksumWithoutHtmlMedia(val));
        List<Long> nids = new ArrayList<>();
        try (Cursor cur = mCol.getDb().query("select id, flds from notes where mid=? and csum=?", mid, csum)) {
            long nid = cur.getLong(0);
            String flds = cur.getString(1);
            if (!ListenerUtil.mutListener.listen(22760)) {
                if (Utils.stripHTMLMedia(Utils.splitFields(flds)[0]).equals(val)) {
                    if (!ListenerUtil.mutListener.listen(22759)) {
                        nids.add(nid);
                    }
                }
            }
        }
        return "n.id in " + Utils.ids2str(nids);
    }

    /**
     * Find and replace fields in a note
     *
     * @param col The collection to search into.
     * @param nids The cards to be searched for.
     * @param src The original text to find.
     * @param dst The text to change to.
     * @return Number of notes with fields that were updated.
     */
    public static int findReplace(Collection col, List<Long> nids, String src, String dst) {
        return findReplace(col, nids, src, dst, false, null, true);
    }

    /**
     * Find and replace fields in a note
     *
     * @param col The collection to search into.
     * @param nids The cards to be searched for.
     * @param src The original text to find.
     * @param dst The text to change to.
     * @param regex If true, the src is treated as a regex. Default = false.
     * @return Number of notes with fields that were updated.
     */
    public static int findReplace(Collection col, List<Long> nids, String src, String dst, boolean regex) {
        return findReplace(col, nids, src, dst, regex, null, true);
    }

    /**
     * Find and replace fields in a note
     *
     * @param col The collection to search into.
     * @param nids The cards to be searched for.
     * @param src The original text to find.
     * @param dst The text to change to.
     * @param field Limit the search to specific field. If null, it searches all fields.
     * @return Number of notes with fields that were updated.
     */
    public static int findReplace(Collection col, List<Long> nids, String src, String dst, String field) {
        return findReplace(col, nids, src, dst, false, field, true);
    }

    /**
     * Find and replace fields in a note
     *
     * @param col The collection to search into.
     * @param nids The cards to be searched for.
     * @param src The original text to find.
     * @param dst The text to change to.
     * @param isRegex If true, the src is treated as a regex. Default = false.
     * @param field Limit the search to specific field. If null, it searches all fields.
     * @param fold If true the search is case-insensitive. Default = true.
     * @return Number of notes with fields that were updated.
     */
    public static int findReplace(Collection col, List<Long> nids, String src, String dst, boolean isRegex, String field, boolean fold) {
        Map<Long, Integer> mmap = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(22766)) {
            if (field != null) {
                if (!ListenerUtil.mutListener.listen(22764)) {
                    {
                        long _loopCounter534 = 0;
                        for (JSONObject m : col.getModels().all()) {
                            ListenerUtil.loopListener.listen("_loopCounter534", ++_loopCounter534);
                            JSONArray flds = m.getJSONArray("flds");
                            if (!ListenerUtil.mutListener.listen(22763)) {
                                {
                                    long _loopCounter533 = 0;
                                    for (JSONObject f : flds.jsonObjectIterable()) {
                                        ListenerUtil.loopListener.listen("_loopCounter533", ++_loopCounter533);
                                        if (!ListenerUtil.mutListener.listen(22762)) {
                                            if (f.getString("name").equalsIgnoreCase(field)) {
                                                if (!ListenerUtil.mutListener.listen(22761)) {
                                                    mmap.put(m.getLong("id"), f.getInt("ord"));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22765)) {
                    if (mmap.isEmpty()) {
                        return 0;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22769)) {
            // find and gather replacements
            if (!isRegex) {
                if (!ListenerUtil.mutListener.listen(22767)) {
                    src = Pattern.quote(src);
                }
                if (!ListenerUtil.mutListener.listen(22768)) {
                    dst = dst.replace("\\", "\\\\");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22771)) {
            if (fold) {
                if (!ListenerUtil.mutListener.listen(22770)) {
                    src = "(?i)" + src;
                }
            }
        }
        Pattern regex = Pattern.compile(src);
        ArrayList<Object[]> d = new ArrayList<>(nids.size());
        String snids = Utils.ids2str(nids);
        Map<Long, java.util.Collection<Long>> midToNid = new HashMap<>(col.getModels().count());
        try (Cursor cur = col.getDb().query("select id, mid, flds from notes where id in " + snids)) {
            if (!ListenerUtil.mutListener.listen(22788)) {
                {
                    long _loopCounter536 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter536", ++_loopCounter536);
                        long mid = cur.getLong(1);
                        String flds = cur.getString(2);
                        String origFlds = flds;
                        // does it match?
                        String[] sflds = Utils.splitFields(flds);
                        if (!ListenerUtil.mutListener.listen(22781)) {
                            if (field != null) {
                                if (!ListenerUtil.mutListener.listen(22779)) {
                                    if (!mmap.containsKey(mid)) {
                                        // note doesn't have that field
                                        continue;
                                    }
                                }
                                int ord = mmap.get(mid);
                                if (!ListenerUtil.mutListener.listen(22780)) {
                                    sflds[ord] = regex.matcher(sflds[ord]).replaceAll(dst);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(22778)) {
                                    {
                                        long _loopCounter535 = 0;
                                        for (int i = 0; (ListenerUtil.mutListener.listen(22777) ? (i >= sflds.length) : (ListenerUtil.mutListener.listen(22776) ? (i <= sflds.length) : (ListenerUtil.mutListener.listen(22775) ? (i > sflds.length) : (ListenerUtil.mutListener.listen(22774) ? (i != sflds.length) : (ListenerUtil.mutListener.listen(22773) ? (i == sflds.length) : (i < sflds.length)))))); ++i) {
                                            ListenerUtil.loopListener.listen("_loopCounter535", ++_loopCounter535);
                                            if (!ListenerUtil.mutListener.listen(22772)) {
                                                sflds[i] = regex.matcher(sflds[i]).replaceAll(dst);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22782)) {
                            flds = Utils.joinFields(sflds);
                        }
                        if (!ListenerUtil.mutListener.listen(22787)) {
                            if (!flds.equals(origFlds)) {
                                long nid = cur.getLong(0);
                                if (!ListenerUtil.mutListener.listen(22784)) {
                                    if (!midToNid.containsKey(mid)) {
                                        if (!ListenerUtil.mutListener.listen(22783)) {
                                            midToNid.put(mid, new ArrayList<>());
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(22785)) {
                                    midToNid.get(mid).add(nid);
                                }
                                if (!ListenerUtil.mutListener.listen(22786)) {
                                    // order based on query below
                                    d.add(new Object[] { flds, col.getTime().intTime(), col.usn(), nid });
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22789)) {
            if (d.isEmpty()) {
                return 0;
            }
        }
        if (!ListenerUtil.mutListener.listen(22790)) {
            // replace
            col.getDb().executeMany("update notes set flds=?,mod=?,usn=? where id=?", d);
        }
        if (!ListenerUtil.mutListener.listen(22793)) {
            {
                long _loopCounter537 = 0;
                for (Map.Entry<Long, java.util.Collection<Long>> entry : midToNid.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter537", ++_loopCounter537);
                    long mid = entry.getKey();
                    java.util.Collection<Long> nids_ = entry.getValue();
                    if (!ListenerUtil.mutListener.listen(22791)) {
                        col.updateFieldCache(nids_);
                    }
                    if (!ListenerUtil.mutListener.listen(22792)) {
                        col.genCards(nids_, mid);
                    }
                }
            }
        }
        return d.size();
    }

    public static Integer ordForMid(Collection col, Map<Long, Integer> fields, long mid, String fieldName) {
        if (!ListenerUtil.mutListener.listen(22803)) {
            if (!fields.containsKey(mid)) {
                JSONObject model = col.getModels().get(mid);
                JSONArray flds = model.getJSONArray("flds");
                if (!ListenerUtil.mutListener.listen(22801)) {
                    {
                        long _loopCounter538 = 0;
                        for (int c = 0; (ListenerUtil.mutListener.listen(22800) ? (c >= flds.length()) : (ListenerUtil.mutListener.listen(22799) ? (c <= flds.length()) : (ListenerUtil.mutListener.listen(22798) ? (c > flds.length()) : (ListenerUtil.mutListener.listen(22797) ? (c != flds.length()) : (ListenerUtil.mutListener.listen(22796) ? (c == flds.length()) : (c < flds.length())))))); c++) {
                            ListenerUtil.loopListener.listen("_loopCounter538", ++_loopCounter538);
                            JSONObject f = flds.getJSONObject(c);
                            if (!ListenerUtil.mutListener.listen(22795)) {
                                if (f.getString("name").equalsIgnoreCase(fieldName)) {
                                    if (!ListenerUtil.mutListener.listen(22794)) {
                                        fields.put(mid, c);
                                    }
                                    return c;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22802)) {
                    fields.put(mid, null);
                }
            }
        }
        return fields.get(mid);
    }

    public static List<Pair<String, List<Long>>> findDupes(Collection col, String fieldName) {
        return findDupes(col, fieldName, "");
    }

    /**
     * @param col       the collection
     * @param fieldName a name of a field of some note type(s)
     * @param search A search query, as in the browser
     * @return List of Pair("dupestr", List[nids]), with nids note satisfying the search query, and having a field fieldName with value duepstr. Each list has at least two elements.
     */
    public static List<Pair<String, List<Long>>> findDupes(Collection col, String fieldName, String search) {
        if (!ListenerUtil.mutListener.listen(22805)) {
            // limit search to notes with applicable field name
            if (!TextUtils.isEmpty(search)) {
                if (!ListenerUtil.mutListener.listen(22804)) {
                    search = "(" + search + ") ";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22806)) {
            search += "'" + fieldName + ":*'";
        }
        // go through notes
        List<Long> nids = col.findNotes(search);
        Map<String, List<Long>> vals = new HashMap<>(nids.size());
        List<Pair<String, List<Long>>> dupes = new ArrayList<>(nids.size());
        Map<Long, Integer> fields = new HashMap<>();
        try (Cursor cur = col.getDb().query("select id, mid, flds from notes where id in " + Utils.ids2str(col.findNotes(search)))) {
            if (!ListenerUtil.mutListener.listen(22820)) {
                {
                    long _loopCounter539 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter539", ++_loopCounter539);
                        long nid = cur.getLong(0);
                        long mid = cur.getLong(1);
                        String[] flds = Utils.splitFields(cur.getString(2));
                        Integer ord = ordForMid(col, fields, mid, fieldName);
                        if (!ListenerUtil.mutListener.listen(22807)) {
                            if (ord == null) {
                                continue;
                            }
                        }
                        String val = flds[ord];
                        if (!ListenerUtil.mutListener.listen(22808)) {
                            val = Utils.stripHTMLMedia(val);
                        }
                        if (!ListenerUtil.mutListener.listen(22809)) {
                            // empty does not count as duplicate
                            if (TextUtils.isEmpty(val)) {
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22811)) {
                            if (!vals.containsKey(val)) {
                                if (!ListenerUtil.mutListener.listen(22810)) {
                                    vals.put(val, new ArrayList<>());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22812)) {
                            vals.get(val).add(nid);
                        }
                        if (!ListenerUtil.mutListener.listen(22819)) {
                            if ((ListenerUtil.mutListener.listen(22817) ? (vals.get(val).size() >= 2) : (ListenerUtil.mutListener.listen(22816) ? (vals.get(val).size() <= 2) : (ListenerUtil.mutListener.listen(22815) ? (vals.get(val).size() > 2) : (ListenerUtil.mutListener.listen(22814) ? (vals.get(val).size() < 2) : (ListenerUtil.mutListener.listen(22813) ? (vals.get(val).size() != 2) : (vals.get(val).size() == 2))))))) {
                                if (!ListenerUtil.mutListener.listen(22818)) {
                                    dupes.add(new Pair<>(val, vals.get(val)));
                                }
                            }
                        }
                    }
                }
            }
        }
        return dupes;
    }
}

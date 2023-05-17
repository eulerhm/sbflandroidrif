/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.

 This file incorporates work covered by the following copyright and permission notice.
 Please see the file LICENSE in this directory for full details

 Ported from https://github.com/python/cpython/blob/a74eea238f5baba15797e2e8b570d153bc8690a7/Lib/csv.py#L159

 */
package com.ichi2.libanki.importer.python;

import android.os.Build;
import com.ichi2.libanki.importer.CsvException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Regex group(str)
@RequiresApi(Build.VERSION_CODES.O)
public class CsvSniffer {

    private final char[] preferred;

    public CsvSniffer() {
        // in case there is more than one possible delimiter
        preferred = new char[] { ',', '\t', ';', ' ', ':' };
    }

    public CsvDialect sniff(String sample, char[] delimiters) {
        List<Character> delimiterList = toList(delimiters);
        GuessQuoteAndDelimiter result = _guess_quote_and_delimiter(sample, delimiterList);
        char quotechar = result.quotechar;
        boolean doublequote = result.doublequote;
        char delimiter = result.delimiter;
        boolean skipinitialspace = result.skipinitialspace;
        if (!ListenerUtil.mutListener.listen(13499)) {
            if (delimiter == '\0') {
                Guess g = _guess_delimiter(sample, delimiterList);
                if (!ListenerUtil.mutListener.listen(13497)) {
                    delimiter = g.delimiter;
                }
                if (!ListenerUtil.mutListener.listen(13498)) {
                    skipinitialspace = g.skipinitialspace;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13500)) {
            if (delimiter == '\0') {
                throw new CsvException("Could not determine delimiter");
            }
        }
        CsvDialect dialect = new CsvDialect("sniffed");
        if (!ListenerUtil.mutListener.listen(13501)) {
            dialect.mDoublequote = doublequote;
        }
        if (!ListenerUtil.mutListener.listen(13502)) {
            dialect.mDelimiter = delimiter;
        }
        if (!ListenerUtil.mutListener.listen(13503)) {
            // _csv.reader won't accept a quotechar of ''
            dialect.mQuotechar = quotechar == '\0' ? '"' : quotechar;
        }
        if (!ListenerUtil.mutListener.listen(13504)) {
            dialect.mSkipInitialSpace = skipinitialspace;
        }
        return dialect;
    }

    private List<Character> toList(@Nullable char[] delimiters) {
        if (!ListenerUtil.mutListener.listen(13505)) {
            if (delimiters == null) {
                return new ArrayList<>(0);
            }
        }
        ArrayList<Character> ret = new ArrayList<>(delimiters.length);
        if (!ListenerUtil.mutListener.listen(13507)) {
            {
                long _loopCounter241 = 0;
                for (char delimiter : delimiters) {
                    ListenerUtil.loopListener.listen("_loopCounter241", ++_loopCounter241);
                    if (!ListenerUtil.mutListener.listen(13506)) {
                        ret.add(delimiter);
                    }
                }
            }
        }
        return ret;
    }

    /**
     *  Looks for text enclosed between two identical quotes
     *  (the probable quotechar) which are preceded and followed
     *  by the same character (the probable delimiter).
     *  For example:
     *                   ,'some text',
     *  The quote with the most wins, same with the delimiter.
     *  If there is no quotechar the delimiter can't be determined
     *  this way.
     */
    private GuessQuoteAndDelimiter _guess_quote_and_delimiter(String data, List<Character> delimiters) {
        ArrayList<String> regexes = new ArrayList<>(4);
        if (!ListenerUtil.mutListener.listen(13508)) {
            // ,".*?",
            regexes.add("(?<delim>[^\\w\\n\"'])(?<space> ?)(?<quote>[\"']).*?\\k<quote>\\k<delim>");
        }
        if (!ListenerUtil.mutListener.listen(13509)) {
            // ".*?",
            regexes.add("(?:^|\\n)(?<quote>[\"']).*?\\k<quote>(?<delim>[^\\w\\n\"'])(?<space> ?)");
        }
        if (!ListenerUtil.mutListener.listen(13510)) {
            // ,".*?"
            regexes.add("(?<delim>[^\\w\\n\"'])(?<space> ?)(?<quote>[\"']).*?\\k<quote>(?:$|\\n)");
        }
        if (!ListenerUtil.mutListener.listen(13511)) {
            // ".*?" (no delim, no space)
            regexes.add("(?:^|\\n)(?<quote>[\"']).*?\\k<quote>(?:$|\\n)");
        }
        List<Group> matches = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(13518)) {
            {
                long _loopCounter243 = 0;
                for (String regex : regexes) {
                    ListenerUtil.loopListener.listen("_loopCounter243", ++_loopCounter243);
                    Pattern p = Pattern.compile(regex, Pattern.MULTILINE | Pattern.DOTALL);
                    Matcher m = p.matcher(data);
                    if (!ListenerUtil.mutListener.listen(13516)) {
                        {
                            long _loopCounter242 = 0;
                            while (m.find()) {
                                ListenerUtil.loopListener.listen("_loopCounter242", ++_loopCounter242);
                                Group g = new Group();
                                if (!ListenerUtil.mutListener.listen(13512)) {
                                    g.delim = getCharOrNull(m, "delim");
                                }
                                if (!ListenerUtil.mutListener.listen(13513)) {
                                    g.quote = getCharOrNull(m, "quote");
                                }
                                if (!ListenerUtil.mutListener.listen(13514)) {
                                    g.space = m.group("space");
                                }
                                if (!ListenerUtil.mutListener.listen(13515)) {
                                    matches.add(g);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13517)) {
                        if (!matches.isEmpty()) {
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13519)) {
            if (matches.isEmpty()) {
                return new GuessQuoteAndDelimiter('\0', false, '\0', false);
            }
        }
        Map<Character, Integer> quotes = new HashMap<>(matches.size());
        Map<Character, Integer> delims = new HashMap<>();
        int spaces = 0;
        if (!ListenerUtil.mutListener.listen(13544)) {
            {
                long _loopCounter244 = 0;
                for (Group m : matches) {
                    ListenerUtil.loopListener.listen("_loopCounter244", ++_loopCounter244);
                    char key = m.quote;
                    if (!ListenerUtil.mutListener.listen(13525)) {
                        if (key != '\0') {
                            if (!ListenerUtil.mutListener.listen(13524)) {
                                quotes.put(key, (ListenerUtil.mutListener.listen(13523) ? (quotes.getOrDefault(key, 0) % 1) : (ListenerUtil.mutListener.listen(13522) ? (quotes.getOrDefault(key, 0) / 1) : (ListenerUtil.mutListener.listen(13521) ? (quotes.getOrDefault(key, 0) * 1) : (ListenerUtil.mutListener.listen(13520) ? (quotes.getOrDefault(key, 0) - 1) : (quotes.getOrDefault(key, 0) + 1))))));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13526)) {
                        key = m.delim;
                    }
                    if (!ListenerUtil.mutListener.listen(13535)) {
                        if ((ListenerUtil.mutListener.listen(13529) ? (key != '\0' || ((ListenerUtil.mutListener.listen(13528) ? ((ListenerUtil.mutListener.listen(13527) ? (delimiters == null && delimiters.isEmpty()) : (delimiters == null || delimiters.isEmpty())) && delimiters.contains(key)) : ((ListenerUtil.mutListener.listen(13527) ? (delimiters == null && delimiters.isEmpty()) : (delimiters == null || delimiters.isEmpty())) || delimiters.contains(key))))) : (key != '\0' && ((ListenerUtil.mutListener.listen(13528) ? ((ListenerUtil.mutListener.listen(13527) ? (delimiters == null && delimiters.isEmpty()) : (delimiters == null || delimiters.isEmpty())) && delimiters.contains(key)) : ((ListenerUtil.mutListener.listen(13527) ? (delimiters == null && delimiters.isEmpty()) : (delimiters == null || delimiters.isEmpty())) || delimiters.contains(key))))))) {
                            if (!ListenerUtil.mutListener.listen(13534)) {
                                delims.put(key, (ListenerUtil.mutListener.listen(13533) ? (delims.getOrDefault(key, 0) % 1) : (ListenerUtil.mutListener.listen(13532) ? (delims.getOrDefault(key, 0) / 1) : (ListenerUtil.mutListener.listen(13531) ? (delims.getOrDefault(key, 0) * 1) : (ListenerUtil.mutListener.listen(13530) ? (delims.getOrDefault(key, 0) - 1) : (delims.getOrDefault(key, 0) + 1))))));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13543)) {
                        if ((ListenerUtil.mutListener.listen(13541) ? (m.space != null || (ListenerUtil.mutListener.listen(13540) ? (m.space.length() >= 0) : (ListenerUtil.mutListener.listen(13539) ? (m.space.length() <= 0) : (ListenerUtil.mutListener.listen(13538) ? (m.space.length() < 0) : (ListenerUtil.mutListener.listen(13537) ? (m.space.length() != 0) : (ListenerUtil.mutListener.listen(13536) ? (m.space.length() == 0) : (m.space.length() > 0))))))) : (m.space != null && (ListenerUtil.mutListener.listen(13540) ? (m.space.length() >= 0) : (ListenerUtil.mutListener.listen(13539) ? (m.space.length() <= 0) : (ListenerUtil.mutListener.listen(13538) ? (m.space.length() < 0) : (ListenerUtil.mutListener.listen(13537) ? (m.space.length() != 0) : (ListenerUtil.mutListener.listen(13536) ? (m.space.length() == 0) : (m.space.length() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(13542)) {
                                spaces += 1;
                            }
                        }
                    }
                }
            }
        }
        Character quotechar = max(quotes);
        Character delim;
        boolean skipinitialspace;
        if (!delims.isEmpty()) {
            delim = max(delims);
            skipinitialspace = (ListenerUtil.mutListener.listen(13549) ? (delims.get(delim) >= spaces) : (ListenerUtil.mutListener.listen(13548) ? (delims.get(delim) <= spaces) : (ListenerUtil.mutListener.listen(13547) ? (delims.get(delim) > spaces) : (ListenerUtil.mutListener.listen(13546) ? (delims.get(delim) < spaces) : (ListenerUtil.mutListener.listen(13545) ? (delims.get(delim) != spaces) : (delims.get(delim) == spaces))))));
            if (delim == '\n') {
                // most likely a file with a single column
                delim = '\0';
            }
        } else {
            // there is *no* delimiter, it's a single column of quoted data
            delim = '\0';
            skipinitialspace = false;
        }
        // double quoted format
        String regex = String.format("((%s)|^)\\W*%s[^%s\\n]*%s[^%s\\n]*%s\\W*((%s)|$)", delim, quotechar, delim, quotechar, delim, quotechar, delim);
        Pattern dq_regexp = Pattern.compile(regex, Pattern.MULTILINE);
        boolean doublequote = dq_regexp.matcher(data).find();
        return new GuessQuoteAndDelimiter(quotechar, doublequote, delim, skipinitialspace);
    }

    private char getCharOrNull(Matcher m, String delim) {
        String group = m.group(delim);
        if (!ListenerUtil.mutListener.listen(13556)) {
            if ((ListenerUtil.mutListener.listen(13555) ? (group == null && (ListenerUtil.mutListener.listen(13554) ? (group.length() >= 0) : (ListenerUtil.mutListener.listen(13553) ? (group.length() <= 0) : (ListenerUtil.mutListener.listen(13552) ? (group.length() > 0) : (ListenerUtil.mutListener.listen(13551) ? (group.length() < 0) : (ListenerUtil.mutListener.listen(13550) ? (group.length() != 0) : (group.length() == 0))))))) : (group == null || (ListenerUtil.mutListener.listen(13554) ? (group.length() >= 0) : (ListenerUtil.mutListener.listen(13553) ? (group.length() <= 0) : (ListenerUtil.mutListener.listen(13552) ? (group.length() > 0) : (ListenerUtil.mutListener.listen(13551) ? (group.length() < 0) : (ListenerUtil.mutListener.listen(13550) ? (group.length() != 0) : (group.length() == 0))))))))) {
                return '\0';
            }
        }
        return group.charAt(0);
    }

    /**
     * The delimiter /should/ occur the same number of times on
     * each row. However, due to malformed data, it may not. We don't want
     * an all or nothing approach, so we allow for small variations in this
     * number.
     *   1) build a table of the frequency of each character on every line.
     *   2) build a table of frequencies of this frequency (meta-frequency?),
     *      e.g.  'x occurred 5 times in 10 rows, 6 times in 1000 rows,
     *      7 times in 2 rows'
     *   3) use the mode of the meta-frequency to determine the /expected/
     *      frequency for that character
     *   4) find out how often the character actually meets that goal
     *   5) the character that best meets its goal is the delimiter
     * For performance reasons, the data is evaluated in chunks, so it can
     * try and evaluate the smallest portion of the data possible, evaluating
     * additional chunks as necessary.
     */
    private Guess _guess_delimiter(String input, List<Character> delimiters) {
        // remove falsey values
        String[] samples = input.split("\n");
        List<String> data = new ArrayList<>(samples.length);
        if (!ListenerUtil.mutListener.listen(13565)) {
            {
                long _loopCounter245 = 0;
                for (String s : samples) {
                    ListenerUtil.loopListener.listen("_loopCounter245", ++_loopCounter245);
                    if (!ListenerUtil.mutListener.listen(13563)) {
                        if ((ListenerUtil.mutListener.listen(13562) ? (s == null && (ListenerUtil.mutListener.listen(13561) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(13560) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(13559) ? (s.length() > 0) : (ListenerUtil.mutListener.listen(13558) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(13557) ? (s.length() != 0) : (s.length() == 0))))))) : (s == null || (ListenerUtil.mutListener.listen(13561) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(13560) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(13559) ? (s.length() > 0) : (ListenerUtil.mutListener.listen(13558) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(13557) ? (s.length() != 0) : (s.length() == 0))))))))) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13564)) {
                        data.add(s);
                    }
                }
            }
        }
        // 7-bit ASCII
        char[] ascii = new char[128];
        if (!ListenerUtil.mutListener.listen(13572)) {
            {
                long _loopCounter246 = 0;
                for (char i = 0; (ListenerUtil.mutListener.listen(13571) ? (i >= 128) : (ListenerUtil.mutListener.listen(13570) ? (i <= 128) : (ListenerUtil.mutListener.listen(13569) ? (i > 128) : (ListenerUtil.mutListener.listen(13568) ? (i != 128) : (ListenerUtil.mutListener.listen(13567) ? (i == 128) : (i < 128)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter246", ++_loopCounter246);
                    if (!ListenerUtil.mutListener.listen(13566)) {
                        ascii[i] = i;
                    }
                }
            }
        }
        // build frequency tables
        int chunkLength = Math.min(10, data.size());
        int iteration = 0;
        Map<Character, Map<Integer, Integer>> charFrequency = new HashMap<>();
        Map<Character, Tuple> modes = new HashMap<>();
        Map<Character, Tuple> delims = new HashMap<>();
        int start = 0;
        int end = chunkLength;
        if (!ListenerUtil.mutListener.listen(13667)) {
            {
                long _loopCounter253 = 0;
                while ((ListenerUtil.mutListener.listen(13666) ? (start >= data.size()) : (ListenerUtil.mutListener.listen(13665) ? (start <= data.size()) : (ListenerUtil.mutListener.listen(13664) ? (start > data.size()) : (ListenerUtil.mutListener.listen(13663) ? (start != data.size()) : (ListenerUtil.mutListener.listen(13662) ? (start == data.size()) : (start < data.size()))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter253", ++_loopCounter253);
                    if (!ListenerUtil.mutListener.listen(13573)) {
                        iteration++;
                    }
                    if (!ListenerUtil.mutListener.listen(13581)) {
                        {
                            long _loopCounter248 = 0;
                            for (String line : data.subList(start, end)) {
                                ListenerUtil.loopListener.listen("_loopCounter248", ++_loopCounter248);
                                if (!ListenerUtil.mutListener.listen(13580)) {
                                    {
                                        long _loopCounter247 = 0;
                                        for (char c : ascii) {
                                            ListenerUtil.loopListener.listen("_loopCounter247", ++_loopCounter247);
                                            Map<Integer, Integer> metaFrequency = charFrequency.getOrDefault(c, new HashMap<>());
                                            // must count even if frequency is 0
                                            int freq = countInString(line, c);
                                            if (!ListenerUtil.mutListener.listen(13578)) {
                                                // value is the mode
                                                metaFrequency.put(freq, (ListenerUtil.mutListener.listen(13577) ? (metaFrequency.getOrDefault(freq, 0) % 1) : (ListenerUtil.mutListener.listen(13576) ? (metaFrequency.getOrDefault(freq, 0) / 1) : (ListenerUtil.mutListener.listen(13575) ? (metaFrequency.getOrDefault(freq, 0) * 1) : (ListenerUtil.mutListener.listen(13574) ? (metaFrequency.getOrDefault(freq, 0) - 1) : (metaFrequency.getOrDefault(freq, 0) + 1))))));
                                            }
                                            if (!ListenerUtil.mutListener.listen(13579)) {
                                                charFrequency.put(c, metaFrequency);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13610)) {
                        {
                            long _loopCounter250 = 0;
                            for (Map.Entry<Character, Map<Integer, Integer>> e : charFrequency.entrySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter250", ++_loopCounter250);
                                char c = e.getKey();
                                Set<Map.Entry<Integer, Integer>> bareList = e.getValue().entrySet();
                                List<Tuple> items = new ArrayList<>(bareList.size());
                                if (!ListenerUtil.mutListener.listen(13583)) {
                                    {
                                        long _loopCounter249 = 0;
                                        for (Map.Entry<Integer, Integer> entry : bareList) {
                                            ListenerUtil.loopListener.listen("_loopCounter249", ++_loopCounter249);
                                            if (!ListenerUtil.mutListener.listen(13582)) {
                                                items.add(new Tuple(entry));
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13595)) {
                                    if ((ListenerUtil.mutListener.listen(13594) ? ((ListenerUtil.mutListener.listen(13588) ? (items.size() >= 1) : (ListenerUtil.mutListener.listen(13587) ? (items.size() <= 1) : (ListenerUtil.mutListener.listen(13586) ? (items.size() > 1) : (ListenerUtil.mutListener.listen(13585) ? (items.size() < 1) : (ListenerUtil.mutListener.listen(13584) ? (items.size() != 1) : (items.size() == 1)))))) || (ListenerUtil.mutListener.listen(13593) ? (items.get(0).second >= 0) : (ListenerUtil.mutListener.listen(13592) ? (items.get(0).second <= 0) : (ListenerUtil.mutListener.listen(13591) ? (items.get(0).second > 0) : (ListenerUtil.mutListener.listen(13590) ? (items.get(0).second < 0) : (ListenerUtil.mutListener.listen(13589) ? (items.get(0).second != 0) : (items.get(0).second == 0))))))) : ((ListenerUtil.mutListener.listen(13588) ? (items.size() >= 1) : (ListenerUtil.mutListener.listen(13587) ? (items.size() <= 1) : (ListenerUtil.mutListener.listen(13586) ? (items.size() > 1) : (ListenerUtil.mutListener.listen(13585) ? (items.size() < 1) : (ListenerUtil.mutListener.listen(13584) ? (items.size() != 1) : (items.size() == 1)))))) && (ListenerUtil.mutListener.listen(13593) ? (items.get(0).second >= 0) : (ListenerUtil.mutListener.listen(13592) ? (items.get(0).second <= 0) : (ListenerUtil.mutListener.listen(13591) ? (items.get(0).second > 0) : (ListenerUtil.mutListener.listen(13590) ? (items.get(0).second < 0) : (ListenerUtil.mutListener.listen(13589) ? (items.get(0).second != 0) : (items.get(0).second == 0))))))))) {
                                        continue;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13609)) {
                                    // get the mode of the frequencies
                                    if ((ListenerUtil.mutListener.listen(13600) ? (items.size() >= 1) : (ListenerUtil.mutListener.listen(13599) ? (items.size() <= 1) : (ListenerUtil.mutListener.listen(13598) ? (items.size() < 1) : (ListenerUtil.mutListener.listen(13597) ? (items.size() != 1) : (ListenerUtil.mutListener.listen(13596) ? (items.size() == 1) : (items.size() > 1))))))) {
                                        if (!ListenerUtil.mutListener.listen(13602)) {
                                            modes.put(c, maxSecond(items));
                                        }
                                        // other frequencies
                                        Tuple toRemove = modes.get(c);
                                        if (!ListenerUtil.mutListener.listen(13603)) {
                                            items.remove(toRemove);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13608)) {
                                            modes.put(c, new Tuple(toRemove.first, (ListenerUtil.mutListener.listen(13607) ? (toRemove.second % sumSecond(items)) : (ListenerUtil.mutListener.listen(13606) ? (toRemove.second / sumSecond(items)) : (ListenerUtil.mutListener.listen(13605) ? (toRemove.second * sumSecond(items)) : (ListenerUtil.mutListener.listen(13604) ? (toRemove.second + sumSecond(items)) : (toRemove.second - sumSecond(items))))))));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(13601)) {
                                            modes.put(c, items.get(0));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // build a list of possible delimiters
                    Set<Map.Entry<Character, Tuple>> modeList = modes.entrySet();
                    float total = Math.min((ListenerUtil.mutListener.listen(13614) ? (chunkLength % iteration) : (ListenerUtil.mutListener.listen(13613) ? (chunkLength / iteration) : (ListenerUtil.mutListener.listen(13612) ? (chunkLength - iteration) : (ListenerUtil.mutListener.listen(13611) ? (chunkLength + iteration) : (chunkLength * iteration))))), data.size());
                    // (rows of consistent data) / (number of rows) = 100%
                    double consistency = 1.0;
                    // minimum consistency threshold
                    double threshold = 0.9;
                    if (!ListenerUtil.mutListener.listen(13653)) {
                        {
                            long _loopCounter252 = 0;
                            while ((ListenerUtil.mutListener.listen(13652) ? ((ListenerUtil.mutListener.listen(13646) ? (delims.size() >= 0) : (ListenerUtil.mutListener.listen(13645) ? (delims.size() <= 0) : (ListenerUtil.mutListener.listen(13644) ? (delims.size() > 0) : (ListenerUtil.mutListener.listen(13643) ? (delims.size() < 0) : (ListenerUtil.mutListener.listen(13642) ? (delims.size() != 0) : (delims.size() == 0)))))) || (ListenerUtil.mutListener.listen(13651) ? (consistency <= threshold) : (ListenerUtil.mutListener.listen(13650) ? (consistency > threshold) : (ListenerUtil.mutListener.listen(13649) ? (consistency < threshold) : (ListenerUtil.mutListener.listen(13648) ? (consistency != threshold) : (ListenerUtil.mutListener.listen(13647) ? (consistency == threshold) : (consistency >= threshold))))))) : ((ListenerUtil.mutListener.listen(13646) ? (delims.size() >= 0) : (ListenerUtil.mutListener.listen(13645) ? (delims.size() <= 0) : (ListenerUtil.mutListener.listen(13644) ? (delims.size() > 0) : (ListenerUtil.mutListener.listen(13643) ? (delims.size() < 0) : (ListenerUtil.mutListener.listen(13642) ? (delims.size() != 0) : (delims.size() == 0)))))) && (ListenerUtil.mutListener.listen(13651) ? (consistency <= threshold) : (ListenerUtil.mutListener.listen(13650) ? (consistency > threshold) : (ListenerUtil.mutListener.listen(13649) ? (consistency < threshold) : (ListenerUtil.mutListener.listen(13648) ? (consistency != threshold) : (ListenerUtil.mutListener.listen(13647) ? (consistency == threshold) : (consistency >= threshold))))))))) {
                                ListenerUtil.loopListener.listen("_loopCounter252", ++_loopCounter252);
                                if (!ListenerUtil.mutListener.listen(13640)) {
                                    {
                                        long _loopCounter251 = 0;
                                        for (Map.Entry<Character, Tuple> entry : modeList) {
                                            ListenerUtil.loopListener.listen("_loopCounter251", ++_loopCounter251);
                                            Tuple value = entry.getValue();
                                            if (!ListenerUtil.mutListener.listen(13639)) {
                                                if ((ListenerUtil.mutListener.listen(13625) ? ((ListenerUtil.mutListener.listen(13619) ? (value.first >= 0) : (ListenerUtil.mutListener.listen(13618) ? (value.first <= 0) : (ListenerUtil.mutListener.listen(13617) ? (value.first < 0) : (ListenerUtil.mutListener.listen(13616) ? (value.first != 0) : (ListenerUtil.mutListener.listen(13615) ? (value.first == 0) : (value.first > 0)))))) || (ListenerUtil.mutListener.listen(13624) ? (value.second >= 0) : (ListenerUtil.mutListener.listen(13623) ? (value.second <= 0) : (ListenerUtil.mutListener.listen(13622) ? (value.second < 0) : (ListenerUtil.mutListener.listen(13621) ? (value.second != 0) : (ListenerUtil.mutListener.listen(13620) ? (value.second == 0) : (value.second > 0))))))) : ((ListenerUtil.mutListener.listen(13619) ? (value.first >= 0) : (ListenerUtil.mutListener.listen(13618) ? (value.first <= 0) : (ListenerUtil.mutListener.listen(13617) ? (value.first < 0) : (ListenerUtil.mutListener.listen(13616) ? (value.first != 0) : (ListenerUtil.mutListener.listen(13615) ? (value.first == 0) : (value.first > 0)))))) && (ListenerUtil.mutListener.listen(13624) ? (value.second >= 0) : (ListenerUtil.mutListener.listen(13623) ? (value.second <= 0) : (ListenerUtil.mutListener.listen(13622) ? (value.second < 0) : (ListenerUtil.mutListener.listen(13621) ? (value.second != 0) : (ListenerUtil.mutListener.listen(13620) ? (value.second == 0) : (value.second > 0))))))))) {
                                                    if (!ListenerUtil.mutListener.listen(13638)) {
                                                        if ((ListenerUtil.mutListener.listen(13636) ? ((ListenerUtil.mutListener.listen(13634) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) <= consistency) : (ListenerUtil.mutListener.listen(13633) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) > consistency) : (ListenerUtil.mutListener.listen(13632) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) < consistency) : (ListenerUtil.mutListener.listen(13631) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) != consistency) : (ListenerUtil.mutListener.listen(13630) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) == consistency) : (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) >= consistency)))))) || ((ListenerUtil.mutListener.listen(13635) ? (delimiters == null && delimiters.contains(entry.getKey())) : (delimiters == null || delimiters.contains(entry.getKey()))))) : ((ListenerUtil.mutListener.listen(13634) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) <= consistency) : (ListenerUtil.mutListener.listen(13633) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) > consistency) : (ListenerUtil.mutListener.listen(13632) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) < consistency) : (ListenerUtil.mutListener.listen(13631) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) != consistency) : (ListenerUtil.mutListener.listen(13630) ? (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) == consistency) : (((ListenerUtil.mutListener.listen(13629) ? ((double) value.second % total) : (ListenerUtil.mutListener.listen(13628) ? ((double) value.second * total) : (ListenerUtil.mutListener.listen(13627) ? ((double) value.second - total) : (ListenerUtil.mutListener.listen(13626) ? ((double) value.second + total) : ((double) value.second / total)))))) >= consistency)))))) && ((ListenerUtil.mutListener.listen(13635) ? (delimiters == null && delimiters.contains(entry.getKey())) : (delimiters == null || delimiters.contains(entry.getKey()))))))) {
                                                            if (!ListenerUtil.mutListener.listen(13637)) {
                                                                delims.put(entry.getKey(), value);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13641)) {
                                    consistency -= 0.01;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13659)) {
                        if ((ListenerUtil.mutListener.listen(13658) ? (delims.size() >= 1) : (ListenerUtil.mutListener.listen(13657) ? (delims.size() <= 1) : (ListenerUtil.mutListener.listen(13656) ? (delims.size() > 1) : (ListenerUtil.mutListener.listen(13655) ? (delims.size() < 1) : (ListenerUtil.mutListener.listen(13654) ? (delims.size() != 1) : (delims.size() == 1))))))) {
                            Character delim = new ArrayList<>(delims.keySet()).get(0);
                            boolean skipinitialspace = countInString(data.get(0), delim) == countInString(data.get(0), delim + " ");
                            return new Guess(delim, skipinitialspace);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13660)) {
                        // analyze another chunkLength lines
                        start = end;
                    }
                    if (!ListenerUtil.mutListener.listen(13661)) {
                        end += chunkLength;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13668)) {
            if (delims.isEmpty()) {
                return new Guess('\0', false);
            }
        }
        if (!ListenerUtil.mutListener.listen(13681)) {
            // if there's more than one, fall back to a 'preferred' list
            if ((ListenerUtil.mutListener.listen(13673) ? (delims.size() >= 1) : (ListenerUtil.mutListener.listen(13672) ? (delims.size() <= 1) : (ListenerUtil.mutListener.listen(13671) ? (delims.size() < 1) : (ListenerUtil.mutListener.listen(13670) ? (delims.size() != 1) : (ListenerUtil.mutListener.listen(13669) ? (delims.size() == 1) : (delims.size() > 1))))))) {
                if (!ListenerUtil.mutListener.listen(13680)) {
                    {
                        long _loopCounter254 = 0;
                        for (char d : preferred) {
                            ListenerUtil.loopListener.listen("_loopCounter254", ++_loopCounter254);
                            if (!ListenerUtil.mutListener.listen(13679)) {
                                if (delims.containsKey(d)) {
                                    boolean skipinitialspace = (ListenerUtil.mutListener.listen(13678) ? (countInString(data.get(0), d) >= countInString(data.get(0), d + " ")) : (ListenerUtil.mutListener.listen(13677) ? (countInString(data.get(0), d) <= countInString(data.get(0), d + " ")) : (ListenerUtil.mutListener.listen(13676) ? (countInString(data.get(0), d) > countInString(data.get(0), d + " ")) : (ListenerUtil.mutListener.listen(13675) ? (countInString(data.get(0), d) < countInString(data.get(0), d + " ")) : (ListenerUtil.mutListener.listen(13674) ? (countInString(data.get(0), d) != countInString(data.get(0), d + " ")) : (countInString(data.get(0), d) == countInString(data.get(0), d + " ")))))));
                                    return new Guess(d, skipinitialspace);
                                }
                            }
                        }
                    }
                }
            }
        }
        // dominates(?)
        ArrayList<Map.Entry<Tuple, Character>> items = new ArrayList<>(delims.size());
        if (!ListenerUtil.mutListener.listen(13683)) {
            {
                long _loopCounter255 = 0;
                for (Map.Entry<Character, Tuple> i : delims.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter255", ++_loopCounter255);
                    if (!ListenerUtil.mutListener.listen(13682)) {
                        items.add(new AbstractMap.SimpleEntry<>(i.getValue(), i.getKey()));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13684)) {
            items.sort((o1, o2) -> {
                int compare = Integer.compare(o1.getKey().first, o2.getKey().first);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(o1.getKey().second, o2.getKey().second);
            });
        }
        char delim = items.get((ListenerUtil.mutListener.listen(13688) ? (items.size() % 1) : (ListenerUtil.mutListener.listen(13687) ? (items.size() / 1) : (ListenerUtil.mutListener.listen(13686) ? (items.size() * 1) : (ListenerUtil.mutListener.listen(13685) ? (items.size() + 1) : (items.size() - 1)))))).getValue();
        boolean skipinitialspace = (ListenerUtil.mutListener.listen(13693) ? (countInString(data.get(0), delim) >= countInString(data.get(0), delim + " ")) : (ListenerUtil.mutListener.listen(13692) ? (countInString(data.get(0), delim) <= countInString(data.get(0), delim + " ")) : (ListenerUtil.mutListener.listen(13691) ? (countInString(data.get(0), delim) > countInString(data.get(0), delim + " ")) : (ListenerUtil.mutListener.listen(13690) ? (countInString(data.get(0), delim) < countInString(data.get(0), delim + " ")) : (ListenerUtil.mutListener.listen(13689) ? (countInString(data.get(0), delim) != countInString(data.get(0), delim + " ")) : (countInString(data.get(0), delim) == countInString(data.get(0), delim + " ")))))));
        return new Guess(delim, skipinitialspace);
    }

    private int sumSecond(List<Tuple> items) {
        int total = 0;
        if (!ListenerUtil.mutListener.listen(13695)) {
            {
                long _loopCounter256 = 0;
                for (Tuple item : items) {
                    ListenerUtil.loopListener.listen("_loopCounter256", ++_loopCounter256);
                    if (!ListenerUtil.mutListener.listen(13694)) {
                        total += item.second;
                    }
                }
            }
        }
        return total;
    }

    private <T> T max(Map<T, Integer> histogram) {
        T max = null;
        int maximum = 0;
        if (!ListenerUtil.mutListener.listen(13704)) {
            {
                long _loopCounter257 = 0;
                for (Map.Entry<T, Integer> entry : histogram.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter257", ++_loopCounter257);
                    if (!ListenerUtil.mutListener.listen(13703)) {
                        if ((ListenerUtil.mutListener.listen(13700) ? (entry.getValue() >= maximum) : (ListenerUtil.mutListener.listen(13699) ? (entry.getValue() <= maximum) : (ListenerUtil.mutListener.listen(13698) ? (entry.getValue() < maximum) : (ListenerUtil.mutListener.listen(13697) ? (entry.getValue() != maximum) : (ListenerUtil.mutListener.listen(13696) ? (entry.getValue() == maximum) : (entry.getValue() > maximum))))))) {
                            if (!ListenerUtil.mutListener.listen(13701)) {
                                maximum = entry.getValue();
                            }
                            if (!ListenerUtil.mutListener.listen(13702)) {
                                max = entry.getKey();
                            }
                        }
                    }
                }
            }
        }
        return max;
    }

    /**
     * max(items, key = lambda x:x[1])
     */
    private Tuple maxSecond(List<Tuple> items) {
        // (1,1) - the first is picked, so use > max
        int max = 0;
        Tuple bestMax = null;
        if (!ListenerUtil.mutListener.listen(13713)) {
            {
                long _loopCounter258 = 0;
                for (Tuple item : items) {
                    ListenerUtil.loopListener.listen("_loopCounter258", ++_loopCounter258);
                    if (!ListenerUtil.mutListener.listen(13712)) {
                        if ((ListenerUtil.mutListener.listen(13709) ? (item.second >= max) : (ListenerUtil.mutListener.listen(13708) ? (item.second <= max) : (ListenerUtil.mutListener.listen(13707) ? (item.second < max) : (ListenerUtil.mutListener.listen(13706) ? (item.second != max) : (ListenerUtil.mutListener.listen(13705) ? (item.second == max) : (item.second > max))))))) {
                            if (!ListenerUtil.mutListener.listen(13710)) {
                                bestMax = item;
                            }
                            if (!ListenerUtil.mutListener.listen(13711)) {
                                max = item.second;
                            }
                        }
                    }
                }
            }
        }
        return bestMax;
    }

    private static class Tuple {

        public final int first;

        public final int second;

        public Tuple(Integer key, Integer value) {
            first = key;
            second = value;
        }

        public Tuple(Map.Entry<Integer, Integer> entry) {
            this(entry.getKey(), entry.getValue());
        }
    }

    private static int countInString(String s, char c) {
        int count = 0;
        if (!ListenerUtil.mutListener.listen(13721)) {
            {
                long _loopCounter259 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(13720) ? (i >= s.length()) : (ListenerUtil.mutListener.listen(13719) ? (i <= s.length()) : (ListenerUtil.mutListener.listen(13718) ? (i > s.length()) : (ListenerUtil.mutListener.listen(13717) ? (i != s.length()) : (ListenerUtil.mutListener.listen(13716) ? (i == s.length()) : (i < s.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter259", ++_loopCounter259);
                    if (!ListenerUtil.mutListener.listen(13715)) {
                        if (s.charAt(i) == c) {
                            if (!ListenerUtil.mutListener.listen(13714)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private static int countInString(String haystack, String needle) {
        int idx = 0;
        int count = 0;
        if (!ListenerUtil.mutListener.listen(13736)) {
            {
                long _loopCounter260 = 0;
                while ((ListenerUtil.mutListener.listen(13735) ? (idx >= -1) : (ListenerUtil.mutListener.listen(13734) ? (idx <= -1) : (ListenerUtil.mutListener.listen(13733) ? (idx > -1) : (ListenerUtil.mutListener.listen(13732) ? (idx < -1) : (ListenerUtil.mutListener.listen(13731) ? (idx == -1) : (idx != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter260", ++_loopCounter260);
                    if (!ListenerUtil.mutListener.listen(13722)) {
                        idx = haystack.indexOf(needle, idx);
                    }
                    if (!ListenerUtil.mutListener.listen(13730)) {
                        if ((ListenerUtil.mutListener.listen(13727) ? (idx >= -1) : (ListenerUtil.mutListener.listen(13726) ? (idx <= -1) : (ListenerUtil.mutListener.listen(13725) ? (idx > -1) : (ListenerUtil.mutListener.listen(13724) ? (idx < -1) : (ListenerUtil.mutListener.listen(13723) ? (idx == -1) : (idx != -1))))))) {
                            if (!ListenerUtil.mutListener.listen(13728)) {
                                count++;
                            }
                            if (!ListenerUtil.mutListener.listen(13729)) {
                                idx += needle.length();
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    protected static class GuessQuoteAndDelimiter extends Guess {

        public final char quotechar;

        public final boolean doublequote;

        public GuessQuoteAndDelimiter(char quotechar, boolean doublequote, char delimiter, boolean skipinitialspace) {
            super(delimiter, skipinitialspace);
            this.quotechar = quotechar;
            this.doublequote = doublequote;
        }
    }

    protected static class Group {

        public char quote;

        public char delim;

        public String space;
    }

    protected static class Guess {

        public final char delimiter;

        public final boolean skipinitialspace;

        public Guess(char delimiter, boolean skipinitialspace) {
            this.delimiter = delimiter;
            this.skipinitialspace = skipinitialspace;
        }
    }
}

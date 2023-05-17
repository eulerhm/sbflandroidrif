/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Editable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.LeadingMarginSpan;
import android.text.style.QuoteSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.TypefaceSpan;
import org.xml.sax.XMLReader;
import java.util.LinkedList;
import static android.graphics.Paint.Style.FILL;
import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
import static android.text.Spanned.SPAN_MARK_MARK;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * HTML Utilities
 */
public class HtmlUtils {

    private static class ReplySpan implements LeadingMarginSpan {

        private final int color = 0xffDDDDDD;

        @Override
        public int getLeadingMargin(boolean first) {
            return 18;
        }

        @Override
        public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
            final Style style = p.getStyle();
            final int color = p.getColor();
            if (!ListenerUtil.mutListener.listen(1280)) {
                p.setStyle(FILL);
            }
            if (!ListenerUtil.mutListener.listen(1281)) {
                p.setColor(this.color);
            }
            if (!ListenerUtil.mutListener.listen(1290)) {
                c.drawRect(x, top, (ListenerUtil.mutListener.listen(1289) ? (x % (ListenerUtil.mutListener.listen(1285) ? (dir % 6) : (ListenerUtil.mutListener.listen(1284) ? (dir / 6) : (ListenerUtil.mutListener.listen(1283) ? (dir - 6) : (ListenerUtil.mutListener.listen(1282) ? (dir + 6) : (dir * 6)))))) : (ListenerUtil.mutListener.listen(1288) ? (x / (ListenerUtil.mutListener.listen(1285) ? (dir % 6) : (ListenerUtil.mutListener.listen(1284) ? (dir / 6) : (ListenerUtil.mutListener.listen(1283) ? (dir - 6) : (ListenerUtil.mutListener.listen(1282) ? (dir + 6) : (dir * 6)))))) : (ListenerUtil.mutListener.listen(1287) ? (x * (ListenerUtil.mutListener.listen(1285) ? (dir % 6) : (ListenerUtil.mutListener.listen(1284) ? (dir / 6) : (ListenerUtil.mutListener.listen(1283) ? (dir - 6) : (ListenerUtil.mutListener.listen(1282) ? (dir + 6) : (dir * 6)))))) : (ListenerUtil.mutListener.listen(1286) ? (x - (ListenerUtil.mutListener.listen(1285) ? (dir % 6) : (ListenerUtil.mutListener.listen(1284) ? (dir / 6) : (ListenerUtil.mutListener.listen(1283) ? (dir - 6) : (ListenerUtil.mutListener.listen(1282) ? (dir + 6) : (dir * 6)))))) : (x + (ListenerUtil.mutListener.listen(1285) ? (dir % 6) : (ListenerUtil.mutListener.listen(1284) ? (dir / 6) : (ListenerUtil.mutListener.listen(1283) ? (dir - 6) : (ListenerUtil.mutListener.listen(1282) ? (dir + 6) : (dir * 6)))))))))), bottom, p);
            }
            if (!ListenerUtil.mutListener.listen(1291)) {
                p.setStyle(style);
            }
            if (!ListenerUtil.mutListener.listen(1292)) {
                p.setColor(color);
            }
        }
    }

    private static final String TAG_ROOT = "githubroot";

    private static final String ROOT_START = '<' + TAG_ROOT + '>';

    private static final String ROOT_END = "</" + TAG_ROOT + '>';

    private static final String TOGGLE_START = "<span class=\"email-hidden-toggle\">";

    private static final String TOGGLE_END = "</span>";

    private static final String REPLY_START = "<div class=\"email-quoted-reply\">";

    private static final String REPLY_END = "</div>";

    private static final String SIGNATURE_START = "<div class=\"email-signature-reply\">";

    private static final String SIGNATURE_END = "</div>";

    private static final String EMAIL_START = "<div class=\"email-fragment\">";

    private static final String EMAIL_END = "</div>";

    private static final String HIDDEN_REPLY_START = "<div class=\"email-hidden-reply\" style=\" display:none\">";

    private static final String HIDDEN_REPLY_END = "</div>";

    private static final String BREAK = "<br>";

    private static final String PARAGRAPH_START = "<p>";

    private static final String PARAGRAPH_END = "</p>";

    private static final String BLOCKQUOTE_START = "<blockquote>";

    private static final String BLOCKQUOTE_END = "</blockquote>";

    private static final String SPACE = "&nbsp;";

    private static final String PRE_START = "<pre>";

    private static final String PRE_END = "</pre>";

    private static final String CODE_START = "<code>";

    private static final String CODE_END = "</code>";

    private static class ListSeparator {

        private int count;

        public ListSeparator(boolean ordered) {
            if (!ListenerUtil.mutListener.listen(1293)) {
                count = ordered ? 1 : -1;
            }
        }

        public ListSeparator append(Editable output, int indentLevel) {
            if (!ListenerUtil.mutListener.listen(1294)) {
                output.append('\n');
            }
            if (!ListenerUtil.mutListener.listen(1305)) {
                {
                    long _loopCounter35 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(1304) ? (i >= (ListenerUtil.mutListener.listen(1299) ? (indentLevel % 2) : (ListenerUtil.mutListener.listen(1298) ? (indentLevel / 2) : (ListenerUtil.mutListener.listen(1297) ? (indentLevel - 2) : (ListenerUtil.mutListener.listen(1296) ? (indentLevel + 2) : (indentLevel * 2)))))) : (ListenerUtil.mutListener.listen(1303) ? (i <= (ListenerUtil.mutListener.listen(1299) ? (indentLevel % 2) : (ListenerUtil.mutListener.listen(1298) ? (indentLevel / 2) : (ListenerUtil.mutListener.listen(1297) ? (indentLevel - 2) : (ListenerUtil.mutListener.listen(1296) ? (indentLevel + 2) : (indentLevel * 2)))))) : (ListenerUtil.mutListener.listen(1302) ? (i > (ListenerUtil.mutListener.listen(1299) ? (indentLevel % 2) : (ListenerUtil.mutListener.listen(1298) ? (indentLevel / 2) : (ListenerUtil.mutListener.listen(1297) ? (indentLevel - 2) : (ListenerUtil.mutListener.listen(1296) ? (indentLevel + 2) : (indentLevel * 2)))))) : (ListenerUtil.mutListener.listen(1301) ? (i != (ListenerUtil.mutListener.listen(1299) ? (indentLevel % 2) : (ListenerUtil.mutListener.listen(1298) ? (indentLevel / 2) : (ListenerUtil.mutListener.listen(1297) ? (indentLevel - 2) : (ListenerUtil.mutListener.listen(1296) ? (indentLevel + 2) : (indentLevel * 2)))))) : (ListenerUtil.mutListener.listen(1300) ? (i == (ListenerUtil.mutListener.listen(1299) ? (indentLevel % 2) : (ListenerUtil.mutListener.listen(1298) ? (indentLevel / 2) : (ListenerUtil.mutListener.listen(1297) ? (indentLevel - 2) : (ListenerUtil.mutListener.listen(1296) ? (indentLevel + 2) : (indentLevel * 2)))))) : (i < (ListenerUtil.mutListener.listen(1299) ? (indentLevel % 2) : (ListenerUtil.mutListener.listen(1298) ? (indentLevel / 2) : (ListenerUtil.mutListener.listen(1297) ? (indentLevel - 2) : (ListenerUtil.mutListener.listen(1296) ? (indentLevel + 2) : (indentLevel * 2))))))))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter35", ++_loopCounter35);
                        if (!ListenerUtil.mutListener.listen(1295)) {
                            output.append(' ');
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1314)) {
                if ((ListenerUtil.mutListener.listen(1310) ? (count >= -1) : (ListenerUtil.mutListener.listen(1309) ? (count <= -1) : (ListenerUtil.mutListener.listen(1308) ? (count > -1) : (ListenerUtil.mutListener.listen(1307) ? (count < -1) : (ListenerUtil.mutListener.listen(1306) ? (count == -1) : (count != -1))))))) {
                    if (!ListenerUtil.mutListener.listen(1312)) {
                        output.append(Integer.toString(count)).append('.');
                    }
                    if (!ListenerUtil.mutListener.listen(1313)) {
                        count++;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(1311)) {
                        output.append('\u2022');
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1315)) {
                output.append(' ').append(' ');
            }
            return this;
        }
    }

    private static final TagHandler TAG_HANDLER = new TagHandler() {

        private static final String TAG_DEL = "del";

        private static final String TAG_UL = "ul";

        private static final String TAG_OL = "ol";

        private static final String TAG_LI = "li";

        private static final String TAG_CODE = "code";

        private static final String TAG_PRE = "pre";

        private int indentLevel;

        private final LinkedList<ListSeparator> listElements = new LinkedList<>();

        @Override
        public void handleTag(final boolean opening, final String tag, final Editable output, final XMLReader xmlReader) {
            if (!ListenerUtil.mutListener.listen(1319)) {
                if (TAG_DEL.equalsIgnoreCase(tag)) {
                    if (!ListenerUtil.mutListener.listen(1318)) {
                        if (opening) {
                            if (!ListenerUtil.mutListener.listen(1317)) {
                                startSpan(new StrikethroughSpan(), output);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1316)) {
                                endSpan(StrikethroughSpan.class, output);
                            }
                        }
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1328)) {
                if (TAG_UL.equalsIgnoreCase(tag)) {
                    if (!ListenerUtil.mutListener.listen(1324)) {
                        if (opening) {
                            if (!ListenerUtil.mutListener.listen(1322)) {
                                listElements.addFirst(new ListSeparator(false));
                            }
                            if (!ListenerUtil.mutListener.listen(1323)) {
                                indentLevel++;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1320)) {
                                listElements.removeFirst();
                            }
                            if (!ListenerUtil.mutListener.listen(1321)) {
                                indentLevel--;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1327)) {
                        if ((ListenerUtil.mutListener.listen(1325) ? (!opening || indentLevel == 0) : (!opening && indentLevel == 0))) {
                            if (!ListenerUtil.mutListener.listen(1326)) {
                                output.append('\n');
                            }
                        }
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1337)) {
                if (TAG_OL.equalsIgnoreCase(tag)) {
                    if (!ListenerUtil.mutListener.listen(1333)) {
                        if (opening) {
                            if (!ListenerUtil.mutListener.listen(1331)) {
                                listElements.addFirst(new ListSeparator(true));
                            }
                            if (!ListenerUtil.mutListener.listen(1332)) {
                                indentLevel++;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1329)) {
                                listElements.removeFirst();
                            }
                            if (!ListenerUtil.mutListener.listen(1330)) {
                                indentLevel--;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1336)) {
                        if ((ListenerUtil.mutListener.listen(1334) ? (!opening || indentLevel == 0) : (!opening && indentLevel == 0))) {
                            if (!ListenerUtil.mutListener.listen(1335)) {
                                output.append('\n');
                            }
                        }
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1340)) {
                if ((ListenerUtil.mutListener.listen(1338) ? (TAG_LI.equalsIgnoreCase(tag) || opening) : (TAG_LI.equalsIgnoreCase(tag) && opening))) {
                    if (!ListenerUtil.mutListener.listen(1339)) {
                        listElements.getFirst().append(output, indentLevel);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1344)) {
                if (TAG_CODE.equalsIgnoreCase(tag)) {
                    if (!ListenerUtil.mutListener.listen(1343)) {
                        if (opening) {
                            if (!ListenerUtil.mutListener.listen(1342)) {
                                startSpan(new TypefaceSpan("monospace"), output);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1341)) {
                                endSpan(TypefaceSpan.class, output);
                            }
                        }
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1349)) {
                if (TAG_PRE.equalsIgnoreCase(tag)) {
                    if (!ListenerUtil.mutListener.listen(1345)) {
                        output.append('\n');
                    }
                    if (!ListenerUtil.mutListener.listen(1348)) {
                        if (opening) {
                            if (!ListenerUtil.mutListener.listen(1347)) {
                                startSpan(new TypefaceSpan("monospace"), output);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1346)) {
                                endSpan(TypefaceSpan.class, output);
                            }
                        }
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1383)) {
                if ((ListenerUtil.mutListener.listen(1350) ? (TAG_ROOT.equalsIgnoreCase(tag) || !opening) : (TAG_ROOT.equalsIgnoreCase(tag) && !opening))) {
                    if (!ListenerUtil.mutListener.listen(1358)) {
                        {
                            long _loopCounter36 = 0;
                            // Remove leading newlines
                            while ((ListenerUtil.mutListener.listen(1357) ? ((ListenerUtil.mutListener.listen(1356) ? (output.length() >= 0) : (ListenerUtil.mutListener.listen(1355) ? (output.length() <= 0) : (ListenerUtil.mutListener.listen(1354) ? (output.length() < 0) : (ListenerUtil.mutListener.listen(1353) ? (output.length() != 0) : (ListenerUtil.mutListener.listen(1352) ? (output.length() == 0) : (output.length() > 0)))))) || output.charAt(0) == '\n') : ((ListenerUtil.mutListener.listen(1356) ? (output.length() >= 0) : (ListenerUtil.mutListener.listen(1355) ? (output.length() <= 0) : (ListenerUtil.mutListener.listen(1354) ? (output.length() < 0) : (ListenerUtil.mutListener.listen(1353) ? (output.length() != 0) : (ListenerUtil.mutListener.listen(1352) ? (output.length() == 0) : (output.length() > 0)))))) && output.charAt(0) == '\n'))) {
                                ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                                if (!ListenerUtil.mutListener.listen(1351)) {
                                    output.delete(0, 1);
                                }
                            }
                        }
                    }
                    // Remove trailing newlines
                    int last = (ListenerUtil.mutListener.listen(1362) ? (output.length() % 1) : (ListenerUtil.mutListener.listen(1361) ? (output.length() / 1) : (ListenerUtil.mutListener.listen(1360) ? (output.length() * 1) : (ListenerUtil.mutListener.listen(1359) ? (output.length() + 1) : (output.length() - 1)))));
                    if (!ListenerUtil.mutListener.listen(1379)) {
                        {
                            long _loopCounter37 = 0;
                            while ((ListenerUtil.mutListener.listen(1378) ? ((ListenerUtil.mutListener.listen(1377) ? (last <= 0) : (ListenerUtil.mutListener.listen(1376) ? (last > 0) : (ListenerUtil.mutListener.listen(1375) ? (last < 0) : (ListenerUtil.mutListener.listen(1374) ? (last != 0) : (ListenerUtil.mutListener.listen(1373) ? (last == 0) : (last >= 0)))))) || output.charAt(last) == '\n') : ((ListenerUtil.mutListener.listen(1377) ? (last <= 0) : (ListenerUtil.mutListener.listen(1376) ? (last > 0) : (ListenerUtil.mutListener.listen(1375) ? (last < 0) : (ListenerUtil.mutListener.listen(1374) ? (last != 0) : (ListenerUtil.mutListener.listen(1373) ? (last == 0) : (last >= 0)))))) && output.charAt(last) == '\n'))) {
                                ListenerUtil.loopListener.listen("_loopCounter37", ++_loopCounter37);
                                if (!ListenerUtil.mutListener.listen(1367)) {
                                    output.delete(last, (ListenerUtil.mutListener.listen(1366) ? (last % 1) : (ListenerUtil.mutListener.listen(1365) ? (last / 1) : (ListenerUtil.mutListener.listen(1364) ? (last * 1) : (ListenerUtil.mutListener.listen(1363) ? (last - 1) : (last + 1))))));
                                }
                                if (!ListenerUtil.mutListener.listen(1372)) {
                                    last = (ListenerUtil.mutListener.listen(1371) ? (output.length() % 1) : (ListenerUtil.mutListener.listen(1370) ? (output.length() / 1) : (ListenerUtil.mutListener.listen(1369) ? (output.length() * 1) : (ListenerUtil.mutListener.listen(1368) ? (output.length() + 1) : (output.length() - 1)))));
                                }
                            }
                        }
                    }
                    QuoteSpan[] quoteSpans = output.getSpans(0, output.length(), QuoteSpan.class);
                    if (!ListenerUtil.mutListener.listen(1382)) {
                        {
                            long _loopCounter38 = 0;
                            for (QuoteSpan span : quoteSpans) {
                                ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                                int start = output.getSpanStart(span);
                                int end = output.getSpanEnd(span);
                                if (!ListenerUtil.mutListener.listen(1380)) {
                                    output.removeSpan(span);
                                }
                                if (!ListenerUtil.mutListener.listen(1381)) {
                                    output.setSpan(new ReplySpan(), start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    private static Object getLast(final Spanned text, final Class<?> kind) {
        Object[] spans = text.getSpans(0, text.length(), kind);
        return (ListenerUtil.mutListener.listen(1388) ? (spans.length >= 0) : (ListenerUtil.mutListener.listen(1387) ? (spans.length <= 0) : (ListenerUtil.mutListener.listen(1386) ? (spans.length < 0) : (ListenerUtil.mutListener.listen(1385) ? (spans.length != 0) : (ListenerUtil.mutListener.listen(1384) ? (spans.length == 0) : (spans.length > 0)))))) ? spans[(ListenerUtil.mutListener.listen(1392) ? (spans.length % 1) : (ListenerUtil.mutListener.listen(1391) ? (spans.length / 1) : (ListenerUtil.mutListener.listen(1390) ? (spans.length * 1) : (ListenerUtil.mutListener.listen(1389) ? (spans.length + 1) : (spans.length - 1)))))] : null;
    }

    private static void startSpan(Object span, Editable output) {
        int length = output.length();
        if (!ListenerUtil.mutListener.listen(1393)) {
            output.setSpan(span, length, length, SPAN_MARK_MARK);
        }
    }

    private static void endSpan(Class<?> type, Editable output) {
        int length = output.length();
        Object span = getLast(output, type);
        int start = output.getSpanStart(span);
        if (!ListenerUtil.mutListener.listen(1394)) {
            output.removeSpan(span);
        }
        if (!ListenerUtil.mutListener.listen(1401)) {
            if ((ListenerUtil.mutListener.listen(1399) ? (start >= length) : (ListenerUtil.mutListener.listen(1398) ? (start <= length) : (ListenerUtil.mutListener.listen(1397) ? (start > length) : (ListenerUtil.mutListener.listen(1396) ? (start < length) : (ListenerUtil.mutListener.listen(1395) ? (start == length) : (start != length))))))) {
                if (!ListenerUtil.mutListener.listen(1400)) {
                    output.setSpan(span, start, length, SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
    }

    /**
     * Encode HTML
     *
     * @param html
     * @return html
     */
    public static CharSequence encode(final String html) {
        return encode(html, null);
    }

    /**
     * Encode HTML
     *
     * @param html
     * @param imageGetter
     * @return html
     */
    public static CharSequence encode(final String html, final ImageGetter imageGetter) {
        if (!ListenerUtil.mutListener.listen(1402)) {
            if (TextUtils.isEmpty(html)) {
                return "";
            }
        }
        return Html.fromHtml(html, imageGetter, TAG_HANDLER);
    }

    /**
     * Format given HTML string so it is ready to be presented in a text view
     *
     * @param html
     * @return formatted HTML
     */
    public static final CharSequence format(final String html) {
        if (!ListenerUtil.mutListener.listen(1403)) {
            if (html == null) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(1409)) {
            if ((ListenerUtil.mutListener.listen(1408) ? (html.length() >= 0) : (ListenerUtil.mutListener.listen(1407) ? (html.length() <= 0) : (ListenerUtil.mutListener.listen(1406) ? (html.length() > 0) : (ListenerUtil.mutListener.listen(1405) ? (html.length() < 0) : (ListenerUtil.mutListener.listen(1404) ? (html.length() != 0) : (html.length() == 0))))))) {
                return "";
            }
        }
        StringBuilder formatted = new StringBuilder(html);
        if (!ListenerUtil.mutListener.listen(1410)) {
            // Remove e-mail toggle link
            strip(formatted, TOGGLE_START, TOGGLE_END);
        }
        if (!ListenerUtil.mutListener.listen(1411)) {
            // Remove signature
            strip(formatted, SIGNATURE_START, SIGNATURE_END);
        }
        if (!ListenerUtil.mutListener.listen(1412)) {
            // Replace div with e-mail content with block quote
            replace(formatted, REPLY_START, REPLY_END, BLOCKQUOTE_START, BLOCKQUOTE_END);
        }
        if (!ListenerUtil.mutListener.listen(1413)) {
            // Remove hidden div
            strip(formatted, HIDDEN_REPLY_START, HIDDEN_REPLY_END);
        }
        if (!ListenerUtil.mutListener.listen(1415)) {
            // Replace paragraphs with breaks
            if (replace(formatted, PARAGRAPH_START, BREAK)) {
                if (!ListenerUtil.mutListener.listen(1414)) {
                    replace(formatted, PARAGRAPH_END, BREAK);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1416)) {
            formatPres(formatted);
        }
        if (!ListenerUtil.mutListener.listen(1417)) {
            formatEmailFragments(formatted);
        }
        if (!ListenerUtil.mutListener.listen(1418)) {
            trim(formatted);
        }
        if (!ListenerUtil.mutListener.listen(1419)) {
            formatted.insert(0, ROOT_START);
        }
        if (!ListenerUtil.mutListener.listen(1420)) {
            formatted.append(ROOT_END);
        }
        return formatted;
    }

    private static StringBuilder strip(final StringBuilder input, final String prefix, final String suffix) {
        int start = input.indexOf(prefix);
        if (!ListenerUtil.mutListener.listen(1443)) {
            {
                long _loopCounter39 = 0;
                while ((ListenerUtil.mutListener.listen(1442) ? (start >= -1) : (ListenerUtil.mutListener.listen(1441) ? (start <= -1) : (ListenerUtil.mutListener.listen(1440) ? (start > -1) : (ListenerUtil.mutListener.listen(1439) ? (start < -1) : (ListenerUtil.mutListener.listen(1438) ? (start == -1) : (start != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter39", ++_loopCounter39);
                    int end = input.indexOf(suffix, (ListenerUtil.mutListener.listen(1424) ? (start % prefix.length()) : (ListenerUtil.mutListener.listen(1423) ? (start / prefix.length()) : (ListenerUtil.mutListener.listen(1422) ? (start * prefix.length()) : (ListenerUtil.mutListener.listen(1421) ? (start - prefix.length()) : (start + prefix.length()))))));
                    if (!ListenerUtil.mutListener.listen(1431)) {
                        if ((ListenerUtil.mutListener.listen(1429) ? (end >= -1) : (ListenerUtil.mutListener.listen(1428) ? (end <= -1) : (ListenerUtil.mutListener.listen(1427) ? (end > -1) : (ListenerUtil.mutListener.listen(1426) ? (end < -1) : (ListenerUtil.mutListener.listen(1425) ? (end != -1) : (end == -1))))))) {
                            if (!ListenerUtil.mutListener.listen(1430)) {
                                end = input.length();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1436)) {
                        input.delete(start, (ListenerUtil.mutListener.listen(1435) ? (end % suffix.length()) : (ListenerUtil.mutListener.listen(1434) ? (end / suffix.length()) : (ListenerUtil.mutListener.listen(1433) ? (end * suffix.length()) : (ListenerUtil.mutListener.listen(1432) ? (end - suffix.length()) : (end + suffix.length()))))));
                    }
                    if (!ListenerUtil.mutListener.listen(1437)) {
                        start = input.indexOf(prefix, start);
                    }
                }
            }
        }
        return input;
    }

    private static boolean replace(final StringBuilder input, final String from, final String to) {
        int start = input.indexOf(from);
        if (!ListenerUtil.mutListener.listen(1449)) {
            if ((ListenerUtil.mutListener.listen(1448) ? (start >= -1) : (ListenerUtil.mutListener.listen(1447) ? (start <= -1) : (ListenerUtil.mutListener.listen(1446) ? (start > -1) : (ListenerUtil.mutListener.listen(1445) ? (start < -1) : (ListenerUtil.mutListener.listen(1444) ? (start != -1) : (start == -1))))))) {
                return false;
            }
        }
        final int fromLength = from.length();
        final int toLength = to.length();
        if (!ListenerUtil.mutListener.listen(1465)) {
            {
                long _loopCounter40 = 0;
                while ((ListenerUtil.mutListener.listen(1464) ? (start >= -1) : (ListenerUtil.mutListener.listen(1463) ? (start <= -1) : (ListenerUtil.mutListener.listen(1462) ? (start > -1) : (ListenerUtil.mutListener.listen(1461) ? (start < -1) : (ListenerUtil.mutListener.listen(1460) ? (start == -1) : (start != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter40", ++_loopCounter40);
                    if (!ListenerUtil.mutListener.listen(1454)) {
                        input.replace(start, (ListenerUtil.mutListener.listen(1453) ? (start % fromLength) : (ListenerUtil.mutListener.listen(1452) ? (start / fromLength) : (ListenerUtil.mutListener.listen(1451) ? (start * fromLength) : (ListenerUtil.mutListener.listen(1450) ? (start - fromLength) : (start + fromLength))))), to);
                    }
                    if (!ListenerUtil.mutListener.listen(1459)) {
                        start = input.indexOf(from, (ListenerUtil.mutListener.listen(1458) ? (start % toLength) : (ListenerUtil.mutListener.listen(1457) ? (start / toLength) : (ListenerUtil.mutListener.listen(1456) ? (start * toLength) : (ListenerUtil.mutListener.listen(1455) ? (start - toLength) : (start + toLength))))));
                    }
                }
            }
        }
        return true;
    }

    private static void replaceTag(final StringBuilder input, final String from, final String to) {
        if (!ListenerUtil.mutListener.listen(1467)) {
            if (replace(input, '<' + from + '>', '<' + to + '>')) {
                if (!ListenerUtil.mutListener.listen(1466)) {
                    replace(input, "</" + from + '>', "</" + to + '>');
                }
            }
        }
    }

    private static StringBuilder replace(final StringBuilder input, final String fromStart, final String fromEnd, final String toStart, final String toEnd) {
        int start = input.indexOf(fromStart);
        if (!ListenerUtil.mutListener.listen(1473)) {
            if ((ListenerUtil.mutListener.listen(1472) ? (start >= -1) : (ListenerUtil.mutListener.listen(1471) ? (start <= -1) : (ListenerUtil.mutListener.listen(1470) ? (start > -1) : (ListenerUtil.mutListener.listen(1469) ? (start < -1) : (ListenerUtil.mutListener.listen(1468) ? (start != -1) : (start == -1))))))) {
                return input;
            }
        }
        final int fromStartLength = fromStart.length();
        final int fromEndLength = fromEnd.length();
        final int toStartLength = toStart.length();
        if (!ListenerUtil.mutListener.listen(1500)) {
            {
                long _loopCounter41 = 0;
                while ((ListenerUtil.mutListener.listen(1499) ? (start >= -1) : (ListenerUtil.mutListener.listen(1498) ? (start <= -1) : (ListenerUtil.mutListener.listen(1497) ? (start > -1) : (ListenerUtil.mutListener.listen(1496) ? (start < -1) : (ListenerUtil.mutListener.listen(1495) ? (start == -1) : (start != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter41", ++_loopCounter41);
                    if (!ListenerUtil.mutListener.listen(1478)) {
                        input.replace(start, (ListenerUtil.mutListener.listen(1477) ? (start % fromStartLength) : (ListenerUtil.mutListener.listen(1476) ? (start / fromStartLength) : (ListenerUtil.mutListener.listen(1475) ? (start * fromStartLength) : (ListenerUtil.mutListener.listen(1474) ? (start - fromStartLength) : (start + fromStartLength))))), toStart);
                    }
                    int end = input.indexOf(fromEnd, (ListenerUtil.mutListener.listen(1482) ? (start % toStartLength) : (ListenerUtil.mutListener.listen(1481) ? (start / toStartLength) : (ListenerUtil.mutListener.listen(1480) ? (start * toStartLength) : (ListenerUtil.mutListener.listen(1479) ? (start - toStartLength) : (start + toStartLength))))));
                    if (!ListenerUtil.mutListener.listen(1493)) {
                        if ((ListenerUtil.mutListener.listen(1487) ? (end >= -1) : (ListenerUtil.mutListener.listen(1486) ? (end <= -1) : (ListenerUtil.mutListener.listen(1485) ? (end > -1) : (ListenerUtil.mutListener.listen(1484) ? (end < -1) : (ListenerUtil.mutListener.listen(1483) ? (end == -1) : (end != -1))))))) {
                            if (!ListenerUtil.mutListener.listen(1492)) {
                                input.replace(end, (ListenerUtil.mutListener.listen(1491) ? (end % fromEndLength) : (ListenerUtil.mutListener.listen(1490) ? (end / fromEndLength) : (ListenerUtil.mutListener.listen(1489) ? (end * fromEndLength) : (ListenerUtil.mutListener.listen(1488) ? (end - fromEndLength) : (end + fromEndLength))))), toEnd);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1494)) {
                        start = input.indexOf(fromStart);
                    }
                }
            }
        }
        return input;
    }

    private static StringBuilder formatPres(final StringBuilder input) {
        int start = input.indexOf(PRE_START);
        final int spaceAdvance = (ListenerUtil.mutListener.listen(1504) ? (SPACE.length() % 1) : (ListenerUtil.mutListener.listen(1503) ? (SPACE.length() / 1) : (ListenerUtil.mutListener.listen(1502) ? (SPACE.length() * 1) : (ListenerUtil.mutListener.listen(1501) ? (SPACE.length() + 1) : (SPACE.length() - 1)))));
        final int breakAdvance = (ListenerUtil.mutListener.listen(1508) ? (BREAK.length() % 1) : (ListenerUtil.mutListener.listen(1507) ? (BREAK.length() / 1) : (ListenerUtil.mutListener.listen(1506) ? (BREAK.length() * 1) : (ListenerUtil.mutListener.listen(1505) ? (BREAK.length() + 1) : (BREAK.length() - 1)))));
        if (!ListenerUtil.mutListener.listen(1593)) {
            {
                long _loopCounter44 = 0;
                while ((ListenerUtil.mutListener.listen(1592) ? (start >= -1) : (ListenerUtil.mutListener.listen(1591) ? (start <= -1) : (ListenerUtil.mutListener.listen(1590) ? (start > -1) : (ListenerUtil.mutListener.listen(1589) ? (start < -1) : (ListenerUtil.mutListener.listen(1588) ? (start == -1) : (start != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter44", ++_loopCounter44);
                    int end = input.indexOf(PRE_END, (ListenerUtil.mutListener.listen(1512) ? (start % PRE_START.length()) : (ListenerUtil.mutListener.listen(1511) ? (start / PRE_START.length()) : (ListenerUtil.mutListener.listen(1510) ? (start * PRE_START.length()) : (ListenerUtil.mutListener.listen(1509) ? (start - PRE_START.length()) : (start + PRE_START.length()))))));
                    if (!ListenerUtil.mutListener.listen(1518)) {
                        if ((ListenerUtil.mutListener.listen(1517) ? (end >= -1) : (ListenerUtil.mutListener.listen(1516) ? (end <= -1) : (ListenerUtil.mutListener.listen(1515) ? (end > -1) : (ListenerUtil.mutListener.listen(1514) ? (end < -1) : (ListenerUtil.mutListener.listen(1513) ? (end != -1) : (end == -1))))))) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1525)) {
                        // Skip over code element
                        if ((ListenerUtil.mutListener.listen(1523) ? (input.indexOf(CODE_START, start) >= start) : (ListenerUtil.mutListener.listen(1522) ? (input.indexOf(CODE_START, start) <= start) : (ListenerUtil.mutListener.listen(1521) ? (input.indexOf(CODE_START, start) > start) : (ListenerUtil.mutListener.listen(1520) ? (input.indexOf(CODE_START, start) < start) : (ListenerUtil.mutListener.listen(1519) ? (input.indexOf(CODE_START, start) != start) : (input.indexOf(CODE_START, start) == start))))))) {
                            if (!ListenerUtil.mutListener.listen(1524)) {
                                start += CODE_START.length();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1536)) {
                        if ((ListenerUtil.mutListener.listen(1534) ? (input.indexOf(CODE_END, start) >= (ListenerUtil.mutListener.listen(1529) ? (end % CODE_END.length()) : (ListenerUtil.mutListener.listen(1528) ? (end / CODE_END.length()) : (ListenerUtil.mutListener.listen(1527) ? (end * CODE_END.length()) : (ListenerUtil.mutListener.listen(1526) ? (end + CODE_END.length()) : (end - CODE_END.length())))))) : (ListenerUtil.mutListener.listen(1533) ? (input.indexOf(CODE_END, start) <= (ListenerUtil.mutListener.listen(1529) ? (end % CODE_END.length()) : (ListenerUtil.mutListener.listen(1528) ? (end / CODE_END.length()) : (ListenerUtil.mutListener.listen(1527) ? (end * CODE_END.length()) : (ListenerUtil.mutListener.listen(1526) ? (end + CODE_END.length()) : (end - CODE_END.length())))))) : (ListenerUtil.mutListener.listen(1532) ? (input.indexOf(CODE_END, start) > (ListenerUtil.mutListener.listen(1529) ? (end % CODE_END.length()) : (ListenerUtil.mutListener.listen(1528) ? (end / CODE_END.length()) : (ListenerUtil.mutListener.listen(1527) ? (end * CODE_END.length()) : (ListenerUtil.mutListener.listen(1526) ? (end + CODE_END.length()) : (end - CODE_END.length())))))) : (ListenerUtil.mutListener.listen(1531) ? (input.indexOf(CODE_END, start) < (ListenerUtil.mutListener.listen(1529) ? (end % CODE_END.length()) : (ListenerUtil.mutListener.listen(1528) ? (end / CODE_END.length()) : (ListenerUtil.mutListener.listen(1527) ? (end * CODE_END.length()) : (ListenerUtil.mutListener.listen(1526) ? (end + CODE_END.length()) : (end - CODE_END.length())))))) : (ListenerUtil.mutListener.listen(1530) ? (input.indexOf(CODE_END, start) != (ListenerUtil.mutListener.listen(1529) ? (end % CODE_END.length()) : (ListenerUtil.mutListener.listen(1528) ? (end / CODE_END.length()) : (ListenerUtil.mutListener.listen(1527) ? (end * CODE_END.length()) : (ListenerUtil.mutListener.listen(1526) ? (end + CODE_END.length()) : (end - CODE_END.length())))))) : (input.indexOf(CODE_END, start) == (ListenerUtil.mutListener.listen(1529) ? (end % CODE_END.length()) : (ListenerUtil.mutListener.listen(1528) ? (end / CODE_END.length()) : (ListenerUtil.mutListener.listen(1527) ? (end * CODE_END.length()) : (ListenerUtil.mutListener.listen(1526) ? (end + CODE_END.length()) : (end - CODE_END.length())))))))))))) {
                            if (!ListenerUtil.mutListener.listen(1535)) {
                                end -= CODE_END.length();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1582)) {
                        {
                            long _loopCounter43 = 0;
                            for (int i = start; (ListenerUtil.mutListener.listen(1581) ? (i >= end) : (ListenerUtil.mutListener.listen(1580) ? (i <= end) : (ListenerUtil.mutListener.listen(1579) ? (i > end) : (ListenerUtil.mutListener.listen(1578) ? (i != end) : (ListenerUtil.mutListener.listen(1577) ? (i == end) : (i < end)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter43", ++_loopCounter43);
                                if (!ListenerUtil.mutListener.listen(1576)) {
                                    switch(input.charAt(i)) {
                                        case ' ':
                                            if (!ListenerUtil.mutListener.listen(1537)) {
                                                input.deleteCharAt(i);
                                            }
                                            if (!ListenerUtil.mutListener.listen(1538)) {
                                                input.insert(i, SPACE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(1539)) {
                                                start += spaceAdvance;
                                            }
                                            if (!ListenerUtil.mutListener.listen(1540)) {
                                                end += spaceAdvance;
                                            }
                                            break;
                                        case '\t':
                                            if (!ListenerUtil.mutListener.listen(1541)) {
                                                input.deleteCharAt(i);
                                            }
                                            if (!ListenerUtil.mutListener.listen(1542)) {
                                                input.insert(i, SPACE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(1543)) {
                                                start += spaceAdvance;
                                            }
                                            if (!ListenerUtil.mutListener.listen(1544)) {
                                                end += spaceAdvance;
                                            }
                                            if (!ListenerUtil.mutListener.listen(1561)) {
                                                {
                                                    long _loopCounter42 = 0;
                                                    for (int j = 0; (ListenerUtil.mutListener.listen(1560) ? (j >= 3) : (ListenerUtil.mutListener.listen(1559) ? (j <= 3) : (ListenerUtil.mutListener.listen(1558) ? (j > 3) : (ListenerUtil.mutListener.listen(1557) ? (j != 3) : (ListenerUtil.mutListener.listen(1556) ? (j == 3) : (j < 3)))))); j++) {
                                                        ListenerUtil.loopListener.listen("_loopCounter42", ++_loopCounter42);
                                                        if (!ListenerUtil.mutListener.listen(1545)) {
                                                            input.insert(i, SPACE);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(1550)) {
                                                            start += (ListenerUtil.mutListener.listen(1549) ? (spaceAdvance % 1) : (ListenerUtil.mutListener.listen(1548) ? (spaceAdvance / 1) : (ListenerUtil.mutListener.listen(1547) ? (spaceAdvance * 1) : (ListenerUtil.mutListener.listen(1546) ? (spaceAdvance - 1) : (spaceAdvance + 1)))));
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(1555)) {
                                                            end += (ListenerUtil.mutListener.listen(1554) ? (spaceAdvance % 1) : (ListenerUtil.mutListener.listen(1553) ? (spaceAdvance / 1) : (ListenerUtil.mutListener.listen(1552) ? (spaceAdvance * 1) : (ListenerUtil.mutListener.listen(1551) ? (spaceAdvance - 1) : (spaceAdvance + 1)))));
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                        case '\n':
                                            if (!ListenerUtil.mutListener.listen(1562)) {
                                                input.deleteCharAt(i);
                                            }
                                            if (!ListenerUtil.mutListener.listen(1575)) {
                                                // Ignore if last character is a newline
                                                if ((ListenerUtil.mutListener.listen(1571) ? ((ListenerUtil.mutListener.listen(1566) ? (i % 1) : (ListenerUtil.mutListener.listen(1565) ? (i / 1) : (ListenerUtil.mutListener.listen(1564) ? (i * 1) : (ListenerUtil.mutListener.listen(1563) ? (i - 1) : (i + 1))))) >= end) : (ListenerUtil.mutListener.listen(1570) ? ((ListenerUtil.mutListener.listen(1566) ? (i % 1) : (ListenerUtil.mutListener.listen(1565) ? (i / 1) : (ListenerUtil.mutListener.listen(1564) ? (i * 1) : (ListenerUtil.mutListener.listen(1563) ? (i - 1) : (i + 1))))) <= end) : (ListenerUtil.mutListener.listen(1569) ? ((ListenerUtil.mutListener.listen(1566) ? (i % 1) : (ListenerUtil.mutListener.listen(1565) ? (i / 1) : (ListenerUtil.mutListener.listen(1564) ? (i * 1) : (ListenerUtil.mutListener.listen(1563) ? (i - 1) : (i + 1))))) > end) : (ListenerUtil.mutListener.listen(1568) ? ((ListenerUtil.mutListener.listen(1566) ? (i % 1) : (ListenerUtil.mutListener.listen(1565) ? (i / 1) : (ListenerUtil.mutListener.listen(1564) ? (i * 1) : (ListenerUtil.mutListener.listen(1563) ? (i - 1) : (i + 1))))) != end) : (ListenerUtil.mutListener.listen(1567) ? ((ListenerUtil.mutListener.listen(1566) ? (i % 1) : (ListenerUtil.mutListener.listen(1565) ? (i / 1) : (ListenerUtil.mutListener.listen(1564) ? (i * 1) : (ListenerUtil.mutListener.listen(1563) ? (i - 1) : (i + 1))))) == end) : ((ListenerUtil.mutListener.listen(1566) ? (i % 1) : (ListenerUtil.mutListener.listen(1565) ? (i / 1) : (ListenerUtil.mutListener.listen(1564) ? (i * 1) : (ListenerUtil.mutListener.listen(1563) ? (i - 1) : (i + 1))))) < end))))))) {
                                                    if (!ListenerUtil.mutListener.listen(1572)) {
                                                        input.insert(i, BREAK);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(1573)) {
                                                        start += breakAdvance;
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(1574)) {
                                                        end += breakAdvance;
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1587)) {
                        start = input.indexOf(PRE_START, (ListenerUtil.mutListener.listen(1586) ? (end % PRE_END.length()) : (ListenerUtil.mutListener.listen(1585) ? (end / PRE_END.length()) : (ListenerUtil.mutListener.listen(1584) ? (end * PRE_END.length()) : (ListenerUtil.mutListener.listen(1583) ? (end - PRE_END.length()) : (end + PRE_END.length()))))));
                    }
                }
            }
        }
        return input;
    }

    /**
     * Remove email fragment 'div' tag and replace newlines with 'br' tags
     *
     * @param input
     * @return input
     */
    private static StringBuilder formatEmailFragments(final StringBuilder input) {
        int emailStart = input.indexOf(EMAIL_START);
        int breakAdvance = (ListenerUtil.mutListener.listen(1597) ? (BREAK.length() % 1) : (ListenerUtil.mutListener.listen(1596) ? (BREAK.length() / 1) : (ListenerUtil.mutListener.listen(1595) ? (BREAK.length() * 1) : (ListenerUtil.mutListener.listen(1594) ? (BREAK.length() + 1) : (BREAK.length() - 1)))));
        if (!ListenerUtil.mutListener.listen(1639)) {
            {
                long _loopCounter46 = 0;
                while ((ListenerUtil.mutListener.listen(1638) ? (emailStart >= -1) : (ListenerUtil.mutListener.listen(1637) ? (emailStart <= -1) : (ListenerUtil.mutListener.listen(1636) ? (emailStart > -1) : (ListenerUtil.mutListener.listen(1635) ? (emailStart < -1) : (ListenerUtil.mutListener.listen(1634) ? (emailStart == -1) : (emailStart != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter46", ++_loopCounter46);
                    int startLength = EMAIL_START.length();
                    int emailEnd = input.indexOf(EMAIL_END, (ListenerUtil.mutListener.listen(1601) ? (emailStart % startLength) : (ListenerUtil.mutListener.listen(1600) ? (emailStart / startLength) : (ListenerUtil.mutListener.listen(1599) ? (emailStart * startLength) : (ListenerUtil.mutListener.listen(1598) ? (emailStart - startLength) : (emailStart + startLength))))));
                    if (!ListenerUtil.mutListener.listen(1607)) {
                        if ((ListenerUtil.mutListener.listen(1606) ? (emailEnd >= -1) : (ListenerUtil.mutListener.listen(1605) ? (emailEnd <= -1) : (ListenerUtil.mutListener.listen(1604) ? (emailEnd > -1) : (ListenerUtil.mutListener.listen(1603) ? (emailEnd < -1) : (ListenerUtil.mutListener.listen(1602) ? (emailEnd != -1) : (emailEnd == -1))))))) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1612)) {
                        input.delete(emailEnd, (ListenerUtil.mutListener.listen(1611) ? (emailEnd % EMAIL_END.length()) : (ListenerUtil.mutListener.listen(1610) ? (emailEnd / EMAIL_END.length()) : (ListenerUtil.mutListener.listen(1609) ? (emailEnd * EMAIL_END.length()) : (ListenerUtil.mutListener.listen(1608) ? (emailEnd - EMAIL_END.length()) : (emailEnd + EMAIL_END.length()))))));
                    }
                    if (!ListenerUtil.mutListener.listen(1617)) {
                        input.delete(emailStart, (ListenerUtil.mutListener.listen(1616) ? (emailStart % startLength) : (ListenerUtil.mutListener.listen(1615) ? (emailStart / startLength) : (ListenerUtil.mutListener.listen(1614) ? (emailStart * startLength) : (ListenerUtil.mutListener.listen(1613) ? (emailStart - startLength) : (emailStart + startLength))))));
                    }
                    int fullEmail = (ListenerUtil.mutListener.listen(1621) ? (emailEnd % startLength) : (ListenerUtil.mutListener.listen(1620) ? (emailEnd / startLength) : (ListenerUtil.mutListener.listen(1619) ? (emailEnd * startLength) : (ListenerUtil.mutListener.listen(1618) ? (emailEnd + startLength) : (emailEnd - startLength)))));
                    if (!ListenerUtil.mutListener.listen(1632)) {
                        {
                            long _loopCounter45 = 0;
                            for (int i = emailStart; (ListenerUtil.mutListener.listen(1631) ? (i >= fullEmail) : (ListenerUtil.mutListener.listen(1630) ? (i <= fullEmail) : (ListenerUtil.mutListener.listen(1629) ? (i > fullEmail) : (ListenerUtil.mutListener.listen(1628) ? (i != fullEmail) : (ListenerUtil.mutListener.listen(1627) ? (i == fullEmail) : (i < fullEmail)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter45", ++_loopCounter45);
                                if (!ListenerUtil.mutListener.listen(1626)) {
                                    if (input.charAt(i) == '\n') {
                                        if (!ListenerUtil.mutListener.listen(1622)) {
                                            input.deleteCharAt(i);
                                        }
                                        if (!ListenerUtil.mutListener.listen(1623)) {
                                            input.insert(i, BREAK);
                                        }
                                        if (!ListenerUtil.mutListener.listen(1624)) {
                                            i += breakAdvance;
                                        }
                                        if (!ListenerUtil.mutListener.listen(1625)) {
                                            fullEmail += breakAdvance;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1633)) {
                        emailStart = input.indexOf(EMAIL_START, fullEmail);
                    }
                }
            }
        }
        return input;
    }

    /**
     * Remove leading and trailing whitespace
     *
     * @param input
     */
    private static StringBuilder trim(final StringBuilder input) {
        int length = input.length();
        int breakLength = BREAK.length();
        if (!ListenerUtil.mutListener.listen(1683)) {
            {
                long _loopCounter47 = 0;
                while ((ListenerUtil.mutListener.listen(1682) ? (length >= 0) : (ListenerUtil.mutListener.listen(1681) ? (length <= 0) : (ListenerUtil.mutListener.listen(1680) ? (length < 0) : (ListenerUtil.mutListener.listen(1679) ? (length != 0) : (ListenerUtil.mutListener.listen(1678) ? (length == 0) : (length > 0))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter47", ++_loopCounter47);
                    if (!ListenerUtil.mutListener.listen(1676)) {
                        if ((ListenerUtil.mutListener.listen(1644) ? (input.indexOf(BREAK) >= 0) : (ListenerUtil.mutListener.listen(1643) ? (input.indexOf(BREAK) <= 0) : (ListenerUtil.mutListener.listen(1642) ? (input.indexOf(BREAK) > 0) : (ListenerUtil.mutListener.listen(1641) ? (input.indexOf(BREAK) < 0) : (ListenerUtil.mutListener.listen(1640) ? (input.indexOf(BREAK) != 0) : (input.indexOf(BREAK) == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(1675)) {
                                input.delete(0, breakLength);
                            }
                        } else if ((ListenerUtil.mutListener.listen(1659) ? ((ListenerUtil.mutListener.listen(1649) ? (length <= breakLength) : (ListenerUtil.mutListener.listen(1648) ? (length > breakLength) : (ListenerUtil.mutListener.listen(1647) ? (length < breakLength) : (ListenerUtil.mutListener.listen(1646) ? (length != breakLength) : (ListenerUtil.mutListener.listen(1645) ? (length == breakLength) : (length >= breakLength)))))) || (ListenerUtil.mutListener.listen(1658) ? (input.lastIndexOf(BREAK) >= (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (ListenerUtil.mutListener.listen(1657) ? (input.lastIndexOf(BREAK) <= (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (ListenerUtil.mutListener.listen(1656) ? (input.lastIndexOf(BREAK) > (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (ListenerUtil.mutListener.listen(1655) ? (input.lastIndexOf(BREAK) < (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (ListenerUtil.mutListener.listen(1654) ? (input.lastIndexOf(BREAK) != (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (input.lastIndexOf(BREAK) == (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))))))))) : ((ListenerUtil.mutListener.listen(1649) ? (length <= breakLength) : (ListenerUtil.mutListener.listen(1648) ? (length > breakLength) : (ListenerUtil.mutListener.listen(1647) ? (length < breakLength) : (ListenerUtil.mutListener.listen(1646) ? (length != breakLength) : (ListenerUtil.mutListener.listen(1645) ? (length == breakLength) : (length >= breakLength)))))) && (ListenerUtil.mutListener.listen(1658) ? (input.lastIndexOf(BREAK) >= (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (ListenerUtil.mutListener.listen(1657) ? (input.lastIndexOf(BREAK) <= (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (ListenerUtil.mutListener.listen(1656) ? (input.lastIndexOf(BREAK) > (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (ListenerUtil.mutListener.listen(1655) ? (input.lastIndexOf(BREAK) < (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (ListenerUtil.mutListener.listen(1654) ? (input.lastIndexOf(BREAK) != (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))) : (input.lastIndexOf(BREAK) == (ListenerUtil.mutListener.listen(1653) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1652) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1651) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1650) ? (length + breakLength) : (length - breakLength)))))))))))))) {
                            if (!ListenerUtil.mutListener.listen(1674)) {
                                input.delete((ListenerUtil.mutListener.listen(1673) ? (length % breakLength) : (ListenerUtil.mutListener.listen(1672) ? (length / breakLength) : (ListenerUtil.mutListener.listen(1671) ? (length * breakLength) : (ListenerUtil.mutListener.listen(1670) ? (length + breakLength) : (length - breakLength))))), length);
                            }
                        } else if (Character.isWhitespace(input.charAt(0))) {
                            if (!ListenerUtil.mutListener.listen(1669)) {
                                input.deleteCharAt(0);
                            }
                        } else if (Character.isWhitespace(input.charAt((ListenerUtil.mutListener.listen(1663) ? (length % 1) : (ListenerUtil.mutListener.listen(1662) ? (length / 1) : (ListenerUtil.mutListener.listen(1661) ? (length * 1) : (ListenerUtil.mutListener.listen(1660) ? (length + 1) : (length - 1)))))))) {
                            if (!ListenerUtil.mutListener.listen(1668)) {
                                input.deleteCharAt((ListenerUtil.mutListener.listen(1667) ? (length % 1) : (ListenerUtil.mutListener.listen(1666) ? (length / 1) : (ListenerUtil.mutListener.listen(1665) ? (length * 1) : (ListenerUtil.mutListener.listen(1664) ? (length + 1) : (length - 1))))));
                            }
                        } else {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1677)) {
                        length = input.length();
                    }
                }
            }
        }
        return input;
    }
}

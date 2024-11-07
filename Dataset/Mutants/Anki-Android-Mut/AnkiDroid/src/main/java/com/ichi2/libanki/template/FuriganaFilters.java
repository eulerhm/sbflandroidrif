/**
 * ************************************************************************************
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
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
package com.ichi2.libanki.template;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FuriganaFilters {

    private static final Pattern r = Pattern.compile(" ?([^ >]+?)\\[(.+?)]");

    private static final String RUBY = "<ruby><rb>$1</rb><rt>$2</rt></ruby>";

    private static String noSound(Matcher match, String repl) {
        if (match.group(2).startsWith("sound:")) {
            // return without modification
            return match.group(0);
        } else {
            return r.matcher(match.group(0)).replaceAll(repl);
        }
    }

    public static String kanjiFilter(String txt) {
        Matcher m = r.matcher(txt);
        StringBuffer sb = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(20531)) {
            {
                long _loopCounter426 = 0;
                while (m.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter426", ++_loopCounter426);
                    if (!ListenerUtil.mutListener.listen(20530)) {
                        m.appendReplacement(sb, noSound(m, "$1"));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20532)) {
            m.appendTail(sb);
        }
        return sb.toString();
    }

    public static String kanaFilter(String txt) {
        Matcher m = r.matcher(txt);
        StringBuffer sb = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(20534)) {
            {
                long _loopCounter427 = 0;
                while (m.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter427", ++_loopCounter427);
                    if (!ListenerUtil.mutListener.listen(20533)) {
                        m.appendReplacement(sb, noSound(m, "$2"));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20535)) {
            m.appendTail(sb);
        }
        return sb.toString();
    }

    public static String furiganaFilter(String txt) {
        Matcher m = r.matcher(txt);
        StringBuffer sb = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(20537)) {
            {
                long _loopCounter428 = 0;
                while (m.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter428", ++_loopCounter428);
                    if (!ListenerUtil.mutListener.listen(20536)) {
                        m.appendReplacement(sb, noSound(m, RUBY));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20538)) {
            m.appendTail(sb);
        }
        return sb.toString();
    }
}

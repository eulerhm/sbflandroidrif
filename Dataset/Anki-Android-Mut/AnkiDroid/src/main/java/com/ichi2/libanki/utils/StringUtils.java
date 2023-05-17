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
 */
package com.ichi2.libanki.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StringUtils {

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+", Pattern.MULTILINE | Pattern.DOTALL);

    /**
     * Equivalent to the python string.split()
     */
    @NonNull
    public static List<String> splitOnWhitespace(@NonNull String value) {
        String[] split = WHITESPACE_PATTERN.split(value);
        List<String> ret = new ArrayList<>(split.length);
        if (!ListenerUtil.mutListener.listen(20741)) {
            {
                long _loopCounter441 = 0;
                for (String s : split) {
                    ListenerUtil.loopListener.listen("_loopCounter441", ++_loopCounter441);
                    if (!ListenerUtil.mutListener.listen(20740)) {
                        if ((ListenerUtil.mutListener.listen(20738) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(20737) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(20736) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(20735) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(20734) ? (s.length() == 0) : (s.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(20739)) {
                                ret.add(s);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}

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
package com.ichi2.utils;

import org.jetbrains.annotations.Contract;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StringUtil {

    /**
     * Trims from the right hand side of a string
     */
    @Nullable
    @Contract("null -> null; !null -> !null")
    public static String trimRight(@Nullable String s) {
        if (!ListenerUtil.mutListener.listen(26014)) {
            if (s == null) {
                return null;
            }
        }
        int newLength = s.length();
        if (!ListenerUtil.mutListener.listen(26026)) {
            {
                long _loopCounter698 = 0;
                while ((ListenerUtil.mutListener.listen(26025) ? ((ListenerUtil.mutListener.listen(26020) ? (newLength >= 0) : (ListenerUtil.mutListener.listen(26019) ? (newLength <= 0) : (ListenerUtil.mutListener.listen(26018) ? (newLength < 0) : (ListenerUtil.mutListener.listen(26017) ? (newLength != 0) : (ListenerUtil.mutListener.listen(26016) ? (newLength == 0) : (newLength > 0)))))) || Character.isWhitespace(s.charAt((ListenerUtil.mutListener.listen(26024) ? (newLength % 1) : (ListenerUtil.mutListener.listen(26023) ? (newLength / 1) : (ListenerUtil.mutListener.listen(26022) ? (newLength * 1) : (ListenerUtil.mutListener.listen(26021) ? (newLength + 1) : (newLength - 1)))))))) : ((ListenerUtil.mutListener.listen(26020) ? (newLength >= 0) : (ListenerUtil.mutListener.listen(26019) ? (newLength <= 0) : (ListenerUtil.mutListener.listen(26018) ? (newLength < 0) : (ListenerUtil.mutListener.listen(26017) ? (newLength != 0) : (ListenerUtil.mutListener.listen(26016) ? (newLength == 0) : (newLength > 0)))))) && Character.isWhitespace(s.charAt((ListenerUtil.mutListener.listen(26024) ? (newLength % 1) : (ListenerUtil.mutListener.listen(26023) ? (newLength / 1) : (ListenerUtil.mutListener.listen(26022) ? (newLength * 1) : (ListenerUtil.mutListener.listen(26021) ? (newLength + 1) : (newLength - 1)))))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter698", ++_loopCounter698);
                    if (!ListenerUtil.mutListener.listen(26015)) {
                        newLength--;
                    }
                }
            }
        }
        return (ListenerUtil.mutListener.listen(26031) ? (newLength >= s.length()) : (ListenerUtil.mutListener.listen(26030) ? (newLength <= s.length()) : (ListenerUtil.mutListener.listen(26029) ? (newLength > s.length()) : (ListenerUtil.mutListener.listen(26028) ? (newLength != s.length()) : (ListenerUtil.mutListener.listen(26027) ? (newLength == s.length()) : (newLength < s.length())))))) ? s.substring(0, newLength) : s;
    }
}

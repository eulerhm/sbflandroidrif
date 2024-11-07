/*
 * Copyright (C) 2011 individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.util;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class MyTextUtils {

    /**
     * Converts a string to title casing.
     *
     * @param str The string to convert.
     * @return The converted string.
     */
    public static String toTitleCase(String str) {
        if (!ListenerUtil.mutListener.listen(7677)) {
            if (str == null) {
                return null;
            }
        }
        boolean isSeparator = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();
        if (!ListenerUtil.mutListener.listen(7689)) {
            {
                long _loopCounter93 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(7688) ? (i >= len) : (ListenerUtil.mutListener.listen(7687) ? (i <= len) : (ListenerUtil.mutListener.listen(7686) ? (i > len) : (ListenerUtil.mutListener.listen(7685) ? (i != len) : (ListenerUtil.mutListener.listen(7684) ? (i == len) : (i < len)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter93", ++_loopCounter93);
                    char c = builder.charAt(i);
                    if (!ListenerUtil.mutListener.listen(7683)) {
                        if (isSeparator) {
                            if (!ListenerUtil.mutListener.listen(7682)) {
                                if (Character.isLetterOrDigit(c)) {
                                    if (!ListenerUtil.mutListener.listen(7680)) {
                                        // Convert to title case and switch out of whitespace mode.
                                        builder.setCharAt(i, Character.toTitleCase(c));
                                    }
                                    if (!ListenerUtil.mutListener.listen(7681)) {
                                        isSeparator = false;
                                    }
                                }
                            }
                        } else if (!Character.isLetterOrDigit(c)) {
                            if (!ListenerUtil.mutListener.listen(7679)) {
                                isSeparator = true;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7678)) {
                                builder.setCharAt(i, Character.toLowerCase(c));
                            }
                        }
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * Returns true if the provided string is all caps, and false if it is not
     *
     * @param str
     * @return true if the provided string is all caps, and false if it is not
     */
    public static boolean isAllCaps(String str) {
        return str.equals(str.toUpperCase());
    }

    /**
     * Converts the given string to sentence case, where the first
     * letter is capitalized and the rest of the string is in
     * lower case.
     *
     * @param inputVal The string to convert.
     * @return The converted string.
     */
    public static String toSentenceCase(String inputVal) {
        if (!ListenerUtil.mutListener.listen(7690)) {
            if (inputVal == null)
                return null;
        }
        if (!ListenerUtil.mutListener.listen(7696)) {
            if ((ListenerUtil.mutListener.listen(7695) ? (inputVal.length() >= 0) : (ListenerUtil.mutListener.listen(7694) ? (inputVal.length() <= 0) : (ListenerUtil.mutListener.listen(7693) ? (inputVal.length() > 0) : (ListenerUtil.mutListener.listen(7692) ? (inputVal.length() < 0) : (ListenerUtil.mutListener.listen(7691) ? (inputVal.length() != 0) : (inputVal.length() == 0)))))))
                return "";
        }
        if (!ListenerUtil.mutListener.listen(7702)) {
            if ((ListenerUtil.mutListener.listen(7701) ? (inputVal.length() >= 1) : (ListenerUtil.mutListener.listen(7700) ? (inputVal.length() <= 1) : (ListenerUtil.mutListener.listen(7699) ? (inputVal.length() > 1) : (ListenerUtil.mutListener.listen(7698) ? (inputVal.length() < 1) : (ListenerUtil.mutListener.listen(7697) ? (inputVal.length() != 1) : (inputVal.length() == 1)))))))
                return inputVal.toUpperCase();
        }
        return inputVal.substring(0, 1).toUpperCase() + inputVal.substring(1).toLowerCase();
    }
}

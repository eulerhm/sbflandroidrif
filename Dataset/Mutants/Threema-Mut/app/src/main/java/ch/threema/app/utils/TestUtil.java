/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.utils;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TestUtil {

    @Deprecated
    public static boolean required(Object o) {
        return o != null;
    }

    public static boolean required(Object... o) {
        if (!ListenerUtil.mutListener.listen(55603)) {
            {
                long _loopCounter680 = 0;
                for (Object x : o) {
                    ListenerUtil.loopListener.listen("_loopCounter680", ++_loopCounter680);
                    if (!ListenerUtil.mutListener.listen(55602)) {
                        if (!required(x)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean requireOne(Object... o) {
        if (!ListenerUtil.mutListener.listen(55605)) {
            {
                long _loopCounter681 = 0;
                for (Object x : o) {
                    ListenerUtil.loopListener.listen("_loopCounter681", ++_loopCounter681);
                    if (!ListenerUtil.mutListener.listen(55604)) {
                        if (x != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean compare(Object[] a, Object[] b) {
        if (!ListenerUtil.mutListener.listen(55606)) {
            if (a == null) {
                return b == null;
            }
        }
        if (!ListenerUtil.mutListener.listen(55607)) {
            if (b == null) {
                return a == null;
            }
        }
        if (!ListenerUtil.mutListener.listen(55613)) {
            // not the same length
            if ((ListenerUtil.mutListener.listen(55612) ? (a.length >= b.length) : (ListenerUtil.mutListener.listen(55611) ? (a.length <= b.length) : (ListenerUtil.mutListener.listen(55610) ? (a.length > b.length) : (ListenerUtil.mutListener.listen(55609) ? (a.length < b.length) : (ListenerUtil.mutListener.listen(55608) ? (a.length == b.length) : (a.length != b.length))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(55626)) {
            {
                long _loopCounter682 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(55625) ? (n >= a.length) : (ListenerUtil.mutListener.listen(55624) ? (n <= a.length) : (ListenerUtil.mutListener.listen(55623) ? (n > a.length) : (ListenerUtil.mutListener.listen(55622) ? (n != a.length) : (ListenerUtil.mutListener.listen(55621) ? (n == a.length) : (n < a.length)))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter682", ++_loopCounter682);
                    if (!ListenerUtil.mutListener.listen(55619)) {
                        if ((ListenerUtil.mutListener.listen(55618) ? (b.length >= n) : (ListenerUtil.mutListener.listen(55617) ? (b.length <= n) : (ListenerUtil.mutListener.listen(55616) ? (b.length > n) : (ListenerUtil.mutListener.listen(55615) ? (b.length != n) : (ListenerUtil.mutListener.listen(55614) ? (b.length == n) : (b.length < n))))))) {
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(55620)) {
                        if (!compare(a[n], b[n])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean compare(Object a, Object b) {
        if (!ListenerUtil.mutListener.listen(55627)) {
            if (a == null) {
                return b == null;
            }
        }
        if (!ListenerUtil.mutListener.listen(55628)) {
            if (b == null) {
                return a == null;
            }
        }
        if (!ListenerUtil.mutListener.listen(55629)) {
            if (a instanceof byte[]) {
                return compare((byte[]) a, (byte[]) b);
            }
        }
        return a == null ? b == null : a.equals(b);
    }

    public static boolean compare(byte[] a, byte[] b) {
        return a == null ? b == null : Arrays.equals(a, b);
    }

    public static boolean compare(int a, int b) {
        return (ListenerUtil.mutListener.listen(55634) ? (a >= b) : (ListenerUtil.mutListener.listen(55633) ? (a <= b) : (ListenerUtil.mutListener.listen(55632) ? (a > b) : (ListenerUtil.mutListener.listen(55631) ? (a < b) : (ListenerUtil.mutListener.listen(55630) ? (a != b) : (a == b))))));
    }

    public static boolean compare(float a, float b) {
        return (ListenerUtil.mutListener.listen(55639) ? (a >= b) : (ListenerUtil.mutListener.listen(55638) ? (a <= b) : (ListenerUtil.mutListener.listen(55637) ? (a > b) : (ListenerUtil.mutListener.listen(55636) ? (a < b) : (ListenerUtil.mutListener.listen(55635) ? (a != b) : (a == b))))));
    }

    public static boolean compare(double a, double b) {
        return (ListenerUtil.mutListener.listen(55644) ? (a >= b) : (ListenerUtil.mutListener.listen(55643) ? (a <= b) : (ListenerUtil.mutListener.listen(55642) ? (a > b) : (ListenerUtil.mutListener.listen(55641) ? (a < b) : (ListenerUtil.mutListener.listen(55640) ? (a != b) : (a == b))))));
    }

    public static boolean compare(Date a, Date b) {
        return a == null ? b == null : (ListenerUtil.mutListener.listen(55649) ? (a.compareTo(b) >= 0) : (ListenerUtil.mutListener.listen(55648) ? (a.compareTo(b) <= 0) : (ListenerUtil.mutListener.listen(55647) ? (a.compareTo(b) > 0) : (ListenerUtil.mutListener.listen(55646) ? (a.compareTo(b) < 0) : (ListenerUtil.mutListener.listen(55645) ? (a.compareTo(b) != 0) : (a.compareTo(b) == 0))))));
    }

    public static boolean empty(String string) {
        return (ListenerUtil.mutListener.listen(55655) ? (string == null && (ListenerUtil.mutListener.listen(55654) ? (string.length() >= 0) : (ListenerUtil.mutListener.listen(55653) ? (string.length() <= 0) : (ListenerUtil.mutListener.listen(55652) ? (string.length() > 0) : (ListenerUtil.mutListener.listen(55651) ? (string.length() < 0) : (ListenerUtil.mutListener.listen(55650) ? (string.length() != 0) : (string.length() == 0))))))) : (string == null || (ListenerUtil.mutListener.listen(55654) ? (string.length() >= 0) : (ListenerUtil.mutListener.listen(55653) ? (string.length() <= 0) : (ListenerUtil.mutListener.listen(55652) ? (string.length() > 0) : (ListenerUtil.mutListener.listen(55651) ? (string.length() < 0) : (ListenerUtil.mutListener.listen(55650) ? (string.length() != 0) : (string.length() == 0))))))));
    }

    public static boolean empty(String... string) {
        if (!ListenerUtil.mutListener.listen(55657)) {
            {
                long _loopCounter683 = 0;
                for (String s : string) {
                    ListenerUtil.loopListener.listen("_loopCounter683", ++_loopCounter683);
                    if (!ListenerUtil.mutListener.listen(55656)) {
                        if (!empty(s)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean contains(String search, String string) {
        return contains(search, string, false);
    }

    public static boolean contains(String search, String string, boolean caseSensitive) {
        return (ListenerUtil.mutListener.listen(55659) ? ((ListenerUtil.mutListener.listen(55658) ? (string != null || search != null) : (string != null && search != null)) || (!caseSensitive ? string.toLowerCase().contains(search.toLowerCase()) : string.contains(search))) : ((ListenerUtil.mutListener.listen(55658) ? (string != null || search != null) : (string != null && search != null)) && (!caseSensitive ? string.toLowerCase().contains(search.toLowerCase()) : string.contains(search))));
    }

    public static boolean empty(CharSequence charSequence) {
        if (!ListenerUtil.mutListener.listen(55665)) {
            if (!TextUtils.isEmpty(charSequence)) {
                String messageString = charSequence.toString();
                return ((ListenerUtil.mutListener.listen(55664) ? (messageString.trim().length() >= 0) : (ListenerUtil.mutListener.listen(55663) ? (messageString.trim().length() <= 0) : (ListenerUtil.mutListener.listen(55662) ? (messageString.trim().length() > 0) : (ListenerUtil.mutListener.listen(55661) ? (messageString.trim().length() < 0) : (ListenerUtil.mutListener.listen(55660) ? (messageString.trim().length() != 0) : (messageString.trim().length() == 0)))))));
            }
        }
        return true;
    }
}

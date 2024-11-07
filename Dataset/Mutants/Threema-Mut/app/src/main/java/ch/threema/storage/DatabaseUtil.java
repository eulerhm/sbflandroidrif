/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.storage;

import net.sqlcipher.Cursor;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DatabaseUtil {

    public static Long getDateTimeContentValue(Date date) {
        return date != null ? date.getTime() : null;
    }

    public static Date getDateFromValue(Long timestamp) {
        if (!ListenerUtil.mutListener.listen(71591)) {
            if (timestamp != null) {
                return new Date(timestamp);
            }
        }
        return null;
    }

    public static String makePlaceholders(int len) {
        if ((ListenerUtil.mutListener.listen(71596) ? (len >= 1) : (ListenerUtil.mutListener.listen(71595) ? (len <= 1) : (ListenerUtil.mutListener.listen(71594) ? (len > 1) : (ListenerUtil.mutListener.listen(71593) ? (len != 1) : (ListenerUtil.mutListener.listen(71592) ? (len == 1) : (len < 1))))))) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder((ListenerUtil.mutListener.listen(71604) ? ((ListenerUtil.mutListener.listen(71600) ? (len % 2) : (ListenerUtil.mutListener.listen(71599) ? (len / 2) : (ListenerUtil.mutListener.listen(71598) ? (len - 2) : (ListenerUtil.mutListener.listen(71597) ? (len + 2) : (len * 2))))) % 1) : (ListenerUtil.mutListener.listen(71603) ? ((ListenerUtil.mutListener.listen(71600) ? (len % 2) : (ListenerUtil.mutListener.listen(71599) ? (len / 2) : (ListenerUtil.mutListener.listen(71598) ? (len - 2) : (ListenerUtil.mutListener.listen(71597) ? (len + 2) : (len * 2))))) / 1) : (ListenerUtil.mutListener.listen(71602) ? ((ListenerUtil.mutListener.listen(71600) ? (len % 2) : (ListenerUtil.mutListener.listen(71599) ? (len / 2) : (ListenerUtil.mutListener.listen(71598) ? (len - 2) : (ListenerUtil.mutListener.listen(71597) ? (len + 2) : (len * 2))))) * 1) : (ListenerUtil.mutListener.listen(71601) ? ((ListenerUtil.mutListener.listen(71600) ? (len % 2) : (ListenerUtil.mutListener.listen(71599) ? (len / 2) : (ListenerUtil.mutListener.listen(71598) ? (len - 2) : (ListenerUtil.mutListener.listen(71597) ? (len + 2) : (len * 2))))) + 1) : ((ListenerUtil.mutListener.listen(71600) ? (len % 2) : (ListenerUtil.mutListener.listen(71599) ? (len / 2) : (ListenerUtil.mutListener.listen(71598) ? (len - 2) : (ListenerUtil.mutListener.listen(71597) ? (len + 2) : (len * 2))))) - 1))))));
            if (!ListenerUtil.mutListener.listen(71605)) {
                sb.append("?");
            }
            if (!ListenerUtil.mutListener.listen(71612)) {
                {
                    long _loopCounter931 = 0;
                    for (int i = 1; (ListenerUtil.mutListener.listen(71611) ? (i >= len) : (ListenerUtil.mutListener.listen(71610) ? (i <= len) : (ListenerUtil.mutListener.listen(71609) ? (i > len) : (ListenerUtil.mutListener.listen(71608) ? (i != len) : (ListenerUtil.mutListener.listen(71607) ? (i == len) : (i < len)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter931", ++_loopCounter931);
                        if (!ListenerUtil.mutListener.listen(71606)) {
                            sb.append(",?");
                        }
                    }
                }
            }
            return sb.toString();
        }
    }

    /**
     *  only for a select count(*) result, the first column must be the count value
     *  @param c
     *  @return
     */
    public static long count(Cursor c) {
        long count = 0;
        if (!ListenerUtil.mutListener.listen(71616)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(71615)) {
                        if (c.moveToFirst()) {
                            if (!ListenerUtil.mutListener.listen(71614)) {
                                count = c.getLong(0);
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(71613)) {
                        c.close();
                    }
                }
            }
        }
        return count;
    }

    /**
     *  Convert a array of Objects to a valid argument String array
     *
     *  @param objectList
     *  @return
     */
    public static <T> String[] convertArguments(List<T> objectList) {
        String[] arguments = new String[objectList.size()];
        if (!ListenerUtil.mutListener.listen(71623)) {
            {
                long _loopCounter932 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(71622) ? (n >= objectList.size()) : (ListenerUtil.mutListener.listen(71621) ? (n <= objectList.size()) : (ListenerUtil.mutListener.listen(71620) ? (n > objectList.size()) : (ListenerUtil.mutListener.listen(71619) ? (n != objectList.size()) : (ListenerUtil.mutListener.listen(71618) ? (n == objectList.size()) : (n < objectList.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter932", ++_loopCounter932);
                    if (!ListenerUtil.mutListener.listen(71617)) {
                        arguments[n] = String.valueOf(objectList.get(n));
                    }
                }
            }
        }
        return arguments;
    }
}

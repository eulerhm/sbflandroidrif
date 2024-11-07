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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handling NULL Values and Support Date (as Long) fields
 */
public class CursorHelper {

    private final net.sqlcipher.Cursor cursor;

    private final ColumnIndexCache columnIndexCache;

    // SimpleDateFormat is not thread-safe, so give one to each thread
    public static final ThreadLocal<SimpleDateFormat> dateAsStringFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        }
    };

    public interface Callback {

        /**
         *  @return return false to stop the iteration
         */
        boolean next(CursorHelper cursorFactory);
    }

    public interface CallbackInstance<T> {

        T next(CursorHelper cursorHelper);
    }

    public CursorHelper(net.sqlcipher.Cursor cursor, ColumnIndexCache columnIndexCache) {
        this.cursor = cursor;
        this.columnIndexCache = columnIndexCache;
    }

    public CursorHelper first(Callback callback) {
        if (!ListenerUtil.mutListener.listen(71023)) {
            if ((ListenerUtil.mutListener.listen(71020) ? (callback != null || this.cursor != null) : (callback != null && this.cursor != null))) {
                if (!ListenerUtil.mutListener.listen(71022)) {
                    if (this.cursor.moveToFirst()) {
                        if (!ListenerUtil.mutListener.listen(71021)) {
                            callback.next(this);
                        }
                    }
                }
            }
        }
        return this;
    }

    public CursorHelper current(Callback callback) {
        if (!ListenerUtil.mutListener.listen(71026)) {
            if ((ListenerUtil.mutListener.listen(71024) ? (callback != null || this.cursor != null) : (callback != null && this.cursor != null))) {
                if (!ListenerUtil.mutListener.listen(71025)) {
                    callback.next(this);
                }
            }
        }
        return this;
    }

    public <T> T current(CallbackInstance<T> callback) {
        if (!ListenerUtil.mutListener.listen(71028)) {
            if ((ListenerUtil.mutListener.listen(71027) ? (callback != null || this.cursor != null) : (callback != null && this.cursor != null))) {
                return callback.next(this);
            }
        }
        return null;
    }

    public CursorHelper each(Callback callback) {
        if (!ListenerUtil.mutListener.listen(71032)) {
            if ((ListenerUtil.mutListener.listen(71029) ? (callback != null || this.cursor != null) : (callback != null && this.cursor != null))) {
                if (!ListenerUtil.mutListener.listen(71031)) {
                    {
                        long _loopCounter928 = 0;
                        while (cursor.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter928", ++_loopCounter928);
                            if (!ListenerUtil.mutListener.listen(71030)) {
                                if (!callback.next(this)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return this;
    }

    public Integer getInt(String columnName) {
        Integer index = this.i(columnName);
        if (!ListenerUtil.mutListener.listen(71033)) {
            if (index != null) {
                return this.cursor.getInt(index);
            }
        }
        return null;
    }

    public Long getLong(String columnName) {
        Integer index = this.i(columnName);
        if (!ListenerUtil.mutListener.listen(71034)) {
            if (index != null) {
                return this.cursor.getLong(index);
            }
        }
        return null;
    }

    public String getString(String columnName) {
        Integer index = this.i(columnName);
        if (!ListenerUtil.mutListener.listen(71035)) {
            if (index != null) {
                return this.cursor.getString(index);
            }
        }
        return null;
    }

    public boolean getBoolean(String columnName) {
        Integer v = this.getInt(columnName);
        return (ListenerUtil.mutListener.listen(71041) ? (v != null || (ListenerUtil.mutListener.listen(71040) ? (v >= 1) : (ListenerUtil.mutListener.listen(71039) ? (v <= 1) : (ListenerUtil.mutListener.listen(71038) ? (v > 1) : (ListenerUtil.mutListener.listen(71037) ? (v < 1) : (ListenerUtil.mutListener.listen(71036) ? (v != 1) : (v == 1))))))) : (v != null && (ListenerUtil.mutListener.listen(71040) ? (v >= 1) : (ListenerUtil.mutListener.listen(71039) ? (v <= 1) : (ListenerUtil.mutListener.listen(71038) ? (v > 1) : (ListenerUtil.mutListener.listen(71037) ? (v < 1) : (ListenerUtil.mutListener.listen(71036) ? (v != 1) : (v == 1))))))));
    }

    public Date getDate(String columnName) {
        Long v = this.getLong(columnName);
        if (!ListenerUtil.mutListener.listen(71042)) {
            if (v != null) {
                return new Date(v);
            }
        }
        return null;
    }

    public Date getDateByString(String columnName) {
        String s = this.getString(columnName);
        if (!ListenerUtil.mutListener.listen(71043)) {
            if (s != null) {
                try {
                    return dateAsStringFormat.get().parse(s);
                } catch (ParseException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public byte[] getBlob(String columnName) {
        Integer index = this.i(columnName);
        if (!ListenerUtil.mutListener.listen(71044)) {
            if (index != null) {
                return this.cursor.getBlob(index);
            }
        }
        return null;
    }

    public void close() {
        if (!ListenerUtil.mutListener.listen(71046)) {
            if (this.cursor != null) {
                if (!ListenerUtil.mutListener.listen(71045)) {
                    this.cursor.close();
                }
            }
        }
    }

    private Integer i(String columnName) {
        if (!ListenerUtil.mutListener.listen(71054)) {
            if (this.cursor != null) {
                int i = this.columnIndexCache.getColumnIndex(this.cursor, columnName);
                if (!ListenerUtil.mutListener.listen(71053)) {
                    if ((ListenerUtil.mutListener.listen(71052) ? ((ListenerUtil.mutListener.listen(71051) ? (i <= 0) : (ListenerUtil.mutListener.listen(71050) ? (i > 0) : (ListenerUtil.mutListener.listen(71049) ? (i < 0) : (ListenerUtil.mutListener.listen(71048) ? (i != 0) : (ListenerUtil.mutListener.listen(71047) ? (i == 0) : (i >= 0)))))) || !this.cursor.isNull(i)) : ((ListenerUtil.mutListener.listen(71051) ? (i <= 0) : (ListenerUtil.mutListener.listen(71050) ? (i > 0) : (ListenerUtil.mutListener.listen(71049) ? (i < 0) : (ListenerUtil.mutListener.listen(71048) ? (i != 0) : (ListenerUtil.mutListener.listen(71047) ? (i == 0) : (i >= 0)))))) && !this.cursor.isNull(i)))) {
                        return i;
                    }
                }
            }
        }
        return null;
    }
}

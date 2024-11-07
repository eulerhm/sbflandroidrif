/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Convert Json to X and X to Json
 */
public class ListReader {

    private final List<Object> list;

    private int pos = 0;

    public ListReader(List<Object> list) {
        this.list = list;
    }

    public ListReader rewind() {
        if (!ListenerUtil.mutListener.listen(54621)) {
            this.pos = 0;
        }
        return this;
    }

    public String nextString() {
        return (String) this.next();
    }

    public byte[] nextStringAsByteArray() {
        String v = this.nextString();
        if (!ListenerUtil.mutListener.listen(54628)) {
            if ((ListenerUtil.mutListener.listen(54627) ? (v != null || (ListenerUtil.mutListener.listen(54626) ? (v.length() >= 0) : (ListenerUtil.mutListener.listen(54625) ? (v.length() <= 0) : (ListenerUtil.mutListener.listen(54624) ? (v.length() < 0) : (ListenerUtil.mutListener.listen(54623) ? (v.length() != 0) : (ListenerUtil.mutListener.listen(54622) ? (v.length() == 0) : (v.length() > 0))))))) : (v != null && (ListenerUtil.mutListener.listen(54626) ? (v.length() >= 0) : (ListenerUtil.mutListener.listen(54625) ? (v.length() <= 0) : (ListenerUtil.mutListener.listen(54624) ? (v.length() < 0) : (ListenerUtil.mutListener.listen(54623) ? (v.length() != 0) : (ListenerUtil.mutListener.listen(54622) ? (v.length() == 0) : (v.length() > 0))))))))) {
                return Utils.hexStringToByteArray(v);
            }
        }
        return null;
    }

    public Integer nextInteger() {
        return (Integer) this.next();
    }

    public Boolean nextBool() {
        return (Boolean) this.next();
    }

    public Map<String, Object> nextMap() {
        Object n = this.next();
        if (!ListenerUtil.mutListener.listen(54629)) {
            if (n instanceof Map) {
                return (Map<String, Object>) n;
            }
        }
        return null;
    }

    private Object next() {
        if (!ListenerUtil.mutListener.listen(54635)) {
            if ((ListenerUtil.mutListener.listen(54634) ? (this.list.size() >= this.pos) : (ListenerUtil.mutListener.listen(54633) ? (this.list.size() <= this.pos) : (ListenerUtil.mutListener.listen(54632) ? (this.list.size() < this.pos) : (ListenerUtil.mutListener.listen(54631) ? (this.list.size() != this.pos) : (ListenerUtil.mutListener.listen(54630) ? (this.list.size() == this.pos) : (this.list.size() > this.pos))))))) {
                return this.list.get(this.pos++);
            }
        }
        return null;
    }
}

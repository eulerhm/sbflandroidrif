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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Convert Json to X and X to Json
 */
public class JsonUtil {

    public static List<Object> convertArray(String jsonArrayInputString) throws JSONException {
        JSONArray ja = new JSONArray(jsonArrayInputString);
        if (!ListenerUtil.mutListener.listen(54532)) {
            if (ja != null) {
                return convert(ja);
            }
        }
        return null;
    }

    public static List<Object> convert(JSONArray jsonArray) {
        List<Object> l = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(54539)) {
            {
                long _loopCounter663 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(54538) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(54537) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(54536) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(54535) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(54534) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter663", ++_loopCounter663);
                    try {
                        if (!ListenerUtil.mutListener.listen(54533)) {
                            l.add(jsonArray.isNull(i) ? null : convert(jsonArray.get(i)));
                        }
                    } catch (JSONException e) {
                    }
                }
            }
        }
        return l;
    }

    public static Map<String, Object> convertObject(String jsonObjectInputString) throws JSONException {
        JSONObject jo = new JSONObject(jsonObjectInputString);
        if (!ListenerUtil.mutListener.listen(54540)) {
            if (jo != null) {
                return convert(jo);
            }
        }
        return null;
    }

    public static Map<String, Object> convert(JSONObject jsonObjectInput) {
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keys = jsonObjectInput.keys();
        if (!ListenerUtil.mutListener.listen(54542)) {
            {
                long _loopCounter664 = 0;
                while (keys.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter664", ++_loopCounter664);
                    String key = keys.next();
                    try {
                        if (!ListenerUtil.mutListener.listen(54541)) {
                            map.put(key, jsonObjectInput.isNull(key) ? null : convert(jsonObjectInput.get(key)));
                        }
                    } catch (JSONException e) {
                    }
                }
            }
        }
        return map;
    }

    private static Object convert(Object input) {
        if (!ListenerUtil.mutListener.listen(54543)) {
            if (input instanceof JSONArray) {
                return convert((JSONArray) input);
            } else if (input instanceof JSONObject) {
                return convert((JSONObject) input);
            }
        }
        return input;
    }
}

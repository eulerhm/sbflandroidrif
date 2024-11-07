/*  
 *  Copyright (c) 2020 Arthur Milchior <arthur@milchior.fr>
 *  
 *  This file is free software: you may copy, redistribute and/or modify it  
 *  under the terms of the GNU General Public License as published by the  
 *  Free Software Foundation, either version 3 of the License, or (at your  
 *  option) any later version.  
 *  
 *  This file is distributed in the hope that it will be useful, but  
 *  WITHOUT ANY WARRANTY; without even the implied warranty of  
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU  
 *  General Public License for more details.  
 *  
 *  You should have received a copy of the GNU General Public License  
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  This file incorporates work covered by the following copyright and  
 *  permission notice:  
 *  
 *    Copyright (c) 2002 JSON.org
 *    
 *    Permission is hereby granted, free of charge, to any person obtaining a copy
 *    of this software and associated documentation files (the "Software"), to deal
 *    in the Software without restriction, including without limitation the rights
 *    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *    copies of the Software, and to permit persons to whom the Software is
 *    furnished to do so, subject to the following conditions:
 *   
 *    The above copyright notice and this permission notice shall be included in all
 *    copies or substantial portions of the Software.
 *   
 *    The Software shall be used for Good, not Evil.
 *
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *    SOFTWARE. 
 */
package com.ichi2.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class JSONArray extends org.json.JSONArray {

    public JSONArray() {
        super();
    }

    public JSONArray(org.json.JSONArray copyFrom) {
        try {
            if (!ListenerUtil.mutListener.listen(25791)) {
                {
                    long _loopCounter681 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(25790) ? (i >= copyFrom.length()) : (ListenerUtil.mutListener.listen(25789) ? (i <= copyFrom.length()) : (ListenerUtil.mutListener.listen(25788) ? (i > copyFrom.length()) : (ListenerUtil.mutListener.listen(25787) ? (i != copyFrom.length()) : (ListenerUtil.mutListener.listen(25786) ? (i == copyFrom.length()) : (i < copyFrom.length())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter681", ++_loopCounter681);
                        if (!ListenerUtil.mutListener.listen(25785)) {
                            put(i, copyFrom.get(i));
                        }
                    }
                }
            }
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    /**
     * This method simply change the type.
     *
     * See the comment of objectToObject to read about the problems met here.
     *
     * @param ar Actually a JSONArray
     * @return the same element as input. But considered as a JSONArray.
     */
    public static JSONArray arrayToArray(org.json.JSONArray ar) {
        return (JSONArray) ar;
    }

    public JSONArray(JSONTokener x) {
        this();
        try {
            if (!ListenerUtil.mutListener.listen(25792)) {
                if (x.nextClean() != '[') {
                    throw x.syntaxError("A JSONArray text must start with '['");
                }
            }
            char nextChar = x.nextClean();
            if (!ListenerUtil.mutListener.listen(25793)) {
                if (nextChar == 0) {
                    // array is unclosed. No ']' found, instead EOF
                    throw x.syntaxError("Expected a ',' or ']'");
                }
            }
            if (!ListenerUtil.mutListener.listen(25806)) {
                if (nextChar != ']') {
                    if (!ListenerUtil.mutListener.listen(25794)) {
                        x.back();
                    }
                    if (!ListenerUtil.mutListener.listen(25805)) {
                        {
                            long _loopCounter682 = 0;
                            for (; ; ) {
                                ListenerUtil.loopListener.listen("_loopCounter682", ++_loopCounter682);
                                if (!ListenerUtil.mutListener.listen(25799)) {
                                    if (x.nextClean() == ',') {
                                        if (!ListenerUtil.mutListener.listen(25797)) {
                                            x.back();
                                        }
                                        if (!ListenerUtil.mutListener.listen(25798)) {
                                            put(JSONObject.NULL);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(25795)) {
                                            x.back();
                                        }
                                        if (!ListenerUtil.mutListener.listen(25796)) {
                                            put(x.nextValue());
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(25804)) {
                                    switch(x.nextClean()) {
                                        case 0:
                                            // array is unclosed. No ']' found, instead EOF
                                            throw x.syntaxError("Expected a ',' or ']'");
                                        case ',':
                                            if (!ListenerUtil.mutListener.listen(25800)) {
                                                nextChar = x.nextClean();
                                            }
                                            if (!ListenerUtil.mutListener.listen(25801)) {
                                                if (nextChar == 0) {
                                                    // array is unclosed. No ']' found, instead EOF
                                                    throw x.syntaxError("Expected a ',' or ']'");
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(25802)) {
                                                if (nextChar == ']') {
                                                    return;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(25803)) {
                                                x.back();
                                            }
                                            break;
                                        case ']':
                                            return;
                                        default:
                                            throw x.syntaxError("Expected a ',' or ']'");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public JSONArray(String source) {
        this(new JSONTokener(source));
    }

    public JSONArray(Object array) {
        this();
        if (!ListenerUtil.mutListener.listen(25814)) {
            if (array.getClass().isArray()) {
                int length = Array.getLength(array);
                if (!ListenerUtil.mutListener.listen(25813)) {
                    {
                        long _loopCounter683 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(25812) ? (i >= length) : (ListenerUtil.mutListener.listen(25811) ? (i <= length) : (ListenerUtil.mutListener.listen(25810) ? (i > length) : (ListenerUtil.mutListener.listen(25809) ? (i != length) : (ListenerUtil.mutListener.listen(25808) ? (i == length) : (i < length)))))); i += 1) {
                            ListenerUtil.loopListener.listen("_loopCounter683", ++_loopCounter683);
                            if (!ListenerUtil.mutListener.listen(25807)) {
                                this.put(Array.get(array, i));
                            }
                        }
                    }
                }
            } else {
                throw new JSONException("JSONArray initial value should be a string or collection or array.");
            }
        }
    }

    public JSONArray(Collection<?> copyFrom) {
        this();
        if (!ListenerUtil.mutListener.listen(25817)) {
            if (copyFrom != null) {
                if (!ListenerUtil.mutListener.listen(25816)) {
                    {
                        long _loopCounter684 = 0;
                        for (Object o : copyFrom) {
                            ListenerUtil.loopListener.listen("_loopCounter684", ++_loopCounter684);
                            if (!ListenerUtil.mutListener.listen(25815)) {
                                put(o);
                            }
                        }
                    }
                }
            }
        }
    }

    public JSONArray put(double value) {
        try {
            if (!ListenerUtil.mutListener.listen(25818)) {
                super.put(value);
            }
            return this;
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public JSONArray put(int index, boolean value) {
        try {
            if (!ListenerUtil.mutListener.listen(25819)) {
                super.put(index, value);
            }
            return this;
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public JSONArray put(int index, double value) {
        try {
            if (!ListenerUtil.mutListener.listen(25820)) {
                super.put(index, value);
            }
            return this;
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public JSONArray put(int index, int value) {
        try {
            if (!ListenerUtil.mutListener.listen(25821)) {
                super.put(index, value);
            }
            return this;
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public JSONArray put(int index, long value) {
        try {
            if (!ListenerUtil.mutListener.listen(25822)) {
                super.put(index, value);
            }
            return this;
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public JSONArray put(int index, Object value) {
        try {
            if (!ListenerUtil.mutListener.listen(25823)) {
                super.put(index, value);
            }
            return this;
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public Object get(int index) {
        try {
            return super.get(index);
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public boolean getBoolean(int index) {
        try {
            return super.getBoolean(index);
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public double getDouble(int index) {
        try {
            return super.getDouble(index);
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public int getInt(int index) {
        try {
            return super.getInt(index);
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public long getLong(int index) {
        try {
            return super.getLong(index);
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public String getString(int index) {
        try {
            return super.getString(index);
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public JSONArray getJSONArray(int pos) {
        try {
            return arrayToArray(super.getJSONArray(pos));
        } catch (org.json.JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getJSONObject(int pos) {
        try {
            return JSONObject.objectToObject(super.getJSONObject(pos));
        } catch (org.json.JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String join(String separator) {
        try {
            return super.join(separator);
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    @NonNull
    public String toString(int indentSpaces) {
        try {
            return super.toString(indentSpaces);
        } catch (org.json.JSONException e) {
            throw new JSONException(e);
        }
    }

    public JSONArray deepClone() {
        JSONArray clone = new JSONArray();
        if (!ListenerUtil.mutListener.listen(25833)) {
            {
                long _loopCounter685 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(25832) ? (i >= length()) : (ListenerUtil.mutListener.listen(25831) ? (i <= length()) : (ListenerUtil.mutListener.listen(25830) ? (i > length()) : (ListenerUtil.mutListener.listen(25829) ? (i != length()) : (ListenerUtil.mutListener.listen(25828) ? (i == length()) : (i < length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter685", ++_loopCounter685);
                    if (!ListenerUtil.mutListener.listen(25827)) {
                        if (get(i) instanceof JSONObject) {
                            if (!ListenerUtil.mutListener.listen(25826)) {
                                clone.put(getJSONObject(i).deepClone());
                            }
                        } else if (get(i) instanceof JSONArray) {
                            if (!ListenerUtil.mutListener.listen(25825)) {
                                clone.put(getJSONArray(i).deepClone());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(25824)) {
                                clone.put(get(i));
                            }
                        }
                    }
                }
            }
        }
        return clone;
    }

    public Iterable<JSONArray> jsonArrayIterable() {
        return this::jsonArrayIterator;
    }

    public Iterator<JSONArray> jsonArrayIterator() {
        return new Iterator<JSONArray>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return (ListenerUtil.mutListener.listen(25838) ? (index >= length()) : (ListenerUtil.mutListener.listen(25837) ? (index <= length()) : (ListenerUtil.mutListener.listen(25836) ? (index > length()) : (ListenerUtil.mutListener.listen(25835) ? (index != length()) : (ListenerUtil.mutListener.listen(25834) ? (index == length()) : (index < length()))))));
            }

            @Override
            public JSONArray next() {
                JSONArray array = getJSONArray(index);
                if (!ListenerUtil.mutListener.listen(25839)) {
                    index++;
                }
                return array;
            }
        };
    }

    public Iterable<JSONObject> jsonObjectIterable() {
        return this::jsonObjectIterator;
    }

    public Iterator<JSONObject> jsonObjectIterator() {
        return new Iterator<JSONObject>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return (ListenerUtil.mutListener.listen(25844) ? (index >= length()) : (ListenerUtil.mutListener.listen(25843) ? (index <= length()) : (ListenerUtil.mutListener.listen(25842) ? (index > length()) : (ListenerUtil.mutListener.listen(25841) ? (index != length()) : (ListenerUtil.mutListener.listen(25840) ? (index == length()) : (index < length()))))));
            }

            @Override
            public JSONObject next() {
                JSONObject object = getJSONObject(index);
                if (!ListenerUtil.mutListener.listen(25845)) {
                    index++;
                }
                return object;
            }
        };
    }

    public Iterable<String> stringIterable() {
        return this::stringIterator;
    }

    public Iterator<String> stringIterator() {
        return new Iterator<String>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return (ListenerUtil.mutListener.listen(25850) ? (index >= length()) : (ListenerUtil.mutListener.listen(25849) ? (index <= length()) : (ListenerUtil.mutListener.listen(25848) ? (index > length()) : (ListenerUtil.mutListener.listen(25847) ? (index != length()) : (ListenerUtil.mutListener.listen(25846) ? (index == length()) : (index < length()))))));
            }

            @Override
            public String next() {
                String string = getString(index);
                if (!ListenerUtil.mutListener.listen(25851)) {
                    index++;
                }
                return string;
            }
        };
    }

    public Iterable<Long> longIterable() {
        return this::longIterator;
    }

    public Iterator<Long> longIterator() {
        return new Iterator<Long>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return (ListenerUtil.mutListener.listen(25856) ? (index >= length()) : (ListenerUtil.mutListener.listen(25855) ? (index <= length()) : (ListenerUtil.mutListener.listen(25854) ? (index > length()) : (ListenerUtil.mutListener.listen(25853) ? (index != length()) : (ListenerUtil.mutListener.listen(25852) ? (index == length()) : (index < length()))))));
            }

            @Override
            public Long next() {
                Long long_ = getLong(index);
                if (!ListenerUtil.mutListener.listen(25857)) {
                    index++;
                }
                return long_;
            }
        };
    }

    public List<JSONObject> toJSONObjectList() {
        List<JSONObject> l = new ArrayList<>(length());
        if (!ListenerUtil.mutListener.listen(25859)) {
            {
                long _loopCounter686 = 0;
                for (JSONObject object : jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter686", ++_loopCounter686);
                    if (!ListenerUtil.mutListener.listen(25858)) {
                        l.add(object);
                    }
                }
            }
        }
        return l;
    }

    public List<Long> toLongList() {
        List<Long> l = new ArrayList<>(length());
        if (!ListenerUtil.mutListener.listen(25861)) {
            {
                long _loopCounter687 = 0;
                for (Long object : longIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter687", ++_loopCounter687);
                    if (!ListenerUtil.mutListener.listen(25860)) {
                        l.add(object);
                    }
                }
            }
        }
        return l;
    }

    public List<String> toStringList() {
        List<String> l = new ArrayList<>(length());
        if (!ListenerUtil.mutListener.listen(25863)) {
            {
                long _loopCounter688 = 0;
                for (String object : stringIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter688", ++_loopCounter688);
                    if (!ListenerUtil.mutListener.listen(25862)) {
                        l.add(object);
                    }
                }
            }
        }
        return l;
    }

    /**
     * @return Given an array of objects, return the array of the value with `key`, assuming that they are String.
     * E.g. templates, fields are a JSONArray whose objects have name
     */
    public List<String> toStringList(String key) {
        List<String> l = new ArrayList<>(length());
        if (!ListenerUtil.mutListener.listen(25865)) {
            {
                long _loopCounter689 = 0;
                for (JSONObject object : jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter689", ++_loopCounter689);
                    if (!ListenerUtil.mutListener.listen(25864)) {
                        l.add(object.getString(key));
                    }
                }
            }
        }
        return l;
    }
}

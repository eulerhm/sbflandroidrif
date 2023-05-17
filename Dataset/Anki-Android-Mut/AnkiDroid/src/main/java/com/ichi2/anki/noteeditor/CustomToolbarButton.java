/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki.noteeditor;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import static com.ichi2.libanki.Consts.FIELD_SEPARATOR;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CustomToolbarButton {

    private static final int KEEP_EMPTY_ENTRIES = -1;

    private int mIndex;

    private final String mPrefix;

    private final String mSuffix;

    public CustomToolbarButton(int index, String prefix, String suffix) {
        if (!ListenerUtil.mutListener.listen(2184)) {
            mIndex = index;
        }
        mPrefix = prefix;
        mSuffix = suffix;
    }

    @Nullable
    public static CustomToolbarButton fromString(String s) {
        if (!ListenerUtil.mutListener.listen(2191)) {
            if ((ListenerUtil.mutListener.listen(2190) ? (s == null && (ListenerUtil.mutListener.listen(2189) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(2188) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(2187) ? (s.length() > 0) : (ListenerUtil.mutListener.listen(2186) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(2185) ? (s.length() != 0) : (s.length() == 0))))))) : (s == null || (ListenerUtil.mutListener.listen(2189) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(2188) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(2187) ? (s.length() > 0) : (ListenerUtil.mutListener.listen(2186) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(2185) ? (s.length() != 0) : (s.length() == 0))))))))) {
                return null;
            }
        }
        String[] fields = s.split(FIELD_SEPARATOR, KEEP_EMPTY_ENTRIES);
        if (!ListenerUtil.mutListener.listen(2197)) {
            if ((ListenerUtil.mutListener.listen(2196) ? (fields.length >= 3) : (ListenerUtil.mutListener.listen(2195) ? (fields.length <= 3) : (ListenerUtil.mutListener.listen(2194) ? (fields.length > 3) : (ListenerUtil.mutListener.listen(2193) ? (fields.length < 3) : (ListenerUtil.mutListener.listen(2192) ? (fields.length == 3) : (fields.length != 3))))))) {
                return null;
            }
        }
        int index;
        try {
            index = Integer.parseInt(fields[0]);
        } catch (Exception e) {
            return null;
        }
        return new CustomToolbarButton(index, fields[1], fields[2]);
    }

    @NonNull
    public static ArrayList<CustomToolbarButton> fromStringSet(Set<String> hs) {
        ArrayList<CustomToolbarButton> buttons = new ArrayList<>(hs.size());
        if (!ListenerUtil.mutListener.listen(2200)) {
            {
                long _loopCounter29 = 0;
                for (String s : hs) {
                    ListenerUtil.loopListener.listen("_loopCounter29", ++_loopCounter29);
                    CustomToolbarButton customToolbarButton = CustomToolbarButton.fromString(s);
                    if (!ListenerUtil.mutListener.listen(2199)) {
                        if (customToolbarButton != null) {
                            if (!ListenerUtil.mutListener.listen(2198)) {
                                buttons.add(customToolbarButton);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2201)) {
            Collections.sort(buttons, (o1, o2) -> Integer.compare(o1.getIndex(), o2.getIndex()));
        }
        if (!ListenerUtil.mutListener.listen(2208)) {
            {
                long _loopCounter30 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2207) ? (i >= buttons.size()) : (ListenerUtil.mutListener.listen(2206) ? (i <= buttons.size()) : (ListenerUtil.mutListener.listen(2205) ? (i > buttons.size()) : (ListenerUtil.mutListener.listen(2204) ? (i != buttons.size()) : (ListenerUtil.mutListener.listen(2203) ? (i == buttons.size()) : (i < buttons.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter30", ++_loopCounter30);
                    if (!ListenerUtil.mutListener.listen(2202)) {
                        buttons.get(i).mIndex = i;
                    }
                }
            }
        }
        return buttons;
    }

    public static Set<String> toStringSet(ArrayList<CustomToolbarButton> buttons) {
        HashSet<String> ret = new HashSet<>(buttons.size());
        if (!ListenerUtil.mutListener.listen(2217)) {
            {
                long _loopCounter32 = 0;
                for (CustomToolbarButton b : buttons) {
                    ListenerUtil.loopListener.listen("_loopCounter32", ++_loopCounter32);
                    String[] values = new String[] { Integer.toString(b.mIndex), b.mPrefix, b.mSuffix };
                    if (!ListenerUtil.mutListener.listen(2215)) {
                        {
                            long _loopCounter31 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(2214) ? (i >= values.length) : (ListenerUtil.mutListener.listen(2213) ? (i <= values.length) : (ListenerUtil.mutListener.listen(2212) ? (i > values.length) : (ListenerUtil.mutListener.listen(2211) ? (i != values.length) : (ListenerUtil.mutListener.listen(2210) ? (i == values.length) : (i < values.length)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter31", ++_loopCounter31);
                                if (!ListenerUtil.mutListener.listen(2209)) {
                                    values[i] = values[i].replace(FIELD_SEPARATOR, "");
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2216)) {
                        ret.add(TextUtils.join(FIELD_SEPARATOR, values));
                    }
                }
            }
        }
        return ret;
    }

    public Toolbar.TextFormatter toFormatter() {
        return new Toolbar.TextWrapper(mPrefix, mSuffix);
    }

    public int getIndex() {
        return mIndex;
    }
}

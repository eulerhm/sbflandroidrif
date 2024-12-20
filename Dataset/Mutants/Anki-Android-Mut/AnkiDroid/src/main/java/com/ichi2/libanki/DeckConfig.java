/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 Copyright (c) 2020 Arthur Milchior <Arthur@Milchior.fr>

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
package com.ichi2.libanki;

import com.ichi2.utils.JSONObject;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeckConfig extends JSONObject {

    public DeckConfig(JSONObject json) {
        super(json);
    }

    public DeckConfig(String json) {
        super(json);
    }

    @Nullable
    public static Boolean parseTimer(JSONObject config) {
        // Note: Card.py used != 0, DeckOptions used == 1
        try {
            // #6089 - Anki 2.1.24 changed this to a bool, reverted in 2.1.25.
            return (ListenerUtil.mutListener.listen(22116) ? (config.getInt("timer") >= 0) : (ListenerUtil.mutListener.listen(22115) ? (config.getInt("timer") <= 0) : (ListenerUtil.mutListener.listen(22114) ? (config.getInt("timer") > 0) : (ListenerUtil.mutListener.listen(22113) ? (config.getInt("timer") < 0) : (ListenerUtil.mutListener.listen(22112) ? (config.getInt("timer") == 0) : (config.getInt("timer") != 0))))));
        } catch (Exception e) {
            try {
                return config.getBoolean("timer");
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static boolean parseTimerOpt(JSONObject config, boolean defaultValue) {
        Boolean ret = parseTimer(config);
        if (!ListenerUtil.mutListener.listen(22118)) {
            if (ret == null) {
                if (!ListenerUtil.mutListener.listen(22117)) {
                    ret = defaultValue;
                }
            }
        }
        return ret;
    }
}

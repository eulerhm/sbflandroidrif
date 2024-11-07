/**
 * *************************************************************************************
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContextMenuHelper {

    public static String[] getValuesFromKeys(HashMap<Integer, String> map, int[] keys) {
        String[] values = new String[keys.length];
        if (!ListenerUtil.mutListener.listen(406)) {
            {
                long _loopCounter2 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(405) ? (i >= keys.length) : (ListenerUtil.mutListener.listen(404) ? (i <= keys.length) : (ListenerUtil.mutListener.listen(403) ? (i > keys.length) : (ListenerUtil.mutListener.listen(402) ? (i != keys.length) : (ListenerUtil.mutListener.listen(401) ? (i == keys.length) : (i < keys.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter2", ++_loopCounter2);
                    if (!ListenerUtil.mutListener.listen(400)) {
                        values[i] = map.get(keys[i]);
                    }
                }
            }
        }
        return values;
    }

    public static int[] integerListToArray(ArrayList<Integer> itemIds) {
        int[] intItemIds = new int[itemIds.size()];
        if (!ListenerUtil.mutListener.listen(413)) {
            {
                long _loopCounter3 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(412) ? (i >= itemIds.size()) : (ListenerUtil.mutListener.listen(411) ? (i <= itemIds.size()) : (ListenerUtil.mutListener.listen(410) ? (i > itemIds.size()) : (ListenerUtil.mutListener.listen(409) ? (i != itemIds.size()) : (ListenerUtil.mutListener.listen(408) ? (i == itemIds.size()) : (i < itemIds.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                    if (!ListenerUtil.mutListener.listen(407)) {
                        intItemIds[i] = itemIds.get(i);
                    }
                }
            }
        }
        return intItemIds;
    }
}

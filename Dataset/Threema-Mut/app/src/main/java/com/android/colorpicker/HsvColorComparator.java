/*
 * Copyright (C) 2013 The Android Open Source Project
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
package com.android.colorpicker;

import android.graphics.Color;
import java.util.Comparator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A color comparator which compares based on hue, saturation, and value.
 */
public class HsvColorComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer lhs, Integer rhs) {
        float[] hsv = new float[3];
        if (!ListenerUtil.mutListener.listen(71906)) {
            Color.colorToHSV(lhs, hsv);
        }
        float hue1 = hsv[0];
        float sat1 = hsv[1];
        float val1 = hsv[2];
        float[] hsv2 = new float[3];
        if (!ListenerUtil.mutListener.listen(71907)) {
            Color.colorToHSV(rhs, hsv2);
        }
        float hue2 = hsv2[0];
        float sat2 = hsv2[1];
        float val2 = hsv2[2];
        if (!ListenerUtil.mutListener.listen(71940)) {
            if ((ListenerUtil.mutListener.listen(71912) ? (hue1 >= hue2) : (ListenerUtil.mutListener.listen(71911) ? (hue1 <= hue2) : (ListenerUtil.mutListener.listen(71910) ? (hue1 > hue2) : (ListenerUtil.mutListener.listen(71909) ? (hue1 != hue2) : (ListenerUtil.mutListener.listen(71908) ? (hue1 == hue2) : (hue1 < hue2))))))) {
                return 1;
            } else if ((ListenerUtil.mutListener.listen(71917) ? (hue1 >= hue2) : (ListenerUtil.mutListener.listen(71916) ? (hue1 <= hue2) : (ListenerUtil.mutListener.listen(71915) ? (hue1 < hue2) : (ListenerUtil.mutListener.listen(71914) ? (hue1 != hue2) : (ListenerUtil.mutListener.listen(71913) ? (hue1 == hue2) : (hue1 > hue2))))))) {
                return -1;
            } else {
                if (!ListenerUtil.mutListener.listen(71939)) {
                    if ((ListenerUtil.mutListener.listen(71922) ? (sat1 >= sat2) : (ListenerUtil.mutListener.listen(71921) ? (sat1 <= sat2) : (ListenerUtil.mutListener.listen(71920) ? (sat1 > sat2) : (ListenerUtil.mutListener.listen(71919) ? (sat1 != sat2) : (ListenerUtil.mutListener.listen(71918) ? (sat1 == sat2) : (sat1 < sat2))))))) {
                        return 1;
                    } else if ((ListenerUtil.mutListener.listen(71927) ? (sat1 >= sat2) : (ListenerUtil.mutListener.listen(71926) ? (sat1 <= sat2) : (ListenerUtil.mutListener.listen(71925) ? (sat1 < sat2) : (ListenerUtil.mutListener.listen(71924) ? (sat1 != sat2) : (ListenerUtil.mutListener.listen(71923) ? (sat1 == sat2) : (sat1 > sat2))))))) {
                        return -1;
                    } else {
                        if (!ListenerUtil.mutListener.listen(71938)) {
                            if ((ListenerUtil.mutListener.listen(71932) ? (val1 >= val2) : (ListenerUtil.mutListener.listen(71931) ? (val1 <= val2) : (ListenerUtil.mutListener.listen(71930) ? (val1 > val2) : (ListenerUtil.mutListener.listen(71929) ? (val1 != val2) : (ListenerUtil.mutListener.listen(71928) ? (val1 == val2) : (val1 < val2))))))) {
                                return 1;
                            } else if ((ListenerUtil.mutListener.listen(71937) ? (val1 >= val2) : (ListenerUtil.mutListener.listen(71936) ? (val1 <= val2) : (ListenerUtil.mutListener.listen(71935) ? (val1 < val2) : (ListenerUtil.mutListener.listen(71934) ? (val1 != val2) : (ListenerUtil.mutListener.listen(71933) ? (val1 == val2) : (val1 > val2))))))) {
                                return -1;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}

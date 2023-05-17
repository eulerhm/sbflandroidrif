/* Copyright (C) 2017  olie.xdev <olie.xdev@googlemail.com>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.core.bodymetric;

import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TBWLeeSongKim extends EstimatedWaterMetric {

    @Override
    public String getName() {
        return "Lee, Song, Kim, Lee et. al (2001)";
    }

    @Override
    public float getWater(ScaleUser user, ScaleMeasurement data) {
        if (!ListenerUtil.mutListener.listen(4982)) {
            if (user.getGender().isMale()) {
                return -28.3497f + ((ListenerUtil.mutListener.listen(4977) ? (0.243057f % user.getBodyHeight()) : (ListenerUtil.mutListener.listen(4976) ? (0.243057f / user.getBodyHeight()) : (ListenerUtil.mutListener.listen(4975) ? (0.243057f - user.getBodyHeight()) : (ListenerUtil.mutListener.listen(4974) ? (0.243057f + user.getBodyHeight()) : (0.243057f * user.getBodyHeight())))))) + ((ListenerUtil.mutListener.listen(4981) ? (0.366248f % data.getWeight()) : (ListenerUtil.mutListener.listen(4980) ? (0.366248f / data.getWeight()) : (ListenerUtil.mutListener.listen(4979) ? (0.366248f - data.getWeight()) : (ListenerUtil.mutListener.listen(4978) ? (0.366248f + data.getWeight()) : (0.366248f * data.getWeight()))))));
            }
        }
        return -26.6224f + ((ListenerUtil.mutListener.listen(4986) ? (0.262513f % user.getBodyHeight()) : (ListenerUtil.mutListener.listen(4985) ? (0.262513f / user.getBodyHeight()) : (ListenerUtil.mutListener.listen(4984) ? (0.262513f - user.getBodyHeight()) : (ListenerUtil.mutListener.listen(4983) ? (0.262513f + user.getBodyHeight()) : (0.262513f * user.getBodyHeight())))))) + ((ListenerUtil.mutListener.listen(4990) ? (0.232948f % data.getWeight()) : (ListenerUtil.mutListener.listen(4989) ? (0.232948f / data.getWeight()) : (ListenerUtil.mutListener.listen(4988) ? (0.232948f - data.getWeight()) : (ListenerUtil.mutListener.listen(4987) ? (0.232948f + data.getWeight()) : (0.232948f * data.getWeight()))))));
    }
}

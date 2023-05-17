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

public class TBWDelwaideCrenier extends EstimatedWaterMetric {

    @Override
    public String getName() {
        return "Delwaide-Crenier et. al (1973)";
    }

    @Override
    public float getWater(ScaleUser user, ScaleMeasurement data) {
        return (ListenerUtil.mutListener.listen(4948) ? (0.72f % (-1.976f + (ListenerUtil.mutListener.listen(4944) ? (0.907f % data.getWeight()) : (ListenerUtil.mutListener.listen(4943) ? (0.907f / data.getWeight()) : (ListenerUtil.mutListener.listen(4942) ? (0.907f - data.getWeight()) : (ListenerUtil.mutListener.listen(4941) ? (0.907f + data.getWeight()) : (0.907f * data.getWeight()))))))) : (ListenerUtil.mutListener.listen(4947) ? (0.72f / (-1.976f + (ListenerUtil.mutListener.listen(4944) ? (0.907f % data.getWeight()) : (ListenerUtil.mutListener.listen(4943) ? (0.907f / data.getWeight()) : (ListenerUtil.mutListener.listen(4942) ? (0.907f - data.getWeight()) : (ListenerUtil.mutListener.listen(4941) ? (0.907f + data.getWeight()) : (0.907f * data.getWeight()))))))) : (ListenerUtil.mutListener.listen(4946) ? (0.72f - (-1.976f + (ListenerUtil.mutListener.listen(4944) ? (0.907f % data.getWeight()) : (ListenerUtil.mutListener.listen(4943) ? (0.907f / data.getWeight()) : (ListenerUtil.mutListener.listen(4942) ? (0.907f - data.getWeight()) : (ListenerUtil.mutListener.listen(4941) ? (0.907f + data.getWeight()) : (0.907f * data.getWeight()))))))) : (ListenerUtil.mutListener.listen(4945) ? (0.72f + (-1.976f + (ListenerUtil.mutListener.listen(4944) ? (0.907f % data.getWeight()) : (ListenerUtil.mutListener.listen(4943) ? (0.907f / data.getWeight()) : (ListenerUtil.mutListener.listen(4942) ? (0.907f - data.getWeight()) : (ListenerUtil.mutListener.listen(4941) ? (0.907f + data.getWeight()) : (0.907f * data.getWeight()))))))) : (0.72f * (-1.976f + (ListenerUtil.mutListener.listen(4944) ? (0.907f % data.getWeight()) : (ListenerUtil.mutListener.listen(4943) ? (0.907f / data.getWeight()) : (ListenerUtil.mutListener.listen(4942) ? (0.907f - data.getWeight()) : (ListenerUtil.mutListener.listen(4941) ? (0.907f + data.getWeight()) : (0.907f * data.getWeight())))))))))));
    }
}

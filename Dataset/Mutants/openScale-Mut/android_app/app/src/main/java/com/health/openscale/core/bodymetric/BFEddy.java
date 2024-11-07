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

public class BFEddy extends EstimatedFatMetric {

    @Override
    public String getName() {
        return "Eddy et. al (1976)";
    }

    @Override
    public float getFat(ScaleUser user, ScaleMeasurement data) {
        if (!ListenerUtil.mutListener.listen(4751)) {
            if (user.getGender().isMale()) {
                return (ListenerUtil.mutListener.listen(4750) ? (((ListenerUtil.mutListener.listen(4746) ? (1.281f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4745) ? (1.281f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4744) ? (1.281f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4743) ? (1.281f + data.getBMI(user.getBodyHeight())) : (1.281f * data.getBMI(user.getBodyHeight()))))))) % 10.13f) : (ListenerUtil.mutListener.listen(4749) ? (((ListenerUtil.mutListener.listen(4746) ? (1.281f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4745) ? (1.281f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4744) ? (1.281f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4743) ? (1.281f + data.getBMI(user.getBodyHeight())) : (1.281f * data.getBMI(user.getBodyHeight()))))))) / 10.13f) : (ListenerUtil.mutListener.listen(4748) ? (((ListenerUtil.mutListener.listen(4746) ? (1.281f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4745) ? (1.281f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4744) ? (1.281f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4743) ? (1.281f + data.getBMI(user.getBodyHeight())) : (1.281f * data.getBMI(user.getBodyHeight()))))))) * 10.13f) : (ListenerUtil.mutListener.listen(4747) ? (((ListenerUtil.mutListener.listen(4746) ? (1.281f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4745) ? (1.281f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4744) ? (1.281f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4743) ? (1.281f + data.getBMI(user.getBodyHeight())) : (1.281f * data.getBMI(user.getBodyHeight()))))))) + 10.13f) : (((ListenerUtil.mutListener.listen(4746) ? (1.281f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4745) ? (1.281f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4744) ? (1.281f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4743) ? (1.281f + data.getBMI(user.getBodyHeight())) : (1.281f * data.getBMI(user.getBodyHeight()))))))) - 10.13f)))));
            }
        }
        return (ListenerUtil.mutListener.listen(4759) ? (((ListenerUtil.mutListener.listen(4755) ? (1.48f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4754) ? (1.48f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4753) ? (1.48f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4752) ? (1.48f + data.getBMI(user.getBodyHeight())) : (1.48f * data.getBMI(user.getBodyHeight()))))))) % 7.0f) : (ListenerUtil.mutListener.listen(4758) ? (((ListenerUtil.mutListener.listen(4755) ? (1.48f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4754) ? (1.48f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4753) ? (1.48f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4752) ? (1.48f + data.getBMI(user.getBodyHeight())) : (1.48f * data.getBMI(user.getBodyHeight()))))))) / 7.0f) : (ListenerUtil.mutListener.listen(4757) ? (((ListenerUtil.mutListener.listen(4755) ? (1.48f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4754) ? (1.48f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4753) ? (1.48f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4752) ? (1.48f + data.getBMI(user.getBodyHeight())) : (1.48f * data.getBMI(user.getBodyHeight()))))))) * 7.0f) : (ListenerUtil.mutListener.listen(4756) ? (((ListenerUtil.mutListener.listen(4755) ? (1.48f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4754) ? (1.48f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4753) ? (1.48f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4752) ? (1.48f + data.getBMI(user.getBodyHeight())) : (1.48f * data.getBMI(user.getBodyHeight()))))))) + 7.0f) : (((ListenerUtil.mutListener.listen(4755) ? (1.48f % data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4754) ? (1.48f / data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4753) ? (1.48f - data.getBMI(user.getBodyHeight())) : (ListenerUtil.mutListener.listen(4752) ? (1.48f + data.getBMI(user.getBodyHeight())) : (1.48f * data.getBMI(user.getBodyHeight()))))))) - 7.0f)))));
    }
}

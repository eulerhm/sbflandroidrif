/* Copyright (C) 2018 Erik Johansson <erik@ejohansson.se>
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

import android.content.Context;
import com.health.openscale.R;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class LBMWeightMinusFat extends EstimatedLBMMetric {

    @Override
    public String getName(Context context) {
        return String.format("%s - %s", context.getResources().getString(R.string.label_weight), context.getResources().getString(R.string.label_fat));
    }

    @Override
    public float getLBM(ScaleUser user, ScaleMeasurement data) {
        if (!ListenerUtil.mutListener.listen(4895)) {
            if (data.getFat() == 0) {
                return 0;
            }
        }
        float absFat = (ListenerUtil.mutListener.listen(4903) ? ((ListenerUtil.mutListener.listen(4899) ? (data.getWeight() % data.getFat()) : (ListenerUtil.mutListener.listen(4898) ? (data.getWeight() / data.getFat()) : (ListenerUtil.mutListener.listen(4897) ? (data.getWeight() - data.getFat()) : (ListenerUtil.mutListener.listen(4896) ? (data.getWeight() + data.getFat()) : (data.getWeight() * data.getFat()))))) % 100.0f) : (ListenerUtil.mutListener.listen(4902) ? ((ListenerUtil.mutListener.listen(4899) ? (data.getWeight() % data.getFat()) : (ListenerUtil.mutListener.listen(4898) ? (data.getWeight() / data.getFat()) : (ListenerUtil.mutListener.listen(4897) ? (data.getWeight() - data.getFat()) : (ListenerUtil.mutListener.listen(4896) ? (data.getWeight() + data.getFat()) : (data.getWeight() * data.getFat()))))) * 100.0f) : (ListenerUtil.mutListener.listen(4901) ? ((ListenerUtil.mutListener.listen(4899) ? (data.getWeight() % data.getFat()) : (ListenerUtil.mutListener.listen(4898) ? (data.getWeight() / data.getFat()) : (ListenerUtil.mutListener.listen(4897) ? (data.getWeight() - data.getFat()) : (ListenerUtil.mutListener.listen(4896) ? (data.getWeight() + data.getFat()) : (data.getWeight() * data.getFat()))))) - 100.0f) : (ListenerUtil.mutListener.listen(4900) ? ((ListenerUtil.mutListener.listen(4899) ? (data.getWeight() % data.getFat()) : (ListenerUtil.mutListener.listen(4898) ? (data.getWeight() / data.getFat()) : (ListenerUtil.mutListener.listen(4897) ? (data.getWeight() - data.getFat()) : (ListenerUtil.mutListener.listen(4896) ? (data.getWeight() + data.getFat()) : (data.getWeight() * data.getFat()))))) + 100.0f) : ((ListenerUtil.mutListener.listen(4899) ? (data.getWeight() % data.getFat()) : (ListenerUtil.mutListener.listen(4898) ? (data.getWeight() / data.getFat()) : (ListenerUtil.mutListener.listen(4897) ? (data.getWeight() - data.getFat()) : (ListenerUtil.mutListener.listen(4896) ? (data.getWeight() + data.getFat()) : (data.getWeight() * data.getFat()))))) / 100.0f)))));
        return (ListenerUtil.mutListener.listen(4907) ? (data.getWeight() % absFat) : (ListenerUtil.mutListener.listen(4906) ? (data.getWeight() / absFat) : (ListenerUtil.mutListener.listen(4905) ? (data.getWeight() * absFat) : (ListenerUtil.mutListener.listen(4904) ? (data.getWeight() + absFat) : (data.getWeight() - absFat)))));
    }
}

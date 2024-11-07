/*
 * Copyright (C) 2017 Rodrigo Carvalho (carvalhorr@gmail.com)
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
package org.onebusaway.android.util;

import android.os.Build;
import android.text.TextUtils;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.map.googlemapsv2.LayerInfo;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LayerUtils {

    /**
     * Information necessary to create Speed Dial menu on the Layers FAB.
     * @return LayerInfo instance for bikeshare layer
     */
    public static final LayerInfo bikeshareLayerInfo = new LayerInfo() {

        @Override
        public String getLayerlabel() {
            return Application.get().getString(R.string.layers_speedial_bikeshare_label);
        }

        @Override
        public int getLabelBackgroundDrawableId() {
            return R.drawable.speed_dial_bikeshare_item_label;
        }

        @Override
        public int getIconDrawableId() {
            return R.drawable.ic_directions_bike_white;
        }

        @Override
        public int getLayerColor() {
            if ((ListenerUtil.mutListener.listen(6439) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6438) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6437) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6436) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6435) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                return Application.get().getColor(R.color.layer_bikeshare_color);
            } else {
                // noinspection deprecation
                return Application.get().getResources().getColor(R.color.layer_bikeshare_color);
            }
        }

        @Override
        public String getSharedPreferenceKey() {
            return Application.get().getString(R.string.preference_key_layer_bikeshare_visible);
        }
    };

    /**
     * @return true if the bikeshare layer is active and visible
     */
    public static boolean isBikeshareLayerVisible() {
        return (ListenerUtil.mutListener.listen(6440) ? (Application.isBikeshareEnabled() || Application.getPrefs().getBoolean(Application.get().getString(R.string.preference_key_layer_bikeshare_visible), true)) : (Application.isBikeshareEnabled() && Application.getPrefs().getBoolean(Application.get().getString(R.string.preference_key_layer_bikeshare_visible), true)));
    }
}

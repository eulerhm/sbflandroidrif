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
package org.onebusaway.android.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import org.onebusaway.android.R;
import org.onebusaway.android.map.googlemapsv2.LayerInfo;
import org.onebusaway.android.util.LayerUtils;
import java.util.ArrayList;
import java.util.List;
import uk.co.markormesher.android_fab.SpeedDialMenuAdapter;
import uk.co.markormesher.android_fab.SpeedDialMenuItem;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Control the display of the available layers options in a speed dial when the layers Floating
 * Action Button is clicked.
 */
public class LayersSpeedDialAdapter extends SpeedDialMenuAdapter {

    private final Context context;

    /**
     * Hold information of which layers are activatedLayers
     */
    private Boolean[] activatedLayers;

    /**
     * Hold information of all available layers
     */
    private LayerInfo[] layers;

    /**
     * Listener to be called when a layer option is activatedLayers/deativated. It supports multiple.
     * Currently there is one listener added to actually add/remove the layer on the map and another
     * one to update the speed dial menu state.
     */
    private List<LayerActivationListener> layerActivationListeners = new ArrayList<>();

    public LayersSpeedDialAdapter(Context context) {
        this.context = context;
        if (!ListenerUtil.mutListener.listen(1612)) {
            setupLayers();
        }
        if (!ListenerUtil.mutListener.listen(1613)) {
            activatedLayers = new Boolean[1];
        }
        if (!ListenerUtil.mutListener.listen(1614)) {
            activatedLayers[0] = LayerUtils.isBikeshareLayerVisible();
        }
    }

    public void addLayerActivationListener(LayerActivationListener listener) {
        if (!ListenerUtil.mutListener.listen(1615)) {
            layerActivationListeners.add(listener);
        }
    }

    private void setupLayers() {
        if (!ListenerUtil.mutListener.listen(1616)) {
            layers = new LayerInfo[1];
        }
        if (!ListenerUtil.mutListener.listen(1617)) {
            layers[0] = LayerUtils.bikeshareLayerInfo;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    /**
     * Gets the menu item to display at the specified position in the range 0 to `getCount() - 1`.
     * See `SpeedDialMenuItem` for more details.
     * Note: positions start at zero closest to the FAB and increase for items further away.
     * @return the menu item to display at the specified position
     */
    @SuppressWarnings("deprecation")
    @Override
    public SpeedDialMenuItem getMenuItem(Context context, int position) {
        if (!ListenerUtil.mutListener.listen(1618)) {
            // Refresh active layer info
            activatedLayers[0] = LayerUtils.isBikeshareLayerVisible();
        }
        LayerInfo layer = layers[position];
        SpeedDialMenuItem menuItem = new SpeedDialMenuItem(context, layer.getIconDrawableId(), layer.getLayerlabel());
        return menuItem;
    }

    /**
     * Apply formatting to the `TextView` used for the label of the menu item at the given position.
     * Note: positions start at zero closest to the FAB and increase for items further away.
     *
     * @param context
     * @param position
     * @param label
     */
    @Override
    public void onPrepareItemLabel(@NotNull Context context, int position, @NotNull TextView label) {
        if (!ListenerUtil.mutListener.listen(1619)) {
            // Refresh active layer info
            activatedLayers[0] = LayerUtils.isBikeshareLayerVisible();
        }
        LayerInfo layer = layers[position];
        if (!ListenerUtil.mutListener.listen(1620)) {
            // Set a solid background for the speed dial item label so you can see the text over the map
            label.setText(layer.getLayerlabel());
        }
        if (!ListenerUtil.mutListener.listen(1621)) {
            label.setTextColor(Color.WHITE);
        }
        int labelDrawableId;
        if (activatedLayers[position]) {
            labelDrawableId = layer.getLabelBackgroundDrawableId();
        } else {
            labelDrawableId = R.drawable.speed_dial_disabled_item_label;
        }
        if (!ListenerUtil.mutListener.listen(1629)) {
            if ((ListenerUtil.mutListener.listen(1626) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1625) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1624) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1623) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1622) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN))))))) {
                if (!ListenerUtil.mutListener.listen(1628)) {
                    label.setBackground(context.getResources().getDrawable(labelDrawableId));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1627)) {
                    label.setBackgroundDrawable(context.getResources().getDrawable(labelDrawableId));
                }
            }
        }
    }

    /**
     * Handler for click events on menu items.
     * The position passed corresponds to positions passed to `getMenuItem()`.
     * @return `true` to close the menu after the click; `false` to leave it open
     */
    @Override
    public boolean onMenuItemClick(int position) {
        if ((ListenerUtil.mutListener.listen(1634) ? (position >= activatedLayers.length) : (ListenerUtil.mutListener.listen(1633) ? (position <= activatedLayers.length) : (ListenerUtil.mutListener.listen(1632) ? (position > activatedLayers.length) : (ListenerUtil.mutListener.listen(1631) ? (position != activatedLayers.length) : (ListenerUtil.mutListener.listen(1630) ? (position == activatedLayers.length) : (position < activatedLayers.length))))))) {
            if (!ListenerUtil.mutListener.listen(1641)) {
                if (activatedLayers[position]) {
                    if (!ListenerUtil.mutListener.listen(1640)) {
                        {
                            long _loopCounter17 = 0;
                            for (LayerActivationListener listener : layerActivationListeners) {
                                ListenerUtil.loopListener.listen("_loopCounter17", ++_loopCounter17);
                                if (!ListenerUtil.mutListener.listen(1639)) {
                                    if (listener != null) {
                                        if (!ListenerUtil.mutListener.listen(1638)) {
                                            listener.onDeactivateLayer(layers[position]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(1637)) {
                        {
                            long _loopCounter16 = 0;
                            for (LayerActivationListener listener : layerActivationListeners) {
                                ListenerUtil.loopListener.listen("_loopCounter16", ++_loopCounter16);
                                if (!ListenerUtil.mutListener.listen(1636)) {
                                    if (listener != null) {
                                        if (!ListenerUtil.mutListener.listen(1635)) {
                                            listener.onActivateLayer(layers[position]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1642)) {
                activatedLayers[position] = !activatedLayers[position];
            }
            if (!ListenerUtil.mutListener.listen(1643)) {
                persistSelection(position);
            }
            return false;
        } else {
            return super.onMenuItemClick(position);
        }
    }

    /**
     * Store the layer activation state in the default shared preferences.
     * @param position position of the menu clicked
     */
    private void persistSelection(int position) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(1644)) {
            sp.edit().putBoolean(layers[position].getSharedPreferenceKey(), activatedLayers[position]).apply();
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getBackgroundColour(int position) {
        if (!ListenerUtil.mutListener.listen(1645)) {
            // Refresh active layer info
            activatedLayers[0] = LayerUtils.isBikeshareLayerVisible();
        }
        int activatedColor = layers[position].getLayerColor();
        int deactivatedColor = context.getResources().getColor(R.color.layer_disabled);
        return activatedLayers[position] ? activatedColor : deactivatedColor;
    }

    @Override
    public float fabRotationDegrees() {
        return 45.0f;
    }

    /**
     * Interface that any class wishing to respond to layer activation/deactivation must implement.
     */
    public interface LayerActivationListener {

        void onActivateLayer(LayerInfo layer);

        void onDeactivateLayer(LayerInfo layer);
    }
}

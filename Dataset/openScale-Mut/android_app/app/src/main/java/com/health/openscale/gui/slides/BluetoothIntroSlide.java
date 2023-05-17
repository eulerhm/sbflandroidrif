/* Copyright (C) 2019  olie.xdev <olie.xdev@googlemail.com>
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
package com.health.openscale.gui.slides;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.health.openscale.R;
import com.health.openscale.gui.preferences.BluetoothSettingsFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothIntroSlide extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";

    private int layoutResId;

    private Button btnSearchScale;

    private TextView txtFoundDevice;

    public static BluetoothIntroSlide newInstance(int layoutResId) {
        BluetoothIntroSlide sampleSlide = new BluetoothIntroSlide();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(8811)) {
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        }
        if (!ListenerUtil.mutListener.listen(8812)) {
            sampleSlide.setArguments(args);
        }
        return sampleSlide;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8813)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(8816)) {
            if ((ListenerUtil.mutListener.listen(8814) ? (getArguments() != null || getArguments().containsKey(ARG_LAYOUT_RES_ID)) : (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)))) {
                if (!ListenerUtil.mutListener.listen(8815)) {
                    layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResId, container, false);
        if (!ListenerUtil.mutListener.listen(8817)) {
            txtFoundDevice = view.findViewById(R.id.txtFoundDevice);
        }
        if (!ListenerUtil.mutListener.listen(8818)) {
            txtFoundDevice.setText(getCurrentDeviceName());
        }
        if (!ListenerUtil.mutListener.listen(8819)) {
            btnSearchScale = view.findViewById(R.id.btnSearchScale);
        }
        if (!ListenerUtil.mutListener.listen(8822)) {
            btnSearchScale.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), SlideToNavigationAdapter.class);
                    if (!ListenerUtil.mutListener.listen(8820)) {
                        intent.putExtra(SlideToNavigationAdapter.EXTRA_MODE, SlideToNavigationAdapter.EXTRA_BLUETOOTH_SETTING_MODE);
                    }
                    if (!ListenerUtil.mutListener.listen(8821)) {
                        startActivityForResult(intent, 100);
                    }
                }
            });
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(8823)) {
            txtFoundDevice.setText(getCurrentDeviceName());
        }
    }

    private final String formatDeviceName(String name, String address) {
        if (!ListenerUtil.mutListener.listen(8825)) {
            if ((ListenerUtil.mutListener.listen(8824) ? (name.isEmpty() && address.isEmpty()) : (name.isEmpty() || address.isEmpty()))) {
                return "[" + getContext().getString(R.string.label_empty) + "]";
            }
        }
        return String.format("%s [%s]", name, address);
    }

    private String getCurrentDeviceName() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        return formatDeviceName(prefs.getString(BluetoothSettingsFragment.PREFERENCE_KEY_BLUETOOTH_DEVICE_NAME, ""), prefs.getString(BluetoothSettingsFragment.PREFERENCE_KEY_BLUETOOTH_HW_ADDRESS, ""));
    }
}

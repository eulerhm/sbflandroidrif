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
package com.health.openscale.gui.preferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.bluetooth.BluetoothCommunication;
import com.health.openscale.core.bluetooth.BluetoothFactory;
import com.health.openscale.gui.utils.ColorUtil;
import com.health.openscale.gui.utils.PermissionHelper;
import com.welie.blessed.BluetoothCentral;
import com.welie.blessed.BluetoothCentralCallback;
import com.welie.blessed.BluetoothPeripheral;
import java.util.HashMap;
import java.util.Map;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothSettingsFragment extends Fragment {

    public static final String PREFERENCE_KEY_BLUETOOTH_DEVICE_NAME = "btDeviceName";

    public static final String PREFERENCE_KEY_BLUETOOTH_HW_ADDRESS = "btHwAddress";

    private Map<String, BluetoothDevice> foundDevices = new HashMap<>();

    private LinearLayout deviceListView;

    private TextView txtSearching;

    private ProgressBar progressBar;

    private Handler progressHandler;

    private BluetoothCentral central;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bluetoothsettings, container, false);
        if (!ListenerUtil.mutListener.listen(8232)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(8233)) {
            deviceListView = root.findViewById(R.id.deviceListView);
        }
        if (!ListenerUtil.mutListener.listen(8234)) {
            txtSearching = root.findViewById(R.id.txtSearching);
        }
        if (!ListenerUtil.mutListener.listen(8235)) {
            progressBar = root.findViewById(R.id.progressBar);
        }
        return root;
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(8236)) {
            stopBluetoothDiscovery();
        }
        if (!ListenerUtil.mutListener.listen(8237)) {
            super.onPause();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(8240)) {
            if (PermissionHelper.requestBluetoothPermission(this)) {
                if (!ListenerUtil.mutListener.listen(8239)) {
                    if (PermissionHelper.requestLocationServicePermission(this)) {
                        if (!ListenerUtil.mutListener.listen(8238)) {
                            startBluetoothDiscovery();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8241)) {
            super.onResume();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8242)) {
            menu.clear();
        }
    }

    private static final String formatDeviceName(String name, String address) {
        if (!ListenerUtil.mutListener.listen(8244)) {
            if ((ListenerUtil.mutListener.listen(8243) ? (name.isEmpty() && address.isEmpty()) : (name.isEmpty() || address.isEmpty()))) {
                return "-";
            }
        }
        return String.format("%s [%s]", name, address);
    }

    private static final String formatDeviceName(BluetoothDevice device) {
        return formatDeviceName(device.getName(), device.getAddress());
    }

    private final BluetoothCentralCallback bluetoothCentralCallback = new BluetoothCentralCallback() {

        @Override
        public void onDiscoveredPeripheral(BluetoothPeripheral peripheral, ScanResult scanResult) {
            if (!ListenerUtil.mutListener.listen(8246)) {
                new Handler().post(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(8245)) {
                            onDeviceFound(scanResult);
                        }
                    }
                });
            }
        }
    };

    private void startBluetoothDiscovery() {
        if (!ListenerUtil.mutListener.listen(8247)) {
            deviceListView.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(8248)) {
            foundDevices.clear();
        }
        if (!ListenerUtil.mutListener.listen(8249)) {
            central = new BluetoothCentral(requireContext(), bluetoothCentralCallback, new Handler(Looper.getMainLooper()));
        }
        if (!ListenerUtil.mutListener.listen(8250)) {
            central.scanForPeripherals();
        }
        if (!ListenerUtil.mutListener.listen(8251)) {
            txtSearching.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(8252)) {
            txtSearching.setText(R.string.label_bluetooth_searching);
        }
        if (!ListenerUtil.mutListener.listen(8253)) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(8254)) {
            progressHandler = new Handler();
        }
        if (!ListenerUtil.mutListener.listen(8270)) {
            // Don't let the BLE discovery run forever
            progressHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(8255)) {
                        stopBluetoothDiscovery();
                    }
                    if (!ListenerUtil.mutListener.listen(8256)) {
                        txtSearching.setText(R.string.label_bluetooth_searching_finished);
                    }
                    if (!ListenerUtil.mutListener.listen(8257)) {
                        progressBar.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(8265)) {
                        new Handler().post(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    BluetoothDeviceView notSupported = new BluetoothDeviceView(requireContext());
                                    if (!ListenerUtil.mutListener.listen(8259)) {
                                        notSupported.setDeviceName(requireContext().getString(R.string.label_scale_not_supported));
                                    }
                                    if (!ListenerUtil.mutListener.listen(8260)) {
                                        notSupported.setSummaryText(requireContext().getString(R.string.label_click_to_help_add_support));
                                    }
                                    if (!ListenerUtil.mutListener.listen(8263)) {
                                        notSupported.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View view) {
                                                Intent notSupportedIntent = new Intent(Intent.ACTION_VIEW);
                                                if (!ListenerUtil.mutListener.listen(8261)) {
                                                    notSupportedIntent.setData(Uri.parse("https://github.com/oliexdev/openScale/wiki/Supported-scales-in-openScale"));
                                                }
                                                if (!ListenerUtil.mutListener.listen(8262)) {
                                                    startActivity(notSupportedIntent);
                                                }
                                            }
                                        });
                                    }
                                    if (!ListenerUtil.mutListener.listen(8264)) {
                                        deviceListView.addView(notSupported);
                                    }
                                } catch (IllegalStateException ex) {
                                    if (!ListenerUtil.mutListener.listen(8258)) {
                                        Timber.e(ex.getMessage());
                                    }
                                }
                            }
                        });
                    }
                }
            }, (ListenerUtil.mutListener.listen(8269) ? (20 % 1000) : (ListenerUtil.mutListener.listen(8268) ? (20 / 1000) : (ListenerUtil.mutListener.listen(8267) ? (20 - 1000) : (ListenerUtil.mutListener.listen(8266) ? (20 + 1000) : (20 * 1000))))));
        }
    }

    private void stopBluetoothDiscovery() {
        if (!ListenerUtil.mutListener.listen(8273)) {
            if (progressHandler != null) {
                if (!ListenerUtil.mutListener.listen(8271)) {
                    progressHandler.removeCallbacksAndMessages(null);
                }
                if (!ListenerUtil.mutListener.listen(8272)) {
                    progressHandler = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8275)) {
            if (central != null) {
                if (!ListenerUtil.mutListener.listen(8274)) {
                    central.stopScan();
                }
            }
        }
    }

    private void onDeviceFound(final ScanResult bleScanResult) {
        BluetoothDevice device = bleScanResult.getDevice();
        if (!ListenerUtil.mutListener.listen(8277)) {
            if ((ListenerUtil.mutListener.listen(8276) ? (device.getName() == null && foundDevices.containsKey(device.getAddress())) : (device.getName() == null || foundDevices.containsKey(device.getAddress())))) {
                return;
            }
        }
        BluetoothDeviceView deviceView = new BluetoothDeviceView(requireContext());
        if (!ListenerUtil.mutListener.listen(8278)) {
            deviceView.setDeviceName(formatDeviceName(bleScanResult.getDevice()));
        }
        BluetoothCommunication btDevice = BluetoothFactory.createDeviceDriver(requireContext(), device.getName());
        if (!ListenerUtil.mutListener.listen(8291)) {
            if (btDevice != null) {
                if (!ListenerUtil.mutListener.listen(8287)) {
                    Timber.d("Found supported device %s (driver: %s)", formatDeviceName(device), btDevice.driverName());
                }
                if (!ListenerUtil.mutListener.listen(8288)) {
                    deviceView.setDeviceAddress(device.getAddress());
                }
                if (!ListenerUtil.mutListener.listen(8289)) {
                    deviceView.setIcon(R.drawable.ic_bluetooth_device_supported);
                }
                if (!ListenerUtil.mutListener.listen(8290)) {
                    deviceView.setSummaryText(btDevice.driverName());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8279)) {
                    Timber.d("Found unsupported device %s", formatDeviceName(device));
                }
                if (!ListenerUtil.mutListener.listen(8280)) {
                    deviceView.setIcon(R.drawable.ic_bluetooth_device_not_supported);
                }
                if (!ListenerUtil.mutListener.listen(8281)) {
                    deviceView.setSummaryText(requireContext().getString(R.string.label_bt_device_no_support));
                }
                if (!ListenerUtil.mutListener.listen(8282)) {
                    deviceView.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(8286)) {
                    if (OpenScale.DEBUG_MODE) {
                        if (!ListenerUtil.mutListener.listen(8283)) {
                            deviceView.setEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(8285)) {
                            deviceView.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    if (!ListenerUtil.mutListener.listen(8284)) {
                                        getDebugInfo(device);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8292)) {
            foundDevices.put(device.getAddress(), btDevice != null ? device : null);
        }
        if (!ListenerUtil.mutListener.listen(8293)) {
            deviceListView.addView(deviceView);
        }
    }

    private void getDebugInfo(final BluetoothDevice device) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        if (!ListenerUtil.mutListener.listen(8296)) {
            builder.setTitle("Fetching info").setMessage("Please wait while we fetch extended info from your scale...").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(8294)) {
                        OpenScale.getInstance().disconnectFromBluetoothDevice();
                    }
                    if (!ListenerUtil.mutListener.listen(8295)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        final AlertDialog dialog = builder.create();
        Handler btHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (!ListenerUtil.mutListener.listen(8299)) {
                    switch(BluetoothCommunication.BT_STATUS.values()[msg.what]) {
                        case CONNECTION_LOST:
                            if (!ListenerUtil.mutListener.listen(8297)) {
                                OpenScale.getInstance().disconnectFromBluetoothDevice();
                            }
                            if (!ListenerUtil.mutListener.listen(8298)) {
                                dialog.dismiss();
                            }
                            break;
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(8300)) {
            dialog.show();
        }
        String macAddress = device.getAddress();
        if (!ListenerUtil.mutListener.listen(8301)) {
            stopBluetoothDiscovery();
        }
        if (!ListenerUtil.mutListener.listen(8302)) {
            OpenScale.getInstance().connectToBluetoothDeviceDebugMode(macAddress, btHandler);
        }
    }

    private class BluetoothDeviceView extends LinearLayout implements View.OnClickListener {

        private TextView deviceName;

        private ImageView deviceIcon;

        private String deviceAddress;

        public BluetoothDeviceView(Context context) {
            super(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (!ListenerUtil.mutListener.listen(8303)) {
                layoutParams.setMargins(0, 20, 0, 20);
            }
            if (!ListenerUtil.mutListener.listen(8304)) {
                setLayoutParams(layoutParams);
            }
            if (!ListenerUtil.mutListener.listen(8305)) {
                deviceName = new TextView(context);
            }
            if (!ListenerUtil.mutListener.listen(8306)) {
                deviceName.setLines(2);
            }
            if (!ListenerUtil.mutListener.listen(8307)) {
                deviceIcon = new ImageView(context);
            }
            ;
            LinearLayout.LayoutParams centerLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            if (!ListenerUtil.mutListener.listen(8308)) {
                layoutParams.gravity = Gravity.CENTER;
            }
            if (!ListenerUtil.mutListener.listen(8309)) {
                deviceIcon.setLayoutParams(centerLayoutParams);
            }
            if (!ListenerUtil.mutListener.listen(8310)) {
                deviceName.setLayoutParams(centerLayoutParams);
            }
            if (!ListenerUtil.mutListener.listen(8311)) {
                deviceName.setOnClickListener(this);
            }
            if (!ListenerUtil.mutListener.listen(8312)) {
                deviceIcon.setOnClickListener(this);
            }
            if (!ListenerUtil.mutListener.listen(8313)) {
                setOnClickListener(this);
            }
            if (!ListenerUtil.mutListener.listen(8314)) {
                addView(deviceIcon);
            }
            if (!ListenerUtil.mutListener.listen(8315)) {
                addView(deviceName);
            }
        }

        public void setDeviceAddress(String address) {
            if (!ListenerUtil.mutListener.listen(8316)) {
                deviceAddress = address;
            }
        }

        public String getDeviceAddress() {
            return deviceAddress;
        }

        public void setDeviceName(String name) {
            if (!ListenerUtil.mutListener.listen(8317)) {
                deviceName.setText(name);
            }
        }

        public void setSummaryText(String text) {
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder(new String());
            if (!ListenerUtil.mutListener.listen(8318)) {
                stringBuilder.append(deviceName.getText());
            }
            if (!ListenerUtil.mutListener.listen(8319)) {
                stringBuilder.append("\n");
            }
            int deviceNameLength = deviceName.getText().length();
            if (!ListenerUtil.mutListener.listen(8320)) {
                stringBuilder.append(text);
            }
            if (!ListenerUtil.mutListener.listen(8329)) {
                stringBuilder.setSpan(new ForegroundColorSpan(Color.GRAY), deviceNameLength, (ListenerUtil.mutListener.listen(8328) ? ((ListenerUtil.mutListener.listen(8324) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8323) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8322) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8321) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) % 1) : (ListenerUtil.mutListener.listen(8327) ? ((ListenerUtil.mutListener.listen(8324) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8323) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8322) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8321) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) / 1) : (ListenerUtil.mutListener.listen(8326) ? ((ListenerUtil.mutListener.listen(8324) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8323) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8322) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8321) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) * 1) : (ListenerUtil.mutListener.listen(8325) ? ((ListenerUtil.mutListener.listen(8324) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8323) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8322) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8321) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) - 1) : ((ListenerUtil.mutListener.listen(8324) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8323) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8322) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8321) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) + 1))))), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            if (!ListenerUtil.mutListener.listen(8338)) {
                stringBuilder.setSpan(new RelativeSizeSpan(0.8f), deviceNameLength, (ListenerUtil.mutListener.listen(8337) ? ((ListenerUtil.mutListener.listen(8333) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8332) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8331) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8330) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) % 1) : (ListenerUtil.mutListener.listen(8336) ? ((ListenerUtil.mutListener.listen(8333) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8332) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8331) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8330) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) / 1) : (ListenerUtil.mutListener.listen(8335) ? ((ListenerUtil.mutListener.listen(8333) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8332) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8331) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8330) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) * 1) : (ListenerUtil.mutListener.listen(8334) ? ((ListenerUtil.mutListener.listen(8333) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8332) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8331) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8330) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) - 1) : ((ListenerUtil.mutListener.listen(8333) ? (deviceNameLength % text.length()) : (ListenerUtil.mutListener.listen(8332) ? (deviceNameLength / text.length()) : (ListenerUtil.mutListener.listen(8331) ? (deviceNameLength * text.length()) : (ListenerUtil.mutListener.listen(8330) ? (deviceNameLength - text.length()) : (deviceNameLength + text.length()))))) + 1))))), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            if (!ListenerUtil.mutListener.listen(8339)) {
                deviceName.setText(stringBuilder);
            }
        }

        public void setIcon(int resId) {
            if (!ListenerUtil.mutListener.listen(8340)) {
                deviceIcon.setImageResource(resId);
            }
            int tintColor = ColorUtil.getTintColor(requireContext());
            if (!ListenerUtil.mutListener.listen(8341)) {
                deviceIcon.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
            }
        }

        @Override
        public void setOnClickListener(OnClickListener listener) {
            if (!ListenerUtil.mutListener.listen(8342)) {
                super.setOnClickListener(listener);
            }
            if (!ListenerUtil.mutListener.listen(8343)) {
                deviceName.setOnClickListener(listener);
            }
            if (!ListenerUtil.mutListener.listen(8344)) {
                deviceIcon.setOnClickListener(listener);
            }
        }

        @Override
        public void setEnabled(boolean status) {
            if (!ListenerUtil.mutListener.listen(8345)) {
                super.setEnabled(status);
            }
            if (!ListenerUtil.mutListener.listen(8346)) {
                deviceName.setEnabled(status);
            }
            if (!ListenerUtil.mutListener.listen(8347)) {
                deviceIcon.setEnabled(status);
            }
        }

        @Override
        public void onClick(View view) {
            BluetoothDevice device = foundDevices.get(getDeviceAddress());
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
            if (!ListenerUtil.mutListener.listen(8348)) {
                prefs.edit().putString(PREFERENCE_KEY_BLUETOOTH_HW_ADDRESS, device.getAddress()).putString(PREFERENCE_KEY_BLUETOOTH_DEVICE_NAME, device.getName()).apply();
            }
            if (!ListenerUtil.mutListener.listen(8349)) {
                Timber.d("Saved Bluetooth device " + device.getName() + " with address " + device.getAddress());
            }
            if (!ListenerUtil.mutListener.listen(8350)) {
                stopBluetoothDiscovery();
            }
            if (!ListenerUtil.mutListener.listen(8354)) {
                if (getActivity().findViewById(R.id.nav_host_fragment) != null) {
                    if (!ListenerUtil.mutListener.listen(8352)) {
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).getPreviousBackStackEntry().getSavedStateHandle().set("update", true);
                    }
                    if (!ListenerUtil.mutListener.listen(8353)) {
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8351)) {
                        getActivity().finish();
                    }
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(8358)) {
            if (requestCode == PermissionHelper.ENABLE_BLUETOOTH_REQUEST) {
                if (!ListenerUtil.mutListener.listen(8357)) {
                    if (resultCode == Activity.RESULT_OK) {
                        if (!ListenerUtil.mutListener.listen(8356)) {
                            if (PermissionHelper.requestBluetoothPermission(this)) {
                                if (!ListenerUtil.mutListener.listen(8355)) {
                                    startBluetoothDiscovery();
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8359)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(8370)) {
            switch(requestCode) {
                case PermissionHelper.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                    {
                        if (!ListenerUtil.mutListener.listen(8369)) {
                            if ((ListenerUtil.mutListener.listen(8365) ? ((ListenerUtil.mutListener.listen(8364) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(8363) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(8362) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(8361) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(8360) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(8364) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(8363) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(8362) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(8361) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(8360) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                                if (!ListenerUtil.mutListener.listen(8368)) {
                                    if (PermissionHelper.requestLocationServicePermission(this)) {
                                        if (!ListenerUtil.mutListener.listen(8367)) {
                                            startBluetoothDiscovery();
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(8366)) {
                                    Toast.makeText(requireContext(), R.string.permission_not_granted, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                    }
            }
        }
    }
}

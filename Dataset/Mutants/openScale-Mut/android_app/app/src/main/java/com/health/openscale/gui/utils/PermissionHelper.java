/* Copyright (C) 2018  olie.xdev <olie.xdev@googlemail.com>
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
package com.health.openscale.gui.utils;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.health.openscale.R;
import static android.content.Context.LOCATION_SERVICE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PermissionHelper {

    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public static final int PERMISSIONS_REQUEST_ACCESS_READ_STORAGE = 2;

    public static final int PERMISSIONS_REQUEST_ACCESS_WRITE_STORAGE = 3;

    public static final int ENABLE_BLUETOOTH_REQUEST = 5;

    public static boolean requestBluetoothPermission(final Fragment fragment) {
        final BluetoothManager bluetoothManager = (BluetoothManager) fragment.getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter btAdapter = bluetoothManager.getAdapter();
        if (!ListenerUtil.mutListener.listen(9198)) {
            if ((ListenerUtil.mutListener.listen(9194) ? (btAdapter == null && !btAdapter.isEnabled()) : (btAdapter == null || !btAdapter.isEnabled()))) {
                if (!ListenerUtil.mutListener.listen(9195)) {
                    Toast.makeText(fragment.getContext(), "Bluetooth " + fragment.getContext().getResources().getString(R.string.info_is_not_enable), Toast.LENGTH_SHORT).show();
                }
                if (!ListenerUtil.mutListener.listen(9197)) {
                    if (btAdapter != null) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        if (!ListenerUtil.mutListener.listen(9196)) {
                            fragment.getActivity().startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_REQUEST);
                        }
                    }
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9200)) {
            // Check if Bluetooth 4.x is available
            if (!fragment.getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                if (!ListenerUtil.mutListener.listen(9199)) {
                    Toast.makeText(fragment.getContext(), "Bluetooth 4.x " + fragment.getContext().getResources().getString(R.string.info_is_not_available), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9212)) {
            if ((ListenerUtil.mutListener.listen(9205) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9204) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9203) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9202) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9201) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(9211)) {
                    if (fragment.getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                        if (!ListenerUtil.mutListener.listen(9208)) {
                            builder.setMessage(R.string.permission_bluetooth_info).setTitle(R.string.permission_bluetooth_info_title).setIcon(R.drawable.ic_preferences_about).setPositiveButton(R.string.label_ok, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {
                                    if (!ListenerUtil.mutListener.listen(9206)) {
                                        dialog.dismiss();
                                    }
                                    if (!ListenerUtil.mutListener.listen(9207)) {
                                        fragment.requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                                    }
                                }
                            });
                        }
                        Dialog alertDialog = builder.create();
                        if (!ListenerUtil.mutListener.listen(9209)) {
                            alertDialog.setCanceledOnTouchOutside(false);
                        }
                        if (!ListenerUtil.mutListener.listen(9210)) {
                            alertDialog.show();
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean requestLocationServicePermission(final Fragment fragment) {
        LocationManager locationManager = (LocationManager) fragment.getActivity().getSystemService(LOCATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(9221)) {
            if (!((ListenerUtil.mutListener.listen(9213) ? (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) : (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))))) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
                if (!ListenerUtil.mutListener.listen(9214)) {
                    builder.setTitle(R.string.permission_bluetooth_info_title);
                }
                if (!ListenerUtil.mutListener.listen(9215)) {
                    builder.setIcon(R.drawable.ic_preferences_about);
                }
                if (!ListenerUtil.mutListener.listen(9216)) {
                    builder.setMessage(R.string.permission_location_service_info);
                }
                if (!ListenerUtil.mutListener.listen(9218)) {
                    builder.setPositiveButton(R.string.label_ok, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Show location settings when the user acknowledges the alert dialog
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            if (!ListenerUtil.mutListener.listen(9217)) {
                                fragment.getActivity().startActivity(intent);
                            }
                        }
                    });
                }
                Dialog alertDialog = builder.create();
                if (!ListenerUtil.mutListener.listen(9219)) {
                    alertDialog.setCanceledOnTouchOutside(false);
                }
                if (!ListenerUtil.mutListener.listen(9220)) {
                    alertDialog.show();
                }
                return false;
            }
        }
        return true;
    }

    public static boolean requestReadPermission(final Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(9229)) {
            if ((ListenerUtil.mutListener.listen(9226) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9225) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9224) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9223) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9222) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(9228)) {
                    if (fragment.getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        if (!ListenerUtil.mutListener.listen(9227)) {
                            fragment.requestPermissions(new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, PERMISSIONS_REQUEST_ACCESS_READ_STORAGE);
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean requestWritePermission(final Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(9237)) {
            if ((ListenerUtil.mutListener.listen(9234) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9233) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9232) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9231) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9230) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(9236)) {
                    if (fragment.getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        if (!ListenerUtil.mutListener.listen(9235)) {
                            fragment.requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, PERMISSIONS_REQUEST_ACCESS_WRITE_STORAGE);
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

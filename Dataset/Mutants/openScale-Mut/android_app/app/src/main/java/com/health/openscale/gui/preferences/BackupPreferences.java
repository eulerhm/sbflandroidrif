/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
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

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.alarm.AlarmBackupHandler;
import com.health.openscale.core.alarm.ReminderBootReceiver;
import com.health.openscale.gui.utils.PermissionHelper;
import java.io.IOException;
import static android.app.Activity.RESULT_OK;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BackupPreferences extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String PREFERENCE_KEY_IMPORT_BACKUP = "importBackup";

    private static final String PREFERENCE_KEY_EXPORT_BACKUP = "exportBackup";

    private static final String PREFERENCE_KEY_AUTO_BACKUP = "autoBackup";

    private static final int IMPORT_DATA_REQUEST = 100;

    private static final int EXPORT_DATA_REQUEST = 101;

    private Preference importBackup;

    private Preference exportBackup;

    private CheckBoxPreference autoBackup;

    private boolean isAutoBackupAskForPermission;

    private Fragment fragment;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(8150)) {
            setPreferencesFromResource(R.xml.backup_preferences, rootKey);
        }
        if (!ListenerUtil.mutListener.listen(8151)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(8152)) {
            fragment = this;
        }
        if (!ListenerUtil.mutListener.listen(8153)) {
            importBackup = (Preference) findPreference(PREFERENCE_KEY_IMPORT_BACKUP);
        }
        if (!ListenerUtil.mutListener.listen(8154)) {
            importBackup.setOnPreferenceClickListener(new onClickListenerImportBackup());
        }
        if (!ListenerUtil.mutListener.listen(8155)) {
            exportBackup = (Preference) findPreference(PREFERENCE_KEY_EXPORT_BACKUP);
        }
        if (!ListenerUtil.mutListener.listen(8156)) {
            exportBackup.setOnPreferenceClickListener(new onClickListenerExportBackup());
        }
        if (!ListenerUtil.mutListener.listen(8157)) {
            autoBackup = (CheckBoxPreference) findPreference(PREFERENCE_KEY_AUTO_BACKUP);
        }
        if (!ListenerUtil.mutListener.listen(8158)) {
            autoBackup.setOnPreferenceClickListener(new onClickListenerAutoBackup());
        }
        if (!ListenerUtil.mutListener.listen(8159)) {
            updateBackupPreferences();
        }
    }

    void updateBackupPreferences() {
        ComponentName receiver = new ComponentName(getActivity().getApplicationContext(), ReminderBootReceiver.class);
        PackageManager pm = getActivity().getApplicationContext().getPackageManager();
        AlarmBackupHandler alarmBackupHandler = new AlarmBackupHandler();
        if (!ListenerUtil.mutListener.listen(8160)) {
            isAutoBackupAskForPermission = false;
        }
        if (!ListenerUtil.mutListener.listen(8165)) {
            if (autoBackup.isChecked()) {
                if (!ListenerUtil.mutListener.listen(8163)) {
                    alarmBackupHandler.scheduleAlarms(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(8164)) {
                    pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8161)) {
                    alarmBackupHandler.disableAlarm(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(8162)) {
                    pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(8166)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(8167)) {
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(8168)) {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(8169)) {
            super.onPause();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(8170)) {
            updateBackupPreferences();
        }
    }

    private class onClickListenerAutoBackup implements Preference.OnPreferenceClickListener {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (!ListenerUtil.mutListener.listen(8173)) {
                if (autoBackup.isChecked()) {
                    if (!ListenerUtil.mutListener.listen(8171)) {
                        isAutoBackupAskForPermission = true;
                    }
                    if (!ListenerUtil.mutListener.listen(8172)) {
                        PermissionHelper.requestWritePermission(fragment);
                    }
                }
            }
            return true;
        }
    }

    private class onClickListenerImportBackup implements Preference.OnPreferenceClickListener {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (!ListenerUtil.mutListener.listen(8175)) {
                if (PermissionHelper.requestReadPermission(fragment)) {
                    if (!ListenerUtil.mutListener.listen(8174)) {
                        importBackup();
                    }
                }
            }
            return true;
        }
    }

    private class onClickListenerExportBackup implements Preference.OnPreferenceClickListener {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (!ListenerUtil.mutListener.listen(8177)) {
                if (PermissionHelper.requestWritePermission(fragment)) {
                    if (!ListenerUtil.mutListener.listen(8176)) {
                        exportBackup();
                    }
                }
            }
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(8178)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(8180)) {
            if ((ListenerUtil.mutListener.listen(8179) ? (resultCode != RESULT_OK && data == null) : (resultCode != RESULT_OK || data == null))) {
                return;
            }
        }
        OpenScale openScale = OpenScale.getInstance();
        if (!ListenerUtil.mutListener.listen(8187)) {
            switch(requestCode) {
                case IMPORT_DATA_REQUEST:
                    Uri importURI = data.getData();
                    try {
                        if (!ListenerUtil.mutListener.listen(8182)) {
                            openScale.importDatabase(importURI);
                        }
                        if (!ListenerUtil.mutListener.listen(8183)) {
                            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.info_data_imported) + " " + importURI.getPath(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(8181)) {
                            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.error_importing) + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        return;
                    }
                    break;
                case EXPORT_DATA_REQUEST:
                    Uri exportURI = data.getData();
                    try {
                        if (!ListenerUtil.mutListener.listen(8185)) {
                            openScale.exportDatabase(exportURI);
                        }
                        if (!ListenerUtil.mutListener.listen(8186)) {
                            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.info_data_exported) + " " + exportURI.getPath(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(8184)) {
                            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.error_exporting) + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        return;
                    }
                    break;
            }
        }
    }

    private boolean importBackup() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        if (!ListenerUtil.mutListener.listen(8188)) {
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        if (!ListenerUtil.mutListener.listen(8189)) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        if (!ListenerUtil.mutListener.listen(8190)) {
            intent.setType("*/*");
        }
        if (!ListenerUtil.mutListener.listen(8191)) {
            startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.label_import)), IMPORT_DATA_REQUEST);
        }
        return true;
    }

    private boolean exportBackup() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        if (!ListenerUtil.mutListener.listen(8192)) {
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        if (!ListenerUtil.mutListener.listen(8193)) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        if (!ListenerUtil.mutListener.listen(8194)) {
            intent.setType("*/*");
        }
        if (!ListenerUtil.mutListener.listen(8195)) {
            startActivityForResult(intent, EXPORT_DATA_REQUEST);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(8218)) {
            switch(requestCode) {
                case PermissionHelper.PERMISSIONS_REQUEST_ACCESS_READ_STORAGE:
                    if (!ListenerUtil.mutListener.listen(8204)) {
                        if ((ListenerUtil.mutListener.listen(8201) ? ((ListenerUtil.mutListener.listen(8200) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(8199) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(8198) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(8197) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(8196) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(8200) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(8199) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(8198) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(8197) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(8196) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(8203)) {
                                importBackup();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8202)) {
                                Toast.makeText(getContext(), getResources().getString(R.string.permission_not_granted), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                case PermissionHelper.PERMISSIONS_REQUEST_ACCESS_WRITE_STORAGE:
                    if (!ListenerUtil.mutListener.listen(8217)) {
                        if ((ListenerUtil.mutListener.listen(8210) ? ((ListenerUtil.mutListener.listen(8209) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(8208) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(8207) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(8206) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(8205) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(8209) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(8208) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(8207) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(8206) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(8205) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(8216)) {
                                if (isAutoBackupAskForPermission) {
                                    if (!ListenerUtil.mutListener.listen(8215)) {
                                        autoBackup.setChecked(true);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(8214)) {
                                        exportBackup();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8212)) {
                                if (isAutoBackupAskForPermission) {
                                    if (!ListenerUtil.mutListener.listen(8211)) {
                                        autoBackup.setChecked(false);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(8213)) {
                                Toast.makeText(getContext(), getResources().getString(R.string.permission_not_granted), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8219)) {
            menu.clear();
        }
    }
}

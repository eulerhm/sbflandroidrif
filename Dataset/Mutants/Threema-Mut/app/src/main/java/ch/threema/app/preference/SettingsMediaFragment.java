/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.preference;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.StorageManagementActivity;
import ch.threema.app.services.MessageServiceImpl;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsMediaFragment extends ThreemaPreferenceFragment {

    private static final Logger logger = LoggerFactory.getLogger(SettingsMediaFragment.class);

    private static final int PERMISSION_REQUEST_SAVE_MEDIA = 1;

    private CheckBoxPreference saveMediaPreference;

    private View fragmentView;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32416)) {
            addPreferencesFromResource(R.xml.preference_media);
        }
        PreferenceService preferenceService;
        try {
            preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(32417)) {
                logger.error("Exception", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(32418)) {
            if (preferenceService == null) {
                return;
            }
        }
        Preference autoDownloadExplainSizePreference = findPreference(getResources().getString(R.string.preferences__auto_download_explain));
        if (!ListenerUtil.mutListener.listen(32424)) {
            autoDownloadExplainSizePreference.setSummary(getString(R.string.auto_download_limit_explain, Formatter.formatShortFileSize(getContext(), (ListenerUtil.mutListener.listen(32423) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(32422) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(32421) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(32420) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(32419) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)))))) ? MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_SI : MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO)));
        }
        Preference mediaPreference = findPreference(getResources().getString(R.string.preferences__storage_management));
        if (!ListenerUtil.mutListener.listen(32426)) {
            mediaPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32425)) {
                        startActivity(new Intent(getActivity(), StorageManagementActivity.class));
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32427)) {
            saveMediaPreference = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__save_media));
        }
        if (!ListenerUtil.mutListener.listen(32429)) {
            saveMediaPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(32428)) {
                        if ((boolean) newValue) {
                            return ConfigUtils.requestStoragePermissions(getActivity(), SettingsMediaFragment.this, PERMISSION_REQUEST_SAVE_MEDIA);
                        }
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32433)) {
            if (ConfigUtils.isWorkRestricted()) {
                Boolean value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_save_to_gallery));
                if (!ListenerUtil.mutListener.listen(32432)) {
                    if (value != null) {
                        if (!ListenerUtil.mutListener.listen(32430)) {
                            saveMediaPreference.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32431)) {
                            saveMediaPreference.setSelectable(false);
                        }
                    }
                }
            }
        }
        MultiSelectListPreference wifiDownloadPreference = (MultiSelectListPreference) findPreference(getResources().getString(R.string.preferences__auto_download_wifi));
        if (!ListenerUtil.mutListener.listen(32435)) {
            wifiDownloadPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(32434)) {
                        preference.setSummary(getAutoDownloadSummary((Set<String>) newValue));
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32436)) {
            wifiDownloadPreference.setSummary(getAutoDownloadSummary(preferenceService.getWifiAutoDownload()));
        }
        MultiSelectListPreference mobileDownloadPreference = (MultiSelectListPreference) findPreference(getResources().getString(R.string.preferences__auto_download_mobile));
        if (!ListenerUtil.mutListener.listen(32438)) {
            mobileDownloadPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(32437)) {
                        preference.setSummary(getAutoDownloadSummary((Set<String>) newValue));
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32439)) {
            mobileDownloadPreference.setSummary(getAutoDownloadSummary(preferenceService.getMobileAutoDownload()));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32440)) {
            this.fragmentView = view;
        }
        if (!ListenerUtil.mutListener.listen(32441)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.prefs_media_title);
        }
        if (!ListenerUtil.mutListener.listen(32442)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(32452)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_SAVE_MEDIA:
                    if (!ListenerUtil.mutListener.listen(32451)) {
                        if ((ListenerUtil.mutListener.listen(32448) ? ((ListenerUtil.mutListener.listen(32447) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(32446) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(32445) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(32444) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(32443) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(32447) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(32446) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(32445) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(32444) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(32443) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(32450)) {
                                saveMediaPreference.setChecked(true);
                            }
                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (!ListenerUtil.mutListener.listen(32449)) {
                                ConfigUtils.showPermissionRationale(getContext(), fragmentView, R.string.permission_storage_required);
                            }
                        }
                    }
                    break;
            }
        }
    }

    private CharSequence getAutoDownloadSummary(Set<String> selectedOptions) {
        String[] values = getResources().getStringArray(R.array.list_auto_download_values);
        List<String> result = new ArrayList<>(selectedOptions.size());
        if (!ListenerUtil.mutListener.listen(32460)) {
            {
                long _loopCounter228 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(32459) ? (i >= values.length) : (ListenerUtil.mutListener.listen(32458) ? (i <= values.length) : (ListenerUtil.mutListener.listen(32457) ? (i > values.length) : (ListenerUtil.mutListener.listen(32456) ? (i != values.length) : (ListenerUtil.mutListener.listen(32455) ? (i == values.length) : (i < values.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter228", ++_loopCounter228);
                    if (!ListenerUtil.mutListener.listen(32454)) {
                        if (selectedOptions.contains(values[i]))
                            if (!ListenerUtil.mutListener.listen(32453)) {
                                result.add(getResources().getStringArray(R.array.list_auto_download)[i]);
                            }
                    }
                }
            }
        }
        return result.isEmpty() ? getResources().getString(R.string.never) : TextUtils.join(", ", result);
    }
}

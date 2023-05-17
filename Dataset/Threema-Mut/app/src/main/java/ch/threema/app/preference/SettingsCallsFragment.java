/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsCallsFragment extends ThreemaPreferenceFragment {

    private static final Logger logger = LoggerFactory.getLogger(SettingsCallsFragment.class);

    private static final int PERMISSION_REQUEST_READ_PHONE_STATE = 3;

    private View fragmentView;

    private CheckBoxPreference enableCallReject;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32302)) {
            addPreferencesFromResource(R.xml.preference_calls);
        }
        PreferenceService preferenceService;
        try {
            preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(32303)) {
                logger.error("Exception", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(32304)) {
            if (preferenceService == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(32322)) {
            if (ConfigUtils.isWorkRestricted()) {
                CheckBoxPreference callEnable = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__voip_enable));
                PreferenceCategory videoCategory = (PreferenceCategory) findPreference("pref_key_voip_video_settings");
                CheckBoxPreference videoCallEnable = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__voip_video_enable));
                DropDownPreference videoCallProfile = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__voip_video_profile));
                Boolean disableCalls = AppRestrictionUtil.getBooleanRestriction(getResources().getString(R.string.restriction__disable_calls));
                Boolean disableVideoCalls = AppRestrictionUtil.getBooleanRestriction(getResources().getString(R.string.restriction__disable_video_calls));
                if (!ListenerUtil.mutListener.listen(32310)) {
                    if (disableCalls != null) {
                        if (!ListenerUtil.mutListener.listen(32305)) {
                            // admin does not want user to tamper with call setting
                            callEnable.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32306)) {
                            callEnable.setSelectable(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32307)) {
                            callEnable.setChecked(!disableCalls);
                        }
                        if (!ListenerUtil.mutListener.listen(32309)) {
                            if (disableCalls) {
                                if (!ListenerUtil.mutListener.listen(32308)) {
                                    // disabled calls also disable video calls
                                    disableVideoCalls = true;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(32314)) {
                    if (disableVideoCalls != null) {
                        if (!ListenerUtil.mutListener.listen(32311)) {
                            // admin does not want user to tamper with video call setting
                            videoCallEnable.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32312)) {
                            videoCallEnable.setSelectable(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32313)) {
                            videoCallEnable.setChecked(!disableVideoCalls);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(32321)) {
                    if ((ListenerUtil.mutListener.listen(32315) ? (disableVideoCalls == null && !disableVideoCalls) : (disableVideoCalls == null || !disableVideoCalls))) {
                        if (!ListenerUtil.mutListener.listen(32316)) {
                            // video calls are force-enabled or left to the user - user may change profile setting
                            videoCategory.setDependency(null);
                        }
                        if (!ListenerUtil.mutListener.listen(32317)) {
                            videoCategory.setEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(32318)) {
                            videoCallProfile.setDependency(null);
                        }
                        if (!ListenerUtil.mutListener.listen(32319)) {
                            videoCallProfile.setEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(32320)) {
                            videoCallProfile.setSelectable(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32323)) {
            this.enableCallReject = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__voip_reject_mobile_calls));
        }
        if (!ListenerUtil.mutListener.listen(32325)) {
            this.enableCallReject.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean newCheckedValue = newValue.equals(true);
                    if (!ListenerUtil.mutListener.listen(32324)) {
                        if (newCheckedValue) {
                            return ConfigUtils.requestPhonePermissions(getActivity(), SettingsCallsFragment.this, PERMISSION_REQUEST_READ_PHONE_STATE);
                        }
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32326)) {
            this.fragmentView = view;
        }
        if (!ListenerUtil.mutListener.listen(32327)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.prefs_title_voip);
        }
        if (!ListenerUtil.mutListener.listen(32328)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(32338)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_READ_PHONE_STATE:
                    if (!ListenerUtil.mutListener.listen(32337)) {
                        if ((ListenerUtil.mutListener.listen(32334) ? ((ListenerUtil.mutListener.listen(32333) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(32332) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(32331) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(32330) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(32329) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(32333) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(32332) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(32331) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(32330) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(32329) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(32336)) {
                                this.enableCallReject.setChecked(true);
                            }
                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                            if (!ListenerUtil.mutListener.listen(32335)) {
                                ConfigUtils.showPermissionRationale(getContext(), fragmentView, R.string.permission_phone_required);
                            }
                        }
                    }
                    break;
            }
        }
    }
}

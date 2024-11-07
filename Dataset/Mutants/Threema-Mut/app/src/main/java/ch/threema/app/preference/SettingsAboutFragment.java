/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import ch.threema.app.BuildConfig;
import ch.threema.app.BuildFlavor;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.AboutActivity;
import ch.threema.app.activities.DownloadApkActivity;
import ch.threema.app.activities.LicenseActivity;
import ch.threema.app.activities.PrivacyPolicyActivity;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.license.LicenseServiceSerial;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsAboutFragment extends ThreemaPreferenceFragment {

    private static final Logger logger = LoggerFactory.getLogger(SettingsAboutFragment.class);

    private static final int ABOUT_REQUIRED_CLICKS = 10;

    private static final String DIALOG_TAG_CHECK_UPDATE = "checkup";

    private String updateUrl, updateMessage;

    private int aboutCounter;

    private PreferenceService preferenceService;

    private LicenseService licenseService;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32034)) {
            if (!requiredInstances()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(32035)) {
            addPreferencesFromResource(R.xml.preference_about);
        }
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("pref_key_about");
        PreferenceCategory aboutCategory = (PreferenceCategory) findPreference("pref_key_about_header");
        Preference aboutPreference = findPreference(getResources().getString(R.string.preferences__about));
        Preference workLicensePreference = findPreference(getResources().getString(R.string.preferences__work_license_name));
        Preference licensePreference = findPreference(getResources().getString(R.string.preferences__licenses));
        Preference privacyPolicyPreference = findPreference(getResources().getString(R.string.preferences__privacy_policy));
        Preference checkUpdatePreference = findPreference(getResources().getString(R.string.preferences__check_updates));
        Preference troubleShootingPreference = findPreference(getResources().getString(R.string.preferences__troubleshooting));
        Preference deviceInfoPreference = findPreference(getResources().getString(R.string.preferences__device_info));
        Preference translatorsPreference = findPreference(getResources().getString(R.string.preferences__translators));
        if (!ListenerUtil.mutListener.listen(32036)) {
            aboutPreference.setTitle(getString(R.string.threema_version) + " " + getVersionString());
        }
        if (!ListenerUtil.mutListener.listen(32037)) {
            aboutPreference.setSummary(R.string.about_copyright);
        }
        if (!ListenerUtil.mutListener.listen(32044)) {
            aboutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (aboutCounter == ABOUT_REQUIRED_CLICKS) {
                        if (!ListenerUtil.mutListener.listen(32039)) {
                            aboutCounter++;
                        }
                        final Intent intent = new Intent(getActivity().getApplicationContext(), AboutActivity.class);
                        if (!ListenerUtil.mutListener.listen(32040)) {
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                        }
                        if (!ListenerUtil.mutListener.listen(32041)) {
                            startActivity(intent);
                        }
                        final Activity activity = getActivity();
                        if (!ListenerUtil.mutListener.listen(32043)) {
                            if (activity != null) {
                                if (!ListenerUtil.mutListener.listen(32042)) {
                                    activity.finish();
                                }
                            }
                        }
                        return true;
                    } else {
                        if (!ListenerUtil.mutListener.listen(32038)) {
                            aboutCounter++;
                        }
                        return false;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32046)) {
            licensePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32045)) {
                        startActivity(new Intent(getActivity().getApplicationContext(), LicenseActivity.class));
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32048)) {
            privacyPolicyPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32047)) {
                        startActivity(new Intent(getActivity().getApplicationContext(), PrivacyPolicyActivity.class));
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32053)) {
            if ((ListenerUtil.mutListener.listen(32049) ? (ConfigUtils.isSerialLicensed() || !ConfigUtils.isWorkBuild()) : (ConfigUtils.isSerialLicensed() && !ConfigUtils.isWorkBuild()))) {
                if (!ListenerUtil.mutListener.listen(32052)) {
                    checkUpdatePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            if (!ListenerUtil.mutListener.listen(32051)) {
                                checkForUpdates((LicenseServiceSerial) licenseService);
                            }
                            return true;
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32050)) {
                    aboutCategory.removePreference(checkUpdatePreference);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32061)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(32055)) {
                    workLicensePreference.setSummary(preferenceService.getLicenseUsername());
                }
                if (!ListenerUtil.mutListener.listen(32056)) {
                    preferenceScreen.removePreference(findPreference("pref_key_feedback_header"));
                }
                if (!ListenerUtil.mutListener.listen(32060)) {
                    if (ConfigUtils.isWorkRestricted()) {
                        Boolean readonly = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__readonly_profile));
                        if (!ListenerUtil.mutListener.listen(32059)) {
                            if ((ListenerUtil.mutListener.listen(32057) ? (readonly != null || readonly) : (readonly != null && readonly))) {
                                if (!ListenerUtil.mutListener.listen(32058)) {
                                    aboutCategory.removePreference(workLicensePreference);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32054)) {
                    aboutCategory.removePreference(workLicensePreference);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32065)) {
            if (deviceInfoPreference != null) {
                if (!ListenerUtil.mutListener.listen(32063)) {
                    if (Build.MANUFACTURER != null) {
                        if (!ListenerUtil.mutListener.listen(32062)) {
                            deviceInfoPreference.setTitle(Build.MANUFACTURER + " " + Build.MODEL);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(32064)) {
                    deviceInfoPreference.setSummary(Build.FINGERPRINT);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32071)) {
            if (troubleShootingPreference != null) {
                if (!ListenerUtil.mutListener.listen(32070)) {
                    troubleShootingPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            final Header header = new Header();
                            if (!ListenerUtil.mutListener.listen(32066)) {
                                header.fragment = "ch.threema.app.preference.SettingsTroubleshootingFragment";
                            }
                            if (!ListenerUtil.mutListener.listen(32067)) {
                                header.iconRes = R.drawable.ic_bug_outline;
                            }
                            if (!ListenerUtil.mutListener.listen(32068)) {
                                header.titleRes = R.string.prefs_troubleshooting;
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(32069)) {
                                    ((SettingsActivity) getActivity()).switchToHeader(header);
                                }
                            } catch (Exception ignore) {
                            }
                            return true;
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32073)) {
            translatorsPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32072)) {
                        SimpleStringAlertDialog.newInstance(R.string.translators, getString(R.string.translators_thanks, getString(R.string.translators_list))).show(getFragmentManager(), "tt");
                    }
                    return true;
                }
            });
        }
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(32075)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(32074)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.preferenceService, this.licenseService);
    }

    protected void instantiate() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(32079)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(32076)) {
                    this.preferenceService = serviceManager.getPreferenceService();
                }
                try {
                    if (!ListenerUtil.mutListener.listen(32078)) {
                        this.licenseService = serviceManager.getLicenseService();
                    }
                } catch (FileSystemNotPresentException e) {
                    if (!ListenerUtil.mutListener.listen(32077)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32080)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.menu_about);
        }
        if (!ListenerUtil.mutListener.listen(32081)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    private String getVersionString() {
        final Context context = getContext();
        final StringBuilder version = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(32082)) {
            version.append(ConfigUtils.getFullAppVersion(context));
        }
        if (!ListenerUtil.mutListener.listen(32083)) {
            version.append(" Build ").append(ConfigUtils.getBuildNumber(context));
        }
        if (!ListenerUtil.mutListener.listen(32084)) {
            version.append(" ").append(BuildFlavor.getName());
        }
        if (!ListenerUtil.mutListener.listen(32086)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(32085)) {
                    version.append(" Commit ").append(BuildConfig.GIT_HASH);
                }
            }
        }
        return version.toString();
    }

    @SuppressLint("StaticFieldLeak")
    private void checkForUpdates(@NonNull final LicenseServiceSerial licenseServiceSerial) {
        if (!ListenerUtil.mutListener.listen(32098)) {
            if (BuildConfig.FLAVOR.equals("store_threema")) {
                if (!ListenerUtil.mutListener.listen(32097)) {
                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(32087)) {
                                GenericProgressDialog.newInstance(R.string.check_updates, R.string.please_wait).show(getActivity().getSupportFragmentManager(), DIALOG_TAG_CHECK_UPDATE);
                            }
                        }

                        @Override
                        protected String doInBackground(Void... voids) {
                            try {
                                if (!ListenerUtil.mutListener.listen(32088)) {
                                    // Validate license and check for updates
                                    licenseServiceSerial.validate(false);
                                }
                                if (!ListenerUtil.mutListener.listen(32089)) {
                                    // be set to a non-null value.
                                    updateUrl = licenseServiceSerial.getUpdateUrl();
                                }
                                if (!ListenerUtil.mutListener.listen(32090)) {
                                    updateMessage = licenseServiceSerial.getUpdateMessage();
                                }
                                if (TestUtil.empty(updateUrl, updateMessage)) {
                                    // No update available...
                                    return getString(R.string.no_update_available);
                                }
                                // Update available!
                                return null;
                            } catch (final Exception x) {
                                return String.format(getString(R.string.an_error_occurred_more), x.getLocalizedMessage());
                            }
                        }

                        @Override
                        protected void onPostExecute(String error) {
                            if (!ListenerUtil.mutListener.listen(32091)) {
                                DialogUtil.dismissDialog(getParentFragmentManager(), DIALOG_TAG_CHECK_UPDATE, true);
                            }
                            if (!ListenerUtil.mutListener.listen(32096)) {
                                if (error != null) {
                                    if (!ListenerUtil.mutListener.listen(32095)) {
                                        SimpleStringAlertDialog.newInstance(R.string.check_updates, error).show(getParentFragmentManager(), "nu");
                                    }
                                } else {
                                    Intent dialogIntent = IntentDataUtil.createActionIntentUpdateAvailable(updateMessage, updateUrl);
                                    if (!ListenerUtil.mutListener.listen(32092)) {
                                        dialogIntent.putExtra(DownloadApkActivity.EXTRA_FORCE_UPDATE_DIALOG, true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32093)) {
                                        dialogIntent.setClass(getContext(), DownloadApkActivity.class);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32094)) {
                                        startActivity(dialogIntent);
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            }
        }
    }
}

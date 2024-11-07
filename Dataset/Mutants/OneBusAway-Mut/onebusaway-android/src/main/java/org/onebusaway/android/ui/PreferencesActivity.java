/*
 * Copyright (C) 2010-2017 Brian Ferris (bdferris@onebusaway.org),
 * University of South Florida (sjbarbeau@gmail.com),
 * Microsoft Corporation
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

import static org.onebusaway.android.util.PermissionUtils.RESTORE_BACKUP_PERMISSION_REQUEST;
import static org.onebusaway.android.util.PermissionUtils.SAVE_BACKUP_PERMISSION_REQUEST;
import static org.onebusaway.android.util.PermissionUtils.STORAGE_PERMISSIONS;
import static org.onebusaway.android.util.UIUtils.setAppTheme;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.region.ObaRegionsTask;
import org.onebusaway.android.travelbehavior.TravelBehaviorManager;
import org.onebusaway.android.travelbehavior.io.coroutines.FirebaseDataPusher;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorUtils;
import org.onebusaway.android.util.BackupUtils;
import org.onebusaway.android.util.BuildFlavorUtils;
import org.onebusaway.android.util.PermissionUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.ShowcaseViewUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PreferencesActivity extends PreferenceActivity implements Preference.OnPreferenceClickListener, OnPreferenceChangeListener, SharedPreferences.OnSharedPreferenceChangeListener, ObaRegionsTask.Callback {

    private static final String TAG = "PreferencesActivity";

    public static final String SHOW_CHECK_REGION_DIALOG = ".checkRegionDialog";

    public static final int REQUEST_CODE_RESTORE_BACKUP = 1234;

    Preference mPreference;

    Preference mLeftHandMode;

    Preference mCustomApiUrlPref;

    Preference mCustomOtpApiUrlPref;

    Preference mAnalyticsPref;

    CheckBoxPreference mTravelBehaviorPref;

    Preference mHideAlertsPref;

    Preference mTutorialPref;

    Preference mDonatePref;

    Preference mPoweredByObaPref;

    Preference mAboutPref;

    Preference mSaveBackup;

    Preference mRestoreBackup;

    Preference pushFirebaseData;

    boolean mAutoSelectInitialValue;

    boolean mOtpCustomAPIUrlChanged = false;

    ListPreference preferredUnits;

    ListPreference mThemePref;

    private FirebaseAnalytics mFirebaseAnalytics;

    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2866)) {
            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(2867)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2868)) {
            setProgressBarIndeterminate(true);
        }
        if (!ListenerUtil.mutListener.listen(2869)) {
            addPreferencesFromResource(R.xml.preferences);
        }
        if (!ListenerUtil.mutListener.listen(2870)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        if (!ListenerUtil.mutListener.listen(2871)) {
            mPreference = findPreference(getString(R.string.preference_key_region));
        }
        if (!ListenerUtil.mutListener.listen(2872)) {
            mPreference.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2873)) {
            mLeftHandMode = findPreference(getString(R.string.preference_key_left_hand_mode));
        }
        if (!ListenerUtil.mutListener.listen(2874)) {
            mLeftHandMode.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2875)) {
            mSaveBackup = findPreference(getString(R.string.preference_key_save_backup));
        }
        if (!ListenerUtil.mutListener.listen(2876)) {
            mSaveBackup.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2877)) {
            mRestoreBackup = findPreference(getString(R.string.preference_key_restore_backup));
        }
        if (!ListenerUtil.mutListener.listen(2878)) {
            mRestoreBackup.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2879)) {
            mCustomApiUrlPref = findPreference(getString(R.string.preference_key_oba_api_url));
        }
        if (!ListenerUtil.mutListener.listen(2880)) {
            mCustomApiUrlPref.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2881)) {
            mCustomOtpApiUrlPref = findPreference(getString(R.string.preference_key_otp_api_url));
        }
        if (!ListenerUtil.mutListener.listen(2882)) {
            mCustomOtpApiUrlPref.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2883)) {
            mAnalyticsPref = findPreference(getString(R.string.preferences_key_analytics));
        }
        if (!ListenerUtil.mutListener.listen(2884)) {
            mAnalyticsPref.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2885)) {
            mTravelBehaviorPref = (CheckBoxPreference) findPreference(getString(R.string.preferences_key_travel_behavior));
        }
        if (!ListenerUtil.mutListener.listen(2886)) {
            mTravelBehaviorPref.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2891)) {
            if ((ListenerUtil.mutListener.listen(2888) ? (!TravelBehaviorUtils.isTravelBehaviorActiveInRegion() && ((ListenerUtil.mutListener.listen(2887) ? (!TravelBehaviorUtils.allowEnrollMoreParticipantsInStudy() || !TravelBehaviorUtils.isUserParticipatingInStudy()) : (!TravelBehaviorUtils.allowEnrollMoreParticipantsInStudy() && !TravelBehaviorUtils.isUserParticipatingInStudy())))) : (!TravelBehaviorUtils.isTravelBehaviorActiveInRegion() || ((ListenerUtil.mutListener.listen(2887) ? (!TravelBehaviorUtils.allowEnrollMoreParticipantsInStudy() || !TravelBehaviorUtils.isUserParticipatingInStudy()) : (!TravelBehaviorUtils.allowEnrollMoreParticipantsInStudy() && !TravelBehaviorUtils.isUserParticipatingInStudy())))))) {
                PreferenceCategory aboutCategory = (PreferenceCategory) findPreference(getString(R.string.preferences_category_about));
                if (!ListenerUtil.mutListener.listen(2890)) {
                    aboutCategory.removePreference(mTravelBehaviorPref);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2889)) {
                    mTravelBehaviorPref.setChecked(TravelBehaviorUtils.isUserParticipatingInStudy());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2892)) {
            pushFirebaseData = findPreference(getString(R.string.preference_key_push_firebase_data));
        }
        if (!ListenerUtil.mutListener.listen(2893)) {
            pushFirebaseData.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2894)) {
            mHideAlertsPref = findPreference(getString(R.string.preference_key_hide_alerts));
        }
        if (!ListenerUtil.mutListener.listen(2895)) {
            mHideAlertsPref.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2896)) {
            mTutorialPref = findPreference(getString(R.string.preference_key_tutorial));
        }
        if (!ListenerUtil.mutListener.listen(2897)) {
            mTutorialPref.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2898)) {
            mDonatePref = findPreference(getString(R.string.preferences_key_donate));
        }
        if (!ListenerUtil.mutListener.listen(2899)) {
            mDonatePref.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2900)) {
            mPoweredByObaPref = findPreference(getString(R.string.preferences_key_powered_by_oba));
        }
        if (!ListenerUtil.mutListener.listen(2901)) {
            mPoweredByObaPref.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2902)) {
            mAboutPref = findPreference(getString(R.string.preferences_key_about));
        }
        if (!ListenerUtil.mutListener.listen(2903)) {
            mAboutPref.setOnPreferenceClickListener(this);
        }
        SharedPreferences settings = Application.getPrefs();
        if (!ListenerUtil.mutListener.listen(2904)) {
            mAutoSelectInitialValue = settings.getBoolean(getString(R.string.preference_key_auto_select_region), true);
        }
        if (!ListenerUtil.mutListener.listen(2905)) {
            preferredUnits = (ListPreference) findPreference(getString(R.string.preference_key_preferred_units));
        }
        if (!ListenerUtil.mutListener.listen(2906)) {
            mThemePref = (ListPreference) findPreference(getString(R.string.preference_key_app_theme));
        }
        if (!ListenerUtil.mutListener.listen(2907)) {
            mThemePref.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2908)) {
            settings.registerOnSharedPreferenceChangeListener(this);
        }
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (!ListenerUtil.mutListener.listen(2912)) {
            // Hide any preferences that shouldn't be shown if the region is hard-coded via build flavor
            if (BuildConfig.USE_FIXED_REGION) {
                PreferenceCategory regionCategory = (PreferenceCategory) findPreference(getString(R.string.preferences_category_location));
                if (!ListenerUtil.mutListener.listen(2909)) {
                    regionCategory.removeAll();
                }
                if (!ListenerUtil.mutListener.listen(2910)) {
                    preferenceScreen.removePreference(regionCategory);
                }
                PreferenceCategory advancedCategory = (PreferenceCategory) findPreference(getString(R.string.preferences_category_advanced));
                Preference experimentalRegion = findPreference(getString(R.string.preference_key_experimental_regions));
                if (!ListenerUtil.mutListener.listen(2911)) {
                    advancedCategory.removePreference(experimentalRegion);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2919)) {
            // If the Android version is Oreo (8.0) hide "Notification" preference
            if ((ListenerUtil.mutListener.listen(2917) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(2916) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(2915) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(2914) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(2913) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                if (!ListenerUtil.mutListener.listen(2918)) {
                    getPreferenceScreen().removePreference(findPreference(getString(R.string.preference_key_notifications)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2932)) {
            // If the Android version is lower than Nougat (7.0) and equal to or above Pie (9.0) hide "Share trip logs" preference
            if ((ListenerUtil.mutListener.listen(2930) ? (((ListenerUtil.mutListener.listen(2924) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(2923) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(2922) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(2921) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(2920) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.N))))))) && ((ListenerUtil.mutListener.listen(2929) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(2928) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(2927) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(2926) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(2925) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)))))))) : (((ListenerUtil.mutListener.listen(2924) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(2923) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(2922) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(2921) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(2920) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.N))))))) || ((ListenerUtil.mutListener.listen(2929) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(2928) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(2927) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(2926) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(2925) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)))))))))) {
                if (!ListenerUtil.mutListener.listen(2931)) {
                    getPreferenceScreen().removePreference(findPreference(getString(R.string.preferences_key_user_debugging_logs_category)));
                }
            }
        }
        // If its the OBA brand flavor, then show the "Donate" preference and hide "Powered by OBA"
        PreferenceCategory aboutCategory = (PreferenceCategory) findPreference(getString(R.string.preferences_category_about));
        if (!ListenerUtil.mutListener.listen(2935)) {
            if (BuildConfig.FLAVOR_brand.equalsIgnoreCase(BuildFlavorUtils.OBA_FLAVOR_BRAND)) {
                if (!ListenerUtil.mutListener.listen(2934)) {
                    aboutCategory.removePreference(mPoweredByObaPref);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2933)) {
                    // Its not the OBA brand flavor, then hide the "Donate" preference and show "Powered by OBA"
                    aboutCategory.removePreference(mDonatePref);
                }
            }
        }
        boolean showCheckRegionDialog = getIntent().getBooleanExtra(SHOW_CHECK_REGION_DIALOG, false);
        if (!ListenerUtil.mutListener.listen(2937)) {
            if (showCheckRegionDialog) {
                if (!ListenerUtil.mutListener.listen(2936)) {
                    showCheckRegionDialog();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(2938)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(2939)) {
            changePreferenceSummary(getString(R.string.preference_key_region));
        }
        if (!ListenerUtil.mutListener.listen(2940)) {
            changePreferenceSummary(getString(R.string.preference_key_preferred_units));
        }
        if (!ListenerUtil.mutListener.listen(2941)) {
            changePreferenceSummary(getString(R.string.preference_key_app_theme));
        }
        if (!ListenerUtil.mutListener.listen(2942)) {
            changePreferenceSummary(getString(R.string.preference_key_otp_api_url));
        }
        // Remove preferences for notifications if no trip planning
        ObaRegion obaRegion = Application.get().getCurrentRegion();
        if (!ListenerUtil.mutListener.listen(2947)) {
            if ((ListenerUtil.mutListener.listen(2943) ? (obaRegion != null || TextUtils.isEmpty(obaRegion.getOtpBaseUrl())) : (obaRegion != null && TextUtils.isEmpty(obaRegion.getOtpBaseUrl())))) {
                PreferenceCategory notifications = (PreferenceCategory) findPreference(getString(R.string.preference_key_notifications));
                Preference tripPlan = findPreference(getString(R.string.preference_key_trip_plan_notifications));
                if (!ListenerUtil.mutListener.listen(2946)) {
                    if ((ListenerUtil.mutListener.listen(2944) ? (notifications != null || tripPlan != null) : (notifications != null && tripPlan != null))) {
                        if (!ListenerUtil.mutListener.listen(2945)) {
                            notifications.removePreference(tripPlan);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2948)) {
            super.onPostCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2949)) {
            setupActionBar();
        }
    }

    private void showCheckRegionDialog() {
        ObaRegion obaRegion = Application.get().getCurrentRegion();
        if (!ListenerUtil.mutListener.listen(2950)) {
            if (obaRegion == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2951)) {
            new AlertDialog.Builder(this).setTitle(getString(R.string.preference_region_dialog_title)).setMessage(getString(R.string.preference_region_dialog_message, obaRegion.getName())).setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                }
            }).show();
        }
    }

    /**
     * Changes the summary of a preference based on a given preference key
     *
     * @param preferenceKey preference key that triggers a change in summary
     */
    private void changePreferenceSummary(String preferenceKey) {
        if (!ListenerUtil.mutListener.listen(2967)) {
            // Change the current region summary and server API URL summary
            if ((ListenerUtil.mutListener.listen(2952) ? (preferenceKey.equalsIgnoreCase(getString(R.string.preference_key_region)) && preferenceKey.equalsIgnoreCase(getString(R.string.preference_key_oba_api_url))) : (preferenceKey.equalsIgnoreCase(getString(R.string.preference_key_region)) || preferenceKey.equalsIgnoreCase(getString(R.string.preference_key_oba_api_url))))) {
                if (!ListenerUtil.mutListener.listen(2966)) {
                    if (Application.get().getCurrentRegion() != null) {
                        if (!ListenerUtil.mutListener.listen(2961)) {
                            mPreference.setSummary(Application.get().getCurrentRegion().getName());
                        }
                        if (!ListenerUtil.mutListener.listen(2962)) {
                            mCustomApiUrlPref.setSummary(getString(R.string.preferences_oba_api_servername_summary));
                        }
                        String customOtpApiUrl = Application.get().getCustomOtpApiUrl();
                        if (!ListenerUtil.mutListener.listen(2965)) {
                            if (!TextUtils.isEmpty(customOtpApiUrl)) {
                                if (!ListenerUtil.mutListener.listen(2964)) {
                                    mCustomOtpApiUrlPref.setSummary(customOtpApiUrl);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2963)) {
                                    mCustomOtpApiUrlPref.setSummary(getString(R.string.preferences_otp_api_servername_summary));
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2959)) {
                            mPreference.setSummary(getString(R.string.preferences_region_summary_custom_api));
                        }
                        if (!ListenerUtil.mutListener.listen(2960)) {
                            mCustomApiUrlPref.setSummary(Application.get().getCustomApiUrl());
                        }
                    }
                }
            } else if (preferenceKey.equalsIgnoreCase(getString(R.string.preference_key_preferred_units))) {
                if (!ListenerUtil.mutListener.listen(2958)) {
                    preferredUnits.setSummary(preferredUnits.getValue());
                }
            } else if (preferenceKey.equalsIgnoreCase(getString(R.string.preference_key_app_theme))) {
                if (!ListenerUtil.mutListener.listen(2957)) {
                    mThemePref.setSummary(mThemePref.getValue());
                }
            } else if (preferenceKey.equalsIgnoreCase(getString(R.string.preference_key_otp_api_url))) {
                String customOtpApiUrl = Application.get().getCustomOtpApiUrl();
                if (!ListenerUtil.mutListener.listen(2955)) {
                    if (!TextUtils.isEmpty(customOtpApiUrl)) {
                        if (!ListenerUtil.mutListener.listen(2954)) {
                            mCustomOtpApiUrlPref.setSummary(customOtpApiUrl);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2953)) {
                            mCustomOtpApiUrlPref.setSummary(getString(R.string.preferences_otp_api_servername_summary));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2956)) {
                    Application.get().setUseOldOtpApiUrlVersion(false);
                }
            }
        }
    }

    @Override
    public boolean onPreferenceClick(Preference pref) {
        if (!ListenerUtil.mutListener.listen(2968)) {
            Log.d(TAG, "preference - " + pref.getKey());
        }
        if (!ListenerUtil.mutListener.listen(2982)) {
            if (pref.equals(mPreference)) {
                if (!ListenerUtil.mutListener.listen(2981)) {
                    RegionsActivity.start(this);
                }
            } else if (pref.equals(mTutorialPref)) {
                if (!ListenerUtil.mutListener.listen(2978)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_tutorial), null);
                }
                if (!ListenerUtil.mutListener.listen(2979)) {
                    ShowcaseViewUtils.resetAllTutorials(this);
                }
                if (!ListenerUtil.mutListener.listen(2980)) {
                    NavHelp.goHome(this, true);
                }
            } else if (pref.equals(mDonatePref)) {
                if (!ListenerUtil.mutListener.listen(2976)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_donate), null);
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.donate_url)));
                if (!ListenerUtil.mutListener.listen(2977)) {
                    startActivity(intent);
                }
            } else if (pref.equals(mPoweredByObaPref)) {
                if (!ListenerUtil.mutListener.listen(2974)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_powered_by_oba), null);
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.powered_by_oba_url)));
                if (!ListenerUtil.mutListener.listen(2975)) {
                    startActivity(intent);
                }
            } else if (pref.equals(mAboutPref)) {
                if (!ListenerUtil.mutListener.listen(2972)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_about), null);
                }
                if (!ListenerUtil.mutListener.listen(2973)) {
                    AboutActivity.start(this);
                }
            } else if (pref.equals(mSaveBackup)) {
                if (!ListenerUtil.mutListener.listen(2971)) {
                    // been granted yet so we can handle permissions here
                    maybeRequestPermissions(SAVE_BACKUP_PERMISSION_REQUEST);
                }
            } else if (pref.equals(mRestoreBackup)) {
                if (!ListenerUtil.mutListener.listen(2970)) {
                    // been granted yet so we can handle permissions here.
                    maybeRequestPermissions(RESTORE_BACKUP_PERMISSION_REQUEST);
                }
            } else if (pref.equals(pushFirebaseData)) {
                // Try to push firebase data to the server
                FirebaseDataPusher pusher = new FirebaseDataPusher();
                if (!ListenerUtil.mutListener.listen(2969)) {
                    pusher.push(this);
                }
            }
        }
        return true;
    }

    private void maybeRequestPermissions(int permissionRequest) {
        if (!ListenerUtil.mutListener.listen(2984)) {
            if (!PermissionUtils.hasGrantedAllPermissions(this, STORAGE_PERMISSIONS)) {
                if (!ListenerUtil.mutListener.listen(2983)) {
                    // Request permissions from the user
                    ActivityCompat.requestPermissions(this, STORAGE_PERMISSIONS, permissionRequest);
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        int result = PackageManager.PERMISSION_DENIED;
        if (!ListenerUtil.mutListener.listen(2997)) {
            if ((ListenerUtil.mutListener.listen(2985) ? (requestCode == SAVE_BACKUP_PERMISSION_REQUEST && requestCode == RESTORE_BACKUP_PERMISSION_REQUEST) : (requestCode == SAVE_BACKUP_PERMISSION_REQUEST || requestCode == RESTORE_BACKUP_PERMISSION_REQUEST))) {
                if (!ListenerUtil.mutListener.listen(2996)) {
                    if ((ListenerUtil.mutListener.listen(2991) ? ((ListenerUtil.mutListener.listen(2990) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(2989) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(2988) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(2987) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(2986) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(2990) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(2989) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(2988) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(2987) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(2986) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                        if (!ListenerUtil.mutListener.listen(2993)) {
                            result = PackageManager.PERMISSION_GRANTED;
                        }
                        if (!ListenerUtil.mutListener.listen(2995)) {
                            // User granted permission
                            if (requestCode == SAVE_BACKUP_PERMISSION_REQUEST) {
                                if (!ListenerUtil.mutListener.listen(2994)) {
                                    BackupUtils.save(this);
                                }
                            } else {
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2992)) {
                            showStoragePermissionDialog(this, requestCode);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(2998)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(3005)) {
            if ((ListenerUtil.mutListener.listen(3003) ? (requestCode >= REQUEST_CODE_RESTORE_BACKUP) : (ListenerUtil.mutListener.listen(3002) ? (requestCode <= REQUEST_CODE_RESTORE_BACKUP) : (ListenerUtil.mutListener.listen(3001) ? (requestCode > REQUEST_CODE_RESTORE_BACKUP) : (ListenerUtil.mutListener.listen(3000) ? (requestCode < REQUEST_CODE_RESTORE_BACKUP) : (ListenerUtil.mutListener.listen(2999) ? (requestCode != REQUEST_CODE_RESTORE_BACKUP) : (requestCode == REQUEST_CODE_RESTORE_BACKUP))))))) {
                if (!ListenerUtil.mutListener.listen(3004)) {
                    BackupUtils.restore(this, data.getData());
                }
            }
        }
    }

    /**
     * Shows the dialog to explain why storage permissions are needed
     * @param activity Activity used to show the dialog
     * @param requestCode The requesting permission code (SAVE_BACKUP_PERMISSION_REQUEST or RESTORE_BACKUP_PERMISSION_REQUEST)
     */
    private void showStoragePermissionDialog(Activity activity, int requestCode) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this).setTitle(R.string.storage_permissions_title).setMessage(R.string.storage_permissions_message).setCancelable(false).setPositiveButton(R.string.ok, (dialog, which) -> {
            // Request permissions from the user
            ActivityCompat.requestPermissions(activity, STORAGE_PERMISSIONS, requestCode);
        }).setNegativeButton(R.string.no_thanks, (dialog, which) -> {
        });
        if (!ListenerUtil.mutListener.listen(3006)) {
            builder.create().show();
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (!ListenerUtil.mutListener.listen(3015)) {
            if ((ListenerUtil.mutListener.listen(3007) ? (preference.equals(mCustomApiUrlPref) || newValue instanceof String) : (preference.equals(mCustomApiUrlPref) && newValue instanceof String))) {
                String apiUrl = (String) newValue;
                if (!ListenerUtil.mutListener.listen(3014)) {
                    if (!TextUtils.isEmpty(apiUrl)) {
                        boolean validUrl = validateUrl(apiUrl);
                        if (!ListenerUtil.mutListener.listen(3011)) {
                            if (!validUrl) {
                                if (!ListenerUtil.mutListener.listen(3010)) {
                                    Toast.makeText(this, getString(R.string.custom_api_url_error), Toast.LENGTH_SHORT).show();
                                }
                                return false;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3012)) {
                            // User entered a custom API Url, so set the region info to null
                            Application.get().setCurrentRegion(null);
                        }
                        if (!ListenerUtil.mutListener.listen(3013)) {
                            Log.d(TAG, "User entered new API URL, set region to null.");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3008)) {
                            // User cleared the API URL preference value, so re-initialize regions
                            Log.d(TAG, "User entered blank API URL, re-initializing regions...");
                        }
                        if (!ListenerUtil.mutListener.listen(3009)) {
                            NavHelp.goHome(this, false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3032)) {
            if ((ListenerUtil.mutListener.listen(3016) ? (preference.equals(mCustomOtpApiUrlPref) || newValue instanceof String) : (preference.equals(mCustomOtpApiUrlPref) && newValue instanceof String))) {
                String apiUrl = (String) newValue;
                if (!ListenerUtil.mutListener.listen(3030)) {
                    if (!TextUtils.isEmpty(apiUrl)) {
                        boolean validUrl = validateUrl(apiUrl);
                        if (!ListenerUtil.mutListener.listen(3029)) {
                            if (!validUrl) {
                                if (!ListenerUtil.mutListener.listen(3028)) {
                                    Toast.makeText(this, getString(R.string.custom_otp_api_url_error), Toast.LENGTH_SHORT).show();
                                }
                                return false;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3031)) {
                    mOtpCustomAPIUrlChanged = true;
                }
            } else if ((ListenerUtil.mutListener.listen(3017) ? (preference.equals(mAnalyticsPref) || newValue instanceof Boolean) : (preference.equals(mAnalyticsPref) && newValue instanceof Boolean))) {
                Boolean isAnalyticsActive = (Boolean) newValue;
                if (!ListenerUtil.mutListener.listen(3027)) {
                    // Report if the analytics turns off, just before shared preference changed
                    ObaAnalytics.setSendAnonymousData(mFirebaseAnalytics, isAnalyticsActive);
                }
            } else if ((ListenerUtil.mutListener.listen(3018) ? (preference.equals(mTravelBehaviorPref) || newValue instanceof Boolean) : (preference.equals(mTravelBehaviorPref) && newValue instanceof Boolean))) {
                Boolean activateTravelBehaviorCollection = (Boolean) newValue;
                if (!ListenerUtil.mutListener.listen(3026)) {
                    if (activateTravelBehaviorCollection) {
                        if (!ListenerUtil.mutListener.listen(3025)) {
                            new TravelBehaviorManager(this, getApplicationContext()).registerTravelBehaviorParticipant(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3024)) {
                            showOptOutDialog();
                        }
                        return false;
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(3019) ? (preference.equals(mLeftHandMode) || newValue instanceof Boolean) : (preference.equals(mLeftHandMode) && newValue instanceof Boolean))) {
                Boolean isLeftHandEnabled = (Boolean) newValue;
                if (!ListenerUtil.mutListener.listen(3023)) {
                    // Report if left handed mode is turned on, just before shared preference changed
                    ObaAnalytics.setLeftHanded(mFirebaseAnalytics, isLeftHandEnabled);
                }
            } else if ((ListenerUtil.mutListener.listen(3020) ? (preference.equals(mHideAlertsPref) || newValue instanceof Boolean) : (preference.equals(mHideAlertsPref) && newValue instanceof Boolean))) {
                Boolean hideAlerts = (Boolean) newValue;
                if (!ListenerUtil.mutListener.listen(3022)) {
                    if (hideAlerts) {
                        if (!ListenerUtil.mutListener.listen(3021)) {
                            ObaContract.ServiceAlerts.hideAllAlerts();
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Shows the dialog to explain user is choosing to opt out of travel behavior research study
     */
    private void showOptOutDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this).setTitle(R.string.travel_behavior_dialog_opt_out_title).setMessage(R.string.travel_behavior_dialog_opt_out_message).setCancelable(false).setPositiveButton(R.string.ok, (dialog, which) -> {
            // Remove user from study
            new TravelBehaviorManager(this, getApplicationContext()).stopCollectingData();
            TravelBehaviorManager.optOutUser();
            TravelBehaviorManager.optOutUserOnServer();
            // Change preference
            mTravelBehaviorPref.setChecked(false);
            PreferenceUtils.saveBoolean(getString(R.string.preferences_key_travel_behavior), false);
        }).setNegativeButton(R.string.cancel, (dialog, which) -> {
        });
        if (!ListenerUtil.mutListener.listen(3033)) {
            builder.create().show();
        }
    }

    @Override
    protected void onDestroy() {
        SharedPreferences settings = Application.getPrefs();
        boolean currentValue = settings.getBoolean(getString(R.string.preference_key_auto_select_region), true);
        if (!ListenerUtil.mutListener.listen(3038)) {
            // then run the auto-select by going to HomeFragment
            if (((ListenerUtil.mutListener.listen(3034) ? (currentValue || !mAutoSelectInitialValue) : (currentValue && !mAutoSelectInitialValue)))) {
                if (!ListenerUtil.mutListener.listen(3036)) {
                    Log.d(TAG, "User re-enabled auto-select regions pref, auto-selecting via Home Activity...");
                }
                if (!ListenerUtil.mutListener.listen(3037)) {
                    NavHelp.goHome(this, false);
                }
            } else if (mOtpCustomAPIUrlChanged) {
                if (!ListenerUtil.mutListener.listen(3035)) {
                    // Redraw the navigation drawer when custom otp api url is entered
                    NavHelp.goHome(this, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3039)) {
            super.onDestroy();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences settings = Application.getPrefs();
        if (!ListenerUtil.mutListener.listen(3058)) {
            // Listening to changes to a custom Preference doesn't seem to work, so we can listen to changes to the shared pref value instead
            if (key.equals(getString(R.string.preference_key_experimental_regions))) {
                boolean experimentalServers = settings.getBoolean(getString(R.string.preference_key_experimental_regions), false);
                if (!ListenerUtil.mutListener.listen(3051)) {
                    Log.d(TAG, "Experimental regions shared preference changed to " + experimentalServers);
                }
                if (!ListenerUtil.mutListener.listen(3052)) {
                    /*
            Force a refresh of the regions list, but don't using blocking progress dialog
            inside the ObaRegionsTask AsyncTask.
            We need to use our own Action Bar progress bar here so its linked to this activity,
            which will survive orientation changes.
            */
                    setProgressBarIndeterminateVisibility(true);
                }
                List<ObaRegionsTask.Callback> callbacks = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(3053)) {
                    callbacks.add(this);
                }
                ObaRegionsTask task = new ObaRegionsTask(this, callbacks, true, false);
                if (!ListenerUtil.mutListener.listen(3054)) {
                    task.execute();
                }
                if (!ListenerUtil.mutListener.listen(3057)) {
                    // Analytics
                    if (experimentalServers) {
                        if (!ListenerUtil.mutListener.listen(3056)) {
                            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_experimental_on), null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3055)) {
                            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_experimental_off), null);
                        }
                    }
                }
            } else if (key.equals(getString(R.string.preference_key_oba_api_url))) {
                if (!ListenerUtil.mutListener.listen(3050)) {
                    // Change the region preference description to show we're not using a region
                    changePreferenceSummary(key);
                }
            } else if (key.equals(getString(R.string.preference_key_otp_api_url))) {
                if (!ListenerUtil.mutListener.listen(3049)) {
                    // Change the otp url preference description
                    changePreferenceSummary(key);
                }
            } else if (key.equalsIgnoreCase(getString(R.string.preference_key_preferred_units))) {
                if (!ListenerUtil.mutListener.listen(3048)) {
                    // Change the preferred units description
                    changePreferenceSummary(key);
                }
            } else if (key.equalsIgnoreCase(getString(R.string.preference_key_app_theme))) {
                if (!ListenerUtil.mutListener.listen(3046)) {
                    // Change the app theme preference description
                    changePreferenceSummary(key);
                }
                if (!ListenerUtil.mutListener.listen(3047)) {
                    // Update the app theme
                    setAppTheme(settings.getString(key, getString(R.string.preferences_app_theme_option_system_default)));
                }
            } else if (key.equalsIgnoreCase(getString(R.string.preference_key_auto_select_region))) {
                // Analytics
                boolean autoSelect = settings.getBoolean(getString(R.string.preference_key_auto_select_region), true);
                if (!ListenerUtil.mutListener.listen(3045)) {
                    if (autoSelect) {
                        if (!ListenerUtil.mutListener.listen(3044)) {
                            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_auto), null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3043)) {
                            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_manual), null);
                        }
                    }
                }
            } else if (key.equalsIgnoreCase(getString(R.string.preferences_key_analytics))) {
                Boolean isAnalyticsActive = settings.getBoolean(Application.get().getString(R.string.preferences_key_analytics), Boolean.TRUE);
                if (!ListenerUtil.mutListener.listen(3042)) {
                    // Report if the analytics turns on, just after shared preference changed
                    ObaAnalytics.setSendAnonymousData(mFirebaseAnalytics, isAnalyticsActive);
                }
            } else if (key.equalsIgnoreCase(getString(R.string.preference_key_arrival_info_style))) {
                if (!ListenerUtil.mutListener.listen(3041)) {
                    // Change the arrival info description
                    changePreferenceSummary(key);
                }
            } else if (key.equalsIgnoreCase(getString(R.string.preference_key_show_negative_arrivals))) {
                boolean showDepartedBuses = settings.getBoolean(Application.get().getString(R.string.preference_key_show_negative_arrivals), Boolean.FALSE);
                if (!ListenerUtil.mutListener.listen(3040)) {
                    ObaAnalytics.setShowDepartedVehicles(mFirebaseAnalytics, showDepartedBuses);
                }
            }
        }
    }

    /**
     * Returns true if the provided apiUrl could be a valid URL, false if it could not
     *
     * @param apiUrl the URL to validate
     * @return true if the provided apiUrl could be a valid URL, false if it could not
     */
    private boolean validateUrl(String apiUrl) {
        try {
            // URI.parse() doesn't tell us if the scheme is missing, so use URL() instead (#126)
            URL url = new URL(apiUrl);
        } catch (MalformedURLException e) {
            if (!ListenerUtil.mutListener.listen(3059)) {
                // Assume HTTPS scheme if none is provided
                apiUrl = getString(R.string.https_prefix) + apiUrl;
            }
        }
        return Patterns.WEB_URL.matcher(apiUrl).matches();
    }

    /**
     * Imitate Action Bar with back button - from http://stackoverflow.com/a/27455363/937715
     */
    private void setupActionBar() {
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        if (!ListenerUtil.mutListener.listen(3060)) {
            // insert at top
            root.addView(bar, 0);
        }
        if (!ListenerUtil.mutListener.listen(3061)) {
            bar.setNavigationOnClickListener(v -> finish());
        }
    }

    // 
    public void onRegionTaskFinished(boolean currentRegionChanged) {
        if (!ListenerUtil.mutListener.listen(3062)) {
            setProgressBarIndeterminateVisibility(false);
        }
        if (!ListenerUtil.mutListener.listen(3068)) {
            if (currentRegionChanged) {
                if (!ListenerUtil.mutListener.listen(3065)) {
                    // If region was auto-selected, show user the region we're using
                    if ((ListenerUtil.mutListener.listen(3063) ? (Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true) || Application.get().getCurrentRegion() != null) : (Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true) && Application.get().getCurrentRegion() != null))) {
                        if (!ListenerUtil.mutListener.listen(3064)) {
                            Toast.makeText(this, getString(R.string.region_region_found, Application.get().getCurrentRegion().getName()), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3066)) {
                    // Update the preference summary to show the newly selected region
                    changePreferenceSummary(getString(R.string.preference_key_region));
                }
                if (!ListenerUtil.mutListener.listen(3067)) {
                    // Since the current region was updated as a result of enabling/disabling experimental servers, go home
                    NavHelp.goHome(this, false);
                }
            }
        }
    }
}

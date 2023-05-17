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

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.view.View;
import com.google.android.material.timepicker.MaterialTimePicker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.CheckBoxPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.RingtoneSelectorDialog;
import ch.threema.app.dialogs.ShowOnceDialog;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RingtoneUtil;
import static com.google.android.material.timepicker.TimeFormat.CLOCK_12H;
import static com.google.android.material.timepicker.TimeFormat.CLOCK_24H;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsNotificationsFragment extends ThreemaPreferenceFragment implements GenericAlertDialog.DialogClickListener, RingtoneSelectorDialog.RingtoneSelectorDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(SettingsNotificationsFragment.class);

    private static final String DIALOG_TAG_NOTIFICATIONS_DISABLED = "ndd";

    private static final String DIALOG_TAG_CONTACT_NOTIFICATION = "cn";

    private static final String DIALOG_TAG_GROUP_NOTIFICATION = "gn";

    private static final String DIALOG_TAG_VOIP_NOTIFICATION = "vn";

    private static final String DIALOG_TAG_MIUI_NOTICE = "miui10_channel_notice";

    private static final int INTENT_SYSTEM_NOTIFICATION_SETTINGS = 5199;

    private SharedPreferences sharedPreferences;

    private NotificationManagerCompat notificationManagerCompat;

    // Weekdays used for work-life balance prefs
    private final String[] weekdays = new String[7];

    private final String[] shortWeekdays = new String[7];

    private final String[] weekday_values = new String[] { "0", "1", "2", "3", "4", "5", "6" };

    private Preference startPreference, endPreference;

    private Preference ringtonePreference, groupRingtonePreference, voiceRingtonePreference;

    private final ActivityResultLauncher<Intent> voipRingtonePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            Uri uri = result.getData().getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            onRingtoneSelected(DIALOG_TAG_VOIP_NOTIFICATION, uri);
        }
    });

    private final ActivityResultLauncher<Intent> contactTonePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            Uri uri = result.getData().getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            onRingtoneSelected(DIALOG_TAG_CONTACT_NOTIFICATION, uri);
        }
    });

    private final ActivityResultLauncher<Intent> groupTonePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            Uri uri = result.getData().getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            onRingtoneSelected(DIALOG_TAG_GROUP_NOTIFICATION, uri);
        }
    });

    private void initWorkingTimePrefs() {
        if (!ListenerUtil.mutListener.listen(32470)) {
            if (!ConfigUtils.isWorkBuild()) {
                // remove preferences
                PreferenceScreen preferenceScreen = findPreference("pref_key_notifications");
                PreferenceCategory preferenceCategory = findPreference("pref_key_work_life_balance");
                if (!ListenerUtil.mutListener.listen(32469)) {
                    preferenceScreen.removePreference(preferenceCategory);
                }
                return;
            }
        }
        DateFormatSymbols dfs = new DateFormatSymbols(getResources().getConfiguration().locale);
        if (!ListenerUtil.mutListener.listen(32471)) {
            System.arraycopy(dfs.getWeekdays(), 1, weekdays, 0, 7);
        }
        if (!ListenerUtil.mutListener.listen(32472)) {
            System.arraycopy(dfs.getShortWeekdays(), 1, shortWeekdays, 0, 7);
        }
        MultiSelectListPreference multiSelectListPreference = findPreference(getString(R.string.preferences__working_days));
        if (!ListenerUtil.mutListener.listen(32473)) {
            multiSelectListPreference.setEntries(weekdays);
        }
        if (!ListenerUtil.mutListener.listen(32474)) {
            multiSelectListPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                updateWorkingDaysSummary(preference, (Set<String>) newValue);
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(32475)) {
            updateWorkingDaysSummary(multiSelectListPreference, multiSelectListPreference.getValues());
        }
        if (!ListenerUtil.mutListener.listen(32476)) {
            startPreference = findPreference(getString(R.string.preferences__work_time_start));
        }
        if (!ListenerUtil.mutListener.listen(32477)) {
            updateTimeSummary(startPreference, R.string.prefs_work_time_start_sum);
        }
        if (!ListenerUtil.mutListener.listen(32478)) {
            startPreference.setOnPreferenceClickListener(preference -> {
                int[] startTime = splitDateFromPrefs(R.string.preferences__work_time_start);
                final MaterialTimePicker timePicker = new MaterialTimePicker.Builder().setTitleText(R.string.prefs_work_time_start).setHour(startTime != null ? startTime[0] : 0).setMinute(startTime != null ? startTime[1] : 0).setTimeFormat(DateFormat.is24HourFormat(getContext()) ? CLOCK_24H : CLOCK_12H).build();
                timePicker.addOnPositiveButtonClickListener(v1 -> {
                    int[] endTime = splitDateFromPrefs(R.string.preferences__work_time_end);
                    if (endTime != null) {
                        int newTimeStamp = timePicker.getHour() * 60 + timePicker.getMinute();
                        int endTimeStamp = endTime[0] * 60 + endTime[1];
                        if (newTimeStamp >= endTimeStamp) {
                            return;
                        }
                    }
                    String newValue = String.format(Locale.US, "%02d:%02d", timePicker.getHour(), timePicker.getMinute());
                    sharedPreferences.edit().putString(getResources().getString(R.string.preferences__work_time_start), newValue).apply();
                    updateTimeSummary(startPreference, R.string.prefs_work_time_start_sum);
                });
                if (isAdded()) {
                    timePicker.show(getParentFragmentManager(), "startt");
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(32479)) {
            endPreference = findPreference(getString(R.string.preferences__work_time_end));
        }
        if (!ListenerUtil.mutListener.listen(32480)) {
            updateTimeSummary(endPreference, R.string.prefs_work_time_end_sum);
        }
        if (!ListenerUtil.mutListener.listen(32481)) {
            endPreference.setOnPreferenceClickListener(preference -> {
                int[] endTime = splitDateFromPrefs(R.string.preferences__work_time_end);
                final MaterialTimePicker timePicker = new MaterialTimePicker.Builder().setTitleText(R.string.prefs_work_time_end).setHour(endTime != null ? endTime[0] : 0).setMinute(endTime != null ? endTime[1] : 0).setTimeFormat(DateFormat.is24HourFormat(getContext()) ? CLOCK_24H : CLOCK_12H).build();
                timePicker.addOnPositiveButtonClickListener(v1 -> {
                    int[] startTime = splitDateFromPrefs(R.string.preferences__work_time_start);
                    if (startTime != null) {
                        int newTimeStamp = timePicker.getHour() * 60 + timePicker.getMinute();
                        int startTimeStamp = startTime[0] * 60 + startTime[1];
                        if (newTimeStamp <= startTimeStamp) {
                            return;
                        }
                    }
                    String newValue = String.format(Locale.US, "%02d:%02d", timePicker.getHour(), timePicker.getMinute());
                    sharedPreferences.edit().putString(getResources().getString(R.string.preferences__work_time_end), newValue).apply();
                    updateTimeSummary(endPreference, R.string.prefs_work_time_end_sum);
                });
                if (isAdded()) {
                    timePicker.show(getParentFragmentManager(), "endt");
                }
                return true;
            });
        }
    }

    private void updateWorkingDaysSummary(Preference preference, Set<String> values) {
        StringBuilder summary = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(32490)) {
            {
                long _loopCounter229 = 0;
                for (String value : values) {
                    ListenerUtil.loopListener.listen("_loopCounter229", ++_loopCounter229);
                    int index = Arrays.asList(weekday_values).indexOf(value);
                    if (!ListenerUtil.mutListener.listen(32488)) {
                        if ((ListenerUtil.mutListener.listen(32486) ? (summary.length() >= 0) : (ListenerUtil.mutListener.listen(32485) ? (summary.length() <= 0) : (ListenerUtil.mutListener.listen(32484) ? (summary.length() < 0) : (ListenerUtil.mutListener.listen(32483) ? (summary.length() != 0) : (ListenerUtil.mutListener.listen(32482) ? (summary.length() == 0) : (summary.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(32487)) {
                                summary.append(", ");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(32489)) {
                        summary.append(shortWeekdays[index]);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32497)) {
            if ((ListenerUtil.mutListener.listen(32495) ? (summary.length() >= 0) : (ListenerUtil.mutListener.listen(32494) ? (summary.length() <= 0) : (ListenerUtil.mutListener.listen(32493) ? (summary.length() > 0) : (ListenerUtil.mutListener.listen(32492) ? (summary.length() < 0) : (ListenerUtil.mutListener.listen(32491) ? (summary.length() != 0) : (summary.length() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(32496)) {
                    summary = new StringBuilder(getString(R.string.prefs_working_days_sum));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32498)) {
            preference.setSummary(summary);
        }
    }

    @Nullable
    private int[] splitDateFromPrefs(@StringRes int key) {
        String value = sharedPreferences.getString(getString(key), null);
        if (value == null) {
            return null;
        }
        try {
            String[] hourMinuteString = value.split(":");
            int[] hourMinuteInt = new int[2];
            if (!ListenerUtil.mutListener.listen(32499)) {
                hourMinuteInt[0] = Integer.parseInt(hourMinuteString[0]);
            }
            if (!ListenerUtil.mutListener.listen(32500)) {
                hourMinuteInt[1] = Integer.parseInt(hourMinuteString[1]);
            }
            return hourMinuteInt;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32501)) {
            sharedPreferences = getPreferenceManager().getSharedPreferences();
        }
        if (!ListenerUtil.mutListener.listen(32502)) {
            addPreferencesFromResource(R.xml.preference_notifications);
        }
        int miuiVersion = ConfigUtils.getMIUIVersion();
        if (!ListenerUtil.mutListener.listen(32509)) {
            if ((ListenerUtil.mutListener.listen(32507) ? (miuiVersion >= 10) : (ListenerUtil.mutListener.listen(32506) ? (miuiVersion <= 10) : (ListenerUtil.mutListener.listen(32505) ? (miuiVersion > 10) : (ListenerUtil.mutListener.listen(32504) ? (miuiVersion != 10) : (ListenerUtil.mutListener.listen(32503) ? (miuiVersion == 10) : (miuiVersion < 10))))))) {
                PreferenceScreen preferenceScreen = findPreference("pref_key_notifications");
                if (!ListenerUtil.mutListener.listen(32508)) {
                    preferenceScreen.removePreference(findPreference("pref_key_miui"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32510)) {
            notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(32511)) {
            initWorkingTimePrefs();
        }
        if (!ListenerUtil.mutListener.listen(32512)) {
            // setup defaults and callbacks
            ringtonePreference = findPreference(getResources().getString(R.string.preferences__notification_sound));
        }
        if (!ListenerUtil.mutListener.listen(32513)) {
            updateRingtoneSummary(ringtonePreference, sharedPreferences.getString(getResources().getString(R.string.preferences__notification_sound), ""));
        }
        if (!ListenerUtil.mutListener.listen(32514)) {
            groupRingtonePreference = findPreference(getResources().getString(R.string.preferences__group_notification_sound));
        }
        if (!ListenerUtil.mutListener.listen(32515)) {
            updateRingtoneSummary(groupRingtonePreference, sharedPreferences.getString(getResources().getString(R.string.preferences__group_notification_sound), ""));
        }
        if (!ListenerUtil.mutListener.listen(32516)) {
            voiceRingtonePreference = findPreference(getResources().getString(R.string.preferences__voip_ringtone));
        }
        if (!ListenerUtil.mutListener.listen(32517)) {
            updateRingtoneSummary(voiceRingtonePreference, sharedPreferences.getString(getResources().getString(R.string.preferences__voip_ringtone), ""));
        }
        if (!ListenerUtil.mutListener.listen(32518)) {
            ringtonePreference.setOnPreferenceClickListener(preference -> {
                chooseRingtone(RingtoneManager.TYPE_NOTIFICATION, getRingtoneFromRingtonePref(R.string.preferences__notification_sound), null, getString(R.string.prefs_notification_sound), DIALOG_TAG_CONTACT_NOTIFICATION);
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(32519)) {
            groupRingtonePreference.setOnPreferenceClickListener(preference -> {
                chooseRingtone(RingtoneManager.TYPE_NOTIFICATION, getRingtoneFromRingtonePref(R.string.preferences__group_notification_sound), null, getString(R.string.prefs_notification_sound), DIALOG_TAG_GROUP_NOTIFICATION);
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(32521)) {
            voiceRingtonePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32520)) {
                        chooseRingtone(RingtoneManager.TYPE_RINGTONE, getRingtoneFromRingtonePref(R.string.preferences__voip_ringtone), RingtoneUtil.THREEMA_CALL_RINGTONE_URI, getString(R.string.prefs_voice_call_sound), DIALOG_TAG_VOIP_NOTIFICATION);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32525)) {
            if (ConfigUtils.isWorkRestricted()) {
                CheckBoxPreference notificationPreview = findPreference(getString(R.string.preferences__notification_preview));
                Boolean value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_message_preview));
                if (!ListenerUtil.mutListener.listen(32524)) {
                    if (value != null) {
                        if (!ListenerUtil.mutListener.listen(32522)) {
                            notificationPreview.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32523)) {
                            notificationPreview.setSelectable(false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32538)) {
            if ((ListenerUtil.mutListener.listen(32530) ? (miuiVersion <= 10) : (ListenerUtil.mutListener.listen(32529) ? (miuiVersion > 10) : (ListenerUtil.mutListener.listen(32528) ? (miuiVersion < 10) : (ListenerUtil.mutListener.listen(32527) ? (miuiVersion != 10) : (ListenerUtil.mutListener.listen(32526) ? (miuiVersion == 10) : (miuiVersion >= 10))))))) {
                if (!ListenerUtil.mutListener.listen(32536)) {
                    ShowOnceDialog.newInstance(R.string.miui_notification_title, (ListenerUtil.mutListener.listen(32535) ? (miuiVersion <= 12) : (ListenerUtil.mutListener.listen(32534) ? (miuiVersion > 12) : (ListenerUtil.mutListener.listen(32533) ? (miuiVersion < 12) : (ListenerUtil.mutListener.listen(32532) ? (miuiVersion != 12) : (ListenerUtil.mutListener.listen(32531) ? (miuiVersion == 12) : (miuiVersion >= 12)))))) ? R.string.miui12_notification_body : R.string.miui_notification_body).show(getFragmentManager(), DIALOG_TAG_MIUI_NOTICE);
                }
                Preference miuiPreference = findPreference("pref_key_miui");
                if (!ListenerUtil.mutListener.listen(32537)) {
                    miuiPreference.setOnPreferenceClickListener(preference -> {
                        openMIUINotificationSettings();
                        return true;
                    });
                }
            }
        }
    }

    private void chooseRingtone(final int type, final Uri currentUri, final Uri defaultUri, final String title, final String tag) {
        try {
            Intent intent = RingtoneUtil.getRingtonePickerIntent(type, currentUri, defaultUri);
            if (!ListenerUtil.mutListener.listen(32544)) {
                switch(tag) {
                    case DIALOG_TAG_VOIP_NOTIFICATION:
                        if (!ListenerUtil.mutListener.listen(32541)) {
                            voipRingtonePickerLauncher.launch(intent);
                        }
                        break;
                    case DIALOG_TAG_CONTACT_NOTIFICATION:
                        if (!ListenerUtil.mutListener.listen(32542)) {
                            contactTonePickerLauncher.launch(intent);
                        }
                        break;
                    case DIALOG_TAG_GROUP_NOTIFICATION:
                        if (!ListenerUtil.mutListener.listen(32543)) {
                            groupTonePickerLauncher.launch(intent);
                        }
                        break;
                }
            }
        } catch (ActivityNotFoundException e) {
            RingtoneSelectorDialog dialog = RingtoneSelectorDialog.newInstance(title, type, currentUri, defaultUri, true, true);
            if (!ListenerUtil.mutListener.listen(32539)) {
                dialog.setTargetFragment(SettingsNotificationsFragment.this, 0);
            }
            if (!ListenerUtil.mutListener.listen(32540)) {
                dialog.show(getFragmentManager(), tag);
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32545)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.prefs_notifications);
        }
        if (!ListenerUtil.mutListener.listen(32546)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(32548)) {
            if (!notificationManagerCompat.areNotificationsEnabled()) {
                if (!ListenerUtil.mutListener.listen(32547)) {
                    showNotificationsDisabledDialog();
                }
            }
        }
    }

    private void showNotificationsDisabledDialog() {
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.notifications_disabled_title, R.string.notifications_disabled_text, R.string.notifications_disabled_settings, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(32549)) {
            dialog.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(32550)) {
            dialog.show(getFragmentManager(), DIALOG_TAG_NOTIFICATIONS_DISABLED);
        }
    }

    private Uri getRingtoneFromRingtonePref(@StringRes int preference) {
        String uriString = sharedPreferences.getString(getResources().getString(preference), null);
        if (!ListenerUtil.mutListener.listen(32552)) {
            if (uriString == null) {
                if (!ListenerUtil.mutListener.listen(32551)) {
                    // silent
                    uriString = "";
                }
            }
        }
        return Uri.parse(uriString);
    }

    private void updateRingtoneSummary(Preference preference, String value) {
        String summary = null;
        if (!ListenerUtil.mutListener.listen(32561)) {
            if ((ListenerUtil.mutListener.listen(32558) ? (value == null && (ListenerUtil.mutListener.listen(32557) ? (value.length() >= 0) : (ListenerUtil.mutListener.listen(32556) ? (value.length() <= 0) : (ListenerUtil.mutListener.listen(32555) ? (value.length() > 0) : (ListenerUtil.mutListener.listen(32554) ? (value.length() < 0) : (ListenerUtil.mutListener.listen(32553) ? (value.length() != 0) : (value.length() == 0))))))) : (value == null || (ListenerUtil.mutListener.listen(32557) ? (value.length() >= 0) : (ListenerUtil.mutListener.listen(32556) ? (value.length() <= 0) : (ListenerUtil.mutListener.listen(32555) ? (value.length() > 0) : (ListenerUtil.mutListener.listen(32554) ? (value.length() < 0) : (ListenerUtil.mutListener.listen(32553) ? (value.length() != 0) : (value.length() == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(32560)) {
                    summary = getString(R.string.ringtone_none);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32559)) {
                    summary = RingtoneUtil.getRingtoneNameFromUri(getContext(), Uri.parse(value));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32562)) {
            preference.setSummary(summary);
        }
    }

    private void updateTimeSummary(Preference preference, @StringRes int defaultSummary) {
        if (!ListenerUtil.mutListener.listen(32563)) {
            preference.setSummary(sharedPreferences.getString(preference.getKey(), getString(defaultSummary)));
        }
    }

    private void openMIUINotificationSettings() {
        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationFilterActivity");
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(32564)) {
            bundle.putString("appName", getContext().getResources().getString(getContext().getApplicationInfo().labelRes));
        }
        if (!ListenerUtil.mutListener.listen(32565)) {
            bundle.putString("packageName", BuildConfig.APPLICATION_ID);
        }
        if (!ListenerUtil.mutListener.listen(32566)) {
            bundle.putString(":android:show_fragment", "NotificationAccessSettings");
        }
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(32567)) {
            intent.putExtras(bundle);
        }
        if (!ListenerUtil.mutListener.listen(32568)) {
            intent.setComponent(cn);
        }
        try {
            if (!ListenerUtil.mutListener.listen(32570)) {
                startActivity(intent);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(32569)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(32583)) {
            if ((ListenerUtil.mutListener.listen(32575) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(32574) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(32573) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(32572) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(32571) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(32579)) {
                    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                }
                if (!ListenerUtil.mutListener.listen(32580)) {
                    // for Android 5-7
                    intent.putExtra("app_package", getActivity().getPackageName());
                }
                if (!ListenerUtil.mutListener.listen(32581)) {
                    intent.putExtra("app_uid", getActivity().getApplicationInfo().uid);
                }
                if (!ListenerUtil.mutListener.listen(32582)) {
                    // for Android O
                    intent.putExtra("android.provider.extra.APP_PACKAGE", getActivity().getPackageName());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32576)) {
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                }
                if (!ListenerUtil.mutListener.listen(32577)) {
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                }
                if (!ListenerUtil.mutListener.listen(32578)) {
                    intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32584)) {
            startActivityForResult(intent, INTENT_SYSTEM_NOTIFICATION_SETTINGS);
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(32585)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(32593)) {
            if ((ListenerUtil.mutListener.listen(32591) ? ((ListenerUtil.mutListener.listen(32590) ? (requestCode >= INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (ListenerUtil.mutListener.listen(32589) ? (requestCode <= INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (ListenerUtil.mutListener.listen(32588) ? (requestCode > INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (ListenerUtil.mutListener.listen(32587) ? (requestCode < INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (ListenerUtil.mutListener.listen(32586) ? (requestCode != INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (requestCode == INTENT_SYSTEM_NOTIFICATION_SETTINGS)))))) || !notificationManagerCompat.areNotificationsEnabled()) : ((ListenerUtil.mutListener.listen(32590) ? (requestCode >= INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (ListenerUtil.mutListener.listen(32589) ? (requestCode <= INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (ListenerUtil.mutListener.listen(32588) ? (requestCode > INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (ListenerUtil.mutListener.listen(32587) ? (requestCode < INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (ListenerUtil.mutListener.listen(32586) ? (requestCode != INTENT_SYSTEM_NOTIFICATION_SETTINGS) : (requestCode == INTENT_SYSTEM_NOTIFICATION_SETTINGS)))))) && !notificationManagerCompat.areNotificationsEnabled()))) {
                if (!ListenerUtil.mutListener.listen(32592)) {
                    // return from system settings but notifications still disabled
                    showNotificationsDisabledDialog();
                }
            }
        }
    }

    @Override
    public void onRingtoneSelected(String tag, Uri ringtone) {
        String toneString = ringtone != null ? ringtone.toString() : "";
        if (!ListenerUtil.mutListener.listen(32600)) {
            switch(tag) {
                case DIALOG_TAG_CONTACT_NOTIFICATION:
                    if (!ListenerUtil.mutListener.listen(32594)) {
                        sharedPreferences.edit().putString(ThreemaApplication.getAppContext().getString(R.string.preferences__notification_sound), toneString).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(32595)) {
                        updateRingtoneSummary(ringtonePreference, sharedPreferences.getString(getResources().getString(R.string.preferences__notification_sound), ""));
                    }
                    break;
                case DIALOG_TAG_GROUP_NOTIFICATION:
                    if (!ListenerUtil.mutListener.listen(32596)) {
                        sharedPreferences.edit().putString(ThreemaApplication.getAppContext().getString(R.string.preferences__group_notification_sound), toneString).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(32597)) {
                        updateRingtoneSummary(groupRingtonePreference, sharedPreferences.getString(getResources().getString(R.string.preferences__group_notification_sound), ""));
                    }
                    break;
                case DIALOG_TAG_VOIP_NOTIFICATION:
                    if (!ListenerUtil.mutListener.listen(32598)) {
                        sharedPreferences.edit().putString(ThreemaApplication.getAppContext().getString(R.string.preferences__voip_ringtone), toneString).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(32599)) {
                        updateRingtoneSummary(voiceRingtonePreference, sharedPreferences.getString(getResources().getString(R.string.preferences__voip_ringtone), ""));
                    }
                    break;
            }
        }
    }

    @Override
    public void onCancel(String tag) {
    }
}

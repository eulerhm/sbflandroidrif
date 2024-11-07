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
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.Preference.SummaryProvider;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.DisableBatteryOptimizationsActivity;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.push.PushService;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.MessageServiceImpl;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.WallpaperService;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.PowermanagerUtil;
import ch.threema.app.utils.PushUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.voip.activities.WebRTCDebugActivity;
import ch.threema.app.webclient.activities.WebDiagnosticsActivity;
import ch.threema.logging.backend.DebugLogFileBackend;
import ch.threema.storage.models.ContactModel;
import static ch.threema.app.utils.PowermanagerUtil.RESULT_DISABLE_AUTOSTART;
import static ch.threema.app.utils.PowermanagerUtil.RESULT_DISABLE_POWERMANAGER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsTroubleshootingFragment extends ThreemaPreferenceFragment implements GenericAlertDialog.DialogClickListener, SharedPreferences.OnSharedPreferenceChangeListener, TextEntryDialog.TextEntryDialogClickListener, CancelableHorizontalProgressDialog.ProgressDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(SettingsTroubleshootingFragment.class);

    private static final String DIALOG_TAG_REMOVE_WALLPAPERS = "removeWP";

    private static final String DIALOG_TAG_PUSH_REGISTER = "pushReg";

    private static final String DIALOG_TAG_PUSH_RESULT = "pushRes";

    private static final String DIALOG_TAG_RESET_RINGTONES = "rri";

    private static final String DIALOG_TAG_IPV6_APP_RESTART = "rs";

    private static final String DIALOG_TAG_POWERMANAGER_WORKAROUNDS = "hw";

    private static final String DIALOG_TAG_AUTOSTART_WORKAROUNDS = "as";

    private static final String DIALOG_TAG_REALLY_ENABLE_POLLING = "enp";

    public static final int REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS = 441;

    public static final int REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS_HUAWEI = 442;

    private static final String DIALOG_TAG_SENDLOG = "sl";

    public static final String THREEMA_SUPPORT_IDENTITY = "*SUPPORT";

    private static final int PERMISSION_REQUEST_MESSAGE_LOG = 1;

    private static final int PERMISSION_REQUEST_SEND_LOG = 2;

    private TwoStatePreference pollingTwoStatePreference;

    private TwoStatePreference messageLogPreference, ipv6Preferences;

    private WallpaperService wallpaperService;

    private SharedPreferences sharedPreferences;

    private PreferenceService preferenceService;

    private RingtoneService ringtoneService;

    private NotificationService notificationService;

    private FileService fileService;

    private UserService userService;

    private LifetimeService lifetimeService;

    private DeadlineListService mutedChatsListService, mentionOnlyChatsListService;

    private MessageService messageService;

    private ContactService contactService;

    private BroadcastReceiver pushTokenResetBroadcastReceiver;

    private View fragmentView;

    private boolean pushServicesInstalled;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32967)) {
            if (!requiredInstances()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(32968)) {
            addPreferencesFromResource(R.xml.preference_troubleshooting);
        }
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("pref_key_troubleshooting");
        if (!ListenerUtil.mutListener.listen(32969)) {
            sharedPreferences = getPreferenceManager().getSharedPreferences();
        }
        if (!ListenerUtil.mutListener.listen(32970)) {
            pushServicesInstalled = PushService.servicesInstalled(getContext());
        }
        if (!ListenerUtil.mutListener.listen(32973)) {
            pushTokenResetBroadcastReceiver = new BroadcastReceiver() {

                // register listener for gcm registration result
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (!ListenerUtil.mutListener.listen(32971)) {
                        DialogUtil.dismissDialog(getParentFragmentManager(), DIALOG_TAG_PUSH_REGISTER, true);
                    }
                    String message;
                    if (intent.getBooleanExtra(PushUtil.EXTRA_REGISTRATION_ERROR_BROADCAST, false)) {
                        message = getString(R.string.token_register_failed);
                    } else if (intent.getBooleanExtra(PushUtil.EXTRA_CLEAR_TOKEN, false)) {
                        message = getString(R.string.push_token_cleared);
                    } else {
                        message = getString(R.string.push_reset_text);
                    }
                    if (!ListenerUtil.mutListener.listen(32972)) {
                        SimpleStringAlertDialog.newInstance(-1, message).show(getParentFragmentManager(), DIALOG_TAG_PUSH_RESULT);
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(32974)) {
            pollingTwoStatePreference = (TwoStatePreference) findPreference(getResources().getString(R.string.preferences__polling_switch));
        }
        if (!ListenerUtil.mutListener.listen(32984)) {
            pollingTwoStatePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean newCheckedValue = newValue.equals(true);
                    if (!ListenerUtil.mutListener.listen(32983)) {
                        if (((TwoStatePreference) preference).isChecked() != newCheckedValue) {
                            if (!ListenerUtil.mutListener.listen(32982)) {
                                if (newCheckedValue) {
                                    if (!ListenerUtil.mutListener.listen(32980)) {
                                        if (pushServicesInstalled) {
                                            GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.enable_polling, R.string.push_disable_text, R.string.continue_anyway, R.string.cancel);
                                            if (!ListenerUtil.mutListener.listen(32978)) {
                                                dialog.setTargetFragment(SettingsTroubleshootingFragment.this, 0);
                                            }
                                            if (!ListenerUtil.mutListener.listen(32979)) {
                                                dialog.show(getParentFragmentManager(), DIALOG_TAG_REALLY_ENABLE_POLLING);
                                            }
                                            return false;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(32981)) {
                                        updatePollInterval();
                                    }
                                    return true;
                                } else {
                                    if (!ListenerUtil.mutListener.listen(32977)) {
                                        if (pushServicesInstalled) {
                                            if (!ListenerUtil.mutListener.listen(32976)) {
                                                lifetimeService.setPollingInterval(0);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(32975)) {
                                                Toast.makeText(getContext(), R.string.play_services_not_installed_unable_to_use_push, Toast.LENGTH_SHORT).show();
                                            }
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
            });
        }
        DropDownPreference pollingIntervalPreference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__polling_interval));
        if (!ListenerUtil.mutListener.listen(32985)) {
            pollingIntervalPreference.setEnabled(pollingTwoStatePreference.isChecked());
        }
        if (!ListenerUtil.mutListener.listen(32986)) {
            messageLogPreference = (TwoStatePreference) findPreference(getResources().getString(R.string.preferences__message_log_switch));
        }
        if (!ListenerUtil.mutListener.listen(32990)) {
            messageLogPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean newCheckedValue = newValue.equals(true);
                    if (!ListenerUtil.mutListener.listen(32988)) {
                        if (newCheckedValue) {
                            if (!ListenerUtil.mutListener.listen(32987)) {
                                if (!ConfigUtils.requestStoragePermissions(getActivity(), SettingsTroubleshootingFragment.this, PERMISSION_REQUEST_MESSAGE_LOG)) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(32989)) {
                        DebugLogFileBackend.setEnabled(newCheckedValue);
                    }
                    return true;
                }
            });
        }
        Preference sendLogPreference = findPreference(getResources().getString(R.string.preferences__sendlog));
        if (!ListenerUtil.mutListener.listen(32993)) {
            sendLogPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32992)) {
                        if (ConfigUtils.requestStoragePermissions(getActivity(), SettingsTroubleshootingFragment.this, PERMISSION_REQUEST_SEND_LOG)) {
                            if (!ListenerUtil.mutListener.listen(32991)) {
                                prepareSendLogfile();
                            }
                        }
                    }
                    return true;
                }
            });
        }
        Preference resetPushPreference = findPreference(getResources().getString(R.string.preferences__reset_push));
        if (!ListenerUtil.mutListener.listen(32998)) {
            resetPushPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32997)) {
                        if (pushServicesInstalled) {
                            if (!ListenerUtil.mutListener.listen(32994)) {
                                PushUtil.clearPushTokenSentDate(getActivity());
                            }
                            if (!ListenerUtil.mutListener.listen(32995)) {
                                PushUtil.enqueuePushTokenUpdate(getContext(), false, true);
                            }
                            if (!ListenerUtil.mutListener.listen(32996)) {
                                GenericProgressDialog.newInstance(R.string.push_reset_title, R.string.please_wait).showNow(getParentFragmentManager(), DIALOG_TAG_PUSH_REGISTER);
                            }
                        }
                    }
                    return true;
                }
            });
        }
        Preference wallpaperDeletePreferences = findPreference(getResources().getString(R.string.preferences__remove_wallpapers));
        if (!ListenerUtil.mutListener.listen(33001)) {
            wallpaperDeletePreferences.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.prefs_title_remove_wallpapers, R.string.really_remove_wallpapers, R.string.ok, R.string.cancel);
                    if (!ListenerUtil.mutListener.listen(32999)) {
                        dialog.setTargetFragment(SettingsTroubleshootingFragment.this, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(33000)) {
                        dialog.show(getParentFragmentManager(), DIALOG_TAG_REMOVE_WALLPAPERS);
                    }
                    return false;
                }
            });
        }
        Preference ringtoneResetPreferences = findPreference(getResources().getString(R.string.preferences__reset_ringtones));
        if (!ListenerUtil.mutListener.listen(33004)) {
            ringtoneResetPreferences.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.prefs_title_reset_ringtones, R.string.really_reset_ringtones, R.string.ok, R.string.cancel);
                    if (!ListenerUtil.mutListener.listen(33002)) {
                        dialog.setTargetFragment(SettingsTroubleshootingFragment.this, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(33003)) {
                        dialog.show(getParentFragmentManager(), DIALOG_TAG_RESET_RINGTONES);
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(33005)) {
            ipv6Preferences = (TwoStatePreference) findPreference(getResources().getString(R.string.preferences__ipv6_preferred));
        }
        if (!ListenerUtil.mutListener.listen(33017)) {
            if ((ListenerUtil.mutListener.listen(33010) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33009) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33008) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33007) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33006) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(33016)) {
                    // disable IPv6 support on Android <5.0 due to some know incompatibilities
                    ipv6Preferences.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            boolean newCheckedValue = newValue.equals(true);
                            boolean oldCheckedValue = ((TwoStatePreference) preference).isChecked();
                            if (!ListenerUtil.mutListener.listen(33015)) {
                                if (oldCheckedValue != newCheckedValue) {
                                    // value has changed
                                    GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.prefs_title_ipv6_preferred, R.string.ipv6_requires_restart, R.string.ipv6_restart_now, R.string.cancel);
                                    if (!ListenerUtil.mutListener.listen(33012)) {
                                        dialog.setTargetFragment(SettingsTroubleshootingFragment.this, 0);
                                    }
                                    if (!ListenerUtil.mutListener.listen(33013)) {
                                        dialog.setData(oldCheckedValue);
                                    }
                                    if (!ListenerUtil.mutListener.listen(33014)) {
                                        dialog.show(getParentFragmentManager(), DIALOG_TAG_IPV6_APP_RESTART);
                                    }
                                    return false;
                                }
                            }
                            return true;
                        }
                    });
                }
            } else {
                PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("pref_key_network");
                if (!ListenerUtil.mutListener.listen(33011)) {
                    preferenceScreen.removePreference(preferenceCategory);
                }
            }
        }
        Preference powerManagerPrefs = findPreference(getResources().getString(R.string.preferences__powermanager_workarounds));
        if (!ListenerUtil.mutListener.listen(33040)) {
            if ((ListenerUtil.mutListener.listen(33022) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(33021) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(33020) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(33019) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(33018) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(33028)) {
                    powerManagerPrefs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            if (!ListenerUtil.mutListener.listen(33027)) {
                                if (PowermanagerUtil.hasPowerManagerOption(SettingsTroubleshootingFragment.this.getActivity())) {
                                    GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.disable_powermanager_title, String.format(getString(R.string.disable_powermanager_explain), getString(R.string.app_name)), R.string.next, R.string.cancel);
                                    if (!ListenerUtil.mutListener.listen(33025)) {
                                        dialog.setTargetFragment(SettingsTroubleshootingFragment.this, 0);
                                    }
                                    if (!ListenerUtil.mutListener.listen(33026)) {
                                        dialog.show(getParentFragmentManager(), DIALOG_TAG_POWERMANAGER_WORKAROUNDS);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(33024)) {
                                        disableAutostart();
                                    }
                                }
                            }
                            return true;
                        }
                    });
                }
                Preference backgroundDataPrefs = findPreference(getResources().getString(R.string.preferences__background_data));
                if (!ListenerUtil.mutListener.listen(33038)) {
                    if ((ListenerUtil.mutListener.listen(33033) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(33032) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(33031) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(33030) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(33029) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                        if (!ListenerUtil.mutListener.listen(33037)) {
                            backgroundDataPrefs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                                @TargetApi(Build.VERSION_CODES.N)
                                @Override
                                public boolean onPreferenceClick(Preference preference) {
                                    Intent intent = new Intent(Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS);
                                    if (!ListenerUtil.mutListener.listen(33035)) {
                                        intent.setData(Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                                    }
                                    try {
                                        if (!ListenerUtil.mutListener.listen(33036)) {
                                            startActivity(intent);
                                        }
                                    } catch (ActivityNotFoundException e) {
                                    }
                                    return true;
                                }
                            });
                        }
                    } else {
                        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("pref_key_fix_device");
                        if (!ListenerUtil.mutListener.listen(33034)) {
                            preferenceCategory.removePreference(backgroundDataPrefs);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(33039)) {
                    updatePowerManagerPrefs();
                }
            } else {
                PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("pref_key_fix_device");
                if (!ListenerUtil.mutListener.listen(33023)) {
                    preferenceScreen.removePreference(preferenceCategory);
                }
            }
        }
        DropDownPreference echoCancelPreference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__voip_echocancel));
        int echoCancelIndex = preferenceService.getAECMode().equals("sw") ? 1 : 0;
        final String[] echoCancelArray = getResources().getStringArray(R.array.list_echocancel);
        final List<String> echoCancelValuesArrayList = Arrays.asList(getResources().getStringArray(R.array.list_echocancel_values));
        if (!ListenerUtil.mutListener.listen(33041)) {
            echoCancelPreference.setSummary(echoCancelArray[echoCancelIndex]);
        }
        if (!ListenerUtil.mutListener.listen(33043)) {
            echoCancelPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(33042)) {
                        preference.setSummary(echoCancelArray[echoCancelValuesArrayList.indexOf(newValue.toString())]);
                    }
                    return true;
                }
            });
        }
        final Preference webrtcDebugPreference = findPreference(getResources().getString(R.string.preferences__webrtc_debug));
        if (!ListenerUtil.mutListener.listen(33044)) {
            webrtcDebugPreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(getActivity(), WebRTCDebugActivity.class);
                getActivity().startActivity(intent);
                return true;
            });
        }
        final DropDownPreference videoCodecPreference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__voip_video_codec));
        assert videoCodecPreference != null;
        final String[] videoCodecListDescription = getResources().getStringArray(R.array.list_video_codec);
        final List<String> videoCodecValuesList = Arrays.asList(getResources().getStringArray(R.array.list_video_codec_values));
        if (!ListenerUtil.mutListener.listen(33045)) {
            videoCodecPreference.setSummaryProvider((SummaryProvider<DropDownPreference>) preference -> {
                CharSequence value = preference.getEntry().toString();
                if (TextUtils.isEmpty(value)) {
                    return videoCodecListDescription[videoCodecValuesList.indexOf(PreferenceService.VIDEO_CODEC_HW)];
                }
                return value;
            });
        }
        final Preference webclientDebugPreference = findPreference(getResources().getString(R.string.preferences__webclient_debug));
        if (!ListenerUtil.mutListener.listen(33046)) {
            webclientDebugPreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(getActivity(), WebDiagnosticsActivity.class);
                getActivity().startActivity(intent);
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(33051)) {
            if ((ListenerUtil.mutListener.listen(33047) ? (ConfigUtils.isWorkRestricted() && ConfigUtils.isBlackBerry()) : (ConfigUtils.isWorkRestricted() || ConfigUtils.isBlackBerry()))) {
                Boolean value;
                if (ConfigUtils.isBlackBerry()) {
                    value = true;
                } else {
                    value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_calls));
                }
                if (!ListenerUtil.mutListener.listen(33050)) {
                    if (value != null) {
                        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("pref_key_voip");
                        if (!ListenerUtil.mutListener.listen(33049)) {
                            if (preferenceCategory != null) {
                                if (!ListenerUtil.mutListener.listen(33048)) {
                                    preferenceScreen.removePreference(preferenceCategory);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33055)) {
            if (ConfigUtils.isWorkRestricted()) {
                // remove everything except push reset
                PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("pref_key_workarounds");
                if (!ListenerUtil.mutListener.listen(33052)) {
                    findPreference(getResources().getString(R.string.preferences__reset_push)).setDependency(null);
                }
                if (!ListenerUtil.mutListener.listen(33053)) {
                    preferenceCategory.removePreference(findPreference(getResources().getString(R.string.preferences__polling_switch)));
                }
                if (!ListenerUtil.mutListener.listen(33054)) {
                    preferenceCategory.removePreference(findPreference(getResources().getString(R.string.preferences__polling_interval)));
                }
            }
        }
    }

    private void updatePowerManagerPrefs() {
        if (!ListenerUtil.mutListener.listen(33062)) {
            if ((ListenerUtil.mutListener.listen(33060) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(33059) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(33058) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(33057) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(33056) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(33061)) {
                    findPreference(getResources().getString(R.string.preferences__powermanager_workarounds)).setEnabled(PowermanagerUtil.needsFixing(getActivity()));
                }
            }
        }
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(33064)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(33063)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.wallpaperService, this.lifetimeService, this.preferenceService, this.fileService, this.userService, this.ringtoneService, this.mutedChatsListService, this.messageService, this.contactService);
    }

    protected void instantiate() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(33077)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(33066)) {
                        this.wallpaperService = serviceManager.getWallpaperService();
                    }
                    if (!ListenerUtil.mutListener.listen(33067)) {
                        this.lifetimeService = serviceManager.getLifetimeService();
                    }
                    if (!ListenerUtil.mutListener.listen(33068)) {
                        this.preferenceService = serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(33069)) {
                        this.fileService = serviceManager.getFileService();
                    }
                    if (!ListenerUtil.mutListener.listen(33070)) {
                        this.userService = serviceManager.getUserService();
                    }
                    if (!ListenerUtil.mutListener.listen(33071)) {
                        this.ringtoneService = serviceManager.getRingtoneService();
                    }
                    if (!ListenerUtil.mutListener.listen(33072)) {
                        this.mutedChatsListService = serviceManager.getMutedChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(33073)) {
                        this.mentionOnlyChatsListService = serviceManager.getMentionOnlyChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(33074)) {
                        this.messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(33075)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(33076)) {
                        this.notificationService = serviceManager.getNotificationService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(33065)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(33078)) {
            this.fragmentView = view;
        }
        if (!ListenerUtil.mutListener.listen(33079)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.prefs_troubleshooting);
        }
        if (!ListenerUtil.mutListener.listen(33080)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(33098)) {
            switch(tag) {
                case DIALOG_TAG_REMOVE_WALLPAPERS:
                    if (!ListenerUtil.mutListener.listen(33081)) {
                        wallpaperService.removeAll(getActivity(), false);
                    }
                    if (!ListenerUtil.mutListener.listen(33082)) {
                        preferenceService.setCustomWallpaperEnabled(false);
                    }
                    break;
                case DIALOG_TAG_RESET_RINGTONES:
                    if (!ListenerUtil.mutListener.listen(33083)) {
                        ringtoneService.resetRingtones(getActivity().getApplicationContext());
                    }
                    if (!ListenerUtil.mutListener.listen(33084)) {
                        mutedChatsListService.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(33085)) {
                        mentionOnlyChatsListService.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(33086)) {
                        notificationService.deleteNotificationChannels();
                    }
                    if (!ListenerUtil.mutListener.listen(33087)) {
                        notificationService.createNotificationChannels();
                    }
                    if (!ListenerUtil.mutListener.listen(33089)) {
                        if (ConfigUtils.isWorkBuild()) {
                            if (!ListenerUtil.mutListener.listen(33088)) {
                                preferenceService.setAfterWorkDNDEnabled(false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(33090)) {
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.reset_ringtones_confirm), Toast.LENGTH_SHORT).show();
                    }
                    if (!ListenerUtil.mutListener.listen(33092)) {
                        ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                            @Override
                            public void handle(ConversationListener listener) {
                                if (!ListenerUtil.mutListener.listen(33091)) {
                                    listener.onModifiedAll();
                                }
                            }
                        });
                    }
                    break;
                case DIALOG_TAG_IPV6_APP_RESTART:
                    if (!ListenerUtil.mutListener.listen(33093)) {
                        ipv6Preferences.setChecked(!(boolean) data);
                    }
                    if (!ListenerUtil.mutListener.listen(33094)) {
                        new Handler().postDelayed(() -> RuntimeUtil.runOnUiThread(() -> System.exit(0)), 700);
                    }
                    break;
                case DIALOG_TAG_AUTOSTART_WORKAROUNDS:
                    if (!ListenerUtil.mutListener.listen(33095)) {
                        PowermanagerUtil.callAutostartManager(this);
                    }
                    break;
                case DIALOG_TAG_POWERMANAGER_WORKAROUNDS:
                    if (!ListenerUtil.mutListener.listen(33096)) {
                        PowermanagerUtil.callPowerManager(this);
                    }
                    break;
                case DIALOG_TAG_REALLY_ENABLE_POLLING:
                    if (!ListenerUtil.mutListener.listen(33097)) {
                        requestDisableBatteryOptimizations(getString(R.string.prefs_title_polling_switch), R.string.cancel, REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS);
                    }
                    break;
            }
        }
    }

    private void disableAutostart() {
        if (!ListenerUtil.mutListener.listen(33102)) {
            if (PowermanagerUtil.hasAutostartOption(getActivity())) {
                GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.disable_autostart_title, String.format(getString(R.string.disable_autostart_explain), getString(R.string.app_name)), R.string.next, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(33100)) {
                    dialog.setTargetFragment(SettingsTroubleshootingFragment.this, 0);
                }
                if (!ListenerUtil.mutListener.listen(33101)) {
                    dialog.show(getParentFragmentManager(), DIALOG_TAG_AUTOSTART_WORKAROUNDS);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(33099)) {
                    requestDisableBatteryOptimizations(getString(R.string.app_name), R.string.cancel, REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS_HUAWEI);
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(33103)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(33104)) {
            sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(33105)) {
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(pushTokenResetBroadcastReceiver, new IntentFilter(ThreemaApplication.INTENT_PUSH_REGISTRATION_COMPLETE));
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(33106)) {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(33107)) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(pushTokenResetBroadcastReceiver);
        }
        if (!ListenerUtil.mutListener.listen(33108)) {
            DialogUtil.dismissDialog(getParentFragmentManager(), DIALOG_TAG_PUSH_REGISTER, true);
        }
        if (!ListenerUtil.mutListener.listen(33109)) {
            super.onStop();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(33116)) {
            if (key.equals(getString(R.string.preferences__polling_switch))) {
                boolean newValue = sharedPreferences.getBoolean(getString(R.string.preferences__polling_switch), false);
                Preference preference = findPreference(getString(R.string.preferences__polling_interval));
                if (!ListenerUtil.mutListener.listen(33111)) {
                    preference.setEnabled(newValue);
                }
                if (!ListenerUtil.mutListener.listen(33112)) {
                    if (!isAdded()) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(33115)) {
                    if (pushServicesInstalled) {
                        if (!ListenerUtil.mutListener.listen(33113)) {
                            PushUtil.enqueuePushTokenUpdate(getContext(), newValue, true);
                        }
                        if (!ListenerUtil.mutListener.listen(33114)) {
                            GenericProgressDialog.newInstance(R.string.push_reset_title, R.string.please_wait).showNow(getParentFragmentManager(), DIALOG_TAG_PUSH_REGISTER);
                        }
                    }
                }
            } else if (key.equals(getString(R.string.preferences__polling_interval))) {
                if (!ListenerUtil.mutListener.listen(33110)) {
                    updatePollInterval();
                }
            }
        }
    }

    private void prepareSendLogfile() {
        TextEntryDialog dialog = TextEntryDialog.newInstance(R.string.prefs_sendlog_summary, R.string.enter_description, R.string.send, R.string.cancel, 5, 3000, 1);
        if (!ListenerUtil.mutListener.listen(33117)) {
            dialog.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(33118)) {
            dialog.show(getParentFragmentManager(), DIALOG_TAG_SENDLOG);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void sendLogFileToSupport(final String caption) {
        if (!ListenerUtil.mutListener.listen(33131)) {
            new AsyncTask<Void, Void, Exception>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(33119)) {
                        GenericProgressDialog.newInstance(R.string.preparing_messages, R.string.please_wait).show(getParentFragmentManager(), DIALOG_TAG_SENDLOG);
                    }
                }

                @Override
                protected Exception doInBackground(Void... params) {
                    File zipFile = DebugLogFileBackend.getZipFile(fileService);
                    try {
                        final ContactModel contactModel = contactService.getOrCreateByIdentity(THREEMA_SUPPORT_IDENTITY, true);
                        MessageReceiver receiver = contactService.createReceiver(contactModel);
                        if (!ListenerUtil.mutListener.listen(33120)) {
                            messageService.sendText(caption + "\n-- \n" + ConfigUtils.getDeviceInfo(getActivity(), false) + "\n" + ConfigUtils.getFullAppVersion(getActivity()) + "\n" + userService.getIdentity(), receiver);
                        }
                        MediaItem mediaItem = new MediaItem(Uri.fromFile(zipFile), MediaItem.TYPE_FILE);
                        if (!ListenerUtil.mutListener.listen(33121)) {
                            mediaItem.setFilename(zipFile.getName());
                        }
                        if (!ListenerUtil.mutListener.listen(33122)) {
                            mediaItem.setMimeType(MimeUtil.MIME_TYPE_ZIP);
                        }
                        if (!ListenerUtil.mutListener.listen(33125)) {
                            messageService.sendMediaAsync(Collections.singletonList(mediaItem), Collections.singletonList(receiver), new MessageServiceImpl.SendResultListener() {

                                @Override
                                public void onError(String errorMessage) {
                                    if (!ListenerUtil.mutListener.listen(33123)) {
                                        RuntimeUtil.runOnUiThread(() -> Toast.makeText(getContext(), R.string.an_error_occurred_during_send, Toast.LENGTH_LONG).show());
                                    }
                                }

                                @Override
                                public void onCompleted() {
                                    if (!ListenerUtil.mutListener.listen(33124)) {
                                        RuntimeUtil.runOnUiThread(() -> Toast.makeText(getContext(), R.string.message_sent, Toast.LENGTH_LONG).show());
                                    }
                                }
                            });
                        }
                    } catch (Exception e) {
                        return e;
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Exception exception) {
                    if (!ListenerUtil.mutListener.listen(33130)) {
                        if (isAdded()) {
                            if (!ListenerUtil.mutListener.listen(33126)) {
                                DialogUtil.dismissDialog(getParentFragmentManager(), DIALOG_TAG_SENDLOG, true);
                            }
                            if (!ListenerUtil.mutListener.listen(33129)) {
                                if (exception != null) {
                                    if (!ListenerUtil.mutListener.listen(33128)) {
                                        Toast.makeText(getActivity().getApplicationContext(), R.string.an_error_occurred, Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(33127)) {
                                        Toast.makeText(getActivity().getApplicationContext(), R.string.message_sent, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void updatePollInterval() {
        if (!ListenerUtil.mutListener.listen(33132)) {
            lifetimeService.setPollingInterval(preferenceService.getPollingInterval());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(33143)) {
            switch(requestCode) {
                case REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS:
                    if (!ListenerUtil.mutListener.listen(33136)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(33134)) {
                                pollingTwoStatePreference.setChecked(true);
                            }
                            if (!ListenerUtil.mutListener.listen(33135)) {
                                updatePollInterval();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(33133)) {
                                pollingTwoStatePreference.setChecked(false);
                            }
                        }
                    }
                    break;
                case REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS_HUAWEI:
                    if (!ListenerUtil.mutListener.listen(33137)) {
                        updatePowerManagerPrefs();
                    }
                    break;
                case RESULT_DISABLE_POWERMANAGER:
                    if (!ListenerUtil.mutListener.listen(33138)) {
                        disableAutostart();
                    }
                    if (!ListenerUtil.mutListener.listen(33139)) {
                        updatePowerManagerPrefs();
                    }
                    break;
                case RESULT_DISABLE_AUTOSTART:
                    if (!ListenerUtil.mutListener.listen(33140)) {
                        requestDisableBatteryOptimizations(getString(R.string.app_name), R.string.cancel, REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS_HUAWEI);
                    }
                    if (!ListenerUtil.mutListener.listen(33141)) {
                        updatePowerManagerPrefs();
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(33142)) {
                        super.onActivityResult(requestCode, resultCode, data);
                    }
                    break;
            }
        }
    }

    private void requestDisableBatteryOptimizations(String name, int label, int requestId) {
        Intent intent = new Intent(getActivity(), DisableBatteryOptimizationsActivity.class);
        if (!ListenerUtil.mutListener.listen(33144)) {
            intent.putExtra(DisableBatteryOptimizationsActivity.EXTRA_NAME, name);
        }
        if (!ListenerUtil.mutListener.listen(33145)) {
            intent.putExtra(DisableBatteryOptimizationsActivity.EXTRA_CANCEL_LABEL, label);
        }
        if (!ListenerUtil.mutListener.listen(33146)) {
            startActivityForResult(intent, requestId);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean result = ((ListenerUtil.mutListener.listen(33152) ? ((ListenerUtil.mutListener.listen(33151) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(33150) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(33149) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(33148) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(33147) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(33151) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(33150) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(33149) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(33148) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(33147) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED)));
        if (!ListenerUtil.mutListener.listen(33155)) {
            if (!result) {
                if (!ListenerUtil.mutListener.listen(33154)) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        if (!ListenerUtil.mutListener.listen(33153)) {
                            ConfigUtils.showPermissionRationale(getContext(), fragmentView, R.string.permission_storage_required);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33160)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_MESSAGE_LOG:
                    if (!ListenerUtil.mutListener.listen(33156)) {
                        DebugLogFileBackend.setEnabled(result);
                    }
                    if (!ListenerUtil.mutListener.listen(33157)) {
                        messageLogPreference.setChecked(result);
                    }
                    break;
                case PERMISSION_REQUEST_SEND_LOG:
                    if (!ListenerUtil.mutListener.listen(33159)) {
                        if (result) {
                            if (!ListenerUtil.mutListener.listen(33158)) {
                                prepareSendLogfile();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onYes(String tag, String text) {
        if (!ListenerUtil.mutListener.listen(33161)) {
            sendLogFileToSupport(text);
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(33163)) {
            switch(tag) {
                case DIALOG_TAG_IPV6_APP_RESTART:
                    boolean oldValue = (boolean) data;
                    if (!ListenerUtil.mutListener.listen(33162)) {
                        ipv6Preferences.setChecked(oldValue);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNeutral(String tag) {
    }

    @Override
    public void onCancel(String tag, Object object) {
    }
}

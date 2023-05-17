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

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.DialogFragment;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.activities.UnlockMasterKeyActivity;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.PasswordEntryDialog;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.PassphraseService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.BiometricUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.HiddenChatUtil;
import ch.threema.app.utils.RuntimeUtil;
import static ch.threema.app.services.PreferenceService.LockingMech_NONE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsSecurityFragment extends ThreemaPreferenceFragment implements PasswordEntryDialog.PasswordEntryDialogClickListener, GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(SettingsSecurityFragment.class);

    private Preference pinPreference;

    private TwoStatePreference uiLockSwitchPreference;

    private DropDownPreference gracePreference, lockMechanismPreference;

    private Preference masterkeyPreference;

    private TwoStatePreference masterkeySwitchPreference;

    private PreferenceService preferenceService;

    private DeadlineListService hiddenChatsListService;

    private View fragmentView;

    private PreferenceScreen preferenceScreen;

    private static final String ID_DIALOG_PASSPHRASE = "mkpw";

    private static final String ID_DIALOG_PROGRESS = "pogress";

    private static final String ID_DIALOG_PIN = "dpin";

    private static final String ID_UNHIDE_CHATS_CONFIRM = "uh";

    private static final String DIALOG_TAG_PASSWORD_REMINDER_PIN = "emin";

    private static final String DIALOG_TAG_PASSWORD_REMINDER_PASSPHRASE = "eminpass";

    private static final int ID_ENABLE_SYSTEM_LOCK = 7780;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32732)) {
            logger.debug("### onCreatePreferencesFix savedInstanceState = " + savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(32733)) {
            preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        }
        if (!ListenerUtil.mutListener.listen(32734)) {
            hiddenChatsListService = ThreemaApplication.getServiceManager().getHiddenChatsListService();
        }
        if (!ListenerUtil.mutListener.listen(32735)) {
            addPreferencesFromResource(R.xml.preference_security);
        }
        if (!ListenerUtil.mutListener.listen(32736)) {
            preferenceScreen = findPreference("pref_key_security");
        }
    }

    private void onCreateUnlocked() {
        if (!ListenerUtil.mutListener.listen(32737)) {
            logger.debug("### onCreateUnlocked");
        }
        if (!ListenerUtil.mutListener.listen(32738)) {
            fragmentView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(32739)) {
            uiLockSwitchPreference = findPreference(getResources().getString(R.string.preferences__lock_ui_switch));
        }
        if (!ListenerUtil.mutListener.listen(32740)) {
            lockMechanismPreference = findPreference(getResources().getString(R.string.preferences__lock_mechanism));
        }
        if (!ListenerUtil.mutListener.listen(32741)) {
            pinPreference = findPreference(getResources().getString(R.string.preferences__pin_lock_code));
        }
        if (!ListenerUtil.mutListener.listen(32742)) {
            gracePreference = findPreference(getResources().getString(R.string.preferences__pin_lock_grace_time));
        }
        if (!ListenerUtil.mutListener.listen(32743)) {
            // get pin switch pref from service!
            uiLockSwitchPreference.setChecked(preferenceService.isAppLockEnabled());
        }
        CharSequence[] entries = lockMechanismPreference.getEntries();
        if (!ListenerUtil.mutListener.listen(32757)) {
            if ((ListenerUtil.mutListener.listen(32749) ? ((ListenerUtil.mutListener.listen(32748) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32747) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32746) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32745) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32744) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) && ConfigUtils.isBlackBerry()) : ((ListenerUtil.mutListener.listen(32748) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32747) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32746) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32745) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32744) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) || ConfigUtils.isBlackBerry()))) {
                if (!ListenerUtil.mutListener.listen(32756)) {
                    // remove system screen lock option
                    lockMechanismPreference.setEntries(Arrays.copyOf(entries, 2));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32755)) {
                    if (preferenceService.getLockMechanism().equals(PreferenceService.LockingMech_BIOMETRIC)) {
                        if (!ListenerUtil.mutListener.listen(32754)) {
                            if (!BiometricUtil.isHardwareSupported(getContext())) {
                                if (!ListenerUtil.mutListener.listen(32752)) {
                                    preferenceService.setLockMechanism(LockingMech_NONE);
                                }
                                if (!ListenerUtil.mutListener.listen(32753)) {
                                    // remove biometric option
                                    lockMechanismPreference.setEntries(Arrays.copyOf(entries, 3));
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(32751)) {
                            if (!BiometricUtil.isHardwareSupported(getContext())) {
                                if (!ListenerUtil.mutListener.listen(32750)) {
                                    // remove biometric option
                                    lockMechanismPreference.setEntries(Arrays.copyOf(entries, 3));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32759)) {
            if (preferenceService.isPinSet()) {
                if (!ListenerUtil.mutListener.listen(32758)) {
                    pinPreference.setSummary(getString(R.string.click_here_to_change_pin));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32764)) {
            switch(preferenceService.getLockMechanism()) {
                case PreferenceService.LockingMech_PIN:
                    if (!ListenerUtil.mutListener.listen(32760)) {
                        lockMechanismPreference.setValueIndex(1);
                    }
                    break;
                case PreferenceService.LockingMech_SYSTEM:
                    if (!ListenerUtil.mutListener.listen(32761)) {
                        lockMechanismPreference.setValueIndex(2);
                    }
                    break;
                case PreferenceService.LockingMech_BIOMETRIC:
                    if (!ListenerUtil.mutListener.listen(32762)) {
                        lockMechanismPreference.setValueIndex(3);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(32763)) {
                        lockMechanismPreference.setValueIndex(0);
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(32765)) {
            updateLockPreferences();
        }
        if (!ListenerUtil.mutListener.listen(32766)) {
            setGraceTime();
        }
        if (!ListenerUtil.mutListener.listen(32782)) {
            lockMechanismPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(32780)) {
                        switch((String) newValue) {
                            case LockingMech_NONE:
                                if (!ListenerUtil.mutListener.listen(32775)) {
                                    if ((ListenerUtil.mutListener.listen(32771) ? (hiddenChatsListService.getSize() >= 0) : (ListenerUtil.mutListener.listen(32770) ? (hiddenChatsListService.getSize() <= 0) : (ListenerUtil.mutListener.listen(32769) ? (hiddenChatsListService.getSize() < 0) : (ListenerUtil.mutListener.listen(32768) ? (hiddenChatsListService.getSize() != 0) : (ListenerUtil.mutListener.listen(32767) ? (hiddenChatsListService.getSize() == 0) : (hiddenChatsListService.getSize() > 0))))))) {
                                        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.hide_chat, R.string.unhide_chats_confirm, R.string.continue_anyway, R.string.cancel);
                                        if (!ListenerUtil.mutListener.listen(32773)) {
                                            dialog.setTargetFragment(SettingsSecurityFragment.this, 0);
                                        }
                                        if (!ListenerUtil.mutListener.listen(32774)) {
                                            dialog.show(getFragmentManager(), ID_UNHIDE_CHATS_CONFIRM);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(32772)) {
                                            removeAccessProtection();
                                        }
                                    }
                                }
                                break;
                            case PreferenceService.LockingMech_PIN:
                                GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.warning, getString(R.string.password_remember_warning, getString(R.string.app_name)), R.string.ok, R.string.cancel);
                                if (!ListenerUtil.mutListener.listen(32776)) {
                                    dialog.setTargetFragment(SettingsSecurityFragment.this, 0);
                                }
                                if (!ListenerUtil.mutListener.listen(32777)) {
                                    dialog.show(getFragmentManager(), DIALOG_TAG_PASSWORD_REMINDER_PIN);
                                }
                                break;
                            case PreferenceService.LockingMech_SYSTEM:
                                if (!ListenerUtil.mutListener.listen(32778)) {
                                    setSystemScreenLock();
                                }
                                break;
                            case PreferenceService.LockingMech_BIOMETRIC:
                                if (!ListenerUtil.mutListener.listen(32779)) {
                                    setBiometricLock();
                                }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(32781)) {
                        updateLockPreferences();
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32790)) {
            uiLockSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean newCheckedValue = newValue.equals(true);
                    if (!ListenerUtil.mutListener.listen(32789)) {
                        if (((TwoStatePreference) preference).isChecked() != newCheckedValue) {
                            if (!ListenerUtil.mutListener.listen(32783)) {
                                preferenceService.setAppLockEnabled(false);
                            }
                            if (!ListenerUtil.mutListener.listen(32788)) {
                                if (newCheckedValue) {
                                    if (!ListenerUtil.mutListener.listen(32785)) {
                                        if ((ListenerUtil.mutListener.listen(32784) ? (lockMechanismPreference.getValue() == null && PreferenceService.LockingMech_NONE.equals(lockMechanismPreference.getValue())) : (lockMechanismPreference.getValue() == null || PreferenceService.LockingMech_NONE.equals(lockMechanismPreference.getValue())))) {
                                            return false;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(32786)) {
                                        setGraceTime();
                                    }
                                    if (!ListenerUtil.mutListener.listen(32787)) {
                                        preferenceService.setAppLockEnabled(true);
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32794)) {
            pinPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32793)) {
                        if (preference.getKey().equals(getResources().getString(R.string.preferences__pin_lock_code))) {
                            if (!ListenerUtil.mutListener.listen(32792)) {
                                if (preferenceService.isPinSet()) {
                                    if (!ListenerUtil.mutListener.listen(32791)) {
                                        setPin();
                                    }
                                }
                            }
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32795)) {
            this.updateGracePreferenceSummary(gracePreference.getValue());
        }
        if (!ListenerUtil.mutListener.listen(32797)) {
            gracePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(32796)) {
                        updateGracePreferenceSummary(newValue.toString());
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32798)) {
            masterkeyPreference = findPreference(getResources().getString(R.string.preferences__masterkey_passphrase));
        }
        if (!ListenerUtil.mutListener.listen(32802)) {
            masterkeyPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32801)) {
                        if (preference.getKey().equals(getResources().getString(R.string.preferences__masterkey_passphrase))) {
                            Intent intent = new Intent(getActivity(), UnlockMasterKeyActivity.class);
                            if (!ListenerUtil.mutListener.listen(32799)) {
                                intent.putExtra(ThreemaApplication.INTENT_DATA_PASSPHRASE_CHECK, true);
                            }
                            if (!ListenerUtil.mutListener.listen(32800)) {
                                startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_CHANGE_PASSPHRASE_UNLOCK);
                            }
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32803)) {
            masterkeySwitchPreference = (TwoStatePreference) findPreference(getResources().getString(R.string.preferences__masterkey_switch));
        }
        if (!ListenerUtil.mutListener.listen(32805)) {
            // fix wrong state
            if (masterkeySwitchPreference.isChecked() != ThreemaApplication.getMasterKey().isProtected()) {
                if (!ListenerUtil.mutListener.listen(32804)) {
                    masterkeySwitchPreference.setChecked(ThreemaApplication.getMasterKey().isProtected());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32806)) {
            setMasterKeyPreferenceText();
        }
        if (!ListenerUtil.mutListener.listen(32813)) {
            masterkeySwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean newCheckedValue = newValue.equals(true);
                    if (!ListenerUtil.mutListener.listen(32812)) {
                        if (((TwoStatePreference) preference).isChecked() != newCheckedValue) {
                            if (!ListenerUtil.mutListener.listen(32811)) {
                                if (!newCheckedValue) {
                                    Intent intent = new Intent(getActivity(), UnlockMasterKeyActivity.class);
                                    if (!ListenerUtil.mutListener.listen(32808)) {
                                        intent.putExtra(ThreemaApplication.INTENT_DATA_PASSPHRASE_CHECK, true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32809)) {
                                        startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_RESET_PASSPHRASE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32810)) {
                                        setMasterKeyPreferenceText();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(32807)) {
                                        startSetPassphraseActivity();
                                    }
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32814)) {
            logger.debug("### onCreateUnlocked end");
        }
    }

    private void updateGracePreferenceSummary(String value) {
        String[] existingValues = getResources().getStringArray(R.array.list_pin_grace_time_values);
        if (!ListenerUtil.mutListener.listen(32822)) {
            {
                long _loopCounter230 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(32821) ? (n >= existingValues.length) : (ListenerUtil.mutListener.listen(32820) ? (n <= existingValues.length) : (ListenerUtil.mutListener.listen(32819) ? (n > existingValues.length) : (ListenerUtil.mutListener.listen(32818) ? (n != existingValues.length) : (ListenerUtil.mutListener.listen(32817) ? (n == existingValues.length) : (n < existingValues.length)))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter230", ++_loopCounter230);
                    if (!ListenerUtil.mutListener.listen(32816)) {
                        if (existingValues[n].equals(value)) {
                            if (!ListenerUtil.mutListener.listen(32815)) {
                                gracePreference.setSummary(getResources().getStringArray(R.array.list_pin_grace_time)[n]);
                            }
                        }
                    }
                }
            }
        }
    }

    private void removeAccessProtection() {
        if (!ListenerUtil.mutListener.listen(32823)) {
            lockMechanismPreference.setValue(LockingMech_NONE);
        }
        if (!ListenerUtil.mutListener.listen(32824)) {
            preferenceService.setPrivateChatsHidden(false);
        }
        if (!ListenerUtil.mutListener.listen(32833)) {
            if ((ListenerUtil.mutListener.listen(32829) ? (hiddenChatsListService.getSize() >= 0) : (ListenerUtil.mutListener.listen(32828) ? (hiddenChatsListService.getSize() <= 0) : (ListenerUtil.mutListener.listen(32827) ? (hiddenChatsListService.getSize() < 0) : (ListenerUtil.mutListener.listen(32826) ? (hiddenChatsListService.getSize() != 0) : (ListenerUtil.mutListener.listen(32825) ? (hiddenChatsListService.getSize() == 0) : (hiddenChatsListService.getSize() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(32830)) {
                    hiddenChatsListService.clear();
                }
                if (!ListenerUtil.mutListener.listen(32832)) {
                    ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                        @Override
                        public void handle(ConversationListener listener) {
                            if (!ListenerUtil.mutListener.listen(32831)) {
                                // make hidden chats visible again
                                listener.onModifiedAll();
                            }
                        }
                    });
                }
            }
        }
    }

    private void updateLockPreferences() {
        if (!ListenerUtil.mutListener.listen(32834)) {
            pinPreference.setSummary(preferenceService.isPinSet() ? getString(R.string.click_here_to_change_pin) : getString(R.string.prefs_title_pin_code));
        }
        if (!ListenerUtil.mutListener.listen(32835)) {
            lockMechanismPreference.setSummary(lockMechanismPreference.getEntry());
        }
        if (!ListenerUtil.mutListener.listen(32853)) {
            switch(lockMechanismPreference.getValue()) {
                case LockingMech_NONE:
                    if (!ListenerUtil.mutListener.listen(32836)) {
                        pinPreference.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(32837)) {
                        gracePreference.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(32838)) {
                        uiLockSwitchPreference.setChecked(false);
                    }
                    if (!ListenerUtil.mutListener.listen(32839)) {
                        uiLockSwitchPreference.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(32840)) {
                        preferenceService.setPin(null);
                    }
                    if (!ListenerUtil.mutListener.listen(32841)) {
                        preferenceService.setAppLockEnabled(false);
                    }
                    break;
                case PreferenceService.LockingMech_PIN:
                    if (!ListenerUtil.mutListener.listen(32842)) {
                        pinPreference.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(32843)) {
                        gracePreference.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(32844)) {
                        uiLockSwitchPreference.setEnabled(true);
                    }
                    break;
                case PreferenceService.LockingMech_SYSTEM:
                    if (!ListenerUtil.mutListener.listen(32845)) {
                        pinPreference.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(32846)) {
                        gracePreference.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(32847)) {
                        uiLockSwitchPreference.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(32848)) {
                        preferenceService.setPin(null);
                    }
                    break;
                case PreferenceService.LockingMech_BIOMETRIC:
                    if (!ListenerUtil.mutListener.listen(32849)) {
                        pinPreference.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(32850)) {
                        gracePreference.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(32851)) {
                        uiLockSwitchPreference.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(32852)) {
                        preferenceService.setPin(null);
                    }
                    break;
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32854)) {
            logger.debug("### onViewCreated");
        }
        if (!ListenerUtil.mutListener.listen(32855)) {
            // we make the complete fragment invisible until it's been unlocked
            fragmentView = view;
        }
        if (!ListenerUtil.mutListener.listen(32856)) {
            fragmentView.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(32857)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.prefs_security);
        }
        if (!ListenerUtil.mutListener.listen(32858)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(32866)) {
            // ask for pin before entering
            if (preferenceService.getLockMechanism().equals(LockingMech_NONE)) {
                if (!ListenerUtil.mutListener.listen(32865)) {
                    onCreateUnlocked();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32864)) {
                    if ((ListenerUtil.mutListener.listen(32859) ? ((preferenceService.getLockMechanism().equals(PreferenceService.LockingMech_PIN)) || !preferenceService.isPinSet()) : ((preferenceService.getLockMechanism().equals(PreferenceService.LockingMech_PIN)) && !preferenceService.isPinSet()))) {
                        if (!ListenerUtil.mutListener.listen(32862)) {
                            // fix misconfiguration
                            preferenceService.setLockMechanism(LockingMech_NONE);
                        }
                        if (!ListenerUtil.mutListener.listen(32863)) {
                            onCreateUnlocked();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(32861)) {
                            if (savedInstanceState == null) {
                                if (!ListenerUtil.mutListener.listen(32860)) {
                                    HiddenChatUtil.launchLockCheckDialog(this, preferenceService);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(32886)) {
            if (resultCode == Activity.RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(32884)) {
                    switch(requestCode) {
                        case ThreemaActivity.ACTIVITY_ID_CHECK_LOCK:
                            if (!ListenerUtil.mutListener.listen(32873)) {
                                ThreemaApplication.getServiceManager().getScreenLockService().setAuthenticated(true);
                            }
                            if (!ListenerUtil.mutListener.listen(32874)) {
                                onCreateUnlocked();
                            }
                            break;
                        case ID_ENABLE_SYSTEM_LOCK:
                            if (!ListenerUtil.mutListener.listen(32875)) {
                                lockMechanismPreference.setValue(PreferenceService.LockingMech_SYSTEM);
                            }
                            if (!ListenerUtil.mutListener.listen(32877)) {
                                if (uiLockSwitchPreference.isChecked()) {
                                    if (!ListenerUtil.mutListener.listen(32876)) {
                                        preferenceService.setAppLockEnabled(true);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(32878)) {
                                updateLockPreferences();
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_RESET_PASSPHRASE:
                            // reset here directly
                            try {
                                if (!ListenerUtil.mutListener.listen(32880)) {
                                    ThreemaApplication.getMasterKey().setPassphrase(null);
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(32879)) {
                                    logger.error("Exception", e);
                                }
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_CHANGE_PASSPHRASE_UNLOCK:
                            if (!ListenerUtil.mutListener.listen(32881)) {
                                startChangePassphraseActivity();
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_SET_PASSPHRASE:
                        case ThreemaActivity.ACTIVITY_ID_CHANGE_PASSPHRASE:
                            if (!ListenerUtil.mutListener.listen(32882)) {
                                // do not handle event
                                setMasterKeyPreferenceText();
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(32883)) {
                                super.onActivityResult(requestCode, resultCode, data);
                            }
                    }
                }
                if (!ListenerUtil.mutListener.listen(32885)) {
                    /* show/hide persistent notification */
                    PassphraseService.start(getActivity().getApplicationContext());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32872)) {
                    switch(requestCode) {
                        case ThreemaActivity.ACTIVITY_ID_CHECK_LOCK:
                            if (!ListenerUtil.mutListener.listen(32867)) {
                                ThreemaApplication.getServiceManager().getScreenLockService().setAuthenticated(false);
                            }
                            if (!ListenerUtil.mutListener.listen(32868)) {
                                getActivity().onBackPressed();
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_SET_PASSPHRASE:
                            if (!ListenerUtil.mutListener.listen(32869)) {
                                // only switch back on set
                                masterkeySwitchPreference.setChecked(false);
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_RESET_PASSPHRASE:
                            if (!ListenerUtil.mutListener.listen(32870)) {
                                // only switch back on set
                                masterkeySwitchPreference.setChecked(true);
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(32871)) {
                                super.onActivityResult(requestCode, resultCode, data);
                            }
                    }
                }
            }
        }
    }

    private void setPin() {
        DialogFragment dialogFragment = PasswordEntryDialog.newInstance(R.string.set_pin_menu_title, R.string.set_pin_summary_intro, R.string.set_pin_hint, R.string.ok, R.string.cancel, ThreemaApplication.MIN_PIN_LENGTH, ThreemaApplication.MAX_PIN_LENGTH, R.string.set_pin_again_summary, InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD, 0);
        if (!ListenerUtil.mutListener.listen(32887)) {
            dialogFragment.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(32888)) {
            dialogFragment.show(getFragmentManager(), ID_DIALOG_PIN);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setSystemScreenLock() {
        if (!ListenerUtil.mutListener.listen(32899)) {
            if ((ListenerUtil.mutListener.listen(32893) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32892) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32891) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32890) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32889) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                KeyguardManager keyguardManager = (KeyguardManager) getActivity().getSystemService(Context.KEYGUARD_SERVICE);
                if (!ListenerUtil.mutListener.listen(32898)) {
                    if (keyguardManager.isDeviceSecure()) {
                        if (!ListenerUtil.mutListener.listen(32897)) {
                            BiometricUtil.showUnlockDialog(null, this, true, ID_ENABLE_SYSTEM_LOCK, PreferenceService.LockingMech_SYSTEM);
                        }
                    } else {
                        Snackbar snackbar = Snackbar.make(fragmentView, R.string.no_lockscreen_set, Snackbar.LENGTH_LONG);
                        if (!ListenerUtil.mutListener.listen(32895)) {
                            snackbar.setAction(R.string.configure, new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(32894)) {
                                        startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(32896)) {
                            snackbar.show();
                        }
                    }
                }
            }
        }
    }

    private void setBiometricLock() {
        if (!ListenerUtil.mutListener.listen(32913)) {
            /* TODO: Use BiometricLockActivity */
            if (BiometricUtil.isBiometricsSupported(getContext())) {
                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle(getString(R.string.prefs_title_access_protection)).setSubtitle(getString(R.string.biometric_enter_authentication)).setNegativeButtonText(getString(R.string.cancel)).build();
                BiometricPrompt biometricPrompt = new BiometricPrompt(this.getActivity(), new RuntimeUtil.MainThreadExecutor(), new BiometricPrompt.AuthenticationCallback() {

                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        if (!ListenerUtil.mutListener.listen(32904)) {
                            if ((ListenerUtil.mutListener.listen(32900) ? (errorCode != BiometricPrompt.ERROR_USER_CANCELED || errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON) : (errorCode != BiometricPrompt.ERROR_USER_CANCELED && errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON))) {
                                String text = errString + " (" + errorCode + ")";
                                try {
                                    if (!ListenerUtil.mutListener.listen(32903)) {
                                        Snackbar.make(fragmentView, text, Snackbar.LENGTH_LONG).show();
                                    }
                                } catch (IllegalArgumentException e) {
                                    if (!ListenerUtil.mutListener.listen(32901)) {
                                        logger.error("Exception", e);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32902)) {
                                        Toast.makeText(ThreemaApplication.getAppContext(), text, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        if (!ListenerUtil.mutListener.listen(32905)) {
                            super.onAuthenticationSucceeded(result);
                        }
                        if (!ListenerUtil.mutListener.listen(32906)) {
                            Snackbar.make(fragmentView, R.string.biometric_authentication_successful, Snackbar.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(32907)) {
                            lockMechanismPreference.setValue(PreferenceService.LockingMech_BIOMETRIC);
                        }
                        if (!ListenerUtil.mutListener.listen(32909)) {
                            if (uiLockSwitchPreference.isChecked()) {
                                if (!ListenerUtil.mutListener.listen(32908)) {
                                    preferenceService.setLockMechanism(PreferenceService.LockingMech_BIOMETRIC);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(32910)) {
                            updateLockPreferences();
                        }
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        if (!ListenerUtil.mutListener.listen(32911)) {
                            super.onAuthenticationFailed();
                        }
                    }
                });
                if (!ListenerUtil.mutListener.listen(32912)) {
                    biometricPrompt.authenticate(promptInfo);
                }
            }
        }
    }

    private void setGraceTime() {
        String graceTime = gracePreference.getValue();
        if (!ListenerUtil.mutListener.listen(32927)) {
            if ((ListenerUtil.mutListener.listen(32924) ? ((ListenerUtil.mutListener.listen(32918) ? (Integer.parseInt(graceTime) <= 0) : (ListenerUtil.mutListener.listen(32917) ? (Integer.parseInt(graceTime) > 0) : (ListenerUtil.mutListener.listen(32916) ? (Integer.parseInt(graceTime) < 0) : (ListenerUtil.mutListener.listen(32915) ? (Integer.parseInt(graceTime) != 0) : (ListenerUtil.mutListener.listen(32914) ? (Integer.parseInt(graceTime) == 0) : (Integer.parseInt(graceTime) >= 0)))))) || (ListenerUtil.mutListener.listen(32923) ? (Integer.parseInt(graceTime) >= 30) : (ListenerUtil.mutListener.listen(32922) ? (Integer.parseInt(graceTime) <= 30) : (ListenerUtil.mutListener.listen(32921) ? (Integer.parseInt(graceTime) > 30) : (ListenerUtil.mutListener.listen(32920) ? (Integer.parseInt(graceTime) != 30) : (ListenerUtil.mutListener.listen(32919) ? (Integer.parseInt(graceTime) == 30) : (Integer.parseInt(graceTime) < 30))))))) : ((ListenerUtil.mutListener.listen(32918) ? (Integer.parseInt(graceTime) <= 0) : (ListenerUtil.mutListener.listen(32917) ? (Integer.parseInt(graceTime) > 0) : (ListenerUtil.mutListener.listen(32916) ? (Integer.parseInt(graceTime) < 0) : (ListenerUtil.mutListener.listen(32915) ? (Integer.parseInt(graceTime) != 0) : (ListenerUtil.mutListener.listen(32914) ? (Integer.parseInt(graceTime) == 0) : (Integer.parseInt(graceTime) >= 0)))))) && (ListenerUtil.mutListener.listen(32923) ? (Integer.parseInt(graceTime) >= 30) : (ListenerUtil.mutListener.listen(32922) ? (Integer.parseInt(graceTime) <= 30) : (ListenerUtil.mutListener.listen(32921) ? (Integer.parseInt(graceTime) > 30) : (ListenerUtil.mutListener.listen(32920) ? (Integer.parseInt(graceTime) != 30) : (ListenerUtil.mutListener.listen(32919) ? (Integer.parseInt(graceTime) == 30) : (Integer.parseInt(graceTime) < 30))))))))) {
                if (!ListenerUtil.mutListener.listen(32925)) {
                    // set default grace time to never
                    gracePreference.setValue("-1");
                }
                if (!ListenerUtil.mutListener.listen(32926)) {
                    updateGracePreferenceSummary(gracePreference.getValue());
                }
            }
        }
    }

    private void startSetPassphraseActivity() {
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.warning, getString(R.string.password_remember_warning, getString(R.string.app_name)), R.string.ok, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(32928)) {
            dialog.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(32929)) {
            dialog.show(getFragmentManager(), DIALOG_TAG_PASSWORD_REMINDER_PASSPHRASE);
        }
    }

    private void startChangePassphraseActivity() {
        if (!ListenerUtil.mutListener.listen(32930)) {
            setPassphrase();
        }
    }

    private void setPassphrase() {
        DialogFragment dialogFragment = PasswordEntryDialog.newInstance(R.string.masterkey_passphrase_title, R.string.masterkey_passphrase_summary, R.string.masterkey_passphrase_hint, R.string.ok, R.string.cancel, 8, 0, R.string.masterkey_passphrase_again_summary, 0, 0);
        if (!ListenerUtil.mutListener.listen(32931)) {
            dialogFragment.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(32932)) {
            dialogFragment.show(getFragmentManager(), ID_DIALOG_PASSPHRASE);
        }
    }

    private void setMasterKeyPreferenceText() {
        if (!ListenerUtil.mutListener.listen(32933)) {
            masterkeyPreference.setSummary(ThreemaApplication.getMasterKey().isProtected() ? getString(R.string.click_here_to_change_passphrase) : getString(R.string.prefs_masterkey_passphrase));
        }
    }

    @Override
    public void onYes(String tag, final String text, final boolean isChecked, Object data) {
        if (!ListenerUtil.mutListener.listen(32953)) {
            switch(tag) {
                case ID_DIALOG_PIN:
                    if (!ListenerUtil.mutListener.listen(32939)) {
                        if (preferenceService.setPin(text)) {
                            if (!ListenerUtil.mutListener.listen(32935)) {
                                lockMechanismPreference.setValue(PreferenceService.LockingMech_PIN);
                            }
                            if (!ListenerUtil.mutListener.listen(32937)) {
                                if (uiLockSwitchPreference.isChecked()) {
                                    if (!ListenerUtil.mutListener.listen(32936)) {
                                        preferenceService.setAppLockEnabled(true);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(32938)) {
                                updateLockPreferences();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(32934)) {
                                Toast.makeText(getActivity(), getString(R.string.pin_invalid_not_set), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                case ID_DIALOG_PASSPHRASE:
                    if (!ListenerUtil.mutListener.listen(32952)) {
                        new AsyncTask<Void, Void, Boolean>() {

                            @Override
                            protected void onPreExecute() {
                                if (!ListenerUtil.mutListener.listen(32940)) {
                                    GenericProgressDialog.newInstance(R.string.setting_masterkey_passphrase, R.string.please_wait).show(getFragmentManager(), ID_DIALOG_PROGRESS);
                                }
                            }

                            @Override
                            protected Boolean doInBackground(Void... voids) {
                                try {
                                    // TODO let passwordentrydialog return a char array
                                    int pl = text.length();
                                    char[] password = new char[pl];
                                    if (!ListenerUtil.mutListener.listen(32942)) {
                                        text.getChars(0, pl, password, 0);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32943)) {
                                        ThreemaApplication.getMasterKey().setPassphrase(password);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32945)) {
                                        RuntimeUtil.runOnUiThread(new Runnable() {

                                            @Override
                                            public void run() {
                                                if (!ListenerUtil.mutListener.listen(32944)) {
                                                    setMasterKeyPreferenceText();
                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(32941)) {
                                        logger.error("Exception", e);
                                    }
                                    return false;
                                }
                                return true;
                            }

                            @Override
                            protected void onPostExecute(Boolean success) {
                                if (!ListenerUtil.mutListener.listen(32947)) {
                                    if (isAdded()) {
                                        if (!ListenerUtil.mutListener.listen(32946)) {
                                            DialogUtil.dismissDialog(getFragmentManager(), ID_DIALOG_PROGRESS, true);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(32951)) {
                                    if (success) {
                                        if (!ListenerUtil.mutListener.listen(32948)) {
                                            masterkeySwitchPreference.setChecked(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(32950)) {
                                            if (!PassphraseService.isRunning()) {
                                                if (!ListenerUtil.mutListener.listen(32949)) {
                                                    PassphraseService.start(ThreemaApplication.getAppContext());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }.execute();
                    }
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag) {
        if (!ListenerUtil.mutListener.listen(32958)) {
            switch(tag) {
                case ID_DIALOG_PASSPHRASE:
                    if (!ListenerUtil.mutListener.listen(32954)) {
                        masterkeySwitchPreference.setChecked(ThreemaApplication.getMasterKey().isProtected());
                    }
                    if (!ListenerUtil.mutListener.listen(32955)) {
                        setMasterKeyPreferenceText();
                    }
                    break;
                case ID_DIALOG_PIN:
                    if (!ListenerUtil.mutListener.listen(32956)) {
                        // workaround to reset dropdown state
                        lockMechanismPreference.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(32957)) {
                        lockMechanismPreference.setEnabled(true);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(32963)) {
            switch(tag) {
                case ID_UNHIDE_CHATS_CONFIRM:
                    if (!ListenerUtil.mutListener.listen(32959)) {
                        removeAccessProtection();
                    }
                    if (!ListenerUtil.mutListener.listen(32960)) {
                        updateLockPreferences();
                    }
                    break;
                case DIALOG_TAG_PASSWORD_REMINDER_PIN:
                    if (!ListenerUtil.mutListener.listen(32961)) {
                        setPin();
                    }
                    break;
                case DIALOG_TAG_PASSWORD_REMINDER_PASSPHRASE:
                    if (!ListenerUtil.mutListener.listen(32962)) {
                        setPassphrase();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(32966)) {
            switch(tag) {
                case DIALOG_TAG_PASSWORD_REMINDER_PASSPHRASE:
                    break;
                case DIALOG_TAG_PASSWORD_REMINDER_PIN:
                    if (!ListenerUtil.mutListener.listen(32964)) {
                        // workaround to reset dropdown state
                        lockMechanismPreference.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(32965)) {
                        lockMechanismPreference.setEnabled(true);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

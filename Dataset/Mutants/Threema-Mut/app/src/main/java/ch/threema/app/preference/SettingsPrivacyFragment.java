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

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.TwoStatePreference;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.BlackListActivity;
import ch.threema.app.activities.ExcludedSyncIdentitiesActivity;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.listeners.SynchronizeContactsListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.routines.SynchronizeContactsRoutine;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.SynchronizeContactsUtil;
import ch.threema.localcrypto.MasterKeyLockedException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsPrivacyFragment extends ThreemaPreferenceFragment implements CancelableHorizontalProgressDialog.ProgressDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(SettingsPrivacyFragment.class);

    private static final String DIALOG_TAG_VALIDATE = "vali";

    private static final String DIALOG_TAG_SYNC_CONTACTS = "syncC";

    private static final String DIALOG_TAG_DISABLE_SYNC = "dissync";

    private static final int PERMISSION_REQUEST_CONTACTS = 1;

    private ServiceManager serviceManager = ThreemaApplication.getServiceManager();

    private SynchronizeContactsService synchronizeContactsService;

    private TwoStatePreference contactSyncPreference;

    private CheckBoxPreference disableScreenshot;

    private boolean disableScreenshotChecked = false;

    private final SynchronizeContactsListener synchronizeContactsListener = new SynchronizeContactsListener() {

        @Override
        public void onStarted(SynchronizeContactsRoutine startedRoutine) {
            if (!ListenerUtil.mutListener.listen(32603)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(32601)) {
                            updateView();
                        }
                        if (!ListenerUtil.mutListener.listen(32602)) {
                            GenericProgressDialog.newInstance(R.string.wizard1_sync_contacts, R.string.please_wait).show(getFragmentManager(), DIALOG_TAG_SYNC_CONTACTS);
                        }
                    }
                });
            }
        }

        @Override
        public void onFinished(SynchronizeContactsRoutine finishedRoutine) {
            if (!ListenerUtil.mutListener.listen(32607)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(32604)) {
                            updateView();
                        }
                        if (!ListenerUtil.mutListener.listen(32606)) {
                            if (SettingsPrivacyFragment.this.isAdded()) {
                                if (!ListenerUtil.mutListener.listen(32605)) {
                                    DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_SYNC_CONTACTS, true);
                                }
                            }
                        }
                    }
                });
            }
        }

        @Override
        public void onError(SynchronizeContactsRoutine finishedRoutine) {
            if (!ListenerUtil.mutListener.listen(32611)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(32608)) {
                            updateView();
                        }
                        if (!ListenerUtil.mutListener.listen(32610)) {
                            if (SettingsPrivacyFragment.this.isAdded()) {
                                if (!ListenerUtil.mutListener.listen(32609)) {
                                    DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_SYNC_CONTACTS, true);
                                }
                            }
                        }
                    }
                });
            }
        }
    };

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32612)) {
            addPreferencesFromResource(R.xml.preference_privacy);
        }
        try {
            if (!ListenerUtil.mutListener.listen(32615)) {
                if (this.requireInstances()) {
                    if (!ListenerUtil.mutListener.listen(32614)) {
                        this.synchronizeContactsService = this.serviceManager.getSynchronizeContactsService();
                    }
                }
            }
        } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(32613)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(32616)) {
            this.disableScreenshot = (CheckBoxPreference) findPreference(getString(R.string.preferences__hide_screenshots));
        }
        if (!ListenerUtil.mutListener.listen(32617)) {
            this.disableScreenshotChecked = this.disableScreenshot.isChecked();
        }
        if (!ListenerUtil.mutListener.listen(32618)) {
            this.contactSyncPreference = (TwoStatePreference) findPreference(getResources().getString(R.string.preferences__sync_contacts));
        }
        CheckBoxPreference blockUnknown = (CheckBoxPreference) findPreference(getString(R.string.preferences__block_unknown));
        if (!ListenerUtil.mutListener.listen(32627)) {
            if (SynchronizeContactsUtil.isRestrictedProfile(getActivity())) {
                if (!ListenerUtil.mutListener.listen(32624)) {
                    // restricted android profile (e.g. guest user)
                    this.contactSyncPreference.setChecked(false);
                }
                if (!ListenerUtil.mutListener.listen(32625)) {
                    this.contactSyncPreference.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(32626)) {
                    this.contactSyncPreference.setSelectable(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32623)) {
                    this.contactSyncPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            boolean newCheckedValue = newValue.equals(true);
                            if (!ListenerUtil.mutListener.listen(32622)) {
                                if (((TwoStatePreference) preference).isChecked() != newCheckedValue) {
                                    if (!ListenerUtil.mutListener.listen(32621)) {
                                        if (newCheckedValue) {
                                            if (!ListenerUtil.mutListener.listen(32620)) {
                                                enableSync();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(32619)) {
                                                disableSync();
                                            }
                                        }
                                    }
                                }
                            }
                            // always return true, fix samsung preferences handler
                            return true;
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32639)) {
            if (ConfigUtils.isWorkRestricted()) {
                Boolean value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__block_unknown));
                if (!ListenerUtil.mutListener.listen(32630)) {
                    if (value != null) {
                        if (!ListenerUtil.mutListener.listen(32628)) {
                            blockUnknown.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32629)) {
                            blockUnknown.setSelectable(false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(32631)) {
                    value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_screenshots));
                }
                if (!ListenerUtil.mutListener.listen(32634)) {
                    if (value != null) {
                        if (!ListenerUtil.mutListener.listen(32632)) {
                            this.disableScreenshot.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32633)) {
                            this.disableScreenshot.setSelectable(false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(32635)) {
                    value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__contact_sync));
                }
                if (!ListenerUtil.mutListener.listen(32638)) {
                    if (value != null) {
                        if (!ListenerUtil.mutListener.listen(32636)) {
                            this.contactSyncPreference.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(32637)) {
                            this.contactSyncPreference.setSelectable(false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32642)) {
            if (ConfigUtils.getScreenshotsDisabled(ThreemaApplication.getServiceManager().getPreferenceService(), ThreemaApplication.getServiceManager().getLockAppService())) {
                if (!ListenerUtil.mutListener.listen(32640)) {
                    this.disableScreenshot.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(32641)) {
                    this.disableScreenshot.setSelectable(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32644)) {
            findPreference("pref_excluded_sync_identities").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32643)) {
                        startActivity(new Intent(getActivity(), ExcludedSyncIdentitiesActivity.class));
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32646)) {
            findPreference("pref_black_list").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32645)) {
                        startActivity(new Intent(getActivity(), BlackListActivity.class));
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(32660)) {
            if ((ListenerUtil.mutListener.listen(32651) ? (Build.VERSION.SDK_INT >= 29) : (ListenerUtil.mutListener.listen(32650) ? (Build.VERSION.SDK_INT <= 29) : (ListenerUtil.mutListener.listen(32649) ? (Build.VERSION.SDK_INT > 29) : (ListenerUtil.mutListener.listen(32648) ? (Build.VERSION.SDK_INT != 29) : (ListenerUtil.mutListener.listen(32647) ? (Build.VERSION.SDK_INT == 29) : (Build.VERSION.SDK_INT < 29))))))) {
                PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("pref_key_other");
                if (!ListenerUtil.mutListener.listen(32658)) {
                    if ((ListenerUtil.mutListener.listen(32656) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32655) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32654) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32653) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(32652) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(32657)) {
                            preferenceCategory.removePreference(findPreference(getResources().getString(R.string.preferences__direct_share)));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(32659)) {
                    preferenceCategory.removePreference(findPreference(getResources().getString(R.string.preferences__disable_smart_replies)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32661)) {
            this.updateView();
        }
    }

    private void updateView() {
        if (!ListenerUtil.mutListener.listen(32666)) {
            if (this.synchronizeContactsService.isSynchronizationInProgress()) {
                if (!ListenerUtil.mutListener.listen(32665)) {
                    // disable switcher
                    if (this.contactSyncPreference != null) {
                        if (!ListenerUtil.mutListener.listen(32664)) {
                            this.contactSyncPreference.setEnabled(false);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32663)) {
                    if (this.contactSyncPreference != null) {
                        if (!ListenerUtil.mutListener.listen(32662)) {
                            this.contactSyncPreference.setEnabled(true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32667)) {
            ListenerManager.synchronizeContactsListeners.add(this.synchronizeContactsListener);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(32668)) {
            ListenerManager.synchronizeContactsListeners.remove(this.synchronizeContactsListener);
        }
        if (!ListenerUtil.mutListener.listen(32672)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(32669)) {
                    DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_VALIDATE, true);
                }
                if (!ListenerUtil.mutListener.listen(32670)) {
                    DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_SYNC_CONTACTS, true);
                }
                if (!ListenerUtil.mutListener.listen(32671)) {
                    DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_DISABLE_SYNC, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32673)) {
            super.onDestroyView();
        }
    }

    private boolean requireInstances() {
        if (!ListenerUtil.mutListener.listen(32674)) {
            if (this.serviceManager != null) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(32675)) {
            this.serviceManager = ThreemaApplication.getServiceManager();
        }
        return this.serviceManager != null;
    }

    private boolean enableSync() {
        if (!ListenerUtil.mutListener.listen(32681)) {
            if (this.requireInstances()) {
                SynchronizeContactsService synchronizeContactsService;
                try {
                    synchronizeContactsService = this.serviceManager.getSynchronizeContactsService();
                    if (!ListenerUtil.mutListener.listen(32680)) {
                        if (synchronizeContactsService != null) {
                            if (!ListenerUtil.mutListener.listen(32679)) {
                                if (synchronizeContactsService.enableSync()) {
                                    if (!ListenerUtil.mutListener.listen(32678)) {
                                        if (ConfigUtils.requestContactPermissions(getActivity(), SettingsPrivacyFragment.this, PERMISSION_REQUEST_CONTACTS)) {
                                            if (!ListenerUtil.mutListener.listen(32677)) {
                                                launchContactsSync();
                                            }
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                    if (!ListenerUtil.mutListener.listen(32676)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return false;
    }

    private void launchContactsSync() {
        if (!ListenerUtil.mutListener.listen(32682)) {
            // start a Sync
            synchronizeContactsService.instantiateSynchronizationAndRun();
        }
    }

    private boolean disableSync() {
        if (!ListenerUtil.mutListener.listen(32691)) {
            if (this.requireInstances()) {
                final SynchronizeContactsService synchronizeContactsService;
                try {
                    synchronizeContactsService = this.serviceManager.getSynchronizeContactsService();
                } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                    if (!ListenerUtil.mutListener.listen(32683)) {
                        logger.error("Exception", e);
                    }
                    return false;
                }
                if (!ListenerUtil.mutListener.listen(32684)) {
                    GenericProgressDialog.newInstance(R.string.app_name, R.string.please_wait).show(getFragmentManager(), DIALOG_TAG_DISABLE_SYNC);
                }
                if (!ListenerUtil.mutListener.listen(32690)) {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(32689)) {
                                if (synchronizeContactsService != null) {
                                    if (!ListenerUtil.mutListener.listen(32688)) {
                                        synchronizeContactsService.disableSync(new Runnable() {

                                            @Override
                                            public void run() {
                                                if (!ListenerUtil.mutListener.listen(32687)) {
                                                    RuntimeUtil.runOnUiThread(new Runnable() {

                                                        @Override
                                                        public void run() {
                                                            if (!ListenerUtil.mutListener.listen(32685)) {
                                                                DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_DISABLE_SYNC, true);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(32686)) {
                                                                contactSyncPreference.setChecked(false);
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }).start();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32692)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.prefs_privacy);
        }
        if (!ListenerUtil.mutListener.listen(32693)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(32694)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(32696)) {
            if (this.disableScreenshot.isChecked() != this.disableScreenshotChecked) {
                if (!ListenerUtil.mutListener.listen(32695)) {
                    ConfigUtils.recreateActivity(getActivity());
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(32706)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_CONTACTS:
                    if (!ListenerUtil.mutListener.listen(32705)) {
                        if ((ListenerUtil.mutListener.listen(32702) ? ((ListenerUtil.mutListener.listen(32701) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(32700) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(32699) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(32698) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(32697) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(32701) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(32700) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(32699) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(32698) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(32697) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(32704)) {
                                launchContactsSync();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(32703)) {
                                disableSync();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onCancel(String tag, Object object) {
    }
}

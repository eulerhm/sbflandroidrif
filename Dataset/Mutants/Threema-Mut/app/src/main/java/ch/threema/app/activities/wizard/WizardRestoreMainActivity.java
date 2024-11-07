/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.activities.wizard;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import androidx.annotation.UiThread;
import androidx.core.content.ContextCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.DisableBatteryOptimizationsActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.backuprestore.csv.RestoreService;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.PasswordEntryDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.dialogs.WizardRestoreSelectorDialog;
import ch.threema.app.dialogs.WizardSafeSearchPhoneDialog;
import ch.threema.app.threemasafe.ThreemaSafeAdvancedDialog;
import ch.threema.app.threemasafe.ThreemaSafeMDMConfig;
import ch.threema.app.threemasafe.ThreemaSafeServerInfo;
import ch.threema.app.threemasafe.ThreemaSafeService;
import ch.threema.app.threemasafe.ThreemaSafeServiceImpl;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.ProtocolDefines;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardRestoreMainActivity extends WizardBackgroundActivity implements GenericAlertDialog.DialogClickListener, PasswordEntryDialog.PasswordEntryDialogClickListener, WizardSafeSearchPhoneDialog.WizardSafeSearchPhoneDialogCallback, WizardRestoreSelectorDialog.WizardRestoreSelectorDialogCallback, ThreemaSafeAdvancedDialog.WizardDialogCallback {

    private static final Logger logger = LoggerFactory.getLogger(WizardRestoreMainActivity.class);

    private static final String DIALOG_TAG_PASSWORD = "tpw";

    private static final String DIALOG_TAG_PROGRESS = "tpr";

    private static final String DIALOG_TAG_FORGOT_ID = "li";

    private static final String DIALOG_TAG_RESTORE_SELECTOR = "rss";

    private static final String DIALOG_TAG_DISABLE_ENERGYSAVE_CONFIRM = "de";

    private static final String DIALOG_TAG_DOWNLOADING_BACKUP = "dwnldBkp";

    private static final String DIALOG_TAG_NO_INTERNET = "nin";

    private static final String DIALOG_TAG_ADVANCED = "adv";

    public static final int REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS = 541;

    private ThreemaSafeService threemaSafeService;

    EditText identityEditText;

    ThreemaSafeMDMConfig safeMDMConfig;

    ThreemaSafeServerInfo serverInfo = new ThreemaSafeServerInfo();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1172)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1173)) {
            setContentView(R.layout.activity_wizard_restore_main);
        }
        try {
            if (!ListenerUtil.mutListener.listen(1175)) {
                threemaSafeService = ThreemaApplication.getServiceManager().getThreemaSafeService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1174)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(1176)) {
            this.safeMDMConfig = ThreemaSafeMDMConfig.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(1177)) {
            this.identityEditText = findViewById(R.id.safe_edit_id);
        }
        if (!ListenerUtil.mutListener.listen(1186)) {
            if (ConfigUtils.isWorkRestricted()) {
                if (!ListenerUtil.mutListener.listen(1185)) {
                    if (safeMDMConfig.isRestoreForced()) {
                        if (!ListenerUtil.mutListener.listen(1178)) {
                            this.identityEditText.setText(safeMDMConfig.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(1179)) {
                            this.identityEditText.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(1180)) {
                            findViewById(R.id.backup_restore_other_button).setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(1181)) {
                            findViewById(R.id.safe_restore_subtitle).setVisibility(View.INVISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(1182)) {
                            findViewById(R.id.forgot_id).setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(1184)) {
                            if (safeMDMConfig.isSkipRestorePasswordEntryDialog()) {
                                if (!ListenerUtil.mutListener.listen(1183)) {
                                    reallySafeRestore(safeMDMConfig.getPassword());
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1187)) {
            this.identityEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        }
        if (!ListenerUtil.mutListener.listen(1188)) {
            this.identityEditText.setFilters(new InputFilter[] { new InputFilter.AllCaps(), new InputFilter.LengthFilter(ProtocolDefines.IDENTITY_LEN) });
        }
        if (!ListenerUtil.mutListener.listen(1190)) {
            findViewById(R.id.forgot_id).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(1189)) {
                        WizardSafeSearchPhoneDialog.newInstance().show(getSupportFragmentManager(), DIALOG_TAG_FORGOT_ID);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1192)) {
            findViewById(R.id.backup_restore_other_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(1191)) {
                        showOtherRestoreOptions();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1198)) {
            findViewById(R.id.safe_restore_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(1197)) {
                        if ((ListenerUtil.mutListener.listen(1194) ? ((ListenerUtil.mutListener.listen(1193) ? (identityEditText != null || identityEditText.getText() != null) : (identityEditText != null && identityEditText.getText() != null)) || identityEditText.getText().toString().length() == ProtocolDefines.IDENTITY_LEN) : ((ListenerUtil.mutListener.listen(1193) ? (identityEditText != null || identityEditText.getText() != null) : (identityEditText != null && identityEditText.getText() != null)) && identityEditText.getText().toString().length() == ProtocolDefines.IDENTITY_LEN))) {
                            if (!ListenerUtil.mutListener.listen(1196)) {
                                doSafeRestore();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1195)) {
                                SimpleStringAlertDialog.newInstance(R.string.safe_restore, R.string.invalid_threema_id).show(getSupportFragmentManager(), "");
                            }
                        }
                    }
                }
            });
        }
        Button advancedOptions = findViewById(R.id.advanced_options);
        if (!ListenerUtil.mutListener.listen(1200)) {
            advancedOptions.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ThreemaSafeAdvancedDialog dialog = ThreemaSafeAdvancedDialog.newInstance(serverInfo, false);
                    if (!ListenerUtil.mutListener.listen(1199)) {
                        dialog.show(getSupportFragmentManager(), DIALOG_TAG_ADVANCED);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1215)) {
            if (ConfigUtils.isWorkRestricted()) {
                if (!ListenerUtil.mutListener.listen(1203)) {
                    if (safeMDMConfig.isRestoreExpertSettingsDisabled()) {
                        if (!ListenerUtil.mutListener.listen(1201)) {
                            advancedOptions.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(1202)) {
                            advancedOptions.setVisibility(View.GONE);
                        }
                    }
                }
                Intent intent = getIntent();
                if (!ListenerUtil.mutListener.listen(1206)) {
                    if ((ListenerUtil.mutListener.listen(1204) ? (intent.hasExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP) || intent.hasExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP_PW)) : (intent.hasExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP) && intent.hasExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP_PW)))) {
                        if (!ListenerUtil.mutListener.listen(1205)) {
                            launchIdRecovery(intent.getStringExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP), intent.getStringExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP_PW));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1214)) {
                    if (safeMDMConfig.isRestoreDisabled()) {
                        if (!ListenerUtil.mutListener.listen(1207)) {
                            findViewById(R.id.safe_restore_button).setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(1208)) {
                            ((TextView) findViewById(R.id.safe_restore_title)).setText(R.string.restore);
                        }
                        if (!ListenerUtil.mutListener.listen(1209)) {
                            findViewById(R.id.safe_restore_subtitle).setVisibility(View.INVISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(1210)) {
                            findViewById(R.id.forgot_id).setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(1211)) {
                            identityEditText.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(1212)) {
                            advancedOptions.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(1213)) {
                            showOtherRestoreOptions();
                        }
                    }
                }
            }
        }
    }

    private void showOtherRestoreOptions() {
        if (!ListenerUtil.mutListener.listen(1216)) {
            WizardRestoreSelectorDialog.newInstance().show(getSupportFragmentManager(), DIALOG_TAG_RESTORE_SELECTOR);
        }
    }

    private void doSafeRestore() {
        PasswordEntryDialog dialogFragment = PasswordEntryDialog.newInstance(R.string.safe_enter_password, R.string.restore_data_password_msg, R.string.password_hint, R.string.ok, R.string.cancel, ThreemaSafeServiceImpl.MIN_PW_LENGTH, ThreemaSafeServiceImpl.MAX_PW_LENGTH, 0, 0, 0);
        if (!ListenerUtil.mutListener.listen(1217)) {
            dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_PASSWORD);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void reallySafeRestore(String password) {
        final String identity;
        if (safeMDMConfig.isRestoreForced()) {
            if (!ListenerUtil.mutListener.listen(1220)) {
                serverInfo = safeMDMConfig.getServerInfo();
            }
            identity = safeMDMConfig.getIdentity();
        } else {
            if (!ListenerUtil.mutListener.listen(1219)) {
                if (safeMDMConfig.isRestoreExpertSettingsDisabled()) {
                    if (!ListenerUtil.mutListener.listen(1218)) {
                        serverInfo = safeMDMConfig.getServerInfo();
                    }
                }
            }
            if (identityEditText.getText() != null) {
                identity = identityEditText.getText().toString();
            } else {
                identity = null;
            }
        }
        if (!ListenerUtil.mutListener.listen(1222)) {
            if (TestUtil.empty(identity)) {
                if (!ListenerUtil.mutListener.listen(1221)) {
                    Toast.makeText(this, R.string.invalid_threema_id, Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1224)) {
            if (TestUtil.empty(password)) {
                if (!ListenerUtil.mutListener.listen(1223)) {
                    Toast.makeText(this, R.string.wrong_backupid_or_password_or_no_internet_connection, Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1247)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(1225)) {
                        GenericProgressDialog.newInstance(R.string.restore, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_PROGRESS);
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(1227)) {
                            serviceManager.stopConnection();
                        }
                    } catch (InterruptedException e) {
                        if (!ListenerUtil.mutListener.listen(1226)) {
                            this.cancel(true);
                        }
                    }
                }

                @Override
                protected String doInBackground(Void... voids) {
                    if (this.isCancelled()) {
                        return "Backup cancelled";
                    }
                    if (!ListenerUtil.mutListener.listen(1228)) {
                        preferenceService.setThreemaSafeEnabled(false);
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(1229)) {
                            threemaSafeService.restoreBackup(identity, password, serverInfo);
                        }
                        if (!ListenerUtil.mutListener.listen(1230)) {
                            // intentional: test server to update configuration only after restoring backup, so that master key (and thus shard hash) is set
                            threemaSafeService.testServer(serverInfo);
                        }
                        if (!ListenerUtil.mutListener.listen(1231)) {
                            threemaSafeService.setEnabled(true);
                        }
                        return null;
                    } catch (ThreemaException e) {
                        return e.getMessage();
                    } catch (IOException e) {
                        if (e instanceof FileNotFoundException) {
                            return getString(R.string.safe_no_backup_found);
                        }
                        return e.getLocalizedMessage();
                    }
                }

                @Override
                protected void onCancelled(String failureMessage) {
                    if (!ListenerUtil.mutListener.listen(1232)) {
                        this.onPostExecute(failureMessage);
                    }
                }

                @Override
                protected void onPostExecute(String failureMessage) {
                    if (!ListenerUtil.mutListener.listen(1233)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_PROGRESS, true);
                    }
                    if (!ListenerUtil.mutListener.listen(1246)) {
                        if (failureMessage == null) {
                            if (!ListenerUtil.mutListener.listen(1242)) {
                                SimpleStringAlertDialog.newInstance(R.string.restore_success_body, (ListenerUtil.mutListener.listen(1241) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1240) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1239) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1238) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1237) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P)))))) ? R.string.android_backup_restart_threema : R.string.safe_backup_tap_to_restart, true).show(getSupportFragmentManager(), "d");
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(1244)) {
                                    serviceManager.startConnection();
                                }
                            } catch (ThreemaException e) {
                                if (!ListenerUtil.mutListener.listen(1243)) {
                                    logger.error("Exception", e);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1245)) {
                                ConfigUtils.scheduleAppRestart(getApplicationContext(), 3000, getApplicationContext().getString(R.string.ipv6_restart_now));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1234)) {
                                Toast.makeText(WizardRestoreMainActivity.this, getString(R.string.safe_restore_failed) + ". " + failureMessage, Toast.LENGTH_LONG).show();
                            }
                            if (!ListenerUtil.mutListener.listen(1236)) {
                                if (safeMDMConfig.isRestoreForced()) {
                                    if (!ListenerUtil.mutListener.listen(1235)) {
                                        finish();
                                    }
                                }
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void launchIdRecovery(String backupString, String backupPassword) {
        Intent intent = new Intent(this, WizardRestoreIDActivity.class);
        if (!ListenerUtil.mutListener.listen(1251)) {
            if ((ListenerUtil.mutListener.listen(1248) ? (!TestUtil.empty(backupString) || !TestUtil.empty(backupPassword)) : (!TestUtil.empty(backupString) && !TestUtil.empty(backupPassword)))) {
                if (!ListenerUtil.mutListener.listen(1249)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP, backupString);
                }
                if (!ListenerUtil.mutListener.listen(1250)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP_PW, backupPassword);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1252)) {
            startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_RESTORE_KEY);
        }
        if (!ListenerUtil.mutListener.listen(1253)) {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void showDisableEnergySaveDialog() {
        if (!ListenerUtil.mutListener.listen(1254)) {
            GenericAlertDialog.newInstance(R.string.menu_restore, R.string.restore_disable_energy_saving, R.string.ok, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_DISABLE_ENERGYSAVE_CONFIRM);
        }
    }

    private void doDataBackupRestore(final Uri uri) {
        if (!ListenerUtil.mutListener.listen(1262)) {
            if ((ListenerUtil.mutListener.listen(1255) ? (!ContentResolver.SCHEME_FILE.equalsIgnoreCase(uri.getScheme()) || this.fileService != null) : (!ContentResolver.SCHEME_FILE.equalsIgnoreCase(uri.getScheme()) && this.fileService != null))) {
                if (!ListenerUtil.mutListener.listen(1260)) {
                    // copy "file" to cache directory first
                    GenericProgressDialog.newInstance(R.string.importing_files, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_DOWNLOADING_BACKUP);
                }
                if (!ListenerUtil.mutListener.listen(1261)) {
                    new Thread(() -> {
                        final File file = fileService.copyUriToTempFile(uri, "file", "zip", true);
                        RuntimeUtil.runOnUiThread(() -> {
                            DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_DOWNLOADING_BACKUP, true);
                            if (file != null) {
                                performDataBackupRestore(file);
                            } else {
                                SimpleStringAlertDialog.newInstance(R.string.importing_files, R.string.importing_files_failed).show(getSupportFragmentManager(), "ifail");
                            }
                        });
                    }).start();
                }
            } else {
                String path = FileUtil.getRealPathFromURI(this, uri);
                if (!ListenerUtil.mutListener.listen(1259)) {
                    if ((ListenerUtil.mutListener.listen(1256) ? (path != null || !path.isEmpty()) : (path != null && !path.isEmpty()))) {
                        File file = new File(path);
                        if (!ListenerUtil.mutListener.listen(1258)) {
                            if (file.exists()) {
                                if (!ListenerUtil.mutListener.listen(1257)) {
                                    performDataBackupRestore(file);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void performDataBackupRestore(File file) {
        if (!ListenerUtil.mutListener.listen(1267)) {
            if (file.exists()) {
                // if (zipFile.isValidZipFile()) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (!ListenerUtil.mutListener.listen(1266)) {
                    if ((ListenerUtil.mutListener.listen(1263) ? (activeNetwork == null && !activeNetwork.isConnectedOrConnecting()) : (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()))) {
                        if (!ListenerUtil.mutListener.listen(1265)) {
                            showNoInternetDialog(file);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1264)) {
                            confirmRestore(file);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1268)) {
            // }
            logger.error(getString(R.string.invalid_backup), this);
        }
    }

    private void confirmRestore(File file) {
        PasswordEntryDialog dialogFragment = PasswordEntryDialog.newInstance(R.string.backup_data_title, R.string.restore_data_password_msg, R.string.password_hint, R.string.ok, R.string.cancel, ThreemaApplication.MIN_PW_LENGTH_BACKUP, ThreemaApplication.MAX_PW_LENGTH_BACKUP, 0, 0, 0);
        if (!ListenerUtil.mutListener.listen(1269)) {
            dialogFragment.setData(file);
        }
        if (!ListenerUtil.mutListener.listen(1270)) {
            dialogFragment.show(getSupportFragmentManager(), "restorePW");
        }
    }

    private void startNextWizard() {
        if (!ListenerUtil.mutListener.listen(1271)) {
            logger.debug("start next wizard");
        }
        if (!ListenerUtil.mutListener.listen(1276)) {
            if (this.userService.hasIdentity()) {
                if (!ListenerUtil.mutListener.listen(1272)) {
                    serviceManager.getPreferenceService().setWizardRunning(true);
                }
                if (!ListenerUtil.mutListener.listen(1273)) {
                    startActivity(new Intent(this, WizardBaseActivity.class));
                }
                if (!ListenerUtil.mutListener.listen(1274)) {
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
                if (!ListenerUtil.mutListener.listen(1275)) {
                    finish();
                }
            }
        }
    }

    @UiThread
    private void showNoInternetDialog(File file) {
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.menu_restore, R.string.new_wizard_need_internet, R.string.retry, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(1277)) {
            dialog.setData(file);
        }
        if (!ListenerUtil.mutListener.listen(1278)) {
            dialog.show(getSupportFragmentManager(), DIALOG_TAG_NO_INTERNET);
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(1279)) {
            ThreemaApplication.activityPaused(this);
        }
        if (!ListenerUtil.mutListener.listen(1280)) {
            super.onPause();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(1281)) {
            ThreemaApplication.activityResumed(this);
        }
        if (!ListenerUtil.mutListener.listen(1282)) {
            super.onResume();
        }
    }

    @Override
    public void onUserInteraction() {
        if (!ListenerUtil.mutListener.listen(1283)) {
            ThreemaApplication.activityUserInteract(this);
        }
        if (!ListenerUtil.mutListener.listen(1284)) {
            super.onUserInteraction();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (!ListenerUtil.mutListener.listen(1285)) {
            logger.debug("Restore: {} {}", requestCode, resultCode);
        }
        if (!ListenerUtil.mutListener.listen(1295)) {
            if (resultCode != RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(1294)) {
                    if ((ListenerUtil.mutListener.listen(1291) ? ((ListenerUtil.mutListener.listen(1290) ? (requestCode >= REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (ListenerUtil.mutListener.listen(1289) ? (requestCode <= REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (ListenerUtil.mutListener.listen(1288) ? (requestCode > REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (ListenerUtil.mutListener.listen(1287) ? (requestCode < REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (ListenerUtil.mutListener.listen(1286) ? (requestCode == REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (requestCode != REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS)))))) || requestCode != ThreemaActivity.ACTIVITY_ID_BACKUP_PICKER) : ((ListenerUtil.mutListener.listen(1290) ? (requestCode >= REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (ListenerUtil.mutListener.listen(1289) ? (requestCode <= REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (ListenerUtil.mutListener.listen(1288) ? (requestCode > REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (ListenerUtil.mutListener.listen(1287) ? (requestCode < REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (ListenerUtil.mutListener.listen(1286) ? (requestCode == REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS) : (requestCode != REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS)))))) && requestCode != ThreemaActivity.ACTIVITY_ID_BACKUP_PICKER))) {
                        if (!ListenerUtil.mutListener.listen(1293)) {
                            if (safeMDMConfig.isRestoreDisabled()) {
                                if (!ListenerUtil.mutListener.listen(1292)) {
                                    finish();
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1305)) {
            switch(requestCode) {
                case REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS:
                    if (!ListenerUtil.mutListener.listen(1296)) {
                        FileUtil.selectFile(WizardRestoreMainActivity.this, null, new String[] { MimeUtil.MIME_TYPE_ZIP }, ThreemaActivity.ACTIVITY_ID_BACKUP_PICKER, false, 0, fileService.getBackupPath().getPath());
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_RESTORE_KEY:
                    if (!ListenerUtil.mutListener.listen(1299)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(1297)) {
                                setResult(RESULT_OK);
                            }
                            if (!ListenerUtil.mutListener.listen(1298)) {
                                startNextWizard();
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_BACKUP_PICKER:
                    if (!ListenerUtil.mutListener.listen(1304)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(1300)) {
                                setResult(RESULT_OK);
                            }
                            if (!ListenerUtil.mutListener.listen(1303)) {
                                if (resultData != null) {
                                    Uri uri;
                                    uri = resultData.getData();
                                    if (!ListenerUtil.mutListener.listen(1302)) {
                                        if (uri != null) {
                                            if (!ListenerUtil.mutListener.listen(1301)) {
                                                doDataBackupRestore(uri);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(1306)) {
            super.onActivityResult(requestCode, resultCode, resultData);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1307)) {
            finish();
        }
    }

    @Override
    public void onYes(String tag, String text, boolean isChecked, Object data) {
        if (!ListenerUtil.mutListener.listen(1314)) {
            if (DIALOG_TAG_PASSWORD.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(1313)) {
                    // safe backup restore
                    if (!TestUtil.empty(text)) {
                        if (!ListenerUtil.mutListener.listen(1312)) {
                            reallySafeRestore(text);
                        }
                    }
                }
            } else {
                // data backup restore
                Intent intent = new Intent(this, RestoreService.class);
                if (!ListenerUtil.mutListener.listen(1308)) {
                    intent.putExtra(RestoreService.EXTRA_RESTORE_BACKUP_FILE, (File) data);
                }
                if (!ListenerUtil.mutListener.listen(1309)) {
                    intent.putExtra(RestoreService.EXTRA_RESTORE_BACKUP_PASSWORD, text);
                }
                if (!ListenerUtil.mutListener.listen(1310)) {
                    ContextCompat.startForegroundService(this, intent);
                }
                if (!ListenerUtil.mutListener.listen(1311)) {
                    finish();
                }
            }
        }
    }

    @Override
    public void onYes(String tag, String id) {
        if (!ListenerUtil.mutListener.listen(1318)) {
            // return from id search
            if ((ListenerUtil.mutListener.listen(1315) ? (id != null || id.length() == ProtocolDefines.IDENTITY_LEN) : (id != null && id.length() == ProtocolDefines.IDENTITY_LEN))) {
                if (!ListenerUtil.mutListener.listen(1316)) {
                    this.identityEditText.setText("");
                }
                if (!ListenerUtil.mutListener.listen(1317)) {
                    this.identityEditText.append(id);
                }
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1323)) {
            switch(tag) {
                case DIALOG_TAG_DISABLE_ENERGYSAVE_CONFIRM:
                    Intent intent = new Intent(this, DisableBatteryOptimizationsActivity.class);
                    if (!ListenerUtil.mutListener.listen(1319)) {
                        intent.putExtra(DisableBatteryOptimizationsActivity.EXTRA_NAME, getString(R.string.restore));
                    }
                    if (!ListenerUtil.mutListener.listen(1320)) {
                        intent.putExtra(DisableBatteryOptimizationsActivity.EXTRA_WIZARD, true);
                    }
                    if (!ListenerUtil.mutListener.listen(1321)) {
                        startActivityForResult(intent, REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS);
                    }
                    break;
                case DIALOG_TAG_NO_INTERNET:
                    if (!ListenerUtil.mutListener.listen(1322)) {
                        performDataBackupRestore((File) data);
                    }
                    break;
            }
        }
    }

    @Override
    public void onYes(String tag, ThreemaSafeServerInfo serverInfo) {
        if (!ListenerUtil.mutListener.listen(1324)) {
            this.serverInfo = serverInfo;
        }
    }

    @Override
    public void onNo(String tag) {
        if (!ListenerUtil.mutListener.listen(1327)) {
            if (DIALOG_TAG_RESTORE_SELECTOR.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(1326)) {
                    if (safeMDMConfig.isRestoreDisabled()) {
                        if (!ListenerUtil.mutListener.listen(1325)) {
                            finish();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1330)) {
            if (DIALOG_TAG_DISABLE_ENERGYSAVE_CONFIRM.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(1329)) {
                    if (safeMDMConfig.isRestoreDisabled()) {
                        if (!ListenerUtil.mutListener.listen(1328)) {
                            finish();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDataBackupRestore() {
        if (!ListenerUtil.mutListener.listen(1331)) {
            showDisableEnergySaveDialog();
        }
    }

    @Override
    public void onIdBackupRestore() {
        if (!ListenerUtil.mutListener.listen(1332)) {
            launchIdRecovery(null, null);
        }
    }
}

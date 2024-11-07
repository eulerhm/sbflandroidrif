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
package ch.threema.app.activities.wizard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import ch.threema.app.utils.QRScannerUtil;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.client.ThreemaConnection;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardRestoreIDActivity extends WizardBackgroundActivity {

    private static final Logger logger = LoggerFactory.getLogger(WizardRestoreIDActivity.class);

    private static final String DIALOG_TAG_RESTORE_PROGRESS = "rp";

    private static final int PERMISSION_REQUEST_CAMERA = 1;

    private EditText backupIdText;

    private EditText passwordEditText;

    private boolean passwordOK = false, idOK = false;

    private Button nextButton, scanButton;

    private final int BACKUP_STRING_LENGTH = 99;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1092)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1093)) {
            setContentView(R.layout.activity_wizard_restore_id);
        }
        if (!ListenerUtil.mutListener.listen(1094)) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(1095)) {
            nextButton = findViewById(R.id.wizard_finish);
        }
        if (!ListenerUtil.mutListener.listen(1096)) {
            backupIdText = findViewById(R.id.restore_id_edittext);
        }
        if (!ListenerUtil.mutListener.listen(1097)) {
            backupIdText.setImeOptions(EditorInfo.IME_ACTION_SEND);
        }
        if (!ListenerUtil.mutListener.listen(1098)) {
            backupIdText.setRawInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        }
        if (!ListenerUtil.mutListener.listen(1107)) {
            backupIdText.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(1105)) {
                        idOK = (ListenerUtil.mutListener.listen(1104) ? ((ListenerUtil.mutListener.listen(1103) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(1102) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(1101) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(1100) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(1099) ? (s.length() == 0) : (s.length() > 0)))))) || s.toString().trim().length() == BACKUP_STRING_LENGTH) : ((ListenerUtil.mutListener.listen(1103) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(1102) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(1101) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(1100) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(1099) ? (s.length() == 0) : (s.length() > 0)))))) && s.toString().trim().length() == BACKUP_STRING_LENGTH));
                    }
                    if (!ListenerUtil.mutListener.listen(1106)) {
                        updateMenu();
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1108)) {
            passwordEditText = findViewById(R.id.restore_password);
        }
        if (!ListenerUtil.mutListener.listen(1116)) {
            passwordEditText.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(1114)) {
                        passwordOK = (ListenerUtil.mutListener.listen(1113) ? (s.length() <= ThreemaApplication.MIN_PW_LENGTH_ID_EXPORT_LEGACY) : (ListenerUtil.mutListener.listen(1112) ? (s.length() > ThreemaApplication.MIN_PW_LENGTH_ID_EXPORT_LEGACY) : (ListenerUtil.mutListener.listen(1111) ? (s.length() < ThreemaApplication.MIN_PW_LENGTH_ID_EXPORT_LEGACY) : (ListenerUtil.mutListener.listen(1110) ? (s.length() != ThreemaApplication.MIN_PW_LENGTH_ID_EXPORT_LEGACY) : (ListenerUtil.mutListener.listen(1109) ? (s.length() == ThreemaApplication.MIN_PW_LENGTH_ID_EXPORT_LEGACY) : (s.length() >= ThreemaApplication.MIN_PW_LENGTH_ID_EXPORT_LEGACY))))));
                    }
                    if (!ListenerUtil.mutListener.listen(1115)) {
                        updateMenu();
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1117)) {
            scanButton = findViewById(R.id.wizard_scan);
        }
        if (!ListenerUtil.mutListener.listen(1118)) {
            scanButton.getCompoundDrawables()[2].setColorFilter(getResources().getColor(R.color.wizard_button_text_inverse), PorterDuff.Mode.SRC_IN);
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(1123)) {
            if ((ListenerUtil.mutListener.listen(1119) ? (intent.hasExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP) || intent.hasExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP_PW)) : (intent.hasExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP) && intent.hasExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP_PW)))) {
                if (!ListenerUtil.mutListener.listen(1120)) {
                    backupIdText.setText(intent.getStringExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP));
                }
                if (!ListenerUtil.mutListener.listen(1121)) {
                    passwordEditText.setText(intent.getStringExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP_PW));
                }
                if (!ListenerUtil.mutListener.listen(1122)) {
                    restoreID(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1124)) {
            findViewById(R.id.wizard_finish).setOnClickListener(this::restoreID);
        }
        if (!ListenerUtil.mutListener.listen(1125)) {
            findViewById(R.id.wizard_cancel).setOnClickListener(this::onCancel);
        }
        if (!ListenerUtil.mutListener.listen(1126)) {
            findViewById(R.id.wizard_scan).setOnClickListener(v -> {
                if (ConfigUtils.requestCameraPermissions(WizardRestoreIDActivity.this, null, PERMISSION_REQUEST_CAMERA)) {
                    scanQR();
                }
            });
        }
    }

    private void updateMenu() {
        if (!ListenerUtil.mutListener.listen(1127)) {
            if (nextButton == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(1131)) {
            if ((ListenerUtil.mutListener.listen(1128) ? (idOK || passwordOK) : (idOK && passwordOK))) {
                if (!ListenerUtil.mutListener.listen(1130)) {
                    nextButton.setEnabled(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1129)) {
                    nextButton.setEnabled(false);
                }
            }
        }
    }

    public void onCancel(View view) {
        if (!ListenerUtil.mutListener.listen(1132)) {
            finish();
        }
    }

    public void scanQR() {
        if (!ListenerUtil.mutListener.listen(1133)) {
            QRScannerUtil.getInstance().initiateScan(this, false, null);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void restoreID(View view) {
        if (!ListenerUtil.mutListener.listen(1134)) {
            EditTextUtil.hideSoftKeyboard(backupIdText);
        }
        if (!ListenerUtil.mutListener.listen(1135)) {
            EditTextUtil.hideSoftKeyboard(passwordEditText);
        }
        if (!ListenerUtil.mutListener.listen(1150)) {
            new AsyncTask<Void, Void, Boolean>() {

                String password, backupString;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(1136)) {
                        GenericProgressDialog.newInstance(R.string.restoring_backup, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_RESTORE_PROGRESS);
                    }
                    if (!ListenerUtil.mutListener.listen(1137)) {
                        password = passwordEditText.getText().toString();
                    }
                    if (!ListenerUtil.mutListener.listen(1138)) {
                        backupString = backupIdText.getText().toString().trim();
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    try {
                        ThreemaConnection connection = serviceManager.getConnection();
                        if (!ListenerUtil.mutListener.listen(1143)) {
                            if (connection.isRunning()) {
                                if (!ListenerUtil.mutListener.listen(1142)) {
                                    connection.stop();
                                }
                            }
                        }
                        return serviceManager.getUserService().restoreIdentity(backupString, password);
                    } catch (InterruptedException e) {
                        if (!ListenerUtil.mutListener.listen(1139)) {
                            logger.error("Interrupted", e);
                        }
                        if (!ListenerUtil.mutListener.listen(1140)) {
                            Thread.currentThread().interrupt();
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(1141)) {
                            logger.error("Exception", e);
                        }
                    }
                    return false;
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    if (!ListenerUtil.mutListener.listen(1144)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_RESTORE_PROGRESS, true);
                    }
                    if (!ListenerUtil.mutListener.listen(1149)) {
                        if (result) {
                            if (!ListenerUtil.mutListener.listen(1146)) {
                                // ID successfully restored from ID backup - cancel reminder
                                serviceManager.getPreferenceService().incrementIDBackupCount();
                            }
                            if (!ListenerUtil.mutListener.listen(1147)) {
                                setResult(RESULT_OK);
                            }
                            if (!ListenerUtil.mutListener.listen(1148)) {
                                finish();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1145)) {
                                getSupportFragmentManager().beginTransaction().add(SimpleStringAlertDialog.newInstance(R.string.error, getString(R.string.wrong_backupid_or_password_or_no_internet_connection)), "er").commitAllowingStateLoss();
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String scanResult = QRScannerUtil.getInstance().parseActivityResult(this, requestCode, resultCode, intent);
        if (!ListenerUtil.mutListener.listen(1160)) {
            if (scanResult != null) {
                if (!ListenerUtil.mutListener.listen(1159)) {
                    if ((ListenerUtil.mutListener.listen(1155) ? (scanResult.length() >= BACKUP_STRING_LENGTH) : (ListenerUtil.mutListener.listen(1154) ? (scanResult.length() <= BACKUP_STRING_LENGTH) : (ListenerUtil.mutListener.listen(1153) ? (scanResult.length() > BACKUP_STRING_LENGTH) : (ListenerUtil.mutListener.listen(1152) ? (scanResult.length() < BACKUP_STRING_LENGTH) : (ListenerUtil.mutListener.listen(1151) ? (scanResult.length() != BACKUP_STRING_LENGTH) : (scanResult.length() == BACKUP_STRING_LENGTH))))))) {
                        if (!ListenerUtil.mutListener.listen(1157)) {
                            backupIdText.setText(scanResult);
                        }
                        if (!ListenerUtil.mutListener.listen(1158)) {
                            backupIdText.invalidate();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1156)) {
                            logger.error(getString(R.string.invalid_barcode), this);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1161)) {
            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(1171)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_CAMERA:
                    if (!ListenerUtil.mutListener.listen(1170)) {
                        if ((ListenerUtil.mutListener.listen(1167) ? ((ListenerUtil.mutListener.listen(1166) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1165) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1164) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1163) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1162) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(1166) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1165) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1164) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1163) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1162) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(1169)) {
                                scanQR();
                            }
                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            if (!ListenerUtil.mutListener.listen(1168)) {
                                ConfigUtils.showPermissionRationale(this, findViewById(R.id.top_view), R.string.permission_camera_qr_required);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

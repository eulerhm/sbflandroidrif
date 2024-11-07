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
package ch.threema.app.threemasafe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.fragments.wizard.WizardFragment1;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.TextUtil;
import ch.threema.base.ThreemaException;
import static ch.threema.app.threemasafe.ThreemaSafeServiceImpl.MIN_PW_LENGTH;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaSafeConfigureActivity extends ThreemaToolbarActivity implements ThreemaSafeAdvancedDialog.WizardDialogCallback, GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaSafeConfigureActivity.class);

    private static final String DIALOG_TAG_PREPARING = "prep";

    private static final String DIALOG_TAG_ADVANCED = "adv";

    public static final String EXTRA_CHANGE_PASSWORD = "cp";

    public static final String EXTRA_WORK_FORCE_PASSWORD = "fp";

    private ThreemaSafeService threemaSafeService;

    private UserService userService;

    private EditText password1, password2;

    private String safePassword = null;

    private TextInputLayout password1layout, password2layout;

    private Button nextButton;

    private boolean updatePasswordOnly;

    private ThreemaSafeServerInfo serverInfo;

    @SuppressLint("SetTextI18n")
    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(42754)) {
            super.initActivity(savedInstanceState);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(42755)) {
            if (actionBar == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(42756)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        try {
            if (!ListenerUtil.mutListener.listen(42757)) {
                threemaSafeService = ThreemaApplication.getServiceManager().getThreemaSafeService();
            }
            if (!ListenerUtil.mutListener.listen(42758)) {
                userService = ThreemaApplication.getServiceManager().getUserService();
            }
        } catch (Exception e) {
            return false;
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(42759)) {
            this.password1 = findViewById(R.id.safe_password1);
        }
        if (!ListenerUtil.mutListener.listen(42760)) {
            this.password2 = findViewById(R.id.safe_password2);
        }
        if (!ListenerUtil.mutListener.listen(42761)) {
            this.password1layout = findViewById(R.id.password1layout);
        }
        if (!ListenerUtil.mutListener.listen(42762)) {
            this.password2layout = findViewById(R.id.password2layout);
        }
        if (!ListenerUtil.mutListener.listen(42763)) {
            this.password1.addTextChangedListener(new PasswordWatcher());
        }
        if (!ListenerUtil.mutListener.listen(42764)) {
            this.password2.addTextChangedListener(new PasswordWatcher());
        }
        Button advancedButton = findViewById(R.id.advanced_options);
        if (!ListenerUtil.mutListener.listen(42777)) {
            if (((ListenerUtil.mutListener.listen(42765) ? (intent != null || intent.getBooleanExtra(EXTRA_CHANGE_PASSWORD, false)) : (intent != null && intent.getBooleanExtra(EXTRA_CHANGE_PASSWORD, false))))) {
                if (!ListenerUtil.mutListener.listen(42774)) {
                    updatePasswordOnly = true;
                }
                if (!ListenerUtil.mutListener.listen(42775)) {
                    actionBar.setTitle(R.string.safe_change_password);
                }
                if (!ListenerUtil.mutListener.listen(42776)) {
                    advancedButton.setVisibility(View.INVISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42766)) {
                    updatePasswordOnly = false;
                }
                if (!ListenerUtil.mutListener.listen(42767)) {
                    actionBar.setTitle(R.string.safe_configure_choose_password_title);
                }
                if (!ListenerUtil.mutListener.listen(42768)) {
                    advancedButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(42769)) {
                    advancedButton.setOnClickListener(v -> {
                        ThreemaSafeAdvancedDialog dialog = ThreemaSafeAdvancedDialog.newInstance(serverInfo, true);
                        dialog.show(getSupportFragmentManager(), DIALOG_TAG_ADVANCED);
                    });
                }
                if (!ListenerUtil.mutListener.listen(42773)) {
                    if ((ListenerUtil.mutListener.listen(42771) ? ((ListenerUtil.mutListener.listen(42770) ? (ConfigUtils.isWorkRestricted() || intent != null) : (ConfigUtils.isWorkRestricted() && intent != null)) || intent.getBooleanExtra(EXTRA_WORK_FORCE_PASSWORD, false)) : ((ListenerUtil.mutListener.listen(42770) ? (ConfigUtils.isWorkRestricted() || intent != null) : (ConfigUtils.isWorkRestricted() && intent != null)) && intent.getBooleanExtra(EXTRA_WORK_FORCE_PASSWORD, false)))) {
                        TextView explainText = findViewById(R.id.safe_enable_explain);
                        if (!ListenerUtil.mutListener.listen(42772)) {
                            explainText.setText(getString(R.string.work_safe_forced_explain) + "\n\n" + getString(R.string.safe_configure_choose_password));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42778)) {
            nextButton = findViewById(R.id.next);
        }
        if (!ListenerUtil.mutListener.listen(42779)) {
            nextButton.setOnClickListener(v -> {
                // finish up
                saveChangesAndExit(safePassword);
            });
        }
        if (!ListenerUtil.mutListener.listen(42780)) {
            nextButton.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(42781)) {
            nextButton.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(42782)) {
            this.serverInfo = preferenceService.getThreemaSafeServerInfo();
        }
        if (!ListenerUtil.mutListener.listen(42786)) {
            if (ConfigUtils.isWorkRestricted()) {
                ThreemaSafeMDMConfig safeMDMConfig = ThreemaSafeMDMConfig.getInstance();
                if (!ListenerUtil.mutListener.listen(42785)) {
                    if (safeMDMConfig.isBackupExpertSettingsDisabled()) {
                        if (!ListenerUtil.mutListener.listen(42783)) {
                            advancedButton.setVisibility(View.INVISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(42784)) {
                            this.serverInfo = safeMDMConfig.getServerInfo();
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_threema_safe_configure;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(42788)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(42787)) {
                        onBackPressed();
                    }
                    break;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(42789)) {
            super.onBackPressed();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void saveChangesAndExit(final String safePassword) {
        if (!ListenerUtil.mutListener.listen(42808)) {
            if (!TestUtil.empty(safePassword)) {
                if (!ListenerUtil.mutListener.listen(42807)) {
                    new AsyncTask<Void, Void, Boolean>() {

                        byte[] masterkey;

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(42792)) {
                                GenericProgressDialog.newInstance(R.string.preparing_threema_safe, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_PREPARING);
                            }
                        }

                        @Override
                        protected Boolean doInBackground(Void... voids) {
                            if (!ListenerUtil.mutListener.listen(42793)) {
                                masterkey = threemaSafeService.deriveMasterKey(safePassword, userService.getIdentity());
                            }
                            if (!ListenerUtil.mutListener.listen(42796)) {
                                if (!TextUtil.checkBadPassword(ThreemaSafeConfigureActivity.this, safePassword)) {
                                    if (!ListenerUtil.mutListener.listen(42795)) {
                                        if (updatePasswordOnly) {
                                            if (!ListenerUtil.mutListener.listen(42794)) {
                                                deleteExistingBackup();
                                            }
                                        }
                                    }
                                    return true;
                                }
                            }
                            return false;
                        }

                        @Override
                        protected void onPostExecute(Boolean passwordOK) {
                            if (!ListenerUtil.mutListener.listen(42797)) {
                                DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_PREPARING, true);
                            }
                            if (!ListenerUtil.mutListener.listen(42806)) {
                                if (masterkey != null) {
                                    if (!ListenerUtil.mutListener.listen(42805)) {
                                        if (!passwordOK) {
                                            Context context = ThreemaSafeConfigureActivity.this;
                                            if (!ListenerUtil.mutListener.listen(42804)) {
                                                if (AppRestrictionUtil.isSafePasswordPatternSet(context)) {
                                                    if (!ListenerUtil.mutListener.listen(42803)) {
                                                        GenericAlertDialog.newInstance(R.string.password_bad, AppRestrictionUtil.getSafePasswordMessage(context), R.string.try_again, 0, false).show(getSupportFragmentManager(), "");
                                                    }
                                                } else {
                                                    GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.password_bad, R.string.password_bad_explain, R.string.try_again, R.string.continue_anyway, false);
                                                    if (!ListenerUtil.mutListener.listen(42801)) {
                                                        dialog.setData(masterkey);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(42802)) {
                                                        dialog.show(getSupportFragmentManager(), "");
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(42800)) {
                                                storeKeyAndFinish(masterkey);
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(42798)) {
                                        Toast.makeText(ThreemaSafeConfigureActivity.this, R.string.safe_error_preparing, Toast.LENGTH_LONG).show();
                                    }
                                    if (!ListenerUtil.mutListener.listen(42799)) {
                                        finish();
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42790)) {
                    threemaSafeService.storeMasterKey(new byte[0]);
                }
                if (!ListenerUtil.mutListener.listen(42791)) {
                    finish();
                }
            }
        }
    }

    @WorkerThread
    private boolean deleteExistingBackup() {
        try {
            if (!ListenerUtil.mutListener.listen(42810)) {
                threemaSafeService.deleteBackup();
            }
            return true;
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(42809)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    private void storeKeyAndFinish(byte[] masterkey) {
        if (!ListenerUtil.mutListener.listen(42811)) {
            threemaSafeService.storeMasterKey(masterkey);
        }
        if (!ListenerUtil.mutListener.listen(42812)) {
            preferenceService.setThreemaSafeServerInfo(serverInfo);
        }
        if (!ListenerUtil.mutListener.listen(42813)) {
            threemaSafeService.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(42814)) {
            threemaSafeService.uploadNow(this, true);
        }
        if (!ListenerUtil.mutListener.listen(42817)) {
            if (updatePasswordOnly) {
                if (!ListenerUtil.mutListener.listen(42816)) {
                    Toast.makeText(ThreemaApplication.getAppContext(), R.string.safe_password_updated, Toast.LENGTH_LONG).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42815)) {
                    Toast.makeText(ThreemaApplication.getAppContext(), R.string.safe_activated, Toast.LENGTH_LONG).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42818)) {
            finish();
        }
    }

    private class PasswordWatcher implements TextWatcher {

        private PasswordWatcher() {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean passwordOk = getPasswordOK(password1.getText().toString(), password2.getText().toString());
            if (!ListenerUtil.mutListener.listen(42823)) {
                if (passwordOk) {
                    if (!ListenerUtil.mutListener.listen(42821)) {
                        safePassword = s.toString();
                    }
                    if (!ListenerUtil.mutListener.listen(42822)) {
                        nextButton.setEnabled(true);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(42819)) {
                        safePassword = null;
                    }
                    if (!ListenerUtil.mutListener.listen(42820)) {
                        nextButton.setEnabled(false);
                    }
                }
            }
        }
    }

    private boolean getPasswordOK(String password1Text, String password2Text) {
        boolean lengthOk = WizardFragment1.getPasswordLengthOK(password1Text, AppRestrictionUtil.isSafePasswordPatternSet(this) ? 1 : MIN_PW_LENGTH);
        boolean passwordsMatch = password1Text.equals(password2Text);
        if (!ListenerUtil.mutListener.listen(42836)) {
            if ((ListenerUtil.mutListener.listen(42829) ? (!lengthOk || (ListenerUtil.mutListener.listen(42828) ? (password1Text.length() >= 0) : (ListenerUtil.mutListener.listen(42827) ? (password1Text.length() <= 0) : (ListenerUtil.mutListener.listen(42826) ? (password1Text.length() < 0) : (ListenerUtil.mutListener.listen(42825) ? (password1Text.length() != 0) : (ListenerUtil.mutListener.listen(42824) ? (password1Text.length() == 0) : (password1Text.length() > 0))))))) : (!lengthOk && (ListenerUtil.mutListener.listen(42828) ? (password1Text.length() >= 0) : (ListenerUtil.mutListener.listen(42827) ? (password1Text.length() <= 0) : (ListenerUtil.mutListener.listen(42826) ? (password1Text.length() < 0) : (ListenerUtil.mutListener.listen(42825) ? (password1Text.length() != 0) : (ListenerUtil.mutListener.listen(42824) ? (password1Text.length() == 0) : (password1Text.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(42834)) {
                    this.password1layout.setError(getString(R.string.password_too_short_generic));
                }
                if (!ListenerUtil.mutListener.listen(42835)) {
                    this.password2layout.setError(null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42830)) {
                    this.password1layout.setError(null);
                }
                if (!ListenerUtil.mutListener.listen(42833)) {
                    if (!TestUtil.empty(this.password2.getText())) {
                        if (!ListenerUtil.mutListener.listen(42832)) {
                            this.password2layout.setError(passwordsMatch ? null : getString(R.string.passwords_dont_match));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(42831)) {
                            this.password2layout.setError(null);
                        }
                    }
                }
            }
        }
        return ((ListenerUtil.mutListener.listen(42837) ? (lengthOk || passwordsMatch) : (lengthOk && passwordsMatch)));
    }

    @Override
    public void onYes(String tag, ThreemaSafeServerInfo serverInfo) {
        if (!ListenerUtil.mutListener.listen(42838)) {
            this.serverInfo = serverInfo;
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onYes(String tag, Object data) {
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(42842)) {
            if (updatePasswordOnly) {
                if (!ListenerUtil.mutListener.listen(42841)) {
                    new AsyncTask<Void, Void, Boolean>() {

                        @Override
                        protected Boolean doInBackground(Void... voids) {
                            return deleteExistingBackup();
                        }

                        @Override
                        protected void onPostExecute(Boolean success) {
                            if (!ListenerUtil.mutListener.listen(42840)) {
                                storeKeyAndFinish((byte[]) data);
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42839)) {
                    storeKeyAndFinish((byte[]) data);
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(42843)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(42844)) {
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
    }
}

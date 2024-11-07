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
package ch.threema.app.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.PasswordEntryDialog;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.IdentityBackupGenerator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExportIDActivity extends AppCompatActivity implements PasswordEntryDialog.PasswordEntryDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(AppCompatActivity.class);

    private static final String DIALOG_TAG_SET_ID_BACKUP_PW = "setIDBackupPW";

    private static final String DIALOG_PROGRESS_ID = "idBackup";

    private PreferenceService preferenceService;

    private UserService userService;

    private String identity;

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2709)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2711)) {
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(2710)) {
                    setTheme(R.style.Theme_Threema_Translucent_Dark);
                }
            }
        }
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(2712)) {
            preferenceService = serviceManager.getPreferenceService();
        }
        if (!ListenerUtil.mutListener.listen(2713)) {
            userService = serviceManager.getUserService();
        }
        if (!ListenerUtil.mutListener.listen(2717)) {
            if ((ListenerUtil.mutListener.listen(2714) ? (userService == null && preferenceService == null) : (userService == null || preferenceService == null))) {
                if (!ListenerUtil.mutListener.listen(2715)) {
                    logger.error("services not available", this);
                }
                if (!ListenerUtil.mutListener.listen(2716)) {
                    this.finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2718)) {
            this.identity = userService.getIdentity();
        }
        DialogFragment dialogFragment = PasswordEntryDialog.newInstance(R.string.backup_title, R.string.backup_password_summary, R.string.password_hint, R.string.ok, R.string.cancel, ThreemaApplication.MIN_PW_LENGTH_BACKUP, ThreemaApplication.MAX_PW_LENGTH_BACKUP, R.string.backup_password_again_summary, 0, 0);
        if (!ListenerUtil.mutListener.listen(2719)) {
            dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_SET_ID_BACKUP_PW);
        }
    }

    private void displayIDBackup(String result) {
        Intent intent = new Intent(this, ExportIDResultActivity.class);
        if (!ListenerUtil.mutListener.listen(2720)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP, result);
        }
        if (!ListenerUtil.mutListener.listen(2721)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, identity);
        }
        if (!ListenerUtil.mutListener.listen(2722)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(2723)) {
            finish();
        }
    }

    private void createIDBackup(final String password) {
        if (!ListenerUtil.mutListener.listen(2731)) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(2724)) {
                        super.onPreExecute();
                    }
                    if (!ListenerUtil.mutListener.listen(2725)) {
                        GenericProgressDialog.newInstance(R.string.generating_backup_data, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_PROGRESS_ID);
                    }
                }

                @Override
                protected Void doInBackground(Void... params) {
                    IdentityBackupGenerator identityBackupGenerator = new IdentityBackupGenerator(identity, userService.getPrivateKey());
                    try {
                        final String result = identityBackupGenerator.generateBackup(password);
                        if (!ListenerUtil.mutListener.listen(2727)) {
                            preferenceService.incrementIDBackupCount();
                        }
                        if (!ListenerUtil.mutListener.listen(2728)) {
                            RuntimeUtil.runOnUiThread(() -> displayIDBackup(result));
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(2726)) {
                            logger.debug("no idbackup");
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (!ListenerUtil.mutListener.listen(2729)) {
                        super.onPostExecute(aVoid);
                    }
                    if (!ListenerUtil.mutListener.listen(2730)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_PROGRESS_ID, true);
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(2732)) {
            // activity when the keyboard is opened or orientation changes
            super.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onYes(String tag, String text, boolean isChecked, Object data) {
        if (!ListenerUtil.mutListener.listen(2734)) {
            switch(tag) {
                case DIALOG_TAG_SET_ID_BACKUP_PW:
                    if (!ListenerUtil.mutListener.listen(2733)) {
                        createIDBackup(text);
                    }
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag) {
        if (!ListenerUtil.mutListener.listen(2735)) {
            finish();
        }
    }
}

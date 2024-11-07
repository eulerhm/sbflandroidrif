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
package ch.threema.app.asynctasks;

import android.os.AsyncTask;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.push.PushService;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PassphraseService;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.SecureDeleteUtil;
import ch.threema.app.webclient.services.SessionWakeUpServiceImpl;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.client.ThreemaConnection;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.NonceDatabaseBlobService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeleteIdentityAsyncTask extends AsyncTask<Void, Void, Exception> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteIdentityAsyncTask.class);

    private static final String DIALOG_TAG_DELETING_ID = "di";

    private final ServiceManager serviceManager;

    private final FragmentManager fragmentManager;

    private final Runnable runOnCompletion;

    public DeleteIdentityAsyncTask(FragmentManager fragmentManager, Runnable runOnCompletion) {
        this.serviceManager = ThreemaApplication.getServiceManager();
        this.fragmentManager = fragmentManager;
        this.runOnCompletion = runOnCompletion;
    }

    @Override
    protected void onPreExecute() {
        if (!ListenerUtil.mutListener.listen(10110)) {
            GenericProgressDialog.newInstance(R.string.delete_id_title, R.string.please_wait).show(fragmentManager, DIALOG_TAG_DELETING_ID);
        }
    }

    @Override
    protected Exception doInBackground(Void... params) {
        ThreemaConnection connection = serviceManager.getConnection();
        try {
            if (!ListenerUtil.mutListener.listen(10112)) {
                // clear push token
                PushService.deleteToken(ThreemaApplication.getAppContext());
            }
            if (!ListenerUtil.mutListener.listen(10113)) {
                serviceManager.getThreemaSafeService().unscheduleUpload();
            }
            if (!ListenerUtil.mutListener.listen(10114)) {
                serviceManager.getMessageService().removeAll();
            }
            if (!ListenerUtil.mutListener.listen(10115)) {
                serviceManager.getConversationService().reset();
            }
            if (!ListenerUtil.mutListener.listen(10116)) {
                serviceManager.getGroupService().removeAll();
            }
            if (!ListenerUtil.mutListener.listen(10117)) {
                serviceManager.getContactService().removeAll();
            }
            if (!ListenerUtil.mutListener.listen(10118)) {
                serviceManager.getUserService().removeIdentity();
            }
            if (!ListenerUtil.mutListener.listen(10119)) {
                serviceManager.getDistributionListService().removeAll();
            }
            if (!ListenerUtil.mutListener.listen(10120)) {
                serviceManager.getBallotService().removeAll();
            }
            if (!ListenerUtil.mutListener.listen(10121)) {
                serviceManager.getPreferenceService().clear();
            }
            if (!ListenerUtil.mutListener.listen(10122)) {
                serviceManager.getFileService().removeAllAvatars();
            }
            if (!ListenerUtil.mutListener.listen(10123)) {
                serviceManager.getWallpaperService().removeAll(ThreemaApplication.getAppContext(), true);
            }
            boolean interrupted = false;
            try {
                if (!ListenerUtil.mutListener.listen(10125)) {
                    serviceManager.getConnection().stop();
                }
            } catch (InterruptedException ignored) {
                if (!ListenerUtil.mutListener.listen(10124)) {
                    // incomplete data may remain on the file system.
                    interrupted = true;
                }
            }
            if (!ListenerUtil.mutListener.listen(10126)) {
                // webclient cleanup
                serviceManager.getWebClientServiceManager().getSessionService().stopAll(DisconnectContext.byUs(DisconnectContext.REASON_SESSION_DELETED));
            }
            if (!ListenerUtil.mutListener.listen(10127)) {
                SessionWakeUpServiceImpl.clear();
            }
            try {
                if (!ListenerUtil.mutListener.listen(10128)) {
                    ThreemaApplication.getMasterKey().setPassphrase(null);
                }
            } catch (Exception e) {
            }
            File aesFile = new File(ThreemaApplication.getAppContext().getFilesDir(), ThreemaApplication.AES_KEY_FILE);
            File databaseFile = ThreemaApplication.getAppContext().getDatabasePath(DatabaseServiceNew.DATABASE_NAME_V4);
            File nonceDatabaseFile = ThreemaApplication.getAppContext().getDatabasePath(NonceDatabaseBlobService.DATABASE_NAME_V4);
            File backupFile = ThreemaApplication.getAppContext().getDatabasePath(DatabaseServiceNew.DATABASE_NAME_V4 + DatabaseServiceNew.DATABASE_BACKUP_EXT);
            File cacheDirectory = ThreemaApplication.getAppContext().getCacheDir();
            File externalCacheDirectory = ThreemaApplication.getAppContext().getExternalCacheDir();
            if (!ListenerUtil.mutListener.listen(10129)) {
                secureDelete(aesFile);
            }
            if (!ListenerUtil.mutListener.listen(10130)) {
                secureDelete(databaseFile);
            }
            if (!ListenerUtil.mutListener.listen(10131)) {
                secureDelete(nonceDatabaseFile);
            }
            if (!ListenerUtil.mutListener.listen(10132)) {
                secureDelete(backupFile);
            }
            if (!ListenerUtil.mutListener.listen(10133)) {
                secureDelete(cacheDirectory);
            }
            if (!ListenerUtil.mutListener.listen(10134)) {
                secureDelete(externalCacheDirectory);
            }
            if (!ListenerUtil.mutListener.listen(10136)) {
                if (PassphraseService.isRunning()) {
                    if (!ListenerUtil.mutListener.listen(10135)) {
                        PassphraseService.stop(ThreemaApplication.getAppContext());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10138)) {
                if (interrupted) {
                    if (!ListenerUtil.mutListener.listen(10137)) {
                        // An InterruptedException was caught. Re-set the interruption flag.
                        Thread.currentThread().interrupt();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(10111)) {
                logger.error("Exception", e);
            }
            return e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Exception exception) {
        if (!ListenerUtil.mutListener.listen(10139)) {
            DialogUtil.dismissDialog(fragmentManager, DIALOG_TAG_DELETING_ID, true);
        }
        if (!ListenerUtil.mutListener.listen(10143)) {
            if (exception != null) {
                if (!ListenerUtil.mutListener.listen(10142)) {
                    Toast.makeText(ThreemaApplication.getAppContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10141)) {
                    if (runOnCompletion != null) {
                        if (!ListenerUtil.mutListener.listen(10140)) {
                            runOnCompletion.run();
                        }
                    }
                }
            }
        }
    }

    private void secureDelete(File file) {
        try {
            if (!ListenerUtil.mutListener.listen(10145)) {
                SecureDeleteUtil.secureDelete(file);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(10144)) {
                logger.error("Exception", e);
            }
        }
    }
}

/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.services.systemupdate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.sql.SQLException;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.WorkManager;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.UpdateSystemService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemUpdateToVersion64 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion64.class);

    private Context context;

    public SystemUpdateToVersion64(Context context) {
        if (!ListenerUtil.mutListener.listen(36438)) {
            this.context = context;
        }
    }

    @Override
    public boolean runDirectly() throws SQLException {
        return true;
    }

    @Override
    public boolean runASync() {
        if (!ListenerUtil.mutListener.listen(36439)) {
            deleteMediaLabelsDatabase();
        }
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    private void deleteMediaLabelsDatabase() {
        if (!ListenerUtil.mutListener.listen(36440)) {
            logger.debug("deleteMediaLabelsDatabase");
        }
        if (!ListenerUtil.mutListener.listen(36453)) {
            new AsyncTask<Void, Void, Exception>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(36441)) {
                        WorkManager.getInstance(ThreemaApplication.getAppContext()).cancelAllWorkByTag("ImageLabelsPeriodic");
                    }
                    if (!ListenerUtil.mutListener.listen(36442)) {
                        WorkManager.getInstance(ThreemaApplication.getAppContext()).cancelAllWorkByTag("ImageLabelsOneTime");
                    }
                }

                @Override
                protected Exception doInBackground(Void... voids) {
                    try {
                        final String[] files = new String[] { "media_items.db", "media_items.db-shm", "media_items.db-wal" };
                        if (!ListenerUtil.mutListener.listen(36450)) {
                            {
                                long _loopCounter351 = 0;
                                for (String filename : files) {
                                    ListenerUtil.loopListener.listen("_loopCounter351", ++_loopCounter351);
                                    final File databasePath = context.getDatabasePath(filename);
                                    if (!ListenerUtil.mutListener.listen(36449)) {
                                        if ((ListenerUtil.mutListener.listen(36444) ? (databasePath.exists() || databasePath.isFile()) : (databasePath.exists() && databasePath.isFile()))) {
                                            if (!ListenerUtil.mutListener.listen(36446)) {
                                                logger.info("Removing file {}", filename);
                                            }
                                            if (!ListenerUtil.mutListener.listen(36448)) {
                                                if (!databasePath.delete()) {
                                                    if (!ListenerUtil.mutListener.listen(36447)) {
                                                        logger.warn("Could not remove file {}", filename);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(36445)) {
                                                logger.debug("File {} not found", filename);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(36443)) {
                            logger.error("Exception while deleting media labels database");
                        }
                        return e;
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Exception e) {
                    // remove notification channel
                    String NOTIFICATION_CHANNEL_IMAGE_LABELING = "il";
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                    if (!ListenerUtil.mutListener.listen(36452)) {
                        if (notificationManagerCompat != null) {
                            if (!ListenerUtil.mutListener.listen(36451)) {
                                notificationManagerCompat.deleteNotificationChannel(NOTIFICATION_CHANNEL_IMAGE_LABELING);
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public String getText() {
        return "delete media labels database";
    }
}

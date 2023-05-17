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

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.core.app.FixedJobIntentService;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.listeners.ThreemaSafeListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PreferenceService;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaSafeUploadService extends FixedJobIntentService {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaSafeUploadService.class);

    private static final int JOB_ID = 1001;

    public static final String EXTRA_FORCE_UPLOAD = "force";

    private static boolean isRunning;

    private ServiceManager serviceManager;

    private ThreemaSafeService threemaSafeService;

    private PreferenceService preferenceService;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(43633)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(43634)) {
            isRunning = true;
        }
        try {
            if (!ListenerUtil.mutListener.listen(43635)) {
                serviceManager = ThreemaApplication.getServiceManager();
            }
            if (!ListenerUtil.mutListener.listen(43636)) {
                threemaSafeService = serviceManager.getThreemaSafeService();
            }
            if (!ListenerUtil.mutListener.listen(43637)) {
                preferenceService = serviceManager.getPreferenceService();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(43638)) {
            isRunning = false;
        }
        if (!ListenerUtil.mutListener.listen(43639)) {
            super.onDestroy();
        }
    }

    /**
     *  Convenience method for enqueuing work in to this service.
     */
    public static void enqueueWork(Context context, Intent work) {
        if (!ListenerUtil.mutListener.listen(43640)) {
            if (isRunning())
                return;
        }
        if (!ListenerUtil.mutListener.listen(43641)) {
            enqueueWork(context, ThreemaSafeUploadService.class, JOB_ID, work);
        }
    }

    public static boolean isRunning() {
        return isRunning;
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (!ListenerUtil.mutListener.listen(43642)) {
            logger.debug("ThreemaSafeUploadService: onHandleWork");
        }
        boolean force = intent.getBooleanExtra(EXTRA_FORCE_UPLOAD, false);
        if (!ListenerUtil.mutListener.listen(43649)) {
            if (threemaSafeService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(43646)) {
                        threemaSafeService.createBackup(force);
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(43644)) {
                        showWarningNotification();
                    }
                    if (!ListenerUtil.mutListener.listen(43645)) {
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(43648)) {
                    ListenerManager.threemaSafeListeners.handle(new ListenerManager.HandleListener<ThreemaSafeListener>() {

                        @Override
                        public void handle(ThreemaSafeListener listener) {
                            if (!ListenerUtil.mutListener.listen(43647)) {
                                listener.onBackupStatusChanged();
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(43643)) {
                    stopSelf();
                }
            }
        }
    }

    private void showWarningNotification() {
        Date backupDate = preferenceService.getThreemaSafeBackupDate();
        Date aWeekAgo = new Date((ListenerUtil.mutListener.listen(43653) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(43652) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(43651) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(43650) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))));
        if (!ListenerUtil.mutListener.listen(43665)) {
            if ((ListenerUtil.mutListener.listen(43654) ? (backupDate != null || backupDate.before(aWeekAgo)) : (backupDate != null && backupDate.before(aWeekAgo)))) {
                NotificationService notificationService = serviceManager.getNotificationService();
                if (!ListenerUtil.mutListener.listen(43664)) {
                    if (notificationService != null) {
                        if (!ListenerUtil.mutListener.listen(43663)) {
                            notificationService.showSafeBackupFailed((int) ((ListenerUtil.mutListener.listen(43662) ? (((ListenerUtil.mutListener.listen(43658) ? (System.currentTimeMillis() % backupDate.getTime()) : (ListenerUtil.mutListener.listen(43657) ? (System.currentTimeMillis() / backupDate.getTime()) : (ListenerUtil.mutListener.listen(43656) ? (System.currentTimeMillis() * backupDate.getTime()) : (ListenerUtil.mutListener.listen(43655) ? (System.currentTimeMillis() + backupDate.getTime()) : (System.currentTimeMillis() - backupDate.getTime())))))) % DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(43661) ? (((ListenerUtil.mutListener.listen(43658) ? (System.currentTimeMillis() % backupDate.getTime()) : (ListenerUtil.mutListener.listen(43657) ? (System.currentTimeMillis() / backupDate.getTime()) : (ListenerUtil.mutListener.listen(43656) ? (System.currentTimeMillis() * backupDate.getTime()) : (ListenerUtil.mutListener.listen(43655) ? (System.currentTimeMillis() + backupDate.getTime()) : (System.currentTimeMillis() - backupDate.getTime())))))) * DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(43660) ? (((ListenerUtil.mutListener.listen(43658) ? (System.currentTimeMillis() % backupDate.getTime()) : (ListenerUtil.mutListener.listen(43657) ? (System.currentTimeMillis() / backupDate.getTime()) : (ListenerUtil.mutListener.listen(43656) ? (System.currentTimeMillis() * backupDate.getTime()) : (ListenerUtil.mutListener.listen(43655) ? (System.currentTimeMillis() + backupDate.getTime()) : (System.currentTimeMillis() - backupDate.getTime())))))) - DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(43659) ? (((ListenerUtil.mutListener.listen(43658) ? (System.currentTimeMillis() % backupDate.getTime()) : (ListenerUtil.mutListener.listen(43657) ? (System.currentTimeMillis() / backupDate.getTime()) : (ListenerUtil.mutListener.listen(43656) ? (System.currentTimeMillis() * backupDate.getTime()) : (ListenerUtil.mutListener.listen(43655) ? (System.currentTimeMillis() + backupDate.getTime()) : (System.currentTimeMillis() - backupDate.getTime())))))) + DateUtils.DAY_IN_MILLIS) : (((ListenerUtil.mutListener.listen(43658) ? (System.currentTimeMillis() % backupDate.getTime()) : (ListenerUtil.mutListener.listen(43657) ? (System.currentTimeMillis() / backupDate.getTime()) : (ListenerUtil.mutListener.listen(43656) ? (System.currentTimeMillis() * backupDate.getTime()) : (ListenerUtil.mutListener.listen(43655) ? (System.currentTimeMillis() + backupDate.getTime()) : (System.currentTimeMillis() - backupDate.getTime())))))) / DateUtils.DAY_IN_MILLIS)))))));
                        }
                    }
                }
            }
        }
    }
}

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
package ch.threema.app.webclient.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.MainThread;
import androidx.core.app.NotificationCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.DummyActivity;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.app.services.NotificationService;
import ch.threema.app.webclient.activities.SessionsActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@MainThread
public class SessionAndroidService extends Service {

    private static final Logger logger = LoggerFactory.getLogger(SessionAndroidService.class);

    private static final int WEBCLIENT_ACTIVE_NOTIFICATION_ID = 23329;

    public static final String ACTION_START = "start";

    public static final String ACTION_STOP = "stop";

    public static final String ACTION_UPDATE = "update";

    public static final String ACTION_FORCE_STOP = "force_stop";

    private SessionService sessionService;

    // Binder given to clients
    private static boolean isRunning = false, isStopping = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public synchronized void onCreate() {
        if (!ListenerUtil.mutListener.listen(64445)) {
            logger.trace("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(64446)) {
            super.onCreate();
        }
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this, NotificationService.NOTIFICATION_CHANNEL_WEBCLIENT, null);
        if (!ListenerUtil.mutListener.listen(64447)) {
            builder.setContentTitle(getString(R.string.webclient)).setContentText(getString(R.string.please_wait)).setSmallIcon(R.drawable.ic_web_notification).setPriority(Notification.PRIORITY_LOW).setLocalOnly(true);
        }
        if (!ListenerUtil.mutListener.listen(64448)) {
            startForeground(WEBCLIENT_ACTIVE_NOTIFICATION_ID, builder.build());
        }
        if (!ListenerUtil.mutListener.listen(64449)) {
            logger.info("startForeground called");
        }
        // initialization may lock the app for a while so we display the above temporary notification before getting the service to avoid a Context.startForegroundService() did not then call Service.startForeground() exception
        try {
            if (!ListenerUtil.mutListener.listen(64452)) {
                sessionService = ThreemaApplication.getServiceManager().getWebClientServiceManager().getSessionService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(64450)) {
                logger.error("Service Manager not available (passphrase locked?). Can't start web client", e);
            }
            if (!ListenerUtil.mutListener.listen(64451)) {
                stopSelf();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(64453)) {
            updateNotification();
        }
        if (!ListenerUtil.mutListener.listen(64454)) {
            isRunning = true;
        }
    }

    @Override
    public synchronized int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(64455)) {
            logger.trace("onStartCommand");
        }
        if (!ListenerUtil.mutListener.listen(64457)) {
            if ((ListenerUtil.mutListener.listen(64456) ? (intent == null && intent.getAction() == null) : (intent == null || intent.getAction() == null))) {
                return START_NOT_STICKY;
            }
        }
        if (!ListenerUtil.mutListener.listen(64458)) {
            if (isStopping) {
                return START_NOT_STICKY;
            }
        }
        if (!ListenerUtil.mutListener.listen(64477)) {
            switch(intent.getAction()) {
                case ACTION_START:
                    if (!ListenerUtil.mutListener.listen(64459)) {
                        logger.info("ACTION_START");
                    }
                    break;
                case ACTION_STOP:
                    if (!ListenerUtil.mutListener.listen(64460)) {
                        logger.info("ACTION_STOP");
                    }
                // fallthrough
                case ACTION_UPDATE:
                    if (!ListenerUtil.mutListener.listen(64461)) {
                        logger.info("ACTION_UPDATE");
                    }
                    if (!ListenerUtil.mutListener.listen(64472)) {
                        if ((ListenerUtil.mutListener.listen(64466) ? (sessionService.getRunningSessionsCount() >= 0) : (ListenerUtil.mutListener.listen(64465) ? (sessionService.getRunningSessionsCount() > 0) : (ListenerUtil.mutListener.listen(64464) ? (sessionService.getRunningSessionsCount() < 0) : (ListenerUtil.mutListener.listen(64463) ? (sessionService.getRunningSessionsCount() != 0) : (ListenerUtil.mutListener.listen(64462) ? (sessionService.getRunningSessionsCount() == 0) : (sessionService.getRunningSessionsCount() <= 0))))))) {
                            if (!ListenerUtil.mutListener.listen(64468)) {
                                logger.info("No more running sessions");
                            }
                            if (!ListenerUtil.mutListener.listen(64469)) {
                                isRunning = false;
                            }
                            if (!ListenerUtil.mutListener.listen(64470)) {
                                isStopping = true;
                            }
                            if (!ListenerUtil.mutListener.listen(64471)) {
                                stopSelf();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(64467)) {
                                updateNotification();
                            }
                        }
                    }
                    break;
                case ACTION_FORCE_STOP:
                    if (!ListenerUtil.mutListener.listen(64473)) {
                        logger.info("ACTION_FORCE_STOP");
                    }
                    if (!ListenerUtil.mutListener.listen(64474)) {
                        isRunning = false;
                    }
                    if (!ListenerUtil.mutListener.listen(64475)) {
                        isStopping = true;
                    }
                    if (!ListenerUtil.mutListener.listen(64476)) {
                        stopSelf();
                    }
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(64478)) {
            logger.trace("onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(64479)) {
            removeNotification();
        }
        if (!ListenerUtil.mutListener.listen(64480)) {
            stopForeground(true);
        }
        if (!ListenerUtil.mutListener.listen(64481)) {
            logger.info("stopForeground");
        }
        if (!ListenerUtil.mutListener.listen(64482)) {
            isRunning = false;
        }
        if (!ListenerUtil.mutListener.listen(64483)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(64484)) {
            isStopping = false;
        }
        if (!ListenerUtil.mutListener.listen(64485)) {
            logger.info("Service destroyed");
        }
    }

    @Override
    public void onLowMemory() {
        if (!ListenerUtil.mutListener.listen(64486)) {
            logger.info("onLowMemory");
        }
        if (!ListenerUtil.mutListener.listen(64487)) {
            super.onLowMemory();
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        if (!ListenerUtil.mutListener.listen(64488)) {
            logger.info("onTaskRemoved");
        }
        Intent intent = new Intent(this, DummyActivity.class);
        if (!ListenerUtil.mutListener.listen(64489)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(64490)) {
            startActivity(intent);
        }
    }

    private Notification getNotification() {
        Intent contentIntent = new Intent(this, SessionsActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent stopIntent = new Intent(this, StopSessionsAndroidService.class);
        PendingIntent stopPendingIntent = PendingIntent.getService(this, (int) System.currentTimeMillis(), stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this, NotificationService.NOTIFICATION_CHANNEL_WEBCLIENT, null);
        if (!ListenerUtil.mutListener.listen(64491)) {
            builder.setContentTitle(getString(R.string.webclient)).setContentText(String.format(getString(R.string.webclient_running_sessions), sessionService.getRunningSessionsCount())).setSmallIcon(R.drawable.ic_web_notification).setPriority(Notification.PRIORITY_LOW).setContentIntent(contentPendingIntent).setLocalOnly(true).addAction(R.drawable.ic_close_white_24dp, getString(R.string.webclient_session_stop_all), stopPendingIntent);
        }
        return builder.build();
    }

    private void updateNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(64493)) {
            if (notificationManager != null) {
                if (!ListenerUtil.mutListener.listen(64492)) {
                    notificationManager.notify(WEBCLIENT_ACTIVE_NOTIFICATION_ID, getNotification());
                }
            }
        }
    }

    private void removeNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(64495)) {
            if (notificationManager != null) {
                if (!ListenerUtil.mutListener.listen(64494)) {
                    notificationManager.cancel(WEBCLIENT_ACTIVE_NOTIFICATION_ID);
                }
            }
        }
    }

    public static boolean isRunning() {
        return isRunning;
    }
}

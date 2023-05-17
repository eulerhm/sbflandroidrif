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
package ch.threema.app.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.DummyActivity;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.activities.StopPassphraseServiceActivity;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.localcrypto.MasterKey;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PassphraseService extends Service {

    private static final Logger logger = LoggerFactory.getLogger(PassphraseService.class);

    private static Intent service;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(40207)) {
            logger.debug("onCreate");
        }
        try {
            if (!ListenerUtil.mutListener.listen(40209)) {
                showPersistentNotification();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(40208)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(40210)) {
            logger.debug("onStartCommand");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(40211)) {
            logger.debug("onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(40212)) {
            removePersistentNotification(this);
        }
        if (!ListenerUtil.mutListener.listen(40213)) {
            stopForeground(true);
        }
        if (!ListenerUtil.mutListener.listen(40214)) {
            service = null;
        }
    }

    /**
     *  Workaround for Android bug:
     *  https://code.google.com/p/android/issues/detail?id=53313
     */
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        if (!ListenerUtil.mutListener.listen(40215)) {
            logger.info("*** PassphraseService task removed");
        }
        Intent intent = new Intent(this, DummyActivity.class);
        if (!ListenerUtil.mutListener.listen(40216)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(40217)) {
            startActivity(intent);
        }
    }

    private void showPersistentNotification() {
        if (!ListenerUtil.mutListener.listen(40218)) {
            logger.debug("showPersistentNotification");
        }
        // The Intent to launch our activity if the user selects this notification
        Intent notificationIntent = new Intent(this, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(40219)) {
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(40220)) {
            notificationIntent.setAction(Long.toString(System.currentTimeMillis()));
        }
        Intent stopIntent = new Intent(this, StopPassphraseServiceActivity.class);
        if (!ListenerUtil.mutListener.listen(40221)) {
            stopIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        }
        PendingIntent stopPendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        if (!ListenerUtil.mutListener.listen(40222)) {
            // Adds the back stack
            stackBuilder.addParentStack(HomeActivity.class);
        }
        if (!ListenerUtil.mutListener.listen(40223)) {
            // Adds the Intent to the top of the stack
            stackBuilder.addNextIntent(notificationIntent);
        }
        // Gets a PendingIntent containing the entire back stack
        PendingIntent pendingIntent = stackBuilder.getPendingIntent((int) System.currentTimeMillis(), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this, NotificationService.NOTIFICATION_CHANNEL_PASSPHRASE, null).setSmallIcon(R.drawable.ic_noti_passguard).setContentTitle(getString(R.string.app_name)).setContentText(getString(R.string.masterkey_is_unlocked)).setPriority(Notification.PRIORITY_MIN).addAction(R.drawable.ic_lock_grey600_24dp, getString(R.string.title_lock), stopPendingIntent);
        if (!ListenerUtil.mutListener.listen(40225)) {
            if (pendingIntent != null) {
                if (!ListenerUtil.mutListener.listen(40224)) {
                    builder.setContentIntent(pendingIntent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(40226)) {
            startForeground(ThreemaApplication.PASSPHRASE_SERVICE_NOTIFICATION_ID, builder.build());
        }
    }

    private static void removePersistentNotification(Context context) {
        if (!ListenerUtil.mutListener.listen(40227)) {
            logger.debug("removePersistentNotification");
        }
        // ServiceManager may not yet be available at this point!
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(40228)) {
            notificationManager.cancel(ThreemaApplication.PASSPHRASE_SERVICE_NOTIFICATION_ID);
        }
        if (!ListenerUtil.mutListener.listen(40231)) {
            if (ThreemaApplication.getServiceManager() != null) {
                NotificationService notificationService = ThreemaApplication.getServiceManager().getNotificationService();
                if (!ListenerUtil.mutListener.listen(40230)) {
                    if (notificationService != null) {
                        if (!ListenerUtil.mutListener.listen(40229)) {
                            notificationService.cancelConversationNotificationsOnLockApp();
                        }
                    }
                }
            }
        }
    }

    public static boolean isRunning() {
        if (!ListenerUtil.mutListener.listen(40232)) {
            logger.debug("isRunning");
        }
        return (service != null);
    }

    /**
     *  Start the passphrase service if the masterkey is protected and not locked!
     */
    public static void start(final Context context) {
        if (!ListenerUtil.mutListener.listen(40233)) {
            logger.debug("start");
        }
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(40242)) {
            // start service, if not yet started
            if (service == null) {
                if (!ListenerUtil.mutListener.listen(40239)) {
                    if ((ListenerUtil.mutListener.listen(40238) ? (masterKey.isLocked() && !masterKey.isProtected()) : (masterKey.isLocked() || !masterKey.isProtected()))) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(40240)) {
                    service = new Intent(context, PassphraseService.class);
                }
                if (!ListenerUtil.mutListener.listen(40241)) {
                    ContextCompat.startForegroundService(context, service);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(40237)) {
                    if (!masterKey.isProtected()) {
                        if (!ListenerUtil.mutListener.listen(40234)) {
                            removePersistentNotification(context);
                        }
                        if (!ListenerUtil.mutListener.listen(40235)) {
                            context.stopService(service);
                        }
                        if (!ListenerUtil.mutListener.listen(40236)) {
                            service = null;
                        }
                    }
                }
            }
        }
    }

    public static void stop(final Context context) {
        if (!ListenerUtil.mutListener.listen(40243)) {
            logger.debug("stop");
        }
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(40248)) {
            if (service != null) {
                if (!ListenerUtil.mutListener.listen(40247)) {
                    if (masterKey.isProtected()) {
                        if (!ListenerUtil.mutListener.listen(40244)) {
                            removePersistentNotification(context);
                        }
                        if (!ListenerUtil.mutListener.listen(40245)) {
                            context.stopService(service);
                        }
                        if (!ListenerUtil.mutListener.listen(40246)) {
                            service = null;
                        }
                    }
                }
            }
        }
    }
}

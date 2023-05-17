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
package ch.threema.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.core.app.FixedJobIntentService;
import androidx.core.app.NotificationCompat;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.localcrypto.MasterKey;
import static ch.threema.app.services.NotificationService.NOTIFICATION_CHANNEL_NOTICE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AutostartService extends FixedJobIntentService {

    private static final Logger logger = LoggerFactory.getLogger(AutostartService.class);

    private static final int JOB_ID = 2000;

    public static void enqueueWork(Context context, Intent work) {
        if (!ListenerUtil.mutListener.listen(65130)) {
            enqueueWork(context, AutostartService.class, JOB_ID, work);
        }
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (!ListenerUtil.mutListener.listen(65131)) {
            logger.info("Processing AutoStart - start");
        }
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(65134)) {
            if (masterKey == null) {
                if (!ListenerUtil.mutListener.listen(65132)) {
                    logger.error("Unable to launch app");
                }
                if (!ListenerUtil.mutListener.listen(65133)) {
                    stopSelf();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(65138)) {
            // check if masterkey needs a password and issue a notification if necessary
            if (masterKey.isLocked()) {
                NotificationCompat.Builder notificationCompat = new NotificationBuilderWrapper(this, NOTIFICATION_CHANNEL_NOTICE, null).setSmallIcon(R.drawable.ic_notification_small).setContentTitle(getString(R.string.master_key_locked)).setContentText(getString(R.string.master_key_locked_notify_description)).setTicker(getString(R.string.master_key_locked)).setCategory(NotificationCompat.CATEGORY_SERVICE);
                Intent notificationIntent = IntentDataUtil.createActionIntentHideAfterUnlock(new Intent(this, HomeActivity.class));
                if (!ListenerUtil.mutListener.listen(65135)) {
                    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
                if (!ListenerUtil.mutListener.listen(65136)) {
                    notificationCompat.setContentIntent(pendingIntent);
                }
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(65137)) {
                    notificationManager.notify(ThreemaApplication.MASTER_KEY_LOCKED_NOTIFICATION_ID, notificationCompat.build());
                }
            }
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(65141)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(65139)) {
                    logger.error("Service manager not available");
                }
                if (!ListenerUtil.mutListener.listen(65140)) {
                    stopSelf();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(65151)) {
            // check if background data is disabled and issue a warning
            if ((ListenerUtil.mutListener.listen(65146) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(65145) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(65144) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(65143) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(65142) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (!ListenerUtil.mutListener.listen(65150)) {
                    if ((ListenerUtil.mutListener.listen(65147) ? (connMgr != null || connMgr.getRestrictBackgroundStatus() == ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED) : (connMgr != null && connMgr.getRestrictBackgroundStatus() == ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED))) {
                        NotificationService notificationService = serviceManager.getNotificationService();
                        if (!ListenerUtil.mutListener.listen(65149)) {
                            if (notificationService != null) {
                                if (!ListenerUtil.mutListener.listen(65148)) {
                                    notificationService.showNetworkBlockedNotification(false);
                                }
                            }
                        }
                    }
                }
            }
        }
        // fixes https://issuetracker.google.com/issues/36951052
        PreferenceService preferenceService = serviceManager.getPreferenceService();
        if (!ListenerUtil.mutListener.listen(65158)) {
            if (preferenceService != null) {
                if (!ListenerUtil.mutListener.listen(65152)) {
                    // reset feature level
                    preferenceService.setTransmittedFeatureLevel(0);
                }
                if (!ListenerUtil.mutListener.listen(65157)) {
                    // auto fix failed sync account
                    if (preferenceService.isSyncContacts()) {
                        UserService userService = serviceManager.getUserService();
                        if (!ListenerUtil.mutListener.listen(65156)) {
                            if ((ListenerUtil.mutListener.listen(65153) ? (userService != null || !userService.checkAccount()) : (userService != null && !userService.checkAccount()))) {
                                if (!ListenerUtil.mutListener.listen(65154)) {
                                    // create account
                                    userService.getAccount(true);
                                }
                                if (!ListenerUtil.mutListener.listen(65155)) {
                                    userService.enableAccountAutoSync(true);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65159)) {
            logger.info("Processing AutoStart - end");
        }
    }
}

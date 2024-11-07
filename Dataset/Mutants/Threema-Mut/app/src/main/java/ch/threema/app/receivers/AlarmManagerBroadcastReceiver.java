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
package ch.threema.app.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.text.format.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import ch.threema.app.BuildConfig;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.PollingHelper;
import ch.threema.client.ConnectionState;
import ch.threema.client.ThreemaConnection;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    private static final Logger logger = LoggerFactory.getLogger(AlarmManagerBroadcastReceiver.class);

    private static PendingIntent requireLoggedInConnectionIntent = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        int requestCode = intent.getIntExtra("requestCode", 0);
        if (!ListenerUtil.mutListener.listen(34328)) {
            logger.info("Alarm type {} received", requestCode);
        }
        if (!ListenerUtil.mutListener.listen(34331)) {
            if (ThreemaApplication.getServiceManager() != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(34330)) {
                        new Thread(() -> {
                            PowerManager.WakeLock wakeLock = null;
                            try {
                                wakeLock = ((PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE)).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, BuildConfig.APPLICATION_ID + ":AlarmManagerBroadcastReceiver");
                                wakeLock.acquire(DateUtils.MINUTE_IN_MILLIS * 2);
                            } catch (Exception ignore) {
                            }
                            if (intent.hasExtra("requireLoggedInConnection") && intent.getBooleanExtra("requireLoggedInConnection", false)) {
                                PollingHelper p = new PollingHelper(context, "require");
                                cancelLoggedInConnection(context);
                                if (p.poll(true)) {
                                    // recheck!
                                    requireLoggedInConnection(context, intent.getIntExtra("nextCheck", (int) DateUtils.MINUTE_IN_MILLIS) * 2);
                                }
                            } else {
                                long time = System.currentTimeMillis();
                                logger.info("Alarm type " + requestCode + " dispatch to LifetimeService START");
                                ThreemaApplication.getServiceManager().getLifetimeService().alarm(intent);
                                logger.info("Alarm type " + requestCode + " dispatch to LifetimeService STOP. Duration = " + (System.currentTimeMillis() - time) + "ms");
                            }
                            if (wakeLock != null && wakeLock.isHeld()) {
                                wakeLock.release();
                            }
                        }, "AlarmOnReceive").start();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(34329)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    public static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public static void cancelLoggedInConnection(final Context context) {
        if (!ListenerUtil.mutListener.listen(34336)) {
            if (requireLoggedInConnectionIntent != null) {
                AlarmManager alarmManager = getAlarmManager(context);
                if (!ListenerUtil.mutListener.listen(34335)) {
                    if (alarmManager != null) {
                        if (!ListenerUtil.mutListener.listen(34332)) {
                            logger.debug("cancel cancelLoggedInConnection");
                        }
                        if (!ListenerUtil.mutListener.listen(34333)) {
                            alarmManager.cancel(requireLoggedInConnectionIntent);
                        }
                        if (!ListenerUtil.mutListener.listen(34334)) {
                            requireLoggedInConnectionIntent = null;
                        }
                    }
                }
            }
        }
    }

    public static void requireLoggedInConnection(final Context context, final int milliseconds) {
        if (!ListenerUtil.mutListener.listen(34337)) {
            logger.debug("requireLoggedInConnection");
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(34358)) {
            if (serviceManager != null) {
                final Date now = new Date();
                ThreemaConnection connection = serviceManager.getConnection();
                LifetimeService lifetimeService = serviceManager.getLifetimeService();
                if (!ListenerUtil.mutListener.listen(34357)) {
                    if ((ListenerUtil.mutListener.listen(34338) ? (connection != null || connection.getConnectionState() != ConnectionState.LOGGEDIN) : (connection != null && connection.getConnectionState() != ConnectionState.LOGGEDIN))) {
                        if (!ListenerUtil.mutListener.listen(34356)) {
                            if (lifetimeService != null) {
                                if (!ListenerUtil.mutListener.listen(34355)) {
                                    lifetimeService.addListener(new LifetimeService.LifetimeServiceListener() {

                                        @Override
                                        public boolean connectionStopped() {
                                            if (!ListenerUtil.mutListener.listen(34354)) {
                                                if ((ListenerUtil.mutListener.listen(34339) ? (ThreemaApplication.getLastLoggedIn() == null && ThreemaApplication.getLastLoggedIn().before(now)) : (ThreemaApplication.getLastLoggedIn() == null || ThreemaApplication.getLastLoggedIn().before(now)))) {
                                                    if (!ListenerUtil.mutListener.listen(34344)) {
                                                        // schedule a alarm!
                                                        logger.info("could not login to threema server, try again in {} milliseconds", (ListenerUtil.mutListener.listen(34343) ? (milliseconds % 2) : (ListenerUtil.mutListener.listen(34342) ? (milliseconds / 2) : (ListenerUtil.mutListener.listen(34341) ? (milliseconds - 2) : (ListenerUtil.mutListener.listen(34340) ? (milliseconds + 2) : (milliseconds * 2))))));
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(34345)) {
                                                        // cancel all other pending intents
                                                        cancelLoggedInConnection(context);
                                                    }
                                                    // set alarm
                                                    AlarmManager alarmManager = getAlarmManager(context);
                                                    Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
                                                    if (!ListenerUtil.mutListener.listen(34346)) {
                                                        intent.putExtra("requireLoggedInConnection", true);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(34351)) {
                                                        intent.putExtra("nextCheck", (ListenerUtil.mutListener.listen(34350) ? (milliseconds % 2) : (ListenerUtil.mutListener.listen(34349) ? (milliseconds / 2) : (ListenerUtil.mutListener.listen(34348) ? (milliseconds - 2) : (ListenerUtil.mutListener.listen(34347) ? (milliseconds + 2) : (milliseconds * 2))))));
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(34352)) {
                                                        requireLoggedInConnectionIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(34353)) {
                                                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + milliseconds, requireLoggedInConnectionIntent);
                                                    }
                                                }
                                            }
                                            return true;
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

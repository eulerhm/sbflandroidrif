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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.text.format.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Timer;
import java.util.TimerTask;
import ch.threema.app.BuildConfig;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.backuprestore.csv.BackupService;
import ch.threema.app.backuprestore.csv.RestoreService;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.receivers.FetchMessagesBroadcastReceiver;
import ch.threema.client.ConnectionState;
import ch.threema.client.QueueSendCompleteListener;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class to simplify polling (both time-based and GCM based) by handling all the gory details
 * of connecting for new messages and ensuring that the connection is released once the server
 * has sent all new messages.
 */
public class PollingHelper implements QueueSendCompleteListener {

    private static final Logger logger = LoggerFactory.getLogger(PollingHelper.class);

    private static final int CONNECTION_TIMEOUT = 1000 * 120;

    /* maximum time to stay connected for each poll (usually the connection will be terminated earlier as the server signals the end of the queue) */
    private static final int CONNECTION_TIMEOUT_ALREADY_CONNECTED = 1000 * 60;

    /* same, but timeout to use if we're already connected when polling */
    static final int CONNECTION_LINGER = 1000 * 5;

    private static volatile Timer timer;

    /* same timer for all instances */
    private static final Object timerLock = new Object();

    private final Context context;

    private final String name;

    private boolean connectionAcquired;

    private PowerManager.WakeLock wakeLock;

    private TimerTask timeoutTask;

    public PollingHelper(Context context, String name) {
        this.context = context;
        this.name = name;
    }

    /**
     *  Return whether polling was successful.
     */
    public synchronized boolean poll(final boolean useWakeLock) {
        if (!ListenerUtil.mutListener.listen(40316)) {
            logger.info("Fetch attempt. Source = {}", name);
        }
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (serviceManager == null) {
            return false;
        }
        // If the device is not online, there's no use in trying to connect.
        boolean isOnline = serviceManager.getDeviceService().isOnline();
        if (!isOnline) {
            if (!ListenerUtil.mutListener.listen(40317)) {
                logger.info("Not polling, device is offline");
            }
            return false;
        }
        if (!connectionAcquired) {
            // Check current backup state. If a backup or restore is running, don't poll.
            if (RestoreService.isRunning()) {
                return false;
            }
            if (BackupService.isRunning()) {
                return false;
            }
            if (!ListenerUtil.mutListener.listen(40327)) {
                // If requested, acquire a wakelock
                if (useWakeLock) {
                    if (!ListenerUtil.mutListener.listen(40319)) {
                        logger.info("Aquiring wakelock");
                    }
                    if (!ListenerUtil.mutListener.listen(40321)) {
                        if (wakeLock == null) {
                            PowerManager pm = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
                            if (!ListenerUtil.mutListener.listen(40320)) {
                                wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, BuildConfig.APPLICATION_ID + ":PollingHelper");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(40326)) {
                        wakeLock.acquire((ListenerUtil.mutListener.listen(40325) ? (DateUtils.MINUTE_IN_MILLIS % 10) : (ListenerUtil.mutListener.listen(40324) ? (DateUtils.MINUTE_IN_MILLIS / 10) : (ListenerUtil.mutListener.listen(40323) ? (DateUtils.MINUTE_IN_MILLIS - 10) : (ListenerUtil.mutListener.listen(40322) ? (DateUtils.MINUTE_IN_MILLIS + 10) : (DateUtils.MINUTE_IN_MILLIS * 10))))));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(40328)) {
                // We want to be notified when the server signals that the message queue was flushed completely
                serviceManager.getConnection().addQueueSendCompleteListener(this);
            }
            // Determine timeout duration. If we're already connected it can be shorter.
            long timeout = CONNECTION_TIMEOUT;
            if (!ListenerUtil.mutListener.listen(40331)) {
                if (serviceManager.getConnection().getConnectionState() == ConnectionState.LOGGEDIN) {
                    if (!ListenerUtil.mutListener.listen(40329)) {
                        logger.info("Already connected");
                    }
                    if (!ListenerUtil.mutListener.listen(40330)) {
                        timeout = CONNECTION_TIMEOUT_ALREADY_CONNECTED;
                    }
                }
            }
            // Acquire a connection to the Threema server
            LifetimeService lifetimeService = serviceManager.getLifetimeService();
            if (!ListenerUtil.mutListener.listen(40332)) {
                lifetimeService.acquireConnection(name);
            }
            if (!lifetimeService.isActive()) {
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, FetchMessagesBroadcastReceiver.class), 0);
                if (!ListenerUtil.mutListener.listen(40333)) {
                    // cancel pending alarms
                    alarmManager.cancel(pendingIntent);
                }
                if (!ListenerUtil.mutListener.listen(40334)) {
                    logger.info("Schedule another fetching attempt in two minutes");
                }
                if (!ListenerUtil.mutListener.listen(40350)) {
                    if ((ListenerUtil.mutListener.listen(40339) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(40338) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(40337) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(40336) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(40335) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(40349)) {
                            // try again in two minutes
                            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC, (ListenerUtil.mutListener.listen(40348) ? (System.currentTimeMillis() % timeout) : (ListenerUtil.mutListener.listen(40347) ? (System.currentTimeMillis() / timeout) : (ListenerUtil.mutListener.listen(40346) ? (System.currentTimeMillis() * timeout) : (ListenerUtil.mutListener.listen(40345) ? (System.currentTimeMillis() - timeout) : (System.currentTimeMillis() + timeout))))), pendingIntent);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(40344)) {
                            alarmManager.set(AlarmManager.RTC, (ListenerUtil.mutListener.listen(40343) ? (System.currentTimeMillis() % timeout) : (ListenerUtil.mutListener.listen(40342) ? (System.currentTimeMillis() / timeout) : (ListenerUtil.mutListener.listen(40341) ? (System.currentTimeMillis() * timeout) : (ListenerUtil.mutListener.listen(40340) ? (System.currentTimeMillis() - timeout) : (System.currentTimeMillis() + timeout))))), pendingIntent);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(40351)) {
                    // return false
                    connectionAcquired = false;
                }
                return false;
            }
            if (!ListenerUtil.mutListener.listen(40352)) {
                // Polling was successful
                connectionAcquired = true;
            }
            if (!ListenerUtil.mutListener.listen(40354)) {
                /* schedule a TimerTask so we will release this connection if it's taking too long to receive the queue completion message */
                if (timeoutTask != null)
                    if (!ListenerUtil.mutListener.listen(40353)) {
                        timeoutTask.cancel();
                    }
            }
            if (!ListenerUtil.mutListener.listen(40357)) {
                timeoutTask = new TimerTask() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(40355)) {
                            logger.warn("Timeout fetching message. Releasing connection");
                        }
                        if (!ListenerUtil.mutListener.listen(40356)) {
                            releaseConnection();
                        }
                    }
                };
            }
            if (!ListenerUtil.mutListener.listen(40358)) {
                this.schedule(timeoutTask, timeout);
            }
            return true;
        } else {
            if (!ListenerUtil.mutListener.listen(40318)) {
                logger.info("Fetch attempt. Connection already acquired.");
            }
            return true;
        }
    }

    private void schedule(TimerTask timerTask, long timeout) {
        if (!ListenerUtil.mutListener.listen(40361)) {
            if (timer == null) {
                synchronized (timerLock) {
                    if (!ListenerUtil.mutListener.listen(40360)) {
                        if (timer == null) {
                            if (!ListenerUtil.mutListener.listen(40359)) {
                                timer = new Timer("PollingHelper");
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(40362)) {
            timer.schedule(timerTask, timeout);
        }
    }

    public synchronized boolean poll() {
        return poll(true);
    }

    @Override
    public synchronized void queueSendComplete() {
        if (!ListenerUtil.mutListener.listen(40363)) {
            logger.info("Received queue send complete message from server");
        }
        if (!ListenerUtil.mutListener.listen(40364)) {
            releaseConnection();
        }
    }

    private synchronized void releaseConnection() {
        if (!ListenerUtil.mutListener.listen(40365)) {
            logger.debug("release connection");
        }
        if (!ListenerUtil.mutListener.listen(40378)) {
            if (connectionAcquired) {
                ServiceManager serviceManager = ThreemaApplication.getServiceManager();
                if (!ListenerUtil.mutListener.listen(40377)) {
                    if (serviceManager != null) {
                        if (!ListenerUtil.mutListener.listen(40367)) {
                            serviceManager.getConnection().removeQueueSendCompleteListener(this);
                        }
                        LifetimeService lifetimeService = serviceManager.getLifetimeService();
                        if (!ListenerUtil.mutListener.listen(40368)) {
                            lifetimeService.releaseConnectionLinger(name, CONNECTION_LINGER);
                        }
                        if (!ListenerUtil.mutListener.listen(40369)) {
                            connectionAcquired = false;
                        }
                        if (!ListenerUtil.mutListener.listen(40372)) {
                            if (timeoutTask != null) {
                                if (!ListenerUtil.mutListener.listen(40370)) {
                                    timeoutTask.cancel();
                                }
                                if (!ListenerUtil.mutListener.listen(40371)) {
                                    timeoutTask = null;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(40376)) {
                            if ((ListenerUtil.mutListener.listen(40373) ? (wakeLock != null || wakeLock.isHeld()) : (wakeLock != null && wakeLock.isHeld()))) {
                                if (!ListenerUtil.mutListener.listen(40374)) {
                                    logger.info("Releasing wakelock");
                                }
                                if (!ListenerUtil.mutListener.listen(40375)) {
                                    wakeLock.release();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(40366)) {
                            connectionAcquired = false;
                        }
                    }
                }
            }
        }
    }
}

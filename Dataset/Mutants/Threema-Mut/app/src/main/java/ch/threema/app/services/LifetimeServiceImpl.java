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
import android.os.SystemClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.backuprestore.csv.BackupService;
import ch.threema.app.backuprestore.csv.RestoreService;
import ch.threema.app.receivers.AlarmManagerBroadcastReceiver;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LifetimeServiceImpl implements LifetimeService {

    private static final Logger logger = LoggerFactory.getLogger(LifetimeServiceImpl.class);

    public static final String REQUEST_CODE_KEY = "requestCode";

    public static final int REQUEST_CODE_RELEASE = 1;

    public static final int REQUEST_CODE_RESEND = 2;

    public static final int REQUEST_CODE_POLL = 3;

    public static final int REQUEST_LOCK_APP = 4;

    private static final int MESSAGE_SEND_TIME = 30 * 1000;

    private static final int MESSAGE_RESEND_INTERVAL = 5 * 60 * 1000;

    private final Context context;

    private final AlarmManager alarmManager;

    private boolean active = false;

    private int refCount = 0;

    private long lingerUntil = 0;

    /* time (in SystemClock.elapsedRealtime()) until which the connection must stay active in any case */
    private long pollingInterval;

    private PollingHelper pollingHelper;

    private DownloadService downloadService;

    private final List<LifetimeServiceListener> listeners = new ArrayList<>();

    public LifetimeServiceImpl(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        try {
            if (!ListenerUtil.mutListener.listen(38668)) {
                this.downloadService = ThreemaApplication.getServiceManager().getDownloadService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(38667)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public synchronized void acquireConnection(String source) {
        if (!ListenerUtil.mutListener.listen(38669)) {
            refCount++;
        }
        if (!ListenerUtil.mutListener.listen(38670)) {
            logger.info("acquireConnection: source = {}, refCount = {}", source, refCount);
        }
        if (!ListenerUtil.mutListener.listen(38678)) {
            if (!active) {
                try {
                    if (!ListenerUtil.mutListener.listen(38673)) {
                        // do not start a connection if a restore is in progress
                        if (RestoreService.isRunning()) {
                            throw new Exception("restore in progress");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(38674)) {
                        if (BackupService.isRunning()) {
                            throw new Exception("backup in progress");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(38675)) {
                        ThreemaApplication.getServiceManager().startConnection();
                    }
                    if (!ListenerUtil.mutListener.listen(38676)) {
                        logger.debug("connection started");
                    }
                    if (!ListenerUtil.mutListener.listen(38677)) {
                        active = true;
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(38672)) {
                        logger.error("startConnection: failed or skipped", e);
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38671)) {
                    logger.info("another connection is already active");
                }
            }
        }
    }

    @Override
    public synchronized void releaseConnection(String source) {
        if (!ListenerUtil.mutListener.listen(38685)) {
            if ((ListenerUtil.mutListener.listen(38683) ? (refCount >= 0) : (ListenerUtil.mutListener.listen(38682) ? (refCount <= 0) : (ListenerUtil.mutListener.listen(38681) ? (refCount > 0) : (ListenerUtil.mutListener.listen(38680) ? (refCount < 0) : (ListenerUtil.mutListener.listen(38679) ? (refCount != 0) : (refCount == 0))))))) {
                if (!ListenerUtil.mutListener.listen(38684)) {
                    logger.debug("releaseConnection: refCount is already 0! (source = " + source + ")");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(38686)) {
            refCount--;
        }
        if (!ListenerUtil.mutListener.listen(38687)) {
            logger.info("releaseConnection: source = {}, refCount = {}", source, refCount);
        }
        if (!ListenerUtil.mutListener.listen(38688)) {
            cleanupConnection();
        }
    }

    @Override
    public synchronized void releaseConnectionLinger(String source, long timeoutMs) {
        if (!ListenerUtil.mutListener.listen(38695)) {
            if ((ListenerUtil.mutListener.listen(38693) ? (refCount >= 0) : (ListenerUtil.mutListener.listen(38692) ? (refCount <= 0) : (ListenerUtil.mutListener.listen(38691) ? (refCount > 0) : (ListenerUtil.mutListener.listen(38690) ? (refCount < 0) : (ListenerUtil.mutListener.listen(38689) ? (refCount != 0) : (refCount == 0))))))) {
                if (!ListenerUtil.mutListener.listen(38694)) {
                    logger.debug("releaseConnectionLinger: refCount is already 0! (source = " + source + ")");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(38696)) {
            refCount--;
        }
        if (!ListenerUtil.mutListener.listen(38697)) {
            logger.info("releaseConnectionLinger: source = {}, timeout = {}", source, timeoutMs);
        }
        long newLingerUntil = SystemClock.elapsedRealtime() + timeoutMs;
        if (!ListenerUtil.mutListener.listen(38706)) {
            if ((ListenerUtil.mutListener.listen(38702) ? (newLingerUntil >= lingerUntil) : (ListenerUtil.mutListener.listen(38701) ? (newLingerUntil <= lingerUntil) : (ListenerUtil.mutListener.listen(38700) ? (newLingerUntil < lingerUntil) : (ListenerUtil.mutListener.listen(38699) ? (newLingerUntil != lingerUntil) : (ListenerUtil.mutListener.listen(38698) ? (newLingerUntil == lingerUntil) : (newLingerUntil > lingerUntil))))))) {
                if (!ListenerUtil.mutListener.listen(38703)) {
                    /* must re-schedule alarm */
                    lingerUntil = newLingerUntil;
                }
                if (!ListenerUtil.mutListener.listen(38704)) {
                    cancelAlarm(REQUEST_CODE_RELEASE);
                }
                if (!ListenerUtil.mutListener.listen(38705)) {
                    scheduleAlarm(REQUEST_CODE_RELEASE, lingerUntil, false);
                }
            }
        }
    }

    @Override
    public void setPollingInterval(long intervalMs) {
        if (!ListenerUtil.mutListener.listen(38721)) {
            // "Your alarm's first trigger will not be before the requested time, but it might not occur for almost a full interval after that time"
            if ((ListenerUtil.mutListener.listen(38711) ? (intervalMs >= 0) : (ListenerUtil.mutListener.listen(38710) ? (intervalMs <= 0) : (ListenerUtil.mutListener.listen(38709) ? (intervalMs > 0) : (ListenerUtil.mutListener.listen(38708) ? (intervalMs < 0) : (ListenerUtil.mutListener.listen(38707) ? (intervalMs != 0) : (intervalMs == 0))))))) {
                if (!ListenerUtil.mutListener.listen(38720)) {
                    intervalMs = (ListenerUtil.mutListener.listen(38719) ? ((ListenerUtil.mutListener.listen(38715) ? (intervalMs % 2) : (ListenerUtil.mutListener.listen(38714) ? (intervalMs / 2) : (ListenerUtil.mutListener.listen(38713) ? (intervalMs - 2) : (ListenerUtil.mutListener.listen(38712) ? (intervalMs + 2) : (intervalMs * 2))))) % 3) : (ListenerUtil.mutListener.listen(38718) ? ((ListenerUtil.mutListener.listen(38715) ? (intervalMs % 2) : (ListenerUtil.mutListener.listen(38714) ? (intervalMs / 2) : (ListenerUtil.mutListener.listen(38713) ? (intervalMs - 2) : (ListenerUtil.mutListener.listen(38712) ? (intervalMs + 2) : (intervalMs * 2))))) * 3) : (ListenerUtil.mutListener.listen(38717) ? ((ListenerUtil.mutListener.listen(38715) ? (intervalMs % 2) : (ListenerUtil.mutListener.listen(38714) ? (intervalMs / 2) : (ListenerUtil.mutListener.listen(38713) ? (intervalMs - 2) : (ListenerUtil.mutListener.listen(38712) ? (intervalMs + 2) : (intervalMs * 2))))) - 3) : (ListenerUtil.mutListener.listen(38716) ? ((ListenerUtil.mutListener.listen(38715) ? (intervalMs % 2) : (ListenerUtil.mutListener.listen(38714) ? (intervalMs / 2) : (ListenerUtil.mutListener.listen(38713) ? (intervalMs - 2) : (ListenerUtil.mutListener.listen(38712) ? (intervalMs + 2) : (intervalMs * 2))))) + 3) : ((ListenerUtil.mutListener.listen(38715) ? (intervalMs % 2) : (ListenerUtil.mutListener.listen(38714) ? (intervalMs / 2) : (ListenerUtil.mutListener.listen(38713) ? (intervalMs - 2) : (ListenerUtil.mutListener.listen(38712) ? (intervalMs + 2) : (intervalMs * 2))))) / 3)))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38727)) {
            if ((ListenerUtil.mutListener.listen(38726) ? (pollingInterval >= intervalMs) : (ListenerUtil.mutListener.listen(38725) ? (pollingInterval <= intervalMs) : (ListenerUtil.mutListener.listen(38724) ? (pollingInterval > intervalMs) : (ListenerUtil.mutListener.listen(38723) ? (pollingInterval < intervalMs) : (ListenerUtil.mutListener.listen(38722) ? (pollingInterval != intervalMs) : (pollingInterval == intervalMs)))))))
                return;
        }
        if (!ListenerUtil.mutListener.listen(38728)) {
            pollingInterval = intervalMs;
        }
        if (!ListenerUtil.mutListener.listen(38738)) {
            if ((ListenerUtil.mutListener.listen(38733) ? (pollingInterval >= 0) : (ListenerUtil.mutListener.listen(38732) ? (pollingInterval <= 0) : (ListenerUtil.mutListener.listen(38731) ? (pollingInterval > 0) : (ListenerUtil.mutListener.listen(38730) ? (pollingInterval < 0) : (ListenerUtil.mutListener.listen(38729) ? (pollingInterval != 0) : (pollingInterval == 0))))))) {
                if (!ListenerUtil.mutListener.listen(38736)) {
                    /* polling now disabled - cancel alarm */
                    cancelAlarm(REQUEST_CODE_POLL);
                }
                if (!ListenerUtil.mutListener.listen(38737)) {
                    logger.info("Polling disabled");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38734)) {
                    /* polling now enabled - (re)start alarm */
                    scheduleRepeatingAlarm(REQUEST_CODE_POLL, SystemClock.elapsedRealtime() + pollingInterval, pollingInterval);
                }
                if (!ListenerUtil.mutListener.listen(38735)) {
                    logger.info("Polling enabled. Interval: {}", pollingInterval);
                }
            }
        }
    }

    public synchronized void alarm(Intent intent) {
        int requestCode = intent.getIntExtra("requestCode", 0);
        long time = System.currentTimeMillis();
        if (!ListenerUtil.mutListener.listen(38739)) {
            logger.info("Alarm type " + requestCode + " (handling) START");
        }
        if (!ListenerUtil.mutListener.listen(38748)) {
            switch(requestCode) {
                case REQUEST_CODE_RELEASE:
                    if (!ListenerUtil.mutListener.listen(38740)) {
                        cleanupConnection();
                    }
                    break;
                case REQUEST_CODE_RESEND:
                    if (!ListenerUtil.mutListener.listen(38741)) {
                        /* resend attempt - acquire connection for a moment */
                        acquireConnection("resend_alarm");
                    }
                    if (!ListenerUtil.mutListener.listen(38742)) {
                        releaseConnectionLinger("resend_alarm", MESSAGE_SEND_TIME);
                    }
                    break;
                case REQUEST_CODE_POLL:
                    if (!ListenerUtil.mutListener.listen(38744)) {
                        if (pollingHelper == null)
                            if (!ListenerUtil.mutListener.listen(38743)) {
                                pollingHelper = new PollingHelper(context, "alarm");
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(38746)) {
                        if (pollingHelper.poll()) {
                            if (!ListenerUtil.mutListener.listen(38745)) {
                                updateLastPollTimestamp();
                            }
                        }
                    }
                    break;
                case REQUEST_LOCK_APP:
                    if (!ListenerUtil.mutListener.listen(38747)) {
                        lockApp();
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(38753)) {
            logger.info("Alarm type " + requestCode + " (handling) DONE. Duration = " + ((ListenerUtil.mutListener.listen(38752) ? (System.currentTimeMillis() % time) : (ListenerUtil.mutListener.listen(38751) ? (System.currentTimeMillis() / time) : (ListenerUtil.mutListener.listen(38750) ? (System.currentTimeMillis() * time) : (ListenerUtil.mutListener.listen(38749) ? (System.currentTimeMillis() + time) : (System.currentTimeMillis() - time)))))) + "ms");
        }
    }

    @Override
    public synchronized boolean isActive() {
        return active;
    }

    @Override
    public void addListener(LifetimeServiceListener listener) {
        synchronized (this.listeners) {
            if (!ListenerUtil.mutListener.listen(38755)) {
                if (!this.listeners.contains(listener)) {
                    if (!ListenerUtil.mutListener.listen(38754)) {
                        this.listeners.add(listener);
                    }
                }
            }
        }
    }

    @Override
    public void removeListener(LifetimeServiceListener listener) {
        synchronized (this.listeners) {
            if (!ListenerUtil.mutListener.listen(38756)) {
                this.listeners.remove(listener);
            }
        }
    }

    @Override
    public void clearListeners() {
        synchronized (this.listeners) {
            if (!ListenerUtil.mutListener.listen(38757)) {
                this.listeners.clear();
            }
        }
    }

    private void cleanupConnection() {
        boolean interrupted = false;
        if (!ListenerUtil.mutListener.listen(38798)) {
            if ((ListenerUtil.mutListener.listen(38762) ? (refCount >= 0) : (ListenerUtil.mutListener.listen(38761) ? (refCount <= 0) : (ListenerUtil.mutListener.listen(38760) ? (refCount > 0) : (ListenerUtil.mutListener.listen(38759) ? (refCount < 0) : (ListenerUtil.mutListener.listen(38758) ? (refCount != 0) : (refCount == 0))))))) {
                long curTime = SystemClock.elapsedRealtime();
                if (!ListenerUtil.mutListener.listen(38797)) {
                    if (!active) {
                        if (!ListenerUtil.mutListener.listen(38796)) {
                            logger.info("cleanupConnection: connection not active");
                        }
                    } else if ((ListenerUtil.mutListener.listen(38769) ? ((ListenerUtil.mutListener.listen(38768) ? (lingerUntil >= curTime) : (ListenerUtil.mutListener.listen(38767) ? (lingerUntil <= curTime) : (ListenerUtil.mutListener.listen(38766) ? (lingerUntil < curTime) : (ListenerUtil.mutListener.listen(38765) ? (lingerUntil != curTime) : (ListenerUtil.mutListener.listen(38764) ? (lingerUntil == curTime) : (lingerUntil > curTime)))))) || !ThreemaApplication.isIsDeviceIdle()) : ((ListenerUtil.mutListener.listen(38768) ? (lingerUntil >= curTime) : (ListenerUtil.mutListener.listen(38767) ? (lingerUntil <= curTime) : (ListenerUtil.mutListener.listen(38766) ? (lingerUntil < curTime) : (ListenerUtil.mutListener.listen(38765) ? (lingerUntil != curTime) : (ListenerUtil.mutListener.listen(38764) ? (lingerUntil == curTime) : (lingerUntil > curTime)))))) && !ThreemaApplication.isIsDeviceIdle()))) {
                        if (!ListenerUtil.mutListener.listen(38795)) {
                            logger.info("cleanupConnection: connection must linger for another " + ((ListenerUtil.mutListener.listen(38794) ? (lingerUntil % curTime) : (ListenerUtil.mutListener.listen(38793) ? (lingerUntil / curTime) : (ListenerUtil.mutListener.listen(38792) ? (lingerUntil * curTime) : (ListenerUtil.mutListener.listen(38791) ? (lingerUntil + curTime) : (lingerUntil - curTime)))))) + " milliseconds");
                        }
                    } else if ((ListenerUtil.mutListener.listen(38770) ? (downloadService != null || downloadService.isDownloading()) : (downloadService != null && downloadService.isDownloading()))) {
                        if (!ListenerUtil.mutListener.listen(38788)) {
                            logger.info("cleanupConnection: still downloading - linger on");
                        }
                        if (!ListenerUtil.mutListener.listen(38789)) {
                            cancelAlarm(REQUEST_CODE_RELEASE);
                        }
                        if (!ListenerUtil.mutListener.listen(38790)) {
                            releaseConnectionLinger("ongoing_download", MESSAGE_SEND_TIME);
                        }
                    } else {
                        try {
                            if (!ListenerUtil.mutListener.listen(38773)) {
                                ThreemaApplication.getServiceManager().stopConnection();
                            }
                        } catch (InterruptedException e) {
                            if (!ListenerUtil.mutListener.listen(38771)) {
                                logger.error("Interrupted while stopping connection");
                            }
                            if (!ListenerUtil.mutListener.listen(38772)) {
                                interrupted = true;
                            }
                        }
                        synchronized (this.listeners) {
                            Iterator<LifetimeServiceListener> listIterator = this.listeners.iterator();
                            if (!ListenerUtil.mutListener.listen(38776)) {
                                {
                                    long _loopCounter427 = 0;
                                    while (listIterator.hasNext()) {
                                        ListenerUtil.loopListener.listen("_loopCounter427", ++_loopCounter427);
                                        LifetimeServiceListener l = listIterator.next();
                                        if (!ListenerUtil.mutListener.listen(38775)) {
                                            if (l.connectionStopped()) {
                                                if (!ListenerUtil.mutListener.listen(38774)) {
                                                    listIterator.remove();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(38777)) {
                            active = false;
                        }
                        if (!ListenerUtil.mutListener.listen(38778)) {
                            logger.info("cleanupConnection: connection closed");
                        }
                        /* check if any messages remain in the queue */
                        try {
                            int queueSize = ThreemaApplication.getServiceManager().getMessageQueue().getQueueSize();
                            if (!ListenerUtil.mutListener.listen(38787)) {
                                if ((ListenerUtil.mutListener.listen(38784) ? (queueSize >= 0) : (ListenerUtil.mutListener.listen(38783) ? (queueSize <= 0) : (ListenerUtil.mutListener.listen(38782) ? (queueSize < 0) : (ListenerUtil.mutListener.listen(38781) ? (queueSize != 0) : (ListenerUtil.mutListener.listen(38780) ? (queueSize == 0) : (queueSize > 0))))))) {
                                    long resendTime = SystemClock.elapsedRealtime() + MESSAGE_RESEND_INTERVAL;
                                    if (!ListenerUtil.mutListener.listen(38785)) {
                                        logger.info(queueSize + " messages remaining in queue; scheduling resend at " + new Date(resendTime).toString());
                                    }
                                    if (!ListenerUtil.mutListener.listen(38786)) {
                                        scheduleAlarm(REQUEST_CODE_RESEND, resendTime, false);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(38779)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38763)) {
                    logger.info("cleanupConnection: refCount = {} - not cleaning up", refCount);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38800)) {
            if (interrupted) {
                if (!ListenerUtil.mutListener.listen(38799)) {
                    // Re-set interrupted flag
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void scheduleAlarm(int requestCode, long triggerAtMillis, boolean whenIdle) {
        long curTime = SystemClock.elapsedRealtime();
        if (!ListenerUtil.mutListener.listen(38805)) {
            logger.info("Alarm type " + requestCode + " schedule in " + ((ListenerUtil.mutListener.listen(38804) ? (triggerAtMillis % curTime) : (ListenerUtil.mutListener.listen(38803) ? (triggerAtMillis / curTime) : (ListenerUtil.mutListener.listen(38802) ? (triggerAtMillis * curTime) : (ListenerUtil.mutListener.listen(38801) ? (triggerAtMillis + curTime) : (triggerAtMillis - curTime)))))) + "ms");
        }
        try {
            if (!ListenerUtil.mutListener.listen(38807)) {
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis, makePendingIntentForRequestCode(requestCode));
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(38806)) {
                // KD Interactive C15100m (Pixi3-7_KD), 1024MB RAM, Android 5.0 throws SecurityException here
                logger.error("Exception", e);
            }
        }
    }

    private void scheduleRepeatingAlarm(int requestCode, long triggerAtMillis, long intervalMillis) {
        if (!ListenerUtil.mutListener.listen(38808)) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis, intervalMillis, makePendingIntentForRequestCode(requestCode));
        }
    }

    private void cancelAlarm(int requestCode) {
        if (!ListenerUtil.mutListener.listen(38809)) {
            logger.info("Alarm type " + requestCode + " cancel");
        }
        if (!ListenerUtil.mutListener.listen(38810)) {
            alarmManager.cancel(makePendingIntentForRequestCode(requestCode));
        }
    }

    private PendingIntent makePendingIntentForRequestCode(int requestCode) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        if (!ListenerUtil.mutListener.listen(38811)) {
            intent.putExtra("requestCode", requestCode);
        }
        return PendingIntent.getBroadcast(context, requestCode, intent, 0);
    }

    /**
     *  We want to know when the last successful polling happened. Store it in the preferences.
     */
    private void updateLastPollTimestamp() {
        try {
            final PreferenceService preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
            if (!ListenerUtil.mutListener.listen(38814)) {
                if (preferenceService != null) {
                    long timestamp = System.currentTimeMillis();
                    if (!ListenerUtil.mutListener.listen(38812)) {
                        preferenceService.setLastSuccessfulPollTimestamp(timestamp);
                    }
                    if (!ListenerUtil.mutListener.listen(38813)) {
                        logger.debug("Updated last poll timestamp");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void lockApp() {
        try {
            final LockAppService lockAppService = ThreemaApplication.getServiceManager().getLockAppService();
            if (!ListenerUtil.mutListener.listen(38817)) {
                if (lockAppService != null) {
                    if (!ListenerUtil.mutListener.listen(38816)) {
                        lockAppService.lock();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(38815)) {
                logger.warn("Exception: Could not lock app ", e);
            }
        }
    }
}

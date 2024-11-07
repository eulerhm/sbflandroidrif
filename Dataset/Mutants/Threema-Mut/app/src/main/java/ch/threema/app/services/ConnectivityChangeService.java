/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.core.app.FixedJobIntentService;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.threemasafe.ThreemaSafeService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectivityChangeService extends FixedJobIntentService {

    private static final Logger logger = LoggerFactory.getLogger(ConnectivityChangeService.class);

    private static final int MESSAGE_SEND_TIME = 30 * 1000;

    private static final int JOB_ID = 2001;

    public static void enqueueWork(Context context, Intent work) {
        if (!ListenerUtil.mutListener.listen(36796)) {
            if (work != null) {
                if (!ListenerUtil.mutListener.listen(36795)) {
                    enqueueWork(context, ConnectivityChangeService.class, JOB_ID, work);
                }
            }
        }
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        boolean wasOnline = false;
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(36840)) {
            if (serviceManager != null) {
                PreferenceService preferenceService = serviceManager.getPreferenceService();
                boolean online = serviceManager.getDeviceService().isOnline();
                if (!ListenerUtil.mutListener.listen(36799)) {
                    if (preferenceService != null) {
                        if (!ListenerUtil.mutListener.listen(36797)) {
                            wasOnline = preferenceService.getLastOnlineStatus();
                        }
                        if (!ListenerUtil.mutListener.listen(36798)) {
                            preferenceService.setLastOnlineStatus(online);
                        }
                    }
                }
                Bundle extras = intent.getExtras();
                if (!ListenerUtil.mutListener.listen(36802)) {
                    if (extras != null) {
                        NetworkInfo networkInfo = (NetworkInfo) extras.get(ConnectivityManager.EXTRA_NETWORK_INFO);
                        if (!ListenerUtil.mutListener.listen(36801)) {
                            if (networkInfo != null) {
                                if (!ListenerUtil.mutListener.listen(36800)) {
                                    logger.info(networkInfo.toString());
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36819)) {
                    if ((ListenerUtil.mutListener.listen(36803) ? (online || !wasOnline) : (online && !wasOnline))) {
                        if (!ListenerUtil.mutListener.listen(36818)) {
                            // The device changed from OFFLINE to ONLINE.
                            if ((ListenerUtil.mutListener.listen(36804) ? (preferenceService != null || preferenceService.isPolling()) : (preferenceService != null && preferenceService.isPolling()))) {
                                // polling interval.
                                final Long prev = preferenceService.getLastSuccessfulPollTimestamp();
                                if (!ListenerUtil.mutListener.listen(36817)) {
                                    if (prev != null) {
                                        final long msAgo = (ListenerUtil.mutListener.listen(36808) ? (System.currentTimeMillis() % prev) : (ListenerUtil.mutListener.listen(36807) ? (System.currentTimeMillis() / prev) : (ListenerUtil.mutListener.listen(36806) ? (System.currentTimeMillis() * prev) : (ListenerUtil.mutListener.listen(36805) ? (System.currentTimeMillis() + prev) : (System.currentTimeMillis() - prev)))));
                                        if (!ListenerUtil.mutListener.listen(36816)) {
                                            if ((ListenerUtil.mutListener.listen(36813) ? (msAgo >= preferenceService.getPollingInterval()) : (ListenerUtil.mutListener.listen(36812) ? (msAgo <= preferenceService.getPollingInterval()) : (ListenerUtil.mutListener.listen(36811) ? (msAgo < preferenceService.getPollingInterval()) : (ListenerUtil.mutListener.listen(36810) ? (msAgo != preferenceService.getPollingInterval()) : (ListenerUtil.mutListener.listen(36809) ? (msAgo == preferenceService.getPollingInterval()) : (msAgo > preferenceService.getPollingInterval()))))))) {
                                                // Poll immediately
                                                PollingHelper pollingHelper = new PollingHelper(this, "connectivityChange");
                                                if (!ListenerUtil.mutListener.listen(36815)) {
                                                    if (pollingHelper.poll()) {
                                                        if (!ListenerUtil.mutListener.listen(36814)) {
                                                            preferenceService.setLastSuccessfulPollTimestamp(System.currentTimeMillis());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36839)) {
                    if (online != wasOnline) {
                        if (!ListenerUtil.mutListener.listen(36820)) {
                            logger.info("Device is now {}", online ? "ONLINE" : "OFFLINE");
                        }
                        /* if there are pending messages in the queue, go online for a moment to send them */
                        try {
                            if (!ListenerUtil.mutListener.listen(36830)) {
                                if ((ListenerUtil.mutListener.listen(36826) ? (serviceManager.getMessageQueue().getQueueSize() >= 0) : (ListenerUtil.mutListener.listen(36825) ? (serviceManager.getMessageQueue().getQueueSize() <= 0) : (ListenerUtil.mutListener.listen(36824) ? (serviceManager.getMessageQueue().getQueueSize() < 0) : (ListenerUtil.mutListener.listen(36823) ? (serviceManager.getMessageQueue().getQueueSize() != 0) : (ListenerUtil.mutListener.listen(36822) ? (serviceManager.getMessageQueue().getQueueSize() == 0) : (serviceManager.getMessageQueue().getQueueSize() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(36827)) {
                                        logger.info("Messages in queue; acquiring connection");
                                    }
                                    if (!ListenerUtil.mutListener.listen(36828)) {
                                        serviceManager.getLifetimeService().acquireConnection("connectivity_change");
                                    }
                                    if (!ListenerUtil.mutListener.listen(36829)) {
                                        serviceManager.getLifetimeService().releaseConnectionLinger("connectivity_change", MESSAGE_SEND_TIME);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(36838)) {
                                /* if no backup was created in due time, do it now. The JobScheduler will handle connectivity changes in Lollipop+  */
                                if ((ListenerUtil.mutListener.listen(36835) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36834) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36833) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36832) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36831) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                                    ThreemaSafeService threemaSafeService = serviceManager.getThreemaSafeService();
                                    if (!ListenerUtil.mutListener.listen(36837)) {
                                        if (threemaSafeService.isUploadDue()) {
                                            if (!ListenerUtil.mutListener.listen(36836)) {
                                                threemaSafeService.uploadNow(getApplicationContext(), false);
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(36821)) {
                                logger.error("Error", e);
                            }
                        }
                    }
                }
            }
        }
    }
}

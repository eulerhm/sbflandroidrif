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
package ch.threema.app.webclient.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.utils.BatteryStatusUtil;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.storage.models.WebClientSessionModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handling the WebClient battery status subscription.
 *
 * On change, the BatteryStatusListeners will be notified.
 */
@WorkerThread
public class BatteryStatusServiceImpl implements BatteryStatusService {

    private static final Logger logger = LoggerFactory.getLogger(BatteryStatusServiceImpl.class);

    // State
    @NonNull
    private final Context appContext;

    @NonNull
    private final List<Integer> acquiredSessionIds = new ArrayList<>();

    private boolean subscribed = false;

    /**
     *  Battery status broadcast receiver.
     */
    @NonNull
    private final BroadcastReceiver batteryStatusReceiver = new BroadcastReceiver() {

        // Battery info
        private int batteryPercent = -1;

        @Nullable
        private Boolean isCharging = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            // Process data
            final Boolean charging = BatteryStatusUtil.isCharging(intent);
            final Integer percent = BatteryStatusUtil.getPercent(intent);
            if (!ListenerUtil.mutListener.listen(64349)) {
                if ((ListenerUtil.mutListener.listen(64348) ? (charging == null && percent == null) : (charging == null || percent == null))) {
                    return;
                }
            }
            // Determine whether there was a relative change
            final boolean percentChanged = (percent != this.batteryPercent);
            final boolean isChargingChanged = (charging != this.isCharging);
            if (!ListenerUtil.mutListener.listen(64354)) {
                // If it is, notify listeners
                if ((ListenerUtil.mutListener.listen(64350) ? (percentChanged && isChargingChanged) : (percentChanged || isChargingChanged))) {
                    if (!ListenerUtil.mutListener.listen(64351)) {
                        WebClientListenerManager.batteryStatusListener.handle(listener -> listener.onChange(percent, charging));
                    }
                    if (!ListenerUtil.mutListener.listen(64352)) {
                        this.batteryPercent = percent;
                    }
                    if (!ListenerUtil.mutListener.listen(64353)) {
                        this.isCharging = charging;
                    }
                }
            }
        }
    };

    @AnyThread
    public BatteryStatusServiceImpl(@NonNull Context appContext) {
        this.appContext = appContext;
    }

    /**
     *  Subscribe to the battery status broadcast.
     */
    public void acquire(WebClientSessionModel session) {
        if (!ListenerUtil.mutListener.listen(64355)) {
            logger.debug("Acquire webclient battery status subscription for session " + session.getId());
        }
        if (!ListenerUtil.mutListener.listen(64357)) {
            if (!this.acquiredSessionIds.contains(session.getId())) {
                if (!ListenerUtil.mutListener.listen(64356)) {
                    this.acquiredSessionIds.add(session.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64358)) {
            this.execute();
        }
    }

    /**
     *  Unsubscribe from the battery status broadcast.
     */
    public void release(WebClientSessionModel session) {
        if (!ListenerUtil.mutListener.listen(64359)) {
            logger.debug("Release webclient battery status subscription for session " + session.getId());
        }
        if (!ListenerUtil.mutListener.listen(64361)) {
            if (this.acquiredSessionIds.contains(session.getId())) {
                if (!ListenerUtil.mutListener.listen(64360)) {
                    this.acquiredSessionIds.remove((Integer) session.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64362)) {
            this.execute();
        }
    }

    private void execute() {
        if (!ListenerUtil.mutListener.listen(64378)) {
            if ((ListenerUtil.mutListener.listen(64367) ? (this.acquiredSessionIds.size() >= 0) : (ListenerUtil.mutListener.listen(64366) ? (this.acquiredSessionIds.size() <= 0) : (ListenerUtil.mutListener.listen(64365) ? (this.acquiredSessionIds.size() < 0) : (ListenerUtil.mutListener.listen(64364) ? (this.acquiredSessionIds.size() != 0) : (ListenerUtil.mutListener.listen(64363) ? (this.acquiredSessionIds.size() == 0) : (this.acquiredSessionIds.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(64377)) {
                    if (this.subscribed) {
                        if (!ListenerUtil.mutListener.listen(64376)) {
                            logger.debug("Already subscribed");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(64373)) {
                            this.appContext.registerReceiver(this.batteryStatusReceiver, getBatteryStatusIntentFilter());
                        }
                        if (!ListenerUtil.mutListener.listen(64374)) {
                            this.subscribed = true;
                        }
                        if (!ListenerUtil.mutListener.listen(64375)) {
                            logger.debug("Subscribed");
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(64372)) {
                    if (this.subscribed) {
                        if (!ListenerUtil.mutListener.listen(64369)) {
                            this.appContext.unregisterReceiver(this.batteryStatusReceiver);
                        }
                        if (!ListenerUtil.mutListener.listen(64370)) {
                            this.subscribed = false;
                        }
                        if (!ListenerUtil.mutListener.listen(64371)) {
                            logger.debug("Unsubscribed");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(64368)) {
                            logger.debug("Already unsubscribed");
                        }
                    }
                }
            }
        }
    }

    /**
     *  Return the intent filter for subscribing to battery changes.
     */
    public static IntentFilter getBatteryStatusIntentFilter() {
        final IntentFilter batteryStatusFilter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(64379)) {
            batteryStatusFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(64380)) {
            batteryStatusFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        }
        if (!ListenerUtil.mutListener.listen(64381)) {
            batteryStatusFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        }
        if (!ListenerUtil.mutListener.listen(64382)) {
            batteryStatusFilter.addAction(Intent.ACTION_BATTERY_LOW);
        }
        if (!ListenerUtil.mutListener.listen(64383)) {
            batteryStatusFilter.addAction(Intent.ACTION_BATTERY_OKAY);
        }
        return batteryStatusFilter;
    }
}

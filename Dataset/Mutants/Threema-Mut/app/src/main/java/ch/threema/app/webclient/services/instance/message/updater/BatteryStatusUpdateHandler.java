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
package ch.threema.app.webclient.services.instance.message.updater;

import android.content.Context;
import android.content.Intent;
import org.msgpack.core.MessagePackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import ch.threema.app.utils.BatteryStatusUtil;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.BatteryStatus;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.listeners.BatteryStatusListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.services.BatteryStatusServiceImpl;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageUpdater;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Subscribe to BatteryStatusListener notifications. Send them to Threema Web as update messages.
 */
@WorkerThread
public class BatteryStatusUpdateHandler extends MessageUpdater {

    private static final Logger logger = LoggerFactory.getLogger(BatteryStatusUpdateHandler.class);

    // Handler
    @NonNull
    private final HandlerExecutor handler;

    // Listeners
    private final Listener listener = new Listener();

    // Dispatchers
    private MessageDispatcher dispatcher;

    private final Context appContext;

    private final int sessionId;

    @AnyThread
    public BatteryStatusUpdateHandler(@NonNull Context appContext, @NonNull HandlerExecutor handler, int sessionId, MessageDispatcher dispatcher) {
        super(Protocol.SUB_TYPE_BATTERY_STATUS);
        this.appContext = appContext;
        this.handler = handler;
        if (!ListenerUtil.mutListener.listen(63697)) {
            this.dispatcher = dispatcher;
        }
        this.sessionId = sessionId;
    }

    @Override
    public void register() {
        if (!ListenerUtil.mutListener.listen(63698)) {
            logger.debug("register(" + this.sessionId + ")");
        }
        if (!ListenerUtil.mutListener.listen(63699)) {
            WebClientListenerManager.batteryStatusListener.add(this.listener);
        }
    }

    /**
     *  This method can be safely called multiple times without any negative side effects
     */
    @Override
    public void unregister() {
        if (!ListenerUtil.mutListener.listen(63700)) {
            logger.debug("unregister(" + this.sessionId + ")");
        }
        if (!ListenerUtil.mutListener.listen(63701)) {
            WebClientListenerManager.batteryStatusListener.remove(this.listener);
        }
    }

    public void update(final int percent, final boolean isCharging) {
        try {
            MsgpackObjectBuilder data = BatteryStatus.convert(percent, isCharging);
            if (!ListenerUtil.mutListener.listen(63703)) {
                logger.debug("Sending battery status update ({}%, {})", percent, isCharging ? "C" : "D");
            }
            if (!ListenerUtil.mutListener.listen(63704)) {
                send(this.dispatcher, data, null);
            }
        } catch (MessagePackException e) {
            if (!ListenerUtil.mutListener.listen(63702)) {
                logger.error("Exception", e);
            }
        }
    }

    /**
     *  Trigger a single battery status measurement and send the results to Threema Web.
     */
    public void trigger() {
        // Get current battery status
        final Intent intent = this.appContext.registerReceiver(null, BatteryStatusServiceImpl.getBatteryStatusIntentFilter());
        if (!ListenerUtil.mutListener.listen(63705)) {
            if (intent == null) {
                return;
            }
        }
        // Parse battery status intent
        final Boolean isCharging = BatteryStatusUtil.isCharging(intent);
        final Integer percent = BatteryStatusUtil.getPercent(intent);
        if (!ListenerUtil.mutListener.listen(63707)) {
            if ((ListenerUtil.mutListener.listen(63706) ? (isCharging == null && percent == null) : (isCharging == null || percent == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(63708)) {
            // Send update to Threema Web
            this.update(percent, isCharging);
        }
    }

    @AnyThread
    private class Listener implements BatteryStatusListener {

        @Override
        public void onChange(int percent, boolean isCharging) {
            if (!ListenerUtil.mutListener.listen(63710)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63709)) {
                            BatteryStatusUpdateHandler.this.update(percent, isCharging);
                        }
                    }
                });
            }
        }
    }
}

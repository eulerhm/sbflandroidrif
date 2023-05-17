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

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import org.msgpack.core.MessagePackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import androidx.annotation.WorkerThread;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.voip.listeners.VoipCallEventListener;
import ch.threema.app.voip.managers.VoipListenerManager;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.converter.VoipStatus;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageUpdater;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Subscribe to Voip Status listener. Send them to Threema Web as update messages.
 */
@WorkerThread
public class VoipStatusUpdateHandler extends MessageUpdater {

    private static final Logger logger = LoggerFactory.getLogger(VoipStatusUpdateHandler.class);

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ TYPE_RINGING, TYPE_STARTED, TYPE_FINISHED, TYPE_REJECTED, TYPE_MISSED, TYPE_ABORTED })
    private @interface StatusType {
    }

    private static final String TYPE_RINGING = "ringing";

    private static final String TYPE_STARTED = "started";

    private static final String TYPE_FINISHED = "finished";

    private static final String TYPE_REJECTED = "rejected";

    private static final String TYPE_MISSED = "missed";

    private static final String TYPE_ABORTED = "aborted";

    // Handler
    @NonNull
    private final HandlerExecutor handler;

    // Listeners
    private final Listener listener = new Listener();

    // Dispatchers
    private MessageDispatcher dispatcher;

    // Local variables
    private final int sessionId;

    @AnyThread
    public VoipStatusUpdateHandler(@NonNull HandlerExecutor handler, int sessionId, MessageDispatcher dispatcher) {
        super(Protocol.SUB_TYPE_VOIP_STATUS);
        this.handler = handler;
        this.sessionId = sessionId;
        if (!ListenerUtil.mutListener.listen(63864)) {
            this.dispatcher = dispatcher;
        }
    }

    @Override
    public void register() {
        if (!ListenerUtil.mutListener.listen(63865)) {
            logger.debug("register(" + this.sessionId + ")");
        }
        if (!ListenerUtil.mutListener.listen(63866)) {
            VoipListenerManager.callEventListener.add(this.listener);
        }
    }

    /**
     *  This method can be safely called multiple times without any negative side effects
     */
    @Override
    public void unregister() {
        if (!ListenerUtil.mutListener.listen(63867)) {
            logger.debug("unregister(" + this.sessionId + ")");
        }
        if (!ListenerUtil.mutListener.listen(63868)) {
            VoipListenerManager.callEventListener.remove(this.listener);
        }
    }

    private void update(final MsgpackObjectBuilder data, @StatusType String type) {
        try {
            if (!ListenerUtil.mutListener.listen(63870)) {
                logger.info("Sending voip status update (" + type + ")");
            }
            final MsgpackObjectBuilder args = new MsgpackObjectBuilder().put("type", type);
            if (!ListenerUtil.mutListener.listen(63871)) {
                send(dispatcher, data, args);
            }
        } catch (MessagePackException e) {
            if (!ListenerUtil.mutListener.listen(63869)) {
                logger.error("Exception", e);
            }
        }
    }

    @AnyThread
    private class Listener implements VoipCallEventListener {

        @Override
        public void onRinging(String peerIdentity) {
            if (!ListenerUtil.mutListener.listen(63872)) {
                this.update(VoipStatus.convertOnRinging(peerIdentity), TYPE_RINGING);
            }
        }

        @Override
        public void onStarted(String peerIdentity, boolean outgoing) {
            if (!ListenerUtil.mutListener.listen(63873)) {
                this.update(VoipStatus.convertOnStarted(peerIdentity, outgoing), TYPE_STARTED);
            }
        }

        @Override
        public void onFinished(@NonNull String peerIdentity, boolean outgoing, int duration) {
            if (!ListenerUtil.mutListener.listen(63874)) {
                this.update(VoipStatus.convertOnFinished(peerIdentity, outgoing, duration), TYPE_FINISHED);
            }
        }

        @Override
        public void onRejected(String peerIdentity, boolean outgoing, byte reason) {
            if (!ListenerUtil.mutListener.listen(63875)) {
                this.update(VoipStatus.convertOnRejected(peerIdentity, outgoing, reason), TYPE_REJECTED);
            }
        }

        @Override
        public void onMissed(String peerIdentity, boolean accepted) {
            if (!ListenerUtil.mutListener.listen(63876)) {
                this.update(VoipStatus.convertOnMissed(peerIdentity), TYPE_MISSED);
            }
        }

        @Override
        public void onAborted(String peerIdentity) {
            if (!ListenerUtil.mutListener.listen(63877)) {
                this.update(VoipStatus.convertOnAborted(peerIdentity), TYPE_ABORTED);
            }
        }

        private void update(final MsgpackObjectBuilder data, @StatusType String type) {
            if (!ListenerUtil.mutListener.listen(63879)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63878)) {
                            VoipStatusUpdateHandler.this.update(data, type);
                        }
                    }
                });
            }
        }
    }
}

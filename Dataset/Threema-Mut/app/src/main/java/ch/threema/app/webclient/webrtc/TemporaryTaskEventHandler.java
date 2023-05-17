/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.webclient.webrtc;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.saltyrtc.tasks.webrtc.WebRTCTask;
import org.saltyrtc.tasks.webrtc.events.MessageHandler;
import org.saltyrtc.tasks.webrtc.messages.Answer;
import org.saltyrtc.tasks.webrtc.messages.Candidate;
import org.saltyrtc.tasks.webrtc.messages.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Buffers task events until they can be dispatched.
 */
@AnyThread
public class TemporaryTaskEventHandler implements MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(TemporaryTaskEventHandler.class);

    @NonNull
    private final List<Object> events = new ArrayList<>();

    @Nullable
    private MessageHandler handler;

    @Override
    public synchronized void onOffer(@NonNull final Offer offer) {
        if (!ListenerUtil.mutListener.listen(65000)) {
            if (this.handler != null) {
                if (!ListenerUtil.mutListener.listen(64999)) {
                    this.handler.onOffer(offer);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(64998)) {
                    this.events.add(offer);
                }
            }
        }
    }

    @Override
    public synchronized void onAnswer(@NonNull final Answer answer) {
        if (!ListenerUtil.mutListener.listen(65003)) {
            if (this.handler != null) {
                if (!ListenerUtil.mutListener.listen(65002)) {
                    this.handler.onAnswer(answer);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(65001)) {
                    this.events.add(answer);
                }
            }
        }
    }

    @Override
    public synchronized void onCandidates(@NonNull final Candidate[] candidates) {
        if (!ListenerUtil.mutListener.listen(65006)) {
            if (this.handler != null) {
                if (!ListenerUtil.mutListener.listen(65005)) {
                    this.handler.onCandidates(candidates);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(65004)) {
                    this.events.add(candidates);
                }
            }
        }
    }

    public synchronized void replace(@NonNull final WebRTCTask task, @NonNull final MessageHandler handler) {
        if (!ListenerUtil.mutListener.listen(65007)) {
            logger.debug("Flushing {} events", this.events.size());
        }
        if (!ListenerUtil.mutListener.listen(65008)) {
            this.handler = handler;
        }
        if (!ListenerUtil.mutListener.listen(65014)) {
            {
                long _loopCounter789 = 0;
                for (final Object event : this.events) {
                    ListenerUtil.loopListener.listen("_loopCounter789", ++_loopCounter789);
                    if (!ListenerUtil.mutListener.listen(65013)) {
                        if (event instanceof Offer) {
                            if (!ListenerUtil.mutListener.listen(65012)) {
                                handler.onOffer((Offer) event);
                            }
                        } else if (event instanceof Answer) {
                            if (!ListenerUtil.mutListener.listen(65011)) {
                                handler.onAnswer((Answer) event);
                            }
                        } else if (event.getClass().isArray()) {
                            if (!ListenerUtil.mutListener.listen(65010)) {
                                handler.onCandidates((Candidate[]) event);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(65009)) {
                                logger.error("Invalid buffered task event type: {}", event.getClass());
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65015)) {
            logger.debug("Events flushed, replacing handler");
        }
        if (!ListenerUtil.mutListener.listen(65016)) {
            this.events.clear();
        }
        if (!ListenerUtil.mutListener.listen(65017)) {
            task.setMessageHandler(handler);
        }
    }
}

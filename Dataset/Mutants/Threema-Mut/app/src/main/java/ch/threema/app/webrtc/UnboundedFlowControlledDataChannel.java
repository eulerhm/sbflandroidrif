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
package ch.threema.app.webrtc;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.saltyrtc.tasks.webrtc.exceptions.IllegalStateError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.DataChannel;
import java.util.concurrent.ExecutionException;
import java8.util.concurrent.CompletableFuture;
import java8.util.function.Function;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A flow-controlled (sender side) data channel that allows to queue an
 * infinite amount of messages.
 *
 * While this cancels the effect of the flow control, it prevents the data
 * channel's underlying buffer from becoming saturated by queueing all messages
 * in application space.
 */
@AnyThread
public class UnboundedFlowControlledDataChannel extends FlowControlledDataChannel {

    @NonNull
    private final Logger logger = LoggerFactory.getLogger("UnboundedFlowControlledDataChannel");

    @NonNull
    private CompletableFuture<Void> queue;

    /**
     *  Create a flow-controlled (sender side) data channel with an infinite
     *  buffer.
     *
     *  @param dc The data channel to be flow-controlled
     */
    public UnboundedFlowControlledDataChannel(@NonNull final String logPrefix, @NonNull final DataChannel dc) {
        this(logPrefix, dc, null);
    }

    /**
     *  Create a flow-controlled (sender side) data channel with an infinite
     *  buffer.
     *
     *  @param dc The data channel to be flow-controlled
     *  @param initialFuture Allows to delay forwarding writes to the data
     *    channel until the initialFuture has been completed.
     */
    public UnboundedFlowControlledDataChannel(@NonNull final String logPrefix, @NonNull final DataChannel dc, @Nullable final CompletableFuture<?> initialFuture) {
        super(logPrefix, dc);
        if (!ListenerUtil.mutListener.listen(65057)) {
            if (initialFuture != null) {
                if (!ListenerUtil.mutListener.listen(65056)) {
                    this.queue = initialFuture.thenCompose(v -> this.ready());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(65055)) {
                    this.queue = this.ready();
                }
            }
        }
    }

    /**
     *  Create a flow-controlled (sender side) data channel with an infinite
     *  buffer.
     *
     *  @param dc The data channel to be flow-controlled
     *  @param initialFuture Allows to delay forwarding writes to the data
     *    channel until the initialFuture has been completed.
     *  @param lowWaterMark The low water mark unpauses the data channel once
     *    the buffered amount of bytes becomes less or equal to it.
     *  @param highWaterMark The high water mark pauses the data channel once
     *    the buffered amount of bytes becomes greater or equal to it.
     */
    public UnboundedFlowControlledDataChannel(@NonNull final String logPrefix, @NonNull final DataChannel dc, @Nullable final CompletableFuture<?> initialFuture, final long lowWaterMark, final long highWaterMark) {
        super(logPrefix, dc, lowWaterMark, highWaterMark);
        if (!ListenerUtil.mutListener.listen(65060)) {
            if (initialFuture != null) {
                if (!ListenerUtil.mutListener.listen(65059)) {
                    this.queue = initialFuture.thenCompose(v -> this.ready());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(65058)) {
                    this.queue = this.ready();
                }
            }
        }
    }

    /**
     *  Write a message to the data channel's internal or application buffer for
     *  delivery to the remote side.
     *
     *  @param message The message to be sent.
     */
    public synchronized void write(@NonNull final DataChannel.Buffer message) {
        if (!ListenerUtil.mutListener.listen(65065)) {
            // queueing by using future chaining.
            this.queue = this.queue.thenRunAsync(new Runnable() {

                @Override
                @AnyThread
                public void run() {
                    // Wait until ready
                    try {
                        if (!ListenerUtil.mutListener.listen(65063)) {
                            UnboundedFlowControlledDataChannel.this.ready().get();
                        }
                    } catch (ExecutionException error) {
                        if (!ListenerUtil.mutListener.listen(65061)) {
                            // Should not happen
                            logger.error("Woops!", error);
                        }
                        return;
                    } catch (InterruptedException error) {
                        if (!ListenerUtil.mutListener.listen(65062)) {
                            // Can happen when the channel has been closed abruptly
                            logger.error("Unable to send pending chunk! Channel closed abruptly?", error);
                        }
                        return;
                    }
                    if (!ListenerUtil.mutListener.listen(65064)) {
                        // Write message
                        UnboundedFlowControlledDataChannel.super.write(message);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(65070)) {
            this.queue.exceptionally(new Function<Throwable, Void>() {

                @Override
                @AnyThread
                public Void apply(@NonNull final Throwable error) {
                    if (!ListenerUtil.mutListener.listen(65068)) {
                        // abruptly.
                        if ((ListenerUtil.mutListener.listen(65066) ? (error.getCause() instanceof IllegalStateException && error.getCause() instanceof IllegalStateError) : (error.getCause() instanceof IllegalStateException || error.getCause() instanceof IllegalStateError))) {
                            if (!ListenerUtil.mutListener.listen(65067)) {
                                logger.info("Write queue aborted: {}", error.getMessage());
                            }
                            return null;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(65069)) {
                        // Complain!
                        logger.error("Exception in write queue", error);
                    }
                    return null;
                }
            });
        }
    }
}

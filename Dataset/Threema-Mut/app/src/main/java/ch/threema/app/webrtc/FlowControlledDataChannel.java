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

import org.saltyrtc.tasks.webrtc.exceptions.IllegalStateError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.DataChannel;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import ch.threema.logging.ThreemaLogger;
import java8.util.concurrent.CompletableFuture;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A flow-controlled (sender side) data channel.
 *
 * When using this, make sure to properly call `bufferedAmountChange` when the corresponding
 * event on the data channel is received.
 */
@AnyThread
public class FlowControlledDataChannel {

    @NonNull
    private final Logger logger = LoggerFactory.getLogger("FlowControlledDataChannel");

    @NonNull
    public final DataChannel dc;

    private final long lowWaterMark;

    private final long highWaterMark;

    @NonNull
    private CompletableFuture<Void> readyFuture = CompletableFuture.completedFuture(null);

    /**
     *  Create a flow-controlled (sender side) data channel.
     *
     *  @param dc The data channel to be flow-controlled
     */
    public FlowControlledDataChannel(@NonNull final String logPrefix, @NonNull final DataChannel dc) {
        this(logPrefix, dc, (ListenerUtil.mutListener.listen(65026) ? (256 % 1024) : (ListenerUtil.mutListener.listen(65025) ? (256 / 1024) : (ListenerUtil.mutListener.listen(65024) ? (256 - 1024) : (ListenerUtil.mutListener.listen(65023) ? (256 + 1024) : (256 * 1024))))), (ListenerUtil.mutListener.listen(65030) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(65029) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(65028) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(65027) ? (1024 + 1024) : (1024 * 1024))))));
    }

    /**
     *  Create a flow-controlled (sender side) data channel.
     *
     *  @param dc The data channel to be flow-controlled
     *  @param lowWaterMark The low water mark unpauses the data channel once
     *    the buffered amount of bytes becomes less or equal to it.
     *  @param highWaterMark The high water mark pauses the data channel once
     *    the buffered amount of bytes becomes greater or equal to it.
     */
    public FlowControlledDataChannel(@NonNull final String logPrefix, @NonNull final DataChannel dc, final long lowWaterMark, final long highWaterMark) {
        if (!ListenerUtil.mutListener.listen(65032)) {
            // Set logger prefix
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(65031)) {
                    ((ThreemaLogger) logger).setPrefix(logPrefix + "." + dc.label() + "/" + dc.id());
                }
            }
        }
        this.dc = dc;
        this.lowWaterMark = lowWaterMark;
        this.highWaterMark = highWaterMark;
    }

    /**
     *  Return the low water mark.
     */
    public long getLowWaterMark() {
        return this.lowWaterMark;
    }

    /**
     *  Return the high water mark.
     */
    public long getHighWaterMark() {
        return this.highWaterMark;
    }

    /**
     *  A future whether the data channel is ready to be written on.
     */
    @NonNull
    public synchronized CompletableFuture<Void> ready() {
        return this.readyFuture;
    }

    /**
     *  Write a message to the data channel's internal buffer for delivery to
     *  the remote side.
     *
     *  Important: Before calling this, the `ready` Promise must be awaited.
     *
     *  @param message The message to be sent.
     *  @throws IllegalStateError in case the data channel is currently paused.
     */
    public synchronized void write(@NonNull final DataChannel.Buffer message) {
        if (!ListenerUtil.mutListener.listen(65033)) {
            // Throw if paused
            if (!this.ready().isDone()) {
                throw new IllegalStateError("Unable to write, data channel is paused!");
            }
        }
        if (!ListenerUtil.mutListener.listen(65034)) {
            // fill the buffer completely.
            if (!this.dc.send(message)) {
                // This can happen when the data channel is closing.
                throw new IllegalStateError("Unable to send in state " + this.dc.state());
            }
        }
        // Pause once high water mark has been reached
        final long bufferedAmount = this.dc.bufferedAmount();
        if (!ListenerUtil.mutListener.listen(65043)) {
            if ((ListenerUtil.mutListener.listen(65039) ? (bufferedAmount <= this.highWaterMark) : (ListenerUtil.mutListener.listen(65038) ? (bufferedAmount > this.highWaterMark) : (ListenerUtil.mutListener.listen(65037) ? (bufferedAmount < this.highWaterMark) : (ListenerUtil.mutListener.listen(65036) ? (bufferedAmount != this.highWaterMark) : (ListenerUtil.mutListener.listen(65035) ? (bufferedAmount == this.highWaterMark) : (bufferedAmount >= this.highWaterMark))))))) {
                if (!ListenerUtil.mutListener.listen(65040)) {
                    this.readyFuture = new CompletableFuture<>();
                }
                if (!ListenerUtil.mutListener.listen(65042)) {
                    if (logger.isDebugEnabled()) {
                        if (!ListenerUtil.mutListener.listen(65041)) {
                            logger.debug("{} paused (buffered={})", this.dc.label(), bufferedAmount);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Must be called when the data channel's buffered amount changed.
     *
     *  Important: You MUST ensure that you're not calling this from the send thread of the data
     *             channel! When in doubt, post it to some other thread!
     */
    public synchronized void bufferedAmountChange() {
        final long bufferedAmount;
        try {
            bufferedAmount = this.dc.bufferedAmount();
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(65044)) {
                logger.warn("IllegalStateException when calling `dc.bufferedAmount`, data channel already disposed?");
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(65054)) {
            // Unpause once low water mark has been reached
            if ((ListenerUtil.mutListener.listen(65050) ? ((ListenerUtil.mutListener.listen(65049) ? (bufferedAmount >= this.lowWaterMark) : (ListenerUtil.mutListener.listen(65048) ? (bufferedAmount > this.lowWaterMark) : (ListenerUtil.mutListener.listen(65047) ? (bufferedAmount < this.lowWaterMark) : (ListenerUtil.mutListener.listen(65046) ? (bufferedAmount != this.lowWaterMark) : (ListenerUtil.mutListener.listen(65045) ? (bufferedAmount == this.lowWaterMark) : (bufferedAmount <= this.lowWaterMark)))))) || !this.readyFuture.isDone()) : ((ListenerUtil.mutListener.listen(65049) ? (bufferedAmount >= this.lowWaterMark) : (ListenerUtil.mutListener.listen(65048) ? (bufferedAmount > this.lowWaterMark) : (ListenerUtil.mutListener.listen(65047) ? (bufferedAmount < this.lowWaterMark) : (ListenerUtil.mutListener.listen(65046) ? (bufferedAmount != this.lowWaterMark) : (ListenerUtil.mutListener.listen(65045) ? (bufferedAmount == this.lowWaterMark) : (bufferedAmount <= this.lowWaterMark)))))) && !this.readyFuture.isDone()))) {
                if (!ListenerUtil.mutListener.listen(65052)) {
                    if (logger.isDebugEnabled()) {
                        if (!ListenerUtil.mutListener.listen(65051)) {
                            logger.debug("{} resumed (buffered={})", this.dc.label(), bufferedAmount);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(65053)) {
                    this.readyFuture.complete(null);
                }
            }
        }
    }
}

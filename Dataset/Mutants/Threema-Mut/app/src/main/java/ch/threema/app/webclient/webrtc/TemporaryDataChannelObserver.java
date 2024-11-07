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
import ch.threema.app.webrtc.DataChannelObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.DataChannel;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Buffers data channel events until they can be dispatched.
 */
@AnyThread
public class TemporaryDataChannelObserver extends DataChannelObserver {

    private static final Logger logger = LoggerFactory.getLogger(TemporaryDataChannelObserver.class);

    @NonNull
    private final List<Object> events = new ArrayList<>();

    @Nullable
    private DataChannelObserver observer;

    @Override
    public synchronized void onBufferedAmountChange(final long bufferedAmount) {
        if (!ListenerUtil.mutListener.listen(64979)) {
            if (this.observer != null) {
                if (!ListenerUtil.mutListener.listen(64978)) {
                    this.observer.onBufferedAmountChange(bufferedAmount);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(64977)) {
                    this.events.add(bufferedAmount);
                }
            }
        }
    }

    @Override
    public synchronized void onStateChange(@NonNull final DataChannel.State state) {
        if (!ListenerUtil.mutListener.listen(64982)) {
            if (this.observer != null) {
                if (!ListenerUtil.mutListener.listen(64981)) {
                    this.observer.onStateChange(state);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(64980)) {
                    this.events.add(state);
                }
            }
        }
    }

    @Override
    public synchronized void onMessage(@NonNull final DataChannel.Buffer buffer) {
        if (!ListenerUtil.mutListener.listen(64987)) {
            if (this.observer != null) {
                if (!ListenerUtil.mutListener.listen(64986)) {
                    this.observer.onMessage(buffer);
                }
            } else {
                // Copy the message since the underlying buffer will be reused immediately
                final ByteBuffer copy = ByteBuffer.allocate(buffer.data.remaining());
                if (!ListenerUtil.mutListener.listen(64983)) {
                    copy.put(buffer.data);
                }
                if (!ListenerUtil.mutListener.listen(64984)) {
                    copy.flip();
                }
                if (!ListenerUtil.mutListener.listen(64985)) {
                    this.events.add(new DataChannel.Buffer(copy, buffer.binary));
                }
            }
        }
    }

    public synchronized void replace(@NonNull final DataChannel dc, @NonNull final DataChannelObserver observer) {
        if (!ListenerUtil.mutListener.listen(64988)) {
            logger.debug("Flushing {} events", this.events.size());
        }
        if (!ListenerUtil.mutListener.listen(64989)) {
            this.observer = observer;
        }
        if (!ListenerUtil.mutListener.listen(64995)) {
            {
                long _loopCounter788 = 0;
                for (final Object event : this.events) {
                    ListenerUtil.loopListener.listen("_loopCounter788", ++_loopCounter788);
                    if (!ListenerUtil.mutListener.listen(64994)) {
                        if (event instanceof Long) {
                            if (!ListenerUtil.mutListener.listen(64993)) {
                                observer.onBufferedAmountChange((Long) event);
                            }
                        } else if (event instanceof DataChannel.State) {
                            if (!ListenerUtil.mutListener.listen(64992)) {
                                observer.onStateChange((DataChannel.State) event);
                            }
                        } else if (event instanceof DataChannel.Buffer) {
                            if (!ListenerUtil.mutListener.listen(64991)) {
                                observer.onMessage((DataChannel.Buffer) event);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(64990)) {
                                logger.error("Invalid buffered data channel event type: {}", event.getClass());
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64996)) {
            // segfaults if we attempt to unregister the current observer.
            logger.debug("Events flushed, replacing observer");
        }
        if (!ListenerUtil.mutListener.listen(64997)) {
            this.events.clear();
        }
    }
}

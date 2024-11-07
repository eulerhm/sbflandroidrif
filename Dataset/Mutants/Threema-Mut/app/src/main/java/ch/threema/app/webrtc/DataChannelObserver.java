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

import androidx.annotation.NonNull;
import org.webrtc.DataChannel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An improved data channel observer that passes changed values to
 * the change listeners.
 *
 * Example: This wrapper passes the data channel state to the
 * {@link #onStateChange(DataChannel.State)} method while the original
 * WebRTC observer does not.
 */
public abstract class DataChannelObserver {

    public static void register(@NonNull final DataChannel dc, @NonNull final DataChannelObserver observer) {
        if (!ListenerUtil.mutListener.listen(65018)) {
            observer.register(dc);
        }
    }

    public void register(@NonNull final DataChannel dc) {
        if (!ListenerUtil.mutListener.listen(65022)) {
            dc.registerObserver(new DataChannel.Observer() {

                @Override
                public void onBufferedAmountChange(final long bufferedAmount) {
                    if (!ListenerUtil.mutListener.listen(65019)) {
                        DataChannelObserver.this.onBufferedAmountChange(bufferedAmount);
                    }
                }

                @Override
                public void onStateChange() {
                    if (!ListenerUtil.mutListener.listen(65020)) {
                        DataChannelObserver.this.onStateChange(dc.state());
                    }
                }

                @Override
                public void onMessage(@NonNull final DataChannel.Buffer buffer) {
                    if (!ListenerUtil.mutListener.listen(65021)) {
                        DataChannelObserver.this.onMessage(buffer);
                    }
                }
            });
        }
    }

    public abstract void onBufferedAmountChange(final long bufferedAmount);

    public abstract void onStateChange(@NonNull final DataChannel.State state);

    public abstract void onMessage(@NonNull final DataChannel.Buffer buffer);
}

/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.webclient.utils;

import android.graphics.Bitmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Functions related to the webclient.
 */
@AnyThread
public class ThumbnailUtils {

    private static final Logger logger = LoggerFactory.getLogger(ThumbnailUtils.class);

    public static class Size {

        public int width;

        public int height;

        public Size(int width, int height) {
            if (!ListenerUtil.mutListener.listen(64759)) {
                this.width = width;
            }
            if (!ListenerUtil.mutListener.listen(64760)) {
                this.height = height;
            }
        }
    }

    /**
     *  Calculate new dimensions, resize down proportionally.
     */
    @NonNull
    public static Size resizeProportionally(int width, int height, int maxSidePx) {
        if (!ListenerUtil.mutListener.listen(64784)) {
            if ((ListenerUtil.mutListener.listen(64771) ? ((ListenerUtil.mutListener.listen(64765) ? (width >= maxSidePx) : (ListenerUtil.mutListener.listen(64764) ? (width <= maxSidePx) : (ListenerUtil.mutListener.listen(64763) ? (width < maxSidePx) : (ListenerUtil.mutListener.listen(64762) ? (width != maxSidePx) : (ListenerUtil.mutListener.listen(64761) ? (width == maxSidePx) : (width > maxSidePx)))))) && (ListenerUtil.mutListener.listen(64770) ? (height >= maxSidePx) : (ListenerUtil.mutListener.listen(64769) ? (height <= maxSidePx) : (ListenerUtil.mutListener.listen(64768) ? (height < maxSidePx) : (ListenerUtil.mutListener.listen(64767) ? (height != maxSidePx) : (ListenerUtil.mutListener.listen(64766) ? (height == maxSidePx) : (height > maxSidePx))))))) : ((ListenerUtil.mutListener.listen(64765) ? (width >= maxSidePx) : (ListenerUtil.mutListener.listen(64764) ? (width <= maxSidePx) : (ListenerUtil.mutListener.listen(64763) ? (width < maxSidePx) : (ListenerUtil.mutListener.listen(64762) ? (width != maxSidePx) : (ListenerUtil.mutListener.listen(64761) ? (width == maxSidePx) : (width > maxSidePx)))))) || (ListenerUtil.mutListener.listen(64770) ? (height >= maxSidePx) : (ListenerUtil.mutListener.listen(64769) ? (height <= maxSidePx) : (ListenerUtil.mutListener.listen(64768) ? (height < maxSidePx) : (ListenerUtil.mutListener.listen(64767) ? (height != maxSidePx) : (ListenerUtil.mutListener.listen(64766) ? (height == maxSidePx) : (height > maxSidePx))))))))) {
                int largerSide = Math.max(width, height);
                double scaleFactor = (ListenerUtil.mutListener.listen(64775) ? ((double) maxSidePx % largerSide) : (ListenerUtil.mutListener.listen(64774) ? ((double) maxSidePx * largerSide) : (ListenerUtil.mutListener.listen(64773) ? ((double) maxSidePx - largerSide) : (ListenerUtil.mutListener.listen(64772) ? ((double) maxSidePx + largerSide) : ((double) maxSidePx / largerSide)))));
                int newWidth = (int) Math.round((ListenerUtil.mutListener.listen(64779) ? ((double) width % scaleFactor) : (ListenerUtil.mutListener.listen(64778) ? ((double) width / scaleFactor) : (ListenerUtil.mutListener.listen(64777) ? ((double) width - scaleFactor) : (ListenerUtil.mutListener.listen(64776) ? ((double) width + scaleFactor) : ((double) width * scaleFactor))))));
                int newHeight = (int) Math.round((ListenerUtil.mutListener.listen(64783) ? ((double) height % scaleFactor) : (ListenerUtil.mutListener.listen(64782) ? ((double) height / scaleFactor) : (ListenerUtil.mutListener.listen(64781) ? ((double) height - scaleFactor) : (ListenerUtil.mutListener.listen(64780) ? ((double) height + scaleFactor) : ((double) height * scaleFactor))))));
                return new Size(newWidth, newHeight);
            }
        }
        return new Size(width, height);
    }

    /**
     *  Make sure that no side is larger than maxSize,
     *  resizing if necessary.
     */
    public static Bitmap resize(@NonNull final Bitmap thumbnail, int maxSidePx) {
        int w = thumbnail.getWidth();
        int h = thumbnail.getHeight();
        if (!ListenerUtil.mutListener.listen(64797)) {
            if ((ListenerUtil.mutListener.listen(64795) ? ((ListenerUtil.mutListener.listen(64789) ? (w >= maxSidePx) : (ListenerUtil.mutListener.listen(64788) ? (w <= maxSidePx) : (ListenerUtil.mutListener.listen(64787) ? (w < maxSidePx) : (ListenerUtil.mutListener.listen(64786) ? (w != maxSidePx) : (ListenerUtil.mutListener.listen(64785) ? (w == maxSidePx) : (w > maxSidePx)))))) && (ListenerUtil.mutListener.listen(64794) ? (h >= maxSidePx) : (ListenerUtil.mutListener.listen(64793) ? (h <= maxSidePx) : (ListenerUtil.mutListener.listen(64792) ? (h < maxSidePx) : (ListenerUtil.mutListener.listen(64791) ? (h != maxSidePx) : (ListenerUtil.mutListener.listen(64790) ? (h == maxSidePx) : (h > maxSidePx))))))) : ((ListenerUtil.mutListener.listen(64789) ? (w >= maxSidePx) : (ListenerUtil.mutListener.listen(64788) ? (w <= maxSidePx) : (ListenerUtil.mutListener.listen(64787) ? (w < maxSidePx) : (ListenerUtil.mutListener.listen(64786) ? (w != maxSidePx) : (ListenerUtil.mutListener.listen(64785) ? (w == maxSidePx) : (w > maxSidePx)))))) || (ListenerUtil.mutListener.listen(64794) ? (h >= maxSidePx) : (ListenerUtil.mutListener.listen(64793) ? (h <= maxSidePx) : (ListenerUtil.mutListener.listen(64792) ? (h < maxSidePx) : (ListenerUtil.mutListener.listen(64791) ? (h != maxSidePx) : (ListenerUtil.mutListener.listen(64790) ? (h == maxSidePx) : (h > maxSidePx))))))))) {
                Size newSize = ThumbnailUtils.resizeProportionally(w, h, maxSidePx);
                try {
                    return Bitmap.createScaledBitmap(thumbnail, newSize.width, newSize.height, true);
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(64796)) {
                        logger.error("Exception", x);
                    }
                }
            }
        }
        return thumbnail;
    }
}

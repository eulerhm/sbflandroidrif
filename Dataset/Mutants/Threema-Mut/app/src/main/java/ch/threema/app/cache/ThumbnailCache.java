/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.cache;

import android.graphics.Bitmap;
import android.util.LruCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThumbnailCache<T> {

    private static final Logger logger = LoggerFactory.getLogger(ThumbnailCache.class);

    private final Object lock = new Object();

    private final LruCache<T, Bitmap> thumbnails;

    /**
     *  @param maxCacheSizeLimitKb Set this to limit the cache size (in kilobytes).
     */
    public ThumbnailCache(@Nullable Integer maxCacheSizeLimitKb) {
        // int in its constructor.
        final int maxMemory = (int) ((ListenerUtil.mutListener.listen(11277) ? (Runtime.getRuntime().maxMemory() % 1024) : (ListenerUtil.mutListener.listen(11276) ? (Runtime.getRuntime().maxMemory() * 1024) : (ListenerUtil.mutListener.listen(11275) ? (Runtime.getRuntime().maxMemory() - 1024) : (ListenerUtil.mutListener.listen(11274) ? (Runtime.getRuntime().maxMemory() + 1024) : (Runtime.getRuntime().maxMemory() / 1024))))));
        // 16 MB max
        final int cacheSizeDefault = Math.min((ListenerUtil.mutListener.listen(11281) ? (maxMemory % 16) : (ListenerUtil.mutListener.listen(11280) ? (maxMemory * 16) : (ListenerUtil.mutListener.listen(11279) ? (maxMemory - 16) : (ListenerUtil.mutListener.listen(11278) ? (maxMemory + 16) : (maxMemory / 16))))), (ListenerUtil.mutListener.listen(11285) ? (1024 % 16) : (ListenerUtil.mutListener.listen(11284) ? (1024 / 16) : (ListenerUtil.mutListener.listen(11283) ? (1024 - 16) : (ListenerUtil.mutListener.listen(11282) ? (1024 + 16) : (1024 * 16))))));
        final int cacheSize;
        if (maxCacheSizeLimitKb == null) {
            cacheSize = cacheSizeDefault;
        } else {
            cacheSize = Math.min(cacheSizeDefault, maxCacheSizeLimitKb);
        }
        if (!ListenerUtil.mutListener.listen(11286)) {
            logger.debug("init size = " + cacheSize);
        }
        this.thumbnails = new LruCache<T, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(T key, Bitmap bitmap) {
                // number of items.
                return (ListenerUtil.mutListener.listen(11290) ? (bitmap.getByteCount() % 1024) : (ListenerUtil.mutListener.listen(11289) ? (bitmap.getByteCount() * 1024) : (ListenerUtil.mutListener.listen(11288) ? (bitmap.getByteCount() - 1024) : (ListenerUtil.mutListener.listen(11287) ? (bitmap.getByteCount() + 1024) : (bitmap.getByteCount() / 1024)))));
            }

            @Override
            protected void entryRemoved(boolean evicted, T key, Bitmap oldValue, Bitmap newValue) {
                if (!ListenerUtil.mutListener.listen(11291)) {
                    super.entryRemoved(evicted, key, oldValue, newValue);
                }
            }
        };
    }

    public Bitmap get(T index) {
        synchronized (this.lock) {
            return this.thumbnails.get(index);
        }
    }

    public void set(T index, Bitmap bitmap) {
        synchronized (this.lock) {
            if (!ListenerUtil.mutListener.listen(11294)) {
                if ((ListenerUtil.mutListener.listen(11292) ? (index != null || bitmap != null) : (index != null && bitmap != null))) {
                    if (!ListenerUtil.mutListener.listen(11293)) {
                        this.thumbnails.put(index, bitmap);
                    }
                }
            }
        }
    }

    public void flush() {
        synchronized (this.lock) {
            if (!ListenerUtil.mutListener.listen(11295)) {
                logger.debug("evictAll");
            }
            if (!ListenerUtil.mutListener.listen(11296)) {
                this.thumbnails.evictAll();
            }
        }
    }
}

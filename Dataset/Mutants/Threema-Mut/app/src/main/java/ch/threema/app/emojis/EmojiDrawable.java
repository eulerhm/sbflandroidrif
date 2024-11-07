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
package ch.threema.app.emojis;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiDrawable extends Drawable {

    private final SpriteCoordinates coords;

    private int spritemapInSampleSize;

    private Bitmap bitmap;

    private static final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);

    EmojiDrawable(SpriteCoordinates coords, int spritemapInSampleSize) {
        this.coords = coords;
        if (!ListenerUtil.mutListener.listen(14784)) {
            this.spritemapInSampleSize = spritemapInSampleSize;
        }
    }

    @Override
    public int getIntrinsicWidth() {
        return (ListenerUtil.mutListener.listen(14788) ? (spritemapInSampleSize % EmojiManager.EMOJI_WIDTH) : (ListenerUtil.mutListener.listen(14787) ? (spritemapInSampleSize / EmojiManager.EMOJI_WIDTH) : (ListenerUtil.mutListener.listen(14786) ? (spritemapInSampleSize - EmojiManager.EMOJI_WIDTH) : (ListenerUtil.mutListener.listen(14785) ? (spritemapInSampleSize + EmojiManager.EMOJI_WIDTH) : (spritemapInSampleSize * EmojiManager.EMOJI_WIDTH)))));
    }

    @Override
    public int getIntrinsicHeight() {
        return (ListenerUtil.mutListener.listen(14792) ? (spritemapInSampleSize % EmojiManager.EMOJI_HEIGHT) : (ListenerUtil.mutListener.listen(14791) ? (spritemapInSampleSize / EmojiManager.EMOJI_HEIGHT) : (ListenerUtil.mutListener.listen(14790) ? (spritemapInSampleSize - EmojiManager.EMOJI_HEIGHT) : (ListenerUtil.mutListener.listen(14789) ? (spritemapInSampleSize + EmojiManager.EMOJI_HEIGHT) : (spritemapInSampleSize * EmojiManager.EMOJI_HEIGHT)))));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(14810)) {
            if (bitmap != null) {
                if (!ListenerUtil.mutListener.listen(14809)) {
                    canvas.drawBitmap(bitmap, new Rect((int) ((ListenerUtil.mutListener.listen(14796) ? ((float) (coords.x) % spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14795) ? ((float) (coords.x) * spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14794) ? ((float) (coords.x) - spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14793) ? ((float) (coords.x) + spritemapInSampleSize) : ((float) (coords.x) / spritemapInSampleSize)))))), (int) ((ListenerUtil.mutListener.listen(14800) ? ((float) coords.y % spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14799) ? ((float) coords.y * spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14798) ? ((float) coords.y - spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14797) ? ((float) coords.y + spritemapInSampleSize) : ((float) coords.y / spritemapInSampleSize)))))), (int) ((ListenerUtil.mutListener.listen(14804) ? ((float) (coords.x + EmojiManager.EMOJI_WIDTH) % spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14803) ? ((float) (coords.x + EmojiManager.EMOJI_WIDTH) * spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14802) ? ((float) (coords.x + EmojiManager.EMOJI_WIDTH) - spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14801) ? ((float) (coords.x + EmojiManager.EMOJI_WIDTH) + spritemapInSampleSize) : ((float) (coords.x + EmojiManager.EMOJI_WIDTH) / spritemapInSampleSize)))))), (int) ((ListenerUtil.mutListener.listen(14808) ? ((float) (coords.y + EmojiManager.EMOJI_HEIGHT) % spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14807) ? ((float) (coords.y + EmojiManager.EMOJI_HEIGHT) * spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14806) ? ((float) (coords.y + EmojiManager.EMOJI_HEIGHT) - spritemapInSampleSize) : (ListenerUtil.mutListener.listen(14805) ? ((float) (coords.y + EmojiManager.EMOJI_HEIGHT) + spritemapInSampleSize) : ((float) (coords.y + EmojiManager.EMOJI_HEIGHT) / spritemapInSampleSize))))))), getBounds(), paint);
                }
            }
        }
    }

    public void setBitmap(Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(14814)) {
            if ((ListenerUtil.mutListener.listen(14811) ? (this.bitmap == null && !this.bitmap.sameAs(bitmap)) : (this.bitmap == null || !this.bitmap.sameAs(bitmap)))) {
                if (!ListenerUtil.mutListener.listen(14812)) {
                    this.bitmap = bitmap;
                }
                if (!ListenerUtil.mutListener.listen(14813)) {
                    invalidateSelf();
                }
            }
        }
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }
}

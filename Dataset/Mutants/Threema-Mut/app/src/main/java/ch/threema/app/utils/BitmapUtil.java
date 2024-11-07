/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.webclient.utils.ThumbnailUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BitmapUtil {

    private static final Logger logger = LoggerFactory.getLogger(BitmapUtil.class);

    private static final int DEFAULT_JPG_QUALITY = 80;

    // PNG is lossless anyway
    private static final int DEFAULT_PNG_QUALITY = 100;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ FLIP_NONE, FLIP_HORIZONTAL, FLIP_VERTICAL })
    public @interface FlipType {
    }

    public static final int FLIP_NONE = 0;

    public static final int FLIP_HORIZONTAL = 1;

    public static final int FLIP_VERTICAL = 1 << 1;

    /**
     *  Get the inSampleSize that produces an image that has its width *smaller* or equal to maxWidth
     *
     *  @param bitmapBytes bitmap represented by a byte array
     *  @param maxWidth maxWidth that results from applying the inSampleSize
     *  @return calculated inSampleSize as a power of two
     */
    private static int getInSampleSizeByWidth(byte[] bitmapBytes, int maxWidth) {
        // check dimensions of input bitmap
        BitmapFactory.Options o = new BitmapFactory.Options();
        if (!ListenerUtil.mutListener.listen(49570)) {
            o.inJustDecodeBounds = true;
        }
        if (!ListenerUtil.mutListener.listen(49571)) {
            BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length, o);
        }
        if (!ListenerUtil.mutListener.listen(49581)) {
            // no scaling necessary if width of bitmap is smaller or equal to maxWidth
            if ((ListenerUtil.mutListener.listen(49576) ? (o.outWidth >= maxWidth) : (ListenerUtil.mutListener.listen(49575) ? (o.outWidth <= maxWidth) : (ListenerUtil.mutListener.listen(49574) ? (o.outWidth < maxWidth) : (ListenerUtil.mutListener.listen(49573) ? (o.outWidth != maxWidth) : (ListenerUtil.mutListener.listen(49572) ? (o.outWidth == maxWidth) : (o.outWidth > maxWidth))))))) {
                float scalingFactor = (ListenerUtil.mutListener.listen(49580) ? ((float) o.outWidth % (float) maxWidth) : (ListenerUtil.mutListener.listen(49579) ? ((float) o.outWidth * (float) maxWidth) : (ListenerUtil.mutListener.listen(49578) ? ((float) o.outWidth - (float) maxWidth) : (ListenerUtil.mutListener.listen(49577) ? ((float) o.outWidth + (float) maxWidth) : ((float) o.outWidth / (float) maxWidth)))));
                // round to the next higher power of two
                return MathUtils.getNextHigherPowerOfTwo((int) Math.ceil(scalingFactor));
            }
        }
        return 1;
    }

    /**
     *  Calculate the largest inSampleSize value that is a power of 2 and keeps both
     *  height and width *larger* than the requested height and width.
     *
     *  @param width width of source bitmap
     *  @param height height of source bitmap
     *  @param reqWidth requested width
     *  @param reqHeight requested height
     *  @return SampleResult
     */
    static SampleResult getSampleSize(final int width, final int height, int reqWidth, int reqHeight) {
        SampleResult result = new SampleResult();
        int inSampleSize = 1;
        if (!ListenerUtil.mutListener.listen(49622)) {
            if ((ListenerUtil.mutListener.listen(49592) ? ((ListenerUtil.mutListener.listen(49586) ? (height >= reqHeight) : (ListenerUtil.mutListener.listen(49585) ? (height <= reqHeight) : (ListenerUtil.mutListener.listen(49584) ? (height < reqHeight) : (ListenerUtil.mutListener.listen(49583) ? (height != reqHeight) : (ListenerUtil.mutListener.listen(49582) ? (height == reqHeight) : (height > reqHeight)))))) && (ListenerUtil.mutListener.listen(49591) ? (width >= reqWidth) : (ListenerUtil.mutListener.listen(49590) ? (width <= reqWidth) : (ListenerUtil.mutListener.listen(49589) ? (width < reqWidth) : (ListenerUtil.mutListener.listen(49588) ? (width != reqWidth) : (ListenerUtil.mutListener.listen(49587) ? (width == reqWidth) : (width > reqWidth))))))) : ((ListenerUtil.mutListener.listen(49586) ? (height >= reqHeight) : (ListenerUtil.mutListener.listen(49585) ? (height <= reqHeight) : (ListenerUtil.mutListener.listen(49584) ? (height < reqHeight) : (ListenerUtil.mutListener.listen(49583) ? (height != reqHeight) : (ListenerUtil.mutListener.listen(49582) ? (height == reqHeight) : (height > reqHeight)))))) || (ListenerUtil.mutListener.listen(49591) ? (width >= reqWidth) : (ListenerUtil.mutListener.listen(49590) ? (width <= reqWidth) : (ListenerUtil.mutListener.listen(49589) ? (width < reqWidth) : (ListenerUtil.mutListener.listen(49588) ? (width != reqWidth) : (ListenerUtil.mutListener.listen(49587) ? (width == reqWidth) : (width > reqWidth))))))))) {
                final int halfHeight = (ListenerUtil.mutListener.listen(49596) ? (height % 2) : (ListenerUtil.mutListener.listen(49595) ? (height * 2) : (ListenerUtil.mutListener.listen(49594) ? (height - 2) : (ListenerUtil.mutListener.listen(49593) ? (height + 2) : (height / 2)))));
                final int halfWidth = (ListenerUtil.mutListener.listen(49600) ? (width % 2) : (ListenerUtil.mutListener.listen(49599) ? (width * 2) : (ListenerUtil.mutListener.listen(49598) ? (width - 2) : (ListenerUtil.mutListener.listen(49597) ? (width + 2) : (width / 2)))));
                if (!ListenerUtil.mutListener.listen(49621)) {
                    {
                        long _loopCounter570 = 0;
                        while ((ListenerUtil.mutListener.listen(49620) ? ((ListenerUtil.mutListener.listen(49610) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) >= reqHeight) : (ListenerUtil.mutListener.listen(49609) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) <= reqHeight) : (ListenerUtil.mutListener.listen(49608) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) < reqHeight) : (ListenerUtil.mutListener.listen(49607) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) != reqHeight) : (ListenerUtil.mutListener.listen(49606) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) == reqHeight) : (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) > reqHeight)))))) || (ListenerUtil.mutListener.listen(49619) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) >= reqWidth) : (ListenerUtil.mutListener.listen(49618) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) <= reqWidth) : (ListenerUtil.mutListener.listen(49617) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) < reqWidth) : (ListenerUtil.mutListener.listen(49616) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) != reqWidth) : (ListenerUtil.mutListener.listen(49615) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) == reqWidth) : (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) > reqWidth))))))) : ((ListenerUtil.mutListener.listen(49610) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) >= reqHeight) : (ListenerUtil.mutListener.listen(49609) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) <= reqHeight) : (ListenerUtil.mutListener.listen(49608) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) < reqHeight) : (ListenerUtil.mutListener.listen(49607) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) != reqHeight) : (ListenerUtil.mutListener.listen(49606) ? (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) == reqHeight) : (((ListenerUtil.mutListener.listen(49605) ? (halfHeight % inSampleSize) : (ListenerUtil.mutListener.listen(49604) ? (halfHeight * inSampleSize) : (ListenerUtil.mutListener.listen(49603) ? (halfHeight - inSampleSize) : (ListenerUtil.mutListener.listen(49602) ? (halfHeight + inSampleSize) : (halfHeight / inSampleSize)))))) > reqHeight)))))) && (ListenerUtil.mutListener.listen(49619) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) >= reqWidth) : (ListenerUtil.mutListener.listen(49618) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) <= reqWidth) : (ListenerUtil.mutListener.listen(49617) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) < reqWidth) : (ListenerUtil.mutListener.listen(49616) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) != reqWidth) : (ListenerUtil.mutListener.listen(49615) ? (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) == reqWidth) : (((ListenerUtil.mutListener.listen(49614) ? (halfWidth % inSampleSize) : (ListenerUtil.mutListener.listen(49613) ? (halfWidth * inSampleSize) : (ListenerUtil.mutListener.listen(49612) ? (halfWidth - inSampleSize) : (ListenerUtil.mutListener.listen(49611) ? (halfWidth + inSampleSize) : (halfWidth / inSampleSize)))))) > reqWidth))))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter570", ++_loopCounter570);
                            if (!ListenerUtil.mutListener.listen(49601)) {
                                inSampleSize *= 2;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49623)) {
            result.newWidth = reqWidth;
        }
        if (!ListenerUtil.mutListener.listen(49624)) {
            result.newHeight = reqHeight;
        }
        if (!ListenerUtil.mutListener.listen(49625)) {
            result.inSampleSize = inSampleSize;
        }
        return result;
    }

    public static byte[] bitmapToPngByteArray(Bitmap bitmap) {
        return bitmapToByteArray(bitmap, Bitmap.CompressFormat.PNG, DEFAULT_PNG_QUALITY);
    }

    public static byte[] bitmapToJpegByteArray(Bitmap bitmap) {
        return bitmapToByteArray(bitmap, Bitmap.CompressFormat.JPEG, DEFAULT_JPG_QUALITY);
    }

    /**
     *  Get a compressed byte array representation of the supplied bitmap
     *  @param bitmap
     *  @param quality
     *  @return Byte array of bitmap
     */
    public static byte[] bitmapToByteArray(Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (!ListenerUtil.mutListener.listen(49626)) {
            bitmap.compress(format, quality, stream);
        }
        return stream.toByteArray();
    }

    public static BitmapFactory.Options getImageDimensions(InputStream inputStream) throws OutOfMemoryError {
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (!ListenerUtil.mutListener.listen(49627)) {
            options.inJustDecodeBounds = true;
        }
        if (!ListenerUtil.mutListener.listen(49628)) {
            BitmapFactory.decodeStream(inputStream, null, options);
        }
        return options;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, float rotation) {
        return rotateBitmap(bitmap, rotation, FLIP_NONE);
    }

    /**
     *  Rotate and flip a bitmap according to parameters
     *  The bitmap will be filtered
     *  @param bitmap Bitmap to rotate and/or flip
     *  @param rotation Desired rotation in degrees
     *  @param flip How to flip the bitmap. Choice of horizontal (along the x axis) or vertical (along the y axis)
     *  @return Processed bitmap or original bitmap in case of failure
     */
    @NonNull
    public static Bitmap rotateBitmap(@NonNull Bitmap bitmap, float rotation, int flip) {
        if (!ListenerUtil.mutListener.listen(49683)) {
            if ((ListenerUtil.mutListener.listen(49639) ? ((ListenerUtil.mutListener.listen(49633) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(49632) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(49631) ? (rotation > 0) : (ListenerUtil.mutListener.listen(49630) ? (rotation < 0) : (ListenerUtil.mutListener.listen(49629) ? (rotation == 0) : (rotation != 0)))))) && (ListenerUtil.mutListener.listen(49638) ? (flip >= FLIP_NONE) : (ListenerUtil.mutListener.listen(49637) ? (flip <= FLIP_NONE) : (ListenerUtil.mutListener.listen(49636) ? (flip > FLIP_NONE) : (ListenerUtil.mutListener.listen(49635) ? (flip < FLIP_NONE) : (ListenerUtil.mutListener.listen(49634) ? (flip == FLIP_NONE) : (flip != FLIP_NONE))))))) : ((ListenerUtil.mutListener.listen(49633) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(49632) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(49631) ? (rotation > 0) : (ListenerUtil.mutListener.listen(49630) ? (rotation < 0) : (ListenerUtil.mutListener.listen(49629) ? (rotation == 0) : (rotation != 0)))))) || (ListenerUtil.mutListener.listen(49638) ? (flip >= FLIP_NONE) : (ListenerUtil.mutListener.listen(49637) ? (flip <= FLIP_NONE) : (ListenerUtil.mutListener.listen(49636) ? (flip > FLIP_NONE) : (ListenerUtil.mutListener.listen(49635) ? (flip < FLIP_NONE) : (ListenerUtil.mutListener.listen(49634) ? (flip == FLIP_NONE) : (flip != FLIP_NONE))))))))) {
                final int width = bitmap.getWidth();
                final int height = bitmap.getHeight();
                final Matrix matrix = new Matrix();
                if (!ListenerUtil.mutListener.listen(49675)) {
                    if ((ListenerUtil.mutListener.listen(49644) ? (flip >= FLIP_NONE) : (ListenerUtil.mutListener.listen(49643) ? (flip <= FLIP_NONE) : (ListenerUtil.mutListener.listen(49642) ? (flip > FLIP_NONE) : (ListenerUtil.mutListener.listen(49641) ? (flip < FLIP_NONE) : (ListenerUtil.mutListener.listen(49640) ? (flip == FLIP_NONE) : (flip != FLIP_NONE))))))) {
                        if (!ListenerUtil.mutListener.listen(49659)) {
                            if ((ListenerUtil.mutListener.listen(49649) ? ((flip & FLIP_HORIZONTAL) >= FLIP_HORIZONTAL) : (ListenerUtil.mutListener.listen(49648) ? ((flip & FLIP_HORIZONTAL) <= FLIP_HORIZONTAL) : (ListenerUtil.mutListener.listen(49647) ? ((flip & FLIP_HORIZONTAL) > FLIP_HORIZONTAL) : (ListenerUtil.mutListener.listen(49646) ? ((flip & FLIP_HORIZONTAL) < FLIP_HORIZONTAL) : (ListenerUtil.mutListener.listen(49645) ? ((flip & FLIP_HORIZONTAL) != FLIP_HORIZONTAL) : ((flip & FLIP_HORIZONTAL) == FLIP_HORIZONTAL))))))) {
                                if (!ListenerUtil.mutListener.listen(49658)) {
                                    matrix.postScale(-1, 1, (ListenerUtil.mutListener.listen(49653) ? (width % 2f) : (ListenerUtil.mutListener.listen(49652) ? (width * 2f) : (ListenerUtil.mutListener.listen(49651) ? (width - 2f) : (ListenerUtil.mutListener.listen(49650) ? (width + 2f) : (width / 2f))))), (ListenerUtil.mutListener.listen(49657) ? (height % 2f) : (ListenerUtil.mutListener.listen(49656) ? (height * 2f) : (ListenerUtil.mutListener.listen(49655) ? (height - 2f) : (ListenerUtil.mutListener.listen(49654) ? (height + 2f) : (height / 2f))))));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(49674)) {
                            if ((ListenerUtil.mutListener.listen(49664) ? ((flip & FLIP_VERTICAL) >= FLIP_VERTICAL) : (ListenerUtil.mutListener.listen(49663) ? ((flip & FLIP_VERTICAL) <= FLIP_VERTICAL) : (ListenerUtil.mutListener.listen(49662) ? ((flip & FLIP_VERTICAL) > FLIP_VERTICAL) : (ListenerUtil.mutListener.listen(49661) ? ((flip & FLIP_VERTICAL) < FLIP_VERTICAL) : (ListenerUtil.mutListener.listen(49660) ? ((flip & FLIP_VERTICAL) != FLIP_VERTICAL) : ((flip & FLIP_VERTICAL) == FLIP_VERTICAL))))))) {
                                if (!ListenerUtil.mutListener.listen(49673)) {
                                    matrix.postScale(1, -1, (ListenerUtil.mutListener.listen(49668) ? (width % 2f) : (ListenerUtil.mutListener.listen(49667) ? (width * 2f) : (ListenerUtil.mutListener.listen(49666) ? (width - 2f) : (ListenerUtil.mutListener.listen(49665) ? (width + 2f) : (width / 2f))))), (ListenerUtil.mutListener.listen(49672) ? (height % 2f) : (ListenerUtil.mutListener.listen(49671) ? (height * 2f) : (ListenerUtil.mutListener.listen(49670) ? (height - 2f) : (ListenerUtil.mutListener.listen(49669) ? (height + 2f) : (height / 2f))))));
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(49682)) {
                    if ((ListenerUtil.mutListener.listen(49680) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(49679) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(49678) ? (rotation > 0) : (ListenerUtil.mutListener.listen(49677) ? (rotation < 0) : (ListenerUtil.mutListener.listen(49676) ? (rotation == 0) : (rotation != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(49681)) {
                            matrix.postRotate(rotation);
                        }
                    }
                }
                return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            }
        }
        return bitmap;
    }

    @Nullable
    public static byte[] getJpegByteArray(Bitmap bitmap, float rotation, int flip) {
        if (!ListenerUtil.mutListener.listen(49698)) {
            if (bitmap != null) {
                if (!ListenerUtil.mutListener.listen(49696)) {
                    if ((ListenerUtil.mutListener.listen(49694) ? ((ListenerUtil.mutListener.listen(49688) ? (rotation >= 0f) : (ListenerUtil.mutListener.listen(49687) ? (rotation <= 0f) : (ListenerUtil.mutListener.listen(49686) ? (rotation > 0f) : (ListenerUtil.mutListener.listen(49685) ? (rotation < 0f) : (ListenerUtil.mutListener.listen(49684) ? (rotation == 0f) : (rotation != 0f)))))) && (ListenerUtil.mutListener.listen(49693) ? (flip >= FLIP_NONE) : (ListenerUtil.mutListener.listen(49692) ? (flip <= FLIP_NONE) : (ListenerUtil.mutListener.listen(49691) ? (flip > FLIP_NONE) : (ListenerUtil.mutListener.listen(49690) ? (flip < FLIP_NONE) : (ListenerUtil.mutListener.listen(49689) ? (flip == FLIP_NONE) : (flip != FLIP_NONE))))))) : ((ListenerUtil.mutListener.listen(49688) ? (rotation >= 0f) : (ListenerUtil.mutListener.listen(49687) ? (rotation <= 0f) : (ListenerUtil.mutListener.listen(49686) ? (rotation > 0f) : (ListenerUtil.mutListener.listen(49685) ? (rotation < 0f) : (ListenerUtil.mutListener.listen(49684) ? (rotation == 0f) : (rotation != 0f)))))) || (ListenerUtil.mutListener.listen(49693) ? (flip >= FLIP_NONE) : (ListenerUtil.mutListener.listen(49692) ? (flip <= FLIP_NONE) : (ListenerUtil.mutListener.listen(49691) ? (flip > FLIP_NONE) : (ListenerUtil.mutListener.listen(49690) ? (flip < FLIP_NONE) : (ListenerUtil.mutListener.listen(49689) ? (flip == FLIP_NONE) : (flip != FLIP_NONE))))))))) {
                        if (!ListenerUtil.mutListener.listen(49695)) {
                            bitmap = rotateBitmap(bitmap, rotation, flip);
                        }
                    }
                }
                byte[] bitmapArray = bitmapToJpegByteArray(bitmap);
                if (!ListenerUtil.mutListener.listen(49697)) {
                    recycle(bitmap);
                }
                return bitmapArray;
            }
        }
        return null;
    }

    @Nullable
    public static byte[] getPngByteArray(Bitmap bitmap, float rotation, int flip) {
        if (!ListenerUtil.mutListener.listen(49713)) {
            if (bitmap != null) {
                if (!ListenerUtil.mutListener.listen(49711)) {
                    if ((ListenerUtil.mutListener.listen(49709) ? ((ListenerUtil.mutListener.listen(49703) ? (rotation >= 0f) : (ListenerUtil.mutListener.listen(49702) ? (rotation <= 0f) : (ListenerUtil.mutListener.listen(49701) ? (rotation > 0f) : (ListenerUtil.mutListener.listen(49700) ? (rotation < 0f) : (ListenerUtil.mutListener.listen(49699) ? (rotation == 0f) : (rotation != 0f)))))) && (ListenerUtil.mutListener.listen(49708) ? (flip >= FLIP_NONE) : (ListenerUtil.mutListener.listen(49707) ? (flip <= FLIP_NONE) : (ListenerUtil.mutListener.listen(49706) ? (flip > FLIP_NONE) : (ListenerUtil.mutListener.listen(49705) ? (flip < FLIP_NONE) : (ListenerUtil.mutListener.listen(49704) ? (flip == FLIP_NONE) : (flip != FLIP_NONE))))))) : ((ListenerUtil.mutListener.listen(49703) ? (rotation >= 0f) : (ListenerUtil.mutListener.listen(49702) ? (rotation <= 0f) : (ListenerUtil.mutListener.listen(49701) ? (rotation > 0f) : (ListenerUtil.mutListener.listen(49700) ? (rotation < 0f) : (ListenerUtil.mutListener.listen(49699) ? (rotation == 0f) : (rotation != 0f)))))) || (ListenerUtil.mutListener.listen(49708) ? (flip >= FLIP_NONE) : (ListenerUtil.mutListener.listen(49707) ? (flip <= FLIP_NONE) : (ListenerUtil.mutListener.listen(49706) ? (flip > FLIP_NONE) : (ListenerUtil.mutListener.listen(49705) ? (flip < FLIP_NONE) : (ListenerUtil.mutListener.listen(49704) ? (flip == FLIP_NONE) : (flip != FLIP_NONE))))))))) {
                        if (!ListenerUtil.mutListener.listen(49710)) {
                            bitmap = rotateBitmap(bitmap, rotation, flip);
                        }
                    }
                }
                byte[] bitmapArray = bitmapToPngByteArray(bitmap);
                if (!ListenerUtil.mutListener.listen(49712)) {
                    recycle(bitmap);
                }
                return bitmapArray;
            }
        }
        return null;
    }

    public static Bitmap safeGetBitmapFromUri(Context context, Uri imageUri, int maxSize, boolean highQuality) {
        return safeGetBitmapFromUri(context, imageUri, maxSize, highQuality, true, true);
    }

    public static Bitmap safeGetBitmapFromUri(Context context, Uri imageUri, int maxSize, boolean highQuality, boolean replaceTransparency) {
        return safeGetBitmapFromUri(context, imageUri, maxSize, highQuality, replaceTransparency, false);
    }

    /**
     *  Get a scaled bitmap from a JPG image file pointed at by imageUri keeping its aspect ratio
     *  The image is scaled so that it fits into a bounding box of maxSize x maxSize unless the scaleToWidth parameter is set.
     *  If scaleToWidth is set, the image will be scaled so that its width does not exceed maxSize while the height may be larger
     *
     *  @param context
     *  @param imageUri Uri pointing to source image
     *  @param maxSize max size of the image
     *  @param highQuality if set to false, a RGB_565 bitmap configuration will be used (uses half the memory of the regular ARGB_8888 configuration)
     *  @param replaceTransparency if set to true, transparency in the image will be replaced with Color.WHITE
     *  @param scaleToWidth if set, the image will be scaled so its width does not exceed maxSize while the height may be larger
     *  @return resulting bitmap or null in case of failure
     */
    @Nullable
    public static Bitmap safeGetBitmapFromUri(Context context, Uri imageUri, int maxSize, boolean highQuality, boolean replaceTransparency, boolean scaleToWidth) {
        if (!ListenerUtil.mutListener.listen(49714)) {
            logger.debug("safeGetBitmapFromUri");
        }
        InputStream measure = null, data = null;
        Bitmap unscaledPhoto = null;
        BitmapFactory.Options options;
        int imageWidth, imageHeight;
        final Uri fixedImageUri = FileUtil.getFixedContentUri(context, imageUri);
        if (!ListenerUtil.mutListener.listen(49715)) {
            if (fixedImageUri == null) {
                return null;
            }
        }
        try {
            try {
                try {
                    if (!ListenerUtil.mutListener.listen(49721)) {
                        measure = context.getContentResolver().openInputStream(fixedImageUri);
                    }
                    if (!ListenerUtil.mutListener.listen(49722)) {
                        data = context.getContentResolver().openInputStream(fixedImageUri);
                    }
                } catch (FileNotFoundException | SecurityException | IllegalStateException e) {
                    if (!ListenerUtil.mutListener.listen(49720)) {
                        logger.error("Exception", e);
                    }
                    return null;
                }
                // get dimensions of file
                try {
                    options = getImageDimensions(measure);
                } catch (OutOfMemoryError e) {
                    if (!ListenerUtil.mutListener.listen(49723)) {
                        logger.error("Exception", e);
                    }
                    return null;
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(49719)) {
                    if (measure != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(49718)) {
                                measure.close();
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            }
            imageWidth = options.outWidth;
            imageHeight = options.outHeight;
            if (!ListenerUtil.mutListener.listen(49725)) {
                if (scaleToWidth) {
                    ThumbnailUtils.Size size = getSizeFromTargetWidth(options.outWidth, options.outHeight, maxSize);
                    if (!ListenerUtil.mutListener.listen(49724)) {
                        maxSize = Math.max(size.height, size.width);
                    }
                }
            }
            SampleResult sampleSize = BitmapUtil.getSampleSize(imageWidth, imageHeight, maxSize, maxSize);
            if (!ListenerUtil.mutListener.listen(49726)) {
                options.inSampleSize = sampleSize.inSampleSize;
            }
            if (!ListenerUtil.mutListener.listen(49727)) {
                options.inJustDecodeBounds = false;
            }
            if (!ListenerUtil.mutListener.listen(49729)) {
                if (!highQuality) {
                    if (!ListenerUtil.mutListener.listen(49728)) {
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(49732)) {
                if (data != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(49731)) {
                            unscaledPhoto = BitmapFactory.decodeStream(new BufferedInputStream(data), null, options);
                        }
                    } catch (StackOverflowError e) {
                        if (!ListenerUtil.mutListener.listen(49730)) {
                            logger.error("Exception", e);
                        }
                        return null;
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(49717)) {
                if (data != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(49716)) {
                            data.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49796)) {
            if (unscaledPhoto != null) {
                Bitmap result = unscaledPhoto;
                if (!ListenerUtil.mutListener.listen(49791)) {
                    if ((ListenerUtil.mutListener.listen(49743) ? ((ListenerUtil.mutListener.listen(49737) ? (options.outWidth >= maxSize) : (ListenerUtil.mutListener.listen(49736) ? (options.outWidth <= maxSize) : (ListenerUtil.mutListener.listen(49735) ? (options.outWidth < maxSize) : (ListenerUtil.mutListener.listen(49734) ? (options.outWidth != maxSize) : (ListenerUtil.mutListener.listen(49733) ? (options.outWidth == maxSize) : (options.outWidth > maxSize)))))) && (ListenerUtil.mutListener.listen(49742) ? (options.outHeight >= maxSize) : (ListenerUtil.mutListener.listen(49741) ? (options.outHeight <= maxSize) : (ListenerUtil.mutListener.listen(49740) ? (options.outHeight < maxSize) : (ListenerUtil.mutListener.listen(49739) ? (options.outHeight != maxSize) : (ListenerUtil.mutListener.listen(49738) ? (options.outHeight == maxSize) : (options.outHeight > maxSize))))))) : ((ListenerUtil.mutListener.listen(49737) ? (options.outWidth >= maxSize) : (ListenerUtil.mutListener.listen(49736) ? (options.outWidth <= maxSize) : (ListenerUtil.mutListener.listen(49735) ? (options.outWidth < maxSize) : (ListenerUtil.mutListener.listen(49734) ? (options.outWidth != maxSize) : (ListenerUtil.mutListener.listen(49733) ? (options.outWidth == maxSize) : (options.outWidth > maxSize)))))) || (ListenerUtil.mutListener.listen(49742) ? (options.outHeight >= maxSize) : (ListenerUtil.mutListener.listen(49741) ? (options.outHeight <= maxSize) : (ListenerUtil.mutListener.listen(49740) ? (options.outHeight < maxSize) : (ListenerUtil.mutListener.listen(49739) ? (options.outHeight != maxSize) : (ListenerUtil.mutListener.listen(49738) ? (options.outHeight == maxSize) : (options.outHeight > maxSize))))))))) {
                        final float aspectWidth, aspectHeight;
                        if ((ListenerUtil.mutListener.listen(49754) ? ((ListenerUtil.mutListener.listen(49748) ? (imageWidth >= 0) : (ListenerUtil.mutListener.listen(49747) ? (imageWidth <= 0) : (ListenerUtil.mutListener.listen(49746) ? (imageWidth > 0) : (ListenerUtil.mutListener.listen(49745) ? (imageWidth < 0) : (ListenerUtil.mutListener.listen(49744) ? (imageWidth != 0) : (imageWidth == 0)))))) && (ListenerUtil.mutListener.listen(49753) ? (imageHeight >= 0) : (ListenerUtil.mutListener.listen(49752) ? (imageHeight <= 0) : (ListenerUtil.mutListener.listen(49751) ? (imageHeight > 0) : (ListenerUtil.mutListener.listen(49750) ? (imageHeight < 0) : (ListenerUtil.mutListener.listen(49749) ? (imageHeight != 0) : (imageHeight == 0))))))) : ((ListenerUtil.mutListener.listen(49748) ? (imageWidth >= 0) : (ListenerUtil.mutListener.listen(49747) ? (imageWidth <= 0) : (ListenerUtil.mutListener.listen(49746) ? (imageWidth > 0) : (ListenerUtil.mutListener.listen(49745) ? (imageWidth < 0) : (ListenerUtil.mutListener.listen(49744) ? (imageWidth != 0) : (imageWidth == 0)))))) || (ListenerUtil.mutListener.listen(49753) ? (imageHeight >= 0) : (ListenerUtil.mutListener.listen(49752) ? (imageHeight <= 0) : (ListenerUtil.mutListener.listen(49751) ? (imageHeight > 0) : (ListenerUtil.mutListener.listen(49750) ? (imageHeight < 0) : (ListenerUtil.mutListener.listen(49749) ? (imageHeight != 0) : (imageHeight == 0))))))))) {
                            aspectWidth = maxSize;
                            aspectHeight = maxSize;
                        } else if ((ListenerUtil.mutListener.listen(49759) ? (options.outWidth <= options.outHeight) : (ListenerUtil.mutListener.listen(49758) ? (options.outWidth > options.outHeight) : (ListenerUtil.mutListener.listen(49757) ? (options.outWidth < options.outHeight) : (ListenerUtil.mutListener.listen(49756) ? (options.outWidth != options.outHeight) : (ListenerUtil.mutListener.listen(49755) ? (options.outWidth == options.outHeight) : (options.outWidth >= options.outHeight))))))) {
                            aspectWidth = maxSize;
                            aspectHeight = (ListenerUtil.mutListener.listen(49775) ? (((ListenerUtil.mutListener.listen(49771) ? (aspectWidth % options.outWidth) : (ListenerUtil.mutListener.listen(49770) ? (aspectWidth * options.outWidth) : (ListenerUtil.mutListener.listen(49769) ? (aspectWidth - options.outWidth) : (ListenerUtil.mutListener.listen(49768) ? (aspectWidth + options.outWidth) : (aspectWidth / options.outWidth)))))) % options.outHeight) : (ListenerUtil.mutListener.listen(49774) ? (((ListenerUtil.mutListener.listen(49771) ? (aspectWidth % options.outWidth) : (ListenerUtil.mutListener.listen(49770) ? (aspectWidth * options.outWidth) : (ListenerUtil.mutListener.listen(49769) ? (aspectWidth - options.outWidth) : (ListenerUtil.mutListener.listen(49768) ? (aspectWidth + options.outWidth) : (aspectWidth / options.outWidth)))))) / options.outHeight) : (ListenerUtil.mutListener.listen(49773) ? (((ListenerUtil.mutListener.listen(49771) ? (aspectWidth % options.outWidth) : (ListenerUtil.mutListener.listen(49770) ? (aspectWidth * options.outWidth) : (ListenerUtil.mutListener.listen(49769) ? (aspectWidth - options.outWidth) : (ListenerUtil.mutListener.listen(49768) ? (aspectWidth + options.outWidth) : (aspectWidth / options.outWidth)))))) - options.outHeight) : (ListenerUtil.mutListener.listen(49772) ? (((ListenerUtil.mutListener.listen(49771) ? (aspectWidth % options.outWidth) : (ListenerUtil.mutListener.listen(49770) ? (aspectWidth * options.outWidth) : (ListenerUtil.mutListener.listen(49769) ? (aspectWidth - options.outWidth) : (ListenerUtil.mutListener.listen(49768) ? (aspectWidth + options.outWidth) : (aspectWidth / options.outWidth)))))) + options.outHeight) : (((ListenerUtil.mutListener.listen(49771) ? (aspectWidth % options.outWidth) : (ListenerUtil.mutListener.listen(49770) ? (aspectWidth * options.outWidth) : (ListenerUtil.mutListener.listen(49769) ? (aspectWidth - options.outWidth) : (ListenerUtil.mutListener.listen(49768) ? (aspectWidth + options.outWidth) : (aspectWidth / options.outWidth)))))) * options.outHeight)))));
                        } else {
                            aspectHeight = maxSize;
                            aspectWidth = (ListenerUtil.mutListener.listen(49767) ? (((ListenerUtil.mutListener.listen(49763) ? (aspectHeight % options.outHeight) : (ListenerUtil.mutListener.listen(49762) ? (aspectHeight * options.outHeight) : (ListenerUtil.mutListener.listen(49761) ? (aspectHeight - options.outHeight) : (ListenerUtil.mutListener.listen(49760) ? (aspectHeight + options.outHeight) : (aspectHeight / options.outHeight)))))) % options.outWidth) : (ListenerUtil.mutListener.listen(49766) ? (((ListenerUtil.mutListener.listen(49763) ? (aspectHeight % options.outHeight) : (ListenerUtil.mutListener.listen(49762) ? (aspectHeight * options.outHeight) : (ListenerUtil.mutListener.listen(49761) ? (aspectHeight - options.outHeight) : (ListenerUtil.mutListener.listen(49760) ? (aspectHeight + options.outHeight) : (aspectHeight / options.outHeight)))))) / options.outWidth) : (ListenerUtil.mutListener.listen(49765) ? (((ListenerUtil.mutListener.listen(49763) ? (aspectHeight % options.outHeight) : (ListenerUtil.mutListener.listen(49762) ? (aspectHeight * options.outHeight) : (ListenerUtil.mutListener.listen(49761) ? (aspectHeight - options.outHeight) : (ListenerUtil.mutListener.listen(49760) ? (aspectHeight + options.outHeight) : (aspectHeight / options.outHeight)))))) - options.outWidth) : (ListenerUtil.mutListener.listen(49764) ? (((ListenerUtil.mutListener.listen(49763) ? (aspectHeight % options.outHeight) : (ListenerUtil.mutListener.listen(49762) ? (aspectHeight * options.outHeight) : (ListenerUtil.mutListener.listen(49761) ? (aspectHeight - options.outHeight) : (ListenerUtil.mutListener.listen(49760) ? (aspectHeight + options.outHeight) : (aspectHeight / options.outHeight)))))) + options.outWidth) : (((ListenerUtil.mutListener.listen(49763) ? (aspectHeight % options.outHeight) : (ListenerUtil.mutListener.listen(49762) ? (aspectHeight * options.outHeight) : (ListenerUtil.mutListener.listen(49761) ? (aspectHeight - options.outHeight) : (ListenerUtil.mutListener.listen(49760) ? (aspectHeight + options.outHeight) : (aspectHeight / options.outHeight)))))) * options.outWidth)))));
                        }
                        if (!ListenerUtil.mutListener.listen(49790)) {
                            if ((ListenerUtil.mutListener.listen(49786) ? ((ListenerUtil.mutListener.listen(49780) ? (aspectHeight >= 0) : (ListenerUtil.mutListener.listen(49779) ? (aspectHeight <= 0) : (ListenerUtil.mutListener.listen(49778) ? (aspectHeight < 0) : (ListenerUtil.mutListener.listen(49777) ? (aspectHeight != 0) : (ListenerUtil.mutListener.listen(49776) ? (aspectHeight == 0) : (aspectHeight > 0)))))) || (ListenerUtil.mutListener.listen(49785) ? (aspectWidth >= 0) : (ListenerUtil.mutListener.listen(49784) ? (aspectWidth <= 0) : (ListenerUtil.mutListener.listen(49783) ? (aspectWidth < 0) : (ListenerUtil.mutListener.listen(49782) ? (aspectWidth != 0) : (ListenerUtil.mutListener.listen(49781) ? (aspectWidth == 0) : (aspectWidth > 0))))))) : ((ListenerUtil.mutListener.listen(49780) ? (aspectHeight >= 0) : (ListenerUtil.mutListener.listen(49779) ? (aspectHeight <= 0) : (ListenerUtil.mutListener.listen(49778) ? (aspectHeight < 0) : (ListenerUtil.mutListener.listen(49777) ? (aspectHeight != 0) : (ListenerUtil.mutListener.listen(49776) ? (aspectHeight == 0) : (aspectHeight > 0)))))) && (ListenerUtil.mutListener.listen(49785) ? (aspectWidth >= 0) : (ListenerUtil.mutListener.listen(49784) ? (aspectWidth <= 0) : (ListenerUtil.mutListener.listen(49783) ? (aspectWidth < 0) : (ListenerUtil.mutListener.listen(49782) ? (aspectWidth != 0) : (ListenerUtil.mutListener.listen(49781) ? (aspectWidth == 0) : (aspectWidth > 0))))))))) {
                                Bitmap scaledPhoto = Bitmap.createScaledBitmap(unscaledPhoto, (int) aspectWidth, (int) aspectHeight, true);
                                if (!ListenerUtil.mutListener.listen(49788)) {
                                    if (unscaledPhoto != scaledPhoto) {
                                        if (!ListenerUtil.mutListener.listen(49787)) {
                                            BitmapUtil.recycle(unscaledPhoto);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(49789)) {
                                    result = scaledPhoto;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(49795)) {
                    if ((ListenerUtil.mutListener.listen(49792) ? (replaceTransparency || result.hasAlpha()) : (replaceTransparency && result.hasAlpha()))) {
                        if (!ListenerUtil.mutListener.listen(49793)) {
                            logger.debug("Image has alpha channel, replace transparency with white");
                        }
                        if (!ListenerUtil.mutListener.listen(49794)) {
                            result = replaceTransparency(result, Color.WHITE);
                        }
                    }
                }
                return result;
            }
        }
        return null;
    }

    /**
     *  Replace transparency in bitmap with new color.
     *  After the replacement, the old bitmap will be recycled and cannot be reused.
     *
     *  @param in The bitmap to be processed
     *  @param color A color, e.g. Color.WHITE
     *  @return A new bitmap with transparency replaced by the specified color
     */
    public static Bitmap replaceTransparency(@NonNull Bitmap in, @ColorInt int color) {
        if (!ListenerUtil.mutListener.listen(49799)) {
            if (in.getConfig() != null) {
                final Bitmap out = Bitmap.createBitmap(in.getWidth(), in.getHeight(), in.getConfig());
                if (!ListenerUtil.mutListener.listen(49797)) {
                    out.eraseColor(color);
                }
                // create a canvas to draw on the new image
                Canvas canvas = new Canvas(out);
                if (!ListenerUtil.mutListener.listen(49798)) {
                    // draw old image on the background
                    canvas.drawBitmap(in, 0f, 0f, null);
                }
                return out;
            }
        }
        return in;
    }

    /**
     *  Resize a bitmap provided as a byte array so that the width of the resulting image is less or equal to maxWidth.
     *  For the sake of memory efficiency, we use subsampling which means the scaling is approximate and may only be a power of two.
     *
     *  @param sourceBitmapFileBytes compressed original image data
     *  @param maxWidth maximum width of the image after scaling is applied
     *  @param pos offset withing byte array
     *  @param length the number of bytes, beginning at offset, to parse
     *  @return compressed byte array of scaled bitmap in either PNG or JPG format - depending on source bitmap format
     */
    public static byte[] resizeBitmapByteArrayToMaxWidth(byte[] sourceBitmapFileBytes, int maxWidth, int pos, int length) {
        try {
            boolean isJpeg = ExifInterface.isJpegFormat(sourceBitmapFileBytes);
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            if (!ListenerUtil.mutListener.listen(49801)) {
                o2.inSampleSize = getInSampleSizeByWidth(sourceBitmapFileBytes, maxWidth);
            }
            if (!ListenerUtil.mutListener.listen(49802)) {
                o2.inScaled = true;
            }
            if (!ListenerUtil.mutListener.listen(49803)) {
                o2.inPreferredConfig = isJpeg ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
            }
            WeakReference<Bitmap> newPhoto = new WeakReference<>(BitmapFactory.decodeByteArray(sourceBitmapFileBytes, pos, length, o2));
            if (isJpeg) {
                return bitmapToJpegByteArray(newPhoto.get());
            } else {
                return bitmapToPngByteArray(newPhoto.get());
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(49800)) {
                logger.error("Exception", x);
            }
            return null;
        }
    }

    /**
     *  Resizes a given bitmap so that the width of the resulting image is less or equal to maxWidth while keeping the aspect ratio of the image
     *  Returns a new scaled bitmap or the original bitmap if it already fits into the bounding box
     *
     *  @param bitmap Bitmap to resize
     *  @param maxWidth Width of bounding box
     *  @return scaled and filtered bitmap, or existing bitmap
     */
    public static Bitmap resizeBitmapExactlyToMaxWidth(@NonNull Bitmap bitmap, int maxWidth) {
        if (!ListenerUtil.mutListener.listen(49809)) {
            if ((ListenerUtil.mutListener.listen(49808) ? (bitmap.getWidth() >= maxWidth) : (ListenerUtil.mutListener.listen(49807) ? (bitmap.getWidth() <= maxWidth) : (ListenerUtil.mutListener.listen(49806) ? (bitmap.getWidth() < maxWidth) : (ListenerUtil.mutListener.listen(49805) ? (bitmap.getWidth() != maxWidth) : (ListenerUtil.mutListener.listen(49804) ? (bitmap.getWidth() == maxWidth) : (bitmap.getWidth() > maxWidth))))))) {
                ThumbnailUtils.Size targetSize = getSizeFromTargetWidth(bitmap.getWidth(), bitmap.getHeight(), maxWidth);
                return Bitmap.createScaledBitmap(bitmap, targetSize.width, targetSize.height, true);
            }
        }
        return bitmap;
    }

    public static Bitmap resizeBitmap(Bitmap sourceBitmap, int width, int height) {
        SampleResult sampleSize = BitmapUtil.getSampleSize(sourceBitmap.getWidth(), sourceBitmap.getHeight(), width, height);
        Bitmap res = null;
        if (!ListenerUtil.mutListener.listen(49816)) {
            if ((ListenerUtil.mutListener.listen(49814) ? (sampleSize.inSampleSize >= 0) : (ListenerUtil.mutListener.listen(49813) ? (sampleSize.inSampleSize <= 0) : (ListenerUtil.mutListener.listen(49812) ? (sampleSize.inSampleSize < 0) : (ListenerUtil.mutListener.listen(49811) ? (sampleSize.inSampleSize != 0) : (ListenerUtil.mutListener.listen(49810) ? (sampleSize.inSampleSize == 0) : (sampleSize.inSampleSize > 0))))))) {
                try {
                    return Bitmap.createScaledBitmap(sourceBitmap, sampleSize.newWidth, sampleSize.newHeight, true);
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(49815)) {
                        logger.error("Exception", x);
                    }
                }
            }
        }
        return res;
    }

    public static class ExifOrientation {

        // flip first
        int flip;

        float rotation;

        public ExifOrientation(int flip, float rotation) {
            if (!ListenerUtil.mutListener.listen(49817)) {
                this.flip = flip;
            }
            if (!ListenerUtil.mutListener.listen(49818)) {
                this.rotation = rotation;
            }
        }

        @BitmapUtil.FlipType
        public int getFlip() {
            return flip;
        }

        public float getRotation() {
            return rotation;
        }
    }

    /**
     *  Get the rotation of an image by looking at its Exif data
     *  This should be called from a worker thread as it performs I/O operations
     *  @param context Context
     *  @param uri Uri of the image to be checked for rotation
     *  @return rotation in degrees
     */
    @SuppressLint("InlinedApi")
    @WorkerThread
    public static ExifOrientation getExifOrientation(Context context, Uri uri) {
        ExifOrientation retVal = new ExifOrientation(FLIP_NONE, 0);
        if (!ListenerUtil.mutListener.listen(49829)) {
            if (uri != null) {
                int orientation = ExifInterface.ORIENTATION_UNDEFINED;
                try {
                    try (InputStream inputStream = context.getContentResolver().openInputStream(uri)) {
                        if (!ListenerUtil.mutListener.listen(49823)) {
                            if (inputStream != null) {
                                ExifInterface exif = new ExifInterface(inputStream);
                                if (!ListenerUtil.mutListener.listen(49822)) {
                                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                                }
                            }
                        }
                    } catch (IOException | NegativeArraySizeException e) {
                        if (!ListenerUtil.mutListener.listen(49820)) {
                            logger.debug("Error checking exif");
                        }
                    } catch (SecurityException e) {
                        if (!ListenerUtil.mutListener.listen(49821)) {
                            logger.debug("Error checking exif: Permission denied");
                        }
                    }
                } catch (IllegalStateException e) {
                    if (!ListenerUtil.mutListener.listen(49819)) {
                        logger.debug("Error opening input stream");
                    }
                }
                if (!ListenerUtil.mutListener.listen(49828)) {
                    if (orientation != ExifInterface.ORIENTATION_UNDEFINED) {
                        return (exifOrientationToDegrees(orientation));
                    } else {
                        String[] projection = { MediaStore.Images.Media.ORIENTATION };
                        try (Cursor c = context.getContentResolver().query(uri, projection, null, null, null)) {
                            if (!ListenerUtil.mutListener.listen(49827)) {
                                if ((ListenerUtil.mutListener.listen(49825) ? (c != null || c.moveToFirst()) : (c != null && c.moveToFirst()))) {
                                    if (!ListenerUtil.mutListener.listen(49826)) {
                                        retVal.rotation = (float) c.getInt(0);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(49824)) {
                                logger.debug("No orientation column");
                            }
                        }
                    }
                }
            }
        }
        return retVal;
    }

    private static ExifOrientation exifOrientationToDegrees(int exifOrientation) {
        if (!ListenerUtil.mutListener.listen(49830)) {
            switch(exifOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return new ExifOrientation(FLIP_NONE, 90F);
                case // flip horizontally, rotate 90
                ExifInterface.ORIENTATION_TRANSVERSE:
                    return new ExifOrientation(FLIP_HORIZONTAL, 90F);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return new ExifOrientation(FLIP_NONE, 180F);
                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    return new ExifOrientation(FLIP_VERTICAL, 0F);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return new ExifOrientation(FLIP_NONE, 270F);
                case // flip horizontally, rotate 270
                ExifInterface.ORIENTATION_TRANSPOSE:
                    return new ExifOrientation(FLIP_HORIZONTAL, 270F);
                case ExifInterface.ORIENTATION_NORMAL:
                    return new ExifOrientation(FLIP_NONE, 0F);
                case // flip horizontally
                ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    return new ExifOrientation(FLIP_HORIZONTAL, 0F);
            }
        }
        return new ExifOrientation(FLIP_NONE, 0F);
    }

    public static void recycle(Bitmap bitmapToRecycle) {
        if (!ListenerUtil.mutListener.listen(49834)) {
            if ((ListenerUtil.mutListener.listen(49831) ? (bitmapToRecycle != null || !bitmapToRecycle.isRecycled()) : (bitmapToRecycle != null && !bitmapToRecycle.isRecycled()))) {
                if (!ListenerUtil.mutListener.listen(49832)) {
                    bitmapToRecycle.recycle();
                }
                if (!ListenerUtil.mutListener.listen(49833)) {
                    bitmapToRecycle = null;
                }
            }
        }
    }

    public static Bitmap tintImage(Bitmap bitmap, @ColorInt int color) {
        if (!ListenerUtil.mutListener.listen(49837)) {
            if (bitmap != null) {
                Paint paint = new Paint();
                if (!ListenerUtil.mutListener.listen(49835)) {
                    paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
                }
                Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapResult);
                if (!ListenerUtil.mutListener.listen(49836)) {
                    canvas.drawBitmap(bitmap, 0, 0, paint);
                }
                return bitmapResult;
            }
        }
        return bitmap;
    }

    public static Bitmap addOverlay(Bitmap background, Bitmap foreground, int offset) {
        try {
            int bgWidth = background.getWidth();
            int bgHeight = background.getHeight();
            Bitmap result = Bitmap.createBitmap(bgWidth, bgHeight, Bitmap.Config.ARGB_8888);
            Canvas cv = new Canvas(result);
            int x = (ListenerUtil.mutListener.listen(49845) ? (((ListenerUtil.mutListener.listen(49841) ? (bgWidth % foreground.getWidth()) : (ListenerUtil.mutListener.listen(49840) ? (bgWidth / foreground.getWidth()) : (ListenerUtil.mutListener.listen(49839) ? (bgWidth * foreground.getWidth()) : (ListenerUtil.mutListener.listen(49838) ? (bgWidth + foreground.getWidth()) : (bgWidth - foreground.getWidth())))))) % 2) : (ListenerUtil.mutListener.listen(49844) ? (((ListenerUtil.mutListener.listen(49841) ? (bgWidth % foreground.getWidth()) : (ListenerUtil.mutListener.listen(49840) ? (bgWidth / foreground.getWidth()) : (ListenerUtil.mutListener.listen(49839) ? (bgWidth * foreground.getWidth()) : (ListenerUtil.mutListener.listen(49838) ? (bgWidth + foreground.getWidth()) : (bgWidth - foreground.getWidth())))))) * 2) : (ListenerUtil.mutListener.listen(49843) ? (((ListenerUtil.mutListener.listen(49841) ? (bgWidth % foreground.getWidth()) : (ListenerUtil.mutListener.listen(49840) ? (bgWidth / foreground.getWidth()) : (ListenerUtil.mutListener.listen(49839) ? (bgWidth * foreground.getWidth()) : (ListenerUtil.mutListener.listen(49838) ? (bgWidth + foreground.getWidth()) : (bgWidth - foreground.getWidth())))))) - 2) : (ListenerUtil.mutListener.listen(49842) ? (((ListenerUtil.mutListener.listen(49841) ? (bgWidth % foreground.getWidth()) : (ListenerUtil.mutListener.listen(49840) ? (bgWidth / foreground.getWidth()) : (ListenerUtil.mutListener.listen(49839) ? (bgWidth * foreground.getWidth()) : (ListenerUtil.mutListener.listen(49838) ? (bgWidth + foreground.getWidth()) : (bgWidth - foreground.getWidth())))))) + 2) : (((ListenerUtil.mutListener.listen(49841) ? (bgWidth % foreground.getWidth()) : (ListenerUtil.mutListener.listen(49840) ? (bgWidth / foreground.getWidth()) : (ListenerUtil.mutListener.listen(49839) ? (bgWidth * foreground.getWidth()) : (ListenerUtil.mutListener.listen(49838) ? (bgWidth + foreground.getWidth()) : (bgWidth - foreground.getWidth())))))) / 2)))));
            int y = (ListenerUtil.mutListener.listen(49853) ? (((ListenerUtil.mutListener.listen(49849) ? (bgHeight % foreground.getHeight()) : (ListenerUtil.mutListener.listen(49848) ? (bgHeight / foreground.getHeight()) : (ListenerUtil.mutListener.listen(49847) ? (bgHeight * foreground.getHeight()) : (ListenerUtil.mutListener.listen(49846) ? (bgHeight + foreground.getHeight()) : (bgHeight - foreground.getHeight())))))) % 2) : (ListenerUtil.mutListener.listen(49852) ? (((ListenerUtil.mutListener.listen(49849) ? (bgHeight % foreground.getHeight()) : (ListenerUtil.mutListener.listen(49848) ? (bgHeight / foreground.getHeight()) : (ListenerUtil.mutListener.listen(49847) ? (bgHeight * foreground.getHeight()) : (ListenerUtil.mutListener.listen(49846) ? (bgHeight + foreground.getHeight()) : (bgHeight - foreground.getHeight())))))) * 2) : (ListenerUtil.mutListener.listen(49851) ? (((ListenerUtil.mutListener.listen(49849) ? (bgHeight % foreground.getHeight()) : (ListenerUtil.mutListener.listen(49848) ? (bgHeight / foreground.getHeight()) : (ListenerUtil.mutListener.listen(49847) ? (bgHeight * foreground.getHeight()) : (ListenerUtil.mutListener.listen(49846) ? (bgHeight + foreground.getHeight()) : (bgHeight - foreground.getHeight())))))) - 2) : (ListenerUtil.mutListener.listen(49850) ? (((ListenerUtil.mutListener.listen(49849) ? (bgHeight % foreground.getHeight()) : (ListenerUtil.mutListener.listen(49848) ? (bgHeight / foreground.getHeight()) : (ListenerUtil.mutListener.listen(49847) ? (bgHeight * foreground.getHeight()) : (ListenerUtil.mutListener.listen(49846) ? (bgHeight + foreground.getHeight()) : (bgHeight - foreground.getHeight())))))) + 2) : (((ListenerUtil.mutListener.listen(49849) ? (bgHeight % foreground.getHeight()) : (ListenerUtil.mutListener.listen(49848) ? (bgHeight / foreground.getHeight()) : (ListenerUtil.mutListener.listen(49847) ? (bgHeight * foreground.getHeight()) : (ListenerUtil.mutListener.listen(49846) ? (bgHeight + foreground.getHeight()) : (bgHeight - foreground.getHeight())))))) / 2)))));
            if (!ListenerUtil.mutListener.listen(49854)) {
                cv.drawBitmap(background, 0, 0, null);
            }
            if (!ListenerUtil.mutListener.listen(49863)) {
                cv.drawBitmap(BitmapUtil.tintImage(foreground, Color.GRAY), (ListenerUtil.mutListener.listen(49858) ? (x % offset) : (ListenerUtil.mutListener.listen(49857) ? (x / offset) : (ListenerUtil.mutListener.listen(49856) ? (x * offset) : (ListenerUtil.mutListener.listen(49855) ? (x - offset) : (x + offset))))), (ListenerUtil.mutListener.listen(49862) ? (y % offset) : (ListenerUtil.mutListener.listen(49861) ? (y / offset) : (ListenerUtil.mutListener.listen(49860) ? (y * offset) : (ListenerUtil.mutListener.listen(49859) ? (y - offset) : (y + offset))))), null);
            }
            if (!ListenerUtil.mutListener.listen(49864)) {
                cv.drawBitmap(BitmapUtil.tintImage(foreground, Color.WHITE), x, y, null);
            }
            if (!ListenerUtil.mutListener.listen(49865)) {
                cv.save();
            }
            if (!ListenerUtil.mutListener.listen(49866)) {
                cv.restore();
            }
            return result;
        } catch (Exception e) {
            return background;
        }
    }

    public static Bitmap cropToSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = ((ListenerUtil.mutListener.listen(49871) ? (height >= width) : (ListenerUtil.mutListener.listen(49870) ? (height <= width) : (ListenerUtil.mutListener.listen(49869) ? (height < width) : (ListenerUtil.mutListener.listen(49868) ? (height != width) : (ListenerUtil.mutListener.listen(49867) ? (height == width) : (height > width))))))) ? width : height;
        int newHeight = ((ListenerUtil.mutListener.listen(49876) ? (height >= width) : (ListenerUtil.mutListener.listen(49875) ? (height <= width) : (ListenerUtil.mutListener.listen(49874) ? (height < width) : (ListenerUtil.mutListener.listen(49873) ? (height != width) : (ListenerUtil.mutListener.listen(49872) ? (height == width) : (height > width))))))) ? (ListenerUtil.mutListener.listen(49884) ? (height % ((ListenerUtil.mutListener.listen(49880) ? (height % width) : (ListenerUtil.mutListener.listen(49879) ? (height / width) : (ListenerUtil.mutListener.listen(49878) ? (height * width) : (ListenerUtil.mutListener.listen(49877) ? (height + width) : (height - width))))))) : (ListenerUtil.mutListener.listen(49883) ? (height / ((ListenerUtil.mutListener.listen(49880) ? (height % width) : (ListenerUtil.mutListener.listen(49879) ? (height / width) : (ListenerUtil.mutListener.listen(49878) ? (height * width) : (ListenerUtil.mutListener.listen(49877) ? (height + width) : (height - width))))))) : (ListenerUtil.mutListener.listen(49882) ? (height * ((ListenerUtil.mutListener.listen(49880) ? (height % width) : (ListenerUtil.mutListener.listen(49879) ? (height / width) : (ListenerUtil.mutListener.listen(49878) ? (height * width) : (ListenerUtil.mutListener.listen(49877) ? (height + width) : (height - width))))))) : (ListenerUtil.mutListener.listen(49881) ? (height + ((ListenerUtil.mutListener.listen(49880) ? (height % width) : (ListenerUtil.mutListener.listen(49879) ? (height / width) : (ListenerUtil.mutListener.listen(49878) ? (height * width) : (ListenerUtil.mutListener.listen(49877) ? (height + width) : (height - width))))))) : (height - ((ListenerUtil.mutListener.listen(49880) ? (height % width) : (ListenerUtil.mutListener.listen(49879) ? (height / width) : (ListenerUtil.mutListener.listen(49878) ? (height * width) : (ListenerUtil.mutListener.listen(49877) ? (height + width) : (height - width))))))))))) : height;
        int cropW = (ListenerUtil.mutListener.listen(49892) ? (((ListenerUtil.mutListener.listen(49888) ? (width % height) : (ListenerUtil.mutListener.listen(49887) ? (width / height) : (ListenerUtil.mutListener.listen(49886) ? (width * height) : (ListenerUtil.mutListener.listen(49885) ? (width + height) : (width - height)))))) % 2) : (ListenerUtil.mutListener.listen(49891) ? (((ListenerUtil.mutListener.listen(49888) ? (width % height) : (ListenerUtil.mutListener.listen(49887) ? (width / height) : (ListenerUtil.mutListener.listen(49886) ? (width * height) : (ListenerUtil.mutListener.listen(49885) ? (width + height) : (width - height)))))) * 2) : (ListenerUtil.mutListener.listen(49890) ? (((ListenerUtil.mutListener.listen(49888) ? (width % height) : (ListenerUtil.mutListener.listen(49887) ? (width / height) : (ListenerUtil.mutListener.listen(49886) ? (width * height) : (ListenerUtil.mutListener.listen(49885) ? (width + height) : (width - height)))))) - 2) : (ListenerUtil.mutListener.listen(49889) ? (((ListenerUtil.mutListener.listen(49888) ? (width % height) : (ListenerUtil.mutListener.listen(49887) ? (width / height) : (ListenerUtil.mutListener.listen(49886) ? (width * height) : (ListenerUtil.mutListener.listen(49885) ? (width + height) : (width - height)))))) + 2) : (((ListenerUtil.mutListener.listen(49888) ? (width % height) : (ListenerUtil.mutListener.listen(49887) ? (width / height) : (ListenerUtil.mutListener.listen(49886) ? (width * height) : (ListenerUtil.mutListener.listen(49885) ? (width + height) : (width - height)))))) / 2)))));
        int cropH = (ListenerUtil.mutListener.listen(49900) ? (((ListenerUtil.mutListener.listen(49896) ? (height % width) : (ListenerUtil.mutListener.listen(49895) ? (height / width) : (ListenerUtil.mutListener.listen(49894) ? (height * width) : (ListenerUtil.mutListener.listen(49893) ? (height + width) : (height - width)))))) % 2) : (ListenerUtil.mutListener.listen(49899) ? (((ListenerUtil.mutListener.listen(49896) ? (height % width) : (ListenerUtil.mutListener.listen(49895) ? (height / width) : (ListenerUtil.mutListener.listen(49894) ? (height * width) : (ListenerUtil.mutListener.listen(49893) ? (height + width) : (height - width)))))) * 2) : (ListenerUtil.mutListener.listen(49898) ? (((ListenerUtil.mutListener.listen(49896) ? (height % width) : (ListenerUtil.mutListener.listen(49895) ? (height / width) : (ListenerUtil.mutListener.listen(49894) ? (height * width) : (ListenerUtil.mutListener.listen(49893) ? (height + width) : (height - width)))))) - 2) : (ListenerUtil.mutListener.listen(49897) ? (((ListenerUtil.mutListener.listen(49896) ? (height % width) : (ListenerUtil.mutListener.listen(49895) ? (height / width) : (ListenerUtil.mutListener.listen(49894) ? (height * width) : (ListenerUtil.mutListener.listen(49893) ? (height + width) : (height - width)))))) + 2) : (((ListenerUtil.mutListener.listen(49896) ? (height % width) : (ListenerUtil.mutListener.listen(49895) ? (height / width) : (ListenerUtil.mutListener.listen(49894) ? (height * width) : (ListenerUtil.mutListener.listen(49893) ? (height + width) : (height - width)))))) / 2)))));
        if (!ListenerUtil.mutListener.listen(49906)) {
            cropW = ((ListenerUtil.mutListener.listen(49905) ? (cropW >= 0) : (ListenerUtil.mutListener.listen(49904) ? (cropW <= 0) : (ListenerUtil.mutListener.listen(49903) ? (cropW > 0) : (ListenerUtil.mutListener.listen(49902) ? (cropW != 0) : (ListenerUtil.mutListener.listen(49901) ? (cropW == 0) : (cropW < 0))))))) ? 0 : cropW;
        }
        if (!ListenerUtil.mutListener.listen(49912)) {
            cropH = ((ListenerUtil.mutListener.listen(49911) ? (cropH >= 0) : (ListenerUtil.mutListener.listen(49910) ? (cropH <= 0) : (ListenerUtil.mutListener.listen(49909) ? (cropH > 0) : (ListenerUtil.mutListener.listen(49908) ? (cropH != 0) : (ListenerUtil.mutListener.listen(49907) ? (cropH == 0) : (cropH < 0))))))) ? 0 : cropH;
        }
        return Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight);
    }

    @Nullable
    public static Bitmap getBitmapFromVectorDrawable(Drawable icon, Integer tintColor) {
        Bitmap bitmap = null;
        if (!ListenerUtil.mutListener.listen(49919)) {
            if (icon instanceof BitmapDrawable) {
                if (!ListenerUtil.mutListener.listen(49918)) {
                    bitmap = ((BitmapDrawable) icon).getBitmap();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(49913)) {
                    // e.g. VectorDrawable or AdaptiveIconDrawable
                    bitmap = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                }
                final Canvas canvas = new Canvas(bitmap);
                if (!ListenerUtil.mutListener.listen(49914)) {
                    icon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                }
                if (!ListenerUtil.mutListener.listen(49916)) {
                    if (tintColor != null) {
                        if (!ListenerUtil.mutListener.listen(49915)) {
                            icon.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(49917)) {
                    icon.draw(canvas);
                }
            }
        }
        return bitmap;
    }

    /**
     *  Get size where the width is always smaller or equal to maxWidth,
     *  resizing height if necessary to preserve aspect ratio.
     *
     *  @param inWidth original width
     *  @param inHeight original height
     *  @param maxWidth target width max
     *  @return target size where width is exactly maxWidth or smaller if inWidth is already smaller
     */
    private static ThumbnailUtils.Size getSizeFromTargetWidth(final int inWidth, final int inHeight, final int maxWidth) {
        if (!ListenerUtil.mutListener.listen(49933)) {
            if ((ListenerUtil.mutListener.listen(49924) ? (inWidth >= maxWidth) : (ListenerUtil.mutListener.listen(49923) ? (inWidth <= maxWidth) : (ListenerUtil.mutListener.listen(49922) ? (inWidth < maxWidth) : (ListenerUtil.mutListener.listen(49921) ? (inWidth != maxWidth) : (ListenerUtil.mutListener.listen(49920) ? (inWidth == maxWidth) : (inWidth > maxWidth))))))) {
                float aspectRatio = (ListenerUtil.mutListener.listen(49928) ? ((float) inWidth % (float) maxWidth) : (ListenerUtil.mutListener.listen(49927) ? ((float) inWidth * (float) maxWidth) : (ListenerUtil.mutListener.listen(49926) ? ((float) inWidth - (float) maxWidth) : (ListenerUtil.mutListener.listen(49925) ? ((float) inWidth + (float) maxWidth) : ((float) inWidth / (float) maxWidth)))));
                return new ThumbnailUtils.Size(maxWidth, Math.round((ListenerUtil.mutListener.listen(49932) ? ((float) inHeight % aspectRatio) : (ListenerUtil.mutListener.listen(49931) ? ((float) inHeight * aspectRatio) : (ListenerUtil.mutListener.listen(49930) ? ((float) inHeight - aspectRatio) : (ListenerUtil.mutListener.listen(49929) ? ((float) inHeight + aspectRatio) : ((float) inHeight / aspectRatio)))))));
            }
        }
        return new ThumbnailUtils.Size(inWidth, inHeight);
    }

    /**
     *  Check a bitmap for the presence of transparency
     *  For the sake of speed we only check the topmost left pixel...
     *  @param bitmap
     *  @return true if the topmost left pixel is transparent, false otherwise
     */
    public static boolean hasTransparency(@NonNull Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(49951)) {
            if (bitmap.hasAlpha()) {
                if (!ListenerUtil.mutListener.listen(49950)) {
                    if ((ListenerUtil.mutListener.listen(49944) ? ((ListenerUtil.mutListener.listen(49938) ? (bitmap.getWidth() >= 0) : (ListenerUtil.mutListener.listen(49937) ? (bitmap.getWidth() <= 0) : (ListenerUtil.mutListener.listen(49936) ? (bitmap.getWidth() < 0) : (ListenerUtil.mutListener.listen(49935) ? (bitmap.getWidth() != 0) : (ListenerUtil.mutListener.listen(49934) ? (bitmap.getWidth() == 0) : (bitmap.getWidth() > 0)))))) || (ListenerUtil.mutListener.listen(49943) ? (bitmap.getHeight() >= 0) : (ListenerUtil.mutListener.listen(49942) ? (bitmap.getHeight() <= 0) : (ListenerUtil.mutListener.listen(49941) ? (bitmap.getHeight() < 0) : (ListenerUtil.mutListener.listen(49940) ? (bitmap.getHeight() != 0) : (ListenerUtil.mutListener.listen(49939) ? (bitmap.getHeight() == 0) : (bitmap.getHeight() > 0))))))) : ((ListenerUtil.mutListener.listen(49938) ? (bitmap.getWidth() >= 0) : (ListenerUtil.mutListener.listen(49937) ? (bitmap.getWidth() <= 0) : (ListenerUtil.mutListener.listen(49936) ? (bitmap.getWidth() < 0) : (ListenerUtil.mutListener.listen(49935) ? (bitmap.getWidth() != 0) : (ListenerUtil.mutListener.listen(49934) ? (bitmap.getWidth() == 0) : (bitmap.getWidth() > 0)))))) && (ListenerUtil.mutListener.listen(49943) ? (bitmap.getHeight() >= 0) : (ListenerUtil.mutListener.listen(49942) ? (bitmap.getHeight() <= 0) : (ListenerUtil.mutListener.listen(49941) ? (bitmap.getHeight() < 0) : (ListenerUtil.mutListener.listen(49940) ? (bitmap.getHeight() != 0) : (ListenerUtil.mutListener.listen(49939) ? (bitmap.getHeight() == 0) : (bitmap.getHeight() > 0))))))))) {
                        int pixel = bitmap.getPixel(0, 0);
                        return (ListenerUtil.mutListener.listen(49949) ? ((pixel & 0xff000000) >= 0x0) : (ListenerUtil.mutListener.listen(49948) ? ((pixel & 0xff000000) <= 0x0) : (ListenerUtil.mutListener.listen(49947) ? ((pixel & 0xff000000) > 0x0) : (ListenerUtil.mutListener.listen(49946) ? ((pixel & 0xff000000) < 0x0) : (ListenerUtil.mutListener.listen(49945) ? ((pixel & 0xff000000) != 0x0) : ((pixel & 0xff000000) == 0x0))))));
                    }
                }
            }
        }
        return false;
    }
}

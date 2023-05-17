/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import static android.graphics.Bitmap.Config.ARGB_8888;
import static android.graphics.Color.WHITE;
import static android.graphics.PorterDuff.Mode.DST_IN;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Image utilities
 */
public class ImageUtils {

    private static final String TAG = "ImageUtils";

    /**
     * Get a bitmap from the image path
     *
     * @param imagePath
     * @return bitmap or null if read fails
     */
    public static Bitmap getBitmap(final String imagePath) {
        return getBitmap(imagePath, 1);
    }

    /**
     * Get a bitmap from the image path
     *
     * @param imagePath
     * @param sampleSize
     * @return bitmap or null if read fails
     */
    public static Bitmap getBitmap(final String imagePath, int sampleSize) {
        final Options options = new Options();
        if (!ListenerUtil.mutListener.listen(1752)) {
            options.inDither = false;
        }
        if (!ListenerUtil.mutListener.listen(1753)) {
            options.inSampleSize = sampleSize;
        }
        RandomAccessFile file = null;
        try {
            if (!ListenerUtil.mutListener.listen(1758)) {
                file = new RandomAccessFile(imagePath, "r");
            }
            return BitmapFactory.decodeFileDescriptor(file.getFD(), null, options);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(1754)) {
                Log.d(TAG, e.getMessage(), e);
            }
            return null;
        } finally {
            if (!ListenerUtil.mutListener.listen(1757)) {
                if (file != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1756)) {
                            file.close();
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(1755)) {
                            Log.d(TAG, e.getMessage(), e);
                        }
                    }
                }
            }
        }
    }

    /**
     * Get a bitmap from the image
     *
     * @param image
     * @param sampleSize
     * @return bitmap or null if read fails
     */
    public static Bitmap getBitmap(final byte[] image, int sampleSize) {
        final Options options = new Options();
        if (!ListenerUtil.mutListener.listen(1759)) {
            options.inDither = false;
        }
        if (!ListenerUtil.mutListener.listen(1760)) {
            options.inSampleSize = sampleSize;
        }
        return BitmapFactory.decodeByteArray(image, 0, image.length, options);
    }

    /**
     * Get scale for image of size and max height/width
     *
     * @param size
     * @param width
     * @param height
     * @return scale
     */
    public static int getScale(Point size, int width, int height) {
        if ((ListenerUtil.mutListener.listen(1771) ? ((ListenerUtil.mutListener.listen(1765) ? (size.x >= width) : (ListenerUtil.mutListener.listen(1764) ? (size.x <= width) : (ListenerUtil.mutListener.listen(1763) ? (size.x < width) : (ListenerUtil.mutListener.listen(1762) ? (size.x != width) : (ListenerUtil.mutListener.listen(1761) ? (size.x == width) : (size.x > width)))))) && (ListenerUtil.mutListener.listen(1770) ? (size.y >= height) : (ListenerUtil.mutListener.listen(1769) ? (size.y <= height) : (ListenerUtil.mutListener.listen(1768) ? (size.y < height) : (ListenerUtil.mutListener.listen(1767) ? (size.y != height) : (ListenerUtil.mutListener.listen(1766) ? (size.y == height) : (size.y > height))))))) : ((ListenerUtil.mutListener.listen(1765) ? (size.x >= width) : (ListenerUtil.mutListener.listen(1764) ? (size.x <= width) : (ListenerUtil.mutListener.listen(1763) ? (size.x < width) : (ListenerUtil.mutListener.listen(1762) ? (size.x != width) : (ListenerUtil.mutListener.listen(1761) ? (size.x == width) : (size.x > width)))))) || (ListenerUtil.mutListener.listen(1770) ? (size.y >= height) : (ListenerUtil.mutListener.listen(1769) ? (size.y <= height) : (ListenerUtil.mutListener.listen(1768) ? (size.y < height) : (ListenerUtil.mutListener.listen(1767) ? (size.y != height) : (ListenerUtil.mutListener.listen(1766) ? (size.y == height) : (size.y > height))))))))) {
            return Math.max(Math.round((ListenerUtil.mutListener.listen(1775) ? ((float) size.y % (float) height) : (ListenerUtil.mutListener.listen(1774) ? ((float) size.y * (float) height) : (ListenerUtil.mutListener.listen(1773) ? ((float) size.y - (float) height) : (ListenerUtil.mutListener.listen(1772) ? ((float) size.y + (float) height) : ((float) size.y / (float) height)))))), Math.round((ListenerUtil.mutListener.listen(1779) ? ((float) size.x % (float) width) : (ListenerUtil.mutListener.listen(1778) ? ((float) size.x * (float) width) : (ListenerUtil.mutListener.listen(1777) ? ((float) size.x - (float) width) : (ListenerUtil.mutListener.listen(1776) ? ((float) size.x + (float) width) : ((float) size.x / (float) width)))))));
        } else {
            return 1;
        }
    }

    /**
     * Get size of image
     *
     * @param imagePath
     * @return size
     */
    public static Point getSize(final String imagePath) {
        final Options options = new Options();
        if (!ListenerUtil.mutListener.listen(1780)) {
            options.inJustDecodeBounds = true;
        }
        RandomAccessFile file = null;
        try {
            if (!ListenerUtil.mutListener.listen(1785)) {
                file = new RandomAccessFile(imagePath, "r");
            }
            if (!ListenerUtil.mutListener.listen(1786)) {
                BitmapFactory.decodeFileDescriptor(file.getFD(), null, options);
            }
            return new Point(options.outWidth, options.outHeight);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(1781)) {
                Log.d(TAG, e.getMessage(), e);
            }
            return null;
        } finally {
            if (!ListenerUtil.mutListener.listen(1784)) {
                if (file != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1783)) {
                            file.close();
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(1782)) {
                            Log.d(TAG, e.getMessage(), e);
                        }
                    }
                }
            }
        }
    }

    /**
     * Get size of image
     *
     * @param image
     * @return size
     */
    public static Point getSize(final byte[] image) {
        final Options options = new Options();
        if (!ListenerUtil.mutListener.listen(1787)) {
            options.inJustDecodeBounds = true;
        }
        if (!ListenerUtil.mutListener.listen(1788)) {
            BitmapFactory.decodeByteArray(image, 0, image.length, options);
        }
        return new Point(options.outWidth, options.outHeight);
    }

    /**
     * Get bitmap with maximum height or width
     *
     * @param imagePath
     * @param width
     * @param height
     * @return image
     */
    public static Bitmap getBitmap(final String imagePath, int width, int height) {
        Point size = getSize(imagePath);
        return getBitmap(imagePath, getScale(size, width, height));
    }

    /**
     * Get bitmap with maximum height or width
     *
     * @param image
     * @param width
     * @param height
     * @return image
     */
    public static Bitmap getBitmap(final byte[] image, int width, int height) {
        Point size = getSize(image);
        return getBitmap(image, getScale(size, width, height));
    }

    /**
     * Get bitmap with maximum height or width
     *
     * @param image
     * @param width
     * @param height
     * @return image
     */
    public static Bitmap getBitmap(final File image, int width, int height) {
        return getBitmap(image.getAbsolutePath(), width, height);
    }

    /**
     * Get a bitmap from the image file
     *
     * @param image
     * @return bitmap or null if read fails
     */
    public static Bitmap getBitmap(final File image) {
        return getBitmap(image.getAbsolutePath());
    }

    /**
     * Load a {@link Bitmap} from the given path and set it on the given
     * {@link ImageView}
     *
     * @param imagePath
     * @param view
     */
    public static void setImage(final String imagePath, final ImageView view) {
        if (!ListenerUtil.mutListener.listen(1789)) {
            setImage(new File(imagePath), view);
        }
    }

    /**
     * Load a {@link Bitmap} from the given {@link File} and set it on the given
     * {@link ImageView}
     *
     * @param image
     * @param view
     */
    public static void setImage(final File image, final ImageView view) {
        Bitmap bitmap = getBitmap(image);
        if (!ListenerUtil.mutListener.listen(1791)) {
            if (bitmap != null) {
                if (!ListenerUtil.mutListener.listen(1790)) {
                    view.setImageBitmap(bitmap);
                }
            }
        }
    }

    /**
     * Round the corners of a {@link Bitmap}
     *
     * @param source
     * @param radius
     * @return rounded corner bitmap
     */
    public static Bitmap roundCorners(final Bitmap source, final float radius) {
        int width = source.getWidth();
        int height = source.getHeight();
        Paint paint = new Paint();
        if (!ListenerUtil.mutListener.listen(1792)) {
            paint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(1793)) {
            paint.setColor(WHITE);
        }
        Bitmap clipped = Bitmap.createBitmap(width, height, ARGB_8888);
        Canvas canvas = new Canvas(clipped);
        if (!ListenerUtil.mutListener.listen(1794)) {
            canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, paint);
        }
        if (!ListenerUtil.mutListener.listen(1795)) {
            paint.setXfermode(new PorterDuffXfermode(DST_IN));
        }
        Bitmap rounded = Bitmap.createBitmap(width, height, ARGB_8888);
        if (!ListenerUtil.mutListener.listen(1796)) {
            canvas = new Canvas(rounded);
        }
        if (!ListenerUtil.mutListener.listen(1797)) {
            canvas.drawBitmap(source, 0, 0, null);
        }
        if (!ListenerUtil.mutListener.listen(1798)) {
            canvas.drawBitmap(clipped, 0, 0, paint);
        }
        if (!ListenerUtil.mutListener.listen(1799)) {
            source.recycle();
        }
        if (!ListenerUtil.mutListener.listen(1800)) {
            clipped.recycle();
        }
        return rounded;
    }
}

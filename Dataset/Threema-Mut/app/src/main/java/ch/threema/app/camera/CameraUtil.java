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
package ch.threema.app.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Size;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CameraUtil {

    // list of MAX_QUALITY compatible cameras
    private static final HashSet<String> MAX_QUALITY_CAMERAS = new HashSet<String>() {

        {
            if (!ListenerUtil.mutListener.listen(11612)) {
                add("Pixel 2");
            }
            if (!ListenerUtil.mutListener.listen(11613)) {
                add("Pixel 2 XL");
            }
            if (!ListenerUtil.mutListener.listen(11614)) {
                add("Pixel 3");
            }
            if (!ListenerUtil.mutListener.listen(11615)) {
                add("Pixel 3 XL");
            }
            if (!ListenerUtil.mutListener.listen(11616)) {
                add("Pixel 3a");
            }
            if (!ListenerUtil.mutListener.listen(11617)) {
                add("Pixel 3a XL");
            }
        }
    };

    // list of cameras that are incompatible with camerax
    private static final HashSet<String> BLACKLISTED_CAMERAS = new HashSet<String>() {

        {
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static byte[] transformByteArray(@NonNull byte[] data, Rect cropRect, int rotation, boolean flip) throws IOException {
        Bitmap in = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (!ListenerUtil.mutListener.listen(11618)) {
            options.inJustDecodeBounds = true;
        }
        if (!ListenerUtil.mutListener.listen(11619)) {
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }
        if (!ListenerUtil.mutListener.listen(11635)) {
            if ((ListenerUtil.mutListener.listen(11631) ? ((ListenerUtil.mutListener.listen(11625) ? (cropRect != null || (ListenerUtil.mutListener.listen(11624) ? (cropRect.width() >= options.outWidth) : (ListenerUtil.mutListener.listen(11623) ? (cropRect.width() > options.outWidth) : (ListenerUtil.mutListener.listen(11622) ? (cropRect.width() < options.outWidth) : (ListenerUtil.mutListener.listen(11621) ? (cropRect.width() != options.outWidth) : (ListenerUtil.mutListener.listen(11620) ? (cropRect.width() == options.outWidth) : (cropRect.width() <= options.outWidth))))))) : (cropRect != null && (ListenerUtil.mutListener.listen(11624) ? (cropRect.width() >= options.outWidth) : (ListenerUtil.mutListener.listen(11623) ? (cropRect.width() > options.outWidth) : (ListenerUtil.mutListener.listen(11622) ? (cropRect.width() < options.outWidth) : (ListenerUtil.mutListener.listen(11621) ? (cropRect.width() != options.outWidth) : (ListenerUtil.mutListener.listen(11620) ? (cropRect.width() == options.outWidth) : (cropRect.width() <= options.outWidth)))))))) || (ListenerUtil.mutListener.listen(11630) ? (cropRect.height() >= options.outHeight) : (ListenerUtil.mutListener.listen(11629) ? (cropRect.height() > options.outHeight) : (ListenerUtil.mutListener.listen(11628) ? (cropRect.height() < options.outHeight) : (ListenerUtil.mutListener.listen(11627) ? (cropRect.height() != options.outHeight) : (ListenerUtil.mutListener.listen(11626) ? (cropRect.height() == options.outHeight) : (cropRect.height() <= options.outHeight))))))) : ((ListenerUtil.mutListener.listen(11625) ? (cropRect != null || (ListenerUtil.mutListener.listen(11624) ? (cropRect.width() >= options.outWidth) : (ListenerUtil.mutListener.listen(11623) ? (cropRect.width() > options.outWidth) : (ListenerUtil.mutListener.listen(11622) ? (cropRect.width() < options.outWidth) : (ListenerUtil.mutListener.listen(11621) ? (cropRect.width() != options.outWidth) : (ListenerUtil.mutListener.listen(11620) ? (cropRect.width() == options.outWidth) : (cropRect.width() <= options.outWidth))))))) : (cropRect != null && (ListenerUtil.mutListener.listen(11624) ? (cropRect.width() >= options.outWidth) : (ListenerUtil.mutListener.listen(11623) ? (cropRect.width() > options.outWidth) : (ListenerUtil.mutListener.listen(11622) ? (cropRect.width() < options.outWidth) : (ListenerUtil.mutListener.listen(11621) ? (cropRect.width() != options.outWidth) : (ListenerUtil.mutListener.listen(11620) ? (cropRect.width() == options.outWidth) : (cropRect.width() <= options.outWidth)))))))) && (ListenerUtil.mutListener.listen(11630) ? (cropRect.height() >= options.outHeight) : (ListenerUtil.mutListener.listen(11629) ? (cropRect.height() > options.outHeight) : (ListenerUtil.mutListener.listen(11628) ? (cropRect.height() < options.outHeight) : (ListenerUtil.mutListener.listen(11627) ? (cropRect.height() != options.outHeight) : (ListenerUtil.mutListener.listen(11626) ? (cropRect.height() == options.outHeight) : (cropRect.height() <= options.outHeight))))))))) {
                BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(data, 0, data.length, false);
                if (!ListenerUtil.mutListener.listen(11633)) {
                    in = decoder.decodeRegion(cropRect, new BitmapFactory.Options());
                }
                if (!ListenerUtil.mutListener.listen(11634)) {
                    decoder.recycle();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11632)) {
                    in = BitmapFactory.decodeByteArray(data, 0, data.length);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11650)) {
            if (in != null) {
                Bitmap out = in;
                if (!ListenerUtil.mutListener.listen(11647)) {
                    if ((ListenerUtil.mutListener.listen(11641) ? ((ListenerUtil.mutListener.listen(11640) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(11639) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(11638) ? (rotation > 0) : (ListenerUtil.mutListener.listen(11637) ? (rotation < 0) : (ListenerUtil.mutListener.listen(11636) ? (rotation == 0) : (rotation != 0)))))) && flip) : ((ListenerUtil.mutListener.listen(11640) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(11639) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(11638) ? (rotation > 0) : (ListenerUtil.mutListener.listen(11637) ? (rotation < 0) : (ListenerUtil.mutListener.listen(11636) ? (rotation == 0) : (rotation != 0)))))) || flip))) {
                        Matrix matrix = new Matrix();
                        if (!ListenerUtil.mutListener.listen(11642)) {
                            matrix.postRotate(rotation);
                        }
                        if (!ListenerUtil.mutListener.listen(11645)) {
                            if (flip) {
                                if (!ListenerUtil.mutListener.listen(11643)) {
                                    matrix.postScale(-1, 1);
                                }
                                if (!ListenerUtil.mutListener.listen(11644)) {
                                    matrix.postTranslate(in.getWidth(), 0);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11646)) {
                            out = Bitmap.createBitmap(in, 0, 0, in.getWidth(), in.getHeight(), matrix, true);
                        }
                    }
                }
                byte[] transformedData = toJpegBytes(out);
                if (!ListenerUtil.mutListener.listen(11648)) {
                    in.recycle();
                }
                if (!ListenerUtil.mutListener.listen(11649)) {
                    out.recycle();
                }
                return transformedData;
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static byte[] getJpegBytes(@NonNull ImageProxy image, int rotation, boolean flip) throws IOException {
        ImageProxy.PlaneProxy[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        Rect cropRect = shouldCropImage(image) ? image.getCropRect() : null;
        byte[] data = new byte[buffer.capacity()];
        if (!ListenerUtil.mutListener.listen(11651)) {
            buffer.get(data);
        }
        if (!ListenerUtil.mutListener.listen(11660)) {
            if ((ListenerUtil.mutListener.listen(11658) ? ((ListenerUtil.mutListener.listen(11657) ? (cropRect != null && (ListenerUtil.mutListener.listen(11656) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(11655) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(11654) ? (rotation > 0) : (ListenerUtil.mutListener.listen(11653) ? (rotation < 0) : (ListenerUtil.mutListener.listen(11652) ? (rotation == 0) : (rotation != 0))))))) : (cropRect != null || (ListenerUtil.mutListener.listen(11656) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(11655) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(11654) ? (rotation > 0) : (ListenerUtil.mutListener.listen(11653) ? (rotation < 0) : (ListenerUtil.mutListener.listen(11652) ? (rotation == 0) : (rotation != 0)))))))) && flip) : ((ListenerUtil.mutListener.listen(11657) ? (cropRect != null && (ListenerUtil.mutListener.listen(11656) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(11655) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(11654) ? (rotation > 0) : (ListenerUtil.mutListener.listen(11653) ? (rotation < 0) : (ListenerUtil.mutListener.listen(11652) ? (rotation == 0) : (rotation != 0))))))) : (cropRect != null || (ListenerUtil.mutListener.listen(11656) ? (rotation >= 0) : (ListenerUtil.mutListener.listen(11655) ? (rotation <= 0) : (ListenerUtil.mutListener.listen(11654) ? (rotation > 0) : (ListenerUtil.mutListener.listen(11653) ? (rotation < 0) : (ListenerUtil.mutListener.listen(11652) ? (rotation == 0) : (rotation != 0)))))))) || flip))) {
                if (!ListenerUtil.mutListener.listen(11659)) {
                    data = transformByteArray(data, cropRect, rotation, flip);
                }
            }
        }
        return data;
    }

    private static byte[] toJpegBytes(@NonNull Bitmap bitmap) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (!ListenerUtil.mutListener.listen(11661)) {
            if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                throw new IOException("Failed to compress bitmap.");
            }
        }
        return out.toByteArray();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static boolean shouldCropImage(@NonNull ImageProxy image) {
        Size sourceSize = new Size(image.getWidth(), image.getHeight());
        Size targetSize = new Size(image.getCropRect().width(), image.getCropRect().height());
        return !targetSize.equals(sourceSize);
    }

    @ImageCapture.CaptureMode
    public static int getCaptureMode() {
        return MAX_QUALITY_CAMERAS.contains(Build.MODEL) ? ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY : ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY;
    }

    /**
     *  Return true if the internal camera is compatible with the current hardware or operating system
     *  @return
     */
    public static boolean isInternalCameraSupported() {
        return (ListenerUtil.mutListener.listen(11667) ? ((ListenerUtil.mutListener.listen(11666) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11665) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11664) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11663) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11662) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || !BLACKLISTED_CAMERAS.contains(Build.MODEL)) : ((ListenerUtil.mutListener.listen(11666) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11665) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11664) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11663) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11662) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && !BLACKLISTED_CAMERAS.contains(Build.MODEL)));
    }
}

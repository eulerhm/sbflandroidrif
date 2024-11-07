/**
 * *************************************************************************************
 *  Copyright (c) 2013 Bibek Shrestha <bibekshrestha@gmail.com>                          *
 *  Copyright (c) 2013 Zaur Molotnikov <qutorial@gmail.com>                              *
 *  Copyright (c) 2013 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.ichi2.anki.AnkiDroidApp;
import java.io.File;
import java.io.FileInputStream;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BitmapUtil {

    @Nullable
    public static Bitmap decodeFile(File theFile, int IMAGE_MAX_SIZE) {
        Bitmap bmp = null;
        try {
            if (!ListenerUtil.mutListener.listen(25556)) {
                if (!theFile.exists()) {
                    if (!ListenerUtil.mutListener.listen(25555)) {
                        Timber.i("not displaying preview - image does not exist: '%s'", theFile.getPath());
                    }
                    return null;
                }
            }
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            if (!ListenerUtil.mutListener.listen(25557)) {
                o.inJustDecodeBounds = true;
            }
            FileInputStream fis = null;
            try {
                if (!ListenerUtil.mutListener.listen(25560)) {
                    fis = new FileInputStream(theFile);
                }
                if (!ListenerUtil.mutListener.listen(25561)) {
                    BitmapFactory.decodeStream(fis, null, o);
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(25559)) {
                    if (fis != null) {
                        if (!ListenerUtil.mutListener.listen(25558)) {
                            fis.close();
                        }
                    }
                }
            }
            int scale = 1;
            if (!ListenerUtil.mutListener.listen(25582)) {
                if ((ListenerUtil.mutListener.listen(25572) ? ((ListenerUtil.mutListener.listen(25566) ? (o.outHeight >= IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25565) ? (o.outHeight <= IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25564) ? (o.outHeight < IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25563) ? (o.outHeight != IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25562) ? (o.outHeight == IMAGE_MAX_SIZE) : (o.outHeight > IMAGE_MAX_SIZE)))))) && (ListenerUtil.mutListener.listen(25571) ? (o.outWidth >= IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25570) ? (o.outWidth <= IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25569) ? (o.outWidth < IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25568) ? (o.outWidth != IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25567) ? (o.outWidth == IMAGE_MAX_SIZE) : (o.outWidth > IMAGE_MAX_SIZE))))))) : ((ListenerUtil.mutListener.listen(25566) ? (o.outHeight >= IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25565) ? (o.outHeight <= IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25564) ? (o.outHeight < IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25563) ? (o.outHeight != IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25562) ? (o.outHeight == IMAGE_MAX_SIZE) : (o.outHeight > IMAGE_MAX_SIZE)))))) || (ListenerUtil.mutListener.listen(25571) ? (o.outWidth >= IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25570) ? (o.outWidth <= IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25569) ? (o.outWidth < IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25568) ? (o.outWidth != IMAGE_MAX_SIZE) : (ListenerUtil.mutListener.listen(25567) ? (o.outWidth == IMAGE_MAX_SIZE) : (o.outWidth > IMAGE_MAX_SIZE))))))))) {
                    if (!ListenerUtil.mutListener.listen(25581)) {
                        scale = (int) Math.pow(2, (int) Math.round((ListenerUtil.mutListener.listen(25580) ? (Math.log((ListenerUtil.mutListener.listen(25576) ? (IMAGE_MAX_SIZE % (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25575) ? (IMAGE_MAX_SIZE * (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25574) ? (IMAGE_MAX_SIZE - (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25573) ? (IMAGE_MAX_SIZE + (double) Math.max(o.outHeight, o.outWidth)) : (IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth))))))) % Math.log(0.5)) : (ListenerUtil.mutListener.listen(25579) ? (Math.log((ListenerUtil.mutListener.listen(25576) ? (IMAGE_MAX_SIZE % (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25575) ? (IMAGE_MAX_SIZE * (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25574) ? (IMAGE_MAX_SIZE - (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25573) ? (IMAGE_MAX_SIZE + (double) Math.max(o.outHeight, o.outWidth)) : (IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth))))))) * Math.log(0.5)) : (ListenerUtil.mutListener.listen(25578) ? (Math.log((ListenerUtil.mutListener.listen(25576) ? (IMAGE_MAX_SIZE % (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25575) ? (IMAGE_MAX_SIZE * (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25574) ? (IMAGE_MAX_SIZE - (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25573) ? (IMAGE_MAX_SIZE + (double) Math.max(o.outHeight, o.outWidth)) : (IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth))))))) - Math.log(0.5)) : (ListenerUtil.mutListener.listen(25577) ? (Math.log((ListenerUtil.mutListener.listen(25576) ? (IMAGE_MAX_SIZE % (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25575) ? (IMAGE_MAX_SIZE * (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25574) ? (IMAGE_MAX_SIZE - (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25573) ? (IMAGE_MAX_SIZE + (double) Math.max(o.outHeight, o.outWidth)) : (IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth))))))) + Math.log(0.5)) : (Math.log((ListenerUtil.mutListener.listen(25576) ? (IMAGE_MAX_SIZE % (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25575) ? (IMAGE_MAX_SIZE * (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25574) ? (IMAGE_MAX_SIZE - (double) Math.max(o.outHeight, o.outWidth)) : (ListenerUtil.mutListener.listen(25573) ? (IMAGE_MAX_SIZE + (double) Math.max(o.outHeight, o.outWidth)) : (IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth))))))) / Math.log(0.5))))))));
                    }
                }
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            if (!ListenerUtil.mutListener.listen(25583)) {
                o2.inSampleSize = scale;
            }
            try {
                if (!ListenerUtil.mutListener.listen(25585)) {
                    fis = new FileInputStream(theFile);
                }
                if (!ListenerUtil.mutListener.listen(25586)) {
                    bmp = BitmapFactory.decodeStream(fis, null, o2);
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(25584)) {
                    // don't need a null check, as we reuse the variable.
                    fis.close();
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(25554)) {
                // #5513 - We don't know the reason for the crash, let's find out.
                AnkiDroidApp.sendExceptionReport(e, "BitmapUtil decodeFile");
            }
        }
        return bmp;
    }

    public static void freeImageView(ImageView imageView) {
        // This code behaves differently on various OS builds. That is why put into try catch.
        try {
            if (!ListenerUtil.mutListener.listen(25593)) {
                if (imageView != null) {
                    Drawable dr = imageView.getDrawable();
                    if (!ListenerUtil.mutListener.listen(25588)) {
                        if (dr == null) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25589)) {
                        if (!(dr instanceof BitmapDrawable)) {
                            return;
                        }
                    }
                    BitmapDrawable bd = (BitmapDrawable) imageView.getDrawable();
                    if (!ListenerUtil.mutListener.listen(25592)) {
                        if (bd.getBitmap() != null) {
                            if (!ListenerUtil.mutListener.listen(25590)) {
                                bd.getBitmap().recycle();
                            }
                            if (!ListenerUtil.mutListener.listen(25591)) {
                                imageView.setImageBitmap(null);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(25587)) {
                Timber.e(e);
            }
        }
    }
}

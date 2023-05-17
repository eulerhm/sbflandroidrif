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
package ch.threema.app.utils;

import android.Manifest;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import ch.threema.app.R;
import static ch.threema.app.dialogs.ContactEditDialog.CONTACT_AVATAR_HEIGHT_PX;
import static ch.threema.app.dialogs.ContactEditDialog.CONTACT_AVATAR_WIDTH_PX;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AvatarConverterUtil {

    private static final Logger logger = LoggerFactory.getLogger(AvatarConverterUtil.class);

    private static int avatarSize = -1, iconSize = -1, iconOffset = -1;

    public static int getAvatarSize(Resources r) {
        if (!ListenerUtil.mutListener.listen(49279)) {
            if ((ListenerUtil.mutListener.listen(49277) ? (avatarSize >= -1) : (ListenerUtil.mutListener.listen(49276) ? (avatarSize <= -1) : (ListenerUtil.mutListener.listen(49275) ? (avatarSize > -1) : (ListenerUtil.mutListener.listen(49274) ? (avatarSize < -1) : (ListenerUtil.mutListener.listen(49273) ? (avatarSize != -1) : (avatarSize == -1))))))) {
                if (!ListenerUtil.mutListener.listen(49278)) {
                    avatarSize = r.getDimensionPixelSize(R.dimen.avatar_size_small);
                }
            }
        }
        return avatarSize;
    }

    private static int getContentIconSize(Resources r) {
        if (!ListenerUtil.mutListener.listen(49286)) {
            if ((ListenerUtil.mutListener.listen(49284) ? (iconSize >= -1) : (ListenerUtil.mutListener.listen(49283) ? (iconSize <= -1) : (ListenerUtil.mutListener.listen(49282) ? (iconSize > -1) : (ListenerUtil.mutListener.listen(49281) ? (iconSize < -1) : (ListenerUtil.mutListener.listen(49280) ? (iconSize != -1) : (iconSize == -1))))))) {
                if (!ListenerUtil.mutListener.listen(49285)) {
                    iconSize = r.getDimensionPixelSize(R.dimen.conversation_controller_icon_size);
                }
            }
        }
        return iconSize;
    }

    private static int getContentIconOffset(Resources r) {
        if (!ListenerUtil.mutListener.listen(49301)) {
            if ((ListenerUtil.mutListener.listen(49291) ? (iconOffset >= -1) : (ListenerUtil.mutListener.listen(49290) ? (iconOffset <= -1) : (ListenerUtil.mutListener.listen(49289) ? (iconOffset > -1) : (ListenerUtil.mutListener.listen(49288) ? (iconOffset < -1) : (ListenerUtil.mutListener.listen(49287) ? (iconOffset != -1) : (iconOffset == -1))))))) {
                if (!ListenerUtil.mutListener.listen(49300)) {
                    iconOffset = (ListenerUtil.mutListener.listen(49299) ? (((ListenerUtil.mutListener.listen(49295) ? (getAvatarSize(r) % getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49294) ? (getAvatarSize(r) / getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49293) ? (getAvatarSize(r) * getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49292) ? (getAvatarSize(r) + getContentIconSize(r)) : (getAvatarSize(r) - getContentIconSize(r))))))) % 2) : (ListenerUtil.mutListener.listen(49298) ? (((ListenerUtil.mutListener.listen(49295) ? (getAvatarSize(r) % getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49294) ? (getAvatarSize(r) / getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49293) ? (getAvatarSize(r) * getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49292) ? (getAvatarSize(r) + getContentIconSize(r)) : (getAvatarSize(r) - getContentIconSize(r))))))) * 2) : (ListenerUtil.mutListener.listen(49297) ? (((ListenerUtil.mutListener.listen(49295) ? (getAvatarSize(r) % getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49294) ? (getAvatarSize(r) / getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49293) ? (getAvatarSize(r) * getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49292) ? (getAvatarSize(r) + getContentIconSize(r)) : (getAvatarSize(r) - getContentIconSize(r))))))) - 2) : (ListenerUtil.mutListener.listen(49296) ? (((ListenerUtil.mutListener.listen(49295) ? (getAvatarSize(r) % getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49294) ? (getAvatarSize(r) / getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49293) ? (getAvatarSize(r) * getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49292) ? (getAvatarSize(r) + getContentIconSize(r)) : (getAvatarSize(r) - getContentIconSize(r))))))) + 2) : (((ListenerUtil.mutListener.listen(49295) ? (getAvatarSize(r) % getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49294) ? (getAvatarSize(r) / getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49293) ? (getAvatarSize(r) * getContentIconSize(r)) : (ListenerUtil.mutListener.listen(49292) ? (getAvatarSize(r) + getContentIconSize(r)) : (getAvatarSize(r) - getContentIconSize(r))))))) / 2)))));
                }
            }
        }
        return iconOffset;
    }

    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    public static Bitmap convert(Context context, Uri contactUri) {
        Bitmap source = null;
        SampleResult sampleResult;
        int x = 0, y = 0, size = 0;
        try (InputStream is = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), contactUri, true)) {
            if ((ListenerUtil.mutListener.listen(49308) ? (is == null && (ListenerUtil.mutListener.listen(49307) ? (is.available() >= 0) : (ListenerUtil.mutListener.listen(49306) ? (is.available() <= 0) : (ListenerUtil.mutListener.listen(49305) ? (is.available() > 0) : (ListenerUtil.mutListener.listen(49304) ? (is.available() < 0) : (ListenerUtil.mutListener.listen(49303) ? (is.available() != 0) : (is.available() == 0))))))) : (is == null || (ListenerUtil.mutListener.listen(49307) ? (is.available() >= 0) : (ListenerUtil.mutListener.listen(49306) ? (is.available() <= 0) : (ListenerUtil.mutListener.listen(49305) ? (is.available() > 0) : (ListenerUtil.mutListener.listen(49304) ? (is.available() < 0) : (ListenerUtil.mutListener.listen(49303) ? (is.available() != 0) : (is.available() == 0))))))))) {
                return null;
            }
            // decode image size (decode metadata only, not the whole image)
            BitmapFactory.Options options = BitmapUtil.getImageDimensions(is);
            // save width and height
            int inWidth = options.outWidth;
            int inHeight = options.outHeight;
            if (!ListenerUtil.mutListener.listen(49309)) {
                size = Math.min(inWidth, inHeight);
            }
            if (!ListenerUtil.mutListener.listen(49323)) {
                x = (ListenerUtil.mutListener.listen(49314) ? (inWidth >= inHeight) : (ListenerUtil.mutListener.listen(49313) ? (inWidth <= inHeight) : (ListenerUtil.mutListener.listen(49312) ? (inWidth < inHeight) : (ListenerUtil.mutListener.listen(49311) ? (inWidth != inHeight) : (ListenerUtil.mutListener.listen(49310) ? (inWidth == inHeight) : (inWidth > inHeight)))))) ? (ListenerUtil.mutListener.listen(49322) ? (((ListenerUtil.mutListener.listen(49318) ? (inWidth % size) : (ListenerUtil.mutListener.listen(49317) ? (inWidth / size) : (ListenerUtil.mutListener.listen(49316) ? (inWidth * size) : (ListenerUtil.mutListener.listen(49315) ? (inWidth + size) : (inWidth - size)))))) % 2) : (ListenerUtil.mutListener.listen(49321) ? (((ListenerUtil.mutListener.listen(49318) ? (inWidth % size) : (ListenerUtil.mutListener.listen(49317) ? (inWidth / size) : (ListenerUtil.mutListener.listen(49316) ? (inWidth * size) : (ListenerUtil.mutListener.listen(49315) ? (inWidth + size) : (inWidth - size)))))) * 2) : (ListenerUtil.mutListener.listen(49320) ? (((ListenerUtil.mutListener.listen(49318) ? (inWidth % size) : (ListenerUtil.mutListener.listen(49317) ? (inWidth / size) : (ListenerUtil.mutListener.listen(49316) ? (inWidth * size) : (ListenerUtil.mutListener.listen(49315) ? (inWidth + size) : (inWidth - size)))))) - 2) : (ListenerUtil.mutListener.listen(49319) ? (((ListenerUtil.mutListener.listen(49318) ? (inWidth % size) : (ListenerUtil.mutListener.listen(49317) ? (inWidth / size) : (ListenerUtil.mutListener.listen(49316) ? (inWidth * size) : (ListenerUtil.mutListener.listen(49315) ? (inWidth + size) : (inWidth - size)))))) + 2) : (((ListenerUtil.mutListener.listen(49318) ? (inWidth % size) : (ListenerUtil.mutListener.listen(49317) ? (inWidth / size) : (ListenerUtil.mutListener.listen(49316) ? (inWidth * size) : (ListenerUtil.mutListener.listen(49315) ? (inWidth + size) : (inWidth - size)))))) / 2))))) : 0;
            }
            if (!ListenerUtil.mutListener.listen(49337)) {
                y = (ListenerUtil.mutListener.listen(49328) ? (inWidth >= inHeight) : (ListenerUtil.mutListener.listen(49327) ? (inWidth <= inHeight) : (ListenerUtil.mutListener.listen(49326) ? (inWidth > inHeight) : (ListenerUtil.mutListener.listen(49325) ? (inWidth != inHeight) : (ListenerUtil.mutListener.listen(49324) ? (inWidth == inHeight) : (inWidth < inHeight)))))) ? (ListenerUtil.mutListener.listen(49336) ? (((ListenerUtil.mutListener.listen(49332) ? (inHeight % size) : (ListenerUtil.mutListener.listen(49331) ? (inHeight / size) : (ListenerUtil.mutListener.listen(49330) ? (inHeight * size) : (ListenerUtil.mutListener.listen(49329) ? (inHeight + size) : (inHeight - size)))))) % 2) : (ListenerUtil.mutListener.listen(49335) ? (((ListenerUtil.mutListener.listen(49332) ? (inHeight % size) : (ListenerUtil.mutListener.listen(49331) ? (inHeight / size) : (ListenerUtil.mutListener.listen(49330) ? (inHeight * size) : (ListenerUtil.mutListener.listen(49329) ? (inHeight + size) : (inHeight - size)))))) * 2) : (ListenerUtil.mutListener.listen(49334) ? (((ListenerUtil.mutListener.listen(49332) ? (inHeight % size) : (ListenerUtil.mutListener.listen(49331) ? (inHeight / size) : (ListenerUtil.mutListener.listen(49330) ? (inHeight * size) : (ListenerUtil.mutListener.listen(49329) ? (inHeight + size) : (inHeight - size)))))) - 2) : (ListenerUtil.mutListener.listen(49333) ? (((ListenerUtil.mutListener.listen(49332) ? (inHeight % size) : (ListenerUtil.mutListener.listen(49331) ? (inHeight / size) : (ListenerUtil.mutListener.listen(49330) ? (inHeight * size) : (ListenerUtil.mutListener.listen(49329) ? (inHeight + size) : (inHeight - size)))))) + 2) : (((ListenerUtil.mutListener.listen(49332) ? (inHeight % size) : (ListenerUtil.mutListener.listen(49331) ? (inHeight / size) : (ListenerUtil.mutListener.listen(49330) ? (inHeight * size) : (ListenerUtil.mutListener.listen(49329) ? (inHeight + size) : (inHeight - size)))))) / 2))))) : 0;
            }
            sampleResult = BitmapUtil.getSampleSize(inWidth, inHeight, CONTACT_AVATAR_WIDTH_PX, CONTACT_AVATAR_HEIGHT_PX);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(49302)) {
                logger.error("Exception", e);
            }
            return null;
        }
        try (InputStream is = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), contactUri, true)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (!ListenerUtil.mutListener.listen(49339)) {
                options.inSampleSize = sampleResult.inSampleSize;
            }
            if (!ListenerUtil.mutListener.listen(49340)) {
                options.inJustDecodeBounds = false;
            }
            if (!ListenerUtil.mutListener.listen(49356)) {
                if ((ListenerUtil.mutListener.listen(49345) ? (x >= y) : (ListenerUtil.mutListener.listen(49344) ? (x <= y) : (ListenerUtil.mutListener.listen(49343) ? (x > y) : (ListenerUtil.mutListener.listen(49342) ? (x < y) : (ListenerUtil.mutListener.listen(49341) ? (x == y) : (x != y))))))) {
                    // this is a non-square image. use a region
                    BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);
                    if (!ListenerUtil.mutListener.listen(49355)) {
                        source = decoder.decodeRegion(new Rect(x, y, (ListenerUtil.mutListener.listen(49350) ? (size % x) : (ListenerUtil.mutListener.listen(49349) ? (size / x) : (ListenerUtil.mutListener.listen(49348) ? (size * x) : (ListenerUtil.mutListener.listen(49347) ? (size - x) : (size + x))))), (ListenerUtil.mutListener.listen(49354) ? (size % y) : (ListenerUtil.mutListener.listen(49353) ? (size / y) : (ListenerUtil.mutListener.listen(49352) ? (size * y) : (ListenerUtil.mutListener.listen(49351) ? (size - y) : (size + y)))))), options);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(49346)) {
                        source = BitmapFactory.decodeStream(is, null, options);
                    }
                }
            }
            return source;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(49338)) {
                logger.error("Exception", e);
            }
            return null;
        }
    }

    public static Bitmap convert(Resources r, Bitmap sourceBitmap) {
        return convertToRound(r, sourceBitmap, Color.WHITE, null, getAvatarSize(r));
    }

    public static Bitmap convert(Resources r, Bitmap sourceBitmap, int bgcolor, int fgcolor) {
        return convertToRound(r, sourceBitmap, bgcolor, fgcolor, getAvatarSize(r));
    }

    public static Drawable convertToRound(Resources r, Bitmap sourceBitmap) {
        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(r, sourceBitmap);
        if (!ListenerUtil.mutListener.listen(49357)) {
            bitmapDrawable.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(49358)) {
            bitmapDrawable.setCircular(true);
        }
        return bitmapDrawable;
    }

    @Nullable
    public static Bitmap convertToRound(Resources r, @Nullable Bitmap sourceBitmap, @ColorInt final int bgcolor, @Nullable final Integer fgcolor, int size) {
        if (!ListenerUtil.mutListener.listen(49359)) {
            if (sourceBitmap == null) {
                return null;
            }
        }
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint bgPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(49360)) {
            bgPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(49361)) {
            bgPaint.setFilterBitmap(true);
        }
        if (!ListenerUtil.mutListener.listen(49362)) {
            canvas.drawARGB(0, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(49363)) {
            bgPaint.setColor(bgcolor);
        }
        if (!ListenerUtil.mutListener.listen(49376)) {
            canvas.drawCircle((ListenerUtil.mutListener.listen(49367) ? ((float) output.getWidth() % 2) : (ListenerUtil.mutListener.listen(49366) ? ((float) output.getWidth() * 2) : (ListenerUtil.mutListener.listen(49365) ? ((float) output.getWidth() - 2) : (ListenerUtil.mutListener.listen(49364) ? ((float) output.getWidth() + 2) : ((float) output.getWidth() / 2))))), (ListenerUtil.mutListener.listen(49371) ? ((float) output.getHeight() % 2) : (ListenerUtil.mutListener.listen(49370) ? ((float) output.getHeight() * 2) : (ListenerUtil.mutListener.listen(49369) ? ((float) output.getHeight() - 2) : (ListenerUtil.mutListener.listen(49368) ? ((float) output.getHeight() + 2) : ((float) output.getHeight() / 2))))), (ListenerUtil.mutListener.listen(49375) ? ((float) output.getWidth() % 2) : (ListenerUtil.mutListener.listen(49374) ? ((float) output.getWidth() * 2) : (ListenerUtil.mutListener.listen(49373) ? ((float) output.getWidth() - 2) : (ListenerUtil.mutListener.listen(49372) ? ((float) output.getWidth() + 2) : ((float) output.getWidth() / 2))))), bgPaint);
        }
        if (!ListenerUtil.mutListener.listen(49377)) {
            bgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }
        int sourceLeft = 0, sourceTop = 0, sourceRight = sourceBitmap.getWidth(), sourceBottom = sourceBitmap.getHeight();
        if (!ListenerUtil.mutListener.listen(49413)) {
            // make sure source bitmap is square by cropping longer edge
            if (sourceBitmap.getWidth() != sourceBitmap.getHeight()) {
                if (!ListenerUtil.mutListener.listen(49378)) {
                    sourceBitmap.setDensity(Bitmap.DENSITY_NONE);
                }
                if (!ListenerUtil.mutListener.listen(49412)) {
                    if ((ListenerUtil.mutListener.listen(49383) ? (sourceBitmap.getWidth() >= sourceBitmap.getHeight()) : (ListenerUtil.mutListener.listen(49382) ? (sourceBitmap.getWidth() <= sourceBitmap.getHeight()) : (ListenerUtil.mutListener.listen(49381) ? (sourceBitmap.getWidth() < sourceBitmap.getHeight()) : (ListenerUtil.mutListener.listen(49380) ? (sourceBitmap.getWidth() != sourceBitmap.getHeight()) : (ListenerUtil.mutListener.listen(49379) ? (sourceBitmap.getWidth() == sourceBitmap.getHeight()) : (sourceBitmap.getWidth() > sourceBitmap.getHeight()))))))) {
                        if (!ListenerUtil.mutListener.listen(49406)) {
                            sourceLeft = (ListenerUtil.mutListener.listen(49405) ? (((ListenerUtil.mutListener.listen(49401) ? (sourceRight % sourceBottom) : (ListenerUtil.mutListener.listen(49400) ? (sourceRight / sourceBottom) : (ListenerUtil.mutListener.listen(49399) ? (sourceRight * sourceBottom) : (ListenerUtil.mutListener.listen(49398) ? (sourceRight + sourceBottom) : (sourceRight - sourceBottom)))))) % 2) : (ListenerUtil.mutListener.listen(49404) ? (((ListenerUtil.mutListener.listen(49401) ? (sourceRight % sourceBottom) : (ListenerUtil.mutListener.listen(49400) ? (sourceRight / sourceBottom) : (ListenerUtil.mutListener.listen(49399) ? (sourceRight * sourceBottom) : (ListenerUtil.mutListener.listen(49398) ? (sourceRight + sourceBottom) : (sourceRight - sourceBottom)))))) * 2) : (ListenerUtil.mutListener.listen(49403) ? (((ListenerUtil.mutListener.listen(49401) ? (sourceRight % sourceBottom) : (ListenerUtil.mutListener.listen(49400) ? (sourceRight / sourceBottom) : (ListenerUtil.mutListener.listen(49399) ? (sourceRight * sourceBottom) : (ListenerUtil.mutListener.listen(49398) ? (sourceRight + sourceBottom) : (sourceRight - sourceBottom)))))) - 2) : (ListenerUtil.mutListener.listen(49402) ? (((ListenerUtil.mutListener.listen(49401) ? (sourceRight % sourceBottom) : (ListenerUtil.mutListener.listen(49400) ? (sourceRight / sourceBottom) : (ListenerUtil.mutListener.listen(49399) ? (sourceRight * sourceBottom) : (ListenerUtil.mutListener.listen(49398) ? (sourceRight + sourceBottom) : (sourceRight - sourceBottom)))))) + 2) : (((ListenerUtil.mutListener.listen(49401) ? (sourceRight % sourceBottom) : (ListenerUtil.mutListener.listen(49400) ? (sourceRight / sourceBottom) : (ListenerUtil.mutListener.listen(49399) ? (sourceRight * sourceBottom) : (ListenerUtil.mutListener.listen(49398) ? (sourceRight + sourceBottom) : (sourceRight - sourceBottom)))))) / 2)))));
                        }
                        if (!ListenerUtil.mutListener.listen(49411)) {
                            sourceRight = (ListenerUtil.mutListener.listen(49410) ? (sourceLeft % sourceBottom) : (ListenerUtil.mutListener.listen(49409) ? (sourceLeft / sourceBottom) : (ListenerUtil.mutListener.listen(49408) ? (sourceLeft * sourceBottom) : (ListenerUtil.mutListener.listen(49407) ? (sourceLeft - sourceBottom) : (sourceLeft + sourceBottom)))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(49392)) {
                            sourceTop = (ListenerUtil.mutListener.listen(49391) ? (((ListenerUtil.mutListener.listen(49387) ? (sourceBottom % sourceRight) : (ListenerUtil.mutListener.listen(49386) ? (sourceBottom / sourceRight) : (ListenerUtil.mutListener.listen(49385) ? (sourceBottom * sourceRight) : (ListenerUtil.mutListener.listen(49384) ? (sourceBottom + sourceRight) : (sourceBottom - sourceRight)))))) % 2) : (ListenerUtil.mutListener.listen(49390) ? (((ListenerUtil.mutListener.listen(49387) ? (sourceBottom % sourceRight) : (ListenerUtil.mutListener.listen(49386) ? (sourceBottom / sourceRight) : (ListenerUtil.mutListener.listen(49385) ? (sourceBottom * sourceRight) : (ListenerUtil.mutListener.listen(49384) ? (sourceBottom + sourceRight) : (sourceBottom - sourceRight)))))) * 2) : (ListenerUtil.mutListener.listen(49389) ? (((ListenerUtil.mutListener.listen(49387) ? (sourceBottom % sourceRight) : (ListenerUtil.mutListener.listen(49386) ? (sourceBottom / sourceRight) : (ListenerUtil.mutListener.listen(49385) ? (sourceBottom * sourceRight) : (ListenerUtil.mutListener.listen(49384) ? (sourceBottom + sourceRight) : (sourceBottom - sourceRight)))))) - 2) : (ListenerUtil.mutListener.listen(49388) ? (((ListenerUtil.mutListener.listen(49387) ? (sourceBottom % sourceRight) : (ListenerUtil.mutListener.listen(49386) ? (sourceBottom / sourceRight) : (ListenerUtil.mutListener.listen(49385) ? (sourceBottom * sourceRight) : (ListenerUtil.mutListener.listen(49384) ? (sourceBottom + sourceRight) : (sourceBottom - sourceRight)))))) + 2) : (((ListenerUtil.mutListener.listen(49387) ? (sourceBottom % sourceRight) : (ListenerUtil.mutListener.listen(49386) ? (sourceBottom / sourceRight) : (ListenerUtil.mutListener.listen(49385) ? (sourceBottom * sourceRight) : (ListenerUtil.mutListener.listen(49384) ? (sourceBottom + sourceRight) : (sourceBottom - sourceRight)))))) / 2)))));
                        }
                        if (!ListenerUtil.mutListener.listen(49397)) {
                            sourceBottom = (ListenerUtil.mutListener.listen(49396) ? (sourceTop % sourceRight) : (ListenerUtil.mutListener.listen(49395) ? (sourceTop / sourceRight) : (ListenerUtil.mutListener.listen(49394) ? (sourceTop * sourceRight) : (ListenerUtil.mutListener.listen(49393) ? (sourceTop - sourceRight) : (sourceTop + sourceRight)))));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49420)) {
            if (fgcolor == null) {
                if (!ListenerUtil.mutListener.listen(49419)) {
                    canvas.drawBitmap(sourceBitmap, new Rect(sourceLeft, sourceTop, sourceRight, sourceBottom), new Rect(0, 0, size, size), bgPaint);
                }
            } else {
                final Paint fgPaint = new Paint();
                if (!ListenerUtil.mutListener.listen(49414)) {
                    fgPaint.setAntiAlias(true);
                }
                if (!ListenerUtil.mutListener.listen(49415)) {
                    fgPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, fgcolor));
                }
                if (!ListenerUtil.mutListener.listen(49416)) {
                    fgPaint.setFilterBitmap(true);
                }
                Rect destRect = new Rect(0, 0, getContentIconSize(r), getContentIconSize(r));
                if (!ListenerUtil.mutListener.listen(49417)) {
                    destRect.offset(getContentIconOffset(r), getContentIconOffset(r));
                }
                if (!ListenerUtil.mutListener.listen(49418)) {
                    canvas.drawBitmap(sourceBitmap, new Rect(0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight()), destRect, fgPaint);
                }
            }
        }
        return output;
    }

    @NonNull
    public static Bitmap getAvatarBitmap(VectorDrawableCompat drawable, @ColorInt int color, int size) {
        if (!ListenerUtil.mutListener.listen(49421)) {
            drawable.mutate();
        }
        Bitmap result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        if (!ListenerUtil.mutListener.listen(49422)) {
            drawable.setBounds(0, 0, size, size);
        }
        if (!ListenerUtil.mutListener.listen(49423)) {
            drawable.setTint(color);
        }
        if (!ListenerUtil.mutListener.listen(49424)) {
            drawable.draw(canvas);
        }
        return result;
    }
}

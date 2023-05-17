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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImageViewUtil {

    private ImageViewUtil() {
    }

    private static float cornerRadius = 0F;

    public static void showBitmap(ImageView imageView, Bitmap bitmap, Drawable drawable, int fixWidth) {
        if (!ListenerUtil.mutListener.listen(54378)) {
            if (TestUtil.required(imageView, bitmap)) {
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(54363)) {
                    params.width = fixWidth;
                }
                if (!ListenerUtil.mutListener.listen(54372)) {
                    // calculate height
                    params.height = (int) ((ListenerUtil.mutListener.listen(54371) ? ((ListenerUtil.mutListener.listen(54367) ? ((float) fixWidth % bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54366) ? ((float) fixWidth * bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54365) ? ((float) fixWidth - bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54364) ? ((float) fixWidth + bitmap.getWidth()) : ((float) fixWidth / bitmap.getWidth()))))) % bitmap.getHeight()) : (ListenerUtil.mutListener.listen(54370) ? ((ListenerUtil.mutListener.listen(54367) ? ((float) fixWidth % bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54366) ? ((float) fixWidth * bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54365) ? ((float) fixWidth - bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54364) ? ((float) fixWidth + bitmap.getWidth()) : ((float) fixWidth / bitmap.getWidth()))))) / bitmap.getHeight()) : (ListenerUtil.mutListener.listen(54369) ? ((ListenerUtil.mutListener.listen(54367) ? ((float) fixWidth % bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54366) ? ((float) fixWidth * bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54365) ? ((float) fixWidth - bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54364) ? ((float) fixWidth + bitmap.getWidth()) : ((float) fixWidth / bitmap.getWidth()))))) - bitmap.getHeight()) : (ListenerUtil.mutListener.listen(54368) ? ((ListenerUtil.mutListener.listen(54367) ? ((float) fixWidth % bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54366) ? ((float) fixWidth * bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54365) ? ((float) fixWidth - bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54364) ? ((float) fixWidth + bitmap.getWidth()) : ((float) fixWidth / bitmap.getWidth()))))) + bitmap.getHeight()) : ((ListenerUtil.mutListener.listen(54367) ? ((float) fixWidth % bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54366) ? ((float) fixWidth * bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54365) ? ((float) fixWidth - bitmap.getWidth()) : (ListenerUtil.mutListener.listen(54364) ? ((float) fixWidth + bitmap.getWidth()) : ((float) fixWidth / bitmap.getWidth()))))) * bitmap.getHeight()))))));
                }
                if (!ListenerUtil.mutListener.listen(54375)) {
                    if (drawable != null) {
                        if (!ListenerUtil.mutListener.listen(54374)) {
                            imageView.setImageDrawable(drawable);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(54373)) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(54376)) {
                    imageView.setLayoutParams(params);
                }
                if (!ListenerUtil.mutListener.listen(54377)) {
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public static void showRoundedBitmap(Context context, View blockView, ImageView imageView, Bitmap bitmap, int fixWidth) {
        if (!ListenerUtil.mutListener.listen(54384)) {
            if (blockView != null) {
                ViewGroup.LayoutParams params = blockView.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(54379)) {
                    params.width = fixWidth;
                }
                if (!ListenerUtil.mutListener.listen(54380)) {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                if (!ListenerUtil.mutListener.listen(54381)) {
                    blockView.setLayoutParams(params);
                }
                RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
                if (!ListenerUtil.mutListener.listen(54382)) {
                    drawable.setCornerRadius(getCornerRadius(context));
                }
                if (!ListenerUtil.mutListener.listen(54383)) {
                    showBitmap(imageView, bitmap, drawable, fixWidth);
                }
            }
        }
    }

    public static void showPlaceholderBitmap(View blockView, ImageView imageView, int fixWidth) {
        if (!ListenerUtil.mutListener.listen(54394)) {
            if (blockView != null) {
                if (!ListenerUtil.mutListener.listen(54385)) {
                    // TODO: The placeholder is currently only an empty bubble
                    imageView.setImageBitmap(null);
                }
                if (!ListenerUtil.mutListener.listen(54386)) {
                    imageView.setVisibility(View.INVISIBLE);
                }
                ViewGroup.LayoutParams params = blockView.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(54387)) {
                    params.width = fixWidth;
                }
                if (!ListenerUtil.mutListener.listen(54392)) {
                    params.height = (ListenerUtil.mutListener.listen(54391) ? (fixWidth % 2) : (ListenerUtil.mutListener.listen(54390) ? (fixWidth * 2) : (ListenerUtil.mutListener.listen(54389) ? (fixWidth - 2) : (ListenerUtil.mutListener.listen(54388) ? (fixWidth + 2) : (fixWidth / 2)))));
                }
                if (!ListenerUtil.mutListener.listen(54393)) {
                    blockView.setLayoutParams(params);
                }
            }
        }
    }

    public static float getCornerRadius(Context context) {
        if (!ListenerUtil.mutListener.listen(54401)) {
            if ((ListenerUtil.mutListener.listen(54399) ? (cornerRadius >= 0F) : (ListenerUtil.mutListener.listen(54398) ? (cornerRadius <= 0F) : (ListenerUtil.mutListener.listen(54397) ? (cornerRadius > 0F) : (ListenerUtil.mutListener.listen(54396) ? (cornerRadius < 0F) : (ListenerUtil.mutListener.listen(54395) ? (cornerRadius != 0F) : (cornerRadius == 0F))))))) {
                if (!ListenerUtil.mutListener.listen(54400)) {
                    cornerRadius = context.getResources().getDimensionPixelSize(R.dimen.chat_bubble_border_radius_inside);
                }
            }
        }
        return cornerRadius;
    }
}

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
package ch.threema.app.motionviews.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import ch.threema.app.motionviews.viewmodel.TextLayer;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TextEntity extends MotionEntity {

    private final TextPaint textPaint;

    public static final float TEXT_SHADOW_OFFSET = 0.5f;

    public static final float TEXT_SHADOW_RADIUS = 0.5f;

    @Nullable
    private Bitmap bitmap;

    public TextEntity(@NonNull TextLayer textLayer, @IntRange(from = 1) int canvasWidth, @IntRange(from = 1) int canvasHeight) {
        super(textLayer, canvasWidth, canvasHeight);
        this.textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        if (!ListenerUtil.mutListener.listen(31442)) {
            updateEntity(false);
        }
    }

    private void updateEntity(boolean moveToPreviousCenter) {
        // save previous center
        PointF oldCenter = absoluteCenter();
        Bitmap newBmp = createBitmap(getLayer(), bitmap);
        if (!ListenerUtil.mutListener.listen(31446)) {
            // recycle previous bitmap (if not reused) as soon as possible
            if ((ListenerUtil.mutListener.listen(31444) ? ((ListenerUtil.mutListener.listen(31443) ? (bitmap != null || bitmap != newBmp) : (bitmap != null && bitmap != newBmp)) || !bitmap.isRecycled()) : ((ListenerUtil.mutListener.listen(31443) ? (bitmap != null || bitmap != newBmp) : (bitmap != null && bitmap != newBmp)) && !bitmap.isRecycled()))) {
                if (!ListenerUtil.mutListener.listen(31445)) {
                    bitmap.recycle();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31447)) {
            this.bitmap = newBmp;
        }
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        @SuppressWarnings("UnnecessaryLocalVariable")
        float widthAspect = (ListenerUtil.mutListener.listen(31455) ? ((ListenerUtil.mutListener.listen(31451) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31450) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31449) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31448) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) % width) : (ListenerUtil.mutListener.listen(31454) ? ((ListenerUtil.mutListener.listen(31451) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31450) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31449) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31448) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) * width) : (ListenerUtil.mutListener.listen(31453) ? ((ListenerUtil.mutListener.listen(31451) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31450) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31449) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31448) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) - width) : (ListenerUtil.mutListener.listen(31452) ? ((ListenerUtil.mutListener.listen(31451) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31450) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31449) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31448) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) + width) : ((ListenerUtil.mutListener.listen(31451) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31450) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31449) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31448) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) / width)))));
        if (!ListenerUtil.mutListener.listen(31456)) {
            // for text we always match text width with parent width
            this.holyScale = widthAspect;
        }
        if (!ListenerUtil.mutListener.listen(31457)) {
            // initial position of the entity
            srcPoints[0] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31458)) {
            srcPoints[1] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31459)) {
            srcPoints[2] = width;
        }
        if (!ListenerUtil.mutListener.listen(31460)) {
            srcPoints[3] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31461)) {
            srcPoints[4] = width;
        }
        if (!ListenerUtil.mutListener.listen(31462)) {
            srcPoints[5] = height;
        }
        if (!ListenerUtil.mutListener.listen(31463)) {
            srcPoints[6] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31464)) {
            srcPoints[7] = height;
        }
        if (!ListenerUtil.mutListener.listen(31465)) {
            srcPoints[8] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31466)) {
            srcPoints[8] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31468)) {
            if (moveToPreviousCenter) {
                if (!ListenerUtil.mutListener.listen(31467)) {
                    // move to previous center
                    moveCenterTo(oldCenter);
                }
            }
        }
    }

    /**
     *  If reuseBmp is not null, and size of the new bitmap matches the size of the reuseBmp, new
     *  bitmap won't be created, reuseBmp it will be reused instead
     *
     *  @param textLayer text to draw
     *  @param reuseBmp the bitmap that will be reused
     *  @return bitmap with the text
     */
    @NonNull
    private Bitmap createBitmap(@NonNull TextLayer textLayer, @Nullable Bitmap reuseBmp) {
        int boundsWidth = canvasWidth;
        if (!ListenerUtil.mutListener.listen(31469)) {
            // init params - size, color, typeface
            textPaint.setStyle(Paint.Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(31470)) {
            textPaint.setTextSize(textLayer.getFont().getSize());
        }
        if (!ListenerUtil.mutListener.listen(31471)) {
            textPaint.setColor(textLayer.getFont().getColor());
        }
        if (!ListenerUtil.mutListener.listen(31472)) {
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (!ListenerUtil.mutListener.listen(31473)) {
            textPaint.setShadowLayer(TEXT_SHADOW_RADIUS, TEXT_SHADOW_OFFSET, TEXT_SHADOW_OFFSET, Color.BLACK);
        }
        // Static layout which will be drawn on canvas
        StaticLayout sl = new StaticLayout(// - text which will be drawn
        textLayer.getText(), textPaint, // - width of the layout
        boundsWidth, // - layout alignment
        Layout.Alignment.ALIGN_CENTER, // 1 - text spacing multiply
        1, // 1 - text spacing add
        1, // true - include padding
        true);
        // calculate height for the entity, min - Limits.MIN_BITMAP_HEIGHT
        int boundsHeight = sl.getHeight();
        // create bitmap not smaller than TextLayer.Limits.MIN_BITMAP_HEIGHT
        int bmpHeight = (int) ((ListenerUtil.mutListener.listen(31485) ? (canvasHeight % Math.max(TextLayer.Limits.MIN_BITMAP_HEIGHT, (ListenerUtil.mutListener.listen(31481) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) % canvasHeight) : (ListenerUtil.mutListener.listen(31480) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) * canvasHeight) : (ListenerUtil.mutListener.listen(31479) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) - canvasHeight) : (ListenerUtil.mutListener.listen(31478) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) + canvasHeight) : ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) / canvasHeight))))))) : (ListenerUtil.mutListener.listen(31484) ? (canvasHeight / Math.max(TextLayer.Limits.MIN_BITMAP_HEIGHT, (ListenerUtil.mutListener.listen(31481) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) % canvasHeight) : (ListenerUtil.mutListener.listen(31480) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) * canvasHeight) : (ListenerUtil.mutListener.listen(31479) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) - canvasHeight) : (ListenerUtil.mutListener.listen(31478) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) + canvasHeight) : ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) / canvasHeight))))))) : (ListenerUtil.mutListener.listen(31483) ? (canvasHeight - Math.max(TextLayer.Limits.MIN_BITMAP_HEIGHT, (ListenerUtil.mutListener.listen(31481) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) % canvasHeight) : (ListenerUtil.mutListener.listen(31480) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) * canvasHeight) : (ListenerUtil.mutListener.listen(31479) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) - canvasHeight) : (ListenerUtil.mutListener.listen(31478) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) + canvasHeight) : ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) / canvasHeight))))))) : (ListenerUtil.mutListener.listen(31482) ? (canvasHeight + Math.max(TextLayer.Limits.MIN_BITMAP_HEIGHT, (ListenerUtil.mutListener.listen(31481) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) % canvasHeight) : (ListenerUtil.mutListener.listen(31480) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) * canvasHeight) : (ListenerUtil.mutListener.listen(31479) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) - canvasHeight) : (ListenerUtil.mutListener.listen(31478) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) + canvasHeight) : ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) / canvasHeight))))))) : (canvasHeight * Math.max(TextLayer.Limits.MIN_BITMAP_HEIGHT, (ListenerUtil.mutListener.listen(31481) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) % canvasHeight) : (ListenerUtil.mutListener.listen(31480) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) * canvasHeight) : (ListenerUtil.mutListener.listen(31479) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) - canvasHeight) : (ListenerUtil.mutListener.listen(31478) ? ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) + canvasHeight) : ((ListenerUtil.mutListener.listen(31477) ? (1.0F % boundsHeight) : (ListenerUtil.mutListener.listen(31476) ? (1.0F / boundsHeight) : (ListenerUtil.mutListener.listen(31475) ? (1.0F - boundsHeight) : (ListenerUtil.mutListener.listen(31474) ? (1.0F + boundsHeight) : (1.0F * boundsHeight))))) / canvasHeight))))))))))));
        // create bitmap where text will be drawn
        Bitmap bmp;
        if ((ListenerUtil.mutListener.listen(31487) ? ((ListenerUtil.mutListener.listen(31486) ? (reuseBmp != null || reuseBmp.getWidth() == boundsWidth) : (reuseBmp != null && reuseBmp.getWidth() == boundsWidth)) || reuseBmp.getHeight() == bmpHeight) : ((ListenerUtil.mutListener.listen(31486) ? (reuseBmp != null || reuseBmp.getWidth() == boundsWidth) : (reuseBmp != null && reuseBmp.getWidth() == boundsWidth)) && reuseBmp.getHeight() == bmpHeight))) {
            // if previous bitmap exists, and it's width/height is the same - reuse it
            bmp = reuseBmp;
            if (!ListenerUtil.mutListener.listen(31488)) {
                // erase color when reusing
                bmp.eraseColor(Color.TRANSPARENT);
            }
        } else {
            bmp = Bitmap.createBitmap(boundsWidth, bmpHeight, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bmp);
        if (!ListenerUtil.mutListener.listen(31489)) {
            canvas.save();
        }
        if (!ListenerUtil.mutListener.listen(31504)) {
            // move text to center if bitmap is bigger that text
            if ((ListenerUtil.mutListener.listen(31494) ? (boundsHeight >= bmpHeight) : (ListenerUtil.mutListener.listen(31493) ? (boundsHeight <= bmpHeight) : (ListenerUtil.mutListener.listen(31492) ? (boundsHeight > bmpHeight) : (ListenerUtil.mutListener.listen(31491) ? (boundsHeight != bmpHeight) : (ListenerUtil.mutListener.listen(31490) ? (boundsHeight == bmpHeight) : (boundsHeight < bmpHeight))))))) {
                // center of the canvas so we move Y coordinate to center.
                float textYCoordinate = (ListenerUtil.mutListener.listen(31502) ? (((ListenerUtil.mutListener.listen(31498) ? (bmpHeight % boundsHeight) : (ListenerUtil.mutListener.listen(31497) ? (bmpHeight / boundsHeight) : (ListenerUtil.mutListener.listen(31496) ? (bmpHeight * boundsHeight) : (ListenerUtil.mutListener.listen(31495) ? (bmpHeight + boundsHeight) : (bmpHeight - boundsHeight)))))) % 2) : (ListenerUtil.mutListener.listen(31501) ? (((ListenerUtil.mutListener.listen(31498) ? (bmpHeight % boundsHeight) : (ListenerUtil.mutListener.listen(31497) ? (bmpHeight / boundsHeight) : (ListenerUtil.mutListener.listen(31496) ? (bmpHeight * boundsHeight) : (ListenerUtil.mutListener.listen(31495) ? (bmpHeight + boundsHeight) : (bmpHeight - boundsHeight)))))) * 2) : (ListenerUtil.mutListener.listen(31500) ? (((ListenerUtil.mutListener.listen(31498) ? (bmpHeight % boundsHeight) : (ListenerUtil.mutListener.listen(31497) ? (bmpHeight / boundsHeight) : (ListenerUtil.mutListener.listen(31496) ? (bmpHeight * boundsHeight) : (ListenerUtil.mutListener.listen(31495) ? (bmpHeight + boundsHeight) : (bmpHeight - boundsHeight)))))) - 2) : (ListenerUtil.mutListener.listen(31499) ? (((ListenerUtil.mutListener.listen(31498) ? (bmpHeight % boundsHeight) : (ListenerUtil.mutListener.listen(31497) ? (bmpHeight / boundsHeight) : (ListenerUtil.mutListener.listen(31496) ? (bmpHeight * boundsHeight) : (ListenerUtil.mutListener.listen(31495) ? (bmpHeight + boundsHeight) : (bmpHeight - boundsHeight)))))) + 2) : (((ListenerUtil.mutListener.listen(31498) ? (bmpHeight % boundsHeight) : (ListenerUtil.mutListener.listen(31497) ? (bmpHeight / boundsHeight) : (ListenerUtil.mutListener.listen(31496) ? (bmpHeight * boundsHeight) : (ListenerUtil.mutListener.listen(31495) ? (bmpHeight + boundsHeight) : (bmpHeight - boundsHeight)))))) / 2)))));
                if (!ListenerUtil.mutListener.listen(31503)) {
                    canvas.translate(0, textYCoordinate);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31505)) {
            // draws static layout on canvas
            sl.draw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(31506)) {
            canvas.restore();
        }
        return bmp;
    }

    @Override
    @NonNull
    public TextLayer getLayer() {
        return (TextLayer) layer;
    }

    @Override
    protected void drawContent(@NonNull Canvas canvas, @Nullable Paint drawingPaint) {
        if (!ListenerUtil.mutListener.listen(31508)) {
            if (bitmap != null) {
                if (!ListenerUtil.mutListener.listen(31507)) {
                    canvas.drawBitmap(bitmap, matrix, drawingPaint);
                }
            }
        }
    }

    @Override
    public boolean hasFixedPositionAndSize() {
        return false;
    }

    @Override
    public int getWidth() {
        return bitmap != null ? bitmap.getWidth() : 0;
    }

    @Override
    public int getHeight() {
        return bitmap != null ? bitmap.getHeight() : 0;
    }

    public void updateEntity() {
        if (!ListenerUtil.mutListener.listen(31509)) {
            updateEntity(true);
        }
    }
}

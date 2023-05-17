/**
 * *************************************************************************************
 *  Copyright (c) 2014 Michael Goldbach <michael@wildplot.com>                           *
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
package com.wildplot.android.rendering.graphics.wrapper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Wrapper of swing/awt graphics class for android use
 *
 * @author Michael Goldbach
 */
public class GraphicsWrap {

    private final Canvas canvas;

    private final Paint paint;

    public GraphicsWrap(Canvas canvas, Paint paint) {
        super();
        this.canvas = canvas;
        this.paint = paint;
    }

    public void drawLine(float x1, float y1, float x2, float y2) {
        Style oldStyle = paint.getStyle();
        if (!ListenerUtil.mutListener.listen(26821)) {
            paint.setStyle(Style.FILL_AND_STROKE);
        }
        if (!ListenerUtil.mutListener.listen(26822)) {
            canvas.drawLine(x1, y1, x2, y2, paint);
        }
        if (!ListenerUtil.mutListener.listen(26823)) {
            paint.setStyle(oldStyle);
        }
    }

    public void drawRect(float x, float y, float width, float height) {
        Style oldStyle = paint.getStyle();
        if (!ListenerUtil.mutListener.listen(26824)) {
            paint.setStyle(Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(26833)) {
            canvas.drawRect(x, y, (ListenerUtil.mutListener.listen(26828) ? (x % width) : (ListenerUtil.mutListener.listen(26827) ? (x / width) : (ListenerUtil.mutListener.listen(26826) ? (x * width) : (ListenerUtil.mutListener.listen(26825) ? (x - width) : (x + width))))), (ListenerUtil.mutListener.listen(26832) ? (y % height) : (ListenerUtil.mutListener.listen(26831) ? (y / height) : (ListenerUtil.mutListener.listen(26830) ? (y * height) : (ListenerUtil.mutListener.listen(26829) ? (y - height) : (y + height))))), paint);
        }
        if (!ListenerUtil.mutListener.listen(26834)) {
            paint.setStyle(oldStyle);
        }
    }

    public void fillRect(float x, float y, float width, float height) {
        Style oldStyle = paint.getStyle();
        if (!ListenerUtil.mutListener.listen(26835)) {
            paint.setStyle(Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(26844)) {
            canvas.drawRect(x, y, (ListenerUtil.mutListener.listen(26839) ? (x % width) : (ListenerUtil.mutListener.listen(26838) ? (x / width) : (ListenerUtil.mutListener.listen(26837) ? (x * width) : (ListenerUtil.mutListener.listen(26836) ? (x - width) : (x + width))))), (ListenerUtil.mutListener.listen(26843) ? (y % height) : (ListenerUtil.mutListener.listen(26842) ? (y / height) : (ListenerUtil.mutListener.listen(26841) ? (y * height) : (ListenerUtil.mutListener.listen(26840) ? (y - height) : (y + height))))), paint);
        }
        if (!ListenerUtil.mutListener.listen(26845)) {
            paint.setStyle(oldStyle);
        }
    }

    public StrokeWrap getStroke() {
        return new StrokeWrap(paint.getStrokeWidth());
    }

    public void setStroke(StrokeWrap stroke) {
        if (!ListenerUtil.mutListener.listen(26846)) {
            paint.setStrokeWidth(stroke.getStrokeSize());
        }
    }

    public RectangleWrap getClipBounds() {
        return new RectangleWrap(canvas.getClipBounds());
    }

    public ColorWrap getColor() {
        return new ColorWrap(paint.getColor());
    }

    public void setColor(ColorWrap color) {
        if (!ListenerUtil.mutListener.listen(26847)) {
            paint.setColor(color.getColorValue());
        }
    }

    public void drawArc(float x, float y, float width, float height, float startAngle, float arcAngle) {
        if (!ListenerUtil.mutListener.listen(26853)) {
            if ((ListenerUtil.mutListener.listen(26852) ? (arcAngle >= 0) : (ListenerUtil.mutListener.listen(26851) ? (arcAngle <= 0) : (ListenerUtil.mutListener.listen(26850) ? (arcAngle > 0) : (ListenerUtil.mutListener.listen(26849) ? (arcAngle < 0) : (ListenerUtil.mutListener.listen(26848) ? (arcAngle != 0) : (arcAngle == 0))))))) {
                return;
            }
        }
        Style oldStyle = paint.getStyle();
        if (!ListenerUtil.mutListener.listen(26854)) {
            paint.setStyle(Style.STROKE);
        }
        RectF rectF = new RectF(x, y, (ListenerUtil.mutListener.listen(26858) ? (x % width) : (ListenerUtil.mutListener.listen(26857) ? (x / width) : (ListenerUtil.mutListener.listen(26856) ? (x * width) : (ListenerUtil.mutListener.listen(26855) ? (x - width) : (x + width))))), (ListenerUtil.mutListener.listen(26862) ? (y % height) : (ListenerUtil.mutListener.listen(26861) ? (y / height) : (ListenerUtil.mutListener.listen(26860) ? (y * height) : (ListenerUtil.mutListener.listen(26859) ? (y - height) : (y + height))))));
        if (!ListenerUtil.mutListener.listen(26863)) {
            canvas.drawArc(rectF, startAngle, arcAngle, true, paint);
        }
        if (!ListenerUtil.mutListener.listen(26864)) {
            paint.setStyle(oldStyle);
        }
    }

    public void fillArc(float x, float y, float width, float height, float startAngle, float arcAngle) {
        if (!ListenerUtil.mutListener.listen(26870)) {
            if ((ListenerUtil.mutListener.listen(26869) ? (arcAngle >= 0) : (ListenerUtil.mutListener.listen(26868) ? (arcAngle <= 0) : (ListenerUtil.mutListener.listen(26867) ? (arcAngle > 0) : (ListenerUtil.mutListener.listen(26866) ? (arcAngle < 0) : (ListenerUtil.mutListener.listen(26865) ? (arcAngle != 0) : (arcAngle == 0))))))) {
                return;
            }
        }
        Style oldStyle = paint.getStyle();
        if (!ListenerUtil.mutListener.listen(26871)) {
            paint.setStyle(Style.FILL);
        }
        RectF rectF = new RectF(x, y, (ListenerUtil.mutListener.listen(26875) ? (x % width) : (ListenerUtil.mutListener.listen(26874) ? (x / width) : (ListenerUtil.mutListener.listen(26873) ? (x * width) : (ListenerUtil.mutListener.listen(26872) ? (x - width) : (x + width))))), (ListenerUtil.mutListener.listen(26879) ? (y % height) : (ListenerUtil.mutListener.listen(26878) ? (y / height) : (ListenerUtil.mutListener.listen(26877) ? (y * height) : (ListenerUtil.mutListener.listen(26876) ? (y - height) : (y + height))))));
        if (!ListenerUtil.mutListener.listen(26880)) {
            canvas.drawArc(rectF, startAngle, arcAngle, true, paint);
        }
        if (!ListenerUtil.mutListener.listen(26881)) {
            paint.setStyle(oldStyle);
        }
    }

    public void drawString(String text, float x, float y) {
        Style oldStyle = paint.getStyle();
        if (!ListenerUtil.mutListener.listen(26882)) {
            paint.setStyle(Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(26883)) {
            canvas.drawText(text, x, y, paint);
        }
        if (!ListenerUtil.mutListener.listen(26884)) {
            paint.setStyle(oldStyle);
        }
    }

    public Paint getPaint() {
        return paint;
    }

    public FontMetricsWrap getFontMetrics() {
        return new FontMetricsWrap(this);
    }

    public int save() {
        return canvas.save();
    }

    public void restore() {
        if (!ListenerUtil.mutListener.listen(26885)) {
            canvas.restore();
        }
    }

    public void rotate(float degree, float x, float y) {
        if (!ListenerUtil.mutListener.listen(26886)) {
            canvas.rotate(degree, x, y);
        }
    }

    public float getFontSize() {
        return paint.getTextSize();
    }

    public void setFontSize(float size) {
        if (!ListenerUtil.mutListener.listen(26887)) {
            paint.setTextSize(size);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (!ListenerUtil.mutListener.listen(26888)) {
            paint.setTypeface(typeface);
        }
    }

    public Typeface getTypeface() {
        return paint.getTypeface();
    }
}

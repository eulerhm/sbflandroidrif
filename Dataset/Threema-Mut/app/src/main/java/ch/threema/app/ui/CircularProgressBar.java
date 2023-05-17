/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CircularProgressBar extends View {

    private static final float DEFAULT_MAX_VALUE = 100;

    private static final float DEFAULT_START_ANGLE = 270;

    private float progress = 0;

    private float progressMax = DEFAULT_MAX_VALUE;

    private float strokeWidth = getResources().getDimension(R.dimen.conversation_controller_progress_stroke_width);

    private float backgroundStrokeWidth = getResources().getDimension(R.dimen.conversation_controller_progress_stroke_width);

    private float borderWidth = getResources().getDimension(R.dimen.conversation_controller_progress_border_width);

    private int color = Color.BLACK;

    private int backgroundColor = Color.GRAY;

    // Bounding box for progress bar
    private RectF rectF;

    private Paint backgroundPaint;

    private Paint foregroundPaint;

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(44771)) {
            init(context, attrs);
        }
    }

    private void init(Context context, AttributeSet attrs) {
        if (!ListenerUtil.mutListener.listen(44772)) {
            rectF = new RectF();
        }
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircularProgressBar, 0, 0);
        try {
            if (!ListenerUtil.mutListener.listen(44774)) {
                progress = typedArray.getFloat(R.styleable.CircularProgressBar_progressbar_progress, progress);
            }
            if (!ListenerUtil.mutListener.listen(44775)) {
                progressMax = typedArray.getFloat(R.styleable.CircularProgressBar_progressbar_max, progressMax);
            }
            if (!ListenerUtil.mutListener.listen(44776)) {
                strokeWidth = typedArray.getDimension(R.styleable.CircularProgressBar_progressbar_width, strokeWidth);
            }
            if (!ListenerUtil.mutListener.listen(44777)) {
                backgroundStrokeWidth = typedArray.getDimension(R.styleable.CircularProgressBar_background_width, backgroundStrokeWidth);
            }
            if (!ListenerUtil.mutListener.listen(44778)) {
                color = typedArray.getInt(R.styleable.CircularProgressBar_progressbar_color, color);
            }
            if (!ListenerUtil.mutListener.listen(44779)) {
                backgroundColor = typedArray.getInt(R.styleable.CircularProgressBar_background_color, backgroundColor);
            }
            if (!ListenerUtil.mutListener.listen(44780)) {
                borderWidth = typedArray.getDimension(R.styleable.CircularProgressBar_border_width, borderWidth);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(44773)) {
                typedArray.recycle();
            }
        }
        if (!ListenerUtil.mutListener.listen(44781)) {
            backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        if (!ListenerUtil.mutListener.listen(44782)) {
            backgroundPaint.setColor(backgroundColor);
        }
        if (!ListenerUtil.mutListener.listen(44783)) {
            backgroundPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(44784)) {
            backgroundPaint.setStrokeWidth(backgroundStrokeWidth);
        }
        if (!ListenerUtil.mutListener.listen(44785)) {
            foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        if (!ListenerUtil.mutListener.listen(44786)) {
            foregroundPaint.setColor(color);
        }
        if (!ListenerUtil.mutListener.listen(44787)) {
            foregroundPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(44788)) {
            foregroundPaint.setStrokeWidth(strokeWidth);
        }
    }

    @Override
    protected void onFinishInflate() {
        if (!ListenerUtil.mutListener.listen(44789)) {
            super.onFinishInflate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(44790)) {
            super.onDraw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(44791)) {
            canvas.drawOval(rectF, backgroundPaint);
        }
        float realProgress = (ListenerUtil.mutListener.listen(44799) ? ((ListenerUtil.mutListener.listen(44795) ? (progress % DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44794) ? (progress / DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44793) ? (progress - DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44792) ? (progress + DEFAULT_MAX_VALUE) : (progress * DEFAULT_MAX_VALUE))))) % progressMax) : (ListenerUtil.mutListener.listen(44798) ? ((ListenerUtil.mutListener.listen(44795) ? (progress % DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44794) ? (progress / DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44793) ? (progress - DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44792) ? (progress + DEFAULT_MAX_VALUE) : (progress * DEFAULT_MAX_VALUE))))) * progressMax) : (ListenerUtil.mutListener.listen(44797) ? ((ListenerUtil.mutListener.listen(44795) ? (progress % DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44794) ? (progress / DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44793) ? (progress - DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44792) ? (progress + DEFAULT_MAX_VALUE) : (progress * DEFAULT_MAX_VALUE))))) - progressMax) : (ListenerUtil.mutListener.listen(44796) ? ((ListenerUtil.mutListener.listen(44795) ? (progress % DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44794) ? (progress / DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44793) ? (progress - DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44792) ? (progress + DEFAULT_MAX_VALUE) : (progress * DEFAULT_MAX_VALUE))))) + progressMax) : ((ListenerUtil.mutListener.listen(44795) ? (progress % DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44794) ? (progress / DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44793) ? (progress - DEFAULT_MAX_VALUE) : (ListenerUtil.mutListener.listen(44792) ? (progress + DEFAULT_MAX_VALUE) : (progress * DEFAULT_MAX_VALUE))))) / progressMax)))));
        float angle = (ListenerUtil.mutListener.listen(44807) ? ((ListenerUtil.mutListener.listen(44803) ? (360 % realProgress) : (ListenerUtil.mutListener.listen(44802) ? (360 / realProgress) : (ListenerUtil.mutListener.listen(44801) ? (360 - realProgress) : (ListenerUtil.mutListener.listen(44800) ? (360 + realProgress) : (360 * realProgress))))) % 100) : (ListenerUtil.mutListener.listen(44806) ? ((ListenerUtil.mutListener.listen(44803) ? (360 % realProgress) : (ListenerUtil.mutListener.listen(44802) ? (360 / realProgress) : (ListenerUtil.mutListener.listen(44801) ? (360 - realProgress) : (ListenerUtil.mutListener.listen(44800) ? (360 + realProgress) : (360 * realProgress))))) * 100) : (ListenerUtil.mutListener.listen(44805) ? ((ListenerUtil.mutListener.listen(44803) ? (360 % realProgress) : (ListenerUtil.mutListener.listen(44802) ? (360 / realProgress) : (ListenerUtil.mutListener.listen(44801) ? (360 - realProgress) : (ListenerUtil.mutListener.listen(44800) ? (360 + realProgress) : (360 * realProgress))))) - 100) : (ListenerUtil.mutListener.listen(44804) ? ((ListenerUtil.mutListener.listen(44803) ? (360 % realProgress) : (ListenerUtil.mutListener.listen(44802) ? (360 / realProgress) : (ListenerUtil.mutListener.listen(44801) ? (360 - realProgress) : (ListenerUtil.mutListener.listen(44800) ? (360 + realProgress) : (360 * realProgress))))) + 100) : ((ListenerUtil.mutListener.listen(44803) ? (360 % realProgress) : (ListenerUtil.mutListener.listen(44802) ? (360 / realProgress) : (ListenerUtil.mutListener.listen(44801) ? (360 - realProgress) : (ListenerUtil.mutListener.listen(44800) ? (360 + realProgress) : (360 * realProgress))))) / 100)))));
        if (!ListenerUtil.mutListener.listen(44808)) {
            canvas.drawArc(rectF, DEFAULT_START_ANGLE, angle, false, foregroundPaint);
        }
    }

    private void reDraw() {
        if (!ListenerUtil.mutListener.listen(44809)) {
            requestLayout();
        }
        if (!ListenerUtil.mutListener.listen(44810)) {
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        if (!ListenerUtil.mutListener.listen(44811)) {
            setMeasuredDimension(min, min);
        }
        float highStroke = (ListenerUtil.mutListener.listen(44816) ? (strokeWidth >= backgroundStrokeWidth) : (ListenerUtil.mutListener.listen(44815) ? (strokeWidth <= backgroundStrokeWidth) : (ListenerUtil.mutListener.listen(44814) ? (strokeWidth < backgroundStrokeWidth) : (ListenerUtil.mutListener.listen(44813) ? (strokeWidth != backgroundStrokeWidth) : (ListenerUtil.mutListener.listen(44812) ? (strokeWidth == backgroundStrokeWidth) : (strokeWidth > backgroundStrokeWidth)))))) ? strokeWidth : backgroundStrokeWidth;
        if (!ListenerUtil.mutListener.listen(44857)) {
            rectF.set((ListenerUtil.mutListener.listen(44824) ? (borderWidth % (ListenerUtil.mutListener.listen(44820) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44819) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44818) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44817) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44823) ? (borderWidth / (ListenerUtil.mutListener.listen(44820) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44819) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44818) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44817) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44822) ? (borderWidth * (ListenerUtil.mutListener.listen(44820) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44819) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44818) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44817) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44821) ? (borderWidth - (ListenerUtil.mutListener.listen(44820) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44819) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44818) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44817) ? (highStroke + 2) : (highStroke / 2)))))) : (borderWidth + (ListenerUtil.mutListener.listen(44820) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44819) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44818) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44817) ? (highStroke + 2) : (highStroke / 2)))))))))), (ListenerUtil.mutListener.listen(44832) ? (borderWidth % (ListenerUtil.mutListener.listen(44828) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44827) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44826) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44825) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44831) ? (borderWidth / (ListenerUtil.mutListener.listen(44828) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44827) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44826) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44825) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44830) ? (borderWidth * (ListenerUtil.mutListener.listen(44828) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44827) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44826) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44825) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44829) ? (borderWidth - (ListenerUtil.mutListener.listen(44828) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44827) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44826) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44825) ? (highStroke + 2) : (highStroke / 2)))))) : (borderWidth + (ListenerUtil.mutListener.listen(44828) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44827) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44826) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44825) ? (highStroke + 2) : (highStroke / 2)))))))))), (ListenerUtil.mutListener.listen(44844) ? ((ListenerUtil.mutListener.listen(44836) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44835) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44834) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44833) ? (min + borderWidth) : (min - borderWidth))))) % (ListenerUtil.mutListener.listen(44840) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44839) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44838) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44837) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44843) ? ((ListenerUtil.mutListener.listen(44836) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44835) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44834) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44833) ? (min + borderWidth) : (min - borderWidth))))) / (ListenerUtil.mutListener.listen(44840) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44839) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44838) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44837) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44842) ? ((ListenerUtil.mutListener.listen(44836) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44835) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44834) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44833) ? (min + borderWidth) : (min - borderWidth))))) * (ListenerUtil.mutListener.listen(44840) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44839) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44838) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44837) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44841) ? ((ListenerUtil.mutListener.listen(44836) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44835) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44834) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44833) ? (min + borderWidth) : (min - borderWidth))))) + (ListenerUtil.mutListener.listen(44840) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44839) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44838) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44837) ? (highStroke + 2) : (highStroke / 2)))))) : ((ListenerUtil.mutListener.listen(44836) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44835) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44834) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44833) ? (min + borderWidth) : (min - borderWidth))))) - (ListenerUtil.mutListener.listen(44840) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44839) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44838) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44837) ? (highStroke + 2) : (highStroke / 2)))))))))), (ListenerUtil.mutListener.listen(44856) ? ((ListenerUtil.mutListener.listen(44848) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44847) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44846) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44845) ? (min + borderWidth) : (min - borderWidth))))) % (ListenerUtil.mutListener.listen(44852) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44851) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44850) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44849) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44855) ? ((ListenerUtil.mutListener.listen(44848) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44847) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44846) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44845) ? (min + borderWidth) : (min - borderWidth))))) / (ListenerUtil.mutListener.listen(44852) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44851) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44850) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44849) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44854) ? ((ListenerUtil.mutListener.listen(44848) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44847) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44846) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44845) ? (min + borderWidth) : (min - borderWidth))))) * (ListenerUtil.mutListener.listen(44852) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44851) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44850) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44849) ? (highStroke + 2) : (highStroke / 2)))))) : (ListenerUtil.mutListener.listen(44853) ? ((ListenerUtil.mutListener.listen(44848) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44847) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44846) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44845) ? (min + borderWidth) : (min - borderWidth))))) + (ListenerUtil.mutListener.listen(44852) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44851) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44850) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44849) ? (highStroke + 2) : (highStroke / 2)))))) : ((ListenerUtil.mutListener.listen(44848) ? (min % borderWidth) : (ListenerUtil.mutListener.listen(44847) ? (min / borderWidth) : (ListenerUtil.mutListener.listen(44846) ? (min * borderWidth) : (ListenerUtil.mutListener.listen(44845) ? (min + borderWidth) : (min - borderWidth))))) - (ListenerUtil.mutListener.listen(44852) ? (highStroke % 2) : (ListenerUtil.mutListener.listen(44851) ? (highStroke * 2) : (ListenerUtil.mutListener.listen(44850) ? (highStroke - 2) : (ListenerUtil.mutListener.listen(44849) ? (highStroke + 2) : (highStroke / 2)))))))))));
        }
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        if (!ListenerUtil.mutListener.listen(44863)) {
            this.progress = (ListenerUtil.mutListener.listen(44862) ? (progress >= progressMax) : (ListenerUtil.mutListener.listen(44861) ? (progress > progressMax) : (ListenerUtil.mutListener.listen(44860) ? (progress < progressMax) : (ListenerUtil.mutListener.listen(44859) ? (progress != progressMax) : (ListenerUtil.mutListener.listen(44858) ? (progress == progressMax) : (progress <= progressMax)))))) ? progress : progressMax;
        }
        if (!ListenerUtil.mutListener.listen(44864)) {
            invalidate();
        }
    }

    public float getMax() {
        return progressMax;
    }

    public void setMax(float progressMax) {
        if (!ListenerUtil.mutListener.listen(44870)) {
            this.progressMax = (ListenerUtil.mutListener.listen(44869) ? (progressMax <= 0) : (ListenerUtil.mutListener.listen(44868) ? (progressMax > 0) : (ListenerUtil.mutListener.listen(44867) ? (progressMax < 0) : (ListenerUtil.mutListener.listen(44866) ? (progressMax != 0) : (ListenerUtil.mutListener.listen(44865) ? (progressMax == 0) : (progressMax >= 0)))))) ? progressMax : DEFAULT_MAX_VALUE;
        }
        if (!ListenerUtil.mutListener.listen(44871)) {
            reDraw();
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if (!ListenerUtil.mutListener.listen(44872)) {
            this.color = color;
        }
        if (!ListenerUtil.mutListener.listen(44873)) {
            foregroundPaint.setColor(color);
        }
        if (!ListenerUtil.mutListener.listen(44874)) {
            reDraw();
        }
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        if (!ListenerUtil.mutListener.listen(44875)) {
            this.backgroundColor = backgroundColor;
        }
        if (!ListenerUtil.mutListener.listen(44876)) {
            backgroundPaint.setColor(backgroundColor);
        }
        if (!ListenerUtil.mutListener.listen(44877)) {
            reDraw();
        }
    }
}

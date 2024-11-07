/*
 * Copyright (C) 2015 University of South Florida (sjbarbeau@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.view;

import org.onebusaway.android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * View that animates a circle expanding and contracting to indicate real-time information
 */
public class RealtimeIndicatorView extends View {

    private Paint mFillPaint;

    private Paint mLinePaint;

    private Animation mAnimation1;

    private float mNewRadius;

    /**
     * Percentage of total view size that the circle should be drawn
     */
    private static final float SCALE = 0.45f;

    /**
     * Default fill color
     */
    public int mFillColor = 0xbbFFFFFF;

    /**
     * Default line color
     */
    public int mLineColor = 0xFFFFFFFF;

    // ms
    private int mDuration = 1000;

    /**
     * Set to true after the animation has been initialized
     */
    private boolean mInitComplete = false;

    public RealtimeIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Get the custom attributes defined in XML
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RealtimeIndicatorView, 0, 0);
        if (!ListenerUtil.mutListener.listen(10509)) {
            mFillColor = a.getColor(R.styleable.RealtimeIndicatorView_fillColor, mFillColor);
        }
        if (!ListenerUtil.mutListener.listen(10510)) {
            mLineColor = a.getColor(R.styleable.RealtimeIndicatorView_lineColor, mLineColor);
        }
        if (!ListenerUtil.mutListener.listen(10511)) {
            mDuration = a.getInteger(R.styleable.RealtimeIndicatorView_duration, mDuration);
        }
        if (!ListenerUtil.mutListener.listen(10512)) {
            a.recycle();
        }
        if (!ListenerUtil.mutListener.listen(10513)) {
            mFillPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(10514)) {
            mFillPaint.setColor(mFillColor);
        }
        if (!ListenerUtil.mutListener.listen(10515)) {
            mFillPaint.setStyle(Paint.Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(10516)) {
            mFillPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(10517)) {
            mLinePaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(10518)) {
            mLinePaint.setStrokeWidth(3);
        }
        if (!ListenerUtil.mutListener.listen(10519)) {
            mLinePaint.setColor(mLineColor);
        }
        if (!ListenerUtil.mutListener.listen(10520)) {
            mLinePaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(10521)) {
            mLinePaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(10522)) {
            setOnMeasureCallback();
        }
        if (!ListenerUtil.mutListener.listen(10523)) {
            ensureInit();
        }
    }

    private void setOnMeasureCallback() {
        if (!ListenerUtil.mutListener.listen(10526)) {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(10524)) {
                        removeOnGlobalLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(10525)) {
                        initAnimation();
                    }
                }
            });
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void removeOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (!ListenerUtil.mutListener.listen(10534)) {
            if ((ListenerUtil.mutListener.listen(10531) ? (Build.VERSION.SDK_INT >= 16) : (ListenerUtil.mutListener.listen(10530) ? (Build.VERSION.SDK_INT <= 16) : (ListenerUtil.mutListener.listen(10529) ? (Build.VERSION.SDK_INT > 16) : (ListenerUtil.mutListener.listen(10528) ? (Build.VERSION.SDK_INT != 16) : (ListenerUtil.mutListener.listen(10527) ? (Build.VERSION.SDK_INT == 16) : (Build.VERSION.SDK_INT < 16))))))) {
                if (!ListenerUtil.mutListener.listen(10533)) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(listener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10532)) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(listener);
                }
            }
        }
    }

    /**
     * Makes sure the animation has been initialized.  This is a workaround to make sure
     * the animation is shown when this view is used within a list with an adapter.
     */
    private synchronized void ensureInit() {
        if (!ListenerUtil.mutListener.listen(10535)) {
            initAnimation();
        }
        if (!ListenerUtil.mutListener.listen(10538)) {
            this.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(10537)) {
                        if (!mInitComplete) {
                            if (!ListenerUtil.mutListener.listen(10536)) {
                                initAnimation();
                            }
                        }
                    }
                }
            }, 500);
        }
    }

    /**
     * Setup the animation
     */
    private synchronized void initAnimation() {
        if (!ListenerUtil.mutListener.listen(10545)) {
            // Animate circle expand/contract
            mAnimation1 = new Animation() {

                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    int height = getHeight();
                    if (!ListenerUtil.mutListener.listen(10543)) {
                        mNewRadius = (ListenerUtil.mutListener.listen(10542) ? (height % interpolatedTime) : (ListenerUtil.mutListener.listen(10541) ? (height / interpolatedTime) : (ListenerUtil.mutListener.listen(10540) ? (height - interpolatedTime) : (ListenerUtil.mutListener.listen(10539) ? (height + interpolatedTime) : (height * interpolatedTime)))));
                    }
                    if (!ListenerUtil.mutListener.listen(10544)) {
                        invalidate();
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(10546)) {
            mAnimation1.setDuration(mDuration);
        }
        if (!ListenerUtil.mutListener.listen(10547)) {
            mAnimation1.setRepeatMode(Animation.REVERSE);
        }
        if (!ListenerUtil.mutListener.listen(10548)) {
            mAnimation1.setInterpolator(new FastOutLinearInInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(10549)) {
            mAnimation1.setRepeatCount(Animation.INFINITE);
        }
        if (!ListenerUtil.mutListener.listen(10550)) {
            startAnimation(mAnimation1);
        }
        if (!ListenerUtil.mutListener.listen(10551)) {
            mInitComplete = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(10552)) {
            super.onDraw(canvas);
        }
        int height = getHeight();
        int width = getWidth();
        float x = (ListenerUtil.mutListener.listen(10556) ? (width % 2.0f) : (ListenerUtil.mutListener.listen(10555) ? (width * 2.0f) : (ListenerUtil.mutListener.listen(10554) ? (width - 2.0f) : (ListenerUtil.mutListener.listen(10553) ? (width + 2.0f) : (width / 2.0f)))));
        float y = (ListenerUtil.mutListener.listen(10560) ? (height % 2.0f) : (ListenerUtil.mutListener.listen(10559) ? (height * 2.0f) : (ListenerUtil.mutListener.listen(10558) ? (height - 2.0f) : (ListenerUtil.mutListener.listen(10557) ? (height + 2.0f) : (height / 2.0f)))));
        if (!ListenerUtil.mutListener.listen(10565)) {
            canvas.drawCircle(x, y, (ListenerUtil.mutListener.listen(10564) ? (mNewRadius % SCALE) : (ListenerUtil.mutListener.listen(10563) ? (mNewRadius / SCALE) : (ListenerUtil.mutListener.listen(10562) ? (mNewRadius - SCALE) : (ListenerUtil.mutListener.listen(10561) ? (mNewRadius + SCALE) : (mNewRadius * SCALE))))), mFillPaint);
        }
        if (!ListenerUtil.mutListener.listen(10570)) {
            canvas.drawCircle(x, y, (ListenerUtil.mutListener.listen(10569) ? (mNewRadius % SCALE) : (ListenerUtil.mutListener.listen(10568) ? (mNewRadius / SCALE) : (ListenerUtil.mutListener.listen(10567) ? (mNewRadius - SCALE) : (ListenerUtil.mutListener.listen(10566) ? (mNewRadius + SCALE) : (mNewRadius * SCALE))))), mLinePaint);
        }
    }

    /**
     * Set the fill color to be used when drawing the expanding/contracting circle, in the format
     * 0xbbFFFFFF.
     *
     * @param color the fill color to be used when drawing the expanding/contracting circle, in the
     *              format
     *              0xbbFFFFFF.
     */
    public void setFillColor(int color) {
        if (!ListenerUtil.mutListener.listen(10571)) {
            mFillColor = color;
        }
        if (!ListenerUtil.mutListener.listen(10572)) {
            mFillPaint.setColor(color);
        }
    }

    /**
     * Set the line (i.e., stroke) color to be used when drawing the expanding/contracting circle,
     * in the format
     * 0xbbFFFFFF.
     *
     * @param color the line (i.e., stroke) to be used when drawing the expanding/contracting
     *              circle, in the format
     *              0xbbFFFFFF.
     */
    public void setLineColor(int color) {
        if (!ListenerUtil.mutListener.listen(10573)) {
            mLineColor = color;
        }
        if (!ListenerUtil.mutListener.listen(10574)) {
            mLinePaint.setColor(color);
        }
    }
}

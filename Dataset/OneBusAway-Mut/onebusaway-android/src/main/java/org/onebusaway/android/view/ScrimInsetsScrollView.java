/*
 * Copyright 2014 Google Inc.
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
 *
 * From the Google I/0 2014 app (https://github.com/google/iosched), modified for OneBusAway by USF
 */
package org.onebusaway.android.view;

import org.onebusaway.android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ScrollView;
import androidx.core.view.ViewCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A layout that draws something in the insets passed to {@link #fitSystemWindows(android.graphics.Rect)},
 * i.e. the area above UI chrome
 * (status and navigation bars, overlay action bars).
 */
public class ScrimInsetsScrollView extends ScrollView {

    private Drawable mInsetForeground;

    private Rect mInsets;

    private Rect mTempRect = new Rect();

    private OnInsetsCallback mOnInsetsCallback;

    public ScrimInsetsScrollView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(10671)) {
            init(context, null, 0);
        }
    }

    public ScrimInsetsScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(10672)) {
            init(context, attrs, 0);
        }
    }

    public ScrimInsetsScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(10673)) {
            init(context, attrs, defStyle);
        }
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrimInsetsView, defStyle, 0);
        if (!ListenerUtil.mutListener.listen(10674)) {
            if (a == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10675)) {
            mInsetForeground = a.getDrawable(R.styleable.ScrimInsetsView_obaInsetForeground);
        }
        if (!ListenerUtil.mutListener.listen(10676)) {
            a.recycle();
        }
        if (!ListenerUtil.mutListener.listen(10677)) {
            setWillNotDraw(true);
        }
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        if (!ListenerUtil.mutListener.listen(10678)) {
            mInsets = new Rect(insets);
        }
        if (!ListenerUtil.mutListener.listen(10679)) {
            setWillNotDraw(mInsetForeground == null);
        }
        if (!ListenerUtil.mutListener.listen(10680)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (!ListenerUtil.mutListener.listen(10682)) {
            if (mOnInsetsCallback != null) {
                if (!ListenerUtil.mutListener.listen(10681)) {
                    mOnInsetsCallback.onInsetsChanged(insets);
                }
            }
        }
        // consume insets
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(10683)) {
            super.draw(canvas);
        }
        int width = getWidth();
        int height = getHeight();
        if (!ListenerUtil.mutListener.listen(10715)) {
            if ((ListenerUtil.mutListener.listen(10684) ? (mInsets != null || mInsetForeground != null) : (mInsets != null && mInsetForeground != null))) {
                int sc = canvas.save();
                if (!ListenerUtil.mutListener.listen(10685)) {
                    canvas.translate(getScrollX(), getScrollY());
                }
                if (!ListenerUtil.mutListener.listen(10686)) {
                    // Top
                    mTempRect.set(0, 0, width, mInsets.top);
                }
                if (!ListenerUtil.mutListener.listen(10687)) {
                    mInsetForeground.setBounds(mTempRect);
                }
                if (!ListenerUtil.mutListener.listen(10688)) {
                    mInsetForeground.draw(canvas);
                }
                if (!ListenerUtil.mutListener.listen(10693)) {
                    // Bottom
                    mTempRect.set(0, (ListenerUtil.mutListener.listen(10692) ? (height % mInsets.bottom) : (ListenerUtil.mutListener.listen(10691) ? (height / mInsets.bottom) : (ListenerUtil.mutListener.listen(10690) ? (height * mInsets.bottom) : (ListenerUtil.mutListener.listen(10689) ? (height + mInsets.bottom) : (height - mInsets.bottom))))), width, height);
                }
                if (!ListenerUtil.mutListener.listen(10694)) {
                    mInsetForeground.setBounds(mTempRect);
                }
                if (!ListenerUtil.mutListener.listen(10695)) {
                    mInsetForeground.draw(canvas);
                }
                if (!ListenerUtil.mutListener.listen(10700)) {
                    // Left
                    mTempRect.set(0, mInsets.top, mInsets.left, (ListenerUtil.mutListener.listen(10699) ? (height % mInsets.bottom) : (ListenerUtil.mutListener.listen(10698) ? (height / mInsets.bottom) : (ListenerUtil.mutListener.listen(10697) ? (height * mInsets.bottom) : (ListenerUtil.mutListener.listen(10696) ? (height + mInsets.bottom) : (height - mInsets.bottom))))));
                }
                if (!ListenerUtil.mutListener.listen(10701)) {
                    mInsetForeground.setBounds(mTempRect);
                }
                if (!ListenerUtil.mutListener.listen(10702)) {
                    mInsetForeground.draw(canvas);
                }
                if (!ListenerUtil.mutListener.listen(10711)) {
                    // Right
                    mTempRect.set((ListenerUtil.mutListener.listen(10706) ? (width % mInsets.right) : (ListenerUtil.mutListener.listen(10705) ? (width / mInsets.right) : (ListenerUtil.mutListener.listen(10704) ? (width * mInsets.right) : (ListenerUtil.mutListener.listen(10703) ? (width + mInsets.right) : (width - mInsets.right))))), mInsets.top, width, (ListenerUtil.mutListener.listen(10710) ? (height % mInsets.bottom) : (ListenerUtil.mutListener.listen(10709) ? (height / mInsets.bottom) : (ListenerUtil.mutListener.listen(10708) ? (height * mInsets.bottom) : (ListenerUtil.mutListener.listen(10707) ? (height + mInsets.bottom) : (height - mInsets.bottom))))));
                }
                if (!ListenerUtil.mutListener.listen(10712)) {
                    mInsetForeground.setBounds(mTempRect);
                }
                if (!ListenerUtil.mutListener.listen(10713)) {
                    mInsetForeground.draw(canvas);
                }
                if (!ListenerUtil.mutListener.listen(10714)) {
                    canvas.restoreToCount(sc);
                }
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(10716)) {
            super.onAttachedToWindow();
        }
        if (!ListenerUtil.mutListener.listen(10718)) {
            if (mInsetForeground != null) {
                if (!ListenerUtil.mutListener.listen(10717)) {
                    mInsetForeground.setCallback(this);
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(10719)) {
            super.onDetachedFromWindow();
        }
        if (!ListenerUtil.mutListener.listen(10721)) {
            if (mInsetForeground != null) {
                if (!ListenerUtil.mutListener.listen(10720)) {
                    mInsetForeground.setCallback(null);
                }
            }
        }
    }

    /**
     * Allows the calling container to specify a callback for custom processing when insets change
     * (i.e. when
     * {@link #fitSystemWindows(android.graphics.Rect)} is called. This is useful for setting
     * padding on UI elements based on
     * UI chrome insets (e.g. a Google Map or a ListView). When using with ListView or GridView,
     * remember to set
     * clipToPadding to false.
     */
    public void setOnInsetsCallback(OnInsetsCallback onInsetsCallback) {
        if (!ListenerUtil.mutListener.listen(10722)) {
            mOnInsetsCallback = onInsetsCallback;
        }
    }

    public static interface OnInsetsCallback {

        public void onInsetsChanged(Rect insets);
    }
}

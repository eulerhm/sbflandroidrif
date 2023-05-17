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
package com.ichi2.anki.stats;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.ichi2.anki.Statistics;
import com.wildplot.android.rendering.PlotSheet;
import com.wildplot.android.rendering.graphics.wrapper.GraphicsWrap;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ChartView extends View {

    private Statistics.ChartFragment mFragment;

    private PlotSheet mPlotSheet;

    private boolean mDataIsSet;

    // The following constructors are needed for the layout inflater
    public ChartView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(3655)) {
            setWillNotDraw(false);
        }
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(3656)) {
            setWillNotDraw(false);
        }
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(3657)) {
            setWillNotDraw(false);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(3665)) {
            // Timber.d("drawing chart");
            if (mDataIsSet) {
                // Paint paint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                Paint paint = new Paint(Paint.LINEAR_TEXT_FLAG);
                if (!ListenerUtil.mutListener.listen(3659)) {
                    paint.setAntiAlias(true);
                }
                if (!ListenerUtil.mutListener.listen(3660)) {
                    paint.setStyle(Paint.Style.STROKE);
                }
                GraphicsWrap g = new GraphicsWrap(canvas, paint);
                Rect field = new Rect();
                if (!ListenerUtil.mutListener.listen(3661)) {
                    this.getDrawingRect(field);
                }
                if (!ListenerUtil.mutListener.listen(3664)) {
                    if (mPlotSheet != null) {
                        if (!ListenerUtil.mutListener.listen(3663)) {
                            mPlotSheet.paint(g);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3662)) {
                            super.onDraw(canvas);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3658)) {
                    super.onDraw(canvas);
                }
            }
        }
    }

    public void addFragment(Statistics.ChartFragment fragment) {
        if (!ListenerUtil.mutListener.listen(3666)) {
            mFragment = fragment;
        }
    }

    public void setData(PlotSheet plotSheet) {
        if (!ListenerUtil.mutListener.listen(3667)) {
            mPlotSheet = plotSheet;
        }
        if (!ListenerUtil.mutListener.listen(3668)) {
            mDataIsSet = true;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (!ListenerUtil.mutListener.listen(3669)) {
            super.onSizeChanged(w, h, oldw, oldh);
        }
        if (!ListenerUtil.mutListener.listen(3670)) {
            Timber.d("ChartView sizeChange!");
        }
        if (!ListenerUtil.mutListener.listen(3672)) {
            if (mFragment != null) {
                if (!ListenerUtil.mutListener.listen(3671)) {
                    mFragment.checkAndUpdate();
                }
            }
        }
    }
}

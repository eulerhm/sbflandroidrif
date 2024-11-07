/*
 * Copyright (C) 2014 Sean J. Barbeau (sjbarbeau@gmail.com), University of South Florida
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
import org.onebusaway.android.util.LocationHelper;
import org.onebusaway.android.util.MathUtils;
import org.onebusaway.android.util.OrientationHelper;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.location.Location;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * View that draws an arrow that points towards the given bus mStop
 */
public class ArrowView extends View implements OrientationHelper.Listener, LocationHelper.Listener {

    public interface Listener {

        /**
         * Called when the ArrowView is showing information to the user
         */
        void onInitializationComplete();
    }

    ArrayList<Listener> mListeners = new ArrayList<Listener>();

    private float mHeading;

    private Paint mArrowPaint;

    private Paint mArrowFillPaint;

    private Location mLastLocation;

    Location mStopLocation = new Location("stopLocation");

    float mBearingToStop;

    boolean mInitialized = false;

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(10575)) {
            mArrowPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(10576)) {
            mArrowPaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(10577)) {
            mArrowPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(10578)) {
            mArrowPaint.setStrokeWidth(4.0f);
        }
        if (!ListenerUtil.mutListener.listen(10579)) {
            mArrowPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(10580)) {
            mArrowFillPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(10581)) {
            mArrowFillPaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(10582)) {
            mArrowFillPaint.setStyle(Paint.Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(10583)) {
            mArrowFillPaint.setStrokeWidth(4.0f);
        }
        if (!ListenerUtil.mutListener.listen(10584)) {
            mArrowFillPaint.setAntiAlias(true);
        }
    }

    public void setStopLocation(Location location) {
        if (!ListenerUtil.mutListener.listen(10585)) {
            mStopLocation = location;
        }
    }

    public synchronized void registerListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(10587)) {
            if (!mListeners.contains(listener)) {
                if (!ListenerUtil.mutListener.listen(10586)) {
                    mListeners.add(listener);
                }
            }
        }
    }

    public synchronized void unregisterListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(10589)) {
            if (mListeners.contains(listener)) {
                if (!ListenerUtil.mutListener.listen(10588)) {
                    mListeners.remove(listener);
                }
            }
        }
    }

    /**
     * Returns true if the view is initialized and ready to draw to the screen, false if it is not
     *
     * @return true if the view is initialized and ready to draw to the screen, false if it is not
     */
    public boolean isInitialized() {
        return mInitialized;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(10591)) {
            if ((ListenerUtil.mutListener.listen(10590) ? (mStopLocation == null && mLastLocation == null) : (mStopLocation == null || mLastLocation == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10592)) {
            drawArrow(canvas);
        }
    }

    @Override
    public void onOrientationChanged(float heading, float pitch, float xDelta, float yDelta) {
        if (!ListenerUtil.mutListener.listen(10593)) {
            mHeading = heading;
        }
        if (!ListenerUtil.mutListener.listen(10594)) {
            invalidate();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (!ListenerUtil.mutListener.listen(10595)) {
            mLastLocation = location;
        }
        if (!ListenerUtil.mutListener.listen(10609)) {
            if (mStopLocation != null) {
                if (!ListenerUtil.mutListener.listen(10599)) {
                    if (!mInitialized) {
                        if (!ListenerUtil.mutListener.listen(10596)) {
                            mInitialized = true;
                        }
                        if (!ListenerUtil.mutListener.listen(10598)) {
                            {
                                long _loopCounter139 = 0;
                                // Notify listeners that we have both stop and real-time location and can draw
                                for (Listener l : mListeners) {
                                    ListenerUtil.loopListener.listen("_loopCounter139", ++_loopCounter139);
                                    if (!ListenerUtil.mutListener.listen(10597)) {
                                        l.onInitializationComplete();
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10600)) {
                    mBearingToStop = location.bearingTo(mStopLocation);
                }
                if (!ListenerUtil.mutListener.listen(10607)) {
                    // See http://stackoverflow.com/a/8043485/937715
                    if ((ListenerUtil.mutListener.listen(10605) ? (mBearingToStop >= 0) : (ListenerUtil.mutListener.listen(10604) ? (mBearingToStop <= 0) : (ListenerUtil.mutListener.listen(10603) ? (mBearingToStop > 0) : (ListenerUtil.mutListener.listen(10602) ? (mBearingToStop != 0) : (ListenerUtil.mutListener.listen(10601) ? (mBearingToStop == 0) : (mBearingToStop < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(10606)) {
                            mBearingToStop += 360;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10608)) {
                    invalidate();
                }
            }
        }
    }

    private void drawArrow(Canvas c) {
        int height = getHeight();
        int width = getWidth();
        // Create a buffer around the arrow so when it rotates it doesn't get clipped by view edge
        final float BUFFER = (ListenerUtil.mutListener.listen(10613) ? (width % 5) : (ListenerUtil.mutListener.listen(10612) ? (width * 5) : (ListenerUtil.mutListener.listen(10611) ? (width - 5) : (ListenerUtil.mutListener.listen(10610) ? (width + 5) : (width / 5)))));
        // Height of the cutout in the bottom of the triangle that makes it an arrow (0=triangle)
        final float CUTOUT_HEIGHT = (ListenerUtil.mutListener.listen(10617) ? (getHeight() % 5) : (ListenerUtil.mutListener.listen(10616) ? (getHeight() * 5) : (ListenerUtil.mutListener.listen(10615) ? (getHeight() - 5) : (ListenerUtil.mutListener.listen(10614) ? (getHeight() + 5) : (getHeight() / 5)))));
        // Tip of arrow
        float x1, y1;
        x1 = (ListenerUtil.mutListener.listen(10621) ? (width % 2) : (ListenerUtil.mutListener.listen(10620) ? (width * 2) : (ListenerUtil.mutListener.listen(10619) ? (width - 2) : (ListenerUtil.mutListener.listen(10618) ? (width + 2) : (width / 2)))));
        y1 = BUFFER;
        // lower left
        float x2, y2;
        x2 = BUFFER;
        y2 = (ListenerUtil.mutListener.listen(10625) ? (height % BUFFER) : (ListenerUtil.mutListener.listen(10624) ? (height / BUFFER) : (ListenerUtil.mutListener.listen(10623) ? (height * BUFFER) : (ListenerUtil.mutListener.listen(10622) ? (height + BUFFER) : (height - BUFFER)))));
        // cutout in arrow bottom
        float x3, y3;
        x3 = (ListenerUtil.mutListener.listen(10629) ? (width % 2) : (ListenerUtil.mutListener.listen(10628) ? (width * 2) : (ListenerUtil.mutListener.listen(10627) ? (width - 2) : (ListenerUtil.mutListener.listen(10626) ? (width + 2) : (width / 2)))));
        y3 = (ListenerUtil.mutListener.listen(10637) ? ((ListenerUtil.mutListener.listen(10633) ? (height % CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10632) ? (height / CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10631) ? (height * CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10630) ? (height + CUTOUT_HEIGHT) : (height - CUTOUT_HEIGHT))))) % BUFFER) : (ListenerUtil.mutListener.listen(10636) ? ((ListenerUtil.mutListener.listen(10633) ? (height % CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10632) ? (height / CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10631) ? (height * CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10630) ? (height + CUTOUT_HEIGHT) : (height - CUTOUT_HEIGHT))))) / BUFFER) : (ListenerUtil.mutListener.listen(10635) ? ((ListenerUtil.mutListener.listen(10633) ? (height % CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10632) ? (height / CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10631) ? (height * CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10630) ? (height + CUTOUT_HEIGHT) : (height - CUTOUT_HEIGHT))))) * BUFFER) : (ListenerUtil.mutListener.listen(10634) ? ((ListenerUtil.mutListener.listen(10633) ? (height % CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10632) ? (height / CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10631) ? (height * CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10630) ? (height + CUTOUT_HEIGHT) : (height - CUTOUT_HEIGHT))))) + BUFFER) : ((ListenerUtil.mutListener.listen(10633) ? (height % CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10632) ? (height / CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10631) ? (height * CUTOUT_HEIGHT) : (ListenerUtil.mutListener.listen(10630) ? (height + CUTOUT_HEIGHT) : (height - CUTOUT_HEIGHT))))) - BUFFER)))));
        // lower right
        float x4, y4;
        x4 = (ListenerUtil.mutListener.listen(10641) ? (width % BUFFER) : (ListenerUtil.mutListener.listen(10640) ? (width / BUFFER) : (ListenerUtil.mutListener.listen(10639) ? (width * BUFFER) : (ListenerUtil.mutListener.listen(10638) ? (width + BUFFER) : (width - BUFFER)))));
        y4 = (ListenerUtil.mutListener.listen(10645) ? (height % BUFFER) : (ListenerUtil.mutListener.listen(10644) ? (height / BUFFER) : (ListenerUtil.mutListener.listen(10643) ? (height * BUFFER) : (ListenerUtil.mutListener.listen(10642) ? (height + BUFFER) : (height - BUFFER)))));
        Path path = new Path();
        if (!ListenerUtil.mutListener.listen(10646)) {
            path.setFillType(Path.FillType.EVEN_ODD);
        }
        if (!ListenerUtil.mutListener.listen(10647)) {
            path.moveTo(x1, y1);
        }
        if (!ListenerUtil.mutListener.listen(10648)) {
            path.lineTo(x2, y2);
        }
        if (!ListenerUtil.mutListener.listen(10649)) {
            path.lineTo(x3, y3);
        }
        if (!ListenerUtil.mutListener.listen(10650)) {
            path.lineTo(x4, y4);
        }
        if (!ListenerUtil.mutListener.listen(10651)) {
            path.lineTo(x1, y1);
        }
        if (!ListenerUtil.mutListener.listen(10652)) {
            path.close();
        }
        float direction = (ListenerUtil.mutListener.listen(10656) ? (mHeading % mBearingToStop) : (ListenerUtil.mutListener.listen(10655) ? (mHeading / mBearingToStop) : (ListenerUtil.mutListener.listen(10654) ? (mHeading * mBearingToStop) : (ListenerUtil.mutListener.listen(10653) ? (mHeading + mBearingToStop) : (mHeading - mBearingToStop)))));
        if (!ListenerUtil.mutListener.listen(10657)) {
            // Make sure value is between 0-360
            direction = MathUtils.mod(direction, 360.0f);
        }
        // Rotate arrow around center point
        Matrix matrix = new Matrix();
        if (!ListenerUtil.mutListener.listen(10666)) {
            matrix.postRotate((float) -direction, (ListenerUtil.mutListener.listen(10661) ? (width % 2) : (ListenerUtil.mutListener.listen(10660) ? (width * 2) : (ListenerUtil.mutListener.listen(10659) ? (width - 2) : (ListenerUtil.mutListener.listen(10658) ? (width + 2) : (width / 2))))), (ListenerUtil.mutListener.listen(10665) ? (height % 2) : (ListenerUtil.mutListener.listen(10664) ? (height * 2) : (ListenerUtil.mutListener.listen(10663) ? (height - 2) : (ListenerUtil.mutListener.listen(10662) ? (height + 2) : (height / 2))))));
        }
        if (!ListenerUtil.mutListener.listen(10667)) {
            path.transform(matrix);
        }
        if (!ListenerUtil.mutListener.listen(10668)) {
            c.drawPath(path, mArrowPaint);
        }
        if (!ListenerUtil.mutListener.listen(10669)) {
            c.drawPath(path, mArrowFillPaint);
        }
        // Update content description, so screen readers can announce direction to stop
        String[] spokenDirections = getResources().getStringArray(R.array.spoken_compass_directions);
        String directionName = spokenDirections[MathUtils.getHalfWindIndex(direction)];
        if (!ListenerUtil.mutListener.listen(10670)) {
            setContentDescription(directionName);
        }
    }
}

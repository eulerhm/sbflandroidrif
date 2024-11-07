/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.TextureView;
import android.view.View;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ZoomableTextureView extends TextureView {

    private static final String SUPERSTATE_KEY = "superState";

    private static final String MIN_SCALE_KEY = "minScale";

    private static final String MAX_SCALE_KEY = "maxScale";

    private Context context;

    private float minScale = 1f;

    private float maxScale = 5f;

    private float saveScale = 1f;

    public void setMinScale(float scale) {
        if (!ListenerUtil.mutListener.listen(48444)) {
            if ((ListenerUtil.mutListener.listen(48442) ? ((ListenerUtil.mutListener.listen(48436) ? (scale >= 1.0f) : (ListenerUtil.mutListener.listen(48435) ? (scale <= 1.0f) : (ListenerUtil.mutListener.listen(48434) ? (scale > 1.0f) : (ListenerUtil.mutListener.listen(48433) ? (scale != 1.0f) : (ListenerUtil.mutListener.listen(48432) ? (scale == 1.0f) : (scale < 1.0f)))))) && (ListenerUtil.mutListener.listen(48441) ? (scale >= maxScale) : (ListenerUtil.mutListener.listen(48440) ? (scale <= maxScale) : (ListenerUtil.mutListener.listen(48439) ? (scale < maxScale) : (ListenerUtil.mutListener.listen(48438) ? (scale != maxScale) : (ListenerUtil.mutListener.listen(48437) ? (scale == maxScale) : (scale > maxScale))))))) : ((ListenerUtil.mutListener.listen(48436) ? (scale >= 1.0f) : (ListenerUtil.mutListener.listen(48435) ? (scale <= 1.0f) : (ListenerUtil.mutListener.listen(48434) ? (scale > 1.0f) : (ListenerUtil.mutListener.listen(48433) ? (scale != 1.0f) : (ListenerUtil.mutListener.listen(48432) ? (scale == 1.0f) : (scale < 1.0f)))))) || (ListenerUtil.mutListener.listen(48441) ? (scale >= maxScale) : (ListenerUtil.mutListener.listen(48440) ? (scale <= maxScale) : (ListenerUtil.mutListener.listen(48439) ? (scale < maxScale) : (ListenerUtil.mutListener.listen(48438) ? (scale != maxScale) : (ListenerUtil.mutListener.listen(48437) ? (scale == maxScale) : (scale > maxScale)))))))))
                throw new RuntimeException("minScale can't be lower than 1 or larger than maxScale(" + maxScale + ")");
            else {
                if (!ListenerUtil.mutListener.listen(48443)) {
                    minScale = scale;
                }
            }
        }
    }

    public void setMaxScale(float scale) {
        if (!ListenerUtil.mutListener.listen(48457)) {
            if ((ListenerUtil.mutListener.listen(48455) ? ((ListenerUtil.mutListener.listen(48449) ? (scale >= 1.0f) : (ListenerUtil.mutListener.listen(48448) ? (scale <= 1.0f) : (ListenerUtil.mutListener.listen(48447) ? (scale > 1.0f) : (ListenerUtil.mutListener.listen(48446) ? (scale != 1.0f) : (ListenerUtil.mutListener.listen(48445) ? (scale == 1.0f) : (scale < 1.0f)))))) && (ListenerUtil.mutListener.listen(48454) ? (scale >= minScale) : (ListenerUtil.mutListener.listen(48453) ? (scale <= minScale) : (ListenerUtil.mutListener.listen(48452) ? (scale > minScale) : (ListenerUtil.mutListener.listen(48451) ? (scale != minScale) : (ListenerUtil.mutListener.listen(48450) ? (scale == minScale) : (scale < minScale))))))) : ((ListenerUtil.mutListener.listen(48449) ? (scale >= 1.0f) : (ListenerUtil.mutListener.listen(48448) ? (scale <= 1.0f) : (ListenerUtil.mutListener.listen(48447) ? (scale > 1.0f) : (ListenerUtil.mutListener.listen(48446) ? (scale != 1.0f) : (ListenerUtil.mutListener.listen(48445) ? (scale == 1.0f) : (scale < 1.0f)))))) || (ListenerUtil.mutListener.listen(48454) ? (scale >= minScale) : (ListenerUtil.mutListener.listen(48453) ? (scale <= minScale) : (ListenerUtil.mutListener.listen(48452) ? (scale > minScale) : (ListenerUtil.mutListener.listen(48451) ? (scale != minScale) : (ListenerUtil.mutListener.listen(48450) ? (scale == minScale) : (scale < minScale)))))))))
                throw new RuntimeException("maxScale can't be lower than 1 or minScale(" + minScale + ")");
            else {
                if (!ListenerUtil.mutListener.listen(48456)) {
                    minScale = scale;
                }
            }
        }
    }

    private static final int NONE = 0;

    private static final int DRAG = 1;

    private static final int ZOOM = 2;

    private int mode = NONE;

    private Matrix matrix = new Matrix();

    private ScaleGestureDetector mScaleDetector;

    private float[] m;

    private PointF last = new PointF();

    private PointF start = new PointF();

    private float right, bottom;

    public ZoomableTextureView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(48458)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(48459)) {
            initView(null);
        }
    }

    public ZoomableTextureView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(48460)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(48461)) {
            initView(attrs);
        }
    }

    public ZoomableTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(48462)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(48463)) {
            initView(attrs);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(48464)) {
            bundle.putParcelable(SUPERSTATE_KEY, super.onSaveInstanceState());
        }
        if (!ListenerUtil.mutListener.listen(48465)) {
            bundle.putFloat(MIN_SCALE_KEY, minScale);
        }
        if (!ListenerUtil.mutListener.listen(48466)) {
            bundle.putFloat(MAX_SCALE_KEY, maxScale);
        }
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!ListenerUtil.mutListener.listen(48470)) {
            if (state instanceof Bundle) {
                Bundle bundle = (Bundle) state;
                if (!ListenerUtil.mutListener.listen(48467)) {
                    this.minScale = bundle.getInt(MIN_SCALE_KEY);
                }
                if (!ListenerUtil.mutListener.listen(48468)) {
                    this.minScale = bundle.getInt(MAX_SCALE_KEY);
                }
                if (!ListenerUtil.mutListener.listen(48469)) {
                    state = bundle.getParcelable(SUPERSTATE_KEY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48471)) {
            super.onRestoreInstanceState(state);
        }
    }

    private void initView(AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ZoomableTextureView, 0, 0);
        try {
            if (!ListenerUtil.mutListener.listen(48473)) {
                minScale = a.getFloat(R.styleable.ZoomableTextureView_minScale, minScale);
            }
            if (!ListenerUtil.mutListener.listen(48474)) {
                maxScale = a.getFloat(R.styleable.ZoomableTextureView_maxScale, maxScale);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(48472)) {
                a.recycle();
            }
        }
        if (!ListenerUtil.mutListener.listen(48475)) {
            setOnTouchListener(new ZoomOnTouchListeners());
        }
    }

    private class ZoomOnTouchListeners implements View.OnTouchListener {

        ZoomOnTouchListeners() {
            super();
            if (!ListenerUtil.mutListener.listen(48476)) {
                m = new float[9];
            }
            if (!ListenerUtil.mutListener.listen(48477)) {
                mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
            }
        }

        private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (!ListenerUtil.mutListener.listen(48478)) {
                    performClick();
                }
                return false;
            }
        });

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!ListenerUtil.mutListener.listen(48479)) {
                gestureDetector.onTouchEvent(motionEvent);
            }
            if (!ListenerUtil.mutListener.listen(48480)) {
                mScaleDetector.onTouchEvent(motionEvent);
            }
            if (!ListenerUtil.mutListener.listen(48481)) {
                matrix.getValues(m);
            }
            float x = m[Matrix.MTRANS_X];
            float y = m[Matrix.MTRANS_Y];
            PointF curr = new PointF(motionEvent.getX(), motionEvent.getY());
            if (!ListenerUtil.mutListener.listen(48536)) {
                switch(motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!ListenerUtil.mutListener.listen(48482)) {
                            last.set(motionEvent.getX(), motionEvent.getY());
                        }
                        if (!ListenerUtil.mutListener.listen(48483)) {
                            start.set(last);
                        }
                        if (!ListenerUtil.mutListener.listen(48484)) {
                            mode = DRAG;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!ListenerUtil.mutListener.listen(48485)) {
                            saveScale = 1.0f;
                        }
                        if (!ListenerUtil.mutListener.listen(48486)) {
                            matrix = new Matrix();
                        }
                        if (!ListenerUtil.mutListener.listen(48487)) {
                            mode = NONE;
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (!ListenerUtil.mutListener.listen(48488)) {
                            last.set(motionEvent.getX(), motionEvent.getY());
                        }
                        if (!ListenerUtil.mutListener.listen(48489)) {
                            start.set(last);
                        }
                        if (!ListenerUtil.mutListener.listen(48490)) {
                            mode = ZOOM;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!ListenerUtil.mutListener.listen(48534)) {
                            if ((ListenerUtil.mutListener.listen(48497) ? (mode == ZOOM && ((ListenerUtil.mutListener.listen(48496) ? (mode == DRAG || (ListenerUtil.mutListener.listen(48495) ? (saveScale >= minScale) : (ListenerUtil.mutListener.listen(48494) ? (saveScale <= minScale) : (ListenerUtil.mutListener.listen(48493) ? (saveScale < minScale) : (ListenerUtil.mutListener.listen(48492) ? (saveScale != minScale) : (ListenerUtil.mutListener.listen(48491) ? (saveScale == minScale) : (saveScale > minScale))))))) : (mode == DRAG && (ListenerUtil.mutListener.listen(48495) ? (saveScale >= minScale) : (ListenerUtil.mutListener.listen(48494) ? (saveScale <= minScale) : (ListenerUtil.mutListener.listen(48493) ? (saveScale < minScale) : (ListenerUtil.mutListener.listen(48492) ? (saveScale != minScale) : (ListenerUtil.mutListener.listen(48491) ? (saveScale == minScale) : (saveScale > minScale)))))))))) : (mode == ZOOM || ((ListenerUtil.mutListener.listen(48496) ? (mode == DRAG || (ListenerUtil.mutListener.listen(48495) ? (saveScale >= minScale) : (ListenerUtil.mutListener.listen(48494) ? (saveScale <= minScale) : (ListenerUtil.mutListener.listen(48493) ? (saveScale < minScale) : (ListenerUtil.mutListener.listen(48492) ? (saveScale != minScale) : (ListenerUtil.mutListener.listen(48491) ? (saveScale == minScale) : (saveScale > minScale))))))) : (mode == DRAG && (ListenerUtil.mutListener.listen(48495) ? (saveScale >= minScale) : (ListenerUtil.mutListener.listen(48494) ? (saveScale <= minScale) : (ListenerUtil.mutListener.listen(48493) ? (saveScale < minScale) : (ListenerUtil.mutListener.listen(48492) ? (saveScale != minScale) : (ListenerUtil.mutListener.listen(48491) ? (saveScale == minScale) : (saveScale > minScale)))))))))))) {
                                // x difference
                                float deltaX = (ListenerUtil.mutListener.listen(48501) ? (curr.x % last.x) : (ListenerUtil.mutListener.listen(48500) ? (curr.x / last.x) : (ListenerUtil.mutListener.listen(48499) ? (curr.x * last.x) : (ListenerUtil.mutListener.listen(48498) ? (curr.x + last.x) : (curr.x - last.x)))));
                                // y difference
                                float deltaY = (ListenerUtil.mutListener.listen(48505) ? (curr.y % last.y) : (ListenerUtil.mutListener.listen(48504) ? (curr.y / last.y) : (ListenerUtil.mutListener.listen(48503) ? (curr.y * last.y) : (ListenerUtil.mutListener.listen(48502) ? (curr.y + last.y) : (curr.y - last.y)))));
                                if (!ListenerUtil.mutListener.listen(48518)) {
                                    if ((ListenerUtil.mutListener.listen(48510) ? (y + deltaY >= 0) : (ListenerUtil.mutListener.listen(48509) ? (y + deltaY <= 0) : (ListenerUtil.mutListener.listen(48508) ? (y + deltaY < 0) : (ListenerUtil.mutListener.listen(48507) ? (y + deltaY != 0) : (ListenerUtil.mutListener.listen(48506) ? (y + deltaY == 0) : (y + deltaY > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(48517)) {
                                            deltaY = -y;
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(48515) ? (y + deltaY >= -bottom) : (ListenerUtil.mutListener.listen(48514) ? (y + deltaY <= -bottom) : (ListenerUtil.mutListener.listen(48513) ? (y + deltaY > -bottom) : (ListenerUtil.mutListener.listen(48512) ? (y + deltaY != -bottom) : (ListenerUtil.mutListener.listen(48511) ? (y + deltaY == -bottom) : (y + deltaY < -bottom)))))))
                                        if (!ListenerUtil.mutListener.listen(48516)) {
                                            deltaY = -(y + bottom);
                                        }
                                }
                                if (!ListenerUtil.mutListener.listen(48531)) {
                                    if ((ListenerUtil.mutListener.listen(48523) ? (x + deltaX >= 0) : (ListenerUtil.mutListener.listen(48522) ? (x + deltaX <= 0) : (ListenerUtil.mutListener.listen(48521) ? (x + deltaX < 0) : (ListenerUtil.mutListener.listen(48520) ? (x + deltaX != 0) : (ListenerUtil.mutListener.listen(48519) ? (x + deltaX == 0) : (x + deltaX > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(48530)) {
                                            deltaX = -x;
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(48528) ? (x + deltaX >= -right) : (ListenerUtil.mutListener.listen(48527) ? (x + deltaX <= -right) : (ListenerUtil.mutListener.listen(48526) ? (x + deltaX > -right) : (ListenerUtil.mutListener.listen(48525) ? (x + deltaX != -right) : (ListenerUtil.mutListener.listen(48524) ? (x + deltaX == -right) : (x + deltaX < -right)))))))
                                        if (!ListenerUtil.mutListener.listen(48529)) {
                                            deltaX = -(x + right);
                                        }
                                }
                                if (!ListenerUtil.mutListener.listen(48532)) {
                                    matrix.postTranslate(deltaX, deltaY);
                                }
                                if (!ListenerUtil.mutListener.listen(48533)) {
                                    last.set(curr.x, curr.y);
                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        if (!ListenerUtil.mutListener.listen(48535)) {
                            mode = NONE;
                        }
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(48537)) {
                ZoomableTextureView.this.setTransform(matrix);
            }
            if (!ListenerUtil.mutListener.listen(48538)) {
                ZoomableTextureView.this.invalidate();
            }
            return true;
        }

        private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                if (!ListenerUtil.mutListener.listen(48539)) {
                    mode = ZOOM;
                }
                return true;
            }

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float mScaleFactor = detector.getScaleFactor();
                float origScale = saveScale;
                if (!ListenerUtil.mutListener.listen(48540)) {
                    saveScale *= mScaleFactor;
                }
                if (!ListenerUtil.mutListener.listen(48563)) {
                    if ((ListenerUtil.mutListener.listen(48545) ? (saveScale >= maxScale) : (ListenerUtil.mutListener.listen(48544) ? (saveScale <= maxScale) : (ListenerUtil.mutListener.listen(48543) ? (saveScale < maxScale) : (ListenerUtil.mutListener.listen(48542) ? (saveScale != maxScale) : (ListenerUtil.mutListener.listen(48541) ? (saveScale == maxScale) : (saveScale > maxScale))))))) {
                        if (!ListenerUtil.mutListener.listen(48557)) {
                            saveScale = maxScale;
                        }
                        if (!ListenerUtil.mutListener.listen(48562)) {
                            mScaleFactor = (ListenerUtil.mutListener.listen(48561) ? (maxScale % origScale) : (ListenerUtil.mutListener.listen(48560) ? (maxScale * origScale) : (ListenerUtil.mutListener.listen(48559) ? (maxScale - origScale) : (ListenerUtil.mutListener.listen(48558) ? (maxScale + origScale) : (maxScale / origScale)))));
                        }
                    } else if ((ListenerUtil.mutListener.listen(48550) ? (saveScale >= minScale) : (ListenerUtil.mutListener.listen(48549) ? (saveScale <= minScale) : (ListenerUtil.mutListener.listen(48548) ? (saveScale > minScale) : (ListenerUtil.mutListener.listen(48547) ? (saveScale != minScale) : (ListenerUtil.mutListener.listen(48546) ? (saveScale == minScale) : (saveScale < minScale))))))) {
                        if (!ListenerUtil.mutListener.listen(48551)) {
                            saveScale = minScale;
                        }
                        if (!ListenerUtil.mutListener.listen(48556)) {
                            mScaleFactor = (ListenerUtil.mutListener.listen(48555) ? (minScale % origScale) : (ListenerUtil.mutListener.listen(48554) ? (minScale * origScale) : (ListenerUtil.mutListener.listen(48553) ? (minScale - origScale) : (ListenerUtil.mutListener.listen(48552) ? (minScale + origScale) : (minScale / origScale)))));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(48572)) {
                    right = (ListenerUtil.mutListener.listen(48571) ? ((ListenerUtil.mutListener.listen(48567) ? (getWidth() % saveScale) : (ListenerUtil.mutListener.listen(48566) ? (getWidth() / saveScale) : (ListenerUtil.mutListener.listen(48565) ? (getWidth() - saveScale) : (ListenerUtil.mutListener.listen(48564) ? (getWidth() + saveScale) : (getWidth() * saveScale))))) % getWidth()) : (ListenerUtil.mutListener.listen(48570) ? ((ListenerUtil.mutListener.listen(48567) ? (getWidth() % saveScale) : (ListenerUtil.mutListener.listen(48566) ? (getWidth() / saveScale) : (ListenerUtil.mutListener.listen(48565) ? (getWidth() - saveScale) : (ListenerUtil.mutListener.listen(48564) ? (getWidth() + saveScale) : (getWidth() * saveScale))))) / getWidth()) : (ListenerUtil.mutListener.listen(48569) ? ((ListenerUtil.mutListener.listen(48567) ? (getWidth() % saveScale) : (ListenerUtil.mutListener.listen(48566) ? (getWidth() / saveScale) : (ListenerUtil.mutListener.listen(48565) ? (getWidth() - saveScale) : (ListenerUtil.mutListener.listen(48564) ? (getWidth() + saveScale) : (getWidth() * saveScale))))) * getWidth()) : (ListenerUtil.mutListener.listen(48568) ? ((ListenerUtil.mutListener.listen(48567) ? (getWidth() % saveScale) : (ListenerUtil.mutListener.listen(48566) ? (getWidth() / saveScale) : (ListenerUtil.mutListener.listen(48565) ? (getWidth() - saveScale) : (ListenerUtil.mutListener.listen(48564) ? (getWidth() + saveScale) : (getWidth() * saveScale))))) + getWidth()) : ((ListenerUtil.mutListener.listen(48567) ? (getWidth() % saveScale) : (ListenerUtil.mutListener.listen(48566) ? (getWidth() / saveScale) : (ListenerUtil.mutListener.listen(48565) ? (getWidth() - saveScale) : (ListenerUtil.mutListener.listen(48564) ? (getWidth() + saveScale) : (getWidth() * saveScale))))) - getWidth())))));
                }
                if (!ListenerUtil.mutListener.listen(48581)) {
                    bottom = (ListenerUtil.mutListener.listen(48580) ? ((ListenerUtil.mutListener.listen(48576) ? (getHeight() % saveScale) : (ListenerUtil.mutListener.listen(48575) ? (getHeight() / saveScale) : (ListenerUtil.mutListener.listen(48574) ? (getHeight() - saveScale) : (ListenerUtil.mutListener.listen(48573) ? (getHeight() + saveScale) : (getHeight() * saveScale))))) % getHeight()) : (ListenerUtil.mutListener.listen(48579) ? ((ListenerUtil.mutListener.listen(48576) ? (getHeight() % saveScale) : (ListenerUtil.mutListener.listen(48575) ? (getHeight() / saveScale) : (ListenerUtil.mutListener.listen(48574) ? (getHeight() - saveScale) : (ListenerUtil.mutListener.listen(48573) ? (getHeight() + saveScale) : (getHeight() * saveScale))))) / getHeight()) : (ListenerUtil.mutListener.listen(48578) ? ((ListenerUtil.mutListener.listen(48576) ? (getHeight() % saveScale) : (ListenerUtil.mutListener.listen(48575) ? (getHeight() / saveScale) : (ListenerUtil.mutListener.listen(48574) ? (getHeight() - saveScale) : (ListenerUtil.mutListener.listen(48573) ? (getHeight() + saveScale) : (getHeight() * saveScale))))) * getHeight()) : (ListenerUtil.mutListener.listen(48577) ? ((ListenerUtil.mutListener.listen(48576) ? (getHeight() % saveScale) : (ListenerUtil.mutListener.listen(48575) ? (getHeight() / saveScale) : (ListenerUtil.mutListener.listen(48574) ? (getHeight() - saveScale) : (ListenerUtil.mutListener.listen(48573) ? (getHeight() + saveScale) : (getHeight() * saveScale))))) + getHeight()) : ((ListenerUtil.mutListener.listen(48576) ? (getHeight() % saveScale) : (ListenerUtil.mutListener.listen(48575) ? (getHeight() / saveScale) : (ListenerUtil.mutListener.listen(48574) ? (getHeight() - saveScale) : (ListenerUtil.mutListener.listen(48573) ? (getHeight() + saveScale) : (getHeight() * saveScale))))) - getHeight())))));
                }
                if (!ListenerUtil.mutListener.listen(48689)) {
                    if ((ListenerUtil.mutListener.listen(48592) ? ((ListenerUtil.mutListener.listen(48586) ? (0 >= getWidth()) : (ListenerUtil.mutListener.listen(48585) ? (0 > getWidth()) : (ListenerUtil.mutListener.listen(48584) ? (0 < getWidth()) : (ListenerUtil.mutListener.listen(48583) ? (0 != getWidth()) : (ListenerUtil.mutListener.listen(48582) ? (0 == getWidth()) : (0 <= getWidth())))))) && (ListenerUtil.mutListener.listen(48591) ? (0 >= getHeight()) : (ListenerUtil.mutListener.listen(48590) ? (0 > getHeight()) : (ListenerUtil.mutListener.listen(48589) ? (0 < getHeight()) : (ListenerUtil.mutListener.listen(48588) ? (0 != getHeight()) : (ListenerUtil.mutListener.listen(48587) ? (0 == getHeight()) : (0 <= getHeight()))))))) : ((ListenerUtil.mutListener.listen(48586) ? (0 >= getWidth()) : (ListenerUtil.mutListener.listen(48585) ? (0 > getWidth()) : (ListenerUtil.mutListener.listen(48584) ? (0 < getWidth()) : (ListenerUtil.mutListener.listen(48583) ? (0 != getWidth()) : (ListenerUtil.mutListener.listen(48582) ? (0 == getWidth()) : (0 <= getWidth())))))) || (ListenerUtil.mutListener.listen(48591) ? (0 >= getHeight()) : (ListenerUtil.mutListener.listen(48590) ? (0 > getHeight()) : (ListenerUtil.mutListener.listen(48589) ? (0 < getHeight()) : (ListenerUtil.mutListener.listen(48588) ? (0 != getHeight()) : (ListenerUtil.mutListener.listen(48587) ? (0 == getHeight()) : (0 <= getHeight()))))))))) {
                        if (!ListenerUtil.mutListener.listen(48635)) {
                            matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());
                        }
                        if (!ListenerUtil.mutListener.listen(48688)) {
                            if ((ListenerUtil.mutListener.listen(48640) ? (mScaleFactor >= 1) : (ListenerUtil.mutListener.listen(48639) ? (mScaleFactor <= 1) : (ListenerUtil.mutListener.listen(48638) ? (mScaleFactor > 1) : (ListenerUtil.mutListener.listen(48637) ? (mScaleFactor != 1) : (ListenerUtil.mutListener.listen(48636) ? (mScaleFactor == 1) : (mScaleFactor < 1))))))) {
                                if (!ListenerUtil.mutListener.listen(48641)) {
                                    matrix.getValues(m);
                                }
                                float x = m[Matrix.MTRANS_X];
                                float y = m[Matrix.MTRANS_Y];
                                if (!ListenerUtil.mutListener.listen(48687)) {
                                    if ((ListenerUtil.mutListener.listen(48646) ? (mScaleFactor >= 1) : (ListenerUtil.mutListener.listen(48645) ? (mScaleFactor <= 1) : (ListenerUtil.mutListener.listen(48644) ? (mScaleFactor > 1) : (ListenerUtil.mutListener.listen(48643) ? (mScaleFactor != 1) : (ListenerUtil.mutListener.listen(48642) ? (mScaleFactor == 1) : (mScaleFactor < 1))))))) {
                                        if (!ListenerUtil.mutListener.listen(48686)) {
                                            if ((ListenerUtil.mutListener.listen(48651) ? (0 >= getWidth()) : (ListenerUtil.mutListener.listen(48650) ? (0 <= getWidth()) : (ListenerUtil.mutListener.listen(48649) ? (0 > getWidth()) : (ListenerUtil.mutListener.listen(48648) ? (0 != getWidth()) : (ListenerUtil.mutListener.listen(48647) ? (0 == getWidth()) : (0 < getWidth()))))))) {
                                                if (!ListenerUtil.mutListener.listen(48685)) {
                                                    if ((ListenerUtil.mutListener.listen(48673) ? (y >= -bottom) : (ListenerUtil.mutListener.listen(48672) ? (y <= -bottom) : (ListenerUtil.mutListener.listen(48671) ? (y > -bottom) : (ListenerUtil.mutListener.listen(48670) ? (y != -bottom) : (ListenerUtil.mutListener.listen(48669) ? (y == -bottom) : (y < -bottom))))))) {
                                                        if (!ListenerUtil.mutListener.listen(48684)) {
                                                            matrix.postTranslate(0, -((ListenerUtil.mutListener.listen(48683) ? (y % bottom) : (ListenerUtil.mutListener.listen(48682) ? (y / bottom) : (ListenerUtil.mutListener.listen(48681) ? (y * bottom) : (ListenerUtil.mutListener.listen(48680) ? (y - bottom) : (y + bottom)))))));
                                                        }
                                                    } else if ((ListenerUtil.mutListener.listen(48678) ? (y >= 0) : (ListenerUtil.mutListener.listen(48677) ? (y <= 0) : (ListenerUtil.mutListener.listen(48676) ? (y < 0) : (ListenerUtil.mutListener.listen(48675) ? (y != 0) : (ListenerUtil.mutListener.listen(48674) ? (y == 0) : (y > 0)))))))
                                                        if (!ListenerUtil.mutListener.listen(48679)) {
                                                            matrix.postTranslate(0, -y);
                                                        }
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(48668)) {
                                                    if ((ListenerUtil.mutListener.listen(48656) ? (x >= -right) : (ListenerUtil.mutListener.listen(48655) ? (x <= -right) : (ListenerUtil.mutListener.listen(48654) ? (x > -right) : (ListenerUtil.mutListener.listen(48653) ? (x != -right) : (ListenerUtil.mutListener.listen(48652) ? (x == -right) : (x < -right))))))) {
                                                        if (!ListenerUtil.mutListener.listen(48667)) {
                                                            matrix.postTranslate(-((ListenerUtil.mutListener.listen(48666) ? (x % right) : (ListenerUtil.mutListener.listen(48665) ? (x / right) : (ListenerUtil.mutListener.listen(48664) ? (x * right) : (ListenerUtil.mutListener.listen(48663) ? (x - right) : (x + right)))))), 0);
                                                        }
                                                    } else if ((ListenerUtil.mutListener.listen(48661) ? (x >= 0) : (ListenerUtil.mutListener.listen(48660) ? (x <= 0) : (ListenerUtil.mutListener.listen(48659) ? (x < 0) : (ListenerUtil.mutListener.listen(48658) ? (x != 0) : (ListenerUtil.mutListener.listen(48657) ? (x == 0) : (x > 0)))))))
                                                        if (!ListenerUtil.mutListener.listen(48662)) {
                                                            matrix.postTranslate(-x, 0);
                                                        }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(48593)) {
                            matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());
                        }
                        if (!ListenerUtil.mutListener.listen(48594)) {
                            matrix.getValues(m);
                        }
                        float x = m[Matrix.MTRANS_X];
                        float y = m[Matrix.MTRANS_Y];
                        if (!ListenerUtil.mutListener.listen(48634)) {
                            if ((ListenerUtil.mutListener.listen(48599) ? (mScaleFactor >= 1) : (ListenerUtil.mutListener.listen(48598) ? (mScaleFactor <= 1) : (ListenerUtil.mutListener.listen(48597) ? (mScaleFactor > 1) : (ListenerUtil.mutListener.listen(48596) ? (mScaleFactor != 1) : (ListenerUtil.mutListener.listen(48595) ? (mScaleFactor == 1) : (mScaleFactor < 1))))))) {
                                if (!ListenerUtil.mutListener.listen(48616)) {
                                    if ((ListenerUtil.mutListener.listen(48604) ? (x >= -right) : (ListenerUtil.mutListener.listen(48603) ? (x <= -right) : (ListenerUtil.mutListener.listen(48602) ? (x > -right) : (ListenerUtil.mutListener.listen(48601) ? (x != -right) : (ListenerUtil.mutListener.listen(48600) ? (x == -right) : (x < -right))))))) {
                                        if (!ListenerUtil.mutListener.listen(48615)) {
                                            matrix.postTranslate(-((ListenerUtil.mutListener.listen(48614) ? (x % right) : (ListenerUtil.mutListener.listen(48613) ? (x / right) : (ListenerUtil.mutListener.listen(48612) ? (x * right) : (ListenerUtil.mutListener.listen(48611) ? (x - right) : (x + right)))))), 0);
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(48609) ? (x >= 0) : (ListenerUtil.mutListener.listen(48608) ? (x <= 0) : (ListenerUtil.mutListener.listen(48607) ? (x < 0) : (ListenerUtil.mutListener.listen(48606) ? (x != 0) : (ListenerUtil.mutListener.listen(48605) ? (x == 0) : (x > 0)))))))
                                        if (!ListenerUtil.mutListener.listen(48610)) {
                                            matrix.postTranslate(-x, 0);
                                        }
                                }
                                if (!ListenerUtil.mutListener.listen(48633)) {
                                    if ((ListenerUtil.mutListener.listen(48621) ? (y >= -bottom) : (ListenerUtil.mutListener.listen(48620) ? (y <= -bottom) : (ListenerUtil.mutListener.listen(48619) ? (y > -bottom) : (ListenerUtil.mutListener.listen(48618) ? (y != -bottom) : (ListenerUtil.mutListener.listen(48617) ? (y == -bottom) : (y < -bottom))))))) {
                                        if (!ListenerUtil.mutListener.listen(48632)) {
                                            matrix.postTranslate(0, -((ListenerUtil.mutListener.listen(48631) ? (y % bottom) : (ListenerUtil.mutListener.listen(48630) ? (y / bottom) : (ListenerUtil.mutListener.listen(48629) ? (y * bottom) : (ListenerUtil.mutListener.listen(48628) ? (y - bottom) : (y + bottom)))))));
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(48626) ? (y >= 0) : (ListenerUtil.mutListener.listen(48625) ? (y <= 0) : (ListenerUtil.mutListener.listen(48624) ? (y < 0) : (ListenerUtil.mutListener.listen(48623) ? (y != 0) : (ListenerUtil.mutListener.listen(48622) ? (y == 0) : (y > 0)))))))
                                        if (!ListenerUtil.mutListener.listen(48627)) {
                                            matrix.postTranslate(0, -y);
                                        }
                                }
                            }
                        }
                    }
                }
                return true;
            }
        }
    }
}

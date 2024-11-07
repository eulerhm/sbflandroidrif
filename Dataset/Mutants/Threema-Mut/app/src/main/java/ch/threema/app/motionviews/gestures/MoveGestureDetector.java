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
package ch.threema.app.motionviews.gestures;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @author Almer Thie (code.almeros.com)
 *         Copyright (c) 2013, Almer Thie (code.almeros.com)
 *         <p>
 *         All rights reserved.
 *         <p>
 *         Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *         <p>
 *         Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *         Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer
 *         in the documentation and/or other materials provided with the distribution.
 *         <p>
 *         THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *         INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *         IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *         OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *         OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *         OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 *         OF SUCH DAMAGE.
 */
public class MoveGestureDetector extends BaseGestureDetector {

    private static final PointF FOCUS_DELTA_ZERO = new PointF();

    private final OnMoveGestureListener mListener;

    private PointF mCurrFocusInternal;

    private PointF mPrevFocusInternal;

    private PointF mFocusExternal = new PointF();

    private PointF mFocusDeltaExternal = new PointF();

    public MoveGestureDetector(Context context, OnMoveGestureListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected void handleStartProgressEvent(int actionCode, MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(30607)) {
            switch(actionCode) {
                case MotionEvent.ACTION_DOWN:
                    if (!ListenerUtil.mutListener.listen(30602)) {
                        // In case we missed an UP/CANCEL event
                        resetState();
                    }
                    if (!ListenerUtil.mutListener.listen(30603)) {
                        mPrevEvent = MotionEvent.obtain(event);
                    }
                    if (!ListenerUtil.mutListener.listen(30604)) {
                        mTimeDelta = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(30605)) {
                        updateStateByEvent(event);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!ListenerUtil.mutListener.listen(30606)) {
                        mGestureInProgress = mListener.onMoveBegin(this);
                    }
                    break;
            }
        }
    }

    @Override
    protected void handleInProgressEvent(int actionCode, MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(30624)) {
            switch(actionCode) {
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (!ListenerUtil.mutListener.listen(30608)) {
                        mListener.onMoveEnd(this);
                    }
                    if (!ListenerUtil.mutListener.listen(30609)) {
                        resetState();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!ListenerUtil.mutListener.listen(30610)) {
                        updateStateByEvent(event);
                    }
                    if (!ListenerUtil.mutListener.listen(30623)) {
                        // finger is lifted.
                        if ((ListenerUtil.mutListener.listen(30619) ? ((ListenerUtil.mutListener.listen(30614) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30613) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30612) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30611) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) >= PRESSURE_THRESHOLD) : (ListenerUtil.mutListener.listen(30618) ? ((ListenerUtil.mutListener.listen(30614) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30613) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30612) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30611) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) <= PRESSURE_THRESHOLD) : (ListenerUtil.mutListener.listen(30617) ? ((ListenerUtil.mutListener.listen(30614) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30613) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30612) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30611) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) < PRESSURE_THRESHOLD) : (ListenerUtil.mutListener.listen(30616) ? ((ListenerUtil.mutListener.listen(30614) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30613) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30612) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30611) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) != PRESSURE_THRESHOLD) : (ListenerUtil.mutListener.listen(30615) ? ((ListenerUtil.mutListener.listen(30614) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30613) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30612) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30611) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) == PRESSURE_THRESHOLD) : ((ListenerUtil.mutListener.listen(30614) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30613) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30612) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30611) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) > PRESSURE_THRESHOLD))))))) {
                            final boolean updatePrevious = mListener.onMove(this);
                            if (!ListenerUtil.mutListener.listen(30622)) {
                                if (updatePrevious) {
                                    if (!ListenerUtil.mutListener.listen(30620)) {
                                        mPrevEvent.recycle();
                                    }
                                    if (!ListenerUtil.mutListener.listen(30621)) {
                                        mPrevEvent = MotionEvent.obtain(event);
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }

    protected void updateStateByEvent(MotionEvent curr) {
        if (!ListenerUtil.mutListener.listen(30625)) {
            super.updateStateByEvent(curr);
        }
        final MotionEvent prev = mPrevEvent;
        if (!ListenerUtil.mutListener.listen(30626)) {
            // Focus intenal
            mCurrFocusInternal = determineFocalPoint(curr);
        }
        if (!ListenerUtil.mutListener.listen(30627)) {
            mPrevFocusInternal = determineFocalPoint(prev);
        }
        // - Prevent skipping of focus delta when a finger is added or removed
        boolean mSkipNextMoveEvent = prev.getPointerCount() != curr.getPointerCount();
        if (!ListenerUtil.mutListener.listen(30636)) {
            mFocusDeltaExternal = mSkipNextMoveEvent ? FOCUS_DELTA_ZERO : new PointF((ListenerUtil.mutListener.listen(30631) ? (mCurrFocusInternal.x % mPrevFocusInternal.x) : (ListenerUtil.mutListener.listen(30630) ? (mCurrFocusInternal.x / mPrevFocusInternal.x) : (ListenerUtil.mutListener.listen(30629) ? (mCurrFocusInternal.x * mPrevFocusInternal.x) : (ListenerUtil.mutListener.listen(30628) ? (mCurrFocusInternal.x + mPrevFocusInternal.x) : (mCurrFocusInternal.x - mPrevFocusInternal.x))))), (ListenerUtil.mutListener.listen(30635) ? (mCurrFocusInternal.y % mPrevFocusInternal.y) : (ListenerUtil.mutListener.listen(30634) ? (mCurrFocusInternal.y / mPrevFocusInternal.y) : (ListenerUtil.mutListener.listen(30633) ? (mCurrFocusInternal.y * mPrevFocusInternal.y) : (ListenerUtil.mutListener.listen(30632) ? (mCurrFocusInternal.y + mPrevFocusInternal.y) : (mCurrFocusInternal.y - mPrevFocusInternal.y))))));
        }
        if (!ListenerUtil.mutListener.listen(30637)) {
            // unskipped delta values to mFocusExternal instead.
            mFocusExternal.x += mFocusDeltaExternal.x;
        }
        if (!ListenerUtil.mutListener.listen(30638)) {
            mFocusExternal.y += mFocusDeltaExternal.y;
        }
    }

    /**
     * Determine (multi)finger focal point (a.k.a. center point between all
     * fingers)
     *
     * @param MotionEvent e
     * @return PointF focal point
     */
    private PointF determineFocalPoint(MotionEvent e) {
        // Number of fingers on screen
        final int pCount = e.getPointerCount();
        float x = 0f;
        float y = 0f;
        if (!ListenerUtil.mutListener.listen(30646)) {
            {
                long _loopCounter211 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(30645) ? (i >= pCount) : (ListenerUtil.mutListener.listen(30644) ? (i <= pCount) : (ListenerUtil.mutListener.listen(30643) ? (i > pCount) : (ListenerUtil.mutListener.listen(30642) ? (i != pCount) : (ListenerUtil.mutListener.listen(30641) ? (i == pCount) : (i < pCount)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter211", ++_loopCounter211);
                    if (!ListenerUtil.mutListener.listen(30639)) {
                        x += e.getX(i);
                    }
                    if (!ListenerUtil.mutListener.listen(30640)) {
                        y += e.getY(i);
                    }
                }
            }
        }
        return new PointF((ListenerUtil.mutListener.listen(30650) ? (x % pCount) : (ListenerUtil.mutListener.listen(30649) ? (x * pCount) : (ListenerUtil.mutListener.listen(30648) ? (x - pCount) : (ListenerUtil.mutListener.listen(30647) ? (x + pCount) : (x / pCount))))), (ListenerUtil.mutListener.listen(30654) ? (y % pCount) : (ListenerUtil.mutListener.listen(30653) ? (y * pCount) : (ListenerUtil.mutListener.listen(30652) ? (y - pCount) : (ListenerUtil.mutListener.listen(30651) ? (y + pCount) : (y / pCount))))));
    }

    public float getFocusX() {
        return mFocusExternal.x;
    }

    public float getFocusY() {
        return mFocusExternal.y;
    }

    public PointF getFocusDelta() {
        return mFocusDeltaExternal;
    }

    /**
     * Listener which must be implemented which is used by MoveGestureDetector
     * to perform callbacks to any implementing class which is registered to a
     * MoveGestureDetector via the constructor.
     *
     * @see MoveGestureDetector.SimpleOnMoveGestureListener
     */
    public interface OnMoveGestureListener {

        public boolean onMove(MoveGestureDetector detector);

        public boolean onMoveBegin(MoveGestureDetector detector);

        public void onMoveEnd(MoveGestureDetector detector);
    }

    /**
     * Helper class which may be extended and where the methods may be
     * implemented. This way it is not necessary to implement all methods
     * of OnMoveGestureListener.
     */
    public static class SimpleOnMoveGestureListener implements OnMoveGestureListener {

        public boolean onMove(MoveGestureDetector detector) {
            return false;
        }

        public boolean onMoveBegin(MoveGestureDetector detector) {
            return true;
        }

        public void onMoveEnd(MoveGestureDetector detector) {
        }
    }
}

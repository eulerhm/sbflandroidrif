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
public class RotateGestureDetector extends TwoFingerGestureDetector {

    private final OnRotateGestureListener mListener;

    private boolean mSloppyGesture;

    public RotateGestureDetector(Context context, OnRotateGestureListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected void handleStartProgressEvent(int actionCode, MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(30667)) {
            switch(actionCode) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (!ListenerUtil.mutListener.listen(30655)) {
                        // In case we missed an UP/CANCEL event
                        resetState();
                    }
                    if (!ListenerUtil.mutListener.listen(30656)) {
                        mPrevEvent = MotionEvent.obtain(event);
                    }
                    if (!ListenerUtil.mutListener.listen(30657)) {
                        mTimeDelta = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(30658)) {
                        updateStateByEvent(event);
                    }
                    if (!ListenerUtil.mutListener.listen(30659)) {
                        // See if we have a sloppy gesture
                        mSloppyGesture = isSloppyGesture(event);
                    }
                    if (!ListenerUtil.mutListener.listen(30661)) {
                        if (!mSloppyGesture) {
                            if (!ListenerUtil.mutListener.listen(30660)) {
                                // No, start gesture now
                                mGestureInProgress = mListener.onRotateBegin(this);
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!ListenerUtil.mutListener.listen(30662)) {
                        if (!mSloppyGesture) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30663)) {
                        // See if we still have a sloppy gesture
                        mSloppyGesture = isSloppyGesture(event);
                    }
                    if (!ListenerUtil.mutListener.listen(30665)) {
                        if (!mSloppyGesture) {
                            if (!ListenerUtil.mutListener.listen(30664)) {
                                // No, start normal gesture now
                                mGestureInProgress = mListener.onRotateBegin(this);
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    if (!ListenerUtil.mutListener.listen(30666)) {
                        if (!mSloppyGesture) {
                            break;
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void handleInProgressEvent(int actionCode, MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(30689)) {
            switch(actionCode) {
                case MotionEvent.ACTION_POINTER_UP:
                    if (!ListenerUtil.mutListener.listen(30668)) {
                        // Gesture ended but
                        updateStateByEvent(event);
                    }
                    if (!ListenerUtil.mutListener.listen(30670)) {
                        if (!mSloppyGesture) {
                            if (!ListenerUtil.mutListener.listen(30669)) {
                                mListener.onRotateEnd(this);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30671)) {
                        resetState();
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    if (!ListenerUtil.mutListener.listen(30673)) {
                        if (!mSloppyGesture) {
                            if (!ListenerUtil.mutListener.listen(30672)) {
                                mListener.onRotateEnd(this);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30674)) {
                        resetState();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!ListenerUtil.mutListener.listen(30675)) {
                        updateStateByEvent(event);
                    }
                    if (!ListenerUtil.mutListener.listen(30688)) {
                        // finger is lifted.
                        if ((ListenerUtil.mutListener.listen(30684) ? ((ListenerUtil.mutListener.listen(30679) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30678) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30677) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30676) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) >= PRESSURE_THRESHOLD) : (ListenerUtil.mutListener.listen(30683) ? ((ListenerUtil.mutListener.listen(30679) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30678) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30677) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30676) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) <= PRESSURE_THRESHOLD) : (ListenerUtil.mutListener.listen(30682) ? ((ListenerUtil.mutListener.listen(30679) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30678) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30677) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30676) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) < PRESSURE_THRESHOLD) : (ListenerUtil.mutListener.listen(30681) ? ((ListenerUtil.mutListener.listen(30679) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30678) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30677) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30676) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) != PRESSURE_THRESHOLD) : (ListenerUtil.mutListener.listen(30680) ? ((ListenerUtil.mutListener.listen(30679) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30678) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30677) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30676) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) == PRESSURE_THRESHOLD) : ((ListenerUtil.mutListener.listen(30679) ? (mCurrPressure % mPrevPressure) : (ListenerUtil.mutListener.listen(30678) ? (mCurrPressure * mPrevPressure) : (ListenerUtil.mutListener.listen(30677) ? (mCurrPressure - mPrevPressure) : (ListenerUtil.mutListener.listen(30676) ? (mCurrPressure + mPrevPressure) : (mCurrPressure / mPrevPressure))))) > PRESSURE_THRESHOLD))))))) {
                            final boolean updatePrevious = mListener.onRotate(this);
                            if (!ListenerUtil.mutListener.listen(30687)) {
                                if (updatePrevious) {
                                    if (!ListenerUtil.mutListener.listen(30685)) {
                                        mPrevEvent.recycle();
                                    }
                                    if (!ListenerUtil.mutListener.listen(30686)) {
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

    @Override
    protected void resetState() {
        if (!ListenerUtil.mutListener.listen(30690)) {
            super.resetState();
        }
        if (!ListenerUtil.mutListener.listen(30691)) {
            mSloppyGesture = false;
        }
    }

    /**
     * Return the rotation difference from the previous rotate event to the current
     * event.
     *
     * @return The current rotation //difference in degrees.
     */
    public float getRotationDegreesDelta() {
        double diffRadians = (ListenerUtil.mutListener.listen(30695) ? (Math.atan2(mPrevFingerDiffY, mPrevFingerDiffX) % Math.atan2(mCurrFingerDiffY, mCurrFingerDiffX)) : (ListenerUtil.mutListener.listen(30694) ? (Math.atan2(mPrevFingerDiffY, mPrevFingerDiffX) / Math.atan2(mCurrFingerDiffY, mCurrFingerDiffX)) : (ListenerUtil.mutListener.listen(30693) ? (Math.atan2(mPrevFingerDiffY, mPrevFingerDiffX) * Math.atan2(mCurrFingerDiffY, mCurrFingerDiffX)) : (ListenerUtil.mutListener.listen(30692) ? (Math.atan2(mPrevFingerDiffY, mPrevFingerDiffX) + Math.atan2(mCurrFingerDiffY, mCurrFingerDiffX)) : (Math.atan2(mPrevFingerDiffY, mPrevFingerDiffX) - Math.atan2(mCurrFingerDiffY, mCurrFingerDiffX))))));
        return (float) ((ListenerUtil.mutListener.listen(30703) ? ((ListenerUtil.mutListener.listen(30699) ? (diffRadians % 180) : (ListenerUtil.mutListener.listen(30698) ? (diffRadians / 180) : (ListenerUtil.mutListener.listen(30697) ? (diffRadians - 180) : (ListenerUtil.mutListener.listen(30696) ? (diffRadians + 180) : (diffRadians * 180))))) % Math.PI) : (ListenerUtil.mutListener.listen(30702) ? ((ListenerUtil.mutListener.listen(30699) ? (diffRadians % 180) : (ListenerUtil.mutListener.listen(30698) ? (diffRadians / 180) : (ListenerUtil.mutListener.listen(30697) ? (diffRadians - 180) : (ListenerUtil.mutListener.listen(30696) ? (diffRadians + 180) : (diffRadians * 180))))) * Math.PI) : (ListenerUtil.mutListener.listen(30701) ? ((ListenerUtil.mutListener.listen(30699) ? (diffRadians % 180) : (ListenerUtil.mutListener.listen(30698) ? (diffRadians / 180) : (ListenerUtil.mutListener.listen(30697) ? (diffRadians - 180) : (ListenerUtil.mutListener.listen(30696) ? (diffRadians + 180) : (diffRadians * 180))))) - Math.PI) : (ListenerUtil.mutListener.listen(30700) ? ((ListenerUtil.mutListener.listen(30699) ? (diffRadians % 180) : (ListenerUtil.mutListener.listen(30698) ? (diffRadians / 180) : (ListenerUtil.mutListener.listen(30697) ? (diffRadians - 180) : (ListenerUtil.mutListener.listen(30696) ? (diffRadians + 180) : (diffRadians * 180))))) + Math.PI) : ((ListenerUtil.mutListener.listen(30699) ? (diffRadians % 180) : (ListenerUtil.mutListener.listen(30698) ? (diffRadians / 180) : (ListenerUtil.mutListener.listen(30697) ? (diffRadians - 180) : (ListenerUtil.mutListener.listen(30696) ? (diffRadians + 180) : (diffRadians * 180))))) / Math.PI))))));
    }

    /**
     * Listener which must be implemented which is used by RotateGestureDetector
     * to perform callbacks to any implementing class which is registered to a
     * RotateGestureDetector via the constructor.
     *
     * @see RotateGestureDetector.SimpleOnRotateGestureListener
     */
    public interface OnRotateGestureListener {

        public boolean onRotate(RotateGestureDetector detector);

        public boolean onRotateBegin(RotateGestureDetector detector);

        public void onRotateEnd(RotateGestureDetector detector);
    }

    /**
     * Helper class which may be extended and where the methods may be
     * implemented. This way it is not necessary to implement all methods
     * of OnRotateGestureListener.
     */
    public static class SimpleOnRotateGestureListener implements OnRotateGestureListener {

        public boolean onRotate(RotateGestureDetector detector) {
            return false;
        }

        public boolean onRotateBegin(RotateGestureDetector detector) {
            return true;
        }

        public void onRotateEnd(RotateGestureDetector detector) {
        }
    }
}

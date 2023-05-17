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
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
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
public abstract class TwoFingerGestureDetector extends BaseGestureDetector {

    private final float mEdgeSlop;

    protected float mPrevFingerDiffX;

    protected float mPrevFingerDiffY;

    protected float mCurrFingerDiffX;

    protected float mCurrFingerDiffY;

    private float mRightSlopEdge;

    private float mBottomSlopEdge;

    private float mCurrLen;

    private float mPrevLen;

    public TwoFingerGestureDetector(Context context) {
        super(context);
        ViewConfiguration config = ViewConfiguration.get(context);
        mEdgeSlop = config.getScaledEdgeSlop();
    }

    @Override
    protected abstract void handleStartProgressEvent(int actionCode, MotionEvent event);

    @Override
    protected abstract void handleInProgressEvent(int actionCode, MotionEvent event);

    protected void updateStateByEvent(MotionEvent curr) {
        if (!ListenerUtil.mutListener.listen(30704)) {
            super.updateStateByEvent(curr);
        }
        final MotionEvent prev = mPrevEvent;
        if (!ListenerUtil.mutListener.listen(30705)) {
            mCurrLen = -1;
        }
        if (!ListenerUtil.mutListener.listen(30706)) {
            mPrevLen = -1;
        }
        // Previous
        final float px0 = prev.getX(0);
        final float py0 = prev.getY(0);
        final float px1 = prev.getX(1);
        final float py1 = prev.getY(1);
        final float pvx = (ListenerUtil.mutListener.listen(30710) ? (px1 % px0) : (ListenerUtil.mutListener.listen(30709) ? (px1 / px0) : (ListenerUtil.mutListener.listen(30708) ? (px1 * px0) : (ListenerUtil.mutListener.listen(30707) ? (px1 + px0) : (px1 - px0)))));
        final float pvy = (ListenerUtil.mutListener.listen(30714) ? (py1 % py0) : (ListenerUtil.mutListener.listen(30713) ? (py1 / py0) : (ListenerUtil.mutListener.listen(30712) ? (py1 * py0) : (ListenerUtil.mutListener.listen(30711) ? (py1 + py0) : (py1 - py0)))));
        if (!ListenerUtil.mutListener.listen(30715)) {
            mPrevFingerDiffX = pvx;
        }
        if (!ListenerUtil.mutListener.listen(30716)) {
            mPrevFingerDiffY = pvy;
        }
        // Current
        final float cx0 = curr.getX(0);
        final float cy0 = curr.getY(0);
        final float cx1 = curr.getX(1);
        final float cy1 = curr.getY(1);
        final float cvx = (ListenerUtil.mutListener.listen(30720) ? (cx1 % cx0) : (ListenerUtil.mutListener.listen(30719) ? (cx1 / cx0) : (ListenerUtil.mutListener.listen(30718) ? (cx1 * cx0) : (ListenerUtil.mutListener.listen(30717) ? (cx1 + cx0) : (cx1 - cx0)))));
        final float cvy = (ListenerUtil.mutListener.listen(30724) ? (cy1 % cy0) : (ListenerUtil.mutListener.listen(30723) ? (cy1 / cy0) : (ListenerUtil.mutListener.listen(30722) ? (cy1 * cy0) : (ListenerUtil.mutListener.listen(30721) ? (cy1 + cy0) : (cy1 - cy0)))));
        if (!ListenerUtil.mutListener.listen(30725)) {
            mCurrFingerDiffX = cvx;
        }
        if (!ListenerUtil.mutListener.listen(30726)) {
            mCurrFingerDiffY = cvy;
        }
    }

    /**
     * Return the current distance between the two pointers forming the
     * gesture in progress.
     *
     * @return Distance between pointers in pixels.
     */
    public float getCurrentSpan() {
        if (!ListenerUtil.mutListener.listen(30745)) {
            if ((ListenerUtil.mutListener.listen(30731) ? (mCurrLen >= -1) : (ListenerUtil.mutListener.listen(30730) ? (mCurrLen <= -1) : (ListenerUtil.mutListener.listen(30729) ? (mCurrLen > -1) : (ListenerUtil.mutListener.listen(30728) ? (mCurrLen < -1) : (ListenerUtil.mutListener.listen(30727) ? (mCurrLen != -1) : (mCurrLen == -1))))))) {
                final float cvx = mCurrFingerDiffX;
                final float cvy = mCurrFingerDiffY;
                if (!ListenerUtil.mutListener.listen(30744)) {
                    mCurrLen = (float) Math.sqrt((ListenerUtil.mutListener.listen(30743) ? ((ListenerUtil.mutListener.listen(30735) ? (cvx % cvx) : (ListenerUtil.mutListener.listen(30734) ? (cvx / cvx) : (ListenerUtil.mutListener.listen(30733) ? (cvx - cvx) : (ListenerUtil.mutListener.listen(30732) ? (cvx + cvx) : (cvx * cvx))))) % (ListenerUtil.mutListener.listen(30739) ? (cvy % cvy) : (ListenerUtil.mutListener.listen(30738) ? (cvy / cvy) : (ListenerUtil.mutListener.listen(30737) ? (cvy - cvy) : (ListenerUtil.mutListener.listen(30736) ? (cvy + cvy) : (cvy * cvy)))))) : (ListenerUtil.mutListener.listen(30742) ? ((ListenerUtil.mutListener.listen(30735) ? (cvx % cvx) : (ListenerUtil.mutListener.listen(30734) ? (cvx / cvx) : (ListenerUtil.mutListener.listen(30733) ? (cvx - cvx) : (ListenerUtil.mutListener.listen(30732) ? (cvx + cvx) : (cvx * cvx))))) / (ListenerUtil.mutListener.listen(30739) ? (cvy % cvy) : (ListenerUtil.mutListener.listen(30738) ? (cvy / cvy) : (ListenerUtil.mutListener.listen(30737) ? (cvy - cvy) : (ListenerUtil.mutListener.listen(30736) ? (cvy + cvy) : (cvy * cvy)))))) : (ListenerUtil.mutListener.listen(30741) ? ((ListenerUtil.mutListener.listen(30735) ? (cvx % cvx) : (ListenerUtil.mutListener.listen(30734) ? (cvx / cvx) : (ListenerUtil.mutListener.listen(30733) ? (cvx - cvx) : (ListenerUtil.mutListener.listen(30732) ? (cvx + cvx) : (cvx * cvx))))) * (ListenerUtil.mutListener.listen(30739) ? (cvy % cvy) : (ListenerUtil.mutListener.listen(30738) ? (cvy / cvy) : (ListenerUtil.mutListener.listen(30737) ? (cvy - cvy) : (ListenerUtil.mutListener.listen(30736) ? (cvy + cvy) : (cvy * cvy)))))) : (ListenerUtil.mutListener.listen(30740) ? ((ListenerUtil.mutListener.listen(30735) ? (cvx % cvx) : (ListenerUtil.mutListener.listen(30734) ? (cvx / cvx) : (ListenerUtil.mutListener.listen(30733) ? (cvx - cvx) : (ListenerUtil.mutListener.listen(30732) ? (cvx + cvx) : (cvx * cvx))))) - (ListenerUtil.mutListener.listen(30739) ? (cvy % cvy) : (ListenerUtil.mutListener.listen(30738) ? (cvy / cvy) : (ListenerUtil.mutListener.listen(30737) ? (cvy - cvy) : (ListenerUtil.mutListener.listen(30736) ? (cvy + cvy) : (cvy * cvy)))))) : ((ListenerUtil.mutListener.listen(30735) ? (cvx % cvx) : (ListenerUtil.mutListener.listen(30734) ? (cvx / cvx) : (ListenerUtil.mutListener.listen(30733) ? (cvx - cvx) : (ListenerUtil.mutListener.listen(30732) ? (cvx + cvx) : (cvx * cvx))))) + (ListenerUtil.mutListener.listen(30739) ? (cvy % cvy) : (ListenerUtil.mutListener.listen(30738) ? (cvy / cvy) : (ListenerUtil.mutListener.listen(30737) ? (cvy - cvy) : (ListenerUtil.mutListener.listen(30736) ? (cvy + cvy) : (cvy * cvy)))))))))));
                }
            }
        }
        return mCurrLen;
    }

    /**
     * Return the previous distance between the two pointers forming the
     * gesture in progress.
     *
     * @return Previous distance between pointers in pixels.
     */
    public float getPreviousSpan() {
        if (!ListenerUtil.mutListener.listen(30764)) {
            if ((ListenerUtil.mutListener.listen(30750) ? (mPrevLen >= -1) : (ListenerUtil.mutListener.listen(30749) ? (mPrevLen <= -1) : (ListenerUtil.mutListener.listen(30748) ? (mPrevLen > -1) : (ListenerUtil.mutListener.listen(30747) ? (mPrevLen < -1) : (ListenerUtil.mutListener.listen(30746) ? (mPrevLen != -1) : (mPrevLen == -1))))))) {
                final float pvx = mPrevFingerDiffX;
                final float pvy = mPrevFingerDiffY;
                if (!ListenerUtil.mutListener.listen(30763)) {
                    mPrevLen = (float) Math.sqrt((ListenerUtil.mutListener.listen(30762) ? ((ListenerUtil.mutListener.listen(30754) ? (pvx % pvx) : (ListenerUtil.mutListener.listen(30753) ? (pvx / pvx) : (ListenerUtil.mutListener.listen(30752) ? (pvx - pvx) : (ListenerUtil.mutListener.listen(30751) ? (pvx + pvx) : (pvx * pvx))))) % (ListenerUtil.mutListener.listen(30758) ? (pvy % pvy) : (ListenerUtil.mutListener.listen(30757) ? (pvy / pvy) : (ListenerUtil.mutListener.listen(30756) ? (pvy - pvy) : (ListenerUtil.mutListener.listen(30755) ? (pvy + pvy) : (pvy * pvy)))))) : (ListenerUtil.mutListener.listen(30761) ? ((ListenerUtil.mutListener.listen(30754) ? (pvx % pvx) : (ListenerUtil.mutListener.listen(30753) ? (pvx / pvx) : (ListenerUtil.mutListener.listen(30752) ? (pvx - pvx) : (ListenerUtil.mutListener.listen(30751) ? (pvx + pvx) : (pvx * pvx))))) / (ListenerUtil.mutListener.listen(30758) ? (pvy % pvy) : (ListenerUtil.mutListener.listen(30757) ? (pvy / pvy) : (ListenerUtil.mutListener.listen(30756) ? (pvy - pvy) : (ListenerUtil.mutListener.listen(30755) ? (pvy + pvy) : (pvy * pvy)))))) : (ListenerUtil.mutListener.listen(30760) ? ((ListenerUtil.mutListener.listen(30754) ? (pvx % pvx) : (ListenerUtil.mutListener.listen(30753) ? (pvx / pvx) : (ListenerUtil.mutListener.listen(30752) ? (pvx - pvx) : (ListenerUtil.mutListener.listen(30751) ? (pvx + pvx) : (pvx * pvx))))) * (ListenerUtil.mutListener.listen(30758) ? (pvy % pvy) : (ListenerUtil.mutListener.listen(30757) ? (pvy / pvy) : (ListenerUtil.mutListener.listen(30756) ? (pvy - pvy) : (ListenerUtil.mutListener.listen(30755) ? (pvy + pvy) : (pvy * pvy)))))) : (ListenerUtil.mutListener.listen(30759) ? ((ListenerUtil.mutListener.listen(30754) ? (pvx % pvx) : (ListenerUtil.mutListener.listen(30753) ? (pvx / pvx) : (ListenerUtil.mutListener.listen(30752) ? (pvx - pvx) : (ListenerUtil.mutListener.listen(30751) ? (pvx + pvx) : (pvx * pvx))))) - (ListenerUtil.mutListener.listen(30758) ? (pvy % pvy) : (ListenerUtil.mutListener.listen(30757) ? (pvy / pvy) : (ListenerUtil.mutListener.listen(30756) ? (pvy - pvy) : (ListenerUtil.mutListener.listen(30755) ? (pvy + pvy) : (pvy * pvy)))))) : ((ListenerUtil.mutListener.listen(30754) ? (pvx % pvx) : (ListenerUtil.mutListener.listen(30753) ? (pvx / pvx) : (ListenerUtil.mutListener.listen(30752) ? (pvx - pvx) : (ListenerUtil.mutListener.listen(30751) ? (pvx + pvx) : (pvx * pvx))))) + (ListenerUtil.mutListener.listen(30758) ? (pvy % pvy) : (ListenerUtil.mutListener.listen(30757) ? (pvy / pvy) : (ListenerUtil.mutListener.listen(30756) ? (pvy - pvy) : (ListenerUtil.mutListener.listen(30755) ? (pvy + pvy) : (pvy * pvy)))))))))));
                }
            }
        }
        return mPrevLen;
    }

    /**
     * Check if we have a sloppy gesture. Sloppy gestures can happen if the edge
     * of the user's hand is touching the screen, for example.
     *
     * @param event
     * @return
     */
    protected boolean isSloppyGesture(MotionEvent event) {
        // As orientation can change, query the metrics in touch down
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        if (!ListenerUtil.mutListener.listen(30769)) {
            mRightSlopEdge = (ListenerUtil.mutListener.listen(30768) ? (metrics.widthPixels % mEdgeSlop) : (ListenerUtil.mutListener.listen(30767) ? (metrics.widthPixels / mEdgeSlop) : (ListenerUtil.mutListener.listen(30766) ? (metrics.widthPixels * mEdgeSlop) : (ListenerUtil.mutListener.listen(30765) ? (metrics.widthPixels + mEdgeSlop) : (metrics.widthPixels - mEdgeSlop)))));
        }
        if (!ListenerUtil.mutListener.listen(30774)) {
            mBottomSlopEdge = (ListenerUtil.mutListener.listen(30773) ? (metrics.heightPixels % mEdgeSlop) : (ListenerUtil.mutListener.listen(30772) ? (metrics.heightPixels / mEdgeSlop) : (ListenerUtil.mutListener.listen(30771) ? (metrics.heightPixels * mEdgeSlop) : (ListenerUtil.mutListener.listen(30770) ? (metrics.heightPixels + mEdgeSlop) : (metrics.heightPixels - mEdgeSlop)))));
        }
        final float edgeSlop = mEdgeSlop;
        final float rightSlop = mRightSlopEdge;
        final float bottomSlop = mBottomSlopEdge;
        final float x0 = event.getRawX();
        final float y0 = event.getRawY();
        final float x1 = getRawX(event, 1);
        final float y1 = getRawY(event, 1);
        boolean p0sloppy = (ListenerUtil.mutListener.listen(30797) ? ((ListenerUtil.mutListener.listen(30791) ? ((ListenerUtil.mutListener.listen(30785) ? ((ListenerUtil.mutListener.listen(30779) ? (x0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30778) ? (x0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30777) ? (x0 > edgeSlop) : (ListenerUtil.mutListener.listen(30776) ? (x0 != edgeSlop) : (ListenerUtil.mutListener.listen(30775) ? (x0 == edgeSlop) : (x0 < edgeSlop)))))) && (ListenerUtil.mutListener.listen(30784) ? (y0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30783) ? (y0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30782) ? (y0 > edgeSlop) : (ListenerUtil.mutListener.listen(30781) ? (y0 != edgeSlop) : (ListenerUtil.mutListener.listen(30780) ? (y0 == edgeSlop) : (y0 < edgeSlop))))))) : ((ListenerUtil.mutListener.listen(30779) ? (x0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30778) ? (x0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30777) ? (x0 > edgeSlop) : (ListenerUtil.mutListener.listen(30776) ? (x0 != edgeSlop) : (ListenerUtil.mutListener.listen(30775) ? (x0 == edgeSlop) : (x0 < edgeSlop)))))) || (ListenerUtil.mutListener.listen(30784) ? (y0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30783) ? (y0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30782) ? (y0 > edgeSlop) : (ListenerUtil.mutListener.listen(30781) ? (y0 != edgeSlop) : (ListenerUtil.mutListener.listen(30780) ? (y0 == edgeSlop) : (y0 < edgeSlop)))))))) && (ListenerUtil.mutListener.listen(30790) ? (x0 >= rightSlop) : (ListenerUtil.mutListener.listen(30789) ? (x0 <= rightSlop) : (ListenerUtil.mutListener.listen(30788) ? (x0 < rightSlop) : (ListenerUtil.mutListener.listen(30787) ? (x0 != rightSlop) : (ListenerUtil.mutListener.listen(30786) ? (x0 == rightSlop) : (x0 > rightSlop))))))) : ((ListenerUtil.mutListener.listen(30785) ? ((ListenerUtil.mutListener.listen(30779) ? (x0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30778) ? (x0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30777) ? (x0 > edgeSlop) : (ListenerUtil.mutListener.listen(30776) ? (x0 != edgeSlop) : (ListenerUtil.mutListener.listen(30775) ? (x0 == edgeSlop) : (x0 < edgeSlop)))))) && (ListenerUtil.mutListener.listen(30784) ? (y0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30783) ? (y0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30782) ? (y0 > edgeSlop) : (ListenerUtil.mutListener.listen(30781) ? (y0 != edgeSlop) : (ListenerUtil.mutListener.listen(30780) ? (y0 == edgeSlop) : (y0 < edgeSlop))))))) : ((ListenerUtil.mutListener.listen(30779) ? (x0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30778) ? (x0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30777) ? (x0 > edgeSlop) : (ListenerUtil.mutListener.listen(30776) ? (x0 != edgeSlop) : (ListenerUtil.mutListener.listen(30775) ? (x0 == edgeSlop) : (x0 < edgeSlop)))))) || (ListenerUtil.mutListener.listen(30784) ? (y0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30783) ? (y0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30782) ? (y0 > edgeSlop) : (ListenerUtil.mutListener.listen(30781) ? (y0 != edgeSlop) : (ListenerUtil.mutListener.listen(30780) ? (y0 == edgeSlop) : (y0 < edgeSlop)))))))) || (ListenerUtil.mutListener.listen(30790) ? (x0 >= rightSlop) : (ListenerUtil.mutListener.listen(30789) ? (x0 <= rightSlop) : (ListenerUtil.mutListener.listen(30788) ? (x0 < rightSlop) : (ListenerUtil.mutListener.listen(30787) ? (x0 != rightSlop) : (ListenerUtil.mutListener.listen(30786) ? (x0 == rightSlop) : (x0 > rightSlop)))))))) && (ListenerUtil.mutListener.listen(30796) ? (y0 >= bottomSlop) : (ListenerUtil.mutListener.listen(30795) ? (y0 <= bottomSlop) : (ListenerUtil.mutListener.listen(30794) ? (y0 < bottomSlop) : (ListenerUtil.mutListener.listen(30793) ? (y0 != bottomSlop) : (ListenerUtil.mutListener.listen(30792) ? (y0 == bottomSlop) : (y0 > bottomSlop))))))) : ((ListenerUtil.mutListener.listen(30791) ? ((ListenerUtil.mutListener.listen(30785) ? ((ListenerUtil.mutListener.listen(30779) ? (x0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30778) ? (x0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30777) ? (x0 > edgeSlop) : (ListenerUtil.mutListener.listen(30776) ? (x0 != edgeSlop) : (ListenerUtil.mutListener.listen(30775) ? (x0 == edgeSlop) : (x0 < edgeSlop)))))) && (ListenerUtil.mutListener.listen(30784) ? (y0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30783) ? (y0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30782) ? (y0 > edgeSlop) : (ListenerUtil.mutListener.listen(30781) ? (y0 != edgeSlop) : (ListenerUtil.mutListener.listen(30780) ? (y0 == edgeSlop) : (y0 < edgeSlop))))))) : ((ListenerUtil.mutListener.listen(30779) ? (x0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30778) ? (x0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30777) ? (x0 > edgeSlop) : (ListenerUtil.mutListener.listen(30776) ? (x0 != edgeSlop) : (ListenerUtil.mutListener.listen(30775) ? (x0 == edgeSlop) : (x0 < edgeSlop)))))) || (ListenerUtil.mutListener.listen(30784) ? (y0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30783) ? (y0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30782) ? (y0 > edgeSlop) : (ListenerUtil.mutListener.listen(30781) ? (y0 != edgeSlop) : (ListenerUtil.mutListener.listen(30780) ? (y0 == edgeSlop) : (y0 < edgeSlop)))))))) && (ListenerUtil.mutListener.listen(30790) ? (x0 >= rightSlop) : (ListenerUtil.mutListener.listen(30789) ? (x0 <= rightSlop) : (ListenerUtil.mutListener.listen(30788) ? (x0 < rightSlop) : (ListenerUtil.mutListener.listen(30787) ? (x0 != rightSlop) : (ListenerUtil.mutListener.listen(30786) ? (x0 == rightSlop) : (x0 > rightSlop))))))) : ((ListenerUtil.mutListener.listen(30785) ? ((ListenerUtil.mutListener.listen(30779) ? (x0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30778) ? (x0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30777) ? (x0 > edgeSlop) : (ListenerUtil.mutListener.listen(30776) ? (x0 != edgeSlop) : (ListenerUtil.mutListener.listen(30775) ? (x0 == edgeSlop) : (x0 < edgeSlop)))))) && (ListenerUtil.mutListener.listen(30784) ? (y0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30783) ? (y0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30782) ? (y0 > edgeSlop) : (ListenerUtil.mutListener.listen(30781) ? (y0 != edgeSlop) : (ListenerUtil.mutListener.listen(30780) ? (y0 == edgeSlop) : (y0 < edgeSlop))))))) : ((ListenerUtil.mutListener.listen(30779) ? (x0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30778) ? (x0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30777) ? (x0 > edgeSlop) : (ListenerUtil.mutListener.listen(30776) ? (x0 != edgeSlop) : (ListenerUtil.mutListener.listen(30775) ? (x0 == edgeSlop) : (x0 < edgeSlop)))))) || (ListenerUtil.mutListener.listen(30784) ? (y0 >= edgeSlop) : (ListenerUtil.mutListener.listen(30783) ? (y0 <= edgeSlop) : (ListenerUtil.mutListener.listen(30782) ? (y0 > edgeSlop) : (ListenerUtil.mutListener.listen(30781) ? (y0 != edgeSlop) : (ListenerUtil.mutListener.listen(30780) ? (y0 == edgeSlop) : (y0 < edgeSlop)))))))) || (ListenerUtil.mutListener.listen(30790) ? (x0 >= rightSlop) : (ListenerUtil.mutListener.listen(30789) ? (x0 <= rightSlop) : (ListenerUtil.mutListener.listen(30788) ? (x0 < rightSlop) : (ListenerUtil.mutListener.listen(30787) ? (x0 != rightSlop) : (ListenerUtil.mutListener.listen(30786) ? (x0 == rightSlop) : (x0 > rightSlop)))))))) || (ListenerUtil.mutListener.listen(30796) ? (y0 >= bottomSlop) : (ListenerUtil.mutListener.listen(30795) ? (y0 <= bottomSlop) : (ListenerUtil.mutListener.listen(30794) ? (y0 < bottomSlop) : (ListenerUtil.mutListener.listen(30793) ? (y0 != bottomSlop) : (ListenerUtil.mutListener.listen(30792) ? (y0 == bottomSlop) : (y0 > bottomSlop))))))));
        boolean p1sloppy = (ListenerUtil.mutListener.listen(30820) ? ((ListenerUtil.mutListener.listen(30814) ? ((ListenerUtil.mutListener.listen(30808) ? ((ListenerUtil.mutListener.listen(30802) ? (x1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30801) ? (x1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30800) ? (x1 > edgeSlop) : (ListenerUtil.mutListener.listen(30799) ? (x1 != edgeSlop) : (ListenerUtil.mutListener.listen(30798) ? (x1 == edgeSlop) : (x1 < edgeSlop)))))) && (ListenerUtil.mutListener.listen(30807) ? (y1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30806) ? (y1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30805) ? (y1 > edgeSlop) : (ListenerUtil.mutListener.listen(30804) ? (y1 != edgeSlop) : (ListenerUtil.mutListener.listen(30803) ? (y1 == edgeSlop) : (y1 < edgeSlop))))))) : ((ListenerUtil.mutListener.listen(30802) ? (x1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30801) ? (x1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30800) ? (x1 > edgeSlop) : (ListenerUtil.mutListener.listen(30799) ? (x1 != edgeSlop) : (ListenerUtil.mutListener.listen(30798) ? (x1 == edgeSlop) : (x1 < edgeSlop)))))) || (ListenerUtil.mutListener.listen(30807) ? (y1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30806) ? (y1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30805) ? (y1 > edgeSlop) : (ListenerUtil.mutListener.listen(30804) ? (y1 != edgeSlop) : (ListenerUtil.mutListener.listen(30803) ? (y1 == edgeSlop) : (y1 < edgeSlop)))))))) && (ListenerUtil.mutListener.listen(30813) ? (x1 >= rightSlop) : (ListenerUtil.mutListener.listen(30812) ? (x1 <= rightSlop) : (ListenerUtil.mutListener.listen(30811) ? (x1 < rightSlop) : (ListenerUtil.mutListener.listen(30810) ? (x1 != rightSlop) : (ListenerUtil.mutListener.listen(30809) ? (x1 == rightSlop) : (x1 > rightSlop))))))) : ((ListenerUtil.mutListener.listen(30808) ? ((ListenerUtil.mutListener.listen(30802) ? (x1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30801) ? (x1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30800) ? (x1 > edgeSlop) : (ListenerUtil.mutListener.listen(30799) ? (x1 != edgeSlop) : (ListenerUtil.mutListener.listen(30798) ? (x1 == edgeSlop) : (x1 < edgeSlop)))))) && (ListenerUtil.mutListener.listen(30807) ? (y1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30806) ? (y1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30805) ? (y1 > edgeSlop) : (ListenerUtil.mutListener.listen(30804) ? (y1 != edgeSlop) : (ListenerUtil.mutListener.listen(30803) ? (y1 == edgeSlop) : (y1 < edgeSlop))))))) : ((ListenerUtil.mutListener.listen(30802) ? (x1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30801) ? (x1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30800) ? (x1 > edgeSlop) : (ListenerUtil.mutListener.listen(30799) ? (x1 != edgeSlop) : (ListenerUtil.mutListener.listen(30798) ? (x1 == edgeSlop) : (x1 < edgeSlop)))))) || (ListenerUtil.mutListener.listen(30807) ? (y1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30806) ? (y1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30805) ? (y1 > edgeSlop) : (ListenerUtil.mutListener.listen(30804) ? (y1 != edgeSlop) : (ListenerUtil.mutListener.listen(30803) ? (y1 == edgeSlop) : (y1 < edgeSlop)))))))) || (ListenerUtil.mutListener.listen(30813) ? (x1 >= rightSlop) : (ListenerUtil.mutListener.listen(30812) ? (x1 <= rightSlop) : (ListenerUtil.mutListener.listen(30811) ? (x1 < rightSlop) : (ListenerUtil.mutListener.listen(30810) ? (x1 != rightSlop) : (ListenerUtil.mutListener.listen(30809) ? (x1 == rightSlop) : (x1 > rightSlop)))))))) && (ListenerUtil.mutListener.listen(30819) ? (y1 >= bottomSlop) : (ListenerUtil.mutListener.listen(30818) ? (y1 <= bottomSlop) : (ListenerUtil.mutListener.listen(30817) ? (y1 < bottomSlop) : (ListenerUtil.mutListener.listen(30816) ? (y1 != bottomSlop) : (ListenerUtil.mutListener.listen(30815) ? (y1 == bottomSlop) : (y1 > bottomSlop))))))) : ((ListenerUtil.mutListener.listen(30814) ? ((ListenerUtil.mutListener.listen(30808) ? ((ListenerUtil.mutListener.listen(30802) ? (x1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30801) ? (x1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30800) ? (x1 > edgeSlop) : (ListenerUtil.mutListener.listen(30799) ? (x1 != edgeSlop) : (ListenerUtil.mutListener.listen(30798) ? (x1 == edgeSlop) : (x1 < edgeSlop)))))) && (ListenerUtil.mutListener.listen(30807) ? (y1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30806) ? (y1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30805) ? (y1 > edgeSlop) : (ListenerUtil.mutListener.listen(30804) ? (y1 != edgeSlop) : (ListenerUtil.mutListener.listen(30803) ? (y1 == edgeSlop) : (y1 < edgeSlop))))))) : ((ListenerUtil.mutListener.listen(30802) ? (x1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30801) ? (x1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30800) ? (x1 > edgeSlop) : (ListenerUtil.mutListener.listen(30799) ? (x1 != edgeSlop) : (ListenerUtil.mutListener.listen(30798) ? (x1 == edgeSlop) : (x1 < edgeSlop)))))) || (ListenerUtil.mutListener.listen(30807) ? (y1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30806) ? (y1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30805) ? (y1 > edgeSlop) : (ListenerUtil.mutListener.listen(30804) ? (y1 != edgeSlop) : (ListenerUtil.mutListener.listen(30803) ? (y1 == edgeSlop) : (y1 < edgeSlop)))))))) && (ListenerUtil.mutListener.listen(30813) ? (x1 >= rightSlop) : (ListenerUtil.mutListener.listen(30812) ? (x1 <= rightSlop) : (ListenerUtil.mutListener.listen(30811) ? (x1 < rightSlop) : (ListenerUtil.mutListener.listen(30810) ? (x1 != rightSlop) : (ListenerUtil.mutListener.listen(30809) ? (x1 == rightSlop) : (x1 > rightSlop))))))) : ((ListenerUtil.mutListener.listen(30808) ? ((ListenerUtil.mutListener.listen(30802) ? (x1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30801) ? (x1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30800) ? (x1 > edgeSlop) : (ListenerUtil.mutListener.listen(30799) ? (x1 != edgeSlop) : (ListenerUtil.mutListener.listen(30798) ? (x1 == edgeSlop) : (x1 < edgeSlop)))))) && (ListenerUtil.mutListener.listen(30807) ? (y1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30806) ? (y1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30805) ? (y1 > edgeSlop) : (ListenerUtil.mutListener.listen(30804) ? (y1 != edgeSlop) : (ListenerUtil.mutListener.listen(30803) ? (y1 == edgeSlop) : (y1 < edgeSlop))))))) : ((ListenerUtil.mutListener.listen(30802) ? (x1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30801) ? (x1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30800) ? (x1 > edgeSlop) : (ListenerUtil.mutListener.listen(30799) ? (x1 != edgeSlop) : (ListenerUtil.mutListener.listen(30798) ? (x1 == edgeSlop) : (x1 < edgeSlop)))))) || (ListenerUtil.mutListener.listen(30807) ? (y1 >= edgeSlop) : (ListenerUtil.mutListener.listen(30806) ? (y1 <= edgeSlop) : (ListenerUtil.mutListener.listen(30805) ? (y1 > edgeSlop) : (ListenerUtil.mutListener.listen(30804) ? (y1 != edgeSlop) : (ListenerUtil.mutListener.listen(30803) ? (y1 == edgeSlop) : (y1 < edgeSlop)))))))) || (ListenerUtil.mutListener.listen(30813) ? (x1 >= rightSlop) : (ListenerUtil.mutListener.listen(30812) ? (x1 <= rightSlop) : (ListenerUtil.mutListener.listen(30811) ? (x1 < rightSlop) : (ListenerUtil.mutListener.listen(30810) ? (x1 != rightSlop) : (ListenerUtil.mutListener.listen(30809) ? (x1 == rightSlop) : (x1 > rightSlop)))))))) || (ListenerUtil.mutListener.listen(30819) ? (y1 >= bottomSlop) : (ListenerUtil.mutListener.listen(30818) ? (y1 <= bottomSlop) : (ListenerUtil.mutListener.listen(30817) ? (y1 < bottomSlop) : (ListenerUtil.mutListener.listen(30816) ? (y1 != bottomSlop) : (ListenerUtil.mutListener.listen(30815) ? (y1 == bottomSlop) : (y1 > bottomSlop))))))));
        if (!ListenerUtil.mutListener.listen(30822)) {
            if ((ListenerUtil.mutListener.listen(30821) ? (p0sloppy || p1sloppy) : (p0sloppy && p1sloppy))) {
                return true;
            } else if (p0sloppy) {
                return true;
            } else if (p1sloppy) {
                return true;
            }
        }
        return false;
    }

    /**
     * MotionEvent has no getRawX(int) method; simulate it pending future API approval.
     *
     * @param event
     * @param pointerIndex
     * @return
     */
    protected static float getRawX(MotionEvent event, int pointerIndex) {
        float offset = (ListenerUtil.mutListener.listen(30826) ? (event.getX() % event.getRawX()) : (ListenerUtil.mutListener.listen(30825) ? (event.getX() / event.getRawX()) : (ListenerUtil.mutListener.listen(30824) ? (event.getX() * event.getRawX()) : (ListenerUtil.mutListener.listen(30823) ? (event.getX() + event.getRawX()) : (event.getX() - event.getRawX())))));
        if (!ListenerUtil.mutListener.listen(30832)) {
            if ((ListenerUtil.mutListener.listen(30831) ? (pointerIndex >= event.getPointerCount()) : (ListenerUtil.mutListener.listen(30830) ? (pointerIndex <= event.getPointerCount()) : (ListenerUtil.mutListener.listen(30829) ? (pointerIndex > event.getPointerCount()) : (ListenerUtil.mutListener.listen(30828) ? (pointerIndex != event.getPointerCount()) : (ListenerUtil.mutListener.listen(30827) ? (pointerIndex == event.getPointerCount()) : (pointerIndex < event.getPointerCount()))))))) {
                return event.getX(pointerIndex) + offset;
            }
        }
        return 0f;
    }

    /**
     * MotionEvent has no getRawY(int) method; simulate it pending future API approval.
     *
     * @param event
     * @param pointerIndex
     * @return
     */
    protected static float getRawY(MotionEvent event, int pointerIndex) {
        float offset = Math.abs((ListenerUtil.mutListener.listen(30836) ? (event.getY() % event.getRawY()) : (ListenerUtil.mutListener.listen(30835) ? (event.getY() / event.getRawY()) : (ListenerUtil.mutListener.listen(30834) ? (event.getY() * event.getRawY()) : (ListenerUtil.mutListener.listen(30833) ? (event.getY() + event.getRawY()) : (event.getY() - event.getRawY()))))));
        if (!ListenerUtil.mutListener.listen(30842)) {
            if ((ListenerUtil.mutListener.listen(30841) ? (pointerIndex >= event.getPointerCount()) : (ListenerUtil.mutListener.listen(30840) ? (pointerIndex <= event.getPointerCount()) : (ListenerUtil.mutListener.listen(30839) ? (pointerIndex > event.getPointerCount()) : (ListenerUtil.mutListener.listen(30838) ? (pointerIndex != event.getPointerCount()) : (ListenerUtil.mutListener.listen(30837) ? (pointerIndex == event.getPointerCount()) : (pointerIndex < event.getPointerCount()))))))) {
                return event.getY(pointerIndex) + offset;
            }
        }
        return 0f;
    }
}

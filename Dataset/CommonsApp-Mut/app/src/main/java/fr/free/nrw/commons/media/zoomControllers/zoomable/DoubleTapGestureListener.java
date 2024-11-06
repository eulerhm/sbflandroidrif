package fr.free.nrw.commons.media.zoomControllers.zoomable;

import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Tap gesture listener for double tap to zoom / unzoom and double-tap-and-drag to zoom.
 *
 * @see ZoomableDraweeView#setTapListener(GestureDetector.SimpleOnGestureListener)
 */
public class DoubleTapGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int DURATION_MS = 300;

    private static final int DOUBLE_TAP_SCROLL_THRESHOLD = 20;

    private final ZoomableDraweeView mDraweeView;

    private final PointF mDoubleTapViewPoint = new PointF();

    private final PointF mDoubleTapImagePoint = new PointF();

    private float mDoubleTapScale = 1;

    private boolean mDoubleTapScroll = false;

    public DoubleTapGestureListener(ZoomableDraweeView zoomableDraweeView) {
        mDraweeView = zoomableDraweeView;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        AbstractAnimatedZoomableController zc = (AbstractAnimatedZoomableController) mDraweeView.getZoomableController();
        PointF vp = new PointF(e.getX(), e.getY());
        PointF ip = zc.mapViewToImage(vp);
        if (!ListenerUtil.mutListener.listen(8425)) {
            switch(e.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    if (!ListenerUtil.mutListener.listen(8399)) {
                        mDoubleTapViewPoint.set(vp);
                    }
                    if (!ListenerUtil.mutListener.listen(8400)) {
                        mDoubleTapImagePoint.set(ip);
                    }
                    if (!ListenerUtil.mutListener.listen(8401)) {
                        mDoubleTapScale = zc.getScaleFactor();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!ListenerUtil.mutListener.listen(8403)) {
                        mDoubleTapScroll = (ListenerUtil.mutListener.listen(8402) ? (mDoubleTapScroll && shouldStartDoubleTapScroll(vp)) : (mDoubleTapScroll || shouldStartDoubleTapScroll(vp)));
                    }
                    if (!ListenerUtil.mutListener.listen(8405)) {
                        if (mDoubleTapScroll) {
                            float scale = calcScale(vp);
                            if (!ListenerUtil.mutListener.listen(8404)) {
                                zc.zoomToPoint(scale, mDoubleTapImagePoint, mDoubleTapViewPoint);
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (!ListenerUtil.mutListener.listen(8423)) {
                        if (mDoubleTapScroll) {
                            float scale = calcScale(vp);
                            if (!ListenerUtil.mutListener.listen(8422)) {
                                zc.zoomToPoint(scale, mDoubleTapImagePoint, mDoubleTapViewPoint);
                            }
                        } else {
                            final float maxScale = zc.getMaxScaleFactor();
                            final float minScale = zc.getMinScaleFactor();
                            if (!ListenerUtil.mutListener.listen(8421)) {
                                if ((ListenerUtil.mutListener.listen(8418) ? (zc.getScaleFactor() >= (ListenerUtil.mutListener.listen(8413) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) % 2) : (ListenerUtil.mutListener.listen(8412) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) * 2) : (ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) - 2) : (ListenerUtil.mutListener.listen(8410) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) + 2) : (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) / 2)))))) : (ListenerUtil.mutListener.listen(8417) ? (zc.getScaleFactor() <= (ListenerUtil.mutListener.listen(8413) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) % 2) : (ListenerUtil.mutListener.listen(8412) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) * 2) : (ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) - 2) : (ListenerUtil.mutListener.listen(8410) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) + 2) : (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) / 2)))))) : (ListenerUtil.mutListener.listen(8416) ? (zc.getScaleFactor() > (ListenerUtil.mutListener.listen(8413) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) % 2) : (ListenerUtil.mutListener.listen(8412) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) * 2) : (ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) - 2) : (ListenerUtil.mutListener.listen(8410) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) + 2) : (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) / 2)))))) : (ListenerUtil.mutListener.listen(8415) ? (zc.getScaleFactor() != (ListenerUtil.mutListener.listen(8413) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) % 2) : (ListenerUtil.mutListener.listen(8412) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) * 2) : (ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) - 2) : (ListenerUtil.mutListener.listen(8410) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) + 2) : (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) / 2)))))) : (ListenerUtil.mutListener.listen(8414) ? (zc.getScaleFactor() == (ListenerUtil.mutListener.listen(8413) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) % 2) : (ListenerUtil.mutListener.listen(8412) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) * 2) : (ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) - 2) : (ListenerUtil.mutListener.listen(8410) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) + 2) : (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) / 2)))))) : (zc.getScaleFactor() < (ListenerUtil.mutListener.listen(8413) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) % 2) : (ListenerUtil.mutListener.listen(8412) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) * 2) : (ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) - 2) : (ListenerUtil.mutListener.listen(8410) ? (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) + 2) : (((ListenerUtil.mutListener.listen(8409) ? (maxScale % minScale) : (ListenerUtil.mutListener.listen(8408) ? (maxScale / minScale) : (ListenerUtil.mutListener.listen(8407) ? (maxScale * minScale) : (ListenerUtil.mutListener.listen(8406) ? (maxScale - minScale) : (maxScale + minScale)))))) / 2)))))))))))) {
                                    if (!ListenerUtil.mutListener.listen(8420)) {
                                        zc.zoomToPoint(maxScale, ip, vp, DefaultZoomableController.LIMIT_ALL, DURATION_MS, null);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(8419)) {
                                        zc.zoomToPoint(minScale, ip, vp, DefaultZoomableController.LIMIT_ALL, DURATION_MS, null);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8424)) {
                        mDoubleTapScroll = false;
                    }
                    break;
            }
        }
        return true;
    }

    private boolean shouldStartDoubleTapScroll(PointF viewPoint) {
        double dist = Math.hypot((ListenerUtil.mutListener.listen(8429) ? (viewPoint.x % mDoubleTapViewPoint.x) : (ListenerUtil.mutListener.listen(8428) ? (viewPoint.x / mDoubleTapViewPoint.x) : (ListenerUtil.mutListener.listen(8427) ? (viewPoint.x * mDoubleTapViewPoint.x) : (ListenerUtil.mutListener.listen(8426) ? (viewPoint.x + mDoubleTapViewPoint.x) : (viewPoint.x - mDoubleTapViewPoint.x))))), (ListenerUtil.mutListener.listen(8433) ? (viewPoint.y % mDoubleTapViewPoint.y) : (ListenerUtil.mutListener.listen(8432) ? (viewPoint.y / mDoubleTapViewPoint.y) : (ListenerUtil.mutListener.listen(8431) ? (viewPoint.y * mDoubleTapViewPoint.y) : (ListenerUtil.mutListener.listen(8430) ? (viewPoint.y + mDoubleTapViewPoint.y) : (viewPoint.y - mDoubleTapViewPoint.y))))));
        return (ListenerUtil.mutListener.listen(8438) ? (dist >= DOUBLE_TAP_SCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(8437) ? (dist <= DOUBLE_TAP_SCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(8436) ? (dist < DOUBLE_TAP_SCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(8435) ? (dist != DOUBLE_TAP_SCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(8434) ? (dist == DOUBLE_TAP_SCROLL_THRESHOLD) : (dist > DOUBLE_TAP_SCROLL_THRESHOLD))))));
    }

    private float calcScale(PointF currentViewPoint) {
        float dy = ((ListenerUtil.mutListener.listen(8442) ? (currentViewPoint.y % mDoubleTapViewPoint.y) : (ListenerUtil.mutListener.listen(8441) ? (currentViewPoint.y / mDoubleTapViewPoint.y) : (ListenerUtil.mutListener.listen(8440) ? (currentViewPoint.y * mDoubleTapViewPoint.y) : (ListenerUtil.mutListener.listen(8439) ? (currentViewPoint.y + mDoubleTapViewPoint.y) : (currentViewPoint.y - mDoubleTapViewPoint.y))))));
        float t = (ListenerUtil.mutListener.listen(8450) ? (1 % (ListenerUtil.mutListener.listen(8446) ? (Math.abs(dy) % 0.001f) : (ListenerUtil.mutListener.listen(8445) ? (Math.abs(dy) / 0.001f) : (ListenerUtil.mutListener.listen(8444) ? (Math.abs(dy) - 0.001f) : (ListenerUtil.mutListener.listen(8443) ? (Math.abs(dy) + 0.001f) : (Math.abs(dy) * 0.001f)))))) : (ListenerUtil.mutListener.listen(8449) ? (1 / (ListenerUtil.mutListener.listen(8446) ? (Math.abs(dy) % 0.001f) : (ListenerUtil.mutListener.listen(8445) ? (Math.abs(dy) / 0.001f) : (ListenerUtil.mutListener.listen(8444) ? (Math.abs(dy) - 0.001f) : (ListenerUtil.mutListener.listen(8443) ? (Math.abs(dy) + 0.001f) : (Math.abs(dy) * 0.001f)))))) : (ListenerUtil.mutListener.listen(8448) ? (1 * (ListenerUtil.mutListener.listen(8446) ? (Math.abs(dy) % 0.001f) : (ListenerUtil.mutListener.listen(8445) ? (Math.abs(dy) / 0.001f) : (ListenerUtil.mutListener.listen(8444) ? (Math.abs(dy) - 0.001f) : (ListenerUtil.mutListener.listen(8443) ? (Math.abs(dy) + 0.001f) : (Math.abs(dy) * 0.001f)))))) : (ListenerUtil.mutListener.listen(8447) ? (1 - (ListenerUtil.mutListener.listen(8446) ? (Math.abs(dy) % 0.001f) : (ListenerUtil.mutListener.listen(8445) ? (Math.abs(dy) / 0.001f) : (ListenerUtil.mutListener.listen(8444) ? (Math.abs(dy) - 0.001f) : (ListenerUtil.mutListener.listen(8443) ? (Math.abs(dy) + 0.001f) : (Math.abs(dy) * 0.001f)))))) : (1 + (ListenerUtil.mutListener.listen(8446) ? (Math.abs(dy) % 0.001f) : (ListenerUtil.mutListener.listen(8445) ? (Math.abs(dy) / 0.001f) : (ListenerUtil.mutListener.listen(8444) ? (Math.abs(dy) - 0.001f) : (ListenerUtil.mutListener.listen(8443) ? (Math.abs(dy) + 0.001f) : (Math.abs(dy) * 0.001f))))))))));
        return ((ListenerUtil.mutListener.listen(8455) ? (dy >= 0) : (ListenerUtil.mutListener.listen(8454) ? (dy <= 0) : (ListenerUtil.mutListener.listen(8453) ? (dy > 0) : (ListenerUtil.mutListener.listen(8452) ? (dy != 0) : (ListenerUtil.mutListener.listen(8451) ? (dy == 0) : (dy < 0))))))) ? (ListenerUtil.mutListener.listen(8463) ? (mDoubleTapScale % t) : (ListenerUtil.mutListener.listen(8462) ? (mDoubleTapScale * t) : (ListenerUtil.mutListener.listen(8461) ? (mDoubleTapScale - t) : (ListenerUtil.mutListener.listen(8460) ? (mDoubleTapScale + t) : (mDoubleTapScale / t))))) : (ListenerUtil.mutListener.listen(8459) ? (mDoubleTapScale % t) : (ListenerUtil.mutListener.listen(8458) ? (mDoubleTapScale / t) : (ListenerUtil.mutListener.listen(8457) ? (mDoubleTapScale - t) : (ListenerUtil.mutListener.listen(8456) ? (mDoubleTapScale + t) : (mDoubleTapScale * t)))));
    }
}

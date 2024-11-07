package fr.free.nrw.commons.media.zoomControllers.zoomable;

import android.view.GestureDetector;
import android.view.MotionEvent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Wrapper for SimpleOnGestureListener as GestureDetector does not allow changing its listener.
 */
public class GestureListenerWrapper extends GestureDetector.SimpleOnGestureListener {

    private GestureDetector.SimpleOnGestureListener mDelegate;

    public GestureListenerWrapper() {
        if (!ListenerUtil.mutListener.listen(8536)) {
            mDelegate = new GestureDetector.SimpleOnGestureListener();
        }
    }

    public void setListener(GestureDetector.SimpleOnGestureListener listener) {
        if (!ListenerUtil.mutListener.listen(8537)) {
            mDelegate = listener;
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if (!ListenerUtil.mutListener.listen(8538)) {
            mDelegate.onLongPress(e);
        }
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return mDelegate.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return mDelegate.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public void onShowPress(MotionEvent e) {
        if (!ListenerUtil.mutListener.listen(8539)) {
            mDelegate.onShowPress(e);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return mDelegate.onDown(e);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return mDelegate.onDoubleTap(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return mDelegate.onDoubleTapEvent(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return mDelegate.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return mDelegate.onSingleTapUp(e);
    }
}

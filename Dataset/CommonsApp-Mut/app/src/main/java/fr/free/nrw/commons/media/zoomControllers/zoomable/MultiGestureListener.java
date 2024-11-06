package fr.free.nrw.commons.media.zoomControllers.zoomable;

import android.view.GestureDetector;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Gesture listener that allows multiple child listeners to be added and notified about gesture
 * events.
 *
 * NOTE: The order of the listeners is important. Listeners can consume gesture events. For
 * example, if one of the child listeners consumes {@link #onLongPress(MotionEvent)} (the listener
 * returned true), subsequent listeners will not be notified about the event any more since it has
 * been consumed.
 */
public class MultiGestureListener extends GestureDetector.SimpleOnGestureListener {

    private final List<GestureDetector.SimpleOnGestureListener> mListeners = new ArrayList<>();

    /**
     * Adds a listener to the multi gesture listener.
     *
     * <p>NOTE: The order of the listeners is important since gesture events can be consumed.
     *
     * @param listener the listener to be added
     */
    public synchronized void addListener(GestureDetector.SimpleOnGestureListener listener) {
        if (!ListenerUtil.mutListener.listen(8464)) {
            mListeners.add(listener);
        }
    }

    /**
     * Removes the given listener so that it will not be notified about future events.
     *
     * <p>NOTE: The order of the listeners is important since gesture events can be consumed.
     *
     * @param listener the listener to remove
     */
    public synchronized void removeListener(GestureDetector.SimpleOnGestureListener listener) {
        if (!ListenerUtil.mutListener.listen(8465)) {
            mListeners.remove(listener);
        }
    }

    @Override
    public synchronized boolean onSingleTapUp(MotionEvent e) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8472)) {
            {
                long _loopCounter131 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8471) ? (i >= size) : (ListenerUtil.mutListener.listen(8470) ? (i <= size) : (ListenerUtil.mutListener.listen(8469) ? (i > size) : (ListenerUtil.mutListener.listen(8468) ? (i != size) : (ListenerUtil.mutListener.listen(8467) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter131", ++_loopCounter131);
                    if (!ListenerUtil.mutListener.listen(8466)) {
                        if (mListeners.get(i).onSingleTapUp(e)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public synchronized void onLongPress(MotionEvent e) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8479)) {
            {
                long _loopCounter132 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8478) ? (i >= size) : (ListenerUtil.mutListener.listen(8477) ? (i <= size) : (ListenerUtil.mutListener.listen(8476) ? (i > size) : (ListenerUtil.mutListener.listen(8475) ? (i != size) : (ListenerUtil.mutListener.listen(8474) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter132", ++_loopCounter132);
                    if (!ListenerUtil.mutListener.listen(8473)) {
                        mListeners.get(i).onLongPress(e);
                    }
                }
            }
        }
    }

    @Override
    public synchronized boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8486)) {
            {
                long _loopCounter133 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8485) ? (i >= size) : (ListenerUtil.mutListener.listen(8484) ? (i <= size) : (ListenerUtil.mutListener.listen(8483) ? (i > size) : (ListenerUtil.mutListener.listen(8482) ? (i != size) : (ListenerUtil.mutListener.listen(8481) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter133", ++_loopCounter133);
                    if (!ListenerUtil.mutListener.listen(8480)) {
                        if (mListeners.get(i).onScroll(e1, e2, distanceX, distanceY)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public synchronized boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8493)) {
            {
                long _loopCounter134 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8492) ? (i >= size) : (ListenerUtil.mutListener.listen(8491) ? (i <= size) : (ListenerUtil.mutListener.listen(8490) ? (i > size) : (ListenerUtil.mutListener.listen(8489) ? (i != size) : (ListenerUtil.mutListener.listen(8488) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter134", ++_loopCounter134);
                    if (!ListenerUtil.mutListener.listen(8487)) {
                        if (mListeners.get(i).onFling(e1, e2, velocityX, velocityY)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public synchronized void onShowPress(MotionEvent e) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8500)) {
            {
                long _loopCounter135 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8499) ? (i >= size) : (ListenerUtil.mutListener.listen(8498) ? (i <= size) : (ListenerUtil.mutListener.listen(8497) ? (i > size) : (ListenerUtil.mutListener.listen(8496) ? (i != size) : (ListenerUtil.mutListener.listen(8495) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter135", ++_loopCounter135);
                    if (!ListenerUtil.mutListener.listen(8494)) {
                        mListeners.get(i).onShowPress(e);
                    }
                }
            }
        }
    }

    @Override
    public synchronized boolean onDown(MotionEvent e) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8507)) {
            {
                long _loopCounter136 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8506) ? (i >= size) : (ListenerUtil.mutListener.listen(8505) ? (i <= size) : (ListenerUtil.mutListener.listen(8504) ? (i > size) : (ListenerUtil.mutListener.listen(8503) ? (i != size) : (ListenerUtil.mutListener.listen(8502) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter136", ++_loopCounter136);
                    if (!ListenerUtil.mutListener.listen(8501)) {
                        if (mListeners.get(i).onDown(e)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public synchronized boolean onDoubleTap(MotionEvent e) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8514)) {
            {
                long _loopCounter137 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8513) ? (i >= size) : (ListenerUtil.mutListener.listen(8512) ? (i <= size) : (ListenerUtil.mutListener.listen(8511) ? (i > size) : (ListenerUtil.mutListener.listen(8510) ? (i != size) : (ListenerUtil.mutListener.listen(8509) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter137", ++_loopCounter137);
                    if (!ListenerUtil.mutListener.listen(8508)) {
                        if (mListeners.get(i).onDoubleTap(e)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public synchronized boolean onDoubleTapEvent(MotionEvent e) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8521)) {
            {
                long _loopCounter138 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8520) ? (i >= size) : (ListenerUtil.mutListener.listen(8519) ? (i <= size) : (ListenerUtil.mutListener.listen(8518) ? (i > size) : (ListenerUtil.mutListener.listen(8517) ? (i != size) : (ListenerUtil.mutListener.listen(8516) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter138", ++_loopCounter138);
                    if (!ListenerUtil.mutListener.listen(8515)) {
                        if (mListeners.get(i).onDoubleTapEvent(e)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public synchronized boolean onSingleTapConfirmed(MotionEvent e) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8528)) {
            {
                long _loopCounter139 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8527) ? (i >= size) : (ListenerUtil.mutListener.listen(8526) ? (i <= size) : (ListenerUtil.mutListener.listen(8525) ? (i > size) : (ListenerUtil.mutListener.listen(8524) ? (i != size) : (ListenerUtil.mutListener.listen(8523) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter139", ++_loopCounter139);
                    if (!ListenerUtil.mutListener.listen(8522)) {
                        if (mListeners.get(i).onSingleTapConfirmed(e)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public synchronized boolean onContextClick(MotionEvent e) {
        final int size = mListeners.size();
        if (!ListenerUtil.mutListener.listen(8535)) {
            {
                long _loopCounter140 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8534) ? (i >= size) : (ListenerUtil.mutListener.listen(8533) ? (i <= size) : (ListenerUtil.mutListener.listen(8532) ? (i > size) : (ListenerUtil.mutListener.listen(8531) ? (i != size) : (ListenerUtil.mutListener.listen(8530) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter140", ++_loopCounter140);
                    if (!ListenerUtil.mutListener.listen(8529)) {
                        if (mListeners.get(i).onContextClick(e)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

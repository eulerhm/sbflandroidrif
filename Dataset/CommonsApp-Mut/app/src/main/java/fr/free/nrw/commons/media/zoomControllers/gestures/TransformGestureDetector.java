package fr.free.nrw.commons.media.zoomControllers.gestures;

import android.view.MotionEvent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Component that detects translation, scale and rotation based on touch events.
 *
 * This class notifies its listeners whenever a gesture begins, updates or ends. The instance of
 * this detector is passed to the listeners, so it can be queried for pivot, translation, scale or
 * rotation.
 */
public class TransformGestureDetector implements MultiPointerGestureDetector.Listener {

    /**
     * The listener for receiving notifications when gestures occur.
     */
    public interface Listener {

        /**
         * A callback called right before the gesture is about to start.
         */
        public void onGestureBegin(TransformGestureDetector detector);

        /**
         * A callback called each time the gesture gets updated.
         */
        public void onGestureUpdate(TransformGestureDetector detector);

        /**
         * A callback called right after the gesture has finished.
         */
        public void onGestureEnd(TransformGestureDetector detector);
    }

    private final MultiPointerGestureDetector mDetector;

    private Listener mListener = null;

    public TransformGestureDetector(MultiPointerGestureDetector multiPointerGestureDetector) {
        mDetector = multiPointerGestureDetector;
        if (!ListenerUtil.mutListener.listen(8647)) {
            mDetector.setListener(this);
        }
    }

    /**
     * Factory method that creates a new instance of TransformGestureDetector
     */
    public static TransformGestureDetector newInstance() {
        return new TransformGestureDetector(MultiPointerGestureDetector.newInstance());
    }

    /**
     * Sets the listener.
     *
     * @param listener listener to set
     */
    public void setListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(8648)) {
            mListener = listener;
        }
    }

    /**
     * Resets the component to the initial state.
     */
    public void reset() {
        if (!ListenerUtil.mutListener.listen(8649)) {
            mDetector.reset();
        }
    }

    /**
     * Handles the given motion event.
     *
     * @param event event to handle
     * @return whether or not the event was handled
     */
    public boolean onTouchEvent(final MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    @Override
    public void onGestureBegin(MultiPointerGestureDetector detector) {
        if (!ListenerUtil.mutListener.listen(8651)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(8650)) {
                    mListener.onGestureBegin(this);
                }
            }
        }
    }

    @Override
    public void onGestureUpdate(MultiPointerGestureDetector detector) {
        if (!ListenerUtil.mutListener.listen(8653)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(8652)) {
                    mListener.onGestureUpdate(this);
                }
            }
        }
    }

    @Override
    public void onGestureEnd(MultiPointerGestureDetector detector) {
        if (!ListenerUtil.mutListener.listen(8655)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(8654)) {
                    mListener.onGestureEnd(this);
                }
            }
        }
    }

    private float calcAverage(float[] arr, int len) {
        float sum = 0;
        if (!ListenerUtil.mutListener.listen(8662)) {
            {
                long _loopCounter145 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8661) ? (i >= len) : (ListenerUtil.mutListener.listen(8660) ? (i <= len) : (ListenerUtil.mutListener.listen(8659) ? (i > len) : (ListenerUtil.mutListener.listen(8658) ? (i != len) : (ListenerUtil.mutListener.listen(8657) ? (i == len) : (i < len)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter145", ++_loopCounter145);
                    if (!ListenerUtil.mutListener.listen(8656)) {
                        sum += arr[i];
                    }
                }
            }
        }
        return ((ListenerUtil.mutListener.listen(8667) ? (len >= 0) : (ListenerUtil.mutListener.listen(8666) ? (len <= 0) : (ListenerUtil.mutListener.listen(8665) ? (len < 0) : (ListenerUtil.mutListener.listen(8664) ? (len != 0) : (ListenerUtil.mutListener.listen(8663) ? (len == 0) : (len > 0))))))) ? (ListenerUtil.mutListener.listen(8671) ? (sum % len) : (ListenerUtil.mutListener.listen(8670) ? (sum * len) : (ListenerUtil.mutListener.listen(8669) ? (sum - len) : (ListenerUtil.mutListener.listen(8668) ? (sum + len) : (sum / len))))) : 0;
    }

    /**
     * Restarts the current gesture (if any).
     */
    public void restartGesture() {
        if (!ListenerUtil.mutListener.listen(8672)) {
            mDetector.restartGesture();
        }
    }

    /**
     * Gets whether there is a gesture in progress
     */
    public boolean isGestureInProgress() {
        return mDetector.isGestureInProgress();
    }

    /**
     * Gets the number of pointers after the current gesture
     */
    public int getNewPointerCount() {
        return mDetector.getNewPointerCount();
    }

    /**
     * Gets the number of pointers in the current gesture
     */
    public int getPointerCount() {
        return mDetector.getPointerCount();
    }

    /**
     * Gets the X coordinate of the pivot point
     */
    public float getPivotX() {
        return calcAverage(mDetector.getStartX(), mDetector.getPointerCount());
    }

    /**
     * Gets the Y coordinate of the pivot point
     */
    public float getPivotY() {
        return calcAverage(mDetector.getStartY(), mDetector.getPointerCount());
    }

    /**
     * Gets the X component of the translation
     */
    public float getTranslationX() {
        return (ListenerUtil.mutListener.listen(8676) ? (calcAverage(mDetector.getCurrentX(), mDetector.getPointerCount()) % calcAverage(mDetector.getStartX(), mDetector.getPointerCount())) : (ListenerUtil.mutListener.listen(8675) ? (calcAverage(mDetector.getCurrentX(), mDetector.getPointerCount()) / calcAverage(mDetector.getStartX(), mDetector.getPointerCount())) : (ListenerUtil.mutListener.listen(8674) ? (calcAverage(mDetector.getCurrentX(), mDetector.getPointerCount()) * calcAverage(mDetector.getStartX(), mDetector.getPointerCount())) : (ListenerUtil.mutListener.listen(8673) ? (calcAverage(mDetector.getCurrentX(), mDetector.getPointerCount()) + calcAverage(mDetector.getStartX(), mDetector.getPointerCount())) : (calcAverage(mDetector.getCurrentX(), mDetector.getPointerCount()) - calcAverage(mDetector.getStartX(), mDetector.getPointerCount()))))));
    }

    /**
     * Gets the Y component of the translation
     */
    public float getTranslationY() {
        return (ListenerUtil.mutListener.listen(8680) ? (calcAverage(mDetector.getCurrentY(), mDetector.getPointerCount()) % calcAverage(mDetector.getStartY(), mDetector.getPointerCount())) : (ListenerUtil.mutListener.listen(8679) ? (calcAverage(mDetector.getCurrentY(), mDetector.getPointerCount()) / calcAverage(mDetector.getStartY(), mDetector.getPointerCount())) : (ListenerUtil.mutListener.listen(8678) ? (calcAverage(mDetector.getCurrentY(), mDetector.getPointerCount()) * calcAverage(mDetector.getStartY(), mDetector.getPointerCount())) : (ListenerUtil.mutListener.listen(8677) ? (calcAverage(mDetector.getCurrentY(), mDetector.getPointerCount()) + calcAverage(mDetector.getStartY(), mDetector.getPointerCount())) : (calcAverage(mDetector.getCurrentY(), mDetector.getPointerCount()) - calcAverage(mDetector.getStartY(), mDetector.getPointerCount()))))));
    }

    /**
     * Gets the scale
     */
    public float getScale() {
        if ((ListenerUtil.mutListener.listen(8685) ? (mDetector.getPointerCount() >= 2) : (ListenerUtil.mutListener.listen(8684) ? (mDetector.getPointerCount() <= 2) : (ListenerUtil.mutListener.listen(8683) ? (mDetector.getPointerCount() > 2) : (ListenerUtil.mutListener.listen(8682) ? (mDetector.getPointerCount() != 2) : (ListenerUtil.mutListener.listen(8681) ? (mDetector.getPointerCount() == 2) : (mDetector.getPointerCount() < 2))))))) {
            return 1;
        } else {
            float startDeltaX = (ListenerUtil.mutListener.listen(8689) ? (mDetector.getStartX()[1] % mDetector.getStartX()[0]) : (ListenerUtil.mutListener.listen(8688) ? (mDetector.getStartX()[1] / mDetector.getStartX()[0]) : (ListenerUtil.mutListener.listen(8687) ? (mDetector.getStartX()[1] * mDetector.getStartX()[0]) : (ListenerUtil.mutListener.listen(8686) ? (mDetector.getStartX()[1] + mDetector.getStartX()[0]) : (mDetector.getStartX()[1] - mDetector.getStartX()[0])))));
            float startDeltaY = (ListenerUtil.mutListener.listen(8693) ? (mDetector.getStartY()[1] % mDetector.getStartY()[0]) : (ListenerUtil.mutListener.listen(8692) ? (mDetector.getStartY()[1] / mDetector.getStartY()[0]) : (ListenerUtil.mutListener.listen(8691) ? (mDetector.getStartY()[1] * mDetector.getStartY()[0]) : (ListenerUtil.mutListener.listen(8690) ? (mDetector.getStartY()[1] + mDetector.getStartY()[0]) : (mDetector.getStartY()[1] - mDetector.getStartY()[0])))));
            float currentDeltaX = (ListenerUtil.mutListener.listen(8697) ? (mDetector.getCurrentX()[1] % mDetector.getCurrentX()[0]) : (ListenerUtil.mutListener.listen(8696) ? (mDetector.getCurrentX()[1] / mDetector.getCurrentX()[0]) : (ListenerUtil.mutListener.listen(8695) ? (mDetector.getCurrentX()[1] * mDetector.getCurrentX()[0]) : (ListenerUtil.mutListener.listen(8694) ? (mDetector.getCurrentX()[1] + mDetector.getCurrentX()[0]) : (mDetector.getCurrentX()[1] - mDetector.getCurrentX()[0])))));
            float currentDeltaY = (ListenerUtil.mutListener.listen(8701) ? (mDetector.getCurrentY()[1] % mDetector.getCurrentY()[0]) : (ListenerUtil.mutListener.listen(8700) ? (mDetector.getCurrentY()[1] / mDetector.getCurrentY()[0]) : (ListenerUtil.mutListener.listen(8699) ? (mDetector.getCurrentY()[1] * mDetector.getCurrentY()[0]) : (ListenerUtil.mutListener.listen(8698) ? (mDetector.getCurrentY()[1] + mDetector.getCurrentY()[0]) : (mDetector.getCurrentY()[1] - mDetector.getCurrentY()[0])))));
            float startDist = (float) Math.hypot(startDeltaX, startDeltaY);
            float currentDist = (float) Math.hypot(currentDeltaX, currentDeltaY);
            return (ListenerUtil.mutListener.listen(8705) ? (currentDist % startDist) : (ListenerUtil.mutListener.listen(8704) ? (currentDist * startDist) : (ListenerUtil.mutListener.listen(8703) ? (currentDist - startDist) : (ListenerUtil.mutListener.listen(8702) ? (currentDist + startDist) : (currentDist / startDist)))));
        }
    }

    /**
     * Gets the rotation in radians
     */
    public float getRotation() {
        if ((ListenerUtil.mutListener.listen(8710) ? (mDetector.getPointerCount() >= 2) : (ListenerUtil.mutListener.listen(8709) ? (mDetector.getPointerCount() <= 2) : (ListenerUtil.mutListener.listen(8708) ? (mDetector.getPointerCount() > 2) : (ListenerUtil.mutListener.listen(8707) ? (mDetector.getPointerCount() != 2) : (ListenerUtil.mutListener.listen(8706) ? (mDetector.getPointerCount() == 2) : (mDetector.getPointerCount() < 2))))))) {
            return 0;
        } else {
            float startDeltaX = (ListenerUtil.mutListener.listen(8714) ? (mDetector.getStartX()[1] % mDetector.getStartX()[0]) : (ListenerUtil.mutListener.listen(8713) ? (mDetector.getStartX()[1] / mDetector.getStartX()[0]) : (ListenerUtil.mutListener.listen(8712) ? (mDetector.getStartX()[1] * mDetector.getStartX()[0]) : (ListenerUtil.mutListener.listen(8711) ? (mDetector.getStartX()[1] + mDetector.getStartX()[0]) : (mDetector.getStartX()[1] - mDetector.getStartX()[0])))));
            float startDeltaY = (ListenerUtil.mutListener.listen(8718) ? (mDetector.getStartY()[1] % mDetector.getStartY()[0]) : (ListenerUtil.mutListener.listen(8717) ? (mDetector.getStartY()[1] / mDetector.getStartY()[0]) : (ListenerUtil.mutListener.listen(8716) ? (mDetector.getStartY()[1] * mDetector.getStartY()[0]) : (ListenerUtil.mutListener.listen(8715) ? (mDetector.getStartY()[1] + mDetector.getStartY()[0]) : (mDetector.getStartY()[1] - mDetector.getStartY()[0])))));
            float currentDeltaX = (ListenerUtil.mutListener.listen(8722) ? (mDetector.getCurrentX()[1] % mDetector.getCurrentX()[0]) : (ListenerUtil.mutListener.listen(8721) ? (mDetector.getCurrentX()[1] / mDetector.getCurrentX()[0]) : (ListenerUtil.mutListener.listen(8720) ? (mDetector.getCurrentX()[1] * mDetector.getCurrentX()[0]) : (ListenerUtil.mutListener.listen(8719) ? (mDetector.getCurrentX()[1] + mDetector.getCurrentX()[0]) : (mDetector.getCurrentX()[1] - mDetector.getCurrentX()[0])))));
            float currentDeltaY = (ListenerUtil.mutListener.listen(8726) ? (mDetector.getCurrentY()[1] % mDetector.getCurrentY()[0]) : (ListenerUtil.mutListener.listen(8725) ? (mDetector.getCurrentY()[1] / mDetector.getCurrentY()[0]) : (ListenerUtil.mutListener.listen(8724) ? (mDetector.getCurrentY()[1] * mDetector.getCurrentY()[0]) : (ListenerUtil.mutListener.listen(8723) ? (mDetector.getCurrentY()[1] + mDetector.getCurrentY()[0]) : (mDetector.getCurrentY()[1] - mDetector.getCurrentY()[0])))));
            float startAngle = (float) Math.atan2(startDeltaY, startDeltaX);
            float currentAngle = (float) Math.atan2(currentDeltaY, currentDeltaX);
            return (ListenerUtil.mutListener.listen(8730) ? (currentAngle % startAngle) : (ListenerUtil.mutListener.listen(8729) ? (currentAngle / startAngle) : (ListenerUtil.mutListener.listen(8728) ? (currentAngle * startAngle) : (ListenerUtil.mutListener.listen(8727) ? (currentAngle + startAngle) : (currentAngle - startAngle)))));
        }
    }
}

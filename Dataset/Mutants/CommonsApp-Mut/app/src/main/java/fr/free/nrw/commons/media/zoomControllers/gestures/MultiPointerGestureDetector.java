package fr.free.nrw.commons.media.zoomControllers.gestures;

import android.view.MotionEvent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Component that detects and tracks multiple pointers based on touch events.
 *
 * Each time a pointer gets pressed or released, the current gesture (if any) will end, and a new
 * one will be started (if there are still pressed pointers left). It is guaranteed that the number
 * of pointers within the single gesture will remain the same during the whole gesture.
 */
public class MultiPointerGestureDetector {

    /**
     * The listener for receiving notifications when gestures occur.
     */
    public interface Listener {

        /**
         * A callback called right before the gesture is about to start.
         */
        public void onGestureBegin(MultiPointerGestureDetector detector);

        /**
         * A callback called each time the gesture gets updated.
         */
        public void onGestureUpdate(MultiPointerGestureDetector detector);

        /**
         * A callback called right after the gesture has finished.
         */
        public void onGestureEnd(MultiPointerGestureDetector detector);
    }

    private static final int MAX_POINTERS = 2;

    private boolean mGestureInProgress;

    private int mPointerCount;

    private int mNewPointerCount;

    private final int[] mId = new int[MAX_POINTERS];

    private final float[] mStartX = new float[MAX_POINTERS];

    private final float[] mStartY = new float[MAX_POINTERS];

    private final float[] mCurrentX = new float[MAX_POINTERS];

    private final float[] mCurrentY = new float[MAX_POINTERS];

    private Listener mListener = null;

    public MultiPointerGestureDetector() {
        if (!ListenerUtil.mutListener.listen(8540)) {
            reset();
        }
    }

    /**
     * Factory method that creates a new instance of MultiPointerGestureDetector
     */
    public static MultiPointerGestureDetector newInstance() {
        return new MultiPointerGestureDetector();
    }

    /**
     * Sets the listener.
     *
     * @param listener listener to set
     */
    public void setListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(8541)) {
            mListener = listener;
        }
    }

    /**
     * Resets the component to the initial state.
     */
    public void reset() {
        if (!ListenerUtil.mutListener.listen(8542)) {
            mGestureInProgress = false;
        }
        if (!ListenerUtil.mutListener.listen(8543)) {
            mPointerCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(8550)) {
            {
                long _loopCounter141 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8549) ? (i >= MAX_POINTERS) : (ListenerUtil.mutListener.listen(8548) ? (i <= MAX_POINTERS) : (ListenerUtil.mutListener.listen(8547) ? (i > MAX_POINTERS) : (ListenerUtil.mutListener.listen(8546) ? (i != MAX_POINTERS) : (ListenerUtil.mutListener.listen(8545) ? (i == MAX_POINTERS) : (i < MAX_POINTERS)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter141", ++_loopCounter141);
                    if (!ListenerUtil.mutListener.listen(8544)) {
                        mId[i] = MotionEvent.INVALID_POINTER_ID;
                    }
                }
            }
        }
    }

    /**
     * This method can be overridden in order to perform threshold check or something similar.
     *
     * @return whether or not to start a new gesture
     */
    protected boolean shouldStartGesture() {
        return true;
    }

    /**
     * Starts a new gesture and calls the listener just before starting it.
     */
    private void startGesture() {
        if (!ListenerUtil.mutListener.listen(8554)) {
            if (!mGestureInProgress) {
                if (!ListenerUtil.mutListener.listen(8552)) {
                    if (mListener != null) {
                        if (!ListenerUtil.mutListener.listen(8551)) {
                            mListener.onGestureBegin(this);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8553)) {
                    mGestureInProgress = true;
                }
            }
        }
    }

    /**
     * Stops the current gesture and calls the listener right after stopping it.
     */
    private void stopGesture() {
        if (!ListenerUtil.mutListener.listen(8558)) {
            if (mGestureInProgress) {
                if (!ListenerUtil.mutListener.listen(8555)) {
                    mGestureInProgress = false;
                }
                if (!ListenerUtil.mutListener.listen(8557)) {
                    if (mListener != null) {
                        if (!ListenerUtil.mutListener.listen(8556)) {
                            mListener.onGestureEnd(this);
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the index of the i-th pressed pointer. Normally, the index will be equal to i, except in
     * the case when the pointer is released.
     *
     * @return index of the specified pointer or -1 if not found (i.e. not enough pointers are down)
     */
    private int getPressedPointerIndex(MotionEvent event, int i) {
        final int count = event.getPointerCount();
        final int action = event.getActionMasked();
        final int index = event.getActionIndex();
        if (!ListenerUtil.mutListener.listen(8567)) {
            if ((ListenerUtil.mutListener.listen(8559) ? (action == MotionEvent.ACTION_UP && action == MotionEvent.ACTION_POINTER_UP) : (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP))) {
                if (!ListenerUtil.mutListener.listen(8566)) {
                    if ((ListenerUtil.mutListener.listen(8564) ? (i <= index) : (ListenerUtil.mutListener.listen(8563) ? (i > index) : (ListenerUtil.mutListener.listen(8562) ? (i < index) : (ListenerUtil.mutListener.listen(8561) ? (i != index) : (ListenerUtil.mutListener.listen(8560) ? (i == index) : (i >= index))))))) {
                        if (!ListenerUtil.mutListener.listen(8565)) {
                            i++;
                        }
                    }
                }
            }
        }
        return ((ListenerUtil.mutListener.listen(8572) ? (i >= count) : (ListenerUtil.mutListener.listen(8571) ? (i <= count) : (ListenerUtil.mutListener.listen(8570) ? (i > count) : (ListenerUtil.mutListener.listen(8569) ? (i != count) : (ListenerUtil.mutListener.listen(8568) ? (i == count) : (i < count))))))) ? i : -1;
    }

    /**
     * Gets the number of pressed pointers (fingers down).
     */
    private static int getPressedPointerCount(MotionEvent event) {
        int count = event.getPointerCount();
        int action = event.getActionMasked();
        if (!ListenerUtil.mutListener.listen(8575)) {
            if ((ListenerUtil.mutListener.listen(8573) ? (action == MotionEvent.ACTION_UP && action == MotionEvent.ACTION_POINTER_UP) : (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP))) {
                if (!ListenerUtil.mutListener.listen(8574)) {
                    count--;
                }
            }
        }
        return count;
    }

    private void updatePointersOnTap(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(8576)) {
            mPointerCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(8593)) {
            {
                long _loopCounter142 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8592) ? (i >= MAX_POINTERS) : (ListenerUtil.mutListener.listen(8591) ? (i <= MAX_POINTERS) : (ListenerUtil.mutListener.listen(8590) ? (i > MAX_POINTERS) : (ListenerUtil.mutListener.listen(8589) ? (i != MAX_POINTERS) : (ListenerUtil.mutListener.listen(8588) ? (i == MAX_POINTERS) : (i < MAX_POINTERS)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter142", ++_loopCounter142);
                    int index = getPressedPointerIndex(event, i);
                    if (!ListenerUtil.mutListener.listen(8587)) {
                        if ((ListenerUtil.mutListener.listen(8581) ? (index >= -1) : (ListenerUtil.mutListener.listen(8580) ? (index <= -1) : (ListenerUtil.mutListener.listen(8579) ? (index > -1) : (ListenerUtil.mutListener.listen(8578) ? (index < -1) : (ListenerUtil.mutListener.listen(8577) ? (index != -1) : (index == -1))))))) {
                            if (!ListenerUtil.mutListener.listen(8586)) {
                                mId[i] = MotionEvent.INVALID_POINTER_ID;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8582)) {
                                mId[i] = event.getPointerId(index);
                            }
                            if (!ListenerUtil.mutListener.listen(8583)) {
                                mCurrentX[i] = mStartX[i] = event.getX(index);
                            }
                            if (!ListenerUtil.mutListener.listen(8584)) {
                                mCurrentY[i] = mStartY[i] = event.getY(index);
                            }
                            if (!ListenerUtil.mutListener.listen(8585)) {
                                mPointerCount++;
                            }
                        }
                    }
                }
            }
        }
    }

    private void updatePointersOnMove(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(8607)) {
            {
                long _loopCounter143 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8606) ? (i >= MAX_POINTERS) : (ListenerUtil.mutListener.listen(8605) ? (i <= MAX_POINTERS) : (ListenerUtil.mutListener.listen(8604) ? (i > MAX_POINTERS) : (ListenerUtil.mutListener.listen(8603) ? (i != MAX_POINTERS) : (ListenerUtil.mutListener.listen(8602) ? (i == MAX_POINTERS) : (i < MAX_POINTERS)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter143", ++_loopCounter143);
                    int index = event.findPointerIndex(mId[i]);
                    if (!ListenerUtil.mutListener.listen(8601)) {
                        if ((ListenerUtil.mutListener.listen(8598) ? (index >= -1) : (ListenerUtil.mutListener.listen(8597) ? (index <= -1) : (ListenerUtil.mutListener.listen(8596) ? (index > -1) : (ListenerUtil.mutListener.listen(8595) ? (index < -1) : (ListenerUtil.mutListener.listen(8594) ? (index == -1) : (index != -1))))))) {
                            if (!ListenerUtil.mutListener.listen(8599)) {
                                mCurrentX[i] = event.getX(index);
                            }
                            if (!ListenerUtil.mutListener.listen(8600)) {
                                mCurrentY[i] = event.getY(index);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Handles the given motion event.
     *
     * @param event event to handle
     * @return whether or not the event was handled
     */
    public boolean onTouchEvent(final MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(8635)) {
            switch(event.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:
                    {
                        if (!ListenerUtil.mutListener.listen(8608)) {
                            // update pointers
                            updatePointersOnMove(event);
                        }
                        if (!ListenerUtil.mutListener.listen(8617)) {
                            // start a new gesture if not already started
                            if ((ListenerUtil.mutListener.listen(8615) ? ((ListenerUtil.mutListener.listen(8614) ? (!mGestureInProgress || (ListenerUtil.mutListener.listen(8613) ? (mPointerCount >= 0) : (ListenerUtil.mutListener.listen(8612) ? (mPointerCount <= 0) : (ListenerUtil.mutListener.listen(8611) ? (mPointerCount < 0) : (ListenerUtil.mutListener.listen(8610) ? (mPointerCount != 0) : (ListenerUtil.mutListener.listen(8609) ? (mPointerCount == 0) : (mPointerCount > 0))))))) : (!mGestureInProgress && (ListenerUtil.mutListener.listen(8613) ? (mPointerCount >= 0) : (ListenerUtil.mutListener.listen(8612) ? (mPointerCount <= 0) : (ListenerUtil.mutListener.listen(8611) ? (mPointerCount < 0) : (ListenerUtil.mutListener.listen(8610) ? (mPointerCount != 0) : (ListenerUtil.mutListener.listen(8609) ? (mPointerCount == 0) : (mPointerCount > 0)))))))) || shouldStartGesture()) : ((ListenerUtil.mutListener.listen(8614) ? (!mGestureInProgress || (ListenerUtil.mutListener.listen(8613) ? (mPointerCount >= 0) : (ListenerUtil.mutListener.listen(8612) ? (mPointerCount <= 0) : (ListenerUtil.mutListener.listen(8611) ? (mPointerCount < 0) : (ListenerUtil.mutListener.listen(8610) ? (mPointerCount != 0) : (ListenerUtil.mutListener.listen(8609) ? (mPointerCount == 0) : (mPointerCount > 0))))))) : (!mGestureInProgress && (ListenerUtil.mutListener.listen(8613) ? (mPointerCount >= 0) : (ListenerUtil.mutListener.listen(8612) ? (mPointerCount <= 0) : (ListenerUtil.mutListener.listen(8611) ? (mPointerCount < 0) : (ListenerUtil.mutListener.listen(8610) ? (mPointerCount != 0) : (ListenerUtil.mutListener.listen(8609) ? (mPointerCount == 0) : (mPointerCount > 0)))))))) && shouldStartGesture()))) {
                                if (!ListenerUtil.mutListener.listen(8616)) {
                                    startGesture();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(8620)) {
                            // notify listener
                            if ((ListenerUtil.mutListener.listen(8618) ? (mGestureInProgress || mListener != null) : (mGestureInProgress && mListener != null))) {
                                if (!ListenerUtil.mutListener.listen(8619)) {
                                    mListener.onGestureUpdate(this);
                                }
                            }
                        }
                        break;
                    }
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_UP:
                    {
                        if (!ListenerUtil.mutListener.listen(8621)) {
                            // restart gesture whenever the number of pointers changes
                            mNewPointerCount = getPressedPointerCount(event);
                        }
                        if (!ListenerUtil.mutListener.listen(8622)) {
                            stopGesture();
                        }
                        if (!ListenerUtil.mutListener.listen(8623)) {
                            updatePointersOnTap(event);
                        }
                        if (!ListenerUtil.mutListener.listen(8631)) {
                            if ((ListenerUtil.mutListener.listen(8629) ? ((ListenerUtil.mutListener.listen(8628) ? (mPointerCount >= 0) : (ListenerUtil.mutListener.listen(8627) ? (mPointerCount <= 0) : (ListenerUtil.mutListener.listen(8626) ? (mPointerCount < 0) : (ListenerUtil.mutListener.listen(8625) ? (mPointerCount != 0) : (ListenerUtil.mutListener.listen(8624) ? (mPointerCount == 0) : (mPointerCount > 0)))))) || shouldStartGesture()) : ((ListenerUtil.mutListener.listen(8628) ? (mPointerCount >= 0) : (ListenerUtil.mutListener.listen(8627) ? (mPointerCount <= 0) : (ListenerUtil.mutListener.listen(8626) ? (mPointerCount < 0) : (ListenerUtil.mutListener.listen(8625) ? (mPointerCount != 0) : (ListenerUtil.mutListener.listen(8624) ? (mPointerCount == 0) : (mPointerCount > 0)))))) && shouldStartGesture()))) {
                                if (!ListenerUtil.mutListener.listen(8630)) {
                                    startGesture();
                                }
                            }
                        }
                        break;
                    }
                case MotionEvent.ACTION_CANCEL:
                    {
                        if (!ListenerUtil.mutListener.listen(8632)) {
                            mNewPointerCount = 0;
                        }
                        if (!ListenerUtil.mutListener.listen(8633)) {
                            stopGesture();
                        }
                        if (!ListenerUtil.mutListener.listen(8634)) {
                            reset();
                        }
                        break;
                    }
            }
        }
        return true;
    }

    /**
     * Restarts the current gesture (if any).
     */
    public void restartGesture() {
        if (!ListenerUtil.mutListener.listen(8636)) {
            if (!mGestureInProgress) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8637)) {
            stopGesture();
        }
        if (!ListenerUtil.mutListener.listen(8645)) {
            {
                long _loopCounter144 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8644) ? (i >= MAX_POINTERS) : (ListenerUtil.mutListener.listen(8643) ? (i <= MAX_POINTERS) : (ListenerUtil.mutListener.listen(8642) ? (i > MAX_POINTERS) : (ListenerUtil.mutListener.listen(8641) ? (i != MAX_POINTERS) : (ListenerUtil.mutListener.listen(8640) ? (i == MAX_POINTERS) : (i < MAX_POINTERS)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter144", ++_loopCounter144);
                    if (!ListenerUtil.mutListener.listen(8638)) {
                        mStartX[i] = mCurrentX[i];
                    }
                    if (!ListenerUtil.mutListener.listen(8639)) {
                        mStartY[i] = mCurrentY[i];
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8646)) {
            startGesture();
        }
    }

    /**
     * Gets whether there is a gesture in progress
     */
    public boolean isGestureInProgress() {
        return mGestureInProgress;
    }

    /**
     * Gets the number of pointers after the current gesture
     */
    public int getNewPointerCount() {
        return mNewPointerCount;
    }

    /**
     * Gets the number of pointers in the current gesture
     */
    public int getPointerCount() {
        return mPointerCount;
    }

    /**
     * Gets the start X coordinates for the all pointers Mutable array is exposed for performance
     * reasons and is not to be modified by the callers.
     */
    public float[] getStartX() {
        return mStartX;
    }

    /**
     * Gets the start Y coordinates for the all pointers Mutable array is exposed for performance
     * reasons and is not to be modified by the callers.
     */
    public float[] getStartY() {
        return mStartY;
    }

    /**
     * Gets the current X coordinates for the all pointers Mutable array is exposed for performance
     * reasons and is not to be modified by the callers.
     */
    public float[] getCurrentX() {
        return mCurrentX;
    }

    /**
     * Gets the current Y coordinates for the all pointers Mutable array is exposed for performance
     * reasons and is not to be modified by the callers.
     */
    public float[] getCurrentY() {
        return mCurrentY;
    }
}

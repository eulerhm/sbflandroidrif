package fr.free.nrw.commons.media.zoomControllers.zoomable;

import android.graphics.Matrix;
import android.graphics.PointF;
import com.facebook.common.logging.FLog;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.media.zoomControllers.gestures.TransformGestureDetector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Abstract class for ZoomableController that adds animation capabilities to
 * DefaultZoomableController.
 */
public abstract class AbstractAnimatedZoomableController extends DefaultZoomableController {

    private boolean mIsAnimating;

    private final float[] mStartValues = new float[9];

    private final float[] mStopValues = new float[9];

    private final float[] mCurrentValues = new float[9];

    private final Matrix mNewTransform = new Matrix();

    private final Matrix mWorkingTransform = new Matrix();

    public AbstractAnimatedZoomableController(TransformGestureDetector transformGestureDetector) {
        super(transformGestureDetector);
    }

    @Override
    public void reset() {
        if (!ListenerUtil.mutListener.listen(8344)) {
            FLog.v(getLogTag(), "reset");
        }
        if (!ListenerUtil.mutListener.listen(8345)) {
            stopAnimation();
        }
        if (!ListenerUtil.mutListener.listen(8346)) {
            mWorkingTransform.reset();
        }
        if (!ListenerUtil.mutListener.listen(8347)) {
            mNewTransform.reset();
        }
        if (!ListenerUtil.mutListener.listen(8348)) {
            super.reset();
        }
    }

    /**
     * Returns true if the zoomable transform is identity matrix, and the controller is idle.
     */
    @Override
    public boolean isIdentity() {
        return (ListenerUtil.mutListener.listen(8349) ? (!isAnimating() || super.isIdentity()) : (!isAnimating() && super.isIdentity()));
    }

    /**
     * Zooms to the desired scale and positions the image so that the given image point corresponds to
     * the given view point.
     *
     * <p>If this method is called while an animation or gesture is already in progress, the current
     * animation or gesture will be stopped first.
     *
     * @param scale desired scale, will be limited to {min, max} scale factor
     * @param imagePoint 2D point in image's relative coordinate system (i.e. 0 <= x, y <= 1)
     * @param viewPoint 2D point in view's absolute coordinate system
     */
    @Override
    public void zoomToPoint(float scale, PointF imagePoint, PointF viewPoint) {
        if (!ListenerUtil.mutListener.listen(8350)) {
            zoomToPoint(scale, imagePoint, viewPoint, LIMIT_ALL, 0, null);
        }
    }

    /**
     * Zooms to the desired scale and positions the image so that the given image point corresponds to
     * the given view point.
     *
     * <p>If this method is called while an animation or gesture is already in progress, the current
     * animation or gesture will be stopped first.
     *
     * @param scale desired scale, will be limited to {min, max} scale factor
     * @param imagePoint 2D point in image's relative coordinate system (i.e. 0 <= x, y <= 1)
     * @param viewPoint 2D point in view's absolute coordinate system
     * @param limitFlags whether to limit translation and/or scale.
     * @param durationMs length of animation of the zoom, or 0 if no animation desired
     * @param onAnimationComplete code to run when the animation completes. Ignored if durationMs=0
     */
    public void zoomToPoint(float scale, PointF imagePoint, PointF viewPoint, @LimitFlag int limitFlags, long durationMs, @Nullable Runnable onAnimationComplete) {
        if (!ListenerUtil.mutListener.listen(8351)) {
            FLog.v(getLogTag(), "zoomToPoint: duration %d ms", durationMs);
        }
        if (!ListenerUtil.mutListener.listen(8352)) {
            calculateZoomToPointTransform(mNewTransform, scale, imagePoint, viewPoint, limitFlags);
        }
        if (!ListenerUtil.mutListener.listen(8353)) {
            setTransform(mNewTransform, durationMs, onAnimationComplete);
        }
    }

    /**
     * Sets a new zoomable transformation and animates to it if desired.
     *
     * <p>If this method is called while an animation or gesture is already in progress, the current
     * animation or gesture will be stopped first.
     *
     * @param newTransform new transform to make active
     * @param durationMs duration of the animation, or 0 to not animate
     * @param onAnimationComplete code to run when the animation completes. Ignored if durationMs=0
     */
    public void setTransform(Matrix newTransform, long durationMs, @Nullable Runnable onAnimationComplete) {
        if (!ListenerUtil.mutListener.listen(8354)) {
            FLog.v(getLogTag(), "setTransform: duration %d ms", durationMs);
        }
        if (!ListenerUtil.mutListener.listen(8362)) {
            if ((ListenerUtil.mutListener.listen(8359) ? (durationMs >= 0) : (ListenerUtil.mutListener.listen(8358) ? (durationMs > 0) : (ListenerUtil.mutListener.listen(8357) ? (durationMs < 0) : (ListenerUtil.mutListener.listen(8356) ? (durationMs != 0) : (ListenerUtil.mutListener.listen(8355) ? (durationMs == 0) : (durationMs <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(8361)) {
                    setTransformImmediate(newTransform);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8360)) {
                    setTransformAnimated(newTransform, durationMs, onAnimationComplete);
                }
            }
        }
    }

    private void setTransformImmediate(final Matrix newTransform) {
        if (!ListenerUtil.mutListener.listen(8363)) {
            FLog.v(getLogTag(), "setTransformImmediate");
        }
        if (!ListenerUtil.mutListener.listen(8364)) {
            stopAnimation();
        }
        if (!ListenerUtil.mutListener.listen(8365)) {
            mWorkingTransform.set(newTransform);
        }
        if (!ListenerUtil.mutListener.listen(8366)) {
            super.setTransform(newTransform);
        }
        if (!ListenerUtil.mutListener.listen(8367)) {
            getDetector().restartGesture();
        }
    }

    protected boolean isAnimating() {
        return mIsAnimating;
    }

    protected void setAnimating(boolean isAnimating) {
        if (!ListenerUtil.mutListener.listen(8368)) {
            mIsAnimating = isAnimating;
        }
    }

    protected float[] getStartValues() {
        return mStartValues;
    }

    protected float[] getStopValues() {
        return mStopValues;
    }

    protected Matrix getWorkingTransform() {
        return mWorkingTransform;
    }

    @Override
    public void onGestureBegin(TransformGestureDetector detector) {
        if (!ListenerUtil.mutListener.listen(8369)) {
            FLog.v(getLogTag(), "onGestureBegin");
        }
        if (!ListenerUtil.mutListener.listen(8370)) {
            stopAnimation();
        }
        if (!ListenerUtil.mutListener.listen(8371)) {
            super.onGestureBegin(detector);
        }
    }

    @Override
    public void onGestureUpdate(TransformGestureDetector detector) {
        if (!ListenerUtil.mutListener.listen(8372)) {
            FLog.v(getLogTag(), "onGestureUpdate %s", isAnimating() ? "(ignored)" : "");
        }
        if (!ListenerUtil.mutListener.listen(8373)) {
            if (isAnimating()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8374)) {
            super.onGestureUpdate(detector);
        }
    }

    protected void calculateInterpolation(Matrix outMatrix, float fraction) {
        if (!ListenerUtil.mutListener.listen(8397)) {
            {
                long _loopCounter130 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8396) ? (i >= 9) : (ListenerUtil.mutListener.listen(8395) ? (i <= 9) : (ListenerUtil.mutListener.listen(8394) ? (i > 9) : (ListenerUtil.mutListener.listen(8393) ? (i != 9) : (ListenerUtil.mutListener.listen(8392) ? (i == 9) : (i < 9)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter130", ++_loopCounter130);
                    if (!ListenerUtil.mutListener.listen(8391)) {
                        mCurrentValues[i] = (ListenerUtil.mutListener.listen(8390) ? ((ListenerUtil.mutListener.listen(8382) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) % mStartValues[i]) : (ListenerUtil.mutListener.listen(8381) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) / mStartValues[i]) : (ListenerUtil.mutListener.listen(8380) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) - mStartValues[i]) : (ListenerUtil.mutListener.listen(8379) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) + mStartValues[i]) : (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) * mStartValues[i]))))) % (ListenerUtil.mutListener.listen(8386) ? (fraction % mStopValues[i]) : (ListenerUtil.mutListener.listen(8385) ? (fraction / mStopValues[i]) : (ListenerUtil.mutListener.listen(8384) ? (fraction - mStopValues[i]) : (ListenerUtil.mutListener.listen(8383) ? (fraction + mStopValues[i]) : (fraction * mStopValues[i])))))) : (ListenerUtil.mutListener.listen(8389) ? ((ListenerUtil.mutListener.listen(8382) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) % mStartValues[i]) : (ListenerUtil.mutListener.listen(8381) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) / mStartValues[i]) : (ListenerUtil.mutListener.listen(8380) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) - mStartValues[i]) : (ListenerUtil.mutListener.listen(8379) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) + mStartValues[i]) : (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) * mStartValues[i]))))) / (ListenerUtil.mutListener.listen(8386) ? (fraction % mStopValues[i]) : (ListenerUtil.mutListener.listen(8385) ? (fraction / mStopValues[i]) : (ListenerUtil.mutListener.listen(8384) ? (fraction - mStopValues[i]) : (ListenerUtil.mutListener.listen(8383) ? (fraction + mStopValues[i]) : (fraction * mStopValues[i])))))) : (ListenerUtil.mutListener.listen(8388) ? ((ListenerUtil.mutListener.listen(8382) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) % mStartValues[i]) : (ListenerUtil.mutListener.listen(8381) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) / mStartValues[i]) : (ListenerUtil.mutListener.listen(8380) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) - mStartValues[i]) : (ListenerUtil.mutListener.listen(8379) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) + mStartValues[i]) : (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) * mStartValues[i]))))) * (ListenerUtil.mutListener.listen(8386) ? (fraction % mStopValues[i]) : (ListenerUtil.mutListener.listen(8385) ? (fraction / mStopValues[i]) : (ListenerUtil.mutListener.listen(8384) ? (fraction - mStopValues[i]) : (ListenerUtil.mutListener.listen(8383) ? (fraction + mStopValues[i]) : (fraction * mStopValues[i])))))) : (ListenerUtil.mutListener.listen(8387) ? ((ListenerUtil.mutListener.listen(8382) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) % mStartValues[i]) : (ListenerUtil.mutListener.listen(8381) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) / mStartValues[i]) : (ListenerUtil.mutListener.listen(8380) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) - mStartValues[i]) : (ListenerUtil.mutListener.listen(8379) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) + mStartValues[i]) : (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) * mStartValues[i]))))) - (ListenerUtil.mutListener.listen(8386) ? (fraction % mStopValues[i]) : (ListenerUtil.mutListener.listen(8385) ? (fraction / mStopValues[i]) : (ListenerUtil.mutListener.listen(8384) ? (fraction - mStopValues[i]) : (ListenerUtil.mutListener.listen(8383) ? (fraction + mStopValues[i]) : (fraction * mStopValues[i])))))) : ((ListenerUtil.mutListener.listen(8382) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) % mStartValues[i]) : (ListenerUtil.mutListener.listen(8381) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) / mStartValues[i]) : (ListenerUtil.mutListener.listen(8380) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) - mStartValues[i]) : (ListenerUtil.mutListener.listen(8379) ? (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) + mStartValues[i]) : (((ListenerUtil.mutListener.listen(8378) ? (1 % fraction) : (ListenerUtil.mutListener.listen(8377) ? (1 / fraction) : (ListenerUtil.mutListener.listen(8376) ? (1 * fraction) : (ListenerUtil.mutListener.listen(8375) ? (1 + fraction) : (1 - fraction)))))) * mStartValues[i]))))) + (ListenerUtil.mutListener.listen(8386) ? (fraction % mStopValues[i]) : (ListenerUtil.mutListener.listen(8385) ? (fraction / mStopValues[i]) : (ListenerUtil.mutListener.listen(8384) ? (fraction - mStopValues[i]) : (ListenerUtil.mutListener.listen(8383) ? (fraction + mStopValues[i]) : (fraction * mStopValues[i]))))))))));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8398)) {
            outMatrix.setValues(mCurrentValues);
        }
    }

    public abstract void setTransformAnimated(final Matrix newTransform, long durationMs, @Nullable final Runnable onAnimationComplete);

    protected abstract void stopAnimation();

    protected abstract Class<?> getLogTag();
}

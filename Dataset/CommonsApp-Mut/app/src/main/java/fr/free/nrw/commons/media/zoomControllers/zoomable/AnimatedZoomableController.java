package fr.free.nrw.commons.media.zoomControllers.zoomable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.animation.DecelerateInterpolator;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.media.zoomControllers.gestures.TransformGestureDetector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * ZoomableController that adds animation capabilities to DefaultZoomableController using standard
 * Android animation classes
 */
public class AnimatedZoomableController extends AbstractAnimatedZoomableController {

    private static final Class<?> TAG = AnimatedZoomableController.class;

    private final ValueAnimator mValueAnimator;

    public static AnimatedZoomableController newInstance() {
        return new AnimatedZoomableController(TransformGestureDetector.newInstance());
    }

    @SuppressLint("NewApi")
    public AnimatedZoomableController(TransformGestureDetector transformGestureDetector) {
        super(transformGestureDetector);
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        if (!ListenerUtil.mutListener.listen(8220)) {
            mValueAnimator.setInterpolator(new DecelerateInterpolator());
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void setTransformAnimated(final Matrix newTransform, long durationMs, @Nullable final Runnable onAnimationComplete) {
        if (!ListenerUtil.mutListener.listen(8221)) {
            FLog.v(getLogTag(), "setTransformAnimated: duration %d ms", durationMs);
        }
        if (!ListenerUtil.mutListener.listen(8222)) {
            stopAnimation();
        }
        if (!ListenerUtil.mutListener.listen(8228)) {
            Preconditions.checkArgument((ListenerUtil.mutListener.listen(8227) ? (durationMs >= 0) : (ListenerUtil.mutListener.listen(8226) ? (durationMs <= 0) : (ListenerUtil.mutListener.listen(8225) ? (durationMs < 0) : (ListenerUtil.mutListener.listen(8224) ? (durationMs != 0) : (ListenerUtil.mutListener.listen(8223) ? (durationMs == 0) : (durationMs > 0)))))));
        }
        if (!ListenerUtil.mutListener.listen(8229)) {
            Preconditions.checkState(!isAnimating());
        }
        if (!ListenerUtil.mutListener.listen(8230)) {
            setAnimating(true);
        }
        if (!ListenerUtil.mutListener.listen(8231)) {
            mValueAnimator.setDuration(durationMs);
        }
        if (!ListenerUtil.mutListener.listen(8232)) {
            getTransform().getValues(getStartValues());
        }
        if (!ListenerUtil.mutListener.listen(8233)) {
            newTransform.getValues(getStopValues());
        }
        if (!ListenerUtil.mutListener.listen(8236)) {
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (!ListenerUtil.mutListener.listen(8234)) {
                        calculateInterpolation(getWorkingTransform(), (float) valueAnimator.getAnimatedValue());
                    }
                    if (!ListenerUtil.mutListener.listen(8235)) {
                        AnimatedZoomableController.super.setTransform(getWorkingTransform());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8245)) {
            mValueAnimator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationCancel(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(8237)) {
                        FLog.v(getLogTag(), "setTransformAnimated: animation cancelled");
                    }
                    if (!ListenerUtil.mutListener.listen(8238)) {
                        onAnimationStopped();
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(8239)) {
                        FLog.v(getLogTag(), "setTransformAnimated: animation finished");
                    }
                    if (!ListenerUtil.mutListener.listen(8240)) {
                        onAnimationStopped();
                    }
                }

                private void onAnimationStopped() {
                    if (!ListenerUtil.mutListener.listen(8242)) {
                        if (onAnimationComplete != null) {
                            if (!ListenerUtil.mutListener.listen(8241)) {
                                onAnimationComplete.run();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8243)) {
                        setAnimating(false);
                    }
                    if (!ListenerUtil.mutListener.listen(8244)) {
                        getDetector().restartGesture();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8246)) {
            mValueAnimator.start();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void stopAnimation() {
        if (!ListenerUtil.mutListener.listen(8247)) {
            if (!isAnimating()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8248)) {
            FLog.v(getLogTag(), "stopAnimation");
        }
        if (!ListenerUtil.mutListener.listen(8249)) {
            mValueAnimator.cancel();
        }
        if (!ListenerUtil.mutListener.listen(8250)) {
            mValueAnimator.removeAllUpdateListeners();
        }
        if (!ListenerUtil.mutListener.listen(8251)) {
            mValueAnimator.removeAllListeners();
        }
    }

    @Override
    protected Class<?> getLogTag() {
        return TAG;
    }
}

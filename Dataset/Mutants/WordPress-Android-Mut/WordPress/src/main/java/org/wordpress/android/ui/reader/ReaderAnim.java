package org.wordpress.android.ui.reader;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import org.wordpress.android.util.AniUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderAnim {

    /*
     * animation when user taps a like button
     */
    private enum ReaderButton {

        LIKE_ON, LIKE_OFF
    }

    public static void animateLikeButton(final View target, boolean isAskingToLike) {
        if (!ListenerUtil.mutListener.listen(20448)) {
            animateButton(target, isAskingToLike ? ReaderButton.LIKE_ON : ReaderButton.LIKE_OFF);
        }
    }

    private static void animateButton(final View target, ReaderButton button) {
        if (!ListenerUtil.mutListener.listen(20450)) {
            if ((ListenerUtil.mutListener.listen(20449) ? (target == null && button == null) : (target == null || button == null))) {
                return;
            }
        }
        ObjectAnimator animX = ObjectAnimator.ofFloat(target, View.SCALE_X, 1f, 1.2f);
        if (!ListenerUtil.mutListener.listen(20451)) {
            animX.setRepeatMode(ValueAnimator.REVERSE);
        }
        if (!ListenerUtil.mutListener.listen(20452)) {
            animX.setRepeatCount(1);
        }
        ObjectAnimator animY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 1f, 1.2f);
        if (!ListenerUtil.mutListener.listen(20453)) {
            animY.setRepeatMode(ValueAnimator.REVERSE);
        }
        if (!ListenerUtil.mutListener.listen(20454)) {
            animY.setRepeatCount(1);
        }
        AnimatorSet set = new AnimatorSet();
        if (!ListenerUtil.mutListener.listen(20459)) {
            switch(button) {
                case LIKE_ON:
                case LIKE_OFF:
                    // rotate like button +/- 72 degrees (72 = 360/5, 5 is the number of points in the star)
                    float endRotate = (button == ReaderButton.LIKE_ON ? 72f : -72f);
                    ObjectAnimator animRotate = ObjectAnimator.ofFloat(target, View.ROTATION, 0f, endRotate);
                    if (!ListenerUtil.mutListener.listen(20455)) {
                        animRotate.setRepeatMode(ValueAnimator.REVERSE);
                    }
                    if (!ListenerUtil.mutListener.listen(20456)) {
                        animRotate.setRepeatCount(1);
                    }
                    if (!ListenerUtil.mutListener.listen(20457)) {
                        set.play(animX).with(animY).with(animRotate);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(20458)) {
                        set.play(animX).with(animY);
                    }
                    break;
            }
        }
        long durationMillis = AniUtils.Duration.SHORT.toMillis(target.getContext());
        if (!ListenerUtil.mutListener.listen(20460)) {
            set.setDuration(durationMillis);
        }
        if (!ListenerUtil.mutListener.listen(20461)) {
            set.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(20462)) {
            set.start();
        }
    }
}

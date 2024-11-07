package org.wordpress.android.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AniUtils {

    public enum Duration {

        SHORT, MEDIUM, LONG;

        public long toMillis(Context context) {
            switch(this) {
                case LONG:
                    return context.getResources().getInteger(android.R.integer.config_longAnimTime);
                case MEDIUM:
                    return context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
                default:
                    return context.getResources().getInteger(android.R.integer.config_shortAnimTime);
            }
        }
    }

    public interface AnimationEndListener {

        void onAnimationEnd();
    }

    private AniUtils() {
        throw new AssertionError();
    }

    public static void startAnimation(View target, int aniResId) {
        if (!ListenerUtil.mutListener.listen(27411)) {
            startAnimation(target, aniResId, null);
        }
    }

    public static void startAnimation(View target, int aniResId, int duration) {
        if (!ListenerUtil.mutListener.listen(27412)) {
            if (target == null) {
                return;
            }
        }
        Animation animation = AnimationUtils.loadAnimation(target.getContext(), aniResId);
        if (!ListenerUtil.mutListener.listen(27415)) {
            if (animation != null) {
                if (!ListenerUtil.mutListener.listen(27413)) {
                    animation.setDuration(duration);
                }
                if (!ListenerUtil.mutListener.listen(27414)) {
                    target.startAnimation(animation);
                }
            }
        }
    }

    public static void startAnimation(View target, int aniResId, AnimationListener listener) {
        if (!ListenerUtil.mutListener.listen(27416)) {
            if (target == null) {
                return;
            }
        }
        Animation animation = AnimationUtils.loadAnimation(target.getContext(), aniResId);
        if (!ListenerUtil.mutListener.listen(27420)) {
            if (animation != null) {
                if (!ListenerUtil.mutListener.listen(27418)) {
                    if (listener != null) {
                        if (!ListenerUtil.mutListener.listen(27417)) {
                            animation.setAnimationListener(listener);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27419)) {
                    target.startAnimation(animation);
                }
            }
        }
    }

    /*
     * in/out animation for floating action button
     */
    public static void showFab(final View view, final boolean show) {
        if (!ListenerUtil.mutListener.listen(27421)) {
            if (view == null) {
                return;
            }
        }
        Context context = view.getContext();
        int fabHeight = context.getResources().getDimensionPixelSize(R.dimen.design_fab_size);
        int fabMargin = context.getResources().getDimensionPixelSize(R.dimen.fab_margin);
        int max = (ListenerUtil.mutListener.listen(27429) ? (((ListenerUtil.mutListener.listen(27425) ? (fabHeight % fabMargin) : (ListenerUtil.mutListener.listen(27424) ? (fabHeight / fabMargin) : (ListenerUtil.mutListener.listen(27423) ? (fabHeight * fabMargin) : (ListenerUtil.mutListener.listen(27422) ? (fabHeight - fabMargin) : (fabHeight + fabMargin)))))) % 2) : (ListenerUtil.mutListener.listen(27428) ? (((ListenerUtil.mutListener.listen(27425) ? (fabHeight % fabMargin) : (ListenerUtil.mutListener.listen(27424) ? (fabHeight / fabMargin) : (ListenerUtil.mutListener.listen(27423) ? (fabHeight * fabMargin) : (ListenerUtil.mutListener.listen(27422) ? (fabHeight - fabMargin) : (fabHeight + fabMargin)))))) / 2) : (ListenerUtil.mutListener.listen(27427) ? (((ListenerUtil.mutListener.listen(27425) ? (fabHeight % fabMargin) : (ListenerUtil.mutListener.listen(27424) ? (fabHeight / fabMargin) : (ListenerUtil.mutListener.listen(27423) ? (fabHeight * fabMargin) : (ListenerUtil.mutListener.listen(27422) ? (fabHeight - fabMargin) : (fabHeight + fabMargin)))))) - 2) : (ListenerUtil.mutListener.listen(27426) ? (((ListenerUtil.mutListener.listen(27425) ? (fabHeight % fabMargin) : (ListenerUtil.mutListener.listen(27424) ? (fabHeight / fabMargin) : (ListenerUtil.mutListener.listen(27423) ? (fabHeight * fabMargin) : (ListenerUtil.mutListener.listen(27422) ? (fabHeight - fabMargin) : (fabHeight + fabMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(27425) ? (fabHeight % fabMargin) : (ListenerUtil.mutListener.listen(27424) ? (fabHeight / fabMargin) : (ListenerUtil.mutListener.listen(27423) ? (fabHeight * fabMargin) : (ListenerUtil.mutListener.listen(27422) ? (fabHeight - fabMargin) : (fabHeight + fabMargin)))))) * 2)))));
        float fromY = (show ? max : 0f);
        float toY = (show ? 0f : max);
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, fromY, toY);
        if (!ListenerUtil.mutListener.listen(27432)) {
            if (show) {
                if (!ListenerUtil.mutListener.listen(27431)) {
                    anim.setInterpolator(new DecelerateInterpolator());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27430)) {
                    anim.setInterpolator(new AccelerateInterpolator());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27433)) {
            anim.setDuration(show ? Duration.LONG.toMillis(context) : Duration.SHORT.toMillis(context));
        }
        if (!ListenerUtil.mutListener.listen(27440)) {
            anim.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(27434)) {
                        super.onAnimationStart(animation);
                    }
                    if (!ListenerUtil.mutListener.listen(27436)) {
                        if (view.getVisibility() != View.VISIBLE) {
                            if (!ListenerUtil.mutListener.listen(27435)) {
                                view.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(27437)) {
                        super.onAnimationEnd(animation);
                    }
                    if (!ListenerUtil.mutListener.listen(27439)) {
                        if (!show) {
                            if (!ListenerUtil.mutListener.listen(27438)) {
                                view.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(27441)) {
            anim.start();
        }
    }

    /*
     * used when animating a toolbar in/out
     */
    public static void animateTopBar(View view, boolean show) {
        if (!ListenerUtil.mutListener.listen(27442)) {
            animateBar(view, show, true, Duration.SHORT);
        }
    }

    public static void animateBottomBar(View view, boolean show) {
        if (!ListenerUtil.mutListener.listen(27443)) {
            animateBar(view, show, false, Duration.SHORT);
        }
    }

    public static void animateBottomBar(View view, boolean show, Duration duration) {
        if (!ListenerUtil.mutListener.listen(27444)) {
            animateBar(view, show, false, duration);
        }
    }

    private static void animateBar(View view, boolean show, boolean isTopBar, Duration duration) {
        int newVisibility = (show ? View.VISIBLE : View.GONE);
        if (!ListenerUtil.mutListener.listen(27446)) {
            if ((ListenerUtil.mutListener.listen(27445) ? (view == null && view.getVisibility() == newVisibility) : (view == null || view.getVisibility() == newVisibility))) {
                return;
            }
        }
        float fromY;
        float toY;
        if (isTopBar) {
            fromY = (show ? -1f : 0f);
            toY = (show ? 0f : -1f);
        } else {
            fromY = (show ? 1f : 0f);
            toY = (show ? 0f : 1f);
        }
        Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        long durationMillis = duration.toMillis(view.getContext());
        if (!ListenerUtil.mutListener.listen(27447)) {
            animation.setDuration(durationMillis);
        }
        if (!ListenerUtil.mutListener.listen(27450)) {
            if (show) {
                if (!ListenerUtil.mutListener.listen(27449)) {
                    animation.setInterpolator(new DecelerateInterpolator());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27448)) {
                    animation.setInterpolator(new AccelerateInterpolator());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27451)) {
            view.clearAnimation();
        }
        if (!ListenerUtil.mutListener.listen(27452)) {
            view.startAnimation(animation);
        }
        if (!ListenerUtil.mutListener.listen(27453)) {
            view.setVisibility(newVisibility);
        }
    }

    public static ObjectAnimator getFadeInAnim(final View target, Duration duration) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(target, View.ALPHA, 0.0f, 1.0f);
        if (!ListenerUtil.mutListener.listen(27454)) {
            fadeIn.setDuration(duration.toMillis(target.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(27455)) {
            fadeIn.setInterpolator(new LinearInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(27457)) {
            fadeIn.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(27456)) {
                        target.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        return fadeIn;
    }

    public static ObjectAnimator getFadeOutAnim(final View target, Duration duration, final int endVisibility) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(target, View.ALPHA, 1.0f, 0.0f);
        if (!ListenerUtil.mutListener.listen(27458)) {
            fadeOut.setDuration(duration.toMillis(target.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(27459)) {
            fadeOut.setInterpolator(new LinearInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(27461)) {
            fadeOut.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(27460)) {
                        target.setVisibility(endVisibility);
                    }
                }
            });
        }
        return fadeOut;
    }

    public static void fadeIn(final View target, Duration duration) {
        if (!ListenerUtil.mutListener.listen(27464)) {
            if ((ListenerUtil.mutListener.listen(27462) ? (target != null || duration != null) : (target != null && duration != null))) {
                if (!ListenerUtil.mutListener.listen(27463)) {
                    getFadeInAnim(target, duration).start();
                }
            }
        }
    }

    public static void fadeOut(final View target, Duration duration) {
        if (!ListenerUtil.mutListener.listen(27465)) {
            fadeOut(target, duration, View.GONE);
        }
    }

    public static void fadeOut(final View target, Duration duration, int endVisibility) {
        if (!ListenerUtil.mutListener.listen(27468)) {
            if ((ListenerUtil.mutListener.listen(27466) ? (target != null || duration != null) : (target != null && duration != null))) {
                if (!ListenerUtil.mutListener.listen(27467)) {
                    getFadeOutAnim(target, duration, endVisibility).start();
                }
            }
        }
    }

    public static void scale(final View target, float scaleStart, float scaleEnd, Duration duration) {
        if (!ListenerUtil.mutListener.listen(27470)) {
            if ((ListenerUtil.mutListener.listen(27469) ? (target == null && duration == null) : (target == null || duration == null))) {
                return;
            }
        }
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, scaleStart, scaleEnd);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, scaleStart, scaleEnd);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, scaleX, scaleY);
        if (!ListenerUtil.mutListener.listen(27471)) {
            animator.setDuration(duration.toMillis(target.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(27472)) {
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(27473)) {
            animator.start();
        }
    }

    public static void scaleIn(final View target, Duration duration) {
        if (!ListenerUtil.mutListener.listen(27475)) {
            if ((ListenerUtil.mutListener.listen(27474) ? (target == null && duration == null) : (target == null || duration == null))) {
                return;
            }
        }
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, scaleX, scaleY);
        if (!ListenerUtil.mutListener.listen(27476)) {
            animator.setDuration(duration.toMillis(target.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(27477)) {
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(27479)) {
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(27478)) {
                        target.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(27480)) {
            animator.start();
        }
    }

    public static void scaleOut(final View target, Duration duration) {
        if (!ListenerUtil.mutListener.listen(27481)) {
            scaleOut(target, View.GONE, duration, null);
        }
    }

    public static void scaleOut(final View target, final int endVisibility, Duration duration, final AnimationEndListener endListener) {
        if (!ListenerUtil.mutListener.listen(27483)) {
            if ((ListenerUtil.mutListener.listen(27482) ? (target == null && duration == null) : (target == null || duration == null))) {
                return;
            }
        }
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, scaleX, scaleY);
        if (!ListenerUtil.mutListener.listen(27484)) {
            animator.setDuration(duration.toMillis(target.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(27485)) {
            animator.setInterpolator(new AccelerateInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(27489)) {
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(27486)) {
                        target.setVisibility(endVisibility);
                    }
                    if (!ListenerUtil.mutListener.listen(27488)) {
                        if (endListener != null) {
                            if (!ListenerUtil.mutListener.listen(27487)) {
                                endListener.onAnimationEnd();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(27490)) {
            animator.start();
        }
    }
}

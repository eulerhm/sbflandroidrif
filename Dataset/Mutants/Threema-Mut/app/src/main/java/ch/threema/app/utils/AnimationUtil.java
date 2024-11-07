/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AnimationUtil {

    private static final Logger logger = LoggerFactory.getLogger(AnimationUtil.class);

    public static void expand(final View v) {
        if (!ListenerUtil.mutListener.listen(48945)) {
            expand(v, null);
        }
    }

    public static void expand(final View v, final Runnable onFinishRunnable) {
        if (!ListenerUtil.mutListener.listen(48946)) {
            v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        final int targetHeight = v.getMeasuredHeight();
        if (!ListenerUtil.mutListener.listen(48947)) {
            // Older versions of android (pre API 21) cancel animations for views with a height of 0.
            v.getLayoutParams().height = 1;
        }
        if (!ListenerUtil.mutListener.listen(48948)) {
            v.setVisibility(View.VISIBLE);
        }
        Animation a = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (!ListenerUtil.mutListener.listen(48958)) {
                    v.getLayoutParams().height = (ListenerUtil.mutListener.listen(48953) ? (interpolatedTime >= 1) : (ListenerUtil.mutListener.listen(48952) ? (interpolatedTime <= 1) : (ListenerUtil.mutListener.listen(48951) ? (interpolatedTime > 1) : (ListenerUtil.mutListener.listen(48950) ? (interpolatedTime < 1) : (ListenerUtil.mutListener.listen(48949) ? (interpolatedTime != 1) : (interpolatedTime == 1)))))) ? LinearLayout.LayoutParams.WRAP_CONTENT : (int) ((ListenerUtil.mutListener.listen(48957) ? (targetHeight % interpolatedTime) : (ListenerUtil.mutListener.listen(48956) ? (targetHeight / interpolatedTime) : (ListenerUtil.mutListener.listen(48955) ? (targetHeight - interpolatedTime) : (ListenerUtil.mutListener.listen(48954) ? (targetHeight + interpolatedTime) : (targetHeight * interpolatedTime))))));
                }
                if (!ListenerUtil.mutListener.listen(48959)) {
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        if (!ListenerUtil.mutListener.listen(48968)) {
            // 2dp/ms
            a.setDuration((ListenerUtil.mutListener.listen(48967) ? ((int) ((ListenerUtil.mutListener.listen(48963) ? (targetHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48962) ? (targetHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48961) ? (targetHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48960) ? (targetHeight + v.getContext().getResources().getDisplayMetrics().density) : (targetHeight / v.getContext().getResources().getDisplayMetrics().density)))))) % 2) : (ListenerUtil.mutListener.listen(48966) ? ((int) ((ListenerUtil.mutListener.listen(48963) ? (targetHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48962) ? (targetHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48961) ? (targetHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48960) ? (targetHeight + v.getContext().getResources().getDisplayMetrics().density) : (targetHeight / v.getContext().getResources().getDisplayMetrics().density)))))) / 2) : (ListenerUtil.mutListener.listen(48965) ? ((int) ((ListenerUtil.mutListener.listen(48963) ? (targetHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48962) ? (targetHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48961) ? (targetHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48960) ? (targetHeight + v.getContext().getResources().getDisplayMetrics().density) : (targetHeight / v.getContext().getResources().getDisplayMetrics().density)))))) - 2) : (ListenerUtil.mutListener.listen(48964) ? ((int) ((ListenerUtil.mutListener.listen(48963) ? (targetHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48962) ? (targetHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48961) ? (targetHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48960) ? (targetHeight + v.getContext().getResources().getDisplayMetrics().density) : (targetHeight / v.getContext().getResources().getDisplayMetrics().density)))))) + 2) : ((int) ((ListenerUtil.mutListener.listen(48963) ? (targetHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48962) ? (targetHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48961) ? (targetHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48960) ? (targetHeight + v.getContext().getResources().getDisplayMetrics().density) : (targetHeight / v.getContext().getResources().getDisplayMetrics().density)))))) * 2))))));
        }
        if (!ListenerUtil.mutListener.listen(48971)) {
            if (onFinishRunnable != null) {
                if (!ListenerUtil.mutListener.listen(48970)) {
                    a.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (!ListenerUtil.mutListener.listen(48969)) {
                                onFinishRunnable.run();
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48972)) {
            v.startAnimation(a);
        }
    }

    public static void collapse(final View v) {
        if (!ListenerUtil.mutListener.listen(48973)) {
            collapse(v, null);
        }
    }

    public static void collapse(final View v, final Runnable onFinishRunnable) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (!ListenerUtil.mutListener.listen(48990)) {
                    if ((ListenerUtil.mutListener.listen(48978) ? (interpolatedTime >= 1) : (ListenerUtil.mutListener.listen(48977) ? (interpolatedTime <= 1) : (ListenerUtil.mutListener.listen(48976) ? (interpolatedTime > 1) : (ListenerUtil.mutListener.listen(48975) ? (interpolatedTime < 1) : (ListenerUtil.mutListener.listen(48974) ? (interpolatedTime != 1) : (interpolatedTime == 1))))))) {
                        if (!ListenerUtil.mutListener.listen(48989)) {
                            v.setVisibility(View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(48987)) {
                            v.getLayoutParams().height = (ListenerUtil.mutListener.listen(48986) ? (initialHeight % (int) ((ListenerUtil.mutListener.listen(48982) ? (initialHeight % interpolatedTime) : (ListenerUtil.mutListener.listen(48981) ? (initialHeight / interpolatedTime) : (ListenerUtil.mutListener.listen(48980) ? (initialHeight - interpolatedTime) : (ListenerUtil.mutListener.listen(48979) ? (initialHeight + interpolatedTime) : (initialHeight * interpolatedTime))))))) : (ListenerUtil.mutListener.listen(48985) ? (initialHeight / (int) ((ListenerUtil.mutListener.listen(48982) ? (initialHeight % interpolatedTime) : (ListenerUtil.mutListener.listen(48981) ? (initialHeight / interpolatedTime) : (ListenerUtil.mutListener.listen(48980) ? (initialHeight - interpolatedTime) : (ListenerUtil.mutListener.listen(48979) ? (initialHeight + interpolatedTime) : (initialHeight * interpolatedTime))))))) : (ListenerUtil.mutListener.listen(48984) ? (initialHeight * (int) ((ListenerUtil.mutListener.listen(48982) ? (initialHeight % interpolatedTime) : (ListenerUtil.mutListener.listen(48981) ? (initialHeight / interpolatedTime) : (ListenerUtil.mutListener.listen(48980) ? (initialHeight - interpolatedTime) : (ListenerUtil.mutListener.listen(48979) ? (initialHeight + interpolatedTime) : (initialHeight * interpolatedTime))))))) : (ListenerUtil.mutListener.listen(48983) ? (initialHeight + (int) ((ListenerUtil.mutListener.listen(48982) ? (initialHeight % interpolatedTime) : (ListenerUtil.mutListener.listen(48981) ? (initialHeight / interpolatedTime) : (ListenerUtil.mutListener.listen(48980) ? (initialHeight - interpolatedTime) : (ListenerUtil.mutListener.listen(48979) ? (initialHeight + interpolatedTime) : (initialHeight * interpolatedTime))))))) : (initialHeight - (int) ((ListenerUtil.mutListener.listen(48982) ? (initialHeight % interpolatedTime) : (ListenerUtil.mutListener.listen(48981) ? (initialHeight / interpolatedTime) : (ListenerUtil.mutListener.listen(48980) ? (initialHeight - interpolatedTime) : (ListenerUtil.mutListener.listen(48979) ? (initialHeight + interpolatedTime) : (initialHeight * interpolatedTime)))))))))));
                        }
                        if (!ListenerUtil.mutListener.listen(48988)) {
                            v.requestLayout();
                        }
                    }
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        if (!ListenerUtil.mutListener.listen(48999)) {
            // 2dp/ms
            a.setDuration((ListenerUtil.mutListener.listen(48998) ? ((int) ((ListenerUtil.mutListener.listen(48994) ? (initialHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48993) ? (initialHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48992) ? (initialHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48991) ? (initialHeight + v.getContext().getResources().getDisplayMetrics().density) : (initialHeight / v.getContext().getResources().getDisplayMetrics().density)))))) % 2) : (ListenerUtil.mutListener.listen(48997) ? ((int) ((ListenerUtil.mutListener.listen(48994) ? (initialHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48993) ? (initialHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48992) ? (initialHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48991) ? (initialHeight + v.getContext().getResources().getDisplayMetrics().density) : (initialHeight / v.getContext().getResources().getDisplayMetrics().density)))))) / 2) : (ListenerUtil.mutListener.listen(48996) ? ((int) ((ListenerUtil.mutListener.listen(48994) ? (initialHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48993) ? (initialHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48992) ? (initialHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48991) ? (initialHeight + v.getContext().getResources().getDisplayMetrics().density) : (initialHeight / v.getContext().getResources().getDisplayMetrics().density)))))) - 2) : (ListenerUtil.mutListener.listen(48995) ? ((int) ((ListenerUtil.mutListener.listen(48994) ? (initialHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48993) ? (initialHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48992) ? (initialHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48991) ? (initialHeight + v.getContext().getResources().getDisplayMetrics().density) : (initialHeight / v.getContext().getResources().getDisplayMetrics().density)))))) + 2) : ((int) ((ListenerUtil.mutListener.listen(48994) ? (initialHeight % v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48993) ? (initialHeight * v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48992) ? (initialHeight - v.getContext().getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(48991) ? (initialHeight + v.getContext().getResources().getDisplayMetrics().density) : (initialHeight / v.getContext().getResources().getDisplayMetrics().density)))))) * 2))))));
        }
        if (!ListenerUtil.mutListener.listen(49002)) {
            if (onFinishRunnable != null) {
                if (!ListenerUtil.mutListener.listen(49001)) {
                    a.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (!ListenerUtil.mutListener.listen(49000)) {
                                onFinishRunnable.run();
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49003)) {
            v.startAnimation(a);
        }
    }

    public static void startActivityForResult(Activity activity, View v, Intent intent, int requestCode) {
        if (!ListenerUtil.mutListener.listen(49004)) {
            logger.debug("start activity for result " + activity + " " + intent + " " + requestCode);
        }
        if (!ListenerUtil.mutListener.listen(49022)) {
            if (activity != null) {
                ActivityOptionsCompat options = null;
                if (!ListenerUtil.mutListener.listen(49007)) {
                    if (v != null) {
                        if (!ListenerUtil.mutListener.listen(49005)) {
                            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        }
                        if (!ListenerUtil.mutListener.listen(49006)) {
                            options = ActivityOptionsCompat.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(49021)) {
                    if ((ListenerUtil.mutListener.listen(49012) ? (requestCode >= 0) : (ListenerUtil.mutListener.listen(49011) ? (requestCode <= 0) : (ListenerUtil.mutListener.listen(49010) ? (requestCode > 0) : (ListenerUtil.mutListener.listen(49009) ? (requestCode < 0) : (ListenerUtil.mutListener.listen(49008) ? (requestCode == 0) : (requestCode != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(49020)) {
                            if (options != null) {
                                if (!ListenerUtil.mutListener.listen(49019)) {
                                    ActivityCompat.startActivityForResult(activity, intent, requestCode, options.toBundle());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(49017)) {
                                    activity.startActivityForResult(intent, requestCode);
                                }
                                if (!ListenerUtil.mutListener.listen(49018)) {
                                    activity.overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(49016)) {
                            if (options != null) {
                                if (!ListenerUtil.mutListener.listen(49015)) {
                                    ActivityCompat.startActivity(activity, intent, options.toBundle());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(49013)) {
                                    activity.startActivity(intent);
                                }
                                if (!ListenerUtil.mutListener.listen(49014)) {
                                    activity.overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void startActivity(Activity activity, View v, Intent intent) {
        if (!ListenerUtil.mutListener.listen(49023)) {
            startActivityForResult(activity, v, intent, 0);
        }
    }

    public static void setupTransitions(Context context, Window window) {
        if (!ListenerUtil.mutListener.listen(49037)) {
            // requestFeature() must be called before adding content
            if ((ListenerUtil.mutListener.listen(49030) ? ((ListenerUtil.mutListener.listen(49029) ? ((ListenerUtil.mutListener.listen(49028) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49027) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49026) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49025) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49024) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || window != null) : ((ListenerUtil.mutListener.listen(49028) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49027) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49026) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49025) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49024) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && window != null)) || context != null) : ((ListenerUtil.mutListener.listen(49029) ? ((ListenerUtil.mutListener.listen(49028) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49027) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49026) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49025) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49024) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || window != null) : ((ListenerUtil.mutListener.listen(49028) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49027) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49026) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49025) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49024) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && window != null)) && context != null))) {
                android.transition.Transition fade = new android.transition.Fade();
                if (!ListenerUtil.mutListener.listen(49031)) {
                    fade.excludeTarget(android.R.id.navigationBarBackground, true);
                }
                if (!ListenerUtil.mutListener.listen(49032)) {
                    window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
                }
                if (!ListenerUtil.mutListener.listen(49033)) {
                    window.setEnterTransition(fade);
                }
                if (!ListenerUtil.mutListener.listen(49034)) {
                    window.setExitTransition(fade);
                }
                if (!ListenerUtil.mutListener.listen(49035)) {
                    window.setAllowEnterTransitionOverlap(true);
                }
                if (!ListenerUtil.mutListener.listen(49036)) {
                    window.setAllowReturnTransitionOverlap(true);
                }
            }
        }
    }

    public static void getViewCenter(View theView, View containerView, int[] location) {
        if (!ListenerUtil.mutListener.listen(49053)) {
            if (theView != null) {
                final int[] containerViewLocation = new int[2];
                if (!ListenerUtil.mutListener.listen(49038)) {
                    theView.getLocationOnScreen(location);
                }
                if (!ListenerUtil.mutListener.listen(49043)) {
                    location[0] += (ListenerUtil.mutListener.listen(49042) ? (theView.getWidth() % 2) : (ListenerUtil.mutListener.listen(49041) ? (theView.getWidth() * 2) : (ListenerUtil.mutListener.listen(49040) ? (theView.getWidth() - 2) : (ListenerUtil.mutListener.listen(49039) ? (theView.getWidth() + 2) : (theView.getWidth() / 2)))));
                }
                if (!ListenerUtil.mutListener.listen(49048)) {
                    location[1] += (ListenerUtil.mutListener.listen(49047) ? (theView.getHeight() % 2) : (ListenerUtil.mutListener.listen(49046) ? (theView.getHeight() * 2) : (ListenerUtil.mutListener.listen(49045) ? (theView.getHeight() - 2) : (ListenerUtil.mutListener.listen(49044) ? (theView.getHeight() + 2) : (theView.getHeight() / 2)))));
                }
                if (!ListenerUtil.mutListener.listen(49052)) {
                    if (containerView != null) {
                        if (!ListenerUtil.mutListener.listen(49049)) {
                            containerView.getLocationOnScreen(containerViewLocation);
                        }
                        if (!ListenerUtil.mutListener.listen(49050)) {
                            location[0] -= containerViewLocation[0];
                        }
                        if (!ListenerUtil.mutListener.listen(49051)) {
                            location[1] -= containerViewLocation[1];
                        }
                    }
                }
            }
        }
    }

    public static void circularReveal(View theLayout, int cx, int cy, boolean fromBottom) {
        if (!ListenerUtil.mutListener.listen(49054)) {
            circularReveal(theLayout, cx, cy, 300, fromBottom);
        }
    }

    private static void circularReveal(View theLayout, int cx, int cy, int duration, boolean fromBottom) {
        if (!ListenerUtil.mutListener.listen(49123)) {
            if ((ListenerUtil.mutListener.listen(49071) ? ((ListenerUtil.mutListener.listen(49059) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49058) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49057) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49056) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49055) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || !((ListenerUtil.mutListener.listen(49070) ? ((ListenerUtil.mutListener.listen(49064) ? (cx >= 0) : (ListenerUtil.mutListener.listen(49063) ? (cx <= 0) : (ListenerUtil.mutListener.listen(49062) ? (cx > 0) : (ListenerUtil.mutListener.listen(49061) ? (cx < 0) : (ListenerUtil.mutListener.listen(49060) ? (cx != 0) : (cx == 0)))))) || (ListenerUtil.mutListener.listen(49069) ? (cy >= 0) : (ListenerUtil.mutListener.listen(49068) ? (cy <= 0) : (ListenerUtil.mutListener.listen(49067) ? (cy > 0) : (ListenerUtil.mutListener.listen(49066) ? (cy < 0) : (ListenerUtil.mutListener.listen(49065) ? (cy != 0) : (cy == 0))))))) : ((ListenerUtil.mutListener.listen(49064) ? (cx >= 0) : (ListenerUtil.mutListener.listen(49063) ? (cx <= 0) : (ListenerUtil.mutListener.listen(49062) ? (cx > 0) : (ListenerUtil.mutListener.listen(49061) ? (cx < 0) : (ListenerUtil.mutListener.listen(49060) ? (cx != 0) : (cx == 0)))))) && (ListenerUtil.mutListener.listen(49069) ? (cy >= 0) : (ListenerUtil.mutListener.listen(49068) ? (cy <= 0) : (ListenerUtil.mutListener.listen(49067) ? (cy > 0) : (ListenerUtil.mutListener.listen(49066) ? (cy < 0) : (ListenerUtil.mutListener.listen(49065) ? (cy != 0) : (cy == 0)))))))))) : ((ListenerUtil.mutListener.listen(49059) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49058) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49057) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49056) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49055) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && !((ListenerUtil.mutListener.listen(49070) ? ((ListenerUtil.mutListener.listen(49064) ? (cx >= 0) : (ListenerUtil.mutListener.listen(49063) ? (cx <= 0) : (ListenerUtil.mutListener.listen(49062) ? (cx > 0) : (ListenerUtil.mutListener.listen(49061) ? (cx < 0) : (ListenerUtil.mutListener.listen(49060) ? (cx != 0) : (cx == 0)))))) || (ListenerUtil.mutListener.listen(49069) ? (cy >= 0) : (ListenerUtil.mutListener.listen(49068) ? (cy <= 0) : (ListenerUtil.mutListener.listen(49067) ? (cy > 0) : (ListenerUtil.mutListener.listen(49066) ? (cy < 0) : (ListenerUtil.mutListener.listen(49065) ? (cy != 0) : (cy == 0))))))) : ((ListenerUtil.mutListener.listen(49064) ? (cx >= 0) : (ListenerUtil.mutListener.listen(49063) ? (cx <= 0) : (ListenerUtil.mutListener.listen(49062) ? (cx > 0) : (ListenerUtil.mutListener.listen(49061) ? (cx < 0) : (ListenerUtil.mutListener.listen(49060) ? (cx != 0) : (cx == 0)))))) && (ListenerUtil.mutListener.listen(49069) ? (cy >= 0) : (ListenerUtil.mutListener.listen(49068) ? (cy <= 0) : (ListenerUtil.mutListener.listen(49067) ? (cy > 0) : (ListenerUtil.mutListener.listen(49066) ? (cy < 0) : (ListenerUtil.mutListener.listen(49065) ? (cy != 0) : (cy == 0)))))))))))) {
                if (!ListenerUtil.mutListener.listen(49073)) {
                    theLayout.setVisibility(View.INVISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(49122)) {
                    theLayout.post(new Runnable() {

                        @Override
                        public void run() {
                            int viewWidth = theLayout.getWidth();
                            if (!ListenerUtil.mutListener.listen(49089)) {
                                if ((ListenerUtil.mutListener.listen(49082) ? (cx >= ((ListenerUtil.mutListener.listen(49077) ? (viewWidth % 2) : (ListenerUtil.mutListener.listen(49076) ? (viewWidth * 2) : (ListenerUtil.mutListener.listen(49075) ? (viewWidth - 2) : (ListenerUtil.mutListener.listen(49074) ? (viewWidth + 2) : (viewWidth / 2))))))) : (ListenerUtil.mutListener.listen(49081) ? (cx <= ((ListenerUtil.mutListener.listen(49077) ? (viewWidth % 2) : (ListenerUtil.mutListener.listen(49076) ? (viewWidth * 2) : (ListenerUtil.mutListener.listen(49075) ? (viewWidth - 2) : (ListenerUtil.mutListener.listen(49074) ? (viewWidth + 2) : (viewWidth / 2))))))) : (ListenerUtil.mutListener.listen(49080) ? (cx < ((ListenerUtil.mutListener.listen(49077) ? (viewWidth % 2) : (ListenerUtil.mutListener.listen(49076) ? (viewWidth * 2) : (ListenerUtil.mutListener.listen(49075) ? (viewWidth - 2) : (ListenerUtil.mutListener.listen(49074) ? (viewWidth + 2) : (viewWidth / 2))))))) : (ListenerUtil.mutListener.listen(49079) ? (cx != ((ListenerUtil.mutListener.listen(49077) ? (viewWidth % 2) : (ListenerUtil.mutListener.listen(49076) ? (viewWidth * 2) : (ListenerUtil.mutListener.listen(49075) ? (viewWidth - 2) : (ListenerUtil.mutListener.listen(49074) ? (viewWidth + 2) : (viewWidth / 2))))))) : (ListenerUtil.mutListener.listen(49078) ? (cx == ((ListenerUtil.mutListener.listen(49077) ? (viewWidth % 2) : (ListenerUtil.mutListener.listen(49076) ? (viewWidth * 2) : (ListenerUtil.mutListener.listen(49075) ? (viewWidth - 2) : (ListenerUtil.mutListener.listen(49074) ? (viewWidth + 2) : (viewWidth / 2))))))) : (cx > ((ListenerUtil.mutListener.listen(49077) ? (viewWidth % 2) : (ListenerUtil.mutListener.listen(49076) ? (viewWidth * 2) : (ListenerUtil.mutListener.listen(49075) ? (viewWidth - 2) : (ListenerUtil.mutListener.listen(49074) ? (viewWidth + 2) : (viewWidth / 2))))))))))))) {
                                    if (!ListenerUtil.mutListener.listen(49088)) {
                                        viewWidth = cx;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(49087)) {
                                        viewWidth = (ListenerUtil.mutListener.listen(49086) ? (viewWidth % cx) : (ListenerUtil.mutListener.listen(49085) ? (viewWidth / cx) : (ListenerUtil.mutListener.listen(49084) ? (viewWidth * cx) : (ListenerUtil.mutListener.listen(49083) ? (viewWidth + cx) : (viewWidth - cx)))));
                                    }
                                }
                            }
                            int viewHeight = theLayout.getHeight();
                            if (!ListenerUtil.mutListener.listen(49105)) {
                                if ((ListenerUtil.mutListener.listen(49098) ? (cy >= ((ListenerUtil.mutListener.listen(49093) ? (viewHeight % 2) : (ListenerUtil.mutListener.listen(49092) ? (viewHeight * 2) : (ListenerUtil.mutListener.listen(49091) ? (viewHeight - 2) : (ListenerUtil.mutListener.listen(49090) ? (viewHeight + 2) : (viewHeight / 2))))))) : (ListenerUtil.mutListener.listen(49097) ? (cy <= ((ListenerUtil.mutListener.listen(49093) ? (viewHeight % 2) : (ListenerUtil.mutListener.listen(49092) ? (viewHeight * 2) : (ListenerUtil.mutListener.listen(49091) ? (viewHeight - 2) : (ListenerUtil.mutListener.listen(49090) ? (viewHeight + 2) : (viewHeight / 2))))))) : (ListenerUtil.mutListener.listen(49096) ? (cy < ((ListenerUtil.mutListener.listen(49093) ? (viewHeight % 2) : (ListenerUtil.mutListener.listen(49092) ? (viewHeight * 2) : (ListenerUtil.mutListener.listen(49091) ? (viewHeight - 2) : (ListenerUtil.mutListener.listen(49090) ? (viewHeight + 2) : (viewHeight / 2))))))) : (ListenerUtil.mutListener.listen(49095) ? (cy != ((ListenerUtil.mutListener.listen(49093) ? (viewHeight % 2) : (ListenerUtil.mutListener.listen(49092) ? (viewHeight * 2) : (ListenerUtil.mutListener.listen(49091) ? (viewHeight - 2) : (ListenerUtil.mutListener.listen(49090) ? (viewHeight + 2) : (viewHeight / 2))))))) : (ListenerUtil.mutListener.listen(49094) ? (cy == ((ListenerUtil.mutListener.listen(49093) ? (viewHeight % 2) : (ListenerUtil.mutListener.listen(49092) ? (viewHeight * 2) : (ListenerUtil.mutListener.listen(49091) ? (viewHeight - 2) : (ListenerUtil.mutListener.listen(49090) ? (viewHeight + 2) : (viewHeight / 2))))))) : (cy > ((ListenerUtil.mutListener.listen(49093) ? (viewHeight % 2) : (ListenerUtil.mutListener.listen(49092) ? (viewHeight * 2) : (ListenerUtil.mutListener.listen(49091) ? (viewHeight - 2) : (ListenerUtil.mutListener.listen(49090) ? (viewHeight + 2) : (viewHeight / 2))))))))))))) {
                                    if (!ListenerUtil.mutListener.listen(49104)) {
                                        viewHeight = cy;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(49103)) {
                                        viewHeight = (ListenerUtil.mutListener.listen(49102) ? (viewHeight % cy) : (ListenerUtil.mutListener.listen(49101) ? (viewHeight / cy) : (ListenerUtil.mutListener.listen(49100) ? (viewHeight * cy) : (ListenerUtil.mutListener.listen(49099) ? (viewHeight + cy) : (viewHeight - cy)))));
                                    }
                                }
                            }
                            float finalRadius = (float) Math.sqrt((ListenerUtil.mutListener.listen(49117) ? ((ListenerUtil.mutListener.listen(49109) ? (viewWidth % viewWidth) : (ListenerUtil.mutListener.listen(49108) ? (viewWidth / viewWidth) : (ListenerUtil.mutListener.listen(49107) ? (viewWidth - viewWidth) : (ListenerUtil.mutListener.listen(49106) ? (viewWidth + viewWidth) : (viewWidth * viewWidth))))) % (ListenerUtil.mutListener.listen(49113) ? (viewHeight % viewHeight) : (ListenerUtil.mutListener.listen(49112) ? (viewHeight / viewHeight) : (ListenerUtil.mutListener.listen(49111) ? (viewHeight - viewHeight) : (ListenerUtil.mutListener.listen(49110) ? (viewHeight + viewHeight) : (viewHeight * viewHeight)))))) : (ListenerUtil.mutListener.listen(49116) ? ((ListenerUtil.mutListener.listen(49109) ? (viewWidth % viewWidth) : (ListenerUtil.mutListener.listen(49108) ? (viewWidth / viewWidth) : (ListenerUtil.mutListener.listen(49107) ? (viewWidth - viewWidth) : (ListenerUtil.mutListener.listen(49106) ? (viewWidth + viewWidth) : (viewWidth * viewWidth))))) / (ListenerUtil.mutListener.listen(49113) ? (viewHeight % viewHeight) : (ListenerUtil.mutListener.listen(49112) ? (viewHeight / viewHeight) : (ListenerUtil.mutListener.listen(49111) ? (viewHeight - viewHeight) : (ListenerUtil.mutListener.listen(49110) ? (viewHeight + viewHeight) : (viewHeight * viewHeight)))))) : (ListenerUtil.mutListener.listen(49115) ? ((ListenerUtil.mutListener.listen(49109) ? (viewWidth % viewWidth) : (ListenerUtil.mutListener.listen(49108) ? (viewWidth / viewWidth) : (ListenerUtil.mutListener.listen(49107) ? (viewWidth - viewWidth) : (ListenerUtil.mutListener.listen(49106) ? (viewWidth + viewWidth) : (viewWidth * viewWidth))))) * (ListenerUtil.mutListener.listen(49113) ? (viewHeight % viewHeight) : (ListenerUtil.mutListener.listen(49112) ? (viewHeight / viewHeight) : (ListenerUtil.mutListener.listen(49111) ? (viewHeight - viewHeight) : (ListenerUtil.mutListener.listen(49110) ? (viewHeight + viewHeight) : (viewHeight * viewHeight)))))) : (ListenerUtil.mutListener.listen(49114) ? ((ListenerUtil.mutListener.listen(49109) ? (viewWidth % viewWidth) : (ListenerUtil.mutListener.listen(49108) ? (viewWidth / viewWidth) : (ListenerUtil.mutListener.listen(49107) ? (viewWidth - viewWidth) : (ListenerUtil.mutListener.listen(49106) ? (viewWidth + viewWidth) : (viewWidth * viewWidth))))) - (ListenerUtil.mutListener.listen(49113) ? (viewHeight % viewHeight) : (ListenerUtil.mutListener.listen(49112) ? (viewHeight / viewHeight) : (ListenerUtil.mutListener.listen(49111) ? (viewHeight - viewHeight) : (ListenerUtil.mutListener.listen(49110) ? (viewHeight + viewHeight) : (viewHeight * viewHeight)))))) : ((ListenerUtil.mutListener.listen(49109) ? (viewWidth % viewWidth) : (ListenerUtil.mutListener.listen(49108) ? (viewWidth / viewWidth) : (ListenerUtil.mutListener.listen(49107) ? (viewWidth - viewWidth) : (ListenerUtil.mutListener.listen(49106) ? (viewWidth + viewWidth) : (viewWidth * viewWidth))))) + (ListenerUtil.mutListener.listen(49113) ? (viewHeight % viewHeight) : (ListenerUtil.mutListener.listen(49112) ? (viewHeight / viewHeight) : (ListenerUtil.mutListener.listen(49111) ? (viewHeight - viewHeight) : (ListenerUtil.mutListener.listen(49110) ? (viewHeight + viewHeight) : (viewHeight * viewHeight)))))))))));
                            try {
                                Animator anim = ViewAnimationUtils.createCircularReveal(theLayout, cx, cy, 0, finalRadius);
                                if (!ListenerUtil.mutListener.listen(49119)) {
                                    anim.setDuration(duration);
                                }
                                if (!ListenerUtil.mutListener.listen(49120)) {
                                    // make the view visible and start the animation
                                    theLayout.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(49121)) {
                                    anim.start();
                                }
                            } catch (IllegalStateException e) {
                                if (!ListenerUtil.mutListener.listen(49118)) {
                                    theLayout.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(49072)) {
                    slideInAnimation(theLayout, fromBottom, 250);
                }
            }
        }
    }

    public static void circularObscure(final View theLayout, int cx, int cy, boolean toBottom, final Runnable onFinishRunnable) {
        if (!ListenerUtil.mutListener.listen(49150)) {
            if ((ListenerUtil.mutListener.listen(49140) ? ((ListenerUtil.mutListener.listen(49128) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49127) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49126) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49125) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49124) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || !((ListenerUtil.mutListener.listen(49139) ? ((ListenerUtil.mutListener.listen(49133) ? (cx >= 0) : (ListenerUtil.mutListener.listen(49132) ? (cx <= 0) : (ListenerUtil.mutListener.listen(49131) ? (cx > 0) : (ListenerUtil.mutListener.listen(49130) ? (cx < 0) : (ListenerUtil.mutListener.listen(49129) ? (cx != 0) : (cx == 0)))))) || (ListenerUtil.mutListener.listen(49138) ? (cy >= 0) : (ListenerUtil.mutListener.listen(49137) ? (cy <= 0) : (ListenerUtil.mutListener.listen(49136) ? (cy > 0) : (ListenerUtil.mutListener.listen(49135) ? (cy < 0) : (ListenerUtil.mutListener.listen(49134) ? (cy != 0) : (cy == 0))))))) : ((ListenerUtil.mutListener.listen(49133) ? (cx >= 0) : (ListenerUtil.mutListener.listen(49132) ? (cx <= 0) : (ListenerUtil.mutListener.listen(49131) ? (cx > 0) : (ListenerUtil.mutListener.listen(49130) ? (cx < 0) : (ListenerUtil.mutListener.listen(49129) ? (cx != 0) : (cx == 0)))))) && (ListenerUtil.mutListener.listen(49138) ? (cy >= 0) : (ListenerUtil.mutListener.listen(49137) ? (cy <= 0) : (ListenerUtil.mutListener.listen(49136) ? (cy > 0) : (ListenerUtil.mutListener.listen(49135) ? (cy < 0) : (ListenerUtil.mutListener.listen(49134) ? (cy != 0) : (cy == 0)))))))))) : ((ListenerUtil.mutListener.listen(49128) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49127) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49126) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49125) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(49124) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && !((ListenerUtil.mutListener.listen(49139) ? ((ListenerUtil.mutListener.listen(49133) ? (cx >= 0) : (ListenerUtil.mutListener.listen(49132) ? (cx <= 0) : (ListenerUtil.mutListener.listen(49131) ? (cx > 0) : (ListenerUtil.mutListener.listen(49130) ? (cx < 0) : (ListenerUtil.mutListener.listen(49129) ? (cx != 0) : (cx == 0)))))) || (ListenerUtil.mutListener.listen(49138) ? (cy >= 0) : (ListenerUtil.mutListener.listen(49137) ? (cy <= 0) : (ListenerUtil.mutListener.listen(49136) ? (cy > 0) : (ListenerUtil.mutListener.listen(49135) ? (cy < 0) : (ListenerUtil.mutListener.listen(49134) ? (cy != 0) : (cy == 0))))))) : ((ListenerUtil.mutListener.listen(49133) ? (cx >= 0) : (ListenerUtil.mutListener.listen(49132) ? (cx <= 0) : (ListenerUtil.mutListener.listen(49131) ? (cx > 0) : (ListenerUtil.mutListener.listen(49130) ? (cx < 0) : (ListenerUtil.mutListener.listen(49129) ? (cx != 0) : (cx == 0)))))) && (ListenerUtil.mutListener.listen(49138) ? (cy >= 0) : (ListenerUtil.mutListener.listen(49137) ? (cy <= 0) : (ListenerUtil.mutListener.listen(49136) ? (cy > 0) : (ListenerUtil.mutListener.listen(49135) ? (cy < 0) : (ListenerUtil.mutListener.listen(49134) ? (cy != 0) : (cy == 0)))))))))))) {
                int initialRadius = theLayout.getWidth();
                if (!ListenerUtil.mutListener.listen(49149)) {
                    if (theLayout.isAttachedToWindow()) {
                        Animator anim = ViewAnimationUtils.createCircularReveal(theLayout, cx, cy, initialRadius, 0);
                        if (!ListenerUtil.mutListener.listen(49142)) {
                            anim.setDuration(200);
                        }
                        if (!ListenerUtil.mutListener.listen(49147)) {
                            anim.addListener(new AnimatorListenerAdapter() {

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if (!ListenerUtil.mutListener.listen(49143)) {
                                        super.onAnimationEnd(animation);
                                    }
                                    if (!ListenerUtil.mutListener.listen(49144)) {
                                        theLayout.setVisibility(View.INVISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(49146)) {
                                        if (onFinishRunnable != null) {
                                            if (!ListenerUtil.mutListener.listen(49145)) {
                                                onFinishRunnable.run();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(49148)) {
                            anim.start();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(49141)) {
                    slideOutAnimation(theLayout, toBottom, 1f, onFinishRunnable);
                }
            }
        }
    }

    public static void slideInFromBottomOvershoot(final View theLayout) {
        if (!ListenerUtil.mutListener.listen(49151)) {
            if (theLayout == null)
                return;
        }
        AnimationSet animation = new AnimationSet(true);
        Animation slideUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1.4f, Animation.RELATIVE_TO_SELF, 0f);
        if (!ListenerUtil.mutListener.listen(49152)) {
            animation.addAnimation(slideUp);
        }
        if (!ListenerUtil.mutListener.listen(49153)) {
            animation.setFillAfter(true);
        }
        if (!ListenerUtil.mutListener.listen(49154)) {
            animation.setInterpolator(new OvershootInterpolator(1f));
        }
        if (!ListenerUtil.mutListener.listen(49155)) {
            animation.setDuration(350);
        }
        if (!ListenerUtil.mutListener.listen(49156)) {
            theLayout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(49157)) {
            theLayout.startAnimation(animation);
        }
    }

    public static void slideInAnimation(final View theLayout, boolean fromBottom, int duration) {
        AnimationSet animation = new AnimationSet(true);
        Animation slideUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, fromBottom ? 1f : -1f, Animation.RELATIVE_TO_SELF, 0f);
        if (!ListenerUtil.mutListener.listen(49158)) {
            animation.addAnimation(slideUp);
        }
        if (!ListenerUtil.mutListener.listen(49159)) {
            animation.setFillAfter(true);
        }
        if (!ListenerUtil.mutListener.listen(49160)) {
            animation.setInterpolator(new DecelerateInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(49161)) {
            animation.setDuration(duration);
        }
        if (!ListenerUtil.mutListener.listen(49162)) {
            theLayout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(49163)) {
            theLayout.startAnimation(animation);
        }
    }

    public static void slideOutAnimation(final View theLayout, boolean toBottom, float toValue, final Runnable onFinishRunnable) {
        AnimationSet animation = new AnimationSet(true);
        Animation slideDown = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, toBottom ? toValue : (ListenerUtil.mutListener.listen(49167) ? (toValue % -1f) : (ListenerUtil.mutListener.listen(49166) ? (toValue / -1f) : (ListenerUtil.mutListener.listen(49165) ? (toValue - -1f) : (ListenerUtil.mutListener.listen(49164) ? (toValue + -1f) : (toValue * -1f))))));
        if (!ListenerUtil.mutListener.listen(49168)) {
            animation.addAnimation(slideDown);
        }
        if (!ListenerUtil.mutListener.listen(49169)) {
            animation.setFillAfter(true);
        }
        if (!ListenerUtil.mutListener.listen(49170)) {
            animation.setInterpolator(new AccelerateInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(49171)) {
            animation.setDuration(200);
        }
        if (!ListenerUtil.mutListener.listen(49175)) {
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Handler handler = new Handler();
                    if (!ListenerUtil.mutListener.listen(49174)) {
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(49173)) {
                                    if (onFinishRunnable != null) {
                                        if (!ListenerUtil.mutListener.listen(49172)) {
                                            onFinishRunnable.run();
                                        }
                                    }
                                }
                            }
                        });
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(49176)) {
            theLayout.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(49177)) {
            theLayout.startAnimation(animation);
        }
    }

    public static void zoomInAnimate(View view) {
        if (!ListenerUtil.mutListener.listen(49183)) {
            if (view.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(49178)) {
                    view.setVisibility(View.VISIBLE);
                }
                AnimationSet animation = new AnimationSet(true);
                Animation scale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                if (!ListenerUtil.mutListener.listen(49179)) {
                    animation.addAnimation(scale);
                }
                if (!ListenerUtil.mutListener.listen(49180)) {
                    animation.setInterpolator(new LinearInterpolator());
                }
                if (!ListenerUtil.mutListener.listen(49181)) {
                    animation.setDuration(100);
                }
                if (!ListenerUtil.mutListener.listen(49182)) {
                    view.startAnimation(animation);
                }
            }
        }
    }

    public static void zoomOutAnimate(final View view) {
        AnimationSet animation = new AnimationSet(true);
        Animation scale = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (!ListenerUtil.mutListener.listen(49184)) {
            animation.addAnimation(scale);
        }
        if (!ListenerUtil.mutListener.listen(49185)) {
            animation.setInterpolator(new LinearInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(49186)) {
            animation.setDuration(100);
        }
        if (!ListenerUtil.mutListener.listen(49190)) {
            animation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (!ListenerUtil.mutListener.listen(49189)) {
                        if ((ListenerUtil.mutListener.listen(49187) ? (view != null || view.getVisibility() == View.VISIBLE) : (view != null && view.getVisibility() == View.VISIBLE)))
                            if (!ListenerUtil.mutListener.listen(49188)) {
                                view.setVisibility(View.GONE);
                            }
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(49191)) {
            view.startAnimation(animation);
        }
    }

    public static void bubbleAnimate(View view, int delay) {
        AnimationSet animation = new AnimationSet(true);
        Animation scale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (!ListenerUtil.mutListener.listen(49192)) {
            animation.addAnimation(scale);
        }
        if (!ListenerUtil.mutListener.listen(49193)) {
            animation.setInterpolator(new OvershootInterpolator(1));
        }
        if (!ListenerUtil.mutListener.listen(49194)) {
            animation.setDuration(300);
        }
        if (!ListenerUtil.mutListener.listen(49195)) {
            animation.setStartOffset(delay);
        }
        if (!ListenerUtil.mutListener.listen(49196)) {
            view.startAnimation(animation);
        }
    }

    public static ObjectAnimator pulseAnimate(View view, final int delay) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat("scaleX", 1.15f), PropertyValuesHolder.ofFloat("scaleY", 1.15f));
        if (!ListenerUtil.mutListener.listen(49197)) {
            animator.setDuration(200);
        }
        if (!ListenerUtil.mutListener.listen(49198)) {
            animator.setRepeatMode(ObjectAnimator.REVERSE);
        }
        if (!ListenerUtil.mutListener.listen(49199)) {
            animator.setRepeatCount(1);
        }
        if (!ListenerUtil.mutListener.listen(49200)) {
            animator.setInterpolator(new FastOutSlowInInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(49203)) {
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(final Animator animation) {
                    if (!ListenerUtil.mutListener.listen(49202)) {
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(49201)) {
                                    animation.start();
                                }
                            }
                        }, delay);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(49204)) {
            animator.start();
        }
        return animator;
    }

    public static void popupAnimateIn(View view) {
        AnimationSet animation = new AnimationSet(true);
        Animation scale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        Animation fade = new AlphaAnimation(0.0f, 1.0f);
        if (!ListenerUtil.mutListener.listen(49205)) {
            animation.addAnimation(scale);
        }
        if (!ListenerUtil.mutListener.listen(49206)) {
            animation.addAnimation(fade);
        }
        if (!ListenerUtil.mutListener.listen(49207)) {
            animation.setInterpolator(new OvershootInterpolator(1));
        }
        if (!ListenerUtil.mutListener.listen(49208)) {
            animation.setDuration(250);
        }
        if (!ListenerUtil.mutListener.listen(49209)) {
            view.startAnimation(animation);
        }
    }

    public static void popupAnimateOut(View view, final Runnable onFinishRunnable) {
        AnimationSet animation = new AnimationSet(true);
        Animation scale = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        Animation fade = new AlphaAnimation(1.0f, 0.0f);
        if (!ListenerUtil.mutListener.listen(49210)) {
            animation.addAnimation(scale);
        }
        if (!ListenerUtil.mutListener.listen(49211)) {
            animation.addAnimation(fade);
        }
        if (!ListenerUtil.mutListener.listen(49212)) {
            animation.setInterpolator(new AccelerateInterpolator());
        }
        if (!ListenerUtil.mutListener.listen(49213)) {
            animation.setDuration(100);
        }
        if (!ListenerUtil.mutListener.listen(49217)) {
            if (onFinishRunnable != null) {
                if (!ListenerUtil.mutListener.listen(49216)) {
                    animation.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Handler handler = new Handler();
                            if (!ListenerUtil.mutListener.listen(49215)) {
                                handler.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(49214)) {
                                            onFinishRunnable.run();
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49218)) {
            view.startAnimation(animation);
        }
    }

    public static void fadeViewVisibility(final View view, final int visibility) {
        if (!ListenerUtil.mutListener.listen(49219)) {
            view.animate().cancel();
        }
        if (!ListenerUtil.mutListener.listen(49220)) {
            view.animate().setListener(null);
        }
        if (!ListenerUtil.mutListener.listen(49225)) {
            if (visibility == View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(49223)) {
                    view.animate().alpha(1f).start();
                }
                if (!ListenerUtil.mutListener.listen(49224)) {
                    view.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(49222)) {
                    view.animate().setListener(new AnimatorListenerAdapter() {

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (!ListenerUtil.mutListener.listen(49221)) {
                                view.setVisibility(visibility);
                            }
                        }
                    }).alpha(0f).start();
                }
            }
        }
    }

    public static void slideDown(@NonNull Context context, @NonNull View v) {
        if (!ListenerUtil.mutListener.listen(49226)) {
            slideDown(context, v, null);
        }
    }

    public static void slideDown(@NonNull Context context, @NonNull View v, @Nullable Runnable onEndRunnable) {
        Animation a = AnimationUtils.loadAnimation(context, R.anim.slide_down);
        if (!ListenerUtil.mutListener.listen(49234)) {
            if (a != null) {
                if (!ListenerUtil.mutListener.listen(49229)) {
                    if (onEndRunnable != null) {
                        if (!ListenerUtil.mutListener.listen(49228)) {
                            a.setAnimationListener(new Animation.AnimationListener() {

                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    if (!ListenerUtil.mutListener.listen(49227)) {
                                        onEndRunnable.run();
                                    }
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                }
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(49230)) {
                    a.reset();
                }
                if (!ListenerUtil.mutListener.listen(49233)) {
                    if (v != null) {
                        if (!ListenerUtil.mutListener.listen(49231)) {
                            v.clearAnimation();
                        }
                        if (!ListenerUtil.mutListener.listen(49232)) {
                            v.startAnimation(a);
                        }
                    }
                }
            }
        }
    }

    public static void slideUp(Context context, View v) {
        Animation a = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        if (!ListenerUtil.mutListener.listen(49239)) {
            if (a != null) {
                if (!ListenerUtil.mutListener.listen(49235)) {
                    a.reset();
                }
                if (!ListenerUtil.mutListener.listen(49238)) {
                    if (v != null) {
                        if (!ListenerUtil.mutListener.listen(49236)) {
                            v.clearAnimation();
                        }
                        if (!ListenerUtil.mutListener.listen(49237)) {
                            v.startAnimation(a);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Changes the visibility of a view by fading in or out
     *  @param view View to change visibility of
     *  @param visibility Visibility of the view after transition
     */
    public static void setFadingVisibility(View view, int visibility) {
        if (!ListenerUtil.mutListener.listen(49245)) {
            if (view.getVisibility() != visibility) {
                Transition transition = new Fade();
                if (!ListenerUtil.mutListener.listen(49240)) {
                    transition.setDuration(170);
                }
                if (!ListenerUtil.mutListener.listen(49241)) {
                    transition.addTarget(view);
                }
                if (!ListenerUtil.mutListener.listen(49242)) {
                    TransitionManager.endTransitions((ViewGroup) view.getParent());
                }
                if (!ListenerUtil.mutListener.listen(49243)) {
                    TransitionManager.beginDelayedTransition((ViewGroup) view.getParent(), transition);
                }
                if (!ListenerUtil.mutListener.listen(49244)) {
                    view.setVisibility(visibility);
                }
            }
        }
    }
}

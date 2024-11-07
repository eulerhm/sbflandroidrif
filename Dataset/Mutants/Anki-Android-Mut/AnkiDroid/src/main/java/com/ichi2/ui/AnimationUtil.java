/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.ui;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AnimationUtil {

    /**
     * This is a fast animation - We don't want the user incorrectly selecting the current position
     * for the next collapse operation
     */
    public static final int DURATION_MILLIS = 200;

    public static void collapseView(View view, boolean animationEnabled) {
        if (!ListenerUtil.mutListener.listen(24918)) {
            view.animate().cancel();
        }
        if (!ListenerUtil.mutListener.listen(24920)) {
            if (!animationEnabled) {
                if (!ListenerUtil.mutListener.listen(24919)) {
                    view.setVisibility(View.GONE);
                }
                return;
            }
        }
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation expandAnimation = new ScaleAnimation(1f, 1f, 1f, 0.5f);
        if (!ListenerUtil.mutListener.listen(24921)) {
            expandAnimation.setDuration(DURATION_MILLIS);
        }
        if (!ListenerUtil.mutListener.listen(24923)) {
            expandAnimation.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (!ListenerUtil.mutListener.listen(24922)) {
                        view.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
        if (!ListenerUtil.mutListener.listen(24924)) {
            alphaAnimation.setDuration(DURATION_MILLIS);
        }
        if (!ListenerUtil.mutListener.listen(24925)) {
            alphaAnimation.setFillAfter(true);
        }
        if (!ListenerUtil.mutListener.listen(24926)) {
            set.addAnimation(expandAnimation);
        }
        if (!ListenerUtil.mutListener.listen(24927)) {
            set.addAnimation(alphaAnimation);
        }
        if (!ListenerUtil.mutListener.listen(24928)) {
            view.startAnimation(set);
        }
    }

    public static void expandView(View view, boolean enableAnimation) {
        if (!ListenerUtil.mutListener.listen(24929)) {
            view.animate().cancel();
        }
        if (!ListenerUtil.mutListener.listen(24933)) {
            if (!enableAnimation) {
                if (!ListenerUtil.mutListener.listen(24930)) {
                    view.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(24931)) {
                    view.setAlpha(1.0f);
                }
                if (!ListenerUtil.mutListener.listen(24932)) {
                    view.setScaleY(1.0f);
                }
                return;
            }
        }
        // Sadly this seems necessary - yScale didn't work.
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation resetEditTextScale = new ScaleAnimation(1f, 1f, 1f, 1f);
        if (!ListenerUtil.mutListener.listen(24934)) {
            resetEditTextScale.setDuration(DURATION_MILLIS);
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        if (!ListenerUtil.mutListener.listen(24935)) {
            alphaAnimation.setFillAfter(true);
        }
        if (!ListenerUtil.mutListener.listen(24936)) {
            alphaAnimation.setDuration(DURATION_MILLIS);
        }
        if (!ListenerUtil.mutListener.listen(24937)) {
            set.addAnimation(resetEditTextScale);
        }
        if (!ListenerUtil.mutListener.listen(24938)) {
            set.addAnimation(alphaAnimation);
        }
        if (!ListenerUtil.mutListener.listen(24939)) {
            view.startAnimation(set);
        }
        if (!ListenerUtil.mutListener.listen(24940)) {
            view.setVisibility(View.VISIBLE);
        }
    }
}

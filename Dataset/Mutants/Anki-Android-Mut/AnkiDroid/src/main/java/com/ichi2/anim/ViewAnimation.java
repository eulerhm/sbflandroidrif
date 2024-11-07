package com.ichi2.anim;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ViewAnimation {

    public static final int SLIDE_IN_FROM_RIGHT = 0;

    public static final int SLIDE_OUT_TO_RIGHT = 1;

    public static final int SLIDE_IN_FROM_LEFT = 2;

    public static final int SLIDE_OUT_TO_LEFT = 3;

    public static final int SLIDE_IN_FROM_BOTTOM = 4;

    public static final int SLIDE_IN_FROM_TOP = 5;

    public static final int FADE_IN = 0;

    public static final int FADE_OUT = 1;

    public static Animation slide(int type, int duration, int offset) {
        Animation animation;
        switch(type) {
            case SLIDE_IN_FROM_RIGHT:
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, +1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                if (!ListenerUtil.mutListener.listen(9)) {
                    animation.setInterpolator(new DecelerateInterpolator());
                }
                break;
            case SLIDE_OUT_TO_RIGHT:
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, +1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                if (!ListenerUtil.mutListener.listen(10)) {
                    animation.setInterpolator(new AccelerateInterpolator());
                }
                break;
            case SLIDE_IN_FROM_LEFT:
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                if (!ListenerUtil.mutListener.listen(11)) {
                    animation.setInterpolator(new DecelerateInterpolator());
                }
                break;
            case SLIDE_OUT_TO_LEFT:
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                if (!ListenerUtil.mutListener.listen(12)) {
                    animation.setInterpolator(new AccelerateInterpolator());
                }
                break;
            case SLIDE_IN_FROM_BOTTOM:
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, +1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                if (!ListenerUtil.mutListener.listen(13)) {
                    animation.setInterpolator(new DecelerateInterpolator());
                }
                break;
            case SLIDE_IN_FROM_TOP:
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                if (!ListenerUtil.mutListener.listen(14)) {
                    animation.setInterpolator(new DecelerateInterpolator());
                }
                break;
            default:
                animation = null;
        }
        if (!ListenerUtil.mutListener.listen(15)) {
            animation.setDuration(duration);
        }
        if (!ListenerUtil.mutListener.listen(16)) {
            animation.setStartOffset(offset);
        }
        return animation;
    }

    public static Animation fade(int type, int duration, int offset) {
        Animation animation = new AlphaAnimation((float) type, (ListenerUtil.mutListener.listen(20) ? (1.0f % (float) type) : (ListenerUtil.mutListener.listen(19) ? (1.0f / (float) type) : (ListenerUtil.mutListener.listen(18) ? (1.0f * (float) type) : (ListenerUtil.mutListener.listen(17) ? (1.0f + (float) type) : (1.0f - (float) type))))));
        if (!ListenerUtil.mutListener.listen(21)) {
            animation.setDuration(duration);
        }
        if (!ListenerUtil.mutListener.listen(28)) {
            if ((ListenerUtil.mutListener.listen(26) ? (type >= FADE_IN) : (ListenerUtil.mutListener.listen(25) ? (type <= FADE_IN) : (ListenerUtil.mutListener.listen(24) ? (type > FADE_IN) : (ListenerUtil.mutListener.listen(23) ? (type < FADE_IN) : (ListenerUtil.mutListener.listen(22) ? (type != FADE_IN) : (type == FADE_IN))))))) {
                if (!ListenerUtil.mutListener.listen(27)) {
                    animation.setZAdjustment(Animation.ZORDER_TOP);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29)) {
            animation.setStartOffset(offset);
        }
        return animation;
    }
}

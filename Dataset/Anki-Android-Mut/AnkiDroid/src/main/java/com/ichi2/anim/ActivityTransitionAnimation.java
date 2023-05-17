package com.ichi2.anim;

import android.app.Activity;
import com.ichi2.anki.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ActivityTransitionAnimation {

    public static void slide(Activity activity, Direction direction) {
        if (!ListenerUtil.mutListener.listen(8)) {
            switch(direction) {
                case LEFT:
                    if (!ListenerUtil.mutListener.listen(2)) {
                        activity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                    }
                    break;
                case RIGHT:
                    if (!ListenerUtil.mutListener.listen(3)) {
                        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
                    }
                    break;
                case FADE:
                    if (!ListenerUtil.mutListener.listen(4)) {
                        activity.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    }
                    break;
                case UP:
                    if (!ListenerUtil.mutListener.listen(5)) {
                        activity.overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
                    }
                    break;
                case DIALOG_EXIT:
                    if (!ListenerUtil.mutListener.listen(6)) {
                        activity.overridePendingTransition(R.anim.none, R.anim.dialog_exit);
                    }
                    break;
                case NONE:
                    if (!ListenerUtil.mutListener.listen(7)) {
                        activity.overridePendingTransition(R.anim.none, R.anim.none);
                    }
                    break;
                // DOWN:
                default:
            }
        }
    }

    public enum Direction {

        LEFT,
        RIGHT,
        FADE,
        UP,
        DOWN,
        DIALOG_EXIT,
        NONE
    }
}

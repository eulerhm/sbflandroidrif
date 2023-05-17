package com.ichi2.anki.reviewer;

import android.view.View;
import android.widget.ImageView;
import com.ichi2.anki.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handles the star and flag marker for the card viewer
 */
public class CardMarker {

    public static final int FLAG_NONE = 0;

    public static final int FLAG_RED = 1;

    public static final int FLAG_ORANGE = 2;

    public static final int FLAG_GREEN = 3;

    public static final int FLAG_BLUE = 4;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ FLAG_NONE, FLAG_RED, FLAG_ORANGE, FLAG_GREEN, FLAG_BLUE })
    public @interface FlagDef {
    }

    @NonNull
    private final ImageView markView;

    @NonNull
    private final ImageView flagView;

    public CardMarker(@NonNull ImageView markView, @NonNull ImageView flagView) {
        this.markView = markView;
        this.flagView = flagView;
    }

    /**
     * Sets the mark icon on a card (the star)
     */
    public void displayMark(boolean markStatus) {
        if (!ListenerUtil.mutListener.listen(2956)) {
            if (markStatus) {
                if (!ListenerUtil.mutListener.listen(2954)) {
                    markView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(2955)) {
                    markView.setImageResource(R.drawable.ic_star_white_bordered_24dp);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2953)) {
                    markView.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    /**
     * Sets the flag icon on the card
     */
    public void displayFlag(@FlagDef int flagStatus) {
        if (!ListenerUtil.mutListener.listen(2962)) {
            switch(flagStatus) {
                case FLAG_RED:
                    if (!ListenerUtil.mutListener.listen(2957)) {
                        setFlagView(R.drawable.ic_flag_red);
                    }
                    break;
                case FLAG_ORANGE:
                    if (!ListenerUtil.mutListener.listen(2958)) {
                        setFlagView(R.drawable.ic_flag_orange);
                    }
                    break;
                case FLAG_GREEN:
                    if (!ListenerUtil.mutListener.listen(2959)) {
                        setFlagView(R.drawable.ic_flag_green);
                    }
                    break;
                case FLAG_BLUE:
                    if (!ListenerUtil.mutListener.listen(2960)) {
                        setFlagView(R.drawable.ic_flag_blue);
                    }
                    break;
                case FLAG_NONE:
                default:
                    if (!ListenerUtil.mutListener.listen(2961)) {
                        flagView.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    }

    private void setFlagView(@DrawableRes int drawableId) {
        if (!ListenerUtil.mutListener.listen(2963)) {
            // set the resource before to ensure we display the correct icon.
            flagView.setImageResource(drawableId);
        }
        if (!ListenerUtil.mutListener.listen(2964)) {
            flagView.setVisibility(View.VISIBLE);
        }
    }
}

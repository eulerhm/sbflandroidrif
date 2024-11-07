package fr.free.nrw.commons.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A card view which informs onSwipe events to its child
 */
public abstract class SwipableCardView extends CardView {

    float x1, x2;

    private static final float MINIMUM_THRESHOLD_FOR_SWIPE = 100;

    public SwipableCardView(@NonNull Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(2195)) {
            interceptOnTouchListener();
        }
    }

    public SwipableCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(2196)) {
            interceptOnTouchListener();
        }
    }

    public SwipableCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(2197)) {
            interceptOnTouchListener();
        }
    }

    private void interceptOnTouchListener() {
        if (!ListenerUtil.mutListener.listen(2198)) {
            this.setOnTouchListener((v, event) -> {
                boolean isSwipe = false;
                float deltaX = 0.0f;
                Timber.e(event.getAction() + "");
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        deltaX = x2 - x1;
                        if (deltaX < 0) {
                            // Right to left swipe
                            isSwipe = true;
                        } else if (deltaX > 0) {
                            // Left to right swipe
                            isSwipe = true;
                        }
                        break;
                }
                if (isSwipe && (pixelToDp(Math.abs(deltaX)) > MINIMUM_THRESHOLD_FOR_SWIPE)) {
                    return onSwipe(v);
                }
                return false;
            });
        }
    }

    /**
     * abstract function which informs swipe events to those who have inherited from it
     */
    public abstract boolean onSwipe(View view);

    private float pixelToDp(float pixels) {
        return ((ListenerUtil.mutListener.listen(2202) ? (pixels % Resources.getSystem().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2201) ? (pixels * Resources.getSystem().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2200) ? (pixels - Resources.getSystem().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2199) ? (pixels + Resources.getSystem().getDisplayMetrics().density) : (pixels / Resources.getSystem().getDisplayMetrics().density))))));
    }
}

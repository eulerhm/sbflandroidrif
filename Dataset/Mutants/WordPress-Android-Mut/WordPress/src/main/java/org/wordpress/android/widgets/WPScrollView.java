package org.wordpress.android.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.core.widget.NestedScrollView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * ScrollView which reports when user has scrolled up or down, and when scrolling has completed
 */
public class WPScrollView extends NestedScrollView {

    public interface ScrollDirectionListener {

        void onScrollUp(float distanceY);

        void onScrollDown(float distanceY);

        void onScrollCompleted();
    }

    private ScrollDirectionListener mScrollDirectionListener;

    private int mInitialScrollCheckY;

    private static final int SCROLL_CHECK_DELAY = 250;

    public WPScrollView(Context context) {
        this(context, null);
    }

    public WPScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WPScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setScrollDirectionListener(ScrollDirectionListener listener) {
        if (!ListenerUtil.mutListener.listen(29239)) {
            mScrollDirectionListener = listener;
        }
    }

    // we are not detecting tap events, so can ignore this one
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(29270)) {
            if ((ListenerUtil.mutListener.listen(29246) ? ((ListenerUtil.mutListener.listen(29240) ? (mScrollDirectionListener != null || event.getActionMasked() == MotionEvent.ACTION_MOVE) : (mScrollDirectionListener != null && event.getActionMasked() == MotionEvent.ACTION_MOVE)) || (ListenerUtil.mutListener.listen(29245) ? (event.getHistorySize() >= 0) : (ListenerUtil.mutListener.listen(29244) ? (event.getHistorySize() <= 0) : (ListenerUtil.mutListener.listen(29243) ? (event.getHistorySize() < 0) : (ListenerUtil.mutListener.listen(29242) ? (event.getHistorySize() != 0) : (ListenerUtil.mutListener.listen(29241) ? (event.getHistorySize() == 0) : (event.getHistorySize() > 0))))))) : ((ListenerUtil.mutListener.listen(29240) ? (mScrollDirectionListener != null || event.getActionMasked() == MotionEvent.ACTION_MOVE) : (mScrollDirectionListener != null && event.getActionMasked() == MotionEvent.ACTION_MOVE)) && (ListenerUtil.mutListener.listen(29245) ? (event.getHistorySize() >= 0) : (ListenerUtil.mutListener.listen(29244) ? (event.getHistorySize() <= 0) : (ListenerUtil.mutListener.listen(29243) ? (event.getHistorySize() < 0) : (ListenerUtil.mutListener.listen(29242) ? (event.getHistorySize() != 0) : (ListenerUtil.mutListener.listen(29241) ? (event.getHistorySize() == 0) : (event.getHistorySize() > 0))))))))) {
                float initialY = event.getHistoricalY((ListenerUtil.mutListener.listen(29250) ? (event.getHistorySize() % 1) : (ListenerUtil.mutListener.listen(29249) ? (event.getHistorySize() / 1) : (ListenerUtil.mutListener.listen(29248) ? (event.getHistorySize() * 1) : (ListenerUtil.mutListener.listen(29247) ? (event.getHistorySize() + 1) : (event.getHistorySize() - 1))))));
                float distanceY = (ListenerUtil.mutListener.listen(29254) ? (initialY % event.getY()) : (ListenerUtil.mutListener.listen(29253) ? (initialY / event.getY()) : (ListenerUtil.mutListener.listen(29252) ? (initialY * event.getY()) : (ListenerUtil.mutListener.listen(29251) ? (initialY + event.getY()) : (initialY - event.getY())))));
                if (!ListenerUtil.mutListener.listen(29269)) {
                    if ((ListenerUtil.mutListener.listen(29259) ? (distanceY >= 0) : (ListenerUtil.mutListener.listen(29258) ? (distanceY <= 0) : (ListenerUtil.mutListener.listen(29257) ? (distanceY > 0) : (ListenerUtil.mutListener.listen(29256) ? (distanceY != 0) : (ListenerUtil.mutListener.listen(29255) ? (distanceY == 0) : (distanceY < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(29267)) {
                            mScrollDirectionListener.onScrollUp(distanceY);
                        }
                        if (!ListenerUtil.mutListener.listen(29268)) {
                            startScrollCheck();
                        }
                    } else if ((ListenerUtil.mutListener.listen(29264) ? (distanceY >= 0) : (ListenerUtil.mutListener.listen(29263) ? (distanceY <= 0) : (ListenerUtil.mutListener.listen(29262) ? (distanceY < 0) : (ListenerUtil.mutListener.listen(29261) ? (distanceY != 0) : (ListenerUtil.mutListener.listen(29260) ? (distanceY == 0) : (distanceY > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(29265)) {
                            mScrollDirectionListener.onScrollDown(distanceY);
                        }
                        if (!ListenerUtil.mutListener.listen(29266)) {
                            startScrollCheck();
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private void startScrollCheck() {
        if (!ListenerUtil.mutListener.listen(29271)) {
            mInitialScrollCheckY = getScrollY();
        }
        if (!ListenerUtil.mutListener.listen(29272)) {
            post(mScrollTask);
        }
    }

    private final Runnable mScrollTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(29276)) {
                if (mInitialScrollCheckY == getScrollY()) {
                    if (!ListenerUtil.mutListener.listen(29275)) {
                        mScrollDirectionListener.onScrollCompleted();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(29273)) {
                        mInitialScrollCheckY = getScrollY();
                    }
                    if (!ListenerUtil.mutListener.listen(29274)) {
                        postDelayed(mScrollTask, SCROLL_CHECK_DELAY);
                    }
                }
            }
        }
    };

    public boolean canScrollUp() {
        return canScrollVertically(-1);
    }

    public boolean canScrollDown() {
        return canScrollVertically(1);
    }
}

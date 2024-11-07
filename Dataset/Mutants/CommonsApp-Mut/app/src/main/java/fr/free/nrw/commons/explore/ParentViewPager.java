package fr.free.nrw.commons.explore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * ParentViewPager A custom viewPager whose scrolling can be enabled and disabled.
 */
public class ParentViewPager extends ViewPager {

    /**
     * Boolean variable that stores the current state of pager scroll i.e(enabled or disabled)
     */
    private boolean canScroll = true;

    /**
     * Default constructors
     */
    public ParentViewPager(Context context) {
        super(context);
    }

    public ParentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Setter method for canScroll.
     */
    public void setCanScroll(boolean canScroll) {
        if (!ListenerUtil.mutListener.listen(4560)) {
            this.canScroll = canScroll;
        }
    }

    /**
     * Getter method for canScroll.
     */
    public boolean isCanScroll() {
        return canScroll;
    }

    /**
     * Method that prevents scrolling if canScroll is set to false.
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return (ListenerUtil.mutListener.listen(4561) ? (canScroll || super.onTouchEvent(ev)) : (canScroll && super.onTouchEvent(ev)));
    }

    /**
     * A facilitator method that allows parent to intercept touch events before its children. thus
     * making it possible to prevent swiping parent on child end.
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return (ListenerUtil.mutListener.listen(4562) ? (canScroll || super.onInterceptTouchEvent(ev)) : (canScroll && super.onInterceptTouchEvent(ev)));
    }
}

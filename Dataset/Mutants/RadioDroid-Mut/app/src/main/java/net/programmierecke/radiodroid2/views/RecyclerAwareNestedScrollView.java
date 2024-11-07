package net.programmierecke.radiodroid2.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Field;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Credits to Alex Lockwood blogpost "Experimenting with Nested Scrolling"
 * Credits to https://stackoverflow.com/questions/31829976/onclick-method-not-working-properly-after-nestedscrollview-scrolled
 * <p>
 * Allows scroll view to have {@link RecyclerView} alongside with other content in it and be scrolled
 * as expected by user.
 * <p>
 * The NestedScrollView should steal the scroll/fling events away from
 * the RecyclerView if either is true:
 * - the user is dragging their finger down and the RecyclerView is scrolled to the top of its content
 * - the user is dragging their finger up and the NestedScrollView is not scrolled to the bottom of its content.
 */
public class RecyclerAwareNestedScrollView extends NestedScrollView {

    private OverScroller mScroller;

    public boolean isFling = false;

    public RecyclerAwareNestedScrollView(@NonNull Context context) {
        this(context, null);
    }

    public RecyclerAwareNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerAwareNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(3476)) {
            mScroller = getOverScroller();
        }
    }

    @Override
    public void fling(int velocityY) {
        if (!ListenerUtil.mutListener.listen(3477)) {
            super.fling(velocityY);
        }
        if (!ListenerUtil.mutListener.listen(3485)) {
            // Here we effectively extend the super class functionality for backwards compatibility and just call invalidateOnAnimation()
            if ((ListenerUtil.mutListener.listen(3482) ? (getChildCount() >= 0) : (ListenerUtil.mutListener.listen(3481) ? (getChildCount() <= 0) : (ListenerUtil.mutListener.listen(3480) ? (getChildCount() < 0) : (ListenerUtil.mutListener.listen(3479) ? (getChildCount() != 0) : (ListenerUtil.mutListener.listen(3478) ? (getChildCount() == 0) : (getChildCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(3483)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                if (!ListenerUtil.mutListener.listen(3484)) {
                    // Initializing isFling to true to track fling action in onScrollChanged() method
                    isFling = true;
                }
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, final int t, final int oldl, int oldt) {
        if (!ListenerUtil.mutListener.listen(3486)) {
            super.onScrollChanged(l, t, oldl, oldt);
        }
        if (!ListenerUtil.mutListener.listen(3511)) {
            if (isFling) {
                if (!ListenerUtil.mutListener.listen(3510)) {
                    if ((ListenerUtil.mutListener.listen(3506) ? ((ListenerUtil.mutListener.listen(3501) ? ((ListenerUtil.mutListener.listen(3495) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) >= 3) : (ListenerUtil.mutListener.listen(3494) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) > 3) : (ListenerUtil.mutListener.listen(3493) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) < 3) : (ListenerUtil.mutListener.listen(3492) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) != 3) : (ListenerUtil.mutListener.listen(3491) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) == 3) : (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) <= 3)))))) && (ListenerUtil.mutListener.listen(3500) ? (t >= 0) : (ListenerUtil.mutListener.listen(3499) ? (t <= 0) : (ListenerUtil.mutListener.listen(3498) ? (t > 0) : (ListenerUtil.mutListener.listen(3497) ? (t < 0) : (ListenerUtil.mutListener.listen(3496) ? (t != 0) : (t == 0))))))) : ((ListenerUtil.mutListener.listen(3495) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) >= 3) : (ListenerUtil.mutListener.listen(3494) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) > 3) : (ListenerUtil.mutListener.listen(3493) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) < 3) : (ListenerUtil.mutListener.listen(3492) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) != 3) : (ListenerUtil.mutListener.listen(3491) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) == 3) : (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) <= 3)))))) || (ListenerUtil.mutListener.listen(3500) ? (t >= 0) : (ListenerUtil.mutListener.listen(3499) ? (t <= 0) : (ListenerUtil.mutListener.listen(3498) ? (t > 0) : (ListenerUtil.mutListener.listen(3497) ? (t < 0) : (ListenerUtil.mutListener.listen(3496) ? (t != 0) : (t == 0)))))))) && t == ((ListenerUtil.mutListener.listen(3505) ? (getChildAt(0).getMeasuredHeight() % getMeasuredHeight()) : (ListenerUtil.mutListener.listen(3504) ? (getChildAt(0).getMeasuredHeight() / getMeasuredHeight()) : (ListenerUtil.mutListener.listen(3503) ? (getChildAt(0).getMeasuredHeight() * getMeasuredHeight()) : (ListenerUtil.mutListener.listen(3502) ? (getChildAt(0).getMeasuredHeight() + getMeasuredHeight()) : (getChildAt(0).getMeasuredHeight() - getMeasuredHeight()))))))) : ((ListenerUtil.mutListener.listen(3501) ? ((ListenerUtil.mutListener.listen(3495) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) >= 3) : (ListenerUtil.mutListener.listen(3494) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) > 3) : (ListenerUtil.mutListener.listen(3493) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) < 3) : (ListenerUtil.mutListener.listen(3492) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) != 3) : (ListenerUtil.mutListener.listen(3491) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) == 3) : (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) <= 3)))))) && (ListenerUtil.mutListener.listen(3500) ? (t >= 0) : (ListenerUtil.mutListener.listen(3499) ? (t <= 0) : (ListenerUtil.mutListener.listen(3498) ? (t > 0) : (ListenerUtil.mutListener.listen(3497) ? (t < 0) : (ListenerUtil.mutListener.listen(3496) ? (t != 0) : (t == 0))))))) : ((ListenerUtil.mutListener.listen(3495) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) >= 3) : (ListenerUtil.mutListener.listen(3494) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) > 3) : (ListenerUtil.mutListener.listen(3493) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) < 3) : (ListenerUtil.mutListener.listen(3492) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) != 3) : (ListenerUtil.mutListener.listen(3491) ? (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) == 3) : (Math.abs((ListenerUtil.mutListener.listen(3490) ? (t % oldt) : (ListenerUtil.mutListener.listen(3489) ? (t / oldt) : (ListenerUtil.mutListener.listen(3488) ? (t * oldt) : (ListenerUtil.mutListener.listen(3487) ? (t + oldt) : (t - oldt)))))) <= 3)))))) || (ListenerUtil.mutListener.listen(3500) ? (t >= 0) : (ListenerUtil.mutListener.listen(3499) ? (t <= 0) : (ListenerUtil.mutListener.listen(3498) ? (t > 0) : (ListenerUtil.mutListener.listen(3497) ? (t < 0) : (ListenerUtil.mutListener.listen(3496) ? (t != 0) : (t == 0)))))))) || t == ((ListenerUtil.mutListener.listen(3505) ? (getChildAt(0).getMeasuredHeight() % getMeasuredHeight()) : (ListenerUtil.mutListener.listen(3504) ? (getChildAt(0).getMeasuredHeight() / getMeasuredHeight()) : (ListenerUtil.mutListener.listen(3503) ? (getChildAt(0).getMeasuredHeight() * getMeasuredHeight()) : (ListenerUtil.mutListener.listen(3502) ? (getChildAt(0).getMeasuredHeight() + getMeasuredHeight()) : (getChildAt(0).getMeasuredHeight() - getMeasuredHeight()))))))))) {
                        if (!ListenerUtil.mutListener.listen(3507)) {
                            isFling = false;
                        }
                        if (!ListenerUtil.mutListener.listen(3509)) {
                            // This forces the mFinish variable in scroller to true and does the trick
                            if (mScroller != null) {
                                if (!ListenerUtil.mutListener.listen(3508)) {
                                    mScroller.abortAnimation();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        final RecyclerView rv = (RecyclerView) target;
        if (!ListenerUtil.mutListener.listen(3527)) {
            if ((ListenerUtil.mutListener.listen(3524) ? (((ListenerUtil.mutListener.listen(3517) ? ((ListenerUtil.mutListener.listen(3516) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3515) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3514) ? (dy > 0) : (ListenerUtil.mutListener.listen(3513) ? (dy != 0) : (ListenerUtil.mutListener.listen(3512) ? (dy == 0) : (dy < 0)))))) || isRvScrolledToTop(rv)) : ((ListenerUtil.mutListener.listen(3516) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3515) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3514) ? (dy > 0) : (ListenerUtil.mutListener.listen(3513) ? (dy != 0) : (ListenerUtil.mutListener.listen(3512) ? (dy == 0) : (dy < 0)))))) && isRvScrolledToTop(rv)))) && ((ListenerUtil.mutListener.listen(3523) ? ((ListenerUtil.mutListener.listen(3522) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3521) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3520) ? (dy < 0) : (ListenerUtil.mutListener.listen(3519) ? (dy != 0) : (ListenerUtil.mutListener.listen(3518) ? (dy == 0) : (dy > 0)))))) || !isNsvScrolledToBottom(this)) : ((ListenerUtil.mutListener.listen(3522) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3521) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3520) ? (dy < 0) : (ListenerUtil.mutListener.listen(3519) ? (dy != 0) : (ListenerUtil.mutListener.listen(3518) ? (dy == 0) : (dy > 0)))))) && !isNsvScrolledToBottom(this))))) : (((ListenerUtil.mutListener.listen(3517) ? ((ListenerUtil.mutListener.listen(3516) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3515) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3514) ? (dy > 0) : (ListenerUtil.mutListener.listen(3513) ? (dy != 0) : (ListenerUtil.mutListener.listen(3512) ? (dy == 0) : (dy < 0)))))) || isRvScrolledToTop(rv)) : ((ListenerUtil.mutListener.listen(3516) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3515) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3514) ? (dy > 0) : (ListenerUtil.mutListener.listen(3513) ? (dy != 0) : (ListenerUtil.mutListener.listen(3512) ? (dy == 0) : (dy < 0)))))) && isRvScrolledToTop(rv)))) || ((ListenerUtil.mutListener.listen(3523) ? ((ListenerUtil.mutListener.listen(3522) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3521) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3520) ? (dy < 0) : (ListenerUtil.mutListener.listen(3519) ? (dy != 0) : (ListenerUtil.mutListener.listen(3518) ? (dy == 0) : (dy > 0)))))) || !isNsvScrolledToBottom(this)) : ((ListenerUtil.mutListener.listen(3522) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3521) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3520) ? (dy < 0) : (ListenerUtil.mutListener.listen(3519) ? (dy != 0) : (ListenerUtil.mutListener.listen(3518) ? (dy == 0) : (dy > 0)))))) && !isNsvScrolledToBottom(this))))))) {
                if (!ListenerUtil.mutListener.listen(3525)) {
                    // (so that the RecyclerView will know not to perform the scroll as well).
                    scrollBy(0, dy);
                }
                if (!ListenerUtil.mutListener.listen(3526)) {
                    consumed[1] = dy;
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3528)) {
            super.onNestedPreScroll(target, dx, dy, consumed, type);
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velX, float velY) {
        final RecyclerView rv = (RecyclerView) target;
        if (!ListenerUtil.mutListener.listen(3543)) {
            if ((ListenerUtil.mutListener.listen(3541) ? (((ListenerUtil.mutListener.listen(3534) ? ((ListenerUtil.mutListener.listen(3533) ? (velY >= 0) : (ListenerUtil.mutListener.listen(3532) ? (velY <= 0) : (ListenerUtil.mutListener.listen(3531) ? (velY > 0) : (ListenerUtil.mutListener.listen(3530) ? (velY != 0) : (ListenerUtil.mutListener.listen(3529) ? (velY == 0) : (velY < 0)))))) || isRvScrolledToTop(rv)) : ((ListenerUtil.mutListener.listen(3533) ? (velY >= 0) : (ListenerUtil.mutListener.listen(3532) ? (velY <= 0) : (ListenerUtil.mutListener.listen(3531) ? (velY > 0) : (ListenerUtil.mutListener.listen(3530) ? (velY != 0) : (ListenerUtil.mutListener.listen(3529) ? (velY == 0) : (velY < 0)))))) && isRvScrolledToTop(rv)))) && ((ListenerUtil.mutListener.listen(3540) ? ((ListenerUtil.mutListener.listen(3539) ? (velY >= 0) : (ListenerUtil.mutListener.listen(3538) ? (velY <= 0) : (ListenerUtil.mutListener.listen(3537) ? (velY < 0) : (ListenerUtil.mutListener.listen(3536) ? (velY != 0) : (ListenerUtil.mutListener.listen(3535) ? (velY == 0) : (velY > 0)))))) || !isNsvScrolledToBottom(this)) : ((ListenerUtil.mutListener.listen(3539) ? (velY >= 0) : (ListenerUtil.mutListener.listen(3538) ? (velY <= 0) : (ListenerUtil.mutListener.listen(3537) ? (velY < 0) : (ListenerUtil.mutListener.listen(3536) ? (velY != 0) : (ListenerUtil.mutListener.listen(3535) ? (velY == 0) : (velY > 0)))))) && !isNsvScrolledToBottom(this))))) : (((ListenerUtil.mutListener.listen(3534) ? ((ListenerUtil.mutListener.listen(3533) ? (velY >= 0) : (ListenerUtil.mutListener.listen(3532) ? (velY <= 0) : (ListenerUtil.mutListener.listen(3531) ? (velY > 0) : (ListenerUtil.mutListener.listen(3530) ? (velY != 0) : (ListenerUtil.mutListener.listen(3529) ? (velY == 0) : (velY < 0)))))) || isRvScrolledToTop(rv)) : ((ListenerUtil.mutListener.listen(3533) ? (velY >= 0) : (ListenerUtil.mutListener.listen(3532) ? (velY <= 0) : (ListenerUtil.mutListener.listen(3531) ? (velY > 0) : (ListenerUtil.mutListener.listen(3530) ? (velY != 0) : (ListenerUtil.mutListener.listen(3529) ? (velY == 0) : (velY < 0)))))) && isRvScrolledToTop(rv)))) || ((ListenerUtil.mutListener.listen(3540) ? ((ListenerUtil.mutListener.listen(3539) ? (velY >= 0) : (ListenerUtil.mutListener.listen(3538) ? (velY <= 0) : (ListenerUtil.mutListener.listen(3537) ? (velY < 0) : (ListenerUtil.mutListener.listen(3536) ? (velY != 0) : (ListenerUtil.mutListener.listen(3535) ? (velY == 0) : (velY > 0)))))) || !isNsvScrolledToBottom(this)) : ((ListenerUtil.mutListener.listen(3539) ? (velY >= 0) : (ListenerUtil.mutListener.listen(3538) ? (velY <= 0) : (ListenerUtil.mutListener.listen(3537) ? (velY < 0) : (ListenerUtil.mutListener.listen(3536) ? (velY != 0) : (ListenerUtil.mutListener.listen(3535) ? (velY == 0) : (velY > 0)))))) && !isNsvScrolledToBottom(this))))))) {
                if (!ListenerUtil.mutListener.listen(3542)) {
                    // will know not to perform the fling as well).
                    fling((int) velY);
                }
                return true;
            }
        }
        return super.onNestedPreFling(target, velX, velY);
    }

    /**
     * Returns true if the NestedScrollView is scrolled to the bottom of its
     * content (i.e. if the card's inner RecyclerView is completely visible).
     */
    private static boolean isNsvScrolledToBottom(NestedScrollView nsv) {
        return !nsv.canScrollVertically(1);
    }

    /**
     * Returns true iff the RecyclerView is scrolled to the top of its
     * content (i.e. if the RecyclerView's first item is completely visible).
     */
    private static boolean isRvScrolledToTop(RecyclerView rv) {
        final LinearLayoutManager lm = (LinearLayoutManager) rv.getLayoutManager();
        return (ListenerUtil.mutListener.listen(3544) ? (lm.findFirstVisibleItemPosition() == 0 || lm.findViewByPosition(0).getTop() == 0) : (lm.findFirstVisibleItemPosition() == 0 && lm.findViewByPosition(0).getTop() == 0));
    }

    private OverScroller getOverScroller() {
        Field fs;
        try {
            fs = this.getClass().getSuperclass().getDeclaredField("mScroller");
            if (!ListenerUtil.mutListener.listen(3545)) {
                fs.setAccessible(true);
            }
            return (OverScroller) fs.get(this);
        } catch (Throwable t) {
            return null;
        }
    }
}

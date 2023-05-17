package org.wordpress.android.widgets;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ScrollView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.core.widget.NestedScrollView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Code copied from android.support.v4.widget.NestedScrollView with one modification in the AccessibilityDelegate
 * .performAccessibilityAction(..) method. There is a bug in the Support Library which breaks scroll in the
 * Accessibility Switch Control mode.
 * Steps to reproduce the bug
 * 1. Go to Stats
 * 2. Scroll to the bottom using the Switch Control
 * 3. Try to scroll to the top -> clicking on the scrollView doesn't do anything.
 * <p>
 * WARNING - Do not modify this file, so we can remove it, when the bug in the support library is fixed.
 * https://issuetracker.google.com/issues/68366782
 * https://issuetracker.google.com/issues/70310373
 */
public class WPNestedScrollView extends NestedScrollView {

    private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();

    public WPNestedScrollView(Context context) {
        this(context, null);
    }

    public WPNestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WPNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(29042)) {
            ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
        }
    }

    int getScrollRange() {
        int scrollRange = 0;
        if (!ListenerUtil.mutListener.listen(29061)) {
            if ((ListenerUtil.mutListener.listen(29047) ? (getChildCount() >= 0) : (ListenerUtil.mutListener.listen(29046) ? (getChildCount() <= 0) : (ListenerUtil.mutListener.listen(29045) ? (getChildCount() < 0) : (ListenerUtil.mutListener.listen(29044) ? (getChildCount() != 0) : (ListenerUtil.mutListener.listen(29043) ? (getChildCount() == 0) : (getChildCount() > 0))))))) {
                View child = getChildAt(0);
                if (!ListenerUtil.mutListener.listen(29060)) {
                    scrollRange = Math.max(0, (ListenerUtil.mutListener.listen(29059) ? (child.getHeight() % ((ListenerUtil.mutListener.listen(29055) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) % getPaddingTop()) : (ListenerUtil.mutListener.listen(29054) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) / getPaddingTop()) : (ListenerUtil.mutListener.listen(29053) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) * getPaddingTop()) : (ListenerUtil.mutListener.listen(29052) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) + getPaddingTop()) : ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) - getPaddingTop()))))))) : (ListenerUtil.mutListener.listen(29058) ? (child.getHeight() / ((ListenerUtil.mutListener.listen(29055) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) % getPaddingTop()) : (ListenerUtil.mutListener.listen(29054) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) / getPaddingTop()) : (ListenerUtil.mutListener.listen(29053) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) * getPaddingTop()) : (ListenerUtil.mutListener.listen(29052) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) + getPaddingTop()) : ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) - getPaddingTop()))))))) : (ListenerUtil.mutListener.listen(29057) ? (child.getHeight() * ((ListenerUtil.mutListener.listen(29055) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) % getPaddingTop()) : (ListenerUtil.mutListener.listen(29054) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) / getPaddingTop()) : (ListenerUtil.mutListener.listen(29053) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) * getPaddingTop()) : (ListenerUtil.mutListener.listen(29052) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) + getPaddingTop()) : ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) - getPaddingTop()))))))) : (ListenerUtil.mutListener.listen(29056) ? (child.getHeight() + ((ListenerUtil.mutListener.listen(29055) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) % getPaddingTop()) : (ListenerUtil.mutListener.listen(29054) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) / getPaddingTop()) : (ListenerUtil.mutListener.listen(29053) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) * getPaddingTop()) : (ListenerUtil.mutListener.listen(29052) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) + getPaddingTop()) : ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) - getPaddingTop()))))))) : (child.getHeight() - ((ListenerUtil.mutListener.listen(29055) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) % getPaddingTop()) : (ListenerUtil.mutListener.listen(29054) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) / getPaddingTop()) : (ListenerUtil.mutListener.listen(29053) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) * getPaddingTop()) : (ListenerUtil.mutListener.listen(29052) ? ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) + getPaddingTop()) : ((ListenerUtil.mutListener.listen(29051) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(29050) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(29049) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(29048) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))) - getPaddingTop()))))))))))));
                }
            }
        }
        return scrollRange;
    }

    static class AccessibilityDelegate extends AccessibilityDelegateCompat {

        @Override
        public boolean performAccessibilityAction(View host, int action, Bundle arguments) {
            if (!ListenerUtil.mutListener.listen(29062)) {
                if (super.performAccessibilityAction(host, action, arguments)) {
                    return true;
                }
            }
            final WPNestedScrollView nsvHost = (WPNestedScrollView) host;
            if (!ListenerUtil.mutListener.listen(29063)) {
                if (!nsvHost.isEnabled()) {
                    return false;
                }
            }
            int viewportHeight;
            int targetScrollY;
            switch(action) {
                case AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD:
                    if (!ListenerUtil.mutListener.listen(29064)) {
                        nsvHost.fling(0);
                    }
                    viewportHeight = (ListenerUtil.mutListener.listen(29072) ? ((ListenerUtil.mutListener.listen(29068) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29067) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29066) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29065) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) % nsvHost.getPaddingTop()) : (ListenerUtil.mutListener.listen(29071) ? ((ListenerUtil.mutListener.listen(29068) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29067) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29066) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29065) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) / nsvHost.getPaddingTop()) : (ListenerUtil.mutListener.listen(29070) ? ((ListenerUtil.mutListener.listen(29068) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29067) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29066) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29065) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) * nsvHost.getPaddingTop()) : (ListenerUtil.mutListener.listen(29069) ? ((ListenerUtil.mutListener.listen(29068) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29067) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29066) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29065) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) + nsvHost.getPaddingTop()) : ((ListenerUtil.mutListener.listen(29068) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29067) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29066) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29065) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) - nsvHost.getPaddingTop())))));
                    targetScrollY = Math.min(nsvHost.getScrollY() + viewportHeight, nsvHost.getScrollRange());
                    if (!ListenerUtil.mutListener.listen(29074)) {
                        if (targetScrollY != nsvHost.getScrollY()) {
                            if (!ListenerUtil.mutListener.listen(29073)) {
                                nsvHost.smoothScrollTo(0, targetScrollY);
                            }
                            return true;
                        }
                    }
                    return false;
                case AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD:
                    if (!ListenerUtil.mutListener.listen(29075)) {
                        nsvHost.fling(0);
                    }
                    viewportHeight = (ListenerUtil.mutListener.listen(29083) ? ((ListenerUtil.mutListener.listen(29079) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29078) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29077) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29076) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) % nsvHost.getPaddingTop()) : (ListenerUtil.mutListener.listen(29082) ? ((ListenerUtil.mutListener.listen(29079) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29078) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29077) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29076) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) / nsvHost.getPaddingTop()) : (ListenerUtil.mutListener.listen(29081) ? ((ListenerUtil.mutListener.listen(29079) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29078) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29077) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29076) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) * nsvHost.getPaddingTop()) : (ListenerUtil.mutListener.listen(29080) ? ((ListenerUtil.mutListener.listen(29079) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29078) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29077) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29076) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) + nsvHost.getPaddingTop()) : ((ListenerUtil.mutListener.listen(29079) ? (nsvHost.getHeight() % nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29078) ? (nsvHost.getHeight() / nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29077) ? (nsvHost.getHeight() * nsvHost.getPaddingBottom()) : (ListenerUtil.mutListener.listen(29076) ? (nsvHost.getHeight() + nsvHost.getPaddingBottom()) : (nsvHost.getHeight() - nsvHost.getPaddingBottom()))))) - nsvHost.getPaddingTop())))));
                    targetScrollY = Math.max((ListenerUtil.mutListener.listen(29087) ? (nsvHost.getScrollY() % viewportHeight) : (ListenerUtil.mutListener.listen(29086) ? (nsvHost.getScrollY() / viewportHeight) : (ListenerUtil.mutListener.listen(29085) ? (nsvHost.getScrollY() * viewportHeight) : (ListenerUtil.mutListener.listen(29084) ? (nsvHost.getScrollY() + viewportHeight) : (nsvHost.getScrollY() - viewportHeight))))), 0);
                    if (!ListenerUtil.mutListener.listen(29089)) {
                        if (targetScrollY != nsvHost.getScrollY()) {
                            if (!ListenerUtil.mutListener.listen(29088)) {
                                nsvHost.smoothScrollTo(0, targetScrollY);
                            }
                            return true;
                        }
                    }
                    return false;
            }
            return false;
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            if (!ListenerUtil.mutListener.listen(29090)) {
                super.onInitializeAccessibilityNodeInfo(host, info);
            }
            final WPNestedScrollView nsvHost = (WPNestedScrollView) host;
            if (!ListenerUtil.mutListener.listen(29091)) {
                info.setClassName(ScrollView.class.getName());
            }
            if (!ListenerUtil.mutListener.listen(29113)) {
                if (nsvHost.isEnabled()) {
                    final int scrollRange = nsvHost.getScrollRange();
                    if (!ListenerUtil.mutListener.listen(29112)) {
                        if ((ListenerUtil.mutListener.listen(29096) ? (scrollRange >= 0) : (ListenerUtil.mutListener.listen(29095) ? (scrollRange <= 0) : (ListenerUtil.mutListener.listen(29094) ? (scrollRange < 0) : (ListenerUtil.mutListener.listen(29093) ? (scrollRange != 0) : (ListenerUtil.mutListener.listen(29092) ? (scrollRange == 0) : (scrollRange > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(29097)) {
                                info.setScrollable(true);
                            }
                            if (!ListenerUtil.mutListener.listen(29104)) {
                                if ((ListenerUtil.mutListener.listen(29102) ? (nsvHost.getScrollY() >= 0) : (ListenerUtil.mutListener.listen(29101) ? (nsvHost.getScrollY() <= 0) : (ListenerUtil.mutListener.listen(29100) ? (nsvHost.getScrollY() < 0) : (ListenerUtil.mutListener.listen(29099) ? (nsvHost.getScrollY() != 0) : (ListenerUtil.mutListener.listen(29098) ? (nsvHost.getScrollY() == 0) : (nsvHost.getScrollY() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(29103)) {
                                        info.addAction(AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(29111)) {
                                if ((ListenerUtil.mutListener.listen(29109) ? (nsvHost.getScrollY() >= scrollRange) : (ListenerUtil.mutListener.listen(29108) ? (nsvHost.getScrollY() <= scrollRange) : (ListenerUtil.mutListener.listen(29107) ? (nsvHost.getScrollY() > scrollRange) : (ListenerUtil.mutListener.listen(29106) ? (nsvHost.getScrollY() != scrollRange) : (ListenerUtil.mutListener.listen(29105) ? (nsvHost.getScrollY() == scrollRange) : (nsvHost.getScrollY() < scrollRange))))))) {
                                    if (!ListenerUtil.mutListener.listen(29110)) {
                                        info.addAction(AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            if (!ListenerUtil.mutListener.listen(29114)) {
                super.onInitializeAccessibilityEvent(host, event);
            }
            final WPNestedScrollView nsvHost = (WPNestedScrollView) host;
            if (!ListenerUtil.mutListener.listen(29115)) {
                event.setClassName(ScrollView.class.getName());
            }
            final boolean scrollable = (ListenerUtil.mutListener.listen(29120) ? (nsvHost.getScrollRange() >= 0) : (ListenerUtil.mutListener.listen(29119) ? (nsvHost.getScrollRange() <= 0) : (ListenerUtil.mutListener.listen(29118) ? (nsvHost.getScrollRange() < 0) : (ListenerUtil.mutListener.listen(29117) ? (nsvHost.getScrollRange() != 0) : (ListenerUtil.mutListener.listen(29116) ? (nsvHost.getScrollRange() == 0) : (nsvHost.getScrollRange() > 0))))));
            if (!ListenerUtil.mutListener.listen(29121)) {
                event.setScrollable(scrollable);
            }
            if (!ListenerUtil.mutListener.listen(29122)) {
                event.setScrollX(nsvHost.getScrollX());
            }
            if (!ListenerUtil.mutListener.listen(29123)) {
                event.setScrollY(nsvHost.getScrollY());
            }
            if (!ListenerUtil.mutListener.listen(29124)) {
                AccessibilityRecordCompat.setMaxScrollX(event, nsvHost.getScrollX());
            }
            if (!ListenerUtil.mutListener.listen(29125)) {
                AccessibilityRecordCompat.setMaxScrollY(event, nsvHost.getScrollRange());
            }
        }
    }
}

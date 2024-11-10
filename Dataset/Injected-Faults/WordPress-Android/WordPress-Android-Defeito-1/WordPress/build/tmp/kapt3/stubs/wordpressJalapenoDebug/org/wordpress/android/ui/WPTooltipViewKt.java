package org.wordpress.android.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\t\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0002"}, d2 = {"HIDE_ANIMATION_DURATION", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPTooltipViewKt {
    
    /**
     * Partially based on https://stackoverflow.com/a/42756576
     *
     * NOTE: this is a very basic implementation of a tooltip component
     * mainly used to cover the need of onboarding/announcing in the IA Project main create FAB.
     * More work/rework is needed to make it a full custom view tooltip component.
     * A different and more dynamic approach that can be used is with PopupWindow taking care of behaviors like
     * CoordinatorLayout behavior (as in this scenario with FAB and snackbars)
     */
    private static final long HIDE_ANIMATION_DURATION = 50L;
}
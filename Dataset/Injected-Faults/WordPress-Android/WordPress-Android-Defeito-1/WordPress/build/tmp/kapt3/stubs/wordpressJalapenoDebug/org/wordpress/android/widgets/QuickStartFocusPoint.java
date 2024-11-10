package org.wordpress.android.widgets;

import java.lang.System;

/**
 * Perpetually animated quick start focus point (hint)
 * Consists of:
 * - Initial expand animation with bounce
 * 2 staggered animations on repeat:
 * - Collapse
 * - Expand
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\fH\u0002\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/widgets/QuickStartFocusPoint;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "initView", "", "readSize", "setVisibleOrGone", "visible", "", "startAnimation", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartFocusPoint extends android.widget.FrameLayout {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.widgets.QuickStartFocusPoint.Companion Companion = null;
    public static final long OUTER_CIRCLE_ANIMATION_START_OFFSET_MS = 1000L;
    public static final long INNER_CIRCLE_ANIMATION_START_OFFSET_MS = 1050L;
    private static final int SIZE_SMALL = 0;
    private static final int SIZE_NORMAL = 1;
    
    public QuickStartFocusPoint(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public QuickStartFocusPoint(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public QuickStartFocusPoint(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attrs, int defStyle) {
        super(null);
    }
    
    private final void initView(int readSize) {
    }
    
    private final int readSize(android.util.AttributeSet attrs) {
        return 0;
    }
    
    public final void setVisibleOrGone(boolean visible) {
    }
    
    private final void startAnimation() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/widgets/QuickStartFocusPoint$Companion;", "", "()V", "INNER_CIRCLE_ANIMATION_START_OFFSET_MS", "", "OUTER_CIRCLE_ANIMATION_START_OFFSET_MS", "SIZE_NORMAL", "", "SIZE_SMALL", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
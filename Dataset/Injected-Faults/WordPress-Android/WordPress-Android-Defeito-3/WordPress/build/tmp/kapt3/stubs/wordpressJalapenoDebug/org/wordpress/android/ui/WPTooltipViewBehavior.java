package org.wordpress.android.ui;

import java.lang.System;

/**
 * This class will let WPTooltipViewBehavior anchor above FloatingActionButton with transition animation.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0003B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/WPTooltipViewBehavior;", "Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "Lorg/wordpress/android/ui/WPTooltipView;", "()V", "context", "Landroid/content/Context;", "attr", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "layoutDependsOn", "", "parent", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "child", "dependency", "Landroid/view/View;", "onDependentViewChanged", "onDependentViewRemoved", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPTooltipViewBehavior extends androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior<org.wordpress.android.ui.WPTooltipView> {
    
    public WPTooltipViewBehavior() {
        super();
    }
    
    public WPTooltipViewBehavior(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attr) {
        super();
    }
    
    @java.lang.Override()
    public boolean layoutDependsOn(@org.jetbrains.annotations.NotNull()
    androidx.coordinatorlayout.widget.CoordinatorLayout parent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.WPTooltipView child, @org.jetbrains.annotations.NotNull()
    android.view.View dependency) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onDependentViewChanged(@org.jetbrains.annotations.NotNull()
    androidx.coordinatorlayout.widget.CoordinatorLayout parent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.WPTooltipView child, @org.jetbrains.annotations.NotNull()
    android.view.View dependency) {
        return false;
    }
    
    @java.lang.Override()
    public void onDependentViewRemoved(@org.jetbrains.annotations.NotNull()
    androidx.coordinatorlayout.widget.CoordinatorLayout parent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.WPTooltipView child, @org.jetbrains.annotations.NotNull()
    android.view.View dependency) {
    }
}
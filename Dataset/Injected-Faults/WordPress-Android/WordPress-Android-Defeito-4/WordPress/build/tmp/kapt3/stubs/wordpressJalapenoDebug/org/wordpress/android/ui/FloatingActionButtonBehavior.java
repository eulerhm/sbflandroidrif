package org.wordpress.android.ui;

import java.lang.System;

/**
 * This class will let FloatingActionButton anchor above SnackBar with transition animation.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0003B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000fH\u0002J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J \u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/FloatingActionButtonBehavior;", "Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "()V", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "layoutDependsOn", "", "parent", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "child", "dependency", "Landroid/view/View;", "moveChildToInitialPosition", "", "moveChildUp", "translation", "", "onDependentViewChanged", "onDependentViewRemoved", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FloatingActionButtonBehavior extends androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior<com.google.android.material.floatingactionbutton.FloatingActionButton> {
    
    public FloatingActionButtonBehavior() {
        super();
    }
    
    public FloatingActionButtonBehavior(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attrs) {
        super();
    }
    
    @java.lang.Override()
    public boolean layoutDependsOn(@org.jetbrains.annotations.NotNull()
    androidx.coordinatorlayout.widget.CoordinatorLayout parent, @org.jetbrains.annotations.NotNull()
    com.google.android.material.floatingactionbutton.FloatingActionButton child, @org.jetbrains.annotations.NotNull()
    android.view.View dependency) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onDependentViewChanged(@org.jetbrains.annotations.NotNull()
    androidx.coordinatorlayout.widget.CoordinatorLayout parent, @org.jetbrains.annotations.NotNull()
    com.google.android.material.floatingactionbutton.FloatingActionButton child, @org.jetbrains.annotations.NotNull()
    android.view.View dependency) {
        return false;
    }
    
    @java.lang.Override()
    public void onDependentViewRemoved(@org.jetbrains.annotations.NotNull()
    androidx.coordinatorlayout.widget.CoordinatorLayout parent, @org.jetbrains.annotations.NotNull()
    com.google.android.material.floatingactionbutton.FloatingActionButton child, @org.jetbrains.annotations.NotNull()
    android.view.View dependency) {
    }
    
    private final void moveChildUp(android.view.View child, int translation) {
    }
    
    private final void moveChildToInitialPosition(android.view.View child) {
    }
}
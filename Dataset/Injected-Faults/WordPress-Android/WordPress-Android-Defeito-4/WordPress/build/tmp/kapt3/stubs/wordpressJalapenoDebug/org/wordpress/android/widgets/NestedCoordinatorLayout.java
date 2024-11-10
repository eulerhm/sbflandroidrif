package org.wordpress.android.widgets;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J,\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016J4\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\bH\u0016J2\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016J:\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\bH\u0016JB\u0010\u0019\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0016H\u0016J\b\u0010\u001f\u001a\u00020\rH\u0016J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\bH\u0016J\b\u0010 \u001a\u00020\rH\u0016J(\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\rH\u0016J \u0010$\u001a\u00020\r2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J(\u0010%\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0016H\u0016J0\u0010%\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\bH\u0016J0\u0010&\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J8\u0010&\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0016J@\u0010&\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0016H\u0016J \u0010\'\u001a\u00020\r2\u0006\u0010(\u001a\u00020#2\u0006\u0010\"\u001a\u00020#2\u0006\u0010)\u001a\u00020\bH\u0016J(\u0010\'\u001a\u00020\r2\u0006\u0010(\u001a\u00020#2\u0006\u0010\"\u001a\u00020#2\u0006\u0010)\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0016J\u0010\u0010*\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010*\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\bH\u0016J\u0010\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\rH\u0016J\u0010\u0010-\u001a\u00020\r2\u0006\u0010)\u001a\u00020\bH\u0016J\u0018\u0010-\u001a\u00020\r2\u0006\u0010)\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0016J\b\u0010.\u001a\u00020\u001eH\u0016J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\bH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/widgets/NestedCoordinatorLayout;", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "Landroidx/core/view/NestedScrollingChild3;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "helper", "Landroidx/core/view/NestedScrollingChildHelper;", "dispatchNestedFling", "", "velocityX", "", "velocityY", "consumed", "dispatchNestedPreFling", "dispatchNestedPreScroll", "dx", "dy", "", "offsetInWindow", "type", "dispatchNestedScroll", "dxConsumed", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "", "hasNestedScrollingParent", "isNestedScrollingEnabled", "onNestedFling", "target", "Landroid/view/View;", "onNestedPreFling", "onNestedPreScroll", "onNestedScroll", "onStartNestedScroll", "child", "axes", "onStopNestedScroll", "setNestedScrollingEnabled", "enabled", "startNestedScroll", "stopNestedScroll", "org.wordpress.android_wordpressJalapenoDebug"})
public final class NestedCoordinatorLayout extends androidx.coordinatorlayout.widget.CoordinatorLayout implements androidx.core.view.NestedScrollingChild3 {
    private final androidx.core.view.NestedScrollingChildHelper helper = null;
    
    @kotlin.jvm.JvmOverloads()
    public NestedCoordinatorLayout(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public NestedCoordinatorLayout(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public NestedCoordinatorLayout(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, @android.annotation.SuppressLint(value = {"PrivateResource"})
    @androidx.annotation.AttrRes()
    int defStyleAttr) {
        super(null);
    }
    
    @java.lang.Override()
    public boolean isNestedScrollingEnabled() {
        return false;
    }
    
    @java.lang.Override()
    public void setNestedScrollingEnabled(boolean enabled) {
    }
    
    @java.lang.Override()
    public boolean hasNestedScrollingParent(int type) {
        return false;
    }
    
    @java.lang.Override()
    public boolean hasNestedScrollingParent() {
        return false;
    }
    
    @java.lang.Override()
    public boolean onStartNestedScroll(@org.jetbrains.annotations.NotNull()
    android.view.View child, @org.jetbrains.annotations.NotNull()
    android.view.View target, int axes, int type) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onStartNestedScroll(@org.jetbrains.annotations.NotNull()
    android.view.View child, @org.jetbrains.annotations.NotNull()
    android.view.View target, int axes) {
        return false;
    }
    
    @java.lang.Override()
    public void onNestedPreScroll(@org.jetbrains.annotations.NotNull()
    android.view.View target, int dx, int dy, @org.jetbrains.annotations.NotNull()
    int[] consumed, int type) {
    }
    
    @java.lang.Override()
    public void onNestedPreScroll(@org.jetbrains.annotations.NotNull()
    android.view.View target, int dx, int dy, @org.jetbrains.annotations.NotNull()
    int[] consumed) {
    }
    
    @java.lang.Override()
    public void onNestedScroll(@org.jetbrains.annotations.NotNull()
    android.view.View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @org.jetbrains.annotations.NotNull()
    int[] consumed) {
    }
    
    @java.lang.Override()
    public void onNestedScroll(@org.jetbrains.annotations.NotNull()
    android.view.View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
    }
    
    @java.lang.Override()
    public void onNestedScroll(@org.jetbrains.annotations.NotNull()
    android.view.View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }
    
    @java.lang.Override()
    public void onStopNestedScroll(@org.jetbrains.annotations.NotNull()
    android.view.View target, int type) {
    }
    
    @java.lang.Override()
    public void onStopNestedScroll(@org.jetbrains.annotations.NotNull()
    android.view.View target) {
    }
    
    @java.lang.Override()
    public boolean onNestedPreFling(@org.jetbrains.annotations.NotNull()
    android.view.View target, float velocityX, float velocityY) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onNestedFling(@org.jetbrains.annotations.NotNull()
    android.view.View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }
    
    @java.lang.Override()
    public boolean startNestedScroll(int axes, int type) {
        return false;
    }
    
    @java.lang.Override()
    public boolean startNestedScroll(int axes) {
        return false;
    }
    
    @java.lang.Override()
    public void stopNestedScroll(int type) {
    }
    
    @java.lang.Override()
    public void stopNestedScroll() {
    }
    
    @java.lang.Override()
    public void dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @org.jetbrains.annotations.Nullable()
    int[] offsetInWindow, int type, @org.jetbrains.annotations.NotNull()
    int[] consumed) {
    }
    
    @java.lang.Override()
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @org.jetbrains.annotations.Nullable()
    int[] offsetInWindow, int type) {
        return false;
    }
    
    @java.lang.Override()
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @org.jetbrains.annotations.Nullable()
    int[] offsetInWindow) {
        return false;
    }
    
    @java.lang.Override()
    public boolean dispatchNestedPreScroll(int dx, int dy, @org.jetbrains.annotations.Nullable()
    int[] consumed, @org.jetbrains.annotations.Nullable()
    int[] offsetInWindow, int type) {
        return false;
    }
    
    @java.lang.Override()
    public boolean dispatchNestedPreScroll(int dx, int dy, @org.jetbrains.annotations.Nullable()
    int[] consumed, @org.jetbrains.annotations.Nullable()
    int[] offsetInWindow) {
        return false;
    }
    
    @java.lang.Override()
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return false;
    }
    
    @java.lang.Override()
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return false;
    }
}
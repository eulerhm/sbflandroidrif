package com.google.android.gnd;

import java.lang.System;

/**
 * Base activity class containing common helper methods.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\nH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0014J\b\u0010\u0010\u001a\u00020\nH\u0014J\b\u0010\u0011\u001a\u00020\nH\u0014J\b\u0010\u0012\u001a\u00020\nH\u0014J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0015J\u0016\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0018\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0001\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006 "}, d2 = {"Lcom/google/android/gnd/AbstractActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "drawableUtil", "Lcom/google/android/gnd/ui/util/DrawableUtil;", "getDrawableUtil", "()Lcom/google/android/gnd/ui/util/DrawableUtil;", "setDrawableUtil", "(Lcom/google/android/gnd/ui/util/DrawableUtil;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onStart", "onStop", "onToolbarUpClicked", "onWindowInsetChanged", "insets", "Landroidx/core/view/WindowInsetsCompat;", "setActionBar", "toolbar", "Lcom/google/android/gnd/ui/common/TwoLineToolbar;", "showTitle", "", "upIconId", "", "setContentView", "view", "Landroid/view/View;", "gnd_debug"})
public abstract class AbstractActivity extends androidx.appcompat.app.AppCompatActivity {
    @javax.inject.Inject()
    public com.google.android.gnd.ui.util.DrawableUtil drawableUtil;
    
    public AbstractActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.util.DrawableUtil getDrawableUtil() {
        return null;
    }
    
    public final void setDrawableUtil(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.util.DrawableUtil p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void setContentView(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Adjust UI elements with respect to top/bottom insets.
     */
    @javax.annotation.OverridingMethodsMustInvokeSuper()
    protected void onWindowInsetChanged(@org.jetbrains.annotations.NotNull()
    androidx.core.view.WindowInsetsCompat insets) {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public final void setActionBar(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.TwoLineToolbar toolbar, @androidx.annotation.DrawableRes()
    int upIconId) {
    }
    
    public final void setActionBar(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.TwoLineToolbar toolbar, boolean showTitle) {
    }
    
    protected void onToolbarUpClicked() {
    }
}
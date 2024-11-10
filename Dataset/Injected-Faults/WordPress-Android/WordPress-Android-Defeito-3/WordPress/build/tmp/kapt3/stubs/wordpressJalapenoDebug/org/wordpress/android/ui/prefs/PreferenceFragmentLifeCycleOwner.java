package org.wordpress.android.ui.prefs;

import java.lang.System;

/**
 * LifecycleOwner is a single method interface that denotes that the class has a Lifecycle.
 * android.preference.PreferenceFragment doesn't implement android.app.Fragment.LifecycleOwner interface.
 * Fragments and Activities in Support Library 26.1.0 and later already implement the LifecycleOwner interface.
 * Until we migrate to androidx Preference Library, we can use this class instead of deprecated PreferenceFragment,
 * which supports the use of lifecycleCoroutineScope for observing Live data or Flows.
 * https://developer.android.com/topic/libraries/architecture/lifecycle#implementing-lco
 */
@java.lang.SuppressWarnings(value = {"deprecation"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/prefs/PreferenceFragmentLifeCycleOwner;", "Landroid/preference/PreferenceFragment;", "Landroidx/lifecycle/LifecycleOwner;", "()V", "lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "lifecycleScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "getLifecycleScope", "()Landroidx/lifecycle/LifecycleCoroutineScope;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onStart", "onStop", "org.wordpress.android_wordpressJalapenoDebug"})
public class PreferenceFragmentLifeCycleOwner extends android.preference.PreferenceFragment implements androidx.lifecycle.LifecycleOwner {
    private androidx.lifecycle.LifecycleRegistry lifecycleRegistry;
    
    public PreferenceFragmentLifeCycleOwner() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LifecycleCoroutineScope getLifecycleScope() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.Lifecycle getLifecycle() {
        return null;
    }
}
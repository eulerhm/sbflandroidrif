package com.google.android.gnd.system;

import java.lang.System;

/**
 * Bridge between the [Activity] and various `Manager` classes.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\u0002\b\u00072\u0006\u0010\u0010\u001a\u00020\u0011J\u0019\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\u0002\b\u00072\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J)\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u00020\u00152\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\"\u0010\u0003\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u00a2\u0006\u0002\b\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u000b0\u0004\u00a2\u0006\u0002\b\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\r\u0012\u0004\u0012\u00020\r0\u0004\u00a2\u0006\u0002\b\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/google/android/gnd/system/ActivityStreams;", "", "()V", "activityRequests", "Lio/reactivex/subjects/Subject;", "Ljava8/util/function/Consumer;", "Landroid/app/Activity;", "Lcom/google/android/gnd/rx/annotations/Hot;", "getActivityRequests", "()Lio/reactivex/subjects/Subject;", "activityResults", "Lcom/google/android/gnd/system/ActivityResult;", "requestPermissionsResults", "Lcom/google/android/gnd/system/RequestPermissionsResult;", "getActivityResults", "Lio/reactivex/Observable;", "requestCode", "", "getNextActivityResult", "getNextRequestPermissionsResult", "onActivityResult", "", "resultCode", "data", "Landroid/content/Intent;", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "withActivity", "callback", "gnd_debug"})
@javax.inject.Singleton()
public final class ActivityStreams {
    
    /**
     * Emits [Consumer]s to be executed in the context of the [Activity].
     */
    @org.jetbrains.annotations.NotNull()
    private final io.reactivex.subjects.Subject<java8.util.function.Consumer<android.app.Activity>> activityRequests = null;
    
    /**
     * Emits [Activity.onActivityResult] events.
     */
    private final io.reactivex.subjects.Subject<com.google.android.gnd.system.ActivityResult> activityResults = null;
    
    /**
     * Emits [Activity.onRequestPermissionsResult] events.
     */
    private final io.reactivex.subjects.Subject<com.google.android.gnd.system.RequestPermissionsResult> requestPermissionsResults = null;
    
    @javax.inject.Inject()
    public ActivityStreams() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.subjects.Subject<java8.util.function.Consumer<android.app.Activity>> getActivityRequests() {
        return null;
    }
    
    /**
     * Queues the specified [Consumer] for execution. An instance of the current [ ] is provided to the `Consumer` when called.
     */
    public final void withActivity(@org.jetbrains.annotations.NotNull()
    java8.util.function.Consumer<android.app.Activity> callback) {
    }
    
    /**
     * Callback used to communicate [Activity.onActivityResult] events with
     * various `Manager` classes.
     */
    public final void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    /**
     * Callback used to communicate [Activity.onRequestPermissionsResult]
     * events with various `Manager` classes.
     */
    public final void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    /**
     * Emits [Activity.onActivityResult] events where `requestCode`
     * matches the specified value.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<com.google.android.gnd.system.ActivityResult> getActivityResults(int requestCode) {
        return null;
    }
    
    /**
     * Emits the next [Activity.onActivityResult] event where `requestCode` matches the specified value.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<com.google.android.gnd.system.ActivityResult> getNextActivityResult(int requestCode) {
        return null;
    }
    
    /**
     * Emits the next [Activity.onRequestPermissionsResult] event where
     * `requestCode` matches the specified value.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<com.google.android.gnd.system.RequestPermissionsResult> getNextRequestPermissionsResult(int requestCode) {
        return null;
    }
}
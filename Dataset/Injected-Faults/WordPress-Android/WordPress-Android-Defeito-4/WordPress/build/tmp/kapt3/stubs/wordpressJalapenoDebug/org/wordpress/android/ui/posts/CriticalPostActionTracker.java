package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\nJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\nJ\u0016\u0010\u0011\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000bR\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/CriticalPostActionTracker;", "", "onStateChanged", "Lkotlin/Function0;", "", "shouldCrashOnUnexpectedAction", "", "(Lkotlin/jvm/functions/Function0;Z)V", "map", "Ljava/util/HashMap;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "Lorg/wordpress/android/ui/posts/CriticalPostActionTracker$CriticalPostAction;", "add", "localPostId", "criticalPostAction", "contains", "get", "remove", "CriticalPostAction", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CriticalPostActionTracker {
    private final kotlin.jvm.functions.Function0<kotlin.Unit> onStateChanged = null;
    private final boolean shouldCrashOnUnexpectedAction = false;
    private final java.util.HashMap<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId, org.wordpress.android.ui.posts.CriticalPostActionTracker.CriticalPostAction> map = null;
    
    public CriticalPostActionTracker(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStateChanged, boolean shouldCrashOnUnexpectedAction) {
        super();
    }
    
    public final void add(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localPostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.CriticalPostActionTracker.CriticalPostAction criticalPostAction) {
    }
    
    public final boolean contains(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localPostId) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.posts.CriticalPostActionTracker.CriticalPostAction get(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localPostId) {
        return null;
    }
    
    public final void remove(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localPostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.CriticalPostActionTracker.CriticalPostAction criticalPostAction) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/posts/CriticalPostActionTracker$CriticalPostAction;", "", "(Ljava/lang/String;I)V", "DELETING_POST", "RESTORING_POST", "TRASHING_POST", "TRASHING_POST_WITH_LOCAL_CHANGES", "MOVING_POST_TO_DRAFT", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum CriticalPostAction {
        /*public static final*/ DELETING_POST /* = new DELETING_POST() */,
        /*public static final*/ RESTORING_POST /* = new RESTORING_POST() */,
        /*public static final*/ TRASHING_POST /* = new TRASHING_POST() */,
        /*public static final*/ TRASHING_POST_WITH_LOCAL_CHANGES /* = new TRASHING_POST_WITH_LOCAL_CHANGES() */,
        /*public static final*/ MOVING_POST_TO_DRAFT /* = new MOVING_POST_TO_DRAFT() */;
        
        CriticalPostAction() {
        }
    }
}
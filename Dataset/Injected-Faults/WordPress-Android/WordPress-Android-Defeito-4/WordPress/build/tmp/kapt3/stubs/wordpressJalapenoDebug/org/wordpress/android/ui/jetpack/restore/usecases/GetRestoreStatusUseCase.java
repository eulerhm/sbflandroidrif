package org.wordpress.android.ui.jetpack.restore.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J5\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020\u00130\u001a2\u0006\u0010\u001b\u001a\u00020\rH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ#\u0010\u001d\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020\u00130\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u001b\u0010!\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020\u00130\u001aH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J#\u0010#\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020\u00130\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J#\u0010$\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020\u00130\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J+\u0010%\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00130\u001a2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006+"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/usecases/GetRestoreStatusUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "activityLogStore", "Lorg/wordpress/android/fluxc/store/ActivityLogStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/store/ActivityLogStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "tag", "", "kotlin.jvm.PlatformType", "fetchActivitiesRewind", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRestoreStatus", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "restoreId", "", "checkIfAwaitingCredentials", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/Long;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitAwaitingCredentials", "", "Lkotlinx/coroutines/flow/FlowCollector;", "isAwaitingCredentials", "(Lkotlinx/coroutines/flow/FlowCollector;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitComplete", "rewind", "Lorg/wordpress/android/fluxc/model/activity/RewindStatusModel$Rewind;", "(Lkotlinx/coroutines/flow/FlowCollector;Lorg/wordpress/android/fluxc/model/activity/RewindStatusModel$Rewind;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitFailure", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitFinished", "emitProgress", "handleError", "retryAttempts", "", "failure", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure;", "(Lkotlinx/coroutines/flow/FlowCollector;ILorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GetRestoreStatusUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final java.lang.String tag = null;
    
    @javax.inject.Inject()
    public GetRestoreStatusUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"ComplexMethod", "LoopWithTooManyJumpStatements"})
    public final java.lang.Object getRestoreStatus(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    java.lang.Long restoreId, boolean checkIfAwaitingCredentials, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState>> continuation) {
        return null;
    }
    
    private final java.lang.Object fetchActivitiesRewind(org.wordpress.android.fluxc.model.SiteModel site, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final java.lang.Object emitFinished(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.restore.RestoreRequestState> $this$emitFinished, org.wordpress.android.fluxc.model.activity.RewindStatusModel.Rewind rewind, kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final java.lang.Object emitComplete(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.restore.RestoreRequestState> $this$emitComplete, org.wordpress.android.fluxc.model.activity.RewindStatusModel.Rewind rewind, kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final java.lang.Object emitFailure(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.restore.RestoreRequestState> $this$emitFailure, kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    private final java.lang.Object emitAwaitingCredentials(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.restore.RestoreRequestState> $this$emitAwaitingCredentials, boolean isAwaitingCredentials, kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final java.lang.Object emitProgress(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.restore.RestoreRequestState> $this$emitProgress, org.wordpress.android.fluxc.model.activity.RewindStatusModel.Rewind rewind, kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final java.lang.Object handleError(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.restore.RestoreRequestState> $this$handleError, int retryAttempts, org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Failure failure, kotlin.coroutines.Continuation<? super java.lang.Boolean> p3) {
        return null;
    }
}
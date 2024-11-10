package org.wordpress.android.ui.jetpack.backup.download.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ+\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J+\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002\u00a2\u0006\u0002\u0010\u0019J#\u0010\u001a\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u000e0\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ+\u0010\u001f\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u000e0\u001b2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/usecases/GetBackupDownloadStatusUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "activityLogStore", "Lorg/wordpress/android/fluxc/store/ActivityLogStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/store/ActivityLogStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "tag", "", "kotlin.jvm.PlatformType", "getBackupDownloadStatus", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "downloadId", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isValid", "", "url", "validUntil", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/util/Date;Ljava/lang/Long;)Z", "emitCompleteElseProgress", "Lkotlinx/coroutines/flow/FlowCollector;", "status", "Lorg/wordpress/android/fluxc/model/activity/BackupDownloadStatusModel;", "(Lkotlinx/coroutines/flow/FlowCollector;Lorg/wordpress/android/fluxc/model/activity/BackupDownloadStatusModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleError", "retryAttempts", "", "failure", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure;", "(Lkotlinx/coroutines/flow/FlowCollector;ILorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GetBackupDownloadStatusUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final java.lang.String tag = null;
    
    @javax.inject.Inject()
    public GetBackupDownloadStatusUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"ComplexMethod", "LoopWithTooManyJumpStatements"})
    public final java.lang.Object getBackupDownloadStatus(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    java.lang.Long downloadId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState>> continuation) {
        return null;
    }
    
    private final java.lang.Object emitCompleteElseProgress(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState> $this$emitCompleteElseProgress, org.wordpress.android.fluxc.model.activity.BackupDownloadStatusModel status, kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
    
    private final java.lang.Object handleError(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState> $this$handleError, int retryAttempts, org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Failure failure, kotlin.coroutines.Continuation<? super java.lang.Boolean> p3) {
        return null;
    }
    
    private final boolean isValid(java.lang.String url, java.util.Date validUntil, java.lang.Long downloadId) {
        return false;
    }
}
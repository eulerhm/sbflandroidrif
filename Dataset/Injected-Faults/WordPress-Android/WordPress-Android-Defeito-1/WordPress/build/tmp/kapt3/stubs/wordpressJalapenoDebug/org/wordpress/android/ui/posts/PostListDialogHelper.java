package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * This is a temporary class to make the PostListViewModel more manageable. Please feel free to refactor it any way
 * you see fit.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJJ\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u0003JJ\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u0003J\u00ae\u0001\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u0003J\u000e\u0010&\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u000e\u0010*\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u000e\u0010+\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u000e\u0010,\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u000e\u0010-\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u000e\u0010.\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u000e\u0010/\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u0010\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u0011\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u0013\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u0014\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u0015\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListDialogHelper;", "", "showDialog", "Lkotlin/Function1;", "Lorg/wordpress/android/viewmodel/helpers/DialogHolder;", "", "checkNetworkConnection", "Lkotlin/Function0;", "", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "localPostIdForAutosaveRevisionResolutionDialog", "", "Ljava/lang/Integer;", "localPostIdForConflictResolutionDialog", "localPostIdForCopyConflictDialog", "localPostIdForDeleteDialog", "localPostIdForMoveTrashedPostToDraftDialog", "localPostIdForScheduledPostSyncDialog", "localPostIdForTrashPostWithLocalChangesDialog", "localPostIdForTrashPostWithUnsavedChangesDialog", "onDismissByOutsideTouchForBasicDialog", "instanceTag", "", "updateConflictedPostWithLocalVersion", "editLocalPost", "copyLocalPost", "onNegativeClickedForBasicDialog", "onPositiveClickedForBasicDialog", "trashPostWithLocalChanges", "trashPostWithUnsavedChanges", "deletePost", "publishPost", "updateConflictedPostWithRemoteVersion", "editRestoredAutoSavePost", "moveTrashedPostToDraft", "resolveConflictsAndEditPost", "showAutoSaveRevisionDialog", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "showConflictedPostResolutionDialog", "showCopyConflictDialog", "showDeletePostConfirmationDialog", "showMoveTrashedPostToDraftDialog", "showSyncScheduledPostConfirmationDialog", "showTrashPostWithLocalChangesConfirmationDialog", "showTrashPostWithUnsavedChangesConfirmationDialog", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListDialogHelper {
    private final kotlin.jvm.functions.Function1<org.wordpress.android.viewmodel.helpers.DialogHolder, kotlin.Unit> showDialog = null;
    private final kotlin.jvm.functions.Function0<java.lang.Boolean> checkNetworkConnection = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private java.lang.Integer localPostIdForDeleteDialog;
    private java.lang.Integer localPostIdForMoveTrashedPostToDraftDialog;
    private java.lang.Integer localPostIdForTrashPostWithLocalChangesDialog;
    private java.lang.Integer localPostIdForTrashPostWithUnsavedChangesDialog;
    private java.lang.Integer localPostIdForConflictResolutionDialog;
    private java.lang.Integer localPostIdForAutosaveRevisionResolutionDialog;
    private java.lang.Integer localPostIdForScheduledPostSyncDialog;
    private java.lang.Integer localPostIdForCopyConflictDialog;
    
    public PostListDialogHelper(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.viewmodel.helpers.DialogHolder, kotlin.Unit> showDialog, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<java.lang.Boolean> checkNetworkConnection, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
    }
    
    public final void showMoveTrashedPostToDraftDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void showDeletePostConfirmationDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void showSyncScheduledPostConfirmationDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void showTrashPostWithLocalChangesConfirmationDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void showTrashPostWithUnsavedChangesConfirmationDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void showConflictedPostResolutionDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void showAutoSaveRevisionDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void showCopyConflictDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void onPositiveClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> trashPostWithLocalChanges, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> trashPostWithUnsavedChanges, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> deletePost, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> publishPost, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> updateConflictedPostWithRemoteVersion, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> editRestoredAutoSavePost, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> moveTrashedPostToDraft, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> resolveConflictsAndEditPost) {
    }
    
    public final void onNegativeClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> updateConflictedPostWithLocalVersion, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> editLocalPost, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> copyLocalPost) {
    }
    
    public final void onDismissByOutsideTouchForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> updateConflictedPostWithLocalVersion, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> editLocalPost, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> copyLocalPost) {
    }
}
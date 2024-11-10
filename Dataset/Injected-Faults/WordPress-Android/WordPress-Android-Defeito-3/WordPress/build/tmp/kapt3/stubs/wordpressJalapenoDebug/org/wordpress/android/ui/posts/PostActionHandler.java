package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * This is a temporary class to make the PostListViewModel more manageable. Please feel free to refactor it any way
 * you see fit.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0087\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000b\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00110\u000b\u0012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00110\u000b\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0016\u0012\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00110\u000b\u0012\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00110\u000b\u0012\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00110\u001d\u0012\u001e\u0010 \u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00110!\u00a2\u0006\u0002\u0010\"J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\fH\u0002J\u0010\u0010\'\u001a\u00020(2\u0006\u0010&\u001a\u00020\fH\u0002J\u000e\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+J\u0018\u0010,\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\fH\u0002J\u0018\u0010-\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\fH\u0002J\u0010\u0010.\u001a\u00020\u00112\b\u0010/\u001a\u0004\u0018\u000100J\u001e\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u0002032\u0006\u0010&\u001a\u00020\f2\u0006\u00104\u001a\u00020\rJ\u001e\u00105\u001a\u00020\u00112\u0006\u0010*\u001a\u0002062\u0006\u00107\u001a\u00020\r2\u0006\u00108\u001a\u00020\rJ\u0016\u00109\u001a\u00020\u00112\u0006\u0010*\u001a\u0002062\u0006\u00108\u001a\u00020\rJ\u0016\u0010:\u001a\u00020\u00112\u0006\u0010*\u001a\u0002062\u0006\u00108\u001a\u00020\rJ\u0016\u0010;\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020\r2\u0006\u0010*\u001a\u000206J\u000e\u0010?\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+J\u0010\u0010?\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\fH\u0002J\u0006\u0010@\u001a\u00020\u0011J\u0006\u0010A\u001a\u00020\u0011J\u0018\u0010B\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\fH\u0002J\u000e\u0010C\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+J\u000e\u0010C\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\fJ\u000e\u0010D\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+J\u0010\u0010E\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\fH\u0002J\u001a\u0010F\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\f2\b\b\u0002\u0010G\u001a\u00020\rH\u0002J\u000e\u0010H\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+J\u000e\u0010I\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010 \u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00110!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00110\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00110\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00110\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00110\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00110\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006J"}, d2 = {"Lorg/wordpress/android/ui/posts/PostActionHandler;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "postListDialogHelper", "Lorg/wordpress/android/ui/posts/PostListDialogHelper;", "doesPostHaveUnhandledConflict", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/model/PostModel;", "", "hasUnhandledAutoSave", "triggerPostListAction", "Lorg/wordpress/android/ui/posts/PostListAction;", "", "triggerPostUploadAction", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "triggerPublishAction", "invalidateList", "Lkotlin/Function0;", "checkNetworkConnection", "showSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "showToast", "Lorg/wordpress/android/viewmodel/helpers/ToastMessageHolder;", "triggerPreviewStateUpdate", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;", "Lorg/wordpress/android/ui/posts/PostInfoType;", "copyPost", "Lkotlin/Function3;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/ui/posts/PostListDialogHelper;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)V", "criticalPostActionTracker", "Lorg/wordpress/android/ui/posts/CriticalPostActionTracker;", "cancelPendingAutoUpload", "post", "copyUrlAction", "Lorg/wordpress/android/ui/posts/PostListAction$CopyUrl;", "deletePost", "localPostId", "", "editPost", "editPostButtonAction", "handleEditPostResult", "data", "Landroid/content/Intent;", "handlePostButton", "buttonType", "Lorg/wordpress/android/widgets/PostListButtonType;", "hasAutoSave", "handlePostDeletedOrRemoved", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "isRemoved", "isError", "handlePostRestored", "handlePostTrashed", "handleRemotePreview", "remotePreviewType", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "isPerformingCriticalAction", "moveTrashedPostToDraft", "newPost", "newStoryPost", "performChecksAndEdit", "publishPost", "resolveConflictsAndEditPost", "restorePost", "trashPost", "hasLocalChanges", "trashPostWithLocalChanges", "trashPostWithUnsavedChanges", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostActionHandler {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.ui.posts.PostListDialogHelper postListDialogHelper = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> doesPostHaveUnhandledConflict = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> hasUnhandledAutoSave = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.posts.PostListAction, kotlin.Unit> triggerPostListAction = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.posts.PostUploadAction, kotlin.Unit> triggerPostUploadAction = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.PostModel, kotlin.Unit> triggerPublishAction = null;
    private final kotlin.jvm.functions.Function0<kotlin.Unit> invalidateList = null;
    private final kotlin.jvm.functions.Function0<java.lang.Boolean> checkNetworkConnection = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.pages.SnackbarMessageHolder, kotlin.Unit> showSnackbar = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.viewmodel.helpers.ToastMessageHolder, kotlin.Unit> showToast = null;
    private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.posts.PostListRemotePreviewState, org.wordpress.android.ui.posts.PostInfoType, kotlin.Unit> triggerPreviewStateUpdate = null;
    private final kotlin.jvm.functions.Function3<org.wordpress.android.fluxc.model.SiteModel, org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean, kotlin.Unit> copyPost = null;
    private final org.wordpress.android.ui.posts.CriticalPostActionTracker criticalPostActionTracker = null;
    
    public PostActionHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListDialogHelper postListDialogHelper, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> doesPostHaveUnhandledConflict, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> hasUnhandledAutoSave, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.posts.PostListAction, kotlin.Unit> triggerPostListAction, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.posts.PostUploadAction, kotlin.Unit> triggerPostUploadAction, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, kotlin.Unit> triggerPublishAction, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> invalidateList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<java.lang.Boolean> checkNetworkConnection, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.pages.SnackbarMessageHolder, kotlin.Unit> showSnackbar, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.viewmodel.helpers.ToastMessageHolder, kotlin.Unit> showToast, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.posts.PostListRemotePreviewState, ? super org.wordpress.android.ui.posts.PostInfoType, kotlin.Unit> triggerPreviewStateUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super org.wordpress.android.fluxc.model.SiteModel, ? super org.wordpress.android.fluxc.model.PostModel, ? super java.lang.Boolean, kotlin.Unit> copyPost) {
        super();
    }
    
    public final void handlePostButton(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.widgets.PostListButtonType buttonType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, boolean hasAutoSave) {
    }
    
    private final org.wordpress.android.ui.posts.PostListAction.CopyUrl copyUrlAction(org.wordpress.android.fluxc.model.PostModel post) {
        return null;
    }
    
    private final void cancelPendingAutoUpload(org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void newPost() {
    }
    
    public final void newStoryPost() {
    }
    
    public final void handleEditPostResult(@org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public final void handleRemotePreview(int localPostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType remotePreviewType) {
    }
    
    public final void publishPost(int localPostId) {
    }
    
    public final void publishPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void resolveConflictsAndEditPost(int localPostId) {
    }
    
    public final void moveTrashedPostToDraft(int localPostId) {
    }
    
    private final void moveTrashedPostToDraft(org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    private final void editPostButtonAction(org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    private final void performChecksAndEdit(org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    private final void editPost(org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void deletePost(int localPostId) {
    }
    
    /**
     * This function handles a post being deleted and removed. Since deleting remote posts will trigger both delete
     * and remove actions we only want to remove the critical action when the post is actually successfully removed.
     *
     * It's possible to separate these into two methods that handles delete and remove. However, the fact that they
     * follow the same approach and the tricky nature of delete action makes combining the actions like so makes our
     * expectations clearer.
     */
    public final void handlePostDeletedOrRemoved(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localPostId, boolean isRemoved, boolean isError) {
    }
    
    public final void trashPostWithLocalChanges(int localPostId) {
    }
    
    public final void trashPostWithUnsavedChanges(int localPostId) {
    }
    
    private final void trashPost(org.wordpress.android.fluxc.model.PostModel post, boolean hasLocalChanges) {
    }
    
    public final void handlePostTrashed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localPostId, boolean isError) {
    }
    
    private final void restorePost(org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final void handlePostRestored(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localPostId, boolean isError) {
    }
    
    public final boolean isPerformingCriticalAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localPostId) {
        return false;
    }
}
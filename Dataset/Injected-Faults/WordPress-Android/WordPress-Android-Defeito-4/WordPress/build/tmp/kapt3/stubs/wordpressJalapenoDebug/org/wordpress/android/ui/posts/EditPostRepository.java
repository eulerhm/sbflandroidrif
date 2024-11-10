package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0081\u0001B3\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\t\u00a2\u0006\u0002\u0010\u000bJ\b\u0010Y\u001a\u0004\u0018\u00010AJ\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[J\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\\0TJ\b\u0010^\u001a\u0004\u0018\u00010\u000fJ\b\u0010_\u001a\u0004\u0018\u00010AJ\u0006\u0010`\u001a\u00020/J\u0006\u0010a\u001a\u00020/J\u0006\u0010b\u001a\u00020/J\u000e\u0010c\u001a\u00020/2\u0006\u0010O\u001a\u00020PJ\u0006\u0010d\u001a\u00020/J\u000e\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020\u0019J\u0016\u0010h\u001a\u00020f2\u0006\u0010K\u001a\u00020)2\u0006\u0010i\u001a\u00020jJ\u0006\u0010k\u001a\u00020/J\u0006\u0010l\u001a\u00020/J\u001a\u0010m\u001a\u00020f2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020A0oJ\u0010\u0010p\u001a\u00020f2\u0006\u0010q\u001a\u00020/H\u0002J\u0006\u0010r\u001a\u00020fJ\u0006\u0010s\u001a\u00020fJ\u0006\u0010t\u001a\u00020fJ\u0014\u0010u\u001a\u00020f2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020A0vJ\u0006\u0010w\u001a\u00020fJ\'\u0010x\u001a\u0002Hy\"\u0004\b\u0000\u0010y2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u0002Hy0oH\u0007\u00a2\u0006\u0002\u0010zJ8\u0010{\u001a\u00020f2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020/0o2\u001c\b\u0002\u0010|\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020~\u0012\u0004\u0012\u00020f\u0018\u00010}J\u000e\u0010\u007f\u001a\u00020f2\u0006\u0010@\u001a\u00020AJ\u0007\u0010\u0080\u0001\u001a\u00020fR\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u00198F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0014\u0010\u001e\u001a\u00020\u001f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b#\u0010\u0013R\u0011\u0010$\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u0013R\u0011\u0010&\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b\'\u0010\u0013R\u0011\u0010(\u001a\u00020)8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010+R\u0011\u0010,\u001a\u00020\u00198F\u00a2\u0006\u0006\u001a\u0004\b-\u0010\u001bR\u0011\u0010.\u001a\u00020/8F\u00a2\u0006\u0006\u001a\u0004\b.\u00100R\u0011\u00101\u001a\u00020/8F\u00a2\u0006\u0006\u001a\u0004\b1\u00100R\u0011\u00102\u001a\u00020/8F\u00a2\u0006\u0006\u001a\u0004\b2\u00100R\u000e\u00103\u001a\u000204X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u00105\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b6\u0010\u0013R\u0011\u00107\u001a\u00020\u00198F\u00a2\u0006\u0006\u001a\u0004\b8\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u00109\u001a\u00020:8F\u00a2\u0006\u0006\u001a\u0004\b;\u0010<R\u000e\u0010=\u001a\u00020/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010>\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b?\u0010\u0013R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0C\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0010\u0010F\u001a\u0004\u0018\u00010AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010G\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\bH\u0010\u0013R\u0010\u0010I\u001a\u0004\u0018\u00010AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u0004\u0018\u00010AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010K\u001a\u00020)8F\u00a2\u0006\u0006\u001a\u0004\bL\u0010+R\u0011\u0010M\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\bN\u0010\u0013R\u0011\u0010O\u001a\u00020P8F\u00a2\u0006\u0006\u001a\u0004\bQ\u0010RR\u0017\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00110T8F\u00a2\u0006\u0006\u001a\u0004\bU\u0010VR\u0011\u0010W\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\bX\u0010\u0013\u00a8\u0006\u0082\u0001"}, d2 = {"Lorg/wordpress/android/ui/posts/EditPostRepository;", "Lkotlinx/coroutines/CoroutineScope;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "postUtils", "Lorg/wordpress/android/ui/posts/PostUtilsWrapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "(Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/ui/posts/PostUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_postChanged", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "autoSaveContent", "", "getAutoSaveContent", "()Ljava/lang/String;", "autoSaveExcerpt", "getAutoSaveExcerpt", "autoSaveTitle", "getAutoSaveTitle", "changesConfirmedContentHashcode", "", "getChangesConfirmedContentHashcode", "()I", "content", "getContent", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "dateCreated", "getDateCreated", "dateLocallyChanged", "getDateLocallyChanged", "excerpt", "getExcerpt", "featuredImageId", "", "getFeaturedImageId", "()J", "id", "getId", "isLocalDraft", "", "()Z", "isLocallyChanged", "isPage", "job", "Lkotlinx/coroutines/Job;", "link", "getLink", "localSiteId", "getLocalSiteId", "location", "Lorg/wordpress/android/fluxc/model/post/PostLocation;", "getLocation", "()Lorg/wordpress/android/fluxc/model/post/PostLocation;", "locked", "password", "getPassword", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "postChanged", "Landroidx/lifecycle/LiveData;", "getPostChanged", "()Landroidx/lifecycle/LiveData;", "postForUndo", "postFormat", "getPostFormat", "postSnapshot", "postSnapshotWhenEditorOpened", "remotePostId", "getRemotePostId", "slug", "getSlug", "status", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "getStatus", "()Lorg/wordpress/android/fluxc/model/post/PostStatus;", "tagNameList", "", "getTagNameList", "()Ljava/util/List;", "title", "getTitle", "getEditablePost", "getPendingMediaForPost", "", "Lorg/wordpress/android/fluxc/model/MediaModel;", "getPendingOrInProgressMediaUploadsForPost", "getPost", "getPostForUndo", "hasLocation", "hasPost", "hasPostSnapshotWhenEditorOpened", "hasStatus", "isPostPublishable", "loadPostByLocalPostId", "", "postId", "loadPostByRemotePostId", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "postHasChanges", "postWasChangedInCurrentSession", "replace", "action", "Lkotlin/Function1;", "reportTransactionState", "lock", "saveForUndo", "savePostSnapshot", "savePostSnapshotWhenEditorOpened", "set", "Lkotlin/Function0;", "undo", "update", "T", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "updateAsync", "onCompleted", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult;", "updatePublishDateIfShouldBePublishedImmediately", "updateStatusFromPostSnapshotWhenEditorOpened", "UpdatePostResult", "org.wordpress.android_wordpressJalapenoDebug"})
public final class EditPostRepository implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.ui.posts.PostUtilsWrapper postUtils = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private kotlinx.coroutines.Job job;
    private org.wordpress.android.fluxc.model.PostModel post;
    private org.wordpress.android.fluxc.model.PostModel postForUndo;
    private org.wordpress.android.fluxc.model.PostModel postSnapshotWhenEditorOpened;
    private org.wordpress.android.fluxc.model.PostModel postSnapshot;
    private boolean locked = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.model.PostImmutableModel>> _postChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.model.PostImmutableModel>> postChanged = null;
    
    @javax.inject.Inject()
    public EditPostRepository(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostUtilsWrapper postUtils, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    public final int getId() {
        return 0;
    }
    
    public final int getLocalSiteId() {
        return 0;
    }
    
    public final long getRemotePostId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAutoSaveTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContent() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAutoSaveContent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExcerpt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAutoSaveExcerpt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPassword() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.post.PostStatus getStatus() {
        return null;
    }
    
    public final boolean isPage() {
        return false;
    }
    
    public final boolean isLocalDraft() {
        return false;
    }
    
    public final boolean isLocallyChanged() {
        return false;
    }
    
    public final long getFeaturedImageId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateCreated() {
        return null;
    }
    
    public final int getChangesConfirmedContentHashcode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPostFormat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSlug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLink() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.post.PostLocation getLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTagNameList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateLocallyChanged() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.model.PostImmutableModel>> getPostChanged() {
        return null;
    }
    
    @androidx.annotation.MainThread()
    public final <T extends java.lang.Object>T update(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, ? extends T> action) {
        return null;
    }
    
    public final void updateAsync(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> action, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.fluxc.model.PostImmutableModel, ? super org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult, kotlin.Unit> onCompleted) {
    }
    
    public final void replace(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, ? extends org.wordpress.android.fluxc.model.PostModel> action) {
    }
    
    public final void set(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends org.wordpress.android.fluxc.model.PostModel> action) {
    }
    
    @kotlin.jvm.Synchronized()
    private final synchronized void reportTransactionState(boolean lock) {
    }
    
    public final boolean hasLocation() {
        return false;
    }
    
    public final boolean hasPost() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.PostImmutableModel getPost() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.PostModel getEditablePost() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.PostModel getPostForUndo() {
        return null;
    }
    
    public final boolean hasStatus(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.post.PostStatus status) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<org.wordpress.android.fluxc.model.MediaModel> getPendingMediaForPost() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.fluxc.model.MediaModel> getPendingOrInProgressMediaUploadsForPost() {
        return null;
    }
    
    public final void updatePublishDateIfShouldBePublishedImmediately(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    public final boolean isPostPublishable() {
        return false;
    }
    
    public final void saveForUndo() {
    }
    
    public final void undo() {
    }
    
    public final boolean postHasChanges() {
        return false;
    }
    
    public final void savePostSnapshot() {
    }
    
    public final void savePostSnapshotWhenEditorOpened() {
    }
    
    public final boolean hasPostSnapshotWhenEditorOpened() {
        return false;
    }
    
    public final void updateStatusFromPostSnapshotWhenEditorOpened() {
    }
    
    public final boolean postWasChangedInCurrentSession() {
        return false;
    }
    
    public final void loadPostByLocalPostId(int postId) {
    }
    
    public final void loadPostByRemotePostId(long remotePostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult;", "", "()V", "NoChanges", "Updated", "Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult$Updated;", "Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult$NoChanges;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UpdatePostResult {
        
        private UpdatePostResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult$Updated;", "Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Updated extends org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult.Updated INSTANCE = null;
            
            private Updated() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult$NoChanges;", "Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NoChanges extends org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult.NoChanges INSTANCE = null;
            
            private NoChanges() {
                super();
            }
        }
    }
}
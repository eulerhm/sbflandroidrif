package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B3\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0012\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ?\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00170 2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00192\u0012\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0%\"\u00020&\u00a2\u0006\u0002\u0010\'J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u00132\b\u0010,\u001a\u0004\u0018\u00010-J\u0010\u0010.\u001a\u00020\u00132\b\u0010,\u001a\u0004\u0018\u00010-R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/CommentsStoreAdapter;", "Lkotlinx/coroutines/CoroutineScope;", "unifiedStore", "Lorg/wordpress/android/fluxc/store/CommentsStore;", "commentsMapper", "Lorg/wordpress/android/fluxc/model/comments/CommentsMapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lorg/wordpress/android/fluxc/store/CommentsStore;Lorg/wordpress/android/fluxc/model/comments/CommentsMapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/Dispatcher;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "dispatch", "", "action", "Lorg/wordpress/android/fluxc/annotations/action/Action;", "getCommentByLocalId", "Lorg/wordpress/android/fluxc/model/CommentModel;", "localId", "", "getCommentBySiteAndRemoteId", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "remoteCommentId", "", "getCommentsForSite", "", "orderByDateAscending", "", "limit", "statuses", "", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "(Lorg/wordpress/android/fluxc/model/SiteModel;ZI[Lorg/wordpress/android/fluxc/model/CommentStatus;)Ljava/util/List;", "logOrCrash", "msg", "", "register", "object", "", "unregister", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
@java.lang.Deprecated()
public final class CommentsStoreAdapter implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.fluxc.store.CommentsStore unifiedStore = null;
    private final org.wordpress.android.fluxc.model.comments.CommentsMapper commentsMapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private kotlinx.coroutines.Job job;
    
    @javax.inject.Inject()
    public CommentsStoreAdapter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.CommentsStore unifiedStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.comments.CommentsMapper commentsMapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.fluxc.model.CommentModel> getCommentsForSite(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, boolean orderByDateAscending, int limit, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.CommentStatus... statuses) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.CommentModel getCommentByLocalId(int localId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.CommentModel getCommentBySiteAndRemoteId(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, long remoteCommentId) {
        return null;
    }
    
    @kotlin.Suppress(names = {"FunctionParameterNaming"})
    public final void register(@org.jetbrains.annotations.Nullable()
    java.lang.Object object) {
    }
    
    @kotlin.Suppress(names = {"FunctionParameterNaming"})
    public final void unregister(@org.jetbrains.annotations.Nullable()
    java.lang.Object object) {
    }
    
    public final void dispatch(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.annotations.action.Action<?> action) {
    }
    
    private final void logOrCrash(java.lang.String msg) {
    }
}
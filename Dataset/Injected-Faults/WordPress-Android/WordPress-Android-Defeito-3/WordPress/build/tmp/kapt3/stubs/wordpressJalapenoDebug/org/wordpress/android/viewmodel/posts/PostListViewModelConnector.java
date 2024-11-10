package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00128\u0010\u0011\u001a4\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0015\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012\u00a2\u0006\u0002\u0010\u0018J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u0015R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR@\u0010\u0011\u001a4\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0015\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListViewModelConnector;", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "postListType", "Lorg/wordpress/android/ui/posts/PostListType;", "postActionHandler", "Lorg/wordpress/android/ui/posts/PostActionHandler;", "doesPostHaveUnhandledConflict", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/model/PostModel;", "", "hasAutoSave", "postFetcher", "Lorg/wordpress/android/viewmodel/posts/PostFetcher;", "uploadStatusTracker", "Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;", "getFeaturedImageUrl", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "featuredImageId", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/ui/posts/PostListType;Lorg/wordpress/android/ui/posts/PostActionHandler;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/viewmodel/posts/PostFetcher;Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;Lkotlin/jvm/functions/Function2;)V", "getDoesPostHaveUnhandledConflict", "()Lkotlin/jvm/functions/Function1;", "getHasAutoSave", "getPostActionHandler", "()Lorg/wordpress/android/ui/posts/PostActionHandler;", "getPostFetcher", "()Lorg/wordpress/android/viewmodel/posts/PostFetcher;", "getPostListType", "()Lorg/wordpress/android/ui/posts/PostListType;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "getUploadStatusTracker", "()Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListViewModelConnector {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.posts.PostListType postListType = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.posts.PostActionHandler postActionHandler = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> doesPostHaveUnhandledConflict = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> hasAutoSave = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.posts.PostFetcher postFetcher = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.posts.PostModelUploadStatusTracker uploadStatusTracker = null;
    private final kotlin.jvm.functions.Function2<org.wordpress.android.fluxc.model.SiteModel, java.lang.Long, java.lang.String> getFeaturedImageUrl = null;
    
    public PostListViewModelConnector(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListType postListType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostActionHandler postActionHandler, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> doesPostHaveUnhandledConflict, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> hasAutoSave, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostFetcher postFetcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostModelUploadStatusTracker uploadStatusTracker, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.fluxc.model.SiteModel, ? super java.lang.Long, java.lang.String> getFeaturedImageUrl) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PostListType getPostListType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PostActionHandler getPostActionHandler() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> getDoesPostHaveUnhandledConflict() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean> getHasAutoSave() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostFetcher getPostFetcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PostModelUploadStatusTracker getUploadStatusTracker() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFeaturedImageUrl(long featuredImageId) {
        return null;
    }
}
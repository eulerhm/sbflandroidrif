package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * This is a temporary class to make the PostListViewModel more manageable. Please feel free to refactor it any way
 * you see fit.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f0\u0007\u00a2\u0006\u0002\u0010\u0013J\b\u0010\u0017\u001a\u00020\fH\u0002J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\tJ\u0006\u0010\u001b\u001a\u00020\fJ\u000e\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\bR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/posts/PostConflictResolver;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getPostByLocalPostId", "Lkotlin/Function1;", "", "Lorg/wordpress/android/fluxc/model/PostModel;", "invalidateList", "Lkotlin/Function0;", "", "checkNetworkConnection", "", "showSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "showToast", "Lorg/wordpress/android/viewmodel/helpers/ToastMessageHolder;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "localPostIdForFetchingRemoteVersionOfConflictedPost", "Ljava/lang/Integer;", "originalPostCopyForConflictUndo", "conflictedPostUpdatedWithRemoteVersion", "doesPostHaveUnhandledConflict", "post", "hasUnhandledAutoSave", "onPostSuccessfullyUpdated", "updateConflictedPostWithLocalVersion", "localPostId", "updateConflictedPostWithRemoteVersion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostConflictResolver {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final kotlin.jvm.functions.Function1<java.lang.Integer, org.wordpress.android.fluxc.model.PostModel> getPostByLocalPostId = null;
    private final kotlin.jvm.functions.Function0<kotlin.Unit> invalidateList = null;
    private final kotlin.jvm.functions.Function0<java.lang.Boolean> checkNetworkConnection = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.pages.SnackbarMessageHolder, kotlin.Unit> showSnackbar = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.viewmodel.helpers.ToastMessageHolder, kotlin.Unit> showToast = null;
    private org.wordpress.android.fluxc.model.PostModel originalPostCopyForConflictUndo;
    private java.lang.Integer localPostIdForFetchingRemoteVersionOfConflictedPost;
    
    public PostConflictResolver(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends org.wordpress.android.fluxc.model.PostModel> getPostByLocalPostId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> invalidateList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<java.lang.Boolean> checkNetworkConnection, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.pages.SnackbarMessageHolder, kotlin.Unit> showSnackbar, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.viewmodel.helpers.ToastMessageHolder, kotlin.Unit> showToast) {
        super();
    }
    
    public final void updateConflictedPostWithRemoteVersion(int localPostId) {
    }
    
    public final void updateConflictedPostWithLocalVersion(int localPostId) {
    }
    
    public final boolean doesPostHaveUnhandledConflict(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
        return false;
    }
    
    public final boolean hasUnhandledAutoSave(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post) {
        return false;
    }
    
    public final void onPostSuccessfullyUpdated() {
    }
    
    private final void conflictedPostUpdatedWithRemoteVersion() {
    }
}
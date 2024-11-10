package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001aT\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u001a&\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002\u00a8\u0006\u0012"}, d2 = {"createEmptyListUiState", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$EmptyList;", "postListType", "Lorg/wordpress/android/ui/posts/PostListType;", "newPost", "Lkotlin/Function0;", "", "isSearchPromptRequired", "", "createEmptyUiState", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState;", "isNetworkAvailable", "isLoadingData", "isListEmpty", "error", "Lorg/wordpress/android/fluxc/store/ListStore$ListError;", "fetchFirstPage", "createErrorListUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListEmptyUiStateKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.viewmodel.posts.PostListEmptyUiState createEmptyUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListType postListType, boolean isNetworkAvailable, boolean isLoadingData, boolean isListEmpty, boolean isSearchPromptRequired, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.store.ListStore.ListError error, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> fetchFirstPage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> newPost) {
        return null;
    }
    
    private static final org.wordpress.android.viewmodel.posts.PostListEmptyUiState createErrorListUiState(boolean isNetworkAvailable, org.wordpress.android.fluxc.store.ListStore.ListError error, kotlin.jvm.functions.Function0<kotlin.Unit> fetchFirstPage) {
        return null;
    }
    
    private static final org.wordpress.android.viewmodel.posts.PostListEmptyUiState.EmptyList createEmptyListUiState(org.wordpress.android.ui.posts.PostListType postListType, kotlin.jvm.functions.Function0<kotlin.Unit> newPost, boolean isSearchPromptRequired) {
        return null;
    }
}
package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemType;", "", "()V", "EndListIndicatorItem", "LoadingItem", "PostListItemUiState", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$PostListItemUiState;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$LoadingItem;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$EndListIndicatorItem;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostListItemType {
    
    private PostListItemType() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0002\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemType$PostListItemUiState;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType;", "data", "Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateData;", "actions", "", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction;", "compactActions", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction$MoreItem;", "onSelected", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateData;Ljava/util/List;Lorg/wordpress/android/viewmodel/posts/PostListItemAction$MoreItem;Lkotlin/jvm/functions/Function0;)V", "getActions", "()Ljava/util/List;", "getCompactActions", "()Lorg/wordpress/android/viewmodel/posts/PostListItemAction$MoreItem;", "getData", "()Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateData;", "getOnSelected", "()Lkotlin/jvm/functions/Function0;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostListItemUiState extends org.wordpress.android.viewmodel.posts.PostListItemType {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.posts.PostListItemUiStateData data = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.viewmodel.posts.PostListItemAction> actions = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.posts.PostListItemAction.MoreItem compactActions = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onSelected = null;
        
        public PostListItemUiState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.posts.PostListItemUiStateData data, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.viewmodel.posts.PostListItemAction> actions, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.posts.PostListItemAction.MoreItem compactActions, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onSelected) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.posts.PostListItemUiStateData getData() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.viewmodel.posts.PostListItemAction> getActions() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.posts.PostListItemAction.MoreItem getCompactActions() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnSelected() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemType$LoadingItem;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType;", "localOrRemoteId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId;", "options", "Lorg/wordpress/android/viewmodel/posts/LoadingItemOptions;", "(Lorg/wordpress/android/fluxc/model/LocalOrRemoteId;Lorg/wordpress/android/viewmodel/posts/LoadingItemOptions;)V", "getLocalOrRemoteId", "()Lorg/wordpress/android/fluxc/model/LocalOrRemoteId;", "getOptions", "()Lorg/wordpress/android/viewmodel/posts/LoadingItemOptions;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LoadingItem extends org.wordpress.android.viewmodel.posts.PostListItemType {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.LocalOrRemoteId localOrRemoteId = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.posts.LoadingItemOptions options = null;
        
        public LoadingItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId localOrRemoteId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.posts.LoadingItemOptions options) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId getLocalOrRemoteId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.posts.LoadingItemOptions getOptions() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemType$EndListIndicatorItem;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EndListIndicatorItem extends org.wordpress.android.viewmodel.posts.PostListItemType {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.viewmodel.posts.PostListItemType.EndListIndicatorItem INSTANCE = null;
        
        private EndListIndicatorItem() {
            super();
        }
    }
}
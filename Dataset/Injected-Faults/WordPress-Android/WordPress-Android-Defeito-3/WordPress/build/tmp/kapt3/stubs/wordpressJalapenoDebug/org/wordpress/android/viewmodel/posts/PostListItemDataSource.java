package org.wordpress.android.viewmodel.posts;

import java.lang.System;

/**
 * This is the post list data source to be used by `ListStore`. Before making any changes, it's important to know:
 *
 * 1. Lists managed by `ListStore` works by first fetching a smaller version of the models and then fetching the
 * actual model if necessary.
 * 2. During [getItemIdentifiers] the actual models might not be available and should not be relied upon.
 * 3. In [getItemsAndFetchIfNecessary], if the actual model is not available, this class is responsible for fetching
 * that model. For this post list specifically, when the actual model is fetched the list will update itself.
 *
 * // TODO: We can add a link to the wiki for ListStore when that's available.
 * For more information, please see the documentation for `ListStore` components.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B9\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J2\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\r0\u001eH\u0002J,\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u001b2\u0006\u0010\u0014\u001a\u00020\u00022\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001b2\u0006\u0010\"\u001a\u00020#H\u0016J$\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u001b2\u0006\u0010\u0014\u001a\u00020\u00022\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bH\u0016J\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bH\u0002J\u001a\u0010\'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u001c2\b\u0010)\u001a\u0004\u0018\u00010\rH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemDataSource;", "Lorg/wordpress/android/fluxc/model/list/datasource/ListItemDataSourceInterface;", "Lorg/wordpress/android/fluxc/model/list/PostListDescriptor;", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "postFetcher", "Lorg/wordpress/android/viewmodel/posts/PostFetcher;", "transform", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/model/PostModel;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$PostListItemUiState;", "postListType", "Lorg/wordpress/android/ui/posts/PostListType;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/viewmodel/posts/PostFetcher;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/ui/posts/PostListType;)V", "fetchList", "", "listDescriptor", "offset", "", "fetchMissingRemotePosts", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "localOrRemoteIds", "", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId;", "remotePostMap", "", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "getItemIdentifiers", "remoteItemIds", "isListFullyFetched", "", "getItemsAndFetchIfNecessary", "itemIdentifiers", "localOrRemoteIdsFromPostListItemIds", "transformToPostListItemType", "localOrRemoteId", "post", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListItemDataSource implements org.wordpress.android.fluxc.model.list.datasource.ListItemDataSourceInterface<org.wordpress.android.fluxc.model.list.PostListDescriptor, org.wordpress.android.viewmodel.posts.PostListItemIdentifier, org.wordpress.android.viewmodel.posts.PostListItemType> {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.viewmodel.posts.PostFetcher postFetcher = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.PostModel, org.wordpress.android.viewmodel.posts.PostListItemType.PostListItemUiState> transform = null;
    private final org.wordpress.android.ui.posts.PostListType postListType = null;
    
    public PostListItemDataSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostFetcher postFetcher, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostModel, org.wordpress.android.viewmodel.posts.PostListItemType.PostListItemUiState> transform, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListType postListType) {
        super();
    }
    
    @java.lang.Override()
    public void fetchList(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.list.PostListDescriptor listDescriptor, long offset) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<org.wordpress.android.viewmodel.posts.PostListItemIdentifier> getItemIdentifiers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.list.PostListDescriptor listDescriptor, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId> remoteItemIds, boolean isListFullyFetched) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<org.wordpress.android.viewmodel.posts.PostListItemType> getItemsAndFetchIfNecessary(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.list.PostListDescriptor listDescriptor, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.viewmodel.posts.PostListItemIdentifier> itemIdentifiers) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId> localOrRemoteIdsFromPostListItemIds(java.util.List<? extends org.wordpress.android.viewmodel.posts.PostListItemIdentifier> itemIdentifiers) {
        return null;
    }
    
    private final void fetchMissingRemotePosts(org.wordpress.android.fluxc.model.SiteModel site, java.util.List<? extends org.wordpress.android.fluxc.model.LocalOrRemoteId> localOrRemoteIds, java.util.Map<org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, ? extends org.wordpress.android.fluxc.model.PostModel> remotePostMap) {
    }
    
    private final org.wordpress.android.viewmodel.posts.PostListItemType transformToPostListItemType(org.wordpress.android.fluxc.model.LocalOrRemoteId localOrRemoteId, org.wordpress.android.fluxc.model.PostModel post) {
        return null;
    }
}
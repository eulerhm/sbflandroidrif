package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001a\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u001a\u001a\u00020\u000e*\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u001dH\u0002J\u001a\u0010\u001e\u001a\u00020\u000e*\u00020\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\u001dH\u0002J\f\u0010 \u001a\u00020\u000e*\u00020\u001bH\u0002J\f\u0010!\u001a\u00020\u000e*\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/posts/HistoryListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "swipeToRefreshHelper", "Lorg/wordpress/android/util/helpers/SwipeToRefreshHelper;", "viewModel", "Lorg/wordpress/android/viewmodel/history/HistoryViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", "item", "Lorg/wordpress/android/ui/history/HistoryListItem;", "onViewCreated", "view", "Landroid/view/View;", "reloadList", "Lorg/wordpress/android/databinding/HistoryListFragmentBinding;", "data", "", "setList", "list", "setObservers", "updatePostOrPageEmptyView", "Companion", "HistoryItemClickInterface", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HistoryListFragment extends androidx.fragment.app.Fragment {
    private org.wordpress.android.util.helpers.SwipeToRefreshHelper swipeToRefreshHelper;
    private org.wordpress.android.viewmodel.history.HistoryViewModel viewModel;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.HistoryListFragment.Companion Companion = null;
    private static final java.lang.String KEY_POST_LOCAL_ID = "key_post_local_id";
    private static final java.lang.String KEY_SITE = "key_site";
    
    public HistoryListFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    private final void onItemClicked(org.wordpress.android.ui.history.HistoryListItem item) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updatePostOrPageEmptyView(org.wordpress.android.databinding.HistoryListFragmentBinding $this$updatePostOrPageEmptyView) {
    }
    
    private final void reloadList(org.wordpress.android.databinding.HistoryListFragmentBinding $this$reloadList, java.util.List<? extends org.wordpress.android.ui.history.HistoryListItem> data) {
    }
    
    private final void setList(org.wordpress.android.databinding.HistoryListFragmentBinding $this$setList, java.util.List<? extends org.wordpress.android.ui.history.HistoryListItem> list) {
    }
    
    private final void setObservers(org.wordpress.android.databinding.HistoryListFragmentBinding $this$setObservers) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/posts/HistoryListFragment$HistoryItemClickInterface;", "", "onHistoryItemClicked", "", "revision", "Lorg/wordpress/android/ui/history/HistoryListItem$Revision;", "revisions", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface HistoryItemClickInterface {
        
        public abstract void onHistoryItemClicked(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.history.HistoryListItem.Revision revision, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.history.HistoryListItem.Revision> revisions);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/posts/HistoryListFragment$Companion;", "", "()V", "KEY_POST_LOCAL_ID", "", "KEY_SITE", "newInstance", "Lorg/wordpress/android/ui/posts/HistoryListFragment;", "postId", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.HistoryListFragment newInstance(int postId, @org.jetbrains.annotations.NotNull()
        @androidx.annotation.NonNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
            return null;
        }
    }
}
package org.wordpress.android.ui.reader.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010-\u001a\u00020\u000fH\u0016J\u0010\u0010.\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u000fH\u0016J\u0014\u00100\u001a\u0002012\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J\u001c\u00103\u001a\u0002012\n\u00104\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010/\u001a\u00020\u000fH\u0016J\u001c\u00105\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u000fH\u0016R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\'\u001a\u00020(8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,\u00a8\u00069"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/CommentSnippetAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/reader/viewholders/CommentsSnippetViewHolder;", "context", "Landroid/content/Context;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Landroid/content/Context;Lorg/wordpress/android/models/ReaderPost;)V", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "getAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "setAccountStore", "(Lorg/wordpress/android/fluxc/store/AccountStore;)V", "contentWidth", "", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "itemsList", "", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "getReaderTracker", "()Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "setReaderTracker", "(Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "threadedCommentsUtils", "Lorg/wordpress/android/ui/reader/utils/ThreadedCommentsUtils;", "getThreadedCommentsUtils", "()Lorg/wordpress/android/ui/reader/utils/ThreadedCommentsUtils;", "setThreadedCommentsUtils", "(Lorg/wordpress/android/ui/reader/utils/ThreadedCommentsUtils;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "getItemCount", "getItemViewType", "position", "loadData", "", "items", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CommentSnippetAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.reader.viewholders.CommentsSnippetViewHolder<?>> {
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.models.ReaderPost post = null;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils threadedCommentsUtils;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.AccountStore accountStore;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private java.util.List<? extends org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState> itemsList;
    private int contentWidth;
    
    public CommentSnippetAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderPost getPost() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils getThreadedCommentsUtils() {
        return null;
    }
    
    public final void setThreadedCommentsUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getAccountStore() {
        return null;
    }
    
    public final void setAccountStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.tracker.ReaderTracker getReaderTracker() {
        return null;
    }
    
    public final void setReaderTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    public final void loadData(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState> items) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.reader.viewholders.CommentsSnippetViewHolder<?> onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.viewholders.CommentsSnippetViewHolder<?> holder, int position) {
    }
}
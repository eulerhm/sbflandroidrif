package org.wordpress.android.ui.reader.discover.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J&\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J(\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001dH\u0002J\u0010\u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010&\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\'\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/reader/discover/viewholders/ReaderPostViewHolder;", "Lorg/wordpress/android/ui/reader/discover/viewholders/ReaderViewHolder;", "Lorg/wordpress/android/databinding/ReaderCardviewPostBinding;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "parentView", "Landroid/view/ViewGroup;", "(Lorg/wordpress/android/ui/utils/UiHelpers;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Landroid/view/ViewGroup;)V", "viewContext", "Landroid/content/Context;", "getViewContext", "()Landroid/content/Context;", "loadVideoThumbnail", "", "state", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState;", "(Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState;)Lkotlin/Unit;", "onBind", "uiState", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "renderMoreMenu", "actions", "", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction;", "v", "Landroid/view/View;", "updateActionButton", "postId", "", "blogId", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;", "view", "updateAvatarOrBlavatar", "updateBlogSection", "updateDiscoverSection", "updateFeaturedImage", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderPostViewHolder extends org.wordpress.android.ui.reader.discover.viewholders.ReaderViewHolder<org.wordpress.android.databinding.ReaderCardviewPostBinding> {
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context viewContext = null;
    
    public ReaderPostViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parentView) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getViewContext() {
        return null;
    }
    
    @java.lang.Override()
    public void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderCardUiState uiState) {
    }
    
    private final void updateBlogSection(org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState state) {
    }
    
    private final void updateFeaturedImage(org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState state) {
    }
    
    private final void updateAvatarOrBlavatar(org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState state) {
    }
    
    private final void updateDiscoverSection(org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState state) {
    }
    
    private final void updateActionButton(long postId, long blogId, org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction state, android.view.View view) {
    }
    
    private final kotlin.Unit loadVideoThumbnail(org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState state) {
        return null;
    }
    
    private final void renderMoreMenu(org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState uiState, java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction> actions, android.view.View v) {
    }
}
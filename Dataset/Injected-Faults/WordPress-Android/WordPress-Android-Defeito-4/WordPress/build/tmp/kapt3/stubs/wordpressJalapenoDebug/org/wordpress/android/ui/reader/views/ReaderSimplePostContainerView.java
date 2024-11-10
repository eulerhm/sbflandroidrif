package org.wordpress.android.ui.reader.views;

import java.lang.System;

/**
 * used by the detail view to display related posts, which can be either local (related posts
 * from the same site as the source post) or global (related posts from across wp.com)
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020!J\u0014\u0010%\u001a\u00020!*\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u00020\u001b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/reader/views/ReaderSimplePostContainerView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lorg/wordpress/android/databinding/ReaderSimplePostsContainerViewBinding;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "railcarJsonStrings", "", "", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "getReaderTracker", "()Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "setReaderTracker", "(Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "showPosts", "", "state", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState;", "trackRailcarRender", "initRecyclerView", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderSimplePostContainerView extends android.widget.LinearLayout {
    private final org.wordpress.android.databinding.ReaderSimplePostsContainerViewBinding binding = null;
    private final java.util.List<java.lang.String> railcarJsonStrings = null;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker;
    
    @kotlin.jvm.JvmOverloads()
    public ReaderSimplePostContainerView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public ReaderSimplePostContainerView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public ReaderSimplePostContainerView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.tracker.ReaderTracker getReaderTracker() {
        return null;
    }
    
    public final void setReaderTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker p0) {
    }
    
    private final void initRecyclerView(org.wordpress.android.databinding.ReaderSimplePostsContainerViewBinding $this$initRecyclerView, android.content.Context context) {
    }
    
    public final void showPosts(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState state) {
    }
    
    public final void trackRailcarRender() {
    }
}
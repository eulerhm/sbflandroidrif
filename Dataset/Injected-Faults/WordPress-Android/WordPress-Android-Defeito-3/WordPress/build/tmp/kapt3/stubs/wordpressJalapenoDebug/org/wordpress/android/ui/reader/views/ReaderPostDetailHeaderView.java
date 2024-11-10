package org.wordpress.android.ui.reader.views;

import java.lang.System;

/**
 * topmost view in post detail
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0014\u0010\u001b\u001a\u00020\u0018*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0014\u0010\u001f\u001a\u00020\u0018*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0014\u0010 \u001a\u00020\u0018*\u00020\n2\u0006\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/reader/views/ReaderPostDetailHeaderView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lorg/wordpress/android/databinding/ReaderPostDetailHeaderViewBinding;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "updatePost", "", "uiState", "Lorg/wordpress/android/ui/reader/views/uistates/ReaderPostDetailsHeaderViewUiState$ReaderPostDetailsHeaderUiState;", "updateBlavatar", "Lorg/wordpress/android/databinding/ReaderBlogSectionViewBinding;", "state", "Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState;", "updateBlogSection", "updateFollowButton", "followButtonUiState", "Lorg/wordpress/android/ui/reader/views/uistates/FollowButtonUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderPostDetailHeaderView extends android.widget.LinearLayout {
    private final org.wordpress.android.databinding.ReaderPostDetailHeaderViewBinding binding = null;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    
    @kotlin.jvm.JvmOverloads()
    public ReaderPostDetailHeaderView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public ReaderPostDetailHeaderView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public ReaderPostDetailHeaderView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    public final void updatePost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.views.uistates.ReaderPostDetailsHeaderViewUiState.ReaderPostDetailsHeaderUiState uiState) {
    }
    
    private final void updateBlogSection(org.wordpress.android.databinding.ReaderBlogSectionViewBinding $this$updateBlogSection, org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState state) {
    }
    
    private final void updateBlavatar(org.wordpress.android.databinding.ReaderBlogSectionViewBinding $this$updateBlavatar, org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState state) {
    }
    
    private final void updateFollowButton(org.wordpress.android.databinding.ReaderPostDetailHeaderViewBinding $this$updateFollowButton, org.wordpress.android.ui.reader.views.uistates.FollowButtonUiState followButtonUiState) {
    }
}
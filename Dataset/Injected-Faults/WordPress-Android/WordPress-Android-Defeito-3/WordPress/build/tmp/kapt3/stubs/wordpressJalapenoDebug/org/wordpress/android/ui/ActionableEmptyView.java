package org.wordpress.android.ui;

import java.lang.System;

/**
 * View shown when screen is in an empty state.  It contains the following:
 * - Image showing related illustration (optional)
 * - Title describing cause for empty state (required)
 * - Subtitle detailing cause for empty state (optional)
 * - Button providing action to take (optional)
 * - Bottom Image which can be used for attribution logos (optional)
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0006\u0010.\u001a\u00020/J\u0018\u00100\u001a\u00020/2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0016\u00101\u001a\u00020/2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\bR\u001a\u0010\n\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\u001a\u0010\u0019\u001a\u00020\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020&X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/ActionableEmptyView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bottomImage", "Landroid/widget/ImageView;", "getBottomImage", "()Landroid/widget/ImageView;", "setBottomImage", "(Landroid/widget/ImageView;)V", "button", "Landroidx/appcompat/widget/AppCompatButton;", "getButton", "()Landroidx/appcompat/widget/AppCompatButton;", "setButton", "(Landroidx/appcompat/widget/AppCompatButton;)V", "image", "getImage", "setImage", "layout", "Landroid/view/View;", "getLayout", "()Landroid/view/View;", "setLayout", "(Landroid/view/View;)V", "progressBar", "Landroid/widget/ProgressBar;", "getProgressBar", "()Landroid/widget/ProgressBar;", "setProgressBar", "(Landroid/widget/ProgressBar;)V", "subtitle", "Lorg/wordpress/android/widgets/WPTextView;", "getSubtitle", "()Lorg/wordpress/android/widgets/WPTextView;", "setSubtitle", "(Lorg/wordpress/android/widgets/WPTextView;)V", "title", "getTitle", "setTitle", "announceEmptyStateForAccessibility", "", "initView", "updateLayoutForSearch", "isSearching", "", "topMargin", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActionableEmptyView extends android.widget.LinearLayout {
    public androidx.appcompat.widget.AppCompatButton button;
    public android.widget.ImageView image;
    public android.view.View layout;
    public org.wordpress.android.widgets.WPTextView subtitle;
    public org.wordpress.android.widgets.WPTextView title;
    public android.widget.ProgressBar progressBar;
    
    /**
     * Image shown at the bottom after the subtitle.
     *
     * This can be used for attribution logos. This is [View.GONE] by default.
     */
    public android.widget.ImageView bottomImage;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.appcompat.widget.AppCompatButton getButton() {
        return null;
    }
    
    public final void setButton(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.widget.AppCompatButton p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.ImageView getImage() {
        return null;
    }
    
    public final void setImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.View getLayout() {
        return null;
    }
    
    public final void setLayout(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.widgets.WPTextView getSubtitle() {
        return null;
    }
    
    public final void setSubtitle(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.widgets.WPTextView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.widgets.WPTextView getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.widgets.WPTextView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.ProgressBar getProgressBar() {
        return null;
    }
    
    public final void setProgressBar(@org.jetbrains.annotations.NotNull()
    android.widget.ProgressBar p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.ImageView getBottomImage() {
        return null;
    }
    
    public final void setBottomImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView p0) {
    }
    
    public ActionableEmptyView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public ActionableEmptyView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attrs, int defStyle) {
        super(null);
    }
    
    private final void initView(android.content.Context context, android.util.AttributeSet attrs) {
    }
    
    /**
     * Update actionable empty view layout when used while searching.  The following characteristics are for each case:
     *     Default - center in parent, use original top margin
     *     Search  - center at top of parent, use original top margin, add 48dp top padding, hide image, hide button
     *
     * @param isSearching true when searching; false otherwise
     * @param topMargin top margin in pixels to offset with other views (e.g. toolbar or tabs)
     */
    public final void updateLayoutForSearch(boolean isSearching, int topMargin) {
    }
    
    /**
     * Announces the empty view when the empty state occurs. Due to the formatting of subtitle text in certain
     * circumstances TalkBack isn't able to properly make it's announcement so in cases like these the content
     * description is dynamically added before doing so.
     */
    public final void announceEmptyStateForAccessibility() {
    }
}
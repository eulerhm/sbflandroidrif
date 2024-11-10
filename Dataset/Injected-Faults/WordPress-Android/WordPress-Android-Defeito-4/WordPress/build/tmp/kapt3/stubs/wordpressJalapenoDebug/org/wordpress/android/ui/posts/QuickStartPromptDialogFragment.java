package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 52\u00020\u0001:\u000256B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0002J<\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\b\b\u0003\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0006H\u0007J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#H\u0016J\u0012\u0010$\u001a\u00020\u001b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010\'\u001a\u00020(2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J&\u0010)\u001a\u0004\u0018\u00010 2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020&H\u0016J\u0010\u00100\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u00101\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u00102\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u00103\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u00104\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u0012\u0010\u0003\u001a\u00020\u00048\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lorg/wordpress/android/ui/posts/QuickStartPromptDialogFragment;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "drawableResId", "", "fragmentTag", "", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "message", "negativeButtonLabel", "positiveButtonLabel", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "getSelectedSiteRepository", "()Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "setSelectedSiteRepository", "(Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "siteRecord", "Lorg/wordpress/android/ui/main/SitePickerAdapter$SiteRecord;", "title", "getTheme", "initDagger", "", "initialize", "tag", "initializeView", "view", "Landroid/view/View;", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onSaveInstanceState", "outState", "updateDialogDescription", "updateDialogTitle", "updateNegativeButton", "updatePositiveButton", "updateSiteLayout", "Companion", "QuickStartPromptClickInterface", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartPromptDialogFragment extends androidx.appcompat.app.AppCompatDialogFragment {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.QuickStartPromptDialogFragment.Companion Companion = null;
    private static final java.lang.String STATE_KEY_DRAWABLE_RES_ID = "state_key_drawable";
    private static final java.lang.String STATE_KEY_TAG = "state_key_tag";
    private static final java.lang.String STATE_KEY_TITLE = "state_key_title";
    private static final java.lang.String STATE_KEY_MESSAGE = "state_key_message";
    private static final java.lang.String STATE_KEY_POSITIVE_BUTTON_LABEL = "state_key_positive_button_label";
    private static final java.lang.String STATE_KEY_NEGATIVE_BUTTON_LABEL = "state_key_negative_button_label";
    private static final int UNDEFINED_RES_ID = -1;
    private static final int SITE_IMAGE_CORNER_RADIUS_IN_DP = 4;
    @androidx.annotation.DrawableRes()
    private int drawableResId = -1;
    private java.lang.String fragmentTag;
    private java.lang.String message;
    private java.lang.String negativeButtonLabel;
    private java.lang.String positiveButtonLabel;
    private java.lang.String title;
    private org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteRecord;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository;
    
    public QuickStartPromptDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.SelectedSiteRepository getSelectedSiteRepository() {
        return null;
    }
    
    public final void setSelectedSiteRepository(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository p0) {
    }
    
    @java.lang.Override()
    public int getTheme() {
        return 0;
    }
    
    @kotlin.jvm.JvmOverloads()
    @kotlin.Suppress(names = {"LongParameterList"})
    public final void initialize(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String positiveButtonLabel) {
    }
    
    @kotlin.jvm.JvmOverloads()
    @kotlin.Suppress(names = {"LongParameterList"})
    public final void initialize(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String positiveButtonLabel, @androidx.annotation.DrawableRes()
    int drawableResId) {
    }
    
    @kotlin.jvm.JvmOverloads()
    @kotlin.Suppress(names = {"LongParameterList"})
    public final void initialize(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String positiveButtonLabel, @androidx.annotation.DrawableRes()
    int drawableResId, @org.jetbrains.annotations.NotNull()
    java.lang.String negativeButtonLabel) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void initDagger() {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void initializeView(android.view.View view) {
    }
    
    private final void updateSiteLayout(android.view.View view) {
    }
    
    private final void updateDialogTitle(android.view.View view) {
    }
    
    private final void updateDialogDescription(android.view.View view) {
    }
    
    private final void updatePositiveButton(android.view.View view) {
    }
    
    private final void updateNegativeButton(android.view.View view) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/QuickStartPromptDialogFragment$QuickStartPromptClickInterface;", "", "onNegativeClicked", "", "instanceTag", "", "onPositiveClicked", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface QuickStartPromptClickInterface {
        
        public abstract void onNegativeClicked(@org.jetbrains.annotations.NotNull()
        java.lang.String instanceTag);
        
        public abstract void onPositiveClicked(@org.jetbrains.annotations.NotNull()
        java.lang.String instanceTag);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/posts/QuickStartPromptDialogFragment$Companion;", "", "()V", "SITE_IMAGE_CORNER_RADIUS_IN_DP", "", "STATE_KEY_DRAWABLE_RES_ID", "", "STATE_KEY_MESSAGE", "STATE_KEY_NEGATIVE_BUTTON_LABEL", "STATE_KEY_POSITIVE_BUTTON_LABEL", "STATE_KEY_TAG", "STATE_KEY_TITLE", "UNDEFINED_RES_ID", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
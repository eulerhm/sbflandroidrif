package org.wordpress.android.ui.mediapicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ae\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u0000 U2\u00020\u00012\u00020\u0002:\u0002UVB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010(\u001a\u00020)H\u0002J\b\u00102\u001a\u00020/H\u0002J\"\u00103\u001a\u00020/2\u0006\u00104\u001a\u00020\u00172\u0006\u00105\u001a\u00020\u00172\b\u00106\u001a\u0004\u0018\u000107H\u0014J\u0012\u00108\u001a\u00020/2\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\u0010\u0010;\u001a\u00020/2\u0006\u0010<\u001a\u00020=H\u0016J\u0016\u0010>\u001a\u00020/2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@H\u0016J\u0010\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0016J\u0010\u0010F\u001a\u00020/2\u0006\u00109\u001a\u00020:H\u0014J\u0010\u0010G\u001a\u00020/2\u0006\u0010H\u001a\u00020:H\u0014J\u001a\u0010I\u001a\u00020/*\u0002072\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00170@H\u0002J\u001a\u0010K\u001a\u00020/*\u0002072\f\u0010L\u001a\b\u0012\u0004\u0012\u00020M0@H\u0002J\u001a\u0010N\u001a\u00020/*\u0002072\f\u0010O\u001a\b\u0012\u0004\u0012\u00020P0@H\u0002J\u001a\u0010Q\u001a\u00020/*\u0002072\f\u0010O\u001a\b\u0012\u0004\u0012\u00020P0@H\u0002J\u001d\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00190S*\b\u0012\u0004\u0012\u00020P0@H\u0002\u00a2\u0006\u0002\u0010TR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0016\u0010\"\u001a\u0004\u0018\u00010#8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b$\u0010%R\u0010\u0010&\u001a\u0004\u0018\u00010\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010(\u001a\u00020)8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-\u00a8\u0006W"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerListener;", "()V", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "setDispatcher", "(Lorg/wordpress/android/fluxc/Dispatcher;)V", "featuredImageHelper", "Lorg/wordpress/android/ui/posts/FeaturedImageHelper;", "getFeaturedImageHelper", "()Lorg/wordpress/android/ui/posts/FeaturedImageHelper;", "setFeaturedImageHelper", "(Lorg/wordpress/android/ui/posts/FeaturedImageHelper;)V", "imageEditorTracker", "Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker;", "getImageEditorTracker", "()Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker;", "setImageEditorTracker", "(Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker;)V", "localPostId", "", "mediaCapturePath", "", "mediaPickerSetup", "Lorg/wordpress/android/ui/mediapicker/MediaPickerSetup;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "getMediaStore", "()Lorg/wordpress/android/fluxc/store/MediaStore;", "setMediaStore", "(Lorg/wordpress/android/fluxc/store/MediaStore;)V", "pickerFragment", "Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment;", "getPickerFragment", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "launchChooserWithContext", "", "openSystemPicker", "Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerAction$OpenSystemPicker;", "launchWPStoriesCamera", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onIconClicked", "action", "Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerAction;", "onItemsChosen", "identifiers", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onRestoreInstanceState", "onSaveInstanceState", "outState", "putLocalIds", "mediaLocalIds", "putMediaIds", "mediaIds", "", "putQueuedUris", "mediaUris", "Landroid/net/Uri;", "putUris", "toStringArray", "", "(Ljava/util/List;)[Ljava/lang/String;", "Companion", "MediaPickerMediaSource", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MediaPickerActivity extends org.wordpress.android.ui.LocaleAwareActivity implements org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerListener {
    private java.lang.String mediaCapturePath;
    private org.wordpress.android.ui.mediapicker.MediaPickerSetup mediaPickerSetup;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private int localPostId = -1;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.Dispatcher dispatcher;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.MediaStore mediaStore;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.FeaturedImageHelper featuredImageHelper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.editor.ImageEditorTracker imageEditorTracker;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mediapicker.MediaPickerActivity.Companion Companion = null;
    private static final java.lang.String PICKER_FRAGMENT_TAG = "picker_fragment_tag";
    private static final java.lang.String KEY_MEDIA_CAPTURE_PATH = "media_capture_path";
    
    public MediaPickerActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    public final void setDispatcher(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.MediaStore getMediaStore() {
        return null;
    }
    
    public final void setMediaStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.FeaturedImageHelper getFeaturedImageHelper() {
        return null;
    }
    
    public final void setFeaturedImageHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.FeaturedImageHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.editor.ImageEditorTracker getImageEditorTracker() {
        return null;
    }
    
    public final void setImageEditorTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.ImageEditorTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaPickerFragment getPickerFragment() {
        return null;
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    protected void onRestoreInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void launchChooserWithContext(org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerAction.OpenSystemPicker openSystemPicker, org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
    }
    
    private final void launchWPStoriesCamera() {
    }
    
    private final void putUris(android.content.Intent $this$putUris, java.util.List<? extends android.net.Uri> mediaUris) {
    }
    
    private final void putQueuedUris(android.content.Intent $this$putQueuedUris, java.util.List<? extends android.net.Uri> mediaUris) {
    }
    
    private final void putMediaIds(android.content.Intent $this$putMediaIds, java.util.List<java.lang.Long> mediaIds) {
    }
    
    private final void putLocalIds(android.content.Intent $this$putLocalIds, java.util.List<java.lang.Integer> mediaLocalIds) {
    }
    
    @java.lang.Override()
    public void onItemsChosen(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers) {
    }
    
    @java.lang.Override()
    public void onIconClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerAction action) {
    }
    
    private final java.lang.String[] toStringArray(java.util.List<? extends android.net.Uri> $this$toStringArray) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerActivity$MediaPickerMediaSource;", "", "(Ljava/lang/String;I)V", "ANDROID_CAMERA", "ANDROID_PICKER", "APP_PICKER", "WP_MEDIA_PICKER", "STOCK_MEDIA_PICKER", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum MediaPickerMediaSource {
        /*public static final*/ ANDROID_CAMERA /* = new ANDROID_CAMERA() */,
        /*public static final*/ ANDROID_PICKER /* = new ANDROID_PICKER() */,
        /*public static final*/ APP_PICKER /* = new APP_PICKER() */,
        /*public static final*/ WP_MEDIA_PICKER /* = new WP_MEDIA_PICKER() */,
        /*public static final*/ STOCK_MEDIA_PICKER /* = new STOCK_MEDIA_PICKER() */;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.mediapicker.MediaPickerActivity.MediaPickerMediaSource.Companion Companion = null;
        
        MediaPickerMediaSource() {
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerActivity$MediaPickerMediaSource$Companion;", "", "()V", "fromDataSource", "Lorg/wordpress/android/ui/mediapicker/MediaPickerActivity$MediaPickerMediaSource;", "dataSource", "Lorg/wordpress/android/ui/mediapicker/MediaPickerSetup$DataSource;", "fromString", "strSource", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.mediapicker.MediaPickerActivity.MediaPickerMediaSource fromString(@org.jetbrains.annotations.Nullable()
            java.lang.String strSource) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerActivity.MediaPickerMediaSource fromDataSource(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mediapicker.MediaPickerSetup.DataSource dataSource) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J3\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerActivity$Companion;", "", "()V", "KEY_MEDIA_CAPTURE_PATH", "", "PICKER_FRAGMENT_TAG", "buildIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "mediaPickerSetup", "Lorg/wordpress/android/ui/mediapicker/MediaPickerSetup;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "localPostId", "", "(Landroid/content/Context;Lorg/wordpress/android/ui/mediapicker/MediaPickerSetup;Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/Integer;)Landroid/content/Intent;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerSetup mediaPickerSetup, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
        java.lang.Integer localPostId) {
            return null;
        }
    }
}
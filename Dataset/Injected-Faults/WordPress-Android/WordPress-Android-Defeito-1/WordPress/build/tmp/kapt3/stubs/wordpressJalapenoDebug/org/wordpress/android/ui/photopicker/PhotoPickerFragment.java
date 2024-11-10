package org.wordpress.android.ui.photopicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 O2\u00020\u0001:\u0003OPQB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001bJ\u0010\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0014\u0010$\u001a\u00020\u001b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&J\u0012\u0010(\u001a\u00020\u001b2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\u001bH\u0016J+\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002\u0006\u00102\u001a\u000203H\u0016\u00a2\u0006\u0002\u00104J\b\u00105\u001a\u00020\u001bH\u0016J\u0010\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u00020*H\u0016J\u001a\u00108\u001a\u00020\u001b2\u0006\u00109\u001a\u00020\"2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u000e\u0010:\u001a\u00020\u001b2\u0006\u00109\u001a\u00020\"J\u0006\u0010;\u001a\u00020\u001bJ\b\u0010<\u001a\u00020\u001bH\u0002J\b\u0010=\u001a\u00020\u001bH\u0002J\u0010\u0010>\u001a\u00020\u001b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\b\u0010?\u001a\u00020\u001bH\u0002J\u0010\u0010@\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\u0002J\u000e\u0010A\u001a\u00020\u001b2\u0006\u00109\u001a\u00020\"J\u0014\u0010B\u001a\u00020\u001b2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0&J\u0014\u0010E\u001a\u00020\u001b*\u00020\u00042\u0006\u0010F\u001a\u00020GH\u0002J\u0014\u0010H\u001a\u00020\u001b*\u00020\u00042\u0006\u0010I\u001a\u00020JH\u0002J\u0014\u0010K\u001a\u00020\u001b*\u00020\u00042\u0006\u0010F\u001a\u00020LH\u0002J\u0014\u0010M\u001a\u00020\u001b*\u00020\u00042\u0006\u0010F\u001a\u00020NH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u00a8\u0006R"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lorg/wordpress/android/databinding/PhotoPickerFragmentBinding;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "isStoragePermissionAlwaysDenied", "", "()Z", "listener", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerListener;", "viewModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "canShowMediaSourceBottomBar", "hideMediaBottomBarInPortrait", "checkStoragePermission", "", "doIconClicked", "wpMedia", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;", "finishActionMode", "hideBottomBar", "bottomBar", "Landroid/view/View;", "isBottomBarShowing", "mediaIdsSelectedFromWPMediaPicker", "mediaIds", "", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onSaveInstanceState", "outState", "onViewCreated", "view", "performActionOrShowPopup", "refresh", "requestCameraPermission", "requestStoragePermission", "setPhotoPickerListener", "setupProgressDialog", "showBottomBar", "showCameraPopupMenu", "urisSelectedFromSystemPicker", "uris", "Landroid/net/Uri;", "setupBottomBar", "uiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel;", "setupFab", "fabUiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$FabUiModel;", "setupPhotoList", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel;", "setupSoftAskView", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel;", "Companion", "PhotoPickerIcon", "PhotoPickerListener", "org.wordpress.android_wordpressJalapenoDebug"})
@java.lang.Deprecated()
public final class PhotoPickerFragment extends androidx.fragment.app.Fragment {
    private org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerListener listener;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.photopicker.PhotoPickerViewModel viewModel;
    private org.wordpress.android.databinding.PhotoPickerFragmentBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.photopicker.PhotoPickerFragment.Companion Companion = null;
    private static final java.lang.String KEY_LAST_TAPPED_ICON = "last_tapped_icon";
    private static final java.lang.String KEY_SELECTED_POSITIONS = "selected_positions";
    private static final java.lang.String KEY_LIST_STATE = "list_state";
    public static final int NUM_COLUMNS = 3;
    
    public PhotoPickerFragment() {
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
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void setupSoftAskView(org.wordpress.android.databinding.PhotoPickerFragmentBinding $this$setupSoftAskView, org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel uiModel) {
    }
    
    private final void setupPhotoList(org.wordpress.android.databinding.PhotoPickerFragmentBinding $this$setupPhotoList, org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel uiModel) {
    }
    
    private final void setupFab(org.wordpress.android.databinding.PhotoPickerFragmentBinding $this$setupFab, org.wordpress.android.ui.photopicker.PhotoPickerViewModel.FabUiModel fabUiModel) {
    }
    
    private final void setupBottomBar(org.wordpress.android.databinding.PhotoPickerFragmentBinding $this$setupBottomBar, org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel uiModel) {
    }
    
    private final void setupProgressDialog() {
    }
    
    private final boolean canShowMediaSourceBottomBar(boolean hideMediaBottomBarInPortrait) {
        return false;
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    public final void performActionOrShowPopup(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void showCameraPopupMenu(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void setPhotoPickerListener(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerListener listener) {
    }
    
    private final void showBottomBar(android.view.View bottomBar) {
    }
    
    private final void hideBottomBar(android.view.View bottomBar) {
    }
    
    private final boolean isBottomBarShowing(android.view.View bottomBar) {
        return false;
    }
    
    public final void refresh() {
    }
    
    private final boolean isStoragePermissionAlwaysDenied() {
        return false;
    }
    
    private final void checkStoragePermission() {
    }
    
    private final void requestStoragePermission() {
    }
    
    private final void requestCameraPermission() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    public final void finishActionMode() {
    }
    
    public final void doIconClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon wpMedia) {
    }
    
    public final void urisSelectedFromSystemPicker(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris) {
    }
    
    public final void mediaIdsSelectedFromWPMediaPicker(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> mediaIds) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.photopicker.PhotoPickerFragment newInstance(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerListener listener, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.media.MediaBrowserType browserType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;", "", "mRequiresUploadPermission", "", "(Ljava/lang/String;IZ)V", "requiresUploadPermission", "ANDROID_CHOOSE_PHOTO", "ANDROID_CHOOSE_VIDEO", "ANDROID_CAPTURE_PHOTO", "ANDROID_CAPTURE_VIDEO", "ANDROID_CHOOSE_PHOTO_OR_VIDEO", "WP_MEDIA", "STOCK_MEDIA", "GIF", "WP_STORIES_CAPTURE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PhotoPickerIcon {
        /*public static final*/ ANDROID_CHOOSE_PHOTO /* = new ANDROID_CHOOSE_PHOTO(false) */,
        /*public static final*/ ANDROID_CHOOSE_VIDEO /* = new ANDROID_CHOOSE_VIDEO(false) */,
        /*public static final*/ ANDROID_CAPTURE_PHOTO /* = new ANDROID_CAPTURE_PHOTO(false) */,
        /*public static final*/ ANDROID_CAPTURE_VIDEO /* = new ANDROID_CAPTURE_VIDEO(false) */,
        /*public static final*/ ANDROID_CHOOSE_PHOTO_OR_VIDEO /* = new ANDROID_CHOOSE_PHOTO_OR_VIDEO(false) */,
        /*public static final*/ WP_MEDIA /* = new WP_MEDIA(false) */,
        /*public static final*/ STOCK_MEDIA /* = new STOCK_MEDIA(false) */,
        /*public static final*/ GIF /* = new GIF(false) */,
        /*public static final*/ WP_STORIES_CAPTURE /* = new WP_STORIES_CAPTURE(false) */;
        private final boolean mRequiresUploadPermission = false;
        
        PhotoPickerIcon(boolean mRequiresUploadPermission) {
        }
        
        public final boolean requiresUploadPermission() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerListener;", "", "onPhotoPickerIconClicked", "", "icon", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;", "allowMultipleSelection", "", "onPhotoPickerMediaChosen", "uriList", "", "Landroid/net/Uri;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface PhotoPickerListener {
        
        public abstract void onPhotoPickerMediaChosen(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends android.net.Uri> uriList);
        
        public abstract void onPhotoPickerIconClicked(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon icon, boolean allowMultipleSelection);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$Companion;", "", "()V", "KEY_LAST_TAPPED_ICON", "", "KEY_LIST_STATE", "KEY_SELECTED_POSITIONS", "NUM_COLUMNS", "", "newInstance", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment;", "listener", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerListener;", "browserType", "Lorg/wordpress/android/ui/media/MediaBrowserType;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.photopicker.PhotoPickerFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerListener listener, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.media.MediaBrowserType browserType, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.SiteModel site) {
            return null;
        }
    }
}
package org.wordpress.android.ui.photopicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00e4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\nrstuvwxyz{BS\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010C\u001a\u00020D2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001bH\u0002J0\u0010E\u001a\u00020F2\u000e\u0010G\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001b2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b2\u0006\u0010H\u001a\u00020IH\u0002J(\u0010J\u001a\u00020K2\u000e\u0010L\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001b2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001bH\u0002J\u0012\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010\'H\u0002J\u000e\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020IJ\u0006\u0010S\u001a\u00020QJ\u000e\u0010T\u001a\u00020Q2\u0006\u0010U\u001a\u00020+J\"\u0010V\u001a\u00020Q2\u0006\u0010W\u001a\u00020!2\b\u0010X\u001a\u0004\u0018\u00010\u00172\u0006\u0010Y\u001a\u00020IH\u0002J\u0006\u0010Z\u001a\u00020QJ\u0014\u0010[\u001a\u00020Q2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00170\u001bJ\u0014\u0010]\u001a\u00020Q2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020!0\u001bJ\u0006\u0010_\u001a\u00020`J\u000e\u0010a\u001a\u00020Q2\u0006\u0010b\u001a\u00020cJ\u000e\u0010d\u001a\u00020Q2\u0006\u0010b\u001a\u00020cJ\u0006\u0010e\u001a\u00020QJ\u000e\u0010f\u001a\u00020Q2\u0006\u0010g\u001a\u00020IJ\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00170\u001bJ\u000e\u0010i\u001a\u00020Q2\u0006\u0010b\u001a\u00020cJ2\u0010j\u001a\u00020Q2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010>\u001a\u0004\u0018\u00010?J\u0018\u0010k\u001a\u00020Q2\u0006\u0010W\u001a\u00020!2\u0006\u0010l\u001a\u00020IH\u0002J\"\u0010m\u001a\u00020Q2\u0006\u0010W\u001a\u00020!2\b\u0010X\u001a\u0004\u0018\u00010\u00172\u0006\u0010Y\u001a\u00020IH\u0002J\u0018\u0010n\u001a\u00020Q2\u0006\u0010o\u001a\u00020p2\u0006\u0010Y\u001a\u00020IH\u0002J\u0014\u0010q\u001a\u00020Q2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00170\u001bR\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00160\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u001b0\u00160\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00160\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001b0\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b0\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00160\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u001601\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R#\u00104\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u001b0\u001601\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00103R\u001d\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u001601\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00103R\u001d\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001601\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u00103R\u001d\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u001601\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u00103R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010<\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b01\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u00103R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010@\u001a\b\u0012\u0004\u0012\u00020A01\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u00103\u00a8\u0006|"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "deviceMediaListBuilder", "Lorg/wordpress/android/ui/photopicker/DeviceMediaListBuilder;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "permissionsHandler", "Lorg/wordpress/android/ui/photopicker/PermissionsHandler;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "copyMediaToAppStorageUseCase", "Lorg/wordpress/android/ui/posts/editor/media/CopyMediaToAppStorageUseCase;", "getMediaModelUseCase", "Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/photopicker/DeviceMediaListBuilder;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/photopicker/PermissionsHandler;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/posts/editor/media/CopyMediaToAppStorageUseCase;Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;)V", "_navigateToPreview", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/util/UriWrapper;", "_onIconClicked", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$IconClickEvent;", "_onInsert", "", "_onPermissionsRequested", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PermissionsRequested;", "_photoPickerItems", "Lorg/wordpress/android/ui/photopicker/PhotoPickerItem;", "_selectedIds", "", "_showPopupMenu", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PopupMenuUiModel;", "_showProgressDialog", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "_softAskRequest", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskRequest;", "browserType", "Lorg/wordpress/android/ui/media/MediaBrowserType;", "lastTappedIcon", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;", "getLastTappedIcon", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;", "setLastTappedIcon", "(Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;)V", "onIconClicked", "Landroidx/lifecycle/LiveData;", "getOnIconClicked", "()Landroidx/lifecycle/LiveData;", "onInsert", "getOnInsert", "onNavigateToPreview", "getOnNavigateToPreview", "onPermissionsRequested", "getOnPermissionsRequested", "onShowPopupMenu", "getOnShowPopupMenu", "selectedIds", "getSelectedIds", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "uiState", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoPickerUiState;", "getUiState", "buildActionModeUiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel;", "buildBottomBar", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel;", "photoPickerItems", "showSoftAskViewModel", "", "buildPhotoPickerUiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel;", "data", "buildSoftAskView", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel;", "softAskRequest", "checkStoragePermission", "", "isAlwaysDenied", "clearSelection", "clickIcon", "icon", "clickItem", "id", "uri", "isVideo", "clickOnLastTappedIcon", "copySelectedUrisLocally", "uris", "mediaIdsSelectedFromWPMediaPicker", "mediaIds", "numSelected", "", "onCameraClicked", "viewWrapper", "Lorg/wordpress/android/util/ViewWrapper;", "performActionOrShowPopup", "performInsertAction", "refreshData", "forceReload", "selectedURIs", "showCameraPopupMenu", "start", "toggleItem", "canMultiselect", "trackOpenPreviewScreenEvent", "trackSelectedOtherSourceEvents", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "urisSelectedFromSystemPicker", "ActionModeUiModel", "BottomBarUiModel", "FabUiModel", "IconClickEvent", "PermissionsRequested", "PhotoListUiModel", "PhotoPickerUiState", "PopupMenuUiModel", "SoftAskRequest", "SoftAskViewUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
@java.lang.Deprecated()
public final class PhotoPickerViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.photopicker.DeviceMediaListBuilder deviceMediaListBuilder = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.ui.photopicker.PermissionsHandler permissionsHandler = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.posts.editor.media.CopyMediaToAppStorageUseCase copyMediaToAppStorageUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.util.UriWrapper>> _navigateToPreview = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.util.List<org.wordpress.android.util.UriWrapper>>> _onInsert = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel>> _showPopupMenu = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerItem>> _photoPickerItems = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<java.lang.Long>> _selectedIds = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.IconClickEvent>> _onIconClicked = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PermissionsRequested>> _onPermissionsRequested = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskRequest> _softAskRequest = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel> _showProgressDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.util.UriWrapper>> onNavigateToPreview = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.util.List<org.wordpress.android.util.UriWrapper>>> onInsert = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.IconClickEvent>> onIconClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel>> onShowPopupMenu = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PermissionsRequested>> onPermissionsRequested = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<java.lang.Long>> selectedIds = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoPickerUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon lastTappedIcon;
    private org.wordpress.android.ui.media.MediaBrowserType browserType;
    private org.wordpress.android.fluxc.model.SiteModel site;
    
    @javax.inject.Inject()
    public PhotoPickerViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.DeviceMediaListBuilder deviceMediaListBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PermissionsHandler permissionsHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.CopyMediaToAppStorageUseCase copyMediaToAppStorageUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.util.UriWrapper>> getOnNavigateToPreview() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.util.List<org.wordpress.android.util.UriWrapper>>> getOnInsert() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.IconClickEvent>> getOnIconClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel>> getOnShowPopupMenu() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PermissionsRequested>> getOnPermissionsRequested() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<java.lang.Long>> getSelectedIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoPickerUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon getLastTappedIcon() {
        return null;
    }
    
    public final void setLastTappedIcon(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon p0) {
    }
    
    private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel buildPhotoPickerUiModel(java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerItem> data, java.util.List<java.lang.Long> selectedIds) {
        return null;
    }
    
    private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel buildActionModeUiModel(java.util.List<java.lang.Long> selectedIds) {
        return null;
    }
    
    private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel buildBottomBar(java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerItem> photoPickerItems, java.util.List<java.lang.Long> selectedIds, boolean showSoftAskViewModel) {
        return null;
    }
    
    public final void refreshData(boolean forceReload) {
    }
    
    public final void clearSelection() {
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Long> selectedIds, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.media.MediaBrowserType browserType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon lastTappedIcon, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final int numSelected() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.util.UriWrapper> selectedURIs() {
        return null;
    }
    
    private final void toggleItem(long id, boolean canMultiselect) {
    }
    
    private final void clickItem(long id, org.wordpress.android.util.UriWrapper uri, boolean isVideo) {
    }
    
    private final void trackOpenPreviewScreenEvent(long id, org.wordpress.android.util.UriWrapper uri, boolean isVideo) {
    }
    
    public final void performInsertAction() {
    }
    
    public final void clickOnLastTappedIcon() {
    }
    
    public final void clickIcon(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon icon) {
    }
    
    private final void trackSelectedOtherSourceEvents(org.wordpress.android.analytics.AnalyticsTracker.Stat stat, boolean isVideo) {
    }
    
    public final void onCameraClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.ViewWrapper viewWrapper) {
    }
    
    public final void showCameraPopupMenu(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.ViewWrapper viewWrapper) {
    }
    
    public final void performActionOrShowPopup(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.ViewWrapper viewWrapper) {
    }
    
    public final void checkStoragePermission(boolean isAlwaysDenied) {
    }
    
    private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel buildSoftAskView(org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskRequest softAskRequest) {
        return null;
    }
    
    public final void urisSelectedFromSystemPicker(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.util.UriWrapper> uris) {
    }
    
    public final void mediaIdsSelectedFromWPMediaPicker(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> mediaIds) {
    }
    
    public final void copySelectedUrisLocally(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.util.UriWrapper> uris) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u000bH\u00c6\u0003J\t\u0010 \u001a\u00020\rH\u00c6\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020(H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoPickerUiState;", "", "photoListUiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel;", "bottomBarUiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel;", "softAskViewUiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel;", "fabUiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$FabUiModel;", "actionModeUiModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel;", "progressDialogUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "(Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel;Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel;Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel;Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$FabUiModel;Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel;Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;)V", "getActionModeUiModel", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel;", "getBottomBarUiModel", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel;", "getFabUiModel", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$FabUiModel;", "getPhotoListUiModel", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel;", "getProgressDialogUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "getSoftAskViewUiModel", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PhotoPickerUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel photoListUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel bottomBarUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel softAskViewUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.FabUiModel fabUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel actionModeUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel progressDialogUiModel = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoPickerUiState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel photoListUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel bottomBarUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel softAskViewUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.FabUiModel fabUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel actionModeUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel progressDialogUiModel) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PhotoPickerUiState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel photoListUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel bottomBarUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel softAskViewUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.FabUiModel fabUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel actionModeUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel progressDialogUiModel) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel getPhotoListUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel getBottomBarUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel getSoftAskViewUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.FabUiModel component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.FabUiModel getFabUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel getActionModeUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel getProgressDialogUiModel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel;", "", "()V", "Data", "Empty", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel$Data;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel$Empty;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PhotoListUiModel {
        
        private PhotoListUiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel$Data;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel;", "items", "", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Data extends org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerUiItem> items = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel.Data copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.photopicker.PhotoPickerUiItem> items) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public Data(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.photopicker.PhotoPickerUiItem> items) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerUiItem> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerUiItem> getItems() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel$Empty;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PhotoListUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Empty extends org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PhotoListUiModel.Empty INSTANCE = null;
            
            private Empty() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\'BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\u0015\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bH\u00c6\u0003J[\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bH\u00c6\u0001J\u0013\u0010!\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel;", "", "type", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel$BottomBar;", "insertEditTextBarVisible", "", "hideMediaBottomBarInPortrait", "showCameraButton", "showWPMediaIcon", "canShowInsertEditBottomBar", "onIconPickerClicked", "Lkotlin/Function1;", "Lorg/wordpress/android/util/ViewWrapper;", "", "(Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel$BottomBar;ZZZZZLkotlin/jvm/functions/Function1;)V", "getCanShowInsertEditBottomBar", "()Z", "getHideMediaBottomBarInPortrait", "getInsertEditTextBarVisible", "getOnIconPickerClicked", "()Lkotlin/jvm/functions/Function1;", "getShowCameraButton", "getShowWPMediaIcon", "getType", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel$BottomBar;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "BottomBar", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BottomBarUiModel {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel.BottomBar type = null;
        private final boolean insertEditTextBarVisible = false;
        private final boolean hideMediaBottomBarInPortrait = false;
        private final boolean showCameraButton = false;
        private final boolean showWPMediaIcon = false;
        private final boolean canShowInsertEditBottomBar = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.util.ViewWrapper, kotlin.Unit> onIconPickerClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel.BottomBar type, boolean insertEditTextBarVisible, boolean hideMediaBottomBarInPortrait, boolean showCameraButton, boolean showWPMediaIcon, boolean canShowInsertEditBottomBar, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.util.ViewWrapper, kotlin.Unit> onIconPickerClicked) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public BottomBarUiModel(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel.BottomBar type, boolean insertEditTextBarVisible, boolean hideMediaBottomBarInPortrait, boolean showCameraButton, boolean showWPMediaIcon, boolean canShowInsertEditBottomBar, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.util.ViewWrapper, kotlin.Unit> onIconPickerClicked) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel.BottomBar component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.BottomBarUiModel.BottomBar getType() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getInsertEditTextBarVisible() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getHideMediaBottomBarInPortrait() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getShowCameraButton() {
            return false;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean getShowWPMediaIcon() {
            return false;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean getCanShowInsertEditBottomBar() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.util.ViewWrapper, kotlin.Unit> component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.util.ViewWrapper, kotlin.Unit> getOnIconPickerClicked() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$BottomBarUiModel$BottomBar;", "", "(Ljava/lang/String;I)V", "INSERT_EDIT", "MEDIA_SOURCE", "NONE", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum BottomBar {
            /*public static final*/ INSERT_EDIT /* = new INSERT_EDIT() */,
            /*public static final*/ MEDIA_SOURCE /* = new MEDIA_SOURCE() */,
            /*public static final*/ NONE /* = new NONE() */;
            
            BottomBar() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel;", "", "()V", "Hidden", "Visible", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel$Visible;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel$Hidden;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SoftAskViewUiModel {
        
        private SoftAskViewUiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel$Visible;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel;", "label", "", "allowId", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "isAlwaysDenied", "", "(Ljava/lang/String;Lorg/wordpress/android/ui/utils/UiString$UiStringRes;Z)V", "getAllowId", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "()Z", "getLabel", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Visible extends org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String label = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes allowId = null;
            private final boolean isAlwaysDenied = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel.Visible copy(@org.jetbrains.annotations.NotNull()
            java.lang.String label, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString.UiStringRes allowId, boolean isAlwaysDenied) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public Visible(@org.jetbrains.annotations.NotNull()
            java.lang.String label, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString.UiStringRes allowId, boolean isAlwaysDenied) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getLabel() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString.UiStringRes component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString.UiStringRes getAllowId() {
                return null;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean isAlwaysDenied() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel$Hidden;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskViewUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Hidden extends org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskViewUiModel.Hidden INSTANCE = null;
            
            private Hidden() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$FabUiModel;", "", "show", "", "action", "Lkotlin/Function0;", "", "(ZLkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "getShow", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FabUiModel {
        private final boolean show = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.FabUiModel copy(boolean show, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> action) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public FabUiModel(boolean show, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> action) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getShow() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel;", "", "()V", "Hidden", "Visible", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel$Visible;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel$Hidden;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ActionModeUiModel {
        
        private ActionModeUiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel$Visible;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel;", "actionModeTitle", "Lorg/wordpress/android/ui/utils/UiString;", "showConfirmAction", "", "(Lorg/wordpress/android/ui/utils/UiString;Z)V", "getActionModeTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "getShowConfirmAction", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Visible extends org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel {
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString actionModeTitle = null;
            private final boolean showConfirmAction = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel.Visible copy(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString actionModeTitle, boolean showConfirmAction) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public Visible() {
                super();
            }
            
            public Visible(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString actionModeTitle, boolean showConfirmAction) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getActionModeTitle() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getShowConfirmAction() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel$Hidden;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$ActionModeUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Hidden extends org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.ActionModeUiModel.Hidden INSTANCE = null;
            
            private Hidden() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$IconClickEvent;", "", "icon", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;", "allowMultipleSelection", "", "(Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;Z)V", "getAllowMultipleSelection", "()Z", "getIcon", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerIcon;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class IconClickEvent {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon icon = null;
        private final boolean allowMultipleSelection = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.IconClickEvent copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon icon, boolean allowMultipleSelection) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public IconClickEvent(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon icon, boolean allowMultipleSelection) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon getIcon() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getAllowMultipleSelection() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PermissionsRequested;", "", "(Ljava/lang/String;I)V", "CAMERA", "STORAGE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PermissionsRequested {
        /*public static final*/ CAMERA /* = new CAMERA() */,
        /*public static final*/ STORAGE /* = new STORAGE() */;
        
        PermissionsRequested() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0016B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PopupMenuUiModel;", "", "view", "Lorg/wordpress/android/util/ViewWrapper;", "items", "", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PopupMenuUiModel$PopupMenuItem;", "(Lorg/wordpress/android/util/ViewWrapper;Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "getView", "()Lorg/wordpress/android/util/ViewWrapper;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "PopupMenuItem", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PopupMenuUiModel {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.util.ViewWrapper view = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel.PopupMenuItem> items = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.ViewWrapper view, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel.PopupMenuItem> items) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PopupMenuUiModel(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.ViewWrapper view, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel.PopupMenuItem> items) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.util.ViewWrapper component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.util.ViewWrapper getView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel.PopupMenuItem> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel.PopupMenuItem> getItems() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$PopupMenuUiModel$PopupMenuItem;", "", "title", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "action", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/ui/utils/UiString$UiStringRes;Lkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PopupMenuItem {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.PopupMenuUiModel.PopupMenuItem copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString.UiStringRes title, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> action) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public PopupMenuItem(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString.UiStringRes title, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> action) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString.UiStringRes component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel$SoftAskRequest;", "", "show", "", "isAlwaysDenied", "(ZZ)V", "()Z", "getShow", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SoftAskRequest {
        private final boolean show = false;
        private final boolean isAlwaysDenied = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerViewModel.SoftAskRequest copy(boolean show, boolean isAlwaysDenied) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SoftAskRequest(boolean show, boolean isAlwaysDenied) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getShow() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isAlwaysDenied() {
            return false;
        }
    }
}
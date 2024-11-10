package org.wordpress.android.ui.mediapicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009c\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0016\u0084\u0001\u0085\u0001\u0086\u0001\u0087\u0001\u0088\u0001\u0089\u0001\u008a\u0001\u008b\u0001\u008c\u0001\u008d\u0001\u008e\u0001B[\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u0015J(\u0010G\u001a\u00020H2\u000e\u0010I\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#2\u000e\u0010J\u001a\n\u0012\u0004\u0012\u00020K\u0018\u00010#H\u0002J!\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010(2\b\u0010O\u001a\u0004\u0018\u00010!H\u0002\u00a2\u0006\u0002\u0010PJ)\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020!2\b\u0010T\u001a\u0004\u0018\u00010U2\b\u0010O\u001a\u0004\u0018\u00010!H\u0002\u00a2\u0006\u0002\u0010VJ\u0012\u0010W\u001a\u00020X2\b\u0010N\u001a\u0004\u0018\u00010(H\u0002J;\u0010Y\u001a\u00020Z2\b\u0010[\u001a\u0004\u0018\u00010\u00182\u000e\u0010I\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#2\b\u0010N\u001a\u0004\u0018\u00010(2\b\u0010\\\u001a\u0004\u0018\u00010!H\u0002\u00a2\u0006\u0002\u0010]J\u000e\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020!J\u0006\u0010a\u001a\u00020_J\u0010\u0010b\u001a\u00020_2\u0006\u0010c\u001a\u00020*H\u0002J\u0018\u0010d\u001a\u00020_2\u0006\u0010e\u001a\u00020$2\u0006\u0010f\u001a\u00020!H\u0002J\b\u0010g\u001a\u00020_H\u0002J\u0006\u0010h\u001a\u00020_J\u0016\u0010i\u001a\u00020_2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020$0#H\u0002J\u0006\u0010k\u001a\u00020lJ\b\u0010m\u001a\u00020_H\u0014J\u000e\u0010n\u001a\u00020_2\u0006\u0010o\u001a\u00020pJ\u0006\u0010q\u001a\u00020_J\u000e\u0010r\u001a\u00020_2\u0006\u0010s\u001a\u00020UJ\u0006\u0010t\u001a\u00020_J\u0006\u0010u\u001a\u00020_J\u0006\u0010v\u001a\u00020_J\u0006\u0010w\u001a\u00020_J\u0018\u0010x\u001a\u00020y2\u0006\u0010c\u001a\u00020*2\u0006\u0010z\u001a\u00020!H\u0002J\u000e\u0010{\u001a\u00020_2\u0006\u0010|\u001a\u00020!J\b\u0010}\u001a\u00020_H\u0002J\f\u0010~\u001a\b\u0012\u0004\u0012\u00020$0#J2\u0010\u007f\u001a\u00020_2\u000e\u0010I\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#2\u0006\u00106\u001a\u0002072\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010B\u001a\u0004\u0018\u00010CJ\u0019\u0010\u0080\u0001\u001a\u00020_2\u0006\u0010e\u001a\u00020$2\u0006\u0010z\u001a\u00020!H\u0002J\u0017\u0010\u0081\u0001\u001a\u00020_2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010#R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001a0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001a0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020$\u0018\u00010#0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020100X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a09\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u001d\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001a09\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010;R\u001d\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001a09\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010;R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010D\u001a\b\u0012\u0004\u0012\u00020E09\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010;\u00a8\u0006\u008f\u0001"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "mediaLoaderFactory", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoaderFactory;", "mediaInsertHandlerFactory", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandlerFactory;", "mediaPickerTracker", "Lorg/wordpress/android/ui/mediapicker/MediaPickerTracker;", "permissionsHandler", "Lorg/wordpress/android/ui/photopicker/PermissionsHandler;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/mediapicker/loader/MediaLoaderFactory;Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandlerFactory;Lorg/wordpress/android/ui/mediapicker/MediaPickerTracker;Lorg/wordpress/android/ui/photopicker/PermissionsHandler;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/util/MediaUtilsWrapper;Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "_domainModel", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel;", "_onNavigate", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent;", "_onPermissionsRequested", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PermissionsRequested;", "_onSnackbarMessage", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_searchExpanded", "", "_selectedIds", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "_showProgressDialog", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "_softAskRequest", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskRequest;", "lastTappedIcon", "Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerIcon;", "getLastTappedIcon", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerIcon;", "setLastTappedIcon", "(Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerIcon;)V", "loadActions", "Lkotlinx/coroutines/channels/Channel;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "mediaInsertHandler", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandler;", "mediaLoader", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader;", "mediaPickerSetup", "Lorg/wordpress/android/ui/mediapicker/MediaPickerSetup;", "onNavigate", "Landroidx/lifecycle/LiveData;", "getOnNavigate", "()Landroidx/lifecycle/LiveData;", "onPermissionsRequested", "getOnPermissionsRequested", "onSnackbarMessage", "getOnSnackbarMessage", "searchJob", "Lkotlinx/coroutines/Job;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "uiState", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$MediaPickerUiState;", "getUiState", "buildActionModeUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel;", "selectedIds", "items", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "buildBrowseMenuUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel;", "softAskRequest", "searchExpanded", "(Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskRequest;Ljava/lang/Boolean;)Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel;", "buildSearchUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;", "isVisible", "filter", "", "(ZLjava/lang/String;Ljava/lang/Boolean;)Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;", "buildSoftAskView", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel;", "buildUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "domainModel", "isSearching", "(Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel;Ljava/util/List;Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskRequest;Ljava/lang/Boolean;)Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "checkStoragePermission", "", "isAlwaysDenied", "clearSelection", "clickIcon", "icon", "clickItem", "identifier", "isVideo", "clickOnCamera", "clickOnLastTappedIcon", "insertIdentifiers", "ids", "numSelected", "", "onCleared", "onMenuItemClicked", "action", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel$BrowseAction;", "onPullToRefresh", "onSearch", "query", "onSearchCollapsed", "onSearchExpanded", "performEditAction", "performInsertAction", "populateIconClickEvent", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$IconClickEvent;", "canMultiselect", "refreshData", "forceReload", "retry", "selectedIdentifiers", "start", "toggleItem", "urisSelectedFromSystemPicker", "uris", "Lorg/wordpress/android/util/UriWrapper;", "ActionModeUiModel", "BrowseMenuUiModel", "EditActionUiModel", "FabUiModel", "MediaPickerUiState", "PermissionsRequested", "PhotoListUiModel", "ProgressDialogUiModel", "SearchUiModel", "SoftAskRequest", "SoftAskViewUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MediaPickerViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.mediapicker.loader.MediaLoaderFactory mediaLoaderFactory = null;
    private final org.wordpress.android.ui.mediapicker.insert.MediaInsertHandlerFactory mediaInsertHandlerFactory = null;
    private final org.wordpress.android.ui.mediapicker.MediaPickerTracker mediaPickerTracker = null;
    private final org.wordpress.android.ui.photopicker.PermissionsHandler permissionsHandler = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private org.wordpress.android.ui.mediapicker.loader.MediaLoader mediaLoader;
    private org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler mediaInsertHandler;
    private final kotlinx.coroutines.channels.Channel<org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction> loadActions = null;
    private kotlinx.coroutines.Job searchJob;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel> _domainModel = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.mediapicker.MediaItem.Identifier>> _selectedIds = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PermissionsRequested>> _onPermissionsRequested = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskRequest> _softAskRequest = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _searchExpanded = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel> _showProgressDialog = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _onSnackbarMessage = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mediapicker.MediaNavigationEvent>> _onNavigate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> onSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mediapicker.MediaNavigationEvent>> onNavigate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PermissionsRequested>> onPermissionsRequested = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.MediaPickerUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerIcon lastTappedIcon;
    private org.wordpress.android.ui.mediapicker.MediaPickerSetup mediaPickerSetup;
    private org.wordpress.android.fluxc.model.SiteModel site;
    
    @javax.inject.Inject()
    public MediaPickerViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.loader.MediaLoaderFactory mediaLoaderFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.insert.MediaInsertHandlerFactory mediaInsertHandlerFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaPickerTracker mediaPickerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PermissionsHandler permissionsHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getOnSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mediapicker.MediaNavigationEvent>> getOnNavigate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PermissionsRequested>> getOnPermissionsRequested() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.MediaPickerUiState> getUiState() {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel buildSearchUiModel(boolean isVisible, java.lang.String filter, java.lang.Boolean searchExpanded) {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel buildBrowseMenuUiModel(org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskRequest softAskRequest, java.lang.Boolean searchExpanded) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerIcon getLastTappedIcon() {
        return null;
    }
    
    public final void setLastTappedIcon(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerIcon p0) {
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel buildUiModel(org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel domainModel, java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> selectedIds, org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskRequest softAskRequest, java.lang.Boolean isSearching) {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel buildActionModeUiModel(java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> selectedIds, java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> items) {
        return null;
    }
    
    public final void refreshData(boolean forceReload) {
    }
    
    private final void retry() {
    }
    
    public final void clearSelection() {
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> selectedIds, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaPickerSetup mediaPickerSetup, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerIcon lastTappedIcon, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final int numSelected() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem.Identifier> selectedIdentifiers() {
        return null;
    }
    
    private final void toggleItem(org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean canMultiselect) {
    }
    
    private final void clickItem(org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean isVideo) {
    }
    
    public final void performInsertAction() {
    }
    
    private final void insertIdentifiers(java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> ids) {
    }
    
    public final void performEditAction() {
    }
    
    public final void clickOnLastTappedIcon() {
    }
    
    private final void clickIcon(org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerIcon icon) {
    }
    
    private final void clickOnCamera() {
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaNavigationEvent.IconClickEvent populateIconClickEvent(org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerIcon icon, boolean canMultiselect) {
        return null;
    }
    
    public final void checkStoragePermission(boolean isAlwaysDenied) {
    }
    
    public final void onMenuItemClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel.BrowseAction action) {
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel buildSoftAskView(org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskRequest softAskRequest) {
        return null;
    }
    
    public final void onSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void onSearchExpanded() {
    }
    
    public final void onSearchCollapsed() {
    }
    
    public final void onPullToRefresh() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void urisSelectedFromSystemPicker(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.util.UriWrapper> uris) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0007H\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\'\u001a\u00020\rH\u00c6\u0003J\t\u0010(\u001a\u00020\u000fH\u00c6\u0003J\t\u0010)\u001a\u00020\u0011H\u00c6\u0003JY\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001J\u0013\u0010+\u001a\u00020\r2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u000200H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$MediaPickerUiState;", "", "photoListUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "softAskViewUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel;", "fabUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$FabUiModel;", "actionModeUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel;", "searchUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;", "isRefreshing", "", "browseMenuUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel;", "progressDialogUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "(Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel;Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$FabUiModel;Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel;Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;ZLorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel;Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;)V", "getActionModeUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel;", "getBrowseMenuUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel;", "getFabUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$FabUiModel;", "()Z", "getPhotoListUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "getProgressDialogUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "getSearchUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;", "getSoftAskViewUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MediaPickerUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel photoListUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel softAskViewUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.FabUiModel fabUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel actionModeUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel searchUiModel = null;
        private final boolean isRefreshing = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel browseMenuUiModel = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel progressDialogUiModel = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.MediaPickerUiState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel photoListUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel softAskViewUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.FabUiModel fabUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel actionModeUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel searchUiModel, boolean isRefreshing, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel browseMenuUiModel, @org.jetbrains.annotations.NotNull()
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
        
        public MediaPickerUiState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel photoListUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel softAskViewUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.FabUiModel fabUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel actionModeUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel searchUiModel, boolean isRefreshing, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel browseMenuUiModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel progressDialogUiModel) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel getPhotoListUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel getSoftAskViewUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.FabUiModel component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.FabUiModel getFabUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel getActionModeUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel getSearchUiModel() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isRefreshing() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel getBrowseMenuUiModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel getProgressDialogUiModel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "", "()V", "Data", "Empty", "Hidden", "Loading", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Data;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Empty;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Hidden;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Loading;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PhotoListUiModel {
        
        private PhotoListUiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Data;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "items", "", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Data extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mediapicker.MediaPickerUiItem> items = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel.Data copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaPickerUiItem> items) {
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
            java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaPickerUiItem> items) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mediapicker.MediaPickerUiItem> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mediapicker.MediaPickerUiItem> getItems() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u00c6\u0003Jd\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u00c6\u0001\u00a2\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010%H\u00d6\u0003J\t\u0010&\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\'\u001a\u00020(H\u00d6\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0016R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Empty;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "htmlSubtitle", "image", "", "bottomImage", "bottomImageDescription", "isSearching", "", "retryAction", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;ZLkotlin/jvm/functions/Function0;)V", "getBottomImage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBottomImageDescription", "()Lorg/wordpress/android/ui/utils/UiString;", "getHtmlSubtitle", "getImage", "()Z", "getRetryAction", "()Lkotlin/jvm/functions/Function0;", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;ZLkotlin/jvm/functions/Function0;)Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Empty;", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Empty extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString htmlSubtitle = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer image = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer bottomImage = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString bottomImageDescription = null;
            private final boolean isSearching = false;
            @org.jetbrains.annotations.Nullable()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> retryAction = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel.Empty copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString htmlSubtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, @org.jetbrains.annotations.Nullable()
            java.lang.Integer bottomImage, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString bottomImageDescription, boolean isSearching, @org.jetbrains.annotations.Nullable()
            kotlin.jvm.functions.Function0<kotlin.Unit> retryAction) {
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
            
            public Empty(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString htmlSubtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, @org.jetbrains.annotations.Nullable()
            java.lang.Integer bottomImage, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString bottomImageDescription, boolean isSearching, @org.jetbrains.annotations.Nullable()
            kotlin.jvm.functions.Function0<kotlin.Unit> retryAction) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getHtmlSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getImage() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component4() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getBottomImage() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component5() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getBottomImageDescription() {
                return null;
            }
            
            public final boolean component6() {
                return false;
            }
            
            public final boolean isSearching() {
                return false;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component7() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> getRetryAction() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Hidden;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Hidden extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel.Hidden INSTANCE = null;
            
            private Hidden() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel$Loading;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PhotoListUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Loading extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.PhotoListUiModel.Loading INSTANCE = null;
            
            private Loading() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel;", "", "()V", "Hidden", "Visible", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel$Visible;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel$Hidden;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SoftAskViewUiModel {
        
        private SoftAskViewUiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel$Visible;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel;", "label", "", "allowId", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "isAlwaysDenied", "", "(Ljava/lang/String;Lorg/wordpress/android/ui/utils/UiString$UiStringRes;Z)V", "getAllowId", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "()Z", "getLabel", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Visible extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String label = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes allowId = null;
            private final boolean isAlwaysDenied = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel.Visible copy(@org.jetbrains.annotations.NotNull()
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
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel$Hidden;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskViewUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Hidden extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskViewUiModel.Hidden INSTANCE = null;
            
            private Hidden() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$FabUiModel;", "", "show", "", "action", "Lkotlin/Function0;", "", "(ZLkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "getShow", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FabUiModel {
        private final boolean show = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.FabUiModel copy(boolean show, @org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel;", "", "()V", "Hidden", "Visible", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel$Visible;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel$Hidden;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ActionModeUiModel {
        
        private ActionModeUiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel$Visible;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel;", "actionModeTitle", "Lorg/wordpress/android/ui/utils/UiString;", "editActionUiModel", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$EditActionUiModel;", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$EditActionUiModel;)V", "getActionModeTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "getEditActionUiModel", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$EditActionUiModel;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Visible extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel {
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString actionModeTitle = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.EditActionUiModel editActionUiModel = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel.Visible copy(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString actionModeTitle, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mediapicker.MediaPickerViewModel.EditActionUiModel editActionUiModel) {
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
            org.wordpress.android.ui.utils.UiString actionModeTitle, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mediapicker.MediaPickerViewModel.EditActionUiModel editActionUiModel) {
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
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.EditActionUiModel component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.EditActionUiModel getEditActionUiModel() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel$Hidden;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ActionModeUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Hidden extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ActionModeUiModel.Hidden INSTANCE = null;
            
            private Hidden() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;", "", "()V", "Collapsed", "Expanded", "Hidden", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel$Collapsed;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel$Expanded;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel$Hidden;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SearchUiModel {
        
        private SearchUiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel$Collapsed;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Collapsed extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel.Collapsed INSTANCE = null;
            
            private Collapsed() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel$Expanded;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;", "filter", "", "closeable", "", "(Ljava/lang/String;Z)V", "getCloseable", "()Z", "getFilter", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Expanded extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String filter = null;
            private final boolean closeable = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel.Expanded copy(@org.jetbrains.annotations.NotNull()
            java.lang.String filter, boolean closeable) {
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
            
            public Expanded(@org.jetbrains.annotations.NotNull()
            java.lang.String filter, boolean closeable) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getFilter() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getCloseable() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel$Hidden;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SearchUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Hidden extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SearchUiModel.Hidden INSTANCE = null;
            
            private Hidden() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0011B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel;", "", "shownActions", "", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel$BrowseAction;", "(Ljava/util/Set;)V", "getShownActions", "()Ljava/util/Set;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BrowseAction", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BrowseMenuUiModel {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel.BrowseAction> shownActions = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel copy(@org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel.BrowseAction> shownActions) {
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
        
        public BrowseMenuUiModel(@org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel.BrowseAction> shownActions) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel.BrowseAction> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<org.wordpress.android.ui.mediapicker.MediaPickerViewModel.BrowseMenuUiModel.BrowseAction> getShownActions() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$BrowseMenuUiModel$BrowseAction;", "", "(Ljava/lang/String;I)V", "SYSTEM_PICKER", "DEVICE", "WP_MEDIA_LIBRARY", "STOCK_LIBRARY", "GIF_LIBRARY", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum BrowseAction {
            /*public static final*/ SYSTEM_PICKER /* = new SYSTEM_PICKER() */,
            /*public static final*/ DEVICE /* = new DEVICE() */,
            /*public static final*/ WP_MEDIA_LIBRARY /* = new WP_MEDIA_LIBRARY() */,
            /*public static final*/ STOCK_LIBRARY /* = new STOCK_LIBRARY() */,
            /*public static final*/ GIF_LIBRARY /* = new GIF_LIBRARY() */;
            
            BrowseAction() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$PermissionsRequested;", "", "(Ljava/lang/String;I)V", "CAMERA", "STORAGE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PermissionsRequested {
        /*public static final*/ CAMERA /* = new CAMERA() */,
        /*public static final*/ STORAGE /* = new STORAGE() */;
        
        PermissionsRequested() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$SoftAskRequest;", "", "show", "", "isAlwaysDenied", "(ZZ)V", "()Z", "getShow", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SoftAskRequest {
        private final boolean show = false;
        private final boolean isAlwaysDenied = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.SoftAskRequest copy(boolean show, boolean isAlwaysDenied) {
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$EditActionUiModel;", "", "isVisible", "", "isCounterBadgeVisible", "counterBadgeValue", "", "(ZZI)V", "getCounterBadgeValue", "()I", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EditActionUiModel {
        private final boolean isVisible = false;
        private final boolean isCounterBadgeVisible = false;
        private final int counterBadgeValue = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.EditActionUiModel copy(boolean isVisible, boolean isCounterBadgeVisible, int counterBadgeValue) {
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
        
        public EditActionUiModel() {
            super();
        }
        
        public EditActionUiModel(boolean isVisible, boolean isCounterBadgeVisible, int counterBadgeValue) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isVisible() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isCounterBadgeVisible() {
            return false;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getCounterBadgeValue() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "", "()V", "Hidden", "Visible", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel$Hidden;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel$Visible;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ProgressDialogUiModel {
        
        private ProgressDialogUiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel$Hidden;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Hidden extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel.Hidden INSTANCE = null;
            
            private Hidden() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel$Visible;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerViewModel$ProgressDialogUiModel;", "title", "", "cancelAction", "Lkotlin/Function0;", "", "(ILkotlin/jvm/functions/Function0;)V", "getCancelAction", "()Lkotlin/jvm/functions/Function0;", "getTitle", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Visible extends org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel {
            private final int title = 0;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> cancelAction = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaPickerViewModel.ProgressDialogUiModel.Visible copy(int title, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> cancelAction) {
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
            
            public Visible(int title, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> cancelAction) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getTitle() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> getCancelAction() {
                return null;
            }
        }
    }
}
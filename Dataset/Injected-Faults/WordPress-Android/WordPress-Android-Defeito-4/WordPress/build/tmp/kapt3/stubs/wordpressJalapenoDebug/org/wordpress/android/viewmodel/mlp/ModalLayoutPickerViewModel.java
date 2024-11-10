package org.wordpress.android.viewmodel.mlp;

import java.lang.System;

/**
 * Implements the Modal Layout Picker view model
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001DB[\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0001\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0015J\u0006\u00106\u001a\u00020\u0019J\b\u00107\u001a\u000208H\u0002J\u0006\u00109\u001a\u000208J\u0006\u0010:\u001a\u000208J\u0010\u0010;\u001a\u0002082\u0006\u0010<\u001a\u00020\u0019H\u0016J\u0010\u0010=\u001a\u0002082\u0006\u0010>\u001a\u00020?H\u0007J\b\u0010@\u001a\u000208H\u0014J\u0006\u0010A\u001a\u000208J\b\u0010B\u001a\u000208H\u0016J\b\u0010C\u001a\u000208H\u0002R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180 \u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010!R\u0014\u0010\u0014\u001a\u00020\u0013X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001c0 \u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0016\u0010\'\u001a\u0004\u0018\u00010(8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010*R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010+\u001a\u00020\u0019X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u001d\u0010.\u001a\u0004\u0018\u00010/8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b2\u00103\u001a\u0004\b0\u00101R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00104\u001a\u00020\u0019X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010-\u00a8\u0006E"}, d2 = {"Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerViewModel;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "supportedBlocksProvider", "Lorg/wordpress/android/ui/mlp/SupportedBlocksProvider;", "thumbDimensionProvider", "Lorg/wordpress/android/ui/mlp/ModalLayoutPickerDimensionProvider;", "displayUtilsWrapper", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "analyticsTracker", "Lorg/wordpress/android/ui/mlp/ModalLayoutPickerTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/ui/mlp/SupportedBlocksProvider;Lorg/wordpress/android/ui/mlp/ModalLayoutPickerDimensionProvider;Lorg/wordpress/android/util/DisplayUtilsWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/mlp/ModalLayoutPickerTracker;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_isModalLayoutPickerShowing", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "_onCreateNewPageRequested", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest$Create;", "getBgDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "isModalLayoutPickerShowing", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "getMainDispatcher", "getNetworkUtils", "()Lorg/wordpress/android/util/NetworkUtilsWrapper;", "onCreateNewPageRequested", "getOnCreateNewPageRequested", "selectedLayout", "Lorg/wordpress/android/ui/layoutpicker/LayoutModel;", "getSelectedLayout", "()Lorg/wordpress/android/ui/layoutpicker/LayoutModel;", "shouldUseMobileThumbnail", "getShouldUseMobileThumbnail", "()Z", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "site$delegate", "Lkotlin/Lazy;", "useCachedData", "getUseCachedData", "canShowModalLayoutPicker", "createPage", "", "createPageFlowTriggered", "dismiss", "fetchLayouts", "preferCache", "onBlockLayoutsFetched", "event", "Lorg/wordpress/android/fluxc/store/SiteStore$OnBlockLayoutsFetched;", "onCleared", "onCreatePageClicked", "onPreviewChooseTapped", "setErrorState", "PageRequest", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ModalLayoutPickerViewModel extends org.wordpress.android.ui.layoutpicker.LayoutPickerViewModel {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.ui.mlp.SupportedBlocksProvider supportedBlocksProvider = null;
    private final org.wordpress.android.ui.mlp.ModalLayoutPickerDimensionProvider thumbDimensionProvider = null;
    private final org.wordpress.android.util.DisplayUtilsWrapper displayUtilsWrapper = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.ui.mlp.ModalLayoutPickerTracker analyticsTracker = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    
    /**
     * Tracks the Modal Layout Picker visibility state
     */
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> _isModalLayoutPickerShowing = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isModalLayoutPickerShowing = null;
    
    /**
     * Create new page event
     */
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel.PageRequest.Create> _onCreateNewPageRequested = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel.PageRequest.Create> onCreateNewPageRequested = null;
    private final kotlin.Lazy site$delegate = null;
    private final boolean useCachedData = true;
    private final boolean shouldUseMobileThumbnail = false;
    
    @javax.inject.Inject()
    public ModalLayoutPickerViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mlp.SupportedBlocksProvider supportedBlocksProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mlp.ModalLayoutPickerDimensionProvider thumbDimensionProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper displayUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mlp.ModalLayoutPickerTracker analyticsTracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.util.NetworkUtilsWrapper getNetworkUtils() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.CoroutineDispatcher getBgDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.CoroutineDispatcher getMainDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isModalLayoutPickerShowing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel.PageRequest.Create> getOnCreateNewPageRequested() {
        return null;
    }
    
    private final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    @java.lang.Override()
    public boolean getUseCachedData() {
        return false;
    }
    
    @java.lang.Override()
    public boolean getShouldUseMobileThumbnail() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public org.wordpress.android.ui.layoutpicker.LayoutModel getSelectedLayout() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @java.lang.Override()
    public void fetchLayouts(boolean preferCache) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onBlockLayoutsFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnBlockLayoutsFetched event) {
    }
    
    private final void setErrorState() {
    }
    
    /**
     * Checks if the Modal Layout Picker can be shown
     * at this point the only requirement is to have the block editor enabled
     * @return true if the Modal Layout Picker can be shown
     */
    public final boolean canShowModalLayoutPicker() {
        return false;
    }
    
    /**
     * Triggers the create page flow and shows the MLP
     */
    public final void createPageFlowTriggered() {
    }
    
    /**
     * Dismisses the MLP
     */
    public final void dismiss() {
    }
    
    /**
     * Create page tapped
     */
    public final void onCreatePageClicked() {
    }
    
    @java.lang.Override()
    public void onPreviewChooseTapped() {
    }
    
    /**
     * Triggers the creation of a new page
     */
    private final void createPage() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u0011\b\u0004\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\n\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest;", "", "template", "", "(Ljava/lang/String;)V", "getTemplate", "()Ljava/lang/String;", "Blank", "Create", "Preview", "Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest$Create;", "Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest$Preview;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PageRequest {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String template = null;
        
        private PageRequest(java.lang.String template) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getTemplate() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest$Create;", "Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest;", "template", "", "title", "(Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static class Create extends org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel.PageRequest {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String title = null;
            
            public Create(@org.jetbrains.annotations.Nullable()
            java.lang.String template, @org.jetbrains.annotations.NotNull()
            java.lang.String title) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getTitle() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest$Blank;", "Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest$Create;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Blank extends org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel.PageRequest.Create {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel.PageRequest.Blank INSTANCE = null;
            
            private Blank() {
                super(null, null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest$Preview;", "Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel$PageRequest;", "template", "", "content", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "demoUrl", "(Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getDemoUrl", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Preview extends org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel.PageRequest {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String content = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String demoUrl = null;
            
            public Preview(@org.jetbrains.annotations.Nullable()
            java.lang.String template, @org.jetbrains.annotations.NotNull()
            java.lang.String content, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
            java.lang.String demoUrl) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getContent() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getDemoUrl() {
                return null;
            }
        }
    }
}
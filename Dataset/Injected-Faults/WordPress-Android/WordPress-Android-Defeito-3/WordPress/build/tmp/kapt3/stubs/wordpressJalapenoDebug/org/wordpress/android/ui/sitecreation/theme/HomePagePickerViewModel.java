package org.wordpress.android.ui.sitecreation.theme;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001:\u00017BC\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020!H\u0016J\u0006\u0010,\u001a\u00020\u0012J\u0006\u0010-\u001a\u00020\u0012J\b\u0010.\u001a\u00020\u0012H\u0014J\u0018\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020)2\u0006\u00101\u001a\u00020!H\u0016J\b\u00102\u001a\u00020\u0012H\u0016J\u0006\u00103\u001a\u00020\u0012J\u001c\u00104\u001a\u00020\u00122\n\b\u0002\u00105\u001a\u0004\u0018\u00010)2\b\b\u0002\u00106\u001a\u00020!R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020!X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0014\u0010&\u001a\u00020!X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010#R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerViewModel;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "fetchHomePageLayoutsUseCase", "Lorg/wordpress/android/ui/sitecreation/usecases/FetchHomePageLayoutsUseCase;", "analyticsTracker", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "recommendationProvider", "Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignRecommendationProvider;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/sitecreation/usecases/FetchHomePageLayoutsUseCase;Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignRecommendationProvider;)V", "_onBackButtonPressed", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_onDesignActionPressed", "Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel$DesignSelectionAction;", "getBgDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "getMainDispatcher", "getNetworkUtils", "()Lorg/wordpress/android/util/NetworkUtilsWrapper;", "onBackButtonPressed", "Landroidx/lifecycle/LiveData;", "getOnBackButtonPressed", "()Landroidx/lifecycle/LiveData;", "onDesignActionPressed", "getOnDesignActionPressed", "shouldUseMobileThumbnail", "", "getShouldUseMobileThumbnail", "()Z", "thumbnailTapOpensPreview", "getThumbnailTapOpensPreview", "useCachedData", "getUseCachedData", "vertical", "", "fetchLayouts", "preferCache", "onBackPressed", "onChooseTapped", "onCleared", "onLayoutTapped", "layoutSlug", "isRecommended", "onPreviewChooseTapped", "onSkippedTapped", "start", "intent", "isTablet", "DesignSelectionAction", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HomePagePickerViewModel extends org.wordpress.android.ui.layoutpicker.LayoutPickerViewModel {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.sitecreation.usecases.FetchHomePageLayoutsUseCase fetchHomePageLayoutsUseCase = null;
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker analyticsTracker = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.ui.sitecreation.theme.SiteDesignRecommendationProvider recommendationProvider = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel.DesignSelectionAction> _onDesignActionPressed = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel.DesignSelectionAction> onDesignActionPressed = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onBackButtonPressed = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onBackButtonPressed = null;
    private java.lang.String vertical = "";
    private final boolean useCachedData = false;
    private final boolean shouldUseMobileThumbnail = true;
    private final boolean thumbnailTapOpensPreview = true;
    
    @javax.inject.Inject()
    public HomePagePickerViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.usecases.FetchHomePageLayoutsUseCase fetchHomePageLayoutsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker analyticsTracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.theme.SiteDesignRecommendationProvider recommendationProvider) {
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
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel.DesignSelectionAction> getOnDesignActionPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnBackButtonPressed() {
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
    
    @java.lang.Override()
    public boolean getThumbnailTapOpensPreview() {
        return false;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    java.lang.String intent, boolean isTablet) {
    }
    
    @java.lang.Override()
    public void fetchLayouts(boolean preferCache) {
    }
    
    @java.lang.Override()
    public void onLayoutTapped(@org.jetbrains.annotations.NotNull()
    java.lang.String layoutSlug, boolean isRecommended) {
    }
    
    @java.lang.Override()
    public void onPreviewChooseTapped() {
    }
    
    public final void onChooseTapped() {
    }
    
    public final void onSkippedTapped() {
    }
    
    public final void onBackPressed() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel$DesignSelectionAction;", "", "template", "", "(Ljava/lang/String;)V", "getTemplate", "()Ljava/lang/String;", "Choose", "Skip", "Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel$DesignSelectionAction$Skip;", "Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel$DesignSelectionAction$Choose;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class DesignSelectionAction {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String template = null;
        
        private DesignSelectionAction(java.lang.String template) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTemplate() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel$DesignSelectionAction$Skip;", "Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel$DesignSelectionAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Skip extends org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel.DesignSelectionAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel.DesignSelectionAction.Skip INSTANCE = null;
            
            private Skip() {
                super(null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel$DesignSelectionAction$Choose;", "Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel$DesignSelectionAction;", "template", "", "(Ljava/lang/String;)V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Choose extends org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel.DesignSelectionAction {
            
            public Choose(@org.jetbrains.annotations.NotNull()
            java.lang.String template) {
                super(null);
            }
        }
    }
}
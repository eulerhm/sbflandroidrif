package org.wordpress.android.viewmodel.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001:\u0001oBi\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0001\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\u0002\u0010\u001aJ\u0011\u0010N\u001a\u00020%H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010OJ\u0006\u0010P\u001a\u00020*J\b\u0010Q\u001a\u00020*H\u0002J\u0012\u0010R\u001a\u00020*2\b\u0010S\u001a\u0004\u0018\u000108H\u0002J\u0010\u0010T\u001a\u00020\u001f2\b\u0010S\u001a\u0004\u0018\u000108J\u0010\u0010U\u001a\u00020\u001f2\u0006\u0010V\u001a\u00020%H\u0002J\u0010\u0010W\u001a\u00020\u001f2\u0006\u0010V\u001a\u00020%H\u0002J\u0018\u0010X\u001a\b\u0012\u0004\u0012\u00020F0\'2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0002J\u0006\u0010[\u001a\u00020*J\u0012\u0010\\\u001a\u00020]2\b\u0010S\u001a\u0004\u0018\u000108H\u0002J\u0010\u0010^\u001a\u00020*2\u0006\u0010_\u001a\u00020\u001fH\u0002J\u0010\u0010`\u001a\u00020*2\u0006\u0010a\u001a\u00020\u001dH\u0002J\u0010\u0010b\u001a\u00020*2\b\u0010S\u001a\u0004\u0018\u000108J\u0010\u0010c\u001a\u00020*2\b\u0010S\u001a\u0004\u0018\u000108J\b\u0010d\u001a\u00020*H\u0002J\u000e\u0010e\u001a\u00020]2\u0006\u0010f\u001a\u00020\u001fJ\u0018\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020%2\b\u0010S\u001a\u0004\u0018\u000108J\u0018\u0010i\u001a\u00020*2\b\u0010S\u001a\u0004\u0018\u0001082\u0006\u0010h\u001a\u00020%J\u0010\u0010j\u001a\u00020*2\b\u0010S\u001a\u0004\u0018\u000108J\u001a\u0010k\u001a\u00020*2\u0006\u0010l\u001a\u00020%2\b\u0010S\u001a\u0004\u0018\u000108H\u0002J\u0010\u0010m\u001a\u00020*2\b\u0010S\u001a\u0004\u0018\u000108J\b\u0010n\u001a\u00020*H\u0002R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\'0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020%0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0$0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0$0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001d00\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\u001f00\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00102R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\"00\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00102R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u00107\u001a\u0004\u0018\u0001088F\u00a2\u0006\u0006\u001a\u0004\b9\u0010:R\u0011\u0010;\u001a\u00020%8F\u00a2\u0006\u0006\u001a\u0004\b<\u0010=R\u001d\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$00\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u00102R\u0011\u0010?\u001a\u00020%8F\u00a2\u0006\u0006\u001a\u0004\b?\u0010=R\u000e\u0010@\u001a\u00020%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\'00\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u00102R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010C\u001a\b\u0012\u0004\u0012\u00020*00\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u00102R#\u0010E\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0\'0$00\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u00102R\u0017\u0010H\u001a\b\u0012\u0004\u0012\u00020*00\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u00102R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0$00\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u00102R\u001d\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0$00\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u00102\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006p"}, d2 = {"Lorg/wordpress/android/viewmodel/main/WPMainActivityViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "featureAnnouncementProvider", "Lorg/wordpress/android/ui/whatsnew/FeatureAnnouncementProvider;", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "quickStartRepository", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "mySiteDefaultTabExperiment", "Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;", "bloggingPromptsFeatureConfig", "Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;", "bloggingPromptsStore", "Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/whatsnew/FeatureAnnouncementProvider;Lorg/wordpress/android/util/BuildConfigWrapper;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_createAction", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "_createPostWithBloggingPrompt", "", "_fabUiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/main/MainFabUiState;", "_isBottomSheetShowing", "Lorg/wordpress/android/viewmodel/Event;", "", "_mainActions", "", "Lorg/wordpress/android/ui/main/MainActionListItem;", "_onFeatureAnnouncementRequested", "", "_openBloggingPromptsOnboarding", "_showQuickStarInBottomSheet", "_startLoginFlow", "_switchToMySite", "createAction", "Landroidx/lifecycle/LiveData;", "getCreateAction", "()Landroidx/lifecycle/LiveData;", "createPostWithBloggingPrompt", "getCreatePostWithBloggingPrompt", "fabUiState", "getFabUiState", "firstSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getFirstSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "hasMultipleSites", "getHasMultipleSites", "()Z", "isBottomSheetShowing", "isSignedInWPComOrHasWPOrgSite", "isStarted", "mainActions", "getMainActions", "onFeatureAnnouncementRequested", "getOnFeatureAnnouncementRequested", "onFocusPointVisibilityChange", "Lorg/wordpress/android/viewmodel/main/WPMainActivityViewModel$FocusPointInfo;", "getOnFocusPointVisibilityChange", "openBloggingPromptsOnboarding", "getOpenBloggingPromptsOnboarding", "startLoginFlow", "getStartLoginFlow", "switchToMySite", "getSwitchToMySite", "canShowFeatureAnnouncement", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkAndSetVariantForMySiteDefaultTabExperiment", "checkAndShowFeatureAnnouncement", "disableTooltip", "site", "getCreateContentMessageId", "getCreateContentMessageId_StoriesFlagOff", "hasFullAccessToContent", "getCreateContentMessageId_StoriesFlagOn", "getExternalFocusPointInfo", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "handleSiteRemoved", "loadMainActions", "Lkotlinx/coroutines/Job;", "onAnswerPromptActionClicked", "promptId", "onCreateActionClicked", "actionType", "onFabClicked", "onFabLongPressed", "onHelpPrompActionClicked", "onOpenLoginPage", "mySitePosition", "onPageChanged", "isOnMySitePageWithValidSite", "onResume", "onTooltipTapped", "setMainFabUiState", "isFabVisible", "start", "updateFeatureAnnouncements", "FocusPointInfo", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPMainActivityViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.whatsnew.FeatureAnnouncementProvider featureAnnouncementProvider = null;
    private final org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment mySiteDefaultTabExperiment = null;
    private final org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig = null;
    private final org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore bloggingPromptsStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private boolean isStarted = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.main.MainFabUiState> _fabUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.main.MainFabUiState> fabUiState = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _showQuickStarInBottomSheet = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.main.MainActionListItem>> _mainActions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.main.MainActionListItem>> mainActions = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.main.MainActionListItem.ActionType> _createAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.main.MainActionListItem.ActionType> createAction = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> _isBottomSheetShowing = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isBottomSheetShowing = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _startLoginFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> startLoginFlow = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _switchToMySite = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> switchToMySite = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onFeatureAnnouncementRequested = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onFeatureAnnouncementRequested = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.Integer> _createPostWithBloggingPrompt = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> createPostWithBloggingPrompt = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _openBloggingPromptsOnboarding = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> openBloggingPromptsOnboarding = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.util.List<org.wordpress.android.viewmodel.main.WPMainActivityViewModel.FocusPointInfo>>> onFocusPointVisibilityChange = null;
    
    @javax.inject.Inject()
    public WPMainActivityViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.whatsnew.FeatureAnnouncementProvider featureAnnouncementProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment mySiteDefaultTabExperiment, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore bloggingPromptsStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.main.MainFabUiState> getFabUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.main.MainActionListItem>> getMainActions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.main.MainActionListItem.ActionType> getCreateAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isBottomSheetShowing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getStartLoginFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getSwitchToMySite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnFeatureAnnouncementRequested() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getCreatePostWithBloggingPrompt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOpenBloggingPromptsOnboarding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.util.List<org.wordpress.android.viewmodel.main.WPMainActivityViewModel.FocusPointInfo>>> getOnFocusPointVisibilityChange() {
        return null;
    }
    
    public final boolean getHasMultipleSites() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.SiteModel getFirstSite() {
        return null;
    }
    
    public final boolean isSignedInWPComOrHasWPOrgSite() {
        return false;
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @kotlin.Suppress(names = {"LongMethod"})
    private final kotlinx.coroutines.Job loadMainActions(org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    private final void onCreateActionClicked(org.wordpress.android.ui.main.MainActionListItem.ActionType actionType) {
    }
    
    private final void onAnswerPromptActionClicked(int promptId) {
    }
    
    private final void onHelpPrompActionClicked() {
    }
    
    private final void disableTooltip(org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void onFabClicked(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void onPageChanged(boolean isOnMySitePageWithValidSite, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void onTooltipTapped(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void onFabLongPressed(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job onOpenLoginPage(int mySitePosition) {
        return null;
    }
    
    public final void checkAndSetVariantForMySiteDefaultTabExperiment() {
    }
    
    public final void onResume(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, boolean isOnMySitePageWithValidSite) {
    }
    
    private final void checkAndShowFeatureAnnouncement() {
    }
    
    private final void setMainFabUiState(boolean isFabVisible, org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final int getCreateContentMessageId(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return 0;
    }
    
    private final int getCreateContentMessageId_StoriesFlagOn(boolean hasFullAccessToContent) {
        return 0;
    }
    
    private final int getCreateContentMessageId_StoriesFlagOff(boolean hasFullAccessToContent) {
        return 0;
    }
    
    private final void updateFeatureAnnouncements() {
    }
    
    private final java.lang.Object canShowFeatureAnnouncement(kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.viewmodel.main.WPMainActivityViewModel.FocusPointInfo> getExternalFocusPointInfo(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return null;
    }
    
    public final void handleSiteRemoved() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/viewmodel/main/WPMainActivityViewModel$FocusPointInfo;", "", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "isVisible", "", "(Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;Z)V", "()Z", "getTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FocusPointInfo {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task = null;
        private final boolean isVisible = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.main.WPMainActivityViewModel.FocusPointInfo copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, boolean isVisible) {
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
        
        public FocusPointInfo(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, boolean isVisible) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getTask() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isVisible() {
            return false;
        }
    }
}
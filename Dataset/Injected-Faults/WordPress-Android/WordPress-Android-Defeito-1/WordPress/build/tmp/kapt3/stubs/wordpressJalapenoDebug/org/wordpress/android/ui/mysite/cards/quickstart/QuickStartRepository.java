package org.wordpress.android.ui.mysite.cards.quickstart;

import java.lang.System;

@kotlin.Suppress(names = {"LongParameterList", "TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u0085\u00012\u00020\u0001:\u0006\u0085\u0001\u0086\u0001\u0087\u0001B\u0091\u0001\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\u0006\u0010 \u001a\u00020!\u0012\u0006\u0010\"\u001a\u00020#\u00a2\u0006\u0002\u0010$J\u0016\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\J\u000e\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020)J\u0006\u0010`\u001a\u00020^J\u0006\u0010a\u001a\u00020^J\u0006\u0010b\u001a\u00020^J\u0006\u0010c\u001a\u00020^J\u0006\u0010d\u001a\u00020^J\u000e\u0010e\u001a\u00020^2\u0006\u0010f\u001a\u00020\'J\u0010\u0010g\u001a\u00020A2\u0006\u0010h\u001a\u00020\\H\u0002J\u001f\u0010i\u001a\b\u0012\u0004\u0012\u00020\\0j2\u0006\u0010Y\u001a\u00020ZH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010kJ\u0010\u0010l\u001a\u00020)2\u0006\u0010f\u001a\u00020\'H\u0002J\u000e\u0010m\u001a\u00020)2\u0006\u0010f\u001a\u00020\'J\u0010\u0010n\u001a\u00020)2\u0006\u0010f\u001a\u00020\'H\u0002J!\u0010o\u001a\u00020^2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010p\u001a\u00020\\H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010qJ\u0010\u0010r\u001a\u00020^2\u0006\u0010f\u001a\u00020\'H\u0002J\u0010\u0010s\u001a\u00020^2\u0006\u0010f\u001a\u00020\'H\u0002J\u000e\u0010t\u001a\u00020^2\u0006\u0010f\u001a\u00020\'J\u0018\u0010u\u001a\u00020^2\u0006\u0010f\u001a\u00020\'2\u0006\u0010v\u001a\u00020:H\u0002J\u0006\u0010w\u001a\u00020^J\u000e\u0010x\u001a\u00020^2\u0006\u0010f\u001a\u00020\'J\u0016\u0010y\u001a\u00020^2\u0006\u0010f\u001a\u00020\'2\u0006\u0010Y\u001a\u00020ZJ\u0006\u0010z\u001a\u00020^J\u0010\u0010{\u001a\u00020^2\u0006\u0010|\u001a\u00020ZH\u0002J\u0006\u0010}\u001a\u00020^J\f\u0010~\u001a\u00020\u007f*\u00020AH\u0002J\r\u0010\u0080\u0001\u001a\u00020)*\u00020\'H\u0002J\r\u0010\u0081\u0001\u001a\u00020)*\u00020\'H\u0002J\u000e\u0010\u0082\u0001\u001a\u00030\u0083\u0001*\u00020\\H\u0002J\u000e\u0010\u0084\u0001\u001a\u00020\\*\u00030\u0083\u0001H\u0002R\u0016\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\'0&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0+0&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010.0&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000+0&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\'02\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00105\u001a\u0002068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b7\u00108R\u001a\u00109\u001a\u00020:X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020B0@X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020)X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010D\u001a\u00020)\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010F\u001a\u00020)\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010ER\u000e\u0010G\u001a\u00020HX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0+02\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u00104R\u0019\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010.02\u00a2\u0006\b\n\u0000\u001a\u0004\bL\u00104R\u001d\u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000+02\u00a2\u0006\b\n\u0000\u001a\u0004\bN\u00104R\u0010\u0010O\u001a\u0004\u0018\u00010\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010P\u001a\u00020:X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010<\"\u0004\bR\u0010>R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010S\u001a\u00020T8F\u00a2\u0006\u0006\u001a\u0004\bU\u0010VR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0088\u0001"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;", "Lkotlinx/coroutines/CoroutineScope;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "quickStartStore", "Lorg/wordpress/android/fluxc/store/QuickStartStore;", "quickStartUtilsWrapper", "Lorg/wordpress/android/util/QuickStartUtilsWrapper;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "eventBus", "Lorg/wordpress/android/util/EventBusWrapper;", "dynamicCardStore", "Lorg/wordpress/android/fluxc/store/DynamicCardStore;", "htmlCompat", "Lorg/wordpress/android/util/HtmlCompatWrapper;", "quickStartDynamicCardsFeatureConfig", "Lorg/wordpress/android/util/config/QuickStartDynamicCardsFeatureConfig;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "quickStartTracker", "Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "mySiteDashboardTabsFeatureConfig", "Lorg/wordpress/android/util/config/MySiteDashboardTabsFeatureConfig;", "quickStartForExistingUsersV2FeatureConfig", "Lorg/wordpress/android/util/config/QuickStartExistingUsersV2FeatureConfig;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/QuickStartStore;Lorg/wordpress/android/util/QuickStartUtilsWrapper;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/fluxc/store/DynamicCardStore;Lorg/wordpress/android/util/HtmlCompatWrapper;Lorg/wordpress/android/util/config/QuickStartDynamicCardsFeatureConfig;Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;Lorg/wordpress/android/ui/quickstart/QuickStartTracker;Lorg/wordpress/android/util/BuildConfigWrapper;Lorg/wordpress/android/util/config/MySiteDashboardTabsFeatureConfig;Lorg/wordpress/android/util/config/QuickStartExistingUsersV2FeatureConfig;)V", "_activeTask", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "_isQuickStartNoticeShown", "", "_onQuickStartMySitePrompts", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/quickstart/QuickStartMySitePrompts;", "_onQuickStartTabStep", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartTabStep;", "_onSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "activeTask", "Landroidx/lifecycle/LiveData;", "getActiveTask", "()Landroidx/lifecycle/LiveData;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "currentTab", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "getCurrentTab", "()Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "setCurrentTab", "(Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;)V", "detailsMap", "", "", "Lorg/wordpress/android/ui/quickstart/QuickStartTaskDetails;", "isMySiteTabsEnabled", "isQuickStartForExistingUsersV2FeatureEnabled", "()Z", "isQuickStartNoticeShown", "job", "Lkotlinx/coroutines/Job;", "onQuickStartMySitePrompts", "getOnQuickStartMySitePrompts", "onQuickStartTabStep", "getOnQuickStartTabStep", "onSnackbar", "getOnSnackbar", "pendingTask", "quickStartTaskOriginTab", "getQuickStartTaskOriginTab", "setQuickStartTaskOriginTab", "quickStartType", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "getQuickStartType", "()Lorg/wordpress/android/ui/quickstart/QuickStartType;", "buildQuickStartCategory", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartCategory;", "siteLocalId", "", "quickStartTaskType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "checkAndSetQuickStartType", "", "isNewSite", "checkAndShowQuickStartNotice", "clear", "clearActiveTask", "clearPendingTask", "clearTabStep", "completeTask", "task", "getCategoryCompletionMessage", "taskType", "getQuickStartTaskTypes", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isHomeStepRequiredForTask", "isPendingTask", "isSiteMenuStepRequiredForTask", "onCategoryCompleted", "categoryType", "(ILorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onQuickStartNoticeButtonAction", "onQuickStartNoticeNegativeAction", "requestNextStepOfTask", "requestTabStepForTask", "tabType", "resetTask", "setActiveTask", "setTaskDoneAndTrack", "showCompletedQuickStartNotice", "showQuickStartNotice", "selectedSiteLocalId", "skipQuickStart", "asHtml", "", "isShownInHomeTab", "isShownInSiteMenuTab", "toDynamicCardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "toQuickStartTaskType", "Companion", "QuickStartCategory", "QuickStartTabStep", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class QuickStartRepository implements kotlinx.coroutines.CoroutineScope {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.store.QuickStartStore quickStartStore = null;
    private final org.wordpress.android.util.QuickStartUtilsWrapper quickStartUtilsWrapper = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.util.EventBusWrapper eventBus = null;
    private final org.wordpress.android.fluxc.store.DynamicCardStore dynamicCardStore = null;
    private final org.wordpress.android.util.HtmlCompatWrapper htmlCompat = null;
    private final org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    private final org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker = null;
    private final kotlinx.coroutines.Job job = null;
    private final java.util.Map<java.lang.String, org.wordpress.android.ui.quickstart.QuickStartTaskDetails> detailsMap = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask> _activeTask = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _onSnackbar = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.quickstart.QuickStartMySitePrompts>> _onQuickStartMySitePrompts = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartTabStep> _onQuickStartTabStep = null;
    private boolean _isQuickStartNoticeShown = false;
    private final boolean isMySiteTabsEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> onSnackbar = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.quickstart.QuickStartMySitePrompts>> onQuickStartMySitePrompts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartTabStep> onQuickStartTabStep = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask> activeTask = null;
    private final boolean isQuickStartNoticeShown = false;
    @org.jetbrains.annotations.NotNull()
    private org.wordpress.android.ui.mysite.tabs.MySiteTabType currentTab;
    private final boolean isQuickStartForExistingUsersV2FeatureEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private org.wordpress.android.ui.mysite.tabs.MySiteTabType quickStartTaskOriginTab;
    private org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask pendingTask;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.Companion Companion = null;
    private static final int QUICK_START_NOTICE_DURATION = 7000;
    private static final long QUICK_START_COMPLETED_NOTICE_DELAY = 5000L;
    
    @javax.inject.Inject()
    public QuickStartRepository(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.QuickStartUtilsWrapper quickStartUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBus, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.DynamicCardStore dynamicCardStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.HtmlCompatWrapper htmlCompat, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig mySiteDashboardTabsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.QuickStartExistingUsersV2FeatureConfig quickStartForExistingUsersV2FeatureConfig) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getOnSnackbar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.quickstart.QuickStartMySitePrompts>> getOnQuickStartMySitePrompts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartTabStep> getOnQuickStartTabStep() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask> getActiveTask() {
        return null;
    }
    
    public final boolean isQuickStartNoticeShown() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.tabs.MySiteTabType getCurrentTab() {
        return null;
    }
    
    public final void setCurrentTab(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.tabs.MySiteTabType p0) {
    }
    
    public final boolean isQuickStartForExistingUsersV2FeatureEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.tabs.MySiteTabType getQuickStartTaskOriginTab() {
        return null;
    }
    
    public final void setQuickStartTaskOriginTab(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.tabs.MySiteTabType p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.quickstart.QuickStartType getQuickStartType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory buildQuickStartCategory(int siteLocalId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType quickStartTaskType) {
        return null;
    }
    
    public final void resetTask() {
    }
    
    public final void clearActiveTask() {
    }
    
    public final void clearPendingTask() {
    }
    
    public final void clearTabStep() {
    }
    
    public final void checkAndSetQuickStartType(boolean isNewSite) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getQuickStartTaskTypes(int siteLocalId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType>> continuation) {
        return null;
    }
    
    public final void skipQuickStart() {
    }
    
    public final void setActiveTask(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    public final boolean isPendingTask(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return false;
    }
    
    public final void completeTask(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    public final void setTaskDoneAndTrack(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, int siteLocalId) {
    }
    
    private final void requestTabStepForTask(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, org.wordpress.android.ui.mysite.tabs.MySiteTabType tabType) {
    }
    
    public final void requestNextStepOfTask(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    public final void clear() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object onCategoryCompleted(int siteLocalId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType categoryType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.String getCategoryCompletionMessage(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType) {
        return null;
    }
    
    private final java.lang.CharSequence asHtml(java.lang.String $this$asHtml) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType toQuickStartTaskType(org.wordpress.android.fluxc.model.DynamicCardType $this$toQuickStartTaskType) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.model.DynamicCardType toDynamicCardType(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType $this$toDynamicCardType) {
        return null;
    }
    
    public final void checkAndShowQuickStartNotice() {
    }
    
    public final void showCompletedQuickStartNotice() {
    }
    
    private final void showQuickStartNotice(int selectedSiteLocalId) {
    }
    
    private final void onQuickStartNoticeButtonAction(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    private final void onQuickStartNoticeNegativeAction(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    private final boolean isSiteMenuStepRequiredForTask(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return false;
    }
    
    private final boolean isHomeStepRequiredForTask(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return false;
    }
    
    private final boolean isShownInSiteMenuTab(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask $this$isShownInSiteMenuTab) {
        return false;
    }
    
    private final boolean isShownInHomeTab(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask $this$isShownInHomeTab) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartTabStep;", "", "isStarted", "", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "mySiteTabType", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "(ZLorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;)V", "()Z", "getMySiteTabType", "()Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "getTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class QuickStartTabStep {
        private final boolean isStarted = false;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.tabs.MySiteTabType mySiteTabType = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartTabStep copy(boolean isStarted, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.tabs.MySiteTabType mySiteTabType) {
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
        
        public QuickStartTabStep(boolean isStarted, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.tabs.MySiteTabType mySiteTabType) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isStarted() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getTask() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.tabs.MySiteTabType component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.tabs.MySiteTabType getMySiteTabType() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J3\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartCategory;", "", "taskType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "uncompletedTasks", "", "Lorg/wordpress/android/ui/quickstart/QuickStartTaskDetails;", "completedTasks", "(Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;Ljava/util/List;Ljava/util/List;)V", "getCompletedTasks", "()Ljava/util/List;", "getTaskType", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "getUncompletedTasks", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class QuickStartCategory {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskDetails> uncompletedTasks = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskDetails> completedTasks = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.quickstart.QuickStartTaskDetails> uncompletedTasks, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.quickstart.QuickStartTaskDetails> completedTasks) {
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
        
        public QuickStartCategory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.quickstart.QuickStartTaskDetails> uncompletedTasks, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.quickstart.QuickStartTaskDetails> completedTasks) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType getTaskType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskDetails> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskDetails> getUncompletedTasks() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskDetails> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskDetails> getCompletedTasks() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$Companion;", "", "()V", "QUICK_START_COMPLETED_NOTICE_DELAY", "", "QUICK_START_NOTICE_DURATION", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
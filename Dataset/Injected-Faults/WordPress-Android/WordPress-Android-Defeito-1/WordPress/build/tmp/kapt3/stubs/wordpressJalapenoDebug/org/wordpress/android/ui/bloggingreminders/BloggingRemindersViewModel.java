package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00d6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 c2\u00020\u0001:\u0003cdeBa\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020.0(2\u0006\u0010=\u001a\u00020>J\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020>0@J\u0016\u0010A\u001a\b\u0012\u0004\u0012\u00020\u001b0B2\u0006\u0010=\u001a\u00020>H\u0002J\u000e\u0010C\u001a\u00020&2\u0006\u0010=\u001a\u00020>J\u000e\u0010D\u001a\u00020&2\u0006\u0010=\u001a\u00020>J\u0006\u0010E\u001a\u00020&J\u0016\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020>2\u0006\u0010H\u001a\u00020>J\u000e\u0010I\u001a\u00020&2\u0006\u0010J\u001a\u00020-J\u001d\u0010K\u001a\u00020&2\u0006\u0010=\u001a\u00020>2\b\u0010L\u001a\u0004\u0018\u00010\u001e\u00a2\u0006\u0002\u0010MJ\u0010\u0010N\u001a\u00020&2\u0006\u0010O\u001a\u00020\"H\u0002J\u0018\u0010P\u001a\u00020&2\u0006\u0010=\u001a\u00020>2\u0006\u0010Q\u001a\u00020RH\u0002J\u000e\u0010S\u001a\u00020&2\u0006\u0010T\u001a\u00020UJ\u000e\u0010V\u001a\u00020&2\u0006\u0010W\u001a\u00020UJ\u000e\u0010X\u001a\u00020&2\u0006\u0010Y\u001a\u00020ZJ\u0006\u0010[\u001a\u00020&J\b\u0010\\\u001a\u00020&H\u0002J \u0010]\u001a\u00020&2\u0006\u0010=\u001a\u00020>2\u0006\u0010O\u001a\u00020\"2\u0006\u0010Q\u001a\u00020RH\u0002J\u0012\u0010^\u001a\u00020&2\b\u0010_\u001a\u0004\u0018\u00010\u001bH\u0002J\b\u0010`\u001a\u00020&H\u0002J\f\u0010a\u001a\u00020b*\u00020\u001bH\u0002R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0(\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010)R\u001d\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0(\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.0,0(\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010)R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u00100\u001a\u0010\u0012\f\u0012\n 1*\u0004\u0018\u00010\"0\"0(X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0(\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010)R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u00104\u001a\u001d\u0012\u0013\u0012\u00110\u001e\u00a2\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020&05X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020:0(\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010)\u00a8\u0006f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bloggingRemindersManager", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersManager;", "bloggingRemindersStore", "Lorg/wordpress/android/fluxc/store/BloggingRemindersStore;", "prologueBuilder", "Lorg/wordpress/android/ui/bloggingreminders/PrologueBuilder;", "daySelectionBuilder", "Lorg/wordpress/android/ui/bloggingreminders/DaySelectionBuilder;", "epilogueBuilder", "Lorg/wordpress/android/ui/bloggingreminders/EpilogueBuilder;", "dayLabelUtils", "Lorg/wordpress/android/ui/bloggingreminders/DayLabelUtils;", "analyticsTracker", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;", "reminderScheduler", "Lorg/wordpress/android/workers/reminder/ReminderScheduler;", "mapper", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersModelMapper;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersManager;Lorg/wordpress/android/fluxc/store/BloggingRemindersStore;Lorg/wordpress/android/ui/bloggingreminders/PrologueBuilder;Lorg/wordpress/android/ui/bloggingreminders/DaySelectionBuilder;Lorg/wordpress/android/ui/bloggingreminders/EpilogueBuilder;Lorg/wordpress/android/ui/bloggingreminders/DayLabelUtils;Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;Lorg/wordpress/android/workers/reminder/ReminderScheduler;Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersModelMapper;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "_bloggingRemindersModel", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersUiModel;", "_isBottomSheetShowing", "Lorg/wordpress/android/viewmodel/Event;", "", "_isFirstTimeFlow", "_isTimePickerShowing", "_selectedScreen", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$Screen;", "_showBloggingPromptHelpDialogVisible", "finish", "Lkotlin/Function0;", "", "isBottomSheetShowing", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "isTimePickerShowing", "notificationsSettingsUiState", "", "", "Lorg/wordpress/android/ui/utils/UiString;", "getNotificationsSettingsUiState", "selectedScreen", "kotlin.jvm.PlatformType", "showBloggingPromptHelpDialogVisible", "getShowBloggingPromptHelpDialogVisible", "startDaySelection", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "isFirstTimeFlow", "uiState", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$UiState;", "getUiState", "getBlogSettingsUiState", "siteId", "", "getSelectedTime", "Lkotlin/Pair;", "getUiModel", "Lkotlinx/coroutines/flow/Flow;", "onBlogSettingsItemClicked", "onBloggingPromptSchedulingRequested", "onBottomSheetDismissed", "onChangeTime", "hour", "minute", "onNotificationSettingsItemClicked", "remoteSiteId", "onPublishingPost", "isFirstTimePublishing", "(ILjava/lang/Boolean;)V", "onScreenChanged", "screen", "onSettingsItemClicked", "source", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker$Source;", "restoreState", "state", "Landroid/os/Bundle;", "saveState", "outState", "selectDay", "day", "Ljava/time/DayOfWeek;", "selectTime", "showBloggingPromptDialog", "showBottomSheet", "showEpilogue", "bloggingRemindersModel", "togglePromptSwitch", "toReminderConfig", "Lorg/wordpress/android/workers/reminder/ReminderConfig$WeeklyReminder;", "Companion", "Screen", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BloggingRemindersViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersManager bloggingRemindersManager = null;
    private final org.wordpress.android.fluxc.store.BloggingRemindersStore bloggingRemindersStore = null;
    private final org.wordpress.android.ui.bloggingreminders.PrologueBuilder prologueBuilder = null;
    private final org.wordpress.android.ui.bloggingreminders.DaySelectionBuilder daySelectionBuilder = null;
    private final org.wordpress.android.ui.bloggingreminders.EpilogueBuilder epilogueBuilder = null;
    private final org.wordpress.android.ui.bloggingreminders.DayLabelUtils dayLabelUtils = null;
    private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker analyticsTracker = null;
    private final org.wordpress.android.workers.reminder.ReminderScheduler reminderScheduler = null;
    private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersModelMapper mapper = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> _isBottomSheetShowing = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isBottomSheetShowing = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> _isTimePickerShowing = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isTimePickerShowing = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> _showBloggingPromptHelpDialogVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> showBloggingPromptHelpDialogVisible = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Screen> _selectedScreen = null;
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Screen> selectedScreen = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.bloggingreminders.BloggingRemindersUiModel> _bloggingRemindersModel = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isFirstTimeFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState> uiState = null;
    private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> startDaySelection = null;
    private final kotlin.jvm.functions.Function0<kotlin.Unit> finish = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<java.lang.Long, org.wordpress.android.ui.utils.UiString>> notificationsSettingsUiState = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Companion Companion = null;
    private static final java.lang.String SELECTED_SCREEN = "key_shown_screen";
    private static final java.lang.String SELECTED_DAYS = "key_selected_days";
    private static final java.lang.String SELECTED_HOUR = "key_selected_hour";
    private static final java.lang.String SELECTED_MINUTE = "key_selected_minute";
    private static final java.lang.String IS_BLOGGING_PROMPT_INCLUDED = "key_is_blogging_prompt_included";
    private static final java.lang.String IS_FIRST_TIME_FLOW = "is_first_time_flow";
    private static final java.lang.String SITE_ID = "key_site_id";
    
    @javax.inject.Inject()
    public BloggingRemindersViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersManager bloggingRemindersManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.BloggingRemindersStore bloggingRemindersStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.PrologueBuilder prologueBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.DaySelectionBuilder daySelectionBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.EpilogueBuilder epilogueBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.DayLabelUtils dayLabelUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.ReminderScheduler reminderScheduler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersModelMapper mapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isBottomSheetShowing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isTimePickerShowing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> getShowBloggingPromptHelpDialogVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState> getUiState() {
        return null;
    }
    
    private final void onScreenChanged(org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Screen screen) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.utils.UiString> getBlogSettingsUiState(int siteId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<java.lang.Long, org.wordpress.android.ui.utils.UiString>> getNotificationsSettingsUiState() {
        return null;
    }
    
    private final kotlinx.coroutines.flow.Flow<org.wordpress.android.ui.bloggingreminders.BloggingRemindersUiModel> getUiModel(int siteId) {
        return null;
    }
    
    private final void showBottomSheet(int siteId, org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Screen screen, org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker.Source source) {
    }
    
    public final void selectDay(@org.jetbrains.annotations.NotNull()
    java.time.DayOfWeek day) {
    }
    
    public final void selectTime() {
    }
    
    private final void togglePromptSwitch() {
    }
    
    private final void showBloggingPromptDialog() {
    }
    
    public final void onChangeTime(int hour, int minute) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.Integer, java.lang.Integer> getSelectedTime() {
        return null;
    }
    
    private final void showEpilogue(org.wordpress.android.ui.bloggingreminders.BloggingRemindersUiModel bloggingRemindersModel) {
    }
    
    public final void saveState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void restoreState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle state) {
    }
    
    public final void onPublishingPost(int siteId, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isFirstTimePublishing) {
    }
    
    public final void onBlogSettingsItemClicked(int siteId) {
    }
    
    public final void onNotificationSettingsItemClicked(long remoteSiteId) {
    }
    
    public final void onBloggingPromptSchedulingRequested(int siteId) {
    }
    
    private final void onSettingsItemClicked(int siteId, org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker.Source source) {
    }
    
    public final void onBottomSheetDismissed() {
    }
    
    private final org.wordpress.android.workers.reminder.ReminderConfig.WeeklyReminder toReminderConfig(org.wordpress.android.ui.bloggingreminders.BloggingRemindersUiModel $this$toReminderConfig) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$Screen;", "", "trackingName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTrackingName", "()Ljava/lang/String;", "PROLOGUE", "PROLOGUE_SETTINGS", "SELECTION", "EPILOGUE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Screen {
        /*public static final*/ PROLOGUE /* = new PROLOGUE(null) */,
        /*public static final*/ PROLOGUE_SETTINGS /* = new PROLOGUE_SETTINGS(null) */,
        /*public static final*/ SELECTION /* = new SELECTION(null) */,
        /*public static final*/ EPILOGUE /* = new EPILOGUE(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String trackingName = null;
        
        Screen(java.lang.String trackingName) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTrackingName() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0016B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J%\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$UiState;", "", "uiItems", "", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "primaryButton", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$UiState$PrimaryButton;", "(Ljava/util/List;Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$UiState$PrimaryButton;)V", "getPrimaryButton", "()Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$UiState$PrimaryButton;", "getUiItems", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "PrimaryButton", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem> uiItems = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState.PrimaryButton primaryButton = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState copy(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem> uiItems, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState.PrimaryButton primaryButton) {
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
        
        public UiState(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem> uiItems, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState.PrimaryButton primaryButton) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem> getUiItems() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState.PrimaryButton component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState.PrimaryButton getPrimaryButton() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$UiState$PrimaryButton;", "", "text", "Lorg/wordpress/android/ui/utils/UiString;", "enabled", "", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/UiString;ZLorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getEnabled", "()Z", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PrimaryButton {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString text = null;
            private final boolean enabled = false;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState.PrimaryButton copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString text, boolean enabled, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
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
            
            public PrimaryButton(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString text, boolean enabled, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getText() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getEnabled() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$Companion;", "", "()V", "IS_BLOGGING_PROMPT_INCLUDED", "", "IS_FIRST_TIME_FLOW", "SELECTED_DAYS", "SELECTED_HOUR", "SELECTED_MINUTE", "SELECTED_SCREEN", "SITE_ID", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
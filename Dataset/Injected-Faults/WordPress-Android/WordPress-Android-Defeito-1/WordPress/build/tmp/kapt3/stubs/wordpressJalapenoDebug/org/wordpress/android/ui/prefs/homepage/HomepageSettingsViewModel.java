package org.wordpress.android.ui.prefs.homepage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B;\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\u0006\u0010\u001a\u001a\u00020\u0011J\r\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\u0002\u0010\u001dJ\r\u0010\u001e\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\u0002\u0010\u001dJ\r\u0010\u001f\u001a\u0004\u0018\u00010 \u00a2\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020\u0011J\b\u0010#\u001a\u00020\u0011H\u0014J\u0006\u0010$\u001a\u00020\u0011J\u001c\u0010%\u001a\u00020\u00112\b\b\u0002\u0010&\u001a\u00020 2\b\b\u0002\u0010\'\u001a\u00020 H\u0002J\u0006\u0010(\u001a\u00020\u0011J\u0006\u0010)\u001a\u00020\u0011J\u000e\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020\u0011J\u000e\u0010.\u001a\u00020 2\u0006\u0010+\u001a\u00020,J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u000201H\u0007J1\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020,2\b\u00104\u001a\u0004\u0018\u00010 2\b\u00105\u001a\u0004\u0018\u00010\u001c2\b\u00106\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\u0002\u00107J\u0006\u00108\u001a\u00020\u0011J%\u00109\u001a\u00020 2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130;H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010<J\u001c\u0010=\u001a\u00020 2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130;H\u0002J\u001a\u0010>\u001a\u00020 *\b\u0012\u0004\u0012\u00020@0?2\u0006\u0010A\u001a\u00020\u001cH\u0002J\"\u0010B\u001a\u00020 *\b\u0012\u0004\u0012\u00020@0?2\u0006\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020\u001cH\u0002R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006E"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "homepageSettingsDataLoader", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "siteOptionsStore", "Lorg/wordpress/android/fluxc/store/SiteOptionsStore;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/SiteOptionsStore;)V", "_dismissDialogEvent", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "_uiState", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState;", "dismissDialogEvent", "Landroidx/lifecycle/LiveData;", "getDismissDialogEvent", "()Landroidx/lifecycle/LiveData;", "uiState", "getUiState", "classicBlogSelected", "getSelectedPageForPostsId", "", "()Ljava/lang/Long;", "getSelectedPageOnFrontId", "isClassicBlog", "", "()Ljava/lang/Boolean;", "onAcceptClicked", "onCleared", "onDialogHidden", "onDialogStateChange", "pageOnFrontDialogOpened", "pageForPostsDialogOpened", "onDismissClicked", "onPageForPostsDialogOpened", "onPageForPostsSelected", "id", "", "onPageOnFrontDialogOpened", "onPageOnFrontSelected", "onSiteChanged", "event", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSiteChanged;", "start", "siteId", "savedClassicBlogValue", "savedPageForPostsId", "savedPageOnFrontId", "(ILjava/lang/Boolean;Ljava/lang/Long;Ljava/lang/Long;)V", "staticHomepageSelected", "updateUiState", "copyFunction", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUiStateFromMainThread", "hasPage", "", "Lorg/wordpress/android/fluxc/model/page/PageModel;", "remoteId", "isValid", "pageOnFrontRemoteId", "pageForPostsRemoteId", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HomepageSettingsViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader homepageSettingsDataLoader = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.store.SiteOptionsStore siteOptionsStore = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState> uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _dismissDialogEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> dismissDialogEvent = null;
    
    @javax.inject.Inject()
    public HomepageSettingsViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader homepageSettingsDataLoader, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteOptionsStore siteOptionsStore) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getDismissDialogEvent() {
        return null;
    }
    
    public final void classicBlogSelected() {
    }
    
    public final void staticHomepageSelected() {
    }
    
    public final boolean onPageOnFrontSelected(int id) {
        return false;
    }
    
    public final boolean onPageForPostsSelected(int id) {
        return false;
    }
    
    public final void onPageOnFrontDialogOpened() {
    }
    
    public final void onPageForPostsDialogOpened() {
    }
    
    public final void onDialogHidden() {
    }
    
    private final void onDialogStateChange(boolean pageOnFrontDialogOpened, boolean pageForPostsDialogOpened) {
    }
    
    public final void onAcceptClicked() {
    }
    
    public final void onDismissClicked() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getSelectedPageOnFrontId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getSelectedPageForPostsId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isClassicBlog() {
        return null;
    }
    
    public final void start(int siteId, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean savedClassicBlogValue, @org.jetbrains.annotations.Nullable()
    java.lang.Long savedPageForPostsId, @org.jetbrains.annotations.Nullable()
    java.lang.Long savedPageOnFrontId) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final boolean isValid(java.util.List<org.wordpress.android.fluxc.model.page.PageModel> $this$isValid, long pageOnFrontRemoteId, long pageForPostsRemoteId) {
        return false;
    }
    
    private final boolean hasPage(java.util.List<org.wordpress.android.fluxc.model.page.PageModel> $this$hasPage, long remoteId) {
        return false;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSiteChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged event) {
    }
    
    private final java.lang.Object updateUiState(kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState, org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState> copyFunction, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final boolean updateUiStateFromMainThread(kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState, org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState> copyFunction) {
        return false;
    }
}
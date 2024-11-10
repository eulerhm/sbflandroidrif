package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020 H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020\tH\u0002J\u0006\u0010&\u001a\u00020 J\u0006\u0010\'\u001a\u00020 J\u0006\u0010(\u001a\u00020 J\u0006\u0010)\u001a\u00020 J\b\u0010*\u001a\u00020 H\u0002J\u0016\u0010+\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u000fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u000e\u0010\u001a\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListCreateMenuViewModel;", "Landroidx/lifecycle/ViewModel;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "_createAction", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "_fabUiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/main/MainFabUiState;", "_isBottomSheetShowing", "Lorg/wordpress/android/viewmodel/Event;", "", "_mainActions", "", "Lorg/wordpress/android/ui/main/MainActionListItem;", "createAction", "Landroidx/lifecycle/LiveData;", "getCreateAction", "()Landroidx/lifecycle/LiveData;", "fabUiState", "getFabUiState", "isBottomSheetShowing", "isStarted", "mainActions", "getMainActions", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "disableTooltip", "", "getCreateContentMessageId", "", "loadMainActions", "onCreateActionClicked", "actionType", "onFabClicked", "onFabLongPressed", "onResume", "onTooltipTapped", "setMainFabUiState", "start", "actionsShownByDefault", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListCreateMenuViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private boolean isStarted = false;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.main.MainFabUiState> _fabUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.main.MainFabUiState> fabUiState = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.main.MainActionListItem>> _mainActions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.main.MainActionListItem>> mainActions = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.main.MainActionListItem.ActionType> _createAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.main.MainActionListItem.ActionType> createAction = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> _isBottomSheetShowing = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isBottomSheetShowing = null;
    
    @javax.inject.Inject()
    public PostListCreateMenuViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
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
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean actionsShownByDefault) {
    }
    
    private final void loadMainActions() {
    }
    
    private final void onCreateActionClicked(org.wordpress.android.ui.main.MainActionListItem.ActionType actionType) {
    }
    
    private final void setMainFabUiState() {
    }
    
    public final void onFabClicked() {
    }
    
    public final void onTooltipTapped() {
    }
    
    public final void onFabLongPressed() {
    }
    
    private final void disableTooltip() {
    }
    
    public final void onResume() {
    }
    
    private final int getCreateContentMessageId() {
        return 0;
    }
}
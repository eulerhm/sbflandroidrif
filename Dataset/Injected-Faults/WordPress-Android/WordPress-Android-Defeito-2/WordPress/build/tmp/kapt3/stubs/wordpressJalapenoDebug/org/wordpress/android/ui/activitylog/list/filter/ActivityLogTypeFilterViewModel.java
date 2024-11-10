package org.wordpress.android.ui.activitylog.list.filter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0003234B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\u001f\u0010!\u001a\u00020\r2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0017H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\b\u0010%\u001a\u00020&H\u0002J\b\u0010\'\u001a\u00020\u000bH\u0002J\u001c\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00172\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u0017H\u0002J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u0016\u0010,\u001a\u00020\u000b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0017H\u0002J\b\u0010-\u001a\u00020\u000bH\u0002J\u0010\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u0018H\u0002J\b\u00100\u001a\u00020\u000bH\u0002J@\u00101\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001c2\u001a\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fj\u0004\u0018\u0001`\u00112\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fj\u0004\u0018\u0001`\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "activityLogStore", "Lorg/wordpress/android/fluxc/store/ActivityLogStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "(Lorg/wordpress/android/fluxc/store/ActivityLogStore;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_dismissDialog", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "_uiState", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState;", "dateRange", "Landroidx/core/util/Pair;", "", "Lorg/wordpress/android/viewmodel/activitylog/DateRange;", "dismissDialog", "Landroidx/lifecycle/LiveData;", "getDismissDialog", "()Landroidx/lifecycle/LiveData;", "initialSelection", "", "", "isStarted", "", "parentViewModel", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel;", "remoteSiteId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "uiState", "getUiState", "buildContentUiState", "activityTypes", "Lorg/wordpress/android/fluxc/model/activity/ActivityTypeModel;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildErrorUiState", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error$ConnectionError;", "fetchAvailableActivityTypes", "getAllActivityTypeItemsUnchecked", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState;", "listItemUiStates", "getSelectedActivityTypeIds", "onApplyClicked", "onClearClicked", "onItemClicked", "itemId", "onRetryClicked", "start", "Action", "ListItemUiState", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogTypeFilterViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private boolean isStarted = false;
    private org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId remoteSiteId;
    private org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel parentViewModel;
    private java.util.List<java.lang.String> initialSelection;
    private androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState> uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _dismissDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> dismissDialog = null;
    
    @javax.inject.Inject()
    public ActivityLogTypeFilterViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getDismissDialog() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId remoteSiteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel parentViewModel, @org.jetbrains.annotations.Nullable()
    androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> initialSelection) {
    }
    
    private final void fetchAvailableActivityTypes() {
    }
    
    private final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.Error.ConnectionError buildErrorUiState() {
        return null;
    }
    
    private final java.lang.Object buildContentUiState(java.util.List<org.wordpress.android.fluxc.model.activity.ActivityTypeModel> activityTypes, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState> continuation) {
        return null;
    }
    
    private final void onItemClicked(java.lang.String itemId) {
    }
    
    private final void onApplyClicked(java.util.List<org.wordpress.android.fluxc.model.activity.ActivityTypeModel> activityTypes) {
    }
    
    private final void onRetryClicked() {
    }
    
    private final void onClearClicked() {
    }
    
    private final java.util.List<org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState> getAllActivityTypeItemsUnchecked(java.util.List<? extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState> listItemUiStates) {
        return null;
    }
    
    private final java.util.List<java.lang.String> getSelectedActivityTypeIds() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006\u0082\u0001\u0003\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState;", "", "()V", "contentVisibility", "", "getContentVisibility", "()Z", "errorVisibility", "getErrorVisibility", "loadingVisibility", "getLoadingVisibility", "Content", "Error", "FullscreenLoading", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$FullscreenLoading;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Content;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UiState {
        private final boolean contentVisibility = false;
        private final boolean loadingVisibility = false;
        private final boolean errorVisibility = false;
        
        private UiState() {
            super();
        }
        
        public boolean getContentVisibility() {
            return false;
        }
        
        public boolean getLoadingVisibility() {
            return false;
        }
        
        public boolean getErrorVisibility() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$FullscreenLoading;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState;", "()V", "loadingText", "Lorg/wordpress/android/ui/utils/UiString;", "getLoadingText", "()Lorg/wordpress/android/ui/utils/UiString;", "loadingVisibility", "", "getLoadingVisibility", "()Z", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class FullscreenLoading extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.FullscreenLoading INSTANCE = null;
            private static final boolean loadingVisibility = true;
            @org.jetbrains.annotations.NotNull()
            private static final org.wordpress.android.ui.utils.UiString loadingText = null;
            
            private FullscreenLoading() {
                super();
            }
            
            @java.lang.Override()
            public boolean getLoadingVisibility() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getLoadingText() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006R\u0012\u0010\u0015\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0006\u0082\u0001\u0002\u0019\u001a\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState;", "()V", "buttonText", "Lorg/wordpress/android/ui/utils/UiString;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString;", "errorVisibility", "", "getErrorVisibility", "()Z", "image", "", "getImage", "()I", "retryAction", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;", "getRetryAction", "()Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;", "subtitle", "getSubtitle", "title", "getTitle", "ConnectionError", "NoActivitiesError", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error$ConnectionError;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error$NoActivitiesError;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Error extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState {
            private final boolean errorVisibility = true;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString buttonText = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action retryAction = null;
            
            private Error() {
                super();
            }
            
            @java.lang.Override()
            public boolean getErrorVisibility() {
                return false;
            }
            
            public abstract int getImage();
            
            @org.jetbrains.annotations.NotNull()
            public abstract org.wordpress.android.ui.utils.UiString getTitle();
            
            @org.jetbrains.annotations.NotNull()
            public abstract org.wordpress.android.ui.utils.UiString getSubtitle();
            
            @org.jetbrains.annotations.Nullable()
            public org.wordpress.android.ui.utils.UiString getButtonText() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action getRetryAction() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\nH\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\n8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0014\u0010\u0011\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\b\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error$ConnectionError;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error;", "retryAction", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;", "(Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;)V", "buttonText", "Lorg/wordpress/android/ui/utils/UiString;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString;", "image", "", "getImage", "()I", "getRetryAction", "()Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;", "subtitle", "getSubtitle", "title", "getTitle", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ConnectionError extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.Error {
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action retryAction = null;
                @androidx.annotation.DrawableRes()
                private final int image = org.wordpress.android.R.drawable.img_illustration_cloud_off_152dp;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString title = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString subtitle = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString buttonText = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.Error.ConnectionError copy(@org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action retryAction) {
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
                
                public ConnectionError(@org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action retryAction) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action getRetryAction() {
                    return null;
                }
                
                @java.lang.Override()
                public int getImage() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString getTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString getSubtitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString getButtonText() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error$NoActivitiesError;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error;", "()V", "image", "", "getImage", "()I", "subtitle", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "title", "getTitle", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoActivitiesError extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.Error {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.Error.NoActivitiesError INSTANCE = null;
                @androidx.annotation.DrawableRes()
                private static final int image = org.wordpress.android.R.drawable.img_illustration_empty_results_216dp;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes subtitle = null;
                
                private NoActivitiesError() {
                    super();
                }
                
                @java.lang.Override()
                public int getImage() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitle() {
                    return null;
                }
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00c6\u0003J-\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0014\u0010\t\u001a\u00020\nX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Content;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState;", "items", "", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState;", "primaryAction", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;", "secondaryAction", "(Ljava/util/List;Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;)V", "contentVisibility", "", "getContentVisibility", "()Z", "getItems", "()Ljava/util/List;", "getPrimaryAction", "()Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;", "getSecondaryAction", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Content extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState> items = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action primaryAction = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action secondaryAction = null;
            private final boolean contentVisibility = true;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.Content copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action primaryAction, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action secondaryAction) {
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
            
            public Content(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action primaryAction, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action secondaryAction) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState> getItems() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action getPrimaryAction() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action getSecondaryAction() {
                return null;
            }
            
            @java.lang.Override()
            public boolean getContentVisibility() {
                return false;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState;", "", "()V", "ActivityType", "SectionHeader", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState$SectionHeader;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState$ActivityType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ListItemUiState {
        
        private ListItemUiState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState$SectionHeader;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SectionHeader extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState.SectionHeader copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title) {
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
            
            public SectionHeader(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title) {
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
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState$ActivityType;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState;", "id", "", "title", "Lorg/wordpress/android/ui/utils/UiString;", "checked", "", "onClick", "Lkotlin/Function0;", "", "(Ljava/lang/String;Lorg/wordpress/android/ui/utils/UiString;ZLkotlin/jvm/functions/Function0;)V", "getChecked", "()Z", "getId", "()Ljava/lang/String;", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ActivityType extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String id = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            private final boolean checked = false;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> onClick = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState.ActivityType copy(@org.jetbrains.annotations.NotNull()
            java.lang.String id, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, boolean checked, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
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
            
            public ActivityType(@org.jetbrains.annotations.NotNull()
            java.lang.String id, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, boolean checked, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getChecked() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnClick() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;", "", "label", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "action", "Lkotlin/Function0;", "", "getAction", "()Lkotlin/jvm/functions/Function0;", "setAction", "(Lkotlin/jvm/functions/Function0;)V", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Action {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString label = null;
        public kotlin.jvm.functions.Function0<kotlin.Unit> action;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label) {
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
        
        public Action(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
            return null;
        }
        
        public final void setAction(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
        }
    }
}
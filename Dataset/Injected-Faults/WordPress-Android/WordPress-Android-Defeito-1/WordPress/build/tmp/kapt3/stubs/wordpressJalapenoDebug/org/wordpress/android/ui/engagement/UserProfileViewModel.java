package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0013\u001a\u00020\u0014Jg\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172M\u0010\u0018\u001aI\u0012\u0013\u0012\u00110\u001a\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001e\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u001e\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00192\b\u0010 \u001a\u0004\u0018\u00010!R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/engagement/UserProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;)V", "_bottomSheetUiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/engagement/BottomSheetUiState;", "_onBottomSheetAction", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/engagement/BottomSheetAction;", "bottomSheetUiState", "Landroidx/lifecycle/LiveData;", "getBottomSheetUiState", "()Landroidx/lifecycle/LiveData;", "onBottomSheetAction", "getOnBottomSheetAction", "onBottomSheetCancelled", "", "onBottomSheetOpen", "userProfile", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet$UserProfile;", "onClick", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "siteId", "", "siteUrl", "source", "Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UserProfileViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.BottomSheetAction>> _onBottomSheetAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.BottomSheetAction>> onBottomSheetAction = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.engagement.BottomSheetUiState> _bottomSheetUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.engagement.BottomSheetUiState> bottomSheetUiState = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.engagement.UserProfileViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_PROFILE_VM_KEY = "USER_PROFILE_VIEW_MODEL_KEY";
    
    @javax.inject.Inject()
    public UserProfileViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.BottomSheetAction>> getOnBottomSheetAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.engagement.BottomSheetUiState> getBottomSheetUiState() {
        return null;
    }
    
    public final void onBottomSheetOpen(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile userProfile, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onClick, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.engagement.EngagementNavigationSource source) {
    }
    
    public final void onBottomSheetCancelled() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/engagement/UserProfileViewModel$Companion;", "", "()V", "USER_PROFILE_VM_KEY", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
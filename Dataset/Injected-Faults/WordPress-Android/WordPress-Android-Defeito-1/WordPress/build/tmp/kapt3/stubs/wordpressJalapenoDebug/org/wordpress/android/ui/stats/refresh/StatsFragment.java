package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\u0014\u0010\'\u001a\u00020\u0017*\u00020(2\u0006\u0010)\u001a\u00020*H\u0002J&\u0010+\u001a\u00020\u0017*\u00020(2\u0006\u0010#\u001a\u00020$2\u0006\u0010,\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0003J\u0014\u0010-\u001a\u00020\u0017*\u00020(2\u0006\u0010#\u001a\u00020$H\u0002J\u0014\u0010.\u001a\u00020\u0017*\u00020(2\u0006\u0010#\u001a\u00020$H\u0002J\u0014\u0010/\u001a\u00020\u0017*\u00020(2\u0006\u00100\u001a\u000201H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u00a8\u00062"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/wordpress/android/ui/ScrollableViewInitializedListener;", "()V", "restorePreviousSearch", "", "selectedTabListener", "Lorg/wordpress/android/ui/stats/refresh/SelectedTabListener;", "swipeToRefreshHelper", "Lorg/wordpress/android/util/helpers/SwipeToRefreshHelper;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/stats/refresh/StatsViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/stats/refresh/StatsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onScrollableViewInitialized", "containerId", "", "onViewCreated", "view", "Landroid/view/View;", "showSnackbar", "activity", "Landroidx/fragment/app/FragmentActivity;", "holder", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "handleSelectedSection", "Lorg/wordpress/android/databinding/StatsFragmentBinding;", "selectedSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "initializeViewModels", "isFirstStart", "initializeViews", "setupObservers", "updateUi", "statsModuleUiModel", "Lorg/wordpress/android/ui/stats/refresh/StatsViewModel$StatsModuleUiModel;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class StatsFragment extends androidx.fragment.app.Fragment implements org.wordpress.android.ui.ScrollableViewInitializedListener {
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private final kotlin.Lazy viewModel$delegate = null;
    private org.wordpress.android.util.helpers.SwipeToRefreshHelper swipeToRefreshHelper;
    private org.wordpress.android.ui.stats.refresh.SelectedTabListener selectedTabListener;
    private boolean restorePreviousSearch = false;
    
    public StatsFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    private final org.wordpress.android.ui.stats.refresh.StatsViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void initializeViews(org.wordpress.android.databinding.StatsFragmentBinding $this$initializeViews, androidx.fragment.app.FragmentActivity activity) {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void initializeViewModels(org.wordpress.android.databinding.StatsFragmentBinding $this$initializeViewModels, androidx.fragment.app.FragmentActivity activity, boolean isFirstStart, android.os.Bundle savedInstanceState) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.StatsFragmentBinding $this$setupObservers, androidx.fragment.app.FragmentActivity activity) {
    }
    
    private final void updateUi(org.wordpress.android.databinding.StatsFragmentBinding $this$updateUi, org.wordpress.android.ui.stats.refresh.StatsViewModel.StatsModuleUiModel statsModuleUiModel) {
    }
    
    private final void handleSelectedSection(org.wordpress.android.databinding.StatsFragmentBinding $this$handleSelectedSection, org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection selectedSection) {
    }
    
    private final void showSnackbar(androidx.fragment.app.FragmentActivity activity, org.wordpress.android.ui.pages.SnackbarMessageHolder holder) {
    }
    
    @java.lang.Override()
    public void onScrollableViewInitialized(int containerId) {
    }
}
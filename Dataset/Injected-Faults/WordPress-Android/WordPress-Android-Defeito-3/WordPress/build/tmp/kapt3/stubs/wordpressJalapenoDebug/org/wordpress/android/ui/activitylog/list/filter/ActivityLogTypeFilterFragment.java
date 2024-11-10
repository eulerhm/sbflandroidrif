package org.wordpress.android.ui.activitylog.list.filter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 62\u00020\u0001:\u00016B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J$\u0010!\u001a\u00020\u0014*\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\'H\u0002J\f\u0010(\u001a\u00020\u0014*\u00020\"H\u0002J\f\u0010)\u001a\u00020\u0014*\u00020\"H\u0002J\f\u0010*\u001a\u00020\u0014*\u00020\"H\u0002J\f\u0010+\u001a\u00020\u0014*\u00020\"H\u0002J\u0014\u0010,\u001a\u00020\u0014*\u00020\"2\u0006\u0010-\u001a\u00020.H\u0002J\u0014\u0010/\u001a\u00020\u0014*\u00020\"2\u0006\u0010-\u001a\u000200H\u0002J\u0014\u00101\u001a\u00020\u0014*\u0002022\u0006\u0010-\u001a\u000203H\u0002J\u0014\u00104\u001a\u00020\u0014*\u00020\"2\u0006\u0010-\u001a\u000205H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u00067"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getTheme", "", "onAttach", "", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "addMenuItem", "Lorg/wordpress/android/databinding/ActivityLogTypeFilterFragmentBinding;", "action", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$Action;", "order", "showAlways", "", "initAdapter", "initRecyclerView", "initToolbar", "initViewModel", "refreshContentScreen", "uiState", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Content;", "refreshErrorScreen", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$Error;", "refreshLoadingScreen", "Lorg/wordpress/android/databinding/ProgressLayoutBinding;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState$FullscreenLoading;", "refreshMenuItems", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$UiState;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogTypeFilterFragment extends androidx.fragment.app.DialogFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterFragment.Companion Companion = null;
    
    public ActivityLogTypeFilterFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @java.lang.Override()
    public int getTheme() {
        return 0;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initToolbar(org.wordpress.android.databinding.ActivityLogTypeFilterFragmentBinding $this$initToolbar) {
    }
    
    private final void initRecyclerView(org.wordpress.android.databinding.ActivityLogTypeFilterFragmentBinding $this$initRecyclerView) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.ActivityLogTypeFilterFragmentBinding $this$initViewModel) {
    }
    
    private final void refreshLoadingScreen(org.wordpress.android.databinding.ProgressLayoutBinding $this$refreshLoadingScreen, org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.FullscreenLoading uiState) {
    }
    
    private final void refreshErrorScreen(org.wordpress.android.databinding.ActivityLogTypeFilterFragmentBinding $this$refreshErrorScreen, org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.Error uiState) {
    }
    
    private final void refreshContentScreen(org.wordpress.android.databinding.ActivityLogTypeFilterFragmentBinding $this$refreshContentScreen, org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState.Content uiState) {
    }
    
    private final void refreshMenuItems(org.wordpress.android.databinding.ActivityLogTypeFilterFragmentBinding $this$refreshMenuItems, org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.UiState uiState) {
    }
    
    private final void addMenuItem(org.wordpress.android.databinding.ActivityLogTypeFilterFragmentBinding $this$addMenuItem, org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.Action action, int order, boolean showAlways) {
    }
    
    private final void initAdapter(org.wordpress.android.databinding.ActivityLogTypeFilterFragmentBinding $this$initAdapter) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterFragment newInstance(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId remoteSiteId, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> initialSelection, @org.jetbrains.annotations.Nullable()
    androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\rH\u0007\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterFragment$Companion;", "", "()V", "newInstance", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterFragment;", "remoteSiteId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "initialSelection", "", "", "dateRange", "Landroidx/core/util/Pair;", "", "Lorg/wordpress/android/viewmodel/activitylog/DateRange;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId remoteSiteId, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> initialSelection, @org.jetbrains.annotations.Nullable()
        androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange) {
            return null;
        }
    }
}
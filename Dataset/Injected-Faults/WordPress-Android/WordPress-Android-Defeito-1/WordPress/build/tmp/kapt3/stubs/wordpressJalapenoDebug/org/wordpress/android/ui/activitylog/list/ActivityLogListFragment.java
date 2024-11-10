package org.wordpress.android.ui.activitylog.list;

import java.lang.System;

/**
 * It was decided to reuse the 'Activity Log' screen instead of creating a new 'Backup' screen. This was due to the
 * fact that there will be lots of code that would need to be duplicated for the new 'Backup' screen. On the other
 * hand, not much more complexity would be introduced if the 'Activity Log' screen is reused (mainly some 'if/else'
 * code branches here and there).
 *
 * However, should more 'Backup' related additions are added to the 'Activity Log' screen, then it should become a
 * necessity to split those features in separate screens in order not to increase further the complexity of this
 * screen's architecture.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0013\u001a\u00020\u00142\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u00170\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010\u001c\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0002J\u001e\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\'J\u0016\u0010(\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$2\u0006\u0010)\u001a\u00020\u0018J\u0010\u0010*\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u001eH\u0016J\u0018\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010 \u001a\u00020!H\u0002J\u001a\u00100\u001a\u00020\u00142\u0006\u00101\u001a\u0002022\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u00103\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u00104\u001a\u00020\u00142\b\u00105\u001a\u0004\u0018\u000106H\u0002J\b\u00107\u001a\u00020\u0014H\u0002J:\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u00020:2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020$0<2\u001a\u0010=\u001a\u0016\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017j\u0004\u0018\u0001`>H\u0002J$\u0010?\u001a\u00020\u00142\u001a\u0010@\u001a\u0016\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017j\u0004\u0018\u0001`>H\u0002J\u0010\u0010A\u001a\u00020\u00142\u0006\u0010B\u001a\u00020CH\u0002J\u001a\u0010D\u001a\u00020\u0014*\u00020E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020!0<H\u0002J\u001a\u0010G\u001a\u00020\u0014*\u00020E2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020!0<H\u0002J\f\u0010H\u001a\u00020\u0014*\u00020EH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006I"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "swipeToRefreshHelper", "Lorg/wordpress/android/util/helpers/SwipeToRefreshHelper;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "initDateRangePickerButtonClickListener", "", "picker", "Lcom/google/android/material/datepicker/MaterialDatePicker;", "Landroidx/core/util/Pair;", "", "navigate", "events", "Lorg/wordpress/android/ui/activitylog/ActivityLogNavigationEvents;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", "item", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "onQueryBackupDownloadStatus", "rewindId", "", "downloadId", "actionState", "", "onQueryRestoreStatus", "restoreId", "onSaveInstanceState", "outState", "onSecondaryActionClicked", "", "secondaryAction", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$SecondaryAction;", "onViewCreated", "view", "Landroid/view/View;", "onViewStateRestored", "refreshProgressBars", "eventListStatus", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$ActivityLogListStatus;", "restoreDateRangePickerListeners", "showActivityTypeFilterDialog", "remoteSiteId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "initialSelection", "", "dateRange", "Lorg/wordpress/android/viewmodel/activitylog/DateRange;", "showDateRangePicker", "initialDateRange", "updateFilters", "uiState", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState$FiltersShown;", "reloadEvents", "Lorg/wordpress/android/databinding/ActivityLogListFragmentBinding;", "data", "setEvents", "setupObservers", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogListFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel viewModel;
    private org.wordpress.android.util.helpers.SwipeToRefreshHelper swipeToRefreshHelper;
    
    public ActivityLogListFragment() {
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
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewStateRestored(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void restoreDateRangePickerListeners() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void onQueryRestoreStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String rewindId, long restoreId) {
    }
    
    public final void onQueryBackupDownloadStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String rewindId, long downloadId, int actionState) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.ActivityLogListFragmentBinding $this$setupObservers) {
    }
    
    private final void navigate(org.wordpress.android.ui.activitylog.ActivityLogNavigationEvents events) {
    }
    
    private final void showDateRangePicker(androidx.core.util.Pair<java.lang.Long, java.lang.Long> initialDateRange) {
    }
    
    private final void initDateRangePickerButtonClickListener(com.google.android.material.datepicker.MaterialDatePicker<androidx.core.util.Pair<java.lang.Long, java.lang.Long>> picker) {
    }
    
    private final void showActivityTypeFilterDialog(org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId remoteSiteId, java.util.List<java.lang.String> initialSelection, androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange) {
    }
    
    private final void updateFilters(org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.FiltersUiState.FiltersShown uiState) {
    }
    
    private final void refreshProgressBars(org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ActivityLogListStatus eventListStatus) {
    }
    
    private final void reloadEvents(org.wordpress.android.databinding.ActivityLogListFragmentBinding $this$reloadEvents, java.util.List<? extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem> data) {
    }
    
    private final void onItemClicked(org.wordpress.android.ui.activitylog.list.ActivityLogListItem item) {
    }
    
    private final boolean onSecondaryActionClicked(org.wordpress.android.ui.activitylog.list.ActivityLogListItem.SecondaryAction secondaryAction, org.wordpress.android.ui.activitylog.list.ActivityLogListItem item) {
        return false;
    }
    
    private final void setEvents(org.wordpress.android.databinding.ActivityLogListFragmentBinding $this$setEvents, java.util.List<? extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem> events) {
    }
}
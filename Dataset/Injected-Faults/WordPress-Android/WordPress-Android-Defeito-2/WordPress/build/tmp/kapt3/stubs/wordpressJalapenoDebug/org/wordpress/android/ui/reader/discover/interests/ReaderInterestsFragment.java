package org.wordpress.android.ui.reader.discover.interests;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 52\u00020\u0001:\u000256B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001c\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0014\u0010!\u001a\u00020\u0014*\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0002J\f\u0010$\u001a\u00020\u0014*\u00020\u001cH\u0002J\f\u0010%\u001a\u00020\u0014*\u00020\u001cH\u0002J\u0014\u0010&\u001a\u00020\u0014*\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0002J\u0014\u0010\'\u001a\u00020\u0014*\u00020(2\u0006\u0010)\u001a\u00020\u0019H\u0002J\u0014\u0010*\u001a\u00020\u0014*\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0002J\u0014\u0010+\u001a\u00020\u0014*\u00020\u001c2\u0006\u0010,\u001a\u00020-H\u0002J\u0014\u0010.\u001a\u00020\u0014*\u00020\u001c2\u0006\u0010/\u001a\u000200H\u0002J\u001a\u00101\u001a\u00020\u0014*\u00020\u001c2\f\u00102\u001a\b\u0012\u0004\u0012\u00020403H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u00067"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/interests/ReaderInterestsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "parentViewModel", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/reader/discover/interests/ReaderInterestsViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "createChipView", "Lcom/google/android/material/chip/Chip;", "Lorg/wordpress/android/databinding/ReaderInterestsFragmentLayoutBinding;", "slug", "", "index", "", "initBackButton", "entryPoint", "Lorg/wordpress/android/ui/reader/discover/interests/ReaderInterestsFragment$EntryPoint;", "initDoneButton", "initRetryButton", "initViewModel", "showSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "anchorView", "startObserving", "updateDoneButton", "doneButtonUiState", "Lorg/wordpress/android/ui/reader/discover/interests/ReaderInterestsViewModel$DoneButtonUiState;", "updateErrorLayout", "uiState", "Lorg/wordpress/android/ui/reader/discover/interests/ReaderInterestsViewModel$UiState$ErrorUiState;", "updateInterests", "interestsUiState", "", "Lorg/wordpress/android/ui/reader/discover/interests/TagUiState;", "Companion", "EntryPoint", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderInterestsFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.reader.discover.interests.ReaderInterestsViewModel viewModel;
    private org.wordpress.android.ui.reader.viewmodels.ReaderViewModel parentViewModel;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.discover.interests.ReaderInterestsFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "reader_interests_fragment_tag";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String READER_INTEREST_ENTRY_POINT = "reader_interest_entry_point";
    
    public ReaderInterestsFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
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
    
    private final void initDoneButton(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$initDoneButton) {
    }
    
    private final void initRetryButton(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$initRetryButton) {
    }
    
    private final void initBackButton(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$initBackButton, org.wordpress.android.ui.reader.discover.interests.ReaderInterestsFragment.EntryPoint entryPoint) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$initViewModel, org.wordpress.android.ui.reader.discover.interests.ReaderInterestsFragment.EntryPoint entryPoint) {
    }
    
    private final void startObserving(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$startObserving, org.wordpress.android.ui.reader.discover.interests.ReaderInterestsFragment.EntryPoint entryPoint) {
    }
    
    private final void updateDoneButton(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$updateDoneButton, org.wordpress.android.ui.reader.discover.interests.ReaderInterestsViewModel.DoneButtonUiState doneButtonUiState) {
    }
    
    private final void updateInterests(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$updateInterests, java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> interestsUiState) {
    }
    
    private final void updateErrorLayout(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$updateErrorLayout, org.wordpress.android.ui.reader.discover.interests.ReaderInterestsViewModel.UiState.ErrorUiState uiState) {
    }
    
    private final void showSnackbar(org.wordpress.android.ui.pages.SnackbarMessageHolder $this$showSnackbar, android.view.View anchorView) {
    }
    
    private final com.google.android.material.chip.Chip createChipView(org.wordpress.android.databinding.ReaderInterestsFragmentLayoutBinding $this$createChipView, java.lang.String slug, int index) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/interests/ReaderInterestsFragment$EntryPoint;", "", "(Ljava/lang/String;I)V", "DISCOVER", "SETTINGS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum EntryPoint {
        /*public static final*/ DISCOVER /* = new DISCOVER() */,
        /*public static final*/ SETTINGS /* = new SETTINGS() */;
        
        EntryPoint() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/interests/ReaderInterestsFragment$Companion;", "", "()V", "READER_INTEREST_ENTRY_POINT", "", "TAG", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
package org.wordpress.android.ui.reader.discover;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J\"\u0010/\u001a\u00020,2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002012\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0012\u00105\u001a\u00020,2\b\u00106\u001a\u0004\u0018\u000107H\u0016J\b\u00108\u001a\u00020,H\u0016J\u001a\u00109\u001a\u00020,2\u0006\u0010:\u001a\u00020*2\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0010\u0010;\u001a\u00020,2\u0006\u0010<\u001a\u00020=H\u0002J\f\u0010>\u001a\u00020,*\u00020?H\u0002J\f\u0010@\u001a\u00020,*\u00020\u0004H\u0002J\f\u0010A\u001a\u00020,*\u00020BH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u00020$8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(\u00a8\u0006C"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderDiscoverFragment;", "Lorg/wordpress/android/ui/ViewPagerFragment;", "()V", "binding", "Lorg/wordpress/android/databinding/ReaderDiscoverFragmentLayoutBinding;", "bookmarksSavedLocallyDialog", "Landroidx/appcompat/app/AlertDialog;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "parentViewModel", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "getReaderTracker", "()Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "setReaderTracker", "(Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "getReaderUtilsWrapper", "()Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "setReaderUtilsWrapper", "(Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/reader/discover/ReaderDiscoverViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getScrollableViewForUniqueIdProvision", "Landroid/view/View;", "handleNavigation", "", "event", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "showBookmarkSavedLocallyDialog", "bookmarkDialog", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowBookmarkedSavedOnlyLocallyDialog;", "addWebViewCachingFragment", "Lorg/wordpress/android/ui/reader/usecases/BookmarkPostState$PreLoadPostContent;", "initViewModel", "showSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderDiscoverFragment extends org.wordpress.android.ui.ViewPagerFragment {
    private androidx.appcompat.app.AlertDialog bookmarksSavedLocallyDialog;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    private org.wordpress.android.ui.reader.discover.ReaderDiscoverViewModel viewModel;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker;
    private org.wordpress.android.ui.reader.viewmodels.ReaderViewModel parentViewModel;
    private org.wordpress.android.databinding.ReaderDiscoverFragmentLayoutBinding binding;
    
    public ReaderDiscoverFragment() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper getReaderUtilsWrapper() {
        return null;
    }
    
    public final void setReaderUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.tracker.ReaderTracker getReaderTracker() {
        return null;
    }
    
    public final void setReaderTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker p0) {
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
    
    private final void initViewModel(org.wordpress.android.databinding.ReaderDiscoverFragmentLayoutBinding $this$initViewModel) {
    }
    
    @kotlin.Suppress(names = {"ComplexMethod"})
    private final void handleNavigation(org.wordpress.android.ui.reader.discover.ReaderNavigationEvents event) {
    }
    
    private final void showBookmarkSavedLocallyDialog(org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowBookmarkedSavedOnlyLocallyDialog bookmarkDialog) {
    }
    
    private final void showSnackbar(org.wordpress.android.ui.pages.SnackbarMessageHolder $this$showSnackbar) {
    }
    
    private final void addWebViewCachingFragment(org.wordpress.android.ui.reader.usecases.BookmarkPostState.PreLoadPostContent $this$addWebViewCachingFragment) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View getScrollableViewForUniqueIdProvision() {
        return null;
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
}
package org.wordpress.android.ui.sitecreation.verticals;

import java.lang.System;

/**
 * Implements the Site Intent Question UI
 */
@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u001a\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010#\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\f\u0010$\u001a\u00020 *\u00020\u0004H\u0002J\f\u0010%\u001a\u00020 *\u00020\u0004H\u0002J\f\u0010&\u001a\u00020 *\u00020\u0004H\u0002J\f\u0010\'\u001a\u00020 *\u00020\u0004H\u0002J\f\u0010(\u001a\u00020 *\u00020\u0004H\u0002J\u0014\u0010)\u001a\u00020 *\u00020\u00042\u0006\u0010*\u001a\u00020+H\u0002J\u0014\u0010,\u001a\u00020 *\u00020\u00042\u0006\u0010-\u001a\u00020.H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lorg/wordpress/android/databinding/SiteCreationIntentsFragmentBinding;", "displayUtils", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "getDisplayUtils$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/util/DisplayUtilsWrapper;", "setDisplayUtils$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/util/DisplayUtilsWrapper;)V", "uiHelper", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelper$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelper$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "onViewStateRestored", "addSearchTextChangedListener", "setScrollListener", "setupActionListeners", "setupUi", "setupViewModel", "updateTitleVisibility", "shouldAppBarTitleBeVisible", "", "updateUiState", "uiState", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class SiteCreationIntentsFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelper;
    @javax.inject.Inject()
    public org.wordpress.android.util.DisplayUtilsWrapper displayUtils;
    private final kotlin.Lazy viewModel$delegate = null;
    private org.wordpress.android.databinding.SiteCreationIntentsFragmentBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "site_creation_intents_fragment_tag";
    
    public SiteCreationIntentsFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelper$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setUiHelper$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.DisplayUtilsWrapper getDisplayUtils$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setDisplayUtils$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper p0) {
    }
    
    private final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel getViewModel() {
        return null;
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
    
    @java.lang.Override()
    public void onViewStateRestored(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUi(org.wordpress.android.databinding.SiteCreationIntentsFragmentBinding $this$setupUi) {
    }
    
    private final void updateUiState(org.wordpress.android.databinding.SiteCreationIntentsFragmentBinding $this$updateUiState, org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState uiState) {
    }
    
    private final void updateTitleVisibility(org.wordpress.android.databinding.SiteCreationIntentsFragmentBinding $this$updateTitleVisibility, boolean shouldAppBarTitleBeVisible) {
    }
    
    private final void setupViewModel(org.wordpress.android.databinding.SiteCreationIntentsFragmentBinding $this$setupViewModel) {
    }
    
    private final void setupActionListeners(org.wordpress.android.databinding.SiteCreationIntentsFragmentBinding $this$setupActionListeners) {
    }
    
    private final void addSearchTextChangedListener(org.wordpress.android.databinding.SiteCreationIntentsFragmentBinding $this$addSearchTextChangedListener) {
    }
    
    private final void setScrollListener(org.wordpress.android.databinding.SiteCreationIntentsFragmentBinding $this$setScrollListener) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsFragment$Companion;", "", "()V", "TAG", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
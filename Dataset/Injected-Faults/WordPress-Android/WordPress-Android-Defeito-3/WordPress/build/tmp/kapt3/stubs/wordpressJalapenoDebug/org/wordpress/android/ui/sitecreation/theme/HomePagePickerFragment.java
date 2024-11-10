package org.wordpress.android.ui.sitecreation.theme;

import java.lang.System;

/**
 * Implements the Home Page Picker UI
 */
@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0007\u0018\u0000 O2\u00020\u0001:\u0001OB\u0005\u00a2\u0006\u0002\u0010\u0002J&\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0010\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020>H\u0016J\u001a\u0010B\u001a\u00020@2\u0006\u0010C\u001a\u0002082\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u001c\u0010D\u001a\u00020@*\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020GH\u0002J\u0014\u0010I\u001a\u00020@*\u00020E2\u0006\u0010J\u001a\u00020GH\u0002J\f\u0010K\u001a\u00020@*\u00020EH\u0002J\f\u0010L\u001a\u00020@*\u00020EH\u0002J\f\u0010M\u001a\u00020@*\u00020EH\u0002J\f\u0010N\u001a\u00020@*\u00020EH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020 8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010%\u001a\u00020&8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001b\u0010+\u001a\u00020,8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b/\u00100\u001a\u0004\b-\u0010.R\u001e\u00101\u001a\u0002028\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106\u00a8\u0006P"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "displayUtils", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "getDisplayUtils", "()Lorg/wordpress/android/util/DisplayUtilsWrapper;", "setDisplayUtils", "(Lorg/wordpress/android/util/DisplayUtilsWrapper;)V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "recommendedDimensionProvider", "Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignRecommendedDimensionProvider;", "getRecommendedDimensionProvider", "()Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignRecommendedDimensionProvider;", "setRecommendedDimensionProvider", "(Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignRecommendedDimensionProvider;)V", "siteIntent", "", "getSiteIntent", "()Ljava/lang/String;", "siteNameFeatureConfig", "Lorg/wordpress/android/util/config/SiteNameFeatureConfig;", "getSiteNameFeatureConfig", "()Lorg/wordpress/android/util/config/SiteNameFeatureConfig;", "setSiteNameFeatureConfig", "(Lorg/wordpress/android/util/config/SiteNameFeatureConfig;)V", "thumbDimensionProvider", "Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignPickerDimensionProvider;", "getThumbDimensionProvider", "()Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignPickerDimensionProvider;", "setThumbDimensionProvider", "(Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignPickerDimensionProvider;)V", "uiHelper", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelper$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelper$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "", "outState", "onViewCreated", "view", "setContentVisibility", "Lorg/wordpress/android/databinding/HomePagePickerFragmentBinding;", "skeleton", "", "error", "setHeaderVisibility", "visible", "setScrollListener", "setupActionListeners", "setupUi", "setupViewModel", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class HomePagePickerFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.util.DisplayUtilsWrapper displayUtils;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelper;
    @javax.inject.Inject()
    public org.wordpress.android.util.config.SiteNameFeatureConfig siteNameFeatureConfig;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.sitecreation.theme.SiteDesignPickerDimensionProvider thumbDimensionProvider;
    @javax.inject.Inject()
    public org.wordpress.android.ui.sitecreation.theme.SiteDesignRecommendedDimensionProvider recommendedDimensionProvider;
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment.Companion Companion = null;
    private static final java.lang.String ARG_SITE_INTENT = "arg_site_intent";
    
    public HomePagePickerFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.DisplayUtilsWrapper getDisplayUtils() {
        return null;
    }
    
    public final void setDisplayUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelper$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setUiHelper$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.SiteNameFeatureConfig getSiteNameFeatureConfig() {
        return null;
    }
    
    public final void setSiteNameFeatureConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SiteNameFeatureConfig p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.sitecreation.theme.SiteDesignPickerDimensionProvider getThumbDimensionProvider() {
        return null;
    }
    
    public final void setThumbDimensionProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.theme.SiteDesignPickerDimensionProvider p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.sitecreation.theme.SiteDesignRecommendedDimensionProvider getRecommendedDimensionProvider() {
        return null;
    }
    
    public final void setRecommendedDimensionProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.theme.SiteDesignRecommendedDimensionProvider p0) {
    }
    
    private final org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel getViewModel() {
        return null;
    }
    
    private final java.lang.String getSiteIntent() {
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
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void setupUi(org.wordpress.android.databinding.HomePagePickerFragmentBinding $this$setupUi) {
    }
    
    private final void setupViewModel(org.wordpress.android.databinding.HomePagePickerFragmentBinding $this$setupViewModel) {
    }
    
    private final void setHeaderVisibility(org.wordpress.android.databinding.HomePagePickerFragmentBinding $this$setHeaderVisibility, boolean visible) {
    }
    
    private final void setContentVisibility(org.wordpress.android.databinding.HomePagePickerFragmentBinding $this$setContentVisibility, boolean skeleton, boolean error) {
    }
    
    private final void setupActionListeners(org.wordpress.android.databinding.HomePagePickerFragmentBinding $this$setupActionListeners) {
    }
    
    private final void setScrollListener(org.wordpress.android.databinding.HomePagePickerFragmentBinding $this$setScrollListener) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerFragment$Companion;", "", "()V", "ARG_SITE_INTENT", "", "newInstance", "Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerFragment;", "siteIntent", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment newInstance(@org.jetbrains.annotations.Nullable()
        java.lang.String siteIntent) {
            return null;
        }
    }
}
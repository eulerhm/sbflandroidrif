package org.wordpress.android.ui.sitecreation.sitename;

import java.lang.System;

/**
 * Implements the Site Name UI
 */
@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 72\u00020\u0001:\u00017B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010-\u001a\u00020.H\u0016J\u001a\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020&2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\f\u00101\u001a\u00020.*\u00020\u0004H\u0002J\f\u00102\u001a\u00020.*\u00020\u0004H\u0002J\f\u00103\u001a\u00020.*\u00020\u0004H\u0002J\u0014\u00104\u001a\u00020.*\u00020\u00042\u0006\u00105\u001a\u000206H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"\u00a8\u00068"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lorg/wordpress/android/databinding/SiteCreationSiteNameFragmentBinding;", "displayUtils", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "getDisplayUtils$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/util/DisplayUtilsWrapper;", "setDisplayUtils$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/util/DisplayUtilsWrapper;)V", "headerTitleWithIntentColoredBlueIfSpecified", "", "getHeaderTitleWithIntentColoredBlueIfSpecified", "()Ljava/lang/CharSequence;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "getHtmlMessageUtils$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "setHtmlMessageUtils$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/utils/HtmlMessageUtils;)V", "siteIntent", "", "getSiteIntent", "()Ljava/lang/String;", "uiHelper", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelper$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelper$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "setupActionListeners", "setupUi", "setupViewModel", "updateUiState", "uiState", "Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameViewModel$SiteNameUiState;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class SiteCreationSiteNameFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelper;
    @javax.inject.Inject()
    public org.wordpress.android.util.DisplayUtilsWrapper displayUtils;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils;
    private final kotlin.Lazy viewModel$delegate = null;
    private org.wordpress.android.databinding.SiteCreationSiteNameFragmentBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameFragment.Companion Companion = null;
    private static final java.lang.String ARG_SITE_INTENT = "arg_site_intent";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "site_creation_site_name_fragment_tag";
    
    public SiteCreationSiteNameFragment() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.HtmlMessageUtils getHtmlMessageUtils$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setHtmlMessageUtils$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils p0) {
    }
    
    private final org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameViewModel getViewModel() {
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
    
    private final java.lang.String getSiteIntent() {
        return null;
    }
    
    private final java.lang.CharSequence getHeaderTitleWithIntentColoredBlueIfSpecified() {
        return null;
    }
    
    private final void setupUi(org.wordpress.android.databinding.SiteCreationSiteNameFragmentBinding $this$setupUi) {
    }
    
    private final void setupViewModel(org.wordpress.android.databinding.SiteCreationSiteNameFragmentBinding $this$setupViewModel) {
    }
    
    private final void setupActionListeners(org.wordpress.android.databinding.SiteCreationSiteNameFragmentBinding $this$setupActionListeners) {
    }
    
    private final void updateUiState(org.wordpress.android.databinding.SiteCreationSiteNameFragmentBinding $this$updateUiState, org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameViewModel.SiteNameUiState uiState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameFragment$Companion;", "", "()V", "ARG_SITE_INTENT", "", "TAG", "newInstance", "Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameFragment;", "siteIntent", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameFragment newInstance(@org.jetbrains.annotations.Nullable()
        java.lang.String siteIntent) {
            return null;
        }
    }
}
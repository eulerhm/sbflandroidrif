package org.wordpress.android.ui.sitecreation.domains;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 52\u00020\u0001:\u00015B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020 H\u0016J\b\u0010$\u001a\u00020 H\u0014J\u0012\u0010%\u001a\u00020 2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\u0010\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020*H\u0014J\b\u0010+\u001a\u00020 H\u0014J\u0010\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020.H\u0002J\f\u0010/\u001a\u00020 *\u00020\u0004H\u0002J\f\u00100\u001a\u00020 *\u00020\u0004H\u0002J\f\u00101\u001a\u00020 *\u00020\u0004H\u0002J\u0014\u00102\u001a\u00020 *\u00020\u00042\u0006\u00103\u001a\u000204H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8TX\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001a\u00a8\u00066"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsFragment;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationBaseFormFragment;", "()V", "binding", "Lorg/wordpress/android/databinding/SiteCreationDomainsScreenBinding;", "displayUtils", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "getDisplayUtils$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/util/DisplayUtilsWrapper;", "setDisplayUtils$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/util/DisplayUtilsWrapper;)V", "screenTitle", "", "getScreenTitle", "()Ljava/lang/String;", "searchInputWithHeader", "Lorg/wordpress/android/ui/sitecreation/misc/SearchInputWithHeader;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getContentLayout", "", "onAttach", "", "context", "Landroid/content/Context;", "onDestroyView", "onHelp", "onViewStateRestored", "savedInstanceState", "Landroid/os/Bundle;", "setBindingViewStubListener", "parentBinding", "Lorg/wordpress/android/databinding/SiteCreationFormScreenBinding;", "setupContent", "updateTitleVisibility", "visible", "", "initAdapter", "initRecyclerView", "initViewModel", "updateContentUiState", "contentState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class SiteCreationDomainsFragment extends org.wordpress.android.ui.sitecreation.SiteCreationBaseFormFragment {
    private org.wordpress.android.ui.sitecreation.misc.SearchInputWithHeader searchInputWithHeader;
    private final kotlin.Lazy viewModel$delegate = null;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.DisplayUtilsWrapper displayUtils;
    private org.wordpress.android.databinding.SiteCreationDomainsScreenBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "site_creation_domains_fragment_tag";
    
    public SiteCreationDomainsFragment() {
        super();
    }
    
    private final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setUiHelpers$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.DisplayUtilsWrapper getDisplayUtils$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setDisplayUtils$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper p0) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    protected int getContentLayout() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.lang.String getScreenTitle() {
        return null;
    }
    
    @java.lang.Override()
    protected void setBindingViewStubListener(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.databinding.SiteCreationFormScreenBinding parentBinding) {
    }
    
    @java.lang.Override()
    protected void setupContent() {
    }
    
    private final void initRecyclerView(org.wordpress.android.databinding.SiteCreationDomainsScreenBinding $this$initRecyclerView) {
    }
    
    private final void initAdapter(org.wordpress.android.databinding.SiteCreationDomainsScreenBinding $this$initAdapter) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.SiteCreationDomainsScreenBinding $this$initViewModel) {
    }
    
    private final void updateContentUiState(org.wordpress.android.databinding.SiteCreationDomainsScreenBinding $this$updateContentUiState, org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState contentState) {
    }
    
    private final void updateTitleVisibility(boolean visible) {
    }
    
    @java.lang.Override()
    protected void onHelp() {
    }
    
    @java.lang.Override()
    public void onViewStateRestored(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsFragment$Companion;", "", "()V", "TAG", "", "newInstance", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsFragment;", "screenTitle", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsFragment newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String screenTitle) {
            return null;
        }
    }
}
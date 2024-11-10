package org.wordpress.android.ui.accounts.login.jetpack;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 12\u00020\u0001:\u00011B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\u001cH\u0002J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0016J\u0012\u0010#\u001a\u00020\u001c2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u001cH\u0016J\b\u0010\'\u001a\u00020\u001cH\u0016J\u001a\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u0010+\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\fH\u0002J\b\u0010-\u001a\u00020\u001cH\u0002J\f\u0010.\u001a\u00020\u001c*\u00020/H\u0002J\f\u00100\u001a\u00020\u001c*\u00020/H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u00062"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginSiteCheckErrorFragment;", "Landroidx/fragment/app/Fragment;", "()V", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "getHtmlMessageUtils", "()Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "setHtmlMessageUtils", "(Lorg/wordpress/android/ui/utils/HtmlMessageUtils;)V", "loginListener", "Lorg/wordpress/android/login/LoginListener;", "siteAddress", "", "unifiedLoginTracker", "Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker;", "getUnifiedLoginTracker", "()Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker;", "setUnifiedLoginTracker", "(Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker;)V", "viewModel", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginSiteCheckErrorViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "initBackPressHandler", "", "initDagger", "initObservers", "initViewModel", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onViewCreated", "view", "Landroid/view/View;", "showInstructions", "url", "showSignInForResultJetpackOnly", "initClickListeners", "Lorg/wordpress/android/databinding/JetpackLoginEmptyViewBinding;", "initErrorMessageView", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LoginSiteCheckErrorFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.accounts.login.jetpack.LoginSiteCheckErrorFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "LoginSiteCheckErrorFragment";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_SITE_ADDRESS = "SITE-ADDRESS";
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.accounts.UnifiedLoginTracker unifiedLoginTracker;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils;
    private org.wordpress.android.login.LoginListener loginListener;
    private java.lang.String siteAddress;
    private org.wordpress.android.ui.accounts.login.jetpack.LoginSiteCheckErrorViewModel viewModel;
    
    public LoginSiteCheckErrorFragment() {
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
    public final org.wordpress.android.ui.accounts.UnifiedLoginTracker getUnifiedLoginTracker() {
        return null;
    }
    
    public final void setUnifiedLoginTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.HtmlMessageUtils getHtmlMessageUtils() {
        return null;
    }
    
    public final void setHtmlMessageUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils p0) {
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
    
    private final void initDagger() {
    }
    
    private final void initViewModel() {
    }
    
    private final void initErrorMessageView(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$initErrorMessageView) {
    }
    
    private final void initClickListeners(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$initClickListeners) {
    }
    
    private final void initObservers() {
    }
    
    private final void showSignInForResultJetpackOnly() {
    }
    
    private final void showInstructions(java.lang.String url) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void initBackPressHandler() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginSiteCheckErrorFragment$Companion;", "", "()V", "ARG_SITE_ADDRESS", "", "TAG", "newInstance", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginSiteCheckErrorFragment;", "siteAddress", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.accounts.login.jetpack.LoginSiteCheckErrorFragment newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String siteAddress) {
            return null;
        }
    }
}
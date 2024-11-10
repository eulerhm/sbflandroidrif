package org.wordpress.android.ui.accounts.login.jetpack;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 62\u00020\u0001:\u00016B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001aH\u0016J\b\u0010 \u001a\u00020\u001aH\u0016J\u0010\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0016J\u001a\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\u001aH\u0002J\f\u0010,\u001a\u00020\u001a*\u00020-H\u0002J\f\u0010.\u001a\u00020\u001a*\u00020-H\u0002J\f\u0010/\u001a\u00020\u001a*\u00020-H\u0002J\u0016\u00100\u001a\u00020\u001a*\u00020-2\b\u0010\'\u001a\u0004\u0018\u00010#H\u0002J\u0014\u00101\u001a\u00020\u001a*\u00020-2\u0006\u00102\u001a\u00020*H\u0002J\u0014\u00103\u001a\u00020\u001a*\u00020-2\u0006\u00104\u001a\u00020*H\u0002J\u0014\u00105\u001a\u00020\u001a*\u00020-2\u0006\u00104\u001a\u00020*H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u00067"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesFragment;", "Landroidx/fragment/app/Fragment;", "()V", "loginListener", "Lorg/wordpress/android/login/LoginListener;", "meGravatarLoader", "Lorg/wordpress/android/ui/main/utils/MeGravatarLoader;", "getMeGravatarLoader", "()Lorg/wordpress/android/ui/main/utils/MeGravatarLoader;", "setMeGravatarLoader", "(Lorg/wordpress/android/ui/main/utils/MeGravatarLoader;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "initBackPressHandler", "", "initDagger", "onAttach", "context", "Landroid/content/Context;", "onDetach", "onResume", "onSaveInstanceState", "outState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "showInstructions", "url", "", "showSignInForResultJetpackOnly", "initClickListeners", "Lorg/wordpress/android/databinding/JetpackLoginEmptyViewBinding;", "initContentViews", "initObservers", "initViewModel", "loadGravatar", "avatarUrl", "setDisplayName", "value", "setUserName", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LoginNoSitesFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "LoginNoSitesFragment";
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.main.utils.MeGravatarLoader meGravatarLoader;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private org.wordpress.android.login.LoginListener loginListener;
    private org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel viewModel;
    
    public LoginNoSitesFragment() {
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
    public final org.wordpress.android.ui.main.utils.MeGravatarLoader getMeGravatarLoader() {
        return null;
    }
    
    public final void setMeGravatarLoader(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.main.utils.MeGravatarLoader p0) {
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
    
    private final void initDagger() {
    }
    
    private final void initContentViews(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$initContentViews) {
    }
    
    private final void initClickListeners(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$initClickListeners) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$initViewModel, android.os.Bundle savedInstanceState) {
    }
    
    private final void initObservers(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$initObservers) {
    }
    
    private final void loadGravatar(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$loadGravatar, java.lang.String avatarUrl) {
    }
    
    private final void setUserName(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$setUserName, java.lang.String value) {
    }
    
    private final void setDisplayName(org.wordpress.android.databinding.JetpackLoginEmptyViewBinding $this$setDisplayName, java.lang.String value) {
    }
    
    private final void showSignInForResultJetpackOnly() {
    }
    
    private final void showInstructions(java.lang.String url) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesFragment$Companion;", "", "()V", "TAG", "", "newInstance", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesFragment;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesFragment newInstance() {
            return null;
        }
    }
}
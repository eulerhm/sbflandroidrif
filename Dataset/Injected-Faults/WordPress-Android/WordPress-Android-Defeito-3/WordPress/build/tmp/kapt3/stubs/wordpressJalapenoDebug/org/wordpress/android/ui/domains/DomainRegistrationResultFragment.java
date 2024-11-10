package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u001a\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u000f\u0010\u0018\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001bH\u0002J\u001c\u0010\u001d\u001a\u00020\f*\u00020\u001e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationResultFragment;", "Landroidx/fragment/app/Fragment;", "()V", "mainViewModel", "Lorg/wordpress/android/ui/domains/DomainRegistrationMainViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "finishRegistration", "", "domainName", "", "email", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "requireAppCompatActivity", "Landroidx/appcompat/app/AppCompatActivity;", "setupObservers", "setupToolbar", "()Lkotlin/Unit;", "setupWindow", "Landroid/view/Window;", "kotlin.jvm.PlatformType", "setupViews", "Lorg/wordpress/android/databinding/DomainRegistrationResultFragmentBinding;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainRegistrationResultFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.domains.DomainRegistrationMainViewModel mainViewModel;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.domains.DomainRegistrationResultFragment.Companion Companion = null;
    private static final java.lang.String EXTRA_REGISTERED_DOMAIN_NAME = "extra_registered_domain_name";
    private static final java.lang.String EXTRA_REGISTERED_DOMAIN_EMAIL = "extra_registered_domain_email";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "DOMAIN_REGISTRATION_RESULT_FRAGMENT";
    
    public DomainRegistrationResultFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final android.view.Window setupWindow() {
        return null;
    }
    
    private final kotlin.Unit setupToolbar() {
        return null;
    }
    
    private final void setupViews(org.wordpress.android.databinding.DomainRegistrationResultFragmentBinding $this$setupViews, java.lang.String domainName, java.lang.String email) {
    }
    
    private final void setupObservers(java.lang.String domainName, java.lang.String email) {
    }
    
    private final void finishRegistration(java.lang.String domainName, java.lang.String email) {
    }
    
    private final androidx.appcompat.app.AppCompatActivity requireAppCompatActivity() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationResultFragment$Companion;", "", "()V", "EXTRA_REGISTERED_DOMAIN_EMAIL", "", "EXTRA_REGISTERED_DOMAIN_NAME", "TAG", "newInstance", "Lorg/wordpress/android/ui/domains/DomainRegistrationResultFragment;", "domainName", "email", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationResultFragment newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String domainName, @org.jetbrains.annotations.Nullable()
        java.lang.String email) {
            return null;
        }
    }
}
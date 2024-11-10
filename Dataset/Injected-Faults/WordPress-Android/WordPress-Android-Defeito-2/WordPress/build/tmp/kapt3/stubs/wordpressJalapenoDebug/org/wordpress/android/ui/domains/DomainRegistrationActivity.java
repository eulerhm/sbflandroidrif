package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 52\u00020\u00012\u00020\u0002:\u000256B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u0013H\u0002J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"2\u0006\u0010\'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010*\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010+\u001a\u00020\u0013H\u0002J(\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020\u001a2\f\u00101\u001a\b\u0012\u0004\u0012\u00020302H\u0002J\f\u00104\u001a\u00020\u0013*\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u00067"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "Lorg/wordpress/android/ui/ScrollableViewInitializedListener;", "()V", "binding", "Lorg/wordpress/android/databinding/DomainRegistrationActivityBinding;", "openCheckout", "Landroidx/activity/result/ActivityResultLauncher;", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewActivity$OpenCheckout$CheckoutDetails;", "kotlin.jvm.PlatformType", "viewModel", "Lorg/wordpress/android/ui/domains/DomainRegistrationMainViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory$org_wordpress_android_wordpressJalapenoDebug", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory$org_wordpress_android_wordpressJalapenoDebug", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "finishDomainRegistration", "", "event", "Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onScrollableViewInitialized", "containerId", "", "openDomainRegistrationCheckoutWebView", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "details", "Lorg/wordpress/android/ui/domains/DomainProductDetails;", "setupObservers", "setupViewModel", "domainRegistrationPurpose", "Lorg/wordpress/android/ui/domains/DomainRegistrationActivity$DomainRegistrationPurpose;", "showDomainRegistrationDetails", "showDomainRegistrationResult", "showDomainSuggestions", "showFragment", "Landroidx/fragment/app/FragmentTransaction;", "tag", "", "isRootFragment", "factory", "Lkotlin/Function0;", "Landroidx/fragment/app/Fragment;", "setupToolbar", "Companion", "DomainRegistrationPurpose", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainRegistrationActivity extends org.wordpress.android.ui.LocaleAwareActivity implements org.wordpress.android.ui.ScrollableViewInitializedListener {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.domains.DomainRegistrationActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RESULT_REGISTERED_DOMAIN_EMAIL = "RESULT_REGISTERED_DOMAIN_EMAIL";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DOMAIN_REGISTRATION_PURPOSE_KEY = "DOMAIN_REGISTRATION_PURPOSE_KEY";
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.domains.DomainRegistrationMainViewModel viewModel;
    private org.wordpress.android.databinding.DomainRegistrationActivityBinding binding;
    private final androidx.activity.result.ActivityResultLauncher<org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewActivity.OpenCheckout.CheckoutDetails> openCheckout = null;
    
    public DomainRegistrationActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setViewModelFactory$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar(org.wordpress.android.databinding.DomainRegistrationActivityBinding $this$setupToolbar) {
    }
    
    private final void setupViewModel(org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose domainRegistrationPurpose) {
    }
    
    private final void setupObservers() {
    }
    
    private final void showDomainSuggestions() {
    }
    
    private final void openDomainRegistrationCheckoutWebView(org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.ui.domains.DomainProductDetails details) {
    }
    
    private final void showDomainRegistrationDetails(org.wordpress.android.ui.domains.DomainProductDetails details) {
    }
    
    private final void showDomainRegistrationResult(org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event) {
    }
    
    private final void finishDomainRegistration(org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event) {
    }
    
    private final androidx.fragment.app.FragmentTransaction showFragment(java.lang.String tag, boolean isRootFragment, kotlin.jvm.functions.Function0<? extends androidx.fragment.app.Fragment> factory) {
        return null;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onScrollableViewInitialized(int containerId) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationActivity$DomainRegistrationPurpose;", "", "(Ljava/lang/String;I)V", "AUTOMATED_TRANSFER", "CTA_DOMAIN_CREDIT_REDEMPTION", "DOMAIN_PURCHASE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum DomainRegistrationPurpose {
        /*public static final*/ AUTOMATED_TRANSFER /* = new AUTOMATED_TRANSFER() */,
        /*public static final*/ CTA_DOMAIN_CREDIT_REDEMPTION /* = new CTA_DOMAIN_CREDIT_REDEMPTION() */,
        /*public static final*/ DOMAIN_PURCHASE /* = new DOMAIN_PURCHASE() */;
        
        DomainRegistrationPurpose() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationActivity$Companion;", "", "()V", "DOMAIN_REGISTRATION_PURPOSE_KEY", "", "RESULT_REGISTERED_DOMAIN_EMAIL", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
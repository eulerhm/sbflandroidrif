package org.wordpress.android.ui.sitecreation;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 S2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\u0001SB\u0005\u00a2\u0006\u0002\u0010\tJ\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u000200H\u0016J\u0012\u00102\u001a\u0002002\b\u00103\u001a\u0004\u0018\u000104H\u0014J\u0010\u00105\u001a\u0002002\u0006\u00106\u001a\u00020,H\u0016J\u0010\u00107\u001a\u0002002\u0006\u00108\u001a\u000209H\u0016J\u0012\u0010:\u001a\u0002002\b\u0010;\u001a\u0004\u0018\u00010,H\u0016J\u0010\u0010<\u001a\u0002002\u0006\u0010=\u001a\u00020,H\u0016J\u0010\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010B\u001a\u0002002\u0006\u0010=\u001a\u00020,H\u0016J\u0010\u0010C\u001a\u0002002\u0006\u0010D\u001a\u000204H\u0014J\b\u0010E\u001a\u000200H\u0016J\u0010\u0010F\u001a\u0002002\u0006\u0010G\u001a\u00020,H\u0016J\u0010\u0010H\u001a\u0002002\u0006\u0010I\u001a\u00020JH\u0016J\u001c\u0010K\u001a\u0002002\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020N0MH\u0002J\u0018\u0010O\u001a\u0002002\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020,H\u0002R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001f\u001a\u00020 8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010%\u001a\u00020&8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*\u00a8\u0006T"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/SiteCreationActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "Lorg/wordpress/android/ui/sitecreation/verticals/IntentsScreenListener;", "Lorg/wordpress/android/ui/sitecreation/sitename/SiteNameScreenListener;", "Lorg/wordpress/android/ui/sitecreation/domains/DomainsScreenListener;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewScreenListener;", "Lorg/wordpress/android/ui/sitecreation/misc/OnHelpClickedListener;", "Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogPositiveClickInterface;", "Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogNegativeClickInterface;", "()V", "hppViewModel", "Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel;", "getHppViewModel", "()Lorg/wordpress/android/ui/sitecreation/theme/HomePagePickerViewModel;", "hppViewModel$delegate", "Lkotlin/Lazy;", "mainViewModel", "Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM;", "getMainViewModel", "()Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM;", "mainViewModel$delegate", "siteCreationIntentsViewModel", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel;", "getSiteCreationIntentsViewModel", "()Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel;", "siteCreationIntentsViewModel$delegate", "siteCreationSiteNameViewModel", "Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameViewModel;", "getSiteCreationSiteNameViewModel", "()Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameViewModel;", "siteCreationSiteNameViewModel$delegate", "siteNameFeatureConfig", "Lorg/wordpress/android/util/config/SiteNameFeatureConfig;", "getSiteNameFeatureConfig$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/util/config/SiteNameFeatureConfig;", "setSiteNameFeatureConfig$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/util/config/SiteNameFeatureConfig;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "getScreenTitle", "", "step", "Lorg/wordpress/android/ui/sitecreation/SiteCreationStep;", "observeVMState", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDomainSelected", "domain", "onHelpClicked", "origin", "Lorg/wordpress/android/ui/accounts/HelpActivity$Origin;", "onIntentSelected", "intent", "onNegativeClicked", "instanceTag", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPositiveClicked", "onSaveInstanceState", "outState", "onSiteCreationCompleted", "onSiteNameEntered", "siteName", "onSitePreviewScreenDismissed", "createSiteState", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState;", "showStep", "target", "Lorg/wordpress/android/util/wizard/WizardNavigationTarget;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationState;", "slideInFragment", "fragment", "Landroidx/fragment/app/Fragment;", "tag", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class SiteCreationActivity extends org.wordpress.android.ui.LocaleAwareActivity implements org.wordpress.android.ui.sitecreation.verticals.IntentsScreenListener, org.wordpress.android.ui.sitecreation.sitename.SiteNameScreenListener, org.wordpress.android.ui.sitecreation.domains.DomainsScreenListener, org.wordpress.android.ui.sitecreation.previews.SitePreviewScreenListener, org.wordpress.android.ui.sitecreation.misc.OnHelpClickedListener, org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogPositiveClickInterface, org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogNegativeClickInterface {
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.config.SiteNameFeatureConfig siteNameFeatureConfig;
    private final kotlin.Lazy mainViewModel$delegate = null;
    private final kotlin.Lazy hppViewModel$delegate = null;
    private final kotlin.Lazy siteCreationIntentsViewModel$delegate = null;
    private final kotlin.Lazy siteCreationSiteNameViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.sitecreation.SiteCreationActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_CREATE_SITE_SOURCE = "ARG_CREATE_SITE_SOURCE";
    
    public SiteCreationActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setUiHelpers$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.SiteNameFeatureConfig getSiteNameFeatureConfig$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setSiteNameFeatureConfig$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SiteNameFeatureConfig p0) {
    }
    
    private final org.wordpress.android.ui.sitecreation.SiteCreationMainVM getMainViewModel() {
        return null;
    }
    
    private final org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel getHppViewModel() {
        return null;
    }
    
    private final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel getSiteCreationIntentsViewModel() {
        return null;
    }
    
    private final org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameViewModel getSiteCreationSiteNameViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void observeVMState() {
    }
    
    @java.lang.Override()
    public void onIntentSelected(@org.jetbrains.annotations.Nullable()
    java.lang.String intent) {
    }
    
    @java.lang.Override()
    public void onSiteNameEntered(@org.jetbrains.annotations.NotNull()
    java.lang.String siteName) {
    }
    
    @java.lang.Override()
    public void onDomainSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String domain) {
    }
    
    @java.lang.Override()
    public void onSiteCreationCompleted() {
    }
    
    @java.lang.Override()
    public void onSitePreviewScreenDismissed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState createSiteState) {
    }
    
    @java.lang.Override()
    public void onHelpClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.HelpActivity.Origin origin) {
    }
    
    private final void showStep(org.wordpress.android.util.wizard.WizardNavigationTarget<org.wordpress.android.ui.sitecreation.SiteCreationStep, org.wordpress.android.ui.sitecreation.SiteCreationState> target) {
    }
    
    private final java.lang.String getScreenTitle(org.wordpress.android.ui.sitecreation.SiteCreationStep step) {
        return null;
    }
    
    private final void slideInFragment(androidx.fragment.app.Fragment fragment, java.lang.String tag) {
    }
    
    @java.lang.Override()
    public void onPositiveClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    @java.lang.Override()
    public void onNegativeClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/SiteCreationActivity$Companion;", "", "()V", "ARG_CREATE_SITE_SOURCE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
package org.wordpress.android.ui.sitecreation;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001YB=\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\u0010\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u0006H\u0002J\u0010\u00107\u001a\u00020\u00142\u0006\u00108\u001a\u00020\u001fH\u0002J\u0006\u00109\u001a\u00020\u0014J\b\u0010:\u001a\u00020\u0014H\u0014J\u000e\u0010;\u001a\u00020\u00142\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020=J\u000e\u0010@\u001a\u00020\u00142\u0006\u0010?\u001a\u00020=J\u0006\u0010A\u001a\u00020\u0014J\u000e\u0010B\u001a\u00020\u00142\u0006\u0010C\u001a\u00020=J\u0010\u0010D\u001a\u00020\u00142\b\u0010E\u001a\u0004\u0018\u00010=J\u0006\u0010F\u001a\u00020\u0014J\u000e\u0010G\u001a\u00020\u00142\u0006\u0010H\u001a\u00020=J\u0006\u0010I\u001a\u00020\u0014J\u000e\u0010J\u001a\u00020\u00142\u0006\u0010K\u001a\u00020\u0017J\u000e\u0010L\u001a\u00020\u00142\u0006\u0010M\u001a\u00020NJ\u000e\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u0006J\u0018\u0010R\u001a\u00020\u00142\b\u0010S\u001a\u0004\u0018\u00010T2\u0006\u0010U\u001a\u00020VJ\u000e\u0010W\u001a\u00020\u00142\u0006\u0010X\u001a\u00020TR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R1\u0010 \u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020#0\"j\u0002`$0!8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\'\u0010(\u001a\u0004\b%\u0010&R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00101\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020#X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006Z"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM;", "Landroidx/lifecycle/ViewModel;", "tracker", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "wizardManager", "Lorg/wordpress/android/util/wizard/WizardManager;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationStep;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "fetchHomePageLayoutsUseCase", "Lorg/wordpress/android/ui/sitecreation/usecases/FetchHomePageLayoutsUseCase;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "(Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;Lorg/wordpress/android/util/wizard/WizardManager;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/sitecreation/usecases/FetchHomePageLayoutsUseCase;Lorg/wordpress/android/util/image/ImageManager;)V", "_dialogAction", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/viewmodel/helpers/DialogHolder;", "_exitFlowObservable", "", "_onBackPressedObservable", "_wizardFinishedObservable", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState;", "dialogActionObservable", "Landroidx/lifecycle/LiveData;", "getDialogActionObservable", "()Landroidx/lifecycle/LiveData;", "exitFlowObservable", "getExitFlowObservable", "isStarted", "", "navigationTargetObservable", "Lorg/wordpress/android/viewmodel/SingleEventObservable;", "Lorg/wordpress/android/util/wizard/WizardNavigationTarget;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationState;", "Lorg/wordpress/android/ui/sitecreation/NavigationTarget;", "getNavigationTargetObservable", "()Lorg/wordpress/android/viewmodel/SingleEventObservable;", "navigationTargetObservable$delegate", "Lkotlin/Lazy;", "onBackPressedObservable", "getOnBackPressedObservable", "preloadingJob", "Lkotlinx/coroutines/Job;", "getPreloadingJob$org_wordpress_android_wordpressJalapenoDebug", "()Lkotlinx/coroutines/Job;", "setPreloadingJob$org_wordpress_android_wordpressJalapenoDebug", "(Lkotlinx/coroutines/Job;)V", "siteCreationCompleted", "siteCreationState", "wizardFinishedObservable", "getWizardFinishedObservable", "clearOldSiteCreationState", "wizardStep", "exitFlow", "forceExit", "onBackPressed", "onCleared", "onDomainsScreenFinished", "domain", "", "onNegativeDialogButtonClicked", "instanceTag", "onPositiveDialogButtonClicked", "onSiteCreationCompleted", "onSiteDesignSelected", "siteDesign", "onSiteIntentSelected", "intent", "onSiteIntentSkipped", "onSiteNameEntered", "siteName", "onSiteNameSkipped", "onSitePreviewScreenFinished", "createSiteState", "preloadThumbnails", "context", "Landroid/content/Context;", "screenTitleForWizardStep", "Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle;", "step", "start", "savedInstanceState", "Landroid/os/Bundle;", "siteCreationSource", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationSource;", "writeToBundle", "outState", "SiteCreationScreenTitle", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteCreationMainVM extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker = null;
    private final org.wordpress.android.util.wizard.WizardManager<org.wordpress.android.ui.sitecreation.SiteCreationStep> wizardManager = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.sitecreation.usecases.FetchHomePageLayoutsUseCase fetchHomePageLayoutsUseCase = null;
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private boolean isStarted = false;
    private boolean siteCreationCompleted = false;
    private org.wordpress.android.ui.sitecreation.SiteCreationState siteCreationState;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job preloadingJob;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy navigationTargetObservable$delegate = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.helpers.DialogHolder> _dialogAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.DialogHolder> dialogActionObservable = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> _wizardFinishedObservable = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> wizardFinishedObservable = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _exitFlowObservable = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> exitFlowObservable = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onBackPressedObservable = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onBackPressedObservable = null;
    
    @javax.inject.Inject()
    public SiteCreationMainVM(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.wizard.WizardManager<org.wordpress.android.ui.sitecreation.SiteCreationStep> wizardManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.usecases.FetchHomePageLayoutsUseCase fetchHomePageLayoutsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager) {
        super();
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlinx.coroutines.Job getPreloadingJob$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setPreloadingJob$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.Nullable()
    kotlinx.coroutines.Job p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.SingleEventObservable<org.wordpress.android.util.wizard.WizardNavigationTarget<org.wordpress.android.ui.sitecreation.SiteCreationStep, org.wordpress.android.ui.sitecreation.SiteCreationState>> getNavigationTargetObservable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.DialogHolder> getDialogActionObservable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> getWizardFinishedObservable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getExitFlowObservable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnBackPressedObservable() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationSource siteCreationSource) {
    }
    
    public final void preloadThumbnails(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void writeToBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void onSiteIntentSelected(@org.jetbrains.annotations.Nullable()
    java.lang.String intent) {
    }
    
    public final void onSiteIntentSkipped() {
    }
    
    public final void onSiteNameSkipped() {
    }
    
    public final void onSiteNameEntered(@org.jetbrains.annotations.NotNull()
    java.lang.String siteName) {
    }
    
    public final void onSiteDesignSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String siteDesign) {
    }
    
    public final void onBackPressed() {
    }
    
    private final void clearOldSiteCreationState(org.wordpress.android.ui.sitecreation.SiteCreationStep wizardStep) {
    }
    
    public final void onDomainsScreenFinished(@org.jetbrains.annotations.NotNull()
    java.lang.String domain) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.sitecreation.SiteCreationMainVM.SiteCreationScreenTitle screenTitleForWizardStep(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.SiteCreationStep step) {
        return null;
    }
    
    public final void onSiteCreationCompleted() {
    }
    
    /**
     * Exits the flow and tracks an event when the user force-exits the "site creation in progress" before it completes.
     */
    private final void exitFlow(boolean forceExit) {
    }
    
    public final void onSitePreviewScreenFinished(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState createSiteState) {
    }
    
    public final void onPositiveDialogButtonClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    public final void onNegativeDialogButtonClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle;", "", "()V", "ScreenTitleEmpty", "ScreenTitleGeneral", "ScreenTitleStepCount", "Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle$ScreenTitleStepCount;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle$ScreenTitleGeneral;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle$ScreenTitleEmpty;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SiteCreationScreenTitle {
        
        private SiteCreationScreenTitle() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle$ScreenTitleStepCount;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle;", "resId", "", "stepsCount", "stepPosition", "(III)V", "getResId", "()I", "getStepPosition", "getStepsCount", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ScreenTitleStepCount extends org.wordpress.android.ui.sitecreation.SiteCreationMainVM.SiteCreationScreenTitle {
            private final int resId = 0;
            private final int stepsCount = 0;
            private final int stepPosition = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.SiteCreationMainVM.SiteCreationScreenTitle.ScreenTitleStepCount copy(@androidx.annotation.StringRes()
            int resId, int stepsCount, int stepPosition) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public ScreenTitleStepCount(@androidx.annotation.StringRes()
            int resId, int stepsCount, int stepPosition) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getResId() {
                return 0;
            }
            
            public final int component2() {
                return 0;
            }
            
            public final int getStepsCount() {
                return 0;
            }
            
            public final int component3() {
                return 0;
            }
            
            public final int getStepPosition() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle$ScreenTitleGeneral;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle;", "resId", "", "(I)V", "getResId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ScreenTitleGeneral extends org.wordpress.android.ui.sitecreation.SiteCreationMainVM.SiteCreationScreenTitle {
            private final int resId = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.SiteCreationMainVM.SiteCreationScreenTitle.ScreenTitleGeneral copy(@androidx.annotation.StringRes()
            int resId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public ScreenTitleGeneral(@androidx.annotation.StringRes()
            int resId) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getResId() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle$ScreenTitleEmpty;", "Lorg/wordpress/android/ui/sitecreation/SiteCreationMainVM$SiteCreationScreenTitle;", "()V", "screenTitle", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ScreenTitleEmpty extends org.wordpress.android.ui.sitecreation.SiteCreationMainVM.SiteCreationScreenTitle {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.sitecreation.SiteCreationMainVM.SiteCreationScreenTitle.ScreenTitleEmpty INSTANCE = null;
            @org.jetbrains.annotations.NotNull()
            public static final java.lang.String screenTitle = "";
            
            private ScreenTitleEmpty() {
                super();
            }
        }
    }
}
package org.wordpress.android.ui.bloggingprompts.onboarding;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 (2\u00020\u0001:\u0002()B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0018H\u0002J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0018H\u0002J\b\u0010%\u001a\u00020\u0018H\u0002J\b\u0010&\u001a\u00020\u0018H\u0002J\b\u0010\'\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingDialogFragment;", "Lorg/wordpress/android/ui/featureintroduction/FeatureIntroductionDialogFragment;", "()V", "dialogType", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingDialogFragment$DialogType;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "sitePickerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "snackbarSequencer", "Lorg/wordpress/android/util/SnackbarSequencer;", "getSnackbarSequencer", "()Lorg/wordpress/android/util/SnackbarSequencer;", "setSnackbarSequencer", "(Lorg/wordpress/android/util/SnackbarSequencer;)V", "viewModel", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingViewModel;", "onAttach", "", "context", "Landroid/content/Context;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupActionObserver", "setupContent", "readyState", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingUiState$Ready;", "setupHeaderIcon", "setupHeaderTitle", "setupSnackbarObserver", "setupUiStateObserver", "Companion", "DialogType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BloggingPromptsOnboardingDialogFragment extends org.wordpress.android.ui.featureintroduction.FeatureIntroductionDialogFragment {
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.util.SnackbarSequencer snackbarSequencer;
    private org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingViewModel viewModel;
    private org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.DialogType dialogType;
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> sitePickerLauncher = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "BLOGGING_PROMPTS_ONBOARDING_DIALOG_FRAGMENT";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DIALOG_TYPE = "KEY_DIALOG_TYPE";
    
    public BloggingPromptsOnboardingDialogFragment() {
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
    public final org.wordpress.android.util.SnackbarSequencer getSnackbarSequencer() {
        return null;
    }
    
    public final void setSnackbarSequencer(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarSequencer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment newInstance(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.DialogType type) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void setupHeaderTitle() {
    }
    
    private final void setupHeaderIcon() {
    }
    
    private final void setupContent(org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingUiState.Ready readyState) {
    }
    
    private final void setupUiStateObserver() {
    }
    
    private final void setupActionObserver() {
    }
    
    private final void setupSnackbarObserver() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingDialogFragment$DialogType;", "", "(Ljava/lang/String;I)V", "ONBOARDING", "INFORMATION", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum DialogType {
        /*public static final*/ ONBOARDING /* = new ONBOARDING() */,
        /*public static final*/ INFORMATION /* = new INFORMATION() */;
        
        DialogType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingDialogFragment$Companion;", "", "()V", "KEY_DIALOG_TYPE", "", "TAG", "newInstance", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingDialogFragment;", "type", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingDialogFragment$DialogType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.DialogType type) {
            return null;
        }
    }
}
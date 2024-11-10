package org.wordpress.android.ui.bloggingprompts.onboarding;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.featureintroduction.FeatureIntroductionDialogFragment_MembersInjector;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.image.ImageManager;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BloggingPromptsOnboardingDialogFragment_MembersInjector implements MembersInjector<BloggingPromptsOnboardingDialogFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  public BloggingPromptsOnboardingDialogFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
  }

  public static MembersInjector<BloggingPromptsOnboardingDialogFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    return new BloggingPromptsOnboardingDialogFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, imageManagerProvider, snackbarSequencerProvider);
  }

  @Override
  public void injectMembers(BloggingPromptsOnboardingDialogFragment instance) {
    FeatureIntroductionDialogFragment_MembersInjector.injectViewModelFactory(instance, viewModelFactoryProvider.get());
    FeatureIntroductionDialogFragment_MembersInjector.injectUiHelpers(instance, uiHelpersProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.imageManager")
  public static void injectImageManager(BloggingPromptsOnboardingDialogFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.snackbarSequencer")
  public static void injectSnackbarSequencer(BloggingPromptsOnboardingDialogFragment instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }
}

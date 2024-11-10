package org.wordpress.android.ui.comments.unified;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.SnackbarSequencer;

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
public final class UnifiedCommentsEditFragment_MembersInjector implements MembersInjector<UnifiedCommentsEditFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  public UnifiedCommentsEditFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
  }

  public static MembersInjector<UnifiedCommentsEditFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    return new UnifiedCommentsEditFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, snackbarSequencerProvider);
  }

  @Override
  public void injectMembers(UnifiedCommentsEditFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentsEditFragment.viewModelFactory")
  public static void injectViewModelFactory(UnifiedCommentsEditFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentsEditFragment.uiHelpers")
  public static void injectUiHelpers(UnifiedCommentsEditFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentsEditFragment.snackbarSequencer")
  public static void injectSnackbarSequencer(UnifiedCommentsEditFragment instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }
}

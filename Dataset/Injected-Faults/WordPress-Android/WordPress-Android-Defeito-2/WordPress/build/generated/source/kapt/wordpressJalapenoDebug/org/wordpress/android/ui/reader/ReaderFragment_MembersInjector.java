package org.wordpress.android.ui.reader;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.QuickStartUtilsWrapper;
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
public final class ReaderFragment_MembersInjector implements MembersInjector<ReaderFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  public ReaderFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.quickStartUtilsWrapperProvider = quickStartUtilsWrapperProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
  }

  public static MembersInjector<ReaderFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    return new ReaderFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, quickStartUtilsWrapperProvider, snackbarSequencerProvider);
  }

  @Override
  public void injectMembers(ReaderFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectQuickStartUtilsWrapper(instance, quickStartUtilsWrapperProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderFragment.viewModelFactory")
  public static void injectViewModelFactory(ReaderFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderFragment.uiHelpers")
  public static void injectUiHelpers(ReaderFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderFragment.quickStartUtilsWrapper")
  public static void injectQuickStartUtilsWrapper(ReaderFragment instance,
      QuickStartUtilsWrapper quickStartUtilsWrapper) {
    instance.quickStartUtilsWrapper = quickStartUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderFragment.snackbarSequencer")
  public static void injectSnackbarSequencer(ReaderFragment instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }
}

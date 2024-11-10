package org.wordpress.android.ui.reader.discover.interests;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class ReaderInterestsFragment_MembersInjector implements MembersInjector<ReaderInterestsFragment> {
  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public ReaderInterestsFragment_MembersInjector(Provider<UiHelpers> uiHelpersProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<ReaderInterestsFragment> create(
      Provider<UiHelpers> uiHelpersProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new ReaderInterestsFragment_MembersInjector(uiHelpersProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(ReaderInterestsFragment instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.discover.interests.ReaderInterestsFragment.uiHelpers")
  public static void injectUiHelpers(ReaderInterestsFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.discover.interests.ReaderInterestsFragment.viewModelFactory")
  public static void injectViewModelFactory(ReaderInterestsFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}

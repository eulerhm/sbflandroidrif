package org.wordpress.android.ui.posts;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
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
public final class PrepublishingCategoriesFragment_MembersInjector implements MembersInjector<PrepublishingCategoriesFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public PrepublishingCategoriesFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<Dispatcher> dispatcherProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  public static MembersInjector<PrepublishingCategoriesFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<Dispatcher> dispatcherProvider) {
    return new PrepublishingCategoriesFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, dispatcherProvider);
  }

  @Override
  public void injectMembers(PrepublishingCategoriesFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectDispatcher(instance, dispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingCategoriesFragment.viewModelFactory")
  public static void injectViewModelFactory(PrepublishingCategoriesFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingCategoriesFragment.uiHelpers")
  public static void injectUiHelpers(PrepublishingCategoriesFragment instance,
      UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingCategoriesFragment.dispatcher")
  public static void injectDispatcher(PrepublishingCategoriesFragment instance,
      Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }
}

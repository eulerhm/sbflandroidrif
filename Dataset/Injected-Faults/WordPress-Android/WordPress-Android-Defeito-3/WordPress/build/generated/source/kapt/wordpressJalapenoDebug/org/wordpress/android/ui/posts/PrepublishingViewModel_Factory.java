package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;

@ScopeMetadata
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
public final class PrepublishingViewModel_Factory implements Factory<PrepublishingViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  public PrepublishingViewModel_Factory(Provider<Dispatcher> dispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public PrepublishingViewModel get() {
    return newInstance(dispatcherProvider.get());
  }

  public static PrepublishingViewModel_Factory create(Provider<Dispatcher> dispatcherProvider) {
    return new PrepublishingViewModel_Factory(dispatcherProvider);
  }

  public static PrepublishingViewModel newInstance(Dispatcher dispatcher) {
    return new PrepublishingViewModel(dispatcher);
  }
}

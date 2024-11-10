package org.wordpress.android.ui.mysite;

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
public final class SelectedSiteSource_Factory implements Factory<SelectedSiteSource> {
  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public SelectedSiteSource_Factory(Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<Dispatcher> dispatcherProvider) {
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public SelectedSiteSource get() {
    return newInstance(selectedSiteRepositoryProvider.get(), dispatcherProvider.get());
  }

  public static SelectedSiteSource_Factory create(
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<Dispatcher> dispatcherProvider) {
    return new SelectedSiteSource_Factory(selectedSiteRepositoryProvider, dispatcherProvider);
  }

  public static SelectedSiteSource newInstance(SelectedSiteRepository selectedSiteRepository,
      Dispatcher dispatcher) {
    return new SelectedSiteSource(selectedSiteRepository, dispatcher);
  }
}

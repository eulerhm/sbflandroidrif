package org.wordpress.android.ui.mysite.dynamiccards;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.DynamicCardStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;

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
public final class DynamicCardsSource_Factory implements Factory<DynamicCardsSource> {
  private final Provider<DynamicCardStore> dynamicCardStoreProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public DynamicCardsSource_Factory(Provider<DynamicCardStore> dynamicCardStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.dynamicCardStoreProvider = dynamicCardStoreProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  @Override
  public DynamicCardsSource get() {
    return newInstance(dynamicCardStoreProvider.get(), selectedSiteRepositoryProvider.get());
  }

  public static DynamicCardsSource_Factory create(
      Provider<DynamicCardStore> dynamicCardStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new DynamicCardsSource_Factory(dynamicCardStoreProvider, selectedSiteRepositoryProvider);
  }

  public static DynamicCardsSource newInstance(DynamicCardStore dynamicCardStore,
      SelectedSiteRepository selectedSiteRepository) {
    return new DynamicCardsSource(dynamicCardStore, selectedSiteRepository);
  }
}

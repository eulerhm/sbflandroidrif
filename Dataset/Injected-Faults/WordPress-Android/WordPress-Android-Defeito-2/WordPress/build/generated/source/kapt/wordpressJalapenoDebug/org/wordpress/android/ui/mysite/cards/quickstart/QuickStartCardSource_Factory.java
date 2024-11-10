package org.wordpress.android.ui.mysite.cards.quickstart;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.util.QuickStartUtilsWrapper;

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
public final class QuickStartCardSource_Factory implements Factory<QuickStartCardSource> {
  private final Provider<QuickStartRepository> quickStartRepositoryProvider;

  private final Provider<QuickStartStore> quickStartStoreProvider;

  private final Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public QuickStartCardSource_Factory(Provider<QuickStartRepository> quickStartRepositoryProvider,
      Provider<QuickStartStore> quickStartStoreProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.quickStartRepositoryProvider = quickStartRepositoryProvider;
    this.quickStartStoreProvider = quickStartStoreProvider;
    this.quickStartUtilsWrapperProvider = quickStartUtilsWrapperProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  @Override
  public QuickStartCardSource get() {
    return newInstance(quickStartRepositoryProvider.get(), quickStartStoreProvider.get(), quickStartUtilsWrapperProvider.get(), selectedSiteRepositoryProvider.get());
  }

  public static QuickStartCardSource_Factory create(
      Provider<QuickStartRepository> quickStartRepositoryProvider,
      Provider<QuickStartStore> quickStartStoreProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new QuickStartCardSource_Factory(quickStartRepositoryProvider, quickStartStoreProvider, quickStartUtilsWrapperProvider, selectedSiteRepositoryProvider);
  }

  public static QuickStartCardSource newInstance(QuickStartRepository quickStartRepository,
      QuickStartStore quickStartStore, QuickStartUtilsWrapper quickStartUtilsWrapper,
      SelectedSiteRepository selectedSiteRepository) {
    return new QuickStartCardSource(quickStartRepository, quickStartStore, quickStartUtilsWrapper, selectedSiteRepository);
  }
}

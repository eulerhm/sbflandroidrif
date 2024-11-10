package org.wordpress.android.ui.prefs.homepage;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PageStore;

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
public final class HomepageSettingsDataLoader_Factory implements Factory<HomepageSettingsDataLoader> {
  private final Provider<PageStore> pageStoreProvider;

  public HomepageSettingsDataLoader_Factory(Provider<PageStore> pageStoreProvider) {
    this.pageStoreProvider = pageStoreProvider;
  }

  @Override
  public HomepageSettingsDataLoader get() {
    return newInstance(pageStoreProvider.get());
  }

  public static HomepageSettingsDataLoader_Factory create(Provider<PageStore> pageStoreProvider) {
    return new HomepageSettingsDataLoader_Factory(pageStoreProvider);
  }

  public static HomepageSettingsDataLoader newInstance(PageStore pageStore) {
    return new HomepageSettingsDataLoader(pageStore);
  }
}

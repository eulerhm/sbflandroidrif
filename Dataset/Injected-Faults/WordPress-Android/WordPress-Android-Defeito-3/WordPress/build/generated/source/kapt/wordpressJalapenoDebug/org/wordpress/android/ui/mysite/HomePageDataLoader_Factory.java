package org.wordpress.android.ui.mysite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PageStore;

@ScopeMetadata("javax.inject.Singleton")
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
public final class HomePageDataLoader_Factory implements Factory<HomePageDataLoader> {
  private final Provider<PageStore> pageStoreProvider;

  public HomePageDataLoader_Factory(Provider<PageStore> pageStoreProvider) {
    this.pageStoreProvider = pageStoreProvider;
  }

  @Override
  public HomePageDataLoader get() {
    return newInstance(pageStoreProvider.get());
  }

  public static HomePageDataLoader_Factory create(Provider<PageStore> pageStoreProvider) {
    return new HomePageDataLoader_Factory(pageStoreProvider);
  }

  public static HomePageDataLoader newInstance(PageStore pageStore) {
    return new HomePageDataLoader(pageStore);
  }
}

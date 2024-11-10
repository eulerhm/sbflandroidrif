package org.wordpress.android.ui.reader.actions;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;

@ScopeMetadata("dagger.Reusable")
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
public final class ReaderPostActionsWrapper_Factory implements Factory<ReaderPostActionsWrapper> {
  private final Provider<SiteStore> siteStoreProvider;

  public ReaderPostActionsWrapper_Factory(Provider<SiteStore> siteStoreProvider) {
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public ReaderPostActionsWrapper get() {
    return newInstance(siteStoreProvider.get());
  }

  public static ReaderPostActionsWrapper_Factory create(Provider<SiteStore> siteStoreProvider) {
    return new ReaderPostActionsWrapper_Factory(siteStoreProvider);
  }

  public static ReaderPostActionsWrapper newInstance(SiteStore siteStore) {
    return new ReaderPostActionsWrapper(siteStore);
  }
}

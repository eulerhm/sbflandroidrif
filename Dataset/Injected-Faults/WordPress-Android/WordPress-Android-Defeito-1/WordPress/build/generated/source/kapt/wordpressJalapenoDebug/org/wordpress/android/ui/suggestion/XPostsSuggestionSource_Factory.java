package org.wordpress.android.ui.suggestion;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.XPostsStore;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class XPostsSuggestionSource_Factory implements Factory<XPostsSuggestionSource> {
  private final Provider<XPostsStore> xPostsStoreProvider;

  private final Provider<SiteModel> siteProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public XPostsSuggestionSource_Factory(Provider<XPostsStore> xPostsStoreProvider,
      Provider<SiteModel> siteProvider, Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.xPostsStoreProvider = xPostsStoreProvider;
    this.siteProvider = siteProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public XPostsSuggestionSource get() {
    return newInstance(xPostsStoreProvider.get(), siteProvider.get(), bgDispatcherProvider.get());
  }

  public static XPostsSuggestionSource_Factory create(Provider<XPostsStore> xPostsStoreProvider,
      Provider<SiteModel> siteProvider, Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new XPostsSuggestionSource_Factory(xPostsStoreProvider, siteProvider, bgDispatcherProvider);
  }

  public static XPostsSuggestionSource newInstance(XPostsStore xPostsStore, SiteModel site,
      CoroutineDispatcher bgDispatcher) {
    return new XPostsSuggestionSource(xPostsStore, site, bgDispatcher);
  }
}

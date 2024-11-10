package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.PageStore;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class PageParentViewModel_Factory implements Factory<PageParentViewModel> {
  private final Provider<PageStore> pageStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  private final Provider<CoroutineDispatcher> defaultDispatcherProvider;

  public PageParentViewModel_Factory(Provider<PageStore> pageStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<CoroutineDispatcher> defaultDispatcherProvider) {
    this.pageStoreProvider = pageStoreProvider;
    this.resourceProvider = resourceProvider;
    this.uiDispatcherProvider = uiDispatcherProvider;
    this.defaultDispatcherProvider = defaultDispatcherProvider;
  }

  @Override
  public PageParentViewModel get() {
    return newInstance(pageStoreProvider.get(), resourceProvider.get(), uiDispatcherProvider.get(), defaultDispatcherProvider.get());
  }

  public static PageParentViewModel_Factory create(Provider<PageStore> pageStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<CoroutineDispatcher> defaultDispatcherProvider) {
    return new PageParentViewModel_Factory(pageStoreProvider, resourceProvider, uiDispatcherProvider, defaultDispatcherProvider);
  }

  public static PageParentViewModel newInstance(PageStore pageStore,
      ResourceProvider resourceProvider, CoroutineDispatcher uiDispatcher,
      CoroutineDispatcher defaultDispatcher) {
    return new PageParentViewModel(pageStore, resourceProvider, uiDispatcher, defaultDispatcher);
  }
}

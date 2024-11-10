package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

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
public final class PageParentSearchViewModel_Factory implements Factory<PageParentSearchViewModel> {
  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  public PageParentSearchViewModel_Factory(Provider<CoroutineDispatcher> uiDispatcherProvider) {
    this.uiDispatcherProvider = uiDispatcherProvider;
  }

  @Override
  public PageParentSearchViewModel get() {
    return newInstance(uiDispatcherProvider.get());
  }

  public static PageParentSearchViewModel_Factory create(
      Provider<CoroutineDispatcher> uiDispatcherProvider) {
    return new PageParentSearchViewModel_Factory(uiDispatcherProvider);
  }

  public static PageParentSearchViewModel newInstance(CoroutineDispatcher uiDispatcher) {
    return new PageParentSearchViewModel(uiDispatcher);
  }
}

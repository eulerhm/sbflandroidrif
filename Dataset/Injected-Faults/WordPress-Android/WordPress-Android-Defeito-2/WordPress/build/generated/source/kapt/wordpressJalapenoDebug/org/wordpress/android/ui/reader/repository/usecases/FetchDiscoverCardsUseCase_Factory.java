package org.wordpress.android.ui.reader.repository.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class FetchDiscoverCardsUseCase_Factory implements Factory<FetchDiscoverCardsUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ContextProvider> contextProvider;

  public FetchDiscoverCardsUseCase_Factory(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public FetchDiscoverCardsUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), contextProvider.get());
  }

  public static FetchDiscoverCardsUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider) {
    return new FetchDiscoverCardsUseCase_Factory(networkUtilsWrapperProvider, contextProvider);
  }

  public static FetchDiscoverCardsUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ContextProvider contextProvider) {
    return new FetchDiscoverCardsUseCase(networkUtilsWrapper, contextProvider);
  }
}

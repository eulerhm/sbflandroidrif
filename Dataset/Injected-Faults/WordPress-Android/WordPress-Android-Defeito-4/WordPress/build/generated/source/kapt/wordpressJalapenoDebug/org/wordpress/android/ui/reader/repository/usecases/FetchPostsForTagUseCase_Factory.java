package org.wordpress.android.ui.reader.repository.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.services.post.wrapper.ReaderPostServiceStarterWrapper;
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
public final class FetchPostsForTagUseCase_Factory implements Factory<FetchPostsForTagUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<ReaderPostServiceStarterWrapper> readerPostServiceStarterWrapperProvider;

  public FetchPostsForTagUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<ReaderPostServiceStarterWrapper> readerPostServiceStarterWrapperProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.contextProvider = contextProvider;
    this.readerPostServiceStarterWrapperProvider = readerPostServiceStarterWrapperProvider;
  }

  @Override
  public FetchPostsForTagUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), contextProvider.get(), readerPostServiceStarterWrapperProvider.get());
  }

  public static FetchPostsForTagUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<ReaderPostServiceStarterWrapper> readerPostServiceStarterWrapperProvider) {
    return new FetchPostsForTagUseCase_Factory(networkUtilsWrapperProvider, contextProvider, readerPostServiceStarterWrapperProvider);
  }

  public static FetchPostsForTagUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ContextProvider contextProvider,
      ReaderPostServiceStarterWrapper readerPostServiceStarterWrapper) {
    return new FetchPostsForTagUseCase(networkUtilsWrapper, contextProvider, readerPostServiceStarterWrapper);
  }
}

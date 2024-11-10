package org.wordpress.android.ui.reader.repository.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;

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
public final class GetPostsForTagWithCountUseCase_Factory implements Factory<GetPostsForTagWithCountUseCase> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  public GetPostsForTagWithCountUseCase_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
  }

  @Override
  public GetPostsForTagWithCountUseCase get() {
    return newInstance(ioDispatcherProvider.get(), readerPostTableWrapperProvider.get());
  }

  public static GetPostsForTagWithCountUseCase_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    return new GetPostsForTagWithCountUseCase_Factory(ioDispatcherProvider, readerPostTableWrapperProvider);
  }

  public static GetPostsForTagWithCountUseCase newInstance(CoroutineDispatcher ioDispatcher,
      ReaderPostTableWrapper readerPostTableWrapper) {
    return new GetPostsForTagWithCountUseCase(ioDispatcher, readerPostTableWrapper);
  }
}

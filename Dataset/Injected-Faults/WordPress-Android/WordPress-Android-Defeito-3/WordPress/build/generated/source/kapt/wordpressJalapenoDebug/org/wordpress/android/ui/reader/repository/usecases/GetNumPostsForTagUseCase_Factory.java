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
public final class GetNumPostsForTagUseCase_Factory implements Factory<GetNumPostsForTagUseCase> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  public GetNumPostsForTagUseCase_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
  }

  @Override
  public GetNumPostsForTagUseCase get() {
    return newInstance(ioDispatcherProvider.get(), readerPostTableWrapperProvider.get());
  }

  public static GetNumPostsForTagUseCase_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    return new GetNumPostsForTagUseCase_Factory(ioDispatcherProvider, readerPostTableWrapperProvider);
  }

  public static GetNumPostsForTagUseCase newInstance(CoroutineDispatcher ioDispatcher,
      ReaderPostTableWrapper readerPostTableWrapper) {
    return new GetNumPostsForTagUseCase(ioDispatcher, readerPostTableWrapper);
  }
}

package org.wordpress.android.ui.reader.usecases;

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
public final class ReaderGetPostUseCase_Factory implements Factory<ReaderGetPostUseCase> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  public ReaderGetPostUseCase_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
  }

  @Override
  public ReaderGetPostUseCase get() {
    return newInstance(ioDispatcherProvider.get(), readerPostTableWrapperProvider.get());
  }

  public static ReaderGetPostUseCase_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    return new ReaderGetPostUseCase_Factory(ioDispatcherProvider, readerPostTableWrapperProvider);
  }

  public static ReaderGetPostUseCase newInstance(CoroutineDispatcher ioDispatcher,
      ReaderPostTableWrapper readerPostTableWrapper) {
    return new ReaderGetPostUseCase(ioDispatcher, readerPostTableWrapper);
  }
}

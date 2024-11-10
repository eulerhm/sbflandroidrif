package org.wordpress.android.ui.reader.repository.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.wrappers.ReaderTagTableWrapper;

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
public final class ShouldAutoUpdateTagUseCase_Factory implements Factory<ShouldAutoUpdateTagUseCase> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider;

  public ShouldAutoUpdateTagUseCase_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.readerTagTableWrapperProvider = readerTagTableWrapperProvider;
  }

  @Override
  public ShouldAutoUpdateTagUseCase get() {
    return newInstance(ioDispatcherProvider.get(), readerTagTableWrapperProvider.get());
  }

  public static ShouldAutoUpdateTagUseCase_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider) {
    return new ShouldAutoUpdateTagUseCase_Factory(ioDispatcherProvider, readerTagTableWrapperProvider);
  }

  public static ShouldAutoUpdateTagUseCase newInstance(CoroutineDispatcher ioDispatcher,
      ReaderTagTableWrapper readerTagTableWrapper) {
    return new ShouldAutoUpdateTagUseCase(ioDispatcher, readerTagTableWrapper);
  }
}

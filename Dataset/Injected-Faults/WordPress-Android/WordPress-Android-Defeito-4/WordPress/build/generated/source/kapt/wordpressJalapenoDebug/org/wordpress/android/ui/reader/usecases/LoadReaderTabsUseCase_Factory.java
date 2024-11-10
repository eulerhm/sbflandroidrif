package org.wordpress.android.ui.reader.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class LoadReaderTabsUseCase_Factory implements Factory<LoadReaderTabsUseCase> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  public LoadReaderTabsUseCase_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
  }

  @Override
  public LoadReaderTabsUseCase get() {
    return newInstance(bgDispatcherProvider.get(), readerUtilsWrapperProvider.get());
  }

  public static LoadReaderTabsUseCase_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider) {
    return new LoadReaderTabsUseCase_Factory(bgDispatcherProvider, readerUtilsWrapperProvider);
  }

  public static LoadReaderTabsUseCase newInstance(CoroutineDispatcher bgDispatcher,
      ReaderUtilsWrapper readerUtilsWrapper) {
    return new LoadReaderTabsUseCase(bgDispatcher, readerUtilsWrapper);
  }
}

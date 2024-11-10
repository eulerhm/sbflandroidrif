package org.wordpress.android.ui.reader.repository.usecases.tags;

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
public final class GetFollowedTagsUseCase_Factory implements Factory<GetFollowedTagsUseCase> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider;

  public GetFollowedTagsUseCase_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.readerTagTableWrapperProvider = readerTagTableWrapperProvider;
  }

  @Override
  public GetFollowedTagsUseCase get() {
    return newInstance(ioDispatcherProvider.get(), readerTagTableWrapperProvider.get());
  }

  public static GetFollowedTagsUseCase_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider) {
    return new GetFollowedTagsUseCase_Factory(ioDispatcherProvider, readerTagTableWrapperProvider);
  }

  public static GetFollowedTagsUseCase newInstance(CoroutineDispatcher ioDispatcher,
      ReaderTagTableWrapper readerTagTableWrapper) {
    return new GetFollowedTagsUseCase(ioDispatcher, readerTagTableWrapper);
  }
}

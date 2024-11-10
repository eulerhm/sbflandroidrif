package org.wordpress.android.ui.reader.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase;

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
public final class ReaderPostSeenStatusWrapper_Factory implements Factory<ReaderPostSeenStatusWrapper> {
  private final Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ReaderPostSeenStatusWrapper_Factory(
      Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.seenStatusToggleUseCaseProvider = seenStatusToggleUseCaseProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ReaderPostSeenStatusWrapper get() {
    return newInstance(seenStatusToggleUseCaseProvider.get(), bgDispatcherProvider.get());
  }

  public static ReaderPostSeenStatusWrapper_Factory create(
      Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ReaderPostSeenStatusWrapper_Factory(seenStatusToggleUseCaseProvider, bgDispatcherProvider);
  }

  public static ReaderPostSeenStatusWrapper newInstance(
      ReaderSeenStatusToggleUseCase seenStatusToggleUseCase, CoroutineDispatcher bgDispatcher) {
    return new ReaderPostSeenStatusWrapper(seenStatusToggleUseCase, bgDispatcher);
  }
}

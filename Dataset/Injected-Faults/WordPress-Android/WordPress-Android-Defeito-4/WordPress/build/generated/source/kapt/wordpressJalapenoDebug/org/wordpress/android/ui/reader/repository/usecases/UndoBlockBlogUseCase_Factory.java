package org.wordpress.android.ui.reader.repository.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;

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
public final class UndoBlockBlogUseCase_Factory implements Factory<UndoBlockBlogUseCase> {
  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public UndoBlockBlogUseCase_Factory(Provider<ReaderTracker> readerTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.readerTrackerProvider = readerTrackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public UndoBlockBlogUseCase get() {
    return newInstance(readerTrackerProvider.get(), bgDispatcherProvider.get());
  }

  public static UndoBlockBlogUseCase_Factory create(Provider<ReaderTracker> readerTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new UndoBlockBlogUseCase_Factory(readerTrackerProvider, bgDispatcherProvider);
  }

  public static UndoBlockBlogUseCase newInstance(ReaderTracker readerTracker,
      CoroutineDispatcher bgDispatcher) {
    return new UndoBlockBlogUseCase(readerTracker, bgDispatcher);
  }
}

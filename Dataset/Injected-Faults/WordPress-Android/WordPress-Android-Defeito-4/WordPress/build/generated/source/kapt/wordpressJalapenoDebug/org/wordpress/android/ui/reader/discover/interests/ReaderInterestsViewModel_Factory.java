package org.wordpress.android.ui.reader.discover.interests;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.repository.ReaderTagRepository;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;

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
public final class ReaderInterestsViewModel_Factory implements Factory<ReaderInterestsViewModel> {
  private final Provider<ReaderTagRepository> readerTagRepositoryProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  public ReaderInterestsViewModel_Factory(Provider<ReaderTagRepository> readerTagRepositoryProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    this.readerTagRepositoryProvider = readerTagRepositoryProvider;
    this.readerTrackerProvider = readerTrackerProvider;
  }

  @Override
  public ReaderInterestsViewModel get() {
    return newInstance(readerTagRepositoryProvider.get(), readerTrackerProvider.get());
  }

  public static ReaderInterestsViewModel_Factory create(
      Provider<ReaderTagRepository> readerTagRepositoryProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    return new ReaderInterestsViewModel_Factory(readerTagRepositoryProvider, readerTrackerProvider);
  }

  public static ReaderInterestsViewModel newInstance(ReaderTagRepository readerTagRepository,
      ReaderTracker readerTracker) {
    return new ReaderInterestsViewModel(readerTagRepository, readerTracker);
  }
}

package org.wordpress.android.ui.reader;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;

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
public final class ReaderSearchActivity_MembersInjector implements MembersInjector<ReaderSearchActivity> {
  private final Provider<ReaderTracker> readerTrackerProvider;

  public ReaderSearchActivity_MembersInjector(Provider<ReaderTracker> readerTrackerProvider) {
    this.readerTrackerProvider = readerTrackerProvider;
  }

  public static MembersInjector<ReaderSearchActivity> create(
      Provider<ReaderTracker> readerTrackerProvider) {
    return new ReaderSearchActivity_MembersInjector(readerTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderSearchActivity instance) {
    injectReaderTracker(instance, readerTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderSearchActivity.readerTracker")
  public static void injectReaderTracker(ReaderSearchActivity instance,
      ReaderTracker readerTracker) {
    instance.readerTracker = readerTracker;
  }
}

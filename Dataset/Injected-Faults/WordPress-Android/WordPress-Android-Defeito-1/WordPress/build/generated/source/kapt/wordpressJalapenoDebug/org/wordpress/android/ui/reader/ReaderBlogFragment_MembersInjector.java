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
public final class ReaderBlogFragment_MembersInjector implements MembersInjector<ReaderBlogFragment> {
  private final Provider<ReaderTracker> mReaderTrackerProvider;

  public ReaderBlogFragment_MembersInjector(Provider<ReaderTracker> mReaderTrackerProvider) {
    this.mReaderTrackerProvider = mReaderTrackerProvider;
  }

  public static MembersInjector<ReaderBlogFragment> create(
      Provider<ReaderTracker> mReaderTrackerProvider) {
    return new ReaderBlogFragment_MembersInjector(mReaderTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderBlogFragment instance) {
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderBlogFragment.mReaderTracker")
  public static void injectMReaderTracker(ReaderBlogFragment instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }
}

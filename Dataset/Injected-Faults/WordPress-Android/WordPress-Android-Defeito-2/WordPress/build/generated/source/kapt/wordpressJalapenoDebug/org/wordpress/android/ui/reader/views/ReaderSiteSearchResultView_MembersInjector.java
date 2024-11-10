package org.wordpress.android.ui.reader.views;

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
public final class ReaderSiteSearchResultView_MembersInjector implements MembersInjector<ReaderSiteSearchResultView> {
  private final Provider<ReaderTracker> mReaderTrackerProvider;

  public ReaderSiteSearchResultView_MembersInjector(
      Provider<ReaderTracker> mReaderTrackerProvider) {
    this.mReaderTrackerProvider = mReaderTrackerProvider;
  }

  public static MembersInjector<ReaderSiteSearchResultView> create(
      Provider<ReaderTracker> mReaderTrackerProvider) {
    return new ReaderSiteSearchResultView_MembersInjector(mReaderTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderSiteSearchResultView instance) {
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderSiteSearchResultView.mReaderTracker")
  public static void injectMReaderTracker(ReaderSiteSearchResultView instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }
}

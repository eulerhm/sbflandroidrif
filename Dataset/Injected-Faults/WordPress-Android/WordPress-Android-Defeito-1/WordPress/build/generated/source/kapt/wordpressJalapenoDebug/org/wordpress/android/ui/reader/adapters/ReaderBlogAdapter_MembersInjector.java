package org.wordpress.android.ui.reader.adapters;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.util.image.ImageManager;

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
public final class ReaderBlogAdapter_MembersInjector implements MembersInjector<ReaderBlogAdapter> {
  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  public ReaderBlogAdapter_MembersInjector(Provider<ImageManager> mImageManagerProvider,
      Provider<ReaderTracker> mReaderTrackerProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
  }

  public static MembersInjector<ReaderBlogAdapter> create(
      Provider<ImageManager> mImageManagerProvider,
      Provider<ReaderTracker> mReaderTrackerProvider) {
    return new ReaderBlogAdapter_MembersInjector(mImageManagerProvider, mReaderTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderBlogAdapter instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderBlogAdapter.mImageManager")
  public static void injectMImageManager(ReaderBlogAdapter instance, ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderBlogAdapter.mReaderTracker")
  public static void injectMReaderTracker(ReaderBlogAdapter instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }
}

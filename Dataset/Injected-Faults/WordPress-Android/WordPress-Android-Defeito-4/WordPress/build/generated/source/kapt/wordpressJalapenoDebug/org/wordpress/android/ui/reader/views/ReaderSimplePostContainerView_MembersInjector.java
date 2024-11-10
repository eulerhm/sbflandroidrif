package org.wordpress.android.ui.reader.views;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.utils.UiHelpers;
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
public final class ReaderSimplePostContainerView_MembersInjector implements MembersInjector<ReaderSimplePostContainerView> {
  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  public ReaderSimplePostContainerView_MembersInjector(Provider<UiHelpers> uiHelpersProvider,
      Provider<ImageManager> imageManagerProvider, Provider<ReaderTracker> readerTrackerProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.readerTrackerProvider = readerTrackerProvider;
  }

  public static MembersInjector<ReaderSimplePostContainerView> create(
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    return new ReaderSimplePostContainerView_MembersInjector(uiHelpersProvider, imageManagerProvider, readerTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderSimplePostContainerView instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectReaderTracker(instance, readerTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderSimplePostContainerView.uiHelpers")
  public static void injectUiHelpers(ReaderSimplePostContainerView instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderSimplePostContainerView.imageManager")
  public static void injectImageManager(ReaderSimplePostContainerView instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderSimplePostContainerView.readerTracker")
  public static void injectReaderTracker(ReaderSimplePostContainerView instance,
      ReaderTracker readerTracker) {
    instance.readerTracker = readerTracker;
  }
}

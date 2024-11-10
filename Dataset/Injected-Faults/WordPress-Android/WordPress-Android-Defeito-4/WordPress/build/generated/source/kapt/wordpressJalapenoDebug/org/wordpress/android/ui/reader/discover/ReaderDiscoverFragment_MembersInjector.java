package org.wordpress.android.ui.reader.discover;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
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
public final class ReaderDiscoverFragment_MembersInjector implements MembersInjector<ReaderDiscoverFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  public ReaderDiscoverFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.readerTrackerProvider = readerTrackerProvider;
  }

  public static MembersInjector<ReaderDiscoverFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    return new ReaderDiscoverFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, imageManagerProvider, readerUtilsWrapperProvider, readerTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderDiscoverFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectReaderUtilsWrapper(instance, readerUtilsWrapperProvider.get());
    injectReaderTracker(instance, readerTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.discover.ReaderDiscoverFragment.viewModelFactory")
  public static void injectViewModelFactory(ReaderDiscoverFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.discover.ReaderDiscoverFragment.uiHelpers")
  public static void injectUiHelpers(ReaderDiscoverFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.discover.ReaderDiscoverFragment.imageManager")
  public static void injectImageManager(ReaderDiscoverFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.discover.ReaderDiscoverFragment.readerUtilsWrapper")
  public static void injectReaderUtilsWrapper(ReaderDiscoverFragment instance,
      ReaderUtilsWrapper readerUtilsWrapper) {
    instance.readerUtilsWrapper = readerUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.discover.ReaderDiscoverFragment.readerTracker")
  public static void injectReaderTracker(ReaderDiscoverFragment instance,
      ReaderTracker readerTracker) {
    instance.readerTracker = readerTracker;
  }
}

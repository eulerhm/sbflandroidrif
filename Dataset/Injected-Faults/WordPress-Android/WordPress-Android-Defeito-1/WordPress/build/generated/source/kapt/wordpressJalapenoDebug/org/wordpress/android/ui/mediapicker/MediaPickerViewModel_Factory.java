package org.wordpress.android.ui.mediapicker;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.mediapicker.insert.MediaInsertHandlerFactory;
import org.wordpress.android.ui.mediapicker.loader.MediaLoaderFactory;
import org.wordpress.android.ui.photopicker.PermissionsHandler;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.MediaUtilsWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class MediaPickerViewModel_Factory implements Factory<MediaPickerViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<MediaLoaderFactory> mediaLoaderFactoryProvider;

  private final Provider<MediaInsertHandlerFactory> mediaInsertHandlerFactoryProvider;

  private final Provider<MediaPickerTracker> mediaPickerTrackerProvider;

  private final Provider<PermissionsHandler> permissionsHandlerProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public MediaPickerViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<MediaLoaderFactory> mediaLoaderFactoryProvider,
      Provider<MediaInsertHandlerFactory> mediaInsertHandlerFactoryProvider,
      Provider<MediaPickerTracker> mediaPickerTrackerProvider,
      Provider<PermissionsHandler> permissionsHandlerProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<ResourceProvider> resourceProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mediaLoaderFactoryProvider = mediaLoaderFactoryProvider;
    this.mediaInsertHandlerFactoryProvider = mediaInsertHandlerFactoryProvider;
    this.mediaPickerTrackerProvider = mediaPickerTrackerProvider;
    this.permissionsHandlerProvider = permissionsHandlerProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public MediaPickerViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), mediaLoaderFactoryProvider.get(), mediaInsertHandlerFactoryProvider.get(), mediaPickerTrackerProvider.get(), permissionsHandlerProvider.get(), localeManagerWrapperProvider.get(), mediaUtilsWrapperProvider.get(), mediaStoreProvider.get(), resourceProvider.get());
  }

  public static MediaPickerViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<MediaLoaderFactory> mediaLoaderFactoryProvider,
      Provider<MediaInsertHandlerFactory> mediaInsertHandlerFactoryProvider,
      Provider<MediaPickerTracker> mediaPickerTrackerProvider,
      Provider<PermissionsHandler> permissionsHandlerProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<ResourceProvider> resourceProvider) {
    return new MediaPickerViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, mediaLoaderFactoryProvider, mediaInsertHandlerFactoryProvider, mediaPickerTrackerProvider, permissionsHandlerProvider, localeManagerWrapperProvider, mediaUtilsWrapperProvider, mediaStoreProvider, resourceProvider);
  }

  public static MediaPickerViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, MediaLoaderFactory mediaLoaderFactory,
      MediaInsertHandlerFactory mediaInsertHandlerFactory, MediaPickerTracker mediaPickerTracker,
      PermissionsHandler permissionsHandler, LocaleManagerWrapper localeManagerWrapper,
      MediaUtilsWrapper mediaUtilsWrapper, MediaStore mediaStore,
      ResourceProvider resourceProvider) {
    return new MediaPickerViewModel(mainDispatcher, bgDispatcher, mediaLoaderFactory, mediaInsertHandlerFactory, mediaPickerTracker, permissionsHandler, localeManagerWrapper, mediaUtilsWrapper, mediaStore, resourceProvider);
  }
}

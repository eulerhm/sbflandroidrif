package org.wordpress.android.ui.mediapicker.loader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.util.DateTimeUtilsWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class MediaLibraryDataSource_MediaLibraryDataSourceFactory_Factory implements Factory<MediaLibraryDataSource.MediaLibraryDataSourceFactory> {
  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  public MediaLibraryDataSource_MediaLibraryDataSourceFactory_Factory(
      Provider<MediaStore> mediaStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider) {
    this.mediaStoreProvider = mediaStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
  }

  @Override
  public MediaLibraryDataSource.MediaLibraryDataSourceFactory get() {
    return newInstance(mediaStoreProvider.get(), dispatcherProvider.get(), bgDispatcherProvider.get(), networkUtilsWrapperProvider.get(), dateTimeUtilsWrapperProvider.get());
  }

  public static MediaLibraryDataSource_MediaLibraryDataSourceFactory_Factory create(
      Provider<MediaStore> mediaStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider) {
    return new MediaLibraryDataSource_MediaLibraryDataSourceFactory_Factory(mediaStoreProvider, dispatcherProvider, bgDispatcherProvider, networkUtilsWrapperProvider, dateTimeUtilsWrapperProvider);
  }

  public static MediaLibraryDataSource.MediaLibraryDataSourceFactory newInstance(
      MediaStore mediaStore, Dispatcher dispatcher, CoroutineDispatcher bgDispatcher,
      NetworkUtilsWrapper networkUtilsWrapper, DateTimeUtilsWrapper dateTimeUtilsWrapper) {
    return new MediaLibraryDataSource.MediaLibraryDataSourceFactory(mediaStore, dispatcher, bgDispatcher, networkUtilsWrapper, dateTimeUtilsWrapper);
  }
}

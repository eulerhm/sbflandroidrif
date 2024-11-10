package org.wordpress.android.ui.mediapicker.loader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class MediaLoaderFactory_Factory implements Factory<MediaLoaderFactory> {
  private final Provider<DeviceListBuilder.DeviceListBuilderFactory> deviceListBuilderFactoryProvider;

  private final Provider<MediaLibraryDataSource.MediaLibraryDataSourceFactory> mediaLibraryDataSourceFactoryProvider;

  private final Provider<StockMediaDataSource> stockMediaDataSourceProvider;

  private final Provider<GifMediaDataSource> gifMediaDataSourceProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public MediaLoaderFactory_Factory(
      Provider<DeviceListBuilder.DeviceListBuilderFactory> deviceListBuilderFactoryProvider,
      Provider<MediaLibraryDataSource.MediaLibraryDataSourceFactory> mediaLibraryDataSourceFactoryProvider,
      Provider<StockMediaDataSource> stockMediaDataSourceProvider,
      Provider<GifMediaDataSource> gifMediaDataSourceProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.deviceListBuilderFactoryProvider = deviceListBuilderFactoryProvider;
    this.mediaLibraryDataSourceFactoryProvider = mediaLibraryDataSourceFactoryProvider;
    this.stockMediaDataSourceProvider = stockMediaDataSourceProvider;
    this.gifMediaDataSourceProvider = gifMediaDataSourceProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public MediaLoaderFactory get() {
    return newInstance(deviceListBuilderFactoryProvider.get(), mediaLibraryDataSourceFactoryProvider.get(), stockMediaDataSourceProvider.get(), gifMediaDataSourceProvider.get(), localeManagerWrapperProvider.get(), networkUtilsWrapperProvider.get());
  }

  public static MediaLoaderFactory_Factory create(
      Provider<DeviceListBuilder.DeviceListBuilderFactory> deviceListBuilderFactoryProvider,
      Provider<MediaLibraryDataSource.MediaLibraryDataSourceFactory> mediaLibraryDataSourceFactoryProvider,
      Provider<StockMediaDataSource> stockMediaDataSourceProvider,
      Provider<GifMediaDataSource> gifMediaDataSourceProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new MediaLoaderFactory_Factory(deviceListBuilderFactoryProvider, mediaLibraryDataSourceFactoryProvider, stockMediaDataSourceProvider, gifMediaDataSourceProvider, localeManagerWrapperProvider, networkUtilsWrapperProvider);
  }

  public static MediaLoaderFactory newInstance(
      DeviceListBuilder.DeviceListBuilderFactory deviceListBuilderFactory,
      MediaLibraryDataSource.MediaLibraryDataSourceFactory mediaLibraryDataSourceFactory,
      StockMediaDataSource stockMediaDataSource, GifMediaDataSource gifMediaDataSource,
      LocaleManagerWrapper localeManagerWrapper, NetworkUtilsWrapper networkUtilsWrapper) {
    return new MediaLoaderFactory(deviceListBuilderFactory, mediaLibraryDataSourceFactory, stockMediaDataSource, gifMediaDataSource, localeManagerWrapper, networkUtilsWrapper);
  }
}

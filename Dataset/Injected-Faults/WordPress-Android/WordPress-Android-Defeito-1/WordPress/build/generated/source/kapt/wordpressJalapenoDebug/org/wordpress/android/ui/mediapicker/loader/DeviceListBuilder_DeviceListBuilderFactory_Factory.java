package org.wordpress.android.ui.mediapicker.loader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.MediaUtilsWrapper;

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
public final class DeviceListBuilder_DeviceListBuilderFactory_Factory implements Factory<DeviceListBuilder.DeviceListBuilderFactory> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<DeviceMediaLoader> deviceMediaLoaderProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public DeviceListBuilder_DeviceListBuilderFactory_Factory(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<DeviceMediaLoader> deviceMediaLoaderProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.deviceMediaLoaderProvider = deviceMediaLoaderProvider;
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public DeviceListBuilder.DeviceListBuilderFactory get() {
    return newInstance(localeManagerWrapperProvider.get(), deviceMediaLoaderProvider.get(), mediaUtilsWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static DeviceListBuilder_DeviceListBuilderFactory_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<DeviceMediaLoader> deviceMediaLoaderProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new DeviceListBuilder_DeviceListBuilderFactory_Factory(localeManagerWrapperProvider, deviceMediaLoaderProvider, mediaUtilsWrapperProvider, bgDispatcherProvider);
  }

  public static DeviceListBuilder.DeviceListBuilderFactory newInstance(
      LocaleManagerWrapper localeManagerWrapper, DeviceMediaLoader deviceMediaLoader,
      MediaUtilsWrapper mediaUtilsWrapper, CoroutineDispatcher bgDispatcher) {
    return new DeviceListBuilder.DeviceListBuilderFactory(localeManagerWrapper, deviceMediaLoader, mediaUtilsWrapper, bgDispatcher);
  }
}

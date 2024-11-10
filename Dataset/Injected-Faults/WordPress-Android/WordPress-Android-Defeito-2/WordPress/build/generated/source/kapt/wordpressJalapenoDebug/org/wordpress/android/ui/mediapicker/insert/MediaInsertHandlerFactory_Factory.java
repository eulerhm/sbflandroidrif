package org.wordpress.android.ui.mediapicker.insert;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class MediaInsertHandlerFactory_Factory implements Factory<MediaInsertHandlerFactory> {
  private final Provider<DeviceListInsertUseCase.DeviceListInsertUseCaseFactory> deviceListInsertUseCaseFactoryProvider;

  private final Provider<StockMediaInsertUseCase.StockMediaInsertUseCaseFactory> stockMediaInsertUseCaseFactoryProvider;

  private final Provider<GifMediaInsertUseCase.GifMediaInsertUseCaseFactory> gifMediaInsertUseCaseFactoryProvider;

  public MediaInsertHandlerFactory_Factory(
      Provider<DeviceListInsertUseCase.DeviceListInsertUseCaseFactory> deviceListInsertUseCaseFactoryProvider,
      Provider<StockMediaInsertUseCase.StockMediaInsertUseCaseFactory> stockMediaInsertUseCaseFactoryProvider,
      Provider<GifMediaInsertUseCase.GifMediaInsertUseCaseFactory> gifMediaInsertUseCaseFactoryProvider) {
    this.deviceListInsertUseCaseFactoryProvider = deviceListInsertUseCaseFactoryProvider;
    this.stockMediaInsertUseCaseFactoryProvider = stockMediaInsertUseCaseFactoryProvider;
    this.gifMediaInsertUseCaseFactoryProvider = gifMediaInsertUseCaseFactoryProvider;
  }

  @Override
  public MediaInsertHandlerFactory get() {
    return newInstance(deviceListInsertUseCaseFactoryProvider.get(), stockMediaInsertUseCaseFactoryProvider.get(), gifMediaInsertUseCaseFactoryProvider.get());
  }

  public static MediaInsertHandlerFactory_Factory create(
      Provider<DeviceListInsertUseCase.DeviceListInsertUseCaseFactory> deviceListInsertUseCaseFactoryProvider,
      Provider<StockMediaInsertUseCase.StockMediaInsertUseCaseFactory> stockMediaInsertUseCaseFactoryProvider,
      Provider<GifMediaInsertUseCase.GifMediaInsertUseCaseFactory> gifMediaInsertUseCaseFactoryProvider) {
    return new MediaInsertHandlerFactory_Factory(deviceListInsertUseCaseFactoryProvider, stockMediaInsertUseCaseFactoryProvider, gifMediaInsertUseCaseFactoryProvider);
  }

  public static MediaInsertHandlerFactory newInstance(
      DeviceListInsertUseCase.DeviceListInsertUseCaseFactory deviceListInsertUseCaseFactory,
      StockMediaInsertUseCase.StockMediaInsertUseCaseFactory stockMediaInsertUseCaseFactory,
      GifMediaInsertUseCase.GifMediaInsertUseCaseFactory gifMediaInsertUseCaseFactory) {
    return new MediaInsertHandlerFactory(deviceListInsertUseCaseFactory, stockMediaInsertUseCaseFactory, gifMediaInsertUseCaseFactory);
  }
}

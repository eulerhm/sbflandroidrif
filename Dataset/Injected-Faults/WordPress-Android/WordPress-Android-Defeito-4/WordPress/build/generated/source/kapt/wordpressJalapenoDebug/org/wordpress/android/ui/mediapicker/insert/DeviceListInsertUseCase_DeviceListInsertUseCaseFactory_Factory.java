package org.wordpress.android.ui.mediapicker.insert;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.WPMediaUtilsWrapper;

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
public final class DeviceListInsertUseCase_DeviceListInsertUseCaseFactory_Factory implements Factory<DeviceListInsertUseCase.DeviceListInsertUseCaseFactory> {
  private final Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider;

  public DeviceListInsertUseCase_DeviceListInsertUseCaseFactory_Factory(
      Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider) {
    this.wpMediaUtilsWrapperProvider = wpMediaUtilsWrapperProvider;
  }

  @Override
  public DeviceListInsertUseCase.DeviceListInsertUseCaseFactory get() {
    return newInstance(wpMediaUtilsWrapperProvider.get());
  }

  public static DeviceListInsertUseCase_DeviceListInsertUseCaseFactory_Factory create(
      Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider) {
    return new DeviceListInsertUseCase_DeviceListInsertUseCaseFactory_Factory(wpMediaUtilsWrapperProvider);
  }

  public static DeviceListInsertUseCase.DeviceListInsertUseCaseFactory newInstance(
      WPMediaUtilsWrapper wpMediaUtilsWrapper) {
    return new DeviceListInsertUseCase.DeviceListInsertUseCaseFactory(wpMediaUtilsWrapper);
  }
}

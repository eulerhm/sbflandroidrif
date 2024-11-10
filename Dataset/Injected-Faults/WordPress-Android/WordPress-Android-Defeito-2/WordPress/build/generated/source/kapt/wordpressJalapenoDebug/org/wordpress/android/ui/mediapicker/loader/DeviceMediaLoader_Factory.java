package org.wordpress.android.ui.mediapicker.loader;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleManagerWrapper;

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
public final class DeviceMediaLoader_Factory implements Factory<DeviceMediaLoader> {
  private final Provider<Context> contextProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public DeviceMediaLoader_Factory(Provider<Context> contextProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.contextProvider = contextProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  @Override
  public DeviceMediaLoader get() {
    return newInstance(contextProvider.get(), localeManagerWrapperProvider.get());
  }

  public static DeviceMediaLoader_Factory create(Provider<Context> contextProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new DeviceMediaLoader_Factory(contextProvider, localeManagerWrapperProvider);
  }

  public static DeviceMediaLoader newInstance(Context context,
      LocaleManagerWrapper localeManagerWrapper) {
    return new DeviceMediaLoader(context, localeManagerWrapper);
  }
}

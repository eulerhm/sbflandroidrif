package org.wordpress.android.ui.photopicker;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

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
public final class DeviceMediaListBuilder_Factory implements Factory<DeviceMediaListBuilder> {
  private final Provider<Context> contextProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public DeviceMediaListBuilder_Factory(Provider<Context> contextProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.contextProvider = contextProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public DeviceMediaListBuilder get() {
    return newInstance(contextProvider.get(), bgDispatcherProvider.get());
  }

  public static DeviceMediaListBuilder_Factory create(Provider<Context> contextProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new DeviceMediaListBuilder_Factory(contextProvider, bgDispatcherProvider);
  }

  public static DeviceMediaListBuilder newInstance(Context context,
      CoroutineDispatcher bgDispatcher) {
    return new DeviceMediaListBuilder(context, bgDispatcher);
  }
}

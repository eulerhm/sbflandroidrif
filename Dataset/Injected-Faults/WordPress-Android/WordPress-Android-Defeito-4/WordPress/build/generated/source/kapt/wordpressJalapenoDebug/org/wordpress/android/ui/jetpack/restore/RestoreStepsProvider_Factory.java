package org.wordpress.android.ui.jetpack.restore;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RestoreStepsProvider_Factory implements Factory<RestoreStepsProvider> {
  @Override
  public RestoreStepsProvider get() {
    return newInstance();
  }

  public static RestoreStepsProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RestoreStepsProvider newInstance() {
    return new RestoreStepsProvider();
  }

  private static final class InstanceHolder {
    private static final RestoreStepsProvider_Factory INSTANCE = new RestoreStepsProvider_Factory();
  }
}

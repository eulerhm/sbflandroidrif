package org.wordpress.android.util.config;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class RemoteConfig_Factory implements Factory<RemoteConfig> {
  @Override
  public RemoteConfig get() {
    return newInstance();
  }

  public static RemoteConfig_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RemoteConfig newInstance() {
    return new RemoteConfig();
  }

  private static final class InstanceHolder {
    private static final RemoteConfig_Factory INSTANCE = new RemoteConfig_Factory();
  }
}

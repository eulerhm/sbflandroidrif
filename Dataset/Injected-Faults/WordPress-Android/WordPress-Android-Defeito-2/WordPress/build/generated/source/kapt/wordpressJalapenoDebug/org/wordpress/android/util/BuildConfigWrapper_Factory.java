package org.wordpress.android.util;

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
public final class BuildConfigWrapper_Factory implements Factory<BuildConfigWrapper> {
  @Override
  public BuildConfigWrapper get() {
    return newInstance();
  }

  public static BuildConfigWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static BuildConfigWrapper newInstance() {
    return new BuildConfigWrapper();
  }

  private static final class InstanceHolder {
    private static final BuildConfigWrapper_Factory INSTANCE = new BuildConfigWrapper_Factory();
  }
}

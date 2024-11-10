package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata("dagger.Reusable")
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
public final class NetworkUtilsWrapper_Factory implements Factory<NetworkUtilsWrapper> {
  @Override
  public NetworkUtilsWrapper get() {
    return newInstance();
  }

  public static NetworkUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NetworkUtilsWrapper newInstance() {
    return new NetworkUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final NetworkUtilsWrapper_Factory INSTANCE = new NetworkUtilsWrapper_Factory();
  }
}

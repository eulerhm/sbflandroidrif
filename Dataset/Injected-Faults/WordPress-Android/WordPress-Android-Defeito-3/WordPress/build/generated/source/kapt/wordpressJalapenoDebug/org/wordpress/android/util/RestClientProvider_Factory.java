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
public final class RestClientProvider_Factory implements Factory<RestClientProvider> {
  @Override
  public RestClientProvider get() {
    return newInstance();
  }

  public static RestClientProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RestClientProvider newInstance() {
    return new RestClientProvider();
  }

  private static final class InstanceHolder {
    private static final RestClientProvider_Factory INSTANCE = new RestClientProvider_Factory();
  }
}

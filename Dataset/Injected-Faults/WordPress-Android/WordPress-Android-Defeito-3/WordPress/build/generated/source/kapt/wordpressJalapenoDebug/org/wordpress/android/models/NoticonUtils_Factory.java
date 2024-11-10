package org.wordpress.android.models;

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
public final class NoticonUtils_Factory implements Factory<NoticonUtils> {
  @Override
  public NoticonUtils get() {
    return newInstance();
  }

  public static NoticonUtils_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NoticonUtils newInstance() {
    return new NoticonUtils();
  }

  private static final class InstanceHolder {
    private static final NoticonUtils_Factory INSTANCE = new NoticonUtils_Factory();
  }
}

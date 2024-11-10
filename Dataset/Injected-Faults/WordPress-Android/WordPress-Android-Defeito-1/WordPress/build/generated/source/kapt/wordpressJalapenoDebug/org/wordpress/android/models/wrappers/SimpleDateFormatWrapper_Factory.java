package org.wordpress.android.models.wrappers;

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
public final class SimpleDateFormatWrapper_Factory implements Factory<SimpleDateFormatWrapper> {
  @Override
  public SimpleDateFormatWrapper get() {
    return newInstance();
  }

  public static SimpleDateFormatWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SimpleDateFormatWrapper newInstance() {
    return new SimpleDateFormatWrapper();
  }

  private static final class InstanceHolder {
    private static final SimpleDateFormatWrapper_Factory INSTANCE = new SimpleDateFormatWrapper_Factory();
  }
}

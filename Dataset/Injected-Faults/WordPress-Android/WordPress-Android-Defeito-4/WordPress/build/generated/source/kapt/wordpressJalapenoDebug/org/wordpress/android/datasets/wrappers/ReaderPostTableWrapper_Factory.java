package org.wordpress.android.datasets.wrappers;

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
public final class ReaderPostTableWrapper_Factory implements Factory<ReaderPostTableWrapper> {
  @Override
  public ReaderPostTableWrapper get() {
    return newInstance();
  }

  public static ReaderPostTableWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderPostTableWrapper newInstance() {
    return new ReaderPostTableWrapper();
  }

  private static final class InstanceHolder {
    private static final ReaderPostTableWrapper_Factory INSTANCE = new ReaderPostTableWrapper_Factory();
  }
}

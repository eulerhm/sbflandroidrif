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
public final class ReaderTagTableWrapper_Factory implements Factory<ReaderTagTableWrapper> {
  @Override
  public ReaderTagTableWrapper get() {
    return newInstance();
  }

  public static ReaderTagTableWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderTagTableWrapper newInstance() {
    return new ReaderTagTableWrapper();
  }

  private static final class InstanceHolder {
    private static final ReaderTagTableWrapper_Factory INSTANCE = new ReaderTagTableWrapper_Factory();
  }
}

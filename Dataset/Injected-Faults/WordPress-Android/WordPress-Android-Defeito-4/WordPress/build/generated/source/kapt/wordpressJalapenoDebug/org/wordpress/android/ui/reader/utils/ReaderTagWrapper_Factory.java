package org.wordpress.android.ui.reader.utils;

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
public final class ReaderTagWrapper_Factory implements Factory<ReaderTagWrapper> {
  @Override
  public ReaderTagWrapper get() {
    return newInstance();
  }

  public static ReaderTagWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderTagWrapper newInstance() {
    return new ReaderTagWrapper();
  }

  private static final class InstanceHolder {
    private static final ReaderTagWrapper_Factory INSTANCE = new ReaderTagWrapper_Factory();
  }
}

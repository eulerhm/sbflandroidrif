package org.wordpress.android.ui.reader.services.update.wrapper;

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
public final class ReaderUpdateServiceStarterWrapper_Factory implements Factory<ReaderUpdateServiceStarterWrapper> {
  @Override
  public ReaderUpdateServiceStarterWrapper get() {
    return newInstance();
  }

  public static ReaderUpdateServiceStarterWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderUpdateServiceStarterWrapper newInstance() {
    return new ReaderUpdateServiceStarterWrapper();
  }

  private static final class InstanceHolder {
    private static final ReaderUpdateServiceStarterWrapper_Factory INSTANCE = new ReaderUpdateServiceStarterWrapper_Factory();
  }
}

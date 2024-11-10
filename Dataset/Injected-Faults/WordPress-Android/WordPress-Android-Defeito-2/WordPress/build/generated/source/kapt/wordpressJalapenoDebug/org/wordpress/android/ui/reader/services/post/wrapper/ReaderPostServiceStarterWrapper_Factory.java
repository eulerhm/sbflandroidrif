package org.wordpress.android.ui.reader.services.post.wrapper;

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
public final class ReaderPostServiceStarterWrapper_Factory implements Factory<ReaderPostServiceStarterWrapper> {
  @Override
  public ReaderPostServiceStarterWrapper get() {
    return newInstance();
  }

  public static ReaderPostServiceStarterWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderPostServiceStarterWrapper newInstance() {
    return new ReaderPostServiceStarterWrapper();
  }

  private static final class InstanceHolder {
    private static final ReaderPostServiceStarterWrapper_Factory INSTANCE = new ReaderPostServiceStarterWrapper_Factory();
  }
}

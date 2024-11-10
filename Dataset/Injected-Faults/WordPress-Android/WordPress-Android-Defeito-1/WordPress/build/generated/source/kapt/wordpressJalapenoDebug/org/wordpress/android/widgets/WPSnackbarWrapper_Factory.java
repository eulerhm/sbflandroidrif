package org.wordpress.android.widgets;

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
public final class WPSnackbarWrapper_Factory implements Factory<WPSnackbarWrapper> {
  @Override
  public WPSnackbarWrapper get() {
    return newInstance();
  }

  public static WPSnackbarWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WPSnackbarWrapper newInstance() {
    return new WPSnackbarWrapper();
  }

  private static final class InstanceHolder {
    private static final WPSnackbarWrapper_Factory INSTANCE = new WPSnackbarWrapper_Factory();
  }
}

package org.wordpress.android.ui.utils;

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
public final class UiHelpers_Factory implements Factory<UiHelpers> {
  @Override
  public UiHelpers get() {
    return newInstance();
  }

  public static UiHelpers_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UiHelpers newInstance() {
    return new UiHelpers();
  }

  private static final class InstanceHolder {
    private static final UiHelpers_Factory INSTANCE = new UiHelpers_Factory();
  }
}

package org.wordpress.android.ui.themes;

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
public final class ThemeBrowserUtils_Factory implements Factory<ThemeBrowserUtils> {
  @Override
  public ThemeBrowserUtils get() {
    return newInstance();
  }

  public static ThemeBrowserUtils_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ThemeBrowserUtils newInstance() {
    return new ThemeBrowserUtils();
  }

  private static final class InstanceHolder {
    private static final ThemeBrowserUtils_Factory INSTANCE = new ThemeBrowserUtils_Factory();
  }
}

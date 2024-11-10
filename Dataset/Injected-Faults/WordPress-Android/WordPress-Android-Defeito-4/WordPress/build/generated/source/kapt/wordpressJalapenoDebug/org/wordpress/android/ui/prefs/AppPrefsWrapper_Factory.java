package org.wordpress.android.ui.prefs;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppPrefsWrapper_Factory implements Factory<AppPrefsWrapper> {
  @Override
  public AppPrefsWrapper get() {
    return newInstance();
  }

  public static AppPrefsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppPrefsWrapper newInstance() {
    return new AppPrefsWrapper();
  }

  private static final class InstanceHolder {
    private static final AppPrefsWrapper_Factory INSTANCE = new AppPrefsWrapper_Factory();
  }
}

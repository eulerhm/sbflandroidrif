package org.wordpress.android.ui.plugins;

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
public final class PluginUtilsWrapper_Factory implements Factory<PluginUtilsWrapper> {
  @Override
  public PluginUtilsWrapper get() {
    return newInstance();
  }

  public static PluginUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PluginUtilsWrapper newInstance() {
    return new PluginUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final PluginUtilsWrapper_Factory INSTANCE = new PluginUtilsWrapper_Factory();
  }
}

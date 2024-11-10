package org.wordpress.android.ui.jetpack.common.providers;

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
public final class JetpackAvailableItemsProvider_Factory implements Factory<JetpackAvailableItemsProvider> {
  @Override
  public JetpackAvailableItemsProvider get() {
    return newInstance();
  }

  public static JetpackAvailableItemsProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static JetpackAvailableItemsProvider newInstance() {
    return new JetpackAvailableItemsProvider();
  }

  private static final class InstanceHolder {
    private static final JetpackAvailableItemsProvider_Factory INSTANCE = new JetpackAvailableItemsProvider_Factory();
  }
}

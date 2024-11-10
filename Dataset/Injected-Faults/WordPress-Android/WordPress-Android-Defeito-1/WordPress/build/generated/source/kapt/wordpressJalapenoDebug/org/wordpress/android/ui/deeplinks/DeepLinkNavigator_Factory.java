package org.wordpress.android.ui.deeplinks;

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
public final class DeepLinkNavigator_Factory implements Factory<DeepLinkNavigator> {
  @Override
  public DeepLinkNavigator get() {
    return newInstance();
  }

  public static DeepLinkNavigator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DeepLinkNavigator newInstance() {
    return new DeepLinkNavigator();
  }

  private static final class InstanceHolder {
    private static final DeepLinkNavigator_Factory INSTANCE = new DeepLinkNavigator_Factory();
  }
}

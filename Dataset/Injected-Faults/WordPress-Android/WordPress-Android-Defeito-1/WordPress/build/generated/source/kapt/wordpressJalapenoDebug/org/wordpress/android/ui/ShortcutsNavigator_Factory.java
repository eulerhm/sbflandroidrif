package org.wordpress.android.ui;

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
public final class ShortcutsNavigator_Factory implements Factory<ShortcutsNavigator> {
  @Override
  public ShortcutsNavigator get() {
    return newInstance();
  }

  public static ShortcutsNavigator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ShortcutsNavigator newInstance() {
    return new ShortcutsNavigator();
  }

  private static final class InstanceHolder {
    private static final ShortcutsNavigator_Factory INSTANCE = new ShortcutsNavigator_Factory();
  }
}

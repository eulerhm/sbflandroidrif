package org.wordpress.android.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class ShortcutUtils_Factory implements Factory<ShortcutUtils> {
  private final Provider<Context> contextProvider;

  public ShortcutUtils_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ShortcutUtils get() {
    return newInstance(contextProvider.get());
  }

  public static ShortcutUtils_Factory create(Provider<Context> contextProvider) {
    return new ShortcutUtils_Factory(contextProvider);
  }

  public static ShortcutUtils newInstance(Context context) {
    return new ShortcutUtils(context);
  }
}

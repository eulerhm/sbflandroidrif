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
public final class DebugUtils_Factory implements Factory<DebugUtils> {
  private final Provider<Context> contextProvider;

  public DebugUtils_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DebugUtils get() {
    return newInstance(contextProvider.get());
  }

  public static DebugUtils_Factory create(Provider<Context> contextProvider) {
    return new DebugUtils_Factory(contextProvider);
  }

  public static DebugUtils newInstance(Context context) {
    return new DebugUtils(context);
  }
}

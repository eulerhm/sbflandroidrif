package org.wordpress.android.ui.photopicker;

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
public final class PermissionsHandler_Factory implements Factory<PermissionsHandler> {
  private final Provider<Context> contextProvider;

  public PermissionsHandler_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PermissionsHandler get() {
    return newInstance(contextProvider.get());
  }

  public static PermissionsHandler_Factory create(Provider<Context> contextProvider) {
    return new PermissionsHandler_Factory(contextProvider);
  }

  public static PermissionsHandler newInstance(Context context) {
    return new PermissionsHandler(context);
  }
}

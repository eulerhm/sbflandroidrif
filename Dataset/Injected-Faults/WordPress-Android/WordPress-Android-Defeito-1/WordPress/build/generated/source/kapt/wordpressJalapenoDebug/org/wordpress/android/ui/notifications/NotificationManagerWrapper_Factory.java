package org.wordpress.android.ui.notifications;

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
public final class NotificationManagerWrapper_Factory implements Factory<NotificationManagerWrapper> {
  private final Provider<Context> contextProvider;

  public NotificationManagerWrapper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NotificationManagerWrapper get() {
    return newInstance(contextProvider.get());
  }

  public static NotificationManagerWrapper_Factory create(Provider<Context> contextProvider) {
    return new NotificationManagerWrapper_Factory(contextProvider);
  }

  public static NotificationManagerWrapper newInstance(Context context) {
    return new NotificationManagerWrapper(context);
  }
}

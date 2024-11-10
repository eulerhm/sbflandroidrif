package org.wordpress.android.workers.notification.local;

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
public final class LocalNotificationScheduler_Factory implements Factory<LocalNotificationScheduler> {
  private final Provider<Context> contextProvider;

  public LocalNotificationScheduler_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocalNotificationScheduler get() {
    return newInstance(contextProvider.get());
  }

  public static LocalNotificationScheduler_Factory create(Provider<Context> contextProvider) {
    return new LocalNotificationScheduler_Factory(contextProvider);
  }

  public static LocalNotificationScheduler newInstance(Context context) {
    return new LocalNotificationScheduler(context);
  }
}

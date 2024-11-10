package org.wordpress.android.ui.notifications.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata("dagger.Reusable")
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
public final class NotificationsActionsWrapper_Factory implements Factory<NotificationsActionsWrapper> {
  @Override
  public NotificationsActionsWrapper get() {
    return newInstance();
  }

  public static NotificationsActionsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NotificationsActionsWrapper newInstance() {
    return new NotificationsActionsWrapper();
  }

  private static final class InstanceHolder {
    private static final NotificationsActionsWrapper_Factory INSTANCE = new NotificationsActionsWrapper_Factory();
  }
}

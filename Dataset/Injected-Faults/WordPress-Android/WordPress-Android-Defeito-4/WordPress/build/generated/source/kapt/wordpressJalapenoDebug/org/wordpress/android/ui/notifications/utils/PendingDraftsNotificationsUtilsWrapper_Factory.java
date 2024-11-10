package org.wordpress.android.ui.notifications.utils;

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
public final class PendingDraftsNotificationsUtilsWrapper_Factory implements Factory<PendingDraftsNotificationsUtilsWrapper> {
  @Override
  public PendingDraftsNotificationsUtilsWrapper get() {
    return newInstance();
  }

  public static PendingDraftsNotificationsUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PendingDraftsNotificationsUtilsWrapper newInstance() {
    return new PendingDraftsNotificationsUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final PendingDraftsNotificationsUtilsWrapper_Factory INSTANCE = new PendingDraftsNotificationsUtilsWrapper_Factory();
  }
}

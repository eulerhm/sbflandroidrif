package org.wordpress.android.ui.deeplinks.handlers;

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
public final class NotificationsLinkHandler_Factory implements Factory<NotificationsLinkHandler> {
  @Override
  public NotificationsLinkHandler get() {
    return newInstance();
  }

  public static NotificationsLinkHandler_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NotificationsLinkHandler newInstance() {
    return new NotificationsLinkHandler();
  }

  private static final class InstanceHolder {
    private static final NotificationsLinkHandler_Factory INSTANCE = new NotificationsLinkHandler_Factory();
  }
}

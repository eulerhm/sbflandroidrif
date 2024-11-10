package org.wordpress.android.ui.notifications.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.tools.FormattableContentMapper;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NotificationsUtilsWrapper_Factory implements Factory<NotificationsUtilsWrapper> {
  private final Provider<FormattableContentMapper> formattableContentMapperProvider;

  public NotificationsUtilsWrapper_Factory(
      Provider<FormattableContentMapper> formattableContentMapperProvider) {
    this.formattableContentMapperProvider = formattableContentMapperProvider;
  }

  @Override
  public NotificationsUtilsWrapper get() {
    return newInstance(formattableContentMapperProvider.get());
  }

  public static NotificationsUtilsWrapper_Factory create(
      Provider<FormattableContentMapper> formattableContentMapperProvider) {
    return new NotificationsUtilsWrapper_Factory(formattableContentMapperProvider);
  }

  public static NotificationsUtilsWrapper newInstance(
      FormattableContentMapper formattableContentMapper) {
    return new NotificationsUtilsWrapper(formattableContentMapper);
  }
}

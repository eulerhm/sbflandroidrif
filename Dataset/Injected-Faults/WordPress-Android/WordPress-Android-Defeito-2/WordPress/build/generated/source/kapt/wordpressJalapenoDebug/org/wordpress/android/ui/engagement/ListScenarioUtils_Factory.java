package org.wordpress.android.ui.engagement;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.util.image.ImageManager;

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
public final class ListScenarioUtils_Factory implements Factory<ListScenarioUtils> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider;

  public ListScenarioUtils_Factory(Provider<ImageManager> imageManagerProvider,
      Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.notificationsUtilsWrapperProvider = notificationsUtilsWrapperProvider;
  }

  @Override
  public ListScenarioUtils get() {
    return newInstance(imageManagerProvider.get(), notificationsUtilsWrapperProvider.get());
  }

  public static ListScenarioUtils_Factory create(Provider<ImageManager> imageManagerProvider,
      Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider) {
    return new ListScenarioUtils_Factory(imageManagerProvider, notificationsUtilsWrapperProvider);
  }

  public static ListScenarioUtils newInstance(ImageManager imageManager,
      NotificationsUtilsWrapper notificationsUtilsWrapper) {
    return new ListScenarioUtils(imageManager, notificationsUtilsWrapper);
  }
}

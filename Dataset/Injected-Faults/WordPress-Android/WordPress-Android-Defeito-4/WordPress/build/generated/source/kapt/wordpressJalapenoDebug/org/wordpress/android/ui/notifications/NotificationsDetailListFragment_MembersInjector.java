package org.wordpress.android.ui.notifications;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.engagement.ListScenarioUtils;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.util.image.ImageManager;

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
public final class NotificationsDetailListFragment_MembersInjector implements MembersInjector<NotificationsDetailListFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider;

  private final Provider<ListScenarioUtils> listScenarioUtilsProvider;

  public NotificationsDetailListFragment_MembersInjector(
      Provider<ImageManager> imageManagerProvider,
      Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider,
      Provider<ListScenarioUtils> listScenarioUtilsProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.notificationsUtilsWrapperProvider = notificationsUtilsWrapperProvider;
    this.listScenarioUtilsProvider = listScenarioUtilsProvider;
  }

  public static MembersInjector<NotificationsDetailListFragment> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider,
      Provider<ListScenarioUtils> listScenarioUtilsProvider) {
    return new NotificationsDetailListFragment_MembersInjector(imageManagerProvider, notificationsUtilsWrapperProvider, listScenarioUtilsProvider);
  }

  @Override
  public void injectMembers(NotificationsDetailListFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectNotificationsUtilsWrapper(instance, notificationsUtilsWrapperProvider.get());
    injectListScenarioUtils(instance, listScenarioUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailListFragment.imageManager")
  public static void injectImageManager(NotificationsDetailListFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailListFragment.notificationsUtilsWrapper")
  public static void injectNotificationsUtilsWrapper(NotificationsDetailListFragment instance,
      NotificationsUtilsWrapper notificationsUtilsWrapper) {
    instance.notificationsUtilsWrapper = notificationsUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailListFragment.listScenarioUtils")
  public static void injectListScenarioUtils(NotificationsDetailListFragment instance,
      ListScenarioUtils listScenarioUtils) {
    instance.listScenarioUtils = listScenarioUtils;
  }
}

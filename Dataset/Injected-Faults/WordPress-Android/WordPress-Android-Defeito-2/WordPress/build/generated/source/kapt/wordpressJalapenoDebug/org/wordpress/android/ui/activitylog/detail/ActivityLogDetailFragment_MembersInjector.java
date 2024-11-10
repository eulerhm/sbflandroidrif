package org.wordpress.android.ui.activitylog.detail;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.notifications.utils.FormattableContentClickHandler;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.ui.utils.UiHelpers;
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
public final class ActivityLogDetailFragment_MembersInjector implements MembersInjector<ActivityLogDetailFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider;

  private final Provider<FormattableContentClickHandler> formattableContentClickHandlerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public ActivityLogDetailFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider,
      Provider<FormattableContentClickHandler> formattableContentClickHandlerProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.notificationsUtilsWrapperProvider = notificationsUtilsWrapperProvider;
    this.formattableContentClickHandlerProvider = formattableContentClickHandlerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<ActivityLogDetailFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<NotificationsUtilsWrapper> notificationsUtilsWrapperProvider,
      Provider<FormattableContentClickHandler> formattableContentClickHandlerProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    return new ActivityLogDetailFragment_MembersInjector(viewModelFactoryProvider, imageManagerProvider, notificationsUtilsWrapperProvider, formattableContentClickHandlerProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(ActivityLogDetailFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectNotificationsUtilsWrapper(instance, notificationsUtilsWrapperProvider.get());
    injectFormattableContentClickHandler(instance, formattableContentClickHandlerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.activitylog.detail.ActivityLogDetailFragment.viewModelFactory")
  public static void injectViewModelFactory(ActivityLogDetailFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.activitylog.detail.ActivityLogDetailFragment.imageManager")
  public static void injectImageManager(ActivityLogDetailFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.activitylog.detail.ActivityLogDetailFragment.notificationsUtilsWrapper")
  public static void injectNotificationsUtilsWrapper(ActivityLogDetailFragment instance,
      NotificationsUtilsWrapper notificationsUtilsWrapper) {
    instance.notificationsUtilsWrapper = notificationsUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.activitylog.detail.ActivityLogDetailFragment.formattableContentClickHandler")
  public static void injectFormattableContentClickHandler(ActivityLogDetailFragment instance,
      FormattableContentClickHandler formattableContentClickHandler) {
    instance.formattableContentClickHandler = formattableContentClickHandler;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.activitylog.detail.ActivityLogDetailFragment.uiHelpers")
  public static void injectUiHelpers(ActivityLogDetailFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}

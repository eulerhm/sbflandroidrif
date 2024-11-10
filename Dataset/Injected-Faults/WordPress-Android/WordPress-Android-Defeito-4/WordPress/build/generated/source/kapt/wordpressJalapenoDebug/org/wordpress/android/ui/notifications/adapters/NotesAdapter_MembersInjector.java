package org.wordpress.android.ui.notifications.adapters;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.models.NoticonUtils;
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
public final class NotesAdapter_MembersInjector implements MembersInjector<NotesAdapter> {
  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<NotificationsUtilsWrapper> mNotificationsUtilsWrapperProvider;

  private final Provider<NoticonUtils> mNoticonUtilsProvider;

  public NotesAdapter_MembersInjector(Provider<ImageManager> mImageManagerProvider,
      Provider<NotificationsUtilsWrapper> mNotificationsUtilsWrapperProvider,
      Provider<NoticonUtils> mNoticonUtilsProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
    this.mNotificationsUtilsWrapperProvider = mNotificationsUtilsWrapperProvider;
    this.mNoticonUtilsProvider = mNoticonUtilsProvider;
  }

  public static MembersInjector<NotesAdapter> create(Provider<ImageManager> mImageManagerProvider,
      Provider<NotificationsUtilsWrapper> mNotificationsUtilsWrapperProvider,
      Provider<NoticonUtils> mNoticonUtilsProvider) {
    return new NotesAdapter_MembersInjector(mImageManagerProvider, mNotificationsUtilsWrapperProvider, mNoticonUtilsProvider);
  }

  @Override
  public void injectMembers(NotesAdapter instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMNotificationsUtilsWrapper(instance, mNotificationsUtilsWrapperProvider.get());
    injectMNoticonUtils(instance, mNoticonUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.adapters.NotesAdapter.mImageManager")
  public static void injectMImageManager(NotesAdapter instance, ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.adapters.NotesAdapter.mNotificationsUtilsWrapper")
  public static void injectMNotificationsUtilsWrapper(NotesAdapter instance,
      NotificationsUtilsWrapper mNotificationsUtilsWrapper) {
    instance.mNotificationsUtilsWrapper = mNotificationsUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.adapters.NotesAdapter.mNoticonUtils")
  public static void injectMNoticonUtils(NotesAdapter instance, NoticonUtils mNoticonUtils) {
    instance.mNoticonUtils = mNoticonUtils;
  }
}

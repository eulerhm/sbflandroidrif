package org.wordpress.android.ui.notifications.receivers;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;

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
public final class NotificationsPendingDraftsReceiver_MembersInjector implements MembersInjector<NotificationsPendingDraftsReceiver> {
  private final Provider<PostStore> mPostStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  public NotificationsPendingDraftsReceiver_MembersInjector(Provider<PostStore> mPostStoreProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    this.mPostStoreProvider = mPostStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mSystemNotificationsTrackerProvider = mSystemNotificationsTrackerProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
  }

  public static MembersInjector<NotificationsPendingDraftsReceiver> create(
      Provider<PostStore> mPostStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    return new NotificationsPendingDraftsReceiver_MembersInjector(mPostStoreProvider, mSiteStoreProvider, mSystemNotificationsTrackerProvider, mSelectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(NotificationsPendingDraftsReceiver instance) {
    injectMPostStore(instance, mPostStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMSystemNotificationsTracker(instance, mSystemNotificationsTrackerProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.receivers.NotificationsPendingDraftsReceiver.mPostStore")
  public static void injectMPostStore(NotificationsPendingDraftsReceiver instance,
      PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.receivers.NotificationsPendingDraftsReceiver.mSiteStore")
  public static void injectMSiteStore(NotificationsPendingDraftsReceiver instance,
      SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.receivers.NotificationsPendingDraftsReceiver.mSystemNotificationsTracker")
  public static void injectMSystemNotificationsTracker(NotificationsPendingDraftsReceiver instance,
      SystemNotificationsTracker mSystemNotificationsTracker) {
    instance.mSystemNotificationsTracker = mSystemNotificationsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.receivers.NotificationsPendingDraftsReceiver.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(NotificationsPendingDraftsReceiver instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }
}

package org.wordpress.android.push;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.comments.unified.CommentsStoreAdapter;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.quickstart.QuickStartTracker;

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
public final class NotificationsProcessingService_MembersInjector implements MembersInjector<NotificationsProcessingService> {
  private final Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider;

  private final Provider<GCMMessageHandler> mGCMMessageHandlerProvider;

  private final Provider<QuickStartTracker> mQuickStartTrackerProvider;

  public NotificationsProcessingService_MembersInjector(
      Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<GCMMessageHandler> mGCMMessageHandlerProvider,
      Provider<QuickStartTracker> mQuickStartTrackerProvider) {
    this.mCommentsStoreAdapterProvider = mCommentsStoreAdapterProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mSystemNotificationsTrackerProvider = mSystemNotificationsTrackerProvider;
    this.mGCMMessageHandlerProvider = mGCMMessageHandlerProvider;
    this.mQuickStartTrackerProvider = mQuickStartTrackerProvider;
  }

  public static MembersInjector<NotificationsProcessingService> create(
      Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<GCMMessageHandler> mGCMMessageHandlerProvider,
      Provider<QuickStartTracker> mQuickStartTrackerProvider) {
    return new NotificationsProcessingService_MembersInjector(mCommentsStoreAdapterProvider, mSiteStoreProvider, mSystemNotificationsTrackerProvider, mGCMMessageHandlerProvider, mQuickStartTrackerProvider);
  }

  @Override
  public void injectMembers(NotificationsProcessingService instance) {
    injectMCommentsStoreAdapter(instance, mCommentsStoreAdapterProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMSystemNotificationsTracker(instance, mSystemNotificationsTrackerProvider.get());
    injectMGCMMessageHandler(instance, mGCMMessageHandlerProvider.get());
    injectMQuickStartTracker(instance, mQuickStartTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.push.NotificationsProcessingService.mCommentsStoreAdapter")
  public static void injectMCommentsStoreAdapter(NotificationsProcessingService instance,
      CommentsStoreAdapter mCommentsStoreAdapter) {
    instance.mCommentsStoreAdapter = mCommentsStoreAdapter;
  }

  @InjectedFieldSignature("org.wordpress.android.push.NotificationsProcessingService.mSiteStore")
  public static void injectMSiteStore(NotificationsProcessingService instance,
      SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.push.NotificationsProcessingService.mSystemNotificationsTracker")
  public static void injectMSystemNotificationsTracker(NotificationsProcessingService instance,
      SystemNotificationsTracker mSystemNotificationsTracker) {
    instance.mSystemNotificationsTracker = mSystemNotificationsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.push.NotificationsProcessingService.mGCMMessageHandler")
  public static void injectMGCMMessageHandler(NotificationsProcessingService instance,
      GCMMessageHandler mGCMMessageHandler) {
    instance.mGCMMessageHandler = mGCMMessageHandler;
  }

  @InjectedFieldSignature("org.wordpress.android.push.NotificationsProcessingService.mQuickStartTracker")
  public static void injectMQuickStartTracker(NotificationsProcessingService instance,
      QuickStartTracker mQuickStartTracker) {
    instance.mQuickStartTracker = mQuickStartTracker;
  }
}

package org.wordpress.android.ui.quickstart;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
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
public final class QuickStartReminderReceiver_MembersInjector implements MembersInjector<QuickStartReminderReceiver> {
  private final Provider<QuickStartStore> mQuickStartStoreProvider;

  private final Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  private final Provider<QuickStartRepository> mQuickStartRepositoryProvider;

  private final Provider<QuickStartTracker> mQuickStartTrackerProvider;

  public QuickStartReminderReceiver_MembersInjector(
      Provider<QuickStartStore> mQuickStartStoreProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<QuickStartTracker> mQuickStartTrackerProvider) {
    this.mQuickStartStoreProvider = mQuickStartStoreProvider;
    this.mSystemNotificationsTrackerProvider = mSystemNotificationsTrackerProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
    this.mQuickStartRepositoryProvider = mQuickStartRepositoryProvider;
    this.mQuickStartTrackerProvider = mQuickStartTrackerProvider;
  }

  public static MembersInjector<QuickStartReminderReceiver> create(
      Provider<QuickStartStore> mQuickStartStoreProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<QuickStartTracker> mQuickStartTrackerProvider) {
    return new QuickStartReminderReceiver_MembersInjector(mQuickStartStoreProvider, mSystemNotificationsTrackerProvider, mSelectedSiteRepositoryProvider, mQuickStartRepositoryProvider, mQuickStartTrackerProvider);
  }

  @Override
  public void injectMembers(QuickStartReminderReceiver instance) {
    injectMQuickStartStore(instance, mQuickStartStoreProvider.get());
    injectMSystemNotificationsTracker(instance, mSystemNotificationsTrackerProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
    injectMQuickStartRepository(instance, mQuickStartRepositoryProvider.get());
    injectMQuickStartTracker(instance, mQuickStartTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartReminderReceiver.mQuickStartStore")
  public static void injectMQuickStartStore(QuickStartReminderReceiver instance,
      QuickStartStore mQuickStartStore) {
    instance.mQuickStartStore = mQuickStartStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartReminderReceiver.mSystemNotificationsTracker")
  public static void injectMSystemNotificationsTracker(QuickStartReminderReceiver instance,
      SystemNotificationsTracker mSystemNotificationsTracker) {
    instance.mSystemNotificationsTracker = mSystemNotificationsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartReminderReceiver.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(QuickStartReminderReceiver instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartReminderReceiver.mQuickStartRepository")
  public static void injectMQuickStartRepository(QuickStartReminderReceiver instance,
      QuickStartRepository mQuickStartRepository) {
    instance.mQuickStartRepository = mQuickStartRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartReminderReceiver.mQuickStartTracker")
  public static void injectMQuickStartTracker(QuickStartReminderReceiver instance,
      QuickStartTracker mQuickStartTracker) {
    instance.mQuickStartTracker = mQuickStartTracker;
  }
}

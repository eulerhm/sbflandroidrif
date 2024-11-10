package org.wordpress.android.viewmodel.activitylog;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.ActivityLogStore;
import org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase;
import org.wordpress.android.ui.jetpack.backup.download.usecases.GetBackupDownloadStatusUseCase;
import org.wordpress.android.ui.jetpack.backup.download.usecases.PostDismissBackupDownloadUseCase;
import org.wordpress.android.ui.jetpack.restore.usecases.GetRestoreStatusUseCase;
import org.wordpress.android.ui.stats.refresh.utils.DateUtils;
import org.wordpress.android.util.analytics.ActivityLogTracker;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class ActivityLogViewModel_Factory implements Factory<ActivityLogViewModel> {
  private final Provider<ActivityLogStore> activityLogStoreProvider;

  private final Provider<GetRestoreStatusUseCase> getRestoreStatusUseCaseProvider;

  private final Provider<GetBackupDownloadStatusUseCase> getBackupDownloadStatusUseCaseProvider;

  private final Provider<PostDismissBackupDownloadUseCase> postDismissBackupDownloadUseCaseProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<DateUtils> dateUtilsProvider;

  private final Provider<ActivityLogTracker> activityLogTrackerProvider;

  private final Provider<JetpackCapabilitiesUseCase> jetpackCapabilitiesUseCaseProvider;

  public ActivityLogViewModel_Factory(Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<GetRestoreStatusUseCase> getRestoreStatusUseCaseProvider,
      Provider<GetBackupDownloadStatusUseCase> getBackupDownloadStatusUseCaseProvider,
      Provider<PostDismissBackupDownloadUseCase> postDismissBackupDownloadUseCaseProvider,
      Provider<ResourceProvider> resourceProvider, Provider<DateUtils> dateUtilsProvider,
      Provider<ActivityLogTracker> activityLogTrackerProvider,
      Provider<JetpackCapabilitiesUseCase> jetpackCapabilitiesUseCaseProvider) {
    this.activityLogStoreProvider = activityLogStoreProvider;
    this.getRestoreStatusUseCaseProvider = getRestoreStatusUseCaseProvider;
    this.getBackupDownloadStatusUseCaseProvider = getBackupDownloadStatusUseCaseProvider;
    this.postDismissBackupDownloadUseCaseProvider = postDismissBackupDownloadUseCaseProvider;
    this.resourceProvider = resourceProvider;
    this.dateUtilsProvider = dateUtilsProvider;
    this.activityLogTrackerProvider = activityLogTrackerProvider;
    this.jetpackCapabilitiesUseCaseProvider = jetpackCapabilitiesUseCaseProvider;
  }

  @Override
  public ActivityLogViewModel get() {
    return newInstance(activityLogStoreProvider.get(), getRestoreStatusUseCaseProvider.get(), getBackupDownloadStatusUseCaseProvider.get(), postDismissBackupDownloadUseCaseProvider.get(), resourceProvider.get(), dateUtilsProvider.get(), activityLogTrackerProvider.get(), jetpackCapabilitiesUseCaseProvider.get());
  }

  public static ActivityLogViewModel_Factory create(
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<GetRestoreStatusUseCase> getRestoreStatusUseCaseProvider,
      Provider<GetBackupDownloadStatusUseCase> getBackupDownloadStatusUseCaseProvider,
      Provider<PostDismissBackupDownloadUseCase> postDismissBackupDownloadUseCaseProvider,
      Provider<ResourceProvider> resourceProvider, Provider<DateUtils> dateUtilsProvider,
      Provider<ActivityLogTracker> activityLogTrackerProvider,
      Provider<JetpackCapabilitiesUseCase> jetpackCapabilitiesUseCaseProvider) {
    return new ActivityLogViewModel_Factory(activityLogStoreProvider, getRestoreStatusUseCaseProvider, getBackupDownloadStatusUseCaseProvider, postDismissBackupDownloadUseCaseProvider, resourceProvider, dateUtilsProvider, activityLogTrackerProvider, jetpackCapabilitiesUseCaseProvider);
  }

  public static ActivityLogViewModel newInstance(ActivityLogStore activityLogStore,
      GetRestoreStatusUseCase getRestoreStatusUseCase,
      GetBackupDownloadStatusUseCase getBackupDownloadStatusUseCase,
      PostDismissBackupDownloadUseCase postDismissBackupDownloadUseCase,
      ResourceProvider resourceProvider, DateUtils dateUtils, ActivityLogTracker activityLogTracker,
      JetpackCapabilitiesUseCase jetpackCapabilitiesUseCase) {
    return new ActivityLogViewModel(activityLogStore, getRestoreStatusUseCase, getBackupDownloadStatusUseCase, postDismissBackupDownloadUseCase, resourceProvider, dateUtils, activityLogTracker, jetpackCapabilitiesUseCase);
  }
}

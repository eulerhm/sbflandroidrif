package org.wordpress.android.ui.jetpack.backup.download;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.jetpack.backup.download.builders.BackupDownloadStateListItemBuilder;
import org.wordpress.android.ui.jetpack.backup.download.usecases.GetBackupDownloadStatusUseCase;
import org.wordpress.android.ui.jetpack.backup.download.usecases.PostBackupDownloadUseCase;
import org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider;
import org.wordpress.android.ui.jetpack.usecases.GetActivityLogItemUseCase;
import org.wordpress.android.util.wizard.WizardManager;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BackupDownloadViewModel_Factory implements Factory<BackupDownloadViewModel> {
  private final Provider<WizardManager<BackupDownloadStep>> wizardManagerProvider;

  private final Provider<JetpackAvailableItemsProvider> availableItemsProvider;

  private final Provider<GetActivityLogItemUseCase> getActivityLogItemUseCaseProvider;

  private final Provider<BackupDownloadStateListItemBuilder> stateListItemBuilderProvider;

  private final Provider<PostBackupDownloadUseCase> postBackupDownloadUseCaseProvider;

  private final Provider<GetBackupDownloadStatusUseCase> getBackupDownloadStatusUseCaseProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public BackupDownloadViewModel_Factory(
      Provider<WizardManager<BackupDownloadStep>> wizardManagerProvider,
      Provider<JetpackAvailableItemsProvider> availableItemsProvider,
      Provider<GetActivityLogItemUseCase> getActivityLogItemUseCaseProvider,
      Provider<BackupDownloadStateListItemBuilder> stateListItemBuilderProvider,
      Provider<PostBackupDownloadUseCase> postBackupDownloadUseCaseProvider,
      Provider<GetBackupDownloadStatusUseCase> getBackupDownloadStatusUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.wizardManagerProvider = wizardManagerProvider;
    this.availableItemsProvider = availableItemsProvider;
    this.getActivityLogItemUseCaseProvider = getActivityLogItemUseCaseProvider;
    this.stateListItemBuilderProvider = stateListItemBuilderProvider;
    this.postBackupDownloadUseCaseProvider = postBackupDownloadUseCaseProvider;
    this.getBackupDownloadStatusUseCaseProvider = getBackupDownloadStatusUseCaseProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public BackupDownloadViewModel get() {
    return newInstance(wizardManagerProvider.get(), availableItemsProvider.get(), getActivityLogItemUseCaseProvider.get(), stateListItemBuilderProvider.get(), postBackupDownloadUseCaseProvider.get(), getBackupDownloadStatusUseCaseProvider.get(), mainDispatcherProvider.get());
  }

  public static BackupDownloadViewModel_Factory create(
      Provider<WizardManager<BackupDownloadStep>> wizardManagerProvider,
      Provider<JetpackAvailableItemsProvider> availableItemsProvider,
      Provider<GetActivityLogItemUseCase> getActivityLogItemUseCaseProvider,
      Provider<BackupDownloadStateListItemBuilder> stateListItemBuilderProvider,
      Provider<PostBackupDownloadUseCase> postBackupDownloadUseCaseProvider,
      Provider<GetBackupDownloadStatusUseCase> getBackupDownloadStatusUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new BackupDownloadViewModel_Factory(wizardManagerProvider, availableItemsProvider, getActivityLogItemUseCaseProvider, stateListItemBuilderProvider, postBackupDownloadUseCaseProvider, getBackupDownloadStatusUseCaseProvider, mainDispatcherProvider);
  }

  public static BackupDownloadViewModel newInstance(WizardManager<BackupDownloadStep> wizardManager,
      JetpackAvailableItemsProvider availableItemsProvider,
      GetActivityLogItemUseCase getActivityLogItemUseCase,
      BackupDownloadStateListItemBuilder stateListItemBuilder,
      PostBackupDownloadUseCase postBackupDownloadUseCase,
      GetBackupDownloadStatusUseCase getBackupDownloadStatusUseCase,
      CoroutineDispatcher mainDispatcher) {
    return new BackupDownloadViewModel(wizardManager, availableItemsProvider, getActivityLogItemUseCase, stateListItemBuilder, postBackupDownloadUseCase, getBackupDownloadStatusUseCase, mainDispatcher);
  }
}

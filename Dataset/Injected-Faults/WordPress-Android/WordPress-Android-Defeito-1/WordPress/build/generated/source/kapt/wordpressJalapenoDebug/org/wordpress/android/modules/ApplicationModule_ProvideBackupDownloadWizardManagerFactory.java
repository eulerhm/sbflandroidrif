package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStep;
import org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStepsProvider;
import org.wordpress.android.util.wizard.WizardManager;

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
public final class ApplicationModule_ProvideBackupDownloadWizardManagerFactory implements Factory<WizardManager<BackupDownloadStep>> {
  private final Provider<BackupDownloadStepsProvider> stepsProvider;

  public ApplicationModule_ProvideBackupDownloadWizardManagerFactory(
      Provider<BackupDownloadStepsProvider> stepsProvider) {
    this.stepsProvider = stepsProvider;
  }

  @Override
  public WizardManager<BackupDownloadStep> get() {
    return provideBackupDownloadWizardManager(stepsProvider.get());
  }

  public static ApplicationModule_ProvideBackupDownloadWizardManagerFactory create(
      Provider<BackupDownloadStepsProvider> stepsProvider) {
    return new ApplicationModule_ProvideBackupDownloadWizardManagerFactory(stepsProvider);
  }

  public static WizardManager<BackupDownloadStep> provideBackupDownloadWizardManager(
      BackupDownloadStepsProvider stepsProvider) {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.provideBackupDownloadWizardManager(stepsProvider));
  }
}

package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.jetpack.restore.RestoreStep;
import org.wordpress.android.ui.jetpack.restore.RestoreStepsProvider;
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
public final class ApplicationModule_ProvideRestoreWizardManagerFactory implements Factory<WizardManager<RestoreStep>> {
  private final Provider<RestoreStepsProvider> stepsProvider;

  public ApplicationModule_ProvideRestoreWizardManagerFactory(
      Provider<RestoreStepsProvider> stepsProvider) {
    this.stepsProvider = stepsProvider;
  }

  @Override
  public WizardManager<RestoreStep> get() {
    return provideRestoreWizardManager(stepsProvider.get());
  }

  public static ApplicationModule_ProvideRestoreWizardManagerFactory create(
      Provider<RestoreStepsProvider> stepsProvider) {
    return new ApplicationModule_ProvideRestoreWizardManagerFactory(stepsProvider);
  }

  public static WizardManager<RestoreStep> provideRestoreWizardManager(
      RestoreStepsProvider stepsProvider) {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.provideRestoreWizardManager(stepsProvider));
  }
}

package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.sitecreation.SiteCreationStep;
import org.wordpress.android.ui.sitecreation.SiteCreationStepsProvider;
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
public final class ApplicationModule_ProvideWizardManagerFactory implements Factory<WizardManager<SiteCreationStep>> {
  private final Provider<SiteCreationStepsProvider> stepsProvider;

  public ApplicationModule_ProvideWizardManagerFactory(
      Provider<SiteCreationStepsProvider> stepsProvider) {
    this.stepsProvider = stepsProvider;
  }

  @Override
  public WizardManager<SiteCreationStep> get() {
    return provideWizardManager(stepsProvider.get());
  }

  public static ApplicationModule_ProvideWizardManagerFactory create(
      Provider<SiteCreationStepsProvider> stepsProvider) {
    return new ApplicationModule_ProvideWizardManagerFactory(stepsProvider);
  }

  public static WizardManager<SiteCreationStep> provideWizardManager(
      SiteCreationStepsProvider stepsProvider) {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.provideWizardManager(stepsProvider));
  }
}

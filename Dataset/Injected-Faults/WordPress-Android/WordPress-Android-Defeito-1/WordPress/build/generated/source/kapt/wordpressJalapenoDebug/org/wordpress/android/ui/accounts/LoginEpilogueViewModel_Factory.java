package org.wordpress.android.ui.accounts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.BuildConfigWrapper;

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
public final class LoginEpilogueViewModel_Factory implements Factory<LoginEpilogueViewModel> {
  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider;

  public LoginEpilogueViewModel_Factory(Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<SiteStore> siteStoreProvider,
      Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider) {
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.mySiteDefaultTabExperimentProvider = mySiteDefaultTabExperimentProvider;
  }

  @Override
  public LoginEpilogueViewModel get() {
    return newInstance(appPrefsWrapperProvider.get(), buildConfigWrapperProvider.get(), siteStoreProvider.get(), mySiteDefaultTabExperimentProvider.get());
  }

  public static LoginEpilogueViewModel_Factory create(
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<SiteStore> siteStoreProvider,
      Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider) {
    return new LoginEpilogueViewModel_Factory(appPrefsWrapperProvider, buildConfigWrapperProvider, siteStoreProvider, mySiteDefaultTabExperimentProvider);
  }

  public static LoginEpilogueViewModel newInstance(AppPrefsWrapper appPrefsWrapper,
      BuildConfigWrapper buildConfigWrapper, SiteStore siteStore,
      MySiteDefaultTabExperiment mySiteDefaultTabExperiment) {
    return new LoginEpilogueViewModel(appPrefsWrapper, buildConfigWrapper, siteStore, mySiteDefaultTabExperiment);
  }
}

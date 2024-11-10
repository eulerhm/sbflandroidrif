package org.wordpress.android.ui.prefs;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment;
import org.wordpress.android.ui.whatsnew.FeatureAnnouncementProvider;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig;
import org.wordpress.android.util.config.UnifiedAboutFeatureConfig;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class AppSettingsFragment_MembersInjector implements MembersInjector<AppSettingsFragment> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<ContextProvider> mContextProvider;

  private final Provider<FeatureAnnouncementProvider> mFeatureAnnouncementProvider;

  private final Provider<BuildConfigWrapper> mBuildConfigWrapperProvider;

  private final Provider<UnifiedAboutFeatureConfig> mUnifiedAboutFeatureConfigProvider;

  private final Provider<MySiteDashboardTabsFeatureConfig> mMySiteDashboardTabsFeatureConfigProvider;

  private final Provider<MySiteDefaultTabExperiment> mMySiteDefaultTabExperimentProvider;

  public AppSettingsFragment_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<AccountStore> mAccountStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ContextProvider> mContextProvider,
      Provider<FeatureAnnouncementProvider> mFeatureAnnouncementProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider,
      Provider<UnifiedAboutFeatureConfig> mUnifiedAboutFeatureConfigProvider,
      Provider<MySiteDashboardTabsFeatureConfig> mMySiteDashboardTabsFeatureConfigProvider,
      Provider<MySiteDefaultTabExperiment> mMySiteDefaultTabExperimentProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mContextProvider = mContextProvider;
    this.mFeatureAnnouncementProvider = mFeatureAnnouncementProvider;
    this.mBuildConfigWrapperProvider = mBuildConfigWrapperProvider;
    this.mUnifiedAboutFeatureConfigProvider = mUnifiedAboutFeatureConfigProvider;
    this.mMySiteDashboardTabsFeatureConfigProvider = mMySiteDashboardTabsFeatureConfigProvider;
    this.mMySiteDefaultTabExperimentProvider = mMySiteDefaultTabExperimentProvider;
  }

  public static MembersInjector<AppSettingsFragment> create(Provider<SiteStore> mSiteStoreProvider,
      Provider<AccountStore> mAccountStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ContextProvider> mContextProvider,
      Provider<FeatureAnnouncementProvider> mFeatureAnnouncementProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider,
      Provider<UnifiedAboutFeatureConfig> mUnifiedAboutFeatureConfigProvider,
      Provider<MySiteDashboardTabsFeatureConfig> mMySiteDashboardTabsFeatureConfigProvider,
      Provider<MySiteDefaultTabExperiment> mMySiteDefaultTabExperimentProvider) {
    return new AppSettingsFragment_MembersInjector(mSiteStoreProvider, mAccountStoreProvider, mDispatcherProvider, mContextProvider, mFeatureAnnouncementProvider, mBuildConfigWrapperProvider, mUnifiedAboutFeatureConfigProvider, mMySiteDashboardTabsFeatureConfigProvider, mMySiteDefaultTabExperimentProvider);
  }

  @Override
  public void injectMembers(AppSettingsFragment instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMContextProvider(instance, mContextProvider.get());
    injectMFeatureAnnouncementProvider(instance, mFeatureAnnouncementProvider.get());
    injectMBuildConfigWrapper(instance, mBuildConfigWrapperProvider.get());
    injectMUnifiedAboutFeatureConfig(instance, mUnifiedAboutFeatureConfigProvider.get());
    injectMMySiteDashboardTabsFeatureConfig(instance, mMySiteDashboardTabsFeatureConfigProvider.get());
    injectMMySiteDefaultTabExperiment(instance, mMySiteDefaultTabExperimentProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mSiteStore")
  public static void injectMSiteStore(AppSettingsFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mAccountStore")
  public static void injectMAccountStore(AppSettingsFragment instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mDispatcher")
  public static void injectMDispatcher(AppSettingsFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mContextProvider")
  public static void injectMContextProvider(AppSettingsFragment instance,
      ContextProvider mContextProvider) {
    instance.mContextProvider = mContextProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mFeatureAnnouncementProvider")
  public static void injectMFeatureAnnouncementProvider(AppSettingsFragment instance,
      FeatureAnnouncementProvider mFeatureAnnouncementProvider) {
    instance.mFeatureAnnouncementProvider = mFeatureAnnouncementProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mBuildConfigWrapper")
  public static void injectMBuildConfigWrapper(AppSettingsFragment instance,
      BuildConfigWrapper mBuildConfigWrapper) {
    instance.mBuildConfigWrapper = mBuildConfigWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mUnifiedAboutFeatureConfig")
  public static void injectMUnifiedAboutFeatureConfig(AppSettingsFragment instance,
      UnifiedAboutFeatureConfig mUnifiedAboutFeatureConfig) {
    instance.mUnifiedAboutFeatureConfig = mUnifiedAboutFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mMySiteDashboardTabsFeatureConfig")
  public static void injectMMySiteDashboardTabsFeatureConfig(AppSettingsFragment instance,
      MySiteDashboardTabsFeatureConfig mMySiteDashboardTabsFeatureConfig) {
    instance.mMySiteDashboardTabsFeatureConfig = mMySiteDashboardTabsFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AppSettingsFragment.mMySiteDefaultTabExperiment")
  public static void injectMMySiteDefaultTabExperiment(AppSettingsFragment instance,
      MySiteDefaultTabExperiment mMySiteDefaultTabExperiment) {
    instance.mMySiteDefaultTabExperiment = mMySiteDefaultTabExperiment;
  }
}

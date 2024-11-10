package org.wordpress.android.ui.main;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.StatsStore;
import org.wordpress.android.util.BuildConfigWrapper;

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
public final class SitePickerActivity_MembersInjector implements MembersInjector<SitePickerActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<StatsStore> mStatsStoreProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<BuildConfigWrapper> mBuildConfigWrapperProvider;

  public SitePickerActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<StatsStore> mStatsStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mStatsStoreProvider = mStatsStoreProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mBuildConfigWrapperProvider = mBuildConfigWrapperProvider;
  }

  public static MembersInjector<SitePickerActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<StatsStore> mStatsStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider) {
    return new SitePickerActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mDispatcherProvider, mStatsStoreProvider, mViewModelFactoryProvider, mBuildConfigWrapperProvider);
  }

  @Override
  public void injectMembers(SitePickerActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMStatsStore(instance, mStatsStoreProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMBuildConfigWrapper(instance, mBuildConfigWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerActivity.mAccountStore")
  public static void injectMAccountStore(SitePickerActivity instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerActivity.mSiteStore")
  public static void injectMSiteStore(SitePickerActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerActivity.mDispatcher")
  public static void injectMDispatcher(SitePickerActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerActivity.mStatsStore")
  public static void injectMStatsStore(SitePickerActivity instance, StatsStore mStatsStore) {
    instance.mStatsStore = mStatsStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerActivity.mViewModelFactory")
  public static void injectMViewModelFactory(SitePickerActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerActivity.mBuildConfigWrapper")
  public static void injectMBuildConfigWrapper(SitePickerActivity instance,
      BuildConfigWrapper mBuildConfigWrapper) {
    instance.mBuildConfigWrapper = mBuildConfigWrapper;
  }
}

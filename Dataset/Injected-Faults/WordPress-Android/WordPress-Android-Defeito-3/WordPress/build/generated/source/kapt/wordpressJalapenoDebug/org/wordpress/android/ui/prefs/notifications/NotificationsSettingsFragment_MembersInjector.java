package org.wordpress.android.ui.prefs.notifications;

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
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.config.BloggingRemindersFeatureConfig;

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
public final class NotificationsSettingsFragment_MembersInjector implements MembersInjector<NotificationsSettingsFragment> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<FollowedBlogsProvider> mFollowedBlogsProvider;

  private final Provider<BuildConfigWrapper> mBuildConfigWrapperProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<BloggingRemindersFeatureConfig> mBloggingRemindersFeatureConfigProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  public NotificationsSettingsFragment_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<FollowedBlogsProvider> mFollowedBlogsProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<BloggingRemindersFeatureConfig> mBloggingRemindersFeatureConfigProvider,
      Provider<UiHelpers> mUiHelpersProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mFollowedBlogsProvider = mFollowedBlogsProvider;
    this.mBuildConfigWrapperProvider = mBuildConfigWrapperProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mBloggingRemindersFeatureConfigProvider = mBloggingRemindersFeatureConfigProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
  }

  public static MembersInjector<NotificationsSettingsFragment> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<Dispatcher> mDispatcherProvider,
      Provider<FollowedBlogsProvider> mFollowedBlogsProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<BloggingRemindersFeatureConfig> mBloggingRemindersFeatureConfigProvider,
      Provider<UiHelpers> mUiHelpersProvider) {
    return new NotificationsSettingsFragment_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mDispatcherProvider, mFollowedBlogsProvider, mBuildConfigWrapperProvider, mViewModelFactoryProvider, mBloggingRemindersFeatureConfigProvider, mUiHelpersProvider);
  }

  @Override
  public void injectMembers(NotificationsSettingsFragment instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMFollowedBlogsProvider(instance, mFollowedBlogsProvider.get());
    injectMBuildConfigWrapper(instance, mBuildConfigWrapperProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMBloggingRemindersFeatureConfig(instance, mBloggingRemindersFeatureConfigProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.notifications.NotificationsSettingsFragment.mAccountStore")
  public static void injectMAccountStore(NotificationsSettingsFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.notifications.NotificationsSettingsFragment.mSiteStore")
  public static void injectMSiteStore(NotificationsSettingsFragment instance,
      SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.notifications.NotificationsSettingsFragment.mDispatcher")
  public static void injectMDispatcher(NotificationsSettingsFragment instance,
      Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.notifications.NotificationsSettingsFragment.mFollowedBlogsProvider")
  public static void injectMFollowedBlogsProvider(NotificationsSettingsFragment instance,
      FollowedBlogsProvider mFollowedBlogsProvider) {
    instance.mFollowedBlogsProvider = mFollowedBlogsProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.notifications.NotificationsSettingsFragment.mBuildConfigWrapper")
  public static void injectMBuildConfigWrapper(NotificationsSettingsFragment instance,
      BuildConfigWrapper mBuildConfigWrapper) {
    instance.mBuildConfigWrapper = mBuildConfigWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.notifications.NotificationsSettingsFragment.mViewModelFactory")
  public static void injectMViewModelFactory(NotificationsSettingsFragment instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.notifications.NotificationsSettingsFragment.mBloggingRemindersFeatureConfig")
  public static void injectMBloggingRemindersFeatureConfig(NotificationsSettingsFragment instance,
      BloggingRemindersFeatureConfig mBloggingRemindersFeatureConfig) {
    instance.mBloggingRemindersFeatureConfig = mBloggingRemindersFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.notifications.NotificationsSettingsFragment.mUiHelpers")
  public static void injectMUiHelpers(NotificationsSettingsFragment instance,
      UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }
}

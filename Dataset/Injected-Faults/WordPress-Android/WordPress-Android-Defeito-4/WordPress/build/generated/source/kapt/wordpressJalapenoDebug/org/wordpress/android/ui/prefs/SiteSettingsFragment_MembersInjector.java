package org.wordpress.android.ui.prefs;

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
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;
import org.wordpress.android.util.config.BloggingRemindersFeatureConfig;
import org.wordpress.android.util.config.ManageCategoriesFeatureConfig;

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
public final class SiteSettingsFragment_MembersInjector implements MembersInjector<SiteSettingsFragment> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<ZendeskHelper> mZendeskHelperProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<BloggingRemindersFeatureConfig> mBloggingRemindersFeatureConfigProvider;

  private final Provider<BloggingPromptsFeatureConfig> mBloggingPromptsFeatureConfigProvider;

  private final Provider<ManageCategoriesFeatureConfig> mManageCategoriesFeatureConfigProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  public SiteSettingsFragment_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<BloggingRemindersFeatureConfig> mBloggingRemindersFeatureConfigProvider,
      Provider<BloggingPromptsFeatureConfig> mBloggingPromptsFeatureConfigProvider,
      Provider<ManageCategoriesFeatureConfig> mManageCategoriesFeatureConfigProvider,
      Provider<UiHelpers> mUiHelpersProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mZendeskHelperProvider = mZendeskHelperProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mBloggingRemindersFeatureConfigProvider = mBloggingRemindersFeatureConfigProvider;
    this.mBloggingPromptsFeatureConfigProvider = mBloggingPromptsFeatureConfigProvider;
    this.mManageCategoriesFeatureConfigProvider = mManageCategoriesFeatureConfigProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
  }

  public static MembersInjector<SiteSettingsFragment> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<ZendeskHelper> mZendeskHelperProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<BloggingRemindersFeatureConfig> mBloggingRemindersFeatureConfigProvider,
      Provider<BloggingPromptsFeatureConfig> mBloggingPromptsFeatureConfigProvider,
      Provider<ManageCategoriesFeatureConfig> mManageCategoriesFeatureConfigProvider,
      Provider<UiHelpers> mUiHelpersProvider) {
    return new SiteSettingsFragment_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mDispatcherProvider, mZendeskHelperProvider, mViewModelFactoryProvider, mBloggingRemindersFeatureConfigProvider, mBloggingPromptsFeatureConfigProvider, mManageCategoriesFeatureConfigProvider, mUiHelpersProvider);
  }

  @Override
  public void injectMembers(SiteSettingsFragment instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMZendeskHelper(instance, mZendeskHelperProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMBloggingRemindersFeatureConfig(instance, mBloggingRemindersFeatureConfigProvider.get());
    injectMBloggingPromptsFeatureConfig(instance, mBloggingPromptsFeatureConfigProvider.get());
    injectMManageCategoriesFeatureConfig(instance, mManageCategoriesFeatureConfigProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mAccountStore")
  public static void injectMAccountStore(SiteSettingsFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mSiteStore")
  public static void injectMSiteStore(SiteSettingsFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mDispatcher")
  public static void injectMDispatcher(SiteSettingsFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mZendeskHelper")
  public static void injectMZendeskHelper(SiteSettingsFragment instance,
      ZendeskHelper mZendeskHelper) {
    instance.mZendeskHelper = mZendeskHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mViewModelFactory")
  public static void injectMViewModelFactory(SiteSettingsFragment instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mBloggingRemindersFeatureConfig")
  public static void injectMBloggingRemindersFeatureConfig(SiteSettingsFragment instance,
      BloggingRemindersFeatureConfig mBloggingRemindersFeatureConfig) {
    instance.mBloggingRemindersFeatureConfig = mBloggingRemindersFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mBloggingPromptsFeatureConfig")
  public static void injectMBloggingPromptsFeatureConfig(SiteSettingsFragment instance,
      BloggingPromptsFeatureConfig mBloggingPromptsFeatureConfig) {
    instance.mBloggingPromptsFeatureConfig = mBloggingPromptsFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mManageCategoriesFeatureConfig")
  public static void injectMManageCategoriesFeatureConfig(SiteSettingsFragment instance,
      ManageCategoriesFeatureConfig mManageCategoriesFeatureConfig) {
    instance.mManageCategoriesFeatureConfig = mManageCategoriesFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsFragment.mUiHelpers")
  public static void injectMUiHelpers(SiteSettingsFragment instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }
}

package org.wordpress.android.ui.accounts;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.login.LoginAnalyticsListener;
import org.wordpress.android.support.ZendeskHelper;
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
public final class LoginActivity_MembersInjector implements MembersInjector<LoginActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> mDispatchingAndroidInjectorProvider;

  private final Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider;

  private final Provider<ZendeskHelper> mZendeskHelperProvider;

  private final Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<BuildConfigWrapper> mBuildConfigWrapperProvider;

  public LoginActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> mDispatchingAndroidInjectorProvider,
      Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider,
      Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider) {
    this.mDispatchingAndroidInjectorProvider = mDispatchingAndroidInjectorProvider;
    this.mLoginAnalyticsListenerProvider = mLoginAnalyticsListenerProvider;
    this.mZendeskHelperProvider = mZendeskHelperProvider;
    this.mUnifiedLoginTrackerProvider = mUnifiedLoginTrackerProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mBuildConfigWrapperProvider = mBuildConfigWrapperProvider;
  }

  public static MembersInjector<LoginActivity> create(
      Provider<DispatchingAndroidInjector<Object>> mDispatchingAndroidInjectorProvider,
      Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider,
      Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider) {
    return new LoginActivity_MembersInjector(mDispatchingAndroidInjectorProvider, mLoginAnalyticsListenerProvider, mZendeskHelperProvider, mUnifiedLoginTrackerProvider, mSiteStoreProvider, mViewModelFactoryProvider, mBuildConfigWrapperProvider);
  }

  @Override
  public void injectMembers(LoginActivity instance) {
    injectMDispatchingAndroidInjector(instance, mDispatchingAndroidInjectorProvider.get());
    injectMLoginAnalyticsListener(instance, mLoginAnalyticsListenerProvider.get());
    injectMZendeskHelper(instance, mZendeskHelperProvider.get());
    injectMUnifiedLoginTracker(instance, mUnifiedLoginTrackerProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMBuildConfigWrapper(instance, mBuildConfigWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginActivity.mDispatchingAndroidInjector")
  public static void injectMDispatchingAndroidInjector(LoginActivity instance,
      DispatchingAndroidInjector<Object> mDispatchingAndroidInjector) {
    instance.mDispatchingAndroidInjector = mDispatchingAndroidInjector;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginActivity.mLoginAnalyticsListener")
  public static void injectMLoginAnalyticsListener(LoginActivity instance,
      LoginAnalyticsListener mLoginAnalyticsListener) {
    instance.mLoginAnalyticsListener = mLoginAnalyticsListener;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginActivity.mZendeskHelper")
  public static void injectMZendeskHelper(LoginActivity instance, ZendeskHelper mZendeskHelper) {
    instance.mZendeskHelper = mZendeskHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginActivity.mUnifiedLoginTracker")
  public static void injectMUnifiedLoginTracker(LoginActivity instance,
      UnifiedLoginTracker mUnifiedLoginTracker) {
    instance.mUnifiedLoginTracker = mUnifiedLoginTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginActivity.mSiteStore")
  public static void injectMSiteStore(LoginActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginActivity.mViewModelFactory")
  public static void injectMViewModelFactory(LoginActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginActivity.mBuildConfigWrapper")
  public static void injectMBuildConfigWrapper(LoginActivity instance,
      BuildConfigWrapper mBuildConfigWrapper) {
    instance.mBuildConfigWrapper = mBuildConfigWrapper;
  }
}

package org.wordpress.android.ui.accounts.login;

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
import org.wordpress.android.login.LoginAnalyticsListener;
import org.wordpress.android.login.LoginBaseFormFragment_MembersInjector;
import org.wordpress.android.ui.accounts.LoginEpilogueViewModel;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.image.ImageManager;

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
public final class LoginEpilogueFragment_MembersInjector implements MembersInjector<LoginEpilogueFragment> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<LoginAnalyticsListener> mAnalyticsListenerProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider;

  private final Provider<BuildConfigWrapper> mBuildConfigWrapperProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<LoginEpilogueViewModel> mParentViewModelProvider;

  public LoginEpilogueFragment_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<AccountStore> mAccountStoreProvider,
      Provider<LoginAnalyticsListener> mAnalyticsListenerProvider,
      Provider<ImageManager> mImageManagerProvider,
      Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<LoginEpilogueViewModel> mParentViewModelProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mAnalyticsListenerProvider = mAnalyticsListenerProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mUnifiedLoginTrackerProvider = mUnifiedLoginTrackerProvider;
    this.mBuildConfigWrapperProvider = mBuildConfigWrapperProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mParentViewModelProvider = mParentViewModelProvider;
  }

  public static MembersInjector<LoginEpilogueFragment> create(
      Provider<Dispatcher> mDispatcherProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<AccountStore> mAccountStoreProvider,
      Provider<LoginAnalyticsListener> mAnalyticsListenerProvider,
      Provider<ImageManager> mImageManagerProvider,
      Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<LoginEpilogueViewModel> mParentViewModelProvider) {
    return new LoginEpilogueFragment_MembersInjector(mDispatcherProvider, mSiteStoreProvider, mAccountStoreProvider, mAnalyticsListenerProvider, mImageManagerProvider, mUnifiedLoginTrackerProvider, mBuildConfigWrapperProvider, mViewModelFactoryProvider, mParentViewModelProvider);
  }

  @Override
  public void injectMembers(LoginEpilogueFragment instance) {
    LoginBaseFormFragment_MembersInjector.injectMDispatcher(instance, mDispatcherProvider.get());
    LoginBaseFormFragment_MembersInjector.injectMSiteStore(instance, mSiteStoreProvider.get());
    LoginBaseFormFragment_MembersInjector.injectMAccountStore(instance, mAccountStoreProvider.get());
    LoginBaseFormFragment_MembersInjector.injectMAnalyticsListener(instance, mAnalyticsListenerProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMUnifiedLoginTracker(instance, mUnifiedLoginTrackerProvider.get());
    injectMBuildConfigWrapper(instance, mBuildConfigWrapperProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMParentViewModel(instance, mParentViewModelProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.LoginEpilogueFragment.mImageManager")
  public static void injectMImageManager(LoginEpilogueFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.LoginEpilogueFragment.mUnifiedLoginTracker")
  public static void injectMUnifiedLoginTracker(LoginEpilogueFragment instance,
      UnifiedLoginTracker mUnifiedLoginTracker) {
    instance.mUnifiedLoginTracker = mUnifiedLoginTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.LoginEpilogueFragment.mBuildConfigWrapper")
  public static void injectMBuildConfigWrapper(LoginEpilogueFragment instance,
      BuildConfigWrapper mBuildConfigWrapper) {
    instance.mBuildConfigWrapper = mBuildConfigWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.LoginEpilogueFragment.mViewModelFactory")
  public static void injectMViewModelFactory(LoginEpilogueFragment instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.LoginEpilogueFragment.mParentViewModel")
  public static void injectMParentViewModel(LoginEpilogueFragment instance,
      LoginEpilogueViewModel mParentViewModel) {
    instance.mParentViewModel = mParentViewModel;
  }
}

package org.wordpress.android.ui.accounts.signup;

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
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
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
public final class SignupEpilogueFragment_MembersInjector implements MembersInjector<SignupEpilogueFragment> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<LoginAnalyticsListener> mAnalyticsListenerProvider;

  private final Provider<AccountStore> mAccountProvider;

  private final Provider<Dispatcher> mDispatcherProvider2;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<AppPrefsWrapper> mAppPrefsWrapperProvider;

  private final Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider;

  private final Provider<SignupUtils> mSignupUtilsProvider;

  private final Provider<MediaPickerLauncher> mMediaPickerLauncherProvider;

  public SignupEpilogueFragment_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<AccountStore> mAccountStoreProvider,
      Provider<LoginAnalyticsListener> mAnalyticsListenerProvider,
      Provider<AccountStore> mAccountProvider, Provider<Dispatcher> mDispatcherProvider2,
      Provider<ImageManager> mImageManagerProvider,
      Provider<AppPrefsWrapper> mAppPrefsWrapperProvider,
      Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider,
      Provider<SignupUtils> mSignupUtilsProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mAnalyticsListenerProvider = mAnalyticsListenerProvider;
    this.mAccountProvider = mAccountProvider;
    this.mDispatcherProvider2 = mDispatcherProvider2;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mAppPrefsWrapperProvider = mAppPrefsWrapperProvider;
    this.mUnifiedLoginTrackerProvider = mUnifiedLoginTrackerProvider;
    this.mSignupUtilsProvider = mSignupUtilsProvider;
    this.mMediaPickerLauncherProvider = mMediaPickerLauncherProvider;
  }

  public static MembersInjector<SignupEpilogueFragment> create(
      Provider<Dispatcher> mDispatcherProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<AccountStore> mAccountStoreProvider,
      Provider<LoginAnalyticsListener> mAnalyticsListenerProvider,
      Provider<AccountStore> mAccountProvider, Provider<Dispatcher> mDispatcherProvider2,
      Provider<ImageManager> mImageManagerProvider,
      Provider<AppPrefsWrapper> mAppPrefsWrapperProvider,
      Provider<UnifiedLoginTracker> mUnifiedLoginTrackerProvider,
      Provider<SignupUtils> mSignupUtilsProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider) {
    return new SignupEpilogueFragment_MembersInjector(mDispatcherProvider, mSiteStoreProvider, mAccountStoreProvider, mAnalyticsListenerProvider, mAccountProvider, mDispatcherProvider2, mImageManagerProvider, mAppPrefsWrapperProvider, mUnifiedLoginTrackerProvider, mSignupUtilsProvider, mMediaPickerLauncherProvider);
  }

  @Override
  public void injectMembers(SignupEpilogueFragment instance) {
    LoginBaseFormFragment_MembersInjector.injectMDispatcher(instance, mDispatcherProvider.get());
    LoginBaseFormFragment_MembersInjector.injectMSiteStore(instance, mSiteStoreProvider.get());
    LoginBaseFormFragment_MembersInjector.injectMAccountStore(instance, mAccountStoreProvider.get());
    LoginBaseFormFragment_MembersInjector.injectMAnalyticsListener(instance, mAnalyticsListenerProvider.get());
    injectMAccount(instance, mAccountProvider.get());
    injectMDispatcher(instance, mDispatcherProvider2.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMAppPrefsWrapper(instance, mAppPrefsWrapperProvider.get());
    injectMUnifiedLoginTracker(instance, mUnifiedLoginTrackerProvider.get());
    injectMSignupUtils(instance, mSignupUtilsProvider.get());
    injectMMediaPickerLauncher(instance, mMediaPickerLauncherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.signup.SignupEpilogueFragment.mAccount")
  public static void injectMAccount(SignupEpilogueFragment instance, AccountStore mAccount) {
    instance.mAccount = mAccount;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.signup.SignupEpilogueFragment.mDispatcher")
  public static void injectMDispatcher(SignupEpilogueFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.signup.SignupEpilogueFragment.mImageManager")
  public static void injectMImageManager(SignupEpilogueFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.signup.SignupEpilogueFragment.mAppPrefsWrapper")
  public static void injectMAppPrefsWrapper(SignupEpilogueFragment instance,
      AppPrefsWrapper mAppPrefsWrapper) {
    instance.mAppPrefsWrapper = mAppPrefsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.signup.SignupEpilogueFragment.mUnifiedLoginTracker")
  public static void injectMUnifiedLoginTracker(SignupEpilogueFragment instance,
      UnifiedLoginTracker mUnifiedLoginTracker) {
    instance.mUnifiedLoginTracker = mUnifiedLoginTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.signup.SignupEpilogueFragment.mSignupUtils")
  public static void injectMSignupUtils(SignupEpilogueFragment instance, SignupUtils mSignupUtils) {
    instance.mSignupUtils = mSignupUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.signup.SignupEpilogueFragment.mMediaPickerLauncher")
  public static void injectMMediaPickerLauncher(SignupEpilogueFragment instance,
      MediaPickerLauncher mMediaPickerLauncher) {
    instance.mMediaPickerLauncher = mMediaPickerLauncher;
  }
}

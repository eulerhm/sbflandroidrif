package org.wordpress.android.ui;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.DisplayUtilsWrapper;

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
public final class WPWebViewActivity_MembersInjector implements MembersInjector<WPWebViewActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  private final Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<DisplayUtilsWrapper> mDisplayUtilsWrapperProvider;

  public WPWebViewActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider,
      Provider<Dispatcher> mDispatcherProvider,
      Provider<DisplayUtilsWrapper> mDisplayUtilsWrapperProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
    this.mPrivateAtomicCookieProvider = mPrivateAtomicCookieProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mDisplayUtilsWrapperProvider = mDisplayUtilsWrapperProvider;
  }

  public static MembersInjector<WPWebViewActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider,
      Provider<Dispatcher> mDispatcherProvider,
      Provider<DisplayUtilsWrapper> mDisplayUtilsWrapperProvider) {
    return new WPWebViewActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mViewModelFactoryProvider, mUiHelpersProvider, mPrivateAtomicCookieProvider, mDispatcherProvider, mDisplayUtilsWrapperProvider);
  }

  @Override
  public void injectMembers(WPWebViewActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
    injectMPrivateAtomicCookie(instance, mPrivateAtomicCookieProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMDisplayUtilsWrapper(instance, mDisplayUtilsWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.WPWebViewActivity.mAccountStore")
  public static void injectMAccountStore(WPWebViewActivity instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.WPWebViewActivity.mSiteStore")
  public static void injectMSiteStore(WPWebViewActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.WPWebViewActivity.mViewModelFactory")
  public static void injectMViewModelFactory(WPWebViewActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.WPWebViewActivity.mUiHelpers")
  public static void injectMUiHelpers(WPWebViewActivity instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.WPWebViewActivity.mPrivateAtomicCookie")
  public static void injectMPrivateAtomicCookie(WPWebViewActivity instance,
      PrivateAtomicCookie mPrivateAtomicCookie) {
    instance.mPrivateAtomicCookie = mPrivateAtomicCookie;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.WPWebViewActivity.mDispatcher")
  public static void injectMDispatcher(WPWebViewActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.WPWebViewActivity.mDisplayUtilsWrapper")
  public static void injectMDisplayUtilsWrapper(WPWebViewActivity instance,
      DisplayUtilsWrapper mDisplayUtilsWrapper) {
    instance.mDisplayUtilsWrapper = mDisplayUtilsWrapper;
  }
}

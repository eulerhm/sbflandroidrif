package org.wordpress.android.ui.accounts;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;

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
public final class LoginEpilogueActivity_MembersInjector implements MembersInjector<LoginEpilogueActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<LoginEpilogueViewModel> mViewModelProvider;

  public LoginEpilogueActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<LoginEpilogueViewModel> mViewModelProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mViewModelProvider = mViewModelProvider;
  }

  public static MembersInjector<LoginEpilogueActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<LoginEpilogueViewModel> mViewModelProvider) {
    return new LoginEpilogueActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mViewModelFactoryProvider, mViewModelProvider);
  }

  @Override
  public void injectMembers(LoginEpilogueActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMViewModel(instance, mViewModelProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginEpilogueActivity.mAccountStore")
  public static void injectMAccountStore(LoginEpilogueActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginEpilogueActivity.mSiteStore")
  public static void injectMSiteStore(LoginEpilogueActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginEpilogueActivity.mViewModelFactory")
  public static void injectMViewModelFactory(LoginEpilogueActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginEpilogueActivity.mViewModel")
  public static void injectMViewModel(LoginEpilogueActivity instance,
      LoginEpilogueViewModel mViewModel) {
    instance.mViewModel = mViewModel;
  }
}

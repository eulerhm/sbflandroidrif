package org.wordpress.android.ui.prefs;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;

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
public final class MyProfileFragment_MembersInjector implements MembersInjector<MyProfileFragment> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  public MyProfileFragment_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
  }

  public static MembersInjector<MyProfileFragment> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider) {
    return new MyProfileFragment_MembersInjector(mDispatcherProvider, mAccountStoreProvider);
  }

  @Override
  public void injectMembers(MyProfileFragment instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMAccountStore(instance, mAccountStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.MyProfileFragment.mDispatcher")
  public static void injectMDispatcher(MyProfileFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.MyProfileFragment.mAccountStore")
  public static void injectMAccountStore(MyProfileFragment instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }
}

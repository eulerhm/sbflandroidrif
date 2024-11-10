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
public final class MyProfileActivity_MembersInjector implements MembersInjector<MyProfileActivity> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  public MyProfileActivity_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
  }

  public static MembersInjector<MyProfileActivity> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider) {
    return new MyProfileActivity_MembersInjector(mDispatcherProvider, mAccountStoreProvider);
  }

  @Override
  public void injectMembers(MyProfileActivity instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMAccountStore(instance, mAccountStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.MyProfileActivity.mDispatcher")
  public static void injectMDispatcher(MyProfileActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.MyProfileActivity.mAccountStore")
  public static void injectMAccountStore(MyProfileActivity instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }
}

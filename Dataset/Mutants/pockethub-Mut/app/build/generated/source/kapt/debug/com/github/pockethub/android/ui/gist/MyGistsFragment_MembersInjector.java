package com.github.pockethub.android.ui.gist;

import android.accounts.Account;
import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.util.AvatarLoader;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MyGistsFragment_MembersInjector implements MembersInjector<MyGistsFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  private final Provider<GistStore> storeProvider;

  private final Provider<Account> accountProvider;

  public MyGistsFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider, Provider<GistStore> storeProvider,
      Provider<Account> accountProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.avatarsProvider = avatarsProvider;
    this.storeProvider = storeProvider;
    this.accountProvider = accountProvider;
  }

  public static MembersInjector<MyGistsFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider, Provider<GistStore> storeProvider,
      Provider<Account> accountProvider) {
    return new MyGistsFragment_MembersInjector(androidInjectorProvider, avatarsProvider, storeProvider, accountProvider);}

  @Override
  public void injectMembers(MyGistsFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    GistsFragment_MembersInjector.injectAvatars(instance, avatarsProvider.get());
    GistsFragment_MembersInjector.injectStore(instance, storeProvider.get());
    injectAccountProvider(instance, accountProvider);
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.MyGistsFragment.accountProvider")
  public static void injectAccountProvider(MyGistsFragment instance,
      Provider<Account> accountProvider) {
    instance.accountProvider = accountProvider;
  }
}

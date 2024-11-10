package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.persistence.AccountDataManager;
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
public final class FilterListFragment_MembersInjector implements MembersInjector<FilterListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AccountDataManager> cacheProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public FilterListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> cacheProvider, Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.cacheProvider = cacheProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<FilterListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> cacheProvider, Provider<AvatarLoader> avatarsProvider) {
    return new FilterListFragment_MembersInjector(androidInjectorProvider, cacheProvider, avatarsProvider);}

  @Override
  public void injectMembers(FilterListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectCache(instance, cacheProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.FilterListFragment.cache")
  public static void injectCache(FilterListFragment instance, AccountDataManager cache) {
    instance.cache = cache;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.FilterListFragment.avatars")
  public static void injectAvatars(FilterListFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}

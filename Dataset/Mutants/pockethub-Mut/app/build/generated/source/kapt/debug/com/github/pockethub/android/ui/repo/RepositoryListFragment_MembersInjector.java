package com.github.pockethub.android.ui.repo;

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
public final class RepositoryListFragment_MembersInjector implements MembersInjector<RepositoryListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AccountDataManager> cacheProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public RepositoryListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> cacheProvider, Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.cacheProvider = cacheProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<RepositoryListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> cacheProvider, Provider<AvatarLoader> avatarsProvider) {
    return new RepositoryListFragment_MembersInjector(androidInjectorProvider, cacheProvider, avatarsProvider);}

  @Override
  public void injectMembers(RepositoryListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectCache(instance, cacheProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.repo.RepositoryListFragment.cache")
  public static void injectCache(RepositoryListFragment instance, AccountDataManager cache) {
    instance.cache = cache;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.repo.RepositoryListFragment.avatars")
  public static void injectAvatars(RepositoryListFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}

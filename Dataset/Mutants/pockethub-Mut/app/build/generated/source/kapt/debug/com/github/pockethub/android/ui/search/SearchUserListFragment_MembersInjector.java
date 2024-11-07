package com.github.pockethub.android.ui.search;

import com.github.pockethub.android.util.AvatarLoader;
import com.meisolsson.githubsdk.service.search.SearchService;
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
public final class SearchUserListFragment_MembersInjector implements MembersInjector<SearchUserListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<SearchService> serviceProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public SearchUserListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<SearchService> serviceProvider, Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.serviceProvider = serviceProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<SearchUserListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<SearchService> serviceProvider, Provider<AvatarLoader> avatarsProvider) {
    return new SearchUserListFragment_MembersInjector(androidInjectorProvider, serviceProvider, avatarsProvider);}

  @Override
  public void injectMembers(SearchUserListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectService(instance, serviceProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.search.SearchUserListFragment.service")
  public static void injectService(SearchUserListFragment instance, SearchService service) {
    instance.service = service;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.search.SearchUserListFragment.avatars")
  public static void injectAvatars(SearchUserListFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}

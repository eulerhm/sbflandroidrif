package com.github.pockethub.android.ui.repo;

import com.meisolsson.githubsdk.service.repositories.RepositoryService;
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
public final class UserRepositoryListFragment_MembersInjector implements MembersInjector<UserRepositoryListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<RepositoryService> serviceProvider;

  public UserRepositoryListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<RepositoryService> serviceProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.serviceProvider = serviceProvider;
  }

  public static MembersInjector<UserRepositoryListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<RepositoryService> serviceProvider) {
    return new UserRepositoryListFragment_MembersInjector(androidInjectorProvider, serviceProvider);}

  @Override
  public void injectMembers(UserRepositoryListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectService(instance, serviceProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.repo.UserRepositoryListFragment.service")
  public static void injectService(UserRepositoryListFragment instance, RepositoryService service) {
    instance.service = service;
  }
}

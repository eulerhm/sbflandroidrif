package com.github.pockethub.android.ui.repo;

import com.github.pockethub.android.util.AvatarLoader;
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
public final class RepositoryContributorsFragment_MembersInjector implements MembersInjector<RepositoryContributorsFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<RepositoryService> serviceProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public RepositoryContributorsFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<RepositoryService> serviceProvider, Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.serviceProvider = serviceProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<RepositoryContributorsFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<RepositoryService> serviceProvider, Provider<AvatarLoader> avatarsProvider) {
    return new RepositoryContributorsFragment_MembersInjector(androidInjectorProvider, serviceProvider, avatarsProvider);}

  @Override
  public void injectMembers(RepositoryContributorsFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectService(instance, serviceProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.repo.RepositoryContributorsFragment.service")
  public static void injectService(RepositoryContributorsFragment instance,
      RepositoryService service) {
    instance.service = service;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.repo.RepositoryContributorsFragment.avatars")
  public static void injectAvatars(RepositoryContributorsFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}

package com.github.pockethub.android.ui.commit;

import com.github.pockethub.android.core.commit.CommitStore;
import com.github.pockethub.android.util.AvatarLoader;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommitService;
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
public final class CommitListFragment_MembersInjector implements MembersInjector<CommitListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<RepositoryCommitService> serviceProvider;

  private final Provider<RepositoryService> repoServiceProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  private final Provider<CommitStore> storeProvider;

  public CommitListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<RepositoryCommitService> serviceProvider,
      Provider<RepositoryService> repoServiceProvider, Provider<AvatarLoader> avatarsProvider,
      Provider<CommitStore> storeProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.serviceProvider = serviceProvider;
    this.repoServiceProvider = repoServiceProvider;
    this.avatarsProvider = avatarsProvider;
    this.storeProvider = storeProvider;
  }

  public static MembersInjector<CommitListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<RepositoryCommitService> serviceProvider,
      Provider<RepositoryService> repoServiceProvider, Provider<AvatarLoader> avatarsProvider,
      Provider<CommitStore> storeProvider) {
    return new CommitListFragment_MembersInjector(androidInjectorProvider, serviceProvider, repoServiceProvider, avatarsProvider, storeProvider);}

  @Override
  public void injectMembers(CommitListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectService(instance, serviceProvider.get());
    injectRepoService(instance, repoServiceProvider.get());
    injectAvatars(instance, avatarsProvider.get());
    injectStore(instance, storeProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitListFragment.service")
  public static void injectService(CommitListFragment instance, RepositoryCommitService service) {
    instance.service = service;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitListFragment.repoService")
  public static void injectRepoService(CommitListFragment instance, RepositoryService repoService) {
    instance.repoService = repoService;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitListFragment.avatars")
  public static void injectAvatars(CommitListFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitListFragment.store")
  public static void injectStore(CommitListFragment instance, CommitStore store) {
    instance.store = store;
  }
}

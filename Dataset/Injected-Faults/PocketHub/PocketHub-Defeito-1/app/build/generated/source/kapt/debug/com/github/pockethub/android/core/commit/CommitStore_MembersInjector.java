package com.github.pockethub.android.core.commit;

import com.meisolsson.githubsdk.service.repositories.RepositoryCommitService;
import dagger.MembersInjector;
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
public final class CommitStore_MembersInjector implements MembersInjector<CommitStore> {
  private final Provider<RepositoryCommitService> serviceProvider;

  public CommitStore_MembersInjector(Provider<RepositoryCommitService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  public static MembersInjector<CommitStore> create(
      Provider<RepositoryCommitService> serviceProvider) {
    return new CommitStore_MembersInjector(serviceProvider);}

  @Override
  public void injectMembers(CommitStore instance) {
    injectService(instance, serviceProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.core.commit.CommitStore.service")
  public static void injectService(CommitStore instance, RepositoryCommitService service) {
    instance.service = service;
  }
}

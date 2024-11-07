package com.github.pockethub.android.core.commit;

import com.meisolsson.githubsdk.service.repositories.RepositoryCommitService;
import dagger.internal.Factory;
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
public final class CommitStore_Factory implements Factory<CommitStore> {
  private final Provider<RepositoryCommitService> serviceProvider;

  public CommitStore_Factory(Provider<RepositoryCommitService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public CommitStore get() {
    CommitStore instance = newInstance();
    CommitStore_MembersInjector.injectService(instance, serviceProvider.get());
    return instance;
  }

  public static CommitStore_Factory create(Provider<RepositoryCommitService> serviceProvider) {
    return new CommitStore_Factory(serviceProvider);
  }

  public static CommitStore newInstance() {
    return new CommitStore();
  }
}

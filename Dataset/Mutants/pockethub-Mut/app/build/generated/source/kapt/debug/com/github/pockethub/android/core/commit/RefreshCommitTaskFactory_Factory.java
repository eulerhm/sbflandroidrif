package com.github.pockethub.android.core.commit;

import com.github.pockethub.android.util.HttpImageGetter;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommentService;
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
public final class RefreshCommitTaskFactory_Factory implements Factory<RefreshCommitTaskFactory> {
  private final Provider<CommitStore> storeProvider;

  private final Provider<HttpImageGetter> imageGetterProvider;

  private final Provider<RepositoryCommentService> serviceProvider;

  public RefreshCommitTaskFactory_Factory(Provider<CommitStore> storeProvider,
      Provider<HttpImageGetter> imageGetterProvider,
      Provider<RepositoryCommentService> serviceProvider) {
    this.storeProvider = storeProvider;
    this.imageGetterProvider = imageGetterProvider;
    this.serviceProvider = serviceProvider;
  }

  @Override
  public RefreshCommitTaskFactory get() {
    return newInstance(storeProvider, imageGetterProvider, serviceProvider);
  }

  public static RefreshCommitTaskFactory_Factory create(Provider<CommitStore> storeProvider,
      Provider<HttpImageGetter> imageGetterProvider,
      Provider<RepositoryCommentService> serviceProvider) {
    return new RefreshCommitTaskFactory_Factory(storeProvider, imageGetterProvider, serviceProvider);
  }

  public static RefreshCommitTaskFactory newInstance(Provider<CommitStore> storeProvider,
      Provider<HttpImageGetter> imageGetterProvider,
      Provider<RepositoryCommentService> serviceProvider) {
    return new RefreshCommitTaskFactory(storeProvider, imageGetterProvider, serviceProvider);
  }
}

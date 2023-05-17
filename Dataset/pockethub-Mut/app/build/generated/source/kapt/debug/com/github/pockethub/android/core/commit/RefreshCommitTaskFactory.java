package com.github.pockethub.android.core.commit;

import android.app.Activity;
import com.github.pockethub.android.util.HttpImageGetter;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommentService;
import javax.annotation.Generated;
import javax.inject.Inject;
import javax.inject.Provider;

@Generated(
    value = "com.google.auto.factory.processor.AutoFactoryProcessor",
    comments = "https://github.com/google/auto/tree/master/factory")
public final class RefreshCommitTaskFactory {
  private final Provider<CommitStore> storeProvider;

  private final Provider<HttpImageGetter> imageGetterProvider;

  private final Provider<RepositoryCommentService> serviceProvider;

  @Inject
  public RefreshCommitTaskFactory(
      Provider<CommitStore> storeProvider,
      Provider<HttpImageGetter> imageGetterProvider,
      Provider<RepositoryCommentService> serviceProvider) {
    this.storeProvider = checkNotNull(storeProvider, 1);
    this.imageGetterProvider = checkNotNull(imageGetterProvider, 2);
    this.serviceProvider = checkNotNull(serviceProvider, 3);
  }

  public RefreshCommitTask create(Activity activity, Repository repository, String id) {
    return new RefreshCommitTask(
        checkNotNull(storeProvider.get(), 1),
        checkNotNull(imageGetterProvider.get(), 2),
        checkNotNull(serviceProvider.get(), 3),
        checkNotNull(activity, 4),
        checkNotNull(repository, 5),
        checkNotNull(id, 6));
  }

  private static <T> T checkNotNull(T reference, int argumentIndex) {
    if (reference == null) {
      throw new NullPointerException(
          "@AutoFactory method argument is null but is not marked @Nullable. Argument index: "
              + argumentIndex);
    }
    return reference;
  }
}

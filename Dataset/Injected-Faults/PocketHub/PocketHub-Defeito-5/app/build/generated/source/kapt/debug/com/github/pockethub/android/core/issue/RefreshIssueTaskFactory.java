package com.github.pockethub.android.core.issue;

import android.content.Context;
import com.github.pockethub.android.util.HttpImageGetter;
import com.meisolsson.githubsdk.model.Repository;
import javax.annotation.Generated;
import javax.inject.Inject;
import javax.inject.Provider;

@Generated(
    value = "com.google.auto.factory.processor.AutoFactoryProcessor",
    comments = "https://github.com/google/auto/tree/master/factory")
public final class RefreshIssueTaskFactory {
  private final Provider<Context> contextProvider;

  private final Provider<HttpImageGetter> com_github_pockethub_android_util_HttpImageGetterProvider;

  private final Provider<IssueStore> storeProvider;

  @Inject
  public RefreshIssueTaskFactory(
      Provider<Context> contextProvider,
      Provider<HttpImageGetter> com_github_pockethub_android_util_HttpImageGetterProvider,
      Provider<IssueStore> storeProvider) {
    this.contextProvider = checkNotNull(contextProvider, 1);
    this.com_github_pockethub_android_util_HttpImageGetterProvider =
        checkNotNull(com_github_pockethub_android_util_HttpImageGetterProvider, 2);
    this.storeProvider = checkNotNull(storeProvider, 3);
  }

  public RefreshIssueTask create(Repository repo, int issueNumber) {
    return new RefreshIssueTask(
        checkNotNull(contextProvider.get(), 1),
        checkNotNull(repo, 2),
        issueNumber,
        checkNotNull(com_github_pockethub_android_util_HttpImageGetterProvider.get(), 4),
        checkNotNull(com_github_pockethub_android_util_HttpImageGetterProvider.get(), 5),
        checkNotNull(storeProvider.get(), 6));
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

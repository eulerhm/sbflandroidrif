package com.github.pockethub.android.core.issue;

import android.content.Context;
import com.github.pockethub.android.util.HttpImageGetter;
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
public final class RefreshIssueTaskFactory_Factory implements Factory<RefreshIssueTaskFactory> {
  private final Provider<Context> contextProvider;

  private final Provider<HttpImageGetter> com_github_pockethub_android_util_HttpImageGetterProvider;

  private final Provider<IssueStore> storeProvider;

  public RefreshIssueTaskFactory_Factory(Provider<Context> contextProvider,
      Provider<HttpImageGetter> com_github_pockethub_android_util_HttpImageGetterProvider,
      Provider<IssueStore> storeProvider) {
    this.contextProvider = contextProvider;
    this.com_github_pockethub_android_util_HttpImageGetterProvider = com_github_pockethub_android_util_HttpImageGetterProvider;
    this.storeProvider = storeProvider;
  }

  @Override
  public RefreshIssueTaskFactory get() {
    return newInstance(contextProvider, com_github_pockethub_android_util_HttpImageGetterProvider, storeProvider);
  }

  public static RefreshIssueTaskFactory_Factory create(Provider<Context> contextProvider,
      Provider<HttpImageGetter> com_github_pockethub_android_util_HttpImageGetterProvider,
      Provider<IssueStore> storeProvider) {
    return new RefreshIssueTaskFactory_Factory(contextProvider, com_github_pockethub_android_util_HttpImageGetterProvider, storeProvider);
  }

  public static RefreshIssueTaskFactory newInstance(Provider<Context> contextProvider,
      Provider<HttpImageGetter> com_github_pockethub_android_util_HttpImageGetterProvider,
      Provider<IssueStore> storeProvider) {
    return new RefreshIssueTaskFactory(contextProvider, com_github_pockethub_android_util_HttpImageGetterProvider, storeProvider);
  }
}

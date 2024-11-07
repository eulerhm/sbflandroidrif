package com.github.pockethub.android.core.issue;

import android.content.Context;
import com.meisolsson.githubsdk.service.issues.IssueService;
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
public final class IssueStore_Factory implements Factory<IssueStore> {
  private final Provider<Context> contextProvider;

  private final Provider<IssueService> serviceProvider;

  public IssueStore_Factory(Provider<Context> contextProvider,
      Provider<IssueService> serviceProvider) {
    this.contextProvider = contextProvider;
    this.serviceProvider = serviceProvider;
  }

  @Override
  public IssueStore get() {
    IssueStore instance = newInstance();
    IssueStore_MembersInjector.injectContext(instance, contextProvider.get());
    IssueStore_MembersInjector.injectService(instance, serviceProvider.get());
    return instance;
  }

  public static IssueStore_Factory create(Provider<Context> contextProvider,
      Provider<IssueService> serviceProvider) {
    return new IssueStore_Factory(contextProvider, serviceProvider);
  }

  public static IssueStore newInstance() {
    return new IssueStore();
  }
}

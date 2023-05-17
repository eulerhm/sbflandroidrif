package com.github.pockethub.android.core.issue;

import android.content.Context;
import com.meisolsson.githubsdk.service.issues.IssueService;
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
public final class IssueStore_MembersInjector implements MembersInjector<IssueStore> {
  private final Provider<Context> contextProvider;

  private final Provider<IssueService> serviceProvider;

  public IssueStore_MembersInjector(Provider<Context> contextProvider,
      Provider<IssueService> serviceProvider) {
    this.contextProvider = contextProvider;
    this.serviceProvider = serviceProvider;
  }

  public static MembersInjector<IssueStore> create(Provider<Context> contextProvider,
      Provider<IssueService> serviceProvider) {
    return new IssueStore_MembersInjector(contextProvider, serviceProvider);}

  @Override
  public void injectMembers(IssueStore instance) {
    injectContext(instance, contextProvider.get());
    injectService(instance, serviceProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.core.issue.IssueStore.context")
  public static void injectContext(IssueStore instance, Context context) {
    instance.context = context;
  }

  @InjectedFieldSignature("com.github.pockethub.android.core.issue.IssueStore.service")
  public static void injectService(IssueStore instance, IssueService service) {
    instance.service = service;
  }
}

package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.util.AvatarLoader;
import com.meisolsson.githubsdk.service.issues.IssueService;
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
public final class DashboardIssueFragment_MembersInjector implements MembersInjector<DashboardIssueFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<IssueService> serviceProvider;

  private final Provider<IssueStore> storeProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public DashboardIssueFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<IssueService> serviceProvider, Provider<IssueStore> storeProvider,
      Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.serviceProvider = serviceProvider;
    this.storeProvider = storeProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<DashboardIssueFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<IssueService> serviceProvider, Provider<IssueStore> storeProvider,
      Provider<AvatarLoader> avatarsProvider) {
    return new DashboardIssueFragment_MembersInjector(androidInjectorProvider, serviceProvider, storeProvider, avatarsProvider);}

  @Override
  public void injectMembers(DashboardIssueFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectService(instance, serviceProvider.get());
    injectStore(instance, storeProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.DashboardIssueFragment.service")
  public static void injectService(DashboardIssueFragment instance, IssueService service) {
    instance.service = service;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.DashboardIssueFragment.store")
  public static void injectStore(DashboardIssueFragment instance, IssueStore store) {
    instance.store = store;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.DashboardIssueFragment.avatars")
  public static void injectAvatars(DashboardIssueFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}

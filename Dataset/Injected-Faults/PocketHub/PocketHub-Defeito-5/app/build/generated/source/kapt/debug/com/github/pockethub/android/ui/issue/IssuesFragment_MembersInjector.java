package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.persistence.AccountDataManager;
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
public final class IssuesFragment_MembersInjector implements MembersInjector<IssuesFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AccountDataManager> cacheProvider;

  private final Provider<IssueStore> storeProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  private final Provider<IssueService> serviceProvider;

  public IssuesFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> cacheProvider, Provider<IssueStore> storeProvider,
      Provider<AvatarLoader> avatarsProvider, Provider<IssueService> serviceProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.cacheProvider = cacheProvider;
    this.storeProvider = storeProvider;
    this.avatarsProvider = avatarsProvider;
    this.serviceProvider = serviceProvider;
  }

  public static MembersInjector<IssuesFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> cacheProvider, Provider<IssueStore> storeProvider,
      Provider<AvatarLoader> avatarsProvider, Provider<IssueService> serviceProvider) {
    return new IssuesFragment_MembersInjector(androidInjectorProvider, cacheProvider, storeProvider, avatarsProvider, serviceProvider);}

  @Override
  public void injectMembers(IssuesFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectCache(instance, cacheProvider.get());
    injectStore(instance, storeProvider.get());
    injectAvatars(instance, avatarsProvider.get());
    injectService(instance, serviceProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssuesFragment.cache")
  public static void injectCache(IssuesFragment instance, AccountDataManager cache) {
    instance.cache = cache;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssuesFragment.store")
  public static void injectStore(IssuesFragment instance, IssueStore store) {
    instance.store = store;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssuesFragment.avatars")
  public static void injectAvatars(IssuesFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssuesFragment.service")
  public static void injectService(IssuesFragment instance, IssueService service) {
    instance.service = service;
  }
}

package com.github.pockethub.android.ui.search;

import com.meisolsson.githubsdk.service.search.SearchService;
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
public final class SearchRepositoryListFragment_MembersInjector implements MembersInjector<SearchRepositoryListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<SearchService> serviceProvider;

  public SearchRepositoryListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<SearchService> serviceProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.serviceProvider = serviceProvider;
  }

  public static MembersInjector<SearchRepositoryListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<SearchService> serviceProvider) {
    return new SearchRepositoryListFragment_MembersInjector(androidInjectorProvider, serviceProvider);}

  @Override
  public void injectMembers(SearchRepositoryListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectService(instance, serviceProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.search.SearchRepositoryListFragment.service")
  public static void injectService(SearchRepositoryListFragment instance, SearchService service) {
    instance.service = service;
  }
}

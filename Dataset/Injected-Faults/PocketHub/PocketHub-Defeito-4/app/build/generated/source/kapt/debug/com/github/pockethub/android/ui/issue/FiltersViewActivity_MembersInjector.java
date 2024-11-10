package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.persistence.AccountDataManager;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
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
public final class FiltersViewActivity_MembersInjector implements MembersInjector<FiltersViewActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AccountDataManager> cacheProvider;

  public FiltersViewActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> cacheProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.cacheProvider = cacheProvider;
  }

  public static MembersInjector<FiltersViewActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> cacheProvider) {
    return new FiltersViewActivity_MembersInjector(androidInjectorProvider, cacheProvider);}

  @Override
  public void injectMembers(FiltersViewActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectCache(instance, cacheProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.FiltersViewActivity.cache")
  public static void injectCache(FiltersViewActivity instance, AccountDataManager cache) {
    instance.cache = cache;
  }
}

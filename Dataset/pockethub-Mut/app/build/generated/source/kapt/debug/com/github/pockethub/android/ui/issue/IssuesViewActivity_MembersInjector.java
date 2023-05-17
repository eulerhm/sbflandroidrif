package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.core.issue.IssueStore;
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
public final class IssuesViewActivity_MembersInjector implements MembersInjector<IssuesViewActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<IssueStore> storeProvider;

  public IssuesViewActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<IssueStore> storeProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.storeProvider = storeProvider;
  }

  public static MembersInjector<IssuesViewActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<IssueStore> storeProvider) {
    return new IssuesViewActivity_MembersInjector(androidInjectorProvider, storeProvider);}

  @Override
  public void injectMembers(IssuesViewActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectStore(instance, storeProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssuesViewActivity.store")
  public static void injectStore(IssuesViewActivity instance, IssueStore store) {
    instance.store = store;
  }
}

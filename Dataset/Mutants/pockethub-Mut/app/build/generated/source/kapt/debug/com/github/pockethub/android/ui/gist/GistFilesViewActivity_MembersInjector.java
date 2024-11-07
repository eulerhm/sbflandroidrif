package com.github.pockethub.android.ui.gist;

import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.core.gist.RefreshGistTaskFactory;
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
public final class GistFilesViewActivity_MembersInjector implements MembersInjector<GistFilesViewActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<GistStore> storeProvider;

  private final Provider<RefreshGistTaskFactory> refreshGistTaskFactoryProvider;

  public GistFilesViewActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<GistStore> storeProvider,
      Provider<RefreshGistTaskFactory> refreshGistTaskFactoryProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.storeProvider = storeProvider;
    this.refreshGistTaskFactoryProvider = refreshGistTaskFactoryProvider;
  }

  public static MembersInjector<GistFilesViewActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<GistStore> storeProvider,
      Provider<RefreshGistTaskFactory> refreshGistTaskFactoryProvider) {
    return new GistFilesViewActivity_MembersInjector(androidInjectorProvider, storeProvider, refreshGistTaskFactoryProvider);}

  @Override
  public void injectMembers(GistFilesViewActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectStore(instance, storeProvider.get());
    injectRefreshGistTaskFactory(instance, refreshGistTaskFactoryProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistFilesViewActivity.store")
  public static void injectStore(GistFilesViewActivity instance, GistStore store) {
    instance.store = store;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistFilesViewActivity.refreshGistTaskFactory")
  public static void injectRefreshGistTaskFactory(GistFilesViewActivity instance,
      RefreshGistTaskFactory refreshGistTaskFactory) {
    instance.refreshGistTaskFactory = refreshGistTaskFactory;
  }
}

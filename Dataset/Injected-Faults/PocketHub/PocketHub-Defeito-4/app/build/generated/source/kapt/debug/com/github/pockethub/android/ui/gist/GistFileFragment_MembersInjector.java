package com.github.pockethub.android.ui.gist;

import com.github.pockethub.android.core.gist.GistStore;
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
public final class GistFileFragment_MembersInjector implements MembersInjector<GistFileFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<GistStore> storeProvider;

  public GistFileFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<GistStore> storeProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.storeProvider = storeProvider;
  }

  public static MembersInjector<GistFileFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<GistStore> storeProvider) {
    return new GistFileFragment_MembersInjector(androidInjectorProvider, storeProvider);}

  @Override
  public void injectMembers(GistFileFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectStore(instance, storeProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistFileFragment.store")
  public static void injectStore(GistFileFragment instance, GistStore store) {
    instance.store = store;
  }
}

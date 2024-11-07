package com.github.pockethub.android.ui.gist;

import com.github.pockethub.android.core.gist.GistStore;
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
public final class GistsViewActivity_MembersInjector implements MembersInjector<GistsViewActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<GistStore> storeProvider;

  public GistsViewActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<GistStore> storeProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.storeProvider = storeProvider;
  }

  public static MembersInjector<GistsViewActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<GistStore> storeProvider) {
    return new GistsViewActivity_MembersInjector(androidInjectorProvider, storeProvider);}

  @Override
  public void injectMembers(GistsViewActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectStore(instance, storeProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistsViewActivity.store")
  public static void injectStore(GistsViewActivity instance, GistStore store) {
    instance.store = store;
  }
}

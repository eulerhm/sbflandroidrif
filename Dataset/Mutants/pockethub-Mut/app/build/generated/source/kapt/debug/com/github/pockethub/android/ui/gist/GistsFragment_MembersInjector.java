package com.github.pockethub.android.ui.gist;

import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.util.AvatarLoader;
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
public final class GistsFragment_MembersInjector implements MembersInjector<GistsFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  private final Provider<GistStore> storeProvider;

  public GistsFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider, Provider<GistStore> storeProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.avatarsProvider = avatarsProvider;
    this.storeProvider = storeProvider;
  }

  public static MembersInjector<GistsFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider, Provider<GistStore> storeProvider) {
    return new GistsFragment_MembersInjector(androidInjectorProvider, avatarsProvider, storeProvider);}

  @Override
  public void injectMembers(GistsFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectAvatars(instance, avatarsProvider.get());
    injectStore(instance, storeProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistsFragment.avatars")
  public static void injectAvatars(GistsFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistsFragment.store")
  public static void injectStore(GistsFragment instance, GistStore store) {
    instance.store = store;
  }
}

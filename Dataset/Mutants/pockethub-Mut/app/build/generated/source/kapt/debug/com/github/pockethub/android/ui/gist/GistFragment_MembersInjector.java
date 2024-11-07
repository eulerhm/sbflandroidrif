package com.github.pockethub.android.ui.gist;

import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.core.gist.RefreshGistTaskFactory;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.HttpImageGetter;
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
public final class GistFragment_MembersInjector implements MembersInjector<GistFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<GistStore> storeProvider;

  private final Provider<RefreshGistTaskFactory> refreshGistTaskFactoryProvider;

  private final Provider<HttpImageGetter> imageGetterProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public GistFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<GistStore> storeProvider,
      Provider<RefreshGistTaskFactory> refreshGistTaskFactoryProvider,
      Provider<HttpImageGetter> imageGetterProvider, Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.storeProvider = storeProvider;
    this.refreshGistTaskFactoryProvider = refreshGistTaskFactoryProvider;
    this.imageGetterProvider = imageGetterProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<GistFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<GistStore> storeProvider,
      Provider<RefreshGistTaskFactory> refreshGistTaskFactoryProvider,
      Provider<HttpImageGetter> imageGetterProvider, Provider<AvatarLoader> avatarsProvider) {
    return new GistFragment_MembersInjector(androidInjectorProvider, storeProvider, refreshGistTaskFactoryProvider, imageGetterProvider, avatarsProvider);}

  @Override
  public void injectMembers(GistFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectStore(instance, storeProvider.get());
    injectRefreshGistTaskFactory(instance, refreshGistTaskFactoryProvider.get());
    injectImageGetter(instance, imageGetterProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistFragment.store")
  public static void injectStore(GistFragment instance, GistStore store) {
    instance.store = store;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistFragment.refreshGistTaskFactory")
  public static void injectRefreshGistTaskFactory(GistFragment instance,
      RefreshGistTaskFactory refreshGistTaskFactory) {
    instance.refreshGistTaskFactory = refreshGistTaskFactory;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistFragment.imageGetter")
  public static void injectImageGetter(GistFragment instance, HttpImageGetter imageGetter) {
    instance.imageGetter = imageGetter;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.gist.GistFragment.avatars")
  public static void injectAvatars(GistFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}

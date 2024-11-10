package com.github.pockethub.android.core.gist;

import com.github.pockethub.android.util.HttpImageGetter;
import dagger.internal.Factory;
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
public final class RefreshGistTaskFactory_Factory implements Factory<RefreshGistTaskFactory> {
  private final Provider<GistStore> storeProvider;

  private final Provider<HttpImageGetter> imageGetterProvider;

  public RefreshGistTaskFactory_Factory(Provider<GistStore> storeProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    this.storeProvider = storeProvider;
    this.imageGetterProvider = imageGetterProvider;
  }

  @Override
  public RefreshGistTaskFactory get() {
    return newInstance(storeProvider, imageGetterProvider);
  }

  public static RefreshGistTaskFactory_Factory create(Provider<GistStore> storeProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    return new RefreshGistTaskFactory_Factory(storeProvider, imageGetterProvider);
  }

  public static RefreshGistTaskFactory newInstance(Provider<GistStore> storeProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    return new RefreshGistTaskFactory(storeProvider, imageGetterProvider);
  }
}

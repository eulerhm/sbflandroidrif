package com.github.pockethub.android.core.gist;

import com.meisolsson.githubsdk.service.gists.GistService;
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
public final class GistStore_Factory implements Factory<GistStore> {
  private final Provider<GistService> gistServiceProvider;

  public GistStore_Factory(Provider<GistService> gistServiceProvider) {
    this.gistServiceProvider = gistServiceProvider;
  }

  @Override
  public GistStore get() {
    GistStore instance = newInstance();
    GistStore_MembersInjector.injectGistService(instance, gistServiceProvider.get());
    return instance;
  }

  public static GistStore_Factory create(Provider<GistService> gistServiceProvider) {
    return new GistStore_Factory(gistServiceProvider);
  }

  public static GistStore newInstance() {
    return new GistStore();
  }
}

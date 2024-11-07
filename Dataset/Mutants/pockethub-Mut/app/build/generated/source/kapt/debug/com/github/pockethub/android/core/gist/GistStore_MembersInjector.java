package com.github.pockethub.android.core.gist;

import com.meisolsson.githubsdk.service.gists.GistService;
import dagger.MembersInjector;
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
public final class GistStore_MembersInjector implements MembersInjector<GistStore> {
  private final Provider<GistService> gistServiceProvider;

  public GistStore_MembersInjector(Provider<GistService> gistServiceProvider) {
    this.gistServiceProvider = gistServiceProvider;
  }

  public static MembersInjector<GistStore> create(Provider<GistService> gistServiceProvider) {
    return new GistStore_MembersInjector(gistServiceProvider);}

  @Override
  public void injectMembers(GistStore instance) {
    injectGistService(instance, gistServiceProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.core.gist.GistStore.gistService")
  public static void injectGistService(GistStore instance, GistService gistService) {
    instance.gistService = gistService;
  }
}

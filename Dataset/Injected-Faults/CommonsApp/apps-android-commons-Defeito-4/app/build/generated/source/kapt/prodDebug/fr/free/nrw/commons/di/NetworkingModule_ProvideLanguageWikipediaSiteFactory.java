// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.wikipedia.dataclient.WikiSite;

public final class NetworkingModule_ProvideLanguageWikipediaSiteFactory implements Factory<WikiSite> {
  private final NetworkingModule module;

  public NetworkingModule_ProvideLanguageWikipediaSiteFactory(NetworkingModule module) {
    this.module = module;
  }

  @Override
  public WikiSite get() {
    return provideLanguageWikipediaSite(module);
  }

  public static NetworkingModule_ProvideLanguageWikipediaSiteFactory create(
      NetworkingModule module) {
    return new NetworkingModule_ProvideLanguageWikipediaSiteFactory(module);
  }

  public static WikiSite provideLanguageWikipediaSite(NetworkingModule instance) {
    return Preconditions.checkNotNull(instance.provideLanguageWikipediaSite(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

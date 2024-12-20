// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.di;

import android.content.ContentProviderClient;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CommonsApplicationModule_ProvideRecentSearchContentProviderClientFactory implements Factory<ContentProviderClient> {
  private final CommonsApplicationModule module;

  private final Provider<Context> contextProvider;

  public CommonsApplicationModule_ProvideRecentSearchContentProviderClientFactory(
      CommonsApplicationModule module, Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public ContentProviderClient get() {
    return provideRecentSearchContentProviderClient(module, contextProvider.get());
  }

  public static CommonsApplicationModule_ProvideRecentSearchContentProviderClientFactory create(
      CommonsApplicationModule module, Provider<Context> contextProvider) {
    return new CommonsApplicationModule_ProvideRecentSearchContentProviderClientFactory(module, contextProvider);
  }

  public static ContentProviderClient provideRecentSearchContentProviderClient(
      CommonsApplicationModule instance, Context context) {
    return Preconditions.checkNotNull(instance.provideRecentSearchContentProviderClient(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

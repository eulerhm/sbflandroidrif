package com.github.pockethub.android;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
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
public final class PocketHubModule_CacheDirFactory implements Factory<File> {
  private final PocketHubModule module;

  private final Provider<Context> contextProvider;

  public PocketHubModule_CacheDirFactory(PocketHubModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public File get() {
    return cacheDir(module, contextProvider.get());
  }

  public static PocketHubModule_CacheDirFactory create(PocketHubModule module,
      Provider<Context> contextProvider) {
    return new PocketHubModule_CacheDirFactory(module, contextProvider);
  }

  public static File cacheDir(PocketHubModule instance, Context context) {
    return Preconditions.checkNotNull(instance.cacheDir(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

package com.github.pockethub.android;

import android.content.Context;
import com.meisolsson.githubsdk.service.gists.GistService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class GitHubModule_ProvidesGistServiceFactory implements Factory<GistService> {
  private final GitHubModule module;

  private final Provider<Context> contextProvider;

  public GitHubModule_ProvidesGistServiceFactory(GitHubModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public GistService get() {
    return providesGistService(module, contextProvider.get());
  }

  public static GitHubModule_ProvidesGistServiceFactory create(GitHubModule module,
      Provider<Context> contextProvider) {
    return new GitHubModule_ProvidesGistServiceFactory(module, contextProvider);
  }

  public static GistService providesGistService(GitHubModule instance, Context context) {
    return Preconditions.checkNotNull(instance.providesGistService(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

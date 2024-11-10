package com.github.pockethub.android;

import android.content.Context;
import com.meisolsson.githubsdk.service.repositories.RepositoryService;
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
public final class GitHubModule_ProvidesRepositoryServiceFactory implements Factory<RepositoryService> {
  private final GitHubModule module;

  private final Provider<Context> contextProvider;

  public GitHubModule_ProvidesRepositoryServiceFactory(GitHubModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public RepositoryService get() {
    return providesRepositoryService(module, contextProvider.get());
  }

  public static GitHubModule_ProvidesRepositoryServiceFactory create(GitHubModule module,
      Provider<Context> contextProvider) {
    return new GitHubModule_ProvidesRepositoryServiceFactory(module, contextProvider);
  }

  public static RepositoryService providesRepositoryService(GitHubModule instance,
      Context context) {
    return Preconditions.checkNotNull(instance.providesRepositoryService(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

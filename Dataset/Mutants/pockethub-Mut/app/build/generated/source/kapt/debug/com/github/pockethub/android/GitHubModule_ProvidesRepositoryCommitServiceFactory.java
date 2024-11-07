package com.github.pockethub.android;

import android.content.Context;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommitService;
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
public final class GitHubModule_ProvidesRepositoryCommitServiceFactory implements Factory<RepositoryCommitService> {
  private final GitHubModule module;

  private final Provider<Context> contextProvider;

  public GitHubModule_ProvidesRepositoryCommitServiceFactory(GitHubModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public RepositoryCommitService get() {
    return providesRepositoryCommitService(module, contextProvider.get());
  }

  public static GitHubModule_ProvidesRepositoryCommitServiceFactory create(GitHubModule module,
      Provider<Context> contextProvider) {
    return new GitHubModule_ProvidesRepositoryCommitServiceFactory(module, contextProvider);
  }

  public static RepositoryCommitService providesRepositoryCommitService(GitHubModule instance,
      Context context) {
    return Preconditions.checkNotNull(instance.providesRepositoryCommitService(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

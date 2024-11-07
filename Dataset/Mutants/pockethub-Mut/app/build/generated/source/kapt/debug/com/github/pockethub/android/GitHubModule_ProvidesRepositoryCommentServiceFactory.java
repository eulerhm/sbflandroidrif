package com.github.pockethub.android;

import android.content.Context;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommentService;
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
public final class GitHubModule_ProvidesRepositoryCommentServiceFactory implements Factory<RepositoryCommentService> {
  private final GitHubModule module;

  private final Provider<Context> contextProvider;

  public GitHubModule_ProvidesRepositoryCommentServiceFactory(GitHubModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public RepositoryCommentService get() {
    return providesRepositoryCommentService(module, contextProvider.get());
  }

  public static GitHubModule_ProvidesRepositoryCommentServiceFactory create(GitHubModule module,
      Provider<Context> contextProvider) {
    return new GitHubModule_ProvidesRepositoryCommentServiceFactory(module, contextProvider);
  }

  public static RepositoryCommentService providesRepositoryCommentService(GitHubModule instance,
      Context context) {
    return Preconditions.checkNotNull(instance.providesRepositoryCommentService(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

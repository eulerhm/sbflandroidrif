package com.github.pockethub.android;

import android.content.Context;
import com.meisolsson.githubsdk.service.search.SearchService;
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
public final class GitHubModule_ProvidesSearchServiceFactory implements Factory<SearchService> {
  private final GitHubModule module;

  private final Provider<Context> contextProvider;

  public GitHubModule_ProvidesSearchServiceFactory(GitHubModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public SearchService get() {
    return providesSearchService(module, contextProvider.get());
  }

  public static GitHubModule_ProvidesSearchServiceFactory create(GitHubModule module,
      Provider<Context> contextProvider) {
    return new GitHubModule_ProvidesSearchServiceFactory(module, contextProvider);
  }

  public static SearchService providesSearchService(GitHubModule instance, Context context) {
    return Preconditions.checkNotNull(instance.providesSearchService(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

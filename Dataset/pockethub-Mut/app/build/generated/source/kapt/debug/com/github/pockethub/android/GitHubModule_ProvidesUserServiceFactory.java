package com.github.pockethub.android;

import android.content.Context;
import com.meisolsson.githubsdk.service.users.UserService;
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
public final class GitHubModule_ProvidesUserServiceFactory implements Factory<UserService> {
  private final GitHubModule module;

  private final Provider<Context> contextProvider;

  public GitHubModule_ProvidesUserServiceFactory(GitHubModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public UserService get() {
    return providesUserService(module, contextProvider.get());
  }

  public static GitHubModule_ProvidesUserServiceFactory create(GitHubModule module,
      Provider<Context> contextProvider) {
    return new GitHubModule_ProvidesUserServiceFactory(module, contextProvider);
  }

  public static UserService providesUserService(GitHubModule instance, Context context) {
    return Preconditions.checkNotNull(instance.providesUserService(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

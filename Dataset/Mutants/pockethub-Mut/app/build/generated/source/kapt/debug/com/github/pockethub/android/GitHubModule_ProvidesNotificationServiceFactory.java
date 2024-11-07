package com.github.pockethub.android;

import android.content.Context;
import com.meisolsson.githubsdk.service.activity.NotificationService;
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
public final class GitHubModule_ProvidesNotificationServiceFactory implements Factory<NotificationService> {
  private final GitHubModule module;

  private final Provider<Context> contextProvider;

  public GitHubModule_ProvidesNotificationServiceFactory(GitHubModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public NotificationService get() {
    return providesNotificationService(module, contextProvider.get());
  }

  public static GitHubModule_ProvidesNotificationServiceFactory create(GitHubModule module,
      Provider<Context> contextProvider) {
    return new GitHubModule_ProvidesNotificationServiceFactory(module, contextProvider);
  }

  public static NotificationService providesNotificationService(GitHubModule instance,
      Context context) {
    return Preconditions.checkNotNull(instance.providesNotificationService(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}

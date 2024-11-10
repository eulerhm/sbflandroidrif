package org.wordpress.android.workers.notification.bloggingprompts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;
import org.wordpress.android.workers.notification.local.LocalNotificationScheduler;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BloggingPromptsOnboardingNotificationScheduler_Factory implements Factory<BloggingPromptsOnboardingNotificationScheduler> {
  private final Provider<LocalNotificationScheduler> localNotificationSchedulerProvider;

  private final Provider<BloggingPromptsOnboardingNotificationHandler> bloggingPromptsOnboardingNotificationHandlerProvider;

  private final Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider;

  public BloggingPromptsOnboardingNotificationScheduler_Factory(
      Provider<LocalNotificationScheduler> localNotificationSchedulerProvider,
      Provider<BloggingPromptsOnboardingNotificationHandler> bloggingPromptsOnboardingNotificationHandlerProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider) {
    this.localNotificationSchedulerProvider = localNotificationSchedulerProvider;
    this.bloggingPromptsOnboardingNotificationHandlerProvider = bloggingPromptsOnboardingNotificationHandlerProvider;
    this.bloggingPromptsFeatureConfigProvider = bloggingPromptsFeatureConfigProvider;
  }

  @Override
  public BloggingPromptsOnboardingNotificationScheduler get() {
    return newInstance(localNotificationSchedulerProvider.get(), bloggingPromptsOnboardingNotificationHandlerProvider.get(), bloggingPromptsFeatureConfigProvider.get());
  }

  public static BloggingPromptsOnboardingNotificationScheduler_Factory create(
      Provider<LocalNotificationScheduler> localNotificationSchedulerProvider,
      Provider<BloggingPromptsOnboardingNotificationHandler> bloggingPromptsOnboardingNotificationHandlerProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider) {
    return new BloggingPromptsOnboardingNotificationScheduler_Factory(localNotificationSchedulerProvider, bloggingPromptsOnboardingNotificationHandlerProvider, bloggingPromptsFeatureConfigProvider);
  }

  public static BloggingPromptsOnboardingNotificationScheduler newInstance(
      LocalNotificationScheduler localNotificationScheduler,
      BloggingPromptsOnboardingNotificationHandler bloggingPromptsOnboardingNotificationHandler,
      BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig) {
    return new BloggingPromptsOnboardingNotificationScheduler(localNotificationScheduler, bloggingPromptsOnboardingNotificationHandler, bloggingPromptsFeatureConfig);
  }
}

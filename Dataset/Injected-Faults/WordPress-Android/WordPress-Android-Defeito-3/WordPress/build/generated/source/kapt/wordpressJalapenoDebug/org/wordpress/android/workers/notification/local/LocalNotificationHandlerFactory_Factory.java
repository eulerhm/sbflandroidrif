package org.wordpress.android.workers.notification.local;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.workers.notification.bloggingprompts.BloggingPromptsOnboardingNotificationHandler;
import org.wordpress.android.workers.notification.createsite.CreateSiteNotificationHandler;

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
public final class LocalNotificationHandlerFactory_Factory implements Factory<LocalNotificationHandlerFactory> {
  private final Provider<CreateSiteNotificationHandler> createSiteNotificationHandlerProvider;

  private final Provider<BloggingPromptsOnboardingNotificationHandler> bloggingPromptsOnboardingNotificationHandlerProvider;

  public LocalNotificationHandlerFactory_Factory(
      Provider<CreateSiteNotificationHandler> createSiteNotificationHandlerProvider,
      Provider<BloggingPromptsOnboardingNotificationHandler> bloggingPromptsOnboardingNotificationHandlerProvider) {
    this.createSiteNotificationHandlerProvider = createSiteNotificationHandlerProvider;
    this.bloggingPromptsOnboardingNotificationHandlerProvider = bloggingPromptsOnboardingNotificationHandlerProvider;
  }

  @Override
  public LocalNotificationHandlerFactory get() {
    return newInstance(createSiteNotificationHandlerProvider.get(), bloggingPromptsOnboardingNotificationHandlerProvider.get());
  }

  public static LocalNotificationHandlerFactory_Factory create(
      Provider<CreateSiteNotificationHandler> createSiteNotificationHandlerProvider,
      Provider<BloggingPromptsOnboardingNotificationHandler> bloggingPromptsOnboardingNotificationHandlerProvider) {
    return new LocalNotificationHandlerFactory_Factory(createSiteNotificationHandlerProvider, bloggingPromptsOnboardingNotificationHandlerProvider);
  }

  public static LocalNotificationHandlerFactory newInstance(
      CreateSiteNotificationHandler createSiteNotificationHandler,
      BloggingPromptsOnboardingNotificationHandler bloggingPromptsOnboardingNotificationHandler) {
    return new LocalNotificationHandlerFactory(createSiteNotificationHandler, bloggingPromptsOnboardingNotificationHandler);
  }
}

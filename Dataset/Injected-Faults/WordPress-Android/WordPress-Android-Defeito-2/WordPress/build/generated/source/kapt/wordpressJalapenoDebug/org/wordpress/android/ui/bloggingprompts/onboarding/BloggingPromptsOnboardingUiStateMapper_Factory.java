package org.wordpress.android.ui.bloggingprompts.onboarding;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class BloggingPromptsOnboardingUiStateMapper_Factory implements Factory<BloggingPromptsOnboardingUiStateMapper> {
  @Override
  public BloggingPromptsOnboardingUiStateMapper get() {
    return newInstance();
  }

  public static BloggingPromptsOnboardingUiStateMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static BloggingPromptsOnboardingUiStateMapper newInstance() {
    return new BloggingPromptsOnboardingUiStateMapper();
  }

  private static final class InstanceHolder {
    private static final BloggingPromptsOnboardingUiStateMapper_Factory INSTANCE = new BloggingPromptsOnboardingUiStateMapper_Factory();
  }
}

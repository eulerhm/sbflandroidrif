package org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts;

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
public final class BloggingPromptCardBuilder_Factory implements Factory<BloggingPromptCardBuilder> {
  @Override
  public BloggingPromptCardBuilder get() {
    return newInstance();
  }

  public static BloggingPromptCardBuilder_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static BloggingPromptCardBuilder newInstance() {
    return new BloggingPromptCardBuilder();
  }

  private static final class InstanceHolder {
    private static final BloggingPromptCardBuilder_Factory INSTANCE = new BloggingPromptCardBuilder_Factory();
  }
}

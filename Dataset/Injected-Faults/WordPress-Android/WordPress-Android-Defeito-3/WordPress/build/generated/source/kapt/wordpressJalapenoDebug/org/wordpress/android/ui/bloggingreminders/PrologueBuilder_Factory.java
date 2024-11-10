package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;

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
public final class PrologueBuilder_Factory implements Factory<PrologueBuilder> {
  private final Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider;

  public PrologueBuilder_Factory(
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider) {
    this.bloggingPromptsFeatureConfigProvider = bloggingPromptsFeatureConfigProvider;
  }

  @Override
  public PrologueBuilder get() {
    return newInstance(bloggingPromptsFeatureConfigProvider.get());
  }

  public static PrologueBuilder_Factory create(
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider) {
    return new PrologueBuilder_Factory(bloggingPromptsFeatureConfigProvider);
  }

  public static PrologueBuilder newInstance(
      BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig) {
    return new PrologueBuilder(bloggingPromptsFeatureConfig);
  }
}

package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.config.BloggingRemindersFeatureConfig;

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
public final class BloggingRemindersManager_Factory implements Factory<BloggingRemindersManager> {
  private final Provider<BloggingRemindersFeatureConfig> bloggingRemindersFeatureConfigProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  public BloggingRemindersManager_Factory(
      Provider<BloggingRemindersFeatureConfig> bloggingRemindersFeatureConfigProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    this.bloggingRemindersFeatureConfigProvider = bloggingRemindersFeatureConfigProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
  }

  @Override
  public BloggingRemindersManager get() {
    return newInstance(bloggingRemindersFeatureConfigProvider.get(), appPrefsWrapperProvider.get());
  }

  public static BloggingRemindersManager_Factory create(
      Provider<BloggingRemindersFeatureConfig> bloggingRemindersFeatureConfigProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    return new BloggingRemindersManager_Factory(bloggingRemindersFeatureConfigProvider, appPrefsWrapperProvider);
  }

  public static BloggingRemindersManager newInstance(
      BloggingRemindersFeatureConfig bloggingRemindersFeatureConfig,
      AppPrefsWrapper appPrefsWrapper) {
    return new BloggingRemindersManager(bloggingRemindersFeatureConfig, appPrefsWrapper);
  }
}

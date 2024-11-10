package org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.BloggingRemindersStore;
import org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BloggingPromptCardSource_Factory implements Factory<BloggingPromptCardSource> {
  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<BloggingPromptsStore> promptsStoreProvider;

  private final Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<BloggingRemindersStore> bloggingRemindersStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public BloggingPromptCardSource_Factory(
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<BloggingPromptsStore> promptsStoreProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<BloggingRemindersStore> bloggingRemindersStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.promptsStoreProvider = promptsStoreProvider;
    this.bloggingPromptsFeatureConfigProvider = bloggingPromptsFeatureConfigProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.bloggingRemindersStoreProvider = bloggingRemindersStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public BloggingPromptCardSource get() {
    return newInstance(selectedSiteRepositoryProvider.get(), promptsStoreProvider.get(), bloggingPromptsFeatureConfigProvider.get(), appPrefsWrapperProvider.get(), bloggingRemindersStoreProvider.get(), bgDispatcherProvider.get());
  }

  public static BloggingPromptCardSource_Factory create(
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<BloggingPromptsStore> promptsStoreProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<BloggingRemindersStore> bloggingRemindersStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new BloggingPromptCardSource_Factory(selectedSiteRepositoryProvider, promptsStoreProvider, bloggingPromptsFeatureConfigProvider, appPrefsWrapperProvider, bloggingRemindersStoreProvider, bgDispatcherProvider);
  }

  public static BloggingPromptCardSource newInstance(SelectedSiteRepository selectedSiteRepository,
      BloggingPromptsStore promptsStore, BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig,
      AppPrefsWrapper appPrefsWrapper, BloggingRemindersStore bloggingRemindersStore,
      CoroutineDispatcher bgDispatcher) {
    return new BloggingPromptCardSource(selectedSiteRepository, promptsStore, bloggingPromptsFeatureConfig, appPrefsWrapper, bloggingRemindersStore, bgDispatcher);
  }
}

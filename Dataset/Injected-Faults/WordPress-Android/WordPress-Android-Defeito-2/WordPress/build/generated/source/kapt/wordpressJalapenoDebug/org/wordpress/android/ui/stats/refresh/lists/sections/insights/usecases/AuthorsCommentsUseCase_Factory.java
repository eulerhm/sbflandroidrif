package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.CommentsStore;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;

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
public final class AuthorsCommentsUseCase_Factory implements Factory<AuthorsCommentsUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<CommentsStore> commentsStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public AuthorsCommentsUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<CommentsStore> commentsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.commentsStoreProvider = commentsStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public AuthorsCommentsUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), commentsStoreProvider.get(), statsSiteProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static AuthorsCommentsUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<CommentsStore> commentsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new AuthorsCommentsUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, commentsStoreProvider, statsSiteProvider, contentDescriptionHelperProvider);
  }

  public static AuthorsCommentsUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, CommentsStore commentsStore,
      StatsSiteProvider statsSiteProvider, ContentDescriptionHelper contentDescriptionHelper) {
    return new AuthorsCommentsUseCase(mainDispatcher, backgroundDispatcher, commentsStore, statsSiteProvider, contentDescriptionHelper);
  }
}

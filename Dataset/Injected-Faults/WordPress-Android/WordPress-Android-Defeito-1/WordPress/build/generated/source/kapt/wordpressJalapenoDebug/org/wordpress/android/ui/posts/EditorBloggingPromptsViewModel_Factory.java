package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore;

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
public final class EditorBloggingPromptsViewModel_Factory implements Factory<EditorBloggingPromptsViewModel> {
  private final Provider<BloggingPromptsStore> bloggingPromptsStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public EditorBloggingPromptsViewModel_Factory(
      Provider<BloggingPromptsStore> bloggingPromptsStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.bloggingPromptsStoreProvider = bloggingPromptsStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public EditorBloggingPromptsViewModel get() {
    return newInstance(bloggingPromptsStoreProvider.get(), bgDispatcherProvider.get());
  }

  public static EditorBloggingPromptsViewModel_Factory create(
      Provider<BloggingPromptsStore> bloggingPromptsStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new EditorBloggingPromptsViewModel_Factory(bloggingPromptsStoreProvider, bgDispatcherProvider);
  }

  public static EditorBloggingPromptsViewModel newInstance(
      BloggingPromptsStore bloggingPromptsStore, CoroutineDispatcher bgDispatcher) {
    return new EditorBloggingPromptsViewModel(bloggingPromptsStore, bgDispatcher);
  }
}

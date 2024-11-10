package org.wordpress.android.ui.stories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.ui.posts.SavePostToDbUseCase;

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
public final class SaveInitialPostUseCase_Factory implements Factory<SaveInitialPostUseCase> {
  private final Provider<PostStore> postStoreProvider;

  private final Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider;

  public SaveInitialPostUseCase_Factory(Provider<PostStore> postStoreProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider) {
    this.postStoreProvider = postStoreProvider;
    this.savePostToDbUseCaseProvider = savePostToDbUseCaseProvider;
  }

  @Override
  public SaveInitialPostUseCase get() {
    return newInstance(postStoreProvider.get(), savePostToDbUseCaseProvider.get());
  }

  public static SaveInitialPostUseCase_Factory create(Provider<PostStore> postStoreProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider) {
    return new SaveInitialPostUseCase_Factory(postStoreProvider, savePostToDbUseCaseProvider);
  }

  public static SaveInitialPostUseCase newInstance(PostStore postStore,
      SavePostToDbUseCase savePostToDbUseCase) {
    return new SaveInitialPostUseCase(postStore, savePostToDbUseCase);
  }
}

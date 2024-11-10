package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.util.LocaleManagerWrapper;

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
public final class EditPostRepository_Factory implements Factory<EditPostRepository> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<PostUtilsWrapper> postUtilsProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public EditPostRepository_Factory(Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<PostStore> postStoreProvider, Provider<PostUtilsWrapper> postUtilsProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.postStoreProvider = postStoreProvider;
    this.postUtilsProvider = postUtilsProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public EditPostRepository get() {
    return newInstance(localeManagerWrapperProvider.get(), postStoreProvider.get(), postUtilsProvider.get(), mainDispatcherProvider.get(), bgDispatcherProvider.get());
  }

  public static EditPostRepository_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<PostStore> postStoreProvider, Provider<PostUtilsWrapper> postUtilsProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new EditPostRepository_Factory(localeManagerWrapperProvider, postStoreProvider, postUtilsProvider, mainDispatcherProvider, bgDispatcherProvider);
  }

  public static EditPostRepository newInstance(LocaleManagerWrapper localeManagerWrapper,
      PostStore postStore, PostUtilsWrapper postUtils, CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher) {
    return new EditPostRepository(localeManagerWrapper, postStore, postUtils, mainDispatcher, bgDispatcher);
  }
}

package org.wordpress.android.ui.uploads;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PostStore;

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
public final class AutoSavePostIfNotDraftUseCase_Factory implements Factory<AutoSavePostIfNotDraftUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public AutoSavePostIfNotDraftUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<PostStore> postStoreProvider, Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.postStoreProvider = postStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public AutoSavePostIfNotDraftUseCase get() {
    return newInstance(dispatcherProvider.get(), postStoreProvider.get(), bgDispatcherProvider.get());
  }

  public static AutoSavePostIfNotDraftUseCase_Factory create(
      Provider<Dispatcher> dispatcherProvider, Provider<PostStore> postStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new AutoSavePostIfNotDraftUseCase_Factory(dispatcherProvider, postStoreProvider, bgDispatcherProvider);
  }

  public static AutoSavePostIfNotDraftUseCase newInstance(Dispatcher dispatcher,
      PostStore postStore, CoroutineDispatcher bgDispatcher) {
    return new AutoSavePostIfNotDraftUseCase(dispatcher, postStore, bgDispatcher);
  }
}

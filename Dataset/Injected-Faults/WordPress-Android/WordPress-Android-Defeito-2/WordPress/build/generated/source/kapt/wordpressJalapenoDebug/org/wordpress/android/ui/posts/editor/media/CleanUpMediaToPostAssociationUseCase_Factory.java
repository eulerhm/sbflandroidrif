package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.ui.posts.PostUtilsWrapper;
import org.wordpress.android.ui.posts.editor.AztecEditorFragmentStaticWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class CleanUpMediaToPostAssociationUseCase_Factory implements Factory<CleanUpMediaToPostAssociationUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<UploadStore> uploadStoreProvider;

  private final Provider<AztecEditorFragmentStaticWrapper> aztecEditorWrapperProvider;

  private final Provider<PostUtilsWrapper> postUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public CleanUpMediaToPostAssociationUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<UploadStore> uploadStoreProvider,
      Provider<AztecEditorFragmentStaticWrapper> aztecEditorWrapperProvider,
      Provider<PostUtilsWrapper> postUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.uploadStoreProvider = uploadStoreProvider;
    this.aztecEditorWrapperProvider = aztecEditorWrapperProvider;
    this.postUtilsWrapperProvider = postUtilsWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public CleanUpMediaToPostAssociationUseCase get() {
    return newInstance(dispatcherProvider.get(), uploadStoreProvider.get(), aztecEditorWrapperProvider.get(), postUtilsWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static CleanUpMediaToPostAssociationUseCase_Factory create(
      Provider<Dispatcher> dispatcherProvider, Provider<UploadStore> uploadStoreProvider,
      Provider<AztecEditorFragmentStaticWrapper> aztecEditorWrapperProvider,
      Provider<PostUtilsWrapper> postUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new CleanUpMediaToPostAssociationUseCase_Factory(dispatcherProvider, uploadStoreProvider, aztecEditorWrapperProvider, postUtilsWrapperProvider, bgDispatcherProvider);
  }

  public static CleanUpMediaToPostAssociationUseCase newInstance(Dispatcher dispatcher,
      UploadStore uploadStore, AztecEditorFragmentStaticWrapper aztecEditorWrapper,
      PostUtilsWrapper postUtilsWrapper, CoroutineDispatcher bgDispatcher) {
    return new CleanUpMediaToPostAssociationUseCase(dispatcher, uploadStore, aztecEditorWrapper, postUtilsWrapper, bgDispatcher);
  }
}

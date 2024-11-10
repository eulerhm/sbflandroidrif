package org.wordpress.android.ui.posts.prepublishing.home.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.posts.editor.EditorActionsProvider;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;

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
public final class GetButtonUiStateUseCase_Factory implements Factory<GetButtonUiStateUseCase> {
  private final Provider<EditorActionsProvider> editorActionsProvider;

  private final Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider;

  public GetButtonUiStateUseCase_Factory(Provider<EditorActionsProvider> editorActionsProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider) {
    this.editorActionsProvider = editorActionsProvider;
    this.uploadUtilsWrapperProvider = uploadUtilsWrapperProvider;
  }

  @Override
  public GetButtonUiStateUseCase get() {
    return newInstance(editorActionsProvider.get(), uploadUtilsWrapperProvider.get());
  }

  public static GetButtonUiStateUseCase_Factory create(
      Provider<EditorActionsProvider> editorActionsProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider) {
    return new GetButtonUiStateUseCase_Factory(editorActionsProvider, uploadUtilsWrapperProvider);
  }

  public static GetButtonUiStateUseCase newInstance(EditorActionsProvider editorActionsProvider,
      UploadUtilsWrapper uploadUtilsWrapper) {
    return new GetButtonUiStateUseCase(editorActionsProvider, uploadUtilsWrapper);
  }
}

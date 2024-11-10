package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.posts.editor.EditorTracker;

@ScopeMetadata("dagger.Reusable")
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
public final class AddExistingMediaToPostUseCase_Factory implements Factory<AddExistingMediaToPostUseCase> {
  private final Provider<EditorTracker> editorTrackerProvider;

  private final Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider;

  private final Provider<AppendMediaToEditorUseCase> appendMediaToEditorUseCaseProvider;

  public AddExistingMediaToPostUseCase_Factory(Provider<EditorTracker> editorTrackerProvider,
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider,
      Provider<AppendMediaToEditorUseCase> appendMediaToEditorUseCaseProvider) {
    this.editorTrackerProvider = editorTrackerProvider;
    this.getMediaModelUseCaseProvider = getMediaModelUseCaseProvider;
    this.appendMediaToEditorUseCaseProvider = appendMediaToEditorUseCaseProvider;
  }

  @Override
  public AddExistingMediaToPostUseCase get() {
    return newInstance(editorTrackerProvider.get(), getMediaModelUseCaseProvider.get(), appendMediaToEditorUseCaseProvider.get());
  }

  public static AddExistingMediaToPostUseCase_Factory create(
      Provider<EditorTracker> editorTrackerProvider,
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider,
      Provider<AppendMediaToEditorUseCase> appendMediaToEditorUseCaseProvider) {
    return new AddExistingMediaToPostUseCase_Factory(editorTrackerProvider, getMediaModelUseCaseProvider, appendMediaToEditorUseCaseProvider);
  }

  public static AddExistingMediaToPostUseCase newInstance(EditorTracker editorTracker,
      GetMediaModelUseCase getMediaModelUseCase,
      AppendMediaToEditorUseCase appendMediaToEditorUseCase) {
    return new AddExistingMediaToPostUseCase(editorTracker, getMediaModelUseCase, appendMediaToEditorUseCase);
  }
}

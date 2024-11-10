package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.posts.editor.EditorTracker;
import org.wordpress.android.util.MediaUtilsWrapper;

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
public final class OptimizeMediaUseCase_Factory implements Factory<OptimizeMediaUseCase> {
  private final Provider<EditorTracker> editorTrackerProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public OptimizeMediaUseCase_Factory(Provider<EditorTracker> editorTrackerProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.editorTrackerProvider = editorTrackerProvider;
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public OptimizeMediaUseCase get() {
    return newInstance(editorTrackerProvider.get(), mediaUtilsWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static OptimizeMediaUseCase_Factory create(Provider<EditorTracker> editorTrackerProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new OptimizeMediaUseCase_Factory(editorTrackerProvider, mediaUtilsWrapperProvider, bgDispatcherProvider);
  }

  public static OptimizeMediaUseCase newInstance(EditorTracker editorTracker,
      MediaUtilsWrapper mediaUtilsWrapper, CoroutineDispatcher bgDispatcher) {
    return new OptimizeMediaUseCase(editorTracker, mediaUtilsWrapper, bgDispatcher);
  }
}

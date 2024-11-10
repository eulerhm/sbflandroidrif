package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.util.MediaUtilsWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class EditorMedia_Factory implements Factory<EditorMedia> {
  private final Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider;

  private final Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider;

  private final Provider<AddExistingMediaToPostUseCase> addExistingMediaToPostUseCaseProvider;

  private final Provider<RetryFailedMediaUploadUseCase> retryFailedMediaUploadUseCaseProvider;

  private final Provider<CleanUpMediaToPostAssociationUseCase> cleanUpMediaToPostAssociationUseCaseProvider;

  private final Provider<RemoveMediaUseCase> removeMediaUseCaseProvider;

  private final Provider<ReattachUploadingMediaUseCase> reattachUploadingMediaUseCaseProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public EditorMedia_Factory(Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider,
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider,
      Provider<AddExistingMediaToPostUseCase> addExistingMediaToPostUseCaseProvider,
      Provider<RetryFailedMediaUploadUseCase> retryFailedMediaUploadUseCaseProvider,
      Provider<CleanUpMediaToPostAssociationUseCase> cleanUpMediaToPostAssociationUseCaseProvider,
      Provider<RemoveMediaUseCase> removeMediaUseCaseProvider,
      Provider<ReattachUploadingMediaUseCase> reattachUploadingMediaUseCaseProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.updateMediaModelUseCaseProvider = updateMediaModelUseCaseProvider;
    this.getMediaModelUseCaseProvider = getMediaModelUseCaseProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.addLocalMediaToPostUseCaseProvider = addLocalMediaToPostUseCaseProvider;
    this.addExistingMediaToPostUseCaseProvider = addExistingMediaToPostUseCaseProvider;
    this.retryFailedMediaUploadUseCaseProvider = retryFailedMediaUploadUseCaseProvider;
    this.cleanUpMediaToPostAssociationUseCaseProvider = cleanUpMediaToPostAssociationUseCaseProvider;
    this.removeMediaUseCaseProvider = removeMediaUseCaseProvider;
    this.reattachUploadingMediaUseCaseProvider = reattachUploadingMediaUseCaseProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public EditorMedia get() {
    return newInstance(updateMediaModelUseCaseProvider.get(), getMediaModelUseCaseProvider.get(), dispatcherProvider.get(), mediaUtilsWrapperProvider.get(), networkUtilsWrapperProvider.get(), addLocalMediaToPostUseCaseProvider.get(), addExistingMediaToPostUseCaseProvider.get(), retryFailedMediaUploadUseCaseProvider.get(), cleanUpMediaToPostAssociationUseCaseProvider.get(), removeMediaUseCaseProvider.get(), reattachUploadingMediaUseCaseProvider.get(), analyticsUtilsWrapperProvider.get(), analyticsTrackerWrapperProvider.get(), mainDispatcherProvider.get(), bgDispatcherProvider.get());
  }

  public static EditorMedia_Factory create(
      Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider,
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider,
      Provider<AddExistingMediaToPostUseCase> addExistingMediaToPostUseCaseProvider,
      Provider<RetryFailedMediaUploadUseCase> retryFailedMediaUploadUseCaseProvider,
      Provider<CleanUpMediaToPostAssociationUseCase> cleanUpMediaToPostAssociationUseCaseProvider,
      Provider<RemoveMediaUseCase> removeMediaUseCaseProvider,
      Provider<ReattachUploadingMediaUseCase> reattachUploadingMediaUseCaseProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new EditorMedia_Factory(updateMediaModelUseCaseProvider, getMediaModelUseCaseProvider, dispatcherProvider, mediaUtilsWrapperProvider, networkUtilsWrapperProvider, addLocalMediaToPostUseCaseProvider, addExistingMediaToPostUseCaseProvider, retryFailedMediaUploadUseCaseProvider, cleanUpMediaToPostAssociationUseCaseProvider, removeMediaUseCaseProvider, reattachUploadingMediaUseCaseProvider, analyticsUtilsWrapperProvider, analyticsTrackerWrapperProvider, mainDispatcherProvider, bgDispatcherProvider);
  }

  public static EditorMedia newInstance(UpdateMediaModelUseCase updateMediaModelUseCase,
      GetMediaModelUseCase getMediaModelUseCase, Dispatcher dispatcher,
      MediaUtilsWrapper mediaUtilsWrapper, NetworkUtilsWrapper networkUtilsWrapper,
      AddLocalMediaToPostUseCase addLocalMediaToPostUseCase,
      AddExistingMediaToPostUseCase addExistingMediaToPostUseCase,
      RetryFailedMediaUploadUseCase retryFailedMediaUploadUseCase,
      CleanUpMediaToPostAssociationUseCase cleanUpMediaToPostAssociationUseCase,
      RemoveMediaUseCase removeMediaUseCase,
      ReattachUploadingMediaUseCase reattachUploadingMediaUseCase,
      AnalyticsUtilsWrapper analyticsUtilsWrapper, AnalyticsTrackerWrapper analyticsTrackerWrapper,
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher bgDispatcher) {
    return new EditorMedia(updateMediaModelUseCase, getMediaModelUseCase, dispatcher, mediaUtilsWrapper, networkUtilsWrapper, addLocalMediaToPostUseCase, addExistingMediaToPostUseCase, retryFailedMediaUploadUseCase, cleanUpMediaToPostAssociationUseCase, removeMediaUseCase, reattachUploadingMediaUseCase, analyticsUtilsWrapper, analyticsTrackerWrapper, mainDispatcher, bgDispatcher);
  }
}

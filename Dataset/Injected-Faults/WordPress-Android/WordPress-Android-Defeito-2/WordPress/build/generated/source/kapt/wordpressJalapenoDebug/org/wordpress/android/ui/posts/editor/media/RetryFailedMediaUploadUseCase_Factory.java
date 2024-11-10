package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class RetryFailedMediaUploadUseCase_Factory implements Factory<RetryFailedMediaUploadUseCase> {
  private final Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider;

  private final Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider;

  private final Provider<UploadMediaUseCase> uploadMediaUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> trackerProvider;

  public RetryFailedMediaUploadUseCase_Factory(
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider,
      Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider,
      Provider<UploadMediaUseCase> uploadMediaUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> trackerProvider) {
    this.getMediaModelUseCaseProvider = getMediaModelUseCaseProvider;
    this.updateMediaModelUseCaseProvider = updateMediaModelUseCaseProvider;
    this.uploadMediaUseCaseProvider = uploadMediaUseCaseProvider;
    this.trackerProvider = trackerProvider;
  }

  @Override
  public RetryFailedMediaUploadUseCase get() {
    return newInstance(getMediaModelUseCaseProvider.get(), updateMediaModelUseCaseProvider.get(), uploadMediaUseCaseProvider.get(), trackerProvider.get());
  }

  public static RetryFailedMediaUploadUseCase_Factory create(
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider,
      Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider,
      Provider<UploadMediaUseCase> uploadMediaUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> trackerProvider) {
    return new RetryFailedMediaUploadUseCase_Factory(getMediaModelUseCaseProvider, updateMediaModelUseCaseProvider, uploadMediaUseCaseProvider, trackerProvider);
  }

  public static RetryFailedMediaUploadUseCase newInstance(GetMediaModelUseCase getMediaModelUseCase,
      UpdateMediaModelUseCase updateMediaModelUseCase, UploadMediaUseCase uploadMediaUseCase,
      AnalyticsTrackerWrapper tracker) {
    return new RetryFailedMediaUploadUseCase(getMediaModelUseCase, updateMediaModelUseCase, uploadMediaUseCase, tracker);
  }
}

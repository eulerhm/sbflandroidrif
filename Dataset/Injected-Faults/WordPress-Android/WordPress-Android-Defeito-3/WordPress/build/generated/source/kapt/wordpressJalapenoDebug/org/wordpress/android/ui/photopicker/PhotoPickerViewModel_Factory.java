package org.wordpress.android.ui.photopicker;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.posts.editor.media.CopyMediaToAppStorageUseCase;
import org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class PhotoPickerViewModel_Factory implements Factory<PhotoPickerViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<DeviceMediaListBuilder> deviceMediaListBuilderProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<PermissionsHandler> permissionsHandlerProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<CopyMediaToAppStorageUseCase> copyMediaToAppStorageUseCaseProvider;

  private final Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider;

  public PhotoPickerViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<DeviceMediaListBuilder> deviceMediaListBuilderProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<PermissionsHandler> permissionsHandlerProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<CopyMediaToAppStorageUseCase> copyMediaToAppStorageUseCaseProvider,
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.deviceMediaListBuilderProvider = deviceMediaListBuilderProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.permissionsHandlerProvider = permissionsHandlerProvider;
    this.resourceProvider = resourceProvider;
    this.copyMediaToAppStorageUseCaseProvider = copyMediaToAppStorageUseCaseProvider;
    this.getMediaModelUseCaseProvider = getMediaModelUseCaseProvider;
  }

  @Override
  public PhotoPickerViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), deviceMediaListBuilderProvider.get(), analyticsUtilsWrapperProvider.get(), analyticsTrackerWrapperProvider.get(), permissionsHandlerProvider.get(), resourceProvider.get(), copyMediaToAppStorageUseCaseProvider.get(), getMediaModelUseCaseProvider.get());
  }

  public static PhotoPickerViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<DeviceMediaListBuilder> deviceMediaListBuilderProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<PermissionsHandler> permissionsHandlerProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<CopyMediaToAppStorageUseCase> copyMediaToAppStorageUseCaseProvider,
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider) {
    return new PhotoPickerViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, deviceMediaListBuilderProvider, analyticsUtilsWrapperProvider, analyticsTrackerWrapperProvider, permissionsHandlerProvider, resourceProvider, copyMediaToAppStorageUseCaseProvider, getMediaModelUseCaseProvider);
  }

  public static PhotoPickerViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, DeviceMediaListBuilder deviceMediaListBuilder,
      AnalyticsUtilsWrapper analyticsUtilsWrapper, AnalyticsTrackerWrapper analyticsTrackerWrapper,
      PermissionsHandler permissionsHandler, ResourceProvider resourceProvider,
      CopyMediaToAppStorageUseCase copyMediaToAppStorageUseCase,
      GetMediaModelUseCase getMediaModelUseCase) {
    return new PhotoPickerViewModel(mainDispatcher, bgDispatcher, deviceMediaListBuilder, analyticsUtilsWrapper, analyticsTrackerWrapper, permissionsHandler, resourceProvider, copyMediaToAppStorageUseCase, getMediaModelUseCase);
  }
}

package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.ui.uploads.UploadServiceFacade;
import org.wordpress.android.util.FluxCUtilsWrapper;
import org.wordpress.android.util.SiteUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class FeaturedImageHelper_Factory implements Factory<FeaturedImageHelper> {
  private final Provider<UploadStore> uploadStoreProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<UploadServiceFacade> uploadServiceFacadeProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider;

  private final Provider<SiteUtilsWrapper> siteUtilsWrapperProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public FeaturedImageHelper_Factory(Provider<UploadStore> uploadStoreProvider,
      Provider<MediaStore> mediaStoreProvider,
      Provider<UploadServiceFacade> uploadServiceFacadeProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<SiteUtilsWrapper> siteUtilsWrapperProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.uploadStoreProvider = uploadStoreProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.uploadServiceFacadeProvider = uploadServiceFacadeProvider;
    this.resourceProvider = resourceProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.fluxCUtilsWrapperProvider = fluxCUtilsWrapperProvider;
    this.siteUtilsWrapperProvider = siteUtilsWrapperProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public FeaturedImageHelper get() {
    return newInstance(uploadStoreProvider.get(), mediaStoreProvider.get(), uploadServiceFacadeProvider.get(), resourceProvider.get(), readerUtilsWrapperProvider.get(), fluxCUtilsWrapperProvider.get(), siteUtilsWrapperProvider.get(), dispatcherProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static FeaturedImageHelper_Factory create(Provider<UploadStore> uploadStoreProvider,
      Provider<MediaStore> mediaStoreProvider,
      Provider<UploadServiceFacade> uploadServiceFacadeProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<SiteUtilsWrapper> siteUtilsWrapperProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new FeaturedImageHelper_Factory(uploadStoreProvider, mediaStoreProvider, uploadServiceFacadeProvider, resourceProvider, readerUtilsWrapperProvider, fluxCUtilsWrapperProvider, siteUtilsWrapperProvider, dispatcherProvider, analyticsTrackerWrapperProvider);
  }

  public static FeaturedImageHelper newInstance(UploadStore uploadStore, MediaStore mediaStore,
      UploadServiceFacade uploadServiceFacade, ResourceProvider resourceProvider,
      ReaderUtilsWrapper readerUtilsWrapper, FluxCUtilsWrapper fluxCUtilsWrapper,
      SiteUtilsWrapper siteUtilsWrapper, Dispatcher dispatcher,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new FeaturedImageHelper(uploadStore, mediaStore, uploadServiceFacade, resourceProvider, readerUtilsWrapper, fluxCUtilsWrapper, siteUtilsWrapper, dispatcher, analyticsTrackerWrapper);
  }
}

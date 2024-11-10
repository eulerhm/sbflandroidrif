package org.wordpress.android.ui.mysite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stories.StoriesMediaPickerResultHandler;
import org.wordpress.android.ui.stories.StoriesTrackerHelper;
import org.wordpress.android.util.EventBusWrapper;
import org.wordpress.android.viewmodel.ContextProvider;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class SiteStoriesHandler_Factory implements Factory<SiteStoriesHandler> {
  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StoriesTrackerHelper> storiesTrackerHelperProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<StoriesMediaPickerResultHandler> storiesMediaPickerResultHandlerProvider;

  public SiteStoriesHandler_Factory(Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<StoriesTrackerHelper> storiesTrackerHelperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<StoriesMediaPickerResultHandler> storiesMediaPickerResultHandlerProvider) {
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.resourceProvider = resourceProvider;
    this.storiesTrackerHelperProvider = storiesTrackerHelperProvider;
    this.contextProvider = contextProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.storiesMediaPickerResultHandlerProvider = storiesMediaPickerResultHandlerProvider;
  }

  @Override
  public SiteStoriesHandler get() {
    return newInstance(eventBusWrapperProvider.get(), resourceProvider.get(), storiesTrackerHelperProvider.get(), contextProvider.get(), selectedSiteRepositoryProvider.get(), storiesMediaPickerResultHandlerProvider.get());
  }

  public static SiteStoriesHandler_Factory create(Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<StoriesTrackerHelper> storiesTrackerHelperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<StoriesMediaPickerResultHandler> storiesMediaPickerResultHandlerProvider) {
    return new SiteStoriesHandler_Factory(eventBusWrapperProvider, resourceProvider, storiesTrackerHelperProvider, contextProvider, selectedSiteRepositoryProvider, storiesMediaPickerResultHandlerProvider);
  }

  public static SiteStoriesHandler newInstance(EventBusWrapper eventBusWrapper,
      ResourceProvider resourceProvider, StoriesTrackerHelper storiesTrackerHelper,
      ContextProvider contextProvider, SelectedSiteRepository selectedSiteRepository,
      StoriesMediaPickerResultHandler storiesMediaPickerResultHandler) {
    return new SiteStoriesHandler(eventBusWrapper, resourceProvider, storiesTrackerHelper, contextProvider, selectedSiteRepository, storiesMediaPickerResultHandler);
  }
}

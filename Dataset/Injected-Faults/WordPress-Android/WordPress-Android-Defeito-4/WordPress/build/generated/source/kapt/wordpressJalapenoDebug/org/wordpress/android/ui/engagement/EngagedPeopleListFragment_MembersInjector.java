package org.wordpress.android.ui.engagement;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class EngagedPeopleListFragment_MembersInjector implements MembersInjector<EngagedPeopleListFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public EngagedPeopleListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ResourceProvider> resourceProvider, Provider<ImageManager> imageManagerProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.resourceProvider = resourceProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  public static MembersInjector<EngagedPeopleListFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ResourceProvider> resourceProvider, Provider<ImageManager> imageManagerProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new EngagedPeopleListFragment_MembersInjector(viewModelFactoryProvider, resourceProvider, imageManagerProvider, snackbarSequencerProvider, uiHelpersProvider, readerTrackerProvider, analyticsUtilsWrapperProvider);
  }

  @Override
  public void injectMembers(EngagedPeopleListFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectResourceProvider(instance, resourceProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectReaderTracker(instance, readerTrackerProvider.get());
    injectAnalyticsUtilsWrapper(instance, analyticsUtilsWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.EngagedPeopleListFragment.viewModelFactory")
  public static void injectViewModelFactory(EngagedPeopleListFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.EngagedPeopleListFragment.resourceProvider")
  public static void injectResourceProvider(EngagedPeopleListFragment instance,
      ResourceProvider resourceProvider) {
    instance.resourceProvider = resourceProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.EngagedPeopleListFragment.imageManager")
  public static void injectImageManager(EngagedPeopleListFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.EngagedPeopleListFragment.snackbarSequencer")
  public static void injectSnackbarSequencer(EngagedPeopleListFragment instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.EngagedPeopleListFragment.uiHelpers")
  public static void injectUiHelpers(EngagedPeopleListFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.EngagedPeopleListFragment.readerTracker")
  public static void injectReaderTracker(EngagedPeopleListFragment instance,
      ReaderTracker readerTracker) {
    instance.readerTracker = readerTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.EngagedPeopleListFragment.analyticsUtilsWrapper")
  public static void injectAnalyticsUtilsWrapper(EngagedPeopleListFragment instance,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    instance.analyticsUtilsWrapper = analyticsUtilsWrapper;
  }
}

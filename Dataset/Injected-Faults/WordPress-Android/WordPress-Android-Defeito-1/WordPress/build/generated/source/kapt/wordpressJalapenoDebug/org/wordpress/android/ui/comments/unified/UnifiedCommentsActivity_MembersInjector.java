package org.wordpress.android.ui.comments.unified;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class UnifiedCommentsActivity_MembersInjector implements MembersInjector<UnifiedCommentsActivity> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public UnifiedCommentsActivity_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  public static MembersInjector<UnifiedCommentsActivity> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new UnifiedCommentsActivity_MembersInjector(viewModelFactoryProvider, analyticsTrackerWrapperProvider, selectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(UnifiedCommentsActivity instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectAnalyticsTrackerWrapper(instance, analyticsTrackerWrapperProvider.get());
    injectSelectedSiteRepository(instance, selectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentsActivity.viewModelFactory")
  public static void injectViewModelFactory(UnifiedCommentsActivity instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentsActivity.analyticsTrackerWrapper")
  public static void injectAnalyticsTrackerWrapper(UnifiedCommentsActivity instance,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    instance.analyticsTrackerWrapper = analyticsTrackerWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentsActivity.selectedSiteRepository")
  public static void injectSelectedSiteRepository(UnifiedCommentsActivity instance,
      SelectedSiteRepository selectedSiteRepository) {
    instance.selectedSiteRepository = selectedSiteRepository;
  }
}

package org.wordpress.android.ui.about;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.models.recommend.RecommendApiCallsProvider;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.WpUrlUtilsWrapper;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class UnifiedAboutViewModel_Factory implements Factory<UnifiedAboutViewModel> {
  private final Provider<ContextProvider> contextProvider;

  private final Provider<WpUrlUtilsWrapper> wpUrlUtilsProvider;

  private final Provider<RecommendApiCallsProvider> recommendApiCallsProvider;

  private final Provider<BuildConfigWrapper> buildConfigProvider;

  private final Provider<UnifiedAboutTracker> unifiedAboutTrackerProvider;

  public UnifiedAboutViewModel_Factory(Provider<ContextProvider> contextProvider,
      Provider<WpUrlUtilsWrapper> wpUrlUtilsProvider,
      Provider<RecommendApiCallsProvider> recommendApiCallsProvider,
      Provider<BuildConfigWrapper> buildConfigProvider,
      Provider<UnifiedAboutTracker> unifiedAboutTrackerProvider) {
    this.contextProvider = contextProvider;
    this.wpUrlUtilsProvider = wpUrlUtilsProvider;
    this.recommendApiCallsProvider = recommendApiCallsProvider;
    this.buildConfigProvider = buildConfigProvider;
    this.unifiedAboutTrackerProvider = unifiedAboutTrackerProvider;
  }

  @Override
  public UnifiedAboutViewModel get() {
    return newInstance(contextProvider.get(), wpUrlUtilsProvider.get(), recommendApiCallsProvider.get(), buildConfigProvider.get(), unifiedAboutTrackerProvider.get());
  }

  public static UnifiedAboutViewModel_Factory create(Provider<ContextProvider> contextProvider,
      Provider<WpUrlUtilsWrapper> wpUrlUtilsProvider,
      Provider<RecommendApiCallsProvider> recommendApiCallsProvider,
      Provider<BuildConfigWrapper> buildConfigProvider,
      Provider<UnifiedAboutTracker> unifiedAboutTrackerProvider) {
    return new UnifiedAboutViewModel_Factory(contextProvider, wpUrlUtilsProvider, recommendApiCallsProvider, buildConfigProvider, unifiedAboutTrackerProvider);
  }

  public static UnifiedAboutViewModel newInstance(ContextProvider contextProvider,
      WpUrlUtilsWrapper wpUrlUtils, RecommendApiCallsProvider recommendApiCallsProvider,
      BuildConfigWrapper buildConfig, UnifiedAboutTracker unifiedAboutTracker) {
    return new UnifiedAboutViewModel(contextProvider, wpUrlUtils, recommendApiCallsProvider, buildConfig, unifiedAboutTracker);
  }
}

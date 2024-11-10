package org.wordpress.android.viewmodel.accounts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class PostSignupInterstitialViewModel_Factory implements Factory<PostSignupInterstitialViewModel> {
  private final Provider<AppPrefsWrapper> appPrefsProvider;

  private final Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public PostSignupInterstitialViewModel_Factory(Provider<AppPrefsWrapper> appPrefsProvider,
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.appPrefsProvider = appPrefsProvider;
    this.unifiedLoginTrackerProvider = unifiedLoginTrackerProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public PostSignupInterstitialViewModel get() {
    return newInstance(appPrefsProvider.get(), unifiedLoginTrackerProvider.get(), analyticsTrackerProvider.get());
  }

  public static PostSignupInterstitialViewModel_Factory create(
      Provider<AppPrefsWrapper> appPrefsProvider,
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new PostSignupInterstitialViewModel_Factory(appPrefsProvider, unifiedLoginTrackerProvider, analyticsTrackerProvider);
  }

  public static PostSignupInterstitialViewModel newInstance(AppPrefsWrapper appPrefs,
      UnifiedLoginTracker unifiedLoginTracker, AnalyticsTrackerWrapper analyticsTracker) {
    return new PostSignupInterstitialViewModel(appPrefs, unifiedLoginTracker, analyticsTracker);
  }
}

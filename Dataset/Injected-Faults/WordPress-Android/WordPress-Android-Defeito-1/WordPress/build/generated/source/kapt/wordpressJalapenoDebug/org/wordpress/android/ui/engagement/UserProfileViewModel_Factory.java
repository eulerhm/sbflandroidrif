package org.wordpress.android.ui.engagement;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;
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
public final class UserProfileViewModel_Factory implements Factory<UserProfileViewModel> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public UserProfileViewModel_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.resourceProvider = resourceProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public UserProfileViewModel get() {
    return newInstance(resourceProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static UserProfileViewModel_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new UserProfileViewModel_Factory(resourceProvider, analyticsUtilsWrapperProvider);
  }

  public static UserProfileViewModel newInstance(ResourceProvider resourceProvider,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new UserProfileViewModel(resourceProvider, analyticsUtilsWrapper);
  }
}

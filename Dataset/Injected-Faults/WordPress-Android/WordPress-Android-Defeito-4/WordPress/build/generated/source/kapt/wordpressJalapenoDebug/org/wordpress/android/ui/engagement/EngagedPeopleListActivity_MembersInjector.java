package org.wordpress.android.ui.engagement;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class EngagedPeopleListActivity_MembersInjector implements MembersInjector<EngagedPeopleListActivity> {
  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public EngagedPeopleListActivity_MembersInjector(
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  public static MembersInjector<EngagedPeopleListActivity> create(
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new EngagedPeopleListActivity_MembersInjector(analyticsUtilsWrapperProvider);
  }

  @Override
  public void injectMembers(EngagedPeopleListActivity instance) {
    injectAnalyticsUtilsWrapper(instance, analyticsUtilsWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.EngagedPeopleListActivity.analyticsUtilsWrapper")
  public static void injectAnalyticsUtilsWrapper(EngagedPeopleListActivity instance,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    instance.analyticsUtilsWrapper = analyticsUtilsWrapper;
  }
}

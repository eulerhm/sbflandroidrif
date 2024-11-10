package org.wordpress.android.ui.notifications;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class DismissNotificationReceiver_MembersInjector implements MembersInjector<DismissNotificationReceiver> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public DismissNotificationReceiver_MembersInjector(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  public static MembersInjector<DismissNotificationReceiver> create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new DismissNotificationReceiver_MembersInjector(analyticsTrackerWrapperProvider);
  }

  @Override
  public void injectMembers(DismissNotificationReceiver instance) {
    injectAnalyticsTrackerWrapper(instance, analyticsTrackerWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.DismissNotificationReceiver.analyticsTrackerWrapper")
  public static void injectAnalyticsTrackerWrapper(DismissNotificationReceiver instance,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    instance.analyticsTrackerWrapper = analyticsTrackerWrapper;
  }
}

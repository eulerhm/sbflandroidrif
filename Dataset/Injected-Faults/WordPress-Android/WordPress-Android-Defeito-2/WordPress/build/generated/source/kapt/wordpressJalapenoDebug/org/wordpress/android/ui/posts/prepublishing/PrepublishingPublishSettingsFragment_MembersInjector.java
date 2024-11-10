package org.wordpress.android.ui.posts.prepublishing;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.posts.PublishSettingsFragment_MembersInjector;
import org.wordpress.android.ui.utils.UiHelpers;
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
public final class PrepublishingPublishSettingsFragment_MembersInjector implements MembersInjector<PrepublishingPublishSettingsFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public PrepublishingPublishSettingsFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<PrepublishingPublishSettingsFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    return new PrepublishingPublishSettingsFragment_MembersInjector(viewModelFactoryProvider, analyticsTrackerWrapperProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(PrepublishingPublishSettingsFragment instance) {
    PublishSettingsFragment_MembersInjector.injectViewModelFactory(instance, viewModelFactoryProvider.get());
    PublishSettingsFragment_MembersInjector.injectAnalyticsTrackerWrapper(instance, analyticsTrackerWrapperProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.prepublishing.PrepublishingPublishSettingsFragment.uiHelpers")
  public static void injectUiHelpers(PrepublishingPublishSettingsFragment instance,
      UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}

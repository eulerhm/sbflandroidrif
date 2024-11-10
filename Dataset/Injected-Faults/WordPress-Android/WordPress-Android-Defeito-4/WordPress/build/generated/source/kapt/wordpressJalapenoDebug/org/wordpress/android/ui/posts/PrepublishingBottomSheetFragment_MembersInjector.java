package org.wordpress.android.ui.posts;

import androidx.lifecycle.ViewModelProvider;
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
public final class PrepublishingBottomSheetFragment_MembersInjector implements MembersInjector<PrepublishingBottomSheetFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public PrepublishingBottomSheetFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  public static MembersInjector<PrepublishingBottomSheetFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new PrepublishingBottomSheetFragment_MembersInjector(viewModelFactoryProvider, analyticsTrackerWrapperProvider);
  }

  @Override
  public void injectMembers(PrepublishingBottomSheetFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectAnalyticsTrackerWrapper(instance, analyticsTrackerWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingBottomSheetFragment.viewModelFactory")
  public static void injectViewModelFactory(PrepublishingBottomSheetFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingBottomSheetFragment.analyticsTrackerWrapper")
  public static void injectAnalyticsTrackerWrapper(PrepublishingBottomSheetFragment instance,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    instance.analyticsTrackerWrapper = analyticsTrackerWrapper;
  }
}

package org.wordpress.android.ui.posts;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.TaxonomyStore;
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
public final class PrepublishingTagsFragment_MembersInjector implements MembersInjector<PrepublishingTagsFragment> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<TaxonomyStore> mTaxonomyStoreProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public PrepublishingTagsFragment_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<TaxonomyStore> mTaxonomyStoreProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mTaxonomyStoreProvider = mTaxonomyStoreProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  public static MembersInjector<PrepublishingTagsFragment> create(
      Provider<Dispatcher> mDispatcherProvider, Provider<TaxonomyStore> mTaxonomyStoreProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new PrepublishingTagsFragment_MembersInjector(mDispatcherProvider, mTaxonomyStoreProvider, viewModelFactoryProvider, uiHelpersProvider, analyticsTrackerWrapperProvider);
  }

  @Override
  public void injectMembers(PrepublishingTagsFragment instance) {
    TagsFragment_MembersInjector.injectMDispatcher(instance, mDispatcherProvider.get());
    TagsFragment_MembersInjector.injectMTaxonomyStore(instance, mTaxonomyStoreProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectAnalyticsTrackerWrapper(instance, analyticsTrackerWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingTagsFragment.viewModelFactory")
  public static void injectViewModelFactory(PrepublishingTagsFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingTagsFragment.uiHelpers")
  public static void injectUiHelpers(PrepublishingTagsFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingTagsFragment.analyticsTrackerWrapper")
  public static void injectAnalyticsTrackerWrapper(PrepublishingTagsFragment instance,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    instance.analyticsTrackerWrapper = analyticsTrackerWrapper;
  }
}

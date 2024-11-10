package org.wordpress.android.ui.reader.subfilter;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig;

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
public final class SubfilterPageFragment_MembersInjector implements MembersInjector<SubfilterPageFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<SeenUnseenWithCounterFeatureConfig> seenUnseenWithCounterFeatureConfigProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  public SubfilterPageFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<SeenUnseenWithCounterFeatureConfig> seenUnseenWithCounterFeatureConfigProvider,
      Provider<StatsUtils> statsUtilsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.seenUnseenWithCounterFeatureConfigProvider = seenUnseenWithCounterFeatureConfigProvider;
    this.statsUtilsProvider = statsUtilsProvider;
  }

  public static MembersInjector<SubfilterPageFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<SeenUnseenWithCounterFeatureConfig> seenUnseenWithCounterFeatureConfigProvider,
      Provider<StatsUtils> statsUtilsProvider) {
    return new SubfilterPageFragment_MembersInjector(androidInjectorProvider, viewModelFactoryProvider, uiHelpersProvider, seenUnseenWithCounterFeatureConfigProvider, statsUtilsProvider);
  }

  @Override
  public void injectMembers(SubfilterPageFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectSeenUnseenWithCounterFeatureConfig(instance, seenUnseenWithCounterFeatureConfigProvider.get());
    injectStatsUtils(instance, statsUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.subfilter.SubfilterPageFragment.viewModelFactory")
  public static void injectViewModelFactory(SubfilterPageFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.subfilter.SubfilterPageFragment.uiHelpers")
  public static void injectUiHelpers(SubfilterPageFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.subfilter.SubfilterPageFragment.seenUnseenWithCounterFeatureConfig")
  public static void injectSeenUnseenWithCounterFeatureConfig(SubfilterPageFragment instance,
      SeenUnseenWithCounterFeatureConfig seenUnseenWithCounterFeatureConfig) {
    instance.seenUnseenWithCounterFeatureConfig = seenUnseenWithCounterFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.subfilter.SubfilterPageFragment.statsUtils")
  public static void injectStatsUtils(SubfilterPageFragment instance, StatsUtils statsUtils) {
    instance.statsUtils = statsUtils;
  }
}

package org.wordpress.android.ui.stats.refresh;

import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsNavigator;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.image.ImageManager;

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
public final class StatsViewAllFragment_MembersInjector implements MembersInjector<StatsViewAllFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<StatsViewAllViewModelFactory.Builder> viewModelFactoryBuilderProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<StatsNavigator> navigatorProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public StatsViewAllFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<StatsViewAllViewModelFactory.Builder> viewModelFactoryBuilderProvider,
      Provider<ImageManager> imageManagerProvider, Provider<StatsNavigator> navigatorProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<UiHelpers> uiHelpersProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.viewModelFactoryBuilderProvider = viewModelFactoryBuilderProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.navigatorProvider = navigatorProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<StatsViewAllFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<StatsViewAllViewModelFactory.Builder> viewModelFactoryBuilderProvider,
      Provider<ImageManager> imageManagerProvider, Provider<StatsNavigator> navigatorProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<UiHelpers> uiHelpersProvider) {
    return new StatsViewAllFragment_MembersInjector(androidInjectorProvider, viewModelFactoryBuilderProvider, imageManagerProvider, navigatorProvider, statsSiteProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(StatsViewAllFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectViewModelFactoryBuilder(instance, viewModelFactoryBuilderProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectNavigator(instance, navigatorProvider.get());
    injectStatsSiteProvider(instance, statsSiteProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.StatsViewAllFragment.viewModelFactoryBuilder")
  public static void injectViewModelFactoryBuilder(StatsViewAllFragment instance,
      StatsViewAllViewModelFactory.Builder viewModelFactoryBuilder) {
    instance.viewModelFactoryBuilder = viewModelFactoryBuilder;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.StatsViewAllFragment.imageManager")
  public static void injectImageManager(StatsViewAllFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.StatsViewAllFragment.navigator")
  public static void injectNavigator(StatsViewAllFragment instance, StatsNavigator navigator) {
    instance.navigator = navigator;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.StatsViewAllFragment.statsSiteProvider")
  public static void injectStatsSiteProvider(StatsViewAllFragment instance,
      StatsSiteProvider statsSiteProvider) {
    instance.statsSiteProvider = statsSiteProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.StatsViewAllFragment.uiHelpers")
  public static void injectUiHelpers(StatsViewAllFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}

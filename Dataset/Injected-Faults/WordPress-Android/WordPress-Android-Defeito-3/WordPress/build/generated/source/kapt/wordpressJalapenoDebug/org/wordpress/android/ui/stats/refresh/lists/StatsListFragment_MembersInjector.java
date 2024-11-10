package org.wordpress.android.ui.stats.refresh.lists;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
import org.wordpress.android.ui.stats.refresh.utils.StatsNavigator;
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
public final class StatsListFragment_MembersInjector implements MembersInjector<StatsListFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<StatsNavigator> navigatorProvider;

  public StatsListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<StatsNavigator> navigatorProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.navigatorProvider = navigatorProvider;
  }

  public static MembersInjector<StatsListFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<StatsNavigator> navigatorProvider) {
    return new StatsListFragment_MembersInjector(viewModelFactoryProvider, imageManagerProvider, statsDateFormatterProvider, navigatorProvider);
  }

  @Override
  public void injectMembers(StatsListFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectStatsDateFormatter(instance, statsDateFormatterProvider.get());
    injectNavigator(instance, navigatorProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.StatsListFragment.viewModelFactory")
  public static void injectViewModelFactory(StatsListFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.StatsListFragment.imageManager")
  public static void injectImageManager(StatsListFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.StatsListFragment.statsDateFormatter")
  public static void injectStatsDateFormatter(StatsListFragment instance,
      StatsDateFormatter statsDateFormatter) {
    instance.statsDateFormatter = statsDateFormatter;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.StatsListFragment.navigator")
  public static void injectNavigator(StatsListFragment instance, StatsNavigator navigator) {
    instance.navigator = navigator;
  }
}

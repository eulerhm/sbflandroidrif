package org.wordpress.android.ui.stats.refresh.lists.detail;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;

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
public final class StatsDetailFragment_MembersInjector implements MembersInjector<StatsDetailFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  public StatsDetailFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<StatsSiteProvider> statsSiteProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.statsSiteProvider = statsSiteProvider;
  }

  public static MembersInjector<StatsDetailFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<StatsSiteProvider> statsSiteProvider) {
    return new StatsDetailFragment_MembersInjector(androidInjectorProvider, viewModelFactoryProvider, statsSiteProvider);
  }

  @Override
  public void injectMembers(StatsDetailFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectStatsSiteProvider(instance, statsSiteProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailFragment.viewModelFactory")
  public static void injectViewModelFactory(StatsDetailFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailFragment.statsSiteProvider")
  public static void injectStatsSiteProvider(StatsDetailFragment instance,
      StatsSiteProvider statsSiteProvider) {
    instance.statsSiteProvider = statsSiteProvider;
  }
}

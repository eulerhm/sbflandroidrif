package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeStatsDetailFragment.StatsDetailFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeStatsDetailFragment {
  private ApplicationModule_ContributeStatsDetailFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StatsDetailFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatsDetailFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatsDetailFragmentSubcomponent extends AndroidInjector<StatsDetailFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatsDetailFragment> {}
  }
}

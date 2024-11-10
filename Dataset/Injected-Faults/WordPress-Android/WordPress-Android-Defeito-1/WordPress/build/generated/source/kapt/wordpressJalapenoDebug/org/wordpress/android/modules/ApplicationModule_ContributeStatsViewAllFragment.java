package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.StatsViewAllFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeStatsViewAllFragment.StatsViewAllFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeStatsViewAllFragment {
  private ApplicationModule_ContributeStatsViewAllFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StatsViewAllFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatsViewAllFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatsViewAllFragmentSubcomponent extends AndroidInjector<StatsViewAllFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatsViewAllFragment> {}
  }
}

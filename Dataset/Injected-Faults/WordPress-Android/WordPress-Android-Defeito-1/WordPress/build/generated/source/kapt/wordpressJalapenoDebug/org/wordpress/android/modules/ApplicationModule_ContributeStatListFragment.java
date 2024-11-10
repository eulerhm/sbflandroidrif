package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.lists.StatsListFragment;

@Module(
  subcomponents = ApplicationModule_ContributeStatListFragment.StatsListFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeStatListFragment {
  private ApplicationModule_ContributeStatListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StatsListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatsListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatsListFragmentSubcomponent extends AndroidInjector<StatsListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatsListFragment> {}
  }
}

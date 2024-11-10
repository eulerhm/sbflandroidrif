package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeStatsViewsWidgetConfigureFragment
          .StatsWidgetConfigureFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeStatsViewsWidgetConfigureFragment {
  private ApplicationModule_ContributeStatsViewsWidgetConfigureFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StatsWidgetConfigureFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatsWidgetConfigureFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatsWidgetConfigureFragmentSubcomponent
      extends AndroidInjector<StatsWidgetConfigureFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatsWidgetConfigureFragment> {}
  }
}

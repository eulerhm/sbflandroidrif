package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidgetConfigureFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeStatsMinifiedWidgetConfigureFragment
          .StatsMinifiedWidgetConfigureFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeStatsMinifiedWidgetConfigureFragment {
  private ApplicationModule_ContributeStatsMinifiedWidgetConfigureFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StatsMinifiedWidgetConfigureFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatsMinifiedWidgetConfigureFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatsMinifiedWidgetConfigureFragmentSubcomponent
      extends AndroidInjector<StatsMinifiedWidgetConfigureFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatsMinifiedWidgetConfigureFragment> {}
  }
}

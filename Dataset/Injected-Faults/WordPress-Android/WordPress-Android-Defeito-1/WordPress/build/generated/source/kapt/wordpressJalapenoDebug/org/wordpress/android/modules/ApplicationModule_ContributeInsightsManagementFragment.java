package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeInsightsManagementFragment.InsightsManagementFragmentSubcomponent
          .class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeInsightsManagementFragment {
  private ApplicationModule_ContributeInsightsManagementFragment() {}

  @Binds
  @IntoMap
  @ClassKey(InsightsManagementFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      InsightsManagementFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface InsightsManagementFragmentSubcomponent
      extends AndroidInjector<InsightsManagementFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<InsightsManagementFragment> {}
  }
}

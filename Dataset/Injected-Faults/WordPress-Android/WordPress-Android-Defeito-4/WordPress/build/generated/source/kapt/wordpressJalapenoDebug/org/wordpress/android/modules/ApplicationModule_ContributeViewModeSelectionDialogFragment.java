package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetColorSelectionDialogFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeViewModeSelectionDialogFragment
          .StatsWidgetColorSelectionDialogFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeViewModeSelectionDialogFragment {
  private ApplicationModule_ContributeViewModeSelectionDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StatsWidgetColorSelectionDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatsWidgetColorSelectionDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatsWidgetColorSelectionDialogFragmentSubcomponent
      extends AndroidInjector<StatsWidgetColorSelectionDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatsWidgetColorSelectionDialogFragment> {}
  }
}

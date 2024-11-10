package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetDataTypeSelectionDialogFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeDataTypeSelectionDialogFragment
          .StatsWidgetDataTypeSelectionDialogFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeDataTypeSelectionDialogFragment {
  private ApplicationModule_ContributeDataTypeSelectionDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StatsWidgetDataTypeSelectionDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatsWidgetDataTypeSelectionDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatsWidgetDataTypeSelectionDialogFragmentSubcomponent
      extends AndroidInjector<StatsWidgetDataTypeSelectionDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatsWidgetDataTypeSelectionDialogFragment> {}
  }
}

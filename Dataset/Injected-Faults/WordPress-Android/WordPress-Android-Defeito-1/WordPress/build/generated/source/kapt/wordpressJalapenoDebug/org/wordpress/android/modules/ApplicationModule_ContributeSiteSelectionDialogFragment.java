package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetSiteSelectionDialogFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeSiteSelectionDialogFragment
          .StatsWidgetSiteSelectionDialogFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeSiteSelectionDialogFragment {
  private ApplicationModule_ContributeSiteSelectionDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StatsWidgetSiteSelectionDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatsWidgetSiteSelectionDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatsWidgetSiteSelectionDialogFragmentSubcomponent
      extends AndroidInjector<StatsWidgetSiteSelectionDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatsWidgetSiteSelectionDialogFragment> {}
  }
}

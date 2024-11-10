package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.debug.DebugSettingsFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeDebugSettingsFragment.DebugSettingsFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeDebugSettingsFragment {
  private ApplicationModule_ContributeDebugSettingsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(DebugSettingsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      DebugSettingsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface DebugSettingsFragmentSubcomponent
      extends AndroidInjector<DebugSettingsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<DebugSettingsFragment> {}
  }
}

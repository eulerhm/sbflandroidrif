package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.accounts.signup.SettingsUsernameChangerFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeSettingsUsernameChangerFragment
          .SettingsUsernameChangerFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeSettingsUsernameChangerFragment {
  private ApplicationModule_ContributeSettingsUsernameChangerFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SettingsUsernameChangerFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SettingsUsernameChangerFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SettingsUsernameChangerFragmentSubcomponent
      extends AndroidInjector<SettingsUsernameChangerFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SettingsUsernameChangerFragment> {}
  }
}

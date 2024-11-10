package org.owntracks.android.ui.welcome;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.welcome.finish.FinishFragment;
import org.owntracks.android.ui.welcome.finish.FinishFragmentModule;

@Module(subcomponents = WelcomeActivityModule_BindFinishFragment.FinishFragmentSubcomponent.class)
public abstract class WelcomeActivityModule_BindFinishFragment {
  private WelcomeActivityModule_BindFinishFragment() {}

  @Binds
  @IntoMap
  @ClassKey(FinishFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      FinishFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = FinishFragmentModule.class)
  @PerFragment
  public interface FinishFragmentSubcomponent extends AndroidInjector<FinishFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<FinishFragment> {}
  }
}

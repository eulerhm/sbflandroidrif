package org.owntracks.android.ui.welcome;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.welcome.play.PlayFragment;
import org.owntracks.android.ui.welcome.play.PlayFragmentModule;

@Module(subcomponents = WelcomeActivityModule_BindPlayFragment.PlayFragmentSubcomponent.class)
public abstract class WelcomeActivityModule_BindPlayFragment {
  private WelcomeActivityModule_BindPlayFragment() {}

  @Binds
  @IntoMap
  @ClassKey(PlayFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      PlayFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = PlayFragmentModule.class)
  @PerFragment
  public interface PlayFragmentSubcomponent extends AndroidInjector<PlayFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<PlayFragment> {}
  }
}

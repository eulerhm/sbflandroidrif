package org.owntracks.android.ui.welcome;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.welcome.intro.IntroFragment;
import org.owntracks.android.ui.welcome.intro.IntroFragmentModule;

@Module(subcomponents = WelcomeActivityModule_BindIntroFragment.IntroFragmentSubcomponent.class)
public abstract class WelcomeActivityModule_BindIntroFragment {
  private WelcomeActivityModule_BindIntroFragment() {}

  @Binds
  @IntoMap
  @ClassKey(IntroFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      IntroFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = IntroFragmentModule.class)
  @PerFragment
  public interface IntroFragmentSubcomponent extends AndroidInjector<IntroFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<IntroFragment> {}
  }
}

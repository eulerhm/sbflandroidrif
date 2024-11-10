package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.welcome.WelcomeActivity;
import org.owntracks.android.ui.welcome.WelcomeActivityModule;

@Module(subcomponents = AndroidBindingModule_BindWelcomeActivity.WelcomeActivitySubcomponent.class)
public abstract class AndroidBindingModule_BindWelcomeActivity {
  private AndroidBindingModule_BindWelcomeActivity() {}

  @Binds
  @IntoMap
  @ClassKey(WelcomeActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      WelcomeActivitySubcomponent.Factory builder);

  @Subcomponent(modules = WelcomeActivityModule.class)
  @PerActivity
  public interface WelcomeActivitySubcomponent extends AndroidInjector<WelcomeActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<WelcomeActivity> {}
  }
}

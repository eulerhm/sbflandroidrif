package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.WelcomeActivity;

@Module(subcomponents = ActivityBuilderModule_BindWelcomeActivity.WelcomeActivitySubcomponent.class)
public abstract class ActivityBuilderModule_BindWelcomeActivity {
  private ActivityBuilderModule_BindWelcomeActivity() {}

  @Binds
  @IntoMap
  @ClassKey(WelcomeActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      WelcomeActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface WelcomeActivitySubcomponent extends AndroidInjector<WelcomeActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<WelcomeActivity> {}
  }
}

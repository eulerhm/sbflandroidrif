package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.auth.SignupActivity;

@Module(subcomponents = ActivityBuilderModule_BindSignupActivity.SignupActivitySubcomponent.class)
public abstract class ActivityBuilderModule_BindSignupActivity {
  private ActivityBuilderModule_BindSignupActivity() {}

  @Binds
  @IntoMap
  @ClassKey(SignupActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SignupActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface SignupActivitySubcomponent extends AndroidInjector<SignupActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SignupActivity> {}
  }
}

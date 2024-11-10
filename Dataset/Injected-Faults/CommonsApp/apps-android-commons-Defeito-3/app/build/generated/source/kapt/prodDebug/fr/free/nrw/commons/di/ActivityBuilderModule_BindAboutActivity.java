package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.AboutActivity;

@Module(subcomponents = ActivityBuilderModule_BindAboutActivity.AboutActivitySubcomponent.class)
public abstract class ActivityBuilderModule_BindAboutActivity {
  private ActivityBuilderModule_BindAboutActivity() {}

  @Binds
  @IntoMap
  @ClassKey(AboutActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      AboutActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface AboutActivitySubcomponent extends AndroidInjector<AboutActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<AboutActivity> {}
  }
}

package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.settings.SettingsActivity;

@Module(
  subcomponents = ActivityBuilderModule_BindSettingsActivity.SettingsActivitySubcomponent.class
)
public abstract class ActivityBuilderModule_BindSettingsActivity {
  private ActivityBuilderModule_BindSettingsActivity() {}

  @Binds
  @IntoMap
  @ClassKey(SettingsActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SettingsActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface SettingsActivitySubcomponent extends AndroidInjector<SettingsActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SettingsActivity> {}
  }
}

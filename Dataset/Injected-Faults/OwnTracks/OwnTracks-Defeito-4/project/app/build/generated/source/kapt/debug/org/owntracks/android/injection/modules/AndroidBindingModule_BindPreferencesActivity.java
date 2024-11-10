package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.preferences.PreferencesActivity;
import org.owntracks.android.ui.preferences.PreferencesActivityModule;

@Module(
  subcomponents = AndroidBindingModule_BindPreferencesActivity.PreferencesActivitySubcomponent.class
)
public abstract class AndroidBindingModule_BindPreferencesActivity {
  private AndroidBindingModule_BindPreferencesActivity() {}

  @Binds
  @IntoMap
  @ClassKey(PreferencesActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      PreferencesActivitySubcomponent.Factory builder);

  @Subcomponent(modules = PreferencesActivityModule.class)
  @PerActivity
  public interface PreferencesActivitySubcomponent extends AndroidInjector<PreferencesActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<PreferencesActivity> {}
  }
}

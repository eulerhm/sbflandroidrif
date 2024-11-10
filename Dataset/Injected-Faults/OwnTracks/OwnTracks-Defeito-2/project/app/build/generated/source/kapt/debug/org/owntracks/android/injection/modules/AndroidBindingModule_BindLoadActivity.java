package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.preferences.load.LoadActivity;
import org.owntracks.android.ui.preferences.load.LoadActivityModule;

@Module(subcomponents = AndroidBindingModule_BindLoadActivity.LoadActivitySubcomponent.class)
public abstract class AndroidBindingModule_BindLoadActivity {
  private AndroidBindingModule_BindLoadActivity() {}

  @Binds
  @IntoMap
  @ClassKey(LoadActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LoadActivitySubcomponent.Factory builder);

  @Subcomponent(modules = LoadActivityModule.class)
  @PerActivity
  public interface LoadActivitySubcomponent extends AndroidInjector<LoadActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LoadActivity> {}
  }
}

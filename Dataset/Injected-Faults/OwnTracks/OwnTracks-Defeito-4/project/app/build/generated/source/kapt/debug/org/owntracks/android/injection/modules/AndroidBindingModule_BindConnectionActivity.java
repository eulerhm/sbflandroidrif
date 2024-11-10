package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.preferences.connection.ConnectionActivity;
import org.owntracks.android.ui.preferences.connection.ConnectionActivityModule;

@Module(
  subcomponents = AndroidBindingModule_BindConnectionActivity.ConnectionActivitySubcomponent.class
)
public abstract class AndroidBindingModule_BindConnectionActivity {
  private AndroidBindingModule_BindConnectionActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ConnectionActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ConnectionActivitySubcomponent.Factory builder);

  @Subcomponent(modules = ConnectionActivityModule.class)
  @PerActivity
  public interface ConnectionActivitySubcomponent extends AndroidInjector<ConnectionActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ConnectionActivity> {}
  }
}

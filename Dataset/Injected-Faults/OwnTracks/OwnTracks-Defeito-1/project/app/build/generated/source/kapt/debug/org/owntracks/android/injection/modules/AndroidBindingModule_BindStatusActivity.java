package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.status.StatusActivity;
import org.owntracks.android.ui.welcome.StatusActivityModule;

@Module(subcomponents = AndroidBindingModule_BindStatusActivity.StatusActivitySubcomponent.class)
public abstract class AndroidBindingModule_BindStatusActivity {
  private AndroidBindingModule_BindStatusActivity() {}

  @Binds
  @IntoMap
  @ClassKey(StatusActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatusActivitySubcomponent.Factory builder);

  @Subcomponent(modules = StatusActivityModule.class)
  @PerActivity
  public interface StatusActivitySubcomponent extends AndroidInjector<StatusActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StatusActivity> {}
  }
}

package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerService;
import org.owntracks.android.services.BackgroundService;
import org.owntracks.android.services.BackgroundServiceModule;

@Module(
  subcomponents = AndroidBindingModule_BindBackgroundService.BackgroundServiceSubcomponent.class
)
public abstract class AndroidBindingModule_BindBackgroundService {
  private AndroidBindingModule_BindBackgroundService() {}

  @Binds
  @IntoMap
  @ClassKey(BackgroundService.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BackgroundServiceSubcomponent.Factory builder);

  @Subcomponent(modules = BackgroundServiceModule.class)
  @PerService
  public interface BackgroundServiceSubcomponent extends AndroidInjector<BackgroundService> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BackgroundService> {}
  }
}

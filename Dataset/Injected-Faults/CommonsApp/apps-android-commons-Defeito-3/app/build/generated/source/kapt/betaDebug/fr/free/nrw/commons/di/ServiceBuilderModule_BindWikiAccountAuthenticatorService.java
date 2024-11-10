package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.auth.WikiAccountAuthenticatorService;

@Module(
  subcomponents =
      ServiceBuilderModule_BindWikiAccountAuthenticatorService
          .WikiAccountAuthenticatorServiceSubcomponent.class
)
public abstract class ServiceBuilderModule_BindWikiAccountAuthenticatorService {
  private ServiceBuilderModule_BindWikiAccountAuthenticatorService() {}

  @Binds
  @IntoMap
  @ClassKey(WikiAccountAuthenticatorService.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      WikiAccountAuthenticatorServiceSubcomponent.Factory builder);

  @Subcomponent
  public interface WikiAccountAuthenticatorServiceSubcomponent
      extends AndroidInjector<WikiAccountAuthenticatorService> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<WikiAccountAuthenticatorService> {}
  }
}

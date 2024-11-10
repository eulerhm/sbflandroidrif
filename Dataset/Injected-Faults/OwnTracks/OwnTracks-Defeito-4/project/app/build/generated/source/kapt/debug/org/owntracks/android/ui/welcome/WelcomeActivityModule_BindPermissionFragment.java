package org.owntracks.android.ui.welcome;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.welcome.permission.PermissionFragment;
import org.owntracks.android.ui.welcome.permission.PermissionFragmentModule;

@Module(
  subcomponents = WelcomeActivityModule_BindPermissionFragment.PermissionFragmentSubcomponent.class
)
public abstract class WelcomeActivityModule_BindPermissionFragment {
  private WelcomeActivityModule_BindPermissionFragment() {}

  @Binds
  @IntoMap
  @ClassKey(PermissionFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      PermissionFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = PermissionFragmentModule.class)
  @PerFragment
  public interface PermissionFragmentSubcomponent extends AndroidInjector<PermissionFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<PermissionFragment> {}
  }
}

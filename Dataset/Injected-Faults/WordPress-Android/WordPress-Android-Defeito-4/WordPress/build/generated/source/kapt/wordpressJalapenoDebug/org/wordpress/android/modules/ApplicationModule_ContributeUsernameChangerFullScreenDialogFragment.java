package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.accounts.signup.UsernameChangerFullScreenDialogFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeUsernameChangerFullScreenDialogFragment
          .UsernameChangerFullScreenDialogFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeUsernameChangerFullScreenDialogFragment {
  private ApplicationModule_ContributeUsernameChangerFullScreenDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(UsernameChangerFullScreenDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UsernameChangerFullScreenDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface UsernameChangerFullScreenDialogFragmentSubcomponent
      extends AndroidInjector<UsernameChangerFullScreenDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UsernameChangerFullScreenDialogFragment> {}
  }
}

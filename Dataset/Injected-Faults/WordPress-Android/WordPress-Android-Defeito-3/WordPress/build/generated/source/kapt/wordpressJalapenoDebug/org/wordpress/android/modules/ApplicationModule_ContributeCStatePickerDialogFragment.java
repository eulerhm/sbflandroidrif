package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.domains.DomainRegistrationDetailsFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeCStatePickerDialogFragment.StatePickerDialogFragmentSubcomponent
          .class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeCStatePickerDialogFragment {
  private ApplicationModule_ContributeCStatePickerDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(DomainRegistrationDetailsFragment.StatePickerDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StatePickerDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StatePickerDialogFragmentSubcomponent
      extends AndroidInjector<DomainRegistrationDetailsFragment.StatePickerDialogFragment> {
    @Subcomponent.Factory
    interface Factory
        extends AndroidInjector.Factory<
            DomainRegistrationDetailsFragment.StatePickerDialogFragment> {}
  }
}

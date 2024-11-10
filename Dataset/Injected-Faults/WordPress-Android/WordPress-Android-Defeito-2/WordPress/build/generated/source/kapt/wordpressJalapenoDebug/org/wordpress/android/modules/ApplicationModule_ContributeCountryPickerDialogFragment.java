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
      ApplicationModule_ContributeCountryPickerDialogFragment
          .CountryPickerDialogFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeCountryPickerDialogFragment {
  private ApplicationModule_ContributeCountryPickerDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(DomainRegistrationDetailsFragment.CountryPickerDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CountryPickerDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface CountryPickerDialogFragmentSubcomponent
      extends AndroidInjector<DomainRegistrationDetailsFragment.CountryPickerDialogFragment> {
    @Subcomponent.Factory
    interface Factory
        extends AndroidInjector.Factory<
            DomainRegistrationDetailsFragment.CountryPickerDialogFragment> {}
  }
}

package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.upload.license.MediaLicenseFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindMediaLicenseFragment.MediaLicenseFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindMediaLicenseFragment {
  private FragmentBuilderModule_BindMediaLicenseFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MediaLicenseFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MediaLicenseFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MediaLicenseFragmentSubcomponent extends AndroidInjector<MediaLicenseFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MediaLicenseFragment> {}
  }
}

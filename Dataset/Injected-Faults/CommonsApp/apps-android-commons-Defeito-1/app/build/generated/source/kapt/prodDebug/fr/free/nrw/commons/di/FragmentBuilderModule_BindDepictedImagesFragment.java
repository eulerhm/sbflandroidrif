package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.depictions.media.DepictedImagesFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindDepictedImagesFragment.DepictedImagesFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindDepictedImagesFragment {
  private FragmentBuilderModule_BindDepictedImagesFragment() {}

  @Binds
  @IntoMap
  @ClassKey(DepictedImagesFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      DepictedImagesFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface DepictedImagesFragmentSubcomponent
      extends AndroidInjector<DepictedImagesFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<DepictedImagesFragment> {}
  }
}

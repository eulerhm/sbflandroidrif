package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.nearby.fragments.NearbyParentFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindNearbyParentFragment.NearbyParentFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindNearbyParentFragment {
  private FragmentBuilderModule_BindNearbyParentFragment() {}

  @Binds
  @IntoMap
  @ClassKey(NearbyParentFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      NearbyParentFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = NearbyParentFragmentModule.class)
  public interface NearbyParentFragmentSubcomponent extends AndroidInjector<NearbyParentFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<NearbyParentFragment> {}
  }
}

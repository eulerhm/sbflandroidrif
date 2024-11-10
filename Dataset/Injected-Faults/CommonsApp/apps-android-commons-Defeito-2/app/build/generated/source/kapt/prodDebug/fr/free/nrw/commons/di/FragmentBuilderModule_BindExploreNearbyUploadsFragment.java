package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.map.ExploreMapFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindExploreNearbyUploadsFragment.ExploreMapFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindExploreNearbyUploadsFragment {
  private FragmentBuilderModule_BindExploreNearbyUploadsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ExploreMapFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ExploreMapFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = ExploreMapFragmentModule.class)
  public interface ExploreMapFragmentSubcomponent extends AndroidInjector<ExploreMapFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ExploreMapFragment> {}
  }
}

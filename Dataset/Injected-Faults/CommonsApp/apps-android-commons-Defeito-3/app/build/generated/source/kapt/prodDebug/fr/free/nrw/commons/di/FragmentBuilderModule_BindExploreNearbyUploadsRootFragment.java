package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.ExploreMapRootFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindExploreNearbyUploadsRootFragment.ExploreMapRootFragmentSubcomponent
          .class
)
public abstract class FragmentBuilderModule_BindExploreNearbyUploadsRootFragment {
  private FragmentBuilderModule_BindExploreNearbyUploadsRootFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ExploreMapRootFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ExploreMapRootFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ExploreMapRootFragmentSubcomponent
      extends AndroidInjector<ExploreMapRootFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ExploreMapRootFragment> {}
  }
}

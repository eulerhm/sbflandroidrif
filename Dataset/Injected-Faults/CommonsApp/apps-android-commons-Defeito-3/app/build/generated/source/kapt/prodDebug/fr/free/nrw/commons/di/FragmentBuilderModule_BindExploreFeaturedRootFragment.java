package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.ExploreListRootFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindExploreFeaturedRootFragment.ExploreListRootFragmentSubcomponent
          .class
)
public abstract class FragmentBuilderModule_BindExploreFeaturedRootFragment {
  private FragmentBuilderModule_BindExploreFeaturedRootFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ExploreListRootFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ExploreListRootFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ExploreListRootFragmentSubcomponent
      extends AndroidInjector<ExploreListRootFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ExploreListRootFragment> {}
  }
}

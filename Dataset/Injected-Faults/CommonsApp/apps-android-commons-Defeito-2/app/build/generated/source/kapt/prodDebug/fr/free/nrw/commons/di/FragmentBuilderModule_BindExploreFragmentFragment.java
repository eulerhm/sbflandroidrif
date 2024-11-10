package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.ExploreFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindExploreFragmentFragment.ExploreFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindExploreFragmentFragment {
  private FragmentBuilderModule_BindExploreFragmentFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ExploreFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ExploreFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ExploreFragmentSubcomponent extends AndroidInjector<ExploreFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ExploreFragment> {}
  }
}

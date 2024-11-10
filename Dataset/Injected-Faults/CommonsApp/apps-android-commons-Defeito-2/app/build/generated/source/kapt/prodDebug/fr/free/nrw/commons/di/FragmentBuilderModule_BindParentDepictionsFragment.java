package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.depictions.parent.ParentDepictionsFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindParentDepictionsFragment.ParentDepictionsFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindParentDepictionsFragment {
  private FragmentBuilderModule_BindParentDepictionsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ParentDepictionsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ParentDepictionsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ParentDepictionsFragmentSubcomponent
      extends AndroidInjector<ParentDepictionsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ParentDepictionsFragment> {}
  }
}

package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.categories.parent.ParentCategoriesFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindParentCategoriesFragment.ParentCategoriesFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindParentCategoriesFragment {
  private FragmentBuilderModule_BindParentCategoriesFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ParentCategoriesFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ParentCategoriesFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ParentCategoriesFragmentSubcomponent
      extends AndroidInjector<ParentCategoriesFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ParentCategoriesFragment> {}
  }
}

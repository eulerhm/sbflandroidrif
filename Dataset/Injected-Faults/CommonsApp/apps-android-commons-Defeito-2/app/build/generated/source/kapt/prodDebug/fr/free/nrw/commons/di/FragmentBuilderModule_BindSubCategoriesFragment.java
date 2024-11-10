package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.categories.sub.SubCategoriesFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindSubCategoriesFragment.SubCategoriesFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindSubCategoriesFragment {
  private FragmentBuilderModule_BindSubCategoriesFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SubCategoriesFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SubCategoriesFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SubCategoriesFragmentSubcomponent
      extends AndroidInjector<SubCategoriesFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SubCategoriesFragment> {}
  }
}

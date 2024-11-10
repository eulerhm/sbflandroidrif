package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.categories.media.CategoriesMediaFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindCategoriesMediaFragment.CategoriesMediaFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindCategoriesMediaFragment {
  private FragmentBuilderModule_BindCategoriesMediaFragment() {}

  @Binds
  @IntoMap
  @ClassKey(CategoriesMediaFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CategoriesMediaFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface CategoriesMediaFragmentSubcomponent
      extends AndroidInjector<CategoriesMediaFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CategoriesMediaFragment> {}
  }
}

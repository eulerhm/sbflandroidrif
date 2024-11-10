package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.categories.search.SearchCategoryFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindSearchCategoryListFragment.SearchCategoryFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindSearchCategoryListFragment {
  private FragmentBuilderModule_BindSearchCategoryListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SearchCategoryFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SearchCategoryFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SearchCategoryFragmentSubcomponent
      extends AndroidInjector<SearchCategoryFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SearchCategoryFragment> {}
  }
}

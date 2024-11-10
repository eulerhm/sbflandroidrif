package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.depictions.search.SearchDepictionsFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindSearchDepictionListFragment.SearchDepictionsFragmentSubcomponent
          .class
)
public abstract class FragmentBuilderModule_BindSearchDepictionListFragment {
  private FragmentBuilderModule_BindSearchDepictionListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SearchDepictionsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SearchDepictionsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SearchDepictionsFragmentSubcomponent
      extends AndroidInjector<SearchDepictionsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SearchDepictionsFragment> {}
  }
}

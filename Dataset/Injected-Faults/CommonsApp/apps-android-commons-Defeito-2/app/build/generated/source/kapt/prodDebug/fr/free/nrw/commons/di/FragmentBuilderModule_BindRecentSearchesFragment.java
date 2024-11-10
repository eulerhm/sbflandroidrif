package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.recentsearches.RecentSearchesFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindRecentSearchesFragment.RecentSearchesFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindRecentSearchesFragment {
  private FragmentBuilderModule_BindRecentSearchesFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RecentSearchesFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RecentSearchesFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RecentSearchesFragmentSubcomponent
      extends AndroidInjector<RecentSearchesFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RecentSearchesFragment> {}
  }
}

package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.media.SearchMediaFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindBrowseImagesListFragment.SearchMediaFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindBrowseImagesListFragment {
  private FragmentBuilderModule_BindBrowseImagesListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SearchMediaFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SearchMediaFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SearchMediaFragmentSubcomponent extends AndroidInjector<SearchMediaFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SearchMediaFragment> {}
  }
}

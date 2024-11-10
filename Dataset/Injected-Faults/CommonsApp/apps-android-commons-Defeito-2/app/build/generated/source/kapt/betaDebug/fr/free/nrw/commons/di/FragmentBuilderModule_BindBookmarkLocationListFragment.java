package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindBookmarkLocationListFragment.BookmarkLocationsFragmentSubcomponent
          .class
)
public abstract class FragmentBuilderModule_BindBookmarkLocationListFragment {
  private FragmentBuilderModule_BindBookmarkLocationListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(BookmarkLocationsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BookmarkLocationsFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = BookmarkLocationsFragmentModule.class)
  public interface BookmarkLocationsFragmentSubcomponent
      extends AndroidInjector<BookmarkLocationsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BookmarkLocationsFragment> {}
  }
}

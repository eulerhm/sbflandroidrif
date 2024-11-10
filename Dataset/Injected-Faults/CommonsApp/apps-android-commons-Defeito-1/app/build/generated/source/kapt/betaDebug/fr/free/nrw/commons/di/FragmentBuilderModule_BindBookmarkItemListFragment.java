package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.bookmarks.items.BookmarkItemsFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindBookmarkItemListFragment.BookmarkItemsFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindBookmarkItemListFragment {
  private FragmentBuilderModule_BindBookmarkItemListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(BookmarkItemsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BookmarkItemsFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = BookmarkItemsFragmentModule.class)
  public interface BookmarkItemsFragmentSubcomponent
      extends AndroidInjector<BookmarkItemsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BookmarkItemsFragment> {}
  }
}

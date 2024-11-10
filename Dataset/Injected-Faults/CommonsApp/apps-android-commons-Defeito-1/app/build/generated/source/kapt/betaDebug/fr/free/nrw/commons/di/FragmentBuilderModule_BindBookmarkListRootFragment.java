package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.bookmarks.BookmarkListRootFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindBookmarkListRootFragment.BookmarkListRootFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindBookmarkListRootFragment {
  private FragmentBuilderModule_BindBookmarkListRootFragment() {}

  @Binds
  @IntoMap
  @ClassKey(BookmarkListRootFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BookmarkListRootFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface BookmarkListRootFragmentSubcomponent
      extends AndroidInjector<BookmarkListRootFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BookmarkListRootFragment> {}
  }
}

package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.bookmarks.BookmarkFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindBookmarkFragmentFragment.BookmarkFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindBookmarkFragmentFragment {
  private FragmentBuilderModule_BindBookmarkFragmentFragment() {}

  @Binds
  @IntoMap
  @ClassKey(BookmarkFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BookmarkFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface BookmarkFragmentSubcomponent extends AndroidInjector<BookmarkFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BookmarkFragment> {}
  }
}

package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.bookmarks.pictures.BookmarkPicturesFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindBookmarkPictureListFragment.BookmarkPicturesFragmentSubcomponent
          .class
)
public abstract class FragmentBuilderModule_BindBookmarkPictureListFragment {
  private FragmentBuilderModule_BindBookmarkPictureListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(BookmarkPicturesFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BookmarkPicturesFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface BookmarkPicturesFragmentSubcomponent
      extends AndroidInjector<BookmarkPicturesFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BookmarkPicturesFragment> {}
  }
}

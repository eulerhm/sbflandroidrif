package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.bookmarks.pictures.BookmarkPicturesContentProvider;

@Module(
  subcomponents =
      ContentProviderBuilderModule_BindBookmarkContentProvider
          .BookmarkPicturesContentProviderSubcomponent.class
)
public abstract class ContentProviderBuilderModule_BindBookmarkContentProvider {
  private ContentProviderBuilderModule_BindBookmarkContentProvider() {}

  @Binds
  @IntoMap
  @ClassKey(BookmarkPicturesContentProvider.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BookmarkPicturesContentProviderSubcomponent.Factory builder);

  @Subcomponent
  public interface BookmarkPicturesContentProviderSubcomponent
      extends AndroidInjector<BookmarkPicturesContentProvider> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BookmarkPicturesContentProvider> {}
  }
}

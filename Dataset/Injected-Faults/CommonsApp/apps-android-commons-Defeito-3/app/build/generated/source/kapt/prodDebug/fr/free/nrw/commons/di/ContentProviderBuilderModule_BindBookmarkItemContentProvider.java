package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.bookmarks.items.BookmarkItemsContentProvider;

@Module(
  subcomponents =
      ContentProviderBuilderModule_BindBookmarkItemContentProvider
          .BookmarkItemsContentProviderSubcomponent.class
)
public abstract class ContentProviderBuilderModule_BindBookmarkItemContentProvider {
  private ContentProviderBuilderModule_BindBookmarkItemContentProvider() {}

  @Binds
  @IntoMap
  @ClassKey(BookmarkItemsContentProvider.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BookmarkItemsContentProviderSubcomponent.Factory builder);

  @Subcomponent
  public interface BookmarkItemsContentProviderSubcomponent
      extends AndroidInjector<BookmarkItemsContentProvider> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BookmarkItemsContentProvider> {}
  }
}

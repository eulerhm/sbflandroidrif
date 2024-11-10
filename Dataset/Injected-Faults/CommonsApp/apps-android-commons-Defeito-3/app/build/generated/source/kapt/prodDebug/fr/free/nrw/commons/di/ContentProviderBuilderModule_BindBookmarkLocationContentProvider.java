package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsContentProvider;

@Module(
  subcomponents =
      ContentProviderBuilderModule_BindBookmarkLocationContentProvider
          .BookmarkLocationsContentProviderSubcomponent.class
)
public abstract class ContentProviderBuilderModule_BindBookmarkLocationContentProvider {
  private ContentProviderBuilderModule_BindBookmarkLocationContentProvider() {}

  @Binds
  @IntoMap
  @ClassKey(BookmarkLocationsContentProvider.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BookmarkLocationsContentProviderSubcomponent.Factory builder);

  @Subcomponent
  public interface BookmarkLocationsContentProviderSubcomponent
      extends AndroidInjector<BookmarkLocationsContentProvider> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BookmarkLocationsContentProvider> {}
  }
}

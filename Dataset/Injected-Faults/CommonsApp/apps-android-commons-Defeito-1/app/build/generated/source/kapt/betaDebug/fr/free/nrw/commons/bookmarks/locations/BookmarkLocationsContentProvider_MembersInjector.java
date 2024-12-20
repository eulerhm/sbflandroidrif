// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.bookmarks.locations;

import dagger.MembersInjector;
import fr.free.nrw.commons.data.DBOpenHelper;
import javax.inject.Provider;

public final class BookmarkLocationsContentProvider_MembersInjector implements MembersInjector<BookmarkLocationsContentProvider> {
  private final Provider<DBOpenHelper> dbOpenHelperProvider;

  public BookmarkLocationsContentProvider_MembersInjector(
      Provider<DBOpenHelper> dbOpenHelperProvider) {
    this.dbOpenHelperProvider = dbOpenHelperProvider;
  }

  public static MembersInjector<BookmarkLocationsContentProvider> create(
      Provider<DBOpenHelper> dbOpenHelperProvider) {
    return new BookmarkLocationsContentProvider_MembersInjector(dbOpenHelperProvider);
  }

  @Override
  public void injectMembers(BookmarkLocationsContentProvider instance) {
    injectDbOpenHelper(instance, dbOpenHelperProvider.get());
  }

  public static void injectDbOpenHelper(BookmarkLocationsContentProvider instance,
      DBOpenHelper dbOpenHelper) {
    instance.dbOpenHelper = dbOpenHelper;
  }
}

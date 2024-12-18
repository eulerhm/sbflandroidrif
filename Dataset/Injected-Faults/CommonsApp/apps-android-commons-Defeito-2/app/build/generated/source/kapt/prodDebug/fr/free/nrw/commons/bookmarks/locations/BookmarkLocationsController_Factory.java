// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.bookmarks.locations;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class BookmarkLocationsController_Factory implements Factory<BookmarkLocationsController> {
  private final Provider<BookmarkLocationsDao> bookmarkLocationDaoProvider;

  public BookmarkLocationsController_Factory(
      Provider<BookmarkLocationsDao> bookmarkLocationDaoProvider) {
    this.bookmarkLocationDaoProvider = bookmarkLocationDaoProvider;
  }

  @Override
  public BookmarkLocationsController get() {
    BookmarkLocationsController instance = new BookmarkLocationsController();
    BookmarkLocationsController_MembersInjector.injectBookmarkLocationDao(instance, bookmarkLocationDaoProvider.get());
    return instance;
  }

  public static BookmarkLocationsController_Factory create(
      Provider<BookmarkLocationsDao> bookmarkLocationDaoProvider) {
    return new BookmarkLocationsController_Factory(bookmarkLocationDaoProvider);
  }

  public static BookmarkLocationsController newInstance() {
    return new BookmarkLocationsController();
  }
}

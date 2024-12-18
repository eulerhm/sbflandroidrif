package fr.free.nrw.commons.bookmarks.pictures;

import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.bookmarks.models.Bookmark;
import fr.free.nrw.commons.media.MediaClient;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class BookmarkPicturesController {

    private final MediaClient mediaClient;

    private final BookmarkPicturesDao bookmarkDao;

    private List<Bookmark> currentBookmarks;

    @Inject
    public BookmarkPicturesController(MediaClient mediaClient, BookmarkPicturesDao bookmarkDao) {
        this.mediaClient = mediaClient;
        this.bookmarkDao = bookmarkDao;
        if (!ListenerUtil.mutListener.listen(4798)) {
            currentBookmarks = new ArrayList<>();
        }
    }

    /**
     * Loads the Media objects from the raw data stored in DB and the API.
     * @return a list of bookmarked Media object
     */
    Single<List<Media>> loadBookmarkedPictures() {
        List<Bookmark> bookmarks = bookmarkDao.getAllBookmarks();
        if (!ListenerUtil.mutListener.listen(4799)) {
            currentBookmarks = bookmarks;
        }
        return Observable.fromIterable(bookmarks).flatMap((Function<Bookmark, ObservableSource<Media>>) this::getMediaFromBookmark).toList();
    }

    private Observable<Media> getMediaFromBookmark(Bookmark bookmark) {
        return mediaClient.getMedia(bookmark.getMediaName()).toObservable().onErrorResumeNext(Observable.empty());
    }

    /**
     * Loads the Media objects from the raw data stored in DB and the API.
     * @return a list of bookmarked Media object
     */
    boolean needRefreshBookmarkedPictures() {
        List<Bookmark> bookmarks = bookmarkDao.getAllBookmarks();
        return bookmarks.size() != currentBookmarks.size();
    }

    /**
     * Cancels the requests to the API and the DB
     */
    void stop() {
    }
}

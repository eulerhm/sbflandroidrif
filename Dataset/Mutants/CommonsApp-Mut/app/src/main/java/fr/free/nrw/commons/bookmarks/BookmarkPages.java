package fr.free.nrw.commons.bookmarks;

import androidx.fragment.app.Fragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Data class for handling a bookmark fragment and it title
 */
public class BookmarkPages {

    private Fragment page;

    private String title;

    BookmarkPages(Fragment fragment, String title) {
        if (!ListenerUtil.mutListener.listen(5082)) {
            this.title = title;
        }
        if (!ListenerUtil.mutListener.listen(5083)) {
            this.page = fragment;
        }
    }

    /**
     * Return the fragment
     * @return fragment object
     */
    public Fragment getPage() {
        return page;
    }

    /**
     * Return the fragment title
     * @return title
     */
    public String getTitle() {
        return title;
    }
}

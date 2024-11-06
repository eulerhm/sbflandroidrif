package fr.free.nrw.commons.bookmarks;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListAdapter;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsFragment;
import fr.free.nrw.commons.bookmarks.pictures.BookmarkPicturesFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BookmarksPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<BookmarkPages> pages;

    /**
     * Default Constructor
     * @param fm
     * @param context
     * @param onlyPictures is true if the fragment requires only BookmarkPictureFragment
     *                     (i.e. when no user is logged in).
     */
    BookmarksPagerAdapter(FragmentManager fm, Context context, boolean onlyPictures) {
        super(fm);
        if (!ListenerUtil.mutListener.listen(5172)) {
            pages = new ArrayList<>();
        }
        Bundle picturesBundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(5173)) {
            picturesBundle.putString("categoryName", context.getString(R.string.title_page_bookmarks_pictures));
        }
        if (!ListenerUtil.mutListener.listen(5174)) {
            picturesBundle.putInt("order", 0);
        }
        if (!ListenerUtil.mutListener.listen(5175)) {
            pages.add(new BookmarkPages(new BookmarkListRootFragment(picturesBundle, this), context.getString(R.string.title_page_bookmarks_pictures)));
        }
        if (!ListenerUtil.mutListener.listen(5181)) {
            if (!onlyPictures) {
                // if onlyPictures is false we also add the location fragment.
                Bundle locationBundle = new Bundle();
                if (!ListenerUtil.mutListener.listen(5176)) {
                    locationBundle.putString("categoryName", context.getString(R.string.title_page_bookmarks_locations));
                }
                if (!ListenerUtil.mutListener.listen(5177)) {
                    locationBundle.putInt("order", 1);
                }
                if (!ListenerUtil.mutListener.listen(5178)) {
                    pages.add(new BookmarkPages(new BookmarkListRootFragment(locationBundle, this), context.getString(R.string.title_page_bookmarks_locations)));
                }
                if (!ListenerUtil.mutListener.listen(5179)) {
                    locationBundle.putInt("orderItem", 2);
                }
                if (!ListenerUtil.mutListener.listen(5180)) {
                    pages.add(new BookmarkPages(new BookmarkListRootFragment(locationBundle, this), context.getString(R.string.title_page_bookmarks_items)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5182)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position).getPage();
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getTitle();
    }

    /**
     * Return the Adapter used to display the picture gridview
     * @return adapter
     */
    public ListAdapter getMediaAdapter() {
        BookmarkPicturesFragment fragment = (BookmarkPicturesFragment) (((BookmarkListRootFragment) pages.get(0).getPage()).listFragment);
        return fragment.getAdapter();
    }

    /**
     * Update the pictures list for the bookmark fragment
     */
    public void requestPictureListUpdate() {
        BookmarkPicturesFragment fragment = (BookmarkPicturesFragment) (((BookmarkListRootFragment) pages.get(0).getPage()).listFragment);
        if (!ListenerUtil.mutListener.listen(5183)) {
            fragment.onResume();
        }
    }
}

package fr.free.nrw.commons.bookmarks;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.bookmarks.items.BookmarkItemsFragment;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsFragment;
import fr.free.nrw.commons.bookmarks.pictures.BookmarkPicturesFragment;
import fr.free.nrw.commons.category.CategoryImagesCallback;
import fr.free.nrw.commons.category.GridViewAdapter;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;
import fr.free.nrw.commons.navtab.NavTab;
import java.util.ArrayList;
import java.util.Iterator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BookmarkListRootFragment extends CommonsDaggerSupportFragment implements FragmentManager.OnBackStackChangedListener, MediaDetailPagerFragment.MediaDetailProvider, AdapterView.OnItemClickListener, CategoryImagesCallback {

    private MediaDetailPagerFragment mediaDetails;

    // private BookmarkPicturesFragment bookmarkPicturesFragment;
    private BookmarkLocationsFragment bookmarkLocationsFragment;

    public Fragment listFragment;

    private BookmarksPagerAdapter bookmarksPagerAdapter;

    @BindView(R.id.explore_container)
    FrameLayout container;

    public BookmarkListRootFragment() {
    }

    public BookmarkListRootFragment(Bundle bundle, BookmarksPagerAdapter bookmarksPagerAdapter) {
        String title = bundle.getString("categoryName");
        int order = bundle.getInt("order");
        final int orderItem = bundle.getInt("orderItem");
        if (!ListenerUtil.mutListener.listen(5115)) {
            if ((ListenerUtil.mutListener.listen(5105) ? (order >= 0) : (ListenerUtil.mutListener.listen(5104) ? (order <= 0) : (ListenerUtil.mutListener.listen(5103) ? (order > 0) : (ListenerUtil.mutListener.listen(5102) ? (order < 0) : (ListenerUtil.mutListener.listen(5101) ? (order != 0) : (order == 0))))))) {
                if (!ListenerUtil.mutListener.listen(5114)) {
                    listFragment = new BookmarkPicturesFragment();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5106)) {
                    listFragment = new BookmarkLocationsFragment();
                }
                if (!ListenerUtil.mutListener.listen(5113)) {
                    if ((ListenerUtil.mutListener.listen(5111) ? (orderItem >= 2) : (ListenerUtil.mutListener.listen(5110) ? (orderItem <= 2) : (ListenerUtil.mutListener.listen(5109) ? (orderItem > 2) : (ListenerUtil.mutListener.listen(5108) ? (orderItem < 2) : (ListenerUtil.mutListener.listen(5107) ? (orderItem != 2) : (orderItem == 2))))))) {
                        if (!ListenerUtil.mutListener.listen(5112)) {
                            listFragment = new BookmarkItemsFragment();
                        }
                    }
                }
            }
        }
        Bundle featuredArguments = new Bundle();
        if (!ListenerUtil.mutListener.listen(5116)) {
            featuredArguments.putString("categoryName", title);
        }
        if (!ListenerUtil.mutListener.listen(5117)) {
            listFragment.setArguments(featuredArguments);
        }
        if (!ListenerUtil.mutListener.listen(5118)) {
            this.bookmarksPagerAdapter = bookmarksPagerAdapter;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5119)) {
            super.onCreate(savedInstanceState);
        }
        View view = inflater.inflate(R.layout.fragment_featured_root, container, false);
        if (!ListenerUtil.mutListener.listen(5120)) {
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5121)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5123)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(5122)) {
                    setFragment(listFragment, mediaDetails);
                }
            }
        }
    }

    public void setFragment(Fragment fragment, Fragment otherFragment) {
        if (!ListenerUtil.mutListener.listen(5135)) {
            if ((ListenerUtil.mutListener.listen(5124) ? (fragment.isAdded() || otherFragment != null) : (fragment.isAdded() && otherFragment != null))) {
                if (!ListenerUtil.mutListener.listen(5133)) {
                    getChildFragmentManager().beginTransaction().hide(otherFragment).show(fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(5134)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if ((ListenerUtil.mutListener.listen(5125) ? (fragment.isAdded() || otherFragment == null) : (fragment.isAdded() && otherFragment == null))) {
                if (!ListenerUtil.mutListener.listen(5131)) {
                    getChildFragmentManager().beginTransaction().show(fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(5132)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if ((ListenerUtil.mutListener.listen(5126) ? (!fragment.isAdded() || otherFragment != null) : (!fragment.isAdded() && otherFragment != null))) {
                if (!ListenerUtil.mutListener.listen(5129)) {
                    getChildFragmentManager().beginTransaction().hide(otherFragment).add(R.id.explore_container, fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(5130)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if (!fragment.isAdded()) {
                if (!ListenerUtil.mutListener.listen(5127)) {
                    getChildFragmentManager().beginTransaction().replace(R.id.explore_container, fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(5128)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(5136)) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(5137)) {
            getChildFragmentManager().executePendingTransactions();
        }
    }

    @Override
    public void onAttach(final Context context) {
        if (!ListenerUtil.mutListener.listen(5138)) {
            super.onAttach(context);
        }
    }

    @Override
    public void onMediaClicked(int position) {
        if (!ListenerUtil.mutListener.listen(5139)) {
            Log.d("deneme8", "on media clicked");
        }
    }

    /**
     * This method is called mediaDetailPagerFragment. It returns the Media Object at that Index
     *
     * @param i It is the index of which media object is to be returned which is same as current
     *          index of viewPager.
     * @return Media Object
     */
    @Override
    public Media getMediaAtPosition(int i) {
        if (bookmarksPagerAdapter.getMediaAdapter() == null) {
            // not yet ready to return data
            return null;
        } else {
            return (Media) bookmarksPagerAdapter.getMediaAdapter().getItem(i);
        }
    }

    /**
     * This method is called on from getCount of MediaDetailPagerFragment The viewpager will contain
     * same number of media items as that of media elements in adapter.
     *
     * @return Total Media count in the adapter
     */
    @Override
    public int getTotalMediaCount() {
        if (!ListenerUtil.mutListener.listen(5140)) {
            if (bookmarksPagerAdapter.getMediaAdapter() == null) {
                return 0;
            }
        }
        return bookmarksPagerAdapter.getMediaAdapter().getCount();
    }

    @Override
    public Integer getContributionStateAt(int position) {
        return null;
    }

    /**
     * Reload media detail fragment once media is nominated
     *
     * @param index item position that has been nominated
     */
    @Override
    public void refreshNominatedMedia(int index) {
        if (!ListenerUtil.mutListener.listen(5147)) {
            if ((ListenerUtil.mutListener.listen(5141) ? (mediaDetails != null || !listFragment.isVisible()) : (mediaDetails != null && !listFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(5142)) {
                    removeFragment(mediaDetails);
                }
                if (!ListenerUtil.mutListener.listen(5143)) {
                    mediaDetails = MediaDetailPagerFragment.newInstance(false, true);
                }
                if (!ListenerUtil.mutListener.listen(5144)) {
                    ((BookmarkFragment) getParentFragment()).setScroll(false);
                }
                if (!ListenerUtil.mutListener.listen(5145)) {
                    setFragment(mediaDetails, listFragment);
                }
                if (!ListenerUtil.mutListener.listen(5146)) {
                    mediaDetails.showImage(index);
                }
            }
        }
    }

    /**
     * This method is called on success of API call for featured images or mobile uploads. The
     * viewpager will notified that number of items have changed.
     */
    @Override
    public void viewPagerNotifyDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(5149)) {
            if (mediaDetails != null) {
                if (!ListenerUtil.mutListener.listen(5148)) {
                    mediaDetails.notifyDataSetChanged();
                }
            }
        }
    }

    public boolean backPressed() {
        if (!ListenerUtil.mutListener.listen(5162)) {
            // check mediaDetailPage fragment is not null then we check mediaDetail.is Visible or not to avoid NullPointerException
            if (mediaDetails != null) {
                if (!ListenerUtil.mutListener.listen(5161)) {
                    if (mediaDetails.isVisible()) {
                        if (!ListenerUtil.mutListener.listen(5152)) {
                            // todo add get list fragment
                            ((BookmarkFragment) getParentFragment()).setupTabLayout();
                        }
                        ArrayList<Integer> removed = mediaDetails.getRemovedItems();
                        if (!ListenerUtil.mutListener.listen(5153)) {
                            removeFragment(mediaDetails);
                        }
                        if (!ListenerUtil.mutListener.listen(5154)) {
                            ((BookmarkFragment) getParentFragment()).setScroll(true);
                        }
                        if (!ListenerUtil.mutListener.listen(5155)) {
                            setFragment(listFragment, mediaDetails);
                        }
                        if (!ListenerUtil.mutListener.listen(5156)) {
                            ((MainActivity) getActivity()).showTabs();
                        }
                        if (!ListenerUtil.mutListener.listen(5160)) {
                            if (listFragment instanceof BookmarkPicturesFragment) {
                                GridViewAdapter adapter = ((GridViewAdapter) ((BookmarkPicturesFragment) listFragment).getAdapter());
                                Iterator i = removed.iterator();
                                if (!ListenerUtil.mutListener.listen(5158)) {
                                    {
                                        long _loopCounter76 = 0;
                                        while (i.hasNext()) {
                                            ListenerUtil.loopListener.listen("_loopCounter76", ++_loopCounter76);
                                            if (!ListenerUtil.mutListener.listen(5157)) {
                                                adapter.remove(adapter.getItem((int) i.next()));
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(5159)) {
                                    mediaDetails.clearRemoved();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5151)) {
                            moveToContributionsFragment();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5150)) {
                    moveToContributionsFragment();
                }
            }
        }
        // notify mediaDetails did not handled the backPressed further actions required.
        return false;
    }

    void moveToContributionsFragment() {
        if (!ListenerUtil.mutListener.listen(5163)) {
            ((MainActivity) getActivity()).setSelectedItemId(NavTab.CONTRIBUTIONS.code());
        }
        if (!ListenerUtil.mutListener.listen(5164)) {
            ((MainActivity) getActivity()).showTabs();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!ListenerUtil.mutListener.listen(5165)) {
            Log.d("deneme8", "on media clicked");
        }
        if (!ListenerUtil.mutListener.listen(5166)) {
            container.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5167)) {
            ((BookmarkFragment) getParentFragment()).tabLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(5168)) {
            mediaDetails = MediaDetailPagerFragment.newInstance(false, true);
        }
        if (!ListenerUtil.mutListener.listen(5169)) {
            ((BookmarkFragment) getParentFragment()).setScroll(false);
        }
        if (!ListenerUtil.mutListener.listen(5170)) {
            setFragment(mediaDetails, listFragment);
        }
        if (!ListenerUtil.mutListener.listen(5171)) {
            mediaDetails.showImage(position);
        }
    }

    @Override
    public void onBackStackChanged() {
    }
}

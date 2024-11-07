package fr.free.nrw.commons.explore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.category.CategoryImagesCallback;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.explore.map.ExploreMapFragment;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;
import fr.free.nrw.commons.navtab.NavTab;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExploreMapRootFragment extends CommonsDaggerSupportFragment implements MediaDetailPagerFragment.MediaDetailProvider, CategoryImagesCallback {

    private MediaDetailPagerFragment mediaDetails;

    private ExploreMapFragment mapFragment;

    @BindView(R.id.explore_container)
    FrameLayout container;

    public ExploreMapRootFragment() {
    }

    @NonNull
    public static ExploreMapRootFragment newInstance() {
        ExploreMapRootFragment fragment = new ExploreMapRootFragment();
        if (!ListenerUtil.mutListener.listen(4563)) {
            fragment.setRetainInstance(true);
        }
        return fragment;
    }

    public ExploreMapRootFragment(Bundle bundle) {
        String title = bundle.getString("categoryName");
        if (!ListenerUtil.mutListener.listen(4564)) {
            mapFragment = new ExploreMapFragment();
        }
        Bundle featuredArguments = new Bundle();
        if (!ListenerUtil.mutListener.listen(4565)) {
            featuredArguments.putString("categoryName", title);
        }
        if (!ListenerUtil.mutListener.listen(4566)) {
            mapFragment.setArguments(featuredArguments);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4567)) {
            super.onCreate(savedInstanceState);
        }
        View view = inflater.inflate(R.layout.fragment_featured_root, container, false);
        if (!ListenerUtil.mutListener.listen(4568)) {
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4569)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4571)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(4570)) {
                    setFragment(mapFragment, mediaDetails);
                }
            }
        }
    }

    public void setFragment(Fragment fragment, Fragment otherFragment) {
        if (!ListenerUtil.mutListener.listen(4583)) {
            if ((ListenerUtil.mutListener.listen(4572) ? (fragment.isAdded() || otherFragment != null) : (fragment.isAdded() && otherFragment != null))) {
                if (!ListenerUtil.mutListener.listen(4581)) {
                    getChildFragmentManager().beginTransaction().hide(otherFragment).show(fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(4582)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if ((ListenerUtil.mutListener.listen(4573) ? (fragment.isAdded() || otherFragment == null) : (fragment.isAdded() && otherFragment == null))) {
                if (!ListenerUtil.mutListener.listen(4579)) {
                    getChildFragmentManager().beginTransaction().show(fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(4580)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if ((ListenerUtil.mutListener.listen(4574) ? (!fragment.isAdded() || otherFragment != null) : (!fragment.isAdded() && otherFragment != null))) {
                if (!ListenerUtil.mutListener.listen(4577)) {
                    getChildFragmentManager().beginTransaction().hide(otherFragment).add(R.id.explore_container, fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(4578)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if (!fragment.isAdded()) {
                if (!ListenerUtil.mutListener.listen(4575)) {
                    getChildFragmentManager().beginTransaction().replace(R.id.explore_container, fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(4576)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(4584)) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(4585)) {
            getChildFragmentManager().executePendingTransactions();
        }
    }

    @Override
    public void onAttach(final Context context) {
        if (!ListenerUtil.mutListener.listen(4586)) {
            super.onAttach(context);
        }
    }

    @Override
    public void onMediaClicked(int position) {
        if (!ListenerUtil.mutListener.listen(4587)) {
            container.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4588)) {
            ((ExploreFragment) getParentFragment()).tabLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4589)) {
            mediaDetails = MediaDetailPagerFragment.newInstance(false, true);
        }
        if (!ListenerUtil.mutListener.listen(4590)) {
            ((ExploreFragment) getParentFragment()).setScroll(false);
        }
        if (!ListenerUtil.mutListener.listen(4591)) {
            setFragment(mediaDetails, mapFragment);
        }
        if (!ListenerUtil.mutListener.listen(4592)) {
            mediaDetails.showImage(position);
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
        if ((ListenerUtil.mutListener.listen(4593) ? (mapFragment != null || mapFragment.mediaList != null) : (mapFragment != null && mapFragment.mediaList != null))) {
            return mapFragment.mediaList.get(i);
        } else {
            return null;
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
        if ((ListenerUtil.mutListener.listen(4594) ? (mapFragment != null || mapFragment.mediaList != null) : (mapFragment != null && mapFragment.mediaList != null))) {
            return mapFragment.mediaList.size();
        } else {
            return 0;
        }
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
        if (!ListenerUtil.mutListener.listen(4598)) {
            if ((ListenerUtil.mutListener.listen(4595) ? (mediaDetails != null || !mapFragment.isVisible()) : (mediaDetails != null && !mapFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(4596)) {
                    removeFragment(mediaDetails);
                }
                if (!ListenerUtil.mutListener.listen(4597)) {
                    onMediaClicked(index);
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
        if (!ListenerUtil.mutListener.listen(4600)) {
            if (mediaDetails != null) {
                if (!ListenerUtil.mutListener.listen(4599)) {
                    mediaDetails.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * Performs back pressed action on the fragment. Return true if the event was handled by the
     * mediaDetails otherwise returns false.
     *
     * @return
     */
    public boolean backPressed() {
        if (!ListenerUtil.mutListener.listen(4607)) {
            if ((ListenerUtil.mutListener.listen(4601) ? (null != mediaDetails || mediaDetails.isVisible()) : (null != mediaDetails && mediaDetails.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(4602)) {
                    ((ExploreFragment) getParentFragment()).tabLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4603)) {
                    removeFragment(mediaDetails);
                }
                if (!ListenerUtil.mutListener.listen(4604)) {
                    ((ExploreFragment) getParentFragment()).setScroll(true);
                }
                if (!ListenerUtil.mutListener.listen(4605)) {
                    setFragment(mapFragment, mediaDetails);
                }
                if (!ListenerUtil.mutListener.listen(4606)) {
                    ((MainActivity) getActivity()).showTabs();
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(4612)) {
            if ((ListenerUtil.mutListener.listen(4608) ? (mapFragment != null || mapFragment.isVisible()) : (mapFragment != null && mapFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(4611)) {
                    if (mapFragment.backButtonClicked()) {
                        // Explore map fragment handled the event no further action required.
                        return true;
                    } else {
                        if (!ListenerUtil.mutListener.listen(4610)) {
                            ((MainActivity) getActivity()).showTabs();
                        }
                        return false;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4609)) {
                    ((MainActivity) getActivity()).setSelectedItemId(NavTab.CONTRIBUTIONS.code());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4613)) {
            ((MainActivity) getActivity()).showTabs();
        }
        return false;
    }
}

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
import fr.free.nrw.commons.explore.categories.media.CategoriesMediaFragment;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;
import fr.free.nrw.commons.navtab.NavTab;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExploreListRootFragment extends CommonsDaggerSupportFragment implements MediaDetailPagerFragment.MediaDetailProvider, CategoryImagesCallback {

    private MediaDetailPagerFragment mediaDetails;

    private CategoriesMediaFragment listFragment;

    @BindView(R.id.explore_container)
    FrameLayout container;

    public ExploreListRootFragment() {
    }

    public ExploreListRootFragment(Bundle bundle) {
        String title = bundle.getString("categoryName");
        if (!ListenerUtil.mutListener.listen(4470)) {
            listFragment = new CategoriesMediaFragment();
        }
        Bundle featuredArguments = new Bundle();
        if (!ListenerUtil.mutListener.listen(4471)) {
            featuredArguments.putString("categoryName", title);
        }
        if (!ListenerUtil.mutListener.listen(4472)) {
            listFragment.setArguments(featuredArguments);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4473)) {
            super.onCreate(savedInstanceState);
        }
        View view = inflater.inflate(R.layout.fragment_featured_root, container, false);
        if (!ListenerUtil.mutListener.listen(4474)) {
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4475)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4477)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(4476)) {
                    setFragment(listFragment, mediaDetails);
                }
            }
        }
    }

    public void setFragment(Fragment fragment, Fragment otherFragment) {
        if (!ListenerUtil.mutListener.listen(4489)) {
            if ((ListenerUtil.mutListener.listen(4478) ? (fragment.isAdded() || otherFragment != null) : (fragment.isAdded() && otherFragment != null))) {
                if (!ListenerUtil.mutListener.listen(4487)) {
                    getChildFragmentManager().beginTransaction().hide(otherFragment).show(fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(4488)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if ((ListenerUtil.mutListener.listen(4479) ? (fragment.isAdded() || otherFragment == null) : (fragment.isAdded() && otherFragment == null))) {
                if (!ListenerUtil.mutListener.listen(4485)) {
                    getChildFragmentManager().beginTransaction().show(fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(4486)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if ((ListenerUtil.mutListener.listen(4480) ? (!fragment.isAdded() || otherFragment != null) : (!fragment.isAdded() && otherFragment != null))) {
                if (!ListenerUtil.mutListener.listen(4483)) {
                    getChildFragmentManager().beginTransaction().hide(otherFragment).add(R.id.explore_container, fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(4484)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if (!fragment.isAdded()) {
                if (!ListenerUtil.mutListener.listen(4481)) {
                    getChildFragmentManager().beginTransaction().replace(R.id.explore_container, fragment).addToBackStack("CONTRIBUTION_LIST_FRAGMENT_TAG").commit();
                }
                if (!ListenerUtil.mutListener.listen(4482)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(4490)) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(4491)) {
            getChildFragmentManager().executePendingTransactions();
        }
    }

    @Override
    public void onAttach(final Context context) {
        if (!ListenerUtil.mutListener.listen(4492)) {
            super.onAttach(context);
        }
    }

    @Override
    public void onMediaClicked(int position) {
        if (!ListenerUtil.mutListener.listen(4493)) {
            container.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4494)) {
            ((ExploreFragment) getParentFragment()).tabLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4495)) {
            mediaDetails = MediaDetailPagerFragment.newInstance(false, true);
        }
        if (!ListenerUtil.mutListener.listen(4496)) {
            ((ExploreFragment) getParentFragment()).setScroll(false);
        }
        if (!ListenerUtil.mutListener.listen(4497)) {
            setFragment(mediaDetails, listFragment);
        }
        if (!ListenerUtil.mutListener.listen(4498)) {
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
        if (listFragment != null) {
            return listFragment.getMediaAtPosition(i);
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
        if (listFragment != null) {
            return listFragment.getTotalMediaCount();
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
        if (!ListenerUtil.mutListener.listen(4502)) {
            if ((ListenerUtil.mutListener.listen(4499) ? (mediaDetails != null || !listFragment.isVisible()) : (mediaDetails != null && !listFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(4500)) {
                    removeFragment(mediaDetails);
                }
                if (!ListenerUtil.mutListener.listen(4501)) {
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
        if (!ListenerUtil.mutListener.listen(4504)) {
            if (mediaDetails != null) {
                if (!ListenerUtil.mutListener.listen(4503)) {
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
        if (!ListenerUtil.mutListener.listen(4512)) {
            if ((ListenerUtil.mutListener.listen(4505) ? (null != mediaDetails || mediaDetails.isVisible()) : (null != mediaDetails && mediaDetails.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(4507)) {
                    ((ExploreFragment) getParentFragment()).tabLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4508)) {
                    removeFragment(mediaDetails);
                }
                if (!ListenerUtil.mutListener.listen(4509)) {
                    ((ExploreFragment) getParentFragment()).setScroll(true);
                }
                if (!ListenerUtil.mutListener.listen(4510)) {
                    setFragment(listFragment, mediaDetails);
                }
                if (!ListenerUtil.mutListener.listen(4511)) {
                    ((MainActivity) getActivity()).showTabs();
                }
                return true;
            } else {
                if (!ListenerUtil.mutListener.listen(4506)) {
                    ((MainActivity) getActivity()).setSelectedItemId(NavTab.CONTRIBUTIONS.code());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4513)) {
            ((MainActivity) getActivity()).showTabs();
        }
        return false;
    }
}

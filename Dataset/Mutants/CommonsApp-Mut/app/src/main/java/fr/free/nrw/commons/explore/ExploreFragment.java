package fr.free.nrw.commons.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.ViewPagerAdapter;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.utils.ActivityUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExploreFragment extends CommonsDaggerSupportFragment {

    private static final String FEATURED_IMAGES_CATEGORY = "Featured_pictures_on_Wikimedia_Commons";

    private static final String MOBILE_UPLOADS_CATEGORY = "Uploaded_with_Mobile/Android";

    private static final String EXPLORE_MAP = "Map";

    private static final String MEDIA_DETAILS_FRAGMENT_TAG = "MediaDetailsFragment";

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ParentViewPager viewPager;

    ViewPagerAdapter viewPagerAdapter;

    private ExploreListRootFragment featuredRootFragment;

    private ExploreListRootFragment mobileRootFragment;

    private ExploreMapRootFragment mapRootFragment;

    @Inject
    @Named("default_preferences")
    public JsonKvStore applicationKvStore;

    public void setScroll(boolean canScroll) {
        if (!ListenerUtil.mutListener.listen(4514)) {
            viewPager.setCanScroll(canScroll);
        }
    }

    @NonNull
    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
        if (!ListenerUtil.mutListener.listen(4515)) {
            fragment.setRetainInstance(true);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4516)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4517)) {
            super.onCreate(savedInstanceState);
        }
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        if (!ListenerUtil.mutListener.listen(4518)) {
            ButterKnife.bind(this, view);
        }
        if (!ListenerUtil.mutListener.listen(4519)) {
            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(4520)) {
            viewPager.setAdapter(viewPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(4521)) {
            viewPager.setId(R.id.viewPager);
        }
        if (!ListenerUtil.mutListener.listen(4522)) {
            tabLayout.setupWithViewPager(viewPager);
        }
        if (!ListenerUtil.mutListener.listen(4531)) {
            viewPager.addOnPageChangeListener(new OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(4530)) {
                        if ((ListenerUtil.mutListener.listen(4527) ? (position >= 2) : (ListenerUtil.mutListener.listen(4526) ? (position <= 2) : (ListenerUtil.mutListener.listen(4525) ? (position > 2) : (ListenerUtil.mutListener.listen(4524) ? (position < 2) : (ListenerUtil.mutListener.listen(4523) ? (position != 2) : (position == 2))))))) {
                            if (!ListenerUtil.mutListener.listen(4529)) {
                                viewPager.setCanScroll(false);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4528)) {
                                viewPager.setCanScroll(true);
                            }
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4532)) {
            setTabs();
        }
        if (!ListenerUtil.mutListener.listen(4533)) {
            setHasOptionsMenu(true);
        }
        return view;
    }

    /**
     * Sets the titles in the tabLayout and fragments in the viewPager
     */
    public void setTabs() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        Bundle featuredArguments = new Bundle();
        if (!ListenerUtil.mutListener.listen(4534)) {
            featuredArguments.putString("categoryName", FEATURED_IMAGES_CATEGORY);
        }
        Bundle mobileArguments = new Bundle();
        if (!ListenerUtil.mutListener.listen(4535)) {
            mobileArguments.putString("categoryName", MOBILE_UPLOADS_CATEGORY);
        }
        Bundle mapArguments = new Bundle();
        if (!ListenerUtil.mutListener.listen(4536)) {
            mapArguments.putString("categoryName", EXPLORE_MAP);
        }
        if (!ListenerUtil.mutListener.listen(4537)) {
            featuredRootFragment = new ExploreListRootFragment(featuredArguments);
        }
        if (!ListenerUtil.mutListener.listen(4538)) {
            mobileRootFragment = new ExploreListRootFragment(mobileArguments);
        }
        if (!ListenerUtil.mutListener.listen(4539)) {
            mapRootFragment = new ExploreMapRootFragment(mapArguments);
        }
        if (!ListenerUtil.mutListener.listen(4540)) {
            fragmentList.add(featuredRootFragment);
        }
        if (!ListenerUtil.mutListener.listen(4541)) {
            titleList.add(getString(R.string.explore_tab_title_featured).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(4542)) {
            fragmentList.add(mobileRootFragment);
        }
        if (!ListenerUtil.mutListener.listen(4543)) {
            titleList.add(getString(R.string.explore_tab_title_mobile).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(4544)) {
            fragmentList.add(mapRootFragment);
        }
        if (!ListenerUtil.mutListener.listen(4545)) {
            titleList.add(getString(R.string.explore_tab_title_map).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(4546)) {
            ((MainActivity) getActivity()).showTabs();
        }
        if (!ListenerUtil.mutListener.listen(4547)) {
            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(4548)) {
            viewPagerAdapter.setTabData(fragmentList, titleList);
        }
        if (!ListenerUtil.mutListener.listen(4549)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public boolean onBackPressed() {
        if (!ListenerUtil.mutListener.listen(4556)) {
            if (tabLayout.getSelectedTabPosition() == 0) {
                if (!ListenerUtil.mutListener.listen(4555)) {
                    if (featuredRootFragment.backPressed()) {
                        if (!ListenerUtil.mutListener.listen(4554)) {
                            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        }
                        return true;
                    }
                }
            } else if (tabLayout.getSelectedTabPosition() == 1) {
                if (!ListenerUtil.mutListener.listen(4553)) {
                    // Mobile root fragment
                    if (mobileRootFragment.backPressed()) {
                        if (!ListenerUtil.mutListener.listen(4552)) {
                            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        }
                        return true;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4551)) {
                    // explore map fragment
                    if (mapRootFragment.backPressed()) {
                        if (!ListenerUtil.mutListener.listen(4550)) {
                            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method inflates the menu in the toolbar
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(4557)) {
            inflater.inflate(R.menu.menu_search, menu);
        }
        if (!ListenerUtil.mutListener.listen(4558)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    /**
     * This method handles the logic on ItemSelect in toolbar menu Currently only 1 choice is
     * available to open search page of the app
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch(item.getItemId()) {
            case R.id.action_search:
                if (!ListenerUtil.mutListener.listen(4559)) {
                    ActivityUtils.startActivityWithFlags(getActivity(), SearchActivity.class);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

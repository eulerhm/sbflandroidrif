package fr.free.nrw.commons.bookmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.explore.ParentViewPager;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.theme.BaseActivity;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.contributions.ContributionController;
import javax.inject.Named;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BookmarkFragment extends CommonsDaggerSupportFragment {

    private FragmentManager supportFragmentManager;

    private BookmarksPagerAdapter adapter;

    @BindView(R.id.viewPagerBookmarks)
    ParentViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @Inject
    ContributionController controller;

    /**
     * To check if the user is loggedIn or not.
     */
    @Inject
    @Named("default_preferences")
    public JsonKvStore applicationKvStore;

    @NonNull
    public static BookmarkFragment newInstance() {
        BookmarkFragment fragment = new BookmarkFragment();
        if (!ListenerUtil.mutListener.listen(5084)) {
            fragment.setRetainInstance(true);
        }
        return fragment;
    }

    public void setScroll(boolean canScroll) {
        if (!ListenerUtil.mutListener.listen(5085)) {
            viewPager.setCanScroll(canScroll);
        }
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5086)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5087)) {
            super.onCreateView(inflater, container, savedInstanceState);
        }
        View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);
        if (!ListenerUtil.mutListener.listen(5088)) {
            ButterKnife.bind(this, view);
        }
        if (!ListenerUtil.mutListener.listen(5089)) {
            // reference to the Fragment from FragmentManager, using findFragmentById()
            supportFragmentManager = getChildFragmentManager();
        }
        if (!ListenerUtil.mutListener.listen(5090)) {
            adapter = new BookmarksPagerAdapter(supportFragmentManager, getContext(), applicationKvStore.getBoolean("login_skipped"));
        }
        if (!ListenerUtil.mutListener.listen(5091)) {
            viewPager.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(5092)) {
            tabLayout.setupWithViewPager(viewPager);
        }
        if (!ListenerUtil.mutListener.listen(5093)) {
            ((MainActivity) getActivity()).showTabs();
        }
        if (!ListenerUtil.mutListener.listen(5094)) {
            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(5095)) {
            setupTabLayout();
        }
        return view;
    }

    /**
     * This method sets up the tab layout. If the adapter has only one element it sets the
     * visibility of tabLayout to gone.
     */
    public void setupTabLayout() {
        if (!ListenerUtil.mutListener.listen(5096)) {
            tabLayout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5098)) {
            if (adapter.getCount() == 1) {
                if (!ListenerUtil.mutListener.listen(5097)) {
                    tabLayout.setVisibility(View.GONE);
                }
            }
        }
    }

    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(5099)) {
            if (((BookmarkListRootFragment) (adapter.getItem(tabLayout.getSelectedTabPosition()))).backPressed()) {
                // The event is handled internally by the adapter , no further action required.
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5100)) {
            // Event is not handled by the adapter ( performed back action ) change action bar.
            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }
}

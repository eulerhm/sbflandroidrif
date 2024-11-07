package fr.free.nrw.commons.explore;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxSearchView;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.ViewPagerAdapter;
import fr.free.nrw.commons.category.CategoryImagesCallback;
import fr.free.nrw.commons.explore.categories.search.SearchCategoryFragment;
import fr.free.nrw.commons.explore.depictions.search.SearchDepictionsFragment;
import fr.free.nrw.commons.explore.media.SearchMediaFragment;
import fr.free.nrw.commons.explore.models.RecentSearch;
import fr.free.nrw.commons.explore.recentsearches.RecentSearchesDao;
import fr.free.nrw.commons.explore.recentsearches.RecentSearchesFragment;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.utils.FragmentUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SearchActivity extends BaseActivity implements MediaDetailPagerFragment.MediaDetailProvider, CategoryImagesCallback {

    @BindView(R.id.toolbar_search)
    Toolbar toolbar;

    @BindView(R.id.searchHistoryContainer)
    FrameLayout searchHistoryContainer;

    @BindView(R.id.mediaContainer)
    FrameLayout mediaContainer;

    @BindView(R.id.searchBox)
    SearchView searchView;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    RecentSearchesDao recentSearchesDao;

    private SearchMediaFragment searchMediaFragment;

    private SearchCategoryFragment searchCategoryFragment;

    private SearchDepictionsFragment searchDepictionsFragment;

    private RecentSearchesFragment recentSearchesFragment;

    private FragmentManager supportFragmentManager;

    private MediaDetailPagerFragment mediaDetails;

    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4614)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4615)) {
            setContentView(R.layout.activity_search);
        }
        if (!ListenerUtil.mutListener.listen(4616)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(4617)) {
            setTitle(getString(R.string.title_activity_search));
        }
        if (!ListenerUtil.mutListener.listen(4618)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(4619)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(4620)) {
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        if (!ListenerUtil.mutListener.listen(4621)) {
            supportFragmentManager = getSupportFragmentManager();
        }
        if (!ListenerUtil.mutListener.listen(4622)) {
            setSearchHistoryFragment();
        }
        if (!ListenerUtil.mutListener.listen(4623)) {
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(4624)) {
            viewPager.setAdapter(viewPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(4625)) {
            // Because we want all the fragments to be alive
            viewPager.setOffscreenPageLimit(2);
        }
        if (!ListenerUtil.mutListener.listen(4626)) {
            tabLayout.setupWithViewPager(viewPager);
        }
        if (!ListenerUtil.mutListener.listen(4627)) {
            setTabs();
        }
        if (!ListenerUtil.mutListener.listen(4628)) {
            searchView.setQueryHint(getString(R.string.search_commons));
        }
        if (!ListenerUtil.mutListener.listen(4629)) {
            searchView.onActionViewExpanded();
        }
        if (!ListenerUtil.mutListener.listen(4630)) {
            searchView.clearFocus();
        }
    }

    /**
     * This method sets the search history fragment.
     * Search history fragment is displayed when query is empty.
     */
    private void setSearchHistoryFragment() {
        if (!ListenerUtil.mutListener.listen(4631)) {
            recentSearchesFragment = new RecentSearchesFragment();
        }
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if (!ListenerUtil.mutListener.listen(4632)) {
            transaction.add(R.id.searchHistoryContainer, recentSearchesFragment).commit();
        }
    }

    /**
     * Sets the titles in the tabLayout and fragments in the viewPager
     */
    public void setTabs() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(4633)) {
            searchMediaFragment = new SearchMediaFragment();
        }
        if (!ListenerUtil.mutListener.listen(4634)) {
            searchDepictionsFragment = new SearchDepictionsFragment();
        }
        if (!ListenerUtil.mutListener.listen(4635)) {
            searchCategoryFragment = new SearchCategoryFragment();
        }
        if (!ListenerUtil.mutListener.listen(4636)) {
            fragmentList.add(searchMediaFragment);
        }
        if (!ListenerUtil.mutListener.listen(4637)) {
            titleList.add(getResources().getString(R.string.search_tab_title_media).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(4638)) {
            fragmentList.add(searchCategoryFragment);
        }
        if (!ListenerUtil.mutListener.listen(4639)) {
            titleList.add(getResources().getString(R.string.search_tab_title_categories).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(4640)) {
            fragmentList.add(searchDepictionsFragment);
        }
        if (!ListenerUtil.mutListener.listen(4641)) {
            titleList.add(getResources().getString(R.string.search_tab_title_depictions).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(4642)) {
            viewPagerAdapter.setTabData(fragmentList, titleList);
        }
        if (!ListenerUtil.mutListener.listen(4643)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(4644)) {
            compositeDisposable.add(RxSearchView.queryTextChanges(searchView).takeUntil(RxView.detaches(searchView)).debounce(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleSearch, Timber::e));
        }
    }

    private void handleSearch(final CharSequence query) {
        if (!ListenerUtil.mutListener.listen(4660)) {
            if (!TextUtils.isEmpty(query)) {
                if (!ListenerUtil.mutListener.listen(4650)) {
                    saveRecentSearch(query.toString());
                }
                if (!ListenerUtil.mutListener.listen(4651)) {
                    viewPager.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4652)) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4653)) {
                    searchHistoryContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4655)) {
                    if (FragmentUtils.isFragmentUIActive(searchDepictionsFragment)) {
                        if (!ListenerUtil.mutListener.listen(4654)) {
                            searchDepictionsFragment.onQueryUpdated(query.toString());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4657)) {
                    if (FragmentUtils.isFragmentUIActive(searchMediaFragment)) {
                        if (!ListenerUtil.mutListener.listen(4656)) {
                            searchMediaFragment.onQueryUpdated(query.toString());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4659)) {
                    if (FragmentUtils.isFragmentUIActive(searchCategoryFragment)) {
                        if (!ListenerUtil.mutListener.listen(4658)) {
                            searchCategoryFragment.onQueryUpdated(query.toString());
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4645)) {
                    // Open RecentSearchesFragment
                    recentSearchesFragment.updateRecentSearches();
                }
                if (!ListenerUtil.mutListener.listen(4646)) {
                    viewPager.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4647)) {
                    tabLayout.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4648)) {
                    setSearchHistoryFragment();
                }
                if (!ListenerUtil.mutListener.listen(4649)) {
                    searchHistoryContainer.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void saveRecentSearch(@NonNull final String query) {
        final RecentSearch recentSearch = recentSearchesDao.find(query);
        if (!ListenerUtil.mutListener.listen(4664)) {
            // Newly searched query...
            if (recentSearch == null) {
                if (!ListenerUtil.mutListener.listen(4663)) {
                    recentSearchesDao.save(new RecentSearch(null, query, new Date()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4661)) {
                    recentSearch.setLastSearched(new Date());
                }
                if (!ListenerUtil.mutListener.listen(4662)) {
                    recentSearchesDao.save(recentSearch);
                }
            }
        }
    }

    /**
     * returns Media Object at position
     * @param i position of Media in the imagesRecyclerView adapter.
     */
    @Override
    public Media getMediaAtPosition(int i) {
        return searchMediaFragment.getMediaAtPosition(i);
    }

    /**
     * returns total number of images present in the imagesRecyclerView adapter.
     */
    @Override
    public int getTotalMediaCount() {
        return searchMediaFragment.getTotalMediaCount();
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
        if (!ListenerUtil.mutListener.listen(4667)) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                if (!ListenerUtil.mutListener.listen(4665)) {
                    onBackPressed();
                }
                if (!ListenerUtil.mutListener.listen(4666)) {
                    onMediaClicked(index);
                }
            }
        }
    }

    /**
     * This method is called on success of API call for image Search.
     * The viewpager will notified that number of items have changed.
     */
    @Override
    public void viewPagerNotifyDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(4669)) {
            if (mediaDetails != null) {
                if (!ListenerUtil.mutListener.listen(4668)) {
                    mediaDetails.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * Open media detail pager fragment on click of image in search results
     * @param index item index that should be opened
     */
    @Override
    public void onMediaClicked(int index) {
        if (!ListenerUtil.mutListener.listen(4670)) {
            ViewUtil.hideKeyboard(this.findViewById(R.id.searchBox));
        }
        if (!ListenerUtil.mutListener.listen(4671)) {
            tabLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4672)) {
            viewPager.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4673)) {
            mediaContainer.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4674)) {
            // to remove searchview when mediaDetails fragment open
            searchView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4679)) {
            if ((ListenerUtil.mutListener.listen(4675) ? (mediaDetails == null && !mediaDetails.isVisible()) : (mediaDetails == null || !mediaDetails.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(4676)) {
                    // set isFeaturedImage true for featured images, to include author field on media detail
                    mediaDetails = MediaDetailPagerFragment.newInstance(false, true);
                }
                if (!ListenerUtil.mutListener.listen(4677)) {
                    supportFragmentManager.beginTransaction().hide(supportFragmentManager.getFragments().get(supportFragmentManager.getBackStackEntryCount())).add(R.id.mediaContainer, mediaDetails).addToBackStack(null).commit();
                }
                if (!ListenerUtil.mutListener.listen(4678)) {
                    // https://stackoverflow.com/questions/11353075/how-can-i-maintain-fragment-state-when-added-to-the-back-stack/19022550#19022550
                    supportFragmentManager.executePendingTransactions();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4680)) {
            mediaDetails.showImage(index);
        }
    }

    /**
     * This method is called on Screen Rotation
     */
    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(4682)) {
            if (supportFragmentManager.getBackStackEntryCount() == 1) {
                if (!ListenerUtil.mutListener.listen(4681)) {
                    // 
                    onBackPressed();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4683)) {
            super.onResume();
        }
    }

    /**
     * This method is called on backPressed of anyFragment in the activity.
     * If condition is called when mediaDetailFragment is opened.
     */
    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(4687)) {
            // fixing:https://github.com/commons-app/apps-android-commons/issues/2296
            if (getSupportFragmentManager().getBackStackEntryCount() == 2) {
                if (!ListenerUtil.mutListener.listen(4684)) {
                    supportFragmentManager.beginTransaction().remove(mediaDetails).commit();
                }
                if (!ListenerUtil.mutListener.listen(4685)) {
                    supportFragmentManager.popBackStack();
                }
                if (!ListenerUtil.mutListener.listen(4686)) {
                    supportFragmentManager.executePendingTransactions();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4693)) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                if (!ListenerUtil.mutListener.listen(4689)) {
                    // set the searchview
                    searchView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4690)) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4691)) {
                    viewPager.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4692)) {
                    mediaContainer.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4688)) {
                    toolbar.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4694)) {
            super.onBackPressed();
        }
    }

    /**
     * This method is called on click of a recent search to update query in SearchView.
     * @param query Recent Search Query
     */
    public void updateText(String query) {
        if (!ListenerUtil.mutListener.listen(4695)) {
            searchView.setQuery(query, true);
        }
        if (!ListenerUtil.mutListener.listen(4696)) {
            // https://stackoverflow.com/questions/6117967/how-to-remove-focus-without-setting-focus-to-another-control/15481511
            viewPager.requestFocus();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4697)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(4698)) {
            // Dispose the disposables when the activity is destroyed
            compositeDisposable.dispose();
        }
    }
}

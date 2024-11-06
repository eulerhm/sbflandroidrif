package fr.free.nrw.commons.explore.depictions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.ViewPagerAdapter;
import fr.free.nrw.commons.bookmarks.items.BookmarkItemsDao;
import fr.free.nrw.commons.category.CategoryImagesCallback;
import fr.free.nrw.commons.explore.depictions.child.ChildDepictionsFragment;
import fr.free.nrw.commons.explore.depictions.media.DepictedImagesFragment;
import fr.free.nrw.commons.explore.depictions.parent.ParentDepictionsFragment;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.upload.structure.depictions.DepictModel;
import fr.free.nrw.commons.upload.structure.depictions.DepictedItem;
import fr.free.nrw.commons.wikidata.WikidataConstants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to show depiction media, parent classes and child classes of depicted items in Explore
 */
public class WikidataItemDetailsActivity extends BaseActivity implements MediaDetailPagerFragment.MediaDetailProvider, CategoryImagesCallback {

    private FragmentManager supportFragmentManager;

    private DepictedImagesFragment depictionImagesListFragment;

    private MediaDetailPagerFragment mediaDetailPagerFragment;

    @Inject
    BookmarkItemsDao bookmarkItemsDao;

    private CompositeDisposable compositeDisposable;

    @Inject
    DepictModel depictModel;

    private String wikidataItemName;

    @BindView(R.id.mediaContainer)
    FrameLayout mediaContainer;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ViewPagerAdapter viewPagerAdapter;

    private DepictedItem wikidataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3850)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3851)) {
            setContentView(R.layout.activity_wikidata_item_details);
        }
        if (!ListenerUtil.mutListener.listen(3852)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(3853)) {
            compositeDisposable = new CompositeDisposable();
        }
        if (!ListenerUtil.mutListener.listen(3854)) {
            supportFragmentManager = getSupportFragmentManager();
        }
        if (!ListenerUtil.mutListener.listen(3855)) {
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(3856)) {
            viewPager.setAdapter(viewPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(3857)) {
            viewPager.setOffscreenPageLimit(2);
        }
        if (!ListenerUtil.mutListener.listen(3858)) {
            tabLayout.setupWithViewPager(viewPager);
        }
        final DepictedItem depictedItem = getIntent().getParcelableExtra(WikidataConstants.BOOKMARKS_ITEMS);
        if (!ListenerUtil.mutListener.listen(3859)) {
            wikidataItem = depictedItem;
        }
        if (!ListenerUtil.mutListener.listen(3860)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(3861)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(3862)) {
            setTabs();
        }
        if (!ListenerUtil.mutListener.listen(3863)) {
            setPageTitle();
        }
    }

    /**
     * Gets the passed wikidataItemName from the intents and displays it as the page title
     */
    private void setPageTitle() {
        if (!ListenerUtil.mutListener.listen(3866)) {
            if ((ListenerUtil.mutListener.listen(3864) ? (getIntent() != null || getIntent().getStringExtra("wikidataItemName") != null) : (getIntent() != null && getIntent().getStringExtra("wikidataItemName") != null))) {
                if (!ListenerUtil.mutListener.listen(3865)) {
                    setTitle(getIntent().getStringExtra("wikidataItemName"));
                }
            }
        }
    }

    /**
     * This method is called on success of API call for featured Images.
     * The viewpager will notified that number of items have changed.
     */
    @Override
    public void viewPagerNotifyDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(3868)) {
            if (mediaDetailPagerFragment != null) {
                if (!ListenerUtil.mutListener.listen(3867)) {
                    mediaDetailPagerFragment.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * This activity contains 3 tabs and a viewpager. This method is used to set the titles of tab,
     * Set the fragments according to the tab selected in the viewPager.
     */
    private void setTabs() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(3869)) {
            depictionImagesListFragment = new DepictedImagesFragment();
        }
        ChildDepictionsFragment childDepictionsFragment = new ChildDepictionsFragment();
        ParentDepictionsFragment parentDepictionsFragment = new ParentDepictionsFragment();
        if (!ListenerUtil.mutListener.listen(3870)) {
            wikidataItemName = getIntent().getStringExtra("wikidataItemName");
        }
        String entityId = getIntent().getStringExtra("entityId");
        if (!ListenerUtil.mutListener.listen(3877)) {
            if ((ListenerUtil.mutListener.listen(3871) ? (getIntent() != null || wikidataItemName != null) : (getIntent() != null && wikidataItemName != null))) {
                Bundle arguments = new Bundle();
                if (!ListenerUtil.mutListener.listen(3872)) {
                    arguments.putString("wikidataItemName", wikidataItemName);
                }
                if (!ListenerUtil.mutListener.listen(3873)) {
                    arguments.putString("entityId", entityId);
                }
                if (!ListenerUtil.mutListener.listen(3874)) {
                    depictionImagesListFragment.setArguments(arguments);
                }
                if (!ListenerUtil.mutListener.listen(3875)) {
                    parentDepictionsFragment.setArguments(arguments);
                }
                if (!ListenerUtil.mutListener.listen(3876)) {
                    childDepictionsFragment.setArguments(arguments);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3878)) {
            fragmentList.add(depictionImagesListFragment);
        }
        if (!ListenerUtil.mutListener.listen(3879)) {
            titleList.add(getResources().getString(R.string.title_for_media));
        }
        if (!ListenerUtil.mutListener.listen(3880)) {
            fragmentList.add(childDepictionsFragment);
        }
        if (!ListenerUtil.mutListener.listen(3881)) {
            titleList.add(getResources().getString(R.string.title_for_child_classes));
        }
        if (!ListenerUtil.mutListener.listen(3882)) {
            fragmentList.add(parentDepictionsFragment);
        }
        if (!ListenerUtil.mutListener.listen(3883)) {
            titleList.add(getResources().getString(R.string.title_for_parent_classes));
        }
        if (!ListenerUtil.mutListener.listen(3884)) {
            viewPagerAdapter.setTabData(fragmentList, titleList);
        }
        if (!ListenerUtil.mutListener.listen(3885)) {
            viewPager.setOffscreenPageLimit(2);
        }
        if (!ListenerUtil.mutListener.listen(3886)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Shows media detail fragment when user clicks on any image in the list
     */
    @Override
    public void onMediaClicked(int position) {
        if (!ListenerUtil.mutListener.listen(3887)) {
            tabLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3888)) {
            viewPager.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3889)) {
            mediaContainer.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(3894)) {
            if ((ListenerUtil.mutListener.listen(3890) ? (mediaDetailPagerFragment == null && !mediaDetailPagerFragment.isVisible()) : (mediaDetailPagerFragment == null || !mediaDetailPagerFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(3891)) {
                    // set isFeaturedImage true for featured images, to include author field on media detail
                    mediaDetailPagerFragment = MediaDetailPagerFragment.newInstance(false, true);
                }
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                if (!ListenerUtil.mutListener.listen(3892)) {
                    supportFragmentManager.beginTransaction().replace(R.id.mediaContainer, mediaDetailPagerFragment).addToBackStack(null).commit();
                }
                if (!ListenerUtil.mutListener.listen(3893)) {
                    supportFragmentManager.executePendingTransactions();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3895)) {
            mediaDetailPagerFragment.showImage(position);
        }
    }

    /**
     * This method is called mediaDetailPagerFragment. It returns the Media Object at that Index
     * @param i It is the index of which media object is to be returned which is same as
     *          current index of viewPager.
     * @return Media Object
     */
    @Override
    public Media getMediaAtPosition(int i) {
        return depictionImagesListFragment.getMediaAtPosition(i);
    }

    /**
     * This method is called on backPressed of anyFragment in the activity.
     * If condition is called when mediaDetailFragment is opened.
     */
    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(3899)) {
            if (supportFragmentManager.getBackStackEntryCount() == 1) {
                if (!ListenerUtil.mutListener.listen(3896)) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3897)) {
                    viewPager.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3898)) {
                    mediaContainer.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3900)) {
            super.onBackPressed();
        }
    }

    /**
     * This method is called on from getCount of MediaDetailPagerFragment
     * The viewpager will contain same number of media items as that of media elements in adapter.
     * @return Total Media count in the adapter
     */
    @Override
    public int getTotalMediaCount() {
        return depictionImagesListFragment.getTotalMediaCount();
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
        if (!ListenerUtil.mutListener.listen(3903)) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                if (!ListenerUtil.mutListener.listen(3901)) {
                    onBackPressed();
                }
                if (!ListenerUtil.mutListener.listen(3902)) {
                    onMediaClicked(index);
                }
            }
        }
    }

    /**
     * Consumers should be simply using this method to use this activity.
     *
     * @param context      A Context of the application package implementing this class.
     * @param depictedItem Name of the depicts for displaying its details
     */
    public static void startYourself(Context context, DepictedItem depictedItem) {
        Intent intent = new Intent(context, WikidataItemDetailsActivity.class);
        if (!ListenerUtil.mutListener.listen(3904)) {
            intent.putExtra("wikidataItemName", depictedItem.getName());
        }
        if (!ListenerUtil.mutListener.listen(3905)) {
            intent.putExtra("entityId", depictedItem.getId());
        }
        if (!ListenerUtil.mutListener.listen(3906)) {
            intent.putExtra(WikidataConstants.BOOKMARKS_ITEMS, depictedItem);
        }
        if (!ListenerUtil.mutListener.listen(3907)) {
            context.startActivity(intent);
        }
    }

    /**
     * This function inflates the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(3908)) {
            menuInflater.inflate(R.menu.menu_wikidata_item, menu);
        }
        if (!ListenerUtil.mutListener.listen(3909)) {
            updateBookmarkState(menu.findItem(R.id.menu_bookmark_current_item));
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This method handles the logic on item select in toolbar menu
     * Currently only 1 choice is available to open Wikidata item details page in browser
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.browser_actions_menu_items:
                String entityId = getIntent().getStringExtra("entityId");
                Uri uri = Uri.parse("https://www.wikidata.org/wiki/" + entityId);
                if (!ListenerUtil.mutListener.listen(3910)) {
                    Utils.handleWebUrl(this, uri);
                }
                return true;
            case R.id.menu_bookmark_current_item:
                if (!ListenerUtil.mutListener.listen(3914)) {
                    if (getIntent().getStringExtra("fragment") != null) {
                        if (!ListenerUtil.mutListener.listen(3913)) {
                            compositeDisposable.add(depictModel.getDepictions(getIntent().getStringExtra("entityId")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(depictedItems -> {
                                final boolean bookmarkExists = bookmarkItemsDao.updateBookmarkItem(depictedItems.get(0));
                                final Snackbar snackbar = bookmarkExists ? Snackbar.make(findViewById(R.id.toolbar_layout), R.string.add_bookmark, Snackbar.LENGTH_LONG) : Snackbar.make(findViewById(R.id.toolbar_layout), R.string.remove_bookmark, Snackbar.LENGTH_LONG);
                                snackbar.show();
                                updateBookmarkState(item);
                            }));
                        }
                    } else {
                        final boolean bookmarkExists = bookmarkItemsDao.updateBookmarkItem(wikidataItem);
                        final Snackbar snackbar = bookmarkExists ? Snackbar.make(findViewById(R.id.toolbar_layout), R.string.add_bookmark, Snackbar.LENGTH_LONG) : Snackbar.make(findViewById(R.id.toolbar_layout), R.string.remove_bookmark, Snackbar.LENGTH_LONG);
                        if (!ListenerUtil.mutListener.listen(3911)) {
                            snackbar.show();
                        }
                        if (!ListenerUtil.mutListener.listen(3912)) {
                            updateBookmarkState(item);
                        }
                    }
                }
                return true;
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(3915)) {
                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateBookmarkState(final MenuItem item) {
        final boolean isBookmarked;
        if (getIntent().getStringExtra("fragment") != null) {
            isBookmarked = bookmarkItemsDao.findBookmarkItem(getIntent().getStringExtra("entityId"));
        } else {
            isBookmarked = bookmarkItemsDao.findBookmarkItem(wikidataItem.getId());
        }
        final int icon = isBookmarked ? R.drawable.menu_ic_round_star_filled_24px : R.drawable.menu_ic_round_star_border_24px;
        if (!ListenerUtil.mutListener.listen(3916)) {
            item.setIcon(icon);
        }
    }
}

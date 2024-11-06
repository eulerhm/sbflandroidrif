package fr.free.nrw.commons.category;

import static fr.free.nrw.commons.category.CategoryClientKt.CATEGORY_PREFIX;
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
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.ViewPagerAdapter;
import fr.free.nrw.commons.explore.categories.media.CategoriesMediaFragment;
import fr.free.nrw.commons.explore.categories.parent.ParentCategoriesFragment;
import fr.free.nrw.commons.explore.categories.sub.SubCategoriesFragment;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;
import fr.free.nrw.commons.theme.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import org.wikipedia.page.PageTitle;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CategoryDetailsActivity extends BaseActivity implements MediaDetailPagerFragment.MediaDetailProvider, CategoryImagesCallback {

    private FragmentManager supportFragmentManager;

    private CategoriesMediaFragment categoriesMediaFragment;

    private MediaDetailPagerFragment mediaDetails;

    private String categoryName;

    @BindView(R.id.mediaContainer)
    FrameLayout mediaContainer;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(325)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(326)) {
            setContentView(R.layout.activity_category_details);
        }
        if (!ListenerUtil.mutListener.listen(327)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(328)) {
            supportFragmentManager = getSupportFragmentManager();
        }
        if (!ListenerUtil.mutListener.listen(329)) {
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(330)) {
            viewPager.setAdapter(viewPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(331)) {
            viewPager.setOffscreenPageLimit(2);
        }
        if (!ListenerUtil.mutListener.listen(332)) {
            tabLayout.setupWithViewPager(viewPager);
        }
        if (!ListenerUtil.mutListener.listen(333)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(334)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(335)) {
            setTabs();
        }
        if (!ListenerUtil.mutListener.listen(336)) {
            setPageTitle();
        }
    }

    /**
     * This activity contains 3 tabs and a viewpager. This method is used to set the titles of tab,
     * Set the fragments according to the tab selected in the viewPager.
     */
    private void setTabs() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(337)) {
            categoriesMediaFragment = new CategoriesMediaFragment();
        }
        SubCategoriesFragment subCategoryListFragment = new SubCategoriesFragment();
        ParentCategoriesFragment parentCategoriesFragment = new ParentCategoriesFragment();
        if (!ListenerUtil.mutListener.listen(338)) {
            categoryName = getIntent().getStringExtra("categoryName");
        }
        if (!ListenerUtil.mutListener.listen(344)) {
            if ((ListenerUtil.mutListener.listen(339) ? (getIntent() != null || categoryName != null) : (getIntent() != null && categoryName != null))) {
                Bundle arguments = new Bundle();
                if (!ListenerUtil.mutListener.listen(340)) {
                    arguments.putString("categoryName", categoryName);
                }
                if (!ListenerUtil.mutListener.listen(341)) {
                    categoriesMediaFragment.setArguments(arguments);
                }
                if (!ListenerUtil.mutListener.listen(342)) {
                    subCategoryListFragment.setArguments(arguments);
                }
                if (!ListenerUtil.mutListener.listen(343)) {
                    parentCategoriesFragment.setArguments(arguments);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(345)) {
            fragmentList.add(categoriesMediaFragment);
        }
        if (!ListenerUtil.mutListener.listen(346)) {
            titleList.add("MEDIA");
        }
        if (!ListenerUtil.mutListener.listen(347)) {
            fragmentList.add(subCategoryListFragment);
        }
        if (!ListenerUtil.mutListener.listen(348)) {
            titleList.add("SUBCATEGORIES");
        }
        if (!ListenerUtil.mutListener.listen(349)) {
            fragmentList.add(parentCategoriesFragment);
        }
        if (!ListenerUtil.mutListener.listen(350)) {
            titleList.add("PARENT CATEGORIES");
        }
        if (!ListenerUtil.mutListener.listen(351)) {
            viewPagerAdapter.setTabData(fragmentList, titleList);
        }
        if (!ListenerUtil.mutListener.listen(352)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Gets the passed categoryName from the intents and displays it as the page title
     */
    private void setPageTitle() {
        if (!ListenerUtil.mutListener.listen(355)) {
            if ((ListenerUtil.mutListener.listen(353) ? (getIntent() != null || getIntent().getStringExtra("categoryName") != null) : (getIntent() != null && getIntent().getStringExtra("categoryName") != null))) {
                if (!ListenerUtil.mutListener.listen(354)) {
                    setTitle(getIntent().getStringExtra("categoryName"));
                }
            }
        }
    }

    /**
     * This method is called onClick of media inside category details (CategoryImageListFragment).
     */
    @Override
    public void onMediaClicked(int position) {
        if (!ListenerUtil.mutListener.listen(356)) {
            tabLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(357)) {
            viewPager.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(358)) {
            mediaContainer.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(363)) {
            if ((ListenerUtil.mutListener.listen(359) ? (mediaDetails == null && !mediaDetails.isVisible()) : (mediaDetails == null || !mediaDetails.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(360)) {
                    // set isFeaturedImage true for featured images, to include author field on media detail
                    mediaDetails = MediaDetailPagerFragment.newInstance(false, true);
                }
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                if (!ListenerUtil.mutListener.listen(361)) {
                    supportFragmentManager.beginTransaction().replace(R.id.mediaContainer, mediaDetails).addToBackStack(null).commit();
                }
                if (!ListenerUtil.mutListener.listen(362)) {
                    supportFragmentManager.executePendingTransactions();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(364)) {
            mediaDetails.showImage(position);
        }
    }

    /**
     * Consumers should be simply using this method to use this activity.
     * @param context  A Context of the application package implementing this class.
     * @param categoryName Name of the category for displaying its details
     */
    public static void startYourself(Context context, String categoryName) {
        Intent intent = new Intent(context, CategoryDetailsActivity.class);
        if (!ListenerUtil.mutListener.listen(365)) {
            intent.putExtra("categoryName", categoryName);
        }
        if (!ListenerUtil.mutListener.listen(366)) {
            context.startActivity(intent);
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
        return categoriesMediaFragment.getMediaAtPosition(i);
    }

    /**
     * This method is called on from getCount of MediaDetailPagerFragment
     * The viewpager will contain same number of media items as that of media elements in adapter.
     * @return Total Media count in the adapter
     */
    @Override
    public int getTotalMediaCount() {
        return categoriesMediaFragment.getTotalMediaCount();
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
        if (!ListenerUtil.mutListener.listen(369)) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                if (!ListenerUtil.mutListener.listen(367)) {
                    onBackPressed();
                }
                if (!ListenerUtil.mutListener.listen(368)) {
                    onMediaClicked(index);
                }
            }
        }
    }

    /**
     * This method inflates the menu in the toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(370)) {
            inflater.inflate(R.menu.fragment_category_detail, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This method handles the logic on ItemSelect in toolbar menu
     * Currently only 1 choice is available to open category details page in browser
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch(item.getItemId()) {
            case R.id.menu_browser_current_category:
                PageTitle title = Utils.getPageTitle(CATEGORY_PREFIX + categoryName);
                if (!ListenerUtil.mutListener.listen(371)) {
                    Utils.handleWebUrl(this, Uri.parse(title.getCanonicalUri()));
                }
                return true;
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(372)) {
                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method is called on backPressed of anyFragment in the activity.
     * If condition is called when mediaDetailFragment is opened.
     */
    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(376)) {
            if (supportFragmentManager.getBackStackEntryCount() == 1) {
                if (!ListenerUtil.mutListener.listen(373)) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(374)) {
                    viewPager.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(375)) {
                    mediaContainer.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(377)) {
            super.onBackPressed();
        }
    }

    /**
     * This method is called on success of API call for Images inside a category.
     * The viewpager will notified that number of items have changed.
     */
    @Override
    public void viewPagerNotifyDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(379)) {
            if (mediaDetails != null) {
                if (!ListenerUtil.mutListener.listen(378)) {
                    mediaDetails.notifyDataSetChanged();
                }
            }
        }
    }
}

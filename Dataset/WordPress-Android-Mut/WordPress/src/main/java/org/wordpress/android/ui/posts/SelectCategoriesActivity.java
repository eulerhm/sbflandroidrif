package org.wordpress.android.ui.posts;

import android.content.Intent;
import android.os.Bundle;
import android.util.LongSparseArray;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.TaxonomyActionBuilder;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.TermModel;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.TaxonomyStore;
import org.wordpress.android.fluxc.store.TaxonomyStore.OnTaxonomyChanged;
import org.wordpress.android.fluxc.store.TaxonomyStore.OnTermUploaded;
import org.wordpress.android.fluxc.store.TaxonomyStore.RemoteTermPayload;
import org.wordpress.android.models.CategoryNode;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.ToastUtils.Duration;
import org.wordpress.android.util.helpers.ListScrollPositionManager;
import org.wordpress.android.util.helpers.SwipeToRefreshHelper;
import org.wordpress.android.util.helpers.SwipeToRefreshHelper.RefreshListener;
import org.wordpress.android.util.widgets.CustomSwipeRefreshLayout;
import java.util.ArrayList;
import java.util.HashSet;
import javax.inject.Inject;
import static org.wordpress.android.ui.posts.EditPostActivity.EXTRA_POST_LOCAL_ID;
import static org.wordpress.android.util.WPSwipeToRefreshHelper.buildSwipeToRefreshHelper;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SelectCategoriesActivity extends LocaleAwareActivity {

    public static final String KEY_SELECTED_CATEGORY_IDS = "KEY_SELECTED_CATEGORY_IDS";

    private ListView mListView;

    private TextView mEmptyView;

    private ListScrollPositionManager mListScrollPositionManager;

    private SwipeToRefreshHelper mSwipeToRefreshHelper;

    private HashSet<Long> mSelectedCategories;

    private ArrayList<CategoryNode> mCategoryLevels;

    private LongSparseArray<Integer> mCategoryRemoteIdsToListPositions = new LongSparseArray<>();

    private SiteModel mSite;

    @Inject
    SiteStore mSiteStore;

    @Inject
    PostStore mPostStore;

    @Inject
    TaxonomyStore mTaxonomyStore;

    @Inject
    Dispatcher mDispatcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13084)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13085)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(13086)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(13089)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(13088)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13087)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13092)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(13090)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(13091)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(13093)) {
            setContentView(R.layout.select_categories);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(13094)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(13097)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(13095)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(13096)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13098)) {
            setTitle(getResources().getString(R.string.select_categories));
        }
        if (!ListenerUtil.mutListener.listen(13099)) {
            mListView = (ListView) findViewById(android.R.id.list);
        }
        if (!ListenerUtil.mutListener.listen(13100)) {
            mListScrollPositionManager = new ListScrollPositionManager(mListView, false);
        }
        if (!ListenerUtil.mutListener.listen(13101)) {
            mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }
        if (!ListenerUtil.mutListener.listen(13102)) {
            mListView.setItemsCanFocus(false);
        }
        if (!ListenerUtil.mutListener.listen(13103)) {
            mEmptyView = (TextView) findViewById(R.id.empty_view);
        }
        if (!ListenerUtil.mutListener.listen(13104)) {
            mListView.setEmptyView(mEmptyView);
        }
        if (!ListenerUtil.mutListener.listen(13105)) {
            mSelectedCategories = new HashSet<>();
        }
        Bundle extras = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(13110)) {
            if (extras != null) {
                if (!ListenerUtil.mutListener.listen(13109)) {
                    if (extras.containsKey(EXTRA_POST_LOCAL_ID)) {
                        int localPostId = extras.getInt(EXTRA_POST_LOCAL_ID);
                        PostModel post = mPostStore.getPostByLocalPostId(localPostId);
                        if (!ListenerUtil.mutListener.listen(13108)) {
                            if (post != null) {
                                if (!ListenerUtil.mutListener.listen(13107)) {
                                    {
                                        long _loopCounter224 = 0;
                                        for (Long categoryId : post.getCategoryIdList()) {
                                            ListenerUtil.loopListener.listen("_loopCounter224", ++_loopCounter224);
                                            if (!ListenerUtil.mutListener.listen(13106)) {
                                                mSelectedCategories.add(categoryId);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13114)) {
            // swipe to refresh setup
            mSwipeToRefreshHelper = buildSwipeToRefreshHelper((CustomSwipeRefreshLayout) findViewById(R.id.ptr_layout), new RefreshListener() {

                @Override
                public void onRefreshStarted() {
                    if (!ListenerUtil.mutListener.listen(13112)) {
                        if (!NetworkUtils.checkConnection(getBaseContext())) {
                            if (!ListenerUtil.mutListener.listen(13111)) {
                                mSwipeToRefreshHelper.setRefreshing(false);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13113)) {
                        refreshCategories();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13115)) {
            populateCategoryList();
        }
        if (!ListenerUtil.mutListener.listen(13120)) {
            if (NetworkUtils.isNetworkAvailable(this)) {
                if (!ListenerUtil.mutListener.listen(13117)) {
                    mEmptyView.setText(R.string.empty_list_default);
                }
                if (!ListenerUtil.mutListener.listen(13119)) {
                    if (isCategoryListEmpty()) {
                        if (!ListenerUtil.mutListener.listen(13118)) {
                            refreshCategories();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13116)) {
                    mEmptyView.setText(R.string.no_network_title);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(13121)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(13122)) {
            super.onDestroy();
        }
    }

    private boolean isCategoryListEmpty() {
        return (ListenerUtil.mutListener.listen(13123) ? (mListView.getAdapter() == null && mListView.getAdapter().isEmpty()) : (mListView.getAdapter() == null || mListView.getAdapter().isEmpty()));
    }

    private void populateCategoryList() {
        CategoryNode categoryTree = CategoryNode.createCategoryTreeFromList(mTaxonomyStore.getCategoriesForSite(mSite));
        if (!ListenerUtil.mutListener.listen(13124)) {
            mCategoryLevels = CategoryNode.getSortedListOfCategoriesFromRoot(categoryTree);
        }
        if (!ListenerUtil.mutListener.listen(13131)) {
            {
                long _loopCounter225 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(13130) ? (i >= mCategoryLevels.size()) : (ListenerUtil.mutListener.listen(13129) ? (i <= mCategoryLevels.size()) : (ListenerUtil.mutListener.listen(13128) ? (i > mCategoryLevels.size()) : (ListenerUtil.mutListener.listen(13127) ? (i != mCategoryLevels.size()) : (ListenerUtil.mutListener.listen(13126) ? (i == mCategoryLevels.size()) : (i < mCategoryLevels.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter225", ++_loopCounter225);
                    if (!ListenerUtil.mutListener.listen(13125)) {
                        mCategoryRemoteIdsToListPositions.put(mCategoryLevels.get(i).getCategoryId(), i);
                    }
                }
            }
        }
        CategoryArrayAdapter categoryAdapter = new CategoryArrayAdapter(this, R.layout.categories_row, mCategoryLevels);
        if (!ListenerUtil.mutListener.listen(13132)) {
            mListView.setAdapter(categoryAdapter);
        }
        if (!ListenerUtil.mutListener.listen(13136)) {
            if (mSelectedCategories != null) {
                if (!ListenerUtil.mutListener.listen(13135)) {
                    {
                        long _loopCounter226 = 0;
                        for (Long selectedCategory : mSelectedCategories) {
                            ListenerUtil.loopListener.listen("_loopCounter226", ++_loopCounter226);
                            if (!ListenerUtil.mutListener.listen(13134)) {
                                if (mCategoryRemoteIdsToListPositions.get(selectedCategory) != null) {
                                    if (!ListenerUtil.mutListener.listen(13133)) {
                                        mListView.setItemChecked(mCategoryRemoteIdsToListPositions.get(selectedCategory), true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13137)) {
            mListScrollPositionManager.restoreScrollOffset();
        }
    }

    private void showAddCategoryFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (!ListenerUtil.mutListener.listen(13139)) {
            if (prev != null) {
                if (!ListenerUtil.mutListener.listen(13138)) {
                    ft.remove(prev);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13140)) {
            ft.addToBackStack(null);
        }
        // Create and show the dialog.
        AddCategoryFragment newFragment = AddCategoryFragment.newInstance(mSite);
        if (!ListenerUtil.mutListener.listen(13141)) {
            newFragment.show(ft, "dialog");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(13142)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(13143)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(13144)) {
            super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(13145)) {
            inflater.inflate(R.menu.categories, menu);
        }
        return true;
    }

    public void categoryAdded(TermModel newCategory) {
        if (!ListenerUtil.mutListener.listen(13147)) {
            if (!NetworkUtils.checkConnection(this)) {
                if (!ListenerUtil.mutListener.listen(13146)) {
                    mEmptyView.setText(R.string.no_network_title);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(13148)) {
            // Save selected categories
            updateSelectedCategoryList();
        }
        if (!ListenerUtil.mutListener.listen(13149)) {
            mListScrollPositionManager.saveScrollOffset();
        }
        if (!ListenerUtil.mutListener.listen(13150)) {
            mSwipeToRefreshHelper.setRefreshing(true);
        }
        RemoteTermPayload payload = new RemoteTermPayload(newCategory, mSite);
        if (!ListenerUtil.mutListener.listen(13151)) {
            mDispatcher.dispatch(TaxonomyActionBuilder.newPushTermAction(payload));
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(13155)) {
            if (itemId == R.id.menu_new_category) {
                if (!ListenerUtil.mutListener.listen(13154)) {
                    if (NetworkUtils.checkConnection(this)) {
                        if (!ListenerUtil.mutListener.listen(13153)) {
                            showAddCategoryFragment();
                        }
                    }
                }
                return true;
            } else if (itemId == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(13152)) {
                    saveAndFinish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshCategories() {
        if (!ListenerUtil.mutListener.listen(13156)) {
            mSwipeToRefreshHelper.setRefreshing(true);
        }
        if (!ListenerUtil.mutListener.listen(13157)) {
            mListScrollPositionManager.saveScrollOffset();
        }
        if (!ListenerUtil.mutListener.listen(13158)) {
            updateSelectedCategoryList();
        }
        if (!ListenerUtil.mutListener.listen(13159)) {
            mDispatcher.dispatch(TaxonomyActionBuilder.newFetchCategoriesAction(mSite));
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(13160)) {
            saveAndFinish();
        }
        if (!ListenerUtil.mutListener.listen(13161)) {
            super.onBackPressed();
        }
    }

    private void updateSelectedCategoryList() {
        SparseBooleanArray selectedItems = mListView.getCheckedItemPositions();
        if (!ListenerUtil.mutListener.listen(13176)) {
            {
                long _loopCounter227 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(13175) ? (i >= selectedItems.size()) : (ListenerUtil.mutListener.listen(13174) ? (i <= selectedItems.size()) : (ListenerUtil.mutListener.listen(13173) ? (i > selectedItems.size()) : (ListenerUtil.mutListener.listen(13172) ? (i != selectedItems.size()) : (ListenerUtil.mutListener.listen(13171) ? (i == selectedItems.size()) : (i < selectedItems.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter227", ++_loopCounter227);
                    if (!ListenerUtil.mutListener.listen(13167)) {
                        if ((ListenerUtil.mutListener.listen(13166) ? (selectedItems.keyAt(i) <= mCategoryLevels.size()) : (ListenerUtil.mutListener.listen(13165) ? (selectedItems.keyAt(i) > mCategoryLevels.size()) : (ListenerUtil.mutListener.listen(13164) ? (selectedItems.keyAt(i) < mCategoryLevels.size()) : (ListenerUtil.mutListener.listen(13163) ? (selectedItems.keyAt(i) != mCategoryLevels.size()) : (ListenerUtil.mutListener.listen(13162) ? (selectedItems.keyAt(i) == mCategoryLevels.size()) : (selectedItems.keyAt(i) >= mCategoryLevels.size()))))))) {
                            continue;
                        }
                    }
                    long categoryRemoteId = mCategoryLevels.get(selectedItems.keyAt(i)).getCategoryId();
                    if (!ListenerUtil.mutListener.listen(13170)) {
                        if (selectedItems.get(selectedItems.keyAt(i))) {
                            if (!ListenerUtil.mutListener.listen(13169)) {
                                mSelectedCategories.add(categoryRemoteId);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13168)) {
                                mSelectedCategories.remove(categoryRemoteId);
                            }
                        }
                    }
                }
            }
        }
    }

    private void saveAndFinish() {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(13177)) {
            updateSelectedCategoryList();
        }
        if (!ListenerUtil.mutListener.listen(13178)) {
            bundle.putSerializable(KEY_SELECTED_CATEGORY_IDS, new ArrayList<>(mSelectedCategories));
        }
        Intent mIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(13179)) {
            mIntent.putExtras(bundle);
        }
        if (!ListenerUtil.mutListener.listen(13180)) {
            setResult(RESULT_OK, mIntent);
        }
        if (!ListenerUtil.mutListener.listen(13181)) {
            finish();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTaxonomyChanged(OnTaxonomyChanged event) {
        if (!ListenerUtil.mutListener.listen(13187)) {
            switch(event.causeOfChange) {
                case FETCH_CATEGORIES:
                    if (!ListenerUtil.mutListener.listen(13182)) {
                        mSwipeToRefreshHelper.setRefreshing(false);
                    }
                    if (!ListenerUtil.mutListener.listen(13186)) {
                        if (event.isError()) {
                            if (!ListenerUtil.mutListener.listen(13185)) {
                                if (!isFinishing()) {
                                    if (!ListenerUtil.mutListener.listen(13184)) {
                                        ToastUtils.showToast(SelectCategoriesActivity.this, R.string.category_refresh_error, Duration.LONG);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13183)) {
                                populateCategoryList();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTermUploaded(OnTermUploaded event) {
        if (!ListenerUtil.mutListener.listen(13188)) {
            mSwipeToRefreshHelper.setRefreshing(false);
        }
        if (!ListenerUtil.mutListener.listen(13195)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(13194)) {
                    if (!isFinishing()) {
                        if (!ListenerUtil.mutListener.listen(13193)) {
                            ToastUtils.showToast(SelectCategoriesActivity.this, R.string.adding_cat_failed, Duration.LONG);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13189)) {
                    mSelectedCategories.add(event.term.getRemoteTermId());
                }
                if (!ListenerUtil.mutListener.listen(13190)) {
                    populateCategoryList();
                }
                if (!ListenerUtil.mutListener.listen(13192)) {
                    if (!isFinishing()) {
                        if (!ListenerUtil.mutListener.listen(13191)) {
                            ToastUtils.showToast(SelectCategoriesActivity.this, R.string.adding_cat_success, Duration.SHORT);
                        }
                    }
                }
            }
        }
    }
}

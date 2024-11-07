package org.wordpress.android.ui.stockmedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.generated.StockMediaActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.StockMediaModel;
import org.wordpress.android.fluxc.store.MediaStore.OnStockMediaUploaded;
import org.wordpress.android.fluxc.store.MediaStore.UploadStockMediaPayload;
import org.wordpress.android.fluxc.store.StockMediaStore;
import org.wordpress.android.fluxc.store.StockMediaStore.FetchStockMediaListPayload;
import org.wordpress.android.fluxc.store.StockMediaStore.OnStockMediaListFetched;
import org.wordpress.android.ui.ActionableEmptyView;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.media.MediaPreviewActivity;
import org.wordpress.android.ui.photopicker.MediaPickerConstants;
import org.wordpress.android.ui.stockmedia.StockMediaRetainedFragment.StockMediaRetainedData;
import org.wordpress.android.util.AccessibilityUtils;
import org.wordpress.android.util.ActivityUtils;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.PhotoPickerUtils;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.util.WPLinkMovementMethod;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StockMediaPickerActivity extends LocaleAwareActivity implements SearchView.OnQueryTextListener {

    private static final int MIN_SEARCH_QUERY_SIZE = 3;

    private static final String TAG_RETAINED_FRAGMENT = "retained_fragment";

    private static final String KEY_SEARCH_QUERY = "search_query";

    private static final String KEY_IS_SHOWING_EMPTY_VIEW = "is_showing_empty_view";

    private static final String KEY_IS_UPLOADING = "is_uploading";

    public static final String KEY_REQUEST_CODE = "request_code";

    public static final String KEY_UPLOADED_MEDIA_IDS = "uploaded_media_ids";

    private SiteModel mSite;

    private StockMediaAdapter mAdapter;

    private StockMediaRetainedFragment mRetainedFragment;

    private ProgressDialog mProgressDialog;

    private RecyclerView mRecycler;

    private ViewGroup mSelectionBar;

    private TextView mTextAdd;

    private TextView mTextPreview;

    private SearchView mSearchView;

    private String mSearchQuery;

    private final Handler mHandler = new Handler();

    private int mThumbWidth;

    private int mThumbHeight;

    private boolean mIsFetching;

    private boolean mIsShowingEmptyView;

    private boolean mIsUploading;

    private boolean mCanLoadMore;

    private int mNextPage;

    private int mRequestCode;

    @SuppressWarnings("unused")
    @Inject
    StockMediaStore mStockMediaStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    ImageManager mImageManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(22743)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(22744)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(22745)) {
            setContentView(R.layout.media_picker_activity);
        }
        if (!ListenerUtil.mutListener.listen(22748)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(22747)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22746)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22751)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(22749)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(22750)) {
                    finish();
                }
                return;
            }
        }
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(22752)) {
            mRetainedFragment = (StockMediaRetainedFragment) fm.findFragmentByTag(TAG_RETAINED_FRAGMENT);
        }
        if (!ListenerUtil.mutListener.listen(22755)) {
            if (mRetainedFragment == null) {
                if (!ListenerUtil.mutListener.listen(22753)) {
                    mRetainedFragment = StockMediaRetainedFragment.newInstance();
                }
                if (!ListenerUtil.mutListener.listen(22754)) {
                    fm.beginTransaction().add(mRetainedFragment, TAG_RETAINED_FRAGMENT).commit();
                }
            }
        }
        int displayWidth = DisplayUtils.getWindowPixelWidth(this);
        if (!ListenerUtil.mutListener.listen(22760)) {
            mThumbWidth = (ListenerUtil.mutListener.listen(22759) ? (displayWidth % getColumnCount()) : (ListenerUtil.mutListener.listen(22758) ? (displayWidth * getColumnCount()) : (ListenerUtil.mutListener.listen(22757) ? (displayWidth - getColumnCount()) : (ListenerUtil.mutListener.listen(22756) ? (displayWidth + getColumnCount()) : (displayWidth / getColumnCount())))));
        }
        if (!ListenerUtil.mutListener.listen(22765)) {
            mThumbHeight = (int) ((ListenerUtil.mutListener.listen(22764) ? (mThumbWidth % 0.75f) : (ListenerUtil.mutListener.listen(22763) ? (mThumbWidth / 0.75f) : (ListenerUtil.mutListener.listen(22762) ? (mThumbWidth - 0.75f) : (ListenerUtil.mutListener.listen(22761) ? (mThumbWidth + 0.75f) : (mThumbWidth * 0.75f))))));
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(22766)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(22769)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(22767)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(22768)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22770)) {
            mRecycler = findViewById(R.id.recycler);
        }
        if (!ListenerUtil.mutListener.listen(22771)) {
            mRecycler.setLayoutManager(new GridLayoutManager(this, getColumnCount()));
        }
        if (!ListenerUtil.mutListener.listen(22772)) {
            mAdapter = new StockMediaAdapter();
        }
        if (!ListenerUtil.mutListener.listen(22773)) {
            mRecycler.setAdapter(mAdapter);
        }
        if (!ListenerUtil.mutListener.listen(22774)) {
            mSelectionBar = findViewById(R.id.container_selection_bar);
        }
        if (!ListenerUtil.mutListener.listen(22775)) {
            mTextAdd = findViewById(R.id.text_add);
        }
        if (!ListenerUtil.mutListener.listen(22776)) {
            mTextPreview = findViewById(R.id.text_preview);
        }
        if (!ListenerUtil.mutListener.listen(22794)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(22792)) {
                    showEmptyView(true);
                }
                if (!ListenerUtil.mutListener.listen(22793)) {
                    mRequestCode = getIntent().getIntExtra(KEY_REQUEST_CODE, 0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22777)) {
                    mSearchQuery = savedInstanceState.getString(KEY_SEARCH_QUERY);
                }
                if (!ListenerUtil.mutListener.listen(22778)) {
                    mIsUploading = savedInstanceState.getBoolean(KEY_IS_UPLOADING);
                }
                if (!ListenerUtil.mutListener.listen(22779)) {
                    mIsShowingEmptyView = savedInstanceState.getBoolean(KEY_IS_SHOWING_EMPTY_VIEW);
                }
                if (!ListenerUtil.mutListener.listen(22780)) {
                    mRequestCode = savedInstanceState.getInt(KEY_REQUEST_CODE);
                }
                if (!ListenerUtil.mutListener.listen(22782)) {
                    if (mIsShowingEmptyView) {
                        if (!ListenerUtil.mutListener.listen(22781)) {
                            showEmptyView(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22784)) {
                    if (mIsUploading) {
                        if (!ListenerUtil.mutListener.listen(22783)) {
                            showUploadProgressDialog(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22791)) {
                    if (!TextUtils.isEmpty(mSearchQuery)) {
                        StockMediaRetainedData data = mRetainedFragment.getData();
                        if (!ListenerUtil.mutListener.listen(22790)) {
                            if (data != null) {
                                if (!ListenerUtil.mutListener.listen(22786)) {
                                    mCanLoadMore = data.canLoadMore();
                                }
                                if (!ListenerUtil.mutListener.listen(22787)) {
                                    mNextPage = data.getNextPage();
                                }
                                if (!ListenerUtil.mutListener.listen(22788)) {
                                    mAdapter.setMediaList(data.getStockMediaList());
                                }
                                if (!ListenerUtil.mutListener.listen(22789)) {
                                    mAdapter.setSelectedItems(data.getSelectedItems());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(22785)) {
                                    submitSearch(mSearchQuery, true);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22805)) {
            mTextAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(22803)) {
                        if ((ListenerUtil.mutListener.listen(22801) ? ((ListenerUtil.mutListener.listen(22795) ? (null != mSite || mSite.hasDiskSpaceQuotaInformation()) : (null != mSite && mSite.hasDiskSpaceQuotaInformation())) || (ListenerUtil.mutListener.listen(22800) ? (mSite.getSpaceAvailable() >= 0) : (ListenerUtil.mutListener.listen(22799) ? (mSite.getSpaceAvailable() > 0) : (ListenerUtil.mutListener.listen(22798) ? (mSite.getSpaceAvailable() < 0) : (ListenerUtil.mutListener.listen(22797) ? (mSite.getSpaceAvailable() != 0) : (ListenerUtil.mutListener.listen(22796) ? (mSite.getSpaceAvailable() == 0) : (mSite.getSpaceAvailable() <= 0))))))) : ((ListenerUtil.mutListener.listen(22795) ? (null != mSite || mSite.hasDiskSpaceQuotaInformation()) : (null != mSite && mSite.hasDiskSpaceQuotaInformation())) && (ListenerUtil.mutListener.listen(22800) ? (mSite.getSpaceAvailable() >= 0) : (ListenerUtil.mutListener.listen(22799) ? (mSite.getSpaceAvailable() > 0) : (ListenerUtil.mutListener.listen(22798) ? (mSite.getSpaceAvailable() < 0) : (ListenerUtil.mutListener.listen(22797) ? (mSite.getSpaceAvailable() != 0) : (ListenerUtil.mutListener.listen(22796) ? (mSite.getSpaceAvailable() == 0) : (mSite.getSpaceAvailable() <= 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(22802)) {
                                ToastUtils.showToast(StockMediaPickerActivity.this, R.string.error_media_quota_exceeded_toast);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22804)) {
                        uploadSelection();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(22810)) {
            if (isMultiSelectEnabled()) {
                if (!ListenerUtil.mutListener.listen(22809)) {
                    mTextPreview.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(22808)) {
                                previewSelection();
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22806)) {
                    mTextAdd.setText(R.string.photo_picker_use_photo);
                }
                if (!ListenerUtil.mutListener.listen(22807)) {
                    mTextPreview.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22811)) {
            configureSearchView();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(22814)) {
            if (mSearchView != null) {
                if (!ListenerUtil.mutListener.listen(22812)) {
                    mSearchView.setOnQueryTextListener(null);
                }
                if (!ListenerUtil.mutListener.listen(22813)) {
                    mSearchView.setOnCloseListener(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22815)) {
            showUploadProgressDialog(false);
        }
        if (!ListenerUtil.mutListener.listen(22816)) {
            super.onDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(22817)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(22818)) {
            outState.putBoolean(KEY_IS_SHOWING_EMPTY_VIEW, mIsShowingEmptyView);
        }
        if (!ListenerUtil.mutListener.listen(22819)) {
            outState.putBoolean(KEY_IS_UPLOADING, mIsUploading);
        }
        if (!ListenerUtil.mutListener.listen(22820)) {
            outState.putInt(KEY_REQUEST_CODE, mRequestCode);
        }
        if (!ListenerUtil.mutListener.listen(22822)) {
            if (mSite != null) {
                if (!ListenerUtil.mutListener.listen(22821)) {
                    outState.putSerializable(WordPress.SITE, mSite);
                }
            }
        }
        String query = mSearchView != null ? mSearchView.getQuery().toString() : null;
        if (!ListenerUtil.mutListener.listen(22823)) {
            outState.putString(KEY_SEARCH_QUERY, query);
        }
        StockMediaRetainedData data = new StockMediaRetainedData(mAdapter.mItems, mAdapter.mSelectedItems, mCanLoadMore, mNextPage);
        if (!ListenerUtil.mutListener.listen(22824)) {
            mRetainedFragment.setData(data);
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(22825)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(22826)) {
            mDispatcher.register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(22827)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(22828)) {
            super.onStop();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(22831)) {
            if ((ListenerUtil.mutListener.listen(22829) ? (isFinishing() || mRetainedFragment != null) : (isFinishing() && mRetainedFragment != null))) {
                if (!ListenerUtil.mutListener.listen(22830)) {
                    getSupportFragmentManager().beginTransaction().remove(mRetainedFragment).commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22832)) {
            super.onPause();
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(22835)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(22833)) {
                    setResult(RESULT_CANCELED);
                }
                if (!ListenerUtil.mutListener.listen(22834)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isMultiSelectEnabled() {
        return mRequestCode == RequestCodes.STOCK_MEDIA_PICKER_MULTI_SELECT;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!ListenerUtil.mutListener.listen(22837)) {
            if (mSearchView != null) {
                if (!ListenerUtil.mutListener.listen(22836)) {
                    mSearchView.clearFocus();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22838)) {
            ActivityUtils.hideKeyboard(this);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (!ListenerUtil.mutListener.listen(22840)) {
            if (!StringUtils.equals(query, mSearchQuery)) {
                if (!ListenerUtil.mutListener.listen(22839)) {
                    submitSearch(query, true);
                }
            }
        }
        return true;
    }

    private void configureSearchView() {
        if (!ListenerUtil.mutListener.listen(22841)) {
            mSearchView = findViewById(R.id.search_view);
        }
        if (!ListenerUtil.mutListener.listen(22842)) {
            // don't allow the SearchView to be closed
            mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {

                @Override
                public boolean onClose() {
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(22843)) {
            mSearchView.setOnQueryTextListener(this);
        }
    }

    private void showEmptyView(boolean show) {
        if (!ListenerUtil.mutListener.listen(22861)) {
            if (!isFinishing()) {
                if (!ListenerUtil.mutListener.listen(22844)) {
                    mIsShowingEmptyView = show;
                }
                ActionableEmptyView actionableEmptyView = findViewById(R.id.actionable_empty_view);
                if (!ListenerUtil.mutListener.listen(22845)) {
                    actionableEmptyView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(22846)) {
                    actionableEmptyView.updateLayoutForSearch(true, 0);
                }
                if (!ListenerUtil.mutListener.listen(22860)) {
                    if (show) {
                        boolean isEmpty = (ListenerUtil.mutListener.listen(22852) ? (mSearchQuery == null && (ListenerUtil.mutListener.listen(22851) ? (mSearchQuery.length() >= MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22850) ? (mSearchQuery.length() <= MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22849) ? (mSearchQuery.length() > MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22848) ? (mSearchQuery.length() != MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22847) ? (mSearchQuery.length() == MIN_SEARCH_QUERY_SIZE) : (mSearchQuery.length() < MIN_SEARCH_QUERY_SIZE))))))) : (mSearchQuery == null || (ListenerUtil.mutListener.listen(22851) ? (mSearchQuery.length() >= MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22850) ? (mSearchQuery.length() <= MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22849) ? (mSearchQuery.length() > MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22848) ? (mSearchQuery.length() != MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22847) ? (mSearchQuery.length() == MIN_SEARCH_QUERY_SIZE) : (mSearchQuery.length() < MIN_SEARCH_QUERY_SIZE))))))));
                        if (!ListenerUtil.mutListener.listen(22859)) {
                            if (isEmpty) {
                                if (!ListenerUtil.mutListener.listen(22855)) {
                                    actionableEmptyView.title.setText(R.string.stock_media_picker_initial_empty_text);
                                }
                                String link = "<a href='https://pexels.com/'>Pexels</a>";
                                Spanned html = Html.fromHtml(getString(R.string.stock_media_picker_initial_empty_subtext, link));
                                if (!ListenerUtil.mutListener.listen(22856)) {
                                    actionableEmptyView.subtitle.setText(html);
                                }
                                if (!ListenerUtil.mutListener.listen(22857)) {
                                    actionableEmptyView.getSubtitle().setMovementMethod(WPLinkMovementMethod.getInstance());
                                }
                                if (!ListenerUtil.mutListener.listen(22858)) {
                                    actionableEmptyView.subtitle.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(22853)) {
                                    actionableEmptyView.title.setText(R.string.media_empty_search_list);
                                }
                                if (!ListenerUtil.mutListener.listen(22854)) {
                                    actionableEmptyView.subtitle.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void showProgress(boolean show) {
        if (!ListenerUtil.mutListener.listen(22863)) {
            if (!isFinishing()) {
                if (!ListenerUtil.mutListener.listen(22862)) {
                    findViewById(R.id.progress).setVisibility(show ? View.VISIBLE : View.GONE);
                }
            }
        }
    }

    private void showUploadProgressDialog(boolean show) {
        if (!ListenerUtil.mutListener.listen(22871)) {
            if (show) {
                if (!ListenerUtil.mutListener.listen(22866)) {
                    mProgressDialog = new ProgressDialog(this);
                }
                if (!ListenerUtil.mutListener.listen(22867)) {
                    mProgressDialog.setCancelable(false);
                }
                if (!ListenerUtil.mutListener.listen(22868)) {
                    mProgressDialog.setIndeterminate(true);
                }
                if (!ListenerUtil.mutListener.listen(22869)) {
                    mProgressDialog.setMessage(getString(R.string.uploading_media));
                }
                if (!ListenerUtil.mutListener.listen(22870)) {
                    mProgressDialog.show();
                }
            } else if ((ListenerUtil.mutListener.listen(22864) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(22865)) {
                    mProgressDialog.dismiss();
                }
            }
        }
    }

    private void submitSearch(@Nullable final String query, boolean delayed) {
        if (!ListenerUtil.mutListener.listen(22872)) {
            mSearchQuery = query;
        }
        if (!ListenerUtil.mutListener.listen(22877)) {
            if (delayed) {
                if (!ListenerUtil.mutListener.listen(22876)) {
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(22875)) {
                                if (StringUtils.equals(query, mSearchQuery)) {
                                    if (!ListenerUtil.mutListener.listen(22874)) {
                                        submitSearch(query, false);
                                    }
                                }
                            }
                        }
                    }, 500);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22873)) {
                    fetchStockMedia(query, 1);
                }
            }
        }
    }

    private void fetchStockMedia(@Nullable String searchQuery, int page) {
        if (!ListenerUtil.mutListener.listen(22885)) {
            // already fetching anything
            if ((ListenerUtil.mutListener.listen(22884) ? (((ListenerUtil.mutListener.listen(22883) ? (mIsFetching || (ListenerUtil.mutListener.listen(22882) ? (page >= 1) : (ListenerUtil.mutListener.listen(22881) ? (page <= 1) : (ListenerUtil.mutListener.listen(22880) ? (page > 1) : (ListenerUtil.mutListener.listen(22879) ? (page < 1) : (ListenerUtil.mutListener.listen(22878) ? (page == 1) : (page != 1))))))) : (mIsFetching && (ListenerUtil.mutListener.listen(22882) ? (page >= 1) : (ListenerUtil.mutListener.listen(22881) ? (page <= 1) : (ListenerUtil.mutListener.listen(22880) ? (page > 1) : (ListenerUtil.mutListener.listen(22879) ? (page < 1) : (ListenerUtil.mutListener.listen(22878) ? (page == 1) : (page != 1))))))))) && !NetworkUtils.checkConnection(this)) : (((ListenerUtil.mutListener.listen(22883) ? (mIsFetching || (ListenerUtil.mutListener.listen(22882) ? (page >= 1) : (ListenerUtil.mutListener.listen(22881) ? (page <= 1) : (ListenerUtil.mutListener.listen(22880) ? (page > 1) : (ListenerUtil.mutListener.listen(22879) ? (page < 1) : (ListenerUtil.mutListener.listen(22878) ? (page == 1) : (page != 1))))))) : (mIsFetching && (ListenerUtil.mutListener.listen(22882) ? (page >= 1) : (ListenerUtil.mutListener.listen(22881) ? (page <= 1) : (ListenerUtil.mutListener.listen(22880) ? (page > 1) : (ListenerUtil.mutListener.listen(22879) ? (page < 1) : (ListenerUtil.mutListener.listen(22878) ? (page == 1) : (page != 1))))))))) || !NetworkUtils.checkConnection(this)))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22886)) {
            mSearchQuery = searchQuery;
        }
        if (!ListenerUtil.mutListener.listen(22897)) {
            if ((ListenerUtil.mutListener.listen(22892) ? (mSearchQuery == null && (ListenerUtil.mutListener.listen(22891) ? (mSearchQuery.length() >= MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22890) ? (mSearchQuery.length() <= MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22889) ? (mSearchQuery.length() > MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22888) ? (mSearchQuery.length() != MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22887) ? (mSearchQuery.length() == MIN_SEARCH_QUERY_SIZE) : (mSearchQuery.length() < MIN_SEARCH_QUERY_SIZE))))))) : (mSearchQuery == null || (ListenerUtil.mutListener.listen(22891) ? (mSearchQuery.length() >= MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22890) ? (mSearchQuery.length() <= MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22889) ? (mSearchQuery.length() > MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22888) ? (mSearchQuery.length() != MIN_SEARCH_QUERY_SIZE) : (ListenerUtil.mutListener.listen(22887) ? (mSearchQuery.length() == MIN_SEARCH_QUERY_SIZE) : (mSearchQuery.length() < MIN_SEARCH_QUERY_SIZE))))))))) {
                if (!ListenerUtil.mutListener.listen(22893)) {
                    mIsFetching = false;
                }
                if (!ListenerUtil.mutListener.listen(22894)) {
                    showProgress(false);
                }
                if (!ListenerUtil.mutListener.listen(22895)) {
                    mAdapter.clear();
                }
                if (!ListenerUtil.mutListener.listen(22896)) {
                    showEmptyView(true);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22904)) {
            if ((ListenerUtil.mutListener.listen(22902) ? (page >= 1) : (ListenerUtil.mutListener.listen(22901) ? (page <= 1) : (ListenerUtil.mutListener.listen(22900) ? (page > 1) : (ListenerUtil.mutListener.listen(22899) ? (page < 1) : (ListenerUtil.mutListener.listen(22898) ? (page != 1) : (page == 1))))))) {
                if (!ListenerUtil.mutListener.listen(22903)) {
                    mAdapter.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22905)) {
            showProgress(true);
        }
        if (!ListenerUtil.mutListener.listen(22906)) {
            mIsFetching = true;
        }
        if (!ListenerUtil.mutListener.listen(22907)) {
            showEmptyView(false);
        }
        if (!ListenerUtil.mutListener.listen(22908)) {
            AppLog.d(AppLog.T.MEDIA, "Fetching stock media page " + page);
        }
        FetchStockMediaListPayload payload = new FetchStockMediaListPayload(searchQuery, page);
        if (!ListenerUtil.mutListener.listen(22909)) {
            mDispatcher.dispatch(StockMediaActionBuilder.newFetchStockMediaAction(payload));
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStockMediaListFetched(OnStockMediaListFetched event) {
        if (!ListenerUtil.mutListener.listen(22911)) {
            // make sure these results are for the same query
            if ((ListenerUtil.mutListener.listen(22910) ? (mSearchQuery == null && !mSearchQuery.equals(event.searchTerm)) : (mSearchQuery == null || !mSearchQuery.equals(event.searchTerm)))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22912)) {
            mIsFetching = false;
        }
        if (!ListenerUtil.mutListener.listen(22913)) {
            showProgress(false);
        }
        if (!ListenerUtil.mutListener.listen(22917)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(22914)) {
                    AppLog.e(AppLog.T.MEDIA, "An error occurred while searching stock media");
                }
                if (!ListenerUtil.mutListener.listen(22915)) {
                    ToastUtils.showToast(this, R.string.media_generic_error);
                }
                if (!ListenerUtil.mutListener.listen(22916)) {
                    mCanLoadMore = false;
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22918)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.STOCK_MEDIA_SEARCHED);
        }
        if (!ListenerUtil.mutListener.listen(22919)) {
            mNextPage = event.nextPage;
        }
        if (!ListenerUtil.mutListener.listen(22920)) {
            mCanLoadMore = event.canLoadMore;
        }
        if (!ListenerUtil.mutListener.listen(22923)) {
            // set the results to the event's mediaList if this is the first page, otherwise add to the existing results
            if (event.nextPage == 2) {
                if (!ListenerUtil.mutListener.listen(22922)) {
                    mAdapter.setMediaList(event.mediaList);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22921)) {
                    mAdapter.addMediaList(event.mediaList);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22925)) {
            showEmptyView((ListenerUtil.mutListener.listen(22924) ? (mAdapter.isEmpty() || !TextUtils.isEmpty(mSearchQuery)) : (mAdapter.isEmpty() && !TextUtils.isEmpty(mSearchQuery))));
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStockMediaUploaded(OnStockMediaUploaded event) {
        if (!ListenerUtil.mutListener.listen(22926)) {
            mIsUploading = false;
        }
        if (!ListenerUtil.mutListener.listen(22927)) {
            showUploadProgressDialog(false);
        }
        if (!ListenerUtil.mutListener.listen(22950)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(22948)) {
                    ToastUtils.showToast(this, R.string.media_upload_error);
                }
                if (!ListenerUtil.mutListener.listen(22949)) {
                    AppLog.e(AppLog.T.MEDIA, "An error occurred while uploading stock media");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22928)) {
                    trackUploadedStockMediaEvent(event.mediaList);
                }
                int count = event.mediaList.size();
                if (!ListenerUtil.mutListener.listen(22935)) {
                    if ((ListenerUtil.mutListener.listen(22933) ? (count >= 0) : (ListenerUtil.mutListener.listen(22932) ? (count <= 0) : (ListenerUtil.mutListener.listen(22931) ? (count > 0) : (ListenerUtil.mutListener.listen(22930) ? (count < 0) : (ListenerUtil.mutListener.listen(22929) ? (count != 0) : (count == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(22934)) {
                            AppLog.w(AppLog.T.MEDIA, "No stock media chosen");
                        }
                        return;
                    }
                }
                Intent intent = new Intent();
                if (!ListenerUtil.mutListener.listen(22945)) {
                    if (isMultiSelectEnabled()) {
                        long[] idArray = new long[count];
                        if (!ListenerUtil.mutListener.listen(22943)) {
                            {
                                long _loopCounter342 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(22942) ? (i >= count) : (ListenerUtil.mutListener.listen(22941) ? (i <= count) : (ListenerUtil.mutListener.listen(22940) ? (i > count) : (ListenerUtil.mutListener.listen(22939) ? (i != count) : (ListenerUtil.mutListener.listen(22938) ? (i == count) : (i < count)))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter342", ++_loopCounter342);
                                    if (!ListenerUtil.mutListener.listen(22937)) {
                                        idArray[i] = event.mediaList.get(i).getMediaId();
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22944)) {
                            intent.putExtra(KEY_UPLOADED_MEDIA_IDS, idArray);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(22936)) {
                            intent.putExtra(MediaPickerConstants.EXTRA_MEDIA_ID, event.mediaList.get(0).getMediaId());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22946)) {
                    setResult(RESULT_OK, intent);
                }
                if (!ListenerUtil.mutListener.listen(22947)) {
                    finish();
                }
            }
        }
    }

    private void trackUploadedStockMediaEvent(@NonNull List<MediaModel> mediaList) {
        if (!ListenerUtil.mutListener.listen(22952)) {
            if (mediaList.size() == 0) {
                if (!ListenerUtil.mutListener.listen(22951)) {
                    AppLog.e(AppLog.T.MEDIA, "Cannot track uploaded stock media event if mediaList is empty");
                }
                return;
            }
        }
        boolean isMultiselect = (ListenerUtil.mutListener.listen(22957) ? (mediaList.size() >= 1) : (ListenerUtil.mutListener.listen(22956) ? (mediaList.size() <= 1) : (ListenerUtil.mutListener.listen(22955) ? (mediaList.size() < 1) : (ListenerUtil.mutListener.listen(22954) ? (mediaList.size() != 1) : (ListenerUtil.mutListener.listen(22953) ? (mediaList.size() == 1) : (mediaList.size() > 1))))));
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(22958)) {
            properties.put("is_part_of_multiselection", isMultiselect);
        }
        if (!ListenerUtil.mutListener.listen(22959)) {
            properties.put("number_of_media_selected", mediaList.size());
        }
        if (!ListenerUtil.mutListener.listen(22960)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.STOCK_MEDIA_UPLOADED, properties);
        }
    }

    private void showSelectionBar(final boolean show) {
        if (!ListenerUtil.mutListener.listen(22965)) {
            if ((ListenerUtil.mutListener.listen(22961) ? (show || mSelectionBar.getVisibility() != View.VISIBLE) : (show && mSelectionBar.getVisibility() != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(22964)) {
                    AniUtils.animateBottomBar(mSelectionBar, true);
                }
            } else if ((ListenerUtil.mutListener.listen(22962) ? (!show || mSelectionBar.getVisibility() == View.VISIBLE) : (!show && mSelectionBar.getVisibility() == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(22963)) {
                    AniUtils.animateBottomBar(mSelectionBar, false);
                }
            } else {
                return;
            }
        }
        // sure the bar doesn't overlap the bottom row when showing
        long msDelay = AniUtils.Duration.SHORT.toMillis(this);
        if (!ListenerUtil.mutListener.listen(22970)) {
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(22969)) {
                        if (!isFinishing()) {
                            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRecycler.getLayoutParams();
                            if (!ListenerUtil.mutListener.listen(22968)) {
                                if (show) {
                                    if (!ListenerUtil.mutListener.listen(22967)) {
                                        params.addRule(RelativeLayout.ABOVE, R.id.container_selection_bar);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(22966)) {
                                        params.addRule(RelativeLayout.ABOVE, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }, msDelay);
        }
    }

    private void notifySelectionCountChanged() {
        int numSelected = mAdapter.getSelectionCount();
        if (!ListenerUtil.mutListener.listen(22988)) {
            if ((ListenerUtil.mutListener.listen(22975) ? (numSelected >= 0) : (ListenerUtil.mutListener.listen(22974) ? (numSelected <= 0) : (ListenerUtil.mutListener.listen(22973) ? (numSelected < 0) : (ListenerUtil.mutListener.listen(22972) ? (numSelected != 0) : (ListenerUtil.mutListener.listen(22971) ? (numSelected == 0) : (numSelected > 0))))))) {
                if (!ListenerUtil.mutListener.listen(22979)) {
                    if (isMultiSelectEnabled()) {
                        String labelAdd = String.format(getString(R.string.add_count), numSelected);
                        if (!ListenerUtil.mutListener.listen(22977)) {
                            mTextAdd.setText(labelAdd);
                        }
                        String labelPreview = String.format(getString(R.string.preview_count), numSelected);
                        if (!ListenerUtil.mutListener.listen(22978)) {
                            mTextPreview.setText(labelPreview);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22980)) {
                    showSelectionBar(true);
                }
                if (!ListenerUtil.mutListener.listen(22987)) {
                    if ((ListenerUtil.mutListener.listen(22985) ? (numSelected >= 1) : (ListenerUtil.mutListener.listen(22984) ? (numSelected <= 1) : (ListenerUtil.mutListener.listen(22983) ? (numSelected > 1) : (ListenerUtil.mutListener.listen(22982) ? (numSelected < 1) : (ListenerUtil.mutListener.listen(22981) ? (numSelected != 1) : (numSelected == 1))))))) {
                        if (!ListenerUtil.mutListener.listen(22986)) {
                            ActivityUtils.hideKeyboardForced(mSearchView);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22976)) {
                    showSelectionBar(false);
                }
            }
        }
    }

    private void previewSelection() {
        List<StockMediaModel> items = mAdapter.getSelectedStockMedia();
        if (!ListenerUtil.mutListener.listen(22989)) {
            if (items.size() == 0)
                return;
        }
        ArrayList<String> imageUrlList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(22991)) {
            {
                long _loopCounter343 = 0;
                for (StockMediaModel media : items) {
                    ListenerUtil.loopListener.listen("_loopCounter343", ++_loopCounter343);
                    if (!ListenerUtil.mutListener.listen(22990)) {
                        imageUrlList.add(media.getUrl());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22992)) {
            MediaPreviewActivity.showPreview(this, null, imageUrlList, imageUrlList.get(0));
        }
    }

    private void uploadSelection() {
        if (!ListenerUtil.mutListener.listen(22993)) {
            if (!NetworkUtils.checkConnection(this))
                return;
        }
        if (!ListenerUtil.mutListener.listen(22994)) {
            mIsUploading = true;
        }
        if (!ListenerUtil.mutListener.listen(22995)) {
            showUploadProgressDialog(true);
        }
        List<StockMediaModel> items = mAdapter.getSelectedStockMedia();
        UploadStockMediaPayload payload = new UploadStockMediaPayload(mSite, items);
        if (!ListenerUtil.mutListener.listen(22996)) {
            mDispatcher.dispatch(MediaActionBuilder.newUploadStockMediaAction(payload));
        }
    }

    class StockMediaAdapter extends RecyclerView.Adapter<StockViewHolder> {

        private static final float SCALE_NORMAL = 1.0f;

        private static final float SCALE_SELECTED = .8f;

        private final List<StockMediaModel> mItems = new ArrayList<>();

        private final ArrayList<Integer> mSelectedItems = new ArrayList<>();

        StockMediaAdapter() {
            if (!ListenerUtil.mutListener.listen(22997)) {
                setHasStableIds(true);
            }
        }

        void setMediaList(@NonNull List<StockMediaModel> mediaList) {
            if (!ListenerUtil.mutListener.listen(22998)) {
                mItems.clear();
            }
            if (!ListenerUtil.mutListener.listen(22999)) {
                mItems.addAll(mediaList);
            }
            if (!ListenerUtil.mutListener.listen(23000)) {
                notifyDataSetChanged();
            }
        }

        void addMediaList(@NonNull List<StockMediaModel> mediaList) {
            if (!ListenerUtil.mutListener.listen(23001)) {
                mItems.addAll(mediaList);
            }
            if (!ListenerUtil.mutListener.listen(23002)) {
                notifyDataSetChanged();
            }
        }

        void clear() {
            if (!ListenerUtil.mutListener.listen(23003)) {
                mItems.clear();
            }
            if (!ListenerUtil.mutListener.listen(23011)) {
                if ((ListenerUtil.mutListener.listen(23008) ? (mSelectedItems.size() >= 0) : (ListenerUtil.mutListener.listen(23007) ? (mSelectedItems.size() <= 0) : (ListenerUtil.mutListener.listen(23006) ? (mSelectedItems.size() < 0) : (ListenerUtil.mutListener.listen(23005) ? (mSelectedItems.size() != 0) : (ListenerUtil.mutListener.listen(23004) ? (mSelectedItems.size() == 0) : (mSelectedItems.size() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(23009)) {
                        mSelectedItems.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(23010)) {
                        notifySelectionCountChanged();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23012)) {
                notifyDataSetChanged();
            }
        }

        @Override
        public long getItemId(int position) {
            return mItems.get(position).getId().hashCode();
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        boolean isEmpty() {
            return (ListenerUtil.mutListener.listen(23017) ? (getItemCount() >= 0) : (ListenerUtil.mutListener.listen(23016) ? (getItemCount() <= 0) : (ListenerUtil.mutListener.listen(23015) ? (getItemCount() > 0) : (ListenerUtil.mutListener.listen(23014) ? (getItemCount() < 0) : (ListenerUtil.mutListener.listen(23013) ? (getItemCount() != 0) : (getItemCount() == 0))))));
        }

        @NonNull
        @Override
        public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.media_picker_thumbnail, parent, false);
            return new StockViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
            StockMediaModel media = mItems.get(position);
            String imageUrl = PhotonUtils.getPhotonImageUrl(media.getThumbnail(), mThumbWidth, mThumbHeight);
            if (!ListenerUtil.mutListener.listen(23018)) {
                mImageManager.load(holder.mImageView, ImageType.PHOTO, imageUrl, ScaleType.CENTER_CROP);
            }
            if (!ListenerUtil.mutListener.listen(23019)) {
                holder.mImageView.setContentDescription(media.getTitle());
            }
            boolean isSelected = isItemSelected(position);
            if (!ListenerUtil.mutListener.listen(23020)) {
                holder.mSelectionCountTextView.setSelected(isSelected);
            }
            if (!ListenerUtil.mutListener.listen(23030)) {
                if (isMultiSelectEnabled()) {
                    if (!ListenerUtil.mutListener.listen(23029)) {
                        if (isSelected) {
                            int count = (ListenerUtil.mutListener.listen(23027) ? (mSelectedItems.indexOf(position) % 1) : (ListenerUtil.mutListener.listen(23026) ? (mSelectedItems.indexOf(position) / 1) : (ListenerUtil.mutListener.listen(23025) ? (mSelectedItems.indexOf(position) * 1) : (ListenerUtil.mutListener.listen(23024) ? (mSelectedItems.indexOf(position) - 1) : (mSelectedItems.indexOf(position) + 1)))));
                            String label = Integer.toString(count);
                            if (!ListenerUtil.mutListener.listen(23028)) {
                                holder.mSelectionCountTextView.setText(label);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23023)) {
                                holder.mSelectionCountTextView.setText(null);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(23022)) {
                        holder.mSelectionCountTextView.setVisibility((ListenerUtil.mutListener.listen(23021) ? (isSelected && isMultiSelectEnabled()) : (isSelected || isMultiSelectEnabled())) ? View.VISIBLE : View.GONE);
                    }
                }
            }
            float scale = isSelected ? SCALE_SELECTED : SCALE_NORMAL;
            if (!ListenerUtil.mutListener.listen(23033)) {
                if (holder.mImageView.getScaleX() != scale) {
                    if (!ListenerUtil.mutListener.listen(23031)) {
                        holder.mImageView.setScaleX(scale);
                    }
                    if (!ListenerUtil.mutListener.listen(23032)) {
                        holder.mImageView.setScaleY(scale);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23045)) {
                if ((ListenerUtil.mutListener.listen(23043) ? (mCanLoadMore || (ListenerUtil.mutListener.listen(23042) ? (position >= (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(23041) ? (position <= (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(23040) ? (position > (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(23039) ? (position < (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(23038) ? (position != (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))) : (mCanLoadMore && (ListenerUtil.mutListener.listen(23042) ? (position >= (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(23041) ? (position <= (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(23040) ? (position > (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(23039) ? (position < (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(23038) ? (position != (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(23037) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(23036) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(23035) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(23034) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))))) {
                    if (!ListenerUtil.mutListener.listen(23044)) {
                        fetchStockMedia(mSearchQuery, mNextPage);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23046)) {
                addImageSelectedToAccessibilityFocusedEvent(holder.mImageView, position);
            }
        }

        private void addImageSelectedToAccessibilityFocusedEvent(ImageView imageView, int position) {
            if (!ListenerUtil.mutListener.listen(23047)) {
                AccessibilityUtils.addPopulateAccessibilityEventFocusedListener(imageView, event -> {
                    if (isValidPosition(position)) {
                        if (isItemSelected(position)) {
                            final String imageSelectedText = imageView.getContext().getString(R.string.photo_picker_image_selected);
                            if (!imageView.getContentDescription().toString().contains(imageSelectedText)) {
                                imageView.setContentDescription(imageView.getContentDescription() + " " + imageSelectedText);
                            }
                        }
                    }
                });
            }
        }

        boolean isValidPosition(int position) {
            return (ListenerUtil.mutListener.listen(23058) ? ((ListenerUtil.mutListener.listen(23052) ? (position <= 0) : (ListenerUtil.mutListener.listen(23051) ? (position > 0) : (ListenerUtil.mutListener.listen(23050) ? (position < 0) : (ListenerUtil.mutListener.listen(23049) ? (position != 0) : (ListenerUtil.mutListener.listen(23048) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(23057) ? (position >= getItemCount()) : (ListenerUtil.mutListener.listen(23056) ? (position <= getItemCount()) : (ListenerUtil.mutListener.listen(23055) ? (position > getItemCount()) : (ListenerUtil.mutListener.listen(23054) ? (position != getItemCount()) : (ListenerUtil.mutListener.listen(23053) ? (position == getItemCount()) : (position < getItemCount()))))))) : ((ListenerUtil.mutListener.listen(23052) ? (position <= 0) : (ListenerUtil.mutListener.listen(23051) ? (position > 0) : (ListenerUtil.mutListener.listen(23050) ? (position < 0) : (ListenerUtil.mutListener.listen(23049) ? (position != 0) : (ListenerUtil.mutListener.listen(23048) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(23057) ? (position >= getItemCount()) : (ListenerUtil.mutListener.listen(23056) ? (position <= getItemCount()) : (ListenerUtil.mutListener.listen(23055) ? (position > getItemCount()) : (ListenerUtil.mutListener.listen(23054) ? (position != getItemCount()) : (ListenerUtil.mutListener.listen(23053) ? (position == getItemCount()) : (position < getItemCount()))))))));
        }

        boolean isItemSelected(int position) {
            return mSelectedItems.contains(position);
        }

        void setItemSelected(StockViewHolder holder, int position, boolean selected) {
            if (!ListenerUtil.mutListener.listen(23059)) {
                if (!isValidPosition(position))
                    return;
            }
            if (!ListenerUtil.mutListener.listen(23065)) {
                // if this is single select, make sure to deselect any existing selection
                if ((ListenerUtil.mutListener.listen(23061) ? ((ListenerUtil.mutListener.listen(23060) ? (selected || !isMultiSelectEnabled()) : (selected && !isMultiSelectEnabled())) || !mSelectedItems.isEmpty()) : ((ListenerUtil.mutListener.listen(23060) ? (selected || !isMultiSelectEnabled()) : (selected && !isMultiSelectEnabled())) && !mSelectedItems.isEmpty()))) {
                    int prevPosition = mSelectedItems.get(0);
                    StockViewHolder prevHolder = (StockViewHolder) mRecycler.findViewHolderForAdapterPosition(prevPosition);
                    if (!ListenerUtil.mutListener.listen(23064)) {
                        if (prevHolder != null) {
                            if (!ListenerUtil.mutListener.listen(23063)) {
                                setItemSelected(prevHolder, prevPosition, false);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23062)) {
                                // holder may be null if not laid out
                                mSelectedItems.clear();
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23074)) {
                if (selected) {
                    if (!ListenerUtil.mutListener.listen(23073)) {
                        mSelectedItems.add(position);
                    }
                } else {
                    int index = mSelectedItems.indexOf(position);
                    if (!ListenerUtil.mutListener.listen(23071)) {
                        if ((ListenerUtil.mutListener.listen(23070) ? (index >= -1) : (ListenerUtil.mutListener.listen(23069) ? (index <= -1) : (ListenerUtil.mutListener.listen(23068) ? (index > -1) : (ListenerUtil.mutListener.listen(23067) ? (index < -1) : (ListenerUtil.mutListener.listen(23066) ? (index != -1) : (index == -1))))))) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23072)) {
                        mSelectedItems.remove(index);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23086)) {
                // show and animate the count bubble
                if (isMultiSelectEnabled()) {
                    if (!ListenerUtil.mutListener.listen(23084)) {
                        if (selected) {
                            String label = Integer.toString((ListenerUtil.mutListener.listen(23082) ? (mSelectedItems.indexOf(position) % 1) : (ListenerUtil.mutListener.listen(23081) ? (mSelectedItems.indexOf(position) / 1) : (ListenerUtil.mutListener.listen(23080) ? (mSelectedItems.indexOf(position) * 1) : (ListenerUtil.mutListener.listen(23079) ? (mSelectedItems.indexOf(position) - 1) : (mSelectedItems.indexOf(position) + 1))))));
                            if (!ListenerUtil.mutListener.listen(23083)) {
                                holder.mSelectionCountTextView.setText(label);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23078)) {
                                holder.mSelectionCountTextView.setText(null);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23085)) {
                        AniUtils.startAnimation(holder.mSelectionCountTextView, R.anim.pop);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(23077)) {
                        if (selected) {
                            if (!ListenerUtil.mutListener.listen(23076)) {
                                AniUtils.scaleIn(holder.mSelectionCountTextView, AniUtils.Duration.MEDIUM);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23075)) {
                                AniUtils.scaleOut(holder.mSelectionCountTextView, AniUtils.Duration.MEDIUM);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23089)) {
                // scale the thumbnail
                if (selected) {
                    if (!ListenerUtil.mutListener.listen(23088)) {
                        AniUtils.scale(holder.mImageView, SCALE_NORMAL, SCALE_SELECTED, AniUtils.Duration.SHORT);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(23087)) {
                        AniUtils.scale(holder.mImageView, SCALE_SELECTED, SCALE_NORMAL, AniUtils.Duration.SHORT);
                    }
                }
            }
            // redraw after the scale animation completes
            long delayMs = AniUtils.Duration.SHORT.toMillis(StockMediaPickerActivity.this);
            if (!ListenerUtil.mutListener.listen(23091)) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(23090)) {
                            notifyDataSetChanged();
                        }
                    }
                }, delayMs);
            }
        }

        private void toggleItemSelected(StockViewHolder holder, int position) {
            if (!ListenerUtil.mutListener.listen(23092)) {
                if (!isValidPosition(position))
                    return;
            }
            boolean isSelected = isItemSelected(position);
            if (!ListenerUtil.mutListener.listen(23093)) {
                setItemSelected(holder, position, !isSelected);
            }
            if (!ListenerUtil.mutListener.listen(23094)) {
                notifySelectionCountChanged();
            }
            if (!ListenerUtil.mutListener.listen(23095)) {
                PhotoPickerUtils.announceSelectedMediaForAccessibility(holder.mImageView, false, !isSelected);
            }
        }

        @SuppressWarnings("unused")
        private List<StockMediaModel> getSelectedStockMedia() {
            List<StockMediaModel> items = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(23097)) {
                {
                    long _loopCounter344 = 0;
                    for (int i : mSelectedItems) {
                        ListenerUtil.loopListener.listen("_loopCounter344", ++_loopCounter344);
                        if (!ListenerUtil.mutListener.listen(23096)) {
                            items.add(mItems.get(i));
                        }
                    }
                }
            }
            return items;
        }

        private void setSelectedItems(@NonNull List<Integer> selectedItems) {
            if (!ListenerUtil.mutListener.listen(23099)) {
                if ((ListenerUtil.mutListener.listen(23098) ? (mSelectedItems.isEmpty() || selectedItems.isEmpty()) : (mSelectedItems.isEmpty() && selectedItems.isEmpty()))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(23100)) {
                mSelectedItems.clear();
            }
            if (!ListenerUtil.mutListener.listen(23101)) {
                mSelectedItems.addAll(selectedItems);
            }
            if (!ListenerUtil.mutListener.listen(23102)) {
                notifyDataSetChanged();
            }
            if (!ListenerUtil.mutListener.listen(23103)) {
                notifySelectionCountChanged();
            }
        }

        int getSelectionCount() {
            return mSelectedItems.size();
        }
    }

    class StockViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        private final TextView mSelectionCountTextView;

        StockViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.image_thumbnail);
            mSelectionCountTextView = view.findViewById(R.id.text_selection_count);
            if (!ListenerUtil.mutListener.listen(23104)) {
                mImageView.getLayoutParams().width = mThumbWidth;
            }
            if (!ListenerUtil.mutListener.listen(23105)) {
                mImageView.getLayoutParams().height = mThumbHeight;
            }
            if (!ListenerUtil.mutListener.listen(23108)) {
                mImageView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (!ListenerUtil.mutListener.listen(23107)) {
                            if (mAdapter.isValidPosition(position)) {
                                if (!ListenerUtil.mutListener.listen(23106)) {
                                    mAdapter.toggleItemSelected(StockViewHolder.this, position);
                                }
                            }
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(23111)) {
                mImageView.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        int position = getAdapterPosition();
                        if (!ListenerUtil.mutListener.listen(23110)) {
                            if (mAdapter.isValidPosition(position)) {
                                if (!ListenerUtil.mutListener.listen(23109)) {
                                    MediaPreviewActivity.showPreview(v.getContext(), mSite, mAdapter.mItems.get(position).getUrl());
                                }
                            }
                        }
                        return true;
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(23112)) {
                ViewExtensionsKt.redirectContextClickToLongPressListener(mImageView);
            }
        }
    }

    private int getColumnCount() {
        return DisplayUtils.isLandscape(this) ? 4 : 3;
    }
}

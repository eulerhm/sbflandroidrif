package org.wordpress.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import org.wordpress.android.R;
import org.wordpress.android.models.FilterCriteria;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.helpers.SwipeToRefreshHelper;
import org.wordpress.android.util.widgets.CustomSwipeRefreshLayout;
import org.wordpress.android.widgets.RecyclerItemDecoration;
import java.util.ArrayList;
import java.util.List;
import static org.wordpress.android.util.WPSwipeToRefreshHelper.buildSwipeToRefreshHelper;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FilteredRecyclerView extends RelativeLayout {

    private ProgressBar mProgressLoadMore;

    private SwipeToRefreshHelper mSwipeToRefreshHelper;

    private Spinner mSpinner;

    private boolean mSelectingRememberedFilterOnCreate = false;

    private boolean mHideAppBarLayout = false;

    private RecyclerView mRecyclerView;

    private TextView mEmptyView;

    private Toolbar mToolbar;

    private AppBarLayout mAppBarLayout;

    private RecyclerView mSearchSuggestionsRecyclerView;

    private List<FilterCriteria> mFilterCriteriaOptions;

    private FilterCriteria mCurrentFilter;

    private FilterListener mFilterListener;

    private SpinnerAdapter mSpinnerAdapter;

    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;

    private int mSpinnerTextColor;

    private int mSpinnerDrawableRight;

    private AppLog.T mTAG;

    private boolean mShowEmptyView;

    private boolean mToolbarDisableScrollGestures = false;

    @LayoutRes
    private int mSpinnerItemView = 0;

    @LayoutRes
    private int mSpinnerDropDownItemView = 0;

    public FilteredRecyclerView(Context context) {
        this(context, null);
    }

    public FilteredRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilteredRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(26061)) {
            init(context, attrs);
        }
    }

    public RecyclerView getInternalRecyclerView() {
        return mRecyclerView;
    }

    public void setRefreshing(boolean refreshing) {
        if (!ListenerUtil.mutListener.listen(26062)) {
            mSwipeToRefreshHelper.setRefreshing(refreshing);
        }
    }

    public boolean isRefreshing() {
        return mSwipeToRefreshHelper.isRefreshing();
    }

    public void setCurrentFilter(FilterCriteria filter) {
        if (!ListenerUtil.mutListener.listen(26063)) {
            mCurrentFilter = filter;
        }
        if (!ListenerUtil.mutListener.listen(26072)) {
            if (!mHideAppBarLayout) {
                int position = mSpinnerAdapter.getIndexOfCriteria(filter);
                if (!ListenerUtil.mutListener.listen(26071)) {
                    if ((ListenerUtil.mutListener.listen(26069) ? ((ListenerUtil.mutListener.listen(26068) ? (position >= -1) : (ListenerUtil.mutListener.listen(26067) ? (position <= -1) : (ListenerUtil.mutListener.listen(26066) ? (position < -1) : (ListenerUtil.mutListener.listen(26065) ? (position != -1) : (ListenerUtil.mutListener.listen(26064) ? (position == -1) : (position > -1)))))) || position != mSpinner.getSelectedItemPosition()) : ((ListenerUtil.mutListener.listen(26068) ? (position >= -1) : (ListenerUtil.mutListener.listen(26067) ? (position <= -1) : (ListenerUtil.mutListener.listen(26066) ? (position < -1) : (ListenerUtil.mutListener.listen(26065) ? (position != -1) : (ListenerUtil.mutListener.listen(26064) ? (position == -1) : (position > -1)))))) && position != mSpinner.getSelectedItemPosition()))) {
                        if (!ListenerUtil.mutListener.listen(26070)) {
                            mSpinner.setSelection(position);
                        }
                    }
                }
            }
        }
    }

    public FilterCriteria getCurrentFilter() {
        return mCurrentFilter;
    }

    public boolean isValidFilter(FilterCriteria filter) {
        return (ListenerUtil.mutListener.listen(26075) ? ((ListenerUtil.mutListener.listen(26074) ? ((ListenerUtil.mutListener.listen(26073) ? (filter != null || mFilterCriteriaOptions != null) : (filter != null && mFilterCriteriaOptions != null)) || !mFilterCriteriaOptions.isEmpty()) : ((ListenerUtil.mutListener.listen(26073) ? (filter != null || mFilterCriteriaOptions != null) : (filter != null && mFilterCriteriaOptions != null)) && !mFilterCriteriaOptions.isEmpty())) || mFilterCriteriaOptions.contains(filter)) : ((ListenerUtil.mutListener.listen(26074) ? ((ListenerUtil.mutListener.listen(26073) ? (filter != null || mFilterCriteriaOptions != null) : (filter != null && mFilterCriteriaOptions != null)) || !mFilterCriteriaOptions.isEmpty()) : ((ListenerUtil.mutListener.listen(26073) ? (filter != null || mFilterCriteriaOptions != null) : (filter != null && mFilterCriteriaOptions != null)) && !mFilterCriteriaOptions.isEmpty())) && mFilterCriteriaOptions.contains(filter)));
    }

    public void setFilterListener(FilterListener filterListener) {
        if (!ListenerUtil.mutListener.listen(26076)) {
            mFilterListener = filterListener;
        }
        if (!ListenerUtil.mutListener.listen(26077)) {
            setup(false);
        }
    }

    public void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        if (!ListenerUtil.mutListener.listen(26078)) {
            mAdapter = adapter;
        }
        if (!ListenerUtil.mutListener.listen(26079)) {
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public RecyclerView.Adapter<RecyclerView.ViewHolder> getAdapter() {
        return mAdapter;
    }

    public void setSwipeToRefreshEnabled(boolean enable) {
        if (!ListenerUtil.mutListener.listen(26080)) {
            mSwipeToRefreshHelper.setEnabled(enable);
        }
    }

    public void setLogT(AppLog.T tag) {
        if (!ListenerUtil.mutListener.listen(26081)) {
            mTAG = tag;
        }
    }

    public void setCustomEmptyView() {
        if (!ListenerUtil.mutListener.listen(26082)) {
            mShowEmptyView = true;
        }
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (!ListenerUtil.mutListener.listen(26083)) {
            inflate(getContext(), R.layout.filtered_list_component, this);
        }
        if (!ListenerUtil.mutListener.listen(26089)) {
            if (attrs != null) {
                TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FilteredRecyclerView, 0, 0);
                try {
                    if (!ListenerUtil.mutListener.listen(26085)) {
                        mToolbarDisableScrollGestures = a.getBoolean(R.styleable.FilteredRecyclerView_wpToolbarDisableScrollGestures, false);
                    }
                    if (!ListenerUtil.mutListener.listen(26086)) {
                        mSpinnerItemView = a.getResourceId(R.styleable.FilteredRecyclerView_wpSpinnerItemView, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(26087)) {
                        mSpinnerDropDownItemView = a.getResourceId(R.styleable.FilteredRecyclerView_wpSpinnerDropDownItemView, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(26088)) {
                        mHideAppBarLayout = a.getBoolean(R.styleable.FilteredRecyclerView_wpHideAppBarLayout, false);
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(26084)) {
                        a.recycle();
                    }
                }
            }
        }
        int spacingHorizontal = 0;
        int spacingVertical = DisplayUtils.dpToPx(getContext(), 1);
        if (!ListenerUtil.mutListener.listen(26090)) {
            mRecyclerView = findViewById(R.id.recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(26091)) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(26092)) {
            mRecyclerView.addItemDecoration(new RecyclerItemDecoration(spacingHorizontal, spacingVertical));
        }
        if (!ListenerUtil.mutListener.listen(26093)) {
            mToolbar = findViewById(R.id.toolbar_with_spinner);
        }
        if (!ListenerUtil.mutListener.listen(26094)) {
            mAppBarLayout = findViewById(R.id.app_bar_layout);
        }
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(26097)) {
            if (mToolbarDisableScrollGestures) {
                if (!ListenerUtil.mutListener.listen(26096)) {
                    params.setScrollFlags(0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26095)) {
                    params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26098)) {
            mSearchSuggestionsRecyclerView = findViewById(R.id.suggestions_recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(26099)) {
            mEmptyView = findViewById(R.id.empty_view);
        }
        if (!ListenerUtil.mutListener.listen(26100)) {
            // progress bar that appears when loading more items
            mProgressLoadMore = findViewById(R.id.progress_loading);
        }
        if (!ListenerUtil.mutListener.listen(26101)) {
            mProgressLoadMore.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(26108)) {
            mSwipeToRefreshHelper = buildSwipeToRefreshHelper((CustomSwipeRefreshLayout) findViewById(R.id.ptr_layout), new SwipeToRefreshHelper.RefreshListener() {

                @Override
                public void onRefreshStarted() {
                    if (!ListenerUtil.mutListener.listen(26107)) {
                        post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(26104)) {
                                    if (!NetworkUtils.checkConnection(getContext())) {
                                        if (!ListenerUtil.mutListener.listen(26102)) {
                                            mSwipeToRefreshHelper.setRefreshing(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26103)) {
                                            updateEmptyView(EmptyViewMessageType.NETWORK_ERROR);
                                        }
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26106)) {
                                    if (mFilterListener != null) {
                                        if (!ListenerUtil.mutListener.listen(26105)) {
                                            mFilterListener.onLoadData(true);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26110)) {
            if (mSpinner == null) {
                if (!ListenerUtil.mutListener.listen(26109)) {
                    mSpinner = findViewById(R.id.filter_spinner);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26111)) {
            mAppBarLayout.setVisibility(mHideAppBarLayout ? View.GONE : View.VISIBLE);
        }
    }

    private void setup(boolean refresh) {
        List<FilterCriteria> criterias = mFilterListener.onLoadFilterCriteriaOptions(refresh);
        if (!ListenerUtil.mutListener.listen(26113)) {
            if (criterias != null) {
                if (!ListenerUtil.mutListener.listen(26112)) {
                    mFilterCriteriaOptions = criterias;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26122)) {
            if (criterias == null) {
                if (!ListenerUtil.mutListener.listen(26121)) {
                    mFilterListener.onLoadFilterCriteriaOptionsAsync(new FilterCriteriaAsyncLoaderListener() {

                        @Override
                        public void onFilterCriteriasLoaded(List<FilterCriteria> criteriaList) {
                            if (!ListenerUtil.mutListener.listen(26120)) {
                                if (criteriaList != null) {
                                    if (!ListenerUtil.mutListener.listen(26116)) {
                                        mFilterCriteriaOptions = new ArrayList<>();
                                    }
                                    if (!ListenerUtil.mutListener.listen(26117)) {
                                        mFilterCriteriaOptions.addAll(criteriaList);
                                    }
                                    if (!ListenerUtil.mutListener.listen(26118)) {
                                        initFilterAdapter();
                                    }
                                    if (!ListenerUtil.mutListener.listen(26119)) {
                                        setCurrentFilter(mFilterListener.onRecallSelection());
                                    }
                                }
                            }
                        }
                    }, refresh);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26114)) {
                    initFilterAdapter();
                }
                if (!ListenerUtil.mutListener.listen(26115)) {
                    setCurrentFilter(mFilterListener.onRecallSelection());
                }
            }
        }
    }

    private void onSpinnerItemSelected(int position) {
        if (!ListenerUtil.mutListener.listen(26123)) {
            if (mHideAppBarLayout) {
                throw new IllegalStateException("Developer error: Spinner shouldn't be visible when the appbar is hidden.");
            }
        }
        if (!ListenerUtil.mutListener.listen(26125)) {
            if (mSelectingRememberedFilterOnCreate) {
                if (!ListenerUtil.mutListener.listen(26124)) {
                    mSelectingRememberedFilterOnCreate = false;
                }
                return;
            }
        }
        FilterCriteria selectedCriteria = (FilterCriteria) mSpinnerAdapter.getItem(position);
        if (!ListenerUtil.mutListener.listen(26127)) {
            if (mCurrentFilter == selectedCriteria) {
                if (!ListenerUtil.mutListener.listen(26126)) {
                    AppLog.d(mTAG, "The selected STATUS is already active: " + selectedCriteria.getLabel());
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26128)) {
            AppLog.d(mTAG, "NEW STATUS : " + selectedCriteria.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(26129)) {
            setCurrentFilter(selectedCriteria);
        }
        if (!ListenerUtil.mutListener.listen(26133)) {
            if (mFilterListener != null) {
                if (!ListenerUtil.mutListener.listen(26130)) {
                    mFilterListener.onFilterSelected(position, selectedCriteria);
                }
                if (!ListenerUtil.mutListener.listen(26131)) {
                    setRefreshing(true);
                }
                if (!ListenerUtil.mutListener.listen(26132)) {
                    mFilterListener.onLoadData(false);
                }
            }
        }
    }

    private void initFilterAdapter() {
        if (!ListenerUtil.mutListener.listen(26134)) {
            mSelectingRememberedFilterOnCreate = true;
        }
        if (!ListenerUtil.mutListener.listen(26135)) {
            mSpinnerAdapter = new SpinnerAdapter(getContext(), mFilterCriteriaOptions, mSpinnerItemView, mSpinnerDropDownItemView);
        }
        if (!ListenerUtil.mutListener.listen(26136)) {
            mSpinner.setAdapter(mSpinnerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(26138)) {
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!ListenerUtil.mutListener.listen(26137)) {
                        onSpinnerItemSelected(position);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    private boolean hasAdapter() {
        return (mAdapter != null);
    }

    public void hideEmptyView() {
        if (!ListenerUtil.mutListener.listen(26140)) {
            if (mEmptyView != null) {
                if (!ListenerUtil.mutListener.listen(26139)) {
                    mEmptyView.setVisibility(View.GONE);
                }
            }
        }
    }

    public void updateEmptyView(EmptyViewMessageType emptyViewMessageType) {
        if (!ListenerUtil.mutListener.listen(26141)) {
            if (mEmptyView == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(26152)) {
            if ((ListenerUtil.mutListener.listen(26142) ? (!hasAdapter() && mAdapter.getItemCount() == 0) : (!hasAdapter() || mAdapter.getItemCount() == 0))) {
                if (!ListenerUtil.mutListener.listen(26151)) {
                    if (mFilterListener != null) {
                        if (!ListenerUtil.mutListener.listen(26150)) {
                            if (mShowEmptyView) {
                                String msg = mFilterListener.onShowEmptyViewMessage(emptyViewMessageType);
                                if (!ListenerUtil.mutListener.listen(26147)) {
                                    if (msg == null) {
                                        if (!ListenerUtil.mutListener.listen(26146)) {
                                            msg = getContext().getString(R.string.empty_list_default);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26148)) {
                                    mEmptyView.setText(msg);
                                }
                                if (!ListenerUtil.mutListener.listen(26149)) {
                                    mEmptyView.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(26144)) {
                                    mEmptyView.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(26145)) {
                                    mFilterListener.onShowCustomEmptyView(emptyViewMessageType);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26143)) {
                    mEmptyView.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * show/hide progress bar which appears at the bottom when loading more items
     */
    public void showLoadingProgress() {
        if (!ListenerUtil.mutListener.listen(26154)) {
            if (mProgressLoadMore != null) {
                if (!ListenerUtil.mutListener.listen(26153)) {
                    mProgressLoadMore.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void hideLoadingProgress() {
        if (!ListenerUtil.mutListener.listen(26156)) {
            if (mProgressLoadMore != null) {
                if (!ListenerUtil.mutListener.listen(26155)) {
                    mProgressLoadMore.setVisibility(View.GONE);
                }
            }
        }
    }

    /*
     * add a menu to the right side of the toolbar, returns the toolbar menu so the caller
     * can act upon it
     */
    public Menu addToolbarMenu(@MenuRes int menuResId) {
        if (!ListenerUtil.mutListener.listen(26157)) {
            mToolbar.inflateMenu(menuResId);
        }
        return mToolbar.getMenu();
    }

    public void setToolbarBackgroundColor(int color) {
        if (!ListenerUtil.mutListener.listen(26158)) {
            mToolbar.setBackgroundColor(color);
        }
    }

    public void setToolbarSpinnerTextColor(int color) {
        if (!ListenerUtil.mutListener.listen(26159)) {
            mSpinnerTextColor = color;
        }
    }

    public void setToolbarSpinnerDrawable(int drawableResId) {
        if (!ListenerUtil.mutListener.listen(26160)) {
            mSpinnerDrawableRight = drawableResId;
        }
    }

    public void setToolbarLeftPadding(int paddingLeft) {
        if (!ListenerUtil.mutListener.listen(26161)) {
            ViewCompat.setPaddingRelative(mToolbar, paddingLeft, mToolbar.getPaddingTop(), ViewCompat.getPaddingEnd(mToolbar), mToolbar.getPaddingBottom());
        }
    }

    public void setToolbarRightPadding(int paddingRight) {
        if (!ListenerUtil.mutListener.listen(26162)) {
            ViewCompat.setPaddingRelative(mToolbar, ViewCompat.getPaddingStart(mToolbar), mToolbar.getPaddingTop(), paddingRight, mToolbar.getPaddingBottom());
        }
    }

    public void setToolbarLeftAndRightPadding(int paddingLeft, int paddingRight) {
        if (!ListenerUtil.mutListener.listen(26163)) {
            ViewCompat.setPaddingRelative(mToolbar, paddingLeft, mToolbar.getPaddingTop(), paddingRight, mToolbar.getPaddingBottom());
        }
    }

    public void setToolbarTitle(@StringRes int title, int startMargin) {
        if (!ListenerUtil.mutListener.listen(26164)) {
            mToolbar.setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(26165)) {
            mToolbar.setTitleMarginStart(startMargin);
        }
    }

    public void setToolbarScrollFlags(int flags) {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(26166)) {
            params.setScrollFlags(flags);
        }
        if (!ListenerUtil.mutListener.listen(26167)) {
            mToolbar.setLayoutParams(params);
        }
    }

    public void scrollRecycleViewToPosition(int position) {
        if (!ListenerUtil.mutListener.listen(26168)) {
            if (mRecyclerView == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26169)) {
            mRecyclerView.scrollToPosition(position);
        }
    }

    public int getCurrentPosition() {
        if ((ListenerUtil.mutListener.listen(26170) ? (mRecyclerView != null || mRecyclerView.getLayoutManager() != null) : (mRecyclerView != null && mRecyclerView.getLayoutManager() != null))) {
            return ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        } else {
            return -1;
        }
    }

    public void smoothScrollToPosition(int position) {
        if (!ListenerUtil.mutListener.listen(26173)) {
            if ((ListenerUtil.mutListener.listen(26171) ? (mRecyclerView != null || mRecyclerView.getLayoutManager() != null) : (mRecyclerView != null && mRecyclerView.getLayoutManager() != null))) {
                if (!ListenerUtil.mutListener.listen(26172)) {
                    mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, position);
                }
            }
        }
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        if (!ListenerUtil.mutListener.listen(26174)) {
            if (mRecyclerView == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(26175)) {
            mRecyclerView.addItemDecoration(decor);
        }
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        if (!ListenerUtil.mutListener.listen(26177)) {
            if (mRecyclerView != null) {
                if (!ListenerUtil.mutListener.listen(26176)) {
                    mRecyclerView.addOnScrollListener(listener);
                }
            }
        }
    }

    public void removeOnScrollListener(RecyclerView.OnScrollListener listener) {
        if (!ListenerUtil.mutListener.listen(26179)) {
            if (mRecyclerView != null) {
                if (!ListenerUtil.mutListener.listen(26178)) {
                    mRecyclerView.removeOnScrollListener(listener);
                }
            }
        }
    }

    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }

    /*
     * use this if you need to reload the criterias for this FilteredRecyclerView. The actual data loading goes
     * through the FilteredRecyclerView lifecycle using its listeners:
     *
     * - FilterCriteriaAsyncLoaderListener
     * and
     * - FilterListener.onLoadFilterCriteriaOptions
     * */
    public void refreshFilterCriteriaOptions() {
        if (!ListenerUtil.mutListener.listen(26180)) {
            setup(true);
        }
    }

    public void showSearchSuggestions() {
        if (!ListenerUtil.mutListener.listen(26181)) {
            mSearchSuggestionsRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void hideSearchSuggestions() {
        if (!ListenerUtil.mutListener.listen(26182)) {
            mSearchSuggestionsRecyclerView.setVisibility(View.GONE);
        }
    }

    public void setSearchSuggestionAdapter(RecyclerView.Adapter searchSuggestionAdapter) {
        if (!ListenerUtil.mutListener.listen(26183)) {
            mSearchSuggestionsRecyclerView.setAdapter(searchSuggestionAdapter);
        }
    }

    public void showAppBarLayout() {
        if (!ListenerUtil.mutListener.listen(26184)) {
            mAppBarLayout.setVisibility(VISIBLE);
        }
    }

    /*
     * adapter used by the filter spinner
     */
    private class SpinnerAdapter extends BaseAdapter {

        private final List<FilterCriteria> mFilterValues;

        private final LayoutInflater mInflater;

        @LayoutRes
        private final int mItemView;

        @LayoutRes
        private final int mDropDownItemView;

        SpinnerAdapter(Context context, List<FilterCriteria> filterValues, @LayoutRes int itemView, @LayoutRes int dropDownItemView) {
            super();
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mFilterValues = filterValues;
            if ((ListenerUtil.mutListener.listen(26189) ? (itemView >= 0) : (ListenerUtil.mutListener.listen(26188) ? (itemView <= 0) : (ListenerUtil.mutListener.listen(26187) ? (itemView > 0) : (ListenerUtil.mutListener.listen(26186) ? (itemView < 0) : (ListenerUtil.mutListener.listen(26185) ? (itemView != 0) : (itemView == 0))))))) {
                mItemView = R.layout.filter_spinner_item;
            } else {
                mItemView = itemView;
            }
            if ((ListenerUtil.mutListener.listen(26194) ? (dropDownItemView >= 0) : (ListenerUtil.mutListener.listen(26193) ? (dropDownItemView <= 0) : (ListenerUtil.mutListener.listen(26192) ? (dropDownItemView > 0) : (ListenerUtil.mutListener.listen(26191) ? (dropDownItemView < 0) : (ListenerUtil.mutListener.listen(26190) ? (dropDownItemView != 0) : (dropDownItemView == 0))))))) {
                mDropDownItemView = R.layout.toolbar_spinner_dropdown_item;
            } else {
                mDropDownItemView = dropDownItemView;
            }
        }

        @Override
        public int getCount() {
            return (mFilterValues != null ? mFilterValues.size() : 0);
        }

        @Override
        public Object getItem(int position) {
            return mFilterValues.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View view;
            if (convertView == null) {
                view = mInflater.inflate(mItemView, parent, false);
                final TextView text = view.findViewById(R.id.text);
                FilterCriteria selectedCriteria = (FilterCriteria) getItem(position);
                if (!ListenerUtil.mutListener.listen(26195)) {
                    text.setText(selectedCriteria.getLabel());
                }
                if (!ListenerUtil.mutListener.listen(26202)) {
                    if ((ListenerUtil.mutListener.listen(26200) ? (mSpinnerTextColor >= 0) : (ListenerUtil.mutListener.listen(26199) ? (mSpinnerTextColor <= 0) : (ListenerUtil.mutListener.listen(26198) ? (mSpinnerTextColor > 0) : (ListenerUtil.mutListener.listen(26197) ? (mSpinnerTextColor < 0) : (ListenerUtil.mutListener.listen(26196) ? (mSpinnerTextColor == 0) : (mSpinnerTextColor != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(26201)) {
                            text.setTextColor(mSpinnerTextColor);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(26210)) {
                    if ((ListenerUtil.mutListener.listen(26207) ? (mSpinnerDrawableRight >= 0) : (ListenerUtil.mutListener.listen(26206) ? (mSpinnerDrawableRight <= 0) : (ListenerUtil.mutListener.listen(26205) ? (mSpinnerDrawableRight > 0) : (ListenerUtil.mutListener.listen(26204) ? (mSpinnerDrawableRight < 0) : (ListenerUtil.mutListener.listen(26203) ? (mSpinnerDrawableRight == 0) : (mSpinnerDrawableRight != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(26208)) {
                            text.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.margin_medium));
                        }
                        if (!ListenerUtil.mutListener.listen(26209)) {
                            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
                        }
                    }
                }
            } else {
                view = convertView;
            }
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            FilterCriteria selectedCriteria = (FilterCriteria) getItem(position);
            final TagViewHolder holder;
            if (convertView == null) {
                if (!ListenerUtil.mutListener.listen(26211)) {
                    convertView = mInflater.inflate(mDropDownItemView, parent, false);
                }
                holder = new TagViewHolder(convertView);
                if (!ListenerUtil.mutListener.listen(26212)) {
                    convertView.setTag(holder);
                }
            } else {
                holder = (TagViewHolder) convertView.getTag();
            }
            if (!ListenerUtil.mutListener.listen(26213)) {
                holder.mTextView.setText(selectedCriteria.getLabel());
            }
            return convertView;
        }

        private class TagViewHolder {

            private final TextView mTextView;

            TagViewHolder(View view) {
                mTextView = view.findViewById(R.id.text);
            }
        }

        public int getIndexOfCriteria(FilterCriteria tm) {
            if (!ListenerUtil.mutListener.listen(26223)) {
                if ((ListenerUtil.mutListener.listen(26214) ? (tm != null || mFilterValues != null) : (tm != null && mFilterValues != null))) {
                    if (!ListenerUtil.mutListener.listen(26222)) {
                        {
                            long _loopCounter396 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(26221) ? (i >= mFilterValues.size()) : (ListenerUtil.mutListener.listen(26220) ? (i <= mFilterValues.size()) : (ListenerUtil.mutListener.listen(26219) ? (i > mFilterValues.size()) : (ListenerUtil.mutListener.listen(26218) ? (i != mFilterValues.size()) : (ListenerUtil.mutListener.listen(26217) ? (i == mFilterValues.size()) : (i < mFilterValues.size())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter396", ++_loopCounter396);
                                FilterCriteria criteria = mFilterValues.get(i);
                                if (!ListenerUtil.mutListener.listen(26216)) {
                                    if ((ListenerUtil.mutListener.listen(26215) ? (criteria != null || criteria.equals(tm)) : (criteria != null && criteria.equals(tm)))) {
                                        return i;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }
    }

    /*
     * returns true if the first item is still visible in the RecyclerView - will return
     * false if the first item is scrolled out of view, or if the list is empty
     */
    public boolean isFirstItemVisible() {
        if (!ListenerUtil.mutListener.listen(26225)) {
            if ((ListenerUtil.mutListener.listen(26224) ? (mRecyclerView == null && mRecyclerView.getLayoutManager() == null) : (mRecyclerView == null || mRecyclerView.getLayoutManager() == null))) {
                return false;
            }
        }
        View child = mRecyclerView.getLayoutManager().getChildAt(0);
        return ((ListenerUtil.mutListener.listen(26226) ? (child != null || mRecyclerView.getLayoutManager().getPosition(child) == 0) : (child != null && mRecyclerView.getLayoutManager().getPosition(child) == 0)));
    }

    /**
     * implement this interface to use FilterRecyclerView
     */
    public interface FilterListener {

        /**
         * Called upon initialization - provide an array of FilterCriterias here. These are the possible criterias
         * the Spinner is loaded with, and through which the data can be filtered.
         *
         * @param refresh "true"if the criterias need be refreshed
         * @return an array of FilterCriteria to be used on Spinner initialization, or null if going to use the
         * Async method below
         */
        List<FilterCriteria> onLoadFilterCriteriaOptions(boolean refresh);

        /**
         * Called upon initialization - you can use this callback to start an asynctask to build an array of
         * FilterCriterias here. Once the AsyncTask is done, it should call the provided listener
         * The Spinner is then loaded with such array of FilterCriterias, through which the main data can be filtered.
         *
         * @param listener to be called to pass the FilterCriteria array when done
         * @param refresh  "true"if the criterias need be refreshed
         */
        void onLoadFilterCriteriaOptionsAsync(FilterCriteriaAsyncLoaderListener listener, boolean refresh);

        /**
         * Called upon initialization, right after onLoadFilterCriteriaOptions().
         * Once the criteria options are set up, use this callback to return the latest option selected on the
         * screen the last time the user visited it, or a default value for the filter Spinner to be initialized with.
         *
         * @return
         */
        FilterCriteria onRecallSelection();

        /**
         * When this method is called, you should load data into the FilteredRecyclerView adapter, using the
         * latest criteria passed to you in a previous onFilterSelected() call.
         * Within the FilteredRecyclerView lifecycle, this is triggered in three different moments:
         * 1 - upon initialisation
         * 2 - each time a screen refresh is requested
         * 3 - each time the user changes the filter spinner selection
         */
        void onLoadData(boolean forced);

        /**
         * Called each time the user changes the Spinner selection (i.e. changes the criteria on which to filter
         * the data). You should only take note of the change, and remember it, as a request to load data with
         * the newly selected filter shall always arrive through onLoadData().
         * The parameters passed in this callback can be used alternatively as per your convenience.
         *
         * @param position of the selected criteria within the array returned by onLoadFilterCriteriaOptions()
         * @param criteria the actual criteria selected
         */
        void onFilterSelected(int position, FilterCriteria criteria);

        /**
         * Called when there's no data to show.
         *
         * @param emptyViewMsgType this will hint you on the reason why no data is being shown, so you can return
         *                         a proper message to be displayed to the user
         * @return the message to be displayed to the user, or null if using a Custom Empty View (see below)
         */
        String onShowEmptyViewMessage(EmptyViewMessageType emptyViewMsgType);

        /**
         * Called when there's no data to show, and only if a custom EmptyView is set (onShowEmptyViewMessage will
         * be called otherwise).
         *
         * @param emptyViewMsgType this will hint you on the reason why no data is being shown, and
         *                         also here you should perform any actions on your custom empty view
         * @return nothing
         */
        void onShowCustomEmptyView(EmptyViewMessageType emptyViewMsgType);
    }

    /**
     * implement this interface to load filtering options (that is, an array of FilterCriteria) asynchronously
     */
    public interface FilterCriteriaAsyncLoaderListener {

        /**
         * Will be called during initialization of FilteredRecyclerView once you're ready building the
         * FilterCriteria array
         *
         * @param criteriaList the array of FilterCriteria objects you just built
         */
        void onFilterCriteriasLoaded(List<FilterCriteria> criteriaList);
    }
}

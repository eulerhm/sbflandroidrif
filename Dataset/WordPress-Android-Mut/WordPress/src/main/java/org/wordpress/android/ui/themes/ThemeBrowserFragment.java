package org.wordpress.android.ui.themes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import com.google.android.material.elevation.ElevationOverlayProvider;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.ThemeModel;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.fluxc.store.ThemeStore;
import org.wordpress.android.ui.ActionableEmptyView;
import org.wordpress.android.ui.plans.PlansConstants;
import org.wordpress.android.ui.quickstart.QuickStartEvent;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.QuickStartUtils;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.helpers.SwipeToRefreshHelper;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.widgets.HeaderGridView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import static org.wordpress.android.util.WPSwipeToRefreshHelper.buildSwipeToRefreshHelper;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A fragment display the themes on a grid view.
 */
public class ThemeBrowserFragment extends Fragment implements RecyclerListener, SearchView.OnQueryTextListener {

    public static final String TAG = ThemeBrowserFragment.class.getName();

    private static final String KEY_LAST_SEARCH = "last_search";

    public static ThemeBrowserFragment newInstance(SiteModel site) {
        ThemeBrowserFragment fragment = new ThemeBrowserFragment();
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(23485)) {
            bundle.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(23486)) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    interface ThemeBrowserFragmentCallback {

        void onActivateSelected(String themeId);

        void onTryAndCustomizeSelected(String themeId);

        void onViewSelected(String themeId);

        void onDetailsSelected(String themeId);

        void onSupportSelected(String themeId);

        void onSwipeToRefresh();
    }

    private SwipeToRefreshHelper mSwipeToRefreshHelper;

    private String mCurrentThemeId;

    private String mLastSearch;

    private HeaderGridView mGridView;

    private RelativeLayout mEmptyView;

    private ActionableEmptyView mActionableEmptyView;

    private TextView mCurrentThemeTextView;

    private View mHeaderCustomizeButton;

    private ThemeBrowserAdapter mAdapter;

    private boolean mShouldRefreshOnStart;

    private TextView mEmptyTextView;

    private SiteModel mSite;

    private MenuItem mSearchMenuItem;

    private SearchView mSearchView;

    private ThemeBrowserFragmentCallback mCallback;

    private QuickStartEvent mQuickStartEvent;

    @Inject
    ThemeStore mThemeStore;

    @Inject
    QuickStartStore mQuickStartStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    ImageManager mImageManager;

    @Inject
    QuickStartUtilsWrapper mQuickStartUtilsWrapper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23487)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(23488)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(23489)) {
            mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
        }
        if (!ListenerUtil.mutListener.listen(23492)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(23490)) {
                    ToastUtils.showToast(getActivity(), R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(23491)) {
                    getActivity().finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23493)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(23496)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(23494)) {
                    mLastSearch = savedInstanceState.getString(KEY_LAST_SEARCH);
                }
                if (!ListenerUtil.mutListener.listen(23495)) {
                    mQuickStartEvent = savedInstanceState.getParcelable(QuickStartEvent.KEY);
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(23497)) {
            super.onAttach(activity);
        }
        try {
            if (!ListenerUtil.mutListener.listen(23498)) {
                mCallback = (ThemeBrowserFragmentCallback) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ThemeBrowserFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(23499)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(23501)) {
            if (mSearchView != null) {
                if (!ListenerUtil.mutListener.listen(23500)) {
                    mSearchView.setOnQueryTextListener(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23502)) {
            mCallback = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.theme_browser_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(23503)) {
            mActionableEmptyView = view.findViewById(R.id.actionable_empty_view);
        }
        if (!ListenerUtil.mutListener.listen(23504)) {
            mEmptyTextView = view.findViewById(R.id.text_empty);
        }
        if (!ListenerUtil.mutListener.listen(23505)) {
            mEmptyView = view.findViewById(R.id.empty_view);
        }
        if (!ListenerUtil.mutListener.listen(23506)) {
            configureGridView(inflater, view);
        }
        if (!ListenerUtil.mutListener.listen(23507)) {
            configureSwipeToRefresh(view);
        }
        return view;
    }

    private void showQuickStartFocusPoint() {
        if (!ListenerUtil.mutListener.listen(23508)) {
            if (getView() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23509)) {
            mHeaderCustomizeButton.post(() -> {
                int focusPointSize = getResources().getDimensionPixelOffset(R.dimen.quick_start_focus_point_size);
                int horizontalOffset = (mHeaderCustomizeButton.getWidth() / 2) - focusPointSize + getResources().getDimensionPixelOffset(R.dimen.quick_start_focus_point_bottom_nav_offset);
                QuickStartUtils.addQuickStartFocusPointAboveTheView((ViewGroup) getView(), mHeaderCustomizeButton, horizontalOffset, 0);
                mHeaderCustomizeButton.setPressed(true);
            });
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23510)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(23511)) {
            getAdapter().setThemeList(fetchThemes());
        }
        if (!ListenerUtil.mutListener.listen(23512)) {
            mGridView.setAdapter(getAdapter());
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(23513)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(23516)) {
            if ((ListenerUtil.mutListener.listen(23514) ? (mSearchMenuItem != null || mSearchMenuItem.isActionViewExpanded()) : (mSearchMenuItem != null && mSearchMenuItem.isActionViewExpanded()))) {
                if (!ListenerUtil.mutListener.listen(23515)) {
                    outState.putString(KEY_LAST_SEARCH, mSearchView.getQuery().toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23517)) {
            outState.putParcelable(QuickStartEvent.KEY, mQuickStartEvent);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(23518)) {
            inflater.inflate(R.menu.search, menu);
        }
        if (!ListenerUtil.mutListener.listen(23519)) {
            mSearchMenuItem = menu.findItem(R.id.menu_search);
        }
        if (!ListenerUtil.mutListener.listen(23520)) {
            mSearchView = (SearchView) mSearchMenuItem.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(23521)) {
            mSearchView.setOnQueryTextListener(this);
        }
        if (!ListenerUtil.mutListener.listen(23522)) {
            mSearchView.setMaxWidth(Integer.MAX_VALUE);
        }
        if (!ListenerUtil.mutListener.listen(23526)) {
            if (!TextUtils.isEmpty(mLastSearch)) {
                if (!ListenerUtil.mutListener.listen(23523)) {
                    mSearchMenuItem.expandActionView();
                }
                if (!ListenerUtil.mutListener.listen(23524)) {
                    onQueryTextSubmit(mLastSearch);
                }
                if (!ListenerUtil.mutListener.listen(23525)) {
                    mSearchView.setQuery(mLastSearch, true);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(23528)) {
            if (item.getItemId() == R.id.menu_search) {
                if (!ListenerUtil.mutListener.listen(23527)) {
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.THEMES_ACCESSED_SEARCH, mSite);
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!ListenerUtil.mutListener.listen(23529)) {
            getAdapter().getFilter().filter(query);
        }
        if (!ListenerUtil.mutListener.listen(23531)) {
            if (mSearchView != null) {
                if (!ListenerUtil.mutListener.listen(23530)) {
                    mSearchView.clearFocus();
                }
            }
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!ListenerUtil.mutListener.listen(23532)) {
            getAdapter().getFilter().filter(newText);
        }
        return true;
    }

    @Override
    public void onMovedToScrapHeap(View view) {
        // cancel image fetch requests if the view has been moved to recycler.
        ImageView niv = view.findViewById(R.id.theme_grid_item_image);
        if (!ListenerUtil.mutListener.listen(23534)) {
            if (niv != null) {
                if (!ListenerUtil.mutListener.listen(23533)) {
                    mImageManager.cancelRequestAndClearImageView(niv);
                }
            }
        }
    }

    TextView getCurrentThemeTextView() {
        return mCurrentThemeTextView;
    }

    void setCurrentThemeId(String currentThemeId) {
        if (!ListenerUtil.mutListener.listen(23535)) {
            mCurrentThemeId = currentThemeId;
        }
        if (!ListenerUtil.mutListener.listen(23536)) {
            refreshView();
        }
    }

    private void addHeaderViews(LayoutInflater inflater) {
        if (!ListenerUtil.mutListener.listen(23537)) {
            addMainHeader(inflater);
        }
    }

    private void configureSwipeToRefresh(View view) {
        if (!ListenerUtil.mutListener.listen(23538)) {
            mSwipeToRefreshHelper = buildSwipeToRefreshHelper(view.findViewById(R.id.ptr_layout), () -> {
                if (!isAdded()) {
                    return;
                }
                if (!NetworkUtils.checkConnection(getActivity())) {
                    mSwipeToRefreshHelper.setRefreshing(false);
                    mEmptyTextView.setText(R.string.no_network_title);
                    return;
                }
                setRefreshing(true);
                mCallback.onSwipeToRefresh();
            });
        }
        if (!ListenerUtil.mutListener.listen(23539)) {
            mSwipeToRefreshHelper.setRefreshing(mShouldRefreshOnStart);
        }
    }

    private void configureGridView(LayoutInflater inflater, View view) {
        if (!ListenerUtil.mutListener.listen(23540)) {
            mGridView = view.findViewById(R.id.theme_listview);
        }
        if (!ListenerUtil.mutListener.listen(23541)) {
            addHeaderViews(inflater);
        }
        if (!ListenerUtil.mutListener.listen(23542)) {
            mGridView.setRecyclerListener(this);
        }
    }

    private void addMainHeader(LayoutInflater inflater) {
        @SuppressLint("InflateParams")
        View header = inflater.inflate(R.layout.theme_grid_cardview_header, null);
        // inflater doesn't work with automatic elevation in night mode so we set card background color manually
        View headerCardView = header.findViewById(R.id.header_card);
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(header.getContext());
        int elevatedSurfaceColor = elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(headerCardView.getElevation());
        if (!ListenerUtil.mutListener.listen(23543)) {
            headerCardView.setBackgroundColor(elevatedSurfaceColor);
        }
        if (!ListenerUtil.mutListener.listen(23544)) {
            mCurrentThemeTextView = header.findViewById(R.id.header_theme_text);
        }
        if (!ListenerUtil.mutListener.listen(23545)) {
            setThemeNameIfAlreadyAvailable();
        }
        if (!ListenerUtil.mutListener.listen(23546)) {
            mHeaderCustomizeButton = header.findViewById(R.id.customize);
        }
        if (!ListenerUtil.mutListener.listen(23547)) {
            mHeaderCustomizeButton.setOnClickListener(v -> {
                AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.THEMES_CUSTOMIZE_ACCESSED, mSite);
                mCallback.onTryAndCustomizeSelected(mCurrentThemeId);
            });
        }
        LinearLayout details = header.findViewById(R.id.details);
        if (!ListenerUtil.mutListener.listen(23548)) {
            details.setOnClickListener(v -> mCallback.onDetailsSelected(mCurrentThemeId));
        }
        LinearLayout support = header.findViewById(R.id.support);
        if (!ListenerUtil.mutListener.listen(23549)) {
            support.setOnClickListener(v -> mCallback.onSupportSelected(mCurrentThemeId));
        }
        if (!ListenerUtil.mutListener.listen(23550)) {
            mGridView.addHeaderView(header);
        }
    }

    private void setThemeNameIfAlreadyAvailable() {
        ThemeModel currentTheme = mThemeStore.getActiveThemeForSite(mSite);
        if (!ListenerUtil.mutListener.listen(23552)) {
            if (currentTheme != null) {
                if (!ListenerUtil.mutListener.listen(23551)) {
                    mCurrentThemeTextView.setText(currentTheme.getName());
                }
            }
        }
    }

    public void setRefreshing(boolean refreshing) {
        if (!ListenerUtil.mutListener.listen(23553)) {
            mShouldRefreshOnStart = refreshing;
        }
        if (!ListenerUtil.mutListener.listen(23557)) {
            if (mSwipeToRefreshHelper != null) {
                if (!ListenerUtil.mutListener.listen(23554)) {
                    mSwipeToRefreshHelper.setRefreshing(refreshing);
                }
                if (!ListenerUtil.mutListener.listen(23556)) {
                    if (!refreshing) {
                        if (!ListenerUtil.mutListener.listen(23555)) {
                            refreshView();
                        }
                    }
                }
            }
        }
    }

    private void updateDisplay() {
        if (!ListenerUtil.mutListener.listen(23559)) {
            if ((ListenerUtil.mutListener.listen(23558) ? (!isAdded() && getView() == null) : (!isAdded() || getView() == null))) {
                return;
            }
        }
        boolean hasThemes = (ListenerUtil.mutListener.listen(23564) ? (getAdapter().getUnfilteredCount() >= 0) : (ListenerUtil.mutListener.listen(23563) ? (getAdapter().getUnfilteredCount() <= 0) : (ListenerUtil.mutListener.listen(23562) ? (getAdapter().getUnfilteredCount() < 0) : (ListenerUtil.mutListener.listen(23561) ? (getAdapter().getUnfilteredCount() != 0) : (ListenerUtil.mutListener.listen(23560) ? (getAdapter().getUnfilteredCount() == 0) : (getAdapter().getUnfilteredCount() > 0))))));
        boolean hasVisibleThemes = (ListenerUtil.mutListener.listen(23569) ? (getAdapter().getCount() >= 0) : (ListenerUtil.mutListener.listen(23568) ? (getAdapter().getCount() <= 0) : (ListenerUtil.mutListener.listen(23567) ? (getAdapter().getCount() < 0) : (ListenerUtil.mutListener.listen(23566) ? (getAdapter().getCount() != 0) : (ListenerUtil.mutListener.listen(23565) ? (getAdapter().getCount() == 0) : (getAdapter().getCount() > 0))))));
        boolean hasNoMatchingThemes = (ListenerUtil.mutListener.listen(23570) ? (hasThemes || !hasVisibleThemes) : (hasThemes && !hasVisibleThemes));
        if (!ListenerUtil.mutListener.listen(23571)) {
            mEmptyView.setVisibility(!hasThemes ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23574)) {
            if ((ListenerUtil.mutListener.listen(23572) ? (!hasThemes || !NetworkUtils.isNetworkAvailable(getActivity())) : (!hasThemes && !NetworkUtils.isNetworkAvailable(getActivity())))) {
                if (!ListenerUtil.mutListener.listen(23573)) {
                    mEmptyTextView.setText(R.string.no_network_title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23575)) {
            mGridView.setVisibility(hasVisibleThemes ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23576)) {
            mActionableEmptyView.setVisibility(hasNoMatchingThemes ? View.VISIBLE : View.GONE);
        }
    }

    private List<ThemeModel> fetchThemes() {
        if (!ListenerUtil.mutListener.listen(23577)) {
            if (mSite == null) {
                return new ArrayList<>();
            }
        }
        if (!ListenerUtil.mutListener.listen(23578)) {
            if (mSite.isWPCom()) {
                return getSortedWpComThemes();
            }
        }
        return getSortedJetpackThemes();
    }

    private ThemeBrowserAdapter getAdapter() {
        if (!ListenerUtil.mutListener.listen(23581)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(23579)) {
                    mAdapter = new ThemeBrowserAdapter(getActivity(), mSite.getPlanId(), mCallback, mImageManager);
                }
                if (!ListenerUtil.mutListener.listen(23580)) {
                    mAdapter.registerDataSetObserver(new ThemeDataSetObserver());
                }
            }
        }
        return mAdapter;
    }

    protected void refreshView() {
        if (!ListenerUtil.mutListener.listen(23582)) {
            getAdapter().setThemeList(fetchThemes());
        }
    }

    private List<ThemeModel> getSortedWpComThemes() {
        List<ThemeModel> wpComThemes = mThemeStore.getWpComThemes();
        if (!ListenerUtil.mutListener.listen(23583)) {
            // first thing to do is attempt to find the active theme and move it to the front of the list
            moveActiveThemeToFront(wpComThemes);
        }
        if (!ListenerUtil.mutListener.listen(23585)) {
            // then remove all premium themes from the list with an exception for the active theme
            if (!shouldShowPremiumThemes()) {
                if (!ListenerUtil.mutListener.listen(23584)) {
                    removeNonActivePremiumThemes(wpComThemes);
                }
            }
        }
        return wpComThemes;
    }

    private List<ThemeModel> getSortedJetpackThemes() {
        List<ThemeModel> wpComThemes = mThemeStore.getWpComThemes();
        List<ThemeModel> uploadedThemes = mThemeStore.getThemesForSite(mSite);
        if (!ListenerUtil.mutListener.listen(23586)) {
            // put the active theme at the top of the uploaded themes list
            moveActiveThemeToFront(uploadedThemes);
        }
        if (!ListenerUtil.mutListener.listen(23587)) {
            // remove all premium themes from the WP.com themes list
            removeNonActivePremiumThemes(wpComThemes);
        }
        if (!ListenerUtil.mutListener.listen(23588)) {
            // remove uploaded themes from WP.com themes list (including active theme)
            removeDuplicateThemes(wpComThemes, uploadedThemes);
        }
        List<ThemeModel> allThemes = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(23589)) {
            allThemes.addAll(uploadedThemes);
        }
        if (!ListenerUtil.mutListener.listen(23590)) {
            allThemes.addAll(wpComThemes);
        }
        return allThemes;
    }

    private void moveActiveThemeToFront(final List<ThemeModel> themes) {
        if (!ListenerUtil.mutListener.listen(23593)) {
            if ((ListenerUtil.mutListener.listen(23592) ? ((ListenerUtil.mutListener.listen(23591) ? (themes == null && themes.isEmpty()) : (themes == null || themes.isEmpty())) && TextUtils.isEmpty(mCurrentThemeId)) : ((ListenerUtil.mutListener.listen(23591) ? (themes == null && themes.isEmpty()) : (themes == null || themes.isEmpty())) || TextUtils.isEmpty(mCurrentThemeId)))) {
                return;
            }
        }
        // find the index of the active theme
        int activeThemeIndex = 0;
        if (!ListenerUtil.mutListener.listen(23597)) {
            {
                long _loopCounter350 = 0;
                for (ThemeModel theme : themes) {
                    ListenerUtil.loopListener.listen("_loopCounter350", ++_loopCounter350);
                    if (!ListenerUtil.mutListener.listen(23596)) {
                        if (mCurrentThemeId.equals(theme.getThemeId())) {
                            if (!ListenerUtil.mutListener.listen(23594)) {
                                theme.setActive(true);
                            }
                            if (!ListenerUtil.mutListener.listen(23595)) {
                                activeThemeIndex = themes.indexOf(theme);
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23604)) {
            // move active theme to front of list
            if ((ListenerUtil.mutListener.listen(23602) ? (activeThemeIndex >= 0) : (ListenerUtil.mutListener.listen(23601) ? (activeThemeIndex <= 0) : (ListenerUtil.mutListener.listen(23600) ? (activeThemeIndex < 0) : (ListenerUtil.mutListener.listen(23599) ? (activeThemeIndex != 0) : (ListenerUtil.mutListener.listen(23598) ? (activeThemeIndex == 0) : (activeThemeIndex > 0))))))) {
                if (!ListenerUtil.mutListener.listen(23603)) {
                    themes.add(0, themes.remove(activeThemeIndex));
                }
            }
        }
    }

    private void removeNonActivePremiumThemes(final List<ThemeModel> themes) {
        if (!ListenerUtil.mutListener.listen(23606)) {
            if ((ListenerUtil.mutListener.listen(23605) ? (themes == null && themes.isEmpty()) : (themes == null || themes.isEmpty()))) {
                return;
            }
        }
        Iterator<ThemeModel> iterator = themes.iterator();
        if (!ListenerUtil.mutListener.listen(23610)) {
            {
                long _loopCounter351 = 0;
                while (iterator.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter351", ++_loopCounter351);
                    ThemeModel theme = iterator.next();
                    if (!ListenerUtil.mutListener.listen(23609)) {
                        if ((ListenerUtil.mutListener.listen(23607) ? (!theme.isFree() || !theme.getActive()) : (!theme.isFree() && !theme.getActive()))) {
                            if (!ListenerUtil.mutListener.listen(23608)) {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    private void removeDuplicateThemes(final List<ThemeModel> wpComThemes, final List<ThemeModel> uploadedThemes) {
        if (!ListenerUtil.mutListener.listen(23614)) {
            if ((ListenerUtil.mutListener.listen(23613) ? ((ListenerUtil.mutListener.listen(23612) ? ((ListenerUtil.mutListener.listen(23611) ? (wpComThemes == null && wpComThemes.isEmpty()) : (wpComThemes == null || wpComThemes.isEmpty())) && uploadedThemes == null) : ((ListenerUtil.mutListener.listen(23611) ? (wpComThemes == null && wpComThemes.isEmpty()) : (wpComThemes == null || wpComThemes.isEmpty())) || uploadedThemes == null)) && uploadedThemes.isEmpty()) : ((ListenerUtil.mutListener.listen(23612) ? ((ListenerUtil.mutListener.listen(23611) ? (wpComThemes == null && wpComThemes.isEmpty()) : (wpComThemes == null || wpComThemes.isEmpty())) && uploadedThemes == null) : ((ListenerUtil.mutListener.listen(23611) ? (wpComThemes == null && wpComThemes.isEmpty()) : (wpComThemes == null || wpComThemes.isEmpty())) || uploadedThemes == null)) || uploadedThemes.isEmpty()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23618)) {
            {
                long _loopCounter353 = 0;
                for (ThemeModel uploadedTheme : uploadedThemes) {
                    ListenerUtil.loopListener.listen("_loopCounter353", ++_loopCounter353);
                    Iterator<ThemeModel> wpComIterator = wpComThemes.iterator();
                    if (!ListenerUtil.mutListener.listen(23617)) {
                        {
                            long _loopCounter352 = 0;
                            while (wpComIterator.hasNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter352", ++_loopCounter352);
                                ThemeModel wpComTheme = wpComIterator.next();
                                if (!ListenerUtil.mutListener.listen(23616)) {
                                    if (StringUtils.equals(wpComTheme.getThemeId(), uploadedTheme.getThemeId().replace("-wpcom", ""))) {
                                        if (!ListenerUtil.mutListener.listen(23615)) {
                                            wpComIterator.remove();
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean shouldShowPremiumThemes() {
        if (!ListenerUtil.mutListener.listen(23619)) {
            if (mSite == null) {
                return false;
            }
        }
        long planId = mSite.getPlanId();
        return (ListenerUtil.mutListener.listen(23622) ? ((ListenerUtil.mutListener.listen(23621) ? ((ListenerUtil.mutListener.listen(23620) ? (planId == PlansConstants.PREMIUM_PLAN_ID && planId == PlansConstants.BUSINESS_PLAN_ID) : (planId == PlansConstants.PREMIUM_PLAN_ID || planId == PlansConstants.BUSINESS_PLAN_ID)) && planId == PlansConstants.JETPACK_PREMIUM_PLAN_ID) : ((ListenerUtil.mutListener.listen(23620) ? (planId == PlansConstants.PREMIUM_PLAN_ID && planId == PlansConstants.BUSINESS_PLAN_ID) : (planId == PlansConstants.PREMIUM_PLAN_ID || planId == PlansConstants.BUSINESS_PLAN_ID)) || planId == PlansConstants.JETPACK_PREMIUM_PLAN_ID)) && planId == PlansConstants.JETPACK_BUSINESS_PLAN_ID) : ((ListenerUtil.mutListener.listen(23621) ? ((ListenerUtil.mutListener.listen(23620) ? (planId == PlansConstants.PREMIUM_PLAN_ID && planId == PlansConstants.BUSINESS_PLAN_ID) : (planId == PlansConstants.PREMIUM_PLAN_ID || planId == PlansConstants.BUSINESS_PLAN_ID)) && planId == PlansConstants.JETPACK_PREMIUM_PLAN_ID) : ((ListenerUtil.mutListener.listen(23620) ? (planId == PlansConstants.PREMIUM_PLAN_ID && planId == PlansConstants.BUSINESS_PLAN_ID) : (planId == PlansConstants.PREMIUM_PLAN_ID || planId == PlansConstants.BUSINESS_PLAN_ID)) || planId == PlansConstants.JETPACK_PREMIUM_PLAN_ID)) || planId == PlansConstants.JETPACK_BUSINESS_PLAN_ID));
    }

    private class ThemeDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            if (!ListenerUtil.mutListener.listen(23623)) {
                updateDisplay();
            }
        }

        @Override
        public void onInvalidated() {
            if (!ListenerUtil.mutListener.listen(23624)) {
                updateDisplay();
            }
        }
    }
}

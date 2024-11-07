package org.wordpress.android.ui.plugins;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.material.appbar.AppBarLayout;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel;
import org.wordpress.android.models.networkresource.ListState;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.util.ActivityUtils;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.extensions.AppBarLayoutExtensionsKt;
import org.wordpress.android.util.ColorUtils;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import org.wordpress.android.viewmodel.plugins.PluginBrowserViewModel;
import org.wordpress.android.viewmodel.plugins.PluginBrowserViewModel.PluginListType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PluginBrowserActivity extends LocaleAwareActivity implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    ImageManager mImageManager;

    private PluginBrowserViewModel mViewModel;

    private RecyclerView mSitePluginsRecycler;

    private RecyclerView mFeaturedPluginsRecycler;

    private RecyclerView mPopularPluginsRecycler;

    private RecyclerView mNewPluginsRecycler;

    private AppBarLayout mAppBar;

    private MenuItem mSearchMenuItem;

    private SearchView mSearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10366)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10367)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(10368)) {
            setContentView(R.layout.plugin_browser_activity);
        }
        if (!ListenerUtil.mutListener.listen(10369)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(PluginBrowserViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(10370)) {
            mSitePluginsRecycler = findViewById(R.id.installed_plugins_recycler);
        }
        if (!ListenerUtil.mutListener.listen(10371)) {
            mFeaturedPluginsRecycler = findViewById(R.id.featured_plugins_recycler);
        }
        if (!ListenerUtil.mutListener.listen(10372)) {
            mPopularPluginsRecycler = findViewById(R.id.popular_plugins_recycler);
        }
        if (!ListenerUtil.mutListener.listen(10373)) {
            mNewPluginsRecycler = findViewById(R.id.new_plugins_recycler);
        }
        if (!ListenerUtil.mutListener.listen(10374)) {
            mAppBar = findViewById(R.id.appbar_main);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(10375)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(10378)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(10376)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(10377)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        SiteModel siteModel = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
        if (!ListenerUtil.mutListener.listen(10381)) {
            if (siteModel == null) {
                if (!ListenerUtil.mutListener.listen(10379)) {
                    ToastUtils.showToast(this, R.string.blog_not_found);
                }
                if (!ListenerUtil.mutListener.listen(10380)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10384)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(10383)) {
                    mViewModel.setSite(siteModel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10382)) {
                    mViewModel.readFromBundle(savedInstanceState);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10385)) {
            mViewModel.start();
        }
        if (!ListenerUtil.mutListener.listen(10386)) {
            // site plugin list
            findViewById(R.id.text_manage).setOnClickListener(v -> showListFragment(PluginListType.SITE));
        }
        if (!ListenerUtil.mutListener.listen(10387)) {
            // featured plugin list
            findViewById(R.id.text_all_featured).setOnClickListener(v -> showListFragment(PluginListType.FEATURED));
        }
        if (!ListenerUtil.mutListener.listen(10388)) {
            // popular plugin list
            findViewById(R.id.text_all_popular).setOnClickListener(v -> showListFragment(PluginListType.POPULAR));
        }
        if (!ListenerUtil.mutListener.listen(10389)) {
            // new plugin list
            findViewById(R.id.text_all_new).setOnClickListener(v -> showListFragment(PluginListType.NEW));
        }
        if (!ListenerUtil.mutListener.listen(10390)) {
            getSupportFragmentManager().addOnBackStackChangedListener(() -> {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    mViewModel.setTitle(getString(R.string.plugins));
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10391)) {
            configureRecycler(mSitePluginsRecycler);
        }
        if (!ListenerUtil.mutListener.listen(10392)) {
            configureRecycler(mFeaturedPluginsRecycler);
        }
        if (!ListenerUtil.mutListener.listen(10393)) {
            configureRecycler(mPopularPluginsRecycler);
        }
        if (!ListenerUtil.mutListener.listen(10394)) {
            configureRecycler(mNewPluginsRecycler);
        }
        if (!ListenerUtil.mutListener.listen(10395)) {
            setupObservers();
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(10396)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(10397)) {
            mViewModel.writeToBundle(outState);
        }
    }

    private void setupObservers() {
        if (!ListenerUtil.mutListener.listen(10398)) {
            mViewModel.getTitle().observe(this, title -> setTitle(title));
        }
        if (!ListenerUtil.mutListener.listen(10399)) {
            mViewModel.getSitePluginsLiveData().observe(this, listState -> {
                if (listState != null) {
                    reloadPluginAdapterAndVisibility(PluginListType.SITE, listState);
                    showProgress(listState.isFetchingFirstPage() && listState.getData().isEmpty());
                    // confusion
                    if (listState instanceof ListState.Error && NetworkUtils.isNetworkAvailable(PluginBrowserActivity.this)) {
                        ToastUtils.showToast(PluginBrowserActivity.this, R.string.plugin_fetch_error);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10400)) {
            mViewModel.getFeaturedPluginsLiveData().observe(this, listState -> {
                if (listState != null) {
                    reloadPluginAdapterAndVisibility(PluginListType.FEATURED, listState);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10401)) {
            mViewModel.getPopularPluginsLiveData().observe(this, listState -> {
                if (listState != null) {
                    reloadPluginAdapterAndVisibility(PluginListType.POPULAR, listState);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10402)) {
            mViewModel.getNewPluginsLiveData().observe(this, listState -> {
                if (listState != null) {
                    reloadPluginAdapterAndVisibility(PluginListType.NEW, listState);
                }
            });
        }
    }

    private void configureRecycler(@NonNull RecyclerView recycler) {
        if (!ListenerUtil.mutListener.listen(10403)) {
            recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }
        if (!ListenerUtil.mutListener.listen(10404)) {
            recycler.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(10405)) {
            recycler.setAdapter(new PluginBrowserAdapter(this));
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(10407)) {
            if (mSearchMenuItem != null) {
                if (!ListenerUtil.mutListener.listen(10406)) {
                    mSearchMenuItem.setOnActionExpandListener(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10409)) {
            if (mSearchView != null) {
                if (!ListenerUtil.mutListener.listen(10408)) {
                    mSearchView.setOnQueryTextListener(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10410)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(10411)) {
            getMenuInflater().inflate(R.menu.search, menu);
        }
        if (!ListenerUtil.mutListener.listen(10412)) {
            mSearchMenuItem = menu.findItem(R.id.menu_search);
        }
        if (!ListenerUtil.mutListener.listen(10413)) {
            mSearchView = (SearchView) mSearchMenuItem.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(10414)) {
            mSearchView.setMaxWidth(Integer.MAX_VALUE);
        }
        PluginListFragment currentFragment = getCurrentFragment();
        if (!ListenerUtil.mutListener.listen(10419)) {
            if ((ListenerUtil.mutListener.listen(10415) ? (currentFragment != null || currentFragment.getListType() == PluginListType.SEARCH) : (currentFragment != null && currentFragment.getListType() == PluginListType.SEARCH))) {
                if (!ListenerUtil.mutListener.listen(10416)) {
                    mSearchMenuItem.expandActionView();
                }
                if (!ListenerUtil.mutListener.listen(10417)) {
                    mSearchView.setQuery(mViewModel.getSearchQuery(), false);
                }
                if (!ListenerUtil.mutListener.listen(10418)) {
                    mSearchView.setOnQueryTextListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10420)) {
            mSearchMenuItem.setOnActionExpandListener(this);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(10422)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(10421)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(10429)) {
            if ((ListenerUtil.mutListener.listen(10427) ? (getSupportFragmentManager().getBackStackEntryCount() >= 0) : (ListenerUtil.mutListener.listen(10426) ? (getSupportFragmentManager().getBackStackEntryCount() <= 0) : (ListenerUtil.mutListener.listen(10425) ? (getSupportFragmentManager().getBackStackEntryCount() < 0) : (ListenerUtil.mutListener.listen(10424) ? (getSupportFragmentManager().getBackStackEntryCount() != 0) : (ListenerUtil.mutListener.listen(10423) ? (getSupportFragmentManager().getBackStackEntryCount() == 0) : (getSupportFragmentManager().getBackStackEntryCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(10428)) {
                    // update the lift on scroll target id when we return to the root fragment
                    AppBarLayoutExtensionsKt.setLiftOnScrollTargetViewIdAndRequestLayout(mAppBar, R.id.scroll_view);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10430)) {
            super.onBackPressed();
        }
    }

    private void reloadPluginAdapterAndVisibility(@NonNull PluginListType pluginType, @Nullable ListState<ImmutablePluginModel> listState) {
        if (!ListenerUtil.mutListener.listen(10431)) {
            if (listState == null) {
                return;
            }
        }
        PluginBrowserAdapter adapter = null;
        View cardView = null;
        if (!ListenerUtil.mutListener.listen(10440)) {
            switch(pluginType) {
                case SITE:
                    if (!ListenerUtil.mutListener.listen(10432)) {
                        adapter = (PluginBrowserAdapter) mSitePluginsRecycler.getAdapter();
                    }
                    if (!ListenerUtil.mutListener.listen(10433)) {
                        cardView = findViewById(R.id.installed_plugins_container);
                    }
                    break;
                case FEATURED:
                    if (!ListenerUtil.mutListener.listen(10434)) {
                        adapter = (PluginBrowserAdapter) mFeaturedPluginsRecycler.getAdapter();
                    }
                    if (!ListenerUtil.mutListener.listen(10435)) {
                        cardView = findViewById(R.id.featured_plugins_container);
                    }
                    break;
                case POPULAR:
                    if (!ListenerUtil.mutListener.listen(10436)) {
                        adapter = (PluginBrowserAdapter) mPopularPluginsRecycler.getAdapter();
                    }
                    if (!ListenerUtil.mutListener.listen(10437)) {
                        cardView = findViewById(R.id.popular_plugins_container);
                    }
                    break;
                case NEW:
                    if (!ListenerUtil.mutListener.listen(10438)) {
                        adapter = (PluginBrowserAdapter) mNewPluginsRecycler.getAdapter();
                    }
                    if (!ListenerUtil.mutListener.listen(10439)) {
                        cardView = findViewById(R.id.new_plugins_container);
                    }
                    break;
                case SEARCH:
                    return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10442)) {
            if ((ListenerUtil.mutListener.listen(10441) ? (adapter == null && cardView == null) : (adapter == null || cardView == null))) {
                return;
            }
        }
        List<ImmutablePluginModel> plugins = listState.getData();
        if (!ListenerUtil.mutListener.listen(10443)) {
            adapter.setPlugins(plugins);
        }
        int newVisibility = (ListenerUtil.mutListener.listen(10448) ? (plugins.size() >= 0) : (ListenerUtil.mutListener.listen(10447) ? (plugins.size() <= 0) : (ListenerUtil.mutListener.listen(10446) ? (plugins.size() < 0) : (ListenerUtil.mutListener.listen(10445) ? (plugins.size() != 0) : (ListenerUtil.mutListener.listen(10444) ? (plugins.size() == 0) : (plugins.size() > 0)))))) ? View.VISIBLE : View.GONE;
        int oldVisibility = cardView.getVisibility();
        if (!ListenerUtil.mutListener.listen(10453)) {
            if ((ListenerUtil.mutListener.listen(10449) ? (newVisibility == View.VISIBLE || oldVisibility != View.VISIBLE) : (newVisibility == View.VISIBLE && oldVisibility != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(10452)) {
                    AniUtils.fadeIn(cardView, AniUtils.Duration.MEDIUM);
                }
            } else if ((ListenerUtil.mutListener.listen(10450) ? (newVisibility != View.VISIBLE || oldVisibility == View.VISIBLE) : (newVisibility != View.VISIBLE && oldVisibility == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(10451)) {
                    AniUtils.fadeOut(cardView, AniUtils.Duration.MEDIUM);
                }
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!ListenerUtil.mutListener.listen(10455)) {
            if (mSearchView != null) {
                if (!ListenerUtil.mutListener.listen(10454)) {
                    mSearchView.clearFocus();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10456)) {
            ActivityUtils.hideKeyboard(this);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (!ListenerUtil.mutListener.listen(10457)) {
            mViewModel.setSearchQuery(query != null ? query : "");
        }
        return true;
    }

    private void showListFragment(@NonNull PluginListType listType) {
        if (!ListenerUtil.mutListener.listen(10458)) {
            AppBarLayoutExtensionsKt.setLiftOnScrollTargetViewIdAndRequestLayout(mAppBar, R.id.recycler);
        }
        PluginListFragment listFragment = PluginListFragment.newInstance(mViewModel.getSite(), listType);
        if (!ListenerUtil.mutListener.listen(10459)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment, PluginListFragment.TAG).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }
        if (!ListenerUtil.mutListener.listen(10460)) {
            mViewModel.setTitle(getTitleForListType(listType));
        }
        if (!ListenerUtil.mutListener.listen(10461)) {
            trackPluginListOpened(listType);
        }
    }

    @Nullable
    private PluginListFragment getCurrentFragment() {
        return (PluginListFragment) getSupportFragmentManager().findFragmentByTag(PluginListFragment.TAG);
    }

    private void hideListFragment() {
        if (!ListenerUtil.mutListener.listen(10468)) {
            if ((ListenerUtil.mutListener.listen(10466) ? (getSupportFragmentManager().getBackStackEntryCount() >= 0) : (ListenerUtil.mutListener.listen(10465) ? (getSupportFragmentManager().getBackStackEntryCount() <= 0) : (ListenerUtil.mutListener.listen(10464) ? (getSupportFragmentManager().getBackStackEntryCount() < 0) : (ListenerUtil.mutListener.listen(10463) ? (getSupportFragmentManager().getBackStackEntryCount() != 0) : (ListenerUtil.mutListener.listen(10462) ? (getSupportFragmentManager().getBackStackEntryCount() == 0) : (getSupportFragmentManager().getBackStackEntryCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(10467)) {
                    onBackPressed();
                }
            }
        }
    }

    private void showProgress(boolean show) {
        if (!ListenerUtil.mutListener.listen(10469)) {
            findViewById(R.id.progress).setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        if (!ListenerUtil.mutListener.listen(10470)) {
            showListFragment(PluginListType.SEARCH);
        }
        if (!ListenerUtil.mutListener.listen(10471)) {
            mSearchView.setOnQueryTextListener(this);
        }
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        if (!ListenerUtil.mutListener.listen(10472)) {
            mSearchView.setOnQueryTextListener(null);
        }
        if (!ListenerUtil.mutListener.listen(10473)) {
            hideListFragment();
        }
        if (!ListenerUtil.mutListener.listen(10474)) {
            mViewModel.setSearchQuery("");
        }
        return true;
    }

    private class PluginBrowserAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final PluginList mItems = new PluginList();

        private final LayoutInflater mLayoutInflater;

        PluginBrowserAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
            if (!ListenerUtil.mutListener.listen(10475)) {
                setHasStableIds(true);
            }
        }

        void setPlugins(@NonNull List<ImmutablePluginModel> items) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(mViewModel.getDiffCallback(mItems, items));
            if (!ListenerUtil.mutListener.listen(10476)) {
                mItems.clear();
            }
            if (!ListenerUtil.mutListener.listen(10477)) {
                mItems.addAll(items);
            }
            if (!ListenerUtil.mutListener.listen(10478)) {
                diffResult.dispatchUpdatesTo(this);
            }
        }

        @Nullable
        private Object getItem(int position) {
            return mItems.getItem(position);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public long getItemId(int position) {
            return mItems.getItemId(position);
        }

        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.plugin_browser_row, parent, false);
            return new PluginBrowserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NotNull ViewHolder viewHolder, int position) {
            PluginBrowserViewHolder holder = (PluginBrowserViewHolder) viewHolder;
            ImmutablePluginModel plugin = (ImmutablePluginModel) getItem(position);
            if (!ListenerUtil.mutListener.listen(10479)) {
                if (plugin == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(10480)) {
                holder.mNameText.setText(plugin.getDisplayName());
            }
            if (!ListenerUtil.mutListener.listen(10481)) {
                holder.mAuthorText.setText(plugin.getAuthorName());
            }
            if (!ListenerUtil.mutListener.listen(10482)) {
                mImageManager.load(holder.mIcon, ImageType.PLUGIN, StringUtils.notNullStr(plugin.getIcon()));
            }
            if (!ListenerUtil.mutListener.listen(10492)) {
                if (plugin.isInstalled()) {
                    @StringRes
                    int textResId;
                    @ColorRes
                    int colorResId;
                    @DrawableRes
                    int drawableResId;
                    boolean isAutoManaged = PluginUtils.isAutoManaged(mViewModel.getSite(), plugin);
                    if (isAutoManaged) {
                        textResId = R.string.plugin_auto_managed;
                        colorResId = ContextExtensionsKt.getColorResIdFromAttribute(holder.mStatusIcon.getContext(), R.attr.wpColorSuccess);
                        drawableResId = android.R.color.transparent;
                    } else if (PluginUtils.isUpdateAvailable(plugin)) {
                        textResId = R.string.plugin_needs_update;
                        colorResId = ContextExtensionsKt.getColorResIdFromAttribute(holder.mStatusIcon.getContext(), R.attr.wpColorWarningDark);
                        drawableResId = R.drawable.ic_sync_white_24dp;
                    } else if (plugin.isActive()) {
                        textResId = R.string.plugin_active;
                        colorResId = ContextExtensionsKt.getColorResIdFromAttribute(holder.mStatusIcon.getContext(), R.attr.wpColorSuccess);
                        drawableResId = R.drawable.ic_checkmark_white_24dp;
                    } else {
                        textResId = R.string.plugin_inactive;
                        colorResId = ContextExtensionsKt.getColorResIdFromAttribute(holder.mStatusIcon.getContext(), R.attr.wpColorOnSurfaceMedium);
                        drawableResId = R.drawable.ic_cross_white_24dp;
                    }
                    if (!ListenerUtil.mutListener.listen(10486)) {
                        holder.mStatusText.setText(textResId);
                    }
                    if (!ListenerUtil.mutListener.listen(10487)) {
                        holder.mStatusText.setTextColor(AppCompatResources.getColorStateList(holder.mStatusText.getContext(), colorResId));
                    }
                    if (!ListenerUtil.mutListener.listen(10488)) {
                        holder.mStatusIcon.setVisibility(isAutoManaged ? View.GONE : View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(10489)) {
                        ColorUtils.INSTANCE.setImageResourceWithTint(holder.mStatusIcon, drawableResId, colorResId);
                    }
                    if (!ListenerUtil.mutListener.listen(10490)) {
                        holder.mStatusContainer.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(10491)) {
                        holder.mRatingBar.setVisibility(View.GONE);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(10483)) {
                        holder.mStatusContainer.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(10484)) {
                        holder.mRatingBar.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(10485)) {
                        holder.mRatingBar.setRating(plugin.getAverageStarRating());
                    }
                }
            }
        }

        private class PluginBrowserViewHolder extends ViewHolder {

            private final TextView mNameText;

            private final TextView mAuthorText;

            private final ViewGroup mStatusContainer;

            private final TextView mStatusText;

            private final ImageView mStatusIcon;

            private final ImageView mIcon;

            private final RatingBar mRatingBar;

            PluginBrowserViewHolder(View view) {
                super(view);
                mNameText = view.findViewById(R.id.plugin_name);
                mAuthorText = view.findViewById(R.id.plugin_author);
                mIcon = view.findViewById(R.id.plugin_icon);
                mRatingBar = view.findViewById(R.id.rating_bar);
                mStatusContainer = view.findViewById(R.id.plugin_status_container);
                mStatusText = mStatusContainer.findViewById(R.id.plugin_status_text);
                mStatusIcon = mStatusContainer.findViewById(R.id.plugin_status_icon);
                if (!ListenerUtil.mutListener.listen(10493)) {
                    view.setOnClickListener(v -> {
                        int position = getAdapterPosition();
                        ImmutablePluginModel plugin = (ImmutablePluginModel) getItem(position);
                        if (plugin == null) {
                            return;
                        }
                        ActivityLauncher.viewPluginDetail(PluginBrowserActivity.this, mViewModel.getSite(), plugin.getSlug());
                    });
                }
            }
        }
    }

    private String getTitleForListType(@NonNull PluginListType pluginListType) {
        if (!ListenerUtil.mutListener.listen(10494)) {
            switch(pluginListType) {
                case FEATURED:
                    return getString(R.string.plugin_caption_featured);
                case POPULAR:
                    return getString(R.string.plugin_caption_popular);
                case NEW:
                    return getString(R.string.plugin_caption_new);
                case SEARCH:
                    return getString(R.string.plugin_caption_search);
                case SITE:
                    return getString(R.string.plugin_caption_installed);
            }
        }
        return getString(R.string.plugins);
    }

    private void trackPluginListOpened(PluginListType listType) {
        if (!ListenerUtil.mutListener.listen(10496)) {
            if (listType == PluginListType.SEARCH) {
                if (!ListenerUtil.mutListener.listen(10495)) {
                    // Although it's named as "search performed" we are actually only tracking the first search
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.PLUGIN_SEARCH_PERFORMED, mViewModel.getSite());
                }
                return;
            }
        }
        Map<String, Object> properties = new HashMap<>();
        String type = null;
        if (!ListenerUtil.mutListener.listen(10501)) {
            switch(listType) {
                case SITE:
                    if (!ListenerUtil.mutListener.listen(10497)) {
                        type = "installed";
                    }
                    break;
                case FEATURED:
                    if (!ListenerUtil.mutListener.listen(10498)) {
                        type = "featured";
                    }
                    break;
                case POPULAR:
                    if (!ListenerUtil.mutListener.listen(10499)) {
                        type = "popular";
                    }
                    break;
                case NEW:
                    if (!ListenerUtil.mutListener.listen(10500)) {
                        type = "newest";
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(10502)) {
            properties.put("type", type);
        }
        if (!ListenerUtil.mutListener.listen(10503)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_PLUGIN_LIST, mViewModel.getSite(), properties);
        }
    }
}

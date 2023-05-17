package org.wordpress.android.ui.prefs;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.apache.commons.text.StringEscapeUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.annotations.action.Action;
import org.wordpress.android.fluxc.generated.TaxonomyActionBuilder;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.TermModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.TaxonomyStore;
import org.wordpress.android.fluxc.store.TaxonomyStore.OnTaxonomyChanged;
import org.wordpress.android.ui.ActionableEmptyView;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.util.ActivityUtils;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SiteSettingsTagListActivity extends LocaleAwareActivity implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener, SiteSettingsTagDetailFragment.OnTagDetailListener {

    @Inject
    Dispatcher mDispatcher;

    @Inject
    SiteStore mSiteStore;

    @Inject
    TaxonomyStore mTaxonomyStore;

    private static final String KEY_SAVED_QUERY = "SAVED_QUERY";

    private static final String KEY_PROGRESS_RES_ID = "PROGRESS_RESOURCE_ID";

    private static final String KEY_IS_SEARCHING = "IS_SEARCHING";

    private SiteModel mSite;

    private EmptyViewRecyclerView mRecycler;

    private View mFabView;

    private ActionableEmptyView mActionableEmptyView;

    private TagListAdapter mAdapter;

    private String mSavedQuery;

    private int mLastProgressResId;

    private MenuItem mSearchMenuItem;

    private SearchView mSearchView;

    private ProgressDialog mProgressDialog;

    private boolean mIsSearching;

    public static void showTagList(@NonNull Context context, @NonNull SiteModel site) {
        Intent intent = new Intent(context, SiteSettingsTagListActivity.class);
        if (!ListenerUtil.mutListener.listen(16254)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(16255)) {
            context.startActivity(intent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(16256)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(16257)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(16258)) {
            setContentView(R.layout.site_settings_tag_list_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(16259)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(16262)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(16260)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(16261)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16267)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(16266)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16263)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(16264)) {
                    mSavedQuery = savedInstanceState.getString(KEY_SAVED_QUERY);
                }
                if (!ListenerUtil.mutListener.listen(16265)) {
                    mIsSearching = savedInstanceState.getBoolean(KEY_IS_SEARCHING);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16270)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(16268)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(16269)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16271)) {
            mFabView = findViewById(R.id.fab_button);
        }
        if (!ListenerUtil.mutListener.listen(16272)) {
            mFabView.setOnClickListener(view -> showDetailFragment(null));
        }
        if (!ListenerUtil.mutListener.listen(16273)) {
            mFabView.setOnLongClickListener(view -> {
                if (view.isHapticFeedbackEnabled()) {
                    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                }
                Toast.makeText(view.getContext(), R.string.site_settings_tags_empty_button, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(16274)) {
            ViewExtensionsKt.redirectContextClickToLongPressListener(mFabView);
        }
        if (!ListenerUtil.mutListener.listen(16276)) {
            // hide the FAB the first time the fragment is created in order to animate it in onResume()
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(16275)) {
                    mFabView.setVisibility(View.INVISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16277)) {
            mActionableEmptyView = findViewById(R.id.actionable_empty_view);
        }
        if (!ListenerUtil.mutListener.listen(16278)) {
            mActionableEmptyView.button.setOnClickListener(view -> showDetailFragment(null));
        }
        if (!ListenerUtil.mutListener.listen(16279)) {
            mRecycler = findViewById(R.id.tags_recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(16280)) {
            mRecycler.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(16281)) {
            mRecycler.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(16282)) {
            mRecycler.setEmptyView(mActionableEmptyView);
        }
        if (!ListenerUtil.mutListener.listen(16283)) {
            loadTags();
        }
        if (!ListenerUtil.mutListener.listen(16289)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(16288)) {
                    mDispatcher.dispatch(TaxonomyActionBuilder.newFetchTagsAction(mSite));
                }
            } else {
                SiteSettingsTagDetailFragment fragment = getDetailFragment();
                if (!ListenerUtil.mutListener.listen(16285)) {
                    if (fragment != null) {
                        if (!ListenerUtil.mutListener.listen(16284)) {
                            fragment.setOnTagDetailListener(this);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16287)) {
                    if (savedInstanceState.containsKey(KEY_PROGRESS_RES_ID)) {
                        @StringRes
                        int messageId = savedInstanceState.getInt(KEY_PROGRESS_RES_ID);
                        if (!ListenerUtil.mutListener.listen(16286)) {
                            showProgressDialog(messageId);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(16290)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(16291)) {
            showFabWithConditions();
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(16292)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(16293)) {
            mDispatcher.register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(16294)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(16295)) {
            super.onStop();
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(16296)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(16297)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(16298)) {
            outState.putBoolean(KEY_IS_SEARCHING, mIsSearching);
        }
        if (!ListenerUtil.mutListener.listen(16300)) {
            if (mSearchMenuItem.isActionViewExpanded()) {
                if (!ListenerUtil.mutListener.listen(16299)) {
                    outState.putString(KEY_SAVED_QUERY, mSearchView.getQuery().toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16303)) {
            if ((ListenerUtil.mutListener.listen(16301) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(16302)) {
                    outState.putInt(KEY_PROGRESS_RES_ID, mLastProgressResId);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(16304)) {
            getMenuInflater().inflate(R.menu.tag_list, menu);
        }
        if (!ListenerUtil.mutListener.listen(16305)) {
            mSearchMenuItem = menu.findItem(R.id.menu_search);
        }
        if (!ListenerUtil.mutListener.listen(16306)) {
            mSearchMenuItem.setOnActionExpandListener(this);
        }
        if (!ListenerUtil.mutListener.listen(16307)) {
            mSearchView = (SearchView) mSearchMenuItem.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(16308)) {
            mSearchView.setOnQueryTextListener(this);
        }
        if (!ListenerUtil.mutListener.listen(16309)) {
            mSearchView.setMaxWidth(Integer.MAX_VALUE);
        }
        if (!ListenerUtil.mutListener.listen(16313)) {
            // open search bar if we were searching for something before
            if (mIsSearching) {
                if (!ListenerUtil.mutListener.listen(16310)) {
                    mSearchMenuItem.expandActionView();
                }
                if (!ListenerUtil.mutListener.listen(16311)) {
                    mSearchView.clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(16312)) {
                    mSearchView.setQuery(mSavedQuery, true);
                }
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (!ListenerUtil.mutListener.listen(16314)) {
                onBackPressed();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(16326)) {
            if ((ListenerUtil.mutListener.listen(16319) ? (getFragmentManager().getBackStackEntryCount() >= 0) : (ListenerUtil.mutListener.listen(16318) ? (getFragmentManager().getBackStackEntryCount() <= 0) : (ListenerUtil.mutListener.listen(16317) ? (getFragmentManager().getBackStackEntryCount() < 0) : (ListenerUtil.mutListener.listen(16316) ? (getFragmentManager().getBackStackEntryCount() != 0) : (ListenerUtil.mutListener.listen(16315) ? (getFragmentManager().getBackStackEntryCount() == 0) : (getFragmentManager().getBackStackEntryCount() > 0))))))) {
                SiteSettingsTagDetailFragment fragment = getDetailFragment();
                if (!ListenerUtil.mutListener.listen(16325)) {
                    if ((ListenerUtil.mutListener.listen(16321) ? (fragment != null || fragment.hasChanges()) : (fragment != null && fragment.hasChanges()))) {
                        if (!ListenerUtil.mutListener.listen(16324)) {
                            saveTag(fragment.getTerm(), fragment.isNewTerm());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(16322)) {
                            hideDetailFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(16323)) {
                            loadTags();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16320)) {
                    super.onBackPressed();
                }
            }
        }
    }

    private void showFabIfHidden() {
        // redisplay hidden fab after a short delay
        long delayMs = getResources().getInteger(R.integer.fab_animation_delay);
        if (!ListenerUtil.mutListener.listen(16327)) {
            new Handler().postDelayed(() -> {
                if (!isFinishing() && mFabView.getVisibility() != View.VISIBLE) {
                    AniUtils.scaleIn(mFabView, AniUtils.Duration.MEDIUM);
                }
            }, delayMs);
        }
    }

    private void showFabWithConditions() {
        if (!ListenerUtil.mutListener.listen(16329)) {
            // scale in the fab after a brief delay if it's not already showing
            if (mFabView.getVisibility() != View.VISIBLE) {
                long delayMs = getResources().getInteger(R.integer.fab_animation_delay);
                if (!ListenerUtil.mutListener.listen(16328)) {
                    new Handler().postDelayed(() -> {
                        if (!mIsSearching && !isDetailFragmentShowing() && mActionableEmptyView.getVisibility() != View.VISIBLE) {
                            showFabIfHidden();
                        }
                    }, delayMs);
                }
            }
        }
    }

    private void hideFabIfShowing() {
        if (!ListenerUtil.mutListener.listen(16332)) {
            if ((ListenerUtil.mutListener.listen(16330) ? (!isFinishing() || mFabView.getVisibility() == View.VISIBLE) : (!isFinishing() && mFabView.getVisibility() == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(16331)) {
                    AniUtils.scaleOut(mFabView, AniUtils.Duration.SHORT);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTaxonomyChanged(OnTaxonomyChanged event) {
        if (!ListenerUtil.mutListener.listen(16334)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(16333)) {
                    AppLog.e(AppLog.T.SETTINGS, event.error.message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16341)) {
            switch(event.causeOfChange) {
                case FETCH_TAGS:
                    if (!ListenerUtil.mutListener.listen(16336)) {
                        if (!event.isError()) {
                            if (!ListenerUtil.mutListener.listen(16335)) {
                                loadTags();
                            }
                        }
                    }
                    break;
                case REMOVE_TERM:
                case UPDATE_TERM:
                    if (!ListenerUtil.mutListener.listen(16337)) {
                        hideProgressDialog();
                    }
                    if (!ListenerUtil.mutListener.listen(16338)) {
                        hideDetailFragment();
                    }
                    if (!ListenerUtil.mutListener.listen(16340)) {
                        if (!event.isError()) {
                            if (!ListenerUtil.mutListener.listen(16339)) {
                                loadTags();
                            }
                        }
                    }
                    break;
            }
        }
    }

    private void loadTags() {
        List<TermModel> tags = mTaxonomyStore.getTagsForSite(mSite);
        if (!ListenerUtil.mutListener.listen(16342)) {
            Collections.sort(tags, (t1, t2) -> StringUtils.compareIgnoreCase(t1.getName(), t2.getName()));
        }
        if (!ListenerUtil.mutListener.listen(16343)) {
            mAdapter = new TagListAdapter(tags);
        }
        if (!ListenerUtil.mutListener.listen(16344)) {
            mRecycler.setAdapter(mAdapter);
        }
    }

    private SiteSettingsTagDetailFragment getDetailFragment() {
        return (SiteSettingsTagDetailFragment) getFragmentManager().findFragmentByTag(SiteSettingsTagDetailFragment.TAG);
    }

    /*
     * shows the detail (edit) view for the passed term, or adds a new term is passed term is null
     */
    private void showDetailFragment(@Nullable TermModel term) {
        SiteSettingsTagDetailFragment fragment = SiteSettingsTagDetailFragment.newInstance(term);
        if (!ListenerUtil.mutListener.listen(16345)) {
            fragment.setOnTagDetailListener(this);
        }
        if (!ListenerUtil.mutListener.listen(16346)) {
            getFragmentManager().beginTransaction().add(R.id.container, fragment, SiteSettingsTagDetailFragment.TAG).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commitAllowingStateLoss();
        }
        if (!ListenerUtil.mutListener.listen(16347)) {
            mSearchMenuItem.collapseActionView();
        }
        if (!ListenerUtil.mutListener.listen(16348)) {
            mFabView.setVisibility(View.GONE);
        }
    }

    private boolean isDetailFragmentShowing() {
        return getDetailFragment() != null;
    }

    private void hideDetailFragment() {
        SiteSettingsTagDetailFragment fragment = getDetailFragment();
        if (!ListenerUtil.mutListener.listen(16354)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(16349)) {
                    getFragmentManager().popBackStack();
                }
                if (!ListenerUtil.mutListener.listen(16350)) {
                    ActivityUtils.hideKeyboard(this);
                }
                if (!ListenerUtil.mutListener.listen(16351)) {
                    showFabWithConditions();
                }
                if (!ListenerUtil.mutListener.listen(16352)) {
                    setTitle(R.string.site_settings_tags_title);
                }
                if (!ListenerUtil.mutListener.listen(16353)) {
                    invalidateOptionsMenu();
                }
            }
        }
    }

    private void showActionableEmptyViewForSearch(boolean isSearch) {
        if (!ListenerUtil.mutListener.listen(16355)) {
            mActionableEmptyView.updateLayoutForSearch(isSearch, 0);
        }
        if (!ListenerUtil.mutListener.listen(16364)) {
            if (isSearch) {
                if (!ListenerUtil.mutListener.listen(16360)) {
                    mActionableEmptyView.button.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(16361)) {
                    mActionableEmptyView.image.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(16362)) {
                    mActionableEmptyView.subtitle.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(16363)) {
                    mActionableEmptyView.title.setText(R.string.site_settings_tags_empty_title_search);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16356)) {
                    mActionableEmptyView.button.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(16357)) {
                    mActionableEmptyView.image.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(16358)) {
                    mActionableEmptyView.subtitle.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(16359)) {
                    mActionableEmptyView.title.setText(R.string.site_settings_tags_empty_title);
                }
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!ListenerUtil.mutListener.listen(16365)) {
            mAdapter.filter(query);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (!ListenerUtil.mutListener.listen(16366)) {
            mAdapter.filter(query);
        }
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(16367)) {
            mIsSearching = true;
        }
        if (!ListenerUtil.mutListener.listen(16368)) {
            showActionableEmptyViewForSearch(true);
        }
        if (!ListenerUtil.mutListener.listen(16369)) {
            hideFabIfShowing();
        }
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(16370)) {
            mIsSearching = false;
        }
        if (!ListenerUtil.mutListener.listen(16371)) {
            showActionableEmptyViewForSearch(false);
        }
        if (!ListenerUtil.mutListener.listen(16372)) {
            showFabWithConditions();
        }
        return true;
    }

    private void showProgressDialog(@StringRes int messageId) {
        if (!ListenerUtil.mutListener.listen(16373)) {
            mProgressDialog = new ProgressDialog(this);
        }
        if (!ListenerUtil.mutListener.listen(16374)) {
            mProgressDialog.setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(16375)) {
            mProgressDialog.setIndeterminate(true);
        }
        if (!ListenerUtil.mutListener.listen(16376)) {
            mProgressDialog.setMessage(getString(messageId));
        }
        if (!ListenerUtil.mutListener.listen(16377)) {
            mProgressDialog.show();
        }
        if (!ListenerUtil.mutListener.listen(16378)) {
            mLastProgressResId = messageId;
        }
    }

    private void hideProgressDialog() {
        if (!ListenerUtil.mutListener.listen(16381)) {
            if ((ListenerUtil.mutListener.listen(16379) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(16380)) {
                    mProgressDialog.dismiss();
                }
            }
        }
    }

    @Override
    public void onRequestDeleteTag(@NonNull TermModel term) {
        if (!ListenerUtil.mutListener.listen(16383)) {
            if (NetworkUtils.checkConnection(this)) {
                if (!ListenerUtil.mutListener.listen(16382)) {
                    confirmDeleteTag(term);
                }
            }
        }
    }

    private void confirmDeleteTag(@NonNull final TermModel term) {
        String message = String.format(getString(R.string.dlg_confirm_delete_tag), term.getName());
        AlertDialog.Builder dialogBuilder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(16384)) {
            dialogBuilder.setMessage(message);
        }
        if (!ListenerUtil.mutListener.listen(16385)) {
            dialogBuilder.setPositiveButton(getResources().getText(R.string.delete_yes), (dialog, whichButton) -> {
                showProgressDialog(R.string.dlg_deleting_tag);
                Action action = TaxonomyActionBuilder.newDeleteTermAction(new TaxonomyStore.RemoteTermPayload(term, mSite));
                mDispatcher.dispatch(action);
            });
        }
        if (!ListenerUtil.mutListener.listen(16386)) {
            dialogBuilder.setNegativeButton(R.string.cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(16387)) {
            dialogBuilder.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(16388)) {
            dialogBuilder.create().show();
        }
    }

    private void saveTag(@NonNull TermModel term, boolean isNewTerm) {
        if (!ListenerUtil.mutListener.listen(16391)) {
            if ((ListenerUtil.mutListener.listen(16389) ? (isNewTerm || tagExists(term.getName())) : (isNewTerm && tagExists(term.getName())))) {
                if (!ListenerUtil.mutListener.listen(16390)) {
                    ToastUtils.showToast(this, R.string.error_tag_exists);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16392)) {
            showProgressDialog(R.string.dlg_saving_tag);
        }
        Action action = TaxonomyActionBuilder.newPushTermAction(new TaxonomyStore.RemoteTermPayload(term, mSite));
        if (!ListenerUtil.mutListener.listen(16393)) {
            mDispatcher.dispatch(action);
        }
    }

    private boolean tagExists(@NonNull String termName) {
        List<TermModel> terms = mTaxonomyStore.getTagsForSite(mSite);
        if (!ListenerUtil.mutListener.listen(16395)) {
            {
                long _loopCounter267 = 0;
                for (TermModel term : terms) {
                    ListenerUtil.loopListener.listen("_loopCounter267", ++_loopCounter267);
                    if (!ListenerUtil.mutListener.listen(16394)) {
                        if (termName.equalsIgnoreCase(term.getName())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private class TagListAdapter extends RecyclerView.Adapter<TagListAdapter.TagViewHolder> {

        private final List<TermModel> mAllTags = new ArrayList<>();

        private final List<TermModel> mFilteredTags = new ArrayList<>();

        TagListAdapter(@NonNull List<TermModel> allTags) {
            if (!ListenerUtil.mutListener.listen(16396)) {
                mAllTags.addAll(allTags);
            }
            if (!ListenerUtil.mutListener.listen(16397)) {
                mFilteredTags.addAll(allTags);
            }
        }

        @NonNull
        @Override
        public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_settings_tag_list_row, parent, false);
            return new TagListAdapter.TagViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final TagListAdapter.TagViewHolder holder, int position) {
            TermModel term = mFilteredTags.get(position);
            if (!ListenerUtil.mutListener.listen(16398)) {
                holder.mTxtTag.setText(StringEscapeUtils.unescapeHtml4(term.getName()));
            }
            if (!ListenerUtil.mutListener.listen(16407)) {
                if ((ListenerUtil.mutListener.listen(16403) ? (term.getPostCount() >= 0) : (ListenerUtil.mutListener.listen(16402) ? (term.getPostCount() <= 0) : (ListenerUtil.mutListener.listen(16401) ? (term.getPostCount() < 0) : (ListenerUtil.mutListener.listen(16400) ? (term.getPostCount() != 0) : (ListenerUtil.mutListener.listen(16399) ? (term.getPostCount() == 0) : (term.getPostCount() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(16405)) {
                        holder.mTxtCount.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(16406)) {
                        holder.mTxtCount.setText(String.valueOf(term.getPostCount()));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(16404)) {
                        holder.mTxtCount.setVisibility(View.GONE);
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            return mFilteredTags.size();
        }

        public void filter(final String text) {
            if (!ListenerUtil.mutListener.listen(16408)) {
                mFilteredTags.clear();
            }
            if (!ListenerUtil.mutListener.listen(16413)) {
                if (TextUtils.isEmpty(text)) {
                    if (!ListenerUtil.mutListener.listen(16412)) {
                        mFilteredTags.addAll(mAllTags);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(16411)) {
                        {
                            long _loopCounter268 = 0;
                            for (TermModel tag : mAllTags) {
                                ListenerUtil.loopListener.listen("_loopCounter268", ++_loopCounter268);
                                if (!ListenerUtil.mutListener.listen(16410)) {
                                    if (tag.getName().toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))) {
                                        if (!ListenerUtil.mutListener.listen(16409)) {
                                            mFilteredTags.add(tag);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(16414)) {
                notifyDataSetChanged();
            }
            if (!ListenerUtil.mutListener.listen(16416)) {
                showActionableEmptyViewForSearch((ListenerUtil.mutListener.listen(16415) ? (mIsSearching || mFilteredTags.isEmpty()) : (mIsSearching && mFilteredTags.isEmpty())));
            }
        }

        class TagViewHolder extends RecyclerView.ViewHolder {

            private final TextView mTxtTag;

            private final TextView mTxtCount;

            TagViewHolder(View view) {
                super(view);
                mTxtTag = view.findViewById(R.id.text_tag);
                mTxtCount = view.findViewById(R.id.text_count);
                if (!ListenerUtil.mutListener.listen(16417)) {
                    view.setOnClickListener(view1 -> {
                        if (!isDetailFragmentShowing()) {
                            int position = getAdapterPosition();
                            showDetailFragment(mFilteredTags.get(position));
                        }
                    });
                }
            }
        }
    }
}

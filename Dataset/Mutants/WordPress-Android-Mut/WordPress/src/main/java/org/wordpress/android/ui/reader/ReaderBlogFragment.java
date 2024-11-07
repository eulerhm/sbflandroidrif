package org.wordpress.android.ui.reader;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.models.ReaderBlog;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.ui.ActionableEmptyView;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.reader.adapters.ReaderBlogAdapter;
import org.wordpress.android.ui.reader.adapters.ReaderBlogAdapter.ReaderBlogType;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.ui.reader.views.ReaderRecyclerView;
import org.wordpress.android.util.AppLog;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * fragment hosted by ReaderSubsActivity which shows followed blogs
 */
public class ReaderBlogFragment extends Fragment implements ReaderBlogAdapter.BlogClickListener {

    private ReaderRecyclerView mRecyclerView;

    private ReaderBlogAdapter mAdapter;

    private ReaderBlogType mBlogType;

    private String mSearchFilter;

    private boolean mIgnoreNextSearch;

    @Inject
    ReaderTracker mReaderTracker;

    private static final String ARG_BLOG_TYPE = "blog_type";

    private static final String KEY_SEARCH_FILTER = "search_filter";

    static ReaderBlogFragment newInstance(ReaderBlogType blogType) {
        if (!ListenerUtil.mutListener.listen(20463)) {
            AppLog.d(AppLog.T.READER, "reader blog fragment > newInstance");
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(20464)) {
            args.putSerializable(ARG_BLOG_TYPE, blogType);
        }
        ReaderBlogFragment fragment = new ReaderBlogFragment();
        if (!ListenerUtil.mutListener.listen(20465)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        if (!ListenerUtil.mutListener.listen(20466)) {
            super.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(20467)) {
            restoreState(args);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(20468)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(20469)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(20473)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(20470)) {
                    AppLog.d(AppLog.T.READER, "reader blog fragment > restoring instance state");
                }
                if (!ListenerUtil.mutListener.listen(20471)) {
                    mIgnoreNextSearch = true;
                }
                if (!ListenerUtil.mutListener.listen(20472)) {
                    restoreState(savedInstanceState);
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reader_fragment_list, container, false);
        if (!ListenerUtil.mutListener.listen(20474)) {
            mRecyclerView = view.findViewById(R.id.recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(20475)) {
            // options menu (with search) only appears for followed blogs
            setHasOptionsMenu(getBlogType() == ReaderBlogType.FOLLOWED);
        }
        return view;
    }

    private void checkEmptyView() {
        if (!ListenerUtil.mutListener.listen(20477)) {
            if ((ListenerUtil.mutListener.listen(20476) ? (!isAdded() && getView() == null) : (!isAdded() || getView() == null))) {
                return;
            }
        }
        ActionableEmptyView actionableEmptyView = getView().findViewById(R.id.actionable_empty_view);
        if (!ListenerUtil.mutListener.listen(20478)) {
            if (actionableEmptyView == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20503)) {
            if ((ListenerUtil.mutListener.listen(20479) ? (hasBlogAdapter() || getBlogAdapter().isEmpty()) : (hasBlogAdapter() && getBlogAdapter().isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(20481)) {
                    actionableEmptyView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(20482)) {
                    actionableEmptyView.image.setImageResource(R.drawable.img_illustration_following_empty_results_196dp);
                }
                if (!ListenerUtil.mutListener.listen(20483)) {
                    actionableEmptyView.subtitle.setText(R.string.reader_empty_followed_blogs_description);
                }
                if (!ListenerUtil.mutListener.listen(20484)) {
                    actionableEmptyView.button.setText(R.string.reader_empty_followed_blogs_button_discover);
                }
                if (!ListenerUtil.mutListener.listen(20490)) {
                    actionableEmptyView.button.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            ReaderTag tag = ReaderUtils.getTagFromEndpoint(ReaderTag.DISCOVER_PATH);
                            if (!ListenerUtil.mutListener.listen(20486)) {
                                if (!ReaderTagTable.tagExists(tag)) {
                                    if (!ListenerUtil.mutListener.listen(20485)) {
                                        tag = ReaderTagTable.getFirstTag();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(20487)) {
                                AppPrefs.setReaderTag(tag);
                            }
                            if (!ListenerUtil.mutListener.listen(20489)) {
                                if (getActivity() != null) {
                                    if (!ListenerUtil.mutListener.listen(20488)) {
                                        getActivity().finish();
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(20502)) {
                    switch(getBlogType()) {
                        case FOLLOWED:
                            if (!ListenerUtil.mutListener.listen(20501)) {
                                if (getBlogAdapter().hasSearchFilter()) {
                                    if (!ListenerUtil.mutListener.listen(20496)) {
                                        actionableEmptyView.updateLayoutForSearch(true, 0);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20497)) {
                                        actionableEmptyView.title.setText(R.string.reader_empty_followed_blogs_search_title);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20498)) {
                                        actionableEmptyView.subtitle.setVisibility(View.GONE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20499)) {
                                        actionableEmptyView.button.setVisibility(View.GONE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20500)) {
                                        actionableEmptyView.image.setVisibility(View.GONE);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(20491)) {
                                        actionableEmptyView.updateLayoutForSearch(false, 0);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20492)) {
                                        actionableEmptyView.title.setText(R.string.reader_empty_followed_blogs_title);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20493)) {
                                        actionableEmptyView.subtitle.setVisibility(View.VISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20494)) {
                                        actionableEmptyView.button.setVisibility(View.VISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20495)) {
                                        actionableEmptyView.image.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20480)) {
                    actionableEmptyView.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(20504)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(20505)) {
            mRecyclerView.setAdapter(getBlogAdapter());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(20506)) {
            outState.putSerializable(ARG_BLOG_TYPE, getBlogType());
        }
        if (!ListenerUtil.mutListener.listen(20508)) {
            if (getBlogAdapter().hasSearchFilter()) {
                if (!ListenerUtil.mutListener.listen(20507)) {
                    outState.putString(KEY_SEARCH_FILTER, getBlogAdapter().getSearchFilter());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20509)) {
            super.onSaveInstanceState(outState);
        }
    }

    private void restoreState(Bundle args) {
        if (!ListenerUtil.mutListener.listen(20514)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(20511)) {
                    if (args.containsKey(ARG_BLOG_TYPE)) {
                        if (!ListenerUtil.mutListener.listen(20510)) {
                            mBlogType = (ReaderBlogType) args.getSerializable(ARG_BLOG_TYPE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20513)) {
                    if (args.containsKey(KEY_SEARCH_FILTER)) {
                        if (!ListenerUtil.mutListener.listen(20512)) {
                            mSearchFilter = args.getString(KEY_SEARCH_FILTER);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(20515)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(20516)) {
            refresh();
        }
    }

    /*
     * note this will only be called for followed blogs
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(20517)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
        if (!ListenerUtil.mutListener.listen(20518)) {
            inflater.inflate(R.menu.reader_subs, menu);
        }
        MenuItem searchMenu = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchMenu.getActionView();
        if (!ListenerUtil.mutListener.listen(20519)) {
            searchView.setMaxWidth(Integer.MAX_VALUE);
        }
        if (!ListenerUtil.mutListener.listen(20520)) {
            searchView.setQueryHint(getString(R.string.reader_hint_search_followed_sites));
        }
        if (!ListenerUtil.mutListener.listen(20521)) {
            searchMenu.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(20527)) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!ListenerUtil.mutListener.listen(20522)) {
                        setSearchFilter(query);
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (!ListenerUtil.mutListener.listen(20526)) {
                        // by ignoring the next search performed after recreation
                        if (mIgnoreNextSearch) {
                            if (!ListenerUtil.mutListener.listen(20524)) {
                                mIgnoreNextSearch = false;
                            }
                            if (!ListenerUtil.mutListener.listen(20525)) {
                                AppLog.i(AppLog.T.READER, "reader subs > ignoring search");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(20523)) {
                                setSearchFilter(newText);
                            }
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(20531)) {
            // make sure the search view is expanded and reflects the current filter
            if (!TextUtils.isEmpty(mSearchFilter)) {
                if (!ListenerUtil.mutListener.listen(20528)) {
                    searchMenu.expandActionView();
                }
                if (!ListenerUtil.mutListener.listen(20529)) {
                    searchView.clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(20530)) {
                    searchView.setQuery(mSearchFilter, false);
                }
            }
        }
    }

    void refresh() {
        if (!ListenerUtil.mutListener.listen(20534)) {
            if (hasBlogAdapter()) {
                if (!ListenerUtil.mutListener.listen(20532)) {
                    AppLog.d(AppLog.T.READER, "reader subs > refreshing blog fragment " + getBlogType().name());
                }
                if (!ListenerUtil.mutListener.listen(20533)) {
                    getBlogAdapter().refresh();
                }
            }
        }
    }

    private void setSearchFilter(String constraint) {
        if (!ListenerUtil.mutListener.listen(20535)) {
            mSearchFilter = constraint;
        }
        if (!ListenerUtil.mutListener.listen(20536)) {
            getBlogAdapter().setSearchFilter(constraint);
        }
    }

    private boolean hasBlogAdapter() {
        return (mAdapter != null);
    }

    private ReaderBlogAdapter getBlogAdapter() {
        if (!ListenerUtil.mutListener.listen(20541)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(20537)) {
                    mAdapter = new ReaderBlogAdapter(getActivity(), getBlogType(), mSearchFilter, ReaderTracker.SOURCE_SETTINGS);
                }
                if (!ListenerUtil.mutListener.listen(20538)) {
                    mAdapter.setBlogClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(20540)) {
                    mAdapter.setDataLoadedListener(new ReaderInterfaces.DataLoadedListener() {

                        @Override
                        public void onDataLoaded(boolean isEmpty) {
                            if (!ListenerUtil.mutListener.listen(20539)) {
                                checkEmptyView();
                            }
                        }
                    });
                }
            }
        }
        return mAdapter;
    }

    public ReaderBlogType getBlogType() {
        return mBlogType;
    }

    @Override
    public void onBlogClicked(Object item) {
        if (!ListenerUtil.mutListener.listen(20543)) {
            if (item instanceof ReaderBlog) {
                ReaderBlog blog = (ReaderBlog) item;
                if (!ListenerUtil.mutListener.listen(20542)) {
                    ReaderActivityLauncher.showReaderBlogOrFeedPreview(getActivity(), blog.blogId, blog.feedId, blog.isFollowing, ReaderTracker.SOURCE_SETTINGS, mReaderTracker);
                }
            }
        }
    }
}

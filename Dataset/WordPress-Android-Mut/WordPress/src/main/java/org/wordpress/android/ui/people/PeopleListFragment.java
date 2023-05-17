package org.wordpress.android.ui.people;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.material.appbar.AppBarLayout;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.PeopleTable;
import org.wordpress.android.fluxc.model.RoleModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.FilterCriteria;
import org.wordpress.android.models.PeopleListFilter;
import org.wordpress.android.models.Person;
import org.wordpress.android.models.RoleUtils;
import org.wordpress.android.ui.ActionableEmptyView;
import org.wordpress.android.ui.EmptyViewMessageType;
import org.wordpress.android.ui.FilteredRecyclerView;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.RtlUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PeopleListFragment extends Fragment {

    private SiteModel mSite;

    private OnPersonSelectedListener mOnPersonSelectedListener;

    private OnFetchPeopleListener mOnFetchPeopleListener;

    private ActionableEmptyView mActionableEmptyView;

    private FilteredRecyclerView mFilteredRecyclerView;

    private PeopleListFilter mPeopleListFilter;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ImageManager mImageManager;

    public static PeopleListFragment newInstance(SiteModel site) {
        PeopleListFragment peopleListFragment = new PeopleListFragment();
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(9737)) {
            bundle.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(9738)) {
            peopleListFragment.setArguments(bundle);
        }
        return peopleListFragment;
    }

    public void setOnPersonSelectedListener(OnPersonSelectedListener listener) {
        if (!ListenerUtil.mutListener.listen(9739)) {
            mOnPersonSelectedListener = listener;
        }
    }

    public void setOnFetchPeopleListener(OnFetchPeopleListener listener) {
        if (!ListenerUtil.mutListener.listen(9740)) {
            mOnFetchPeopleListener = listener;
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(9741)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(9742)) {
            mOnPersonSelectedListener = null;
        }
        if (!ListenerUtil.mutListener.listen(9743)) {
            mOnFetchPeopleListener = null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9744)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9745)) {
            ((WordPress) getActivity().getApplicationContext()).component().inject(this);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(9746)) {
            inflater.inflate(R.menu.people_list, menu);
        }
        if (!ListenerUtil.mutListener.listen(9747)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9748)) {
            setHasOptionsMenu(true);
        }
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.people_list_fragment, container, false);
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(9749)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(9753)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(9750)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(9751)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(9752)) {
                    actionBar.setTitle(R.string.people);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9754)) {
            mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
        }
        final boolean isPrivate = (ListenerUtil.mutListener.listen(9755) ? (mSite != null || mSite.isPrivate()) : (mSite != null && mSite.isPrivate()));
        if (!ListenerUtil.mutListener.listen(9756)) {
            mActionableEmptyView = rootView.findViewById(R.id.actionable_empty_view);
        }
        if (!ListenerUtil.mutListener.listen(9757)) {
            mFilteredRecyclerView = rootView.findViewById(R.id.filtered_recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(9758)) {
            mFilteredRecyclerView.addItemDecoration(new PeopleItemDecoration(getActivity()));
        }
        if (!ListenerUtil.mutListener.listen(9759)) {
            mFilteredRecyclerView.setLogT(AppLog.T.PEOPLE);
        }
        if (!ListenerUtil.mutListener.listen(9760)) {
            mFilteredRecyclerView.setSwipeToRefreshEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(9761)) {
            // the following will change the look and feel of the toolbar to match the current design
            mFilteredRecyclerView.setToolbarLeftAndRightPadding(getResources().getDimensionPixelSize(R.dimen.margin_filter_spinner), getResources().getDimensionPixelSize(R.dimen.margin_none));
        }
        if (!ListenerUtil.mutListener.listen(9784)) {
            mFilteredRecyclerView.setFilterListener(new FilteredRecyclerView.FilterListener() {

                @Override
                public List<FilterCriteria> onLoadFilterCriteriaOptions(boolean refresh) {
                    ArrayList<FilterCriteria> list = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(9762)) {
                        Collections.addAll(list, PeopleListFilter.values());
                    }
                    if (!ListenerUtil.mutListener.listen(9764)) {
                        // Only a private blog can have viewers
                        if (!isPrivate) {
                            if (!ListenerUtil.mutListener.listen(9763)) {
                                list.remove(PeopleListFilter.VIEWERS);
                            }
                        }
                    }
                    return list;
                }

                @Override
                public void onLoadFilterCriteriaOptionsAsync(FilteredRecyclerView.FilterCriteriaAsyncLoaderListener listener, boolean refresh) {
                }

                @Override
                public FilterCriteria onRecallSelection() {
                    if (!ListenerUtil.mutListener.listen(9765)) {
                        mPeopleListFilter = AppPrefs.getPeopleListFilter();
                    }
                    if (!ListenerUtil.mutListener.listen(9769)) {
                        // if viewers is not available for this blog, set the filter to TEAM
                        if ((ListenerUtil.mutListener.listen(9766) ? (mPeopleListFilter == PeopleListFilter.VIEWERS || !isPrivate) : (mPeopleListFilter == PeopleListFilter.VIEWERS && !isPrivate))) {
                            if (!ListenerUtil.mutListener.listen(9767)) {
                                mPeopleListFilter = PeopleListFilter.TEAM;
                            }
                            if (!ListenerUtil.mutListener.listen(9768)) {
                                AppPrefs.setPeopleListFilter(mPeopleListFilter);
                            }
                        }
                    }
                    return mPeopleListFilter;
                }

                @Override
                public void onLoadData(boolean forced) {
                    if (!ListenerUtil.mutListener.listen(9770)) {
                        updatePeople(false);
                    }
                }

                @Override
                public void onFilterSelected(int position, FilterCriteria criteria) {
                    if (!ListenerUtil.mutListener.listen(9771)) {
                        AnalyticsTracker.track(Stat.PEOPLE_MANAGEMENT_FILTER_CHANGED);
                    }
                    if (!ListenerUtil.mutListener.listen(9772)) {
                        mPeopleListFilter = (PeopleListFilter) criteria;
                    }
                    if (!ListenerUtil.mutListener.listen(9773)) {
                        AppPrefs.setPeopleListFilter(mPeopleListFilter);
                    }
                }

                @Override
                public String onShowEmptyViewMessage(EmptyViewMessageType emptyViewMsgType) {
                    if (!ListenerUtil.mutListener.listen(9774)) {
                        mActionableEmptyView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(9775)) {
                        mFilteredRecyclerView.setToolbarScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                    }
                    switch(emptyViewMsgType) {
                        case LOADING:
                            return getString(R.string.people_fetching);
                        case NETWORK_ERROR:
                            return getString(R.string.no_network_message);
                        case NO_CONTENT:
                            String title = "";
                            if (!ListenerUtil.mutListener.listen(9780)) {
                                switch(mPeopleListFilter) {
                                    case TEAM:
                                        if (!ListenerUtil.mutListener.listen(9776)) {
                                            title = getString(R.string.people_empty_list_filtered_users);
                                        }
                                        break;
                                    case FOLLOWERS:
                                        if (!ListenerUtil.mutListener.listen(9777)) {
                                            title = getString(R.string.people_empty_list_filtered_followers);
                                        }
                                        break;
                                    case EMAIL_FOLLOWERS:
                                        if (!ListenerUtil.mutListener.listen(9778)) {
                                            title = getString(R.string.people_empty_list_filtered_email_followers);
                                        }
                                        break;
                                    case VIEWERS:
                                        if (!ListenerUtil.mutListener.listen(9779)) {
                                            title = getString(R.string.people_empty_list_filtered_viewers);
                                        }
                                        break;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9781)) {
                                mActionableEmptyView.title.setText(title);
                            }
                            if (!ListenerUtil.mutListener.listen(9782)) {
                                mActionableEmptyView.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(9783)) {
                                mFilteredRecyclerView.setToolbarScrollFlags(0);
                            }
                            return "";
                        case GENERIC_ERROR:
                            switch(mPeopleListFilter) {
                                case TEAM:
                                    return getString(R.string.error_fetch_users_list);
                                case FOLLOWERS:
                                    return getString(R.string.error_fetch_followers_list);
                                case EMAIL_FOLLOWERS:
                                    return getString(R.string.error_fetch_email_followers_list);
                                case VIEWERS:
                                    return getString(R.string.error_fetch_viewers_list);
                            }
                        default:
                            return "";
                    }
                }

                @Override
                public void onShowCustomEmptyView(EmptyViewMessageType emptyViewMsgType) {
                }
            });
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9785)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9786)) {
            // important for accessibility - talkback
            getActivity().setTitle(R.string.people);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(9787)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(9788)) {
            updatePeople(false);
        }
    }

    private void updatePeople(boolean loadMore) {
        if (!ListenerUtil.mutListener.listen(9791)) {
            if (!NetworkUtils.isNetworkAvailable(getActivity())) {
                if (!ListenerUtil.mutListener.listen(9789)) {
                    mFilteredRecyclerView.updateEmptyView(EmptyViewMessageType.NETWORK_ERROR);
                }
                if (!ListenerUtil.mutListener.listen(9790)) {
                    mFilteredRecyclerView.setRefreshing(false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9800)) {
            if (mOnFetchPeopleListener != null) {
                if (!ListenerUtil.mutListener.listen(9799)) {
                    if (loadMore) {
                        boolean isFetching = mOnFetchPeopleListener.onFetchMorePeople(mPeopleListFilter);
                        if (!ListenerUtil.mutListener.listen(9798)) {
                            if (isFetching) {
                                if (!ListenerUtil.mutListener.listen(9797)) {
                                    mFilteredRecyclerView.showLoadingProgress();
                                }
                            }
                        }
                    } else {
                        boolean isFetching = mOnFetchPeopleListener.onFetchFirstPage(mPeopleListFilter);
                        if (!ListenerUtil.mutListener.listen(9795)) {
                            if (isFetching) {
                                if (!ListenerUtil.mutListener.listen(9794)) {
                                    mFilteredRecyclerView.updateEmptyView(EmptyViewMessageType.LOADING);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9792)) {
                                    mFilteredRecyclerView.hideEmptyView();
                                }
                                if (!ListenerUtil.mutListener.listen(9793)) {
                                    mFilteredRecyclerView.setRefreshing(false);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9796)) {
                            refreshPeopleList(isFetching);
                        }
                    }
                }
            }
        }
    }

    public void refreshPeopleList(boolean isFetching) {
        if (!ListenerUtil.mutListener.listen(9801)) {
            if (!isAdded()) {
                return;
            }
        }
        List<Person> peopleList;
        switch(mPeopleListFilter) {
            case TEAM:
                peopleList = PeopleTable.getUsers(mSite.getId());
                break;
            case FOLLOWERS:
                peopleList = PeopleTable.getFollowers(mSite.getId());
                break;
            case EMAIL_FOLLOWERS:
                peopleList = PeopleTable.getEmailFollowers(mSite.getId());
                break;
            case VIEWERS:
                peopleList = PeopleTable.getViewers(mSite.getId());
                break;
            default:
                peopleList = new ArrayList<>();
                break;
        }
        PeopleAdapter peopleAdapter = (PeopleAdapter) mFilteredRecyclerView.getAdapter();
        if (!ListenerUtil.mutListener.listen(9805)) {
            if (peopleAdapter == null) {
                if (!ListenerUtil.mutListener.listen(9803)) {
                    peopleAdapter = new PeopleAdapter(getActivity(), peopleList);
                }
                if (!ListenerUtil.mutListener.listen(9804)) {
                    mFilteredRecyclerView.setAdapter(peopleAdapter);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9802)) {
                    peopleAdapter.setPeopleList(peopleList);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9810)) {
            if (!peopleList.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(9807)) {
                    // if the list is not empty, don't show any message
                    mFilteredRecyclerView.hideEmptyView();
                }
                if (!ListenerUtil.mutListener.listen(9808)) {
                    mFilteredRecyclerView.setToolbarScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                }
                if (!ListenerUtil.mutListener.listen(9809)) {
                    mActionableEmptyView.setVisibility(View.GONE);
                }
            } else if (!isFetching) {
                if (!ListenerUtil.mutListener.listen(9806)) {
                    // if we are not fetching and list is empty, show no content message
                    mFilteredRecyclerView.updateEmptyView(EmptyViewMessageType.NO_CONTENT);
                }
            }
        }
    }

    // Refresh the role display names after user roles is fetched
    public void refreshUserRoles() {
        if (!ListenerUtil.mutListener.listen(9811)) {
            if (mFilteredRecyclerView == null) {
                // bail when list is not available
                return;
            }
        }
        PeopleAdapter peopleAdapter = (PeopleAdapter) mFilteredRecyclerView.getAdapter();
        if (!ListenerUtil.mutListener.listen(9814)) {
            if (peopleAdapter != null) {
                if (!ListenerUtil.mutListener.listen(9812)) {
                    peopleAdapter.refreshUserRoles();
                }
                if (!ListenerUtil.mutListener.listen(9813)) {
                    peopleAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void fetchingRequestFinished(PeopleListFilter filter, boolean isFirstPage, boolean isSuccessful) {
        if (!ListenerUtil.mutListener.listen(9820)) {
            if (mPeopleListFilter == filter) {
                if (!ListenerUtil.mutListener.listen(9819)) {
                    if (isFirstPage) {
                        if (!ListenerUtil.mutListener.listen(9816)) {
                            mFilteredRecyclerView.setRefreshing(false);
                        }
                        if (!ListenerUtil.mutListener.listen(9818)) {
                            if (!isSuccessful) {
                                if (!ListenerUtil.mutListener.listen(9817)) {
                                    mFilteredRecyclerView.updateEmptyView(EmptyViewMessageType.GENERIC_ERROR);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9815)) {
                            mFilteredRecyclerView.hideLoadingProgress();
                        }
                    }
                }
            }
        }
    }

    // Container Activity must implement this interface
    public interface OnPersonSelectedListener {

        void onPersonSelected(Person person);
    }

    public interface OnFetchPeopleListener {

        boolean onFetchFirstPage(PeopleListFilter filter);

        boolean onFetchMorePeople(PeopleListFilter filter);
    }

    public class PeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final LayoutInflater mInflater;

        private List<Person> mPeopleList;

        private int mAvatarSz;

        private List<RoleModel> mUserRoles;

        public PeopleAdapter(Context context, List<Person> peopleList) {
            if (!ListenerUtil.mutListener.listen(9821)) {
                mAvatarSz = context.getResources().getDimensionPixelSize(R.dimen.people_avatar_sz);
            }
            mInflater = LayoutInflater.from(context);
            if (!ListenerUtil.mutListener.listen(9822)) {
                mPeopleList = peopleList;
            }
            if (!ListenerUtil.mutListener.listen(9823)) {
                setHasStableIds(true);
            }
            if (!ListenerUtil.mutListener.listen(9824)) {
                refreshUserRoles();
            }
        }

        public void setPeopleList(List<Person> peopleList) {
            if (!ListenerUtil.mutListener.listen(9825)) {
                mPeopleList = peopleList;
            }
            if (!ListenerUtil.mutListener.listen(9826)) {
                notifyDataSetChanged();
            }
        }

        public Person getPerson(int position) {
            if (!ListenerUtil.mutListener.listen(9827)) {
                if (mPeopleList == null) {
                    return null;
                }
            }
            return mPeopleList.get(position);
        }

        public void refreshUserRoles() {
            if (!ListenerUtil.mutListener.listen(9829)) {
                if (mSite != null) {
                    if (!ListenerUtil.mutListener.listen(9828)) {
                        mUserRoles = mSiteStore.getUserRoles(mSite);
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            if (!ListenerUtil.mutListener.listen(9830)) {
                if (mPeopleList == null) {
                    return 0;
                }
            }
            return mPeopleList.size();
        }

        @Override
        public long getItemId(int position) {
            Person person = getPerson(position);
            if (!ListenerUtil.mutListener.listen(9831)) {
                if (person == null) {
                    return -1;
                }
            }
            return person.getPersonID();
        }

        @NonNull
        @Override
        public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.people_list_row, parent, false);
            return new PeopleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            PeopleViewHolder peopleViewHolder = (PeopleViewHolder) holder;
            final Person person = getPerson(position);
            if (!ListenerUtil.mutListener.listen(9847)) {
                if (person != null) {
                    String avatarUrl = GravatarUtils.fixGravatarUrl(person.getAvatarUrl(), mAvatarSz);
                    if (!ListenerUtil.mutListener.listen(9832)) {
                        mImageManager.loadIntoCircle(peopleViewHolder.mImgAvatar, ImageType.AVATAR_WITH_BACKGROUND, avatarUrl);
                    }
                    if (!ListenerUtil.mutListener.listen(9833)) {
                        peopleViewHolder.mTxtDisplayName.setText(StringEscapeUtils.unescapeHtml4(person.getDisplayName()));
                    }
                    if (!ListenerUtil.mutListener.listen(9837)) {
                        if (person.getRole() != null) {
                            if (!ListenerUtil.mutListener.listen(9835)) {
                                peopleViewHolder.mTxtRole.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(9836)) {
                                peopleViewHolder.mTxtRole.setText(RoleUtils.getDisplayName(person.getRole(), mUserRoles));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9834)) {
                                peopleViewHolder.mTxtRole.setVisibility(View.GONE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9841)) {
                        if (!person.getUsername().isEmpty()) {
                            if (!ListenerUtil.mutListener.listen(9839)) {
                                peopleViewHolder.mTxtUsername.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(9840)) {
                                peopleViewHolder.mTxtUsername.setText(String.format("@%s", person.getUsername()));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9838)) {
                                peopleViewHolder.mTxtUsername.setVisibility(View.GONE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9846)) {
                        if ((ListenerUtil.mutListener.listen(9842) ? (person.getPersonType() == Person.PersonType.USER && person.getPersonType() == Person.PersonType.VIEWER) : (person.getPersonType() == Person.PersonType.USER || person.getPersonType() == Person.PersonType.VIEWER))) {
                            if (!ListenerUtil.mutListener.listen(9845)) {
                                peopleViewHolder.mTxtSubscribed.setVisibility(View.GONE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9843)) {
                                peopleViewHolder.mTxtSubscribed.setVisibility(View.VISIBLE);
                            }
                            String dateSubscribed = SimpleDateFormat.getDateInstance().format(person.getDateSubscribed());
                            String dateText = getString(R.string.follower_subscribed_since, dateSubscribed);
                            if (!ListenerUtil.mutListener.listen(9844)) {
                                peopleViewHolder.mTxtSubscribed.setText(dateText);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9858)) {
                // end of list is reached
                if ((ListenerUtil.mutListener.listen(9856) ? (position >= (ListenerUtil.mutListener.listen(9851) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(9850) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(9849) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(9848) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(9855) ? (position <= (ListenerUtil.mutListener.listen(9851) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(9850) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(9849) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(9848) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(9854) ? (position > (ListenerUtil.mutListener.listen(9851) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(9850) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(9849) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(9848) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(9853) ? (position < (ListenerUtil.mutListener.listen(9851) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(9850) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(9849) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(9848) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(9852) ? (position != (ListenerUtil.mutListener.listen(9851) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(9850) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(9849) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(9848) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(9851) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(9850) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(9849) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(9848) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))) {
                    if (!ListenerUtil.mutListener.listen(9857)) {
                        updatePeople(true);
                    }
                }
            }
        }

        @Override
        public void onViewRecycled(@NonNull ViewHolder holder) {
            if (!ListenerUtil.mutListener.listen(9859)) {
                super.onViewRecycled(holder);
            }
            PeopleViewHolder peopleViewHolder = (PeopleViewHolder) holder;
        }

        public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private final ImageView mImgAvatar;

            private final TextView mTxtDisplayName;

            private final TextView mTxtUsername;

            private final TextView mTxtRole;

            private final TextView mTxtSubscribed;

            public PeopleViewHolder(View view) {
                super(view);
                mImgAvatar = view.findViewById(R.id.person_avatar);
                mTxtDisplayName = view.findViewById(R.id.person_display_name);
                mTxtUsername = view.findViewById(R.id.person_username);
                mTxtRole = view.findViewById(R.id.person_role);
                mTxtSubscribed = view.findViewById(R.id.follower_subscribed_date);
                if (!ListenerUtil.mutListener.listen(9860)) {
                    itemView.setOnClickListener(this);
                }
            }

            @Override
            public void onClick(View v) {
                if (!ListenerUtil.mutListener.listen(9862)) {
                    if (mOnPersonSelectedListener != null) {
                        Person person = getPerson(getAdapterPosition());
                        if (!ListenerUtil.mutListener.listen(9861)) {
                            mOnPersonSelectedListener.onPersonSelected(person);
                        }
                    }
                }
            }
        }
    }

    // Taken from http://stackoverflow.com/a/27037230
    private class PeopleItemDecoration extends RecyclerView.ItemDecoration {

        private InsetDrawable mDivider;

        // use a custom drawable
        PeopleItemDecoration(Context context) {
            int[] attrs = { android.R.attr.listDivider };
            TypedArray ta = context.obtainStyledAttributes(attrs);
            Drawable drawable = ta.getDrawable(0);
            if (!ListenerUtil.mutListener.listen(9863)) {
                ta.recycle();
            }
            int inset = context.getResources().getDimensionPixelOffset(R.dimen.people_list_divider_left_margin);
            if (!ListenerUtil.mutListener.listen(9866)) {
                if (RtlUtils.isRtl(context)) {
                    if (!ListenerUtil.mutListener.listen(9865)) {
                        mDivider = new InsetDrawable(drawable, 0, 0, inset, 0);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9864)) {
                        mDivider = new InsetDrawable(drawable, inset, 0, 0, 0);
                    }
                }
            }
        }

        @Override
        public void onDraw(@NotNull Canvas c, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
            int left = ViewCompat.getPaddingStart(parent);
            int right = (ListenerUtil.mutListener.listen(9870) ? (parent.getWidth() % ViewCompat.getPaddingEnd(parent)) : (ListenerUtil.mutListener.listen(9869) ? (parent.getWidth() / ViewCompat.getPaddingEnd(parent)) : (ListenerUtil.mutListener.listen(9868) ? (parent.getWidth() * ViewCompat.getPaddingEnd(parent)) : (ListenerUtil.mutListener.listen(9867) ? (parent.getWidth() + ViewCompat.getPaddingEnd(parent)) : (parent.getWidth() - ViewCompat.getPaddingEnd(parent))))));
            int childCount = parent.getChildCount();
            if (!ListenerUtil.mutListener.listen(9878)) {
                {
                    long _loopCounter193 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(9877) ? (i >= childCount) : (ListenerUtil.mutListener.listen(9876) ? (i <= childCount) : (ListenerUtil.mutListener.listen(9875) ? (i > childCount) : (ListenerUtil.mutListener.listen(9874) ? (i != childCount) : (ListenerUtil.mutListener.listen(9873) ? (i == childCount) : (i < childCount)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter193", ++_loopCounter193);
                        View child = parent.getChildAt(i);
                        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                        int top = child.getBottom() + params.bottomMargin;
                        int bottom = top + mDivider.getIntrinsicHeight();
                        if (!ListenerUtil.mutListener.listen(9871)) {
                            mDivider.setBounds(left, top, right, bottom);
                        }
                        if (!ListenerUtil.mutListener.listen(9872)) {
                            mDivider.draw(c);
                        }
                    }
                }
            }
        }
    }
}

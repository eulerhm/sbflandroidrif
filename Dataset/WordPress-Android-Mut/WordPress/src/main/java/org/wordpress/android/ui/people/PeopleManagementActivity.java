package org.wordpress.android.ui.people;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.datasets.PeopleTable;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore.OnUserRolesChanged;
import org.wordpress.android.models.PeopleListFilter;
import org.wordpress.android.models.Person;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.people.utils.PeopleUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PeopleManagementActivity extends LocaleAwareActivity implements PeopleListFragment.OnPersonSelectedListener, PeopleListFragment.OnFetchPeopleListener {

    private static final String KEY_PEOPLE_LIST_FRAGMENT = "people-list-fragment";

    private static final String KEY_PERSON_DETAIL_FRAGMENT = "person-detail-fragment";

    private static final String KEY_PEOPLE_INVITE_FRAGMENT = "people-invite-fragment";

    private static final String KEY_TITLE = "page-title";

    private static final String KEY_USERS_END_OF_LIST_REACHED = "users-end-of-list-reached";

    private static final String KEY_FOLLOWERS_END_OF_LIST_REACHED = "followers-end-of-list-reached";

    private static final String KEY_EMAIL_FOLLOWERS_END_OF_LIST_REACHED = "email-followers-end-of-list-reached";

    private static final String KEY_VIEWERS_END_OF_LIST_REACHED = "viewers-end-of-list-reached";

    private static final String KEY_USERS_FETCH_REQUEST_IN_PROGRESS = "users-fetch-request-in-progress";

    private static final String KEY_FOLLOWERS_FETCH_REQUEST_IN_PROGRESS = "followers-fetch-request-in-progress";

    private static final String KEY_EMAIL_FOLLOWERS_FETCH_REQUEST_IN_PROGRESS = "email-followers-fetch-request-in-progress";

    private static final String KEY_VIEWERS_FETCH_REQUEST_IN_PROGRESS = "viewers-fetch-request-in-progress";

    private static final String KEY_HAS_REFRESHED_USERS = "has-refreshed-users";

    private static final String KEY_HAS_REFRESHED_FOLLOWERS = "has-refreshed-followers";

    private static final String KEY_HAS_REFRESHED_EMAIL_FOLLOWERS = "has-refreshed-email-followers";

    private static final String KEY_HAS_REFRESHED_VIEWERS = "has-refreshed-viewers";

    private static final String KEY_FOLLOWERS_LAST_FETCHED_PAGE = "followers-last-fetched-page";

    private static final String KEY_EMAIL_FOLLOWERS_LAST_FETCHED_PAGE = "email-followers-last-fetched-page";

    // End of list reached variables will be true when there is no more data to fetch
    private boolean mUsersEndOfListReached;

    private boolean mFollowersEndOfListReached;

    private boolean mEmailFollowersEndOfListReached;

    private boolean mViewersEndOfListReached;

    // We only allow the lists to be refreshed once to avoid syncing and jumping animation issues
    private boolean mHasRefreshedUsers;

    private boolean mHasRefreshedFollowers;

    private boolean mHasRefreshedEmailFollowers;

    private boolean mHasRefreshedViewers;

    // If we are currently making a request for a certain filter
    private boolean mUsersFetchRequestInProgress;

    private boolean mFollowersFetchRequestInProgress;

    private boolean mEmailFollowersFetchRequestInProgress;

    private boolean mViewersFetchRequestInProgress;

    // Keep track of the last page we received from remote
    private int mFollowersLastFetchedPage;

    private int mEmailFollowersLastFetchedPage;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    AccountStore mAccountStore;

    private SiteModel mSite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9879)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9880)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(9881)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(9882)) {
            setContentView(R.layout.people_management_activity);
        }
        if (!ListenerUtil.mutListener.listen(9885)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(9884)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9883)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9888)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(9886)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(9887)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9889)) {
            // Fetch the user roles to get ready
            mDispatcher.dispatch(SiteActionBuilder.newFetchUserRolesAction(mSite));
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(9926)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(9908)) {
                    // only delete cached people if there is a connection
                    if (NetworkUtils.isNetworkAvailable(this)) {
                        if (!ListenerUtil.mutListener.listen(9907)) {
                            PeopleTable.deletePeopleExceptForFirstPage(mSite.getId());
                        }
                    }
                }
                PeopleListFragment peopleListFragment = PeopleListFragment.newInstance(mSite);
                if (!ListenerUtil.mutListener.listen(9909)) {
                    peopleListFragment.setOnPersonSelectedListener(this);
                }
                if (!ListenerUtil.mutListener.listen(9910)) {
                    peopleListFragment.setOnFetchPeopleListener(this);
                }
                if (!ListenerUtil.mutListener.listen(9911)) {
                    mUsersEndOfListReached = false;
                }
                if (!ListenerUtil.mutListener.listen(9912)) {
                    mFollowersEndOfListReached = false;
                }
                if (!ListenerUtil.mutListener.listen(9913)) {
                    mEmailFollowersEndOfListReached = false;
                }
                if (!ListenerUtil.mutListener.listen(9914)) {
                    mViewersEndOfListReached = false;
                }
                if (!ListenerUtil.mutListener.listen(9915)) {
                    mHasRefreshedUsers = false;
                }
                if (!ListenerUtil.mutListener.listen(9916)) {
                    mHasRefreshedFollowers = false;
                }
                if (!ListenerUtil.mutListener.listen(9917)) {
                    mHasRefreshedEmailFollowers = false;
                }
                if (!ListenerUtil.mutListener.listen(9918)) {
                    mHasRefreshedViewers = false;
                }
                if (!ListenerUtil.mutListener.listen(9919)) {
                    mUsersFetchRequestInProgress = false;
                }
                if (!ListenerUtil.mutListener.listen(9920)) {
                    mFollowersFetchRequestInProgress = false;
                }
                if (!ListenerUtil.mutListener.listen(9921)) {
                    mEmailFollowersFetchRequestInProgress = false;
                }
                if (!ListenerUtil.mutListener.listen(9922)) {
                    mViewersFetchRequestInProgress = false;
                }
                if (!ListenerUtil.mutListener.listen(9923)) {
                    mFollowersLastFetchedPage = 0;
                }
                if (!ListenerUtil.mutListener.listen(9924)) {
                    mEmailFollowersLastFetchedPage = 0;
                }
                if (!ListenerUtil.mutListener.listen(9925)) {
                    fragmentManager.beginTransaction().add(R.id.fragment_container, peopleListFragment, KEY_PEOPLE_LIST_FRAGMENT).commit();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9890)) {
                    mUsersEndOfListReached = savedInstanceState.getBoolean(KEY_USERS_END_OF_LIST_REACHED);
                }
                if (!ListenerUtil.mutListener.listen(9891)) {
                    mFollowersEndOfListReached = savedInstanceState.getBoolean(KEY_FOLLOWERS_END_OF_LIST_REACHED);
                }
                if (!ListenerUtil.mutListener.listen(9892)) {
                    mEmailFollowersEndOfListReached = savedInstanceState.getBoolean(KEY_EMAIL_FOLLOWERS_END_OF_LIST_REACHED);
                }
                if (!ListenerUtil.mutListener.listen(9893)) {
                    mViewersEndOfListReached = savedInstanceState.getBoolean(KEY_VIEWERS_END_OF_LIST_REACHED);
                }
                if (!ListenerUtil.mutListener.listen(9894)) {
                    mHasRefreshedUsers = savedInstanceState.getBoolean(KEY_HAS_REFRESHED_USERS);
                }
                if (!ListenerUtil.mutListener.listen(9895)) {
                    mHasRefreshedFollowers = savedInstanceState.getBoolean(KEY_HAS_REFRESHED_FOLLOWERS);
                }
                if (!ListenerUtil.mutListener.listen(9896)) {
                    mHasRefreshedEmailFollowers = savedInstanceState.getBoolean(KEY_HAS_REFRESHED_EMAIL_FOLLOWERS);
                }
                if (!ListenerUtil.mutListener.listen(9897)) {
                    mHasRefreshedViewers = savedInstanceState.getBoolean(KEY_HAS_REFRESHED_VIEWERS);
                }
                if (!ListenerUtil.mutListener.listen(9898)) {
                    mUsersFetchRequestInProgress = savedInstanceState.getBoolean(KEY_USERS_FETCH_REQUEST_IN_PROGRESS);
                }
                if (!ListenerUtil.mutListener.listen(9899)) {
                    mFollowersFetchRequestInProgress = savedInstanceState.getBoolean(KEY_FOLLOWERS_FETCH_REQUEST_IN_PROGRESS);
                }
                if (!ListenerUtil.mutListener.listen(9900)) {
                    mEmailFollowersFetchRequestInProgress = savedInstanceState.getBoolean(KEY_EMAIL_FOLLOWERS_FETCH_REQUEST_IN_PROGRESS);
                }
                if (!ListenerUtil.mutListener.listen(9901)) {
                    mViewersFetchRequestInProgress = savedInstanceState.getBoolean(KEY_VIEWERS_FETCH_REQUEST_IN_PROGRESS);
                }
                if (!ListenerUtil.mutListener.listen(9902)) {
                    mFollowersLastFetchedPage = savedInstanceState.getInt(KEY_FOLLOWERS_LAST_FETCHED_PAGE);
                }
                if (!ListenerUtil.mutListener.listen(9903)) {
                    mEmailFollowersLastFetchedPage = savedInstanceState.getInt(KEY_EMAIL_FOLLOWERS_LAST_FETCHED_PAGE);
                }
                PeopleListFragment peopleListFragment = getListFragment();
                if (!ListenerUtil.mutListener.listen(9906)) {
                    if (peopleListFragment != null) {
                        if (!ListenerUtil.mutListener.listen(9904)) {
                            peopleListFragment.setOnPersonSelectedListener(this);
                        }
                        if (!ListenerUtil.mutListener.listen(9905)) {
                            peopleListFragment.setOnFetchPeopleListener(this);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(9927)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(9928)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(9929)) {
            outState.putBoolean(KEY_USERS_END_OF_LIST_REACHED, mUsersEndOfListReached);
        }
        if (!ListenerUtil.mutListener.listen(9930)) {
            outState.putBoolean(KEY_FOLLOWERS_END_OF_LIST_REACHED, mFollowersEndOfListReached);
        }
        if (!ListenerUtil.mutListener.listen(9931)) {
            outState.putBoolean(KEY_EMAIL_FOLLOWERS_END_OF_LIST_REACHED, mEmailFollowersEndOfListReached);
        }
        if (!ListenerUtil.mutListener.listen(9932)) {
            outState.putBoolean(KEY_VIEWERS_END_OF_LIST_REACHED, mViewersEndOfListReached);
        }
        if (!ListenerUtil.mutListener.listen(9933)) {
            outState.putBoolean(KEY_HAS_REFRESHED_USERS, mHasRefreshedUsers);
        }
        if (!ListenerUtil.mutListener.listen(9934)) {
            outState.putBoolean(KEY_HAS_REFRESHED_FOLLOWERS, mHasRefreshedFollowers);
        }
        if (!ListenerUtil.mutListener.listen(9935)) {
            outState.putBoolean(KEY_HAS_REFRESHED_EMAIL_FOLLOWERS, mHasRefreshedEmailFollowers);
        }
        if (!ListenerUtil.mutListener.listen(9936)) {
            outState.putBoolean(KEY_HAS_REFRESHED_VIEWERS, mHasRefreshedViewers);
        }
        if (!ListenerUtil.mutListener.listen(9937)) {
            outState.putBoolean(KEY_USERS_FETCH_REQUEST_IN_PROGRESS, mUsersFetchRequestInProgress);
        }
        if (!ListenerUtil.mutListener.listen(9938)) {
            outState.putBoolean(KEY_FOLLOWERS_FETCH_REQUEST_IN_PROGRESS, mFollowersFetchRequestInProgress);
        }
        if (!ListenerUtil.mutListener.listen(9939)) {
            outState.putBoolean(KEY_EMAIL_FOLLOWERS_FETCH_REQUEST_IN_PROGRESS, mEmailFollowersFetchRequestInProgress);
        }
        if (!ListenerUtil.mutListener.listen(9940)) {
            outState.putBoolean(KEY_VIEWERS_FETCH_REQUEST_IN_PROGRESS, mViewersFetchRequestInProgress);
        }
        if (!ListenerUtil.mutListener.listen(9941)) {
            outState.putInt(KEY_FOLLOWERS_LAST_FETCHED_PAGE, mFollowersLastFetchedPage);
        }
        if (!ListenerUtil.mutListener.listen(9942)) {
            outState.putInt(KEY_EMAIL_FOLLOWERS_LAST_FETCHED_PAGE, mEmailFollowersLastFetchedPage);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(9944)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(9943)) {
                    outState.putCharSequence(KEY_TITLE, actionBar.getTitle());
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(9945)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(9946)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(9947)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(9948)) {
            super.onStop();
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(9950)) {
            if (!navigateBackToPeopleListFragment()) {
                if (!ListenerUtil.mutListener.listen(9949)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(9962)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(9961)) {
                    onBackPressed();
                }
                return true;
            } else if (item.getItemId() == R.id.remove_person) {
                if (!ListenerUtil.mutListener.listen(9960)) {
                    confirmRemovePerson();
                }
                return true;
            } else if (item.getItemId() == R.id.invite) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment peopleInviteFragment = fragmentManager.findFragmentByTag(KEY_PERSON_DETAIL_FRAGMENT);
                if (!ListenerUtil.mutListener.listen(9954)) {
                    if (peopleInviteFragment == null) {
                        if (!ListenerUtil.mutListener.listen(9953)) {
                            peopleInviteFragment = PeopleInviteFragment.newInstance(mSite);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9959)) {
                    if ((ListenerUtil.mutListener.listen(9955) ? (peopleInviteFragment != null || !peopleInviteFragment.isAdded()) : (peopleInviteFragment != null && !peopleInviteFragment.isAdded()))) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        if (!ListenerUtil.mutListener.listen(9956)) {
                            fragmentTransaction.replace(R.id.fragment_container, peopleInviteFragment, KEY_PEOPLE_INVITE_FRAGMENT);
                        }
                        if (!ListenerUtil.mutListener.listen(9957)) {
                            fragmentTransaction.addToBackStack(null);
                        }
                        if (!ListenerUtil.mutListener.listen(9958)) {
                            fragmentTransaction.commit();
                        }
                    }
                }
            } else if (item.getItemId() == R.id.send_invitation) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment peopleInviteFragment = fragmentManager.findFragmentByTag(KEY_PEOPLE_INVITE_FRAGMENT);
                if (!ListenerUtil.mutListener.listen(9952)) {
                    if (peopleInviteFragment != null) {
                        if (!ListenerUtil.mutListener.listen(9951)) {
                            ((InvitationSender) peopleInviteFragment).send();
                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean fetchUsersList(final SiteModel site, final int offset) {
        if (!ListenerUtil.mutListener.listen(9965)) {
            if ((ListenerUtil.mutListener.listen(9964) ? ((ListenerUtil.mutListener.listen(9963) ? (mUsersEndOfListReached && mUsersFetchRequestInProgress) : (mUsersEndOfListReached || mUsersFetchRequestInProgress)) && !NetworkUtils.checkConnection(this)) : ((ListenerUtil.mutListener.listen(9963) ? (mUsersEndOfListReached && mUsersFetchRequestInProgress) : (mUsersEndOfListReached || mUsersFetchRequestInProgress)) || !NetworkUtils.checkConnection(this)))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9966)) {
            mUsersFetchRequestInProgress = true;
        }
        if (!ListenerUtil.mutListener.listen(9978)) {
            PeopleUtils.fetchUsers(site, offset, new PeopleUtils.FetchUsersCallback() {

                @Override
                public void onSuccess(List<Person> peopleList, boolean isEndOfList) {
                    boolean isFreshList = (offset == 0);
                    if (!ListenerUtil.mutListener.listen(9967)) {
                        mHasRefreshedUsers = true;
                    }
                    if (!ListenerUtil.mutListener.listen(9968)) {
                        mUsersEndOfListReached = isEndOfList;
                    }
                    if (!ListenerUtil.mutListener.listen(9969)) {
                        PeopleTable.saveUsers(peopleList, site.getId(), isFreshList);
                    }
                    PeopleListFragment peopleListFragment = getListFragment();
                    if (!ListenerUtil.mutListener.listen(9971)) {
                        if (peopleListFragment != null) {
                            if (!ListenerUtil.mutListener.listen(9970)) {
                                peopleListFragment.fetchingRequestFinished(PeopleListFilter.TEAM, isFreshList, true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9972)) {
                        refreshOnScreenFragmentDetails();
                    }
                    if (!ListenerUtil.mutListener.listen(9973)) {
                        mUsersFetchRequestInProgress = false;
                    }
                }

                @Override
                public void onError() {
                    PeopleListFragment peopleListFragment = getListFragment();
                    if (!ListenerUtil.mutListener.listen(9975)) {
                        if (peopleListFragment != null) {
                            boolean isFirstPage = offset == 0;
                            if (!ListenerUtil.mutListener.listen(9974)) {
                                peopleListFragment.fetchingRequestFinished(PeopleListFilter.TEAM, isFirstPage, false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9976)) {
                        mUsersFetchRequestInProgress = false;
                    }
                    if (!ListenerUtil.mutListener.listen(9977)) {
                        ToastUtils.showToast(PeopleManagementActivity.this, R.string.error_fetch_users_list, ToastUtils.Duration.SHORT);
                    }
                }
            });
        }
        return true;
    }

    private boolean fetchFollowersList(final SiteModel site, final int page) {
        if (!ListenerUtil.mutListener.listen(9981)) {
            if ((ListenerUtil.mutListener.listen(9980) ? ((ListenerUtil.mutListener.listen(9979) ? (mFollowersEndOfListReached && mFollowersFetchRequestInProgress) : (mFollowersEndOfListReached || mFollowersFetchRequestInProgress)) && !NetworkUtils.checkConnection(this)) : ((ListenerUtil.mutListener.listen(9979) ? (mFollowersEndOfListReached && mFollowersFetchRequestInProgress) : (mFollowersEndOfListReached || mFollowersFetchRequestInProgress)) || !NetworkUtils.checkConnection(this)))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9982)) {
            mFollowersFetchRequestInProgress = true;
        }
        if (!ListenerUtil.mutListener.listen(9995)) {
            PeopleUtils.fetchFollowers(site, page, new PeopleUtils.FetchFollowersCallback() {

                @Override
                public void onSuccess(List<Person> peopleList, int pageFetched, boolean isEndOfList) {
                    boolean isFreshList = (page == 1);
                    if (!ListenerUtil.mutListener.listen(9983)) {
                        mHasRefreshedFollowers = true;
                    }
                    if (!ListenerUtil.mutListener.listen(9984)) {
                        mFollowersLastFetchedPage = pageFetched;
                    }
                    if (!ListenerUtil.mutListener.listen(9985)) {
                        mFollowersEndOfListReached = isEndOfList;
                    }
                    if (!ListenerUtil.mutListener.listen(9986)) {
                        PeopleTable.saveFollowers(peopleList, site.getId(), isFreshList);
                    }
                    PeopleListFragment peopleListFragment = getListFragment();
                    if (!ListenerUtil.mutListener.listen(9988)) {
                        if (peopleListFragment != null) {
                            if (!ListenerUtil.mutListener.listen(9987)) {
                                peopleListFragment.fetchingRequestFinished(PeopleListFilter.FOLLOWERS, isFreshList, true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9989)) {
                        refreshOnScreenFragmentDetails();
                    }
                    if (!ListenerUtil.mutListener.listen(9990)) {
                        mFollowersFetchRequestInProgress = false;
                    }
                }

                @Override
                public void onError() {
                    PeopleListFragment peopleListFragment = getListFragment();
                    if (!ListenerUtil.mutListener.listen(9992)) {
                        if (peopleListFragment != null) {
                            boolean isFirstPage = page == 1;
                            if (!ListenerUtil.mutListener.listen(9991)) {
                                peopleListFragment.fetchingRequestFinished(PeopleListFilter.FOLLOWERS, isFirstPage, false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9993)) {
                        mFollowersFetchRequestInProgress = false;
                    }
                    if (!ListenerUtil.mutListener.listen(9994)) {
                        ToastUtils.showToast(PeopleManagementActivity.this, R.string.error_fetch_followers_list, ToastUtils.Duration.SHORT);
                    }
                }
            });
        }
        return true;
    }

    private boolean fetchEmailFollowersList(final SiteModel site, final int page) {
        if (!ListenerUtil.mutListener.listen(9998)) {
            if ((ListenerUtil.mutListener.listen(9997) ? ((ListenerUtil.mutListener.listen(9996) ? (mEmailFollowersEndOfListReached && mEmailFollowersFetchRequestInProgress) : (mEmailFollowersEndOfListReached || mEmailFollowersFetchRequestInProgress)) && !NetworkUtils.checkConnection(this)) : ((ListenerUtil.mutListener.listen(9996) ? (mEmailFollowersEndOfListReached && mEmailFollowersFetchRequestInProgress) : (mEmailFollowersEndOfListReached || mEmailFollowersFetchRequestInProgress)) || !NetworkUtils.checkConnection(this)))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9999)) {
            mEmailFollowersFetchRequestInProgress = true;
        }
        if (!ListenerUtil.mutListener.listen(10012)) {
            PeopleUtils.fetchEmailFollowers(site, page, new PeopleUtils.FetchFollowersCallback() {

                @Override
                public void onSuccess(List<Person> peopleList, int pageFetched, boolean isEndOfList) {
                    boolean isFreshList = (page == 1);
                    if (!ListenerUtil.mutListener.listen(10000)) {
                        mHasRefreshedEmailFollowers = true;
                    }
                    if (!ListenerUtil.mutListener.listen(10001)) {
                        mEmailFollowersLastFetchedPage = pageFetched;
                    }
                    if (!ListenerUtil.mutListener.listen(10002)) {
                        mEmailFollowersEndOfListReached = isEndOfList;
                    }
                    if (!ListenerUtil.mutListener.listen(10003)) {
                        PeopleTable.saveEmailFollowers(peopleList, site.getId(), isFreshList);
                    }
                    PeopleListFragment peopleListFragment = getListFragment();
                    if (!ListenerUtil.mutListener.listen(10005)) {
                        if (peopleListFragment != null) {
                            if (!ListenerUtil.mutListener.listen(10004)) {
                                peopleListFragment.fetchingRequestFinished(PeopleListFilter.EMAIL_FOLLOWERS, isFreshList, true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10006)) {
                        refreshOnScreenFragmentDetails();
                    }
                    if (!ListenerUtil.mutListener.listen(10007)) {
                        mEmailFollowersFetchRequestInProgress = false;
                    }
                }

                @Override
                public void onError() {
                    PeopleListFragment peopleListFragment = getListFragment();
                    if (!ListenerUtil.mutListener.listen(10009)) {
                        if (peopleListFragment != null) {
                            boolean isFirstPage = page == 1;
                            if (!ListenerUtil.mutListener.listen(10008)) {
                                peopleListFragment.fetchingRequestFinished(PeopleListFilter.EMAIL_FOLLOWERS, isFirstPage, false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10010)) {
                        mEmailFollowersFetchRequestInProgress = false;
                    }
                    if (!ListenerUtil.mutListener.listen(10011)) {
                        ToastUtils.showToast(PeopleManagementActivity.this, R.string.error_fetch_email_followers_list, ToastUtils.Duration.SHORT);
                    }
                }
            });
        }
        return true;
    }

    private boolean fetchViewersList(final SiteModel site, final int offset) {
        if (!ListenerUtil.mutListener.listen(10015)) {
            if ((ListenerUtil.mutListener.listen(10014) ? ((ListenerUtil.mutListener.listen(10013) ? (mViewersEndOfListReached && mViewersFetchRequestInProgress) : (mViewersEndOfListReached || mViewersFetchRequestInProgress)) && !NetworkUtils.checkConnection(this)) : ((ListenerUtil.mutListener.listen(10013) ? (mViewersEndOfListReached && mViewersFetchRequestInProgress) : (mViewersEndOfListReached || mViewersFetchRequestInProgress)) || !NetworkUtils.checkConnection(this)))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(10016)) {
            mViewersFetchRequestInProgress = true;
        }
        if (!ListenerUtil.mutListener.listen(10028)) {
            PeopleUtils.fetchViewers(site, offset, new PeopleUtils.FetchViewersCallback() {

                @Override
                public void onSuccess(List<Person> peopleList, boolean isEndOfList) {
                    boolean isFreshList = (offset == 0);
                    if (!ListenerUtil.mutListener.listen(10017)) {
                        mHasRefreshedViewers = true;
                    }
                    if (!ListenerUtil.mutListener.listen(10018)) {
                        mViewersEndOfListReached = isEndOfList;
                    }
                    if (!ListenerUtil.mutListener.listen(10019)) {
                        PeopleTable.saveViewers(peopleList, site.getId(), isFreshList);
                    }
                    PeopleListFragment peopleListFragment = getListFragment();
                    if (!ListenerUtil.mutListener.listen(10021)) {
                        if (peopleListFragment != null) {
                            if (!ListenerUtil.mutListener.listen(10020)) {
                                peopleListFragment.fetchingRequestFinished(PeopleListFilter.VIEWERS, isFreshList, true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10022)) {
                        refreshOnScreenFragmentDetails();
                    }
                    if (!ListenerUtil.mutListener.listen(10023)) {
                        mViewersFetchRequestInProgress = false;
                    }
                }

                @Override
                public void onError() {
                    PeopleListFragment peopleListFragment = getListFragment();
                    if (!ListenerUtil.mutListener.listen(10025)) {
                        if (peopleListFragment != null) {
                            boolean isFirstPage = offset == 0;
                            if (!ListenerUtil.mutListener.listen(10024)) {
                                peopleListFragment.fetchingRequestFinished(PeopleListFilter.VIEWERS, isFirstPage, false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10026)) {
                        mViewersFetchRequestInProgress = false;
                    }
                    if (!ListenerUtil.mutListener.listen(10027)) {
                        ToastUtils.showToast(PeopleManagementActivity.this, R.string.error_fetch_viewers_list, ToastUtils.Duration.SHORT);
                    }
                }
            });
        }
        return true;
    }

    @Override
    public void onPersonSelected(Person person) {
        PersonDetailFragment personDetailFragment = getDetailFragment();
        long personID = person.getPersonID();
        int localTableBlogID = person.getLocalTableBlogId();
        if (!ListenerUtil.mutListener.listen(10031)) {
            if (personDetailFragment == null) {
                if (!ListenerUtil.mutListener.listen(10030)) {
                    personDetailFragment = PersonDetailFragment.newInstance(mAccountStore.getAccount().getUserId(), personID, localTableBlogID, person.getPersonType());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10029)) {
                    personDetailFragment.setPersonDetails(personID, localTableBlogID);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10039)) {
            if (!personDetailFragment.isAdded()) {
                if (!ListenerUtil.mutListener.listen(10032)) {
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_PERSON, mSite);
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if (!ListenerUtil.mutListener.listen(10033)) {
                    fragmentTransaction.replace(R.id.fragment_container, personDetailFragment, KEY_PERSON_DETAIL_FRAGMENT);
                }
                if (!ListenerUtil.mutListener.listen(10034)) {
                    fragmentTransaction.addToBackStack(null);
                }
                ActionBar actionBar = getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(10037)) {
                    if (actionBar != null) {
                        if (!ListenerUtil.mutListener.listen(10035)) {
                            actionBar.setTitle("");
                        }
                        if (!ListenerUtil.mutListener.listen(10036)) {
                            // important for accessibility - talkback
                            setTitle(R.string.person_detail_screen_title);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10038)) {
                    fragmentTransaction.commit();
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(RoleChangeDialogFragment.RoleChangeEvent event) {
        if (!ListenerUtil.mutListener.listen(10040)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        final Person person = PeopleTable.getUser(event.getPersonID(), event.getLocalTableBlogId());
        if (!ListenerUtil.mutListener.listen(10043)) {
            if ((ListenerUtil.mutListener.listen(10042) ? ((ListenerUtil.mutListener.listen(10041) ? (person == null && event.getNewRole() == null) : (person == null || event.getNewRole() == null)) && event.getNewRole().equals(person.getRole())) : ((ListenerUtil.mutListener.listen(10041) ? (person == null && event.getNewRole() == null) : (person == null || event.getNewRole() == null)) || event.getNewRole().equals(person.getRole())))) {
                return;
            }
        }
        final PersonDetailFragment personDetailFragment = getDetailFragment();
        if (!ListenerUtil.mutListener.listen(10045)) {
            if (personDetailFragment != null) {
                if (!ListenerUtil.mutListener.listen(10044)) {
                    // optimistically update the role
                    personDetailFragment.changeRole(event.getNewRole());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10052)) {
            PeopleUtils.updateRole(mSite, person.getPersonID(), event.getNewRole(), event.getLocalTableBlogId(), new PeopleUtils.UpdateUserCallback() {

                @Override
                public void onSuccess(Person person) {
                    if (!ListenerUtil.mutListener.listen(10046)) {
                        AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.PERSON_UPDATED, mSite);
                    }
                    if (!ListenerUtil.mutListener.listen(10047)) {
                        PeopleTable.saveUser(person);
                    }
                    if (!ListenerUtil.mutListener.listen(10048)) {
                        refreshOnScreenFragmentDetails();
                    }
                }

                @Override
                public void onError() {
                    if (!ListenerUtil.mutListener.listen(10050)) {
                        // change the role back to it's original value
                        if (personDetailFragment != null) {
                            if (!ListenerUtil.mutListener.listen(10049)) {
                                personDetailFragment.refreshPersonDetails();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10051)) {
                        ToastUtils.showToast(PeopleManagementActivity.this, R.string.error_update_role, ToastUtils.Duration.LONG);
                    }
                }
            });
        }
    }

    private void confirmRemovePerson() {
        Person person = getCurrentPerson();
        if (!ListenerUtil.mutListener.listen(10053)) {
            if (person == null) {
                return;
            }
        }
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(10054)) {
            builder.setTitle(getString(R.string.person_remove_confirmation_title, person.getDisplayName()));
        }
        if (!ListenerUtil.mutListener.listen(10058)) {
            if (person.getPersonType() == Person.PersonType.USER) {
                if (!ListenerUtil.mutListener.listen(10057)) {
                    builder.setMessage(getString(R.string.user_remove_confirmation_message, person.getDisplayName()));
                }
            } else if (person.getPersonType() == Person.PersonType.VIEWER) {
                if (!ListenerUtil.mutListener.listen(10056)) {
                    builder.setMessage(R.string.viewer_remove_confirmation_message);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10055)) {
                    builder.setMessage(R.string.follower_remove_confirmation_message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10059)) {
            builder.setNegativeButton(R.string.cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(10061)) {
            builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(10060)) {
                        removeSelectedPerson();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10062)) {
            builder.show();
        }
    }

    private void removeSelectedPerson() {
        if (!ListenerUtil.mutListener.listen(10063)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        Person person = getCurrentPerson();
        if (!ListenerUtil.mutListener.listen(10064)) {
            if (person == null) {
                return;
            }
        }
        final Person.PersonType personType = person.getPersonType();
        final String displayName = person.getDisplayName();
        PeopleUtils.RemovePersonCallback callback = new PeopleUtils.RemovePersonCallback() {

            @Override
            public void onSuccess(long personID, int localTableBlogId) {
                if (!ListenerUtil.mutListener.listen(10066)) {
                    if (personType == Person.PersonType.USER) {
                        if (!ListenerUtil.mutListener.listen(10065)) {
                            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.PERSON_REMOVED, mSite);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10067)) {
                    // remove the person from db, navigate back to list fragment and refresh it
                    PeopleTable.deletePerson(personID, localTableBlogId, personType);
                }
                String message = getString(R.string.person_removed, displayName);
                if (!ListenerUtil.mutListener.listen(10068)) {
                    ToastUtils.showToast(PeopleManagementActivity.this, message, ToastUtils.Duration.LONG);
                }
                if (!ListenerUtil.mutListener.listen(10069)) {
                    navigateBackToPeopleListFragment();
                }
                if (!ListenerUtil.mutListener.listen(10070)) {
                    refreshPeopleListFragment();
                }
            }

            @Override
            public void onError() {
                int errorMessageRes;
                switch(personType) {
                    case USER:
                        errorMessageRes = R.string.error_remove_user;
                        break;
                    case VIEWER:
                        errorMessageRes = R.string.error_remove_viewer;
                        break;
                    default:
                        errorMessageRes = R.string.error_remove_follower;
                        break;
                }
                if (!ListenerUtil.mutListener.listen(10071)) {
                    ToastUtils.showToast(PeopleManagementActivity.this, errorMessageRes, ToastUtils.Duration.LONG);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(10076)) {
            if ((ListenerUtil.mutListener.listen(10072) ? (personType == Person.PersonType.FOLLOWER && personType == Person.PersonType.EMAIL_FOLLOWER) : (personType == Person.PersonType.FOLLOWER || personType == Person.PersonType.EMAIL_FOLLOWER))) {
                if (!ListenerUtil.mutListener.listen(10075)) {
                    PeopleUtils.removeFollower(mSite, person.getPersonID(), personType, callback);
                }
            } else if (personType == Person.PersonType.VIEWER) {
                if (!ListenerUtil.mutListener.listen(10074)) {
                    PeopleUtils.removeViewer(mSite, person.getPersonID(), callback);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10073)) {
                    PeopleUtils.removeUser(mSite, person.getPersonID(), callback);
                }
            }
        }
    }

    // This helper method is used after a successful network request
    private void refreshOnScreenFragmentDetails() {
        if (!ListenerUtil.mutListener.listen(10077)) {
            refreshPeopleListFragment();
        }
        if (!ListenerUtil.mutListener.listen(10078)) {
            refreshDetailFragment();
        }
    }

    private void refreshPeopleListFragment() {
        PeopleListFragment peopleListFragment = getListFragment();
        if (!ListenerUtil.mutListener.listen(10080)) {
            if (peopleListFragment != null) {
                if (!ListenerUtil.mutListener.listen(10079)) {
                    peopleListFragment.refreshPeopleList(false);
                }
            }
        }
    }

    private void refreshDetailFragment() {
        PersonDetailFragment personDetailFragment = getDetailFragment();
        if (!ListenerUtil.mutListener.listen(10082)) {
            if (personDetailFragment != null) {
                if (!ListenerUtil.mutListener.listen(10081)) {
                    personDetailFragment.refreshPersonDetails();
                }
            }
        }
    }

    private boolean navigateBackToPeopleListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(10091)) {
            if ((ListenerUtil.mutListener.listen(10087) ? (fragmentManager.getBackStackEntryCount() >= 0) : (ListenerUtil.mutListener.listen(10086) ? (fragmentManager.getBackStackEntryCount() <= 0) : (ListenerUtil.mutListener.listen(10085) ? (fragmentManager.getBackStackEntryCount() < 0) : (ListenerUtil.mutListener.listen(10084) ? (fragmentManager.getBackStackEntryCount() != 0) : (ListenerUtil.mutListener.listen(10083) ? (fragmentManager.getBackStackEntryCount() == 0) : (fragmentManager.getBackStackEntryCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(10088)) {
                    fragmentManager.popBackStack();
                }
                ActionBar actionBar = getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(10090)) {
                    if (actionBar != null) {
                        if (!ListenerUtil.mutListener.listen(10089)) {
                            actionBar.setTitle(R.string.people);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private Person getCurrentPerson() {
        PersonDetailFragment personDetailFragment = getDetailFragment();
        if (!ListenerUtil.mutListener.listen(10092)) {
            if (personDetailFragment == null) {
                return null;
            }
        }
        return personDetailFragment.loadPerson();
    }

    @Override
    public boolean onFetchFirstPage(PeopleListFilter filter) {
        if (!ListenerUtil.mutListener.listen(10097)) {
            if ((ListenerUtil.mutListener.listen(10093) ? (filter == PeopleListFilter.TEAM || !mHasRefreshedUsers) : (filter == PeopleListFilter.TEAM && !mHasRefreshedUsers))) {
                return fetchUsersList(mSite, 0);
            } else if ((ListenerUtil.mutListener.listen(10094) ? (filter == PeopleListFilter.FOLLOWERS || !mHasRefreshedFollowers) : (filter == PeopleListFilter.FOLLOWERS && !mHasRefreshedFollowers))) {
                return fetchFollowersList(mSite, 1);
            } else if ((ListenerUtil.mutListener.listen(10095) ? (filter == PeopleListFilter.EMAIL_FOLLOWERS || !mHasRefreshedEmailFollowers) : (filter == PeopleListFilter.EMAIL_FOLLOWERS && !mHasRefreshedEmailFollowers))) {
                return fetchEmailFollowersList(mSite, 1);
            } else if ((ListenerUtil.mutListener.listen(10096) ? (filter == PeopleListFilter.VIEWERS || !mHasRefreshedViewers) : (filter == PeopleListFilter.VIEWERS && !mHasRefreshedViewers))) {
                return fetchViewersList(mSite, 0);
            }
        }
        return false;
    }

    @Override
    public boolean onFetchMorePeople(PeopleListFilter filter) {
        if (!ListenerUtil.mutListener.listen(10110)) {
            if ((ListenerUtil.mutListener.listen(10098) ? (filter == PeopleListFilter.TEAM || !mUsersEndOfListReached) : (filter == PeopleListFilter.TEAM && !mUsersEndOfListReached))) {
                int count = PeopleTable.getUsersCountForLocalBlogId(mSite.getId());
                return fetchUsersList(mSite, count);
            } else if ((ListenerUtil.mutListener.listen(10099) ? (filter == PeopleListFilter.FOLLOWERS || !mFollowersEndOfListReached) : (filter == PeopleListFilter.FOLLOWERS && !mFollowersEndOfListReached))) {
                int pageToFetch = (ListenerUtil.mutListener.listen(10109) ? (mFollowersLastFetchedPage % 1) : (ListenerUtil.mutListener.listen(10108) ? (mFollowersLastFetchedPage / 1) : (ListenerUtil.mutListener.listen(10107) ? (mFollowersLastFetchedPage * 1) : (ListenerUtil.mutListener.listen(10106) ? (mFollowersLastFetchedPage - 1) : (mFollowersLastFetchedPage + 1)))));
                return fetchFollowersList(mSite, pageToFetch);
            } else if ((ListenerUtil.mutListener.listen(10100) ? (filter == PeopleListFilter.EMAIL_FOLLOWERS || !mEmailFollowersEndOfListReached) : (filter == PeopleListFilter.EMAIL_FOLLOWERS && !mEmailFollowersEndOfListReached))) {
                int pageToFetch = (ListenerUtil.mutListener.listen(10105) ? (mEmailFollowersLastFetchedPage % 1) : (ListenerUtil.mutListener.listen(10104) ? (mEmailFollowersLastFetchedPage / 1) : (ListenerUtil.mutListener.listen(10103) ? (mEmailFollowersLastFetchedPage * 1) : (ListenerUtil.mutListener.listen(10102) ? (mEmailFollowersLastFetchedPage - 1) : (mEmailFollowersLastFetchedPage + 1)))));
                return fetchEmailFollowersList(mSite, pageToFetch);
            } else if ((ListenerUtil.mutListener.listen(10101) ? (filter == PeopleListFilter.VIEWERS || !mViewersEndOfListReached) : (filter == PeopleListFilter.VIEWERS && !mViewersEndOfListReached))) {
                int count = PeopleTable.getViewersCountForLocalBlogId(mSite.getId());
                return fetchViewersList(mSite, count);
            }
        }
        return false;
    }

    private PeopleListFragment getListFragment() {
        return (PeopleListFragment) getSupportFragmentManager().findFragmentByTag(KEY_PEOPLE_LIST_FRAGMENT);
    }

    private PersonDetailFragment getDetailFragment() {
        return (PersonDetailFragment) getSupportFragmentManager().findFragmentByTag(KEY_PERSON_DETAIL_FRAGMENT);
    }

    public interface InvitationSender {

        void send();
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserRolesChanged(OnUserRolesChanged event) {
        if (!ListenerUtil.mutListener.listen(10112)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10111)) {
                    AppLog.e(AppLog.T.PEOPLE, "An error occurred while fetching the user roles with type: " + event.error.type);
                }
            }
        }
        PeopleListFragment peopleListFragment = getListFragment();
        if (!ListenerUtil.mutListener.listen(10114)) {
            if (peopleListFragment != null) {
                if (!ListenerUtil.mutListener.listen(10113)) {
                    peopleListFragment.refreshUserRoles();
                }
            }
        }
    }
}

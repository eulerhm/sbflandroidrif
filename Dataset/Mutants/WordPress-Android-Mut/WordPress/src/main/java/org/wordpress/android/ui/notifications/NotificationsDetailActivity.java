package org.wordpress.android.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.appbar.AppBarLayout;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.tools.FormattableRangeType;
import org.wordpress.android.models.Note;
import org.wordpress.android.push.GCMMessageHandler;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.ScrollableViewInitializedListener;
import org.wordpress.android.ui.WPWebViewActivity;
import org.wordpress.android.ui.comments.CommentActions;
import org.wordpress.android.ui.comments.CommentDetailFragment;
import org.wordpress.android.ui.engagement.EngagedPeopleListFragment;
import org.wordpress.android.ui.engagement.ListScenarioUtils;
import org.wordpress.android.ui.notifications.adapters.NotesAdapter;
import org.wordpress.android.ui.notifications.services.NotificationsUpdateServiceStarter;
import org.wordpress.android.ui.notifications.utils.NotificationsActions;
import org.wordpress.android.ui.notifications.utils.NotificationsUtils;
import org.wordpress.android.ui.posts.BasicFragmentDialog;
import org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogPositiveClickInterface;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.reader.ReaderPostDetailFragment;
import org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.stats.StatsViewType;
import org.wordpress.android.util.extensions.AppBarLayoutExtensionsKt;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils.AnalyticsCommentActionSource;
import org.wordpress.android.util.config.LikesEnhancementsFeatureConfig;
import org.wordpress.android.widgets.WPSwipeSnackbar;
import org.wordpress.android.widgets.WPViewPager;
import org.wordpress.android.widgets.WPViewPagerTransformer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import static org.wordpress.android.models.Note.NOTE_COMMENT_LIKE_TYPE;
import static org.wordpress.android.models.Note.NOTE_COMMENT_TYPE;
import static org.wordpress.android.models.Note.NOTE_FOLLOW_TYPE;
import static org.wordpress.android.models.Note.NOTE_LIKE_TYPE;
import static org.wordpress.android.ui.notifications.services.NotificationsUpdateServiceStarter.IS_TAPPED_ON_NOTIFICATION;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsDetailActivity extends LocaleAwareActivity implements CommentActions.OnNoteCommentActionListener, BasicFragmentDialog.BasicDialogPositiveClickInterface, ScrollableViewInitializedListener {

    private static final String ARG_TITLE = "activityTitle";

    private static final String DOMAIN_WPCOM = "wordpress.com";

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    GCMMessageHandler mGCMMessageHandler;

    @Inject
    ReaderTracker mReaderTracker;

    @Inject
    LikesEnhancementsFeatureConfig mLikesEnhancementsFeatureConfig;

    @Inject
    ListScenarioUtils mListScenarioUtils;

    private String mNoteId;

    private boolean mIsTappedOnNotification;

    private WPViewPager mViewPager;

    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    private NotificationDetailFragmentAdapter mAdapter;

    private AppBarLayout mAppBarLayout;

    private Toolbar mToolbar;

    @Override
    public void onBackPressed() {
        CollapseFullScreenDialogFragment fragment = (CollapseFullScreenDialogFragment) getSupportFragmentManager().findFragmentByTag(CollapseFullScreenDialogFragment.TAG);
        if (!ListenerUtil.mutListener.listen(9120)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(9119)) {
                    fragment.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9118)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9121)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9122)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(9123)) {
            AppLog.i(AppLog.T.NOTIFS, "Creating NotificationsDetailActivity");
        }
        if (!ListenerUtil.mutListener.listen(9124)) {
            setContentView(R.layout.notifications_detail_activity);
        }
        if (!ListenerUtil.mutListener.listen(9125)) {
            mToolbar = findViewById(R.id.toolbar_main);
        }
        if (!ListenerUtil.mutListener.listen(9126)) {
            setSupportActionBar(mToolbar);
        }
        if (!ListenerUtil.mutListener.listen(9127)) {
            mAppBarLayout = findViewById(R.id.appbar_main);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(9129)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(9128)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9137)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(9135)) {
                    mNoteId = getIntent().getStringExtra(NotificationsListFragment.NOTE_ID_EXTRA);
                }
                if (!ListenerUtil.mutListener.listen(9136)) {
                    mIsTappedOnNotification = getIntent().getBooleanExtra(IS_TAPPED_ON_NOTIFICATION, false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9132)) {
                    if ((ListenerUtil.mutListener.listen(9130) ? (savedInstanceState.containsKey(ARG_TITLE) || getSupportActionBar() != null) : (savedInstanceState.containsKey(ARG_TITLE) && getSupportActionBar() != null))) {
                        if (!ListenerUtil.mutListener.listen(9131)) {
                            getSupportActionBar().setTitle(StringUtils.notNullStr(savedInstanceState.getString(ARG_TITLE)));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9133)) {
                    mNoteId = savedInstanceState.getString(NotificationsListFragment.NOTE_ID_EXTRA);
                }
                if (!ListenerUtil.mutListener.listen(9134)) {
                    mIsTappedOnNotification = savedInstanceState.getBoolean(IS_TAPPED_ON_NOTIFICATION);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9138)) {
            // set up the viewpager and adapter for lateral navigation
            mViewPager = findViewById(R.id.viewpager);
        }
        if (!ListenerUtil.mutListener.listen(9139)) {
            mViewPager.setPageTransformer(false, new WPViewPagerTransformer(WPViewPagerTransformer.TransformType.SLIDE_OVER));
        }
        Note note = NotificationsTable.getNoteById(mNoteId);
        if (!ListenerUtil.mutListener.listen(9141)) {
            // updated since the notification was first received and created on the system's dashboard
            updateUIAndNote((ListenerUtil.mutListener.listen(9140) ? ((note == null) && mIsTappedOnNotification) : ((note == null) || mIsTappedOnNotification)));
        }
        if (!ListenerUtil.mutListener.listen(9143)) {
            // Hide the keyboard, unless we arrived here from the 'Reply' action in a push notification
            if (!getIntent().getBooleanExtra(NotificationsListFragment.NOTE_INSTANT_REPLY_EXTRA, false)) {
                if (!ListenerUtil.mutListener.listen(9142)) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9146)) {
            // track initial comment note view
            if ((ListenerUtil.mutListener.listen(9144) ? (savedInstanceState == null || note != null) : (savedInstanceState == null && note != null))) {
                if (!ListenerUtil.mutListener.listen(9145)) {
                    trackCommentNote(note);
                }
            }
        }
    }

    private void updateUIAndNote(boolean doRefresh) {
        if (!ListenerUtil.mutListener.listen(9148)) {
            if (mNoteId == null) {
                if (!ListenerUtil.mutListener.listen(9147)) {
                    showErrorToastAndFinish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9151)) {
            if (doRefresh) {
                if (!ListenerUtil.mutListener.listen(9149)) {
                    setProgressVisible(true);
                }
                if (!ListenerUtil.mutListener.listen(9150)) {
                    // here start the service and wait for it
                    NotificationsUpdateServiceStarter.startService(this, mNoteId);
                }
                return;
            }
        }
        Note note = NotificationsTable.getNoteById(mNoteId);
        if (!ListenerUtil.mutListener.listen(9153)) {
            if (note == null) {
                if (!ListenerUtil.mutListener.listen(9152)) {
                    // no note found
                    showErrorToastAndFinish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9155)) {
            // If not, just let it be.
            if (mAdapter != null) {
                Note currentNote = mAdapter.getNoteWithId(mNoteId);
                if (!ListenerUtil.mutListener.listen(9154)) {
                    if (note.equalsTimeAndLength(currentNote)) {
                        return;
                    }
                }
            }
        }
        NotesAdapter.FILTERS filter = NotesAdapter.FILTERS.FILTER_ALL;
        if (!ListenerUtil.mutListener.listen(9157)) {
            if (getIntent().hasExtra(NotificationsListFragment.NOTE_CURRENT_LIST_FILTER_EXTRA)) {
                if (!ListenerUtil.mutListener.listen(9156)) {
                    filter = (NotesAdapter.FILTERS) getIntent().getSerializableExtra(NotificationsListFragment.NOTE_CURRENT_LIST_FILTER_EXTRA);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9158)) {
            mAdapter = buildNoteListAdapterAndSetPosition(note, filter);
        }
        if (!ListenerUtil.mutListener.listen(9159)) {
            resetOnPageChangeListener();
        }
        if (!ListenerUtil.mutListener.listen(9160)) {
            // set title
            setActionBarTitleForNote(note);
        }
        if (!ListenerUtil.mutListener.listen(9161)) {
            markNoteAsRead(note);
        }
        if (!ListenerUtil.mutListener.listen(9162)) {
            // If `note.getTimestamp()` is not the most recent seen note, the server will discard the value.
            NotificationsActions.updateSeenTimestamp(note);
        }
        // analytics tracking
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9163)) {
            properties.put("notification_type", note.getType());
        }
        if (!ListenerUtil.mutListener.listen(9164)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATIONS_OPENED_NOTIFICATION_DETAILS, properties);
        }
        if (!ListenerUtil.mutListener.listen(9165)) {
            setProgressVisible(false);
        }
    }

    private void resetOnPageChangeListener() {
        if (!ListenerUtil.mutListener.listen(9175)) {
            if (mOnPageChangeListener != null) {
                if (!ListenerUtil.mutListener.listen(9174)) {
                    mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9173)) {
                    mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        }

                        @Override
                        public void onPageSelected(int position) {
                            Fragment fragment = mAdapter.getItem(mViewPager.getCurrentItem());
                            boolean hideToolbar = (fragment instanceof ReaderPostDetailFragment);
                            if (!ListenerUtil.mutListener.listen(9166)) {
                                showHideToolbar(hideToolbar);
                            }
                            if (!ListenerUtil.mutListener.listen(9167)) {
                                AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_SWIPE_PAGE_CHANGED);
                            }
                            // change the action bar title for the current note
                            Note currentNote = mAdapter.getNoteAtPosition(position);
                            if (!ListenerUtil.mutListener.listen(9172)) {
                                if (currentNote != null) {
                                    if (!ListenerUtil.mutListener.listen(9168)) {
                                        setActionBarTitleForNote(currentNote);
                                    }
                                    if (!ListenerUtil.mutListener.listen(9169)) {
                                        markNoteAsRead(currentNote);
                                    }
                                    if (!ListenerUtil.mutListener.listen(9170)) {
                                        NotificationsActions.updateSeenTimestamp(currentNote);
                                    }
                                    if (!ListenerUtil.mutListener.listen(9171)) {
                                        // track subsequent comment note views
                                        trackCommentNote(currentNote);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                        }
                    };
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9176)) {
            mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        }
    }

    private void trackCommentNote(@NotNull Note note) {
        if (!ListenerUtil.mutListener.listen(9178)) {
            if (note.isCommentType()) {
                SiteModel site = mSiteStore.getSiteBySiteId(note.getSiteId());
                if (!ListenerUtil.mutListener.listen(9177)) {
                    AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_VIEWED, AnalyticsCommentActionSource.NOTIFICATIONS, site);
                }
            }
        }
    }

    public void showHideToolbar(boolean hide) {
        if (!ListenerUtil.mutListener.listen(9184)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(9182)) {
                    if (hide) {
                        if (!ListenerUtil.mutListener.listen(9181)) {
                            getSupportActionBar().hide();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9179)) {
                            setSupportActionBar(mToolbar);
                        }
                        if (!ListenerUtil.mutListener.listen(9180)) {
                            getSupportActionBar().show();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9183)) {
                    getSupportActionBar().setDisplayShowTitleEnabled(!hide);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(9186)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(9185)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(9189)) {
            if ((ListenerUtil.mutListener.listen(9187) ? (getSupportActionBar() != null || getSupportActionBar().getTitle() != null) : (getSupportActionBar() != null && getSupportActionBar().getTitle() != null))) {
                if (!ListenerUtil.mutListener.listen(9188)) {
                    outState.putString(ARG_TITLE, getSupportActionBar().getTitle().toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9190)) {
            outState.putString(NotificationsListFragment.NOTE_ID_EXTRA, mNoteId);
        }
        if (!ListenerUtil.mutListener.listen(9191)) {
            outState.putBoolean(IS_TAPPED_ON_NOTIFICATION, mIsTappedOnNotification);
        }
        if (!ListenerUtil.mutListener.listen(9192)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(9193)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(9194)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(9204)) {
            // show a hint to promote swipe usage on the ViewPager
            if ((ListenerUtil.mutListener.listen(9201) ? ((ListenerUtil.mutListener.listen(9195) ? (!AppPrefs.isNotificationsSwipeToNavigateShown() || mAdapter != null) : (!AppPrefs.isNotificationsSwipeToNavigateShown() && mAdapter != null)) || (ListenerUtil.mutListener.listen(9200) ? (mAdapter.getCount() >= 1) : (ListenerUtil.mutListener.listen(9199) ? (mAdapter.getCount() <= 1) : (ListenerUtil.mutListener.listen(9198) ? (mAdapter.getCount() < 1) : (ListenerUtil.mutListener.listen(9197) ? (mAdapter.getCount() != 1) : (ListenerUtil.mutListener.listen(9196) ? (mAdapter.getCount() == 1) : (mAdapter.getCount() > 1))))))) : ((ListenerUtil.mutListener.listen(9195) ? (!AppPrefs.isNotificationsSwipeToNavigateShown() || mAdapter != null) : (!AppPrefs.isNotificationsSwipeToNavigateShown() && mAdapter != null)) && (ListenerUtil.mutListener.listen(9200) ? (mAdapter.getCount() >= 1) : (ListenerUtil.mutListener.listen(9199) ? (mAdapter.getCount() <= 1) : (ListenerUtil.mutListener.listen(9198) ? (mAdapter.getCount() < 1) : (ListenerUtil.mutListener.listen(9197) ? (mAdapter.getCount() != 1) : (ListenerUtil.mutListener.listen(9196) ? (mAdapter.getCount() == 1) : (mAdapter.getCount() > 1))))))))) {
                if (!ListenerUtil.mutListener.listen(9202)) {
                    WPSwipeSnackbar.show(mViewPager);
                }
                if (!ListenerUtil.mutListener.listen(9203)) {
                    AppPrefs.setNotificationsSwipeToNavigateShown(true);
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(9205)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(9206)) {
            super.onStop();
        }
    }

    private void showErrorToastAndFinish() {
        if (!ListenerUtil.mutListener.listen(9207)) {
            AppLog.e(AppLog.T.NOTIFS, "Note could not be found.");
        }
        if (!ListenerUtil.mutListener.listen(9208)) {
            ToastUtils.showToast(this, R.string.error_notification_open);
        }
        if (!ListenerUtil.mutListener.listen(9209)) {
            finish();
        }
    }

    private void markNoteAsRead(Note note) {
        if (!ListenerUtil.mutListener.listen(9210)) {
            mGCMMessageHandler.removeNotificationWithNoteIdFromSystemBar(this, note.getId());
        }
        if (!ListenerUtil.mutListener.listen(9215)) {
            // mark the note as read if it's unread
            if (note.isUnread()) {
                if (!ListenerUtil.mutListener.listen(9211)) {
                    NotificationsActions.markNoteAsRead(note);
                }
                if (!ListenerUtil.mutListener.listen(9212)) {
                    note.setRead();
                }
                if (!ListenerUtil.mutListener.listen(9213)) {
                    NotificationsTable.saveNote(note);
                }
                if (!ListenerUtil.mutListener.listen(9214)) {
                    EventBus.getDefault().post(new NotificationEvents.NotificationsChanged());
                }
            }
        }
    }

    private void setActionBarTitleForNote(Note note) {
        if (!ListenerUtil.mutListener.listen(9226)) {
            if (getSupportActionBar() != null) {
                String title = note.getTitle();
                if (!ListenerUtil.mutListener.listen(9221)) {
                    if (TextUtils.isEmpty(title)) {
                        if (!ListenerUtil.mutListener.listen(9220)) {
                            // set a default title if title is not set within the note
                            switch(note.getType()) {
                                case NOTE_FOLLOW_TYPE:
                                    if (!ListenerUtil.mutListener.listen(9216)) {
                                        title = getString(R.string.follows);
                                    }
                                    break;
                                case NOTE_COMMENT_LIKE_TYPE:
                                    if (!ListenerUtil.mutListener.listen(9217)) {
                                        title = getString(R.string.comment_likes);
                                    }
                                    break;
                                case NOTE_LIKE_TYPE:
                                    if (!ListenerUtil.mutListener.listen(9218)) {
                                        title = getString(R.string.like);
                                    }
                                    break;
                                case NOTE_COMMENT_TYPE:
                                    if (!ListenerUtil.mutListener.listen(9219)) {
                                        title = getString(R.string.comment);
                                    }
                                    break;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9223)) {
                    // Force change the Action Bar title for 'new_post' notifications.
                    if (note.isNewPostType()) {
                        if (!ListenerUtil.mutListener.listen(9222)) {
                            title = getString(R.string.reader_title_post_detail);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9224)) {
                    getSupportActionBar().setTitle(title);
                }
                if (!ListenerUtil.mutListener.listen(9225)) {
                    // important for accessibility - talkback
                    setTitle(getString(R.string.notif_detail_screen_title, title));
                }
            }
        }
    }

    private NotificationDetailFragmentAdapter buildNoteListAdapterAndSetPosition(Note note, NotesAdapter.FILTERS filter) {
        NotificationDetailFragmentAdapter adapter;
        ArrayList<Note> notes = NotificationsTable.getLatestNotes();
        ArrayList<Note> filteredNotes = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9227)) {
            // apply filter to the list so we show the same items that the list show vertically, but horizontally
            NotesAdapter.buildFilteredNotesList(filteredNotes, notes, filter);
        }
        adapter = new NotificationDetailFragmentAdapter(getSupportFragmentManager(), filteredNotes);
        if (!ListenerUtil.mutListener.listen(9228)) {
            mViewPager.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(9229)) {
            mViewPager.setCurrentItem(NotificationsUtils.findNoteInNoteArray(filteredNotes, note.getId()));
        }
        return adapter;
    }

    /**
     * Tries to pick the correct fragment detail type for a given note
     * Defaults to NotificationDetailListFragment
     */
    private Fragment getDetailFragmentForNote(Note note) {
        if (!ListenerUtil.mutListener.listen(9230)) {
            if (note == null) {
                return null;
            }
        }
        Fragment fragment;
        if (note.isCommentType()) {
            // show comment detail for comment notifications
            boolean isInstantReply = getIntent().getBooleanExtra(NotificationsListFragment.NOTE_INSTANT_REPLY_EXTRA, false);
            fragment = CommentDetailFragment.newInstance(note.getId(), getIntent().getStringExtra(NotificationsListFragment.NOTE_PREFILLED_REPLY_EXTRA));
            if (!ListenerUtil.mutListener.listen(9235)) {
                if (isInstantReply) {
                    if (!ListenerUtil.mutListener.listen(9234)) {
                        ((CommentDetailFragment) fragment).enableShouldFocusReplyField();
                    }
                }
            }
        } else if (note.isAutomattcherType()) {
            // automattchers are handled by note.isCommentType() above
            boolean isPost = ((ListenerUtil.mutListener.listen(9233) ? ((ListenerUtil.mutListener.listen(9232) ? (note.getSiteId() != 0 || note.getPostId() != 0) : (note.getSiteId() != 0 && note.getPostId() != 0)) || note.getCommentId() == 0) : ((ListenerUtil.mutListener.listen(9232) ? (note.getSiteId() != 0 || note.getPostId() != 0) : (note.getSiteId() != 0 && note.getPostId() != 0)) && note.getCommentId() == 0)));
            if (isPost) {
                fragment = ReaderPostDetailFragment.Companion.newInstance(note.getSiteId(), note.getPostId());
            } else {
                fragment = NotificationsDetailListFragment.newInstance(note.getId());
            }
        } else if (note.isNewPostType()) {
            fragment = ReaderPostDetailFragment.Companion.newInstance(note.getSiteId(), note.getPostId());
        } else {
            if ((ListenerUtil.mutListener.listen(9231) ? (mLikesEnhancementsFeatureConfig.isEnabled() || note.isLikeType()) : (mLikesEnhancementsFeatureConfig.isEnabled() && note.isLikeType()))) {
                fragment = EngagedPeopleListFragment.newInstance(mListScenarioUtils.mapLikeNoteToListScenario(note, this));
            } else {
                fragment = NotificationsDetailListFragment.newInstance(note.getId());
            }
        }
        return fragment;
    }

    public void showBlogPreviewActivity(long siteId, @Nullable Boolean isFollowed) {
        if (!ListenerUtil.mutListener.listen(9236)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9237)) {
            ReaderActivityLauncher.showReaderBlogPreview(this, siteId, isFollowed, ReaderTracker.SOURCE_NOTIFICATION, mReaderTracker);
        }
    }

    public void showPostActivity(long siteId, long postId) {
        if (!ListenerUtil.mutListener.listen(9238)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9239)) {
            ReaderActivityLauncher.showReaderPostDetail(this, siteId, postId);
        }
    }

    public void showScanActivityForSite(long siteId) {
        SiteModel site = getSiteOrToast(siteId);
        if (!ListenerUtil.mutListener.listen(9241)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(9240)) {
                    ActivityLauncher.viewScan(this, site);
                }
            }
        }
    }

    public void showStatsActivityForSite(long siteId, FormattableRangeType rangeType) {
        SiteModel site = getSiteOrToast(siteId);
        if (!ListenerUtil.mutListener.listen(9243)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(9242)) {
                    showStatsActivityForSite(site, rangeType);
                }
            }
        }
    }

    public void showBackupForSite(long siteId) {
        SiteModel site = getSiteOrToast(siteId);
        if (!ListenerUtil.mutListener.listen(9245)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(9244)) {
                    showBackupActivityForSite(site);
                }
            }
        }
    }

    @Nullable
    private SiteModel getSiteOrToast(long siteId) {
        SiteModel site = mSiteStore.getSiteBySiteId(siteId);
        if (!ListenerUtil.mutListener.listen(9247)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(9246)) {
                    // but the site list is not yet updated in the app.
                    ToastUtils.showToast(this, R.string.blog_not_found);
                }
            }
        }
        return site;
    }

    private void showStatsActivityForSite(@NonNull SiteModel site, FormattableRangeType rangeType) {
        if (!ListenerUtil.mutListener.listen(9248)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9251)) {
            if (rangeType == FormattableRangeType.FOLLOW) {
                if (!ListenerUtil.mutListener.listen(9250)) {
                    ActivityLauncher.viewAllTabbedInsightsStats(this, StatsViewType.FOLLOWERS, 0, site.getId());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9249)) {
                    ActivityLauncher.viewBlogStats(this, site);
                }
            }
        }
    }

    private void showBackupActivityForSite(@NonNull SiteModel site) {
        if (!ListenerUtil.mutListener.listen(9252)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9253)) {
            ActivityLauncher.viewBackupList(this, site);
        }
    }

    public void showWebViewActivityForUrl(String url) {
        if (!ListenerUtil.mutListener.listen(9255)) {
            if ((ListenerUtil.mutListener.listen(9254) ? (isFinishing() && url == null) : (isFinishing() || url == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9258)) {
            if (url.contains(DOMAIN_WPCOM)) {
                if (!ListenerUtil.mutListener.listen(9257)) {
                    WPWebViewActivity.openUrlByUsingGlobalWPCOMCredentials(this, url);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9256)) {
                    WPWebViewActivity.openURL(this, url);
                }
            }
        }
    }

    public void showReaderPostLikeUsers(long blogId, long postId) {
        if (!ListenerUtil.mutListener.listen(9259)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9260)) {
            ReaderActivityLauncher.showReaderLikingUsers(this, blogId, postId);
        }
    }

    public void showReaderCommentsList(long siteId, long postId, long commentId) {
        if (!ListenerUtil.mutListener.listen(9261)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9262)) {
            ReaderActivityLauncher.showReaderComments(this, siteId, postId, commentId, ThreadedCommentsActionSource.COMMENT_NOTIFICATION.getSourceDescription());
        }
    }

    private void setProgressVisible(boolean visible) {
        final ProgressBar progress = findViewById(R.id.progress_loading);
        if (!ListenerUtil.mutListener.listen(9264)) {
            if (progress != null) {
                if (!ListenerUtil.mutListener.listen(9263)) {
                    progress.setVisibility(visible ? View.VISIBLE : View.GONE);
                }
            }
        }
    }

    @Override
    public void onModerateCommentForNote(Note note, CommentStatus newStatus) {
        Intent resultIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(9265)) {
            resultIntent.putExtra(NotificationsListFragment.NOTE_MODERATE_ID_EXTRA, note.getId());
        }
        if (!ListenerUtil.mutListener.listen(9266)) {
            resultIntent.putExtra(NotificationsListFragment.NOTE_MODERATE_STATUS_EXTRA, newStatus.toString());
        }
        if (!ListenerUtil.mutListener.listen(9267)) {
            setResult(RESULT_OK, resultIntent);
        }
        if (!ListenerUtil.mutListener.listen(9268)) {
            finish();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final NotificationEvents.NotificationsRefreshCompleted event) {
        if (!ListenerUtil.mutListener.listen(9269)) {
            setProgressVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(9270)) {
            updateUIAndNote(false);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotificationEvents.NotificationsRefreshError error) {
        if (!ListenerUtil.mutListener.listen(9271)) {
            setProgressVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(9273)) {
            if (mNoteId == null) {
                if (!ListenerUtil.mutListener.listen(9272)) {
                    showErrorToastAndFinish();
                }
                return;
            }
        }
        Note note = NotificationsTable.getNoteById(mNoteId);
        if (!ListenerUtil.mutListener.listen(9275)) {
            if (note == null) {
                if (!ListenerUtil.mutListener.listen(9274)) {
                    // no note found
                    showErrorToastAndFinish();
                }
                return;
            }
        }
    }

    @Override
    public void onPositiveClicked(@NotNull String instanceTag) {
        Fragment fragment = mAdapter.getItem(mViewPager.getCurrentItem());
        if (!ListenerUtil.mutListener.listen(9277)) {
            if (fragment instanceof BasicFragmentDialog.BasicDialogPositiveClickInterface) {
                if (!ListenerUtil.mutListener.listen(9276)) {
                    ((BasicDialogPositiveClickInterface) fragment).onPositiveClicked(instanceTag);
                }
            }
        }
    }

    @Override
    public void onScrollableViewInitialized(int containerId) {
        if (!ListenerUtil.mutListener.listen(9278)) {
            AppBarLayoutExtensionsKt.setLiftOnScrollTargetViewIdAndRequestLayout(mAppBarLayout, containerId);
        }
    }

    private class NotificationDetailFragmentAdapter extends FragmentStatePagerAdapter {

        private final ArrayList<Note> mNoteList;

        NotificationDetailFragmentAdapter(FragmentManager fm, ArrayList<Note> notes) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mNoteList = (ArrayList<Note>) notes.clone();
        }

        @Override
        public Fragment getItem(int position) {
            return getDetailFragmentForNote(mNoteList.get(position));
        }

        @Override
        public int getCount() {
            return mNoteList.size();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            // https://code.google.com/p/android/issues/detail?id=42601
            try {
                if (!ListenerUtil.mutListener.listen(9280)) {
                    AppLog.d(AppLog.T.NOTIFS, "notifications pager > adapter restoreState");
                }
                if (!ListenerUtil.mutListener.listen(9281)) {
                    super.restoreState(state, loader);
                }
            } catch (IllegalStateException e) {
                if (!ListenerUtil.mutListener.listen(9279)) {
                    AppLog.e(AppLog.T.NOTIFS, e);
                }
            }
        }

        @Override
        public Parcelable saveState() {
            if (!ListenerUtil.mutListener.listen(9282)) {
                AppLog.d(AppLog.T.NOTIFS, "notifications pager > adapter saveState");
            }
            Bundle bundle = (Bundle) super.saveState();
            if (!ListenerUtil.mutListener.listen(9284)) {
                if (bundle == null) {
                    if (!ListenerUtil.mutListener.listen(9283)) {
                        bundle = new Bundle();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9285)) {
                // See https://issuetracker.google.com/issues/37103380#comment77 for more details
                bundle.putParcelableArray("states", null);
            }
            return bundle;
        }

        boolean isValidPosition(int position) {
            return ((ListenerUtil.mutListener.listen(9296) ? ((ListenerUtil.mutListener.listen(9290) ? (position <= 0) : (ListenerUtil.mutListener.listen(9289) ? (position > 0) : (ListenerUtil.mutListener.listen(9288) ? (position < 0) : (ListenerUtil.mutListener.listen(9287) ? (position != 0) : (ListenerUtil.mutListener.listen(9286) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(9295) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(9294) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(9293) ? (position > getCount()) : (ListenerUtil.mutListener.listen(9292) ? (position != getCount()) : (ListenerUtil.mutListener.listen(9291) ? (position == getCount()) : (position < getCount()))))))) : ((ListenerUtil.mutListener.listen(9290) ? (position <= 0) : (ListenerUtil.mutListener.listen(9289) ? (position > 0) : (ListenerUtil.mutListener.listen(9288) ? (position < 0) : (ListenerUtil.mutListener.listen(9287) ? (position != 0) : (ListenerUtil.mutListener.listen(9286) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(9295) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(9294) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(9293) ? (position > getCount()) : (ListenerUtil.mutListener.listen(9292) ? (position != getCount()) : (ListenerUtil.mutListener.listen(9291) ? (position == getCount()) : (position < getCount())))))))));
        }

        private Note getNoteAtPosition(int position) {
            if (isValidPosition(position)) {
                return mNoteList.get(position);
            } else {
                return null;
            }
        }

        private Note getNoteWithId(String id) {
            if (!ListenerUtil.mutListener.listen(9298)) {
                {
                    long _loopCounter179 = 0;
                    for (Note note : mNoteList) {
                        ListenerUtil.loopListener.listen("_loopCounter179", ++_loopCounter179);
                        if (!ListenerUtil.mutListener.listen(9297)) {
                            if (note.getId().equalsIgnoreCase(id)) {
                                return note;
                            }
                        }
                    }
                }
            }
            return null;
        }
    }
}

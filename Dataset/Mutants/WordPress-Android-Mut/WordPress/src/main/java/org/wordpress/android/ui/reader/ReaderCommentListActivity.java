package org.wordpress.android.ui.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback;
import com.google.android.material.snackbar.Snackbar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.ReaderCommentTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.UserSuggestionTable;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.ReaderComment;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.UserSuggestion;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment.Builder;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment.OnCollapseListener;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment.OnConfirmListener;
import org.wordpress.android.ui.CommentFullScreenDialogFragment;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.comments.unified.CommentIdentifier.ReaderCommentIdentifier;
import org.wordpress.android.ui.comments.unified.UnifiedCommentsEditActivity;
import org.wordpress.android.ui.reader.ReaderCommentListViewModel.ScrollPosition;
import org.wordpress.android.ui.reader.ReaderPostPagerActivity.DirectOperation;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderCommentActions;
import org.wordpress.android.ui.reader.actions.ReaderPostActions;
import org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter;
import org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType;
import org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource;
import org.wordpress.android.ui.reader.services.comment.ReaderCommentService;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.viewmodels.ConversationNotificationsViewModel;
import org.wordpress.android.ui.reader.views.ReaderRecyclerView;
import org.wordpress.android.ui.suggestion.Suggestion;
import org.wordpress.android.ui.suggestion.adapters.SuggestionAdapter;
import org.wordpress.android.ui.suggestion.service.SuggestionEvents;
import org.wordpress.android.ui.suggestion.util.SuggestionServiceConnectionManager;
import org.wordpress.android.ui.suggestion.util.SuggestionUtils;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.EditTextUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.util.WPActivityUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils.AnalyticsCommentActionSource;
import org.wordpress.android.util.helpers.SwipeToRefreshHelper;
import org.wordpress.android.widgets.RecyclerItemDecoration;
import org.wordpress.android.widgets.SuggestionAutoCompleteText;
import org.wordpress.android.widgets.WPSnackbar;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import static org.wordpress.android.ui.CommentFullScreenDialogFragment.RESULT_REPLY;
import static org.wordpress.android.ui.CommentFullScreenDialogFragment.RESULT_SELECTION_END;
import static org.wordpress.android.ui.CommentFullScreenDialogFragment.RESULT_SELECTION_START;
import static org.wordpress.android.ui.reader.FollowConversationUiStateKt.FOLLOW_CONVERSATION_UI_STATE_FLAGS_KEY;
import static org.wordpress.android.util.WPSwipeToRefreshHelper.buildSwipeToRefreshHelper;
import kotlin.Unit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderCommentListActivity extends LocaleAwareActivity implements OnConfirmListener, OnCollapseListener {

    private static final String KEY_REPLY_TO_COMMENT_ID = "reply_to_comment_id";

    private static final String KEY_HAS_UPDATED_COMMENTS = "has_updated_comments";

    private static final String NOTIFICATIONS_BOTTOM_SHEET_TAG = "NOTIFICATIONS_BOTTOM_SHEET_TAG";

    private long mPostId;

    private long mBlogId;

    private ReaderPost mPost;

    private ReaderCommentAdapter mCommentAdapter;

    private SuggestionAdapter mSuggestionAdapter;

    private SuggestionServiceConnectionManager mSuggestionServiceConnectionManager;

    private SwipeToRefreshHelper mSwipeToRefreshHelper;

    private ReaderRecyclerView mRecyclerView;

    private CoordinatorLayout mCoordinator;

    private SuggestionAutoCompleteText mEditComment;

    private View mSubmitReplyBtn;

    private ViewGroup mCommentBox;

    private boolean mIsUpdatingComments;

    private boolean mHasUpdatedComments;

    private boolean mIsSubmittingComment;

    private boolean mUpdateOnResume;

    private DirectOperation mDirectOperation;

    private long mReplyToCommentId;

    private long mCommentId;

    private int mRestorePosition;

    private String mInterceptedUri;

    private String mSource;

    @Inject
    AccountStore mAccountStore;

    @Inject
    UiHelpers mUiHelpers;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    ReaderTracker mReaderTracker;

    @Inject
    SiteStore mSiteStore;

    private ReaderCommentListViewModel mViewModel;

    private ConversationNotificationsViewModel mConversationViewModel;

    @Override
    public void onBackPressed() {
        CollapseFullScreenDialogFragment fragment = (CollapseFullScreenDialogFragment) getSupportFragmentManager().findFragmentByTag(CollapseFullScreenDialogFragment.TAG);
        if (!ListenerUtil.mutListener.listen(20546)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(20545)) {
                    fragment.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20544)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(20547)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(20548)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(20549)) {
            setContentView(R.layout.reader_activity_comment_list);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(20550)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(20553)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(20551)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(20552)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20554)) {
            initViewModel();
        }
        if (!ListenerUtil.mutListener.listen(20555)) {
            initObservers(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(20556)) {
            mSwipeToRefreshHelper = buildSwipeToRefreshHelper(findViewById(R.id.swipe_to_refresh), () -> {
                mConversationViewModel.onRefresh();
                updatePostAndComments();
            });
        }
        if (!ListenerUtil.mutListener.listen(20557)) {
            mCoordinator = findViewById(R.id.coordinator_layout);
        }
        if (!ListenerUtil.mutListener.listen(20558)) {
            mRecyclerView = findViewById(R.id.recycler_view);
        }
        int spacingHorizontal = 0;
        int spacingVertical = DisplayUtils.dpToPx(this, 1);
        if (!ListenerUtil.mutListener.listen(20559)) {
            mRecyclerView.addItemDecoration(new RecyclerItemDecoration(spacingHorizontal, spacingVertical));
        }
        if (!ListenerUtil.mutListener.listen(20560)) {
            mCommentBox = findViewById(R.id.layout_comment_box);
        }
        if (!ListenerUtil.mutListener.listen(20561)) {
            mEditComment = mCommentBox.findViewById(R.id.edit_comment);
        }
        if (!ListenerUtil.mutListener.listen(20562)) {
            mEditComment.initializeWithPrefix('@');
        }
        if (!ListenerUtil.mutListener.listen(20563)) {
            mEditComment.getAutoSaveTextHelper().setUniqueId(String.format(Locale.US, "%d%d", mPostId, mBlogId));
        }
        if (!ListenerUtil.mutListener.listen(20565)) {
            mEditComment.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(20564)) {
                        mSubmitReplyBtn.setEnabled(!TextUtils.isEmpty(s.toString().trim()));
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(20566)) {
            mSubmitReplyBtn = mCommentBox.findViewById(R.id.btn_submit_reply);
        }
        if (!ListenerUtil.mutListener.listen(20567)) {
            mSubmitReplyBtn.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(20568)) {
            mSubmitReplyBtn.setOnLongClickListener(view -> {
                if (view.isHapticFeedbackEnabled()) {
                    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                }
                Toast.makeText(view.getContext(), R.string.send, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(20569)) {
            ViewExtensionsKt.redirectContextClickToLongPressListener(mSubmitReplyBtn);
        }
        if (!ListenerUtil.mutListener.listen(20572)) {
            if (!loadPost()) {
                if (!ListenerUtil.mutListener.listen(20570)) {
                    ToastUtils.showToast(this, R.string.reader_toast_err_get_post);
                }
                if (!ListenerUtil.mutListener.listen(20571)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20573)) {
            mRecyclerView.setAdapter(getCommentAdapter());
        }
        if (!ListenerUtil.mutListener.listen(20575)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(20574)) {
                    setReplyToCommentId(savedInstanceState.getLong(KEY_REPLY_TO_COMMENT_ID), false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20576)) {
            // update the post and its comments upon creation
            mUpdateOnResume = (savedInstanceState == null);
        }
        if (!ListenerUtil.mutListener.listen(20577)) {
            mSuggestionServiceConnectionManager = new SuggestionServiceConnectionManager(this, mBlogId);
        }
        if (!ListenerUtil.mutListener.listen(20578)) {
            mSuggestionAdapter = SuggestionUtils.setupUserSuggestions(mBlogId, this, mSuggestionServiceConnectionManager, mPost.isWP());
        }
        if (!ListenerUtil.mutListener.listen(20580)) {
            if (mSuggestionAdapter != null) {
                if (!ListenerUtil.mutListener.listen(20579)) {
                    mEditComment.setAdapter(mSuggestionAdapter);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20581)) {
            mReaderTracker.trackPost(AnalyticsTracker.Stat.READER_ARTICLE_COMMENTS_OPENED, mPost, mSource);
        }
        ImageView buttonExpand = findViewById(R.id.button_expand);
        if (!ListenerUtil.mutListener.listen(20582)) {
            buttonExpand.setOnClickListener(v -> {
                Bundle bundle = CommentFullScreenDialogFragment.Companion.newBundle(mEditComment.getText().toString(), mEditComment.getSelectionStart(), mEditComment.getSelectionEnd(), mBlogId);
                new Builder(ReaderCommentListActivity.this).setTitle(R.string.comment).setOnCollapseListener(this).setOnConfirmListener(this).setContent(CommentFullScreenDialogFragment.class, bundle).setAction(R.string.send).setHideActivityBar(true).build().show(getSupportFragmentManager(), CollapseFullScreenDialogFragment.TAG);
            });
        }
        if (!ListenerUtil.mutListener.listen(20583)) {
            buttonExpand.setOnLongClickListener(view -> {
                if (view.isHapticFeedbackEnabled()) {
                    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                }
                Toast.makeText(view.getContext(), R.string.description_expand, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(20584)) {
            ViewExtensionsKt.redirectContextClickToLongPressListener(buttonExpand);
        }
        // reattach listeners to collapsible reply dialog
        CollapseFullScreenDialogFragment fragment = (CollapseFullScreenDialogFragment) getSupportFragmentManager().findFragmentByTag(CollapseFullScreenDialogFragment.TAG);
        if (!ListenerUtil.mutListener.listen(20588)) {
            if ((ListenerUtil.mutListener.listen(20585) ? (fragment != null || fragment.isAdded()) : (fragment != null && fragment.isAdded()))) {
                if (!ListenerUtil.mutListener.listen(20586)) {
                    fragment.setOnCollapseListener(this);
                }
                if (!ListenerUtil.mutListener.listen(20587)) {
                    fragment.setOnConfirmListener(this);
                }
            }
        }
    }

    private void initViewModel() {
        if (!ListenerUtil.mutListener.listen(20589)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(ReaderCommentListViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(20590)) {
            mConversationViewModel = new ViewModelProvider(this, mViewModelFactory).get(ConversationNotificationsViewModel.class);
        }
    }

    private void initObservers(Bundle savedInstanceState) {
        AppBarLayout appBarLayout = findViewById(R.id.appbar_main);
        if (!ListenerUtil.mutListener.listen(20591)) {
            mViewModel.getScrollTo().observe(this, scrollPositionEvent -> {
                ScrollPosition content = scrollPositionEvent.getContentIfNotHandled();
                LayoutManager layoutManager = mRecyclerView.getLayoutManager();
                if (content != null && layoutManager != null) {
                    if (content.isSmooth()) {
                        RecyclerView.SmoothScroller smoothScrollerToTop = new LinearSmoothScroller(this) {

                            @Override
                            protected int getVerticalSnapPreference() {
                                return LinearSmoothScroller.SNAP_TO_START;
                            }
                        };
                        smoothScrollerToTop.setTargetPosition(content.getPosition());
                        layoutManager.startSmoothScroll(smoothScrollerToTop);
                    } else {
                        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(content.getPosition(), 0);
                    }
                    appBarLayout.post(appBarLayout::requestLayout);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(20592)) {
            mConversationViewModel.getSnackbarEvents().observe(this, snackbarMessageHolderEvent -> {
                FragmentManager fm = getSupportFragmentManager();
                CommentNotificationsBottomSheetFragment bottomSheet = (CommentNotificationsBottomSheetFragment) fm.findFragmentByTag(NOTIFICATIONS_BOTTOM_SHEET_TAG);
                if (bottomSheet != null)
                    return;
                snackbarMessageHolderEvent.applyIfNotHandled(holder -> {
                    WPSnackbar.make(mCoordinator, mUiHelpers.getTextOfUiString(ReaderCommentListActivity.this, holder.getMessage()), Snackbar.LENGTH_LONG).setAction(holder.getButtonTitle() != null ? mUiHelpers.getTextOfUiString(ReaderCommentListActivity.this, holder.getButtonTitle()) : null, v -> holder.getButtonAction().invoke()).show();
                    return Unit.INSTANCE;
                });
            });
        }
        if (!ListenerUtil.mutListener.listen(20593)) {
            mConversationViewModel.getShowBottomSheetEvent().observe(this, event -> event.applyIfNotHandled(isShowingData -> {
                FragmentManager fm = getSupportFragmentManager();
                CommentNotificationsBottomSheetFragment bottomSheet = (CommentNotificationsBottomSheetFragment) fm.findFragmentByTag(NOTIFICATIONS_BOTTOM_SHEET_TAG);
                if (isShowingData.getShow() && bottomSheet == null) {
                    bottomSheet = CommentNotificationsBottomSheetFragment.newInstance(isShowingData.isReceivingNotifications(), false);
                    bottomSheet.show(fm, NOTIFICATIONS_BOTTOM_SHEET_TAG);
                } else if (!isShowingData.getShow() && bottomSheet != null) {
                    bottomSheet.dismiss();
                }
                return Unit.INSTANCE;
            }));
        }
        if (!ListenerUtil.mutListener.listen(20606)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(20600)) {
                    mBlogId = savedInstanceState.getLong(ReaderConstants.ARG_BLOG_ID);
                }
                if (!ListenerUtil.mutListener.listen(20601)) {
                    mPostId = savedInstanceState.getLong(ReaderConstants.ARG_POST_ID);
                }
                if (!ListenerUtil.mutListener.listen(20602)) {
                    mRestorePosition = savedInstanceState.getInt(ReaderConstants.KEY_RESTORE_POSITION);
                }
                if (!ListenerUtil.mutListener.listen(20603)) {
                    mHasUpdatedComments = savedInstanceState.getBoolean(KEY_HAS_UPDATED_COMMENTS);
                }
                if (!ListenerUtil.mutListener.listen(20604)) {
                    mInterceptedUri = savedInstanceState.getString(ReaderConstants.ARG_INTERCEPTED_URI);
                }
                if (!ListenerUtil.mutListener.listen(20605)) {
                    mSource = savedInstanceState.getString(ReaderConstants.ARG_SOURCE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20594)) {
                    mBlogId = getIntent().getLongExtra(ReaderConstants.ARG_BLOG_ID, 0);
                }
                if (!ListenerUtil.mutListener.listen(20595)) {
                    mPostId = getIntent().getLongExtra(ReaderConstants.ARG_POST_ID, 0);
                }
                if (!ListenerUtil.mutListener.listen(20596)) {
                    mDirectOperation = (DirectOperation) getIntent().getSerializableExtra(ReaderConstants.ARG_DIRECT_OPERATION);
                }
                if (!ListenerUtil.mutListener.listen(20597)) {
                    mCommentId = getIntent().getLongExtra(ReaderConstants.ARG_COMMENT_ID, 0);
                }
                if (!ListenerUtil.mutListener.listen(20598)) {
                    mInterceptedUri = getIntent().getStringExtra(ReaderConstants.ARG_INTERCEPTED_URI);
                }
                if (!ListenerUtil.mutListener.listen(20599)) {
                    mSource = getIntent().getStringExtra(ReaderConstants.ARG_SOURCE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20607)) {
            mConversationViewModel.start(mBlogId, mPostId, ThreadedCommentsActionSource.READER_THREADED_COMMENTS);
        }
    }

    @Override
    public void onCollapse(@Nullable Bundle result) {
        if (!ListenerUtil.mutListener.listen(20611)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(20608)) {
                    mEditComment.setText(result.getString(RESULT_REPLY));
                }
                if (!ListenerUtil.mutListener.listen(20609)) {
                    mEditComment.setSelection(result.getInt(RESULT_SELECTION_START), result.getInt(RESULT_SELECTION_END));
                }
                if (!ListenerUtil.mutListener.listen(20610)) {
                    mEditComment.requestFocus();
                }
            }
        }
    }

    @Override
    public void onConfirm(@Nullable Bundle result) {
        if (!ListenerUtil.mutListener.listen(20614)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(20612)) {
                    mEditComment.setText(result.getString(RESULT_REPLY));
                }
                if (!ListenerUtil.mutListener.listen(20613)) {
                    submitComment();
                }
            }
        }
    }

    private final View.OnClickListener mSignInClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!ListenerUtil.mutListener.listen(20615)) {
                if (isFinishing()) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(20616)) {
                mReaderTracker.trackUri(AnalyticsTracker.Stat.READER_SIGN_IN_INITIATED, mInterceptedUri);
            }
            if (!ListenerUtil.mutListener.listen(20617)) {
                ActivityLauncher.loginWithoutMagicLink(ReaderCommentListActivity.this);
            }
        }
    };

    // to do a complete refresh we need to get updated post and new comments
    private void updatePostAndComments() {
        if (!ListenerUtil.mutListener.listen(20618)) {
            ReaderPostActions.updatePost(mPost, result -> {
                if (!isFinishing() && result.isNewOrChanged()) {
                    // get the updated post and pass it to the adapter
                    ReaderPost post = ReaderPostTable.getBlogPost(mBlogId, mPostId, false);
                    if (post != null) {
                        getCommentAdapter().setPost(post);
                        mPost = post;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(20619)) {
            // load the first page of comments
            updateComments(true, false);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(20620)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(20621)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(20622)) {
            refreshComments();
        }
        if (!ListenerUtil.mutListener.listen(20626)) {
            if ((ListenerUtil.mutListener.listen(20623) ? (mUpdateOnResume || NetworkUtils.isNetworkAvailable(this)) : (mUpdateOnResume && NetworkUtils.isNetworkAvailable(this)))) {
                if (!ListenerUtil.mutListener.listen(20624)) {
                    updatePostAndComments();
                }
                if (!ListenerUtil.mutListener.listen(20625)) {
                    mUpdateOnResume = false;
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(SuggestionEvents.SuggestionNameListUpdated event) {
        if (!ListenerUtil.mutListener.listen(20630)) {
            // check if the updated suggestions are for the current blog and update the suggestions
            if ((ListenerUtil.mutListener.listen(20628) ? ((ListenerUtil.mutListener.listen(20627) ? (event.mRemoteBlogId != 0 || event.mRemoteBlogId == mBlogId) : (event.mRemoteBlogId != 0 && event.mRemoteBlogId == mBlogId)) || mSuggestionAdapter != null) : ((ListenerUtil.mutListener.listen(20627) ? (event.mRemoteBlogId != 0 || event.mRemoteBlogId == mBlogId) : (event.mRemoteBlogId != 0 && event.mRemoteBlogId == mBlogId)) && mSuggestionAdapter != null))) {
                List<UserSuggestion> userSuggestions = UserSuggestionTable.getSuggestionsForSite(event.mRemoteBlogId);
                List<Suggestion> suggestions = Suggestion.Companion.fromUserSuggestions(userSuggestions);
                if (!ListenerUtil.mutListener.listen(20629)) {
                    mSuggestionAdapter.setSuggestionList(suggestions);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(20631)) {
            super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(20632)) {
            inflater.inflate(R.menu.threaded_comments_menu, menu);
        }
        if (!ListenerUtil.mutListener.listen(20633)) {
            mConversationViewModel.getUpdateFollowUiState().observe(this, uiState -> {
                if (menu != null) {
                    MenuItem bellItem = menu.findItem(R.id.manage_notifications_item);
                    MenuItem followItem = menu.findItem(R.id.follow_item);
                    if (bellItem != null && followItem != null) {
                        ShimmerFrameLayout shimmerView = followItem.getActionView().findViewById(R.id.shimmer_view_container);
                        TextView followText = followItem.getActionView().findViewById(R.id.follow_button);
                        followItem.getActionView().setOnClickListener(uiState.getOnFollowTapped() != null ? v -> uiState.getOnFollowTapped().invoke() : null);
                        bellItem.setOnMenuItemClickListener(item -> {
                            uiState.getOnManageNotificationsTapped().invoke();
                            return true;
                        });
                        followItem.getActionView().setEnabled(uiState.getFlags().isMenuEnabled());
                        followText.setEnabled(uiState.getFlags().isMenuEnabled());
                        bellItem.setEnabled(uiState.getFlags().isMenuEnabled());
                        if (uiState.getFlags().getShowMenuShimmer()) {
                            if (!shimmerView.isShimmerVisible()) {
                                shimmerView.showShimmer(true);
                            } else if (!shimmerView.isShimmerStarted()) {
                                shimmerView.startShimmer();
                            }
                        } else {
                            shimmerView.hideShimmer();
                        }
                        followItem.setVisible(uiState.getFlags().isFollowMenuVisible());
                        bellItem.setVisible(uiState.getFlags().isBellMenuVisible());
                        setResult(RESULT_OK, new Intent().putExtra(FOLLOW_CONVERSATION_UI_STATE_FLAGS_KEY, uiState.getFlags()));
                    }
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(20634)) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(20635)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(20636)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void performCommentAction(ReaderComment comment, ReaderCommentMenuActionType action) {
        if (!ListenerUtil.mutListener.listen(20642)) {
            switch(action) {
                case APPROVE:
                    break;
                case EDIT:
                    if (!ListenerUtil.mutListener.listen(20637)) {
                        openCommentEditor(comment);
                    }
                    break;
                case UNAPPROVE:
                    if (!ListenerUtil.mutListener.listen(20638)) {
                        moderateComment(comment, CommentStatus.UNAPPROVED, R.string.comment_unapproved, Stat.COMMENT_UNAPPROVED);
                    }
                    break;
                case SPAM:
                    if (!ListenerUtil.mutListener.listen(20639)) {
                        moderateComment(comment, CommentStatus.SPAM, R.string.comment_spammed, Stat.COMMENT_SPAMMED);
                    }
                    break;
                case TRASH:
                    if (!ListenerUtil.mutListener.listen(20640)) {
                        moderateComment(comment, CommentStatus.TRASH, R.string.comment_trashed, Stat.COMMENT_TRASHED);
                    }
                    break;
                case SHARE:
                    if (!ListenerUtil.mutListener.listen(20641)) {
                        shareComment(comment.getShortUrl());
                    }
                    break;
                case DIVIDER_NO_ACTION:
                    break;
            }
        }
    }

    private void openCommentEditor(ReaderComment comment) {
        SiteModel postSite = mSiteStore.getSiteBySiteId(comment.blogId);
        final Intent intent = UnifiedCommentsEditActivity.createIntent(this, new ReaderCommentIdentifier(comment.blogId, comment.postId, comment.commentId), postSite);
        if (!ListenerUtil.mutListener.listen(20643)) {
            startActivity(intent);
        }
    }

    private void moderateComment(ReaderComment comment, CommentStatus newStatus, int undoMessage, Stat tracker) {
        if (!ListenerUtil.mutListener.listen(20644)) {
            getCommentAdapter().removeComment(comment.commentId);
        }
        if (!ListenerUtil.mutListener.listen(20645)) {
            checkEmptyView();
        }
        Snackbar snackbar = WPSnackbar.make(findViewById(R.id.coordinator_layout), undoMessage, Snackbar.LENGTH_LONG).setAction(R.string.undo, view -> {
            getCommentAdapter().refreshComments();
        });
        if (!ListenerUtil.mutListener.listen(20651)) {
            snackbar.addCallback(new BaseCallback<Snackbar>() {

                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    if (!ListenerUtil.mutListener.listen(20646)) {
                        super.onDismissed(transientBottomBar, event);
                    }
                    if (!ListenerUtil.mutListener.listen(20648)) {
                        if (event == DISMISS_EVENT_ACTION) {
                            if (!ListenerUtil.mutListener.listen(20647)) {
                                AnalyticsUtils.trackCommentActionWithReaderPostDetails(Stat.COMMENT_MODERATION_UNDO, AnalyticsCommentActionSource.READER, mPost);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20649)) {
                        AnalyticsUtils.trackCommentActionWithReaderPostDetails(tracker, AnalyticsCommentActionSource.READER, mPost);
                    }
                    if (!ListenerUtil.mutListener.listen(20650)) {
                        ReaderCommentActions.moderateComment(comment, newStatus);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(20652)) {
            snackbar.show();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReaderEvents.CommentModerated event) {
        if (!ListenerUtil.mutListener.listen(20653)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20657)) {
            if (!event.isSuccess()) {
                if (!ListenerUtil.mutListener.listen(20655)) {
                    ToastUtils.showToast(ReaderCommentListActivity.this, R.string.comment_moderation_error);
                }
                if (!ListenerUtil.mutListener.listen(20656)) {
                    getCommentAdapter().refreshComments();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20654)) {
                    // we do try to remove the comment in case you did PTR and it appeared in the list again
                    getCommentAdapter().removeComment(event.getCommentId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20658)) {
            checkEmptyView();
        }
    }

    private void shareComment(String commentUrl) {
        if (!ListenerUtil.mutListener.listen(20659)) {
            mReaderTracker.trackPost(Stat.READER_ARTICLE_COMMENT_SHARED, mPost);
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(20660)) {
            shareIntent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(20661)) {
            shareIntent.putExtra(Intent.EXTRA_TEXT, commentUrl);
        }
        if (!ListenerUtil.mutListener.listen(20662)) {
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_link)));
        }
    }

    private void setReplyToCommentId(long commentId, boolean doFocus) {
        if (!ListenerUtil.mutListener.listen(20670)) {
            if ((ListenerUtil.mutListener.listen(20667) ? (mReplyToCommentId >= commentId) : (ListenerUtil.mutListener.listen(20666) ? (mReplyToCommentId <= commentId) : (ListenerUtil.mutListener.listen(20665) ? (mReplyToCommentId > commentId) : (ListenerUtil.mutListener.listen(20664) ? (mReplyToCommentId < commentId) : (ListenerUtil.mutListener.listen(20663) ? (mReplyToCommentId != commentId) : (mReplyToCommentId == commentId))))))) {
                if (!ListenerUtil.mutListener.listen(20669)) {
                    mReplyToCommentId = 0;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20668)) {
                    mReplyToCommentId = commentId;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20676)) {
            mEditComment.setHint((ListenerUtil.mutListener.listen(20675) ? (mReplyToCommentId >= 0) : (ListenerUtil.mutListener.listen(20674) ? (mReplyToCommentId <= 0) : (ListenerUtil.mutListener.listen(20673) ? (mReplyToCommentId > 0) : (ListenerUtil.mutListener.listen(20672) ? (mReplyToCommentId < 0) : (ListenerUtil.mutListener.listen(20671) ? (mReplyToCommentId != 0) : (mReplyToCommentId == 0)))))) ? R.string.reader_hint_comment_on_post : R.string.reader_hint_comment_on_comment);
        }
        if (!ListenerUtil.mutListener.listen(20679)) {
            if (doFocus) {
                if (!ListenerUtil.mutListener.listen(20678)) {
                    mEditComment.postDelayed(() -> {
                        final boolean isFocusableInTouchMode = mEditComment.isFocusableInTouchMode();
                        mEditComment.setFocusableInTouchMode(true);
                        EditTextUtils.showSoftInput(mEditComment);
                        mEditComment.setFocusableInTouchMode(isFocusableInTouchMode);
                        setupReplyToComment();
                    }, 200);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20677)) {
                    setupReplyToComment();
                }
            }
        }
    }

    private void setupReplyToComment() {
        if (!ListenerUtil.mutListener.listen(20680)) {
            // listView to reposition due to soft keyboard appearing
            getCommentAdapter().setHighlightCommentId(mReplyToCommentId, false);
        }
        if (!ListenerUtil.mutListener.listen(20681)) {
            getCommentAdapter().setReplyTargetComment(mReplyToCommentId);
        }
        if (!ListenerUtil.mutListener.listen(20682)) {
            getCommentAdapter().notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(20691)) {
            if ((ListenerUtil.mutListener.listen(20687) ? (mReplyToCommentId >= 0) : (ListenerUtil.mutListener.listen(20686) ? (mReplyToCommentId <= 0) : (ListenerUtil.mutListener.listen(20685) ? (mReplyToCommentId > 0) : (ListenerUtil.mutListener.listen(20684) ? (mReplyToCommentId < 0) : (ListenerUtil.mutListener.listen(20683) ? (mReplyToCommentId == 0) : (mReplyToCommentId != 0))))))) {
                if (!ListenerUtil.mutListener.listen(20689)) {
                    scrollToCommentId(mReplyToCommentId);
                }
                if (!ListenerUtil.mutListener.listen(20690)) {
                    // the back button in the editText to hide the soft keyboard
                    mEditComment.setOnBackListener(() -> {
                        if (EditTextUtils.isEmpty(mEditComment)) {
                            setReplyToCommentId(0, false);
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20688)) {
                    mEditComment.setOnBackListener(null);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(20692)) {
            outState.putLong(ReaderConstants.ARG_BLOG_ID, mBlogId);
        }
        if (!ListenerUtil.mutListener.listen(20693)) {
            outState.putLong(ReaderConstants.ARG_POST_ID, mPostId);
        }
        if (!ListenerUtil.mutListener.listen(20694)) {
            outState.putInt(ReaderConstants.KEY_RESTORE_POSITION, getCurrentPosition());
        }
        if (!ListenerUtil.mutListener.listen(20695)) {
            outState.putLong(KEY_REPLY_TO_COMMENT_ID, mReplyToCommentId);
        }
        if (!ListenerUtil.mutListener.listen(20696)) {
            outState.putBoolean(KEY_HAS_UPDATED_COMMENTS, mHasUpdatedComments);
        }
        if (!ListenerUtil.mutListener.listen(20697)) {
            outState.putString(ReaderConstants.ARG_INTERCEPTED_URI, mInterceptedUri);
        }
        if (!ListenerUtil.mutListener.listen(20698)) {
            outState.putString(ReaderConstants.ARG_SOURCE, mSource);
        }
        if (!ListenerUtil.mutListener.listen(20699)) {
            super.onSaveInstanceState(outState);
        }
    }

    private void showCommentsClosedMessage(boolean show) {
        TextView txtCommentsClosed = findViewById(R.id.text_comments_closed);
        if (!ListenerUtil.mutListener.listen(20701)) {
            if (txtCommentsClosed != null) {
                if (!ListenerUtil.mutListener.listen(20700)) {
                    txtCommentsClosed.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            }
        }
    }

    private boolean loadPost() {
        if (!ListenerUtil.mutListener.listen(20702)) {
            mPost = ReaderPostTable.getBlogPost(mBlogId, mPostId, false);
        }
        if (!ListenerUtil.mutListener.listen(20703)) {
            if (mPost == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(20713)) {
            if (!mAccountStore.hasAccessToken()) {
                if (!ListenerUtil.mutListener.listen(20711)) {
                    mCommentBox.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(20712)) {
                    showCommentsClosedMessage(false);
                }
            } else if (mPost.isCommentsOpen) {
                if (!ListenerUtil.mutListener.listen(20707)) {
                    mCommentBox.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(20708)) {
                    showCommentsClosedMessage(false);
                }
                if (!ListenerUtil.mutListener.listen(20709)) {
                    mEditComment.setOnEditorActionListener((v, actionId, event) -> {
                        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEND) {
                            submitComment();
                        }
                        return false;
                    });
                }
                if (!ListenerUtil.mutListener.listen(20710)) {
                    mSubmitReplyBtn.setOnClickListener(v -> submitComment());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20704)) {
                    mCommentBox.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(20705)) {
                    mEditComment.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(20706)) {
                    showCommentsClosedMessage(true);
                }
            }
        }
        return true;
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(20715)) {
            if (mSuggestionServiceConnectionManager != null) {
                if (!ListenerUtil.mutListener.listen(20714)) {
                    mSuggestionServiceConnectionManager.unbindFromService();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20716)) {
            super.onDestroy();
        }
    }

    private boolean hasCommentAdapter() {
        return (mCommentAdapter != null);
    }

    private ReaderCommentAdapter getCommentAdapter() {
        if (!ListenerUtil.mutListener.listen(20724)) {
            if (mCommentAdapter == null) {
                if (!ListenerUtil.mutListener.listen(20717)) {
                    mCommentAdapter = new ReaderCommentAdapter(WPActivityUtils.getThemedContext(this), getPost());
                }
                if (!ListenerUtil.mutListener.listen(20718)) {
                    // adapter calls this when user taps reply icon
                    mCommentAdapter.setReplyListener(commentId -> setReplyToCommentId(commentId, true));
                }
                if (!ListenerUtil.mutListener.listen(20719)) {
                    // adapter calls this when user taps share icon
                    mCommentAdapter.setCommentMenuActionListener(this::performCommentAction);
                }
                if (!ListenerUtil.mutListener.listen(20721)) {
                    // Enable post title click if we came here directly from notifications or deep linking
                    if (mDirectOperation != null) {
                        if (!ListenerUtil.mutListener.listen(20720)) {
                            mCommentAdapter.enableHeaderClicks();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20722)) {
                    // adapter calls this when data has been loaded & displayed
                    mCommentAdapter.setDataLoadedListener(isEmpty -> {
                        if (!isFinishing()) {
                            if (isEmpty || !mHasUpdatedComments) {
                                updateComments(isEmpty, false);
                            } else if (mCommentId > 0 || mDirectOperation != null) {
                                if (mCommentId > 0) {
                                    // Scroll to the commentId once if it was passed to this activity
                                    smoothScrollToCommentId(mCommentId);
                                }
                                doDirectOperation();
                            } else if (mRestorePosition > 0) {
                                mViewModel.scrollToPosition(mRestorePosition, false);
                            }
                            mRestorePosition = 0;
                            checkEmptyView();
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(20723)) {
                    // detects that more comments exist on the server than are stored locally
                    mCommentAdapter.setDataRequestedListener(() -> {
                        if (!mIsUpdatingComments) {
                            AppLog.i(T.READER, "reader comments > requesting next page of comments");
                            updateComments(true, true);
                        }
                    });
                }
            }
        }
        return mCommentAdapter;
    }

    private void doDirectOperation() {
        if (!ListenerUtil.mutListener.listen(20746)) {
            if (mDirectOperation != null) {
                if (!ListenerUtil.mutListener.listen(20745)) {
                    switch(mDirectOperation) {
                        case COMMENT_JUMP:
                            if (!ListenerUtil.mutListener.listen(20726)) {
                                mCommentAdapter.setHighlightCommentId(mCommentId, false);
                            }
                            if (!ListenerUtil.mutListener.listen(20727)) {
                                // clear up the direct operation vars. Only performing it once.
                                mDirectOperation = null;
                            }
                            if (!ListenerUtil.mutListener.listen(20728)) {
                                mCommentId = 0;
                            }
                            break;
                        case COMMENT_REPLY:
                            if (!ListenerUtil.mutListener.listen(20729)) {
                                setReplyToCommentId(mCommentId, mAccountStore.hasAccessToken());
                            }
                            if (!ListenerUtil.mutListener.listen(20730)) {
                                // clear up the direct operation vars. Only performing it once.
                                mDirectOperation = null;
                            }
                            if (!ListenerUtil.mutListener.listen(20731)) {
                                mCommentId = 0;
                            }
                            break;
                        case COMMENT_LIKE:
                            if (!ListenerUtil.mutListener.listen(20732)) {
                                getCommentAdapter().setHighlightCommentId(mCommentId, false);
                            }
                            if (!ListenerUtil.mutListener.listen(20744)) {
                                if (!mAccountStore.hasAccessToken()) {
                                    if (!ListenerUtil.mutListener.listen(20743)) {
                                        WPSnackbar.make(mCoordinator, R.string.reader_snackbar_err_cannot_like_post_logged_out, Snackbar.LENGTH_INDEFINITE).setAction(R.string.sign_in, mSignInClickListener).show();
                                    }
                                } else {
                                    ReaderComment comment = ReaderCommentTable.getComment(mPost.blogId, mPost.postId, mCommentId);
                                    if (!ListenerUtil.mutListener.listen(20741)) {
                                        if (comment == null) {
                                            if (!ListenerUtil.mutListener.listen(20740)) {
                                                ToastUtils.showToast(ReaderCommentListActivity.this, R.string.reader_toast_err_comment_not_found);
                                            }
                                        } else if (comment.isLikedByCurrentUser) {
                                            if (!ListenerUtil.mutListener.listen(20739)) {
                                                ToastUtils.showToast(ReaderCommentListActivity.this, R.string.reader_toast_err_already_liked);
                                            }
                                        } else {
                                            long wpComUserId = mAccountStore.getAccount().getUserId();
                                            if (!ListenerUtil.mutListener.listen(20738)) {
                                                if ((ListenerUtil.mutListener.listen(20733) ? (ReaderCommentActions.performLikeAction(comment, true, wpComUserId) || getCommentAdapter().refreshComment(mCommentId)) : (ReaderCommentActions.performLikeAction(comment, true, wpComUserId) && getCommentAdapter().refreshComment(mCommentId)))) {
                                                    if (!ListenerUtil.mutListener.listen(20735)) {
                                                        getCommentAdapter().setAnimateLikeCommentId(mCommentId);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(20736)) {
                                                        mReaderTracker.trackPost(AnalyticsTracker.Stat.READER_ARTICLE_COMMENT_LIKED, mPost);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(20737)) {
                                                        mReaderTracker.trackPost(AnalyticsTracker.Stat.COMMENT_LIKED, mPost, AnalyticsCommentActionSource.READER.toString());
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(20734)) {
                                                        ToastUtils.showToast(ReaderCommentListActivity.this, R.string.reader_toast_err_generic);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(20742)) {
                                        // clear up the direct operation vars. Only performing it once.
                                        mDirectOperation = null;
                                    }
                                }
                            }
                            break;
                        case POST_LIKE:
                            // nothing special to do in this case
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20725)) {
                    mCommentId = 0;
                }
            }
        }
    }

    private ReaderPost getPost() {
        return mPost;
    }

    private void showProgress() {
        ProgressBar progress = findViewById(R.id.progress_loading);
        if (!ListenerUtil.mutListener.listen(20748)) {
            if (progress != null) {
                if (!ListenerUtil.mutListener.listen(20747)) {
                    progress.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void hideProgress() {
        ProgressBar progress = findViewById(R.id.progress_loading);
        if (!ListenerUtil.mutListener.listen(20750)) {
            if (progress != null) {
                if (!ListenerUtil.mutListener.listen(20749)) {
                    progress.setVisibility(View.GONE);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReaderEvents.UpdateCommentsStarted event) {
        if (!ListenerUtil.mutListener.listen(20751)) {
            mIsUpdatingComments = true;
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReaderEvents.UpdateCommentsEnded event) {
        if (!ListenerUtil.mutListener.listen(20752)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20753)) {
            mIsUpdatingComments = false;
        }
        if (!ListenerUtil.mutListener.listen(20754)) {
            mHasUpdatedComments = true;
        }
        if (!ListenerUtil.mutListener.listen(20755)) {
            hideProgress();
        }
        if (!ListenerUtil.mutListener.listen(20759)) {
            if (event.getResult().isNewOrChanged()) {
                if (!ListenerUtil.mutListener.listen(20757)) {
                    mRestorePosition = getCurrentPosition();
                }
                if (!ListenerUtil.mutListener.listen(20758)) {
                    refreshComments();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20756)) {
                    checkEmptyView();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20760)) {
            setRefreshing(false);
        }
    }

    /*
     * request comments for this post
     */
    private void updateComments(boolean showProgress, boolean requestNextPage) {
        if (!ListenerUtil.mutListener.listen(20763)) {
            if (mIsUpdatingComments) {
                if (!ListenerUtil.mutListener.listen(20761)) {
                    AppLog.w(T.READER, "reader comments > already updating comments");
                }
                if (!ListenerUtil.mutListener.listen(20762)) {
                    setRefreshing(false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20766)) {
            if (!NetworkUtils.isNetworkAvailable(this)) {
                if (!ListenerUtil.mutListener.listen(20764)) {
                    AppLog.w(T.READER, "reader comments > no connection, update canceled");
                }
                if (!ListenerUtil.mutListener.listen(20765)) {
                    setRefreshing(false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20768)) {
            if (showProgress) {
                if (!ListenerUtil.mutListener.listen(20767)) {
                    showProgress();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20769)) {
            ReaderCommentService.startService(this, mPost.blogId, mPost.postId, requestNextPage);
        }
    }

    private void checkEmptyView() {
        TextView txtEmpty = findViewById(R.id.text_empty);
        if (!ListenerUtil.mutListener.listen(20770)) {
            if (txtEmpty == null) {
                return;
            }
        }
        boolean isEmpty = (ListenerUtil.mutListener.listen(20772) ? ((ListenerUtil.mutListener.listen(20771) ? (hasCommentAdapter() || getCommentAdapter().isEmpty()) : (hasCommentAdapter() && getCommentAdapter().isEmpty())) || !mIsSubmittingComment) : ((ListenerUtil.mutListener.listen(20771) ? (hasCommentAdapter() || getCommentAdapter().isEmpty()) : (hasCommentAdapter() && getCommentAdapter().isEmpty())) && !mIsSubmittingComment));
        if (!ListenerUtil.mutListener.listen(20780)) {
            if ((ListenerUtil.mutListener.listen(20773) ? (isEmpty || !NetworkUtils.isNetworkAvailable(this)) : (isEmpty && !NetworkUtils.isNetworkAvailable(this)))) {
                if (!ListenerUtil.mutListener.listen(20778)) {
                    txtEmpty.setText(R.string.no_network_message);
                }
                if (!ListenerUtil.mutListener.listen(20779)) {
                    txtEmpty.setVisibility(View.VISIBLE);
                }
            } else if ((ListenerUtil.mutListener.listen(20774) ? (isEmpty || mHasUpdatedComments) : (isEmpty && mHasUpdatedComments))) {
                if (!ListenerUtil.mutListener.listen(20776)) {
                    txtEmpty.setText(R.string.reader_empty_comments);
                }
                if (!ListenerUtil.mutListener.listen(20777)) {
                    txtEmpty.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20775)) {
                    txtEmpty.setVisibility(View.GONE);
                }
            }
        }
    }

    /*
     * refresh adapter so latest comments appear
     */
    private void refreshComments() {
        if (!ListenerUtil.mutListener.listen(20781)) {
            AppLog.d(T.READER, "reader comments > refreshComments");
        }
        if (!ListenerUtil.mutListener.listen(20782)) {
            getCommentAdapter().refreshComments();
        }
    }

    /*
     * scrolls the passed comment to the top of the listView
     */
    private void scrollToCommentId(long commentId) {
        int position = getCommentAdapter().positionOfCommentId(commentId);
        if (!ListenerUtil.mutListener.listen(20789)) {
            if ((ListenerUtil.mutListener.listen(20787) ? (position >= -1) : (ListenerUtil.mutListener.listen(20786) ? (position <= -1) : (ListenerUtil.mutListener.listen(20785) ? (position < -1) : (ListenerUtil.mutListener.listen(20784) ? (position != -1) : (ListenerUtil.mutListener.listen(20783) ? (position == -1) : (position > -1))))))) {
                if (!ListenerUtil.mutListener.listen(20788)) {
                    mViewModel.scrollToPosition(position, false);
                }
            }
        }
    }

    /*
     * Smoothly scrolls the passed comment to the top of the listView
     */
    private void smoothScrollToCommentId(long commentId) {
        int position = getCommentAdapter().positionOfCommentId(commentId);
        if (!ListenerUtil.mutListener.listen(20796)) {
            if ((ListenerUtil.mutListener.listen(20794) ? (position >= -1) : (ListenerUtil.mutListener.listen(20793) ? (position <= -1) : (ListenerUtil.mutListener.listen(20792) ? (position < -1) : (ListenerUtil.mutListener.listen(20791) ? (position != -1) : (ListenerUtil.mutListener.listen(20790) ? (position == -1) : (position > -1))))))) {
                if (!ListenerUtil.mutListener.listen(20795)) {
                    mViewModel.scrollToPosition(position, true);
                }
            }
        }
    }

    /*
     * submit the text typed into the comment box as a comment on the current post
     */
    private void submitComment() {
        final String commentText = EditTextUtils.getText(mEditComment);
        if (!ListenerUtil.mutListener.listen(20797)) {
            if (TextUtils.isEmpty(commentText)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20798)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20806)) {
            if ((ListenerUtil.mutListener.listen(20803) ? (mReplyToCommentId >= 0) : (ListenerUtil.mutListener.listen(20802) ? (mReplyToCommentId <= 0) : (ListenerUtil.mutListener.listen(20801) ? (mReplyToCommentId > 0) : (ListenerUtil.mutListener.listen(20800) ? (mReplyToCommentId < 0) : (ListenerUtil.mutListener.listen(20799) ? (mReplyToCommentId == 0) : (mReplyToCommentId != 0))))))) {
                if (!ListenerUtil.mutListener.listen(20805)) {
                    mReaderTracker.trackPost(AnalyticsTracker.Stat.READER_ARTICLE_COMMENT_REPLIED_TO, mPost);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20804)) {
                    mReaderTracker.trackPost(AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON, mPost);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20807)) {
            mSubmitReplyBtn.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(20808)) {
            mEditComment.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(20809)) {
            mIsSubmittingComment = true;
        }
        // and reflect it in the adapter before the API call returns
        final long fakeCommentId = ReaderCommentActions.generateFakeCommentId();
        ReaderActions.CommentActionListener actionListener = (succeeded, newComment) -> {
            if (isFinishing()) {
                return;
            }
            mIsSubmittingComment = false;
            mEditComment.setEnabled(true);
            if (succeeded) {
                mSubmitReplyBtn.setEnabled(false);
                // stop highlighting the fake comment and replace it with the real one
                getCommentAdapter().setHighlightCommentId(0, false);
                getCommentAdapter().setReplyTargetComment(0);
                getCommentAdapter().replaceComment(fakeCommentId, newComment);
                getCommentAdapter().refreshPost();
                setReplyToCommentId(0, false);
                mEditComment.getAutoSaveTextHelper().clearSavedText(mEditComment);
            } else {
                mEditComment.setText(commentText);
                mSubmitReplyBtn.setEnabled(true);
                getCommentAdapter().removeComment(fakeCommentId);
                ToastUtils.showToast(ReaderCommentListActivity.this, R.string.reader_toast_err_comment_failed, ToastUtils.Duration.LONG);
            }
            checkEmptyView();
        };
        long wpComUserId = mAccountStore.getAccount().getUserId();
        ReaderComment newComment = ReaderCommentActions.submitPostComment(getPost(), fakeCommentId, commentText, mReplyToCommentId, actionListener, wpComUserId);
        if (!ListenerUtil.mutListener.listen(20816)) {
            if (newComment != null) {
                if (!ListenerUtil.mutListener.listen(20810)) {
                    mEditComment.setText(null);
                }
                if (!ListenerUtil.mutListener.listen(20811)) {
                    // next to it while it's submitted
                    getCommentAdapter().setHighlightCommentId(newComment.commentId, true);
                }
                if (!ListenerUtil.mutListener.listen(20812)) {
                    getCommentAdapter().setReplyTargetComment(0);
                }
                if (!ListenerUtil.mutListener.listen(20813)) {
                    getCommentAdapter().addComment(newComment);
                }
                if (!ListenerUtil.mutListener.listen(20814)) {
                    // make sure it's scrolled into view
                    scrollToCommentId(fakeCommentId);
                }
                if (!ListenerUtil.mutListener.listen(20815)) {
                    checkEmptyView();
                }
            }
        }
    }

    private int getCurrentPosition() {
        if ((ListenerUtil.mutListener.listen(20817) ? (mRecyclerView != null || hasCommentAdapter()) : (mRecyclerView != null && hasCommentAdapter()))) {
            return ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        } else {
            return 0;
        }
    }

    private void setRefreshing(boolean refreshing) {
        if (!ListenerUtil.mutListener.listen(20818)) {
            mSwipeToRefreshHelper.setRefreshing(refreshing);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(20819)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(20822)) {
            // if user is returning from login, make sure to update the post and its comments
            if ((ListenerUtil.mutListener.listen(20820) ? (requestCode == RequestCodes.DO_LOGIN || resultCode == Activity.RESULT_OK) : (requestCode == RequestCodes.DO_LOGIN && resultCode == Activity.RESULT_OK))) {
                if (!ListenerUtil.mutListener.listen(20821)) {
                    mUpdateOnResume = true;
                }
            }
        }
    }
}

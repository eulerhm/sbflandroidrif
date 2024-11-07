package org.wordpress.android.ui.comments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
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
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.snackbar.Snackbar;
import org.apache.commons.text.StringEscapeUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.UserSuggestionTable;
import org.wordpress.android.fluxc.action.CommentAction;
import org.wordpress.android.fluxc.generated.CommentActionBuilder;
import org.wordpress.android.fluxc.model.CommentModel;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.CommentStore.OnCommentChanged;
import org.wordpress.android.fluxc.store.CommentStore.RemoteCommentPayload;
import org.wordpress.android.fluxc.store.CommentStore.RemoteCreateCommentPayload;
import org.wordpress.android.fluxc.store.CommentStore.RemoteLikeCommentPayload;
import org.wordpress.android.fluxc.store.CommentsStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.tools.FluxCImageLoader;
import org.wordpress.android.models.Note;
import org.wordpress.android.models.Note.EnabledActions;
import org.wordpress.android.models.UserSuggestion;
import org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler;
import org.wordpress.android.ui.ActivityId;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment.Builder;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment.OnCollapseListener;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment.OnConfirmListener;
import org.wordpress.android.ui.CommentFullScreenDialogFragment;
import org.wordpress.android.ui.ViewPagerFragment;
import org.wordpress.android.ui.comments.CommentActions.OnCommentActionListener;
import org.wordpress.android.ui.comments.CommentActions.OnNoteCommentActionListener;
import org.wordpress.android.ui.comments.unified.CommentIdentifier;
import org.wordpress.android.ui.comments.unified.CommentIdentifier.NotificationCommentIdentifier;
import org.wordpress.android.ui.comments.unified.CommentIdentifier.SiteCommentIdentifier;
import org.wordpress.android.ui.comments.unified.CommentSource;
import org.wordpress.android.ui.comments.unified.CommentsStoreAdapter;
import org.wordpress.android.ui.comments.unified.UnifiedCommentsEditActivity;
import org.wordpress.android.ui.notifications.NotificationEvents;
import org.wordpress.android.ui.notifications.NotificationFragment;
import org.wordpress.android.ui.notifications.NotificationsDetailListFragment;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.reader.ReaderAnim;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderPostActions;
import org.wordpress.android.ui.suggestion.Suggestion;
import org.wordpress.android.ui.suggestion.adapters.SuggestionAdapter;
import org.wordpress.android.ui.suggestion.service.SuggestionEvents;
import org.wordpress.android.ui.suggestion.util.SuggestionServiceConnectionManager;
import org.wordpress.android.ui.suggestion.util.SuggestionUtils;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.ColorUtils;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.EditTextUtils;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.HtmlUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.util.WPLinkMovementMethod;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.config.UnifiedCommentsCommentEditFeatureConfig;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import org.wordpress.android.widgets.SuggestionAutoCompleteText;
import org.wordpress.android.widgets.WPSnackbar;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * comment detail displayed from both the notification list and the comment list
 * prior to this there were separate comment detail screens for each list
 *
 * @deprecated Comments are being refactored as part of Comments Unification project. If you are adding any
 * features or modifying this class, please ping develric or klymyam
 */
@Deprecated
public class CommentDetailFragment extends ViewPagerFragment implements NotificationFragment, OnConfirmListener, OnCollapseListener {

    private static final String KEY_MODE = "KEY_MODE";

    private static final String KEY_SITE_LOCAL_ID = "KEY_SITE_LOCAL_ID";

    private static final String KEY_COMMENT_ID = "KEY_COMMENT_ID";

    private static final String KEY_NOTE_ID = "KEY_NOTE_ID";

    private static final String KEY_REPLY_TEXT = "KEY_REPLY_TEXT";

    private static final int INTENT_COMMENT_EDITOR = 1010;

    private CommentModel mComment;

    private SiteModel mSite;

    private Note mNote;

    private SuggestionAdapter mSuggestionAdapter;

    private SuggestionServiceConnectionManager mSuggestionServiceConnectionManager;

    private TextView mTxtStatus;

    private TextView mTxtContent;

    private View mSubmitReplyBtn;

    private SuggestionAutoCompleteText mEditReply;

    private ViewGroup mLayoutReply;

    private ViewGroup mLayoutButtons;

    private ViewGroup mCommentContentLayout;

    private View mBtnLikeComment;

    private ImageView mBtnLikeIcon;

    private TextView mBtnLikeTextView;

    private View mBtnModerateComment;

    private ImageView mBtnModerateIcon;

    private TextView mBtnModerateTextView;

    private View mBtnSpamComment;

    private TextView mBtnSpamCommentText;

    private View mBtnMoreComment;

    private View mSnackbarAnchor;

    private View mNestedScrollView;

    private String mRestoredReplyText;

    private String mRestoredNoteId;

    private boolean mIsUsersBlog = false;

    private boolean mShouldFocusReplyField;

    private String mPreviousStatus;

    private float mNormalOpacity = 1f;

    private float mMediumOpacity;

    @Inject
    AccountStore mAccountStore;

    @Inject
    CommentsStoreAdapter mCommentsStoreAdapter;

    @Inject
    SiteStore mSiteStore;

    @Inject
    FluxCImageLoader mImageLoader;

    @Inject
    ImageManager mImageManager;

    @Inject
    CommentsStore mCommentsStore;

    @Inject
    LocalCommentCacheUpdateHandler mLocalCommentCacheUpdateHandler;

    @Inject
    UnifiedCommentsCommentEditFeatureConfig mUnifiedCommentsCommentEditFeatureConfig;

    private boolean mIsSubmittingReply = false;

    private NotificationsDetailListFragment mNotificationsDetailListFragment;

    private OnPostClickListener mOnPostClickListener;

    private OnCommentActionListener mOnCommentActionListener;

    private OnNoteCommentActionListener mOnNoteCommentActionListener;

    private CommentSource mCommentSource;

    /*
     * these determine which actions (moderation, replying, marking as spam) to enable
     * for this comment - all actions are enabled when opened from the comment list, only
     * changed when opened from a notification
     */
    private EnumSet<EnabledActions> mEnabledActions = EnumSet.allOf(EnabledActions.class);

    /*
     * used when called from comment list
     */
    static CommentDetailFragment newInstance(SiteModel site, CommentModel commentModel) {
        CommentDetailFragment fragment = new CommentDetailFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(4214)) {
            args.putSerializable(KEY_MODE, CommentSource.SITE_COMMENTS);
        }
        if (!ListenerUtil.mutListener.listen(4215)) {
            args.putInt(KEY_SITE_LOCAL_ID, site.getId());
        }
        if (!ListenerUtil.mutListener.listen(4216)) {
            args.putLong(KEY_COMMENT_ID, commentModel.getRemoteCommentId());
        }
        if (!ListenerUtil.mutListener.listen(4217)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    /*
     * used when called from notification list for a comment notification
     */
    public static CommentDetailFragment newInstance(final String noteId, final String replyText) {
        CommentDetailFragment fragment = new CommentDetailFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(4218)) {
            args.putSerializable(KEY_MODE, CommentSource.NOTIFICATION);
        }
        if (!ListenerUtil.mutListener.listen(4219)) {
            args.putString(KEY_NOTE_ID, noteId);
        }
        if (!ListenerUtil.mutListener.listen(4220)) {
            args.putString(KEY_REPLY_TEXT, replyText);
        }
        if (!ListenerUtil.mutListener.listen(4221)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4222)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4223)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(4224)) {
            mCommentSource = (CommentSource) getArguments().getSerializable(KEY_MODE);
        }
        if (!ListenerUtil.mutListener.listen(4228)) {
            switch(mCommentSource) {
                case SITE_COMMENTS:
                    if (!ListenerUtil.mutListener.listen(4225)) {
                        setComment(getArguments().getLong(KEY_COMMENT_ID), getArguments().getInt(KEY_SITE_LOCAL_ID));
                    }
                    break;
                case NOTIFICATION:
                    if (!ListenerUtil.mutListener.listen(4226)) {
                        setNote(getArguments().getString(KEY_NOTE_ID));
                    }
                    if (!ListenerUtil.mutListener.listen(4227)) {
                        setReplyText(getArguments().getString(KEY_REPLY_TEXT));
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(4232)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(4231)) {
                    if (savedInstanceState.getString(KEY_NOTE_ID) != null) {
                        if (!ListenerUtil.mutListener.listen(4230)) {
                            // See WordPress.deferredInit()
                            mRestoredNoteId = savedInstanceState.getString(KEY_NOTE_ID);
                        }
                    } else {
                        int siteId = savedInstanceState.getInt(KEY_SITE_LOCAL_ID);
                        long commentId = savedInstanceState.getLong(KEY_COMMENT_ID);
                        if (!ListenerUtil.mutListener.listen(4229)) {
                            setComment(commentId, siteId);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4233)) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(4234)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(4237)) {
            if (mComment != null) {
                if (!ListenerUtil.mutListener.listen(4235)) {
                    outState.putLong(KEY_COMMENT_ID, mComment.getRemoteCommentId());
                }
                if (!ListenerUtil.mutListener.listen(4236)) {
                    outState.putInt(KEY_SITE_LOCAL_ID, mSite.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4239)) {
            if (mNote != null) {
                if (!ListenerUtil.mutListener.listen(4238)) {
                    outState.putString(KEY_NOTE_ID, mNote.getId());
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4241)) {
            if (mSuggestionServiceConnectionManager != null) {
                if (!ListenerUtil.mutListener.listen(4240)) {
                    mSuggestionServiceConnectionManager.unbindFromService();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4242)) {
            super.onDestroy();
        }
    }

    // touching the file resulted in the MethodLength, it's suppressed until we get time to refactor this method
    @SuppressWarnings("checkstyle:MethodLength")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.comment_detail_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(4243)) {
            mMediumOpacity = ResourcesCompat.getFloat(getResources(), R.dimen.material_emphasis_medium);
        }
        if (!ListenerUtil.mutListener.listen(4244)) {
            mTxtStatus = view.findViewById(R.id.text_status);
        }
        if (!ListenerUtil.mutListener.listen(4245)) {
            mTxtContent = view.findViewById(R.id.text_content);
        }
        if (!ListenerUtil.mutListener.listen(4246)) {
            // noinspection InflateParams
            mLayoutButtons = (ViewGroup) inflater.inflate(R.layout.comment_action_footer, null, false);
        }
        if (!ListenerUtil.mutListener.listen(4247)) {
            mBtnLikeComment = mLayoutButtons.findViewById(R.id.btn_like);
        }
        if (!ListenerUtil.mutListener.listen(4248)) {
            mBtnLikeIcon = mLayoutButtons.findViewById(R.id.btn_like_icon);
        }
        if (!ListenerUtil.mutListener.listen(4249)) {
            mBtnLikeTextView = mLayoutButtons.findViewById(R.id.btn_like_text);
        }
        if (!ListenerUtil.mutListener.listen(4250)) {
            mBtnModerateComment = mLayoutButtons.findViewById(R.id.btn_moderate);
        }
        if (!ListenerUtil.mutListener.listen(4251)) {
            mBtnModerateIcon = mLayoutButtons.findViewById(R.id.btn_moderate_icon);
        }
        if (!ListenerUtil.mutListener.listen(4252)) {
            mBtnModerateTextView = mLayoutButtons.findViewById(R.id.btn_moderate_text);
        }
        if (!ListenerUtil.mutListener.listen(4253)) {
            mBtnSpamComment = mLayoutButtons.findViewById(R.id.btn_spam);
        }
        if (!ListenerUtil.mutListener.listen(4254)) {
            mBtnSpamCommentText = mLayoutButtons.findViewById(R.id.btn_spam_text);
        }
        if (!ListenerUtil.mutListener.listen(4255)) {
            mBtnMoreComment = mLayoutButtons.findViewById(R.id.btn_more);
        }
        if (!ListenerUtil.mutListener.listen(4256)) {
            mSnackbarAnchor = view.findViewById(R.id.layout_bottom);
        }
        if (!ListenerUtil.mutListener.listen(4257)) {
            mNestedScrollView = view.findViewById(R.id.nested_scroll_view);
        }
        if (!ListenerUtil.mutListener.listen(4258)) {
            // intended to).
            mCommentContentLayout = view.findViewById(R.id.comment_content_container);
        }
        if (!ListenerUtil.mutListener.listen(4259)) {
            mLayoutReply = view.findViewById(R.id.layout_comment_box);
        }
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(view.getContext());
        float appbarElevation = getResources().getDimension(R.dimen.appbar_elevation);
        int elevatedColor = elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(appbarElevation);
        if (!ListenerUtil.mutListener.listen(4260)) {
            mLayoutReply.setBackgroundColor(elevatedColor);
        }
        if (!ListenerUtil.mutListener.listen(4261)) {
            mSubmitReplyBtn = mLayoutReply.findViewById(R.id.btn_submit_reply);
        }
        if (!ListenerUtil.mutListener.listen(4262)) {
            mSubmitReplyBtn.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(4263)) {
            mSubmitReplyBtn.setOnLongClickListener(view1 -> {
                if (view1.isHapticFeedbackEnabled()) {
                    view1.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                }
                Toast.makeText(view1.getContext(), R.string.send, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(4264)) {
            ViewExtensionsKt.redirectContextClickToLongPressListener(mSubmitReplyBtn);
        }
        if (!ListenerUtil.mutListener.listen(4265)) {
            mEditReply = mLayoutReply.findViewById(R.id.edit_comment);
        }
        if (!ListenerUtil.mutListener.listen(4266)) {
            mEditReply.initializeWithPrefix('@');
        }
        if (!ListenerUtil.mutListener.listen(4268)) {
            mEditReply.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(4267)) {
                        mSubmitReplyBtn.setEnabled(!TextUtils.isEmpty(s.toString().trim()));
                    }
                }
            });
        }
        ImageView buttonExpand = mLayoutReply.findViewById(R.id.button_expand);
        if (!ListenerUtil.mutListener.listen(4269)) {
            buttonExpand.setOnClickListener(v -> {
                Bundle bundle = CommentFullScreenDialogFragment.Companion.newBundle(mEditReply.getText().toString(), mEditReply.getSelectionStart(), mEditReply.getSelectionEnd(), mSite.getSiteId());
                new Builder(requireContext()).setTitle(R.string.comment).setOnCollapseListener(this).setOnConfirmListener(this).setContent(CommentFullScreenDialogFragment.class, bundle).setAction(R.string.send).setHideActivityBar(true).build().show(requireActivity().getSupportFragmentManager(), CollapseFullScreenDialogFragment.TAG + getCommentSpecificFragmentTagSuffix());
            });
        }
        if (!ListenerUtil.mutListener.listen(4270)) {
            buttonExpand.setOnLongClickListener(v -> {
                if (v.isHapticFeedbackEnabled()) {
                    v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                }
                Toast.makeText(v.getContext(), R.string.description_expand, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(4271)) {
            ViewExtensionsKt.redirectContextClickToLongPressListener(buttonExpand);
        }
        if (!ListenerUtil.mutListener.listen(4272)) {
            setReplyUniqueId();
        }
        if (!ListenerUtil.mutListener.listen(4273)) {
            // hide comment like button until we know it can be enabled in showCommentAsNotification()
            mBtnLikeComment.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4274)) {
            // hide moderation buttons until updateModerationButtons() is called
            mLayoutButtons.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4275)) {
            // this is necessary in order for anchor tags in the comment text to be clickable
            mTxtContent.setLinksClickable(true);
        }
        if (!ListenerUtil.mutListener.listen(4276)) {
            mTxtContent.setMovementMethod(WPLinkMovementMethod.getInstance());
        }
        if (!ListenerUtil.mutListener.listen(4277)) {
            mEditReply.setHint(R.string.reader_hint_comment_on_comment);
        }
        if (!ListenerUtil.mutListener.listen(4278)) {
            mEditReply.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEND) {
                    submitReply();
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(4281)) {
            if (!TextUtils.isEmpty(mRestoredReplyText)) {
                if (!ListenerUtil.mutListener.listen(4279)) {
                    mEditReply.setText(mRestoredReplyText);
                }
                if (!ListenerUtil.mutListener.listen(4280)) {
                    mRestoredReplyText = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4282)) {
            mSubmitReplyBtn.setOnClickListener(v -> submitReply());
        }
        if (!ListenerUtil.mutListener.listen(4283)) {
            mBtnSpamComment.setOnClickListener(v -> {
                if (mComment == null) {
                    return;
                }
                if (CommentStatus.fromString(mComment.getStatus()) == CommentStatus.SPAM) {
                    moderateComment(CommentStatus.APPROVED);
                    announceCommentStatusChangeForAccessibility(CommentStatus.UNSPAM);
                } else {
                    moderateComment(CommentStatus.SPAM);
                    announceCommentStatusChangeForAccessibility(CommentStatus.SPAM);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4284)) {
            mBtnLikeComment.setOnClickListener(v -> likeComment(false));
        }
        if (!ListenerUtil.mutListener.listen(4285)) {
            mBtnMoreComment.setOnClickListener(v -> showMoreMenu(v));
        }
        if (!ListenerUtil.mutListener.listen(4286)) {
            // hide more button until we know it can be enabled
            mBtnMoreComment.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4287)) {
            setupSuggestionServiceAndAdapter();
        }
        return view;
    }

    private String getCommentSpecificFragmentTagSuffix() {
        return "_" + mComment.getRemoteSiteId() + "_" + mComment.getRemoteCommentId();
    }

    @Override
    public void onConfirm(@Nullable Bundle result) {
        if (!ListenerUtil.mutListener.listen(4290)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(4288)) {
                    mEditReply.setText(result.getString(CommentFullScreenDialogFragment.RESULT_REPLY));
                }
                if (!ListenerUtil.mutListener.listen(4289)) {
                    submitReply();
                }
            }
        }
    }

    @Override
    public void onCollapse(@Nullable Bundle result) {
        if (!ListenerUtil.mutListener.listen(4294)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(4291)) {
                    mEditReply.setText(result.getString(CommentFullScreenDialogFragment.RESULT_REPLY));
                }
                if (!ListenerUtil.mutListener.listen(4292)) {
                    mEditReply.setSelection(result.getInt(CommentFullScreenDialogFragment.RESULT_SELECTION_START), result.getInt(CommentFullScreenDialogFragment.RESULT_SELECTION_END));
                }
                if (!ListenerUtil.mutListener.listen(4293)) {
                    mEditReply.requestFocus();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(4295)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(4296)) {
            ActivityId.trackLastActivity(ActivityId.COMMENT_DETAIL);
        }
        if (!ListenerUtil.mutListener.listen(4299)) {
            // Set the note if we retrieved the noteId from savedInstanceState
            if (!TextUtils.isEmpty(mRestoredNoteId)) {
                if (!ListenerUtil.mutListener.listen(4297)) {
                    setNote(mRestoredNoteId);
                }
                if (!ListenerUtil.mutListener.listen(4298)) {
                    mRestoredNoteId = null;
                }
            }
        }
        // we need to to it in onResume to make sure mComment is already intialized
        CollapseFullScreenDialogFragment fragment = (CollapseFullScreenDialogFragment) requireActivity().getSupportFragmentManager().findFragmentByTag(CollapseFullScreenDialogFragment.TAG + getCommentSpecificFragmentTagSuffix());
        if (!ListenerUtil.mutListener.listen(4303)) {
            if ((ListenerUtil.mutListener.listen(4300) ? (fragment != null || fragment.isAdded()) : (fragment != null && fragment.isAdded()))) {
                if (!ListenerUtil.mutListener.listen(4301)) {
                    fragment.setOnCollapseListener(this);
                }
                if (!ListenerUtil.mutListener.listen(4302)) {
                    fragment.setOnConfirmListener(this);
                }
            }
        }
    }

    private void setupSuggestionServiceAndAdapter() {
        if (!ListenerUtil.mutListener.listen(4306)) {
            if ((ListenerUtil.mutListener.listen(4305) ? ((ListenerUtil.mutListener.listen(4304) ? (!isAdded() && mSite == null) : (!isAdded() || mSite == null)) && !SiteUtils.isAccessedViaWPComRest(mSite)) : ((ListenerUtil.mutListener.listen(4304) ? (!isAdded() && mSite == null) : (!isAdded() || mSite == null)) || !SiteUtils.isAccessedViaWPComRest(mSite)))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4307)) {
            mSuggestionServiceConnectionManager = new SuggestionServiceConnectionManager(getActivity(), mSite.getSiteId());
        }
        if (!ListenerUtil.mutListener.listen(4308)) {
            mSuggestionAdapter = SuggestionUtils.setupUserSuggestions(mSite, getActivity(), mSuggestionServiceConnectionManager);
        }
        if (!ListenerUtil.mutListener.listen(4310)) {
            if (mSuggestionAdapter != null) {
                if (!ListenerUtil.mutListener.listen(4309)) {
                    mEditReply.setAdapter(mSuggestionAdapter);
                }
            }
        }
    }

    private void setReplyUniqueId() {
        if (!ListenerUtil.mutListener.listen(4319)) {
            if ((ListenerUtil.mutListener.listen(4311) ? (mEditReply != null || isAdded()) : (mEditReply != null && isAdded()))) {
                String sId = null;
                if (!ListenerUtil.mutListener.listen(4315)) {
                    if ((ListenerUtil.mutListener.listen(4312) ? (mSite != null || mComment != null) : (mSite != null && mComment != null))) {
                        if (!ListenerUtil.mutListener.listen(4314)) {
                            sId = String.format(Locale.US, "%d-%d", mSite.getSiteId(), mComment.getRemoteCommentId());
                        }
                    } else if (mNote != null) {
                        if (!ListenerUtil.mutListener.listen(4313)) {
                            sId = String.format(Locale.US, "%d-%d", mNote.getSiteId(), mNote.getCommentId());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4318)) {
                    if (sId != null) {
                        if (!ListenerUtil.mutListener.listen(4316)) {
                            mEditReply.getAutoSaveTextHelper().setUniqueId(sId);
                        }
                        if (!ListenerUtil.mutListener.listen(4317)) {
                            mEditReply.getAutoSaveTextHelper().loadString(mEditReply);
                        }
                    }
                }
            }
        }
    }

    private void setComment(final long commentRemoteId, final int siteLocalId) {
        final SiteModel site = mSiteStore.getSiteByLocalId(siteLocalId);
        if (!ListenerUtil.mutListener.listen(4320)) {
            setComment(mCommentsStoreAdapter.getCommentBySiteAndRemoteId(site, commentRemoteId), site);
        }
    }

    private void setComment(@Nullable final CommentModel comment, @Nullable final SiteModel site) {
        if (!ListenerUtil.mutListener.listen(4321)) {
            mComment = comment;
        }
        if (!ListenerUtil.mutListener.listen(4322)) {
            mSite = site;
        }
        if (!ListenerUtil.mutListener.listen(4324)) {
            // notification about a reply to a comment this user posted on someone else's blog
            mIsUsersBlog = ((ListenerUtil.mutListener.listen(4323) ? (comment != null || site != null) : (comment != null && site != null)));
        }
        if (!ListenerUtil.mutListener.listen(4326)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(4325)) {
                    showComment();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4327)) {
            // Reset the reply unique id since mComment just changed.
            setReplyUniqueId();
        }
    }

    private void disableShouldFocusReplyField() {
        if (!ListenerUtil.mutListener.listen(4328)) {
            mShouldFocusReplyField = false;
        }
    }

    public void enableShouldFocusReplyField() {
        if (!ListenerUtil.mutListener.listen(4329)) {
            mShouldFocusReplyField = true;
        }
    }

    @Override
    public Note getNote() {
        return mNote;
    }

    private SiteModel createDummyWordPressComSite(long siteId) {
        SiteModel site = new SiteModel();
        if (!ListenerUtil.mutListener.listen(4330)) {
            site.setIsWPCom(true);
        }
        if (!ListenerUtil.mutListener.listen(4331)) {
            site.setSiteId(siteId);
        }
        return site;
    }

    public void setNote(Note note) {
        if (!ListenerUtil.mutListener.listen(4332)) {
            mNote = note;
        }
        if (!ListenerUtil.mutListener.listen(4333)) {
            mSite = mSiteStore.getSiteBySiteId(note.getSiteId());
        }
        if (!ListenerUtil.mutListener.listen(4335)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(4334)) {
                    // This should not exist, we should clean that screen so a note without a site/comment can be displayed
                    mSite = createDummyWordPressComSite(mNote.getSiteId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4338)) {
            if ((ListenerUtil.mutListener.listen(4336) ? (isAdded() || mNote != null) : (isAdded() && mNote != null))) {
                if (!ListenerUtil.mutListener.listen(4337)) {
                    showComment();
                }
            }
        }
    }

    @Override
    public void setNote(String noteId) {
        if (!ListenerUtil.mutListener.listen(4340)) {
            if (noteId == null) {
                if (!ListenerUtil.mutListener.listen(4339)) {
                    showErrorToastAndFinish();
                }
                return;
            }
        }
        Note note = NotificationsTable.getNoteById(noteId);
        if (!ListenerUtil.mutListener.listen(4342)) {
            if (note == null) {
                if (!ListenerUtil.mutListener.listen(4341)) {
                    showErrorToastAndFinish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4343)) {
            setNote(note);
        }
    }

    private void setReplyText(String replyText) {
        if (!ListenerUtil.mutListener.listen(4344)) {
            if (replyText == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4345)) {
            mRestoredReplyText = replyText;
        }
    }

    private void showErrorToastAndFinish() {
        if (!ListenerUtil.mutListener.listen(4346)) {
            AppLog.e(AppLog.T.NOTIFS, "Note could not be found.");
        }
        if (!ListenerUtil.mutListener.listen(4349)) {
            if (getActivity() != null) {
                if (!ListenerUtil.mutListener.listen(4347)) {
                    ToastUtils.showToast(getActivity(), R.string.error_notification_open);
                }
                if (!ListenerUtil.mutListener.listen(4348)) {
                    getActivity().finish();
                }
            }
        }
    }

    // TODO: Remove when minSdkVersion >= 23
    @SuppressWarnings("deprecation")
    public void onAttach(@NotNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(4350)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(4352)) {
            if (activity instanceof OnPostClickListener) {
                if (!ListenerUtil.mutListener.listen(4351)) {
                    mOnPostClickListener = (OnPostClickListener) activity;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4354)) {
            if (activity instanceof OnCommentActionListener) {
                if (!ListenerUtil.mutListener.listen(4353)) {
                    mOnCommentActionListener = (OnCommentActionListener) activity;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4356)) {
            if (activity instanceof OnNoteCommentActionListener) {
                if (!ListenerUtil.mutListener.listen(4355)) {
                    mOnNoteCommentActionListener = (OnNoteCommentActionListener) activity;
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(4357)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(4358)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(4359)) {
            mCommentsStoreAdapter.register(this);
        }
        if (!ListenerUtil.mutListener.listen(4360)) {
            showComment();
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(4361)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(4362)) {
            mCommentsStoreAdapter.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(4363)) {
            super.onStop();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(SuggestionEvents.SuggestionNameListUpdated event) {
        if (!ListenerUtil.mutListener.listen(4368)) {
            // check if the updated suggestions are for the current blog and update the suggestions
            if ((ListenerUtil.mutListener.listen(4366) ? ((ListenerUtil.mutListener.listen(4365) ? ((ListenerUtil.mutListener.listen(4364) ? (event.mRemoteBlogId != 0 || mSite != null) : (event.mRemoteBlogId != 0 && mSite != null)) || event.mRemoteBlogId == mSite.getSiteId()) : ((ListenerUtil.mutListener.listen(4364) ? (event.mRemoteBlogId != 0 || mSite != null) : (event.mRemoteBlogId != 0 && mSite != null)) && event.mRemoteBlogId == mSite.getSiteId())) || mSuggestionAdapter != null) : ((ListenerUtil.mutListener.listen(4365) ? ((ListenerUtil.mutListener.listen(4364) ? (event.mRemoteBlogId != 0 || mSite != null) : (event.mRemoteBlogId != 0 && mSite != null)) || event.mRemoteBlogId == mSite.getSiteId()) : ((ListenerUtil.mutListener.listen(4364) ? (event.mRemoteBlogId != 0 || mSite != null) : (event.mRemoteBlogId != 0 && mSite != null)) && event.mRemoteBlogId == mSite.getSiteId())) && mSuggestionAdapter != null))) {
                List<UserSuggestion> userSuggestions = UserSuggestionTable.getSuggestionsForSite(event.mRemoteBlogId);
                List<Suggestion> suggestions = Suggestion.Companion.fromUserSuggestions(userSuggestions);
                if (!ListenerUtil.mutListener.listen(4367)) {
                    mSuggestionAdapter.setSuggestionList(suggestions);
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(4369)) {
            super.onPause();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(4370)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(4378)) {
            if ((ListenerUtil.mutListener.listen(4376) ? ((ListenerUtil.mutListener.listen(4375) ? (requestCode >= INTENT_COMMENT_EDITOR) : (ListenerUtil.mutListener.listen(4374) ? (requestCode <= INTENT_COMMENT_EDITOR) : (ListenerUtil.mutListener.listen(4373) ? (requestCode > INTENT_COMMENT_EDITOR) : (ListenerUtil.mutListener.listen(4372) ? (requestCode < INTENT_COMMENT_EDITOR) : (ListenerUtil.mutListener.listen(4371) ? (requestCode != INTENT_COMMENT_EDITOR) : (requestCode == INTENT_COMMENT_EDITOR)))))) || resultCode == Activity.RESULT_OK) : ((ListenerUtil.mutListener.listen(4375) ? (requestCode >= INTENT_COMMENT_EDITOR) : (ListenerUtil.mutListener.listen(4374) ? (requestCode <= INTENT_COMMENT_EDITOR) : (ListenerUtil.mutListener.listen(4373) ? (requestCode > INTENT_COMMENT_EDITOR) : (ListenerUtil.mutListener.listen(4372) ? (requestCode < INTENT_COMMENT_EDITOR) : (ListenerUtil.mutListener.listen(4371) ? (requestCode != INTENT_COMMENT_EDITOR) : (requestCode == INTENT_COMMENT_EDITOR)))))) && resultCode == Activity.RESULT_OK))) {
                if (!ListenerUtil.mutListener.listen(4377)) {
                    reloadComment();
                }
            }
        }
    }

    /**
     * Reload the current comment from the local database
     */
    private void reloadComment() {
        if (!ListenerUtil.mutListener.listen(4379)) {
            if (mComment == null) {
                return;
            }
        }
        CommentModel updatedComment = mCommentsStoreAdapter.getCommentByLocalId(mComment.getId());
        if (!ListenerUtil.mutListener.listen(4381)) {
            if (updatedComment != null) {
                if (!ListenerUtil.mutListener.listen(4380)) {
                    setComment(updatedComment, mSite);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4383)) {
            if (mNotificationsDetailListFragment != null) {
                if (!ListenerUtil.mutListener.listen(4382)) {
                    mNotificationsDetailListFragment.refreshBlocksForEditedComment(mNote.getId());
                }
            }
        }
    }

    /**
     * open the comment for editing
     */
    private void editComment() {
        if (!ListenerUtil.mutListener.listen(4385)) {
            if ((ListenerUtil.mutListener.listen(4384) ? (!isAdded() && mComment == null) : (!isAdded() || mComment == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4386)) {
            AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_EDITOR_OPENED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
        }
        if (!ListenerUtil.mutListener.listen(4394)) {
            // https://code.google.com/p/android/issues/detail?id=15394#c45
            if (mUnifiedCommentsCommentEditFeatureConfig.isEnabled()) {
                final CommentIdentifier commentIdentifier = mapCommentIdentifier();
                final Intent intent = UnifiedCommentsEditActivity.createIntent(requireActivity(), commentIdentifier, mSite);
                if (!ListenerUtil.mutListener.listen(4393)) {
                    startActivityForResult(intent, INTENT_COMMENT_EDITOR);
                }
            } else {
                Intent intent = new Intent(getActivity(), EditCommentActivity.class);
                if (!ListenerUtil.mutListener.listen(4387)) {
                    intent.putExtra(WordPress.SITE, mSite);
                }
                if (!ListenerUtil.mutListener.listen(4388)) {
                    intent.putExtra(EditCommentActivity.KEY_COMMENT, mComment);
                }
                if (!ListenerUtil.mutListener.listen(4391)) {
                    if ((ListenerUtil.mutListener.listen(4389) ? (mNote != null || mComment == null) : (mNote != null && mComment == null))) {
                        if (!ListenerUtil.mutListener.listen(4390)) {
                            intent.putExtra(EditCommentActivity.KEY_NOTE_ID, mNote.getId());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4392)) {
                    startActivityForResult(intent, INTENT_COMMENT_EDITOR);
                }
            }
        }
    }

    @Nullable
    private CommentIdentifier mapCommentIdentifier() {
        switch(mCommentSource) {
            case SITE_COMMENTS:
                return new SiteCommentIdentifier(mComment.getId(), mComment.getRemoteCommentId());
            case NOTIFICATION:
                return new NotificationCommentIdentifier(mNote.getId(), mNote.getCommentId());
            default:
                return null;
        }
    }

    /*
     * display the current comment
     */
    private void showComment() {
        if (!ListenerUtil.mutListener.listen(4396)) {
            if ((ListenerUtil.mutListener.listen(4395) ? (!isAdded() && getView() == null) : (!isAdded() || getView() == null))) {
                return;
            }
        }
        // these two views contain all the other views except the progress bar
        final View layoutBottom = getView().findViewById(R.id.layout_bottom);
        if (!ListenerUtil.mutListener.listen(4407)) {
            // hide container views when comment is null (will happen when opened from a notification)
            if (mComment == null) {
                if (!ListenerUtil.mutListener.listen(4397)) {
                    mNestedScrollView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4398)) {
                    layoutBottom.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4406)) {
                    if (mNote != null) {
                        SiteModel site = mSiteStore.getSiteBySiteId(mNote.getSiteId());
                        if (!ListenerUtil.mutListener.listen(4400)) {
                            if (site == null) {
                                if (!ListenerUtil.mutListener.listen(4399)) {
                                    // can be displayed
                                    site = createDummyWordPressComSite(mNote.getSiteId());
                                }
                            }
                        }
                        // Check if the comment is already in our store
                        CommentModel comment = mCommentsStoreAdapter.getCommentBySiteAndRemoteId(site, mNote.getCommentId());
                        if (!ListenerUtil.mutListener.listen(4405)) {
                            if (comment != null) {
                                if (!ListenerUtil.mutListener.listen(4404)) {
                                    // It exists, then show it as a "Notification"
                                    showCommentAsNotification(mNote, site, comment);
                                }
                            } else {
                                // It's not in our store yet, request it.
                                RemoteCommentPayload payload = new RemoteCommentPayload(site, mNote.getCommentId());
                                if (!ListenerUtil.mutListener.listen(4401)) {
                                    mCommentsStoreAdapter.dispatch(CommentActionBuilder.newFetchCommentAction(payload));
                                }
                                if (!ListenerUtil.mutListener.listen(4402)) {
                                    setProgressVisible(true);
                                }
                                if (!ListenerUtil.mutListener.listen(4403)) {
                                    // comment has been fetched.
                                    showCommentAsNotification(mNote, site, null);
                                }
                            }
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4408)) {
            mNestedScrollView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4409)) {
            layoutBottom.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4412)) {
            // Add action buttons footer
            if ((ListenerUtil.mutListener.listen(4410) ? (mNote == null || mLayoutButtons.getParent() == null) : (mNote == null && mLayoutButtons.getParent() == null))) {
                if (!ListenerUtil.mutListener.listen(4411)) {
                    mCommentContentLayout.addView(mLayoutButtons);
                }
            }
        }
        final ImageView imgAvatar = getView().findViewById(R.id.image_avatar);
        final TextView txtName = getView().findViewById(R.id.text_name);
        final TextView txtDate = getView().findViewById(R.id.text_date);
        if (!ListenerUtil.mutListener.listen(4413)) {
            txtName.setText(mComment.getAuthorName() == null ? getString(R.string.anonymous) : mComment.getAuthorName());
        }
        if (!ListenerUtil.mutListener.listen(4414)) {
            txtDate.setText(DateTimeUtils.javaDateToTimeSpan(DateTimeUtils.dateFromIso8601(mComment.getDatePublished()), WordPress.getContext()));
        }
        String renderingError = getString(R.string.comment_unable_to_show_error);
        if (!ListenerUtil.mutListener.listen(4415)) {
            mTxtContent.post(() -> CommentUtils.displayHtmlComment(mTxtContent, mComment.getContent(), mTxtContent.getWidth(), mTxtContent.getLineHeight(), renderingError));
        }
        int avatarSz = getResources().getDimensionPixelSize(R.dimen.avatar_sz_large);
        String avatarUrl = "";
        if (!ListenerUtil.mutListener.listen(4418)) {
            if (mComment.getAuthorProfileImageUrl() != null) {
                if (!ListenerUtil.mutListener.listen(4417)) {
                    avatarUrl = GravatarUtils.fixGravatarUrl(mComment.getAuthorProfileImageUrl(), avatarSz);
                }
            } else if (mComment.getAuthorEmail() != null) {
                if (!ListenerUtil.mutListener.listen(4416)) {
                    avatarUrl = GravatarUtils.gravatarFromEmail(mComment.getAuthorEmail(), avatarSz);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4419)) {
            mImageManager.loadIntoCircle(imgAvatar, ImageType.AVATAR_WITH_BACKGROUND, avatarUrl);
        }
        if (!ListenerUtil.mutListener.listen(4420)) {
            updateStatusViews();
        }
        if (!ListenerUtil.mutListener.listen(4425)) {
            // navigate to author's blog when avatar or name clicked
            if (mComment.getAuthorUrl() != null) {
                View.OnClickListener authorListener = v -> ReaderActivityLauncher.openUrl(getActivity(), mComment.getAuthorUrl());
                if (!ListenerUtil.mutListener.listen(4422)) {
                    imgAvatar.setOnClickListener(authorListener);
                }
                if (!ListenerUtil.mutListener.listen(4423)) {
                    txtName.setOnClickListener(authorListener);
                }
                if (!ListenerUtil.mutListener.listen(4424)) {
                    txtName.setTextColor(ContextExtensionsKt.getColorFromAttribute(txtName.getContext(), R.attr.colorPrimary));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4421)) {
                    txtName.setTextColor(ContextExtensionsKt.getColorFromAttribute(txtName.getContext(), R.attr.colorOnSurface));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4426)) {
            showPostTitle(mSite, mComment.getRemotePostId());
        }
        if (!ListenerUtil.mutListener.listen(4433)) {
            // make sure reply box is showing
            if ((ListenerUtil.mutListener.listen(4427) ? (mLayoutReply.getVisibility() != View.VISIBLE || canReply()) : (mLayoutReply.getVisibility() != View.VISIBLE && canReply()))) {
                if (!ListenerUtil.mutListener.listen(4428)) {
                    AniUtils.animateBottomBar(mLayoutReply, true);
                }
                if (!ListenerUtil.mutListener.listen(4432)) {
                    if ((ListenerUtil.mutListener.listen(4429) ? (mEditReply != null || mShouldFocusReplyField) : (mEditReply != null && mShouldFocusReplyField))) {
                        if (!ListenerUtil.mutListener.listen(4430)) {
                            mEditReply.performClick();
                        }
                        if (!ListenerUtil.mutListener.listen(4431)) {
                            disableShouldFocusReplyField();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4434)) {
            getActivity().invalidateOptionsMenu();
        }
    }

    /*
     * displays the passed post title for the current comment, updates stored title if one doesn't exist
     */
    private void setPostTitle(TextView txtTitle, String postTitle, boolean isHyperlink) {
        if (!ListenerUtil.mutListener.listen(4436)) {
            if ((ListenerUtil.mutListener.listen(4435) ? (txtTitle == null && !isAdded()) : (txtTitle == null || !isAdded()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4438)) {
            if (TextUtils.isEmpty(postTitle)) {
                if (!ListenerUtil.mutListener.listen(4437)) {
                    txtTitle.setText(R.string.untitled);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4442)) {
            // if comment doesn't have a post title, set it to the passed one and save to comment table
            if ((ListenerUtil.mutListener.listen(4439) ? (mComment != null || mComment.getPostTitle() == null) : (mComment != null && mComment.getPostTitle() == null))) {
                if (!ListenerUtil.mutListener.listen(4440)) {
                    mComment.setPostTitle(postTitle);
                }
                if (!ListenerUtil.mutListener.listen(4441)) {
                    mCommentsStoreAdapter.dispatch(CommentActionBuilder.newUpdateCommentAction(mComment));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4445)) {
            // display "on [Post Title]..."
            if (isHyperlink) {
                String html = getString(R.string.on) + " <font color=" + HtmlUtils.colorResToHtmlColor(getActivity(), ContextExtensionsKt.getColorResIdFromAttribute(getActivity(), R.attr.colorPrimary)) + ">" + postTitle.trim() + "</font>";
                if (!ListenerUtil.mutListener.listen(4444)) {
                    txtTitle.setText(Html.fromHtml(html));
                }
            } else {
                String text = getString(R.string.on) + " " + postTitle.trim();
                if (!ListenerUtil.mutListener.listen(4443)) {
                    txtTitle.setText(text);
                }
            }
        }
    }

    /*
     * ensure the post associated with this comment is available to the reader and show its
     * title above the comment
     */
    private void showPostTitle(final SiteModel site, final long postId) {
        if (!ListenerUtil.mutListener.listen(4446)) {
            if (!isAdded()) {
                return;
            }
        }
        final TextView txtPostTitle = getView().findViewById(R.id.text_post_title);
        boolean postExists = ReaderPostTable.postExists(site.getSiteId(), postId);
        // jetpack-enabled self-hosted blog, and we have valid .com credentials
        boolean canRequestPost = (ListenerUtil.mutListener.listen(4447) ? (SiteUtils.isAccessedViaWPComRest(site) || mAccountStore.hasAccessToken()) : (SiteUtils.isAccessedViaWPComRest(site) && mAccountStore.hasAccessToken()));
        final String title;
        final boolean hasTitle;
        if (mComment.getPostTitle() != null) {
            // use comment's stored post title if available
            title = mComment.getPostTitle();
            hasTitle = true;
        } else if (postExists) {
            // use title from post if available
            title = ReaderPostTable.getPostTitle(site.getSiteId(), postId);
            hasTitle = !TextUtils.isEmpty(title);
        } else {
            title = null;
            hasTitle = false;
        }
        if (!ListenerUtil.mutListener.listen(4450)) {
            if (hasTitle) {
                if (!ListenerUtil.mutListener.listen(4449)) {
                    setPostTitle(txtPostTitle, title, canRequestPost);
                }
            } else if (canRequestPost) {
                if (!ListenerUtil.mutListener.listen(4448)) {
                    txtPostTitle.setText(postExists ? R.string.untitled : R.string.loading);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4460)) {
            // in the reader
            if (canRequestPost) {
                if (!ListenerUtil.mutListener.listen(4458)) {
                    // the title if it wasn't set above
                    if (!postExists) {
                        if (!ListenerUtil.mutListener.listen(4451)) {
                            AppLog.d(T.COMMENTS, "comment detail > retrieving post");
                        }
                        if (!ListenerUtil.mutListener.listen(4457)) {
                            ReaderPostActions.requestBlogPost(site.getSiteId(), postId, new ReaderActions.OnRequestListener<String>() {

                                @Override
                                public void onSuccess(String blogUrl) {
                                    if (!ListenerUtil.mutListener.listen(4452)) {
                                        if (!isAdded()) {
                                            return;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(4456)) {
                                        // update title if it wasn't set above
                                        if (!hasTitle) {
                                            String postTitle = ReaderPostTable.getPostTitle(site.getSiteId(), postId);
                                            if (!ListenerUtil.mutListener.listen(4455)) {
                                                if (!TextUtils.isEmpty(postTitle)) {
                                                    if (!ListenerUtil.mutListener.listen(4454)) {
                                                        setPostTitle(txtPostTitle, postTitle, true);
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(4453)) {
                                                        txtPostTitle.setText(R.string.untitled);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(int statusCode) {
                                }
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4459)) {
                    txtPostTitle.setOnClickListener(v -> {
                        if (mOnPostClickListener != null) {
                            mOnPostClickListener.onPostClicked(getNote(), site.getSiteId(), (int) mComment.getRemotePostId());
                        } else {
                            // right now this will happen from notifications
                            AppLog.i(T.COMMENTS, "comment detail > no post click listener");
                            ReaderActivityLauncher.showReaderPostDetail(getActivity(), site.getSiteId(), mComment.getRemotePostId());
                        }
                    });
                }
            }
        }
    }

    // TODO klymyam remove legacy comment tracking after new comments are shipped and new funnels are made
    private void trackModerationEvent(final CommentStatus newStatus) {
        if (!ListenerUtil.mutListener.listen(4476)) {
            switch(newStatus) {
                case APPROVED:
                    if (!ListenerUtil.mutListener.listen(4462)) {
                        if (mCommentSource == CommentSource.NOTIFICATION) {
                            if (!ListenerUtil.mutListener.listen(4461)) {
                                AnalyticsTracker.track(Stat.NOTIFICATION_APPROVED);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4463)) {
                        AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_APPROVED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
                    }
                    break;
                case UNAPPROVED:
                    if (!ListenerUtil.mutListener.listen(4465)) {
                        if (mCommentSource == CommentSource.NOTIFICATION) {
                            if (!ListenerUtil.mutListener.listen(4464)) {
                                AnalyticsTracker.track(Stat.NOTIFICATION_UNAPPROVED);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4466)) {
                        AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_UNAPPROVED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
                    }
                    break;
                case SPAM:
                    if (!ListenerUtil.mutListener.listen(4468)) {
                        if (mCommentSource == CommentSource.NOTIFICATION) {
                            if (!ListenerUtil.mutListener.listen(4467)) {
                                AnalyticsTracker.track(Stat.NOTIFICATION_FLAGGED_AS_SPAM);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4469)) {
                        AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_SPAMMED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
                    }
                    break;
                case UNSPAM:
                    if (!ListenerUtil.mutListener.listen(4470)) {
                        AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_UNSPAMMED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
                    }
                    break;
                case TRASH:
                    if (!ListenerUtil.mutListener.listen(4472)) {
                        if (mCommentSource == CommentSource.NOTIFICATION) {
                            if (!ListenerUtil.mutListener.listen(4471)) {
                                AnalyticsTracker.track(Stat.NOTIFICATION_TRASHED);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4473)) {
                        AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_TRASHED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
                    }
                    break;
                case UNTRASH:
                    if (!ListenerUtil.mutListener.listen(4474)) {
                        AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_UNTRASHED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
                    }
                    break;
                case DELETED:
                    if (!ListenerUtil.mutListener.listen(4475)) {
                        AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_DELETED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
                    }
                    break;
                case ALL:
                    break;
            }
        }
    }

    /*
     * approve, disapprove, spam, or trash the current comment
     */
    private void moderateComment(CommentStatus newStatus) {
        if (!ListenerUtil.mutListener.listen(4478)) {
            if ((ListenerUtil.mutListener.listen(4477) ? (!isAdded() && mComment == null) : (!isAdded() || mComment == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4479)) {
            if (!NetworkUtils.checkConnection(getActivity())) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4480)) {
            mPreviousStatus = mComment.getStatus();
        }
        // instead of generic Approve action
        CommentStatus statusToTrack;
        if ((ListenerUtil.mutListener.listen(4481) ? (CommentStatus.fromString(mPreviousStatus) == CommentStatus.SPAM || newStatus == CommentStatus.APPROVED) : (CommentStatus.fromString(mPreviousStatus) == CommentStatus.SPAM && newStatus == CommentStatus.APPROVED))) {
            statusToTrack = CommentStatus.UNSPAM;
        } else if ((ListenerUtil.mutListener.listen(4482) ? (CommentStatus.fromString(mPreviousStatus) == CommentStatus.TRASH || newStatus == CommentStatus.APPROVED) : (CommentStatus.fromString(mPreviousStatus) == CommentStatus.TRASH && newStatus == CommentStatus.APPROVED))) {
            statusToTrack = CommentStatus.UNTRASH;
        } else {
            statusToTrack = newStatus;
        }
        if (!ListenerUtil.mutListener.listen(4483)) {
            trackModerationEvent(statusToTrack);
        }
        if (!ListenerUtil.mutListener.listen(4488)) {
            // Fire the appropriate listener if we have one
            if ((ListenerUtil.mutListener.listen(4484) ? (mNote != null || mOnNoteCommentActionListener != null) : (mNote != null && mOnNoteCommentActionListener != null))) {
                if (!ListenerUtil.mutListener.listen(4486)) {
                    mOnNoteCommentActionListener.onModerateCommentForNote(mNote, newStatus);
                }
                if (!ListenerUtil.mutListener.listen(4487)) {
                    dispatchModerationAction(newStatus);
                }
            } else if (mOnCommentActionListener != null) {
                if (!ListenerUtil.mutListener.listen(4485)) {
                    mOnCommentActionListener.onModerateComment(mSite, mComment, newStatus);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4489)) {
            updateStatusViews();
        }
    }

    private void dispatchModerationAction(CommentStatus newStatus) {
        if (!ListenerUtil.mutListener.listen(4493)) {
            if (newStatus == CommentStatus.DELETED) {
                if (!ListenerUtil.mutListener.listen(4492)) {
                    // For deletion, we need to dispatch a specific action.
                    mCommentsStoreAdapter.dispatch(CommentActionBuilder.newDeleteCommentAction(new RemoteCommentPayload(mSite, mComment)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4490)) {
                    // Actual moderation (push the modified comment).
                    mComment.setStatus(newStatus.toString());
                }
                if (!ListenerUtil.mutListener.listen(4491)) {
                    mCommentsStoreAdapter.dispatch(CommentActionBuilder.newPushCommentAction(new RemoteCommentPayload(mSite, mComment)));
                }
            }
        }
    }

    /*
     * post comment box text as a reply to the current comment
     */
    private void submitReply() {
        if (!ListenerUtil.mutListener.listen(4496)) {
            if ((ListenerUtil.mutListener.listen(4495) ? ((ListenerUtil.mutListener.listen(4494) ? (mComment == null && !isAdded()) : (mComment == null || !isAdded())) && mIsSubmittingReply) : ((ListenerUtil.mutListener.listen(4494) ? (mComment == null && !isAdded()) : (mComment == null || !isAdded())) || mIsSubmittingReply))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4497)) {
            if (!NetworkUtils.checkConnection(getActivity())) {
                return;
            }
        }
        final String replyText = EditTextUtils.getText(mEditReply);
        if (!ListenerUtil.mutListener.listen(4498)) {
            if (TextUtils.isEmpty(replyText)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4499)) {
            // disable editor, hide soft keyboard, hide submit icon, and show progress spinner while submitting
            mEditReply.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(4500)) {
            EditTextUtils.hideSoftInput(mEditReply);
        }
        if (!ListenerUtil.mutListener.listen(4501)) {
            mSubmitReplyBtn.setVisibility(View.GONE);
        }
        final ProgressBar progress = getView().findViewById(R.id.progress_submit_comment);
        if (!ListenerUtil.mutListener.listen(4502)) {
            progress.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4503)) {
            mIsSubmittingReply = true;
        }
        if (!ListenerUtil.mutListener.listen(4504)) {
            AnalyticsUtils.trackCommentReplyWithDetails(false, mSite, mComment, mCommentSource.toAnalyticsCommentActionSource());
        }
        // Pseudo comment reply
        CommentModel reply = new CommentModel();
        if (!ListenerUtil.mutListener.listen(4505)) {
            reply.setContent(replyText);
        }
        if (!ListenerUtil.mutListener.listen(4506)) {
            mCommentsStoreAdapter.dispatch(CommentActionBuilder.newCreateNewCommentAction(new RemoteCreateCommentPayload(mSite, mComment, reply)));
        }
    }

    /*
     * update the text, drawable & click listener for mBtnModerate based on
     * the current status of the comment, show mBtnSpam if the comment isn't
     * already marked as spam, and show the current status of the comment
     */
    private void updateStatusViews() {
        if (!ListenerUtil.mutListener.listen(4508)) {
            if ((ListenerUtil.mutListener.listen(4507) ? (!isAdded() && mComment == null) : (!isAdded() || mComment == null))) {
                return;
            }
        }
        // string resource id for status text
        final int statusTextResId;
        // color for status text
        final int statusColor;
        CommentStatus commentStatus = CommentStatus.fromString(mComment.getStatus());
        switch(commentStatus) {
            case APPROVED:
                statusTextResId = R.string.comment_status_approved;
                statusColor = ContextExtensionsKt.getColorFromAttribute(getActivity(), R.attr.wpColorWarningDark);
                break;
            case UNAPPROVED:
                statusTextResId = R.string.comment_status_unapproved;
                statusColor = ContextExtensionsKt.getColorFromAttribute(getActivity(), R.attr.wpColorWarningDark);
                break;
            case SPAM:
                statusTextResId = R.string.comment_status_spam;
                statusColor = ContextExtensionsKt.getColorFromAttribute(getActivity(), R.attr.colorError);
                break;
            case TRASH:
            default:
                statusTextResId = R.string.comment_status_trash;
                statusColor = ContextExtensionsKt.getColorFromAttribute(getActivity(), R.attr.colorError);
                break;
        }
        if (!ListenerUtil.mutListener.listen(4513)) {
            if (canLike()) {
                if (!ListenerUtil.mutListener.listen(4509)) {
                    mBtnLikeComment.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4512)) {
                    if (mComment != null) {
                        if (!ListenerUtil.mutListener.listen(4511)) {
                            toggleLikeButton(mComment.getILike());
                        }
                    } else if (mNote != null) {
                        if (!ListenerUtil.mutListener.listen(4510)) {
                            mNote.hasLikedComment();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4521)) {
            // comment hasn't been CommentStatus.APPROVED
            if ((ListenerUtil.mutListener.listen(4514) ? (mIsUsersBlog || commentStatus != CommentStatus.APPROVED) : (mIsUsersBlog && commentStatus != CommentStatus.APPROVED))) {
                if (!ListenerUtil.mutListener.listen(4516)) {
                    mTxtStatus.setText(getString(statusTextResId).toUpperCase(Locale.getDefault()));
                }
                if (!ListenerUtil.mutListener.listen(4517)) {
                    mTxtStatus.setTextColor(statusColor);
                }
                if (!ListenerUtil.mutListener.listen(4520)) {
                    if (mTxtStatus.getVisibility() != View.VISIBLE) {
                        if (!ListenerUtil.mutListener.listen(4518)) {
                            mTxtStatus.clearAnimation();
                        }
                        if (!ListenerUtil.mutListener.listen(4519)) {
                            AniUtils.fadeIn(mTxtStatus, AniUtils.Duration.LONG);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4515)) {
                    mTxtStatus.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4526)) {
            if (canModerate()) {
                if (!ListenerUtil.mutListener.listen(4523)) {
                    setModerateButtonForStatus(commentStatus);
                }
                if (!ListenerUtil.mutListener.listen(4524)) {
                    mBtnModerateComment.setOnClickListener(v -> performModerateAction());
                }
                if (!ListenerUtil.mutListener.listen(4525)) {
                    mBtnModerateComment.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4522)) {
                    mBtnModerateComment.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4532)) {
            if (canMarkAsSpam()) {
                if (!ListenerUtil.mutListener.listen(4528)) {
                    mBtnSpamComment.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4531)) {
                    if (commentStatus == CommentStatus.SPAM) {
                        if (!ListenerUtil.mutListener.listen(4530)) {
                            mBtnSpamCommentText.setText(R.string.mnu_comment_unspam);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4529)) {
                            mBtnSpamCommentText.setText(R.string.mnu_comment_spam);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4527)) {
                    mBtnSpamComment.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4536)) {
            if (canTrash()) {
                if (!ListenerUtil.mutListener.listen(4535)) {
                    if (commentStatus == CommentStatus.TRASH) {
                        if (!ListenerUtil.mutListener.listen(4533)) {
                            ColorUtils.INSTANCE.setImageResourceWithTint(mBtnModerateIcon, R.drawable.ic_undo_white_24dp, ContextExtensionsKt.getColorResIdFromAttribute(mBtnModerateTextView.getContext(), R.attr.colorOnSurface));
                        }
                        if (!ListenerUtil.mutListener.listen(4534)) {
                            mBtnModerateTextView.setText(R.string.mnu_comment_untrash);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4539)) {
            if (canShowMore()) {
                if (!ListenerUtil.mutListener.listen(4538)) {
                    mBtnMoreComment.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4537)) {
                    mBtnMoreComment.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4540)) {
            mLayoutButtons.setVisibility(View.VISIBLE);
        }
    }

    private void performModerateAction() {
        if (!ListenerUtil.mutListener.listen(4543)) {
            if ((ListenerUtil.mutListener.listen(4542) ? ((ListenerUtil.mutListener.listen(4541) ? (mComment == null && !isAdded()) : (mComment == null || !isAdded())) && !NetworkUtils.checkConnection(getActivity())) : ((ListenerUtil.mutListener.listen(4541) ? (mComment == null && !isAdded()) : (mComment == null || !isAdded())) || !NetworkUtils.checkConnection(getActivity())))) {
                return;
            }
        }
        CommentStatus newStatus = CommentStatus.APPROVED;
        CommentStatus currentStatus = CommentStatus.fromString(mComment.getStatus());
        if (!ListenerUtil.mutListener.listen(4545)) {
            if (currentStatus == CommentStatus.APPROVED) {
                if (!ListenerUtil.mutListener.listen(4544)) {
                    newStatus = CommentStatus.UNAPPROVED;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4546)) {
            announceCommentStatusChangeForAccessibility(currentStatus == CommentStatus.TRASH ? CommentStatus.UNTRASH : newStatus);
        }
        if (!ListenerUtil.mutListener.listen(4547)) {
            setModerateButtonForStatus(newStatus);
        }
        if (!ListenerUtil.mutListener.listen(4548)) {
            AniUtils.startAnimation(mBtnModerateIcon, R.anim.notifications_button_scale);
        }
        if (!ListenerUtil.mutListener.listen(4549)) {
            moderateComment(newStatus);
        }
    }

    private void setModerateButtonForStatus(CommentStatus status) {
        int color;
        if (status == CommentStatus.APPROVED) {
            color = ContextExtensionsKt.getColorResIdFromAttribute(mBtnModerateTextView.getContext(), R.attr.colorSecondary);
            if (!ListenerUtil.mutListener.listen(4553)) {
                mBtnModerateTextView.setText(R.string.comment_status_approved);
            }
            if (!ListenerUtil.mutListener.listen(4554)) {
                mBtnModerateTextView.setAlpha(mNormalOpacity);
            }
            if (!ListenerUtil.mutListener.listen(4555)) {
                mBtnModerateIcon.setAlpha(mNormalOpacity);
            }
        } else {
            color = ContextExtensionsKt.getColorResIdFromAttribute(mBtnModerateTextView.getContext(), R.attr.colorOnSurface);
            if (!ListenerUtil.mutListener.listen(4550)) {
                mBtnModerateTextView.setText(R.string.mnu_comment_approve);
            }
            if (!ListenerUtil.mutListener.listen(4551)) {
                mBtnModerateTextView.setAlpha(mMediumOpacity);
            }
            if (!ListenerUtil.mutListener.listen(4552)) {
                mBtnModerateIcon.setAlpha(mMediumOpacity);
            }
        }
        if (!ListenerUtil.mutListener.listen(4556)) {
            ColorUtils.INSTANCE.setImageResourceWithTint(mBtnModerateIcon, R.drawable.ic_checkmark_white_24dp, color);
        }
        if (!ListenerUtil.mutListener.listen(4557)) {
            mBtnModerateTextView.setTextColor(ContextCompat.getColor(requireContext(), color));
        }
    }

    /*
     * does user have permission to moderate/reply/spam this comment?
     */
    private boolean canModerate() {
        return (ListenerUtil.mutListener.listen(4559) ? (mEnabledActions != null || ((ListenerUtil.mutListener.listen(4558) ? (mEnabledActions.contains(EnabledActions.ACTION_APPROVE) && mEnabledActions.contains(EnabledActions.ACTION_UNAPPROVE)) : (mEnabledActions.contains(EnabledActions.ACTION_APPROVE) || mEnabledActions.contains(EnabledActions.ACTION_UNAPPROVE))))) : (mEnabledActions != null && ((ListenerUtil.mutListener.listen(4558) ? (mEnabledActions.contains(EnabledActions.ACTION_APPROVE) && mEnabledActions.contains(EnabledActions.ACTION_UNAPPROVE)) : (mEnabledActions.contains(EnabledActions.ACTION_APPROVE) || mEnabledActions.contains(EnabledActions.ACTION_UNAPPROVE))))));
    }

    private boolean canMarkAsSpam() {
        return ((ListenerUtil.mutListener.listen(4560) ? (mEnabledActions != null || mEnabledActions.contains(EnabledActions.ACTION_SPAM)) : (mEnabledActions != null && mEnabledActions.contains(EnabledActions.ACTION_SPAM))));
    }

    private boolean canReply() {
        return ((ListenerUtil.mutListener.listen(4561) ? (mEnabledActions != null || mEnabledActions.contains(EnabledActions.ACTION_REPLY)) : (mEnabledActions != null && mEnabledActions.contains(EnabledActions.ACTION_REPLY))));
    }

    private boolean canTrash() {
        return canModerate();
    }

    private boolean canEdit() {
        return (ListenerUtil.mutListener.listen(4563) ? (mSite != null || ((ListenerUtil.mutListener.listen(4562) ? (mSite.getHasCapabilityEditOthersPosts() && mSite.isSelfHostedAdmin()) : (mSite.getHasCapabilityEditOthersPosts() || mSite.isSelfHostedAdmin())))) : (mSite != null && ((ListenerUtil.mutListener.listen(4562) ? (mSite.getHasCapabilityEditOthersPosts() && mSite.isSelfHostedAdmin()) : (mSite.getHasCapabilityEditOthersPosts() || mSite.isSelfHostedAdmin())))));
    }

    private boolean canLike() {
        return ((ListenerUtil.mutListener.listen(4566) ? ((ListenerUtil.mutListener.listen(4565) ? ((ListenerUtil.mutListener.listen(4564) ? (mEnabledActions != null || mEnabledActions.contains(EnabledActions.ACTION_LIKE)) : (mEnabledActions != null && mEnabledActions.contains(EnabledActions.ACTION_LIKE))) || mSite != null) : ((ListenerUtil.mutListener.listen(4564) ? (mEnabledActions != null || mEnabledActions.contains(EnabledActions.ACTION_LIKE)) : (mEnabledActions != null && mEnabledActions.contains(EnabledActions.ACTION_LIKE))) && mSite != null)) || SiteUtils.isAccessedViaWPComRest(mSite)) : ((ListenerUtil.mutListener.listen(4565) ? ((ListenerUtil.mutListener.listen(4564) ? (mEnabledActions != null || mEnabledActions.contains(EnabledActions.ACTION_LIKE)) : (mEnabledActions != null && mEnabledActions.contains(EnabledActions.ACTION_LIKE))) || mSite != null) : ((ListenerUtil.mutListener.listen(4564) ? (mEnabledActions != null || mEnabledActions.contains(EnabledActions.ACTION_LIKE)) : (mEnabledActions != null && mEnabledActions.contains(EnabledActions.ACTION_LIKE))) && mSite != null)) && SiteUtils.isAccessedViaWPComRest(mSite))));
    }

    /*
     * The more button contains controls which only moderates can use
     */
    private boolean canShowMore() {
        return canModerate();
    }

    /*
     * display the comment associated with the passed notification
     */
    private void showCommentAsNotification(Note note, @NonNull SiteModel site, @Nullable CommentModel comment) {
        if (!ListenerUtil.mutListener.listen(4567)) {
            if (getView() == null) {
                return;
            }
        }
        View view = getView();
        // hide standard comment views, since we'll be adding note blocks instead
        View commentContent = view.findViewById(R.id.comment_content);
        if (!ListenerUtil.mutListener.listen(4569)) {
            if (commentContent != null) {
                if (!ListenerUtil.mutListener.listen(4568)) {
                    commentContent.setVisibility(View.GONE);
                }
            }
        }
        View commentText = view.findViewById(R.id.text_content);
        if (!ListenerUtil.mutListener.listen(4571)) {
            if (commentText != null) {
                if (!ListenerUtil.mutListener.listen(4570)) {
                    commentText.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4572)) {
            /*
         * determine which actions to enable for this comment - if the comment is from this user's
         * blog then all actions will be enabled, but they won't be if it's a reply to a comment
         * this user made on someone else's blog
         */
            mEnabledActions = note.getEnabledActions();
        }
        if (!ListenerUtil.mutListener.listen(4580)) {
            // Set 'Reply to (Name)' in comment reply EditText if it's a reasonable size
            if ((ListenerUtil.mutListener.listen(4578) ? (!TextUtils.isEmpty(mNote.getCommentAuthorName()) || (ListenerUtil.mutListener.listen(4577) ? (mNote.getCommentAuthorName().length() >= 28) : (ListenerUtil.mutListener.listen(4576) ? (mNote.getCommentAuthorName().length() <= 28) : (ListenerUtil.mutListener.listen(4575) ? (mNote.getCommentAuthorName().length() > 28) : (ListenerUtil.mutListener.listen(4574) ? (mNote.getCommentAuthorName().length() != 28) : (ListenerUtil.mutListener.listen(4573) ? (mNote.getCommentAuthorName().length() == 28) : (mNote.getCommentAuthorName().length() < 28))))))) : (!TextUtils.isEmpty(mNote.getCommentAuthorName()) && (ListenerUtil.mutListener.listen(4577) ? (mNote.getCommentAuthorName().length() >= 28) : (ListenerUtil.mutListener.listen(4576) ? (mNote.getCommentAuthorName().length() <= 28) : (ListenerUtil.mutListener.listen(4575) ? (mNote.getCommentAuthorName().length() > 28) : (ListenerUtil.mutListener.listen(4574) ? (mNote.getCommentAuthorName().length() != 28) : (ListenerUtil.mutListener.listen(4573) ? (mNote.getCommentAuthorName().length() == 28) : (mNote.getCommentAuthorName().length() < 28))))))))) {
                if (!ListenerUtil.mutListener.listen(4579)) {
                    mEditReply.setHint(String.format(getString(R.string.comment_reply_to_user), mNote.getCommentAuthorName()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4583)) {
            if (comment != null) {
                if (!ListenerUtil.mutListener.listen(4582)) {
                    setComment(comment, site);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4581)) {
                    setComment(note.buildComment(), site);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4584)) {
            addDetailFragment(note.getId());
        }
        if (!ListenerUtil.mutListener.listen(4585)) {
            getActivity().invalidateOptionsMenu();
        }
    }

    private void addDetailFragment(String noteId) {
        // Now we'll add a detail fragment list
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!ListenerUtil.mutListener.listen(4586)) {
            mNotificationsDetailListFragment = NotificationsDetailListFragment.newInstance(noteId);
        }
        if (!ListenerUtil.mutListener.listen(4587)) {
            mNotificationsDetailListFragment.setFooterView(mLayoutButtons);
        }
        if (!ListenerUtil.mutListener.listen(4588)) {
            fragmentTransaction.replace(mCommentContentLayout.getId(), mNotificationsDetailListFragment);
        }
        if (!ListenerUtil.mutListener.listen(4589)) {
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    // Like or unlike a comment via the REST API
    private void likeComment(boolean forceLike) {
        if (!ListenerUtil.mutListener.listen(4590)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4592)) {
            if ((ListenerUtil.mutListener.listen(4591) ? (forceLike || mBtnLikeComment.isActivated()) : (forceLike && mBtnLikeComment.isActivated()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4593)) {
            toggleLikeButton(!mBtnLikeComment.isActivated());
        }
        if (!ListenerUtil.mutListener.listen(4594)) {
            ReaderAnim.animateLikeButton(mBtnLikeIcon, mBtnLikeComment.isActivated());
        }
        if (!ListenerUtil.mutListener.listen(4596)) {
            // TODO klymyam remove legacy comment tracking after new comments are shipped and new funnels are made
            if (mCommentSource == CommentSource.NOTIFICATION) {
                if (!ListenerUtil.mutListener.listen(4595)) {
                    AnalyticsTracker.track(mBtnLikeComment.isActivated() ? Stat.NOTIFICATION_LIKED : Stat.NOTIFICATION_UNLIKED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4597)) {
            AnalyticsUtils.trackCommentActionWithSiteDetails(mBtnLikeComment.isActivated() ? Stat.COMMENT_LIKED : Stat.COMMENT_UNLIKED, mCommentSource.toAnalyticsCommentActionSource(), mSite);
        }
        if (!ListenerUtil.mutListener.listen(4604)) {
            if ((ListenerUtil.mutListener.listen(4598) ? (mNotificationsDetailListFragment != null || mComment != null) : (mNotificationsDetailListFragment != null && mComment != null))) {
                if (!ListenerUtil.mutListener.listen(4603)) {
                    // WP.com will set a comment to approved if it is liked while unapproved
                    if ((ListenerUtil.mutListener.listen(4599) ? (mBtnLikeComment.isActivated() || CommentStatus.fromString(mComment.getStatus()) == CommentStatus.UNAPPROVED) : (mBtnLikeComment.isActivated() && CommentStatus.fromString(mComment.getStatus()) == CommentStatus.UNAPPROVED))) {
                        if (!ListenerUtil.mutListener.listen(4600)) {
                            mComment.setStatus(CommentStatus.APPROVED.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(4601)) {
                            mNotificationsDetailListFragment.refreshBlocksForCommentStatus(CommentStatus.APPROVED);
                        }
                        if (!ListenerUtil.mutListener.listen(4602)) {
                            setModerateButtonForStatus(CommentStatus.APPROVED);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4605)) {
            mCommentsStoreAdapter.dispatch(CommentActionBuilder.newLikeCommentAction(new RemoteLikeCommentPayload(mSite, mComment, mBtnLikeComment.isActivated())));
        }
        if (!ListenerUtil.mutListener.listen(4606)) {
            mBtnLikeComment.announceForAccessibility(getText(mBtnLikeComment.isActivated() ? R.string.comment_liked_talkback : R.string.comment_unliked_talkback));
        }
    }

    private void toggleLikeButton(boolean isLiked) {
        int color;
        int drawable;
        if (isLiked) {
            color = ContextExtensionsKt.getColorResIdFromAttribute(mBtnLikeIcon.getContext(), R.attr.colorSecondary);
            drawable = R.drawable.ic_star_white_24dp;
            if (!ListenerUtil.mutListener.listen(4611)) {
                mBtnLikeTextView.setText(getResources().getString(R.string.mnu_comment_liked));
            }
            if (!ListenerUtil.mutListener.listen(4612)) {
                mBtnLikeComment.setActivated(true);
            }
            if (!ListenerUtil.mutListener.listen(4613)) {
                mBtnLikeTextView.setAlpha(mNormalOpacity);
            }
            if (!ListenerUtil.mutListener.listen(4614)) {
                mBtnLikeIcon.setAlpha(mNormalOpacity);
            }
        } else {
            color = ContextExtensionsKt.getColorResIdFromAttribute(mBtnLikeIcon.getContext(), R.attr.colorOnSurface);
            drawable = R.drawable.ic_star_outline_white_24dp;
            if (!ListenerUtil.mutListener.listen(4607)) {
                mBtnLikeTextView.setText(getResources().getString(R.string.reader_label_like));
            }
            if (!ListenerUtil.mutListener.listen(4608)) {
                mBtnLikeComment.setActivated(false);
            }
            if (!ListenerUtil.mutListener.listen(4609)) {
                mBtnLikeTextView.setAlpha(mMediumOpacity);
            }
            if (!ListenerUtil.mutListener.listen(4610)) {
                mBtnLikeIcon.setAlpha(mMediumOpacity);
            }
        }
        if (!ListenerUtil.mutListener.listen(4615)) {
            ColorUtils.INSTANCE.setImageResourceWithTint(mBtnLikeIcon, drawable, color);
        }
        if (!ListenerUtil.mutListener.listen(4616)) {
            mBtnLikeTextView.setTextColor(ContextCompat.getColor(requireContext(), color));
        }
    }

    private void setProgressVisible(boolean visible) {
        final ProgressBar progress = ((ListenerUtil.mutListener.listen(4617) ? (isAdded() || getView() != null) : (isAdded() && getView() != null)) ? (ProgressBar) getView().findViewById(R.id.progress_loading) : null);
        if (!ListenerUtil.mutListener.listen(4619)) {
            if (progress != null) {
                if (!ListenerUtil.mutListener.listen(4618)) {
                    progress.setVisibility(visible ? View.VISIBLE : View.GONE);
                }
            }
        }
    }

    private void onCommentModerated(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(4621)) {
            // send signal for listeners to perform any needed updates
            if (mNote != null) {
                if (!ListenerUtil.mutListener.listen(4620)) {
                    EventBus.getDefault().postSticky(new NotificationEvents.NoteLikeOrModerationStatusChanged(mNote.getId()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4622)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4627)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(4624)) {
                    mComment.setStatus(mPreviousStatus);
                }
                if (!ListenerUtil.mutListener.listen(4625)) {
                    updateStatusViews();
                }
                if (!ListenerUtil.mutListener.listen(4626)) {
                    ToastUtils.showToast(getActivity(), R.string.error_moderate_comment);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4623)) {
                    reloadComment();
                }
            }
        }
    }

    private void onCommentCreated(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(4628)) {
            mIsSubmittingReply = false;
        }
        if (!ListenerUtil.mutListener.listen(4629)) {
            mEditReply.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(4630)) {
            mSubmitReplyBtn.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4631)) {
            getView().findViewById(R.id.progress_submit_comment).setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4632)) {
            updateStatusViews();
        }
        if (!ListenerUtil.mutListener.listen(4636)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(4635)) {
                    if (isAdded()) {
                        String strUnEscapeHTML = StringEscapeUtils.unescapeHtml4(event.error.message);
                        if (!ListenerUtil.mutListener.listen(4633)) {
                            ToastUtils.showToast(getActivity(), strUnEscapeHTML, ToastUtils.Duration.LONG);
                        }
                        if (!ListenerUtil.mutListener.listen(4634)) {
                            // refocus editor on failure and show soft keyboard
                            EditTextUtils.showSoftInput(mEditReply);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4637)) {
            reloadComment();
        }
        if (!ListenerUtil.mutListener.listen(4641)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(4638)) {
                    ToastUtils.showToast(getActivity(), getString(R.string.note_reply_successful));
                }
                if (!ListenerUtil.mutListener.listen(4639)) {
                    mEditReply.setText(null);
                }
                if (!ListenerUtil.mutListener.listen(4640)) {
                    mEditReply.getAutoSaveTextHelper().clearSavedText(mEditReply);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4645)) {
            // Self Hosted site does not return a newly created comment, so we need to fetch it manually.
            if ((ListenerUtil.mutListener.listen(4642) ? (!mSite.isUsingWpComRestApi() || !event.changedCommentsLocalIds.isEmpty()) : (!mSite.isUsingWpComRestApi() && !event.changedCommentsLocalIds.isEmpty()))) {
                CommentModel createdComment = mCommentsStoreAdapter.getCommentByLocalId(event.changedCommentsLocalIds.get(0));
                if (!ListenerUtil.mutListener.listen(4644)) {
                    if (createdComment != null) {
                        if (!ListenerUtil.mutListener.listen(4643)) {
                            mCommentsStoreAdapter.dispatch(CommentActionBuilder.newFetchCommentAction(new RemoteCommentPayload(mSite, createdComment.getRemoteCommentId())));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4648)) {
            // approve the comment
            if ((ListenerUtil.mutListener.listen(4646) ? (mComment != null || !(CommentStatus.fromString(mComment.getStatus()) == CommentStatus.APPROVED)) : (mComment != null && !(CommentStatus.fromString(mComment.getStatus()) == CommentStatus.APPROVED)))) {
                if (!ListenerUtil.mutListener.listen(4647)) {
                    moderateComment(CommentStatus.APPROVED);
                }
            }
        }
    }

    private void onCommentLiked(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(4650)) {
            // send signal for listeners to perform any needed updates
            if (mNote != null) {
                if (!ListenerUtil.mutListener.listen(4649)) {
                    EventBus.getDefault().postSticky(new NotificationEvents.NoteLikeOrModerationStatusChanged(mNote.getId()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4652)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(4651)) {
                    // Revert button state in case of an error
                    toggleLikeButton(!mBtnLikeComment.isActivated());
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCommentChanged(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(4653)) {
            setProgressVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(4654)) {
            // requesting local comment cache refresh
            BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getMain(), CoroutineStart.DEFAULT, (coroutineScope, continuation) -> mLocalCommentCacheUpdateHandler.requestCommentsUpdate(continuation));
        }
        if (!ListenerUtil.mutListener.listen(4657)) {
            // Moderating comment
            if (event.causeOfChange == CommentAction.PUSH_COMMENT) {
                if (!ListenerUtil.mutListener.listen(4655)) {
                    onCommentModerated(event);
                }
                if (!ListenerUtil.mutListener.listen(4656)) {
                    mPreviousStatus = null;
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4659)) {
            // New comment (reply)
            if (event.causeOfChange == CommentAction.CREATE_NEW_COMMENT) {
                if (!ListenerUtil.mutListener.listen(4658)) {
                    onCommentCreated(event);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4661)) {
            // Like/Unlike
            if (event.causeOfChange == CommentAction.LIKE_COMMENT) {
                if (!ListenerUtil.mutListener.listen(4660)) {
                    onCommentLiked(event);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4666)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(4662)) {
                    AppLog.i(T.TESTS, "event error type: " + event.error.type + " - message: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(4665)) {
                    if ((ListenerUtil.mutListener.listen(4663) ? (isAdded() || !TextUtils.isEmpty(event.error.message)) : (isAdded() && !TextUtils.isEmpty(event.error.message)))) {
                        if (!ListenerUtil.mutListener.listen(4664)) {
                            ToastUtils.showToast(getActivity(), event.error.message);
                        }
                    }
                }
                return;
            }
        }
    }

    private void announceCommentStatusChangeForAccessibility(CommentStatus newStatus) {
        int resId = -1;
        if (!ListenerUtil.mutListener.listen(4675)) {
            switch(newStatus) {
                case APPROVED:
                    if (!ListenerUtil.mutListener.listen(4667)) {
                        resId = R.string.comment_approved_talkback;
                    }
                    break;
                case UNAPPROVED:
                    if (!ListenerUtil.mutListener.listen(4668)) {
                        resId = R.string.comment_unapproved_talkback;
                    }
                    break;
                case SPAM:
                    if (!ListenerUtil.mutListener.listen(4669)) {
                        resId = R.string.comment_spam_talkback;
                    }
                    break;
                case TRASH:
                    if (!ListenerUtil.mutListener.listen(4670)) {
                        resId = R.string.comment_trash_talkback;
                    }
                    break;
                case DELETED:
                    if (!ListenerUtil.mutListener.listen(4671)) {
                        resId = R.string.comment_delete_talkback;
                    }
                    break;
                case UNSPAM:
                    if (!ListenerUtil.mutListener.listen(4672)) {
                        resId = R.string.comment_unspam_talkback;
                    }
                    break;
                case UNTRASH:
                    if (!ListenerUtil.mutListener.listen(4673)) {
                        resId = R.string.comment_untrash_talkback;
                    }
                    break;
                case ALL:
                    // ignore
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(4674)) {
                        AppLog.w(T.COMMENTS, "AnnounceCommentStatusChangeForAccessibility - Missing switch branch for comment status: " + newStatus);
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(4683)) {
            if ((ListenerUtil.mutListener.listen(4681) ? ((ListenerUtil.mutListener.listen(4680) ? (resId >= -1) : (ListenerUtil.mutListener.listen(4679) ? (resId <= -1) : (ListenerUtil.mutListener.listen(4678) ? (resId > -1) : (ListenerUtil.mutListener.listen(4677) ? (resId < -1) : (ListenerUtil.mutListener.listen(4676) ? (resId == -1) : (resId != -1)))))) || getView() != null) : ((ListenerUtil.mutListener.listen(4680) ? (resId >= -1) : (ListenerUtil.mutListener.listen(4679) ? (resId <= -1) : (ListenerUtil.mutListener.listen(4678) ? (resId > -1) : (ListenerUtil.mutListener.listen(4677) ? (resId < -1) : (ListenerUtil.mutListener.listen(4676) ? (resId == -1) : (resId != -1)))))) && getView() != null))) {
                if (!ListenerUtil.mutListener.listen(4682)) {
                    getView().announceForAccessibility(getText(resId));
                }
            }
        }
    }

    // Handle More Menu
    private void showMoreMenu(View view) {
        androidx.appcompat.widget.PopupMenu morePopupMenu = new androidx.appcompat.widget.PopupMenu(requireContext(), view);
        if (!ListenerUtil.mutListener.listen(4684)) {
            morePopupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.action_edit) {
                    editComment();
                    return true;
                }
                if (item.getItemId() == R.id.action_trash) {
                    trashComment();
                    return true;
                }
                if (item.getItemId() == R.id.action_copy_link_address) {
                    copyCommentLinkAddress();
                    return true;
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(4685)) {
            morePopupMenu.inflate(R.menu.menu_comment_more);
        }
        MenuItem trashMenuItem = morePopupMenu.getMenu().findItem(R.id.action_trash);
        MenuItem copyLinkAddress = morePopupMenu.getMenu().findItem(R.id.action_copy_link_address);
        if (!ListenerUtil.mutListener.listen(4695)) {
            if (canTrash()) {
                CommentStatus commentStatus = CommentStatus.fromString(mComment.getStatus());
                if (!ListenerUtil.mutListener.listen(4694)) {
                    if (commentStatus == CommentStatus.TRASH) {
                        if (!ListenerUtil.mutListener.listen(4692)) {
                            copyLinkAddress.setVisible(false);
                        }
                        if (!ListenerUtil.mutListener.listen(4693)) {
                            trashMenuItem.setTitle(R.string.mnu_comment_delete_permanently);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4688)) {
                            trashMenuItem.setTitle(R.string.mnu_comment_trash);
                        }
                        if (!ListenerUtil.mutListener.listen(4691)) {
                            if (commentStatus == CommentStatus.SPAM) {
                                if (!ListenerUtil.mutListener.listen(4690)) {
                                    copyLinkAddress.setVisible(false);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(4689)) {
                                    copyLinkAddress.setVisible(true);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4686)) {
                    trashMenuItem.setVisible(false);
                }
                if (!ListenerUtil.mutListener.listen(4687)) {
                    copyLinkAddress.setVisible(false);
                }
            }
        }
        MenuItem editMenuItem = morePopupMenu.getMenu().findItem(R.id.action_edit);
        if (!ListenerUtil.mutListener.listen(4696)) {
            editMenuItem.setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(4698)) {
            if (canEdit()) {
                if (!ListenerUtil.mutListener.listen(4697)) {
                    editMenuItem.setVisible(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4699)) {
            morePopupMenu.show();
        }
    }

    private void trashComment() {
        if (!ListenerUtil.mutListener.listen(4701)) {
            if ((ListenerUtil.mutListener.listen(4700) ? (!isAdded() && mComment == null) : (!isAdded() || mComment == null))) {
                return;
            }
        }
        CommentStatus status = CommentStatus.fromString(mComment.getStatus());
        if (!ListenerUtil.mutListener.listen(4711)) {
            // If the comment status is trash or spam, next deletion is a permanent deletion.
            if ((ListenerUtil.mutListener.listen(4702) ? (status == CommentStatus.TRASH && status == CommentStatus.SPAM) : (status == CommentStatus.TRASH || status == CommentStatus.SPAM))) {
                AlertDialog.Builder dialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                if (!ListenerUtil.mutListener.listen(4705)) {
                    dialogBuilder.setTitle(getResources().getText(R.string.delete));
                }
                if (!ListenerUtil.mutListener.listen(4706)) {
                    dialogBuilder.setMessage(getResources().getText(R.string.dlg_sure_to_delete_comment));
                }
                if (!ListenerUtil.mutListener.listen(4707)) {
                    dialogBuilder.setPositiveButton(getResources().getText(R.string.yes), (dialog, whichButton) -> {
                        moderateComment(CommentStatus.DELETED);
                        announceCommentStatusChangeForAccessibility(CommentStatus.DELETED);
                    });
                }
                if (!ListenerUtil.mutListener.listen(4708)) {
                    dialogBuilder.setNegativeButton(getResources().getText(R.string.no), null);
                }
                if (!ListenerUtil.mutListener.listen(4709)) {
                    dialogBuilder.setCancelable(true);
                }
                if (!ListenerUtil.mutListener.listen(4710)) {
                    dialogBuilder.create().show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4703)) {
                    moderateComment(CommentStatus.TRASH);
                }
                if (!ListenerUtil.mutListener.listen(4704)) {
                    announceCommentStatusChangeForAccessibility(CommentStatus.TRASH);
                }
            }
        }
    }

    private void copyCommentLinkAddress() {
        try {
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            if (!ListenerUtil.mutListener.listen(4714)) {
                clipboard.setPrimaryClip(ClipData.newPlainText("CommentLinkAddress", mComment.getUrl()));
            }
            if (!ListenerUtil.mutListener.listen(4715)) {
                showSnackBar(getString(R.string.comment_q_action_copied_url));
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(4712)) {
                AppLog.e(T.UTILS, e);
            }
            if (!ListenerUtil.mutListener.listen(4713)) {
                showSnackBar(getString(R.string.error_copy_to_clipboard));
            }
        }
    }

    private void showSnackBar(String message) {
        View view = getView();
        if (!ListenerUtil.mutListener.listen(4717)) {
            if (view != null) {
                Snackbar snackBar = WPSnackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(getString(R.string.share_action), v -> {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, mComment.getUrl());
                        startActivity(Intent.createChooser(intent, getString(R.string.comment_share_link_via)));
                    } catch (ActivityNotFoundException exception) {
                        ToastUtils.showToast(view.getContext(), R.string.comment_toast_err_share_intent);
                    }
                }).setAnchorView(mSnackbarAnchor);
                if (!ListenerUtil.mutListener.listen(4716)) {
                    snackBar.show();
                }
            }
        }
    }

    @Override
    @Nullable
    public View getScrollableViewForUniqueIdProvision() {
        return mNestedScrollView;
    }
}

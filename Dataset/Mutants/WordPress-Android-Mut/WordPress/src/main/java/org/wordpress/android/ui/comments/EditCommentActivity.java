package org.wordpress.android.ui.comments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.fluxc.action.CommentAction;
import org.wordpress.android.fluxc.generated.CommentActionBuilder;
import org.wordpress.android.fluxc.model.CommentModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.CommentStore.OnCommentChanged;
import org.wordpress.android.fluxc.store.CommentStore.RemoteCommentPayload;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.Note;
import org.wordpress.android.ui.ActivityId;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.comments.unified.CommentsStoreAdapter;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.EditTextUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @deprecated
 * Comments are being refactored as part of Comments Unification project. If you are adding any
 * features or modifying this class, please ping develric or klymyam
 */
@Deprecated
public class EditCommentActivity extends LocaleAwareActivity {

    static final String KEY_COMMENT = "KEY_COMMENT";

    static final String KEY_NOTE_ID = "KEY_NOTE_ID";

    private static final int ID_DIALOG_SAVING = 0;

    private static final String ARG_CANCEL_EDITING_COMMENT_DIALOG_VISIBLE = "cancel_editing_comment_dialog_visible";

    private SiteModel mSite;

    private CommentModel mComment;

    private Note mNote;

    private boolean mFetchingComment;

    private AlertDialog mCancelEditCommentDialog;

    @Inject
    CommentsStoreAdapter mCommentsStoreAdapter;

    @Inject
    SiteStore mSiteStore;

    @Override
    public void onCreate(Bundle icicle) {
        if (!ListenerUtil.mutListener.listen(4909)) {
            super.onCreate(icicle);
        }
        if (!ListenerUtil.mutListener.listen(4910)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(4911)) {
            setContentView(R.layout.comment_edit_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(4912)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(4915)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(4913)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(4914)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4916)) {
            loadComment(getIntent());
        }
        if (!ListenerUtil.mutListener.listen(4919)) {
            if (icicle != null) {
                if (!ListenerUtil.mutListener.listen(4918)) {
                    if (icicle.getBoolean(ARG_CANCEL_EDITING_COMMENT_DIALOG_VISIBLE, false)) {
                        if (!ListenerUtil.mutListener.listen(4917)) {
                            cancelEditCommentConfirmation();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4920)) {
            ActivityId.trackLastActivity(ActivityId.COMMENT_EDITOR);
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(4921)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(4922)) {
            mCommentsStoreAdapter.register(this);
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(4923)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(4925)) {
            if (mCancelEditCommentDialog != null) {
                if (!ListenerUtil.mutListener.listen(4924)) {
                    outState.putBoolean(ARG_CANCEL_EDITING_COMMENT_DIALOG_VISIBLE, mCancelEditCommentDialog.isShowing());
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(4926)) {
            mCommentsStoreAdapter.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(4927)) {
            super.onStop();
        }
    }

    private void loadComment(Intent intent) {
        if (!ListenerUtil.mutListener.listen(4929)) {
            if (intent == null) {
                if (!ListenerUtil.mutListener.listen(4928)) {
                    showErrorAndFinish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4930)) {
            mSite = (SiteModel) intent.getSerializableExtra(WordPress.SITE);
        }
        if (!ListenerUtil.mutListener.listen(4931)) {
            mComment = (CommentModel) intent.getSerializableExtra(KEY_COMMENT);
        }
        final String noteId = intent.getStringExtra(KEY_NOTE_ID);
        if (!ListenerUtil.mutListener.listen(4933)) {
            // If the noteId is passed, load the comment from the note
            if (noteId != null) {
                if (!ListenerUtil.mutListener.listen(4932)) {
                    loadCommentFromNote(noteId);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4935)) {
            // Else make sure the comment has been passed
            if (mComment == null) {
                if (!ListenerUtil.mutListener.listen(4934)) {
                    showErrorAndFinish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4936)) {
            configureViews();
        }
    }

    private void loadCommentFromNote(String noteId) {
        if (!ListenerUtil.mutListener.listen(4937)) {
            mNote = NotificationsTable.getNoteById(noteId);
        }
        if (!ListenerUtil.mutListener.listen(4943)) {
            if (mNote != null) {
                if (!ListenerUtil.mutListener.listen(4939)) {
                    setFetchProgressVisible(true);
                }
                if (!ListenerUtil.mutListener.listen(4940)) {
                    mSite = mSiteStore.getSiteBySiteId(mNote.getSiteId());
                }
                RemoteCommentPayload payload = new RemoteCommentPayload(mSite, mNote.getCommentId());
                if (!ListenerUtil.mutListener.listen(4941)) {
                    mFetchingComment = true;
                }
                if (!ListenerUtil.mutListener.listen(4942)) {
                    mCommentsStoreAdapter.dispatch(CommentActionBuilder.newFetchCommentAction(payload));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4938)) {
                    showErrorAndFinish();
                }
            }
        }
    }

    private void showErrorAndFinish() {
        if (!ListenerUtil.mutListener.listen(4944)) {
            ToastUtils.showToast(this, R.string.error_load_comment);
        }
        if (!ListenerUtil.mutListener.listen(4945)) {
            finish();
        }
    }

    private void configureViews() {
        final EditText editContent = this.findViewById(R.id.edit_comment_content);
        if (!ListenerUtil.mutListener.listen(4946)) {
            editContent.setText(mComment.getContent());
        }
        if (!ListenerUtil.mutListener.listen(4958)) {
            // show error when comment content is empty
            editContent.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    boolean hasError = (editContent.getError() != null);
                    boolean hasText = ((ListenerUtil.mutListener.listen(4952) ? (s != null || (ListenerUtil.mutListener.listen(4951) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(4950) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(4949) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(4948) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(4947) ? (s.length() == 0) : (s.length() > 0))))))) : (s != null && (ListenerUtil.mutListener.listen(4951) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(4950) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(4949) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(4948) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(4947) ? (s.length() == 0) : (s.length() > 0)))))))));
                    if (!ListenerUtil.mutListener.listen(4957)) {
                        if ((ListenerUtil.mutListener.listen(4953) ? (!hasText || !hasError) : (!hasText && !hasError))) {
                            if (!ListenerUtil.mutListener.listen(4956)) {
                                editContent.setError(getString(R.string.content_required));
                            }
                        } else if ((ListenerUtil.mutListener.listen(4954) ? (hasText || hasError) : (hasText && hasError))) {
                            if (!ListenerUtil.mutListener.listen(4955)) {
                                editContent.setError(null);
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4959)) {
            super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(4960)) {
            inflater.inflate(R.menu.edit_comment, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            if (!ListenerUtil.mutListener.listen(4962)) {
                onBackPressed();
            }
            return true;
        } else if (i == R.id.menu_save_comment) {
            if (!ListenerUtil.mutListener.listen(4961)) {
                saveComment();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private String getEditTextStr(int resId) {
        final EditText edit = findViewById(resId);
        return EditTextUtils.getText(edit);
    }

    private void saveComment() {
        // make sure comment content was entered
        final EditText editContent = findViewById(R.id.edit_comment_content);
        if (!ListenerUtil.mutListener.listen(4964)) {
            if (EditTextUtils.isEmpty(editContent)) {
                if (!ListenerUtil.mutListener.listen(4963)) {
                    editContent.setError(getString(R.string.content_required));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4966)) {
            // return immediately if comment hasn't changed
            if (!isCommentEdited()) {
                if (!ListenerUtil.mutListener.listen(4965)) {
                    ToastUtils.showToast(this, R.string.toast_comment_unedited);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4967)) {
            // make sure we have an active connection
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4968)) {
            showSaveDialog();
        }
        if (!ListenerUtil.mutListener.listen(4969)) {
            mComment.setContent(getEditTextStr(R.id.edit_comment_content));
        }
        if (!ListenerUtil.mutListener.listen(4970)) {
            mCommentsStoreAdapter.dispatch(CommentActionBuilder.newPushCommentAction(new RemoteCommentPayload(mSite, mComment)));
        }
    }

    /*
     * returns true if user made any changes to the comment
     */
    private boolean isCommentEdited() {
        if (!ListenerUtil.mutListener.listen(4971)) {
            if (mComment == null) {
                return false;
            }
        }
        final String content = getEditTextStr(R.id.edit_comment_content);
        return !content.equals(mComment.getContent());
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (!ListenerUtil.mutListener.listen(4980)) {
            if ((ListenerUtil.mutListener.listen(4976) ? (id >= ID_DIALOG_SAVING) : (ListenerUtil.mutListener.listen(4975) ? (id <= ID_DIALOG_SAVING) : (ListenerUtil.mutListener.listen(4974) ? (id > ID_DIALOG_SAVING) : (ListenerUtil.mutListener.listen(4973) ? (id < ID_DIALOG_SAVING) : (ListenerUtil.mutListener.listen(4972) ? (id != ID_DIALOG_SAVING) : (id == ID_DIALOG_SAVING))))))) {
                ProgressDialog savingDialog = new ProgressDialog(this);
                if (!ListenerUtil.mutListener.listen(4977)) {
                    savingDialog.setMessage(getResources().getText(R.string.saving_changes));
                }
                if (!ListenerUtil.mutListener.listen(4978)) {
                    savingDialog.setIndeterminate(true);
                }
                if (!ListenerUtil.mutListener.listen(4979)) {
                    savingDialog.setCancelable(true);
                }
                return savingDialog;
            }
        }
        return super.onCreateDialog(id);
    }

    private void showSaveDialog() {
        if (!ListenerUtil.mutListener.listen(4981)) {
            showDialog(ID_DIALOG_SAVING);
        }
    }

    private void dismissSaveDialog() {
        try {
            if (!ListenerUtil.mutListener.listen(4982)) {
                dismissDialog(ID_DIALOG_SAVING);
            }
        } catch (IllegalArgumentException e) {
        }
    }

    private void showEditErrorAlert() {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(4983)) {
            dialogBuilder.setTitle(getResources().getText(R.string.error));
        }
        if (!ListenerUtil.mutListener.listen(4984)) {
            dialogBuilder.setMessage(R.string.error_edit_comment);
        }
        if (!ListenerUtil.mutListener.listen(4985)) {
            dialogBuilder.setPositiveButton(android.R.string.ok, (dialog1, whichButton) -> {
            });
        }
        if (!ListenerUtil.mutListener.listen(4986)) {
            dialogBuilder.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(4987)) {
            dialogBuilder.create().show();
        }
    }

    private void setFetchProgressVisible(boolean progressVisible) {
        final ProgressBar progress = findViewById(R.id.edit_comment_progress);
        final View editContainer = findViewById(R.id.edit_comment_container);
        if (!ListenerUtil.mutListener.listen(4989)) {
            if ((ListenerUtil.mutListener.listen(4988) ? (progress == null && editContainer == null) : (progress == null || editContainer == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4990)) {
            progress.setVisibility(progressVisible ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4991)) {
            editContainer.setVisibility(progressVisible ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(4994)) {
            if (isCommentEdited()) {
                if (!ListenerUtil.mutListener.listen(4993)) {
                    cancelEditCommentConfirmation();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4992)) {
                    super.onBackPressed();
                }
            }
        }
    }

    private void cancelEditCommentConfirmation() {
        if (!ListenerUtil.mutListener.listen(4996)) {
            if (mCancelEditCommentDialog != null) {
                if (!ListenerUtil.mutListener.listen(4995)) {
                    mCancelEditCommentDialog.show();
                }
                return;
            }
        }
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(4997)) {
            dialogBuilder.setTitle(getResources().getText(R.string.cancel_edit));
        }
        if (!ListenerUtil.mutListener.listen(4998)) {
            dialogBuilder.setMessage(getResources().getText(R.string.sure_to_cancel_edit_comment));
        }
        if (!ListenerUtil.mutListener.listen(4999)) {
            dialogBuilder.setPositiveButton(getResources().getText(R.string.yes), (dialog, whichButton) -> finish());
        }
        if (!ListenerUtil.mutListener.listen(5000)) {
            dialogBuilder.setNegativeButton(getResources().getText(R.string.no), (dialog, whichButton) -> {
            });
        }
        if (!ListenerUtil.mutListener.listen(5001)) {
            dialogBuilder.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(5002)) {
            mCancelEditCommentDialog = dialogBuilder.create();
        }
        if (!ListenerUtil.mutListener.listen(5003)) {
            mCancelEditCommentDialog.show();
        }
    }

    private void onCommentPushed(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(5004)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5005)) {
            dismissSaveDialog();
        }
        if (!ListenerUtil.mutListener.listen(5008)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(5006)) {
                    AppLog.i(T.TESTS, "event error type: " + event.error.type + " - message: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(5007)) {
                    showEditErrorAlert();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5009)) {
            setResult(RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(5010)) {
            finish();
        }
    }

    private void onCommentFetched(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(5012)) {
            if ((ListenerUtil.mutListener.listen(5011) ? (isFinishing() && !mFetchingComment) : (isFinishing() || !mFetchingComment))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5013)) {
            mFetchingComment = false;
        }
        if (!ListenerUtil.mutListener.listen(5014)) {
            setFetchProgressVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(5017)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(5015)) {
                    AppLog.i(T.TESTS, "event error type: " + event.error.type + " - message: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(5016)) {
                    showErrorAndFinish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5020)) {
            if (mNote != null) {
                if (!ListenerUtil.mutListener.listen(5019)) {
                    mComment = mCommentsStoreAdapter.getCommentBySiteAndRemoteId(mSite, mNote.getCommentId());
                }
            } else if (mComment != null) {
                if (!ListenerUtil.mutListener.listen(5018)) {
                    // Reload the comment
                    mComment = mCommentsStoreAdapter.getCommentByLocalId(mComment.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5021)) {
            configureViews();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCommentChanged(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(5023)) {
            if (event.causeOfChange == CommentAction.FETCH_COMMENT) {
                if (!ListenerUtil.mutListener.listen(5022)) {
                    onCommentFetched(event);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5025)) {
            if (event.causeOfChange == CommentAction.PUSH_COMMENT) {
                if (!ListenerUtil.mutListener.listen(5024)) {
                    onCommentPushed(event);
                }
            }
        }
    }
}

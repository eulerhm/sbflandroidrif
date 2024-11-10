package com.github.pockethub.android.ui.issue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.pockethub.android.R;
import com.github.pockethub.android.accounts.AccountUtils;
import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.core.issue.IssueUtils;
import com.github.pockethub.android.core.issue.RefreshIssueTaskFactory;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.rx.RxProgress;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.ConfirmDialogFragment;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.comment.DeleteCommentListener;
import com.github.pockethub.android.ui.comment.EditCommentListener;
import com.github.pockethub.android.ui.commit.CommitCompareViewActivity;
import com.github.pockethub.android.ui.item.GitHubCommentItem;
import com.github.pockethub.android.ui.item.LoadingItem;
import com.github.pockethub.android.ui.item.issue.IssueEventItem;
import com.github.pockethub.android.ui.item.issue.IssueHeaderItem;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.HttpImageGetter;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ShareUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.GitHubComment;
import com.meisolsson.githubsdk.model.GitHubEvent;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.IssueEvent;
import com.meisolsson.githubsdk.model.IssueState;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.meisolsson.githubsdk.service.issues.IssueCommentService;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.Section;
import com.xwray.groupie.ViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.fragment_comment_list.*;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Fragment to display an issue
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00da\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 z2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001zB\u0005\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010M\u001a\b\u0012\u0004\u0012\u00020\'0NH\u0002J\u0010\u0010O\u001a\u00020)2\u0006\u0010P\u001a\u00020QH\u0002J\u0012\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\"\u0010V\u001a\u00020S2\u0006\u0010W\u001a\u00020)2\u0006\u0010X\u001a\u00020)2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0016J\b\u0010[\u001a\u00020SH\u0016J\b\u0010\\\u001a\u00020SH\u0016J\u0012\u0010]\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\u0018\u0010^\u001a\u00020S2\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020bH\u0016J&\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010a\u001a\u00020e2\b\u0010f\u001a\u0004\u0018\u00010g2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J \u0010h\u001a\u00020S2\u0006\u0010W\u001a\u00020)2\u0006\u0010X\u001a\u00020)2\u0006\u0010i\u001a\u00020UH\u0016J\b\u0010j\u001a\u00020SH\u0016J\b\u0010k\u001a\u00020SH\u0016J\u0010\u0010l\u001a\u00020\u001e2\u0006\u0010m\u001a\u00020?H\u0016J\u0010\u0010n\u001a\u00020S2\u0006\u0010_\u001a\u00020`H\u0016J\b\u0010o\u001a\u00020SH\u0016J\u001a\u0010p\u001a\u00020S2\u0006\u0010q\u001a\u00020d2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\b\u0010r\u001a\u00020SH\u0002J\b\u0010s\u001a\u00020SH\u0002J\u0018\u0010t\u001a\u00020\u001e2\u0006\u0010u\u001a\u00020)2\u0006\u0010P\u001a\u00020QH\u0002J\b\u0010v\u001a\u00020SH\u0002J\u0012\u0010w\u001a\u00020S2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0002J \u0010x\u001a\u00020S2\b\u0010&\u001a\u0004\u0018\u00010\'2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002J\u0012\u0010y\u001a\u00020S2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001a\"\u0004\b!\u0010\u001cR\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010/\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b0\u0010\r\"\u0004\b1\u0010\u000fR\u000e\u00102\u001a\u000203X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u00106\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b7\u0010\r\"\u0004\b8\u0010\u000fR \u00109\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b:\u0010\r\"\u0004\b;\u0010\u000fR\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010B\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\bC\u0010\r\"\u0004\bD\u0010\u000fR\u001e\u0010E\u001a\u00020F8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u0010\u0010K\u001a\u0004\u0018\u00010LX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006{"}, d2 = {"Lcom/github/pockethub/android/ui/issue/IssueFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Lcom/github/pockethub/android/ui/item/issue/IssueHeaderItem$OnIssueHeaderActionListener;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "()V", "adapter", "Lcom/xwray/groupie/GroupAdapter;", "Lcom/xwray/groupie/ViewHolder;", "assigneeTask", "Lcom/github/pockethub/android/ui/issue/EditAssigneeTask;", "assigneeTaskFactory", "error/NonExistentClass", "getAssigneeTaskFactory", "()Lerror/NonExistentClass;", "setAssigneeTaskFactory", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "bodyImageGetter", "Lcom/github/pockethub/android/util/HttpImageGetter;", "getBodyImageGetter", "()Lcom/github/pockethub/android/util/HttpImageGetter;", "setBodyImageGetter", "(Lcom/github/pockethub/android/util/HttpImageGetter;)V", "canWrite", "", "commentImageGetter", "getCommentImageGetter", "setCommentImageGetter", "deleteCommentListener", "Lcom/github/pockethub/android/ui/comment/DeleteCommentListener;", "editCommentListener", "Lcom/github/pockethub/android/ui/comment/EditCommentListener;", "issue", "Lcom/meisolsson/githubsdk/model/Issue;", "issueNumber", "", "items", "", "", "labelsTask", "Lcom/github/pockethub/android/ui/issue/EditLabelsTask;", "labelsTaskFactory", "getLabelsTaskFactory", "setLabelsTaskFactory", "mainSection", "Lcom/xwray/groupie/Section;", "milestoneTask", "Lcom/github/pockethub/android/ui/issue/EditMilestoneTask;", "milestoneTaskFactory", "getMilestoneTaskFactory", "setMilestoneTaskFactory", "refreshIssueTaskFactory", "getRefreshIssueTaskFactory", "setRefreshIssueTaskFactory", "repositoryId", "Lcom/meisolsson/githubsdk/model/Repository;", "stateItem", "Landroid/view/MenuItem;", "stateTask", "Lcom/github/pockethub/android/ui/issue/EditStateTask;", "stateTaskFactory", "getStateTaskFactory", "setStateTaskFactory", "store", "Lcom/github/pockethub/android/core/issue/IssueStore;", "getStore", "()Lcom/github/pockethub/android/core/issue/IssueStore;", "setStore", "(Lcom/github/pockethub/android/core/issue/IssueStore;)V", "user", "Lcom/meisolsson/githubsdk/model/User;", "createObserver", "Lio/reactivex/functions/Consumer;", "findCommentPositionInItems", "comment", "Lcom/meisolsson/githubsdk/model/GitHubComment;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAssigneesClicked", "onCommitsClicked", "onCreate", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDialogResult", "arguments", "onLabelsClicked", "onMilestonesClicked", "onOptionsItemSelected", "item", "onPrepareOptionsMenu", "onStateClicked", "onViewCreated", "view", "openPullRequestCommits", "refreshIssue", "replaceCommentInItems", "commentPosition", "shareIssue", "updateHeader", "updateList", "updateStateItem", "Companion", "app_debug"})
public final class IssueFragment extends com.github.pockethub.android.ui.base.BaseFragment implements com.github.pockethub.android.ui.item.issue.IssueHeaderItem.OnIssueHeaderActionListener, com.github.pockethub.android.ui.DialogResultListener {
    private int issueNumber;
    private final java.util.List<java.lang.Object> items = null;
    private com.meisolsson.githubsdk.model.Repository repositoryId;
    private com.meisolsson.githubsdk.model.Issue issue;
    private com.meisolsson.githubsdk.model.User user;
    private boolean canWrite;
    private final com.xwray.groupie.GroupAdapter<com.xwray.groupie.ViewHolder> adapter = null;
    private final com.xwray.groupie.Section mainSection = null;
    private com.github.pockethub.android.ui.issue.EditMilestoneTask milestoneTask;
    private com.github.pockethub.android.ui.issue.EditAssigneeTask assigneeTask;
    private com.github.pockethub.android.ui.issue.EditLabelsTask labelsTask;
    private com.github.pockethub.android.ui.issue.EditStateTask stateTask;
    private android.view.MenuItem stateItem;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.AvatarLoader avatars;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.core.issue.IssueStore store;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public RefreshIssueTaskFactory refreshIssueTaskFactory;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public EditLabelsTaskFactory labelsTaskFactory;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public EditMilestoneTaskFactory milestoneTaskFactory;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public EditAssigneeTaskFactory assigneeTaskFactory;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public EditStateTaskFactory stateTaskFactory;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.HttpImageGetter bodyImageGetter;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.HttpImageGetter commentImageGetter;
    
    /**
     * Edit existing comment
     */
    private final com.github.pockethub.android.ui.comment.EditCommentListener editCommentListener = null;
    
    /**
     * Delete existing comment
     */
    private final com.github.pockethub.android.ui.comment.DeleteCommentListener deleteCommentListener = null;
    private static final java.lang.String TAG = "IssueFragment";
    public static final com.github.pockethub.android.ui.issue.IssueFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    public final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.issue.IssueStore getStore() {
        return null;
    }
    
    public final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.issue.IssueStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final RefreshIssueTaskFactory getRefreshIssueTaskFactory() {
        return null;
    }
    
    public final void setRefreshIssueTaskFactory(@org.jetbrains.annotations.NotNull()
    RefreshIssueTaskFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final EditLabelsTaskFactory getLabelsTaskFactory() {
        return null;
    }
    
    public final void setLabelsTaskFactory(@org.jetbrains.annotations.NotNull()
    EditLabelsTaskFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final EditMilestoneTaskFactory getMilestoneTaskFactory() {
        return null;
    }
    
    public final void setMilestoneTaskFactory(@org.jetbrains.annotations.NotNull()
    EditMilestoneTaskFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final EditAssigneeTaskFactory getAssigneeTaskFactory() {
        return null;
    }
    
    public final void setAssigneeTaskFactory(@org.jetbrains.annotations.NotNull()
    EditAssigneeTaskFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final EditStateTaskFactory getStateTaskFactory() {
        return null;
    }
    
    public final void setStateTaskFactory(@org.jetbrains.annotations.NotNull()
    EditStateTaskFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.HttpImageGetter getBodyImageGetter() {
        return null;
    }
    
    public final void setBodyImageGetter(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.HttpImageGetter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.HttpImageGetter getCommentImageGetter() {
        return null;
    }
    
    public final void setCommentImageGetter(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.HttpImageGetter p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final io.reactivex.functions.Consumer<com.meisolsson.githubsdk.model.Issue> createObserver() {
        return null;
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateHeader(com.meisolsson.githubsdk.model.Issue issue) {
    }
    
    private final void refreshIssue() {
    }
    
    private final void updateList(com.meisolsson.githubsdk.model.Issue issue, java.util.List<java.lang.Object> items) {
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
    }
    
    private final void updateStateItem(com.meisolsson.githubsdk.model.Issue issue) {
    }
    
    @java.lang.Override()
    public void onPrepareOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void shareIssue() {
    }
    
    private final void openPullRequestCommits() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    /**
     * Finds the position of the given comment in the list of this issue's items.
     *
     * @param comment The comment to look for.
     * @return The position of the comment in the list, or -1 if not found.
     */
    private final int findCommentPositionInItems(com.meisolsson.githubsdk.model.GitHubComment comment) {
        return 0;
    }
    
    /**
     * Replaces a comment in the list by another
     *
     * @param commentPosition The position of the comment in the list
     * @param comment         The comment to replace
     * @return True if successfully removed, false otherwise.
     */
    private final boolean replaceCommentInItems(int commentPosition, com.meisolsson.githubsdk.model.GitHubComment comment) {
        return false;
    }
    
    @java.lang.Override()
    public void onCommitsClicked() {
    }
    
    @java.lang.Override()
    public void onStateClicked() {
    }
    
    @java.lang.Override()
    public void onMilestonesClicked() {
    }
    
    @java.lang.Override()
    public void onAssigneesClicked() {
    }
    
    @java.lang.Override()
    public void onLabelsClicked() {
    }
    
    public IssueFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/github/pockethub/android/ui/issue/IssueFragment$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
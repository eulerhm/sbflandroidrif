package com.github.pockethub.android.ui.commit;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.commit.CommitStore;
import com.github.pockethub.android.core.commit.CommitUtils;
import com.github.pockethub.android.core.commit.FullCommitFile;
import com.github.pockethub.android.core.commit.RefreshCommitTaskFactory;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.LoadingItem;
import com.github.pockethub.android.ui.item.TextItem;
import com.github.pockethub.android.ui.item.commit.CommitCommentItem;
import com.github.pockethub.android.ui.item.commit.CommitFileHeaderItem;
import com.github.pockethub.android.ui.item.commit.CommitFileLineItem;
import com.github.pockethub.android.ui.item.commit.CommitHeaderItem;
import com.github.pockethub.android.ui.item.commit.CommitParentItem;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.HttpImageGetter;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ShareUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Commit;
import com.meisolsson.githubsdk.model.GitHubFile;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.git.GitComment;
import com.meisolsson.githubsdk.model.git.GitCommit;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.Section;
import com.xwray.groupie.ViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.diff_line_dialog.view.*;
import kotlinx.android.synthetic.main.fragment_commit_diff_list.*;
import java.util.Collections;
import javax.inject.Inject;

/**
 * Fragment to display commit details with diff output
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00d2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u0019H\u0002J\u0010\u00106\u001a\u0002042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u00107\u001a\u000204H\u0002J\u001c\u00108\u001a\b\u0012\u0004\u0012\u00020\u00160 2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\u0012\u00109\u001a\u0002042\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\"\u0010<\u001a\u0002042\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u0012\u0010B\u001a\u0002042\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u0018\u0010C\u001a\u0002042\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0016J$\u0010H\u001a\u00020I2\u0006\u0010F\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010L2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J \u0010M\u001a\u0002042\f\b\u0001\u0010N\u001a\u0006\u0012\u0002\b\u00030O2\b\b\u0001\u0010P\u001a\u00020IH\u0016J\u0010\u0010Q\u001a\u00020R2\u0006\u0010N\u001a\u00020SH\u0016J\u001a\u0010T\u001a\u0002042\u0006\u0010P\u001a\u00020I2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u0010\u0010U\u001a\u0002042\u0006\u0010V\u001a\u00020WH\u0002J\b\u0010X\u001a\u000204H\u0002J$\u0010Y\u001a\u0002042\u0006\u0010Z\u001a\u00020>2\u0006\u0010N\u001a\u00020[2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\b\u0010\\\u001a\u000204H\u0002J \u0010]\u001a\u0002042\u0006\u0010^\u001a\u00020_2\u0006\u0010Z\u001a\u00020>2\u0006\u0010V\u001a\u00020WH\u0002J\u0010\u0010`\u001a\u0002042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J$\u0010a\u001a\u0002042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190 2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J,\u0010b\u001a\u0002042\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010$\u001a\u00020%8\u0006@\u0006X\u0087.\u00a2\u0006\u0010\n\u0002\u0010*\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010-\u001a\u00020.8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102\u00a8\u0006c"}, d2 = {"Lcom/github/pockethub/android/ui/commit/CommitDiffListFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Lcom/xwray/groupie/OnItemClickListener;", "()V", "adapter", "Lcom/xwray/groupie/GroupAdapter;", "Lcom/xwray/groupie/ViewHolder;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "base", "", "commentImageGetter", "Lcom/github/pockethub/android/util/HttpImageGetter;", "getCommentImageGetter", "()Lcom/github/pockethub/android/util/HttpImageGetter;", "setCommentImageGetter", "(Lcom/github/pockethub/android/util/HttpImageGetter;)V", "commentSection", "Lcom/xwray/groupie/Section;", "comments", "", "Lcom/meisolsson/githubsdk/model/git/GitComment;", "commit", "Lcom/meisolsson/githubsdk/model/Commit;", "commitSection", "diffStyler", "Lcom/github/pockethub/android/ui/commit/DiffStyler;", "files", "", "Lcom/github/pockethub/android/core/commit/FullCommitFile;", "filesSection", "mainSection", "refreshCommitTaskFactory", "error/NonExistentClass", "getRefreshCommitTaskFactory", "()Lerror/NonExistentClass;", "setRefreshCommitTaskFactory", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "store", "Lcom/github/pockethub/android/core/commit/CommitStore;", "getStore", "()Lcom/github/pockethub/android/core/commit/CommitStore;", "setStore", "(Lcom/github/pockethub/android/core/commit/CommitStore;)V", "addComment", "", "comment", "addCommitParents", "copyHashToClipboard", "createFileSections", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onItemClick", "item", "Lcom/xwray/groupie/Item;", "view", "onOptionsItemSelected", "", "Landroid/view/MenuItem;", "onViewCreated", "openFile", "file", "Lcom/meisolsson/githubsdk/model/GitHubFile;", "refreshCommit", "selectPreviousFile", "position", "", "shareCommit", "showFileOptions", "line", "", "updateHeader", "updateItems", "updateList", "app_debug"})
public final class CommitDiffListFragment extends com.github.pockethub.android.ui.base.BaseFragment implements com.xwray.groupie.OnItemClickListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public RefreshCommitTaskFactory refreshCommitTaskFactory;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.HttpImageGetter commentImageGetter;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.AvatarLoader avatars;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.core.commit.CommitStore store;
    private com.github.pockethub.android.ui.commit.DiffStyler diffStyler;
    private com.meisolsson.githubsdk.model.Repository repository;
    private java.lang.String base;
    private com.meisolsson.githubsdk.model.Commit commit;
    private java.util.List<com.meisolsson.githubsdk.model.git.GitComment> comments;
    private java.util.List<? extends com.github.pockethub.android.core.commit.FullCommitFile> files;
    private final com.xwray.groupie.GroupAdapter<com.xwray.groupie.ViewHolder> adapter = null;
    private final com.xwray.groupie.Section mainSection = null;
    private final com.xwray.groupie.Section commitSection = null;
    private final com.xwray.groupie.Section filesSection = null;
    private final com.xwray.groupie.Section commentSection = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final RefreshCommitTaskFactory getRefreshCommitTaskFactory() {
        return null;
    }
    
    public final void setRefreshCommitTaskFactory(@org.jetbrains.annotations.NotNull()
    RefreshCommitTaskFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.HttpImageGetter getCommentImageGetter() {
        return null;
    }
    
    public final void setCommentImageGetter(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.HttpImageGetter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    public final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.commit.CommitStore getStore() {
        return null;
    }
    
    public final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.commit.CommitStore p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void addComment(com.meisolsson.githubsdk.model.git.GitComment comment) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void copyHashToClipboard() {
    }
    
    private final void shareCommit() {
    }
    
    private final void refreshCommit() {
    }
    
    private final void addCommitParents(com.meisolsson.githubsdk.model.Commit commit) {
    }
    
    private final void updateHeader(com.meisolsson.githubsdk.model.Commit commit) {
    }
    
    private final void updateList(com.meisolsson.githubsdk.model.Commit commit, java.util.List<com.meisolsson.githubsdk.model.git.GitComment> comments, java.util.List<? extends com.github.pockethub.android.core.commit.FullCommitFile> files) {
    }
    
    private final void updateItems(java.util.List<? extends com.meisolsson.githubsdk.model.git.GitComment> comments, java.util.List<? extends com.github.pockethub.android.core.commit.FullCommitFile> files) {
    }
    
    private final java.util.List<com.xwray.groupie.Section> createFileSections(java.util.List<? extends com.github.pockethub.android.core.commit.FullCommitFile> files) {
        return null;
    }
    
    private final void showFileOptions(java.lang.CharSequence line, int position, com.meisolsson.githubsdk.model.GitHubFile file) {
    }
    
    private final void openFile(com.meisolsson.githubsdk.model.GitHubFile file) {
    }
    
    /**
     * Select previous file by scanning backwards from the current position
     *
     * @param position
     * @param item
     * @param adapter
     */
    private final void selectPreviousFile(int position, java.lang.Object item, com.xwray.groupie.GroupAdapter<?> adapter) {
    }
    
    @java.lang.Override()
    public void onItemClick(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    android.view.View view) {
    }
    
    public CommitDiffListFragment() {
        super();
    }
}
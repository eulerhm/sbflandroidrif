package com.github.pockethub.android.ui.gist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.pockethub.android.R;
import com.github.pockethub.android.accounts.AccountUtils;
import com.github.pockethub.android.core.OnLoadListener;
import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.core.gist.RefreshGistTaskFactory;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.rx.RxProgress;
import com.github.pockethub.android.ui.ConfirmDialogFragment;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.comment.DeleteCommentListener;
import com.github.pockethub.android.ui.comment.EditCommentListener;
import com.github.pockethub.android.ui.item.GitHubCommentItem;
import com.github.pockethub.android.ui.item.LoadingItem;
import com.github.pockethub.android.ui.item.gist.GistFileItem;
import com.github.pockethub.android.ui.item.gist.GistHeaderItem;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.HttpImageGetter;
import com.github.pockethub.android.util.ShareUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Gist;
import com.meisolsson.githubsdk.model.GitHubComment;
import com.meisolsson.githubsdk.service.gists.GistCommentService;
import com.meisolsson.githubsdk.service.gists.GistService;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.Section;
import com.xwray.groupie.ViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.fragment_comment_list.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.inject.Inject;

/**
 * Activity to display an existing Gist
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00bc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \\2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\\B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0012\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108H\u0016J\"\u00109\u001a\u0002062\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0012\u0010?\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u0018\u0010@\u001a\u0002062\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0016J$\u0010E\u001a\u00020F2\u0006\u0010C\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010I2\b\u00107\u001a\u0004\u0018\u000108H\u0016J \u0010J\u001a\u0002062\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010K\u001a\u000208H\u0016J \u0010L\u001a\u0002062\f\b\u0001\u0010M\u001a\u0006\u0012\u0002\b\u00030N2\b\b\u0001\u0010O\u001a\u00020FH\u0016J\u0010\u0010P\u001a\u00020#2\u0006\u0010M\u001a\u00020QH\u0016J\u0010\u0010R\u001a\u0002062\u0006\u0010A\u001a\u00020BH\u0016J\u001a\u0010S\u001a\u0002062\u0006\u0010O\u001a\u00020F2\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u0010T\u001a\u000206H\u0002J\b\u0010U\u001a\u000206H\u0002J\b\u0010V\u001a\u000206H\u0002J\b\u0010W\u001a\u000206H\u0002J\u0012\u0010X\u001a\u0002062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010Y\u001a\u0002062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J \u0010Z\u001a\u0002062\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100[H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010$R\u000e\u0010%\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\'\u001a\u00020(8\u0006@\u0006X\u0087.\u00a2\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010.\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010/\u001a\u0002008\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104\u00a8\u0006]"}, d2 = {"Lcom/github/pockethub/android/ui/gist/GistFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Lcom/xwray/groupie/OnItemClickListener;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "()V", "adapter", "Lcom/xwray/groupie/GroupAdapter;", "Lcom/xwray/groupie/ViewHolder;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "comments", "", "Lcom/meisolsson/githubsdk/model/GitHubComment;", "commentsSection", "Lcom/xwray/groupie/Section;", "deleteCommentListener", "Lcom/github/pockethub/android/ui/comment/DeleteCommentListener;", "editCommentListener", "Lcom/github/pockethub/android/ui/comment/EditCommentListener;", "filesSection", "gist", "Lcom/meisolsson/githubsdk/model/Gist;", "gistId", "", "imageGetter", "Lcom/github/pockethub/android/util/HttpImageGetter;", "getImageGetter", "()Lcom/github/pockethub/android/util/HttpImageGetter;", "setImageGetter", "(Lcom/github/pockethub/android/util/HttpImageGetter;)V", "isOwner", "", "()Z", "loadFinished", "mainSection", "refreshGistTaskFactory", "error/NonExistentClass", "getRefreshGistTaskFactory", "()Lerror/NonExistentClass;", "setRefreshGistTaskFactory", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "starred", "store", "Lcom/github/pockethub/android/core/gist/GistStore;", "getStore", "()Lcom/github/pockethub/android/core/gist/GistStore;", "setStore", "(Lcom/github/pockethub/android/core/gist/GistStore;)V", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDialogResult", "arguments", "onItemClick", "item", "Lcom/xwray/groupie/Item;", "view", "onOptionsItemSelected", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "onViewCreated", "refreshGist", "shareGist", "starGist", "unstarGist", "updateFiles", "updateHeader", "updateList", "", "Companion", "app_debug"})
public final class GistFragment extends com.github.pockethub.android.ui.base.BaseFragment implements com.xwray.groupie.OnItemClickListener, com.github.pockethub.android.ui.DialogResultListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.core.gist.GistStore store;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public RefreshGistTaskFactory refreshGistTaskFactory;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.HttpImageGetter imageGetter;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.AvatarLoader avatars;
    private java.lang.String gistId;
    private java.util.List<com.meisolsson.githubsdk.model.GitHubComment> comments;
    private com.meisolsson.githubsdk.model.Gist gist;
    private final com.xwray.groupie.GroupAdapter<com.xwray.groupie.ViewHolder> adapter = null;
    private final com.xwray.groupie.Section mainSection = null;
    private final com.xwray.groupie.Section filesSection = null;
    private final com.xwray.groupie.Section commentsSection = null;
    private boolean starred;
    private boolean loadFinished;
    
    /**
     * Edit existing comment
     */
    private final com.github.pockethub.android.ui.comment.EditCommentListener editCommentListener = null;
    
    /**
     * Delete existing comment
     */
    private final com.github.pockethub.android.ui.comment.DeleteCommentListener deleteCommentListener = null;
    private static final java.lang.String TAG = "GistFragment";
    public static final com.github.pockethub.android.ui.gist.GistFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.gist.GistStore getStore() {
        return null;
    }
    
    public final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.gist.GistStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final RefreshGistTaskFactory getRefreshGistTaskFactory() {
        return null;
    }
    
    public final void setRefreshGistTaskFactory(@org.jetbrains.annotations.NotNull()
    RefreshGistTaskFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.HttpImageGetter getImageGetter() {
        return null;
    }
    
    public final void setImageGetter(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.HttpImageGetter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    public final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    private final boolean isOwner() {
        return false;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
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
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateHeader(com.meisolsson.githubsdk.model.Gist gist) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    @java.lang.Override()
    public void onPrepareOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void starGist() {
    }
    
    private final void shareGist() {
    }
    
    private final void unstarGist() {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void updateFiles(com.meisolsson.githubsdk.model.Gist gist) {
    }
    
    private final void updateList(com.meisolsson.githubsdk.model.Gist gist, java.util.List<? extends com.meisolsson.githubsdk.model.GitHubComment> comments) {
    }
    
    private final void refreshGist() {
    }
    
    @java.lang.Override()
    public void onItemClick(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    android.view.View view) {
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
    }
    
    public GistFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/github/pockethub/android/ui/gist/GistFragment$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
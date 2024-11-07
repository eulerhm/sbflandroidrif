package com.github.pockethub.android.ui.commit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.PagedListFetcher;
import com.github.pockethub.android.ui.helpers.PagedScrollListener;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.commit.CommitStore;
import com.github.pockethub.android.core.ref.RefUtils;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.commit.CommitItem;
import com.github.pockethub.android.ui.ref.RefDialog;
import com.github.pockethub.android.ui.ref.RefDialogFragment;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Commit;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.git.GitReference;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommitService;
import com.meisolsson.githubsdk.service.repositories.RepositoryService;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import io.reactivex.Single;
import kotlinx.android.synthetic.main.fragment_commit_list.view.*;
import kotlinx.android.synthetic.main.ref_footer.view.*;
import retrofit2.Response;
import javax.inject.Inject;

/**
 * Fragment to display a list of repo commits
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0014\u00103\u001a\u0006\u0012\u0002\b\u0003042\u0006\u00105\u001a\u00020\u0018H\u0004J\"\u00106\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180908072\u0006\u0010:\u001a\u00020\rH\u0002J\"\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\r2\u0006\u0010>\u001a\u00020\r2\b\u0010?\u001a\u0004\u0018\u00010@H\u0016J\u0010\u0010A\u001a\u00020<2\u0006\u0010B\u001a\u00020CH\u0016J&\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010I2\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J \u0010L\u001a\u00020<2\u0006\u0010=\u001a\u00020\r2\u0006\u0010>\u001a\u00020\r2\u0006\u0010M\u001a\u00020KH\u0016J\u001a\u0010N\u001a\u00020<2\n\u0010O\u001a\u0006\u0012\u0002\b\u0003042\u0006\u0010P\u001a\u00020EJ$\u0010Q\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003040R2\u0010\u0010S\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003040RH\u0004J\u001a\u0010T\u001a\u00020<2\u0006\u0010P\u001a\u00020E2\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J\u0010\u0010U\u001a\u00020<2\u0006\u0010\u001d\u001a\u00020VH\u0002J\u0006\u0010W\u001a\u00020<J\b\u0010X\u001a\u00020<H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\"8\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\'\u001a\u00020(8\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010-\u001a\u00020.8\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102\u00a8\u0006Y"}, d2 = {"Lcom/github/pockethub/android/ui/commit/CommitListFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "()V", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "dialog", "Lcom/github/pockethub/android/ui/ref/RefDialog;", "errorMessage", "", "getErrorMessage", "()I", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "getItemListHandler", "()Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "setItemListHandler", "(Lcom/github/pockethub/android/ui/helpers/ItemListHandler;)V", "pagedListFetcher", "Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "Lcom/meisolsson/githubsdk/model/Commit;", "getPagedListFetcher", "()Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "setPagedListFetcher", "(Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;)V", "ref", "", "repo", "Lcom/meisolsson/githubsdk/model/Repository;", "repoService", "Lcom/meisolsson/githubsdk/service/repositories/RepositoryService;", "getRepoService", "()Lcom/meisolsson/githubsdk/service/repositories/RepositoryService;", "setRepoService", "(Lcom/meisolsson/githubsdk/service/repositories/RepositoryService;)V", "service", "Lcom/meisolsson/githubsdk/service/repositories/RepositoryCommitService;", "getService", "()Lcom/meisolsson/githubsdk/service/repositories/RepositoryCommitService;", "setService", "(Lcom/meisolsson/githubsdk/service/repositories/RepositoryCommitService;)V", "store", "Lcom/github/pockethub/android/core/commit/CommitStore;", "getStore", "()Lcom/github/pockethub/android/core/commit/CommitStore;", "setStore", "(Lcom/github/pockethub/android/core/commit/CommitStore;)V", "createItem", "Lcom/xwray/groupie/Item;", "dataItem", "loadData", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "page", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDialogResult", "arguments", "onItemClick", "item", "view", "onPageLoaded", "", "items", "onViewCreated", "setRef", "Lcom/meisolsson/githubsdk/model/git/GitReference;", "switchRefs", "updateRefLabel", "app_debug"})
public final class CommitListFragment extends com.github.pockethub.android.ui.base.BaseFragment implements com.github.pockethub.android.ui.DialogResultListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.meisolsson.githubsdk.service.repositories.RepositoryCommitService service;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.meisolsson.githubsdk.service.repositories.RepositoryService repoService;
    
    /**
     * Avatar loader
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.util.AvatarLoader avatars;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.core.commit.CommitStore store;
    @org.jetbrains.annotations.NotNull()
    protected com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Commit> pagedListFetcher;
    @org.jetbrains.annotations.NotNull()
    protected com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    private com.meisolsson.githubsdk.model.Repository repo;
    private com.github.pockethub.android.ui.ref.RefDialog dialog;
    private java.lang.String ref;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.meisolsson.githubsdk.service.repositories.RepositoryCommitService getService() {
        return null;
    }
    
    protected final void setService(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.service.repositories.RepositoryCommitService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.meisolsson.githubsdk.service.repositories.RepositoryService getRepoService() {
        return null;
    }
    
    protected final void setRepoService(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.service.repositories.RepositoryService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    protected final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.core.commit.CommitStore getStore() {
        return null;
    }
    
    protected final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.commit.CommitStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Commit> getPagedListFetcher() {
        return null;
    }
    
    protected final void setPagedListFetcher(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Commit> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.ui.helpers.ItemListHandler getItemListHandler() {
        return null;
    }
    
    protected final void setItemListHandler(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.ItemListHandler p0) {
    }
    
    protected final int getErrorMessage() {
        return 0;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
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
    
    private final io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.Commit>>> loadData(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.xwray.groupie.Item<?> createItem(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Commit dataItem) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final java.util.List<com.xwray.groupie.Item<?>> onPageLoaded(@org.jetbrains.annotations.NotNull()
    java.util.List<com.xwray.groupie.Item<?>> items) {
        return null;
    }
    
    public final void onItemClick(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
    }
    
    private final void updateRefLabel() {
    }
    
    private final void setRef(com.meisolsson.githubsdk.model.git.GitReference ref) {
    }
    
    public final void switchRefs() {
    }
    
    public CommitListFragment() {
        super();
    }
}
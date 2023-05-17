package com.github.pockethub.android.ui.repo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.ListFetcher;
import com.github.pockethub.android.R;
import com.github.pockethub.android.persistence.AccountDataManager;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.repository.RepositoryHeaderItem;
import com.github.pockethub.android.ui.item.repository.RepositoryItem;
import com.github.pockethub.android.ui.user.OrganizationSelectionListener;
import com.github.pockethub.android.ui.user.OrganizationSelectionProvider;
import com.github.pockethub.android.ui.user.UserViewActivity;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.OnItemLongClickListener;
import io.reactivex.Observable;
import io.reactivex.Single;
import kotlinx.android.synthetic.main.fragment_item_list.view.*;
import kotlinx.android.synthetic.main.repo_dialog.view.*;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;

/**
 * Fragment to display a list of [Repository] instances
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0014\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020\u0018H\u0002J\u001c\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180#0\"2\u0006\u0010$\u001a\u00020%H\u0002J\u0012\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\"\u0010*\u001a\u00020\'2\u0006\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00112\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J&\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u0001042\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J$\u00105\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f062\u0010\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f06H\u0004J\b\u00108\u001a\u00020\'H\u0016J\u001c\u00109\u001a\u00020\'2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010:\u001a\u000200H\u0002J\u001c\u0010;\u001a\u00020%2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010:\u001a\u000200H\u0002J\u0010\u0010<\u001a\u00020\'2\u0006\u0010=\u001a\u00020\u001bH\u0016J\u0010\u0010>\u001a\u00020\'2\u0006\u0010?\u001a\u00020)H\u0016J\b\u0010@\u001a\u00020\'H\u0016J\u001a\u0010A\u001a\u00020\'2\u0006\u0010:\u001a\u0002002\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u001a\u0010B\u001a\u00020\'2\u0010\u0010C\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f06H\u0002J\u0010\u0010D\u001a\u00020\'2\u0006\u0010E\u001a\u00020\u001bH\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006F"}, d2 = {"Lcom/github/pockethub/android/ui/repo/RepositoryListFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Lcom/github/pockethub/android/ui/user/OrganizationSelectionListener;", "()V", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "cache", "Lcom/github/pockethub/android/persistence/AccountDataManager;", "getCache", "()Lcom/github/pockethub/android/persistence/AccountDataManager;", "setCache", "(Lcom/github/pockethub/android/persistence/AccountDataManager;)V", "errorMessage", "", "getErrorMessage", "()I", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "listFetcher", "Lcom/github/pockethub/android/ui/helpers/ListFetcher;", "Lcom/meisolsson/githubsdk/model/Repository;", "org", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/meisolsson/githubsdk/model/User;", "recentRepos", "Lcom/github/pockethub/android/ui/repo/RecentRepositories;", "createItem", "Lcom/xwray/groupie/Item;", "item", "loadData", "Lio/reactivex/Single;", "", "forceRefresh", "", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDataLoaded", "", "newItems", "onDetach", "onItemClick", "view", "onItemLongClick", "onOrganizationSelected", "organization", "onSaveInstanceState", "outState", "onStop", "onViewCreated", "updateHeaders", "repos", "viewUser", "user", "app_debug"})
public final class RepositoryListFragment extends com.github.pockethub.android.ui.base.BaseFragment implements com.github.pockethub.android.ui.user.OrganizationSelectionListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.persistence.AccountDataManager cache;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.util.AvatarLoader avatars;
    private com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    private com.github.pockethub.android.ui.helpers.ListFetcher<com.meisolsson.githubsdk.model.Repository> listFetcher;
    private final java.util.concurrent.atomic.AtomicReference<com.meisolsson.githubsdk.model.User> org = null;
    private com.github.pockethub.android.ui.repo.RecentRepositories recentRepos;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.persistence.AccountDataManager getCache() {
        return null;
    }
    
    protected final void setCache(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.persistence.AccountDataManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    protected final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    private final int getErrorMessage() {
        return 0;
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
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    @java.lang.Override()
    public void onOrganizationSelected(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.User organization) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void onItemClick(com.xwray.groupie.Item<?> item, android.view.View view) {
    }
    
    private final boolean onItemLongClick(com.xwray.groupie.Item<?> item, android.view.View view) {
        return false;
    }
    
    private final void viewUser(com.meisolsson.githubsdk.model.User user) {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
    
    private final void updateHeaders(java.util.List<com.xwray.groupie.Item<?>> repos) {
    }
    
    private final io.reactivex.Single<java.util.List<com.meisolsson.githubsdk.model.Repository>> loadData(boolean forceRefresh) {
        return null;
    }
    
    private final com.xwray.groupie.Item<?> createItem(com.meisolsson.githubsdk.model.Repository item) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final java.util.List<com.xwray.groupie.Item<?>> onDataLoaded(@org.jetbrains.annotations.NotNull()
    java.util.List<com.xwray.groupie.Item<?>> newItems) {
        return null;
    }
    
    public RepositoryListFragment() {
        super();
    }
}
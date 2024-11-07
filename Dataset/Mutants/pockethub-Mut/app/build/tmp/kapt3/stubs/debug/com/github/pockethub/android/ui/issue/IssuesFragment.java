package com.github.pockethub.android.ui.issue;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.PagedListFetcher;
import com.github.pockethub.android.ui.helpers.PagedScrollListener;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueFilter;
import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.persistence.AccountDataManager;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.issue.IssueFilterHeaderItem;
import com.github.pockethub.android.ui.item.issue.IssueItem;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.service.issues.IssueService;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import io.reactivex.Single;
import kotlinx.android.synthetic.main.fragment_item_list.view.*;
import retrofit2.Response;
import java.util.*;
import javax.inject.Inject;

/**
 * Fragment to display a list of issues
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0019H\u0002J\"\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190.0-0,2\u0006\u0010/\u001a\u00020\u0010H\u0002J\"\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00102\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0010\u00106\u001a\u0002012\u0006\u00107\u001a\u000208H\u0016J\u0012\u00109\u001a\u0002012\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u0018\u0010<\u001a\u0002012\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0016J&\u0010A\u001a\u0004\u0018\u00010B2\u0006\u0010?\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u001c\u0010F\u001a\u0002012\n\u0010*\u001a\u0006\u0012\u0002\b\u00030G2\u0006\u0010H\u001a\u00020BH\u0002J\u0010\u0010I\u001a\u00020J2\u0006\u0010*\u001a\u00020KH\u0016J\u001a\u0010L\u001a\u0002012\u0006\u0010H\u001a\u00020B2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020#8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'\u00a8\u0006M"}, d2 = {"Lcom/github/pockethub/android/ui/issue/IssuesFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "()V", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "cache", "Lcom/github/pockethub/android/persistence/AccountDataManager;", "getCache", "()Lcom/github/pockethub/android/persistence/AccountDataManager;", "setCache", "(Lcom/github/pockethub/android/persistence/AccountDataManager;)V", "errorMessage", "", "getErrorMessage", "()I", "filter", "Lcom/github/pockethub/android/core/issue/IssueFilter;", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "pagedListFetcher", "Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "Lcom/meisolsson/githubsdk/model/Issue;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "service", "Lcom/meisolsson/githubsdk/service/issues/IssueService;", "getService", "()Lcom/meisolsson/githubsdk/service/issues/IssueService;", "setService", "(Lcom/meisolsson/githubsdk/service/issues/IssueService;)V", "store", "Lcom/github/pockethub/android/core/issue/IssueStore;", "getStore", "()Lcom/github/pockethub/android/core/issue/IssueStore;", "setStore", "(Lcom/github/pockethub/android/core/issue/IssueStore;)V", "createItem", "Lcom/github/pockethub/android/ui/item/issue/IssueItem;", "item", "loadData", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "page", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onItemClick", "Lcom/xwray/groupie/Item;", "view", "onOptionsItemSelected", "", "Landroid/view/MenuItem;", "onViewCreated", "app_debug"})
public final class IssuesFragment extends com.github.pockethub.android.ui.base.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.persistence.AccountDataManager cache;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.core.issue.IssueStore store;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.AvatarLoader avatars;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.meisolsson.githubsdk.service.issues.IssueService service;
    private com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Issue> pagedListFetcher;
    private com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    private com.github.pockethub.android.core.issue.IssueFilter filter;
    private com.meisolsson.githubsdk.model.Repository repository;
    private final int errorMessage = com.github.pockethub.android.R.string.error_issues_load;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.persistence.AccountDataManager getCache() {
        return null;
    }
    
    public final void setCache(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.persistence.AccountDataManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.issue.IssueStore getStore() {
        return null;
    }
    
    public final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.issue.IssueStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    public final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.service.issues.IssueService getService() {
        return null;
    }
    
    public final void setService(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.service.issues.IssueService p0) {
    }
    
    public final int getErrorMessage() {
        return 0;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
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
    
    private final void onItemClick(com.xwray.groupie.Item<?> item, android.view.View view) {
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
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.Issue>>> loadData(int page) {
        return null;
    }
    
    private final com.github.pockethub.android.ui.item.issue.IssueItem createItem(com.meisolsson.githubsdk.model.Issue item) {
        return null;
    }
    
    public IssuesFragment() {
        super();
    }
}
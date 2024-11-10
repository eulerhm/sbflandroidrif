package com.github.pockethub.android.ui.issue;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.PagedListFetcher;
import com.github.pockethub.android.ui.helpers.PagedScrollListener;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.issue.IssueDashboardItem;
import com.github.pockethub.android.ui.item.issue.IssueItem;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.service.issues.IssueService;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import io.reactivex.Single;
import kotlinx.android.synthetic.main.fragment_item_list.view.*;
import retrofit2.Response;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Fragment to display a pageable list of dashboard issues
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 =2\u00020\u0001:\u0001=B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\"\u001a\u0006\u0012\u0002\b\u00030#2\u0006\u0010$\u001a\u00020\u0015H\u0002J\"\u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150(0\'0&2\u0006\u0010)\u001a\u00020\nH\u0002J\u0012\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\"\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\n2\b\u00101\u001a\u0004\u0018\u000102H\u0016J&\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u001c\u00109\u001a\u00020+2\n\u0010:\u001a\u0006\u0012\u0002\b\u00030#2\u0006\u0010;\u001a\u000204H\u0002J\u001a\u0010<\u001a\u00020+2\u0006\u0010;\u001a\u0002042\b\u0010,\u001a\u0004\u0018\u00010-H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u001d8\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!\u00a8\u0006>"}, d2 = {"Lcom/github/pockethub/android/ui/issue/DashboardIssueFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "()V", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "errorMessage", "", "getErrorMessage", "()I", "filterData", "", "", "", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "pagedListFetcher", "Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "Lcom/meisolsson/githubsdk/model/Issue;", "service", "Lcom/meisolsson/githubsdk/service/issues/IssueService;", "getService", "()Lcom/meisolsson/githubsdk/service/issues/IssueService;", "setService", "(Lcom/meisolsson/githubsdk/service/issues/IssueService;)V", "store", "Lcom/github/pockethub/android/core/issue/IssueStore;", "getStore", "()Lcom/github/pockethub/android/core/issue/IssueStore;", "setStore", "(Lcom/github/pockethub/android/core/issue/IssueStore;)V", "createItem", "Lcom/xwray/groupie/Item;", "item", "loadData", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "page", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onItemClick", "clickedItem", "view", "onViewCreated", "Companion", "app_debug"})
public final class DashboardIssueFragment extends com.github.pockethub.android.ui.base.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.meisolsson.githubsdk.service.issues.IssueService service;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.core.issue.IssueStore store;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.util.AvatarLoader avatars;
    private com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Issue> pagedListFetcher;
    private com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    private java.util.Map<java.lang.String, ? extends java.lang.Object> filterData;
    
    /**
     * Filter data argument
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_FILTER = "filter";
    public static final com.github.pockethub.android.ui.issue.DashboardIssueFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.meisolsson.githubsdk.service.issues.IssueService getService() {
        return null;
    }
    
    protected final void setService(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.service.issues.IssueService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.core.issue.IssueStore getStore() {
        return null;
    }
    
    protected final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.issue.IssueStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    protected final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    protected final int getErrorMessage() {
        return 0;
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
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.Issue>>> loadData(int page) {
        return null;
    }
    
    private final void onItemClick(com.xwray.groupie.Item<?> clickedItem, android.view.View view) {
    }
    
    private final com.xwray.groupie.Item<?> createItem(com.meisolsson.githubsdk.model.Issue item) {
        return null;
    }
    
    public DashboardIssueFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/github/pockethub/android/ui/issue/DashboardIssueFragment$Companion;", "", "()V", "ARG_FILTER", "", "getARG_FILTER", "()Ljava/lang/String;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getARG_FILTER() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
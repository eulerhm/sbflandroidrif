package com.github.pockethub.android.ui.issue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.PagedListFetcher;
import com.github.pockethub.android.ui.helpers.PagedScrollListener;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.issue.IssueItem;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.service.search.SearchService;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import io.reactivex.Single;
import kotlinx.android.synthetic.main.fragment_item_list.view.*;
import retrofit2.Response;
import javax.inject.Inject;

/**
 * Fragment to display a list of [Issue] instances
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010 \u001a\u0006\u0012\u0002\b\u00030!2\u0006\u0010\"\u001a\u00020\u0011H\u0002J\"\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110&0%0$2\u0006\u0010\'\u001a\u00020\nH\u0002J\u0012\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J&\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u0001012\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u001a\u00102\u001a\u00020)2\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030!2\u0006\u00103\u001a\u00020-J\u001a\u00104\u001a\u00020)2\u0006\u00103\u001a\u00020-2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u000e\u00105\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017R\u001e\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u00020\u001b8\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u00a8\u00066"}, d2 = {"Lcom/github/pockethub/android/ui/issue/SearchIssueListFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "()V", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "errorMessage", "", "getErrorMessage", "()I", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "pagedListFetcher", "Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "Lcom/meisolsson/githubsdk/model/Issue;", "getPagedListFetcher", "()Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "setPagedListFetcher", "(Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;)V", "query", "", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "service", "Lcom/meisolsson/githubsdk/service/search/SearchService;", "getService", "()Lcom/meisolsson/githubsdk/service/search/SearchService;", "setService", "(Lcom/meisolsson/githubsdk/service/search/SearchService;)V", "createItem", "Lcom/xwray/groupie/Item;", "item", "loadData", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "page", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onItemClick", "view", "onViewCreated", "setQuery", "app_debug"})
public final class SearchIssueListFragment extends com.github.pockethub.android.ui.base.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.meisolsson.githubsdk.service.search.SearchService service;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.util.AvatarLoader avatars;
    @org.jetbrains.annotations.NotNull()
    public com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Issue> pagedListFetcher;
    private com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    private com.meisolsson.githubsdk.model.Repository repository;
    private java.lang.String query;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.meisolsson.githubsdk.service.search.SearchService getService() {
        return null;
    }
    
    protected final void setService(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.service.search.SearchService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    protected final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Issue> getPagedListFetcher() {
        return null;
    }
    
    public final void setPagedListFetcher(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Issue> p0) {
    }
    
    protected final int getErrorMessage() {
        return 0;
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
    
    /**
     * @param query
     * @return this fragment
     */
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.ui.issue.SearchIssueListFragment setQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    public final void onItemClick(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.Issue>>> loadData(int page) {
        return null;
    }
    
    private final com.xwray.groupie.Item<?> createItem(com.meisolsson.githubsdk.model.Issue item) {
        return null;
    }
    
    public SearchIssueListFragment() {
        super();
    }
}
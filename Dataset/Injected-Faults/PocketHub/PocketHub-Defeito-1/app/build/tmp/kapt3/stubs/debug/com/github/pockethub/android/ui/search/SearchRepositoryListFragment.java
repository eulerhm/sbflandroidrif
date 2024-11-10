package com.github.pockethub.android.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.PagedListFetcher;
import com.github.pockethub.android.ui.helpers.PagedScrollListener;
import com.github.pockethub.android.R;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.rx.RxProgress;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.repository.RepositoryItem;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.service.repositories.RepositoryService;
import com.meisolsson.githubsdk.service.search.SearchService;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.fragment_item_list.view.*;
import retrofit2.Response;
import java.text.MessageFormat;
import javax.inject.Inject;

/**
 * Fragment to display a list of [Repository] instances
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u000bH\u0002J\"\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001c0\u001b0\u001a2\u0006\u0010\u001d\u001a\u00020\u0004H\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u001a\u0010*\u001a\u00020\u001f2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010+\u001a\u00020#J\u001a\u0010,\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0012\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u000100H\u0002J\b\u00101\u001a\u00020\u001fH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u00062"}, d2 = {"Lcom/github/pockethub/android/ui/search/SearchRepositoryListFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "()V", "errorMessage", "", "getErrorMessage", "()I", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "pagedListFetcher", "Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "Lcom/meisolsson/githubsdk/model/Repository;", "getPagedListFetcher", "()Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "setPagedListFetcher", "(Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;)V", "service", "Lcom/meisolsson/githubsdk/service/search/SearchService;", "getService", "()Lcom/meisolsson/githubsdk/service/search/SearchService;", "setService", "(Lcom/meisolsson/githubsdk/service/search/SearchService;)V", "createItem", "Lcom/xwray/groupie/Item;", "item", "loadData", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "page", "onAttach", "", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "view", "onViewCreated", "openRepositoryMatch", "", "query", "", "start", "app_debug"})
public final class SearchRepositoryListFragment extends com.github.pockethub.android.ui.base.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.meisolsson.githubsdk.service.search.SearchService service;
    @org.jetbrains.annotations.NotNull()
    public com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Repository> pagedListFetcher;
    private com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.meisolsson.githubsdk.service.search.SearchService getService() {
        return null;
    }
    
    protected final void setService(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.service.search.SearchService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Repository> getPagedListFetcher() {
        return null;
    }
    
    public final void setPagedListFetcher(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.Repository> p0) {
    }
    
    protected final int getErrorMessage() {
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
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void start() {
    }
    
    public final void onItemClick(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Check if the search query is an exact repository name/owner match and
     * open the repository activity and finish the current activity when it is
     *
     * @param query
     * @return true if query opened as repository, false otherwise
     */
    private final boolean openRepositoryMatch(java.lang.String query) {
        return false;
    }
    
    private final io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.Repository>>> loadData(int page) {
        return null;
    }
    
    private final com.xwray.groupie.Item<?> createItem(com.meisolsson.githubsdk.model.Repository item) {
        return null;
    }
    
    public SearchRepositoryListFragment() {
        super();
    }
}
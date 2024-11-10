package com.github.pockethub.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.gist.GistEventMatcher;
import com.github.pockethub.android.core.issue.IssueEventMatcher;
import com.github.pockethub.android.core.repo.RepositoryEventMatcher;
import com.github.pockethub.android.core.user.UserEventMatcher;
import com.github.pockethub.android.core.user.UserEventMatcher.UserPair;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.commit.CommitCompareViewActivity;
import com.github.pockethub.android.ui.commit.CommitViewActivity;
import com.github.pockethub.android.ui.gist.GistsViewActivity;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.PagedListFetcher;
import com.github.pockethub.android.ui.helpers.PagedScrollListener;
import com.github.pockethub.android.ui.issue.IssuesViewActivity;
import com.github.pockethub.android.ui.item.news.NewsItem;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.ConvertUtils;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.GitHubEvent;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.meisolsson.githubsdk.model.payload.CommitCommentPayload;
import com.meisolsson.githubsdk.model.payload.PushPayload;
import com.meisolsson.githubsdk.model.payload.ReleasePayload;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import io.reactivex.Single;
import kotlinx.android.synthetic.main.fragment_item_list.view.*;
import retrofit2.Response;
import javax.inject.Inject;

/**
 * Base news fragment class with utilities for subclasses to built on
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0004J\"\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001c0\u001b0\u001a2\u0006\u0010\u001d\u001a\u00020\nH$J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001a\u0010&\u001a\u00020\'2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010(\u001a\u00020\u001fJ\u001a\u0010)\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u0010*\u001a\u00020\'2\u0006\u0010+\u001a\u00020\u0011H\u0002J\u0010\u0010,\u001a\u00020\'2\u0006\u0010+\u001a\u00020\u0011H\u0002J\u0010\u0010-\u001a\u00020\'2\u0006\u0010+\u001a\u00020\u0011H\u0002J\u001a\u0010.\u001a\u00020\'2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102H\u0014J\u0012\u00103\u001a\u00020\'2\b\u00101\u001a\u0004\u0018\u000102H\u0014J\u0010\u00104\u001a\u00020\'2\u0006\u00105\u001a\u000206H\u0014J\u0012\u00104\u001a\u0002072\b\u00108\u001a\u0004\u0018\u000109H\u0014R\u001e\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006:"}, d2 = {"Lcom/github/pockethub/android/ui/NewsFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "()V", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "errorMessage", "", "getErrorMessage", "()I", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "pagedListFetcher", "Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "Lcom/meisolsson/githubsdk/model/GitHubEvent;", "getPagedListFetcher", "()Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "setPagedListFetcher", "(Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;)V", "createItem", "Lcom/xwray/groupie/Item;", "item", "loadData", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "page", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "", "view", "onViewCreated", "openCommitComment", "event", "openDownload", "openPush", "viewIssue", "issue", "Lcom/meisolsson/githubsdk/model/Issue;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "viewRepository", "viewUser", "users", "Lcom/github/pockethub/android/core/user/UserEventMatcher$UserPair;", "", "user", "Lcom/meisolsson/githubsdk/model/User;", "app_debug"})
public abstract class NewsFragment extends com.github.pockethub.android.ui.base.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.util.AvatarLoader avatars;
    @org.jetbrains.annotations.NotNull()
    protected com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.GitHubEvent> pagedListFetcher;
    private com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    protected final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.GitHubEvent> getPagedListFetcher() {
        return null;
    }
    
    protected final void setPagedListFetcher(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.PagedListFetcher<com.meisolsson.githubsdk.model.GitHubEvent> p0) {
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
    
    @org.jetbrains.annotations.NotNull()
    protected abstract io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.GitHubEvent>>> loadData(int page);
    
    public final void onItemClick(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void openDownload(com.meisolsson.githubsdk.model.GitHubEvent event) {
    }
    
    private final void openCommitComment(com.meisolsson.githubsdk.model.GitHubEvent event) {
    }
    
    private final void openPush(com.meisolsson.githubsdk.model.GitHubEvent event) {
    }
    
    /**
     * Start an activity to view the given repository
     *
     * @param repository
     */
    protected void viewRepository(@org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.Repository repository) {
    }
    
    /**
     * Start an activity to view the given [UserPair]
     *
     *
     * This method does nothing by default, subclasses should override
     *
     * @param users
     */
    protected void viewUser(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.user.UserEventMatcher.UserPair users) {
    }
    
    /**
     * Start an activity to view the given [User]
     *
     * @param user
     * @return true if new activity started, false otherwise
     */
    protected boolean viewUser(@org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.User user) {
        return false;
    }
    
    /**
     * Start an activity to view the given [Issue]
     *
     * @param issue
     * @param repository
     */
    protected void viewIssue(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Issue issue, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.Repository repository) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.xwray.groupie.Item<?> createItem(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.GitHubEvent item) {
        return null;
    }
    
    public NewsFragment() {
        super();
    }
}
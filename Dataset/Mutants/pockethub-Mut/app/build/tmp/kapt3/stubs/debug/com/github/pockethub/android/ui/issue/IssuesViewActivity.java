package com.github.pockethub.android.ui.issue;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.core.issue.IssueUtils;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.helpers.PagerHandler;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import com.github.pockethub.android.ui.user.UriLauncherActivity;
import com.github.pockethub.android.util.InfoUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.meisolsson.githubsdk.service.repositories.RepositoryService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.activity_pager.*;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Activity to display a collection of issues or pull requests in a pager
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 .2\u00020\u00012\u00020\u0002:\u0001.B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001aH\u0014J \u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u001dH\u0016J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010\'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020!H\u0002J\u0010\u0010)\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020!H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/github/pockethub/android/ui/issue/IssuesViewActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "()V", "canWrite", "", "issueNumbers", "", "pagerHandler", "Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "Lcom/github/pockethub/android/ui/issue/IssuesPagerAdapter;", "pullRequests", "", "repo", "Lcom/meisolsson/githubsdk/model/Repository;", "repoIds", "Ljava/util/ArrayList;", "store", "Lcom/github/pockethub/android/core/issue/IssueStore;", "getStore", "()Lcom/github/pockethub/android/core/issue/IssueStore;", "setStore", "(Lcom/github/pockethub/android/core/issue/IssueStore;)V", "user", "Lcom/meisolsson/githubsdk/model/User;", "configurePager", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDialogResult", "requestCode", "", "resultCode", "arguments", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPageChange", "position", "repositoryLoaded", "startActivity", "intent", "Landroid/content/Intent;", "updateTitle", "Companion", "app_debug"})
public final class IssuesViewActivity extends com.github.pockethub.android.ui.base.BaseActivity implements com.github.pockethub.android.ui.DialogResultListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.core.issue.IssueStore store;
    private int[] issueNumbers;
    private boolean[] pullRequests;
    private java.util.ArrayList<com.meisolsson.githubsdk.model.Repository> repoIds;
    private com.meisolsson.githubsdk.model.Repository repo;
    private com.meisolsson.githubsdk.model.User user;
    private boolean canWrite;
    private com.github.pockethub.android.ui.helpers.PagerHandler<com.github.pockethub.android.ui.issue.IssuesPagerAdapter> pagerHandler;
    private static final java.lang.String EXTRA_PULL_REQUESTS = "pullRequests";
    public static final com.github.pockethub.android.ui.issue.IssuesViewActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.issue.IssueStore getStore() {
        return null;
    }
    
    public final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.issue.IssueStore p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void repositoryLoaded(com.meisolsson.githubsdk.model.Repository repo) {
    }
    
    private final void configurePager() {
    }
    
    private final void updateTitle(int position) {
    }
    
    private final void onPageChange(int position) {
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
    }
    
    @java.lang.Override()
    public void startActivity(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    public IssuesViewActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ$\u0010\u0005\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u001c\u0010\u0005\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/github/pockethub/android/ui/issue/IssuesViewActivity$Companion;", "", "()V", "EXTRA_PULL_REQUESTS", "", "createIntent", "Landroid/content/Intent;", "issue", "Lcom/meisolsson/githubsdk/model/Issue;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "issues", "", "position", "", "app_debug"})
    public static final class Companion {
        
        /**
         * Create an intent to show a single issue
         *
         * @param issue
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Issue issue) {
            return null;
        }
        
        /**
         * Create an intent to show issue
         *
         * @param issue
         * @param repository
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Issue issue, @org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Repository repository) {
            return null;
        }
        
        /**
         * Create an intent to show issues with an initial selected issue
         *
         * @param issues
         * @param repository
         * @param position
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        java.util.Collection<? extends com.meisolsson.githubsdk.model.Issue> issues, @org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Repository repository, int position) {
            return null;
        }
        
        /**
         * Create an intent to show issues with an initial selected issue
         *
         * @param issues
         * @param position
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        java.util.Collection<? extends com.meisolsson.githubsdk.model.Issue> issues, int position) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
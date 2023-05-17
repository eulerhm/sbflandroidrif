package com.github.pockethub.android.ui.repo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.repo.RepositoryUtils;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.helpers.PagerHandler;
import com.github.pockethub.android.ui.user.UriLauncherActivity;
import com.github.pockethub.android.ui.user.UserViewActivity;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ShareUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.service.activity.StarringService;
import com.meisolsson.githubsdk.service.repositories.RepositoryContentService;
import com.meisolsson.githubsdk.service.repositories.RepositoryForkService;
import com.meisolsson.githubsdk.service.repositories.RepositoryService;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.pager_with_tabs.*;
import kotlinx.android.synthetic.main.tabbed_progress_pager.*;
import retrofit2.Response;

/**
 * Activity to view a repository
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \'2\u00020\u00012\u00020\u0002:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u000eH\u0014J \u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0016H\u0016J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0019H\u0016J\b\u0010%\u001a\u00020\u000eH\u0002J\b\u0010&\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/github/pockethub/android/ui/repo/RepositoryViewActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "()V", "hasReadme", "", "isStarred", "pagerHandler", "Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "Lcom/github/pockethub/android/ui/repo/RepositoryPagerAdapter;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "starredStatusChecked", "checkReadme", "", "checkStarredRepositoryStatus", "configurePager", "deleteRepository", "forkRepository", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "optionsMenu", "Landroid/view/Menu;", "onDestroy", "onDialogResult", "requestCode", "", "resultCode", "arguments", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "menu", "shareRepository", "starRepository", "Companion", "app_debug"})
public final class RepositoryViewActivity extends com.github.pockethub.android.ui.base.BaseActivity implements com.github.pockethub.android.ui.DialogResultListener {
    private com.meisolsson.githubsdk.model.Repository repository;
    private boolean isStarred;
    private boolean starredStatusChecked;
    private boolean hasReadme;
    private com.github.pockethub.android.ui.helpers.PagerHandler<com.github.pockethub.android.ui.repo.RepositoryPagerAdapter> pagerHandler;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "RepositoryViewActivity";
    public static final com.github.pockethub.android.ui.repo.RepositoryViewActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu optionsMenu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onPrepareOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void checkReadme() {
    }
    
    private final void configurePager() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
    }
    
    private final void starRepository() {
    }
    
    private final void checkStarredRepositoryStatus() {
    }
    
    private final void shareRepository() {
    }
    
    private final void forkRepository() {
    }
    
    private final void deleteRepository() {
    }
    
    public RepositoryViewActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/github/pockethub/android/ui/repo/RepositoryViewActivity$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "createIntent", "Landroid/content/Intent;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTAG() {
            return null;
        }
        
        /**
         * Create intent for this activity
         *
         * @param repository
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Repository repository) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
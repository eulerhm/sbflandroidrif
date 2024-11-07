package com.github.pockethub.android.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.MainActivity;
import com.github.pockethub.android.ui.helpers.PagerHandler;
import com.github.pockethub.android.util.ToastUtils;
import kotlinx.android.synthetic.main.pager_with_tabs.*;
import kotlinx.android.synthetic.main.tabbed_progress_pager.*;

/**
 * Activity to view search results
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0014J\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u0004H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/github/pockethub/android/ui/search/SearchActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "()V", "lastQuery", "", "pagerHandler", "Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "Lcom/github/pockethub/android/ui/search/SearchPagerAdapter;", "repoFragment", "Lcom/github/pockethub/android/ui/search/SearchRepositoryListFragment;", "searchView", "Landroidx/appcompat/widget/SearchView;", "userFragment", "Lcom/github/pockethub/android/ui/search/SearchUserListFragment;", "configurePager", "", "findFragments", "handleIntent", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onNewIntent", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "search", "query", "app_debug"})
public final class SearchActivity extends com.github.pockethub.android.ui.base.BaseActivity {
    private com.github.pockethub.android.ui.search.SearchRepositoryListFragment repoFragment;
    private com.github.pockethub.android.ui.search.SearchUserListFragment userFragment;
    private androidx.appcompat.widget.SearchView searchView;
    private java.lang.String lastQuery;
    private com.github.pockethub.android.ui.helpers.PagerHandler<com.github.pockethub.android.ui.search.SearchPagerAdapter> pagerHandler;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onNewIntent(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    private final void handleIntent(android.content.Intent intent) {
    }
    
    private final void search(java.lang.String query) {
    }
    
    private final void configurePager() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void findFragments() {
    }
    
    public SearchActivity() {
        super();
    }
}
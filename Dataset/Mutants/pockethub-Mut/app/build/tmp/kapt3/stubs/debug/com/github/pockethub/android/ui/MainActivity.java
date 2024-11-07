package com.github.pockethub.android.ui;

import android.accounts.AccountManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import com.github.pockethub.android.Database;
import com.github.pockethub.android.R;
import com.github.pockethub.android.accounts.AccountUtils;
import com.github.pockethub.android.accounts.AccountsHelper;
import com.github.pockethub.android.accounts.LoginActivity;
import com.github.pockethub.android.core.user.UserComparator;
import com.github.pockethub.android.persistence.AccountDataManager;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.gist.GistsPagerFragment;
import com.github.pockethub.android.ui.issue.FilterListFragment;
import com.github.pockethub.android.ui.issue.IssueDashboardPagerFragment;
import com.github.pockethub.android.ui.notification.NotificationActivity;
import com.github.pockethub.android.ui.user.HomePagerFragment;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.ToastUtils;
import com.google.android.material.navigation.NavigationView;
import com.meisolsson.githubsdk.core.TokenStore;
import com.meisolsson.githubsdk.model.User;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.activity_main.*;
import java.util.Collections;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 O2\u00020\u00012\u00020\u0002:\u0001OB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u00104\u001a\u000205H\u0002J\u0010\u00106\u001a\u0002052\u0006\u00107\u001a\u000208H\u0016J\u0012\u00109\u001a\u0002052\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\u0010\u0010<\u001a\u0002032\u0006\u0010=\u001a\u00020>H\u0016J\b\u0010?\u001a\u000205H\u0014J\u0012\u0010@\u001a\u0002032\b\b\u0001\u0010A\u001a\u00020&H\u0016J\u0010\u0010B\u001a\u0002032\u0006\u0010C\u001a\u00020&H\u0016J\u0016\u0010D\u001a\u0002052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\'0*H\u0007J\u0012\u0010E\u001a\u0002052\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\b\u0010F\u001a\u000205H\u0014J\b\u0010G\u001a\u000205H\u0002J\b\u0010H\u001a\u000205H\u0002J\b\u0010I\u001a\u000205H\u0002J\b\u0010J\u001a\u000205H\u0002J\b\u0010K\u001a\u000205H\u0002J\u001a\u0010L\u001a\u0002052\u0006\u0010M\u001a\u00020\u00182\b\u0010N\u001a\u0004\u0018\u00010\'H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R$\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R&\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u0003\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\'0%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\'0*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u000e\u00102\u001a\u000203X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006P"}, d2 = {"Lcom/github/pockethub/android/ui/MainActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "Lcom/google/android/material/navigation/NavigationView$OnNavigationItemSelectedListener;", "()V", "accountDataManager", "Lcom/github/pockethub/android/persistence/AccountDataManager;", "getAccountDataManager", "()Lcom/github/pockethub/android/persistence/AccountDataManager;", "setAccountDataManager", "(Lcom/github/pockethub/android/persistence/AccountDataManager;)V", "accountManager", "Landroid/accounts/AccountManager;", "getAccountManager", "()Landroid/accounts/AccountManager;", "actionBarDrawerToggle", "Landroidx/appcompat/app/ActionBarDrawerToggle;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "avatars$annotations", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "currentFragment", "Landroidx/fragment/app/Fragment;", "currentFragment$annotations", "getCurrentFragment", "()Landroidx/fragment/app/Fragment;", "setCurrentFragment", "(Landroidx/fragment/app/Fragment;)V", "database", "Lcom/github/pockethub/android/Database;", "getDatabase", "()Lcom/github/pockethub/android/Database;", "setDatabase", "(Lcom/github/pockethub/android/Database;)V", "menuItemOrganizationMap", "", "Landroid/view/MenuItem;", "Lcom/meisolsson/githubsdk/model/User;", "org", "orgs", "", "userComparatorProvider", "Ljavax/inject/Provider;", "Lcom/github/pockethub/android/core/user/UserComparator;", "getUserComparatorProvider", "()Ljavax/inject/Provider;", "setUserComparatorProvider", "(Ljavax/inject/Provider;)V", "userLearnedDrawer", "", "logout", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "optionMenu", "Landroid/view/Menu;", "onDestroy", "onNavigationItemSelected", "menuItem", "onOptionsItemSelected", "item", "onOrgsLoaded", "onPostCreate", "onResume", "reloadOrgs", "setUpHeaderView", "setUpNavigationMenu", "setUpNavigationView", "startLoginActivity", "switchFragment", "fragment", "organization", "Companion", "app_debug"})
public final class MainActivity extends com.github.pockethub.android.ui.base.BaseActivity implements com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.persistence.AccountDataManager accountDataManager;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.Database database;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public javax.inject.Provider<com.github.pockethub.android.core.user.UserComparator> userComparatorProvider;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.AvatarLoader avatars;
    private java.util.List<? extends com.meisolsson.githubsdk.model.User> orgs;
    private com.meisolsson.githubsdk.model.User org;
    private boolean userLearnedDrawer;
    private androidx.appcompat.app.ActionBarDrawerToggle actionBarDrawerToggle;
    @org.jetbrains.annotations.Nullable()
    private androidx.fragment.app.Fragment currentFragment;
    private java.util.Map<android.view.MenuItem, com.meisolsson.githubsdk.model.User> menuItemOrganizationMap;
    private static final java.lang.String TAG = "MainActivity";
    private static final java.lang.String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    public static final com.github.pockethub.android.ui.MainActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.persistence.AccountDataManager getAccountDataManager() {
        return null;
    }
    
    public final void setAccountDataManager(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.persistence.AccountDataManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.Database getDatabase() {
        return null;
    }
    
    public final void setDatabase(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.Database p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final javax.inject.Provider<com.github.pockethub.android.core.user.UserComparator> getUserComparatorProvider() {
        return null;
    }
    
    public final void setUserComparatorProvider(@org.jetbrains.annotations.NotNull()
    javax.inject.Provider<com.github.pockethub.android.core.user.UserComparator> p0) {
    }
    
    @javax.inject.Singleton()
    public static void avatars$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    public final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @androidx.annotation.VisibleForTesting()
    public static void currentFragment$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.fragment.app.Fragment getCurrentFragment() {
        return null;
    }
    
    public final void setCurrentFragment(@org.jetbrains.annotations.Nullable()
    androidx.fragment.app.Fragment p0) {
    }
    
    private final android.accounts.AccountManager getAccountManager() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onConfigurationChanged(@org.jetbrains.annotations.NotNull()
    android.content.res.Configuration newConfig) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onPostCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void reloadOrgs() {
    }
    
    @androidx.annotation.VisibleForTesting()
    public final void onOrgsLoaded(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.meisolsson.githubsdk.model.User> orgs) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu optionMenu) {
        return false;
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void setUpHeaderView() {
    }
    
    private final void setUpNavigationView() {
    }
    
    private final void setUpNavigationMenu() {
    }
    
    @java.lang.Override()
    public boolean onNavigationItemSelected(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    android.view.MenuItem menuItem) {
        return false;
    }
    
    private final void logout() {
    }
    
    private final void startLoginActivity() {
    }
    
    private final void switchFragment(androidx.fragment.app.Fragment fragment, com.meisolsson.githubsdk.model.User organization) {
    }
    
    public MainActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/github/pockethub/android/ui/MainActivity$Companion;", "", "()V", "PREF_USER_LEARNED_DRAWER", "", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
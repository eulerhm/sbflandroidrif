package com.github.pockethub.android.ui.gist;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.core.gist.RefreshGistTaskFactory;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.helpers.PagerHandler;
import com.meisolsson.githubsdk.model.Gist;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.activity_pager_with_title.*;
import javax.inject.Inject;

/**
 * Activity to page through the content of all the files in a Gist
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001aH\u0014J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006$"}, d2 = {"Lcom/github/pockethub/android/ui/gist/GistFilesViewActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "()V", "gist", "Lcom/meisolsson/githubsdk/model/Gist;", "gistId", "", "initialPosition", "", "pagerHandler", "Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "Lcom/github/pockethub/android/ui/gist/GistFilesPagerAdapter;", "refreshGistTaskFactory", "error/NonExistentClass", "getRefreshGistTaskFactory", "()Lerror/NonExistentClass;", "setRefreshGistTaskFactory", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "store", "Lcom/github/pockethub/android/core/gist/GistStore;", "getStore", "()Lcom/github/pockethub/android/core/gist/GistStore;", "setStore", "(Lcom/github/pockethub/android/core/gist/GistStore;)V", "configurePager", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "Companion", "app_debug"})
public final class GistFilesViewActivity extends com.github.pockethub.android.ui.base.BaseActivity {
    private java.lang.String gistId;
    private int initialPosition;
    private com.meisolsson.githubsdk.model.Gist gist;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.core.gist.GistStore store;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public RefreshGistTaskFactory refreshGistTaskFactory;
    private com.github.pockethub.android.ui.helpers.PagerHandler<com.github.pockethub.android.ui.gist.GistFilesPagerAdapter> pagerHandler;
    public static final com.github.pockethub.android.ui.gist.GistFilesViewActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.gist.GistStore getStore() {
        return null;
    }
    
    public final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.gist.GistStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final RefreshGistTaskFactory getRefreshGistTaskFactory() {
        return null;
    }
    
    public final void setRefreshGistTaskFactory(@org.jetbrains.annotations.NotNull()
    RefreshGistTaskFactory p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
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
    
    public GistFilesViewActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/github/pockethub/android/ui/gist/GistFilesViewActivity$Companion;", "", "()V", "createIntent", "Landroid/content/Intent;", "gist", "Lcom/meisolsson/githubsdk/model/Gist;", "position", "", "app_debug"})
    public static final class Companion {
        
        /**
         * Create intent to show files with an initial selected file
         *
         * @param gist
         * @param position
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Gist gist, int position) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
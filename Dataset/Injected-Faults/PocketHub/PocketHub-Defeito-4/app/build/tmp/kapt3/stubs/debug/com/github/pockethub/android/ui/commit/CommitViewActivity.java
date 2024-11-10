package com.github.pockethub.android.ui.commit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.commit.CommitUtils;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.helpers.PagerHandler;
import com.github.pockethub.android.ui.item.commit.CommitItem;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import com.github.pockethub.android.util.InfoUtils;
import com.meisolsson.githubsdk.model.Repository;
import com.xwray.groupie.Item;
import kotlinx.android.synthetic.main.activity_pager.*;

/**
 * Activity to display a commit
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\bH\u0002R\u0018\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/github/pockethub/android/ui/commit/CommitViewActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "()V", "ids", "", "", "[Ljava/lang/CharSequence;", "initialPosition", "", "pagerHandler", "Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "Lcom/github/pockethub/android/ui/commit/CommitPagerAdapter;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPageChanged", "position", "Companion", "app_debug"})
public final class CommitViewActivity extends com.github.pockethub.android.ui.base.BaseActivity {
    private com.meisolsson.githubsdk.model.Repository repository;
    private java.lang.CharSequence[] ids;
    private int initialPosition;
    private com.github.pockethub.android.ui.helpers.PagerHandler<com.github.pockethub.android.ui.commit.CommitPagerAdapter> pagerHandler;
    public static final com.github.pockethub.android.ui.commit.CommitViewActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void onPageChanged(int position) {
    }
    
    public CommitViewActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000b\u00a2\u0006\u0002\u0010\fJ(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0010\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000eJ\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/github/pockethub/android/ui/commit/CommitViewActivity$Companion;", "", "()V", "createIntent", "Landroid/content/Intent;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "position", "", "ids", "", "", "(Lcom/meisolsson/githubsdk/model/Repository;I[Ljava/lang/String;)Landroid/content/Intent;", "commits", "", "Lcom/xwray/groupie/Item;", "id", "app_debug"})
    public static final class Companion {
        
        /**
         * Create intent for this activity
         *
         * @param repository
         * @param id
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Repository repository, @org.jetbrains.annotations.NotNull()
        java.lang.String id) {
            return null;
        }
        
        /**
         * Create intent for this activity
         *
         * @param repository
         * @param position
         * @param commits
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Repository repository, int position, @org.jetbrains.annotations.NotNull()
        java.util.Collection<? extends com.xwray.groupie.Item<?>> commits) {
            return null;
        }
        
        /**
         * Create intent for this activity
         *
         * @param repository
         * @param position
         * @param ids
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Repository repository, int position, @org.jetbrains.annotations.NotNull()
        java.lang.String... ids) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
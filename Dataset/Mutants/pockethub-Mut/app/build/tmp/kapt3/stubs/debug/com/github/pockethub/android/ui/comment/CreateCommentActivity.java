package com.github.pockethub.android.ui.comment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.helpers.PagerHandler;
import com.meisolsson.githubsdk.model.GitHubComment;
import com.meisolsson.githubsdk.model.git.GitComment;
import kotlinx.android.synthetic.main.pager_with_tabs.*;

/**
 * Base activity for creating comments
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0014J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH$J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\rH\u0004J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000eH\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0007H\u0014J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0012\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u0012\u0010\u001d\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/github/pockethub/android/ui/comment/CreateCommentActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "()V", "pagerHandler", "Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "Lcom/github/pockethub/android/ui/comment/CommentPreviewPagerAdapter;", "configurePager", "", "createAdapter", "createComment", "comment", "", "finish", "Lcom/meisolsson/githubsdk/model/GitHubComment;", "Lcom/meisolsson/githubsdk/model/git/GitComment;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPageChanged", "position", "", "onPostCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPrepareOptionsMenu", "app_debug"})
public abstract class CreateCommentActivity extends com.github.pockethub.android.ui.base.BaseActivity {
    private com.github.pockethub.android.ui.helpers.PagerHandler<com.github.pockethub.android.ui.comment.CommentPreviewPagerAdapter> pagerHandler;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onPostCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void configurePager() {
    }
    
    private final void onPageChanged(int position) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    /**
     * Create comment
     *
     * @param comment
     */
    protected abstract void createComment(@org.jetbrains.annotations.NotNull()
    java.lang.String comment);
    
    /**
     * Finish this activity passing back the created comment
     *
     * @param comment
     */
    protected final void finish(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.GitHubComment comment) {
    }
    
    protected final void finish(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.git.GitComment comment) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected com.github.pockethub.android.ui.comment.CommentPreviewPagerAdapter createAdapter() {
        return null;
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onPrepareOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    public CreateCommentActivity() {
        super();
    }
}
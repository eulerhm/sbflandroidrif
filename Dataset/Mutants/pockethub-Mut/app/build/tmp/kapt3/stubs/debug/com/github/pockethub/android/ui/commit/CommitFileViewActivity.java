package com.github.pockethub.android.ui.commit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.GitHubFile;
import com.meisolsson.githubsdk.model.Repository;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.commit.CommitUtils;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.util.MarkdownLoader;
import com.github.pockethub.android.util.HttpImageGetter;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.MarkdownUtils;
import com.github.pockethub.android.util.PreferenceUtils;
import com.github.pockethub.android.util.ShareUtils;
import com.github.pockethub.android.util.SourceEditor;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.git.GitBlob;
import com.meisolsson.githubsdk.service.git.GitService;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.activity_commit_file_view.*;

/**
 * Activity to display the contents of a file in a commit
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\u0012\u0010\u001c\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0013H\u0016J\b\u0010$\u001a\u00020\u001aH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/github/pockethub/android/ui/commit/CommitFileViewActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "()V", "blob", "Lcom/meisolsson/githubsdk/model/git/GitBlob;", "commit", "", "editor", "Lcom/github/pockethub/android/util/SourceEditor;", "file", "imageGetter", "Lcom/github/pockethub/android/util/HttpImageGetter;", "getImageGetter", "()Lcom/github/pockethub/android/util/HttpImageGetter;", "setImageGetter", "(Lcom/github/pockethub/android/util/HttpImageGetter;)V", "isMarkdownFile", "", "markdownItem", "Landroid/view/MenuItem;", "path", "renderedMarkdown", "repo", "Lcom/meisolsson/githubsdk/model/Repository;", "sha", "loadContent", "", "loadMarkdown", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "optionsMenu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "shareFile", "Companion", "app_debug"})
public final class CommitFileViewActivity extends com.github.pockethub.android.ui.base.BaseActivity {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.HttpImageGetter imageGetter;
    private com.meisolsson.githubsdk.model.Repository repo;
    private java.lang.String commit;
    private java.lang.String sha;
    private java.lang.String path;
    private java.lang.String file;
    private boolean isMarkdownFile;
    private java.lang.String renderedMarkdown;
    private com.meisolsson.githubsdk.model.git.GitBlob blob;
    private android.view.MenuItem markdownItem;
    private com.github.pockethub.android.util.SourceEditor editor;
    private static final java.lang.String TAG = "CommitFileViewActivity";
    private static final java.lang.String ARG_TEXT = "text";
    private static final java.lang.String ARG_REPO = "repo";
    public static final com.github.pockethub.android.ui.commit.CommitFileViewActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.HttpImageGetter getImageGetter() {
        return null;
    }
    
    public final void setImageGetter(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.HttpImageGetter p0) {
    }
    
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
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void shareFile() {
    }
    
    private final void loadMarkdown() {
    }
    
    private final void loadContent() {
    }
    
    public CommitFileViewActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/github/pockethub/android/ui/commit/CommitFileViewActivity$Companion;", "", "()V", "ARG_REPO", "", "ARG_TEXT", "TAG", "createIntent", "Landroid/content/Intent;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "commit", "file", "Lcom/meisolsson/githubsdk/model/GitHubFile;", "app_debug"})
    public static final class Companion {
        
        /**
         * Create intent to show file in commit
         *
         * @param repository
         * @param commit
         * @param file
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Repository repository, @org.jetbrains.annotations.NotNull()
        java.lang.String commit, @org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.GitHubFile file) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
package com.github.pockethub.android.ui.item.commit;

import android.content.Context;
import android.view.View;
import com.github.pockethub.android.R;
import com.meisolsson.githubsdk.model.GitHubFile;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.commit_diff_file_header.*;
import java.text.NumberFormat;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/github/pockethub/android/ui/item/commit/CommitFileHeaderItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "context", "Landroid/content/Context;", "file", "Lcom/meisolsson/githubsdk/model/GitHubFile;", "(Landroid/content/Context;Lcom/meisolsson/githubsdk/model/GitHubFile;)V", "addTextColor", "", "getFile", "()Lcom/meisolsson/githubsdk/model/GitHubFile;", "removeTextColor", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "getLayout", "app_debug"})
public final class CommitFileHeaderItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    private final int addTextColor = 0;
    private final int removeTextColor = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.GitHubFile file = null;
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.GitHubFile getFile() {
        return null;
    }
    
    public CommitFileHeaderItem(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.GitHubFile file) {
        super();
    }
}
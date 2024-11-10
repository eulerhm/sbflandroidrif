package com.github.pockethub.android.ui.item;

import android.view.View;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.comment.DeleteCommentListener;
import com.github.pockethub.android.ui.comment.EditCommentListener;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.HttpImageGetter;
import com.github.pockethub.android.util.TimeUtils;
import com.meisolsson.githubsdk.model.GitHubComment;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.comment.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/github/pockethub/android/ui/item/GitHubCommentItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "avatarLoader", "Lcom/github/pockethub/android/util/AvatarLoader;", "imageGetter", "Lcom/github/pockethub/android/util/HttpImageGetter;", "editCommentListener", "Lcom/github/pockethub/android/ui/comment/EditCommentListener;", "deleteCommentListener", "Lcom/github/pockethub/android/ui/comment/DeleteCommentListener;", "username", "", "canWrite", "", "gitHubComment", "Lcom/meisolsson/githubsdk/model/GitHubComment;", "(Lcom/github/pockethub/android/util/AvatarLoader;Lcom/github/pockethub/android/util/HttpImageGetter;Lcom/github/pockethub/android/ui/comment/EditCommentListener;Lcom/github/pockethub/android/ui/comment/DeleteCommentListener;Ljava/lang/String;ZLcom/meisolsson/githubsdk/model/GitHubComment;)V", "getGitHubComment", "()Lcom/meisolsson/githubsdk/model/GitHubComment;", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "", "getLayout", "app_debug"})
public final class GitHubCommentItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    private final com.github.pockethub.android.util.AvatarLoader avatarLoader = null;
    private final com.github.pockethub.android.util.HttpImageGetter imageGetter = null;
    private final com.github.pockethub.android.ui.comment.EditCommentListener editCommentListener = null;
    private final com.github.pockethub.android.ui.comment.DeleteCommentListener deleteCommentListener = null;
    private final java.lang.String username = null;
    private final boolean canWrite = false;
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.GitHubComment gitHubComment = null;
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.GitHubComment getGitHubComment() {
        return null;
    }
    
    public GitHubCommentItem(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader avatarLoader, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.HttpImageGetter imageGetter, @org.jetbrains.annotations.Nullable()
    com.github.pockethub.android.ui.comment.EditCommentListener editCommentListener, @org.jetbrains.annotations.Nullable()
    com.github.pockethub.android.ui.comment.DeleteCommentListener deleteCommentListener, @org.jetbrains.annotations.NotNull()
    java.lang.String username, boolean canWrite, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.GitHubComment gitHubComment) {
        super();
    }
}
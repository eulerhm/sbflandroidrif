package com.github.pockethub.android.core.commit;

import android.text.SpannedString;
import android.text.TextUtils;
import android.widget.ImageView;
import com.github.pockethub.android.util.AvatarLoader;
import com.meisolsson.githubsdk.model.Commit;
import com.meisolsson.githubsdk.model.GitHubFile;
import com.meisolsson.githubsdk.model.git.GitCommit;
import java.text.NumberFormat;
import java.util.Date;

/**
 * Utilities for working with commits
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\fH\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0007J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0007J \u0010\u0013\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0007J\u0018\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0014\u0010\u001f\u001a\u0004\u0018\u00010\t2\b\u0010 \u001a\u0004\u0018\u00010\u0018H\u0007J\u0014\u0010\u001f\u001a\u0004\u0018\u00010\t2\b\u0010!\u001a\u0004\u0018\u00010\tH\u0007J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\r\u001a\u00020\tH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/github/pockethub/android/core/commit/CommitUtils;", "", "()V", "FORMAT", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "LENGTH", "", "abbreviate", "", "commit", "Lcom/meisolsson/githubsdk/model/Commit;", "Lcom/meisolsson/githubsdk/model/git/GitCommit;", "sha", "bindAuthor", "Landroid/widget/ImageView;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "view", "bindCommitter", "formatStats", "Landroid/text/SpannedString;", "files", "", "Lcom/meisolsson/githubsdk/model/GitHubFile;", "getAuthor", "getAuthorDate", "Ljava/util/Date;", "getCommentCount", "getCommitter", "getCommitterDate", "getName", "file", "path", "isValidCommit", "", "app_debug"})
public final class CommitUtils {
    
    /**
     * Length of used for abbreviations
     */
    private static final int LENGTH = 10;
    private static final java.text.NumberFormat FORMAT = null;
    public static final com.github.pockethub.android.core.commit.CommitUtils INSTANCE = null;
    
    /**
     * Abbreviate commit sha to default length if longer
     *
     * @param commit
     * @return abbreviated sha
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String abbreviate(@org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.Commit commit) {
        return null;
    }
    
    /**
     * Abbreviate commit sha to default length if longer
     *
     * @param commit
     * @return abbreviated sha
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String abbreviate(@org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.git.GitCommit commit) {
        return null;
    }
    
    /**
     * Abbreviate sha to default length if longer
     *
     * @param sha
     * @return abbreviated sha
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String abbreviate(@org.jetbrains.annotations.Nullable()
    java.lang.String sha) {
        return null;
    }
    
    /**
     * Is the given commit SHA-1 valid?
     *
     * @param sha
     * @return true if valid, false otherwise
     */
    public static final boolean isValidCommit(@org.jetbrains.annotations.NotNull()
    java.lang.String sha) {
        return false;
    }
    
    /**
     * Get author of commit
     *
     *
     * This checks both the [Commit] and the underlying
     * [Commit] to retrieve a name
     *
     * @param commit
     * @return author name or null if missing
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getAuthor(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Commit commit) {
        return null;
    }
    
    /**
     * Get committer of commit
     *
     *
     * This checks both the [Commit] and the underlying
     * [Commit] to retrieve a name
     *
     * @param commit
     * @return committer name or null if missing
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getCommitter(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Commit commit) {
        return null;
    }
    
    /**
     * Get author date of commit
     *
     * @param commit
     * @return author name or null if missing
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.util.Date getAuthorDate(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Commit commit) {
        return null;
    }
    
    /**
     * Get committer date of commit
     *
     * @param commit
     * @return author name or null if missing
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.util.Date getCommitterDate(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Commit commit) {
        return null;
    }
    
    /**
     * Bind commit author avatar to image view
     *
     * @param commit
     * @param avatars
     * @param view
     * @return view
     */
    @org.jetbrains.annotations.NotNull()
    public static final android.widget.ImageView bindAuthor(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Commit commit, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader avatars, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView view) {
        return null;
    }
    
    /**
     * Bind commit committer avatar to image view
     *
     * @param commit
     * @param avatars
     * @param view
     * @return view
     */
    @org.jetbrains.annotations.NotNull()
    public static final android.widget.ImageView bindCommitter(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Commit commit, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader avatars, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView view) {
        return null;
    }
    
    /**
     * Get comment count
     *
     * @param commit
     * @return count
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getCommentCount(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Commit commit) {
        return null;
    }
    
    /**
     * Format stats into [SpannedString]
     *
     * @param files
     * @return styled text
     */
    @org.jetbrains.annotations.NotNull()
    public static final android.text.SpannedString formatStats(@org.jetbrains.annotations.Nullable()
    java.util.Collection<? extends com.meisolsson.githubsdk.model.GitHubFile> files) {
        return null;
    }
    
    /**
     * Get file name for commit file
     *
     * @param file
     * @return last segment of commit file path
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getName(@org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.GitHubFile file) {
        return null;
    }
    
    /**
     * Get file name for path
     *
     * @param path
     * @return last segment of path
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getName(@org.jetbrains.annotations.Nullable()
    java.lang.String path) {
        return null;
    }
    
    private CommitUtils() {
        super();
    }
}
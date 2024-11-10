package com.github.pockethub.android.core.issue;

import com.meisolsson.githubsdk.model.GitHubComment;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.IssueEvent;

/**
 * Issue model with comments
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/github/pockethub/android/core/issue/FullIssue;", "", "issue", "Lcom/meisolsson/githubsdk/model/Issue;", "comments", "", "Lcom/meisolsson/githubsdk/model/GitHubComment;", "events", "Lcom/meisolsson/githubsdk/model/IssueEvent;", "(Lcom/meisolsson/githubsdk/model/Issue;Ljava/util/Collection;Ljava/util/Collection;)V", "getComments", "()Ljava/util/Collection;", "getEvents", "getIssue", "()Lcom/meisolsson/githubsdk/model/Issue;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class FullIssue {
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.Issue issue = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Collection<com.meisolsson.githubsdk.model.GitHubComment> comments = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Collection<com.meisolsson.githubsdk.model.IssueEvent> events = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.Issue getIssue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Collection<com.meisolsson.githubsdk.model.GitHubComment> getComments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Collection<com.meisolsson.githubsdk.model.IssueEvent> getEvents() {
        return null;
    }
    
    public FullIssue(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Issue issue, @org.jetbrains.annotations.NotNull()
    java.util.Collection<? extends com.meisolsson.githubsdk.model.GitHubComment> comments, @org.jetbrains.annotations.NotNull()
    java.util.Collection<? extends com.meisolsson.githubsdk.model.IssueEvent> events) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.Issue component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Collection<com.meisolsson.githubsdk.model.GitHubComment> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Collection<com.meisolsson.githubsdk.model.IssueEvent> component3() {
        return null;
    }
    
    /**
     * Issue model with comments
     */
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.issue.FullIssue copy(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Issue issue, @org.jetbrains.annotations.NotNull()
    java.util.Collection<? extends com.meisolsson.githubsdk.model.GitHubComment> comments, @org.jetbrains.annotations.NotNull()
    java.util.Collection<? extends com.meisolsson.githubsdk.model.IssueEvent> events) {
        return null;
    }
    
    /**
     * Issue model with comments
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    /**
     * Issue model with comments
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * Issue model with comments
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}
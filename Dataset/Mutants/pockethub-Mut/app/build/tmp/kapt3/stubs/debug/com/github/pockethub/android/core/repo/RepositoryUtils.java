package com.github.pockethub.android.core.repo;

import com.meisolsson.githubsdk.model.Repository;

/**
 * Utilities for working with [Repository] objects
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/github/pockethub/android/core/repo/RepositoryUtils;", "", "()V", "isComplete", "", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "isValidOwner", "name", "", "isValidRepo", "app_debug"})
public final class RepositoryUtils {
    public static final com.github.pockethub.android.core.repo.RepositoryUtils INSTANCE = null;
    
    /**
     * Does the repository have details denoting it was loaded from an API call?
     *
     *
     * This uses a simple heuristic of either being private, being a fork, or
     * having a non-zero amount of forks or watchers, or has issues enable;
     * meaning it came back from an API call providing those details and more.
     *
     * @return true if complete, false otherwise
     */
    public static final boolean isComplete(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Repository repository) {
        return false;
    }
    
    /**
     * Is the given owner name valid?
     *
     * @param name
     * @return true if valid, false otherwise
     */
    public static final boolean isValidOwner(@org.jetbrains.annotations.Nullable()
    java.lang.String name) {
        return false;
    }
    
    /**
     * Is the given repo name valid?
     *
     * @param name
     * @return true if valid, false otherwise
     */
    public static final boolean isValidRepo(@org.jetbrains.annotations.Nullable()
    java.lang.String name) {
        return false;
    }
    
    private RepositoryUtils() {
        super();
    }
}
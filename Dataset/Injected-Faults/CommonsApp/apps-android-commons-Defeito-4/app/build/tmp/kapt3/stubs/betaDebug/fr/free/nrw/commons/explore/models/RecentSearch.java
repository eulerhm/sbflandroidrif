package fr.free.nrw.commons.explore.models;

import java.lang.System;

/**
 * Represents a recently searched query
 * Example - query = "butterfly"
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lfr/free/nrw/commons/explore/models/RecentSearch;", "", "contentUri", "Landroid/net/Uri;", "query", "", "lastSearched", "Ljava/util/Date;", "(Landroid/net/Uri;Ljava/lang/String;Ljava/util/Date;)V", "getContentUri", "()Landroid/net/Uri;", "setContentUri", "(Landroid/net/Uri;)V", "getLastSearched", "()Ljava/util/Date;", "setLastSearched", "(Ljava/util/Date;)V", "getQuery", "()Ljava/lang/String;", "app-commons-v4.2.1-main_betaDebug"})
public final class RecentSearch {
    
    /**
     * Modifies the content URI - marking this query as already saved in the database
     *
     * @param contentUri the content URI
     */
    @org.jetbrains.annotations.Nullable
    private android.net.Uri contentUri;
    
    /**
     * Gets query name
     * @return query name
     */
    @org.jetbrains.annotations.NotNull
    private final java.lang.String query = null;
    @org.jetbrains.annotations.NotNull
    private java.util.Date lastSearched;
    
    public RecentSearch(@org.jetbrains.annotations.Nullable
    android.net.Uri contentUri, @org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    java.util.Date lastSearched) {
        super();
    }
    
    /**
     * Modifies the content URI - marking this query as already saved in the database
     *
     * @param contentUri the content URI
     */
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri getContentUri() {
        return null;
    }
    
    /**
     * Modifies the content URI - marking this query as already saved in the database
     *
     * @param contentUri the content URI
     */
    public final void setContentUri(@org.jetbrains.annotations.Nullable
    android.net.Uri p0) {
    }
    
    /**
     * Gets query name
     * @return query name
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getLastSearched() {
        return null;
    }
    
    public final void setLastSearched(@org.jetbrains.annotations.NotNull
    java.util.Date p0) {
    }
}
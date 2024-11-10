package fr.free.nrw.commons.bookmarks.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r\u00a8\u0006\u000f"}, d2 = {"Lfr/free/nrw/commons/bookmarks/models/Bookmark;", "", "mediaName", "", "mediaCreator", "contentUri", "Landroid/net/Uri;", "(Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V", "getContentUri", "()Landroid/net/Uri;", "setContentUri", "(Landroid/net/Uri;)V", "getMediaCreator", "()Ljava/lang/String;", "getMediaName", "app-commons-v4.2.1-master_prodDebug"})
public final class Bookmark {
    
    /**
     * Modifies the content URI - marking this bookmark as already saved in the database
     * @param contentUri the content URI
     */
    @org.jetbrains.annotations.Nullable
    private android.net.Uri contentUri;
    
    /**
     * Gets the media name
     * @return the media name
     */
    @org.jetbrains.annotations.NotNull
    private final java.lang.String mediaName = null;
    
    /**
     * Gets media creator
     * @return creator name
     */
    @org.jetbrains.annotations.NotNull
    private final java.lang.String mediaCreator = null;
    
    public Bookmark(@org.jetbrains.annotations.Nullable
    java.lang.String mediaName, @org.jetbrains.annotations.Nullable
    java.lang.String mediaCreator, @org.jetbrains.annotations.Nullable
    android.net.Uri contentUri) {
        super();
    }
    
    /**
     * Modifies the content URI - marking this bookmark as already saved in the database
     * @param contentUri the content URI
     */
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri getContentUri() {
        return null;
    }
    
    /**
     * Modifies the content URI - marking this bookmark as already saved in the database
     * @param contentUri the content URI
     */
    public final void setContentUri(@org.jetbrains.annotations.Nullable
    android.net.Uri p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMediaName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMediaCreator() {
        return null;
    }
}
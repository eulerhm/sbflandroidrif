package com.github.pockethub.android.persistence;

import android.util.Log;
import com.github.pockethub.android.Database;
import java.io.IOException;
import javax.inject.Inject;

/**
 * Given a PersistableResource, this class will take support loading/storing
 * it's data or requesting fresh data, as appropriate.
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J$\u0010\t\u001a\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\n\"\u0004\b\u0000\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0002J \u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rJ \u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rR\u001e\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0011"}, d2 = {"Lcom/github/pockethub/android/persistence/DatabaseCache;", "", "()V", "database", "Lcom/github/pockethub/android/Database;", "getDatabase", "()Lcom/github/pockethub/android/Database;", "setDatabase", "(Lcom/github/pockethub/android/Database;)V", "loadFromDB", "", "E", "persistableResource", "Lcom/github/pockethub/android/persistence/PersistableResource;", "loadOrRequest", "requestAndStore", "Companion", "app_debug"})
public class DatabaseCache {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.Database database;
    private static final java.lang.String TAG = "DatabaseCache";
    public static final com.github.pockethub.android.persistence.DatabaseCache.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.Database getDatabase() {
        return null;
    }
    
    protected final void setDatabase(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.Database p0) {
    }
    
    /**
     * Load or request given resources
     *
     * @param persistableResource
     * @return resource
     * @throws IOException
     */
    @org.jetbrains.annotations.NotNull()
    public final <E extends java.lang.Object>java.util.List<E> loadOrRequest(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.persistence.PersistableResource<E> persistableResource) {
        return null;
    }
    
    /**
     * Request and store given resources
     *
     * @param persistableResource
     * @return resources
     * @throws IOException
     */
    @org.jetbrains.annotations.NotNull()
    public final <E extends java.lang.Object>java.util.List<E> requestAndStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.persistence.PersistableResource<E> persistableResource) {
        return null;
    }
    
    private final <E extends java.lang.Object>java.util.List<E> loadFromDB(com.github.pockethub.android.persistence.PersistableResource<E> persistableResource) {
        return null;
    }
    
    @javax.inject.Inject()
    public DatabaseCache() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/github/pockethub/android/persistence/DatabaseCache$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
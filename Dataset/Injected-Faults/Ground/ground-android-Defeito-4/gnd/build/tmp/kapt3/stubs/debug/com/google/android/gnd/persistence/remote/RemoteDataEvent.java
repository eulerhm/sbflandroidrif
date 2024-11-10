package com.google.android.gnd.persistence.remote;

import java.lang.System;

/**
 * An event returned by the remote data store indicating either an entity was successfully loaded,
 * or that it was modified or removed in the remote data store.
 *
 * @param <T> the type of entity being loaded, modified, or removed.
 *
 * TODO: Replace with kotlin.Result once migration to Kotlin is complete. For more info, see this
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\b\u0018\u0000 \u000f*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u000f\u0010B+\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lcom/google/android/gnd/persistence/remote/RemoteDataEvent;", "T", "Lcom/google/android/gnd/rx/ValueOrError;", "entityId", "", "eventType", "Lcom/google/android/gnd/persistence/remote/RemoteDataEvent$EventType;", "entity", "error", "", "(Ljava/lang/String;Lcom/google/android/gnd/persistence/remote/RemoteDataEvent$EventType;Ljava/lang/Object;Ljava/lang/Throwable;)V", "getEntityId", "()Ljava/lang/String;", "getEventType", "()Lcom/google/android/gnd/persistence/remote/RemoteDataEvent$EventType;", "Companion", "EventType", "gnd_debug"})
public final class RemoteDataEvent<T extends java.lang.Object> extends com.google.android.gnd.rx.ValueOrError<T> {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String entityId = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gnd.persistence.remote.RemoteDataEvent.EventType eventType = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.persistence.remote.RemoteDataEvent.Companion Companion = null;
    
    private RemoteDataEvent(java.lang.String entityId, com.google.android.gnd.persistence.remote.RemoteDataEvent.EventType eventType, T entity, java.lang.Throwable error) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEntityId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.persistence.remote.RemoteDataEvent.EventType getEventType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.persistence.remote.RemoteDataEvent<T> loaded(@org.jetbrains.annotations.NotNull()
    java.lang.String entityId, T entity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.persistence.remote.RemoteDataEvent<T> modified(@org.jetbrains.annotations.NotNull()
    java.lang.String entityId, T entity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.persistence.remote.RemoteDataEvent<T> removed(@org.jetbrains.annotations.NotNull()
    java.lang.String entityId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.persistence.remote.RemoteDataEvent<T> error(@org.jetbrains.annotations.Nullable()
    java.lang.Throwable error) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/google/android/gnd/persistence/remote/RemoteDataEvent$EventType;", "", "(Ljava/lang/String;I)V", "ENTITY_LOADED", "ENTITY_MODIFIED", "ENTITY_REMOVED", "ERROR", "gnd_debug"})
    public static enum EventType {
        /*public static final*/ ENTITY_LOADED /* = new ENTITY_LOADED() */,
        /*public static final*/ ENTITY_MODIFIED /* = new ENTITY_MODIFIED() */,
        /*public static final*/ ENTITY_REMOVED /* = new ENTITY_REMOVED() */,
        /*public static final*/ ERROR /* = new ERROR() */;
        
        EventType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\u0003\u001a\u0004\u0018\u00010\u0006H\u0007J)\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u0002H\u0005H\u0007\u00a2\u0006\u0002\u0010\u000bJ)\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u0002H\u0005H\u0007\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\b\u001a\u00020\tH\u0007\u00a8\u0006\u000e"}, d2 = {"Lcom/google/android/gnd/persistence/remote/RemoteDataEvent$Companion;", "", "()V", "error", "Lcom/google/android/gnd/persistence/remote/RemoteDataEvent;", "T", "", "loaded", "entityId", "", "entity", "(Ljava/lang/String;Ljava/lang/Object;)Lcom/google/android/gnd/persistence/remote/RemoteDataEvent;", "modified", "removed", "gnd_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.persistence.remote.RemoteDataEvent<T> loaded(@org.jetbrains.annotations.NotNull()
        java.lang.String entityId, T entity) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.persistence.remote.RemoteDataEvent<T> modified(@org.jetbrains.annotations.NotNull()
        java.lang.String entityId, T entity) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.persistence.remote.RemoteDataEvent<T> removed(@org.jetbrains.annotations.NotNull()
        java.lang.String entityId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.persistence.remote.RemoteDataEvent<T> error(@org.jetbrains.annotations.Nullable()
        java.lang.Throwable error) {
            return null;
        }
    }
}
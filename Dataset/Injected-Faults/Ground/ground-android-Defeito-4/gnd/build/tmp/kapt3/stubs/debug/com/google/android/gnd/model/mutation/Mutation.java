package com.google.android.gnd.model.mutation;

import java.lang.System;

/**
 * Represents a mutation that can be applied to local data and queued for sync with the remote data
 * store.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 &2\u00020\u0001:\u0004%&\'(B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\"\u001a\n\u0012\u0002\b\u00030#R\u00020\u0000H&J\b\u0010$\u001a\u00020\bH\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u0004\u0018\u00010\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\nR\u0012\u0010\u0011\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\nR\u0012\u0010\u0013\u001a\u00020\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\nR\u0012\u0010\u0018\u001a\u00020\u0019X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u00020\u001dX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0012\u0010 \u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\n\u0082\u0001\u0002)*\u00a8\u0006+"}, d2 = {"Lcom/google/android/gnd/model/mutation/Mutation;", "", "()V", "clientTimestamp", "Ljava/util/Date;", "getClientTimestamp", "()Ljava/util/Date;", "featureId", "", "getFeatureId", "()Ljava/lang/String;", "id", "", "getId", "()Ljava/lang/Long;", "jobId", "getJobId", "lastError", "getLastError", "retryCount", "getRetryCount", "()J", "surveyId", "getSurveyId", "syncStatus", "Lcom/google/android/gnd/model/mutation/Mutation$SyncStatus;", "getSyncStatus", "()Lcom/google/android/gnd/model/mutation/Mutation$SyncStatus;", "type", "Lcom/google/android/gnd/model/mutation/Mutation$Type;", "getType", "()Lcom/google/android/gnd/model/mutation/Mutation$Type;", "userId", "getUserId", "toBuilder", "Lcom/google/android/gnd/model/mutation/Mutation$Builder;", "toString", "Builder", "Companion", "SyncStatus", "Type", "Lcom/google/android/gnd/model/mutation/FeatureMutation;", "Lcom/google/android/gnd/model/mutation/SubmissionMutation;", "gnd_debug"})
public abstract class Mutation {
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.model.mutation.Mutation.Companion Companion = null;
    
    private Mutation() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getId();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.android.gnd.model.mutation.Mutation.Type getType();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.android.gnd.model.mutation.Mutation.SyncStatus getSyncStatus();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getSurveyId();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getFeatureId();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getJobId();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getUserId();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Date getClientTimestamp();
    
    public abstract long getRetryCount();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getLastError();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.android.gnd.model.mutation.Mutation.Builder<?> toBuilder();
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final java.util.Comparator<com.google.android.gnd.model.mutation.Mutation> byDescendingClientTimestamp() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/google/android/gnd/model/mutation/Mutation$Type;", "", "(Ljava/lang/String;I)V", "CREATE", "UPDATE", "DELETE", "UNKNOWN", "gnd_debug"})
    public static enum Type {
        /*public static final*/ CREATE /* = new CREATE() */,
        /*public static final*/ UPDATE /* = new UPDATE() */,
        /*public static final*/ DELETE /* = new DELETE() */,
        /*public static final*/ UNKNOWN /* = new UNKNOWN() */;
        
        Type() {
        }
    }
    
    /**
     * Status of mutation being applied to remote data store.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/google/android/gnd/model/mutation/Mutation$SyncStatus;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "PENDING", "IN_PROGRESS", "COMPLETED", "FAILED", "gnd_debug"})
    public static enum SyncStatus {
        /*public static final*/ UNKNOWN /* = new UNKNOWN() */,
        /*public static final*/ PENDING /* = new PENDING() */,
        /*public static final*/ IN_PROGRESS /* = new IN_PROGRESS() */,
        /*public static final*/ COMPLETED /* = new COMPLETED() */,
        /*public static final*/ FAILED /* = new FAILED() */;
        
        SyncStatus() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u00a6\u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\r\u00106\u001a\u00028\u0000H&\u00a2\u0006\u0002\u00107J\u001d\u00108\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u00109\u001a\u00028\u0000\u00a2\u0006\u0002\u0010:J\u0018\u0010\n\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u0010;\u001a\u00020\u0006J\u0018\u0010\u0010\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u0010\r\u001a\u00020\fJ\u001f\u0010\u0016\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0002\u0010<J\u0018\u0010\u001b\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u0010\u0019\u001a\u00020\fJ\u0018\u0010\u001e\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u0010=\u001a\u00020\fJ\u0018\u0010\"\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u0010>\u001a\u00020\u0012J\u0018\u0010&\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u0010$\u001a\u00020\fJ\u0018\u0010+\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u0010(\u001a\u00020\'J\u0018\u00101\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u0010.\u001a\u00020-J\u0018\u00105\u001a\f\u0012\u0004\u0012\u00028\u00000\u0000R\u00020\u00022\u0006\u00103\u001a\u00020\fR$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R*\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0005\u001a\u0004\u0018\u00010\u0012@FX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R$\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011R$\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0012@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010$\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R$\u0010(\u001a\u00020\'2\u0006\u0010\u0005\u001a\u00020\'@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010.\u001a\u00020-2\u0006\u0010\u0005\u001a\u00020-@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R$\u00103\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000f\"\u0004\b5\u0010\u0011\u00a8\u0006?"}, d2 = {"Lcom/google/android/gnd/model/mutation/Mutation$Builder;", "T", "Lcom/google/android/gnd/model/mutation/Mutation;", "", "(Lcom/google/android/gnd/model/mutation/Mutation;)V", "<set-?>", "Ljava/util/Date;", "clientTimestamp", "getClientTimestamp", "()Ljava/util/Date;", "setClientTimestamp", "(Ljava/util/Date;)V", "", "featureId", "getFeatureId", "()Ljava/lang/String;", "setFeatureId", "(Ljava/lang/String;)V", "", "id", "getId", "()Ljava/lang/Long;", "setId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "jobId", "getJobId", "setJobId", "lastError", "getLastError", "setLastError", "retryCount", "getRetryCount", "()J", "setRetryCount", "(J)V", "surveyId", "getSurveyId", "setSurveyId", "Lcom/google/android/gnd/model/mutation/Mutation$SyncStatus;", "syncStatus", "getSyncStatus", "()Lcom/google/android/gnd/model/mutation/Mutation$SyncStatus;", "setSyncStatus", "(Lcom/google/android/gnd/model/mutation/Mutation$SyncStatus;)V", "Lcom/google/android/gnd/model/mutation/Mutation$Type;", "type", "getType", "()Lcom/google/android/gnd/model/mutation/Mutation$Type;", "setType", "(Lcom/google/android/gnd/model/mutation/Mutation$Type;)V", "userId", "getUserId", "setUserId", "build", "()Lcom/google/android/gnd/model/mutation/Mutation;", "fromMutation", "mutation", "(Lcom/google/android/gnd/model/mutation/Mutation;)Lcom/google/android/gnd/model/mutation/Mutation$Builder;", "timestamp", "(Ljava/lang/Long;)Lcom/google/android/gnd/model/mutation/Mutation$Builder;", "error", "count", "gnd_debug"})
    public abstract class Builder<T extends com.google.android.gnd.model.mutation.Mutation> {
        @org.jetbrains.annotations.Nullable()
        private java.lang.Long id;
        @org.jetbrains.annotations.NotNull()
        private com.google.android.gnd.model.mutation.Mutation.Type type = com.google.android.gnd.model.mutation.Mutation.Type.UNKNOWN;
        @org.jetbrains.annotations.NotNull()
        private com.google.android.gnd.model.mutation.Mutation.SyncStatus syncStatus = com.google.android.gnd.model.mutation.Mutation.SyncStatus.UNKNOWN;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String surveyId = "";
        @org.jetbrains.annotations.NotNull()
        private java.lang.String featureId = "";
        @org.jetbrains.annotations.NotNull()
        private java.lang.String jobId = "";
        @org.jetbrains.annotations.NotNull()
        private java.lang.String userId = "";
        @org.jetbrains.annotations.NotNull()
        private java.util.Date clientTimestamp;
        private long retryCount = 0L;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String lastError = "";
        
        public Builder() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Type getType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.SyncStatus getSyncStatus() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSurveyId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFeatureId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getJobId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date getClientTimestamp() {
            return null;
        }
        
        public final long getRetryCount() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLastError() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setId(@org.jetbrains.annotations.Nullable()
        java.lang.Long id) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setType(@org.jetbrains.annotations.NotNull()
        com.google.android.gnd.model.mutation.Mutation.Type type) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setSyncStatus(@org.jetbrains.annotations.NotNull()
        com.google.android.gnd.model.mutation.Mutation.SyncStatus syncStatus) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setSurveyId(@org.jetbrains.annotations.NotNull()
        java.lang.String surveyId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setFeatureId(@org.jetbrains.annotations.NotNull()
        java.lang.String featureId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setJobId(@org.jetbrains.annotations.NotNull()
        java.lang.String jobId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setUserId(@org.jetbrains.annotations.NotNull()
        java.lang.String userId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setClientTimestamp(@org.jetbrains.annotations.NotNull()
        java.util.Date timestamp) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setRetryCount(long count) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> setLastError(@org.jetbrains.annotations.NotNull()
        java.lang.String error) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.gnd.model.mutation.Mutation.Builder<T> fromMutation(@org.jetbrains.annotations.NotNull()
        T mutation) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public abstract T build();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007\u00a8\u0006\u0006"}, d2 = {"Lcom/google/android/gnd/model/mutation/Mutation$Companion;", "", "()V", "byDescendingClientTimestamp", "Ljava/util/Comparator;", "Lcom/google/android/gnd/model/mutation/Mutation;", "gnd_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final java.util.Comparator<com.google.android.gnd.model.mutation.Mutation> byDescendingClientTimestamp() {
            return null;
        }
    }
}
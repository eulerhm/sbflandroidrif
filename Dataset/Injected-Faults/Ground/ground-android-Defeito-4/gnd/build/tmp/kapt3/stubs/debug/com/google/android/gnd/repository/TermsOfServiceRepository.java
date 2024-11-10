package com.google.android.gnd.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0002\b\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/google/android/gnd/repository/TermsOfServiceRepository;", "", "remoteDataStore", "Lcom/google/android/gnd/persistence/remote/RemoteDataStore;", "localValueStore", "Lcom/google/android/gnd/persistence/local/LocalValueStore;", "(Lcom/google/android/gnd/persistence/remote/RemoteDataStore;Lcom/google/android/gnd/persistence/local/LocalValueStore;)V", "value", "", "isTermsOfServiceAccepted", "()Z", "setTermsOfServiceAccepted", "(Z)V", "termsOfService", "Lio/reactivex/Maybe;", "Lcom/google/android/gnd/model/TermsOfService;", "Lcom/google/android/gnd/rx/annotations/Cold;", "getTermsOfService", "()Lio/reactivex/Maybe;", "gnd_debug"})
public final class TermsOfServiceRepository {
    private final com.google.android.gnd.persistence.remote.RemoteDataStore remoteDataStore = null;
    private final com.google.android.gnd.persistence.local.LocalValueStore localValueStore = null;
    
    @javax.inject.Inject()
    public TermsOfServiceRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.remote.RemoteDataStore remoteDataStore, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.local.LocalValueStore localValueStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Maybe<com.google.android.gnd.model.TermsOfService> getTermsOfService() {
        return null;
    }
    
    public final boolean isTermsOfServiceAccepted() {
        return false;
    }
    
    public final void setTermsOfServiceAccepted(boolean value) {
    }
}
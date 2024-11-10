package com.google.android.gnd.repository;

import java.lang.System;

/**
 * Coordinates persistence and retrieval of map type from local value store.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/google/android/gnd/repository/MapsRepository;", "", "localValueStore", "Lcom/google/android/gnd/persistence/local/LocalValueStore;", "(Lcom/google/android/gnd/persistence/local/LocalValueStore;)V", "value", "", "mapType", "getMapType", "()I", "setMapType", "(I)V", "mutableMapType", "Landroidx/lifecycle/MutableLiveData;", "observableMapType", "Landroidx/lifecycle/LiveData;", "gnd_debug"})
@javax.inject.Singleton()
public final class MapsRepository {
    private final com.google.android.gnd.persistence.local.LocalValueStore localValueStore = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> mutableMapType = null;
    
    @javax.inject.Inject()
    public MapsRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.local.LocalValueStore localValueStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> observableMapType() {
        return null;
    }
    
    public final int getMapType() {
        return 0;
    }
    
    public final void setMapType(int value) {
    }
}
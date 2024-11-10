package com.google.android.gnd.ui.common;

import java.lang.System;

/**
 * Injects a [MapFragment] in the container with id "map".
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H$J\u001a\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0010"}, d2 = {"Lcom/google/android/gnd/ui/common/AbstractMapViewerFragment;", "Lcom/google/android/gnd/ui/common/AbstractFragment;", "()V", "mapFragment", "Lcom/google/android/gnd/ui/map/MapFragment;", "getMapFragment", "()Lcom/google/android/gnd/ui/map/MapFragment;", "setMapFragment", "(Lcom/google/android/gnd/ui/map/MapFragment;)V", "onMapReady", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "gnd_debug"})
public abstract class AbstractMapViewerFragment extends com.google.android.gnd.ui.common.AbstractFragment {
    @javax.inject.Inject()
    public com.google.android.gnd.ui.map.MapFragment mapFragment;
    
    public AbstractMapViewerFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.map.MapFragment getMapFragment() {
        return null;
    }
    
    public final void setMapFragment(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.map.MapFragment p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    protected abstract void onMapReady(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.map.MapFragment mapFragment);
}
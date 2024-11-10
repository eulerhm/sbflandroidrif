package com.google.android.gnd.ui.home.mapcontainer;

import java.lang.System;

/**
 * Dialog fragment containing a list of [MapType] for updating basemap layer.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0017J\u001a\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u001d"}, d2 = {"Lcom/google/android/gnd/ui/home/mapcontainer/MapTypeDialogFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "binding", "Lcom/google/android/gnd/databinding/MapTypeDialogFragmentBinding;", "mapTypes", "", "Lcom/google/android/gnd/ui/map/MapType;", "[Lcom/google/android/gnd/ui/map/MapType;", "mapsRepository", "Lcom/google/android/gnd/repository/MapsRepository;", "getMapsRepository", "()Lcom/google/android/gnd/repository/MapsRepository;", "setMapsRepository", "(Lcom/google/android/gnd/repository/MapsRepository;)V", "handleMapTypeSelected", "", "position", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "gnd_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class MapTypeDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    @javax.inject.Inject()
    public com.google.android.gnd.repository.MapsRepository mapsRepository;
    private com.google.android.gnd.databinding.MapTypeDialogFragmentBinding binding;
    private com.google.android.gnd.ui.map.MapType[] mapTypes;
    
    public MapTypeDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.repository.MapsRepository getMapsRepository() {
        return null;
    }
    
    public final void setMapsRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.MapsRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @android.annotation.SuppressLint(value = {"UseRequireInsteadOfGet"})
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void handleMapTypeSelected(int position) {
    }
}
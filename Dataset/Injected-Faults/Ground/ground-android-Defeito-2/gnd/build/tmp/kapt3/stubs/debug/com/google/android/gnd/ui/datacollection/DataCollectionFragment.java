package com.google.android.gnd.ui.datacollection;

import java.lang.System;

/**
 * Fragment allowing the user to collect data to complete a task.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/google/android/gnd/ui/datacollection/DataCollectionFragment;", "Lcom/google/android/gnd/ui/common/AbstractFragment;", "()V", "featureHelper", "Lcom/google/android/gnd/ui/common/FeatureHelper;", "getFeatureHelper", "()Lcom/google/android/gnd/ui/common/FeatureHelper;", "setFeatureHelper", "(Lcom/google/android/gnd/ui/common/FeatureHelper;)V", "navigator", "Lcom/google/android/gnd/ui/common/Navigator;", "getNavigator", "()Lcom/google/android/gnd/ui/common/Navigator;", "setNavigator", "(Lcom/google/android/gnd/ui/common/Navigator;)V", "viewModel", "Lcom/google/android/gnd/ui/datacollection/DataCollectionViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "gnd_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class DataCollectionFragment extends com.google.android.gnd.ui.common.AbstractFragment {
    @javax.inject.Inject()
    public com.google.android.gnd.ui.common.Navigator navigator;
    @javax.inject.Inject()
    public com.google.android.gnd.ui.common.FeatureHelper featureHelper;
    private com.google.android.gnd.ui.datacollection.DataCollectionViewModel viewModel;
    
    public DataCollectionFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.common.Navigator getNavigator() {
        return null;
    }
    
    public final void setNavigator(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.Navigator p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.common.FeatureHelper getFeatureHelper() {
        return null;
    }
    
    public final void setFeatureHelper(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.FeatureHelper p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
}
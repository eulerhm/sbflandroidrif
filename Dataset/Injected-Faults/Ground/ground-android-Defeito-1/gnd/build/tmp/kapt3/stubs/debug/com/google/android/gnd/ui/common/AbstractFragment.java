package com.google.android.gnd.ui.common;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0004\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J&\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\b\u0010\u001e\u001a\u00020\u0010H\u0016J\b\u0010\u001f\u001a\u00020\u0010H\u0016J\b\u0010 \u001a\u00020\u0010H\u0016J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0015H\u0016J\b\u0010#\u001a\u00020\u0010H\u0016J\b\u0010$\u001a\u00020\u0010H\u0016J\u001a\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010\'\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u001a\u0010(\u001a\u00020\u00102\b\b\u0001\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001J)\u0010,\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0004\u00a2\u0006\u0002\u0010.J#\u0010,\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010/\u001a\u000200H\u0002\u00a2\u0006\u0002\u00101J&\u00102\u001a\u00020\u00102\b\u0010\"\u001a\u0004\u0018\u00010\u00152\b\u0010+\u001a\u0004\u0018\u00010\u00012\b\u0010/\u001a\u0004\u0018\u000100H\u0004R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u00063"}, d2 = {"Lcom/google/android/gnd/ui/common/AbstractFragment;", "Landroidx/fragment/app/Fragment;", "()V", "viewModelFactory", "Lcom/google/android/gnd/ui/common/ViewModelFactory;", "getViewModelFactory", "()Lcom/google/android/gnd/ui/common/ViewModelFactory;", "setViewModelFactory", "(Lcom/google/android/gnd/ui/common/ViewModelFactory;)V", "getViewModel", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onDetach", "onPause", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "onViewCreated", "view", "onViewStateRestored", "replaceFragment", "containerViewId", "", "fragment", "restoreChildFragment", "fragmentClass", "(Landroid/os/Bundle;Ljava/lang/Class;)Ljava/lang/Object;", "key", "", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Object;", "saveChildFragment", "gnd_debug"})
public abstract class AbstractFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public com.google.android.gnd.ui.common.ViewModelFactory viewModelFactory;
    
    public AbstractFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.common.ViewModelFactory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.ViewModelFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final <T extends androidx.lifecycle.ViewModel>T getViewModel(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewStateRestored(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    public final void replaceFragment(@androidx.annotation.IdRes()
    int containerViewId, @org.jetbrains.annotations.Nullable()
    androidx.fragment.app.Fragment fragment) {
    }
    
    protected final void saveChildFragment(@org.jetbrains.annotations.Nullable()
    android.os.Bundle outState, @org.jetbrains.annotations.Nullable()
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.Nullable()
    java.lang.String key) {
    }
    
    private final <T extends java.lang.Object>T restoreChildFragment(android.os.Bundle savedInstanceState, java.lang.String key) {
        return null;
    }
    
    protected final <T extends java.lang.Object>T restoreChildFragment(@org.jetbrains.annotations.NotNull()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.NotNull()
    java.lang.Class<T> fragmentClass) {
        return null;
    }
}
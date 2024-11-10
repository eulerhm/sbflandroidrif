package com.google.android.gnd.ui.common;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u000f\u001a\u0002H\u0010\"\n\b\u0000\u0010\u0010*\u0004\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0013H\u0004\u00a2\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\u0004R\u0014\u0010\u0005\u001a\u00020\u00068DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/google/android/gnd/ui/common/AbstractView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activity", "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "viewModelFactory", "Lcom/google/android/gnd/ui/common/ViewModelFactory;", "getViewModelFactory", "()Lcom/google/android/gnd/ui/common/ViewModelFactory;", "setViewModelFactory", "(Lcom/google/android/gnd/ui/common/ViewModelFactory;)V", "getViewModel", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "inflate", "Landroidx/databinding/ViewDataBinding;", "layoutId", "", "gnd_debug"})
public abstract class AbstractView extends android.widget.FrameLayout {
    @javax.inject.Inject()
    public com.google.android.gnd.ui.common.ViewModelFactory viewModelFactory;
    
    public AbstractView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.common.ViewModelFactory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.ViewModelFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final androidx.fragment.app.FragmentActivity getActivity() {
        return null;
    }
    
    protected final <T extends androidx.lifecycle.ViewModel>T getViewModel(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final androidx.databinding.ViewDataBinding inflate(@androidx.annotation.LayoutRes()
    int layoutId) {
        return null;
    }
}
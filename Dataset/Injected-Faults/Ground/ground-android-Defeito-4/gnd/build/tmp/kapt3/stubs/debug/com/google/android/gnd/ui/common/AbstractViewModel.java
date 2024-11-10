package com.google.android.gnd.ui.common;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0004J\b\u0010\t\u001a\u00020\nH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/google/android/gnd/ui/common/AbstractViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "disposeOnClear", "", "subscription", "Lio/reactivex/disposables/Disposable;", "onCleared", "", "gnd_debug"})
public abstract class AbstractViewModel extends androidx.lifecycle.ViewModel {
    private final io.reactivex.disposables.CompositeDisposable disposables = null;
    
    public AbstractViewModel() {
        super();
    }
    
    protected final boolean disposeOnClear(@org.jetbrains.annotations.NotNull()
    io.reactivex.disposables.Disposable subscription) {
        return false;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}
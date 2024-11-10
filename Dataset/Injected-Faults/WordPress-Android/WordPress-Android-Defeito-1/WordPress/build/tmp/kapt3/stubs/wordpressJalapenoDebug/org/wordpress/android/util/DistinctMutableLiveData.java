package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0006\u001a\u00020\u0007J\r\u0010\b\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\tJ\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0004J\u0015\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0004R\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0005\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/util/DistinctMutableLiveData;", "T", "Landroidx/lifecycle/MutableLiveData;", "defaultValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "clear", "", "getValue", "()Ljava/lang/Object;", "postValue", "value", "setValue", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DistinctMutableLiveData<T extends java.lang.Object> extends androidx.lifecycle.MutableLiveData<T> {
    private final T defaultValue = null;
    
    public DistinctMutableLiveData(T defaultValue) {
        super(null);
    }
    
    @java.lang.Override()
    public void postValue(T value) {
    }
    
    @java.lang.Override()
    public void setValue(T value) {
    }
    
    @java.lang.Override()
    public T getValue() {
        return null;
    }
    
    public final void clear() {
    }
}
package fr.free.nrw.commons.upload.categories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002Bg\u0012*\u0010\u0003\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u00050\u0004\"\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\b\u0012\u001a\b\u0002\u0010\n\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\u000bJ\u0013\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006J\u0006\u0010\u0016\u001a\u00020\u0011J\u0013\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0013R\"\u0010\f\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00018\u00008\u00000\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0018"}, d2 = {"Lfr/free/nrw/commons/upload/categories/BaseDelegateAdapter;", "T", "Lcom/hannesdorfmann/adapterdelegates4/AsyncListDifferDelegationAdapter;", "delegates", "", "Lcom/hannesdorfmann/adapterdelegates4/AdapterDelegate;", "", "areItemsTheSame", "Lkotlin/Function2;", "", "areContentsTheSame", "([Lcom/hannesdorfmann/adapterdelegates4/AdapterDelegate;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "itemsOrEmpty", "kotlin.jvm.PlatformType", "getItemsOrEmpty", "()Ljava/util/List;", "add", "", "item", "(Ljava/lang/Object;)V", "addAll", "newResults", "clear", "remove", "app-commons-v4.2.1-main_betaDebug"})
public abstract class BaseDelegateAdapter<T extends java.lang.Object> extends com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter<T> {
    
    public BaseDelegateAdapter(@org.jetbrains.annotations.NotNull
    com.hannesdorfmann.adapterdelegates4.AdapterDelegate<java.util.List<T>>[] delegates, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super T, ? super T, java.lang.Boolean> areItemsTheSame, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super T, ? super T, java.lang.Boolean> areContentsTheSame) {
        super(null);
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull
    java.util.List<? extends T> newResults) {
    }
    
    public final void clear() {
    }
    
    public final void add(T item) {
    }
    
    public final void remove(T item) {
    }
    
    private final java.util.List<T> getItemsOrEmpty() {
        return null;
    }
}
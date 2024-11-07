package com.github.pockethub.android.ui.helpers;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.github.pockethub.android.R;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.xwray.groupie.Item;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\r\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0089\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n\u0012-\u0010\r\u001a)\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00130\u00120\n\u0012%\u0010\u0014\u001a!\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\n\u00a2\u0006\u0002\u0010\u0017J\u0006\u0010!\u001a\u00020\fJ\u0010\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u000bH\u0002J\u001a\u0010$\u001a\u00020\f2\u0010\u0010%\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u001cH\u0002J\b\u0010&\u001a\u00020\fH\u0003J\b\u0010\'\u001a\u00020\fH\u0003J\u0006\u0010(\u001a\u00020\fJ\u0010\u0010(\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000eH\u0002R-\u0010\u0014\u001a!\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R5\u0010\r\u001a)\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00130\u00120\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R:\u0010\u001b\u001a\"\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u001c\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u001c0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/github/pockethub/android/ui/helpers/ListFetcher;", "E", "Landroidx/lifecycle/LifecycleObserver;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "showError", "Lkotlin/Function1;", "", "", "loadData", "", "Lkotlin/ParameterName;", "name", "force", "Lio/reactivex/Single;", "", "createItem", "item", "Lcom/xwray/groupie/Item;", "(Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;Landroidx/lifecycle/Lifecycle;Lcom/github/pockethub/android/ui/helpers/ItemListHandler;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "dataLoadDisposable", "Lio/reactivex/disposables/Disposable;", "isLoading", "onDataLoaded", "", "getOnDataLoaded", "()Lkotlin/jvm/functions/Function1;", "setOnDataLoaded", "(Lkotlin/jvm/functions/Function1;)V", "forceRefresh", "onDataLoadError", "throwable", "onLoadFinished", "newItems", "onPause", "onStart", "refresh", "app_debug"})
public final class ListFetcher<E extends java.lang.Object> implements androidx.lifecycle.LifecycleObserver {
    
    /**
     * Disposable for data load request.
     */
    private io.reactivex.disposables.Disposable dataLoadDisposable;
    private boolean isLoading;
    @org.jetbrains.annotations.NotNull()
    private kotlin.jvm.functions.Function1<? super java.util.List<com.xwray.groupie.Item<?>>, ? extends java.util.List<com.xwray.groupie.Item<?>>> onDataLoaded;
    private final androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayout = null;
    private final androidx.lifecycle.Lifecycle lifecycle = null;
    private final com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler = null;
    private final kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> showError = null;
    private final kotlin.jvm.functions.Function1<java.lang.Boolean, io.reactivex.Single<java.util.List<E>>> loadData = null;
    private final kotlin.jvm.functions.Function1<E, com.xwray.groupie.Item<?>> createItem = null;
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.util.List<com.xwray.groupie.Item<?>>, java.util.List<com.xwray.groupie.Item<?>>> getOnDataLoaded() {
        return null;
    }
    
    public final void setOnDataLoaded(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.xwray.groupie.Item<?>>, ? extends java.util.List<com.xwray.groupie.Item<?>>> p0) {
    }
    
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_START)
    private final void onStart() {
    }
    
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_PAUSE)
    private final void onPause() {
    }
    
    private final void refresh(boolean force) {
    }
    
    public final void refresh() {
    }
    
    public final void forceRefresh() {
    }
    
    /**
     * Called when the data has loaded.
     *
     * @param newItems The items added to the list.
     */
    private final void onLoadFinished(java.util.List<com.xwray.groupie.Item<?>> newItems) {
    }
    
    private final void onDataLoadError(java.lang.Throwable throwable) {
    }
    
    public ListFetcher(@org.jetbrains.annotations.Nullable()
    androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayout, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Lifecycle lifecycle, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> showError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, ? extends io.reactivex.Single<java.util.List<E>>> loadData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super E, ? extends com.xwray.groupie.Item<?>> createItem) {
        super();
    }
}
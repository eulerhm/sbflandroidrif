package com.github.pockethub.android.ui.helpers;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.github.pockethub.android.R;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.meisolsson.githubsdk.model.Page;
import com.xwray.groupie.Item;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u000e\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u008f\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n\u00123\u0010\r\u001a/\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u00130\u00120\n\u0012%\u0010\u0015\u001a!\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\n\u00a2\u0006\u0002\u0010\u0018J\u0006\u0010(\u001a\u00020\fJ\b\u0010)\u001a\u00020\fH\u0002J\u0010\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\u000bH\u0002J\u001a\u0010,\u001a\u00020\f2\u0010\u0010-\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170#H\u0002J\b\u0010.\u001a\u00020\fH\u0003J\b\u0010/\u001a\u00020\fH\u0003J\u0006\u00100\u001a\u00020\fR-\u0010\u0015\u001a!\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R;\u0010\r\u001a/\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u00130\u00120\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R:\u0010\"\u001a\"\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170#\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170#0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "E", "Landroidx/lifecycle/LifecycleObserver;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "showError", "Lkotlin/Function1;", "", "", "loadPage", "", "Lkotlin/ParameterName;", "name", "page", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "createItem", "item", "Lcom/xwray/groupie/Item;", "(Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;Landroidx/lifecycle/Lifecycle;Lcom/github/pockethub/android/ui/helpers/ItemListHandler;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "dataLoadDisposable", "Lio/reactivex/disposables/Disposable;", "hasMore", "", "getHasMore", "()Z", "setHasMore", "(Z)V", "isLoading", "onPageLoaded", "", "getOnPageLoaded", "()Lkotlin/jvm/functions/Function1;", "setOnPageLoaded", "(Lkotlin/jvm/functions/Function1;)V", "fetchNext", "fetchPage", "onDataLoadError", "throwable", "onDataLoaded", "newItems", "onPause", "onStart", "refresh", "app_debug"})
public final class PagedListFetcher<E extends java.lang.Object> implements androidx.lifecycle.LifecycleObserver {
    
    /**
     * Disposable for data load request.
     */
    private io.reactivex.disposables.Disposable dataLoadDisposable;
    private boolean isLoading;
    private boolean hasMore;
    @org.jetbrains.annotations.NotNull()
    private kotlin.jvm.functions.Function1<? super java.util.List<com.xwray.groupie.Item<?>>, ? extends java.util.List<com.xwray.groupie.Item<?>>> onPageLoaded;
    
    /**
     * The current page.
     */
    private int page;
    private final androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayout = null;
    private final androidx.lifecycle.Lifecycle lifecycle = null;
    private final com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler = null;
    private final kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> showError = null;
    private final kotlin.jvm.functions.Function1<java.lang.Integer, io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<E>>>> loadPage = null;
    private final kotlin.jvm.functions.Function1<E, com.xwray.groupie.Item<?>> createItem = null;
    
    public final boolean getHasMore() {
        return false;
    }
    
    public final void setHasMore(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.util.List<com.xwray.groupie.Item<?>>, java.util.List<com.xwray.groupie.Item<?>>> getOnPageLoaded() {
        return null;
    }
    
    public final void setOnPageLoaded(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.xwray.groupie.Item<?>>, ? extends java.util.List<com.xwray.groupie.Item<?>>> p0) {
    }
    
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_START)
    private final void onStart() {
    }
    
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_PAUSE)
    private final void onPause() {
    }
    
    public final void refresh() {
    }
    
    public final void fetchNext() {
    }
    
    private final void fetchPage() {
    }
    
    private final void onDataLoaded(java.util.List<com.xwray.groupie.Item<?>> newItems) {
    }
    
    private final void onDataLoadError(java.lang.Throwable throwable) {
    }
    
    public PagedListFetcher(@org.jetbrains.annotations.Nullable()
    androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayout, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Lifecycle lifecycle, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> showError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<E>>>> loadPage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super E, ? extends com.xwray.groupie.Item<?>> createItem) {
        super();
    }
}
package org.wordpress.android.ui.stats.refresh.lists.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B3\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ(\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001dJ\b\u0010 \u001a\u00020\u0019H\u0014J\b\u0010!\u001a\u00020\u0019H\u0007J\u0006\u0010\"\u001a\u00020\u0019R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0015R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/StatsDetailViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "detailUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "statsPostProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsPostProvider;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsPostProvider;Lorg/wordpress/android/util/NetworkUtilsWrapper;)V", "_isRefreshing", "Landroidx/lifecycle/MutableLiveData;", "", "_showSnackbarMessage", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "isRefreshing", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "showSnackbarMessage", "getShowSnackbarMessage", "init", "", "postId", "", "postType", "", "postTitle", "postUrl", "onCleared", "onPullToRefresh", "refresh", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsDetailViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase detailUseCase = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsPostProvider statsPostProvider = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isRefreshing = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isRefreshing = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> _showSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> showSnackbarMessage = null;
    
    @javax.inject.Inject()
    public StatsDetailViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BlockDetailUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase detailUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsPostProvider statsPostProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isRefreshing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> getShowSnackbarMessage() {
        return null;
    }
    
    public final void init(long postId, @org.jetbrains.annotations.NotNull()
    java.lang.String postType, @org.jetbrains.annotations.NotNull()
    java.lang.String postTitle, @org.jetbrains.annotations.Nullable()
    java.lang.String postUrl) {
    }
    
    public final void refresh() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    public final void onPullToRefresh() {
    }
}
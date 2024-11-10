package org.wordpress.android.models.networkresource;

import java.lang.System;

/**
 * ListState aims to give a highly structured and easy to use way to manage any list's state.
 *
 * There are 5 different states: [Init], [Ready], [Success], [Loading], [Error]. Check out their documentation to see
 * how each state behaves.
 *
 * @property data is initialized depending on each state and once initialized it can not be altered. In [Ready] and
 * [Success] states, it'll be passed as a parameter. In [Loading] and [Error] states, it'll be initialized from the
 * previous state to make sure the access to the data is not lost. In [Init], an empty list will be passed.
 * In situations where the data needs to be changed outside of a fetch, [transform] can be used
 * to get a new instance by using a transform function.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0005\u0010\u0011\u0012\u0013\u0014B\u0015\b\u0004\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u00a2\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tJ.\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u001e\u0010\u000e\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u000fH&R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0005\u0015\u0016\u0017\u0018\u0019\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/models/networkresource/ListState;", "T", "", "data", "", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "isFetchingFirstPage", "", "isLoadingMore", "shouldFetch", "loadMore", "transform", "transformFunc", "Lkotlin/Function1;", "Error", "Init", "Loading", "Ready", "Success", "Lorg/wordpress/android/models/networkresource/ListState$Init;", "Lorg/wordpress/android/models/networkresource/ListState$Ready;", "Lorg/wordpress/android/models/networkresource/ListState$Loading;", "Lorg/wordpress/android/models/networkresource/ListState$Success;", "Lorg/wordpress/android/models/networkresource/ListState$Error;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ListState<T extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<T> data = null;
    
    private ListState(java.util.List<? extends T> data) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<T> getData() {
        return null;
    }
    
    /**
     * In some situations the underlying data might change outside of a fetch. Adding a new item, removing one,
     * a single item getting updated would be some typical examples. Since the [data] property can not be altered
     * directly, which is by design, we need a different way to update it.
     *
     * This method can be used to handle any transformation easily while preserving the current state. Any function
     * that takes a [List] and returns a new one can be used as the transformation.
     *
     * @return a new ListState instance that has the transformed data while preserving the state
     */
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.models.networkresource.ListState<T> transform(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<? extends T>, ? extends java.util.List<? extends T>> transformFunc);
    
    /**
     * Helper function for checking whether the first page is being loaded. It can be used to either show or hide a
     * [android.support.v4.widget.SwipeRefreshLayout].
     */
    public final boolean isFetchingFirstPage() {
        return false;
    }
    
    /**
     * Helper function for checking whether more data is being loaded. It can be used to either show or hide a
     * [android.widget.ProgressBar] for instance, at the bottom of a screen.
     */
    public final boolean isLoadingMore() {
        return false;
    }
    
    /**
     * Helper function to check whether a fetch should occur. If the state is [Loading] fetch is not allowed. Otherwise,
     * the first page can be fetched at any time. Loading more data is only allowed if it's specifically flagged to be
     * possible in [Success] state.
     *
     * @param loadMore should be passed to indicate what kind of fetch is intended: first page or load more
     */
    public final boolean shouldFetch(boolean loadMore) {
        return false;
    }
    
    /**
     * This is the state each object should be created in. In this state [data] would be empty and [shouldFetch] will
     * return `false` with the assumption that the caller will need to get ready before fetch can happen. A typical
     * example would be to initialize a resource as a property and then [Ready] it after the necessary setup, such as
     * getting the `SiteModel` from a `Store`.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J.\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u001e\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006H\u0016\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/models/networkresource/ListState$Init;", "T", "Lorg/wordpress/android/models/networkresource/ListState;", "()V", "transform", "transformFunc", "Lkotlin/Function1;", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Init<T extends java.lang.Object> extends org.wordpress.android.models.networkresource.ListState<T> {
        
        public Init() {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.models.networkresource.ListState.Init<T> transform(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.List<? extends T>, ? extends java.util.List<? extends T>> transformFunc) {
            return null;
        }
    }
    
    /**
     * Ready state signifies that this resource can start being used.
     *
     * @param data This is one of 2 places where the data can be directly passed in. In most cases, it will be set
     * using the cached version of the data, for example from its `Store`.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u00a2\u0006\u0002\u0010\u0005J.\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u001e\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00040\bH\u0016\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/models/networkresource/ListState$Ready;", "T", "Lorg/wordpress/android/models/networkresource/ListState;", "data", "", "(Ljava/util/List;)V", "transform", "transformFunc", "Lkotlin/Function1;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Ready<T extends java.lang.Object> extends org.wordpress.android.models.networkresource.ListState<T> {
        
        public Ready(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends T> data) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.models.networkresource.ListState.Ready<T> transform(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.List<? extends T>, ? extends java.util.List<? extends T>> transformFunc) {
            return null;
        }
    }
    
    /**
     * This state means that a network request has been started to fetch either the first page or more data.
     *
     * @param data can not be passed directly to [Loading] state as it's prevented by a private constructor.
     * It's initialized either from the previous state or from the transformed data using
     * [ListState.transform].
     *
     * @param loadingMore flag is used to indicate whether the first page or more data is being fetched. It's default
     * value is `false` which should be useful in situations where pagination is not available.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\u001d\b\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\tJ.\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u001e\u0010\r\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u000eH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/models/networkresource/ListState$Loading;", "T", "Lorg/wordpress/android/models/networkresource/ListState;", "previous", "loadingMore", "", "(Lorg/wordpress/android/models/networkresource/ListState;Z)V", "data", "", "(Ljava/util/List;Z)V", "getLoadingMore", "()Z", "transform", "transformFunc", "Lkotlin/Function1;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading<T extends java.lang.Object> extends org.wordpress.android.models.networkresource.ListState<T> {
        private final boolean loadingMore = false;
        
        private Loading(java.util.List<? extends T> data, boolean loadingMore) {
            super(null);
        }
        
        public final boolean getLoadingMore() {
            return false;
        }
        
        public Loading(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.networkresource.ListState<T> previous, boolean loadingMore) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.models.networkresource.ListState.Loading<T> transform(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.List<? extends T>, ? extends java.util.List<? extends T>> transformFunc) {
            return null;
        }
    }
    
    /**
     * This state means that at least one fetch has successfully completed.
     *
     * @param data This is the second and final state where the data can be passed in directly.
     *
     * @param canLoadMore For resources where pagination is available, this flag can be used to indicate if more data
     * can be fetched. It's default value is `false` which should be useful in situations where pagination is not
     * available.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J.\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u001e\u0010\u000b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00040\fH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/models/networkresource/ListState$Success;", "T", "Lorg/wordpress/android/models/networkresource/ListState;", "data", "", "canLoadMore", "", "(Ljava/util/List;Z)V", "getCanLoadMore", "()Z", "transform", "transformFunc", "Lkotlin/Function1;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Success<T extends java.lang.Object> extends org.wordpress.android.models.networkresource.ListState<T> {
        private final boolean canLoadMore = false;
        
        public Success(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends T> data, boolean canLoadMore) {
            super(null);
        }
        
        public final boolean getCanLoadMore() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.models.networkresource.ListState.Success<T> transform(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.List<? extends T>, ? extends java.util.List<? extends T>> transformFunc) {
            return null;
        }
    }
    
    /**
     * This state means that at least one fetch has resulted in error.
     *
     * @param data can not be passed directly to [Error] state as it's prevented by a private constructor.
     * It's initialized either from the previous state or from the transformed data using
     * [ListState.transform].
     *
     * @param errorMessage will be the error string received from the API.
     * @param errorMessageResId can be used to propagate error resourceIds from ViewModels to Views.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B-\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bB-\b\u0002\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\n\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\u000bJ.\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u001e\u0010\u0012\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n0\u0013H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/models/networkresource/ListState$Error;", "T", "Lorg/wordpress/android/models/networkresource/ListState;", "previous", "errorMessage", "", "errorMessageResId", "", "(Lorg/wordpress/android/models/networkresource/ListState;Ljava/lang/String;Ljava/lang/Integer;)V", "data", "", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;)V", "getErrorMessage", "()Ljava/lang/String;", "getErrorMessageResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "transform", "transformFunc", "Lkotlin/Function1;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Error<T extends java.lang.Object> extends org.wordpress.android.models.networkresource.ListState<T> {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String errorMessage = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer errorMessageResId = null;
        
        private Error(java.util.List<? extends T> data, java.lang.String errorMessage, @androidx.annotation.StringRes()
        java.lang.Integer errorMessageResId) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getErrorMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getErrorMessageResId() {
            return null;
        }
        
        public Error(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.networkresource.ListState<T> previous, @org.jetbrains.annotations.Nullable()
        java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer errorMessageResId) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.models.networkresource.ListState.Error<T> transform(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.List<? extends T>, ? extends java.util.List<? extends T>> transformFunc) {
            return null;
        }
    }
}
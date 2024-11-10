package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0002\n\u000bJ\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteSource;", "T", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState;", "", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "MySiteRefreshSource", "SiteIndependentSource", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface MySiteSource<T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<T> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u0015\u0010\t\u001a\u00028\u00012\u0006\u0010\n\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\u000bJ\u000f\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0004\u001a\u00020\u000fH\u0016J\u001f\u0010\u0011\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00028\u00010\u00122\u0006\u0010\n\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00028\u00010\u00122\u0006\u0010\n\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\u0013R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteSource$MySiteRefreshSource;", "T", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState;", "Lorg/wordpress/android/ui/mysite/MySiteSource;", "refresh", "Landroidx/lifecycle/MutableLiveData;", "", "getRefresh", "()Landroidx/lifecycle/MutableLiveData;", "getState", "value", "(Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState;)Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState;", "isRefreshing", "()Ljava/lang/Boolean;", "onRefreshedBackgroundThread", "", "onRefreshedMainThread", "postState", "Landroidx/lifecycle/MediatorLiveData;", "(Landroidx/lifecycle/MediatorLiveData;Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState;)V", "setState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface MySiteRefreshSource<T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState> extends org.wordpress.android.ui.mysite.MySiteSource<T> {
        
        @org.jetbrains.annotations.NotNull()
        public abstract androidx.lifecycle.MutableLiveData<java.lang.Boolean> getRefresh();
        
        public abstract void refresh();
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.Boolean isRefreshing();
        
        @org.jetbrains.annotations.NotNull()
        public abstract T getState(@org.jetbrains.annotations.NotNull()
        T value);
        
        public abstract void postState(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.MediatorLiveData<T> $this$postState, @org.jetbrains.annotations.NotNull()
        T value);
        
        public abstract void setState(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.MediatorLiveData<T> $this$setState, @org.jetbrains.annotations.NotNull()
        T value);
        
        public abstract void onRefreshedMainThread();
        
        public abstract void onRefreshedBackgroundThread();
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
        public static final class DefaultImpls {
            
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void refresh(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<T> $this) {
            }
            
            @org.jetbrains.annotations.Nullable()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>java.lang.Boolean isRefreshing(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<T> $this) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>T getState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<T> $this, @org.jetbrains.annotations.NotNull()
            T value) {
                return null;
            }
            
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void postState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<T> $this, @org.jetbrains.annotations.NotNull()
            androidx.lifecycle.MediatorLiveData<T> $this$postState, @org.jetbrains.annotations.NotNull()
            T value) {
            }
            
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void setState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<T> $this, @org.jetbrains.annotations.NotNull()
            androidx.lifecycle.MediatorLiveData<T> $this$setState, @org.jetbrains.annotations.NotNull()
            T value) {
            }
            
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void onRefreshedMainThread(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<T> $this) {
            }
            
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void onRefreshedBackgroundThread(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<T> $this) {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteSource$SiteIndependentSource;", "T", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState;", "Lorg/wordpress/android/ui/mysite/MySiteSource$MySiteRefreshSource;", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface SiteIndependentSource<T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState> extends org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<T> {
        
        @org.jetbrains.annotations.NotNull()
        public abstract androidx.lifecycle.LiveData<T> build(@org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineScope coroutineScope);
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public abstract androidx.lifecycle.LiveData<T> build(@org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId);
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
        public static final class DefaultImpls {
            
            @java.lang.Override()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void refresh(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<T> $this) {
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>java.lang.Boolean isRefreshing(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<T> $this) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>T getState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<T> $this, @org.jetbrains.annotations.NotNull()
            T value) {
                return null;
            }
            
            @java.lang.Override()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void postState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<T> $this, @org.jetbrains.annotations.NotNull()
            androidx.lifecycle.MediatorLiveData<T> $this$postState, @org.jetbrains.annotations.NotNull()
            T value) {
            }
            
            @java.lang.Override()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void setState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<T> $this, @org.jetbrains.annotations.NotNull()
            androidx.lifecycle.MediatorLiveData<T> $this$setState, @org.jetbrains.annotations.NotNull()
            T value) {
            }
            
            @java.lang.Override()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void onRefreshedMainThread(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<T> $this) {
            }
            
            @java.lang.Override()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>void onRefreshedBackgroundThread(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<T> $this) {
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public static <T extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>androidx.lifecycle.LiveData<T> build(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<T> $this, @org.jetbrains.annotations.NotNull()
            kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
                return null;
            }
        }
    }
}
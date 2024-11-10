package org.wordpress.android.ui.debug.cookies;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001aB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J$\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0017R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/debug/cookies/DebugCookiesViewModel;", "Landroidx/lifecycle/ViewModel;", "debugCookieManager", "Lorg/wordpress/android/ui/debug/cookies/DebugCookieManager;", "(Lorg/wordpress/android/ui/debug/cookies/DebugCookieManager;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/debug/cookies/DebugCookiesViewModel$UiState;", "kotlin.jvm.PlatformType", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "getUpdatedItems", "", "Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookieItem;", "onDeleteClick", "", "debugCookie", "Lorg/wordpress/android/ui/debug/cookies/DebugCookie;", "onItemClick", "setCookie", "host", "", "name", "value", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DebugCookiesViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.ui.debug.cookies.DebugCookieManager debugCookieManager = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.debug.cookies.DebugCookiesViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.debug.cookies.DebugCookiesViewModel.UiState> uiState = null;
    
    @javax.inject.Inject()
    public DebugCookiesViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.debug.cookies.DebugCookieManager debugCookieManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.debug.cookies.DebugCookiesViewModel.UiState> getUiState() {
        return null;
    }
    
    public final void setCookie(@org.jetbrains.annotations.Nullable()
    java.lang.String host, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    private final void onItemClick(org.wordpress.android.ui.debug.cookies.DebugCookie debugCookie) {
    }
    
    private final void onDeleteClick(org.wordpress.android.ui.debug.cookies.DebugCookie debugCookie) {
    }
    
    private final java.util.List<org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem> getUpdatedItems() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J=\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0006H\u00d6\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/debug/cookies/DebugCookiesViewModel$UiState;", "", "items", "", "Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookieItem;", "hostInputText", "", "nameInputText", "valueInputText", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHostInputText", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "getNameInputText", "getValueInputText", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem> items = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String hostInputText = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String nameInputText = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String valueInputText = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.debug.cookies.DebugCookiesViewModel.UiState copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem> items, @org.jetbrains.annotations.Nullable()
        java.lang.String hostInputText, @org.jetbrains.annotations.Nullable()
        java.lang.String nameInputText, @org.jetbrains.annotations.Nullable()
        java.lang.String valueInputText) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public UiState(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem> items, @org.jetbrains.annotations.Nullable()
        java.lang.String hostInputText, @org.jetbrains.annotations.Nullable()
        java.lang.String nameInputText, @org.jetbrains.annotations.Nullable()
        java.lang.String valueInputText) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem> getItems() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getHostInputText() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getNameInputText() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getValueInputText() {
            return null;
        }
    }
}
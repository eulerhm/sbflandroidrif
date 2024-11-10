package org.wordpress.android.ui.prefs.homepage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\b\u001a\u00020\tH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader;", "", "pageStore", "Lorg/wordpress/android/fluxc/store/PageStore;", "(Lorg/wordpress/android/fluxc/store/PageStore;)V", "loadPages", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadPagesFromDb", "", "Lorg/wordpress/android/fluxc/model/page/PageModel;", "LoadingResult", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HomepageSettingsDataLoader {
    private final org.wordpress.android.fluxc.store.PageStore pageStore = null;
    
    @javax.inject.Inject()
    public HomepageSettingsDataLoader(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PageStore pageStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadPages(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader.LoadingResult>> continuation) {
        return null;
    }
    
    private final java.lang.Object loadPagesFromDb(org.wordpress.android.fluxc.model.SiteModel siteModel, kotlin.coroutines.Continuation<? super java.util.List<org.wordpress.android.fluxc.model.page.PageModel>> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult;", "", "()V", "Data", "Error", "Loading", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult$Loading;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult$Error;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult$Data;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class LoadingResult {
        
        private LoadingResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult$Loading;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Loading extends org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader.LoadingResult {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader.LoadingResult.Loading INSTANCE = null;
            
            private Loading() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult$Error;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult;", "message", "", "(I)V", "getMessage", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Error extends org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader.LoadingResult {
            private final int message = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader.LoadingResult.Error copy(int message) {
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
            
            public Error(int message) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getMessage() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult$Data;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult;", "pages", "", "Lorg/wordpress/android/fluxc/model/page/PageModel;", "(Ljava/util/List;)V", "getPages", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Data extends org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader.LoadingResult {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader.LoadingResult.Data copy(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages) {
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
            
            public Data(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.page.PageModel> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.page.PageModel> getPages() {
                return null;
            }
        }
    }
}
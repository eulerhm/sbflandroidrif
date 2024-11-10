package org.wordpress.android.ui.prefs.homepage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u00017BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003JZ\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\tH\u00d6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010\'\u001a\u00020\tJ\u0006\u0010(\u001a\u00020\u0000J\'\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010-\u00a2\u0006\u0002\u0010/J\u000e\u00100\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\tJ\u000e\u00101\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\tJ%\u00102\u001a\u0002032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\tH\u0002\u00a2\u0006\u0002\u00106R\u0015\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u00068"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState;", "", "isClassicBlogState", "", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "isSaveEnabled", "isLoading", "error", "", "pageOnFrontState", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsSelectorUiState;", "pageForPostsState", "(ZLorg/wordpress/android/fluxc/model/SiteModel;ZZLjava/lang/Integer;Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsSelectorUiState;Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsSelectorUiState;)V", "getError", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "getPageForPostsState", "()Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsSelectorUiState;", "getPageOnFrontState", "getSiteModel", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(ZLorg/wordpress/android/fluxc/model/SiteModel;ZZLjava/lang/Integer;Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsSelectorUiState;Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsSelectorUiState;)Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState;", "equals", "other", "hashCode", "toString", "", "updateClassicBlogState", "updateWithError", "message", "updateWithLoading", "updateWithLoadingResult", "loadingResult", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult;", "pageForPostsId", "", "pageOnFrontId", "(Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDataLoader$LoadingResult;Ljava/lang/Long;Ljava/lang/Long;)Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState;", "updateWithPageForPosts", "updateWithPageOnFront", "validate", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult;", "newPageForPostsId", "newPageOnFrontId", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult;", "ValidityResult", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HomepageSettingsUiState {
    private final boolean isClassicBlogState = false;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.model.SiteModel siteModel = null;
    private final boolean isSaveEnabled = false;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer error = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState pageOnFrontState = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState pageForPostsState = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState copy(boolean isClassicBlogState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, boolean isSaveEnabled, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.Integer error, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState pageOnFrontState, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState pageForPostsState) {
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
    
    public HomepageSettingsUiState(boolean isClassicBlogState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, boolean isSaveEnabled, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.Integer error, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState pageOnFrontState, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState pageForPostsState) {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final boolean isClassicBlogState() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSiteModel() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final boolean isSaveEnabled() {
        return false;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState getPageOnFrontState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState getPageForPostsState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState updateWithLoadingResult(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.homepage.HomepageSettingsDataLoader.LoadingResult loadingResult, @org.jetbrains.annotations.Nullable()
    java.lang.Long pageForPostsId, @org.jetbrains.annotations.Nullable()
    java.lang.Long pageOnFrontId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState updateWithError(int message) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState updateWithLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState updateWithPageForPosts(int pageForPostsId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState updateWithPageOnFront(int pageOnFrontId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState updateClassicBlogState(boolean isClassicBlogState) {
        return null;
    }
    
    private final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState.ValidityResult validate(java.lang.Integer newPageForPostsId, java.lang.Integer newPageOnFrontId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0005\u0082\u0001\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult;", "", "()V", "printErrorOrNull", "", "()Ljava/lang/Integer;", "Invalid", "Valid", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult$Valid;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult$Invalid;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ValidityResult {
        
        private ValidityResult() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer printErrorOrNull() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult$Valid;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Valid extends org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState.ValidityResult {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState.ValidityResult.Valid INSTANCE = null;
            
            private Valid() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult$Invalid;", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsUiState$ValidityResult;", "error", "", "(I)V", "getError", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Invalid extends org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState.ValidityResult {
            private final int error = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsUiState.ValidityResult.Invalid copy(int error) {
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
            
            public Invalid(int error) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getError() {
                return 0;
            }
        }
    }
}
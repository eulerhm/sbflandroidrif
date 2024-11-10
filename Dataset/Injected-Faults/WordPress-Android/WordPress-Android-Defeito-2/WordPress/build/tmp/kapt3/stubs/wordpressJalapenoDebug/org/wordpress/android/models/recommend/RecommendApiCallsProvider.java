package org.wordpress.android.models.recommend;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u001f !B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J!\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\"\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "restClientProvider", "Lorg/wordpress/android/util/RestClientProvider;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "(Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/RestClientProvider;Lorg/wordpress/android/util/LocaleManagerWrapper;)V", "getNetErrorAndLogStrings", "Lkotlin/Pair;", "", "callingFunction", "volleyError", "Lcom/android/volley/VolleyError;", "getRecommendTemplate", "Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendCallResult;", "appName", "source", "Lorg/wordpress/android/util/analytics/AnalyticsUtils$RecommendAppSource;", "(Ljava/lang/String;Lorg/wordpress/android/util/analytics/AnalyticsUtils$RecommendAppSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTemplateFromJson", "json", "Lorg/json/JSONObject;", "logErrorAndTrack", "", "logMessage", "RecommendAppName", "RecommendCallResult", "RecommendTemplateData", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RecommendApiCallsProvider {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.util.RestClientProvider restClientProvider = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    
    @javax.inject.Inject()
    public RecommendApiCallsProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.RestClientProvider restClientProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRecommendTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String appName, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtils.RecommendAppSource source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendCallResult> continuation) {
        return null;
    }
    
    private final void logErrorAndTrack(org.wordpress.android.util.analytics.AnalyticsUtils.RecommendAppSource source, java.lang.String logMessage) {
    }
    
    private final org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendCallResult getTemplateFromJson(org.json.JSONObject json, java.lang.String appName, org.wordpress.android.util.analytics.AnalyticsUtils.RecommendAppSource source) {
        return null;
    }
    
    private final kotlin.Pair<java.lang.String, java.lang.String> getNetErrorAndLogStrings(java.lang.String callingFunction, com.android.volley.VolleyError volleyError) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendCallResult;", "", "()V", "Failure", "Success", "Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendCallResult$Success;", "Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendCallResult$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class RecommendCallResult {
        
        private RecommendCallResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendCallResult$Success;", "Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendCallResult;", "templateData", "Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendTemplateData;", "(Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendTemplateData;)V", "getTemplateData", "()Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendTemplateData;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendCallResult {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendTemplateData templateData = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendCallResult.Success copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendTemplateData templateData) {
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
            
            public Success(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendTemplateData templateData) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendTemplateData component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendTemplateData getTemplateData() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendCallResult$Failure;", "Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendCallResult;", "error", "", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failure extends org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendCallResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String error = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendCallResult.Failure copy(@org.jetbrains.annotations.NotNull()
            java.lang.String error) {
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
            
            public Failure(@org.jetbrains.annotations.NotNull()
            java.lang.String error) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getError() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendTemplateData;", "", "name", "", "message", "link", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLink", "()Ljava/lang/String;", "getMessage", "getName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class RecommendTemplateData {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String message = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String link = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.recommend.RecommendApiCallsProvider.RecommendTemplateData copy(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.NotNull()
        java.lang.String link) {
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
        
        public RecommendTemplateData(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.NotNull()
        java.lang.String link) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLink() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider$RecommendAppName;", "", "appName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAppName", "()Ljava/lang/String;", "WordPress", "Jetpack", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum RecommendAppName {
        /*public static final*/ WordPress /* = new WordPress(null) */,
        /*public static final*/ Jetpack /* = new Jetpack(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String appName = null;
        
        RecommendAppName(java.lang.String appName) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAppName() {
            return null;
        }
    }
}
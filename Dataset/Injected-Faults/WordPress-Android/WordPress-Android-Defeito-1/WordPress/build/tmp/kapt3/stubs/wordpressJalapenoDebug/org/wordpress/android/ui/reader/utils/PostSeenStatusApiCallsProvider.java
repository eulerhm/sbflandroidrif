package org.wordpress.android.ui.reader.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001a\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "markPostAsSeen", "Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleCallResult;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markPostAsUnseen", "seenStatusToggleSuccessful", "json", "Lorg/json/JSONObject;", "askedToMarkAsSeen", "", "SeenStatusToggleCallResult", "SeenStatusToggleRequestParameters", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostSeenStatusApiCallsProvider {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    
    @javax.inject.Inject()
    public PostSeenStatusApiCallsProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markPostAsSeen(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleCallResult> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markPostAsUnseen(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleCallResult> continuation) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleCallResult seenStatusToggleSuccessful(org.json.JSONObject json, boolean askedToMarkAsSeen) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleCallResult;", "", "()V", "Failure", "Success", "Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleCallResult$Success;", "Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleCallResult$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SeenStatusToggleCallResult {
        
        private SeenStatusToggleCallResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleCallResult$Success;", "Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleCallResult;", "isSeen", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleCallResult {
            private final boolean isSeen = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleCallResult.Success copy(boolean isSeen) {
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
            
            public Success(boolean isSeen) {
                super();
            }
            
            public final boolean component1() {
                return false;
            }
            
            public final boolean isSeen() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleCallResult$Failure;", "Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleCallResult;", "error", "", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failure extends org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleCallResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String error = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleCallResult.Failure copy(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleRequestParameters;", "", "parameters", "Lorg/json/JSONObject;", "endpoint", "", "(Lorg/json/JSONObject;Ljava/lang/String;)V", "getEndpoint", "()Ljava/lang/String;", "getParameters", "()Lorg/json/JSONObject;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SeenStatusToggleRequestParameters {
        @org.jetbrains.annotations.NotNull()
        private final org.json.JSONObject parameters = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String endpoint = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleRequestParameters.Companion Companion = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleRequestParameters copy(@org.jetbrains.annotations.NotNull()
        org.json.JSONObject parameters, @org.jetbrains.annotations.NotNull()
        java.lang.String endpoint) {
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
        
        public SeenStatusToggleRequestParameters(@org.jetbrains.annotations.NotNull()
        org.json.JSONObject parameters, @org.jetbrains.annotations.NotNull()
        java.lang.String endpoint) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.json.JSONObject component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.json.JSONObject getParameters() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEndpoint() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleRequestParameters$Companion;", "", "()V", "fromReaderPost", "Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider$SeenStatusToggleRequestParameters;", "post", "Lorg/wordpress/android/models/ReaderPost;", "askingToMarkAsSeen", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider.SeenStatusToggleRequestParameters fromReaderPost(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.ReaderPost post, boolean askingToMarkAsSeen) {
                return null;
            }
        }
    }
}
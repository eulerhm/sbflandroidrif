package org.wordpress.android.ui.people;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u0019\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "disableLinks", "Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksCallResult;", "blogId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateLinks", "getErrorStringAndLog", "", "functionName", "volleyError", "Lcom/android/volley/VolleyError;", "getInviteLinksStatus", "getLinks", "json", "Lorg/json/JSONObject;", "InviteLinksCallResult", "InviteLinksItem", "org.wordpress.android_wordpressJalapenoDebug"})
public final class InviteLinksApiCallsProvider {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    
    @javax.inject.Inject()
    public InviteLinksApiCallsProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getInviteLinksStatus(long blogId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksCallResult> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generateLinks(long blogId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksCallResult> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object disableLinks(long blogId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksCallResult> continuation) {
        return null;
    }
    
    private final org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksCallResult getLinks(org.json.JSONObject json) {
        return null;
    }
    
    private final java.lang.String getErrorStringAndLog(java.lang.String functionName, long blogId, com.android.volley.VolleyError volleyError) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksItem;", "", "role", "", "expiry", "", "link", "(Ljava/lang/String;JLjava/lang/String;)V", "getExpiry", "()J", "getLink", "()Ljava/lang/String;", "getRole", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class InviteLinksItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String role = null;
        private final long expiry = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String link = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String role, long expiry, @org.jetbrains.annotations.NotNull()
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
        
        public InviteLinksItem(@org.jetbrains.annotations.NotNull()
        java.lang.String role, long expiry, @org.jetbrains.annotations.NotNull()
        java.lang.String link) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getRole() {
            return null;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getExpiry() {
            return 0L;
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksCallResult;", "", "()V", "Failure", "Success", "Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksCallResult$Success;", "Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksCallResult$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class InviteLinksCallResult {
        
        private InviteLinksCallResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksCallResult$Success;", "Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksCallResult;", "links", "", "Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksItem;", "(Ljava/util/List;)V", "getLinks", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksCallResult {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem> links = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksCallResult.Success copy(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem> links) {
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
            java.util.List<org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem> links) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem> getLinks() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksCallResult$Failure;", "Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksCallResult;", "error", "", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failure extends org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksCallResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String error = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksCallResult.Failure copy(@org.jetbrains.annotations.NotNull()
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
}
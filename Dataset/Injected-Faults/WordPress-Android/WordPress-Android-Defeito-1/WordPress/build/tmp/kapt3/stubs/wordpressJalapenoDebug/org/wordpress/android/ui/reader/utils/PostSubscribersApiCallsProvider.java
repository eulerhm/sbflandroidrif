package org.wordpress.android.ui.reader.utils;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000  2\u00020\u0001:\u0002 !B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ*\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J!\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J)\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J!\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J!\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0012\u0010\u001c\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010\u001d\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010\u001e\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010\u001f\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "canFollowComments", "Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$PostSubscribersCallResult;", "blogId", "", "json", "Lorg/json/JSONObject;", "getCanFollowComments", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getErrorStringAndLog", "", "functionName", "postId", "volleyError", "Lcom/android/volley/VolleyError;", "getFollowingStateResult", "getMySubscriptionToPost", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "managePushNotificationsForPost", "activate", "(JJZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribeMeToPost", "unsubscribeMeFromPost", "wasSubscribed", "wasSubscribedForPushNotifications", "wasUnsubscribed", "wasUnsubscribedFromPushNotifications", "Companion", "PostSubscribersCallResult", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostSubscribersApiCallsProvider {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.Companion Companion = null;
    private static final java.lang.String KEY_I_SUBSCRIBE = "i_subscribe";
    private static final java.lang.String KEY_RECEIVES_NOTIFICATIONS = "receives_notifications";
    private static final java.lang.String KEY_SUCCESS = "success";
    
    @javax.inject.Inject()
    public PostSubscribersApiCallsProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCanFollowComments(long blogId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMySubscriptionToPost(long blogId, long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object subscribeMeToPost(long blogId, long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object unsubscribeMeFromPost(long blogId, long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object managePushNotificationsForPost(long blogId, long postId, boolean activate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult> continuation) {
        return null;
    }
    
    private final java.lang.String getErrorStringAndLog(java.lang.String functionName, long blogId, long postId, com.android.volley.VolleyError volleyError) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult getFollowingStateResult(org.json.JSONObject json) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult canFollowComments(long blogId, org.json.JSONObject json) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult wasSubscribed(org.json.JSONObject json) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult wasUnsubscribed(org.json.JSONObject json) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult wasSubscribedForPushNotifications(org.json.JSONObject json) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult wasUnsubscribedFromPushNotifications(org.json.JSONObject json) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$PostSubscribersCallResult;", "", "()V", "Failure", "Success", "Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$PostSubscribersCallResult$Success;", "Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$PostSubscribersCallResult$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PostSubscribersCallResult {
        
        private PostSubscribersCallResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$PostSubscribersCallResult$Success;", "Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$PostSubscribersCallResult;", "isFollowing", "", "isReceivingNotifications", "(ZZ)V", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult {
            private final boolean isFollowing = false;
            private final boolean isReceivingNotifications = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult.Success copy(boolean isFollowing, boolean isReceivingNotifications) {
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
            
            public Success(boolean isFollowing, boolean isReceivingNotifications) {
                super();
            }
            
            public final boolean component1() {
                return false;
            }
            
            public final boolean isFollowing() {
                return false;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean isReceivingNotifications() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$PostSubscribersCallResult$Failure;", "Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$PostSubscribersCallResult;", "error", "", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failure extends org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String error = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider.PostSubscribersCallResult.Failure copy(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider$Companion;", "", "()V", "KEY_I_SUBSCRIBE", "", "KEY_RECEIVES_NOTIFICATIONS", "KEY_SUCCESS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
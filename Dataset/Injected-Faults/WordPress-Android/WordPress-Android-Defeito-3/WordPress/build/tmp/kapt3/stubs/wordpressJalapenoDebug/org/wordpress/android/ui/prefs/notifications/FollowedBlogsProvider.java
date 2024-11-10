package org.wordpress.android.ui.prefs.notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0011B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/prefs/notifications/FollowedBlogsProvider;", "", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "readerBlogTable", "Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "urlUtils", "Lorg/wordpress/android/ui/utils/UrlUtilsWrapper;", "(Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;Lorg/wordpress/android/ui/utils/UrlUtilsWrapper;)V", "getAllFollowedBlogs", "", "Lorg/wordpress/android/ui/prefs/notifications/FollowedBlogsProvider$PreferenceModel;", "query", "", "getSiteNameOrHostFromSubscription", "blogName", "blogUrl", "PreferenceModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FollowedBlogsProvider {
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTable = null;
    private final org.wordpress.android.ui.utils.UrlUtilsWrapper urlUtils = null;
    
    @javax.inject.Inject()
    public FollowedBlogsProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTable, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UrlUtilsWrapper urlUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel> getAllFollowedBlogs(@org.jetbrains.annotations.Nullable()
    java.lang.String query) {
        return null;
    }
    
    private final java.lang.String getSiteNameOrHostFromSubscription(java.lang.String blogName, java.lang.String blogUrl) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001aB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/prefs/notifications/FollowedBlogsProvider$PreferenceModel;", "", "title", "", "summary", "blogId", "clickHandler", "Lorg/wordpress/android/ui/prefs/notifications/FollowedBlogsProvider$PreferenceModel$ClickHandler;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/prefs/notifications/FollowedBlogsProvider$PreferenceModel$ClickHandler;)V", "getBlogId", "()Ljava/lang/String;", "getClickHandler", "()Lorg/wordpress/android/ui/prefs/notifications/FollowedBlogsProvider$PreferenceModel$ClickHandler;", "getSummary", "getTitle", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "ClickHandler", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreferenceModel {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String summary = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String blogId = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel.ClickHandler clickHandler = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel copy(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String summary, @org.jetbrains.annotations.NotNull()
        java.lang.String blogId, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel.ClickHandler clickHandler) {
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
        
        public PreferenceModel(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String summary, @org.jetbrains.annotations.NotNull()
        java.lang.String blogId, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel.ClickHandler clickHandler) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSummary() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBlogId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel.ClickHandler component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel.ClickHandler getClickHandler() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0006H\u00d6\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/prefs/notifications/FollowedBlogsProvider$PreferenceModel$ClickHandler;", "", "shouldNotifyPosts", "", "shouldEmailPosts", "emailPostFrequency", "", "shouldEmailComments", "(ZZLjava/lang/String;Z)V", "getEmailPostFrequency", "()Ljava/lang/String;", "getShouldEmailComments", "()Z", "getShouldEmailPosts", "getShouldNotifyPosts", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ClickHandler {
            private final boolean shouldNotifyPosts = false;
            private final boolean shouldEmailPosts = false;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String emailPostFrequency = null;
            private final boolean shouldEmailComments = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel.ClickHandler copy(boolean shouldNotifyPosts, boolean shouldEmailPosts, @org.jetbrains.annotations.Nullable()
            java.lang.String emailPostFrequency, boolean shouldEmailComments) {
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
            
            public ClickHandler(boolean shouldNotifyPosts, boolean shouldEmailPosts, @org.jetbrains.annotations.Nullable()
            java.lang.String emailPostFrequency, boolean shouldEmailComments) {
                super();
            }
            
            public final boolean component1() {
                return false;
            }
            
            public final boolean getShouldNotifyPosts() {
                return false;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getShouldEmailPosts() {
                return false;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getEmailPostFrequency() {
                return null;
            }
            
            public final boolean component4() {
                return false;
            }
            
            public final boolean getShouldEmailComments() {
                return false;
            }
        }
    }
}
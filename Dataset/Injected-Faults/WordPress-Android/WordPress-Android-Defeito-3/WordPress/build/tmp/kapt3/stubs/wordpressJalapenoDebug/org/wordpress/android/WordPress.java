package org.wordpress.android;

import java.lang.System;

/**
 * An abstract class to be extended by {@link WordPressApp} for real application and WordPressTest for UI test
 * application. Containing public static variables and methods to be accessed by other classes.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\u0005H&J\u0006\u0010\u000b\u001a\u00020\fR\u0015\u0010\u0003\u001a\u00060\u0004R\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/WordPress;", "Landroidx/multidex/MultiDexApplication;", "()V", "storyNotificationTrackerProvider", "Lorg/wordpress/android/AppInitializer$StoryNotificationTrackerProvider;", "Lorg/wordpress/android/AppInitializer;", "getStoryNotificationTrackerProvider", "()Lorg/wordpress/android/AppInitializer$StoryNotificationTrackerProvider;", "component", "Lorg/wordpress/android/modules/AppComponent;", "initializer", "wordPressComSignOut", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class WordPress extends androidx.multidex.MultiDexApplication {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.WordPress.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SITE = "SITE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOCAL_SITE_ID = "LOCAL_SITE_ID";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REMOTE_SITE_ID = "REMOTE_SITE_ID";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_AGENT_APPNAME = "wp-android";
    public static java.lang.String versionName;
    public static org.wordpress.android.WordPressDB wpDB;
    private static boolean appIsInTheBackground = true;
    @org.jetbrains.annotations.Nullable()
    @kotlin.jvm.JvmField()
    public static com.android.volley.RequestQueue requestQueue;
    @org.jetbrains.annotations.Nullable()
    @kotlin.jvm.JvmField()
    public static org.wordpress.android.fluxc.tools.FluxCImageLoader imageLoader;
    
    public WordPress() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.AppInitializer.StoryNotificationTrackerProvider getStoryNotificationTrackerProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.AppInitializer initializer();
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.modules.AppComponent component() {
        return null;
    }
    
    public final void wordPressComSignOut() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.util.BitmapLruCache getBitmapCache() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final android.content.Context getContext() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void updateContextLocale() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.networking.RestClientUtils getRestClientUtils() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.SuppressWarnings(value = {"FunctionNaming"})
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.SuppressWarnings(value = {"FunctionNaming"})
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.SuppressWarnings(value = {"FunctionNaming"})
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final java.lang.String getDefaultUserAgent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final java.lang.String getUserAgent() {
        return null;
    }
    
    @kotlin.Suppress(names = {"TooManyFunctions"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0007J\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00042\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0004J\u001a\u0010#\u001a\u0004\u0018\u00010\u00012\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0004J\b\u0010\'\u001a\u00020(H\u0007J\b\u0010)\u001a\u00020\u0004H\u0007J\b\u0010*\u001a\u00020+H\u0007J\u0006\u0010,\u001a\u00020+J\b\u0010-\u001a\u00020+H\u0007J\b\u0010.\u001a\u00020+H\u0007J\b\u0010/\u001a\u00020+H\u0007J\u0006\u00100\u001a\u00020+J\b\u00101\u001a\u00020\u0004H\u0007J\b\u00102\u001a\u000203H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u00064"}, d2 = {"Lorg/wordpress/android/WordPress$Companion;", "", "()V", "LOCAL_SITE_ID", "", "REMOTE_SITE_ID", "SITE", "USER_AGENT_APPNAME", "appIsInTheBackground", "", "getAppIsInTheBackground", "()Z", "setAppIsInTheBackground", "(Z)V", "imageLoader", "Lorg/wordpress/android/fluxc/tools/FluxCImageLoader;", "requestQueue", "Lcom/android/volley/RequestQueue;", "versionName", "getVersionName", "()Ljava/lang/String;", "setVersionName", "(Ljava/lang/String;)V", "wpDB", "Lorg/wordpress/android/WordPressDB;", "getWpDB", "()Lorg/wordpress/android/WordPressDB;", "setWpDB", "(Lorg/wordpress/android/WordPressDB;)V", "getBitmapCache", "Lorg/wordpress/android/util/BitmapLruCache;", "getBuildConfigString", "activity", "Landroid/app/Activity;", "configValueName", "getBuildConfigValue", "application", "Landroid/app/Application;", "fieldName", "getContext", "Landroid/content/Context;", "getDefaultUserAgent", "getRestClientUtils", "Lorg/wordpress/android/networking/RestClientUtils;", "getRestClientUtilsV0", "getRestClientUtilsV1_1", "getRestClientUtilsV1_2", "getRestClientUtilsV1_3", "getRestClientUtilsV2", "getUserAgent", "updateContextLocale", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getVersionName() {
            return null;
        }
        
        public final void setVersionName(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.WordPressDB getWpDB() {
            return null;
        }
        
        public final void setWpDB(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.WordPressDB p0) {
        }
        
        public final boolean getAppIsInTheBackground() {
            return false;
        }
        
        public final void setAppIsInTheBackground(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.util.BitmapLruCache getBitmapCache() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final android.content.Context getContext() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        public final void updateContextLocale() {
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtils() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.SuppressWarnings(value = {"FunctionNaming"})
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.SuppressWarnings(value = {"FunctionNaming"})
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.SuppressWarnings(value = {"FunctionNaming"})
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV0() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final java.lang.String getDefaultUserAgent() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final java.lang.String getUserAgent() {
            return null;
        }
        
        /**
         * Gets a field from the project's BuildConfig using reflection. This is useful when flavors are used at the
         * project level to set custom fields.
         * based on: https://code.google.com/p/android/issues/detail?id=52962#c38
         *
         * @param application Used to find the correct file
         * @param fieldName The name of the field-to-access
         * @return The value of the field, or `null` if the field is not found.
         */
        @org.jetbrains.annotations.Nullable()
        @kotlin.Suppress(names = {"TooGenericExceptionCaught"})
        public final java.lang.Object getBuildConfigValue(@org.jetbrains.annotations.NotNull()
        android.app.Application application, @org.jetbrains.annotations.Nullable()
        java.lang.String fieldName) {
            return null;
        }
        
        /**
         * Gets a field from the project's BuildConfig using reflection. This is useful when flavors are used at the
         * project level to set custom fields.
         * based on: https://code.google.com/p/android/issues/detail?id=52962#c38
         *
         * @param activity Used to get the Application instance
         * @param configValueName The name of the field-to-access
         * @return The string value of the field, or empty string if the field is not found.
         */
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getBuildConfigString(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity, @org.jetbrains.annotations.NotNull()
        java.lang.String configValueName) {
            return null;
        }
    }
}
package org.wordpress.android.ui.debug.cookies;

import java.lang.System;

/**
 * This class syncs [DebugCookie]s between a [java.net.CookieManager], a [android.webkit.CookieManager], and a
 * [SharedPreferences][android.content.SharedPreferences].
 *
 * Note: this class was not designed with production use in mind, and because of that, it makes several assumptions
 * about the format of the cookies. If we ever need to use this for anything other than manually setting debug cookies,
 * then we should consider introducing our own [java.net.CookieStore] implementation instead.
 *
 * @param httpCookieManager [java.net.CookieManager] instance used by HTTP calls.
 * @param webViewCookieManager [android.webkit.CookieManager] instance used by [WebView][android.webkit.WebView]s.
 * @param preferences [SharedPreferences][android.content.SharedPreferences] instance to store [DebugCookie]s.
 * @param gson Gson instance to encode/decode [DebugCookie]s for [SharedPreferences][android.content.SharedPreferences].
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bB/\b\u0000\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0018\u001a\u00020\u0012J\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0017\u001a\u00020\u0012*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0017\u001a\u00020\u0012*\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0017\u001a\u00020\u0019*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/debug/cookies/DebugCookieManager;", "", "context", "Landroid/content/Context;", "cookieManager", "Ljava/net/CookieManager;", "buildConfig", "Lorg/wordpress/android/util/BuildConfigWrapper;", "(Landroid/content/Context;Ljava/net/CookieManager;Lorg/wordpress/android/util/BuildConfigWrapper;)V", "httpCookieManager", "webViewCookieManager", "Landroid/webkit/CookieManager;", "preferences", "Landroid/content/SharedPreferences;", "gson", "Lcom/google/gson/Gson;", "(Ljava/net/CookieManager;Landroid/webkit/CookieManager;Landroid/content/SharedPreferences;Lcom/google/gson/Gson;Lorg/wordpress/android/util/BuildConfigWrapper;)V", "add", "", "cookie", "Lorg/wordpress/android/ui/debug/cookies/DebugCookie;", "getAll", "", "remove", "sync", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DebugCookieManager {
    private final java.net.CookieManager httpCookieManager = null;
    private final android.webkit.CookieManager webViewCookieManager = null;
    private final android.content.SharedPreferences preferences = null;
    private final com.google.gson.Gson gson = null;
    private final org.wordpress.android.util.BuildConfigWrapper buildConfig = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.debug.cookies.DebugCookieManager.Companion Companion = null;
    private static final java.lang.String DEBUG_COOKIE_PREFERENCES = "debug-cookie-preferences";
    
    public DebugCookieManager(@org.jetbrains.annotations.NotNull()
    java.net.CookieManager httpCookieManager, @org.jetbrains.annotations.NotNull()
    android.webkit.CookieManager webViewCookieManager, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences preferences, @org.jetbrains.annotations.NotNull()
    com.google.gson.Gson gson, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfig) {
        super();
    }
    
    @javax.inject.Inject()
    public DebugCookieManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.net.CookieManager cookieManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfig) {
        super();
    }
    
    public final void sync() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.debug.cookies.DebugCookie> getAll() {
        return null;
    }
    
    public final void add(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.debug.cookies.DebugCookie cookie) {
    }
    
    public final void remove(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.debug.cookies.DebugCookie cookie) {
    }
    
    private final void add(java.net.CookieManager $this$add, org.wordpress.android.ui.debug.cookies.DebugCookie cookie) {
    }
    
    private final boolean remove(java.net.CookieManager $this$remove, org.wordpress.android.ui.debug.cookies.DebugCookie cookie) {
        return false;
    }
    
    private final void remove(android.webkit.CookieManager $this$remove, org.wordpress.android.ui.debug.cookies.DebugCookie cookie) {
    }
    
    private final void add(android.webkit.CookieManager $this$add, org.wordpress.android.ui.debug.cookies.DebugCookie cookie) {
    }
    
    private final void add(android.content.SharedPreferences $this$add, org.wordpress.android.ui.debug.cookies.DebugCookie cookie) {
    }
    
    private final void remove(android.content.SharedPreferences $this$remove, org.wordpress.android.ui.debug.cookies.DebugCookie cookie) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/debug/cookies/DebugCookieManager$Companion;", "", "()V", "DEBUG_COOKIE_PREFERENCES", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
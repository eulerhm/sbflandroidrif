package com.ichi2.utils;

import android.content.SharedPreferences;
import android.os.Build;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WebViewDebugging {

    private static boolean sHasSetDataDirectory = false;

    @UiThread
    public static void initializeDebugging(SharedPreferences sharedPrefs) {
        /*
        java.lang.RuntimeException: Using WebView from more than one process at once with the same data directory
        is not supported. https://crbug.com/558377 : Lock owner com.ichi2.anki:acra at
        org.chromium.android_webview.AwDataDirLock.a(PG:26)
         */
        boolean enableDebugging = sharedPrefs.getBoolean("html_javascript_debugging", false);
        if (!ListenerUtil.mutListener.listen(26082)) {
            WebView.setWebContentsDebuggingEnabled(enableDebugging);
        }
    }

    /**
     * Throws IllegalStateException if a WebView has been initialized
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static void setDataDirectorySuffix(@NonNull String suffix) {
        if (!ListenerUtil.mutListener.listen(26083)) {
            WebView.setDataDirectorySuffix(suffix);
        }
        if (!ListenerUtil.mutListener.listen(26084)) {
            sHasSetDataDirectory = true;
        }
    }

    public static boolean hasSetDataDirectory() {
        // Implicitly truth requires API >= P
        return sHasSetDataDirectory;
    }
}

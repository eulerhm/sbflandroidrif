/**
 * *************************************************************************************
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2009 Casey Link <unnamedrambler@gmail.com>                             *
 *  Copyright (c) 2014 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.LocaleList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import android.view.ViewConfiguration;
import android.webkit.CookieManager;
import com.ichi2.anki.analytics.AnkiDroidCrashReportDialog;
import com.ichi2.anki.contextmenu.AnkiCardContextMenu;
import com.ichi2.anki.contextmenu.CardBrowserContextMenu;
import com.ichi2.anki.exception.ManuallyReportedException;
import com.ichi2.anki.exception.StorageAccessException;
import com.ichi2.anki.services.BootService;
import com.ichi2.anki.services.NotificationService;
import com.ichi2.compat.CompatHelper;
import com.ichi2.utils.AdaptionUtil;
import com.ichi2.utils.ExceptionUtil;
import com.ichi2.utils.LanguageUtil;
import com.ichi2.anki.analytics.UsageAnalytics;
import com.ichi2.utils.Permissions;
import com.ichi2.utils.WebViewDebugging;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraDialog;
import org.acra.annotation.AcraHttpSender;
import org.acra.annotation.AcraLimiter;
import org.acra.annotation.AcraToast;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.DialogConfigurationBuilder;
import org.acra.config.LimiterData;
import org.acra.config.ToastConfigurationBuilder;
import org.acra.sender.HttpSender;
import java.io.InputStream;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import timber.log.Timber;
import static timber.log.Timber.DebugTree;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Application class.
 */
// https://github.com/ACRA/acra/issues/810
@SuppressLint("NonConstantResourceId")
@AcraCore(buildConfigClass = org.acra.dialog.BuildConfig.class, excludeMatchingSharedPreferencesKeys = { "username", "hkey" }, reportContent = { ReportField.REPORT_ID, ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.PACKAGE_NAME, ReportField.FILE_PATH, ReportField.PHONE_MODEL, ReportField.ANDROID_VERSION, ReportField.BUILD, ReportField.BRAND, ReportField.PRODUCT, ReportField.TOTAL_MEM_SIZE, ReportField.AVAILABLE_MEM_SIZE, ReportField.BUILD_CONFIG, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.STACK_TRACE_HASH, // ReportField.INITIAL_CONFIGURATION,
ReportField.CRASH_CONFIGURATION, // ReportField.DISPLAY,
ReportField.USER_COMMENT, ReportField.USER_APP_START_DATE, ReportField.USER_CRASH_DATE, // ReportField.DROPBOX,
ReportField.LOGCAT, // ReportField.IS_SILENT,
ReportField.INSTALLATION_ID, // ReportField.DEVICE_FEATURES,
ReportField.ENVIRONMENT, // ReportField.SETTINGS_GLOBAL,
ReportField.SHARED_PREFERENCES, // ReportField.APPLICATION_LOG,
ReportField.MEDIA_CODEC_LIST, ReportField.THREAD_DETAILS }, logcatArguments = { "-t", "100", "-v", "time", "ActivityManager:I", "SQLiteLog:W", AnkiDroidApp.TAG + ":D", "*:S" })
@AcraDialog(reportDialogClass = AnkiDroidCrashReportDialog.class, resCommentPrompt = R.string.empty_string, resTitle = R.string.feedback_title, resText = R.string.feedback_default_text, resPositiveButtonText = R.string.feedback_report, resIcon = R.drawable.logo_star_144dp)
@AcraHttpSender(httpMethod = HttpSender.Method.PUT, uri = BuildConfig.ACRA_URL)
@AcraToast(resText = R.string.feedback_auto_toast_text)
@AcraLimiter(exceptionClassLimit = 1000, stacktraceLimit = 1)
public class AnkiDroidApp extends Application {

    public static final String XML_CUSTOM_NAMESPACE = "http://arbitrary.app.namespace/com.ichi2.anki";

    // ACRA constants used for stored preferences
    public static final String FEEDBACK_REPORT_KEY = "reportErrorMode";

    public static final String FEEDBACK_REPORT_ASK = "2";

    public static final String FEEDBACK_REPORT_NEVER = "1";

    public static final String FEEDBACK_REPORT_ALWAYS = "0";

    // Tag for logging messages.
    public static final String TAG = "AnkiDroid";

    // Singleton instance of this class.
    private static AnkiDroidApp sInstance;

    // Constants for gestures
    public static int sSwipeMinDistance = -1;

    public static int sSwipeThresholdVelocity = -1;

    private static int DEFAULT_SWIPE_MIN_DISTANCE;

    private static int DEFAULT_SWIPE_THRESHOLD_VELOCITY;

    /**
     * The latest package version number that included important changes to the database integrity check routine. All
     * collections being upgraded to (or after) this version must run an integrity check as it will contain fixes that
     * all collections should have.
     */
    public static final int CHECK_DB_AT_VERSION = 21000172;

    /**
     * The latest package version number that included changes to the preferences that requires handling. All
     * collections being upgraded to (or after) this version must update preferences.
     */
    public static final int CHECK_PREFERENCES_AT_VERSION = 20500225;

    /**
     * Our ACRA configurations, initialized during onCreate()
     */
    private CoreConfigurationBuilder acraCoreConfigBuilder;

    /**
     * An exception if the WebView subsystem fails to load
     */
    @Nullable
    private Throwable mWebViewError;

    @NonNull
    public static InputStream getResourceAsStream(@NonNull String name) {
        return sInstance.getApplicationContext().getClassLoader().getResourceAsStream(name);
    }

    public static boolean isInitialized() {
        return sInstance == null;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void simulateRestoreFromBackup() {
        if (!ListenerUtil.mutListener.listen(5731)) {
            sInstance = null;
        }
    }

    public static boolean isAcraEnbled(Context context, boolean defaultValue) {
        if (!ListenerUtil.mutListener.listen(5732)) {
            if (!getSharedPrefs(context).contains(ACRA.PREF_DISABLE_ACRA)) {
                // we shouldn't use defaultValue below, as it would be inverted which complicated understanding.
                return defaultValue;
            }
        }
        return !getSharedPrefs(context).getBoolean(ACRA.PREF_DISABLE_ACRA, true);
    }

    /**
     * Get the ACRA ConfigurationBuilder - use this followed by setting it to modify the config
     * @return ConfigurationBuilder for the current ACRA config
     */
    public CoreConfigurationBuilder getAcraCoreConfigBuilder() {
        return acraCoreConfigBuilder;
    }

    /**
     * Set the ACRA ConfigurationBuilder and <b>re-initialize the ACRA system</b> with the contents
     * @param acraCoreConfigBuilder the full ACRA config to initialize ACRA with
     */
    private void setAcraConfigBuilder(CoreConfigurationBuilder acraCoreConfigBuilder) {
        if (!ListenerUtil.mutListener.listen(5733)) {
            this.acraCoreConfigBuilder = acraCoreConfigBuilder;
        }
        if (!ListenerUtil.mutListener.listen(5734)) {
            ACRA.init(this, acraCoreConfigBuilder);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        if (!ListenerUtil.mutListener.listen(5735)) {
            // for API < 17 we update the configuration directly
            super.attachBaseContext(updateContextWithLanguage(base));
        }
    }

    /**
     * On application creation.
     */
    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(5736)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(5740)) {
            if (sInstance != null) {
                if (!ListenerUtil.mutListener.listen(5737)) {
                    Timber.i("onCreate() called multiple times");
                }
                if (!ListenerUtil.mutListener.listen(5739)) {
                    // 5887 - fix crash.
                    if (sInstance.getResources() == null) {
                        if (!ListenerUtil.mutListener.listen(5738)) {
                            Timber.w("Skipping re-initialisation - no resources. Maybe uninstalling app?");
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5741)) {
            sInstance = this;
        }
        // Get preferences
        SharedPreferences preferences = getSharedPrefs(this);
        if (!ListenerUtil.mutListener.listen(5742)) {
            // Setup logging and crash reporting
            acraCoreConfigBuilder = new CoreConfigurationBuilder(this);
        }
        if (!ListenerUtil.mutListener.listen(5747)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(5745)) {
                    // Enable verbose error logging and do method tracing to put the Class name as log tag
                    Timber.plant(new DebugTree());
                }
                if (!ListenerUtil.mutListener.listen(5746)) {
                    setDebugACRAConfig(preferences);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5743)) {
                    Timber.plant(new ProductionCrashReportingTree());
                }
                if (!ListenerUtil.mutListener.listen(5744)) {
                    setProductionACRAConfig(preferences);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5748)) {
            Timber.tag(TAG);
        }
        if (!ListenerUtil.mutListener.listen(5749)) {
            Timber.d("Startup - Application Start");
        }
        if (!ListenerUtil.mutListener.listen(5758)) {
            // Analytics falls back to a sensible default if this is not set.
            if ((ListenerUtil.mutListener.listen(5755) ? (ACRA.isACRASenderServiceProcess() || (ListenerUtil.mutListener.listen(5754) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(5753) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(5752) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(5751) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(5750) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) : (ACRA.isACRASenderServiceProcess() && (ListenerUtil.mutListener.listen(5754) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(5753) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(5752) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(5751) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(5750) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(5757)) {
                        WebViewDebugging.setDataDirectorySuffix("acra");
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5756)) {
                        Timber.w(e, "Failed to set WebView data directory");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5759)) {
            // analytics after ACRA, they both install UncaughtExceptionHandlers but Analytics chains while ACRA does not
            UsageAnalytics.initialize(this);
        }
        if (!ListenerUtil.mutListener.listen(5761)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(5760)) {
                    UsageAnalytics.setDryRun(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5763)) {
            // Stop after analytics and logging are initialised.
            if (ACRA.isACRASenderServiceProcess()) {
                if (!ListenerUtil.mutListener.listen(5762)) {
                    Timber.d("Skipping AnkiDroidApp.onCreate from ACRA sender process");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5765)) {
            if (AdaptionUtil.isUserATestClient()) {
                if (!ListenerUtil.mutListener.listen(5764)) {
                    UIUtils.showThemedToast(this.getApplicationContext(), getString(R.string.user_is_a_robot), false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5766)) {
            CardBrowserContextMenu.ensureConsistentStateWithSharedPreferences(this);
        }
        if (!ListenerUtil.mutListener.listen(5767)) {
            AnkiCardContextMenu.ensureConsistentStateWithSharedPreferences(this);
        }
        if (!ListenerUtil.mutListener.listen(5768)) {
            NotificationChannels.setup(getApplicationContext());
        }
        // Configure WebView to allow file scheme pages to access cookies.
        try {
            if (!ListenerUtil.mutListener.listen(5772)) {
                CookieManager.setAcceptFileSchemeCookies(true);
            }
        } catch (Throwable e) {
            if (!ListenerUtil.mutListener.listen(5769)) {
                // Error may be excessive, but I expect a UnsatisfiedLinkError to be possible here.
                this.mWebViewError = e;
            }
            if (!ListenerUtil.mutListener.listen(5770)) {
                sendExceptionReport(e, "setAcceptFileSchemeCookies");
            }
            if (!ListenerUtil.mutListener.listen(5771)) {
                Timber.e(e, "setAcceptFileSchemeCookies");
            }
            return;
        }
        // Set good default values for swipe detection
        final ViewConfiguration vc = ViewConfiguration.get(this);
        if (!ListenerUtil.mutListener.listen(5773)) {
            DEFAULT_SWIPE_MIN_DISTANCE = vc.getScaledPagingTouchSlop();
        }
        if (!ListenerUtil.mutListener.listen(5774)) {
            DEFAULT_SWIPE_THRESHOLD_VELOCITY = vc.getScaledMinimumFlingVelocity();
        }
        if (!ListenerUtil.mutListener.listen(5775)) {
            // Forget the last deck that was used in the CardBrowser
            CardBrowser.clearLastDeckId();
        }
        if (!ListenerUtil.mutListener.listen(5781)) {
            // Create the AnkiDroid directory if missing. Send exception report if inaccessible.
            if (Permissions.hasStorageAccessPermission(this)) {
                try {
                    String dir = CollectionHelper.getCurrentAnkiDroidDirectory(this);
                    if (!ListenerUtil.mutListener.listen(5780)) {
                        CollectionHelper.initializeAnkiDroidDirectory(dir);
                    }
                } catch (StorageAccessException e) {
                    if (!ListenerUtil.mutListener.listen(5776)) {
                        Timber.e(e, "Could not initialize AnkiDroid directory");
                    }
                    String defaultDir = CollectionHelper.getDefaultAnkiDroidDirectory();
                    if (!ListenerUtil.mutListener.listen(5779)) {
                        if ((ListenerUtil.mutListener.listen(5777) ? (isSdCardMounted() || CollectionHelper.getCurrentAnkiDroidDirectory(this).equals(defaultDir)) : (isSdCardMounted() && CollectionHelper.getCurrentAnkiDroidDirectory(this).equals(defaultDir)))) {
                            if (!ListenerUtil.mutListener.listen(5778)) {
                                // Don't send report if the user is using a custom directory as SD cards trip up here a lot
                                sendExceptionReport(e, "AnkiDroidApp.onCreate");
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5782)) {
            Timber.i("AnkiDroidApp: Starting Services");
        }
        if (!ListenerUtil.mutListener.listen(5783)) {
            new BootService().onReceive(this, new Intent(this, BootService.class));
        }
        // Register BroadcastReceiver NotificationService
        NotificationService ns = new NotificationService();
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        if (!ListenerUtil.mutListener.listen(5784)) {
            lbm.registerReceiver(ns, new IntentFilter(NotificationService.INTENT_ACTION));
        }
    }

    /**
     * Convenience method for accessing Shared preferences
     *
     * @param context Context to get preferences for.
     * @return A SharedPreferences object for this instance of the app.
     */
    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
    @SuppressWarnings("deprecation")
    public static SharedPreferences getSharedPrefs(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static AnkiDroidApp getInstance() {
        return sInstance;
    }

    public static String getCacheStorageDirectory() {
        return sInstance.getCacheDir().getAbsolutePath();
    }

    public static Resources getAppResources() {
        return sInstance.getResources();
    }

    public static boolean isSdCardMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * Used when we don't have an exception to throw, but we know something is wrong and want to diagnose it
     */
    public static void sendExceptionReport(@NonNull String message, String origin) {
        if (!ListenerUtil.mutListener.listen(5785)) {
            sendExceptionReport(new ManuallyReportedException(message), origin, null);
        }
    }

    public static void sendExceptionReport(Throwable e, String origin) {
        if (!ListenerUtil.mutListener.listen(5786)) {
            sendExceptionReport(e, origin, null);
        }
    }

    public static void sendExceptionReport(Throwable e, String origin, @Nullable String additionalInfo) {
        if (!ListenerUtil.mutListener.listen(5787)) {
            sendExceptionReport(e, origin, additionalInfo, false);
        }
    }

    public static void sendExceptionReport(Throwable e, String origin, @Nullable String additionalInfo, boolean onlyIfSilent) {
        if (!ListenerUtil.mutListener.listen(5788)) {
            UsageAnalytics.sendAnalyticsException(e, false);
        }
        if (!ListenerUtil.mutListener.listen(5791)) {
            if (onlyIfSilent) {
                String reportMode = getSharedPrefs(getInstance().getApplicationContext()).getString(AnkiDroidApp.FEEDBACK_REPORT_KEY, FEEDBACK_REPORT_ASK);
                if (!ListenerUtil.mutListener.listen(5790)) {
                    if (!FEEDBACK_REPORT_ALWAYS.equals(reportMode)) {
                        if (!ListenerUtil.mutListener.listen(5789)) {
                            Timber.i("sendExceptionReport - onlyIfSilent true, but ACRA is not 'always accept'. Skipping report send.");
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5792)) {
            ACRA.getErrorReporter().putCustomData("origin", origin);
        }
        if (!ListenerUtil.mutListener.listen(5793)) {
            ACRA.getErrorReporter().putCustomData("additionalInfo", additionalInfo);
        }
        if (!ListenerUtil.mutListener.listen(5794)) {
            ACRA.getErrorReporter().handleException(e);
        }
    }

    /**
     * If you want to make sure that the next exception of any time is posted, you need to clear limiter data
     *
     * @param context the context leading to the directory with ACRA limiter data
     */
    public static void deleteACRALimiterData(Context context) {
        try {
            if (!ListenerUtil.mutListener.listen(5796)) {
                new LimiterData().store(context);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5795)) {
                Timber.w(e, "Unable to clear ACRA limiter data");
            }
        }
    }

    /**
     *  Returns a Context with the correct, saved language, to be attached using attachBase().
     *  For old APIs directly sets language using deprecated functions
     *
     * @param remoteContext The base context offered by attachBase() to be passed to super.attachBase().
     *                      Can be modified here to set correct GUI language.
     */
    @SuppressWarnings("deprecation")
    @NonNull
    public static Context updateContextWithLanguage(@NonNull Context remoteContext) {
        try {
            SharedPreferences preferences;
            // and preferences need mBase directly (is provided by remoteContext during attachBaseContext())
            if (getInstance() != null) {
                preferences = getSharedPrefs(getInstance().getBaseContext());
            } else {
                preferences = getSharedPrefs(remoteContext);
            }
            Configuration langConfig = getLanguageConfig(remoteContext.getResources().getConfiguration(), preferences);
            return remoteContext.createConfigurationContext(langConfig);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5797)) {
                Timber.e(e, "failed to update context with new language");
            }
            if (!ListenerUtil.mutListener.listen(5798)) {
                // during AnkiDroidApp.attachBaseContext() ACRA is not initialized, so the exception report will not be sent
                sendExceptionReport(e, "AnkiDroidApp.updateContextWithLanguage");
            }
            return remoteContext;
        }
    }

    /**
     *  Creates and returns a new configuration with the chosen GUI language that is saved in the preferences
     *
     * @param remoteConfig The configuration of the remote context to set the language for
     * @param prefs
     */
    @SuppressWarnings("deprecation")
    @NonNull
    private static Configuration getLanguageConfig(@NonNull Configuration remoteConfig, @NonNull SharedPreferences prefs) {
        Configuration newConfig = new Configuration(remoteConfig);
        Locale newLocale = LanguageUtil.getLocale(prefs.getString(Preferences.LANGUAGE, ""), prefs);
        if (!ListenerUtil.mutListener.listen(5799)) {
            Timber.d("AnkiDroidApp::getLanguageConfig - setting locale to %s", newLocale);
        }
        if (!ListenerUtil.mutListener.listen(5809)) {
            // API level >=24
            if ((ListenerUtil.mutListener.listen(5804) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(5803) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(5802) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(5801) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(5800) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                // Build list of locale strings, separated by commas: newLocale as first element
                String strLocaleList = newLocale.toLanguageTag();
                if (!ListenerUtil.mutListener.listen(5807)) {
                    // LocaleList must not contain language tags twice, will crash otherwise!
                    if (!strLocaleList.contains(Locale.getDefault().toLanguageTag())) {
                        if (!ListenerUtil.mutListener.listen(5806)) {
                            strLocaleList = strLocaleList + "," + Locale.getDefault().toLanguageTag();
                        }
                    }
                }
                LocaleList newLocaleList = LocaleList.forLanguageTags(strLocaleList);
                if (!ListenerUtil.mutListener.listen(5808)) {
                    // first element of setLocales() is automatically setLocal()
                    newConfig.setLocales(newLocaleList);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5805)) {
                    // API level >=17 but <24
                    newConfig.setLocale(newLocale);
                }
            }
        }
        return newConfig;
    }

    public static boolean initiateGestures(SharedPreferences preferences) {
        boolean enabled = preferences.getBoolean("gestures", false);
        if (!ListenerUtil.mutListener.listen(5840)) {
            if (enabled) {
                int sensitivity = preferences.getInt("swipeSensitivity", 100);
                if (!ListenerUtil.mutListener.listen(5839)) {
                    if ((ListenerUtil.mutListener.listen(5814) ? (sensitivity >= 100) : (ListenerUtil.mutListener.listen(5813) ? (sensitivity <= 100) : (ListenerUtil.mutListener.listen(5812) ? (sensitivity > 100) : (ListenerUtil.mutListener.listen(5811) ? (sensitivity < 100) : (ListenerUtil.mutListener.listen(5810) ? (sensitivity == 100) : (sensitivity != 100))))))) {
                        float sens = (ListenerUtil.mutListener.listen(5820) ? (100.0f % sensitivity) : (ListenerUtil.mutListener.listen(5819) ? (100.0f * sensitivity) : (ListenerUtil.mutListener.listen(5818) ? (100.0f - sensitivity) : (ListenerUtil.mutListener.listen(5817) ? (100.0f + sensitivity) : (100.0f / sensitivity)))));
                        if (!ListenerUtil.mutListener.listen(5829)) {
                            sSwipeMinDistance = (int) ((ListenerUtil.mutListener.listen(5828) ? ((ListenerUtil.mutListener.listen(5824) ? (DEFAULT_SWIPE_MIN_DISTANCE % sens) : (ListenerUtil.mutListener.listen(5823) ? (DEFAULT_SWIPE_MIN_DISTANCE / sens) : (ListenerUtil.mutListener.listen(5822) ? (DEFAULT_SWIPE_MIN_DISTANCE - sens) : (ListenerUtil.mutListener.listen(5821) ? (DEFAULT_SWIPE_MIN_DISTANCE + sens) : (DEFAULT_SWIPE_MIN_DISTANCE * sens))))) % 0.5f) : (ListenerUtil.mutListener.listen(5827) ? ((ListenerUtil.mutListener.listen(5824) ? (DEFAULT_SWIPE_MIN_DISTANCE % sens) : (ListenerUtil.mutListener.listen(5823) ? (DEFAULT_SWIPE_MIN_DISTANCE / sens) : (ListenerUtil.mutListener.listen(5822) ? (DEFAULT_SWIPE_MIN_DISTANCE - sens) : (ListenerUtil.mutListener.listen(5821) ? (DEFAULT_SWIPE_MIN_DISTANCE + sens) : (DEFAULT_SWIPE_MIN_DISTANCE * sens))))) / 0.5f) : (ListenerUtil.mutListener.listen(5826) ? ((ListenerUtil.mutListener.listen(5824) ? (DEFAULT_SWIPE_MIN_DISTANCE % sens) : (ListenerUtil.mutListener.listen(5823) ? (DEFAULT_SWIPE_MIN_DISTANCE / sens) : (ListenerUtil.mutListener.listen(5822) ? (DEFAULT_SWIPE_MIN_DISTANCE - sens) : (ListenerUtil.mutListener.listen(5821) ? (DEFAULT_SWIPE_MIN_DISTANCE + sens) : (DEFAULT_SWIPE_MIN_DISTANCE * sens))))) * 0.5f) : (ListenerUtil.mutListener.listen(5825) ? ((ListenerUtil.mutListener.listen(5824) ? (DEFAULT_SWIPE_MIN_DISTANCE % sens) : (ListenerUtil.mutListener.listen(5823) ? (DEFAULT_SWIPE_MIN_DISTANCE / sens) : (ListenerUtil.mutListener.listen(5822) ? (DEFAULT_SWIPE_MIN_DISTANCE - sens) : (ListenerUtil.mutListener.listen(5821) ? (DEFAULT_SWIPE_MIN_DISTANCE + sens) : (DEFAULT_SWIPE_MIN_DISTANCE * sens))))) - 0.5f) : ((ListenerUtil.mutListener.listen(5824) ? (DEFAULT_SWIPE_MIN_DISTANCE % sens) : (ListenerUtil.mutListener.listen(5823) ? (DEFAULT_SWIPE_MIN_DISTANCE / sens) : (ListenerUtil.mutListener.listen(5822) ? (DEFAULT_SWIPE_MIN_DISTANCE - sens) : (ListenerUtil.mutListener.listen(5821) ? (DEFAULT_SWIPE_MIN_DISTANCE + sens) : (DEFAULT_SWIPE_MIN_DISTANCE * sens))))) + 0.5f))))));
                        }
                        if (!ListenerUtil.mutListener.listen(5838)) {
                            sSwipeThresholdVelocity = (int) ((ListenerUtil.mutListener.listen(5837) ? ((ListenerUtil.mutListener.listen(5833) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY % sens) : (ListenerUtil.mutListener.listen(5832) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY / sens) : (ListenerUtil.mutListener.listen(5831) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY - sens) : (ListenerUtil.mutListener.listen(5830) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY + sens) : (DEFAULT_SWIPE_THRESHOLD_VELOCITY * sens))))) % 0.5f) : (ListenerUtil.mutListener.listen(5836) ? ((ListenerUtil.mutListener.listen(5833) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY % sens) : (ListenerUtil.mutListener.listen(5832) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY / sens) : (ListenerUtil.mutListener.listen(5831) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY - sens) : (ListenerUtil.mutListener.listen(5830) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY + sens) : (DEFAULT_SWIPE_THRESHOLD_VELOCITY * sens))))) / 0.5f) : (ListenerUtil.mutListener.listen(5835) ? ((ListenerUtil.mutListener.listen(5833) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY % sens) : (ListenerUtil.mutListener.listen(5832) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY / sens) : (ListenerUtil.mutListener.listen(5831) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY - sens) : (ListenerUtil.mutListener.listen(5830) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY + sens) : (DEFAULT_SWIPE_THRESHOLD_VELOCITY * sens))))) * 0.5f) : (ListenerUtil.mutListener.listen(5834) ? ((ListenerUtil.mutListener.listen(5833) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY % sens) : (ListenerUtil.mutListener.listen(5832) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY / sens) : (ListenerUtil.mutListener.listen(5831) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY - sens) : (ListenerUtil.mutListener.listen(5830) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY + sens) : (DEFAULT_SWIPE_THRESHOLD_VELOCITY * sens))))) - 0.5f) : ((ListenerUtil.mutListener.listen(5833) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY % sens) : (ListenerUtil.mutListener.listen(5832) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY / sens) : (ListenerUtil.mutListener.listen(5831) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY - sens) : (ListenerUtil.mutListener.listen(5830) ? (DEFAULT_SWIPE_THRESHOLD_VELOCITY + sens) : (DEFAULT_SWIPE_THRESHOLD_VELOCITY * sens))))) + 0.5f))))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5815)) {
                            sSwipeMinDistance = DEFAULT_SWIPE_MIN_DISTANCE;
                        }
                        if (!ListenerUtil.mutListener.listen(5816)) {
                            sSwipeThresholdVelocity = DEFAULT_SWIPE_THRESHOLD_VELOCITY;
                        }
                    }
                }
            }
        }
        return enabled;
    }

    /**
     * Turns ACRA reporting off completely and persists it to shared prefs
     * But expands logcat search in case developer manually re-enables it
     *
     * @param prefs SharedPreferences object the reporting state is persisted in
     */
    private void setDebugACRAConfig(SharedPreferences prefs) {
        if (!ListenerUtil.mutListener.listen(5841)) {
            // Disable crash reporting
            setAcraReportingMode(FEEDBACK_REPORT_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(5842)) {
            prefs.edit().putString(FEEDBACK_REPORT_KEY, FEEDBACK_REPORT_NEVER).apply();
        }
        // Use a wider logcat filter in case crash reporting manually re-enabled
        String[] logcatArgs = { "-t", "300", "-v", "long", "ACRA:S" };
        if (!ListenerUtil.mutListener.listen(5843)) {
            setAcraConfigBuilder(getAcraCoreConfigBuilder().setLogcatArguments(logcatArgs));
        }
    }

    /**
     * Puts ACRA Reporting mode into user-specified mode, with default of "ask first"
     *
     * @param prefs SharedPreferences object the reporting state is persisted in
     */
    private void setProductionACRAConfig(SharedPreferences prefs) {
        if (!ListenerUtil.mutListener.listen(5844)) {
            // Enable or disable crash reporting based on user setting
            setAcraReportingMode(prefs.getString(FEEDBACK_REPORT_KEY, FEEDBACK_REPORT_ASK));
        }
    }

    /**
     * Set the reporting mode for ACRA based on the value of the FEEDBACK_REPORT_KEY preference
     * @param value value of FEEDBACK_REPORT_KEY preference
     */
    public void setAcraReportingMode(String value) {
        SharedPreferences.Editor editor = getSharedPrefs(this).edit();
        if (!ListenerUtil.mutListener.listen(5853)) {
            // Set the ACRA disable value
            if (value.equals(FEEDBACK_REPORT_NEVER)) {
                if (!ListenerUtil.mutListener.listen(5852)) {
                    editor.putBoolean(ACRA.PREF_DISABLE_ACRA, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5845)) {
                    editor.putBoolean(ACRA.PREF_DISABLE_ACRA, false);
                }
                // Switch between auto-report via toast and manual report via dialog
                CoreConfigurationBuilder builder = getAcraCoreConfigBuilder();
                DialogConfigurationBuilder dialogBuilder = builder.getPluginConfigurationBuilder(DialogConfigurationBuilder.class);
                ToastConfigurationBuilder toastBuilder = builder.getPluginConfigurationBuilder(ToastConfigurationBuilder.class);
                if (!ListenerUtil.mutListener.listen(5850)) {
                    if (value.equals(FEEDBACK_REPORT_ALWAYS)) {
                        if (!ListenerUtil.mutListener.listen(5848)) {
                            dialogBuilder.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(5849)) {
                            toastBuilder.setResText(R.string.feedback_auto_toast_text);
                        }
                    } else if (value.equals(FEEDBACK_REPORT_ASK)) {
                        if (!ListenerUtil.mutListener.listen(5846)) {
                            dialogBuilder.setEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(5847)) {
                            toastBuilder.setResText(R.string.feedback_manual_toast_text);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5851)) {
                    setAcraConfigBuilder(builder);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5854)) {
            editor.apply();
        }
    }

    public static Intent getMarketIntent(Context context) {
        final String uri = context.getString(CompatHelper.isKindle() ? R.string.link_market_kindle : R.string.link_market);
        Uri parsed = Uri.parse(uri);
        return new Intent(Intent.ACTION_VIEW, parsed);
    }

    /**
     * Get the url for the feedback page
     * @return
     */
    public static String getFeedbackUrl() {
        // properly translated
        if (isCurrentLanguage("ja")) {
            return getAppResources().getString(R.string.link_help_ja);
        } else if (isCurrentLanguage("zh")) {
            return getAppResources().getString(R.string.link_help_zh);
        } else if (isCurrentLanguage("ar")) {
            return getAppResources().getString(R.string.link_help_ar);
        } else {
            return getAppResources().getString(R.string.link_help);
        }
    }

    /**
     * Get the url for the manual
     * @return
     */
    public static String getManualUrl() {
        // properly translated
        if (isCurrentLanguage("ja")) {
            return getAppResources().getString(R.string.link_manual_ja);
        } else if (isCurrentLanguage("zh")) {
            return getAppResources().getString(R.string.link_manual_zh);
        } else if (isCurrentLanguage("ar")) {
            return getAppResources().getString(R.string.link_manual_ar);
        } else {
            return getAppResources().getString(R.string.link_manual);
        }
    }

    /**
     * Check whether l is the currently set language code
     * @param l ISO2 language code
     * @return
     */
    private static boolean isCurrentLanguage(String l) {
        String pref = getSharedPrefs(sInstance).getString(Preferences.LANGUAGE, "");
        return (ListenerUtil.mutListener.listen(5856) ? (pref.equals(l) && (ListenerUtil.mutListener.listen(5855) ? ("".equals(pref) || Locale.getDefault().getLanguage().equals(l)) : ("".equals(pref) && Locale.getDefault().getLanguage().equals(l)))) : (pref.equals(l) || (ListenerUtil.mutListener.listen(5855) ? ("".equals(pref) || Locale.getDefault().getLanguage().equals(l)) : ("".equals(pref) && Locale.getDefault().getLanguage().equals(l)))));
    }

    public static boolean webViewFailedToLoad() {
        return getInstance().mWebViewError != null;
    }

    @Nullable
    public static String getWebViewErrorMessage() {
        Throwable error = getInstance().mWebViewError;
        if (!ListenerUtil.mutListener.listen(5858)) {
            if (error == null) {
                if (!ListenerUtil.mutListener.listen(5857)) {
                    Timber.w("getWebViewExceptionMessage called without webViewFailedToLoad check");
                }
                return null;
            }
        }
        return ExceptionUtil.getExceptionMessage(error);
    }

    /**
     * A tree which logs necessary data for crash reporting.
     *
     * Requirements:
     * 1) ignore verbose and debug log levels
     * 2) use the fixed AnkiDroidApp.TAG log tag (ACRA filters logcat for it when reporting errors)
     * 3) dynamically discover the class name and prepend it to the message for warn and error
     */
    @SuppressLint("LogNotTimber")
    public static class ProductionCrashReportingTree extends Timber.Tree {

        private static final int CALL_STACK_INDEX = 6;

        private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");

        /**
         * Extract the tag which should be used for the message from the {@code element}. By default
         * this will use the class name without any anonymous class suffixes (e.g., {@code Foo$1}
         * becomes {@code Foo}).
         * <p>
         * Note: This will not be called if an API with a manual tag was called with a non-null tag
         */
        @Nullable
        String createStackElementTag(@NonNull StackTraceElement element) {
            String tag = element.getClassName();
            Matcher m = ANONYMOUS_CLASS.matcher(tag);
            if (!ListenerUtil.mutListener.listen(5860)) {
                if (m.find()) {
                    if (!ListenerUtil.mutListener.listen(5859)) {
                        tag = m.replaceAll("");
                    }
                }
            }
            return tag.substring((ListenerUtil.mutListener.listen(5864) ? (tag.lastIndexOf('.') % 1) : (ListenerUtil.mutListener.listen(5863) ? (tag.lastIndexOf('.') / 1) : (ListenerUtil.mutListener.listen(5862) ? (tag.lastIndexOf('.') * 1) : (ListenerUtil.mutListener.listen(5861) ? (tag.lastIndexOf('.') - 1) : (tag.lastIndexOf('.') + 1))))));
        }

        final String getTag() {
            // because Robolectric runs them on the JVM but on Android the elements are different.
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (!ListenerUtil.mutListener.listen(5870)) {
                if ((ListenerUtil.mutListener.listen(5869) ? (stackTrace.length >= CALL_STACK_INDEX) : (ListenerUtil.mutListener.listen(5868) ? (stackTrace.length > CALL_STACK_INDEX) : (ListenerUtil.mutListener.listen(5867) ? (stackTrace.length < CALL_STACK_INDEX) : (ListenerUtil.mutListener.listen(5866) ? (stackTrace.length != CALL_STACK_INDEX) : (ListenerUtil.mutListener.listen(5865) ? (stackTrace.length == CALL_STACK_INDEX) : (stackTrace.length <= CALL_STACK_INDEX))))))) {
                    // We are in production and should not crash the app for a logging failure
                    return TAG + " unknown class";
                }
            }
            return createStackElementTag(stackTrace[CALL_STACK_INDEX]);
        }

        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t) {
            if (!ListenerUtil.mutListener.listen(5874)) {
                switch(priority) {
                    case Log.VERBOSE:
                    case Log.DEBUG:
                        break;
                    case Log.INFO:
                        if (!ListenerUtil.mutListener.listen(5871)) {
                            Log.i(AnkiDroidApp.TAG, message, t);
                        }
                        break;
                    case Log.WARN:
                        if (!ListenerUtil.mutListener.listen(5872)) {
                            Log.w(AnkiDroidApp.TAG, getTag() + "/ " + message, t);
                        }
                        break;
                    case Log.ERROR:
                    case Log.ASSERT:
                        if (!ListenerUtil.mutListener.listen(5873)) {
                            Log.e(AnkiDroidApp.TAG, getTag() + "/ " + message, t);
                        }
                        break;
                }
            }
        }
    }
}

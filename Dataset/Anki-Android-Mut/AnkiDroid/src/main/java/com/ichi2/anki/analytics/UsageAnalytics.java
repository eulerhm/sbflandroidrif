/**
 * *************************************************************************************
 *  Copyright (c) 2018 Mike Hardy <mike@mikehardy.net>                                   *
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
package com.ichi2.anki.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import com.brsanthu.googleanalytics.GoogleAnalytics;
import com.brsanthu.googleanalytics.GoogleAnalyticsConfig;
import com.brsanthu.googleanalytics.httpclient.OkHttpClientImpl;
import com.brsanthu.googleanalytics.request.DefaultRequest;
import com.brsanthu.googleanalytics.request.EventHit;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.BuildConfig;
import com.ichi2.anki.R;
import com.ichi2.utils.WebViewDebugging;
import org.acra.ACRA;
import org.acra.util.Installation;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UsageAnalytics {

    public static final String ANALYTICS_OPTIN_KEY = "analyticsOptIn";

    private static GoogleAnalytics sAnalytics;

    private static Thread.UncaughtExceptionHandler sOriginalUncaughtExceptionHandler;

    private static boolean sOptIn = false;

    private static String sAnalyticsTrackingId;

    private static int sAnalyticsSamplePercentage = -1;

    /**
     * Initialize the analytics provider - must be called prior to sending anything.
     * Usage after that is static
     * Note: may need to implement sampling strategy internally to limit hits, or not track Reviewer...
     *
     * @param context required to look up the analytics codes for the app
     */
    public static synchronized GoogleAnalytics initialize(Context context) {
        if (!ListenerUtil.mutListener.listen(64)) {
            Timber.i("initialize()");
        }
        if (!ListenerUtil.mutListener.listen(67)) {
            if (sAnalytics == null) {
                if (!ListenerUtil.mutListener.listen(65)) {
                    Timber.d("App tracking id 'tid' = %s", getAnalyticsTag(context));
                }
                GoogleAnalyticsConfig gaConfig = new GoogleAnalyticsConfig().setBatchingEnabled(true).setSamplePercentage(getAnalyticsSamplePercentage(context)).setBatchSize(// until this handles application termination we will lose hits if batch>1
                1);
                if (!ListenerUtil.mutListener.listen(66)) {
                    sAnalytics = GoogleAnalytics.builder().withTrackingId(getAnalyticsTag(context)).withConfig(gaConfig).withDefaultRequest(new AndroidDefaultRequest().setAndroidRequestParameters(context).applicationName(context.getString(R.string.app_name)).applicationVersion(Integer.toString(BuildConfig.VERSION_CODE)).applicationId(BuildConfig.APPLICATION_ID).trackingId(getAnalyticsTag(context)).clientId(Installation.id(context)).anonymizeIp(context.getResources().getBoolean(R.bool.ga_anonymizeIp))).withHttpClient(new OkHttpClientImpl(gaConfig)).build();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68)) {
            installDefaultExceptionHandler();
        }
        SharedPreferences userPrefs = AnkiDroidApp.getSharedPrefs(context);
        if (!ListenerUtil.mutListener.listen(69)) {
            setOptIn(userPrefs.getBoolean(ANALYTICS_OPTIN_KEY, false));
        }
        if (!ListenerUtil.mutListener.listen(70)) {
            userPrefs.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
                if (key.equals(ANALYTICS_OPTIN_KEY)) {
                    setOptIn(sharedPreferences.getBoolean(key, false));
                }
            });
        }
        return sAnalytics;
    }

    private static String getAnalyticsTag(Context context) {
        if (!ListenerUtil.mutListener.listen(72)) {
            if (sAnalyticsTrackingId == null) {
                if (!ListenerUtil.mutListener.listen(71)) {
                    sAnalyticsTrackingId = context.getString(R.string.ga_trackingId);
                }
            }
        }
        return sAnalyticsTrackingId;
    }

    private static int getAnalyticsSamplePercentage(Context context) {
        if (!ListenerUtil.mutListener.listen(79)) {
            if ((ListenerUtil.mutListener.listen(77) ? (sAnalyticsSamplePercentage >= -1) : (ListenerUtil.mutListener.listen(76) ? (sAnalyticsSamplePercentage <= -1) : (ListenerUtil.mutListener.listen(75) ? (sAnalyticsSamplePercentage > -1) : (ListenerUtil.mutListener.listen(74) ? (sAnalyticsSamplePercentage < -1) : (ListenerUtil.mutListener.listen(73) ? (sAnalyticsSamplePercentage != -1) : (sAnalyticsSamplePercentage == -1))))))) {
                if (!ListenerUtil.mutListener.listen(78)) {
                    sAnalyticsSamplePercentage = context.getResources().getInteger(R.integer.ga_sampleFrequency);
                }
            }
        }
        return sAnalyticsSamplePercentage;
    }

    public static void setDevMode() {
        if (!ListenerUtil.mutListener.listen(80)) {
            Timber.d("setDevMode() re-configuring for development analytics tagging");
        }
        if (!ListenerUtil.mutListener.listen(81)) {
            sAnalyticsTrackingId = "UA-125800786-2";
        }
        if (!ListenerUtil.mutListener.listen(82)) {
            sAnalyticsSamplePercentage = 100;
        }
        if (!ListenerUtil.mutListener.listen(83)) {
            reInitialize();
        }
    }

    /**
     * We want to send an analytics hit on any exception, then chain to other handlers (e.g., ACRA)
     */
    private static synchronized void installDefaultExceptionHandler() {
        if (!ListenerUtil.mutListener.listen(84)) {
            sOriginalUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        }
        if (!ListenerUtil.mutListener.listen(85)) {
            Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
                sendAnalyticsException(throwable, true);
                sOriginalUncaughtExceptionHandler.uncaughtException(thread, throwable);
            });
        }
    }

    /**
     * Reset the default exception handler
     */
    private static synchronized void unInstallDefaultExceptionHandler() {
        if (!ListenerUtil.mutListener.listen(86)) {
            Thread.setDefaultUncaughtExceptionHandler(sOriginalUncaughtExceptionHandler);
        }
        if (!ListenerUtil.mutListener.listen(87)) {
            sOriginalUncaughtExceptionHandler = null;
        }
    }

    /**
     * Allow users to enable or disable analytics
     *
     * @param optIn true allows collection of analytics information
     */
    private static synchronized void setOptIn(boolean optIn) {
        if (!ListenerUtil.mutListener.listen(88)) {
            Timber.i("setOptIn(): from %s to %s", sOptIn, optIn);
        }
        if (!ListenerUtil.mutListener.listen(89)) {
            sOptIn = optIn;
        }
        if (!ListenerUtil.mutListener.listen(90)) {
            sAnalytics.flush();
        }
        if (!ListenerUtil.mutListener.listen(91)) {
            sAnalytics.getConfig().setEnabled(optIn);
        }
        if (!ListenerUtil.mutListener.listen(92)) {
            sAnalytics.performSamplingElection();
        }
        if (!ListenerUtil.mutListener.listen(93)) {
            Timber.d("setOptIn() optIn / sAnalytics.config().enabled(): %s/%s", sOptIn, sAnalytics.getConfig().isEnabled());
        }
    }

    /**
     * Determine whether we are disabled or not
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean getOptIn() {
        if (!ListenerUtil.mutListener.listen(94)) {
            Timber.d("getOptIn() status: %s", sOptIn);
        }
        return sOptIn;
    }

    /**
     * Set the analytics up to log things, goes to hit validator. Experimental.
     *
     * @param dryRun set to true if you want to log analytics hit but not dispatch
     */
    public static synchronized void setDryRun(boolean dryRun) {
        if (!ListenerUtil.mutListener.listen(95)) {
            Timber.i("setDryRun(): %s, warning dryRun is experimental", dryRun);
        }
    }

    /**
     * Re-Initialize the analytics provider
     */
    public static synchronized void reInitialize() {
        if (!ListenerUtil.mutListener.listen(96)) {
            // send any pending async hits, re-chain default exception handlers and re-init
            Timber.i("reInitialize()");
        }
        if (!ListenerUtil.mutListener.listen(97)) {
            sAnalytics.flush();
        }
        if (!ListenerUtil.mutListener.listen(98)) {
            sAnalytics = null;
        }
        if (!ListenerUtil.mutListener.listen(99)) {
            unInstallDefaultExceptionHandler();
        }
        if (!ListenerUtil.mutListener.listen(100)) {
            initialize(AnkiDroidApp.getInstance().getApplicationContext());
        }
    }

    /**
     * Submit a screen for aggregation / analysis.
     * Intended for use to determine if / how features are being used
     *
     * @param object the result of Object.getClass().getSimpleName() will be used as the screen tag
     */
    public static void sendAnalyticsScreenView(Object object) {
        if (!ListenerUtil.mutListener.listen(101)) {
            sendAnalyticsScreenView(object.getClass().getSimpleName());
        }
    }

    /**
     * Submit a screen display with a synthetic name for aggregation / analysis
     * Intended for use if your class handles multiple screens you want to track separately
     *
     * @param screenName screenName the name to show in analysis reports
     */
    public static void sendAnalyticsScreenView(String screenName) {
        if (!ListenerUtil.mutListener.listen(102)) {
            Timber.d("sendAnalyticsScreenView(): %s", screenName);
        }
        if (!ListenerUtil.mutListener.listen(103)) {
            if (!getOptIn()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(104)) {
            sAnalytics.screenView().screenName(screenName).sendAsync();
        }
    }

    /**
     * Send an arbitrary analytics event - these should be noun/verb pairs, e.g. "text to speech", "enabled"
     *
     * @param category the category of event, make your own but use a constant so reporting is good
     * @param action   the action the user performed
     */
    public static void sendAnalyticsEvent(@NonNull String category, @NonNull String action) {
        if (!ListenerUtil.mutListener.listen(105)) {
            sendAnalyticsEvent(category, action, Integer.MIN_VALUE, null);
        }
    }

    /**
     * Send a detailed arbitrary analytics event, with noun/verb pairs and extra data if needed
     *
     * @param category the category of event, make your own but use a constant so reporting is good
     * @param action   the action the user performed
     * @param value    A value for the event, Integer.MIN_VALUE signifies caller shouldn't send the value
     * @param label    A label for the event, may be null
     */
    @SuppressWarnings("WeakerAccess")
    public static void sendAnalyticsEvent(@NonNull String category, @NonNull String action, int value, String label) {
        if (!ListenerUtil.mutListener.listen(106)) {
            Timber.d("sendAnalyticsEvent() category/action/value/label: %s/%s/%s/%s", category, action, value, label);
        }
        if (!ListenerUtil.mutListener.listen(107)) {
            if (!getOptIn()) {
                return;
            }
        }
        EventHit event = sAnalytics.event().eventCategory(category).eventAction(action);
        if (!ListenerUtil.mutListener.listen(109)) {
            if (label != null) {
                if (!ListenerUtil.mutListener.listen(108)) {
                    event.eventLabel(label);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(116)) {
            if ((ListenerUtil.mutListener.listen(114) ? (value >= Integer.MIN_VALUE) : (ListenerUtil.mutListener.listen(113) ? (value <= Integer.MIN_VALUE) : (ListenerUtil.mutListener.listen(112) ? (value < Integer.MIN_VALUE) : (ListenerUtil.mutListener.listen(111) ? (value != Integer.MIN_VALUE) : (ListenerUtil.mutListener.listen(110) ? (value == Integer.MIN_VALUE) : (value > Integer.MIN_VALUE))))))) {
                if (!ListenerUtil.mutListener.listen(115)) {
                    event.eventValue(value);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(117)) {
            event.sendAsync();
        }
    }

    /**
     * Send an exception event out for aggregation/analysis, parsed from the exception information
     *
     * @param t     Throwable to send for analysis
     * @param fatal whether it was fatal or not
     */
    public static void sendAnalyticsException(@NonNull Throwable t, boolean fatal) {
        if (!ListenerUtil.mutListener.listen(118)) {
            sendAnalyticsException(getCause(t).toString(), fatal);
        }
    }

    public static Throwable getCause(Throwable t) {
        Throwable cause;
        Throwable result = t;
        if (!ListenerUtil.mutListener.listen(121)) {
            {
                long _loopCounter0 = 0;
                while ((ListenerUtil.mutListener.listen(120) ? (null != (cause = result.getCause()) || (!result.equals(cause))) : (null != (cause = result.getCause()) && (!result.equals(cause))))) {
                    ListenerUtil.loopListener.listen("_loopCounter0", ++_loopCounter0);
                    if (!ListenerUtil.mutListener.listen(119)) {
                        result = cause;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Send an exception event out for aggregation/analysis
     *
     * @param description API limited to 100 characters, truncated here to 100 if needed
     * @param fatal       whether it was fatal or not
     */
    @SuppressWarnings("WeakerAccess")
    public static void sendAnalyticsException(@NonNull String description, boolean fatal) {
        if (!ListenerUtil.mutListener.listen(122)) {
            Timber.d("sendAnalyticsException() description/fatal: %s/%s", description, fatal);
        }
        if (!ListenerUtil.mutListener.listen(123)) {
            if (!sOptIn) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(124)) {
            sAnalytics.exception().exceptionDescription(description).exceptionFatal(fatal).sendAsync();
        }
    }

    protected static boolean canGetDefaultUserAgent() {
        if (!ListenerUtil.mutListener.listen(125)) {
            // if we're not under the ACRA process then we're fine to initialize a WebView
            if (!ACRA.isACRASenderServiceProcess()) {
                return true;
            }
        }
        // If we have a custom data directory, then the crash will not occur.
        return WebViewDebugging.hasSetDataDirectory();
    }

    /**
     * An Android-specific device config generator. Without this it's "Desktop" and unknown for all hardware.
     * It is interesting to us what devices people use though (for instance: is Amazon Kindle support worth it?
     * Is anyone still using e-ink devices? How many people are on tablets? ChromeOS?)
     */
    private static class AndroidDefaultRequest extends DefaultRequest {

        private DefaultRequest setAndroidRequestParameters(Context context) {
            // Are we running on really large screens or small screens? Send raw screen size
            try {
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                if (!ListenerUtil.mutListener.listen(126)) {
                    display.getSize(size);
                }
                if (!ListenerUtil.mutListener.listen(127)) {
                    this.screenResolution(size.x + "x" + size.y);
                }
            } catch (RuntimeException e) {
            }
            if (!ListenerUtil.mutListener.listen(128)) {
                // systemVersion, e.g. "7.1.1"  for Android 7.1.1
                this.customDimension(1, Build.VERSION.RELEASE);
            }
            if (!ListenerUtil.mutListener.listen(129)) {
                // brand e.g. "OnePlus"
                this.customDimension(2, Build.BRAND);
            }
            if (!ListenerUtil.mutListener.listen(130)) {
                // model e.g. "ONEPLUS A6013" for the 6T
                this.customDimension(3, Build.MODEL);
            }
            if (!ListenerUtil.mutListener.listen(131)) {
                // deviceId e.g. "sdm845" for the 6T
                this.customDimension(4, Build.BOARD);
            }
            // For maximum analytics built-in report compatibility we will send the official WebView User-Agent string
            try {
                if (!ListenerUtil.mutListener.listen(135)) {
                    if (canGetDefaultUserAgent()) {
                        if (!ListenerUtil.mutListener.listen(134)) {
                            this.userAgent(WebSettings.getDefaultUserAgent(context));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(133)) {
                            this.userAgent(System.getProperty("http.agent"));
                        }
                    }
                }
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(132)) {
                    // but analytics should never be a show-stopper
                    this.userAgent(System.getProperty("http.agent"));
                }
            }
            return this;
        }
    }

    public static boolean isEnabled() {
        SharedPreferences userPrefs = AnkiDroidApp.getSharedPrefs(AnkiDroidApp.getInstance());
        return userPrefs.getBoolean(ANALYTICS_OPTIN_KEY, false);
    }

    public static class Category {

        public static final String SYNC = "Sync";
    }

    // TOOD: Make this package-protected
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void resetForTests() {
        if (!ListenerUtil.mutListener.listen(136)) {
            sAnalytics = null;
        }
    }
}

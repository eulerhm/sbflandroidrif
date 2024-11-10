package org.wordpress.android.widgets;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\'B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010 \u001a\u00020\u0014H\u0002J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#H\u0002J\u000e\u0010$\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#J\b\u0010%\u001a\u00020\u0018H\u0002J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/widgets/AppRatingDialog;", "", "()V", "CRITERIA_INSTALL_DAYS", "", "CRITERIA_INTERACTIONS", "CRITERIA_LAUNCH_TIMES", "KEY_ASK_LATER_DATE", "", "KEY_INSTALL_DATE", "KEY_INTERACTIONS", "KEY_LAUNCH_TIMES", "KEY_OPT_OUT", "PREF_NAME", "askLaterDate", "Ljava/util/Date;", "installDate", "interactions", "launchTimes", "optOut", "", "preferences", "Landroid/content/SharedPreferences;", "clearSharedPreferences", "", "incrementInteractions", "incrementInteractionTracker", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "init", "context", "Landroid/content/Context;", "setOptOut", "shouldShowRateDialog", "showRateDialog", "fragmentManger", "Landroid/app/FragmentManager;", "showRateDialogIfNeeded", "storeAskLaterDate", "storeInstallDate", "AppRatingDialog", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AppRatingDialog {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.widgets.AppRatingDialog INSTANCE = null;
    private static final java.lang.String PREF_NAME = "rate_wpandroid";
    private static final java.lang.String KEY_INSTALL_DATE = "rate_install_date";
    private static final java.lang.String KEY_LAUNCH_TIMES = "rate_launch_times";
    private static final java.lang.String KEY_OPT_OUT = "rate_opt_out";
    private static final java.lang.String KEY_ASK_LATER_DATE = "rate_ask_later_date";
    private static final java.lang.String KEY_INTERACTIONS = "rate_interactions";
    private static final int CRITERIA_INSTALL_DAYS = 7;
    private static final int CRITERIA_LAUNCH_TIMES = 10;
    private static final int CRITERIA_INTERACTIONS = 10;
    private static java.util.Date installDate;
    private static java.util.Date askLaterDate;
    private static int launchTimes = 0;
    private static int interactions = 0;
    private static boolean optOut = false;
    private static android.content.SharedPreferences preferences;
    
    private AppRatingDialog() {
        super();
    }
    
    /**
     * Call this when the launcher activity is launched.
     */
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Show the rate dialog if the criteria is satisfied.
     * @return true if shown, false otherwise.
     */
    public final boolean showRateDialogIfNeeded(@org.jetbrains.annotations.NotNull()
    android.app.FragmentManager fragmentManger) {
        return false;
    }
    
    /**
     * Called from various places in the app where the user has performed a non-trivial action, such as publishing post
     * or page. We use this to avoid showing the rating dialog to uninvolved users
     */
    public final void incrementInteractions(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat incrementInteractionTracker) {
    }
    
    /**
     * Check whether the rate dialog should be shown or not.
     * @return true if the dialog should be shown
     */
    private final boolean shouldShowRateDialog() {
        return false;
    }
    
    private final void showRateDialog(android.app.FragmentManager fragmentManger) {
    }
    
    /**
     * Clear data other than opt-out in shared preferences - called when the "Later" is pressed or dialog is canceled.
     */
    private final void clearSharedPreferences() {
    }
    
    /**
     * Set opt out flag - when true, the rate dialog will never be shown unless app data is cleared.
     */
    private final void setOptOut(boolean optOut) {
    }
    
    /**
     * Store install date - retrieved from package manager if possible.
     */
    private final void storeInstallDate(android.content.Context context) {
    }
    
    /**
     * Store the date the user asked for being asked again later.
     */
    private final void storeAskLaterDate() {
    }
}
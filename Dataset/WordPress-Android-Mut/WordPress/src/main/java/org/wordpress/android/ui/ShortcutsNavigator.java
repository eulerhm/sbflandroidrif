package org.wordpress.android.ui;

import android.app.Activity;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.util.AppLog;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Screen navigator used when the app is opened from an Android Shortcut.
 */
public class ShortcutsNavigator {

    public static final String ACTION_OPEN_SHORTCUT = "org.wordpress.android.ui.ShortcutsNavigator.ACTION_OPEN_SHORTCUT";

    @Inject
    ShortcutsNavigator() {
    }

    public void showTargetScreen(String action, Activity activity, SiteModel currentSite) {
        Shortcut shortcut = Shortcut.fromActionString(action);
        if (!ListenerUtil.mutListener.listen(26627)) {
            if (shortcut == null) {
                if (!ListenerUtil.mutListener.listen(26626)) {
                    AppLog.e(AppLog.T.MAIN, String.format("Unknown Android Shortcut action[%s]", action));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26635)) {
            switch(shortcut) {
                case OPEN_STATS:
                    if (!ListenerUtil.mutListener.listen(26628)) {
                        AnalyticsTracker.track(AnalyticsTracker.Stat.SHORTCUT_STATS_CLICKED);
                    }
                    if (!ListenerUtil.mutListener.listen(26629)) {
                        ActivityLauncher.viewBlogStats(activity, currentSite);
                    }
                    break;
                case CREATE_NEW_POST:
                    if (!ListenerUtil.mutListener.listen(26630)) {
                        AnalyticsTracker.track(AnalyticsTracker.Stat.SHORTCUT_NEW_POST_CLICKED);
                    }
                    if (!ListenerUtil.mutListener.listen(26631)) {
                        ActivityLauncher.addNewPostForResult(activity, currentSite, false, PagePostCreationSourcesDetail.POST_FROM_SHORTCUT, -1, null);
                    }
                    break;
                case OPEN_NOTIFICATIONS:
                    if (!ListenerUtil.mutListener.listen(26632)) {
                        AnalyticsTracker.track(AnalyticsTracker.Stat.SHORTCUT_NOTIFICATIONS_CLICKED);
                    }
                    if (!ListenerUtil.mutListener.listen(26633)) {
                        ActivityLauncher.viewNotifications(activity);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(26634)) {
                        AppLog.e(AppLog.T.MAIN, String.format("Unknown Android Shortcut[%s]", shortcut));
                    }
            }
        }
    }
}

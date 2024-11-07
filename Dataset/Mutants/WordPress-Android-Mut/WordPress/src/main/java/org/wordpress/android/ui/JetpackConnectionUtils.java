package org.wordpress.android.ui;

import org.wordpress.android.analytics.AnalyticsTracker;
import java.util.HashMap;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Wraps utility methods for Jetpack
 */
class JetpackConnectionUtils {

    /**
     * Adds source as a parameter to the tracked Stat
     * @param stat to be tracked
     * @param source of tracking
     */
    static void trackWithSource(AnalyticsTracker.Stat stat, JetpackConnectionSource source) {
        HashMap<String, String> sourceMap = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(26408)) {
            sourceMap.put("source", source.toString());
        }
        if (!ListenerUtil.mutListener.listen(26409)) {
            AnalyticsTracker.track(stat, sourceMap);
        }
    }

    /**
     * Adds source and reason as a parameter to the tracked Stat
     * @param source of tracking
     */
    static void trackFailureWithSource(JetpackConnectionSource source, String failureReason) {
        HashMap<String, String> paramMap = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(26410)) {
            paramMap.put("source", source.toString());
        }
        if (!ListenerUtil.mutListener.listen(26411)) {
            paramMap.put("reason", failureReason);
        }
        if (!ListenerUtil.mutListener.listen(26412)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.CONNECT_JETPACK_FAILED, paramMap);
        }
    }
}

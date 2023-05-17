/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.utils;

import android.app.Activity;
import android.content.Intent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import ch.threema.app.R;
import ch.threema.app.activities.PinLockActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NavigationUtil {

    private static final Logger logger = LoggerFactory.getLogger(NavigationUtil.class);

    public static void navigateUpToHome(Activity activity) {
        // use this, if there are intent filters to get to this activity
        Intent upIntent = NavUtils.getParentActivityIntent(activity);
        if (!ListenerUtil.mutListener.listen(55143)) {
            if ((ListenerUtil.mutListener.listen(55137) ? (upIntent != null || ((ListenerUtil.mutListener.listen(55136) ? (NavUtils.shouldUpRecreateTask(activity, upIntent) && activity.isTaskRoot()) : (NavUtils.shouldUpRecreateTask(activity, upIntent) || activity.isTaskRoot())))) : (upIntent != null && ((ListenerUtil.mutListener.listen(55136) ? (NavUtils.shouldUpRecreateTask(activity, upIntent) && activity.isTaskRoot()) : (NavUtils.shouldUpRecreateTask(activity, upIntent) || activity.isTaskRoot())))))) {
                if (!ListenerUtil.mutListener.listen(55142)) {
                    TaskStackBuilder.create(activity).addNextIntentWithParentStack(upIntent).startActivities();
                }
            } else {
                try {
                    if (!ListenerUtil.mutListener.listen(55140)) {
                        NavUtils.navigateUpFromSameTask(activity);
                    }
                    if (!ListenerUtil.mutListener.listen(55141)) {
                        activity.overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                    }
                } catch (IllegalArgumentException e) {
                    if (!ListenerUtil.mutListener.listen(55138)) {
                        logger.info("Missing parent activity entry in manifest for " + activity.getComponentName());
                    }
                    if (!ListenerUtil.mutListener.listen(55139)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    public static void navigateToLauncher(Activity activity) {
        if (!ListenerUtil.mutListener.listen(55148)) {
            if (activity != null) {
                // go to launcher home!
                Intent intent = new Intent(Intent.ACTION_MAIN);
                if (!ListenerUtil.mutListener.listen(55144)) {
                    intent.addCategory(Intent.CATEGORY_HOME);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(55145)) {
                        activity.startActivity(intent);
                    }
                    if (!ListenerUtil.mutListener.listen(55147)) {
                        if (!(activity instanceof PinLockActivity)) {
                            if (!ListenerUtil.mutListener.listen(55146)) {
                                activity.finish();
                            }
                        }
                    }
                } catch (RuntimeException ignored) {
                }
            }
        }
    }
}

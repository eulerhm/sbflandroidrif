/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import ch.threema.app.activities.DisableBatteryOptimizationsActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PowermanagerUtil {

    private static final Logger logger = LoggerFactory.getLogger(PowermanagerUtil.class);

    private static final Intent[] POWERMANAGER_INTENTS = { new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")), new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.battery.ui.BatteryActivity")), new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")), new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")), new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")), new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")), new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")), new Intent("miui.intent.action.POWER_HIDE_MODE_APP_LIST").addCategory(Intent.CATEGORY_DEFAULT) };

    private static final Intent[] AUTOSTART_INTENTS = { new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")), new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")), new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")), new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")), new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")), new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.privacypermissionsentry.PermissionTopActivity")), new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.MainActivity")), new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity")), new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")), new Intent().setComponent(new ComponentName("com.transsion.phonemanager", "com.itel.autobootmanager.activity.AutoBootMgrActivity")), new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT) };

    public static final int RESULT_DISABLE_POWERMANAGER = 661;

    public static final int RESULT_DISABLE_AUTOSTART = 662;

    private static boolean isCallable(Context context, Intent intent) {
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return (ListenerUtil.mutListener.listen(55164) ? (list.size() >= 0) : (ListenerUtil.mutListener.listen(55163) ? (list.size() <= 0) : (ListenerUtil.mutListener.listen(55162) ? (list.size() < 0) : (ListenerUtil.mutListener.listen(55161) ? (list.size() != 0) : (ListenerUtil.mutListener.listen(55160) ? (list.size() == 0) : (list.size() > 0))))));
    }

    private static Intent getPowermanagerIntent(Context context) {
        if (!ListenerUtil.mutListener.listen(55166)) {
            {
                long _loopCounter669 = 0;
                for (Intent intent : POWERMANAGER_INTENTS) {
                    ListenerUtil.loopListener.listen("_loopCounter669", ++_loopCounter669);
                    if (!ListenerUtil.mutListener.listen(55165)) {
                        if (isCallable(context, intent)) {
                            return intent;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static Intent getAutostartIntent(Context context) {
        if (!ListenerUtil.mutListener.listen(55168)) {
            {
                long _loopCounter670 = 0;
                for (Intent intent : AUTOSTART_INTENTS) {
                    ListenerUtil.loopListener.listen("_loopCounter670", ++_loopCounter670);
                    if (!ListenerUtil.mutListener.listen(55167)) {
                        if (isCallable(context, intent)) {
                            return intent;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void callPowerManager(@NonNull Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(55172)) {
            {
                long _loopCounter671 = 0;
                for (Intent intent : POWERMANAGER_INTENTS) {
                    ListenerUtil.loopListener.listen("_loopCounter671", ++_loopCounter671);
                    if (!ListenerUtil.mutListener.listen(55171)) {
                        if (isCallable(fragment.getActivity(), intent)) {
                            try {
                                if (!ListenerUtil.mutListener.listen(55170)) {
                                    fragment.startActivityForResult(intent, RESULT_DISABLE_POWERMANAGER);
                                }
                                return;
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(55169)) {
                                    logger.error("Unable to start power manager activity", e);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void callAutostartManager(@NonNull Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(55176)) {
            {
                long _loopCounter672 = 0;
                for (Intent intent : AUTOSTART_INTENTS) {
                    ListenerUtil.loopListener.listen("_loopCounter672", ++_loopCounter672);
                    if (!ListenerUtil.mutListener.listen(55175)) {
                        if (isCallable(fragment.getActivity(), intent)) {
                            try {
                                if (!ListenerUtil.mutListener.listen(55174)) {
                                    fragment.startActivityForResult(intent, RESULT_DISABLE_AUTOSTART);
                                }
                                return;
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(55173)) {
                                    logger.error("Unable to start autostart activity", e);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean hasPowerManagerOption(Context context) {
        return getPowermanagerIntent(context) != null;
    }

    public static boolean hasAutostartOption(Context context) {
        return getAutostartIntent(context) != null;
    }

    public static boolean needsFixing(Context context) {
        return (ListenerUtil.mutListener.listen(55178) ? ((ListenerUtil.mutListener.listen(55177) ? (!DisableBatteryOptimizationsActivity.isWhitelisted(context) && hasAutostartOption(context)) : (!DisableBatteryOptimizationsActivity.isWhitelisted(context) || hasAutostartOption(context))) && hasPowerManagerOption(context)) : ((ListenerUtil.mutListener.listen(55177) ? (!DisableBatteryOptimizationsActivity.isWhitelisted(context) && hasAutostartOption(context)) : (!DisableBatteryOptimizationsActivity.isWhitelisted(context) || hasAutostartOption(context))) || hasPowerManagerOption(context)));
    }
}

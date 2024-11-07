/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.services;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.FixedJobIntentService;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@TargetApi(Build.VERSION_CODES.N)
public class RestrictBackgroundChangedService extends FixedJobIntentService {

    private static final int JOB_ID = 2003;

    public static void enqueueWork(Context context, Intent work) {
        if (!ListenerUtil.mutListener.listen(40770)) {
            enqueueWork(context, RestrictBackgroundChangedService.class, JOB_ID, work);
        }
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!ListenerUtil.mutListener.listen(40776)) {
            if (connMgr != null) {
                ServiceManager serviceManager = ThreemaApplication.getServiceManager();
                if (!ListenerUtil.mutListener.listen(40775)) {
                    if (serviceManager != null) {
                        NotificationService notificationService = serviceManager.getNotificationService();
                        if (!ListenerUtil.mutListener.listen(40774)) {
                            if (notificationService != null) {
                                if (!ListenerUtil.mutListener.listen(40773)) {
                                    switch(connMgr.getRestrictBackgroundStatus()) {
                                        case android.net.ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED:
                                            if (!ListenerUtil.mutListener.listen(40771)) {
                                                // the app should also use less data in the foreground.
                                                notificationService.showNetworkBlockedNotification(false);
                                            }
                                            break;
                                        case android.net.ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED:
                                        // metered network, the app should use less data wherever possible.
                                        case android.net.ConnectivityManager.RESTRICT_BACKGROUND_STATUS_WHITELISTED:
                                            if (!ListenerUtil.mutListener.listen(40772)) {
                                                // the app should use less data in the foreground and background.
                                                notificationService.cancelNetworkBlockedNotification();
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

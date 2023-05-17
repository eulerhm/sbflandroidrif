/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeviceServiceImpl implements DeviceService {

    private Context context;

    private boolean isCanMakeCalls;

    private boolean isCanMakeCallsSet = false;

    public DeviceServiceImpl(Context context) {
        if (!ListenerUtil.mutListener.listen(37522)) {
            this.context = context;
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!ListenerUtil.mutListener.listen(37524)) {
            if (cm != null) {
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                return (ListenerUtil.mutListener.listen(37523) ? (netInfo != null || netInfo.isConnectedOrConnecting()) : (netInfo != null && netInfo.isConnectedOrConnecting()));
            }
        }
        return false;
    }

    public boolean canMakeCalls() {
        if (!ListenerUtil.mutListener.listen(37535)) {
            if (!this.isCanMakeCallsSet) {
                if (!ListenerUtil.mutListener.listen(37533)) {
                    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        PackageManager manager = context.getPackageManager();
                        List<ResolveInfo> list = manager.queryIntentActivities(intent, 0);
                        if (!ListenerUtil.mutListener.listen(37532)) {
                            this.isCanMakeCalls = (ListenerUtil.mutListener.listen(37531) ? (list != null || (ListenerUtil.mutListener.listen(37530) ? (list.size() >= 0) : (ListenerUtil.mutListener.listen(37529) ? (list.size() <= 0) : (ListenerUtil.mutListener.listen(37528) ? (list.size() < 0) : (ListenerUtil.mutListener.listen(37527) ? (list.size() != 0) : (ListenerUtil.mutListener.listen(37526) ? (list.size() == 0) : (list.size() > 0))))))) : (list != null && (ListenerUtil.mutListener.listen(37530) ? (list.size() >= 0) : (ListenerUtil.mutListener.listen(37529) ? (list.size() <= 0) : (ListenerUtil.mutListener.listen(37528) ? (list.size() < 0) : (ListenerUtil.mutListener.listen(37527) ? (list.size() != 0) : (ListenerUtil.mutListener.listen(37526) ? (list.size() == 0) : (list.size() > 0))))))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(37525)) {
                            this.isCanMakeCalls = false;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(37534)) {
                    this.isCanMakeCallsSet = true;
                }
            }
        }
        return this.isCanMakeCalls;
    }
}

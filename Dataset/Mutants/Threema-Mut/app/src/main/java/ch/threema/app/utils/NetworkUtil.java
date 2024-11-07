/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2021 Threema GmbH
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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import ch.threema.app.ThreemaApplication;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NetworkUtil {

    public static boolean isAnyNetworkAvailable() {
        Context context = ThreemaApplication.getAppContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!ListenerUtil.mutListener.listen(55157)) {
            if (connectivityManager == null) {
                return false;
            } else {
                NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
                if (!ListenerUtil.mutListener.listen(55156)) {
                    if ((ListenerUtil.mutListener.listen(55153) ? (networkInfo.length >= 0) : (ListenerUtil.mutListener.listen(55152) ? (networkInfo.length <= 0) : (ListenerUtil.mutListener.listen(55151) ? (networkInfo.length < 0) : (ListenerUtil.mutListener.listen(55150) ? (networkInfo.length != 0) : (ListenerUtil.mutListener.listen(55149) ? (networkInfo.length == 0) : (networkInfo.length > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(55155)) {
                            {
                                long _loopCounter668 = 0;
                                for (NetworkInfo info : networkInfo) {
                                    ListenerUtil.loopListener.listen("_loopCounter668", ++_loopCounter668);
                                    if (!ListenerUtil.mutListener.listen(55154)) {
                                        if (info.getState() == NetworkInfo.State.CONNECTED) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) ThreemaApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!ListenerUtil.mutListener.listen(55159)) {
            if (cm != null) {
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                return (ListenerUtil.mutListener.listen(55158) ? (netInfo != null || netInfo.isConnected()) : (netInfo != null && netInfo.isConnected()));
            }
        }
        return false;
    }
}

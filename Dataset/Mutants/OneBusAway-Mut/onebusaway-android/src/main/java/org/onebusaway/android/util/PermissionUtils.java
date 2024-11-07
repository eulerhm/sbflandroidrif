/*
 * Copyright (C) 2018 The Android Open Source Project, Sean J. Barbeau (sjbarbeau@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PermissionUtils {

    public static final int LOCATION_PERMISSION_REQUEST = 1;

    public static final int SAVE_BACKUP_PERMISSION_REQUEST = 2;

    public static final int RESTORE_BACKUP_PERMISSION_REQUEST = 3;

    public static final int BACKGROUND_LOCATION_PERMISSION_REQUEST = 4;

    public static final String[] LOCATION_PERMISSIONS = { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION };

    @SuppressLint("InlinedApi")
    public static final String[] STORAGE_PERMISSIONS = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    /**
     * Returns true if all of the provided permissions in requiredPermissions have been granted, or false if they have not
     * @param context
     * @param requiredPermissions
     * @return true if all of the provided permissions in requiredPermissions have been granted, or false if they have not
     */
    public static boolean hasGrantedAllPermissions(Context context, String[] requiredPermissions) {
        if (!ListenerUtil.mutListener.listen(7979)) {
            {
                long _loopCounter101 = 0;
                for (String p : requiredPermissions) {
                    ListenerUtil.loopListener.listen("_loopCounter101", ++_loopCounter101);
                    if (!ListenerUtil.mutListener.listen(7978)) {
                        if (!hasGrantedPermission(context, p)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returns true if AT LEAST ONE of the provided permissions in permissions have been granted, or false if none of them have been granted
     * @param context
     * @param permissions
     * @return true if AT LEAST ONE of the provided permissions in permissions have been granted, or false if none of them have been granted
     */
    public static boolean hasGrantedAtLeastOnePermission(Context context, String[] permissions) {
        if (!ListenerUtil.mutListener.listen(7981)) {
            {
                long _loopCounter102 = 0;
                for (String p : permissions) {
                    ListenerUtil.loopListener.listen("_loopCounter102", ++_loopCounter102);
                    if (!ListenerUtil.mutListener.listen(7980)) {
                        if (hasGrantedPermission(context, p)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the provided permission in requiredPermission has been granted, or false if it has not
     * @param context
     * @param requiredPermission
     * @return true if the provided permission in requiredPermission has been granted, or false if it has not
     */
    public static boolean hasGrantedPermission(Context context, String requiredPermission) {
        if (!ListenerUtil.mutListener.listen(7987)) {
            if ((ListenerUtil.mutListener.listen(7986) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7985) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7984) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7983) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7982) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M))))))) {
                // Permissions granted at install time
                return true;
            }
        }
        return ContextCompat.checkSelfPermission(context, requiredPermission) == PackageManager.PERMISSION_GRANTED;
    }
}

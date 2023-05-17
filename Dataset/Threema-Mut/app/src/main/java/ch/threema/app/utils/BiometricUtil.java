/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.biometric.BiometricManager;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.BiometricLockActivity;
import static ch.threema.app.activities.BiometricLockActivity.INTENT_DATA_AUTHENTICATION_TYPE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BiometricUtil {

    private static final Logger logger = LoggerFactory.getLogger(BiometricUtil.class);

    public static boolean isBiometricsSupported(Context context) {
        String toast = context.getString(R.string.biometrics_not_avilable);
        if (!ListenerUtil.mutListener.listen(49542)) {
            if ((ListenerUtil.mutListener.listen(49536) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(49535) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(49534) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(49533) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(49532) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(49541)) {
                    if ((ListenerUtil.mutListener.listen(49537) ? (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) : (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED))) {
                        if (!ListenerUtil.mutListener.listen(49540)) {
                            toast = context.getString(R.string.biometrics_no_permission);
                        }
                    } else {
                        BiometricManager biometricManager = BiometricManager.from(context);
                        if (!ListenerUtil.mutListener.listen(49539)) {
                            switch(biometricManager.canAuthenticate()) {
                                case BiometricManager.BIOMETRIC_SUCCESS:
                                    return true;
                                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                                    if (!ListenerUtil.mutListener.listen(49538)) {
                                        toast = context.getString(R.string.biometrics_not_enrolled);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49543)) {
            Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public static boolean isHardwareSupported(Context context) {
        BiometricManager biometricManager = BiometricManager.from(context);
        int result = biometricManager.canAuthenticate();
        return (ListenerUtil.mutListener.listen(49544) ? (result != BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE || result != BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) : (result != BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE && result != BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE));
    }

    public static void showUnlockDialog(Activity activity, boolean testOnly, int id, String authType) {
        if (!ListenerUtil.mutListener.listen(49545)) {
            showUnlockDialog(activity, null, testOnly, id, authType);
        }
    }

    public static void showUnlockDialog(Activity activity, Fragment fragment, boolean testOnly, int id, String authType) {
        if (!ListenerUtil.mutListener.listen(49546)) {
            logger.debug("launch BiometricLockActivity");
        }
        Intent intent = new Intent(activity != null ? activity : fragment.getActivity(), BiometricLockActivity.class);
        if (!ListenerUtil.mutListener.listen(49548)) {
            if (testOnly) {
                if (!ListenerUtil.mutListener.listen(49547)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_CHECK_ONLY, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49550)) {
            if (authType != null) {
                if (!ListenerUtil.mutListener.listen(49549)) {
                    intent.putExtra(INTENT_DATA_AUTHENTICATION_TYPE, authType);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49568)) {
            if (activity != null) {
                if (!ListenerUtil.mutListener.listen(49567)) {
                    if ((ListenerUtil.mutListener.listen(49564) ? (id >= 0) : (ListenerUtil.mutListener.listen(49563) ? (id <= 0) : (ListenerUtil.mutListener.listen(49562) ? (id > 0) : (ListenerUtil.mutListener.listen(49561) ? (id < 0) : (ListenerUtil.mutListener.listen(49560) ? (id != 0) : (id == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(49566)) {
                            activity.startActivity(intent);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(49565)) {
                            activity.startActivityForResult(intent, id);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(49558)) {
                    if ((ListenerUtil.mutListener.listen(49555) ? (id >= 0) : (ListenerUtil.mutListener.listen(49554) ? (id <= 0) : (ListenerUtil.mutListener.listen(49553) ? (id > 0) : (ListenerUtil.mutListener.listen(49552) ? (id < 0) : (ListenerUtil.mutListener.listen(49551) ? (id != 0) : (id == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(49557)) {
                            fragment.startActivity(intent);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(49556)) {
                            fragment.startActivityForResult(intent, id);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(49559)) {
                    activity = fragment.getActivity();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49569)) {
            activity.overridePendingTransition(0, 0);
        }
    }
}

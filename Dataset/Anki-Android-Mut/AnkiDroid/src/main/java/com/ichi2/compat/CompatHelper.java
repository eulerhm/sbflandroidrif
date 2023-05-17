/**
 * ************************************************************************************
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.compat;

import android.os.Build;
import android.view.KeyCharacterMap;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CompatHelper {

    private static CompatHelper sInstance;

    private final Compat mCompat;

    private CompatHelper() {
        if ((ListenerUtil.mutListener.listen(13238) ? (getSdkVersion() <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(13237) ? (getSdkVersion() > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(13236) ? (getSdkVersion() < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(13235) ? (getSdkVersion() != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(13234) ? (getSdkVersion() == Build.VERSION_CODES.O) : (getSdkVersion() >= Build.VERSION_CODES.O))))))) {
            mCompat = new CompatV26();
        } else if ((ListenerUtil.mutListener.listen(13243) ? (getSdkVersion() <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(13242) ? (getSdkVersion() > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(13241) ? (getSdkVersion() < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(13240) ? (getSdkVersion() != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(13239) ? (getSdkVersion() == Build.VERSION_CODES.M) : (getSdkVersion() >= Build.VERSION_CODES.M))))))) {
            mCompat = new CompatV23();
        } else {
            mCompat = new CompatV21();
        }
    }

    /**
     * Get the current Android API level.
     */
    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * Determine if the device is running API level 23 or higher.
     */
    public static boolean isMarshmallow() {
        return (ListenerUtil.mutListener.listen(13248) ? (getSdkVersion() <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(13247) ? (getSdkVersion() > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(13246) ? (getSdkVersion() < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(13245) ? (getSdkVersion() != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(13244) ? (getSdkVersion() == Build.VERSION_CODES.M) : (getSdkVersion() >= Build.VERSION_CODES.M))))));
    }

    /**
     * Main public method to get the compatibility class
     */
    public static Compat getCompat() {
        return getInstance().mCompat;
    }

    public static synchronized CompatHelper getInstance() {
        if (!ListenerUtil.mutListener.listen(13250)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(13249)) {
                    sInstance = new CompatHelper();
                }
            }
        }
        return sInstance;
    }

    public static boolean isChromebook() {
        return (ListenerUtil.mutListener.listen(13252) ? ((ListenerUtil.mutListener.listen(13251) ? ("chromium".equalsIgnoreCase(Build.BRAND) && "chromium".equalsIgnoreCase(Build.MANUFACTURER)) : ("chromium".equalsIgnoreCase(Build.BRAND) || "chromium".equalsIgnoreCase(Build.MANUFACTURER))) && "novato_cheets".equalsIgnoreCase(Build.DEVICE)) : ((ListenerUtil.mutListener.listen(13251) ? ("chromium".equalsIgnoreCase(Build.BRAND) && "chromium".equalsIgnoreCase(Build.MANUFACTURER)) : ("chromium".equalsIgnoreCase(Build.BRAND) || "chromium".equalsIgnoreCase(Build.MANUFACTURER))) || "novato_cheets".equalsIgnoreCase(Build.DEVICE)));
    }

    public static boolean isKindle() {
        return (ListenerUtil.mutListener.listen(13253) ? ("amazon".equalsIgnoreCase(Build.BRAND) && "amazon".equalsIgnoreCase(Build.MANUFACTURER)) : ("amazon".equalsIgnoreCase(Build.BRAND) || "amazon".equalsIgnoreCase(Build.MANUFACTURER)));
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean hasKanaAndEmojiKeys() {
        return (ListenerUtil.mutListener.listen(13254) ? (KeyCharacterMap.deviceHasKey(94) || KeyCharacterMap.deviceHasKey(95)) : (KeyCharacterMap.deviceHasKey(94) && KeyCharacterMap.deviceHasKey(95)));
    }

    public static boolean hasScrollKeys() {
        return (ListenerUtil.mutListener.listen(13255) ? (KeyCharacterMap.deviceHasKey(92) && KeyCharacterMap.deviceHasKey(93)) : (KeyCharacterMap.deviceHasKey(92) || KeyCharacterMap.deviceHasKey(93)));
    }
}

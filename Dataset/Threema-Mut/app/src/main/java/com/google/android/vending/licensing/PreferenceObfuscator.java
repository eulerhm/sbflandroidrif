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
package com.google.android.vending.licensing;

import android.content.SharedPreferences;
import android.util.Log;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An wrapper for SharedPreferences that transparently performs data obfuscation.
 */
public class PreferenceObfuscator {

    private static final String TAG = "PreferenceObfuscator";

    private final SharedPreferences mPreferences;

    private final Obfuscator mObfuscator;

    private SharedPreferences.Editor mEditor;

    /**
     * Constructor.
     *
     * @param sp A SharedPreferences instance provided by the system.
     * @param o The Obfuscator to use when reading or writing data.
     */
    public PreferenceObfuscator(SharedPreferences sp, Obfuscator o) {
        mPreferences = sp;
        mObfuscator = o;
        if (!ListenerUtil.mutListener.listen(73112)) {
            mEditor = null;
        }
    }

    public void putString(String key, String value) {
        if (!ListenerUtil.mutListener.listen(73114)) {
            if (mEditor == null) {
                if (!ListenerUtil.mutListener.listen(73113)) {
                    mEditor = mPreferences.edit();
                }
            }
        }
        String obfuscatedValue = mObfuscator.obfuscate(value, key);
        if (!ListenerUtil.mutListener.listen(73115)) {
            mEditor.putString(key, obfuscatedValue);
        }
    }

    public String getString(String key, String defValue) {
        String result;
        String value = mPreferences.getString(key, null);
        if (value != null) {
            try {
                result = mObfuscator.unobfuscate(value, key);
            } catch (ValidationException e) {
                if (!ListenerUtil.mutListener.listen(73116)) {
                    // Unable to unobfuscate, data corrupt or tampered
                    Log.w(TAG, "Validation error while reading preference: " + key);
                }
                result = defValue;
            }
        } else {
            // Preference not found
            result = defValue;
        }
        return result;
    }

    public void commit() {
        if (!ListenerUtil.mutListener.listen(73119)) {
            if (mEditor != null) {
                if (!ListenerUtil.mutListener.listen(73117)) {
                    mEditor.commit();
                }
                if (!ListenerUtil.mutListener.listen(73118)) {
                    mEditor = null;
                }
            }
        }
    }
}

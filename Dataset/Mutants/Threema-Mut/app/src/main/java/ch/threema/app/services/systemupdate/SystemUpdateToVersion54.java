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
package ch.threema.app.services.systemupdate;

import android.content.Context;
import android.content.SharedPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.preference.PreferenceManager;
import ch.threema.app.R;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UpdateSystemService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * migrate locking prefs
 */
public class SystemUpdateToVersion54 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion54.class);

    private Context context;

    public SystemUpdateToVersion54(Context context) {
        if (!ListenerUtil.mutListener.listen(36566)) {
            this.context = context;
        }
    }

    @Override
    public boolean runDirectly() {
        // Note: PreferenceService is not available at this time if a passphrase has been set!
        String lockMechanism = PreferenceService.LockingMech_NONE;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        if (!ListenerUtil.mutListener.listen(36568)) {
            if (sharedPreferences.contains(context.getString(R.string.preferences__lock_mechanism))) {
                if (!ListenerUtil.mutListener.listen(36567)) {
                    lockMechanism = sharedPreferences.getString(context.getString(R.string.preferences__lock_mechanism), PreferenceService.LockingMech_NONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36573)) {
            if (!PreferenceService.LockingMech_NONE.equals(lockMechanism)) {
                if (!ListenerUtil.mutListener.listen(36572)) {
                    if ((ListenerUtil.mutListener.listen(36569) ? (sharedPreferences.getBoolean("pref_key_system_lock_enabled", false) && sharedPreferences.getBoolean("pref_key_pin_lock_enabled", false)) : (sharedPreferences.getBoolean("pref_key_system_lock_enabled", false) || sharedPreferences.getBoolean("pref_key_pin_lock_enabled", false)))) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (!ListenerUtil.mutListener.listen(36570)) {
                            editor.putBoolean("pref_app_lock_enabled", true);
                        }
                        if (!ListenerUtil.mutListener.listen(36571)) {
                            editor.commit();
                        }
                    }
                }
            }
        }
        // clean up old prefs
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!ListenerUtil.mutListener.listen(36574)) {
            editor.remove("pref_key_system_lock_enabled");
        }
        if (!ListenerUtil.mutListener.listen(36575)) {
            editor.remove("pref_key_pin_lock_enabled");
        }
        if (!ListenerUtil.mutListener.listen(36576)) {
            editor.commit();
        }
        return true;
    }

    @Override
    public boolean runASync() {
        return true;
    }

    @Override
    public String getText() {
        return "version 54";
    }
}

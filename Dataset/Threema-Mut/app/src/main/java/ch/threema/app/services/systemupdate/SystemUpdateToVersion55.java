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

import android.os.Environment;
import android.util.Log;
import java.io.File;
import ch.threema.app.BuildConfig;
import ch.threema.app.services.UpdateSystemService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Remove old message log
 */
public class SystemUpdateToVersion55 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final String TAG = "SystemUpdateToVersion55";

    public SystemUpdateToVersion55() {
    }

    @Override
    public boolean runDirectly() {
        try {
            final File threemaDir = new File(Environment.getExternalStorageDirectory(), BuildConfig.MEDIA_PATH);
            if (!ListenerUtil.mutListener.listen(36363)) {
                if (threemaDir.exists()) {
                    final File messageLog = new File(threemaDir, "message_log.txt");
                    final File debugLog = new File(threemaDir, "debug_log.txt");
                    final boolean hasMessageLog = (ListenerUtil.mutListener.listen(36354) ? (messageLog.exists() || messageLog.isFile()) : (messageLog.exists() && messageLog.isFile()));
                    final boolean hasDebugLog = (ListenerUtil.mutListener.listen(36355) ? (debugLog.exists() || debugLog.isFile()) : (debugLog.exists() && debugLog.isFile()));
                    if (!ListenerUtil.mutListener.listen(36362)) {
                        if ((ListenerUtil.mutListener.listen(36356) ? (hasMessageLog || !hasDebugLog) : (hasMessageLog && !hasDebugLog))) {
                            // Rename
                            boolean success = messageLog.renameTo(debugLog);
                            if (!ListenerUtil.mutListener.listen(36361)) {
                                if (!success) {
                                    if (!ListenerUtil.mutListener.listen(36360)) {
                                        Log.w(TAG, "Renaming message log failed");
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(36357) ? (hasMessageLog || hasDebugLog) : (hasMessageLog && hasDebugLog))) {
                            // Delete
                            boolean success = messageLog.delete();
                            if (!ListenerUtil.mutListener.listen(36359)) {
                                if (!success) {
                                    if (!ListenerUtil.mutListener.listen(36358)) {
                                        Log.w(TAG, "Removing message log failed");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(36353)) {
                Log.w(TAG, "Exception: " + e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean runASync() {
        return true;
    }

    @Override
    public String getText() {
        return "version 55";
    }
}

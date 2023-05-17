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

import android.content.Context;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeviceIdUtil {

    private static final Logger logger = LoggerFactory.getLogger(DeviceIdUtil.class);

    private static final String DEVICE_ID_FILENAME = "device_id";

    public static String getDeviceId(Context context) {
        String deviceId = null;
        File deviceIdFile = new File(context.getFilesDir(), DEVICE_ID_FILENAME);
        if (!ListenerUtil.mutListener.listen(50976)) {
            if (deviceIdFile.exists()) {
                try {
                    if (!ListenerUtil.mutListener.listen(50975)) {
                        deviceId = FileUtils.readFileToString(deviceIdFile);
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(50974)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50980)) {
            if (deviceId == null) {
                if (!ListenerUtil.mutListener.listen(50977)) {
                    deviceId = UUID.randomUUID().toString();
                }
                try {
                    if (!ListenerUtil.mutListener.listen(50979)) {
                        FileUtils.writeStringToFile(deviceIdFile, deviceId);
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(50978)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return deviceId;
    }
}

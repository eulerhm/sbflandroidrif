/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.sql.SQLException;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemUpdateToVersion63 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion63.class);

    private Context context;

    public SystemUpdateToVersion63(Context context) {
        if (!ListenerUtil.mutListener.listen(36430)) {
            this.context = context;
        }
    }

    @Override
    public boolean runDirectly() throws SQLException {
        return true;
    }

    @Override
    public boolean runASync() {
        if (!ListenerUtil.mutListener.listen(36431)) {
            // delete obsolete temporary dirs
            deleteDir(new File(context.getFilesDir(), "tmp"));
        }
        if (!ListenerUtil.mutListener.listen(36432)) {
            deleteDir(new File(context.getExternalFilesDir(null), "data.blob"));
        }
        if (!ListenerUtil.mutListener.listen(36434)) {
            if (ConfigUtils.useContentUris()) {
                if (!ListenerUtil.mutListener.listen(36433)) {
                    deleteDir(new File(context.getExternalFilesDir(null), "tmp"));
                }
            }
        }
        return true;
    }

    private void deleteDir(File tmpPath) {
        if (!ListenerUtil.mutListener.listen(36437)) {
            if (tmpPath.exists()) {
                try {
                    if (!ListenerUtil.mutListener.listen(36436)) {
                        FileUtils.deleteDirectory(tmpPath);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(36435)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public String getText() {
        return "clean old temp directory";
    }
}

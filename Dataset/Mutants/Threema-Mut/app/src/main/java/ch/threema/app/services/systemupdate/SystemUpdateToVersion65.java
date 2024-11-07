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

import android.Manifest;
import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import androidx.annotation.RequiresPermission;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.utils.AndroidContactUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.localcrypto.MasterKeyLockedException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemUpdateToVersion65 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion65.class);

    private Context context;

    public SystemUpdateToVersion65(Context context) {
        if (!ListenerUtil.mutListener.listen(36454)) {
            this.context = context;
        }
    }

    @Override
    public boolean runDirectly() throws SQLException {
        return true;
    }

    @Override
    public boolean runASync() {
        if (!ListenerUtil.mutListener.listen(36455)) {
            if (!ConfigUtils.isPermissionGranted(ThreemaApplication.getAppContext(), Manifest.permission.WRITE_CONTACTS)) {
                // best effort
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(36456)) {
            forceContactResync();
        }
        return true;
    }

    @RequiresPermission(Manifest.permission.WRITE_CONTACTS)
    private void forceContactResync() {
        if (!ListenerUtil.mutListener.listen(36457)) {
            logger.info("Force a contacts resync");
        }
        AndroidContactUtil androidContactUtil = AndroidContactUtil.getInstance();
        if (!ListenerUtil.mutListener.listen(36458)) {
            androidContactUtil.deleteAllThreemaRawContacts();
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(36464)) {
            if (serviceManager != null) {
                PreferenceService preferenceService = serviceManager.getPreferenceService();
                if (!ListenerUtil.mutListener.listen(36463)) {
                    if (preferenceService != null) {
                        if (!ListenerUtil.mutListener.listen(36462)) {
                            if (preferenceService.isSyncContacts()) {
                                final SynchronizeContactsService synchronizeContactService;
                                try {
                                    synchronizeContactService = serviceManager.getSynchronizeContactsService();
                                    if (!ListenerUtil.mutListener.listen(36461)) {
                                        if (synchronizeContactService != null) {
                                            if (!ListenerUtil.mutListener.listen(36460)) {
                                                synchronizeContactService.instantiateSynchronizationAndRun();
                                            }
                                        }
                                    }
                                } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                                    if (!ListenerUtil.mutListener.listen(36459)) {
                                        logger.error("Exception", e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getText() {
        return "force a contacts resync";
    }
}

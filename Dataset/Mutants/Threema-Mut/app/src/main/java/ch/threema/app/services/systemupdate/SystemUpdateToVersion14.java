/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.utils.LogUtil;
import ch.threema.localcrypto.MasterKeyLockedException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemUpdateToVersion14 implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion14.class);

    @Override
    public boolean runDirectly() {
        return true;
    }

    @Override
    public boolean runASync() {
        // check if auto sync is enabled
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(36114)) {
            if (serviceManager != null) {
                PreferenceService preferenceService = serviceManager.getPreferenceService();
                if (!ListenerUtil.mutListener.listen(36113)) {
                    if (preferenceService != null) {
                        if (!ListenerUtil.mutListener.listen(36112)) {
                            if (preferenceService.isSyncContacts()) {
                                // disable sync
                                final SynchronizeContactsService synchronizeContactService;
                                try {
                                    synchronizeContactService = serviceManager.getSynchronizeContactsService();
                                    if (!ListenerUtil.mutListener.listen(36111)) {
                                        if (synchronizeContactService != null) {
                                            if (!ListenerUtil.mutListener.listen(36110)) {
                                                synchronizeContactService.disableSync(new Runnable() {

                                                    @Override
                                                    public void run() {
                                                        if (!ListenerUtil.mutListener.listen(36109)) {
                                                            synchronizeContactService.enableSync();
                                                        }
                                                    }
                                                });
                                            }
                                            return true;
                                        }
                                    }
                                } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                                    if (!ListenerUtil.mutListener.listen(36108)) {
                                        logger.error("Exception", e);
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

    @Override
    public String getText() {
        return "version 13";
    }
}

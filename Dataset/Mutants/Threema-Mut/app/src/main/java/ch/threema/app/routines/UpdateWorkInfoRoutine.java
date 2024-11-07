/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.routines;

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.DeviceService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.license.LicenseServiceUser;
import ch.threema.app.services.license.UserCredentials;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.client.APIConnector;
import ch.threema.client.IdentityStoreInterface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 *  Send (only in work build) the infos to the work info resource
 */
public class UpdateWorkInfoRoutine implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UpdateWorkInfoRoutine.class);

    private final APIConnector apiConnector;

    private final IdentityStoreInterface identityStore;

    private final DeviceService deviceService;

    private final LicenseService licenseService;

    private final Context context;

    public UpdateWorkInfoRoutine(Context context, APIConnector apiConnector, IdentityStoreInterface identityStore, DeviceService deviceService, LicenseService licenseService) {
        this.context = context;
        this.apiConnector = apiConnector;
        this.identityStore = identityStore;
        this.deviceService = deviceService;
        this.licenseService = licenseService;
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(34941)) {
            if (!ConfigUtils.isWorkBuild()) {
                // ignore on a not-work build
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(34951)) {
            if ((ListenerUtil.mutListener.listen(34942) ? (this.deviceService == null && this.deviceService.isOnline()) : (this.deviceService == null || this.deviceService.isOnline()))) {
                if (!ListenerUtil.mutListener.listen(34944)) {
                    logger.debug("update work info");
                }
                UserCredentials userCredentials = ((LicenseServiceUser) this.licenseService).loadCredentials();
                if (!ListenerUtil.mutListener.listen(34946)) {
                    if (userCredentials == null) {
                        if (!ListenerUtil.mutListener.listen(34945)) {
                            logger.error("no credentials found");
                        }
                        return;
                    }
                }
                try {
                    String mdmFirstName = AppRestrictionUtil.getStringRestriction(this.context.getString(R.string.restriction__firstname));
                    String mdmLastName = AppRestrictionUtil.getStringRestriction(this.context.getString(R.string.restriction__lastname));
                    String mdmCSI = AppRestrictionUtil.getStringRestriction(this.context.getString(R.string.restriction__csi));
                    String mdmCategory = AppRestrictionUtil.getStringRestriction(this.context.getString(R.string.restriction__category));
                    if (!ListenerUtil.mutListener.listen(34950)) {
                        if (this.apiConnector.updateWorkInfo(userCredentials.username, userCredentials.password, this.identityStore, mdmFirstName, mdmLastName, mdmCSI, mdmCategory)) {
                            if (!ListenerUtil.mutListener.listen(34949)) {
                                logger.debug("work info successfully updated");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(34948)) {
                                logger.error("failed to update work info");
                            }
                        }
                    }
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(34947)) {
                        logger.error("Exception", x);
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(34943)) {
                    logger.error("device is not online");
                }
            }
        }
    }

    /**
     *  start a update in a new thread
     *  return the new created thread
     */
    public static Thread start() {
        // try to get all instances
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(34952)) {
            if (serviceManager == null) {
                return null;
            }
        }
        try {
            Thread t = new Thread(new UpdateWorkInfoRoutine(serviceManager.getContext(), serviceManager.getAPIConnector(), serviceManager.getIdentityStore(), serviceManager.getDeviceService(), serviceManager.getLicenseService()));
            if (!ListenerUtil.mutListener.listen(34954)) {
                t.start();
            }
            return t;
        } catch (FileSystemNotPresentException x) {
            if (!ListenerUtil.mutListener.listen(34953)) {
                logger.error("File system not present", x);
            }
        }
        return null;
    }
}

/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.receivers;

import android.content.BroadcastReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class ActionBroadcastReceiver extends BroadcastReceiver {

    private static final Logger logger = LoggerFactory.getLogger(ActionBroadcastReceiver.class);

    protected static final String TAG = "ActionBroadcastReceiver";

    protected static final int WEARABLE_CONNECTION_LINGER = 1000 * 5;

    protected MessageService messageService;

    protected LifetimeService lifetimeService;

    protected NotificationService notificationService;

    protected ContactService contactService;

    protected DistributionListService distributionListService;

    protected GroupService groupService;

    public ActionBroadcastReceiver() {
        if (!ListenerUtil.mutListener.listen(34317)) {
            this.instantiate();
        }
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(34319)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(34318)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.messageService, this.lifetimeService, this.notificationService, this.contactService, this.distributionListService, this.groupService);
    }

    protected void instantiate() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(34327)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(34321)) {
                        this.messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(34322)) {
                        this.lifetimeService = serviceManager.getLifetimeService();
                    }
                    if (!ListenerUtil.mutListener.listen(34323)) {
                        this.notificationService = serviceManager.getNotificationService();
                    }
                    if (!ListenerUtil.mutListener.listen(34324)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(34325)) {
                        this.distributionListService = serviceManager.getDistributionListService();
                    }
                    if (!ListenerUtil.mutListener.listen(34326)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(34320)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }
}

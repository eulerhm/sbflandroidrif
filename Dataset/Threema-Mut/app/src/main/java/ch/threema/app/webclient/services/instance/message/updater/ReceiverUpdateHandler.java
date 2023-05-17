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
package ch.threema.app.webclient.services.instance.message.updater;

import org.msgpack.core.MessagePackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.annotation.WorkerThread;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.Contact;
import ch.threema.app.webclient.converter.DistributionList;
import ch.threema.app.webclient.converter.Group;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.converter.Receiver;
import ch.threema.app.webclient.converter.Utils;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageUpdater;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Notify Threema Web about changes to receivers (contacts, groups, distribution lists).
 */
@WorkerThread
public class ReceiverUpdateHandler extends MessageUpdater {

    private static final Logger logger = LoggerFactory.getLogger(ReceiverUpdateHandler.class);

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Protocol.ARGUMENT_MODE_NEW, Protocol.ARGUMENT_MODE_MODIFIED, Protocol.ARGUMENT_MODE_REMOVED })
    private @interface UpdateMode {
    }

    // Handler
    @NonNull
    private final HandlerExecutor handler;

    // Listeners
    private final ContactListener contactListener;

    private final GroupListener groupListener;

    private final DistributionListListener distributionListListener;

    // Dispatchers
    private MessageDispatcher dispatcher;

    // Services
    private final SynchronizeContactsService synchronizeContactsService;

    @AnyThread
    public ReceiverUpdateHandler(@NonNull HandlerExecutor handler, MessageDispatcher dispatcher, SynchronizeContactsService synchronizeContactsService) {
        super(Protocol.SUB_TYPE_RECEIVER);
        this.handler = handler;
        if (!ListenerUtil.mutListener.listen(63807)) {
            this.dispatcher = dispatcher;
        }
        this.synchronizeContactsService = synchronizeContactsService;
        this.contactListener = new ContactListener();
        this.groupListener = new GroupListener();
        this.distributionListListener = new DistributionListListener();
    }

    @Override
    public void register() {
        if (!ListenerUtil.mutListener.listen(63808)) {
            logger.debug("register()");
        }
        if (!ListenerUtil.mutListener.listen(63809)) {
            ListenerManager.contactListeners.add(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(63810)) {
            ListenerManager.groupListeners.add(this.groupListener);
        }
        if (!ListenerUtil.mutListener.listen(63811)) {
            ListenerManager.distributionListListeners.add(this.distributionListListener);
        }
    }

    /**
     *  This method can be safely called multiple times without any negative side effects
     */
    @Override
    public void unregister() {
        if (!ListenerUtil.mutListener.listen(63812)) {
            logger.debug("unregister()");
        }
        if (!ListenerUtil.mutListener.listen(63813)) {
            ListenerManager.contactListeners.remove(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(63814)) {
            ListenerManager.groupListeners.remove(this.groupListener);
        }
        if (!ListenerUtil.mutListener.listen(63815)) {
            ListenerManager.distributionListListeners.remove(this.distributionListListener);
        }
    }

    @AnyThread
    private void updateContact(final ContactModel contact, @UpdateMode String mode) {
        if (!ListenerUtil.mutListener.listen(63818)) {
            handler.post(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    try {
                        // Convert contact and dispatch
                        MsgpackObjectBuilder data = Contact.convert(contact);
                        if (!ListenerUtil.mutListener.listen(63817)) {
                            ReceiverUpdateHandler.this.update(new Utils.ModelWrapper(contact), data, mode);
                        }
                    } catch (ConversionException e) {
                        if (!ListenerUtil.mutListener.listen(63816)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            });
        }
    }

    @AnyThread
    private void updateGroup(GroupModel group, @UpdateMode String mode) {
        if (!ListenerUtil.mutListener.listen(63821)) {
            handler.post(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    try {
                        // Convert contact and dispatch
                        MsgpackObjectBuilder data = Group.convert(group);
                        if (!ListenerUtil.mutListener.listen(63820)) {
                            ReceiverUpdateHandler.this.update(new Utils.ModelWrapper(group), data, mode);
                        }
                    } catch (ConversionException e) {
                        if (!ListenerUtil.mutListener.listen(63819)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            });
        }
    }

    @AnyThread
    private void updateDistributionList(DistributionListModel distributionList, @UpdateMode String mode) {
        if (!ListenerUtil.mutListener.listen(63824)) {
            handler.post(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    try {
                        // Convert contact and dispatch
                        MsgpackObjectBuilder data = DistributionList.convert(distributionList);
                        if (!ListenerUtil.mutListener.listen(63823)) {
                            ReceiverUpdateHandler.this.update(new Utils.ModelWrapper(distributionList), data, mode);
                        }
                    } catch (ConversionException e) {
                        if (!ListenerUtil.mutListener.listen(63822)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            });
        }
    }

    private void update(final Utils.ModelWrapper model, final MsgpackObjectBuilder data, @UpdateMode final String mode) {
        try {
            // Convert message and prepare arguments
            MsgpackObjectBuilder args = Receiver.getArguments(model);
            if (!ListenerUtil.mutListener.listen(63826)) {
                args.put(Protocol.ARGUMENT_MODE, mode);
            }
            if (!ListenerUtil.mutListener.listen(63827)) {
                // Send message
                logger.debug("Sending receiver update");
            }
            if (!ListenerUtil.mutListener.listen(63828)) {
                send(dispatcher, data, args);
            }
        } catch (ConversionException | MessagePackException e) {
            if (!ListenerUtil.mutListener.listen(63825)) {
                logger.error("Exception", e);
            }
        }
    }

    /**
     *  Listen for contact changes.
     */
    @AnyThread
    private class ContactListener implements ch.threema.app.listeners.ContactListener {

        @Override
        public void onModified(ContactModel modifiedContactModel) {
            if (!ListenerUtil.mutListener.listen(63830)) {
                if (synchronizeContactsService.isFullSyncInProgress()) {
                    if (!ListenerUtil.mutListener.listen(63829)) {
                        // updates and send the entire receivers list as soon as the sync is done.
                        logger.debug("Ignoring onModified (contact sync in progress)");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(63831)) {
                updateContact(modifiedContactModel, Protocol.ARGUMENT_MODE_MODIFIED);
            }
        }

        @Override
        public void onNew(ContactModel createdContactModel) {
            if (!ListenerUtil.mutListener.listen(63832)) {
                updateContact(createdContactModel, Protocol.ARGUMENT_MODE_NEW);
            }
        }

        @Override
        public void onRemoved(ContactModel removedContactModel) {
            if (!ListenerUtil.mutListener.listen(63833)) {
                updateContact(removedContactModel, Protocol.ARGUMENT_MODE_REMOVED);
            }
        }
    }

    @AnyThread
    private class GroupListener implements ch.threema.app.listeners.GroupListener {

        @Override
        public void onCreate(GroupModel newGroupModel) {
            if (!ListenerUtil.mutListener.listen(63834)) {
                logger.debug("Group Listener: onCreate");
            }
            if (!ListenerUtil.mutListener.listen(63835)) {
                updateGroup(newGroupModel, Protocol.ARGUMENT_MODE_NEW);
            }
        }

        @Override
        public void onRename(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(63836)) {
                logger.debug("Group Listener: onRename");
            }
            if (!ListenerUtil.mutListener.listen(63837)) {
                updateGroup(groupModel, Protocol.ARGUMENT_MODE_MODIFIED);
            }
        }

        @Override
        public void onRemove(GroupModel removedGroupModel) {
            if (!ListenerUtil.mutListener.listen(63838)) {
                // TODO: We should probably send an empty response
                logger.debug("Group Listener: onRemove");
            }
            if (!ListenerUtil.mutListener.listen(63839)) {
                updateGroup(removedGroupModel, Protocol.ARGUMENT_MODE_REMOVED);
            }
        }

        @Override
        public void onNewMember(GroupModel group, String newIdentity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(63840)) {
                logger.debug("Group Listener: onNewMember");
            }
            if (!ListenerUtil.mutListener.listen(63841)) {
                updateGroup(group, Protocol.ARGUMENT_MODE_MODIFIED);
            }
        }

        @Override
        public void onMemberLeave(GroupModel group, String identity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(63842)) {
                logger.debug("Group Listener: onMemberLeave");
            }
            if (!ListenerUtil.mutListener.listen(63843)) {
                updateGroup(group, Protocol.ARGUMENT_MODE_MODIFIED);
            }
        }

        @Override
        public void onMemberKicked(GroupModel group, String identity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(63844)) {
                logger.debug("Group Listener: onMemberKicked");
            }
            if (!ListenerUtil.mutListener.listen(63845)) {
                updateGroup(group, Protocol.ARGUMENT_MODE_MODIFIED);
            }
        }

        @Override
        public void onUpdate(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(63846)) {
                logger.debug("Group Listener: onUpdate");
            }
            if (!ListenerUtil.mutListener.listen(63847)) {
                updateGroup(groupModel, Protocol.ARGUMENT_MODE_MODIFIED);
            }
        }

        @Override
        public void onLeave(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(63848)) {
                logger.debug("Group Listener: onLeave");
            }
            if (!ListenerUtil.mutListener.listen(63849)) {
                updateGroup(groupModel, Protocol.ARGUMENT_MODE_MODIFIED);
            }
        }
    }

    @AnyThread
    private class DistributionListListener implements ch.threema.app.listeners.DistributionListListener {

        @Override
        public void onCreate(DistributionListModel distributionListModel) {
            if (!ListenerUtil.mutListener.listen(63850)) {
                logger.debug("Distribution List Listener: onCreate");
            }
            if (!ListenerUtil.mutListener.listen(63851)) {
                updateDistributionList(distributionListModel, Protocol.ARGUMENT_MODE_NEW);
            }
        }

        @Override
        public void onModify(DistributionListModel distributionListModel) {
            if (!ListenerUtil.mutListener.listen(63852)) {
                logger.debug("Distribution List Listener: onModify");
            }
            if (!ListenerUtil.mutListener.listen(63853)) {
                updateDistributionList(distributionListModel, Protocol.ARGUMENT_MODE_MODIFIED);
            }
        }

        @Override
        public void onRemove(DistributionListModel distributionListModel) {
            if (!ListenerUtil.mutListener.listen(63854)) {
                logger.debug("Distribution List Listener: onRemove");
            }
            if (!ListenerUtil.mutListener.listen(63855)) {
                updateDistributionList(distributionListModel, Protocol.ARGUMENT_MODE_REMOVED);
            }
        }
    }
}

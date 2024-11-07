/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import org.msgpack.core.MessagePackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.converter.Utils;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageUpdater;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Notify Threema Web about changes to avatars.
 */
@WorkerThread
public class AvatarUpdateHandler extends MessageUpdater {

    private static final Logger logger = LoggerFactory.getLogger(AvatarUpdateHandler.class);

    // Handler
    @NonNull
    private final HandlerExecutor handler;

    // Listeners
    @NonNull
    private final ContactListener contactListener;

    @NonNull
    private final GroupListener groupListener;

    // Dispatchers
    @NonNull
    private final MessageDispatcher updateDispatcher;

    @AnyThread
    public AvatarUpdateHandler(@NonNull HandlerExecutor handler, @NonNull MessageDispatcher updateDispatcher) {
        super(Protocol.SUB_TYPE_AVATAR);
        this.handler = handler;
        // Dispatchers
        this.updateDispatcher = updateDispatcher;
        // Create receiver listeners
        this.contactListener = new ContactListener();
        this.groupListener = new GroupListener();
    }

    @Override
    public void register() {
        if (!ListenerUtil.mutListener.listen(63678)) {
            logger.debug("register()");
        }
        if (!ListenerUtil.mutListener.listen(63679)) {
            ListenerManager.contactListeners.add(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(63680)) {
            ListenerManager.groupListeners.add(this.groupListener);
        }
    }

    /**
     *  This method can be safely called multiple times without any negative side effects
     */
    @Override
    public void unregister() {
        if (!ListenerUtil.mutListener.listen(63681)) {
            logger.debug("unregister()");
        }
        if (!ListenerUtil.mutListener.listen(63682)) {
            ListenerManager.contactListeners.remove(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(63683)) {
            ListenerManager.groupListeners.remove(this.groupListener);
        }
    }

    /**
     *  Update a contact avatar.
     */
    private void update(final ContactModel contact) {
        if (!ListenerUtil.mutListener.listen(63684)) {
            this.update(new Utils.ModelWrapper(contact));
        }
    }

    /**
     *  Update a group avatar.
     */
    private void update(GroupModel group) {
        if (!ListenerUtil.mutListener.listen(63685)) {
            this.update(new Utils.ModelWrapper(group));
        }
    }

    private void update(final Utils.ModelWrapper model) {
        try {
            // Get avatar
            byte[] data = model.getAvatar(true, Protocol.SIZE_AVATAR_HIRES_MAX_PX);
            // Convert message and prepare arguments
            MsgpackObjectBuilder args = new MsgpackObjectBuilder();
            if (!ListenerUtil.mutListener.listen(63687)) {
                args.put(Protocol.ARGUMENT_RECEIVER_TYPE, model.getType());
            }
            if (!ListenerUtil.mutListener.listen(63688)) {
                args.put(Protocol.ARGUMENT_RECEIVER_ID, model.getId());
            }
            if (!ListenerUtil.mutListener.listen(63689)) {
                // Send message
                logger.debug("Sending {} avatar update", model.getType());
            }
            if (!ListenerUtil.mutListener.listen(63690)) {
                send(updateDispatcher, data, args);
            }
        } catch (ConversionException | MessagePackException e) {
            if (!ListenerUtil.mutListener.listen(63686)) {
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
        public void onAvatarChanged(ContactModel contactModel) {
            if (!ListenerUtil.mutListener.listen(63691)) {
                logger.debug("Contact Listener: onAvatarChanged");
            }
            if (!ListenerUtil.mutListener.listen(63693)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63692)) {
                            AvatarUpdateHandler.this.update(contactModel);
                        }
                    }
                });
            }
        }
    }

    @AnyThread
    private class GroupListener implements ch.threema.app.listeners.GroupListener {

        @Override
        public void onUpdatePhoto(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(63694)) {
                logger.debug("Group Listener: onUpdatePhoto");
            }
            if (!ListenerUtil.mutListener.listen(63696)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63695)) {
                            AvatarUpdateHandler.this.update(groupModel);
                        }
                    }
                });
            }
        }
    }
}

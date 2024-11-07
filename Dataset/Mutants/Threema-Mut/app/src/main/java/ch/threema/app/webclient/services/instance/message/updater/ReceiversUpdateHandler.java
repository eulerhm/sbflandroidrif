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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.routines.SynchronizeContactsRoutine;
import ch.threema.app.services.ContactService;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.Contact;
import ch.threema.app.webclient.converter.MsgpackBuilder;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.converter.Receiver;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageUpdater;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Listen for changes that require the entire list of contacts to be refreshed in Threema Web.
 *
 * Example: When the name format of the contacts changes.
 */
@WorkerThread
public class ReceiversUpdateHandler extends MessageUpdater {

    private static final Logger logger = LoggerFactory.getLogger(ReceiversUpdateHandler.class);

    // Handler
    @NonNull
    private final HandlerExecutor handler;

    // Listeners
    private final ContactSettingsListener contactSettingsListener;

    private final SynchronizeContactsListener synchronizeContactsListener;

    // Dispatchers
    private MessageDispatcher updateDispatcher;

    // Services
    private ContactService contactService;

    @AnyThread
    public ReceiversUpdateHandler(@NonNull HandlerExecutor handler, MessageDispatcher updateDispatcher, ContactService contactService) {
        super(Protocol.SUB_TYPE_RECEIVERS);
        this.handler = handler;
        if (!ListenerUtil.mutListener.listen(63778)) {
            this.updateDispatcher = updateDispatcher;
        }
        if (!ListenerUtil.mutListener.listen(63779)) {
            this.contactService = contactService;
        }
        this.contactSettingsListener = new ContactSettingsListener();
        this.synchronizeContactsListener = new SynchronizeContactsListener();
    }

    @Override
    public void register() {
        if (!ListenerUtil.mutListener.listen(63780)) {
            logger.debug("register()");
        }
        if (!ListenerUtil.mutListener.listen(63781)) {
            ListenerManager.contactSettingsListeners.add(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(63782)) {
            ListenerManager.synchronizeContactsListeners.add(this.synchronizeContactsListener);
        }
    }

    /**
     *  This method can be safely called multiple times without any negative side effects
     */
    @Override
    public void unregister() {
        if (!ListenerUtil.mutListener.listen(63783)) {
            logger.debug("unregister()");
        }
        if (!ListenerUtil.mutListener.listen(63784)) {
            ListenerManager.contactSettingsListeners.remove(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(63785)) {
            ListenerManager.synchronizeContactsListeners.remove(this.synchronizeContactsListener);
        }
    }

    /**
     *  Update the list of contacts.
     */
    private void updateContacts() {
        try {
            // Prepare args
            final MsgpackObjectBuilder args = new MsgpackObjectBuilder().put(Protocol.ARGUMENT_RECEIVER_TYPE, Receiver.Type.CONTACT);
            // Convert contacts
            final List<MsgpackBuilder> data = Contact.convert(contactService.find(Contact.getContactFilter()));
            if (!ListenerUtil.mutListener.listen(63787)) {
                // Send message
                logger.debug("Sending receivers update");
            }
            if (!ListenerUtil.mutListener.listen(63788)) {
                this.send(this.updateDispatcher, data, args);
            }
        } catch (ConversionException e) {
            if (!ListenerUtil.mutListener.listen(63786)) {
                logger.error("Exception", e);
            }
        }
    }

    @AnyThread
    private class ContactSettingsListener implements ch.threema.app.listeners.ContactSettingsListener {

        @Override
        public void onSortingChanged() {
            if (!ListenerUtil.mutListener.listen(63789)) {
                logger.debug("Contact Listener: onSortingChanged");
            }
            if (!ListenerUtil.mutListener.listen(63791)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63790)) {
                            ReceiversUpdateHandler.this.updateContacts();
                        }
                    }
                });
            }
        }

        @Override
        public void onNameFormatChanged() {
            if (!ListenerUtil.mutListener.listen(63792)) {
                logger.debug("Contact Listener: onNameFormatChanged");
            }
            if (!ListenerUtil.mutListener.listen(63794)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63793)) {
                            ReceiversUpdateHandler.this.updateContacts();
                        }
                    }
                });
            }
        }

        @Override
        public void onAvatarSettingChanged() {
            if (!ListenerUtil.mutListener.listen(63795)) {
                logger.debug("Contact Listener: onAvatarSettingChanged");
            }
        }

        @Override
        public void onInactiveContactsSettingChanged() {
            if (!ListenerUtil.mutListener.listen(63796)) {
                logger.debug("Contact Listener: onInactiveContactsSettingChanged");
            }
            if (!ListenerUtil.mutListener.listen(63798)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63797)) {
                            ReceiversUpdateHandler.this.updateContacts();
                        }
                    }
                });
            }
        }

        @Override
        public void onNotificationSettingChanged(String uid) {
            if (!ListenerUtil.mutListener.listen(63799)) {
                logger.debug("Contact Listener: onNotificationSettingChanged");
            }
        }
    }

    @AnyThread
    private class SynchronizeContactsListener implements ch.threema.app.listeners.SynchronizeContactsListener {

        @Override
        public void onStarted(SynchronizeContactsRoutine startedRoutine) {
            if (!ListenerUtil.mutListener.listen(63800)) {
                logger.debug("Contact sync started");
            }
        }

        @Override
        public void onFinished(SynchronizeContactsRoutine finishedRoutine) {
            if (!ListenerUtil.mutListener.listen(63801)) {
                logger.debug("Contact sync finished, sending receivers update");
            }
            if (!ListenerUtil.mutListener.listen(63803)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63802)) {
                            ReceiversUpdateHandler.this.updateContacts();
                        }
                    }
                });
            }
        }

        @Override
        public void onError(SynchronizeContactsRoutine finishedRoutine) {
            if (!ListenerUtil.mutListener.listen(63804)) {
                logger.warn("Contact sync error, sending receivers update");
            }
            if (!ListenerUtil.mutListener.listen(63806)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63805)) {
                            ReceiversUpdateHandler.this.updateContacts();
                        }
                    }
                });
            }
        }
    }
}

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
package ch.threema.app.webclient.services.instance;

import org.msgpack.core.MessagePackException;
import org.msgpack.value.MapValue;
import org.msgpack.value.Value;
import org.saltyrtc.client.SaltyRTCBuilder;
import org.saltyrtc.client.crypto.CryptoException;
import org.saltyrtc.client.crypto.CryptoProvider;
import org.saltyrtc.client.keystore.KeyStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.managers.ListenerManager.HandleListener;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.SendMode;
import ch.threema.app.webclient.converter.ConnectionDisconnect;
import ch.threema.app.webclient.exceptions.DispatchException;
import ch.threema.app.webclient.listeners.WebClientMessageListener;
import ch.threema.app.webclient.listeners.WebClientServiceListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.services.ServicesContainer;
import ch.threema.app.webclient.services.instance.message.receiver.AcknowledgeRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ActiveConversationHandler;
import ch.threema.app.webclient.services.instance.message.receiver.AvatarRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.BlobRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.CleanReceiverConversationRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ClientInfoRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ConnectionInfoUpdateHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ContactDetailRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ConversationRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.CreateContactHandler;
import ch.threema.app.webclient.services.instance.message.receiver.CreateDistributionListHandler;
import ch.threema.app.webclient.services.instance.message.receiver.CreateGroupHandler;
import ch.threema.app.webclient.services.instance.message.receiver.DeleteDistributionListHandler;
import ch.threema.app.webclient.services.instance.message.receiver.DeleteGroupHandler;
import ch.threema.app.webclient.services.instance.message.receiver.DeleteMessageHandler;
import ch.threema.app.webclient.services.instance.message.receiver.FileMessageCreateHandler;
import ch.threema.app.webclient.services.instance.message.receiver.IgnoreRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.IsTypingHandler;
import ch.threema.app.webclient.services.instance.message.receiver.KeyPersistedRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.MessageReadRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.MessageRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ModifyContactHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ModifyConversationHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ModifyDistributionListHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ModifyGroupHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ModifyProfileHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ProfileRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ReceiversRequestHandler;
import ch.threema.app.webclient.services.instance.message.receiver.SyncGroupHandler;
import ch.threema.app.webclient.services.instance.message.receiver.TextMessageCreateHandler;
import ch.threema.app.webclient.services.instance.message.receiver.ThumbnailRequestHandler;
import ch.threema.app.webclient.services.instance.message.updater.AlertHandler;
import ch.threema.app.webclient.services.instance.message.updater.AvatarUpdateHandler;
import ch.threema.app.webclient.services.instance.message.updater.BatteryStatusUpdateHandler;
import ch.threema.app.webclient.services.instance.message.updater.ConversationUpdateHandler;
import ch.threema.app.webclient.services.instance.message.updater.MessageUpdateHandler;
import ch.threema.app.webclient.services.instance.message.updater.ProfileUpdateHandler;
import ch.threema.app.webclient.services.instance.message.updater.ReceiverUpdateHandler;
import ch.threema.app.webclient.services.instance.message.updater.ReceiversUpdateHandler;
import ch.threema.app.webclient.services.instance.message.updater.TypingUpdateHandler;
import ch.threema.app.webclient.services.instance.message.updater.VoipStatusUpdateHandler;
import ch.threema.app.webclient.services.instance.state.SessionStateManager;
import ch.threema.app.webclient.state.WebClientSessionState;
import ch.threema.logging.ThreemaLogger;
import ch.threema.storage.models.WebClientSessionModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Service class that handles everything related to the ARP session process.
 */
@WorkerThread
public class SessionInstanceServiceImpl implements SessionInstanceService {

    @NonNull
    final Logger logger = LoggerFactory.getLogger(SessionInstanceServiceImpl.class);

    // Session id registry
    @NonNull
    private static AtomicInteger staticSessionId = new AtomicInteger(0);

    // Services
    @NonNull
    private final ServicesContainer services;

    // NaCl crypto provider
    @NonNull
    private final CryptoProvider cryptoProvider;

    // Model
    @NonNull
    private final WebClientSessionModel model;

    // Session id
    private final int sessionId;

    // Session state manager
    @NonNull
    private final SessionStateManager stateManager;

    // Message updaters
    @NonNull
    private final MessageUpdater[] updaters;

    // Message dispatchers
    @NonNull
    private final MessageDispatcher[] dispatchers;

    // Listeners
    @NonNull
    private final WebClientMessageListener messageListener;

    // Current affiliation id
    @Nullable
    private String affiliationId;

    // Performance testing
    private long startTimeNs = -1;

    @AnyThread
    public SessionInstanceServiceImpl(@NonNull final ServicesContainer services, @NonNull final CryptoProvider cryptoProvider, @NonNull final WebClientSessionModel model, @NonNull final HandlerExecutor handler) {
        this.services = services;
        this.cryptoProvider = cryptoProvider;
        this.model = model;
        // Determine session id
        this.sessionId = SessionInstanceServiceImpl.staticSessionId.getAndIncrement();
        if (!ListenerUtil.mutListener.listen(64198)) {
            // Set logger prefix
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(64197)) {
                    ((ThreemaLogger) logger).setPrefix(String.valueOf(this.sessionId));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64199)) {
            logger.info("Initialize SessionInstanceServiceImpl");
        }
        // Initialize state manager
        this.stateManager = new SessionStateManager(sessionId, model, handler, services, new SessionStateManager.StopHandler() {

            @Override
            @WorkerThread
            public void onStopped(@NonNull DisconnectContext reason) {
                if (!ListenerUtil.mutListener.listen(64200)) {
                    SessionInstanceServiceImpl.this.unregister();
                }
            }
        });
        // Create dispatchers
        final MessageDispatcher responseDispatcher = new MessageDispatcher(Protocol.TYPE_RESPONSE, this, services.lifetime, services.messageQueue);
        final MessageDispatcher updateDispatcher = new MessageDispatcher(Protocol.TYPE_UPDATE, this, services.lifetime, services.messageQueue);
        final MessageDispatcher deleteDispatcher = new MessageDispatcher(Protocol.TYPE_DELETE, this, services.lifetime, services.messageQueue);
        // Create update handlers
        final ReceiverUpdateHandler receiverUpdateHandler = new ReceiverUpdateHandler(handler, updateDispatcher, services.synchronizeContacts);
        final ReceiversUpdateHandler receiversUpdateHandler = new ReceiversUpdateHandler(handler, updateDispatcher, services.contact);
        final AvatarUpdateHandler avatarUpdateHandler = new AvatarUpdateHandler(handler, updateDispatcher);
        final ConversationUpdateHandler conversationUpdateHandler = new ConversationUpdateHandler(handler, updateDispatcher, services.contact, services.group, services.distributionList, services.hiddenChat, this.sessionId);
        final MessageUpdateHandler messageUpdateHandler = new MessageUpdateHandler(handler, updateDispatcher, services.hiddenChat, services.file);
        final TypingUpdateHandler typingUpdateHandler = new TypingUpdateHandler(handler, updateDispatcher);
        final BatteryStatusUpdateHandler batteryStatusUpdateHandler = new BatteryStatusUpdateHandler(services.appContext, handler, this.sessionId, updateDispatcher);
        final VoipStatusUpdateHandler voipStatusUpdateHandler = new VoipStatusUpdateHandler(handler, this.sessionId, updateDispatcher);
        final ProfileUpdateHandler profileUpdateHandler = new ProfileUpdateHandler(handler, updateDispatcher, services.user, services.contact);
        // Register alert handler
        final AlertHandler alertHandler = new AlertHandler(handler, updateDispatcher);
        if (!ListenerUtil.mutListener.listen(64201)) {
            alertHandler.register();
        }
        // Dispatchers
        final MessageDispatcher requestDispatcher = new MessageDispatcher(Protocol.TYPE_REQUEST, this, services.lifetime, services.messageQueue);
        if (!ListenerUtil.mutListener.listen(64211)) {
            // Client info requester
            requestDispatcher.addReceiver(new ClientInfoRequestHandler(responseDispatcher, services.preference, services.appContext, new ClientInfoRequestHandler.Listener() {

                @Override
                @WorkerThread
                public void onReceived(@NonNull final String userAgent) {
                    if (!ListenerUtil.mutListener.listen(64203)) {
                        WebClientListenerManager.serviceListener.handle(new HandleListener<WebClientServiceListener>() {

                            @Override
                            @WorkerThread
                            public void handle(WebClientServiceListener listener) {
                                if (!ListenerUtil.mutListener.listen(64202)) {
                                    listener.onStarted(model, Objects.requireNonNull(model.getKey()), userAgent);
                                }
                            }
                        });
                    }
                }

                @Override
                @WorkerThread
                public void onAnswered(@Nullable final String pushToken) {
                    if (!ListenerUtil.mutListener.listen(64206)) {
                        // Save the gcm token in the model
                        if (!TestUtil.compare(model.getPushToken(), pushToken)) {
                            if (!ListenerUtil.mutListener.listen(64205)) {
                                WebClientListenerManager.serviceListener.handle(new HandleListener<WebClientServiceListener>() {

                                    @Override
                                    @WorkerThread
                                    public void handle(WebClientServiceListener listener) {
                                        if (!ListenerUtil.mutListener.listen(64204)) {
                                            listener.onPushTokenChanged(model, pushToken);
                                        }
                                    }
                                });
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64207)) {
                        // Register battery status listener
                        batteryStatusUpdateHandler.register();
                    }
                    if (!ListenerUtil.mutListener.listen(64208)) {
                        // VoIP status listener
                        voipStatusUpdateHandler.register();
                    }
                    if (!ListenerUtil.mutListener.listen(64209)) {
                        // Send initial battery status
                        batteryStatusUpdateHandler.trigger();
                    }
                    if (!ListenerUtil.mutListener.listen(64210)) {
                        SessionInstanceServiceImpl.this.logActionSinceStart("Client info sent");
                    }
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(64214)) {
            // Key persisted info requester
            requestDispatcher.addReceiver(new KeyPersistedRequestHandler(new KeyPersistedRequestHandler.Listener() {

                @Override
                @WorkerThread
                public void onReceived() {
                    if (!ListenerUtil.mutListener.listen(64213)) {
                        WebClientListenerManager.serviceListener.handle(new HandleListener<WebClientServiceListener>() {

                            @Override
                            @WorkerThread
                            public void handle(WebClientServiceListener listener) {
                                if (!ListenerUtil.mutListener.listen(64212)) {
                                    listener.onKeyPersisted(model, true);
                                }
                            }
                        });
                    }
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(64221)) {
            requestDispatcher.addReceiver(new ReceiversRequestHandler(responseDispatcher, services.contact, services.group, services.distributionList, new ReceiversRequestHandler.Listener() {

                private boolean registered = false;

                @Override
                @WorkerThread
                public void onReceived() {
                    if (!ListenerUtil.mutListener.listen(64219)) {
                        if (!registered) {
                            if (!ListenerUtil.mutListener.listen(64215)) {
                                registered = true;
                            }
                            if (!ListenerUtil.mutListener.listen(64216)) {
                                receiverUpdateHandler.register();
                            }
                            if (!ListenerUtil.mutListener.listen(64217)) {
                                receiversUpdateHandler.register();
                            }
                            if (!ListenerUtil.mutListener.listen(64218)) {
                                avatarUpdateHandler.register();
                            }
                        }
                    }
                }

                @Override
                @WorkerThread
                public void onAnswered() {
                    if (!ListenerUtil.mutListener.listen(64220)) {
                        SessionInstanceServiceImpl.this.logActionSinceStart("Receivers sent");
                    }
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(64227)) {
            requestDispatcher.addReceiver(new ConversationRequestHandler(responseDispatcher, services.conversation, new ConversationRequestHandler.Listener() {

                private boolean registered = false;

                @Override
                @WorkerThread
                public void onRespond() {
                    if (!ListenerUtil.mutListener.listen(64225)) {
                        if (!registered) {
                            if (!ListenerUtil.mutListener.listen(64222)) {
                                registered = true;
                            }
                            if (!ListenerUtil.mutListener.listen(64223)) {
                                conversationUpdateHandler.register();
                            }
                            if (!ListenerUtil.mutListener.listen(64224)) {
                                typingUpdateHandler.register();
                            }
                        }
                    }
                }

                @Override
                @WorkerThread
                public void onAnswered() {
                    if (!ListenerUtil.mutListener.listen(64226)) {
                        SessionInstanceServiceImpl.this.logActionSinceStart("Conversations sent");
                    }
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(64231)) {
            requestDispatcher.addReceiver(new MessageRequestHandler(responseDispatcher, services.message, services.hiddenChat, new MessageRequestHandler.Listener() {

                @Override
                @WorkerThread
                public void onReceive(ch.threema.app.messagereceiver.MessageReceiver receiver) {
                    if (!ListenerUtil.mutListener.listen(64230)) {
                        // Register for updates
                        if (messageUpdateHandler.register(receiver)) {
                            if (!ListenerUtil.mutListener.listen(64229)) {
                                logger.info("Registered message updates");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(64228)) {
                                logger.warn("Message updates not registered");
                            }
                        }
                    }
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(64232)) {
            requestDispatcher.addReceiver(new BlobRequestHandler(handler, responseDispatcher, services.message, services.file));
        }
        if (!ListenerUtil.mutListener.listen(64233)) {
            requestDispatcher.addReceiver(new AvatarRequestHandler(responseDispatcher));
        }
        if (!ListenerUtil.mutListener.listen(64234)) {
            requestDispatcher.addReceiver(new ThumbnailRequestHandler(responseDispatcher, services.message, services.file));
        }
        if (!ListenerUtil.mutListener.listen(64235)) {
            requestDispatcher.addReceiver(new AcknowledgeRequestHandler(services.message, services.notification));
        }
        if (!ListenerUtil.mutListener.listen(64236)) {
            requestDispatcher.addReceiver(new MessageReadRequestHandler(services.contact, services.group, services.message, services.notification));
        }
        if (!ListenerUtil.mutListener.listen(64237)) {
            requestDispatcher.addReceiver(new ContactDetailRequestHandler(responseDispatcher, services.contact));
        }
        if (!ListenerUtil.mutListener.listen(64238)) {
            requestDispatcher.addReceiver(new SyncGroupHandler(responseDispatcher, services.group));
        }
        if (!ListenerUtil.mutListener.listen(64242)) {
            requestDispatcher.addReceiver(new ProfileRequestHandler(responseDispatcher, services.user, services.contact, new ProfileRequestHandler.Listener() {

                @Override
                @WorkerThread
                public void onReceived() {
                    if (!ListenerUtil.mutListener.listen(64239)) {
                        // Register for updates
                        profileUpdateHandler.register();
                    }
                    if (!ListenerUtil.mutListener.listen(64240)) {
                        logger.info("Registered for profile updates");
                    }
                }

                @Override
                @WorkerThread
                public void onAnswered() {
                    if (!ListenerUtil.mutListener.listen(64241)) {
                        SessionInstanceServiceImpl.this.logActionSinceStart("Profile sent");
                    }
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(64243)) {
            // Ignore battery status requests
            requestDispatcher.addReceiver(new IgnoreRequestHandler(Protocol.TYPE_REQUEST, Protocol.SUB_TYPE_BATTERY_STATUS));
        }
        // Create 'create' dispatcher and the handlers
        final MessageDispatcher createDispatcher = new MessageDispatcher(Protocol.TYPE_CREATE, this, services.lifetime, services.messageQueue);
        if (!ListenerUtil.mutListener.listen(64244)) {
            createDispatcher.addReceiver(new TextMessageCreateHandler(createDispatcher, services.message, services.lifetime, services.blackList));
        }
        if (!ListenerUtil.mutListener.listen(64245)) {
            createDispatcher.addReceiver(new FileMessageCreateHandler(createDispatcher, services.message, services.file, services.lifetime, services.blackList));
        }
        if (!ListenerUtil.mutListener.listen(64246)) {
            createDispatcher.addReceiver(new CreateContactHandler(createDispatcher, services.contact));
        }
        if (!ListenerUtil.mutListener.listen(64247)) {
            createDispatcher.addReceiver(new CreateGroupHandler(createDispatcher, services.group));
        }
        if (!ListenerUtil.mutListener.listen(64248)) {
            createDispatcher.addReceiver(new CreateDistributionListHandler(createDispatcher, services.distributionList));
        }
        if (!ListenerUtil.mutListener.listen(64249)) {
            updateDispatcher.addReceiver(new ModifyContactHandler(updateDispatcher, services.contact));
        }
        if (!ListenerUtil.mutListener.listen(64250)) {
            updateDispatcher.addReceiver(new ModifyGroupHandler(updateDispatcher, services.group));
        }
        if (!ListenerUtil.mutListener.listen(64251)) {
            updateDispatcher.addReceiver(new ModifyDistributionListHandler(updateDispatcher, services.distributionList));
        }
        if (!ListenerUtil.mutListener.listen(64252)) {
            updateDispatcher.addReceiver(new ModifyProfileHandler(responseDispatcher, services.contact, services.user));
        }
        if (!ListenerUtil.mutListener.listen(64253)) {
            updateDispatcher.addReceiver(new ModifyConversationHandler(responseDispatcher, services.conversation, services.conversationTag));
        }
        if (!ListenerUtil.mutListener.listen(64254)) {
            updateDispatcher.addReceiver(new IsTypingHandler(services.user));
        }
        if (!ListenerUtil.mutListener.listen(64255)) {
            updateDispatcher.addReceiver(new ConnectionInfoUpdateHandler());
        }
        if (!ListenerUtil.mutListener.listen(64256)) {
            updateDispatcher.addReceiver(new ActiveConversationHandler(services.contact, services.group, services.conversation, services.conversationTag));
        }
        if (!ListenerUtil.mutListener.listen(64257)) {
            deleteDispatcher.addReceiver(new DeleteMessageHandler(responseDispatcher, services.message));
        }
        if (!ListenerUtil.mutListener.listen(64258)) {
            deleteDispatcher.addReceiver(new DeleteGroupHandler(responseDispatcher, services.group));
        }
        if (!ListenerUtil.mutListener.listen(64259)) {
            deleteDispatcher.addReceiver(new DeleteDistributionListHandler(responseDispatcher, services.distributionList));
        }
        if (!ListenerUtil.mutListener.listen(64260)) {
            deleteDispatcher.addReceiver(new CleanReceiverConversationRequestHandler(responseDispatcher, services.conversation));
        }
        // Create update handlers array
        this.updaters = new MessageUpdater[] { receiverUpdateHandler, receiversUpdateHandler, avatarUpdateHandler, conversationUpdateHandler, messageUpdateHandler, typingUpdateHandler, batteryStatusUpdateHandler, voipStatusUpdateHandler, profileUpdateHandler, alertHandler };
        // Create message dispatchers array
        this.dispatchers = new MessageDispatcher[] { requestDispatcher, responseDispatcher, updateDispatcher, createDispatcher, deleteDispatcher };
        // Register listener for new web client messages
        this.messageListener = new WebClientMessageListener() {

            @Override
            @WorkerThread
            public void onMessage(MapValue message) {
                if (!ListenerUtil.mutListener.listen(64261)) {
                    receive(message);
                }
            }

            @Override
            @WorkerThread
            public boolean handle(WebClientSessionModel sessionModel) {
                return sessionModel.getId() == SessionInstanceServiceImpl.this.model.getId();
            }
        };
    }

    /**
     *  Return whether this session is in a non-terminal state.
     */
    @Override
    // Should be safe, we're just checking a variable
    @AnyThread
    public boolean isRunning() {
        final WebClientSessionState state = this.stateManager.getState();
        switch(state) {
            case DISCONNECTED:
            case ERROR:
                return false;
            case CONNECTING:
            case CONNECTED:
                return true;
            default:
                throw new IllegalStateException("Unhandled state: " + state);
        }
    }

    /**
     *  Return the current state of the session.
     */
    @Override
    @NonNull
    public WebClientSessionState getState() {
        return this.stateManager.getState();
    }

    /**
     *  Return whether the session needs to be restarted
     *  (if not currently running or due to a different affiliation id).
     */
    @Override
    public boolean needsRestart(@Nullable final String affiliationId) {
        if (!ListenerUtil.mutListener.listen(64262)) {
            if (!this.isRunning()) {
                return true;
            }
        }
        return (ListenerUtil.mutListener.listen(64264) ? (affiliationId != null || ((ListenerUtil.mutListener.listen(64263) ? (this.affiliationId == null && !this.affiliationId.equals(affiliationId)) : (this.affiliationId == null || !this.affiliationId.equals(affiliationId))))) : (affiliationId != null && ((ListenerUtil.mutListener.listen(64263) ? (this.affiliationId == null && !this.affiliationId.equals(affiliationId)) : (this.affiliationId == null || !this.affiliationId.equals(affiliationId))))));
    }

    /**
     *  Return the session model.
     */
    @Override
    @NonNull
    public WebClientSessionModel getModel() {
        return this.model;
    }

    /**
     *  Start the session.
     */
    @Override
    public void start(@NonNull final byte[] permanentKey, @NonNull byte[] authToken, @Nullable final String affiliationId) {
        if (!ListenerUtil.mutListener.listen(64266)) {
            // Update logger prefix
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(64265)) {
                    ((ThreemaLogger) logger).setPrefix(this.sessionId + "." + affiliationId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64267)) {
            logger.info("Starting Threema Web session");
        }
        final KeyStore ks = new KeyStore(this.cryptoProvider);
        if (!ListenerUtil.mutListener.listen(64268)) {
            // Temporarily set the key on session model, but do not save
            this.model.setKey(permanentKey).setPrivateKey(ks.getPrivateKey());
        }
        // Create a builder with a new keystore, including a new permanent key pair.
        final SaltyRTCBuilder builder = this.getBuilder().initiatorInfo(permanentKey, authToken).withKeyStore(ks);
        if (!ListenerUtil.mutListener.listen(64269)) {
            this.init(builder, affiliationId);
        }
    }

    /**
     *  Resume this session based on the data stored in the session model.
     */
    @Override
    public void resume(@Nullable final String affiliationId) throws CryptoException {
        if (!ListenerUtil.mutListener.listen(64271)) {
            // Update logger prefix
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(64270)) {
                    ((ThreemaLogger) logger).setPrefix(this.sessionId + "." + affiliationId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64272)) {
            logger.info("Resuming Threema Web session");
        }
        if (!ListenerUtil.mutListener.listen(64274)) {
            if (this.model.getKey() == null) {
                if (!ListenerUtil.mutListener.listen(64273)) {
                    logger.error("No session key in model instance, aborting resume");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(64276)) {
            if (this.model.getPrivateKey() == null) {
                if (!ListenerUtil.mutListener.listen(64275)) {
                    logger.error("No private key in model instance, aborting resume");
                }
                return;
            }
        }
        // Create a builder with a new keystore based on an existing session.
        final SaltyRTCBuilder builder = this.getBuilder().withTrustedPeerKey(this.model.getKey()).withKeyStore(new KeyStore(this.cryptoProvider, this.model.getPrivateKey()));
        if (!ListenerUtil.mutListener.listen(64277)) {
            this.init(builder, affiliationId);
        }
    }

    /**
     *  Get a SaltyRTC builder instance, pre-initialised with default values based
     *  on the app preferences.
     */
    @NonNull
    private SaltyRTCBuilder getBuilder() {
        // When IPv6 disabled, only use IPv4.
        SaltyRTCBuilder.DualStackMode dualStackMode = SaltyRTCBuilder.DualStackMode.BOTH;
        if (!ListenerUtil.mutListener.listen(64279)) {
            if (!this.services.preference.allowWebrtcIpv6()) {
                if (!ListenerUtil.mutListener.listen(64278)) {
                    dualStackMode = SaltyRTCBuilder.DualStackMode.IPV4_ONLY;
                }
            }
        }
        // Create builder instance
        return new SaltyRTCBuilder(this.cryptoProvider).withWebSocketDualStackMode(dualStackMode);
    }

    /**
     *  Initialize the connection.
     *
     *  Warning: The caller MUST ensure that the current state is either DISCONNECTED or ERROR!
     */
    private void init(@NonNull final SaltyRTCBuilder builder, @Nullable final String affiliationId) {
        if (!ListenerUtil.mutListener.listen(64283)) {
            // restarted immediately by a pending wakeup.
            if (!WebClientListenerManager.messageListener.contains(this.messageListener)) {
                if (!ListenerUtil.mutListener.listen(64281)) {
                    logger.debug("Registering message listener");
                }
                if (!ListenerUtil.mutListener.listen(64282)) {
                    WebClientListenerManager.messageListener.add(this.messageListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(64280)) {
                    logger.debug("Message listener already registered");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64284)) {
            // Store affiliation id and connect
            this.affiliationId = affiliationId;
        }
        if (!ListenerUtil.mutListener.listen(64285)) {
            this.stateManager.setConnecting(builder, affiliationId);
        }
        if (!ListenerUtil.mutListener.listen(64286)) {
            // Log start timestamp
            this.startTimeNs = System.nanoTime();
        }
    }

    @Override
    public void stop(@NonNull final DisconnectContext reason) {
        if (!ListenerUtil.mutListener.listen(64287)) {
            logger.info("Stopping Threema Web session: {}", reason);
        }
        if (!ListenerUtil.mutListener.listen(64288)) {
            // Run unregister procedure
            this.unregister();
        }
        if (!ListenerUtil.mutListener.listen(64289)) {
            // trigger waking up pending sessions which may restart the session again!
            this.stateManager.setDisconnected(reason);
        }
    }

    /**
     *  Should always be called when a stop request is being made or when being stopped.
     */
    private void unregister() {
        if (!ListenerUtil.mutListener.listen(64291)) {
            {
                long _loopCounter777 = 0;
                // Deregister update handlers
                for (final MessageUpdater handler : this.updaters) {
                    ListenerUtil.loopListener.listen("_loopCounter777", ++_loopCounter777);
                    if (!ListenerUtil.mutListener.listen(64290)) {
                        handler.unregister();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64295)) {
            // Remove listener
            if (WebClientListenerManager.messageListener.contains(this.messageListener)) {
                if (!ListenerUtil.mutListener.listen(64293)) {
                    logger.debug("Unregistering message listener");
                }
                if (!ListenerUtil.mutListener.listen(64294)) {
                    WebClientListenerManager.messageListener.remove(this.messageListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(64292)) {
                    logger.error("Message listener was not registered!");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64296)) {
            // Reset connection duration timer
            this.startTimeNs = -1;
        }
    }

    /**
     *  Send a msgpack encoded message to the peer through the secure data channel.
     */
    @Override
    public void send(@NonNull final ByteBuffer message, @NonNull final SendMode mode) {
        if (!ListenerUtil.mutListener.listen(64297)) {
            this.stateManager.send(message, mode);
        }
    }

    /**
     *  Receive an incoming message.
     */
    private void receive(MapValue message) {
        try {
            final Map<String, Value> map = new HashMap<>();
            if (!ListenerUtil.mutListener.listen(64304)) {
                {
                    long _loopCounter778 = 0;
                    for (Map.Entry<Value, Value> entry : message.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter778", ++_loopCounter778);
                        if (!ListenerUtil.mutListener.listen(64303)) {
                            map.put(entry.getKey().asStringValue().asString(), entry.getValue());
                        }
                    }
                }
            }
            // Get type and subtype
            final Value typeValue = map.get(Protocol.FIELD_TYPE);
            final String type = typeValue.asStringValue().asString();
            final Value subTypeValue = map.get(Protocol.FIELD_SUB_TYPE);
            final String subType = subTypeValue.asStringValue().asString();
            if (!ListenerUtil.mutListener.listen(64305)) {
                logger.debug("Received {}/{}", type, subType);
            }
            boolean received = false;
            // without jumping through some hoops using listeners.
            final boolean isUpdate = Protocol.TYPE_UPDATE.equals(type);
            if (!ListenerUtil.mutListener.listen(64309)) {
                if ((ListenerUtil.mutListener.listen(64306) ? (isUpdate || Protocol.SUB_TYPE_CONNECTION_DISCONNECT.equals(subType)) : (isUpdate && Protocol.SUB_TYPE_CONNECTION_DISCONNECT.equals(subType)))) {
                    if (!ListenerUtil.mutListener.listen(64307)) {
                        this.receiveConnectionDisconnect(map);
                    }
                    if (!ListenerUtil.mutListener.listen(64308)) {
                        received = true;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(64313)) {
                // Dispatch message
                if (!received) {
                    if (!ListenerUtil.mutListener.listen(64312)) {
                        {
                            long _loopCounter779 = 0;
                            for (MessageDispatcher dispatcher : this.dispatchers) {
                                ListenerUtil.loopListener.listen("_loopCounter779", ++_loopCounter779);
                                if (!ListenerUtil.mutListener.listen(64311)) {
                                    if (dispatcher.dispatch(type, subType, map)) {
                                        if (!ListenerUtil.mutListener.listen(64310)) {
                                            received = true;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(64315)) {
                // Check that one dispatcher received the message
                if (!received) {
                    if (!ListenerUtil.mutListener.listen(64314)) {
                        logger.warn("Ignored message with type {}", type);
                    }
                }
            }
        } catch (MessagePackException e) {
            if (!ListenerUtil.mutListener.listen(64298)) {
                logger.error("Protocol error due to invalid message", e);
            }
            if (!ListenerUtil.mutListener.listen(64299)) {
                this.stop(DisconnectContext.byUs(DisconnectContext.REASON_ERROR));
            }
        } catch (NullPointerException e) {
            if (!ListenerUtil.mutListener.listen(64300)) {
                // the potential NPEs. There are dozens...
                logger.error("Protocol error due to NPE", e);
            }
            if (!ListenerUtil.mutListener.listen(64301)) {
                this.stop(DisconnectContext.byUs(DisconnectContext.REASON_ERROR));
            }
        } catch (DispatchException e) {
            if (!ListenerUtil.mutListener.listen(64302)) {
                logger.warn("Could not dispatch message", e);
            }
        }
    }

    /**
     *  Receive and handle an update/connectionDisconnect message.
     */
    private void receiveConnectionDisconnect(final Map<String, Value> map) {
        if (!ListenerUtil.mutListener.listen(64317)) {
            // Extract data map
            if (!map.containsKey(Protocol.FIELD_DATA)) {
                if (!ListenerUtil.mutListener.listen(64316)) {
                    logger.warn("Ignored connectionDisconnect message without data field");
                }
                return;
            }
        }
        final Value data = map.get(Protocol.FIELD_DATA);
        if (!ListenerUtil.mutListener.listen(64319)) {
            if (!data.isMapValue()) {
                if (!ListenerUtil.mutListener.listen(64318)) {
                    logger.warn("Ignored connectionDisconnect message with non-map data field");
                }
                return;
            }
        }
        final Map<String, Value> dataMap = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(64321)) {
            {
                long _loopCounter780 = 0;
                for (Map.Entry<Value, Value> entry : data.asMapValue().entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter780", ++_loopCounter780);
                    if (!ListenerUtil.mutListener.listen(64320)) {
                        dataMap.put(entry.getKey().asStringValue().asString(), entry.getValue());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64323)) {
            // Extract reason
            if (!dataMap.containsKey(ConnectionDisconnect.REASON)) {
                if (!ListenerUtil.mutListener.listen(64322)) {
                    logger.warn("Ignored connectionDisconnect message without reason field");
                }
                return;
            }
        }
        final Value reasonValue = dataMap.get(ConnectionDisconnect.REASON);
        if (!ListenerUtil.mutListener.listen(64325)) {
            if (!reasonValue.isStringValue()) {
                if (!ListenerUtil.mutListener.listen(64324)) {
                    logger.warn("Ignored connectionDisconnect message with non-string reason field");
                }
                return;
            }
        }
        final String reasonText = reasonValue.asStringValue().toString();
        // Create DisconnectContext
        final DisconnectContext reason;
        switch(reasonText) {
            case ConnectionDisconnect.REASON_SESSION_STOPPED:
                reason = new DisconnectContext.ByPeer(DisconnectContext.REASON_SESSION_STOPPED);
                break;
            case ConnectionDisconnect.REASON_SESSION_DELETED:
                reason = new DisconnectContext.ByPeer(DisconnectContext.REASON_SESSION_DELETED);
                break;
            case ConnectionDisconnect.REASON_WEBCLIENT_DISABLED:
                reason = new DisconnectContext.ByPeer(DisconnectContext.REASON_WEBCLIENT_DISABLED);
                break;
            case ConnectionDisconnect.REASON_SESSION_REPLACED:
                reason = new DisconnectContext.ByPeer(DisconnectContext.REASON_SESSION_REPLACED);
                break;
            case ConnectionDisconnect.REASON_OUT_OF_MEMORY:
                reason = new DisconnectContext.ByPeer(DisconnectContext.REASON_OUT_OF_MEMORY);
                break;
            case ConnectionDisconnect.REASON_ERROR:
                reason = new DisconnectContext.ByPeer(DisconnectContext.REASON_ERROR);
                break;
            default:
                if (!ListenerUtil.mutListener.listen(64326)) {
                    logger.warn("Ignored connectionDisconnect message with invalid reason field: " + reasonText);
                }
                return;
        }
        if (!ListenerUtil.mutListener.listen(64327)) {
            logger.debug("Peer requested disconnecting via connectionDisconnect msg");
        }
        if (!ListenerUtil.mutListener.listen(64328)) {
            this.stop(reason);
        }
    }

    private void logActionSinceStart(@NonNull String message) {
        if (!ListenerUtil.mutListener.listen(64347)) {
            if ((ListenerUtil.mutListener.listen(64333) ? (SessionInstanceServiceImpl.this.startTimeNs >= 0) : (ListenerUtil.mutListener.listen(64332) ? (SessionInstanceServiceImpl.this.startTimeNs <= 0) : (ListenerUtil.mutListener.listen(64331) ? (SessionInstanceServiceImpl.this.startTimeNs < 0) : (ListenerUtil.mutListener.listen(64330) ? (SessionInstanceServiceImpl.this.startTimeNs != 0) : (ListenerUtil.mutListener.listen(64329) ? (SessionInstanceServiceImpl.this.startTimeNs == 0) : (SessionInstanceServiceImpl.this.startTimeNs > 0))))))) {
                long ms = (ListenerUtil.mutListener.listen(64345) ? ((ListenerUtil.mutListener.listen(64341) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) % 1000) : (ListenerUtil.mutListener.listen(64340) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) * 1000) : (ListenerUtil.mutListener.listen(64339) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) - 1000) : (ListenerUtil.mutListener.listen(64338) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) + 1000) : (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(64344) ? ((ListenerUtil.mutListener.listen(64341) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) % 1000) : (ListenerUtil.mutListener.listen(64340) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) * 1000) : (ListenerUtil.mutListener.listen(64339) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) - 1000) : (ListenerUtil.mutListener.listen(64338) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) + 1000) : (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(64343) ? ((ListenerUtil.mutListener.listen(64341) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) % 1000) : (ListenerUtil.mutListener.listen(64340) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) * 1000) : (ListenerUtil.mutListener.listen(64339) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) - 1000) : (ListenerUtil.mutListener.listen(64338) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) + 1000) : (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(64342) ? ((ListenerUtil.mutListener.listen(64341) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) % 1000) : (ListenerUtil.mutListener.listen(64340) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) * 1000) : (ListenerUtil.mutListener.listen(64339) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) - 1000) : (ListenerUtil.mutListener.listen(64338) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) + 1000) : (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(64341) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) % 1000) : (ListenerUtil.mutListener.listen(64340) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) * 1000) : (ListenerUtil.mutListener.listen(64339) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) - 1000) : (ListenerUtil.mutListener.listen(64338) ? (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) + 1000) : (((ListenerUtil.mutListener.listen(64337) ? (System.nanoTime() % SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64336) ? (System.nanoTime() / SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64335) ? (System.nanoTime() * SessionInstanceServiceImpl.this.startTimeNs) : (ListenerUtil.mutListener.listen(64334) ? (System.nanoTime() + SessionInstanceServiceImpl.this.startTimeNs) : (System.nanoTime() - SessionInstanceServiceImpl.this.startTimeNs)))))) / 1000))))) / 1000)))));
                if (!ListenerUtil.mutListener.listen(64346)) {
                    logger.info("{} after {} ms", message, ms);
                }
            }
        }
    }
}

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
package ch.threema.app.webclient.services;

import android.content.Intent;
import org.saltyrtc.client.crypto.CryptoProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.crypto.NativeJnaclCryptoProvider;
import ch.threema.app.webclient.listeners.WebClientServiceListener;
import ch.threema.app.webclient.listeners.WebClientSessionListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.services.instance.SessionInstanceService;
import ch.threema.app.webclient.services.instance.SessionInstanceServiceImpl;
import ch.threema.app.webclient.state.WebClientSessionState;
import ch.threema.client.Utils;
import ch.threema.storage.models.WebClientSessionModel;
import java8.util.concurrent.CompletableFuture;
import java8.util.function.Supplier;
import java8.util.stream.Stream;
import java8.util.stream.StreamSupport;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class SessionServiceImpl implements SessionService {

    /**
     *  Container for a session instance service and a corresponding listener.
     */
    private class SessionInstanceContainer {

        @NonNull
        private final SessionInstanceService instance;

        @NonNull
        private final WebClientServiceListener listener;

        private SessionInstanceContainer(@NonNull final SessionInstanceService instance, @NonNull final WebClientServiceListener listener) {
            this.instance = instance;
            this.listener = listener;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    // Worker thread
    @NonNull
    private final HandlerExecutor handler;

    // Services
    @NonNull
    private final ServicesContainer services;

    // NaCl crypto provider
    @NonNull
    private final CryptoProvider cryptoProvider;

    // Currently running instances
    @NonNull
    private final Map<Integer, SessionInstanceContainer> instances = new HashMap<>();

    public SessionServiceImpl(@NonNull final HandlerExecutor handler, @NonNull final ServicesContainer services) {
        this.handler = handler;
        this.services = services;
        // Create NaCl crypto provider
        this.cryptoProvider = new NativeJnaclCryptoProvider();
    }

    @Override
    public void setEnabled(final boolean enable) {
        if (!ListenerUtil.mutListener.listen(64516)) {
            this.handler.post(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    if (!ListenerUtil.mutListener.listen(64496)) {
                        if (SessionServiceImpl.this.services.preference.isWebClientEnabled() == enable) {
                            // No change
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64497)) {
                        // Save enabled/disabled flag
                        SessionServiceImpl.this.services.preference.setWebClientEnabled(enable);
                    }
                    if (!ListenerUtil.mutListener.listen(64514)) {
                        // Disable
                        if (!enable) {
                            if (!ListenerUtil.mutListener.listen(64498)) {
                                // Discard all pending wakeups
                                SessionServiceImpl.this.services.sessionWakeUp.discardPendingWakeups();
                            }
                            if (!ListenerUtil.mutListener.listen(64507)) {
                                {
                                    long _loopCounter782 = 0;
                                    // Stop all running session instances
                                    for (WebClientSessionModel model : SessionServiceImpl.this.getAllSessionModels()) {
                                        ListenerUtil.loopListener.listen("_loopCounter782", ++_loopCounter782);
                                        final SessionInstanceService instance = SessionServiceImpl.this.getInstanceService(model, false);
                                        @DisconnectContext.DisconnectReason
                                        int reason = DisconnectContext.REASON_WEBCLIENT_DISABLED;
                                        if (!ListenerUtil.mutListener.listen(64501)) {
                                            // Remove session if non-persistent
                                            if (!model.isPersistent()) {
                                                if (!ListenerUtil.mutListener.listen(64499)) {
                                                    SessionServiceImpl.this.services.database.getWebClientSessionModelFactory().delete(model);
                                                }
                                                if (!ListenerUtil.mutListener.listen(64500)) {
                                                    reason = DisconnectContext.REASON_SESSION_DELETED;
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(64503)) {
                                            // Stop session (if an instance exists)
                                            if (instance != null) {
                                                if (!ListenerUtil.mutListener.listen(64502)) {
                                                    instance.stop(DisconnectContext.byUs(reason));
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(64506)) {
                                            // Raise 'removed' event if session has been removed
                                            if (reason == DisconnectContext.REASON_SESSION_DELETED) {
                                                if (!ListenerUtil.mutListener.listen(64505)) {
                                                    WebClientListenerManager.sessionListener.handle(new ListenerManager.HandleListener<WebClientSessionListener>() {

                                                        @Override
                                                        @WorkerThread
                                                        public void handle(WebClientSessionListener listener) {
                                                            if (!ListenerUtil.mutListener.listen(64504)) {
                                                                listener.onRemoved(model);
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(64508)) {
                                // Release all wake locks
                                SessionServiceImpl.this.services.wakeLock.releaseAll();
                            }
                            if (!ListenerUtil.mutListener.listen(64509)) {
                                // Force stop the foreground service
                                logger.info("Force stopping SessionAndroidService");
                            }
                            if (!ListenerUtil.mutListener.listen(64513)) {
                                if (SessionAndroidService.isRunning()) {
                                    final Intent intent = new Intent(SessionServiceImpl.this.services.appContext, SessionAndroidService.class);
                                    if (!ListenerUtil.mutListener.listen(64510)) {
                                        intent.setAction(SessionAndroidService.ACTION_FORCE_STOP);
                                    }
                                    if (!ListenerUtil.mutListener.listen(64511)) {
                                        logger.info("Sending FORCE_STOP to SessionAndroidService");
                                    }
                                    if (!ListenerUtil.mutListener.listen(64512)) {
                                        SessionServiceImpl.this.services.appContext.startService(intent);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64515)) {
                        // Fire 'enabled' or 'disabled' event
                        WebClientListenerManager.serviceListener.handle(listener -> {
                            if (enable) {
                                listener.onEnabled();
                            } else {
                                listener.onDisabled();
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public boolean isEnabled() {
        return (ListenerUtil.mutListener.listen(64518) ? ((ListenerUtil.mutListener.listen(64517) ? (this.services.preference.isWebClientEnabled() || !AppRestrictionUtil.isWebDisabled(this.services.appContext)) : (this.services.preference.isWebClientEnabled() && !AppRestrictionUtil.isWebDisabled(this.services.appContext))) || this.services.license.isLicensed()) : ((ListenerUtil.mutListener.listen(64517) ? (this.services.preference.isWebClientEnabled() || !AppRestrictionUtil.isWebDisabled(this.services.appContext)) : (this.services.preference.isWebClientEnabled() && !AppRestrictionUtil.isWebDisabled(this.services.appContext))) && this.services.license.isLicensed()));
    }

    @Override
    @NonNull
    public List<WebClientSessionModel> getAllSessionModels() {
        return this.services.database.getWebClientSessionModelFactory().getAll();
    }

    @Override
    @Nullable
    public synchronized SessionInstanceService getInstanceService(@NonNull final String publicKeySha256String, boolean createIfNotExists) {
        // Look up session instance
        SessionInstanceContainer container = Functional.select(this.instances, new IPredicateNonNull<SessionInstanceContainer>() {

            @Override
            @AnyThread
            public boolean apply(@NonNull final SessionInstanceContainer container) {
                final WebClientSessionModel model = container.instance.getModel();
                return TestUtil.compare(model.getKey256(), publicKeySha256String);
            }
        });
        // If necessary, create new instance
        SessionInstanceService instance = null;
        if (!ListenerUtil.mutListener.listen(64522)) {
            if (container != null) {
                if (!ListenerUtil.mutListener.listen(64521)) {
                    instance = container.instance;
                }
            } else if (createIfNotExists) {
                final WebClientSessionModel model = this.services.database.getWebClientSessionModelFactory().getByKey256(publicKeySha256String);
                if (!ListenerUtil.mutListener.listen(64520)) {
                    if (model != null) {
                        if (!ListenerUtil.mutListener.listen(64519)) {
                            instance = this.getInstanceService(model, true);
                        }
                    }
                }
            }
        }
        return instance;
    }

    @Override
    @Nullable
    public synchronized SessionInstanceService getInstanceService(@NonNull final WebClientSessionModel model, boolean createIfNotExists) {
        if (!ListenerUtil.mutListener.listen(64523)) {
            if (this.instances.containsKey(model.getId())) {
                final SessionInstanceContainer container = this.instances.get(model.getId());
                return container != null ? container.instance : null;
            } else if (!createIfNotExists) {
                return null;
            }
        }
        final SessionInstanceContainer container = this.createInstanceService(model);
        if (!ListenerUtil.mutListener.listen(64524)) {
            this.instances.put(model.getId(), container);
        }
        return container.instance;
    }

    @NonNull
    private SessionInstanceContainer createInstanceService(@NonNull final WebClientSessionModel model) {
        // Create session instance
        final SessionInstanceService instance = new SessionInstanceServiceImpl(this.services, this.cryptoProvider, model, this.handler);
        // Create service events listener
        final WebClientServiceListener listener = new WebClientServiceListener() {

            @Override
            @AnyThread
            public void onStarted(@NonNull final WebClientSessionModel eventModel, @NonNull final byte[] permanentKey, @NonNull final String browser) {
                synchronized (SessionServiceImpl.this) {
                    if (!ListenerUtil.mutListener.listen(64525)) {
                        if (!this.isLocalModel(eventModel)) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64526)) {
                        // Update the session model
                        model.setKey(permanentKey).setClientDescription(browser).setState(WebClientSessionModel.State.AUTHORIZED);
                    }
                    // Save a hash of the permanent key
                    try {
                        if (!ListenerUtil.mutListener.listen(64528)) {
                            model.setKey256(Utils.byteArrayToSha256HexString(permanentKey));
                        }
                    } catch (NoSuchAlgorithmException e) {
                        if (!ListenerUtil.mutListener.listen(64527)) {
                            // Should never happen
                            logger.error("Exception", e);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64529)) {
                        SessionServiceImpl.this.update(model);
                    }
                }
            }

            @Override
            @AnyThread
            public void onKeyPersisted(@NonNull WebClientSessionModel eventModel, final boolean persisted) {
                synchronized (SessionServiceImpl.this) {
                    if (!ListenerUtil.mutListener.listen(64530)) {
                        if (!this.isLocalModel(eventModel)) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64531)) {
                        model.setPersistent(persisted);
                    }
                    if (!ListenerUtil.mutListener.listen(64532)) {
                        SessionServiceImpl.this.update(model);
                    }
                }
            }

            @Override
            @AnyThread
            public void onStateChanged(@NonNull final WebClientSessionModel eventModel, @NonNull final WebClientSessionState oldState, @NonNull final WebClientSessionState newState) {
                synchronized (SessionServiceImpl.this) {
                    if (!ListenerUtil.mutListener.listen(64533)) {
                        if (!this.isLocalModel(eventModel)) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64535)) {
                        // Update last connection date when connected
                        if (newState == WebClientSessionState.CONNECTED) {
                            if (!ListenerUtil.mutListener.listen(64534)) {
                                SessionServiceImpl.this.update(model.setLastConnection(new Date()));
                            }
                        }
                    }
                }
            }

            @Override
            @AnyThread
            public void onPushTokenChanged(@NonNull final WebClientSessionModel eventModel, @Nullable final String newPushToken) {
                synchronized (SessionServiceImpl.this) {
                    if (!ListenerUtil.mutListener.listen(64536)) {
                        if (!this.isLocalModel(eventModel)) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64537)) {
                        // Save the NEW gcm token
                        SessionServiceImpl.this.update(model.setPushToken(newPushToken));
                    }
                }
            }

            @Override
            @AnyThread
            public void onStopped(@NonNull final WebClientSessionModel eventModel, @NonNull final DisconnectContext reason) {
                synchronized (SessionServiceImpl.this) {
                    if (!ListenerUtil.mutListener.listen(64538)) {
                        if (!this.isLocalModel(eventModel)) {
                            return;
                        }
                    }
                    // 2. We requested a disconnect and the model is not persistent.
                    boolean removed = false;
                    if (!ListenerUtil.mutListener.listen(64547)) {
                        if ((ListenerUtil.mutListener.listen(64540) ? (reason.shouldForget() && ((ListenerUtil.mutListener.listen(64539) ? (reason instanceof DisconnectContext.ByUs || !model.isPersistent()) : (reason instanceof DisconnectContext.ByUs && !model.isPersistent())))) : (reason.shouldForget() || ((ListenerUtil.mutListener.listen(64539) ? (reason instanceof DisconnectContext.ByUs || !model.isPersistent()) : (reason instanceof DisconnectContext.ByUs && !model.isPersistent())))))) {
                            if (!ListenerUtil.mutListener.listen(64546)) {
                                removed = (ListenerUtil.mutListener.listen(64545) ? (SessionServiceImpl.this.services.database.getWebClientSessionModelFactory().delete(model) >= 0) : (ListenerUtil.mutListener.listen(64544) ? (SessionServiceImpl.this.services.database.getWebClientSessionModelFactory().delete(model) <= 0) : (ListenerUtil.mutListener.listen(64543) ? (SessionServiceImpl.this.services.database.getWebClientSessionModelFactory().delete(model) < 0) : (ListenerUtil.mutListener.listen(64542) ? (SessionServiceImpl.this.services.database.getWebClientSessionModelFactory().delete(model) != 0) : (ListenerUtil.mutListener.listen(64541) ? (SessionServiceImpl.this.services.database.getWebClientSessionModelFactory().delete(model) == 0) : (SessionServiceImpl.this.services.database.getWebClientSessionModelFactory().delete(model) > 0))))));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64550)) {
                        // Update model
                        if (model.getState() == WebClientSessionModel.State.INITIALIZING) {
                            if (!ListenerUtil.mutListener.listen(64549)) {
                                model.setState(WebClientSessionModel.State.ERROR);
                            }
                        } else if (!removed) {
                            if (!ListenerUtil.mutListener.listen(64548)) {
                                model.setLastConnection(new Date());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64551)) {
                        SessionServiceImpl.this.update(model);
                    }
                    // Unregister the listener and remove the session instance
                    final SessionInstanceContainer container = SessionServiceImpl.this.instances.get(model.getId());
                    if (!ListenerUtil.mutListener.listen(64557)) {
                        if (container != null) {
                            if (!ListenerUtil.mutListener.listen(64553)) {
                                WebClientListenerManager.serviceListener.remove(container.listener);
                            }
                            if (!ListenerUtil.mutListener.listen(64556)) {
                                if (container.instance.isRunning()) {
                                    if (!ListenerUtil.mutListener.listen(64555)) {
                                        // This indicates a bug
                                        logger.error("Cannot remove running session instance!");
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(64554)) {
                                        SessionServiceImpl.this.instances.remove(model.getId());
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(64552)) {
                                logger.error("No session instance for session model {}", model.getId());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64560)) {
                        // Raise 'removed' event if session has been removed
                        if (removed) {
                            if (!ListenerUtil.mutListener.listen(64559)) {
                                WebClientListenerManager.sessionListener.handle(new ListenerManager.HandleListener<WebClientSessionListener>() {

                                    @Override
                                    @AnyThread
                                    public void handle(WebClientSessionListener listener) {
                                        if (!ListenerUtil.mutListener.listen(64558)) {
                                            listener.onRemoved(model);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }

            /**
             *  Return whether we want to handle events for the specified
             *  model.
             *
             *  Return true if this session is the session we're managing.
             */
            @AnyThread
            private boolean isLocalModel(@NonNull final WebClientSessionModel eventModel) {
                return eventModel.getId() == model.getId();
            }
        };
        if (!ListenerUtil.mutListener.listen(64561)) {
            // Register service events listener
            WebClientListenerManager.serviceListener.add(listener);
        }
        // Return as container
        return new SessionInstanceContainer(instance, listener);
    }

    /**
     *  Update session model and fire the 'modified' event.
     */
    private synchronized void update(@NonNull final WebClientSessionModel model) {
        if (!ListenerUtil.mutListener.listen(64564)) {
            if (this.services.database.getWebClientSessionModelFactory().createOrUpdate(model)) {
                if (!ListenerUtil.mutListener.listen(64563)) {
                    WebClientListenerManager.sessionListener.handle(new ListenerManager.HandleListener<WebClientSessionListener>() {

                        @Override
                        @AnyThread
                        public void handle(WebClientSessionListener listener) {
                            if (!ListenerUtil.mutListener.listen(64562)) {
                                listener.onModified(model);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    @NonNull
    public synchronized WebClientSessionModel create(@NonNull byte[] permanentyKey, @NonNull byte[] authToken, @NonNull String saltyRtcHost, int saltyRtcPort, @Nullable byte[] serverKey, boolean isPermanent, boolean isSelfHosted, @Nullable String affiliationId) {
        // Create and save a database model
        final WebClientSessionModel model = new WebClientSessionModel().setState(WebClientSessionModel.State.INITIALIZING).setSaltyRtcPort(saltyRtcPort).setSaltyRtcHost(saltyRtcHost).setServerKey(serverKey).setPersistent(isPermanent).setPushToken(this.services.preference.getPushToken()).setSelfHosted(isSelfHosted);
        if (!ListenerUtil.mutListener.listen(64565)) {
            this.services.database.getWebClientSessionModelFactory().createOrUpdate(model);
        }
        if (!ListenerUtil.mutListener.listen(64567)) {
            // Dispatch 'create' event
            WebClientListenerManager.sessionListener.handle(new ListenerManager.HandleListener<WebClientSessionListener>() {

                @Override
                @AnyThread
                public void handle(WebClientSessionListener listener) {
                    if (!ListenerUtil.mutListener.listen(64566)) {
                        listener.onCreated(model);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(64569)) {
            // Automatically enable Threema Web
            if (!this.isEnabled()) {
                if (!ListenerUtil.mutListener.listen(64568)) {
                    this.setEnabled(true);
                }
            }
        }
        // Get instance service
        final SessionInstanceService instance = this.getInstanceService(model, true);
        if (!ListenerUtil.mutListener.listen(64570)) {
            if (instance == null) {
                throw new IllegalStateException("Could not get session instance service");
            }
        }
        if (!ListenerUtil.mutListener.listen(64572)) {
            // Start session asynchronously and return the model
            this.handler.post(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    if (!ListenerUtil.mutListener.listen(64571)) {
                        // Start session
                        instance.start(permanentyKey, authToken, affiliationId);
                    }
                }
            });
        }
        return model;
    }

    @Override
    public long getRunningSessionsCount() {
        final Stream<SessionInstanceContainer> stream;
        synchronized (this) {
            stream = StreamSupport.stream(this.instances.values());
        }
        try {
            return CompletableFuture.supplyAsync(new Supplier<Long>() {

                @Override
                @WorkerThread
                @NonNull
                public Long get() {
                    return stream.filter(container -> container.instance.isRunning()).count();
                }
            }, this.handler.getExecutor()).get();
        } catch (InterruptedException | ExecutionException error) {
            if (!ListenerUtil.mutListener.listen(64573)) {
                logger.error("Unable to count running sessions", error);
            }
            return 0;
        }
    }

    @Override
    @NonNull
    public WebClientSessionState getState(@NonNull final WebClientSessionModel model) {
        SessionInstanceService instance = this.getInstanceService(model, false);
        if (instance == null) {
            return WebClientSessionState.DISCONNECTED;
        }
        // Dispatch 'isRunning' from the worker thread
        try {
            return CompletableFuture.supplyAsync(new Supplier<WebClientSessionState>() {

                @Override
                @WorkerThread
                public WebClientSessionState get() {
                    return instance.getState();
                }
            }, this.handler.getExecutor()).get();
        } catch (InterruptedException | ExecutionException error) {
            if (!ListenerUtil.mutListener.listen(64574)) {
                logger.error("Unable to retrieve session state", error);
            }
            return WebClientSessionState.ERROR;
        }
    }

    @Override
    public boolean isRunning(@NonNull final WebClientSessionModel model) {
        SessionInstanceService instance = this.getInstanceService(model, false);
        if (instance == null) {
            return false;
        }
        // Dispatch 'isRunning' from the worker thread
        try {
            return CompletableFuture.supplyAsync(new Supplier<Boolean>() {

                @Override
                @WorkerThread
                public Boolean get() {
                    return instance.isRunning();
                }
            }, this.handler.getExecutor()).get();
        } catch (InterruptedException | ExecutionException error) {
            if (!ListenerUtil.mutListener.listen(64575)) {
                logger.error("Unable to check if session is running", error);
            }
            return false;
        }
    }

    @Override
    public synchronized void stop(@NonNull final WebClientSessionModel model, @NonNull final DisconnectContext reason) {
        final SessionInstanceContainer container;
        if (!ListenerUtil.mutListener.listen(64576)) {
            // Remove and stop session instance
            logger.debug("Removing session instance for model {}", model.getId());
        }
        container = this.instances.remove(model.getId());
        if (!ListenerUtil.mutListener.listen(64580)) {
            if (container != null) {
                if (!ListenerUtil.mutListener.listen(64579)) {
                    this.handler.post(new Runnable() {

                        @Override
                        @WorkerThread
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(64578)) {
                                container.instance.stop(reason);
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(64577)) {
                    logger.warn("No session instance for session model {}", model.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64589)) {
            // in the UI ASAP.
            if (reason.shouldForget()) {
                // Remove and raise 'removed' event if the session has been removed
                final boolean removed = (ListenerUtil.mutListener.listen(64585) ? (this.services.database.getWebClientSessionModelFactory().delete(model) >= 0) : (ListenerUtil.mutListener.listen(64584) ? (this.services.database.getWebClientSessionModelFactory().delete(model) <= 0) : (ListenerUtil.mutListener.listen(64583) ? (this.services.database.getWebClientSessionModelFactory().delete(model) < 0) : (ListenerUtil.mutListener.listen(64582) ? (this.services.database.getWebClientSessionModelFactory().delete(model) != 0) : (ListenerUtil.mutListener.listen(64581) ? (this.services.database.getWebClientSessionModelFactory().delete(model) == 0) : (this.services.database.getWebClientSessionModelFactory().delete(model) > 0))))));
                if (!ListenerUtil.mutListener.listen(64588)) {
                    if (removed) {
                        if (!ListenerUtil.mutListener.listen(64587)) {
                            WebClientListenerManager.sessionListener.handle(new ListenerManager.HandleListener<WebClientSessionListener>() {

                                @Override
                                @AnyThread
                                public void handle(WebClientSessionListener listener) {
                                    if (!ListenerUtil.mutListener.listen(64586)) {
                                        listener.onRemoved(model);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    @Override
    public synchronized void stopAll(@NonNull final DisconnectContext reason) {
        if (!ListenerUtil.mutListener.listen(64591)) {
            {
                long _loopCounter783 = 0;
                for (WebClientSessionModel model : this.getAllSessionModels()) {
                    ListenerUtil.loopListener.listen("_loopCounter783", ++_loopCounter783);
                    if (!ListenerUtil.mutListener.listen(64590)) {
                        this.stop(model, reason);
                    }
                }
            }
        }
    }
}

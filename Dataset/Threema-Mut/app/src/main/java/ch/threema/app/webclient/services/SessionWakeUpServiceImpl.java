/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import android.content.Context;
import android.widget.Toast;
import org.saltyrtc.client.crypto.CryptoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.WorkerThread;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.NotificationService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.listeners.WebClientWakeUpListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.manager.WebClientServiceManager;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.services.instance.SessionInstanceService;
import ch.threema.base.ThreemaException;
import ch.threema.localcrypto.MasterKey;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class SessionWakeUpServiceImpl implements SessionWakeUpService {

    private enum StartResult {

        OK,
        SERVICE_NOT_AVAILABLE,
        SERVICE_DISABLED,
        HOST_CONSTRAINED_BY_MDM,
        SESSION_UNKNOWN,
        RESTARTED,
        ALREADY_STARTED,
        EXCEPTION
    }

    // Timeouts
    public static final int DEFAULT_WAKEUP_SECONDS = 60;

    public static final int DISCONNECT_WAKEUP_SECONDS = 20;

    // Logger
    @NonNull
    private static final Logger logger = LoggerFactory.getLogger(SessionWakeUpServiceImpl.class);

    // Singleton
    @Nullable
    private static SessionWakeUpService instance = null;

    // Service manager
    @Nullable
    private ServiceManager serviceManager = null;

    // Master key. Do not access this directly, use getMasterKey instead.
    @Nullable
    private final MasterKey masterKey;

    // Queue of pending wakeups. Do not access this directly, use getPendingWakeUps instead.
    private final Queue<PendingWakeup> pendingWakeUps = new ArrayDeque<>();

    @AnyThread
    @NonNull
    public static synchronized SessionWakeUpService getInstance() {
        if (!ListenerUtil.mutListener.listen(64593)) {
            if (instance == null) {
                if (!ListenerUtil.mutListener.listen(64592)) {
                    instance = new SessionWakeUpServiceImpl(ThreemaApplication.getMasterKey());
                }
            }
        }
        return instance;
    }

    @AnyThread
    public static synchronized void clear() {
        if (!ListenerUtil.mutListener.listen(64594)) {
            instance = null;
        }
    }

    @AnyThread
    private SessionWakeUpServiceImpl(@Nullable MasterKey masterKey) {
        this.masterKey = masterKey;
    }

    @NonNull
    private Queue<PendingWakeup> getPendingWakeUps() {
        return this.pendingWakeUps;
    }

    @AnyThread
    @Nullable
    private MasterKey getMasterKey() {
        return this.masterKey;
    }

    private Context getContext() {
        return ThreemaApplication.getAppContext();
    }

    /**
     *  Returns true if...
     *
     *  - the service manager is available, and
     *  - the master key is available.
     */
    @AnyThread
    private synchronized boolean isAvailable() {
        if (!ListenerUtil.mutListener.listen(64596)) {
            if (this.serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(64595)) {
                    this.serviceManager = ThreemaApplication.getServiceManager();
                }
            }
        }
        return (ListenerUtil.mutListener.listen(64597) ? (this.serviceManager != null || this.getMasterKey() != null) : (this.serviceManager != null && this.getMasterKey() != null));
    }

    /**
     *  Returns the session service if...
     *
     *  - the service manager is available,
     *  - the master key is available, and
     *  - the session service is available.
     *
     *  Otherwise, it raises ThreemaException.
     */
    @NonNull
    private SessionService getSessionService() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(64598)) {
            if (!this.isAvailable()) {
                throw new ThreemaException("Service manager or master key unavailable");
            }
        }
        return Objects.requireNonNull(serviceManager).getWebClientServiceManager().getSessionService();
    }

    @Override
    @AnyThread
    public synchronized void resume(@NonNull final String publicKeySha256String, final int version, @Nullable final String affiliationId) {
        if (!ListenerUtil.mutListener.listen(64599)) {
            logger.info("Attempting to resume session (public-key={}, version={}, affiliation={})", publicKeySha256String, version, affiliationId);
        }
        if (!ListenerUtil.mutListener.listen(64601)) {
            if (!this.isAvailable()) {
                if (!ListenerUtil.mutListener.listen(64600)) {
                    logger.error("Service unavailable");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(64605)) {
            // Validate protocol version
            if (Protocol.PROTOCOL_VERSION != version) {
                if (!ListenerUtil.mutListener.listen(64602)) {
                    logger.error("Unexpected protocol version: {}", version);
                }
                if (!ListenerUtil.mutListener.listen(64604)) {
                    WebClientListenerManager.wakeUpListener.handle(new ListenerManager.HandleListener<WebClientWakeUpListener>() {

                        @Override
                        @AnyThread
                        public void handle(WebClientWakeUpListener listener) {
                            if (!ListenerUtil.mutListener.listen(64603)) {
                                listener.onProtocolError();
                            }
                        }
                    });
                }
            }
        }
        // Ensure the web client service manager is available
        final WebClientServiceManager manager;
        try {
            manager = Objects.requireNonNull(this.serviceManager).getWebClientServiceManager();
        } catch (ThreemaException error) {
            if (!ListenerUtil.mutListener.listen(64606)) {
                logger.error("Cannot access web client service manager", error);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(64608)) {
            if (this.serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(64607)) {
                    logger.error("Cannot resume or schedule wakeup, web client service manager unavailable");
                }
                return;
            }
        }
        // Handle locked master key
        final MasterKey masterKey = this.getMasterKey();
        if (!ListenerUtil.mutListener.listen(64617)) {
            if ((ListenerUtil.mutListener.listen(64609) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked()))) {
                if (!ListenerUtil.mutListener.listen(64610)) {
                    logger.warn("Master key is locked, scheduling wakeup");
                }
                if (!ListenerUtil.mutListener.listen(64616)) {
                    manager.getHandler().post(new Runnable() {

                        @Override
                        @WorkerThread
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(64615)) {
                                SessionWakeUpServiceImpl.this.schedule(publicKeySha256String, affiliationId, (ListenerUtil.mutListener.listen(64614) ? (DEFAULT_WAKEUP_SECONDS % 1000) : (ListenerUtil.mutListener.listen(64613) ? (DEFAULT_WAKEUP_SECONDS / 1000) : (ListenerUtil.mutListener.listen(64612) ? (DEFAULT_WAKEUP_SECONDS - 1000) : (ListenerUtil.mutListener.listen(64611) ? (DEFAULT_WAKEUP_SECONDS + 1000) : (DEFAULT_WAKEUP_SECONDS * 1000))))));
                            }
                        }
                    });
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(64645)) {
            // Try to start session on the worker thread
            manager.getHandler().post(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    if (!ListenerUtil.mutListener.listen(64644)) {
                        switch(SessionWakeUpServiceImpl.this.start(publicKeySha256String, affiliationId)) {
                            case SERVICE_DISABLED:
                                if (!ListenerUtil.mutListener.listen(64618)) {
                                    logger.warn("Threema Web service is disabled, store pending wakeup");
                                }
                                if (!ListenerUtil.mutListener.listen(64619)) {
                                    RuntimeUtil.runOnUiThread(() -> {
                                        // Show a toast
                                        final Context context = SessionWakeUpServiceImpl.this.getContext();
                                        final String text = context.getString(R.string.webclient_cannot_restore) + ": " + context.getString(R.string.webclient_disabled);
                                        final Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                                        toast.show();
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(64624)) {
                                    SessionWakeUpServiceImpl.this.schedule(publicKeySha256String, affiliationId, (ListenerUtil.mutListener.listen(64623) ? (DEFAULT_WAKEUP_SECONDS % 1000) : (ListenerUtil.mutListener.listen(64622) ? (DEFAULT_WAKEUP_SECONDS / 1000) : (ListenerUtil.mutListener.listen(64621) ? (DEFAULT_WAKEUP_SECONDS - 1000) : (ListenerUtil.mutListener.listen(64620) ? (DEFAULT_WAKEUP_SECONDS + 1000) : (DEFAULT_WAKEUP_SECONDS * 1000))))));
                                }
                                break;
                            case SERVICE_NOT_AVAILABLE:
                                if (!ListenerUtil.mutListener.listen(64625)) {
                                    logger.warn("Service not available, store pending wakeup");
                                }
                                if (!ListenerUtil.mutListener.listen(64630)) {
                                    SessionWakeUpServiceImpl.this.schedule(publicKeySha256String, affiliationId, (ListenerUtil.mutListener.listen(64629) ? (DEFAULT_WAKEUP_SECONDS % 1000) : (ListenerUtil.mutListener.listen(64628) ? (DEFAULT_WAKEUP_SECONDS / 1000) : (ListenerUtil.mutListener.listen(64627) ? (DEFAULT_WAKEUP_SECONDS - 1000) : (ListenerUtil.mutListener.listen(64626) ? (DEFAULT_WAKEUP_SECONDS + 1000) : (DEFAULT_WAKEUP_SECONDS * 1000))))));
                                }
                                break;
                            case SESSION_UNKNOWN:
                                if (!ListenerUtil.mutListener.listen(64631)) {
                                    logger.warn("Session unknown, ignoring");
                                }
                                break;
                            case RESTARTED:
                                if (!ListenerUtil.mutListener.listen(64632)) {
                                    logger.info("Session has been restarted");
                                }
                                break;
                            case ALREADY_STARTED:
                                if (!ListenerUtil.mutListener.listen(64633)) {
                                    logger.warn("Already started, store pending wakeup");
                                }
                                if (!ListenerUtil.mutListener.listen(64638)) {
                                    SessionWakeUpServiceImpl.this.schedule(publicKeySha256String, affiliationId, (ListenerUtil.mutListener.listen(64637) ? (DISCONNECT_WAKEUP_SECONDS % 1000) : (ListenerUtil.mutListener.listen(64636) ? (DISCONNECT_WAKEUP_SECONDS / 1000) : (ListenerUtil.mutListener.listen(64635) ? (DISCONNECT_WAKEUP_SECONDS - 1000) : (ListenerUtil.mutListener.listen(64634) ? (DISCONNECT_WAKEUP_SECONDS + 1000) : (DISCONNECT_WAKEUP_SECONDS * 1000))))));
                                }
                                break;
                            case HOST_CONSTRAINED_BY_MDM:
                                if (!ListenerUtil.mutListener.listen(64639)) {
                                    logger.warn("Could not resume session, host constrained by administrator");
                                }
                                if (!ListenerUtil.mutListener.listen(64640)) {
                                    SessionWakeUpServiceImpl.this.showWarningNotification(R.string.webclient_constrained_by_mdm);
                                }
                                break;
                            case EXCEPTION:
                                if (!ListenerUtil.mutListener.listen(64641)) {
                                    logger.error("Exception while trying to wake up session");
                                }
                                break;
                            case OK:
                                if (!ListenerUtil.mutListener.listen(64642)) {
                                    logger.info("Session has been started");
                                }
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(64643)) {
                                    logger.error("Warning: Unhandled StartResult!");
                                }
                                break;
                        }
                    }
                }
            });
        }
    }

    /**
     *  Schedule a wakeup for the specific session.
     */
    private void schedule(@NonNull final String publicKeySha256String, @Nullable final String affiliationId, final int lifetimeMs) {
        if (!ListenerUtil.mutListener.listen(64662)) {
            if ((ListenerUtil.mutListener.listen(64650) ? (lifetimeMs >= 0) : (ListenerUtil.mutListener.listen(64649) ? (lifetimeMs <= 0) : (ListenerUtil.mutListener.listen(64648) ? (lifetimeMs < 0) : (ListenerUtil.mutListener.listen(64647) ? (lifetimeMs != 0) : (ListenerUtil.mutListener.listen(64646) ? (lifetimeMs == 0) : (lifetimeMs > 0))))))) {
                final long expiration = (ListenerUtil.mutListener.listen(64654) ? (System.currentTimeMillis() % lifetimeMs) : (ListenerUtil.mutListener.listen(64653) ? (System.currentTimeMillis() / lifetimeMs) : (ListenerUtil.mutListener.listen(64652) ? (System.currentTimeMillis() * lifetimeMs) : (ListenerUtil.mutListener.listen(64651) ? (System.currentTimeMillis() - lifetimeMs) : (System.currentTimeMillis() + lifetimeMs)))));
                final Queue<PendingWakeup> pendingQueue = this.getPendingWakeUps();
                if (!ListenerUtil.mutListener.listen(64659)) {
                    {
                        long _loopCounter784 = 0;
                        // Update existing wakeup (if any)
                        for (PendingWakeup pending : pendingQueue) {
                            ListenerUtil.loopListener.listen("_loopCounter784", ++_loopCounter784);
                            if (!ListenerUtil.mutListener.listen(64658)) {
                                if (pending.publicKeySha256String.equals(publicKeySha256String)) {
                                    if (!ListenerUtil.mutListener.listen(64655)) {
                                        logger.info("Wakeup already scheduled, refreshing expiration +{} ms", lifetimeMs);
                                    }
                                    if (!ListenerUtil.mutListener.listen(64656)) {
                                        pending.expiration = expiration;
                                    }
                                    if (!ListenerUtil.mutListener.listen(64657)) {
                                        pending.affiliationId = affiliationId;
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(64660)) {
                    // Add new wakeup
                    logger.info("Wakeup scheduled, expiration +{} ms", lifetimeMs);
                }
                if (!ListenerUtil.mutListener.listen(64661)) {
                    pendingQueue.add(new PendingWakeup(publicKeySha256String, affiliationId, expiration));
                }
            }
        }
    }

    private StartResult start(@NonNull final String publicKeySha256String, @Nullable final String affiliationId) {
        // Get session service
        final SessionService service;
        try {
            service = this.getSessionService();
        } catch (ThreemaException error) {
            if (!ListenerUtil.mutListener.listen(64663)) {
                logger.debug("Service unavailable: {}", error.getMessage());
            }
            return StartResult.SERVICE_NOT_AVAILABLE;
        }
        if (!ListenerUtil.mutListener.listen(64664)) {
            // Ensure the web client is enabled
            if (!service.isEnabled()) {
                return StartResult.SERVICE_DISABLED;
            }
        }
        if (!ListenerUtil.mutListener.listen(64665)) {
            // Ensure there is a session instance
            logger.debug("Retrieving session instance");
        }
        final SessionInstanceService webClientInstanceService = service.getInstanceService(publicKeySha256String, true);
        if (!ListenerUtil.mutListener.listen(64666)) {
            if (webClientInstanceService == null) {
                return StartResult.SESSION_UNKNOWN;
            }
        }
        if (!ListenerUtil.mutListener.listen(64676)) {
            // Check if the session is still running
            if (webClientInstanceService.isRunning()) {
                if (!ListenerUtil.mutListener.listen(64675)) {
                    // Stop current session if affiliation ID has changed
                    if (webClientInstanceService.needsRestart(affiliationId)) {
                        if (!ListenerUtil.mutListener.listen(64668)) {
                            logger.info("Restarting session", affiliationId);
                        }
                        if (!ListenerUtil.mutListener.listen(64673)) {
                            this.schedule(publicKeySha256String, affiliationId, (ListenerUtil.mutListener.listen(64672) ? (DISCONNECT_WAKEUP_SECONDS % 1000) : (ListenerUtil.mutListener.listen(64671) ? (DISCONNECT_WAKEUP_SECONDS / 1000) : (ListenerUtil.mutListener.listen(64670) ? (DISCONNECT_WAKEUP_SECONDS - 1000) : (ListenerUtil.mutListener.listen(64669) ? (DISCONNECT_WAKEUP_SECONDS + 1000) : (DISCONNECT_WAKEUP_SECONDS * 1000))))));
                        }
                        if (!ListenerUtil.mutListener.listen(64674)) {
                            webClientInstanceService.stop(DisconnectContext.byPeer(DisconnectContext.REASON_SESSION_REPLACED));
                        }
                        return StartResult.RESTARTED;
                    } else {
                        if (!ListenerUtil.mutListener.listen(64667)) {
                            logger.debug("Session already started", affiliationId);
                        }
                        return StartResult.ALREADY_STARTED;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64678)) {
            // MDM constraints
            if (ConfigUtils.isWorkRestricted()) {
                final String hostname = webClientInstanceService.getModel().getSaltyRtcHost();
                if (!ListenerUtil.mutListener.listen(64677)) {
                    if (!AppRestrictionUtil.isWebHostAllowed(this.getContext(), hostname)) {
                        return StartResult.HOST_CONSTRAINED_BY_MDM;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64679)) {
            // Resume the session
            logger.info("Resuming session", affiliationId);
        }
        try {
            if (!ListenerUtil.mutListener.listen(64681)) {
                webClientInstanceService.resume(affiliationId);
            }
        } catch (CryptoException error) {
            if (!ListenerUtil.mutListener.listen(64680)) {
                logger.error("Unable to resume session", error);
            }
            return StartResult.EXCEPTION;
        }
        return StartResult.OK;
    }

    /**
     *  Process pending wakeups asynchronously.
     */
    @Override
    @AnyThread
    public synchronized void processPendingWakeupsAsync() {
        if (!ListenerUtil.mutListener.listen(64683)) {
            if (!this.isAvailable()) {
                if (!ListenerUtil.mutListener.listen(64682)) {
                    logger.error("Service unavailable");
                }
                return;
            }
        }
        // Ensure the web client service manager is available
        final WebClientServiceManager manager;
        try {
            manager = Objects.requireNonNull(this.serviceManager).getWebClientServiceManager();
        } catch (ThreemaException error) {
            if (!ListenerUtil.mutListener.listen(64684)) {
                logger.error("Cannot access web client service manager", error);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(64686)) {
            // Process pending wakeups on worker thread
            manager.getHandler().post(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    if (!ListenerUtil.mutListener.listen(64685)) {
                        SessionWakeUpServiceImpl.this.processPendingWakeups();
                    }
                }
            });
        }
    }

    /**
     *  Process pending wakeups.
     */
    @Override
    public void processPendingWakeups() {
        final Queue<PendingWakeup> pendingQueue = this.getPendingWakeUps();
        if (!ListenerUtil.mutListener.listen(64687)) {
            logger.info("Process {} pending wakeups", pendingQueue.size());
        }
        // Try to resume each pending session
        PendingWakeup pending;
        final List<PendingWakeup> failed = new ArrayList<>();
        final long now = System.currentTimeMillis();
        if (!ListenerUtil.mutListener.listen(64712)) {
            {
                long _loopCounter785 = 0;
                while ((pending = pendingQueue.poll()) != null) {
                    ListenerUtil.loopListener.listen("_loopCounter785", ++_loopCounter785);
                    if (!ListenerUtil.mutListener.listen(64694)) {
                        // Check if expired
                        if ((ListenerUtil.mutListener.listen(64692) ? (now >= pending.expiration) : (ListenerUtil.mutListener.listen(64691) ? (now <= pending.expiration) : (ListenerUtil.mutListener.listen(64690) ? (now < pending.expiration) : (ListenerUtil.mutListener.listen(64689) ? (now != pending.expiration) : (ListenerUtil.mutListener.listen(64688) ? (now == pending.expiration) : (now > pending.expiration))))))) {
                            if (!ListenerUtil.mutListener.listen(64693)) {
                                logger.info("Pending wakeup expired, ignoring");
                            }
                            continue;
                        }
                    }
                    // Try a wakeup
                    final MasterKey masterKey = this.getMasterKey();
                    if (!ListenerUtil.mutListener.listen(64698)) {
                        if ((ListenerUtil.mutListener.listen(64695) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked()))) {
                            if (!ListenerUtil.mutListener.listen(64696)) {
                                logger.error("Cannot wake up {}, master key is locked", pending.publicKeySha256String);
                            }
                            if (!ListenerUtil.mutListener.listen(64697)) {
                                failed.add(pending);
                            }
                            continue;
                        }
                    }
                    try {
                        // Get session service
                        final SessionService sessionService = this.getSessionService();
                        if (!ListenerUtil.mutListener.listen(64704)) {
                            if (!sessionService.isEnabled()) {
                                if (!ListenerUtil.mutListener.listen(64702)) {
                                    logger.error("Cannot wake up {}, session service is disabled", pending.publicKeySha256String);
                                }
                                if (!ListenerUtil.mutListener.listen(64703)) {
                                    failed.add(pending);
                                }
                                continue;
                            }
                        }
                        // Get session instance
                        final SessionInstanceService webClientInstanceService = sessionService.getInstanceService(pending.publicKeySha256String.trim(), true);
                        if (!ListenerUtil.mutListener.listen(64706)) {
                            if (webClientInstanceService == null) {
                                if (!ListenerUtil.mutListener.listen(64705)) {
                                    logger.error("Cannot wake up {}, session instance not found, remove from pending list", pending.publicKeySha256String);
                                }
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(64709)) {
                            // Check MDM constraints
                            if (ConfigUtils.isWorkRestricted()) {
                                final String hostname = webClientInstanceService.getModel().getSaltyRtcHost();
                                if (!ListenerUtil.mutListener.listen(64708)) {
                                    if (!AppRestrictionUtil.isWebHostAllowed(this.getContext(), hostname)) {
                                        if (!ListenerUtil.mutListener.listen(64707)) {
                                            logger.warn("Cannot wake up session {}, disabled by administrator", pending.publicKeySha256String);
                                        }
                                        continue;
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(64710)) {
                            // Resume session instance
                            webClientInstanceService.resume(pending.affiliationId);
                        }
                        if (!ListenerUtil.mutListener.listen(64711)) {
                            logger.info("Resumed session {} from pending wakeup list", pending.publicKeySha256String);
                        }
                    } catch (CryptoException error) {
                        if (!ListenerUtil.mutListener.listen(64699)) {
                            logger.error("Exception while waking up session", error);
                        }
                    } catch (ThreemaException error) {
                        if (!ListenerUtil.mutListener.listen(64700)) {
                            logger.error("Exception while waking up session", error);
                        }
                        if (!ListenerUtil.mutListener.listen(64701)) {
                            failed.add(pending);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64716)) {
            // Reschedule failed wakeup attempts
            if (!failed.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(64713)) {
                    logger.info("Re-scheduling {} pending wakeups", failed.size());
                }
                if (!ListenerUtil.mutListener.listen(64714)) {
                    pendingQueue.addAll(failed);
                }
                if (!ListenerUtil.mutListener.listen(64715)) {
                    failed.clear();
                }
            }
        }
    }

    /**
     *  Discard pending wakeups.
     */
    @Override
    public void discardPendingWakeups() {
        final Queue<PendingWakeup> pendingQueue = this.getPendingWakeUps();
        if (!ListenerUtil.mutListener.listen(64717)) {
            logger.info("Discarding {} pending wakeups", pendingQueue.size());
        }
        if (!ListenerUtil.mutListener.listen(64718)) {
            pendingQueue.clear();
        }
    }

    private void showWarningNotification(@StringRes int message) {
        NotificationService notificationService = Objects.requireNonNull(this.serviceManager).getNotificationService();
        if (!ListenerUtil.mutListener.listen(64720)) {
            if (notificationService != null) {
                final String msg = this.getContext().getString(R.string.webclient_cannot_restore) + ": " + this.getContext().getString(message);
                if (!ListenerUtil.mutListener.listen(64719)) {
                    notificationService.showWebclientResumeFailed(msg);
                }
            }
        }
    }
}

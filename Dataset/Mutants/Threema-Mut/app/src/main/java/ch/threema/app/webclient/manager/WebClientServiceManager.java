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
package ch.threema.app.webclient.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.services.ServicesContainer;
import ch.threema.app.webclient.services.SessionService;
import ch.threema.app.webclient.services.SessionServiceImpl;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class WebClientServiceManager {

    // Services used by web client services
    @NonNull
    private final ServicesContainer services;

    // Lazily created Session service instance
    @Nullable
    private SessionServiceImpl sessionService;

    // Handler on top of the web client's worker thread
    @NonNull
    private final HandlerExecutor handler;

    public WebClientServiceManager(@NonNull final ServicesContainer services) {
        this.services = services;
        // Worker thread used within most of the web client code
        HandlerThread handlerThread = new HandlerThread("WCWorker");
        if (!ListenerUtil.mutListener.listen(63015)) {
            handlerThread.start();
        }
        final Looper looper = handlerThread.getLooper();
        final Handler parent;
        if ((ListenerUtil.mutListener.listen(63020) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(63019) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(63018) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(63017) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(63016) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.P) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P))))))) {
            parent = Handler.createAsync(looper);
        } else {
            parent = new Handler(looper);
        }
        this.handler = new HandlerExecutor(parent);
    }

    /**
     *  Return the web client worker thread handler.
     */
    @NonNull
    public HandlerExecutor getHandler() {
        return this.handler;
    }

    /**
     *  Return or lazily create a new session service.
     */
    @NonNull
    public SessionService getSessionService() {
        if (!ListenerUtil.mutListener.listen(63022)) {
            if (this.sessionService == null) {
                if (!ListenerUtil.mutListener.listen(63021)) {
                    this.sessionService = new SessionServiceImpl(this.handler, this.services);
                }
            }
        }
        return this.sessionService;
    }
}

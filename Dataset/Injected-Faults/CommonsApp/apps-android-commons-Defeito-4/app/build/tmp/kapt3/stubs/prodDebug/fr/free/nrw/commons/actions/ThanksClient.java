package fr.free.nrw.commons.actions;

import java.lang.System;

/**
 * Client for the Wkikimedia Thanks API extension
 * Thanks are used by a user to show gratitude to another user for their contributions
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/actions/ThanksClient;", "", "csrfTokenClient", "Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient;", "service", "Lfr/free/nrw/commons/actions/ThanksInterface;", "(Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient;Lfr/free/nrw/commons/actions/ThanksInterface;)V", "thank", "Lio/reactivex/Observable;", "", "revisionId", "", "app-commons-v4.2.1-master_prodDebug"})
@javax.inject.Singleton
public final class ThanksClient {
    private final fr.free.nrw.commons.auth.csrf.CsrfTokenClient csrfTokenClient = null;
    private final fr.free.nrw.commons.actions.ThanksInterface service = null;
    
    @javax.inject.Inject
    public ThanksClient(@org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "commons-csrf")
    fr.free.nrw.commons.auth.csrf.CsrfTokenClient csrfTokenClient, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.actions.ThanksInterface service) {
        super();
    }
    
    /**
     * Thanks a user for a particular revision
     * @param revisionId The revision ID the user would like to thank someone for
     * @return if thanks was successfully sent to intended recipient
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Observable<java.lang.Boolean> thank(long revisionId) {
        return null;
    }
}
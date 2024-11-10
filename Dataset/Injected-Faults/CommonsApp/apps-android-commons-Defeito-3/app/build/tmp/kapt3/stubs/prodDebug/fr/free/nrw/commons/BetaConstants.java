package fr.free.nrw.commons;

import java.lang.System;

/**
 * Production variant related constants which is used in beta variant for some specific GET calls on
 * production server where beta server does not work
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lfr/free/nrw/commons/BetaConstants;", "", "()V", "COMMONS_URL", "", "DEPICTS_PROPERTY", "app-commons-v4.2.1-master_prodDebug"})
public final class BetaConstants {
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.BetaConstants INSTANCE = null;
    
    /**
     * Commons production URL which is used in beta for some specific GET calls on
     * production server where beta server does not work
     */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String COMMONS_URL = "https://commons.wikimedia.org/";
    
    /**
     * Commons production's depicts property which is used in beta for some specific GET calls on
     * production server where beta server does not work
     */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DEPICTS_PROPERTY = "P180";
    
    private BetaConstants() {
        super();
    }
}
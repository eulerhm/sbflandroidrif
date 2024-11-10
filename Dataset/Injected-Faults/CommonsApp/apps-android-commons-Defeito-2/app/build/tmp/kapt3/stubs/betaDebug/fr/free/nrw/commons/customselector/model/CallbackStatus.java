package fr.free.nrw.commons.customselector.model;

import java.lang.System;

/**
 * sealed class Callback Status.
 * Current status of the device image query.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lfr/free/nrw/commons/customselector/model/CallbackStatus;", "", "()V", "FETCHING", "IDLE", "SUCCESS", "Lfr/free/nrw/commons/customselector/model/CallbackStatus$FETCHING;", "Lfr/free/nrw/commons/customselector/model/CallbackStatus$IDLE;", "Lfr/free/nrw/commons/customselector/model/CallbackStatus$SUCCESS;", "app-commons-v4.2.1-main_betaDebug"})
public abstract class CallbackStatus {
    
    private CallbackStatus() {
        super();
    }
    
    /**
     * IDLE : The callback is idle , doing nothing.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/model/CallbackStatus$IDLE;", "Lfr/free/nrw/commons/customselector/model/CallbackStatus;", "()V", "app-commons-v4.2.1-main_betaDebug"})
    public static final class IDLE extends fr.free.nrw.commons.customselector.model.CallbackStatus {
        @org.jetbrains.annotations.NotNull
        public static final fr.free.nrw.commons.customselector.model.CallbackStatus.IDLE INSTANCE = null;
        
        private IDLE() {
            super();
        }
    }
    
    /**
     * FETCHING : Fetching images.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/model/CallbackStatus$FETCHING;", "Lfr/free/nrw/commons/customselector/model/CallbackStatus;", "()V", "app-commons-v4.2.1-main_betaDebug"})
    public static final class FETCHING extends fr.free.nrw.commons.customselector.model.CallbackStatus {
        @org.jetbrains.annotations.NotNull
        public static final fr.free.nrw.commons.customselector.model.CallbackStatus.FETCHING INSTANCE = null;
        
        private FETCHING() {
            super();
        }
    }
    
    /**
     * SUCCESS : Success fetching images.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/model/CallbackStatus$SUCCESS;", "Lfr/free/nrw/commons/customselector/model/CallbackStatus;", "()V", "app-commons-v4.2.1-main_betaDebug"})
    public static final class SUCCESS extends fr.free.nrw.commons.customselector.model.CallbackStatus {
        @org.jetbrains.annotations.NotNull
        public static final fr.free.nrw.commons.customselector.model.CallbackStatus.SUCCESS INSTANCE = null;
        
        private SUCCESS() {
            super();
        }
    }
}
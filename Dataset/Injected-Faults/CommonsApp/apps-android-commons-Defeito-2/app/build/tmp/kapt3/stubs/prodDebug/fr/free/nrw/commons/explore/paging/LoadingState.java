package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lfr/free/nrw/commons/explore/paging/LoadingState;", "", "()V", "Complete", "Error", "InitialLoad", "Loading", "Lfr/free/nrw/commons/explore/paging/LoadingState$Complete;", "Lfr/free/nrw/commons/explore/paging/LoadingState$Error;", "Lfr/free/nrw/commons/explore/paging/LoadingState$InitialLoad;", "Lfr/free/nrw/commons/explore/paging/LoadingState$Loading;", "app-commons-v4.2.1-master_prodDebug"})
public abstract class LoadingState {
    
    private LoadingState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/explore/paging/LoadingState$InitialLoad;", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "()V", "app-commons-v4.2.1-master_prodDebug"})
    public static final class InitialLoad extends fr.free.nrw.commons.explore.paging.LoadingState {
        @org.jetbrains.annotations.NotNull
        public static final fr.free.nrw.commons.explore.paging.LoadingState.InitialLoad INSTANCE = null;
        
        private InitialLoad() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/explore/paging/LoadingState$Loading;", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "()V", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Loading extends fr.free.nrw.commons.explore.paging.LoadingState {
        @org.jetbrains.annotations.NotNull
        public static final fr.free.nrw.commons.explore.paging.LoadingState.Loading INSTANCE = null;
        
        private Loading() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/explore/paging/LoadingState$Complete;", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "()V", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Complete extends fr.free.nrw.commons.explore.paging.LoadingState {
        @org.jetbrains.annotations.NotNull
        public static final fr.free.nrw.commons.explore.paging.LoadingState.Complete INSTANCE = null;
        
        private Complete() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/explore/paging/LoadingState$Error;", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "()V", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Error extends fr.free.nrw.commons.explore.paging.LoadingState {
        @org.jetbrains.annotations.NotNull
        public static final fr.free.nrw.commons.explore.paging.LoadingState.Error INSTANCE = null;
        
        private Error() {
            super();
        }
    }
}
package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001aN\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0010\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2(\u0010\n\u001a$\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\f0\u000bj\b\u0012\u0004\u0012\u0002H\u0005`\r\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"INITIAL_LOAD_SIZE", "", "PAGE_SIZE", "dataSourceFactory", "Lfr/free/nrw/commons/explore/paging/PagingDataSourceFactory;", "T", "loadingStates", "Lio/reactivex/processors/PublishProcessor;", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "Lfr/free/nrw/commons/explore/depictions/search/LoadingStates;", "loadFunction", "Lkotlin/Function2;", "", "Lfr/free/nrw/commons/explore/depictions/search/LoadFunction;", "app-commons-v4.2.1-master_prodDebug"})
public final class PagingDataSourceFactoryKt {
    private static final int PAGE_SIZE = 50;
    private static final int INITIAL_LOAD_SIZE = 50;
    
    @org.jetbrains.annotations.NotNull
    public static final <T extends java.lang.Object>fr.free.nrw.commons.explore.paging.PagingDataSourceFactory<T> dataSourceFactory(@org.jetbrains.annotations.NotNull
    io.reactivex.processors.PublishProcessor<fr.free.nrw.commons.explore.paging.LoadingState> loadingStates, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.Integer, ? extends java.util.List<? extends T>> loadFunction) {
        return null;
    }
}
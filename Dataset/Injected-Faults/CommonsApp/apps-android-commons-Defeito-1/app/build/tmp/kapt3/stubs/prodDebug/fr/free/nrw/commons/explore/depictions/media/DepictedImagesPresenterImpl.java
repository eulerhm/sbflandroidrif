package fr.free.nrw.commons.explore.depictions.media;

import java.lang.System;

/**
 * Presenter for DepictedImagesFragment
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lfr/free/nrw/commons/explore/depictions/media/DepictedImagesPresenterImpl;", "Lfr/free/nrw/commons/explore/paging/BasePagingPresenter;", "Lfr/free/nrw/commons/Media;", "Lfr/free/nrw/commons/explore/depictions/media/DepictedImagesPresenter;", "mainThreadScheduler", "Lio/reactivex/Scheduler;", "dataSourceFactory", "Lfr/free/nrw/commons/explore/depictions/media/PageableDepictedMediaDataSource;", "(Lio/reactivex/Scheduler;Lfr/free/nrw/commons/explore/depictions/media/PageableDepictedMediaDataSource;)V", "app-commons-v4.2.1-master_prodDebug"})
public final class DepictedImagesPresenterImpl extends fr.free.nrw.commons.explore.paging.BasePagingPresenter<fr.free.nrw.commons.Media> implements fr.free.nrw.commons.explore.depictions.media.DepictedImagesPresenter {
    
    @javax.inject.Inject
    public DepictedImagesPresenterImpl(@org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "main_thread")
    io.reactivex.Scheduler mainThreadScheduler, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.depictions.media.PageableDepictedMediaDataSource dataSourceFactory) {
        super(null, null);
    }
}
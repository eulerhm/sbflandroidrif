package fr.free.nrw.commons.upload.structure.depictions;

import java.lang.System;

/**
 * The model class for depictions in upload
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u00152\u0006\u0010\u0016\u001a\u00020\u0011H\u0002J\"\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lfr/free/nrw/commons/upload/structure/depictions/DepictModel;", "", "depictsClient", "Lfr/free/nrw/commons/explore/depictions/DepictsClient;", "(Lfr/free/nrw/commons/explore/depictions/DepictsClient;)V", "nearbyPlaces", "Lio/reactivex/processors/BehaviorProcessor;", "", "Lfr/free/nrw/commons/nearby/Place;", "getNearbyPlaces", "()Lio/reactivex/processors/BehaviorProcessor;", "cleanUp", "", "getDepictions", "Lio/reactivex/Single;", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "ids", "", "getPlaceDepictions", "qids", "networkItems", "Lio/reactivex/Flowable;", "query", "searchAllEntities", "repository", "Lfr/free/nrw/commons/repository/UploadRepository;", "Companion", "app-commons-v4.2.1-main_betaDebug"})
@javax.inject.Singleton
public final class DepictModel {
    private final fr.free.nrw.commons.explore.depictions.DepictsClient depictsClient = null;
    @org.jetbrains.annotations.NotNull
    private final io.reactivex.processors.BehaviorProcessor<java.util.List<fr.free.nrw.commons.nearby.Place>> nearbyPlaces = null;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.upload.structure.depictions.DepictModel.Companion Companion = null;
    private static final int SEARCH_DEPICTS_LIMIT = 25;
    
    @javax.inject.Inject
    public DepictModel(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.depictions.DepictsClient depictsClient) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.processors.BehaviorProcessor<java.util.List<fr.free.nrw.commons.nearby.Place>> getNearbyPlaces() {
        return null;
    }
    
    /**
     * Search for depictions
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Flowable<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> searchAllEntities(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.repository.UploadRepository repository) {
        return null;
    }
    
    /**
     * Provides [DepictedItem] instances via a [Single] for a given list of ids, providing an
     * empty list if no places/country are provided or if there is an error
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> getPlaceDepictions(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> qids) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> getDepictions(@org.jetbrains.annotations.NotNull
    java.lang.String ids) {
        return null;
    }
    
    private final io.reactivex.Flowable<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> networkItems(java.lang.String query) {
        return null;
    }
    
    public final void cleanUp() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lfr/free/nrw/commons/upload/structure/depictions/DepictModel$Companion;", "", "()V", "SEARCH_DEPICTS_LIMIT", "", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
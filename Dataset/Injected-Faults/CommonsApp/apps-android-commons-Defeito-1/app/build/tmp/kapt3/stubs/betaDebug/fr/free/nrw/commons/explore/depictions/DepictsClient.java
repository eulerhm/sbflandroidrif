package fr.free.nrw.commons.explore.depictions;

import java.lang.System;

/**
 * Depicts Client to handle custom calls to Commons Wikibase APIs
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J,\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J \u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0006J\u0018\u0010\u0017\u001a\u00020\t*\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J,\u0010\u001a\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u000b \u001b*\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000f0\u000f0\u0006*\b\u0012\u0004\u0012\u00020\t0\u0006H\u0003J\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u000f*\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lfr/free/nrw/commons/explore/depictions/DepictsClient;", "", "depictsInterface", "Lfr/free/nrw/commons/upload/depicts/DepictsInterface;", "(Lfr/free/nrw/commons/upload/depicts/DepictsInterface;)V", "getEntities", "Lio/reactivex/Single;", "Lorg/wikipedia/wikidata/Entities;", "ids", "", "mapToDepictItem", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "entity", "Lorg/wikipedia/wikidata/Entities$Entity;", "searchForDepictions", "", "query", "limit", "", "offset", "toDepictions", "sparqlResponse", "Lfr/free/nrw/commons/mwapi/SparqlResponse;", "byLanguageOrFirstOrEmpty", "", "Lorg/wikipedia/wikidata/Entities$Label;", "mapToDepictions", "kotlin.jvm.PlatformType", "toIds", "Lorg/wikipedia/wikidata/Statement_partial;", "app-commons-v4.2.1-main_betaDebug"})
@javax.inject.Singleton
public final class DepictsClient {
    private final fr.free.nrw.commons.upload.depicts.DepictsInterface depictsInterface = null;
    
    @javax.inject.Inject
    public DepictsClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.DepictsInterface depictsInterface) {
        super();
    }
    
    /**
     * Search for depictions using the search item
     * @return list of depicted items
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> searchForDepictions(@org.jetbrains.annotations.Nullable
    java.lang.String query, int limit, int offset) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<org.wikipedia.wikidata.Entities> getEntities(@org.jetbrains.annotations.NotNull
    java.lang.String ids) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> toDepictions(@org.jetbrains.annotations.NotNull
    io.reactivex.Single<fr.free.nrw.commons.mwapi.SparqlResponse> sparqlResponse) {
        return null;
    }
    
    /**
     * Fetches Entities from ids ex. "Q1233|Q546" and converts them into DepictedItem
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    private final io.reactivex.Single<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> mapToDepictions(io.reactivex.Single<java.lang.String> $this$mapToDepictions) {
        return null;
    }
    
    /**
     * Convert different entities into DepictedItem
     */
    private final fr.free.nrw.commons.upload.structure.depictions.DepictedItem mapToDepictItem(org.wikipedia.wikidata.Entities.Entity entity) {
        return null;
    }
    
    /**
     * Tries to get Entities.Label by default language from the map.
     * If that returns null, Tries to retrieve first element from the map.
     * If that still returns null, function returns "".
     */
    private final java.lang.String byLanguageOrFirstOrEmpty(java.util.Map<java.lang.String, ? extends org.wikipedia.wikidata.Entities.Label> $this$byLanguageOrFirstOrEmpty) {
        return null;
    }
    
    /**
     * returns list of id ex. "Q2323" from Statement_partial
     */
    private final java.util.List<java.lang.String> toIds(java.util.List<org.wikipedia.wikidata.Statement_partial> $this$toIds) {
        return null;
    }
}
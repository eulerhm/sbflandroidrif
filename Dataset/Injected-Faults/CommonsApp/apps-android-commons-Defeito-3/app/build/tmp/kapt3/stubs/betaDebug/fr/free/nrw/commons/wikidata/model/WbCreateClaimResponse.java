package fr.free.nrw.commons.wikidata.model;

import java.lang.System;

/**
 * Wikidata create claim response model class
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lfr/free/nrw/commons/wikidata/model/WbCreateClaimResponse;", "", "pageinfo", "Lfr/free/nrw/commons/wikidata/model/PageInfo;", "success", "", "(Lfr/free/nrw/commons/wikidata/model/PageInfo;I)V", "getPageinfo", "()Lfr/free/nrw/commons/wikidata/model/PageInfo;", "getSuccess", "()I", "app-commons-v4.2.1-main_betaDebug"})
public final class WbCreateClaimResponse {
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "pageinfo")
    @com.google.gson.annotations.Expose
    private final fr.free.nrw.commons.wikidata.model.PageInfo pageinfo = null;
    @com.google.gson.annotations.SerializedName(value = "success")
    @com.google.gson.annotations.Expose
    private final int success = 0;
    
    public WbCreateClaimResponse(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.wikidata.model.PageInfo pageinfo, int success) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.wikidata.model.PageInfo getPageinfo() {
        return null;
    }
    
    public final int getSuccess() {
        return 0;
    }
}
package fr.free.nrw.commons.wikidata.model;

import java.lang.System;

/**
 * PageInfo model class with last revision id of the edited Wikidata entity
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lfr/free/nrw/commons/wikidata/model/PageInfo;", "", "lastrevid", "", "(J)V", "getLastrevid", "()J", "app-commons-v4.2.1-master_prodDebug"})
public final class PageInfo {
    @com.google.gson.annotations.SerializedName(value = "lastrevid")
    @com.google.gson.annotations.Expose
    private final long lastrevid = 0L;
    
    public PageInfo(long lastrevid) {
        super();
    }
    
    public final long getLastrevid() {
        return 0L;
    }
}
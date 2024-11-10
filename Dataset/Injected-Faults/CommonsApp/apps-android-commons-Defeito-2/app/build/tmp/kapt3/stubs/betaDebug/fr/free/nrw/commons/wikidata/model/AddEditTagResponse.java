package fr.free.nrw.commons.wikidata.model;

import java.lang.System;

/**
 * Response class for add edit tag
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lfr/free/nrw/commons/wikidata/model/AddEditTagResponse;", "", "()V", "tag", "", "Lfr/free/nrw/commons/wikidata/model/EditTag;", "getTag", "()Ljava/util/List;", "setTag", "(Ljava/util/List;)V", "app-commons-v4.2.1-main_betaDebug"})
public final class AddEditTagResponse {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.Expose
    @com.google.gson.annotations.SerializedName(value = "tag")
    private java.util.List<fr.free.nrw.commons.wikidata.model.EditTag> tag;
    
    public AddEditTagResponse() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<fr.free.nrw.commons.wikidata.model.EditTag> getTag() {
        return null;
    }
    
    public final void setTag(@org.jetbrains.annotations.Nullable
    java.util.List<fr.free.nrw.commons.wikidata.model.EditTag> p0) {
    }
}
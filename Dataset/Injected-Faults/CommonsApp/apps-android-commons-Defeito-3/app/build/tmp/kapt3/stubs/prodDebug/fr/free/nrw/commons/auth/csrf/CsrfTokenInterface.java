package fr.free.nrw.commons.auth.csrf;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\'\u00a8\u0006\u0005"}, d2 = {"Lfr/free/nrw/commons/auth/csrf/CsrfTokenInterface;", "", "getCsrfTokenCall", "Lretrofit2/Call;", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "app-commons-v4.2.1-master_prodDebug"})
public abstract interface CsrfTokenInterface {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=query&meta=tokens&type=csrf")
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    public abstract retrofit2.Call<org.wikipedia.dataclient.mwapi.MwQueryResponse> getCsrfTokenCall();
}
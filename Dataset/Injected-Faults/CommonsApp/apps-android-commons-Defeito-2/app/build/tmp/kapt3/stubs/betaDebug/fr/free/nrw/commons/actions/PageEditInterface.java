package fr.free.nrw.commons.actions;

import java.lang.System;

/**
 * This interface facilitates wiki commons page editing services to the Networking module
 * which provides all network related services used by the app.
 *
 * This interface posts a form encoded request to the wikimedia API
 * with editing action as argument to edit a particular page
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J6\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u0006H\'J@\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u0006H\'J6\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u0006H\'J6\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u0014\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u0006H\'\u00a8\u0006\u0015"}, d2 = {"Lfr/free/nrw/commons/actions/PageEditInterface;", "", "getWikiText", "Lio/reactivex/Single;", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "title", "", "postAppendEdit", "Lio/reactivex/Observable;", "Lorg/wikipedia/edit/Edit;", "summary", "appendText", "token", "postCaptions", "Lorg/wikipedia/wikidata/Entities;", "language", "value", "postEdit", "text", "postPrependEdit", "prependText", "app-commons-v4.2.1-main_betaDebug"})
public abstract interface PageEditInterface {
    
    /**
     * This method posts such that the Content which the page
     * has will be completely replaced by the value being passed to the
     * "text" field of the encoded form data
     * @param title    Title of the page to edit. Cannot be used together with pageid.
     * @param summary  Edit summary. Also section title when section=new and sectiontitle is not set
     * @param text     Holds the page content
     * @param token    A "csrf" token
     */
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=edit")
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    @retrofit2.http.FormUrlEncoded
    public abstract io.reactivex.Observable<org.wikipedia.edit.Edit> postEdit(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "title")
    java.lang.String title, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "summary")
    java.lang.String summary, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "text")
    java.lang.String text, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "token")
    java.lang.String token);
    
    /**
     * This method posts such that the Content which the page
     * has will be appended with the value being passed to the
     * "appendText" field of the encoded form data
     * @param title    Title of the page to edit. Cannot be used together with pageid.
     * @param summary  Edit summary. Also section title when section=new and sectiontitle is not set
     * @param appendText Text to add to the end of the page
     * @param token    A "csrf" token
     */
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=edit")
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    @retrofit2.http.FormUrlEncoded
    public abstract io.reactivex.Observable<org.wikipedia.edit.Edit> postAppendEdit(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "title")
    java.lang.String title, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "summary")
    java.lang.String summary, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "appendtext")
    java.lang.String appendText, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "token")
    java.lang.String token);
    
    /**
     * This method posts such that the Content which the page
     * has will be prepended with the value being passed to the
     * "prependText" field of the encoded form data
     * @param title    Title of the page to edit. Cannot be used together with pageid.
     * @param summary  Edit summary. Also section title when section=new and sectiontitle is not set
     * @param prependText Text to add to the beginning of the page
     * @param token    A "csrf" token
     */
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=edit")
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    @retrofit2.http.FormUrlEncoded
    public abstract io.reactivex.Observable<org.wikipedia.edit.Edit> postPrependEdit(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "title")
    java.lang.String title, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "summary")
    java.lang.String summary, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "prependtext")
    java.lang.String prependText, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "token")
    java.lang.String token);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=wbsetlabel&format=json&site=commonswiki&formatversion=2")
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    @retrofit2.http.FormUrlEncoded
    public abstract io.reactivex.Observable<org.wikipedia.wikidata.Entities> postCaptions(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "summary")
    java.lang.String summary, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "title")
    java.lang.String title, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "language")
    java.lang.String language, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "value")
    java.lang.String value, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "token")
    java.lang.String token);
    
    /**
     * Get wiki text for provided file names
     * @param titles : Name of the file
     * @return Single<MwQueryResult>
     */
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=query&prop=revisions&rvprop=content|timestamp&rvlimit=1&converttitles=")
    public abstract io.reactivex.Single<org.wikipedia.dataclient.mwapi.MwQueryResponse> getWikiText(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "titles")
    java.lang.String title);
}
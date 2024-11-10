package fr.free.nrw.commons.actions;

import java.lang.System;

/**
 * This class acts as a Client to facilitate wiki page editing
 * services to various dependency providing modules such as the Network module, the Review Controller, etc.
 *
 * The methods provided by this class will post to the Media wiki api
 * documented at: https://commons.wikimedia.org/w/api.php?action=help&modules=edit
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bJ\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00112\u0006\u0010\u0012\u001a\u00020\u000bJ$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bJ,\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lfr/free/nrw/commons/actions/PageEditClient;", "", "csrfTokenClient", "Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient;", "pageEditInterface", "Lfr/free/nrw/commons/actions/PageEditInterface;", "(Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient;Lfr/free/nrw/commons/actions/PageEditInterface;)V", "appendEdit", "Lio/reactivex/Observable;", "", "pageTitle", "", "appendText", "summary", "edit", "text", "getCurrentWikiText", "Lio/reactivex/Single;", "title", "prependEdit", "prependText", "setCaptions", "", "language", "value", "app-commons-v4.2.1-master_prodDebug"})
public final class PageEditClient {
    private final fr.free.nrw.commons.auth.csrf.CsrfTokenClient csrfTokenClient = null;
    private final fr.free.nrw.commons.actions.PageEditInterface pageEditInterface = null;
    
    public PageEditClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.csrf.CsrfTokenClient csrfTokenClient, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.actions.PageEditInterface pageEditInterface) {
        super();
    }
    
    /**
     * Replace the content of a wiki page
     * @param pageTitle   Title of the page to edit
     * @param text        Holds the page content
     * @param summary     Edit summary
     * @return whether the edit was successful
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Observable<java.lang.Boolean> edit(@org.jetbrains.annotations.NotNull
    java.lang.String pageTitle, @org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    java.lang.String summary) {
        return null;
    }
    
    /**
     * Append text to the end of a wiki page
     * @param pageTitle   Title of the page to edit
     * @param appendText  The received page content is added to the end of the page
     * @param summary     Edit summary
     * @return whether the edit was successful
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Observable<java.lang.Boolean> appendEdit(@org.jetbrains.annotations.NotNull
    java.lang.String pageTitle, @org.jetbrains.annotations.NotNull
    java.lang.String appendText, @org.jetbrains.annotations.NotNull
    java.lang.String summary) {
        return null;
    }
    
    /**
     * Prepend text to the beginning of a wiki page
     * @param pageTitle   Title of the page to edit
     * @param prependText The received page content is added to the beginning of the page
     * @param summary     Edit summary
     * @return whether the edit was successful
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Observable<java.lang.Boolean> prependEdit(@org.jetbrains.annotations.NotNull
    java.lang.String pageTitle, @org.jetbrains.annotations.NotNull
    java.lang.String prependText, @org.jetbrains.annotations.NotNull
    java.lang.String summary) {
        return null;
    }
    
    /**
     * Set new labels to Wikibase server of commons
     * @param summary   Edit summary
     * @param title Title of the page to edit
     * @param language  Corresponding language of label
     * @param value label
     * @return 1 when the edit was successful
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Observable<java.lang.Integer> setCaptions(@org.jetbrains.annotations.NotNull
    java.lang.String summary, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    /**
     * Get whole WikiText of required file
     * @param title : Name of the file
     * @return Observable<MwQueryResult>
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.String> getCurrentWikiText(@org.jetbrains.annotations.NotNull
    java.lang.String title) {
        return null;
    }
}
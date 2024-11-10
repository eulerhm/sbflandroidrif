package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u0006H\u0007J&\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0006J8\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/util/ReblogUtils;", "", "urlUtils", "Lorg/wordpress/android/util/UrlUtilsWrapper;", "(Lorg/wordpress/android/util/UrlUtilsWrapper;)V", "embeddedCitation", "", "string", "embeddedQuote", "embeddedWpQuote", "htmlImage", "imageUrlString", "htmlParagraph", "htmlWpImage", "hyperLink", "url", "text", "quoteWithCitation", "quote", "citationUrl", "citationTitle", "reblogContent", "imageUrl", "isGutenberg", "", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class ReblogUtils {
    private final org.wordpress.android.util.UrlUtilsWrapper urlUtils = null;
    
    @javax.inject.Inject()
    public ReblogUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UrlUtilsWrapper urlUtils) {
        super();
    }
    
    /**
     * Returns the [string] embedded in a quote
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String embeddedQuote(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
        return null;
    }
    
    /**
     * Returns the [string] embedded in a WordPress quote
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String embeddedWpQuote(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
        return null;
    }
    
    /**
     * Returns the [string] embedded in a citation
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String embeddedCitation(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
        return null;
    }
    
    /**
     * Creates an html image from an image url string or null if the url is not valid
     * @param imageUrlString the image url string
     * @return html image or null if the url is not valid
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String htmlImage(@org.jetbrains.annotations.Nullable()
    java.lang.String imageUrlString) {
        return null;
    }
    
    /**
     * Returns an html WordPress image from an image url string or null if the url is not valid
     * @param imageUrlString the image url string
     * @return html image or null if the url is not valid
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String htmlWpImage(@org.jetbrains.annotations.Nullable()
    java.lang.String imageUrlString) {
        return null;
    }
    
    /**
     * Returns the [string] in an html paragraph
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String htmlParagraph(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
        return null;
    }
    
    /**
     * Creates a hyperlink from a url after validating the link
     * @param url the url
     * @param text the text to display. If not provided the [url] will be used
     * @return the html of the hyperlink or null if the url is not valid
     */
    @org.jetbrains.annotations.Nullable()
    @kotlin.jvm.JvmOverloads()
    public final java.lang.String hyperLink(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    /**
     * Creates a hyperlink from a url after validating the link
     * @param url the url
     * @param text the text to display. If not provided the [url] will be used
     * @return the html of the hyperlink or null if the url is not valid
     */
    @org.jetbrains.annotations.Nullable()
    @kotlin.jvm.JvmOverloads()
    public final java.lang.String hyperLink(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    /**
     * Provides an html containing the post [quote] followed by a link citation if the later is valid
     * @param quote the post quot
     * @param citationUrl the citation link (optional)
     * @param citationTitle the citation text (optional)
     * @return the html containing the post [quote] followed by a link citation if the later is valid
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String quoteWithCitation(@org.jetbrains.annotations.NotNull()
    java.lang.String quote, @org.jetbrains.annotations.Nullable()
    java.lang.String citationUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String citationTitle) {
        return null;
    }
    
    /**
     * Provides the reblog post containing a featured image (if exists) followed by the quote and citation
     * @param imageUrl the featured image url (might be null)
     * @param quote the post quote
     * @param citationTitle the citation text
     * @param citationUrl the citation link
     * @param isGutenberg if true
     * @return the html containing the featured image (if exists) followed by the quote and citation
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    public final java.lang.String reblogContent(@org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String quote, @org.jetbrains.annotations.Nullable()
    java.lang.String citationTitle, @org.jetbrains.annotations.Nullable()
    java.lang.String citationUrl) {
        return null;
    }
    
    /**
     * Provides the reblog post containing a featured image (if exists) followed by the quote and citation
     * @param imageUrl the featured image url (might be null)
     * @param quote the post quote
     * @param citationTitle the citation text
     * @param citationUrl the citation link
     * @param isGutenberg if true
     * @return the html containing the featured image (if exists) followed by the quote and citation
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    public final java.lang.String reblogContent(@org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String quote, @org.jetbrains.annotations.Nullable()
    java.lang.String citationTitle, @org.jetbrains.annotations.Nullable()
    java.lang.String citationUrl, boolean isGutenberg) {
        return null;
    }
}
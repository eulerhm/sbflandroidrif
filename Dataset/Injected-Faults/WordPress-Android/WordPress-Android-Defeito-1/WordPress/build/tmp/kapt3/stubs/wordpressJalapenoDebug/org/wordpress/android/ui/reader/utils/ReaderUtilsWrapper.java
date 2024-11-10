package org.wordpress.android.ui.reader.utils;

import java.lang.System;

/**
 * Injectable wrapper around ReaderUtils.
 *
 * ReaderUtils interface is consisted of static methods, which make the client code difficult to test/mock. Main purpose of
 * this wrapper is to make testing easier.
 */
@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\bJ\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010J2\u0010\u0016\u001a\u0004\u0018\u00010\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bJ*\u0010\u0016\u001a\u0004\u0018\u00010\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u0004\u0018\u00010\u00102\u0006\u0010#\u001a\u00020\u0012J\u0016\u0010$\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010%\u001a\u00020\nJ\u001e\u0010&\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nJ\u0016\u0010\'\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "", "appContext", "Landroid/content/Context;", "tagUpdateClientUtilsProvider", "Lorg/wordpress/android/ui/reader/services/update/TagUpdateClientUtilsProvider;", "(Landroid/content/Context;Lorg/wordpress/android/ui/reader/services/update/TagUpdateClientUtilsProvider;)V", "commentExists", "", "blogId", "", "postId", "commentId", "getDefaultTagFromDbOrCreateInMemory", "Lorg/wordpress/android/models/ReaderTag;", "getLongLikeLabelText", "", "numLikes", "", "isLikedByCurrentUser", "getReportPostUrl", "blogUrl", "getResizedImageUrl", "imageUrl", "width", "height", "isPrivate", "isAtomic", "siteAccessibilityInfo", "Lorg/wordpress/android/ui/reader/utils/SiteAccessibilityInfo;", "getTagFromTagName", "tagName", "tagType", "Lorg/wordpress/android/models/ReaderTagType;", "getTextForCommentSnippet", "numComments", "isExternalFeed", "feedId", "postAndCommentExists", "postExists", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderUtilsWrapper {
    private final android.content.Context appContext = null;
    private final org.wordpress.android.ui.reader.services.update.TagUpdateClientUtilsProvider tagUpdateClientUtilsProvider = null;
    
    @javax.inject.Inject()
    public ReaderUtilsWrapper(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.services.update.TagUpdateClientUtilsProvider tagUpdateClientUtilsProvider) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getResizedImageUrl(@org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, int width, int height, boolean isPrivate, boolean isAtomic) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getResizedImageUrl(@org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, int width, int height, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.SiteAccessibilityInfo siteAccessibilityInfo) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.ReaderTag getTagFromTagName(@org.jetbrains.annotations.NotNull()
    java.lang.String tagName, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTagType tagType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.ReaderTag getDefaultTagFromDbOrCreateInMemory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLongLikeLabelText(int numLikes, boolean isLikedByCurrentUser) {
        return null;
    }
    
    public final boolean isExternalFeed(long blogId, long feedId) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReportPostUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String blogUrl) {
        return null;
    }
    
    public final boolean postAndCommentExists(long blogId, long postId, long commentId) {
        return false;
    }
    
    public final boolean postExists(long blogId, long postId) {
        return false;
    }
    
    public final boolean commentExists(long blogId, long postId, long commentId) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTextForCommentSnippet(int numComments) {
        return null;
    }
}
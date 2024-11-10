package org.wordpress.android.ui.reader.repository.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0002J\u0014\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bJ\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fJ\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\b2\u0006\u0010\u001a\u001a\u00020\u000fJ\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u000fJ \u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0\u001c0\b2\u0006\u0010 \u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/ParseDiscoverCardsJsonUseCase;", "", "crashLogging", "Lcom/automattic/android/tracks/crashlogging/CrashLogging;", "(Lcom/automattic/android/tracks/crashlogging/CrashLogging;)V", "concatArrays", "Lorg/json/JSONArray;", "arrays", "", "convertListOfJsonArraysIntoSingleJsonArray", "jsons", "", "parseInterestCard", "Lorg/wordpress/android/models/ReaderTagList;", "interestCardJson", "Lorg/json/JSONObject;", "parseInterestTag", "Lorg/wordpress/android/models/ReaderTag;", "interestJsonCard", "parseNextPageHandle", "jsonObject", "parsePostCard", "Lorg/wordpress/android/models/ReaderPost;", "postCardJson", "parseRecommendedBlogsCard", "Lorg/wordpress/android/models/ReaderBlog;", "cardJson", "parseSimplifiedPostCard", "Lkotlin/Pair;", "", "simplifiedPostCardjson", "parseSimplifiedRecommendedBlogsCard", "recommendedBlogsCardJson", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ParseDiscoverCardsJsonUseCase {
    private final com.automattic.android.tracks.crashlogging.CrashLogging crashLogging = null;
    
    @javax.inject.Inject()
    public ParseDiscoverCardsJsonUseCase(@org.jetbrains.annotations.NotNull()
    com.automattic.android.tracks.crashlogging.CrashLogging crashLogging) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.ReaderPost parsePostCard(@org.jetbrains.annotations.NotNull()
    org.json.JSONObject postCardJson) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.Long, java.lang.Long> parseSimplifiedPostCard(@org.jetbrains.annotations.NotNull()
    org.json.JSONObject simplifiedPostCardjson) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<java.lang.Long, java.lang.Long>> parseSimplifiedRecommendedBlogsCard(@org.jetbrains.annotations.NotNull()
    org.json.JSONObject recommendedBlogsCardJson) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.ReaderTagList parseInterestCard(@org.jetbrains.annotations.Nullable()
    org.json.JSONObject interestCardJson) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.models.ReaderBlog> parseRecommendedBlogsCard(@org.jetbrains.annotations.NotNull()
    org.json.JSONObject cardJson) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String parseNextPageHandle(@org.jetbrains.annotations.NotNull()
    org.json.JSONObject jsonObject) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.json.JSONArray convertListOfJsonArraysIntoSingleJsonArray(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> jsons) {
        return null;
    }
    
    private final org.wordpress.android.models.ReaderTag parseInterestTag(org.json.JSONObject interestJsonCard) {
        return null;
    }
    
    private final org.json.JSONArray concatArrays(java.util.List<? extends org.json.JSONArray> arrays) {
        return null;
    }
}
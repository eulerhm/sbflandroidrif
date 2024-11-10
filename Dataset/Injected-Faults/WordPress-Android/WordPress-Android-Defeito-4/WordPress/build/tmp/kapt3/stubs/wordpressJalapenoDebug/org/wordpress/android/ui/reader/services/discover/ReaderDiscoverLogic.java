package org.wordpress.android.ui.reader.services.discover;

import java.lang.System;

/**
 * This class contains logic related to fetching data for the discover tab in the Reader.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001RB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010/\u001a\u000200H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u000203H\u0002J\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0002J\u0010\u00108\u001a\u0002062\u0006\u00107\u001a\u000206H\u0002J\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020;0:H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J+\u0010<\u001a\u0002002\u0006\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u0001062\u0006\u0010@\u001a\u00020AH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BJ\u0016\u0010C\u001a\u0002002\f\u0010D\u001a\b\u0012\u0004\u0012\u00020;0:H\u0002J\u0010\u0010E\u001a\u0002002\u0006\u0010F\u001a\u000203H\u0002J\u0016\u0010G\u001a\u0002002\f\u0010H\u001a\b\u0012\u0004\u0012\u00020I0:H\u0002J \u0010J\u001a\u0012\u0012\u0004\u0012\u00020L0Kj\b\u0012\u0004\u0012\u00020L`M2\u0006\u00104\u001a\u000203H\u0002J\u0018\u0010N\u001a\u0002002\u0006\u0010O\u001a\u00020>2\b\u0010P\u001a\u0004\u0018\u00010\u001cJ\u0018\u0010Q\u001a\u0002002\u0006\u0010=\u001a\u00020>2\u0006\u0010@\u001a\u00020AH\u0002R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u00020\u001e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010#\u001a\u00020$8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u001e\u0010)\u001a\u00020*8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006S"}, d2 = {"Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverLogic;", "", "completionListener", "Lorg/wordpress/android/ui/reader/services/ServiceCompletionListener;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "appComponent", "Lorg/wordpress/android/modules/AppComponent;", "(Lorg/wordpress/android/ui/reader/services/ServiceCompletionListener;Lkotlinx/coroutines/CoroutineScope;Lorg/wordpress/android/modules/AppComponent;)V", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "getAppPrefsWrapper", "()Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "setAppPrefsWrapper", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "getDiscoverCardsUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/GetDiscoverCardsUseCase;", "getGetDiscoverCardsUseCase", "()Lorg/wordpress/android/ui/reader/repository/usecases/GetDiscoverCardsUseCase;", "setGetDiscoverCardsUseCase", "(Lorg/wordpress/android/ui/reader/repository/usecases/GetDiscoverCardsUseCase;)V", "getFollowedTagsUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/tags/GetFollowedTagsUseCase;", "getGetFollowedTagsUseCase", "()Lorg/wordpress/android/ui/reader/repository/usecases/tags/GetFollowedTagsUseCase;", "setGetFollowedTagsUseCase", "(Lorg/wordpress/android/ui/reader/repository/usecases/tags/GetFollowedTagsUseCase;)V", "listenerCompanion", "Landroid/app/job/JobParameters;", "parseDiscoverCardsJsonUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/ParseDiscoverCardsJsonUseCase;", "getParseDiscoverCardsJsonUseCase", "()Lorg/wordpress/android/ui/reader/repository/usecases/ParseDiscoverCardsJsonUseCase;", "setParseDiscoverCardsJsonUseCase", "(Lorg/wordpress/android/ui/reader/repository/usecases/ParseDiscoverCardsJsonUseCase;)V", "readerBlogTableWrapper", "Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "getReaderBlogTableWrapper", "()Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "setReaderBlogTableWrapper", "(Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;)V", "readerTagTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderTagTableWrapper;", "getReaderTagTableWrapper", "()Lorg/wordpress/android/datasets/wrappers/ReaderTagTableWrapper;", "setReaderTagTableWrapper", "(Lorg/wordpress/android/datasets/wrappers/ReaderTagTableWrapper;)V", "clearCache", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSimplifiedJson", "Lorg/json/JSONArray;", "cardsJsonArray", "createSimplifiedPostJson", "Lorg/json/JSONObject;", "originalCardJson", "createSimplifiedRecommendedBlogsCardJson", "getRecommendedBlogsToBeDeleted", "", "Lorg/wordpress/android/models/ReaderBlog;", "handleRequestDiscoverDataResponse", "taskType", "Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverLogic$DiscoverTasks;", "json", "resultListener", "Lorg/wordpress/android/ui/reader/actions/ReaderActions$UpdateResultListener;", "(Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverLogic$DiscoverTasks;Lorg/json/JSONObject;Lorg/wordpress/android/ui/reader/actions/ReaderActions$UpdateResultListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertBlogsIntoDb", "blogs", "insertCardsJsonIntoDb", "simplifiedCardsJson", "insertPostsIntoDb", "posts", "Lorg/wordpress/android/models/ReaderPost;", "parseCards", "Ljava/util/ArrayList;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard;", "Lkotlin/collections/ArrayList;", "performTasks", "task", "companion", "requestDataForDiscover", "DiscoverTasks", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderDiscoverLogic {
    private final org.wordpress.android.ui.reader.services.ServiceCompletionListener completionListener = null;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.repository.usecases.ParseDiscoverCardsJsonUseCase parseDiscoverCardsJsonUseCase;
    @javax.inject.Inject()
    public org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.datasets.wrappers.ReaderTagTableWrapper readerTagTableWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.repository.usecases.tags.GetFollowedTagsUseCase getFollowedTagsUseCase;
    @javax.inject.Inject()
    public org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.repository.usecases.GetDiscoverCardsUseCase getDiscoverCardsUseCase;
    private android.app.job.JobParameters listenerCompanion;
    
    public ReaderDiscoverLogic(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.services.ServiceCompletionListener completionListener, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.modules.AppComponent appComponent) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.repository.usecases.ParseDiscoverCardsJsonUseCase getParseDiscoverCardsJsonUseCase() {
        return null;
    }
    
    public final void setParseDiscoverCardsJsonUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.ParseDiscoverCardsJsonUseCase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.AppPrefsWrapper getAppPrefsWrapper() {
        return null;
    }
    
    public final void setAppPrefsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.datasets.wrappers.ReaderTagTableWrapper getReaderTagTableWrapper() {
        return null;
    }
    
    public final void setReaderTagTableWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderTagTableWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.repository.usecases.tags.GetFollowedTagsUseCase getGetFollowedTagsUseCase() {
        return null;
    }
    
    public final void setGetFollowedTagsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.tags.GetFollowedTagsUseCase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.datasets.ReaderBlogTableWrapper getReaderBlogTableWrapper() {
        return null;
    }
    
    public final void setReaderBlogTableWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.ReaderBlogTableWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.repository.usecases.GetDiscoverCardsUseCase getGetDiscoverCardsUseCase() {
        return null;
    }
    
    public final void setGetDiscoverCardsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.GetDiscoverCardsUseCase p0) {
    }
    
    public final void performTasks(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.DiscoverTasks task, @org.jetbrains.annotations.Nullable()
    android.app.job.JobParameters companion) {
    }
    
    private final void requestDataForDiscover(org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.DiscoverTasks taskType, org.wordpress.android.ui.reader.actions.ReaderActions.UpdateResultListener resultListener) {
    }
    
    private final java.lang.Object handleRequestDiscoverDataResponse(org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.DiscoverTasks taskType, org.json.JSONObject json, org.wordpress.android.ui.reader.actions.ReaderActions.UpdateResultListener resultListener, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.util.ArrayList<org.wordpress.android.models.discover.ReaderDiscoverCard> parseCards(org.json.JSONArray cardsJsonArray) {
        return null;
    }
    
    private final void insertPostsIntoDb(java.util.List<? extends org.wordpress.android.models.ReaderPost> posts) {
    }
    
    private final void insertBlogsIntoDb(java.util.List<? extends org.wordpress.android.models.ReaderBlog> blogs) {
    }
    
    /**
     * This method creates a simplified version of the provided json.
     *
     * It for example copies only ids from post object as we don't need to store the gigantic post in the json
     * as it's already stored in the db.
     */
    private final org.json.JSONArray createSimplifiedJson(org.json.JSONArray cardsJsonArray) {
        return null;
    }
    
    /**
     * Create a simplified copy of the json - keeps only postId, siteId and pseudoId.
     */
    private final org.json.JSONObject createSimplifiedPostJson(org.json.JSONObject originalCardJson) {
        return null;
    }
    
    private final org.json.JSONObject createSimplifiedRecommendedBlogsCardJson(org.json.JSONObject originalCardJson) {
        return null;
    }
    
    private final void insertCardsJsonIntoDb(org.json.JSONArray simplifiedCardsJson) {
    }
    
    private final java.lang.Object clearCache(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object getRecommendedBlogsToBeDeleted(kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.models.ReaderBlog>> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverLogic$DiscoverTasks;", "", "(Ljava/lang/String;I)V", "REQUEST_MORE", "REQUEST_FIRST_PAGE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum DiscoverTasks {
        /*public static final*/ REQUEST_MORE /* = new REQUEST_MORE() */,
        /*public static final*/ REQUEST_FIRST_PAGE /* = new REQUEST_FIRST_PAGE() */;
        
        DiscoverTasks() {
        }
    }
}
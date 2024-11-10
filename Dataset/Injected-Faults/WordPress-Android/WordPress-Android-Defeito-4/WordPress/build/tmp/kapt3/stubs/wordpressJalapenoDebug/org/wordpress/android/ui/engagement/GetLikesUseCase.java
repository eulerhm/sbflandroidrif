package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 <2\u00020\u0001:\u0007<=>?@ABB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010\u0018\u001a\u00020\u0019J8\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u00132\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J7\u0010&\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\'\u00101\u001a\b\u0012\u0004\u0012\u00020+022\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00103J\'\u00104\u001a\b\u0012\u0004\u0012\u00020+022\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00103J-\u00105\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\'\u001a\u00020(2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00106J\u0010\u00107\u001a\u00020\u00192\u0006\u00108\u001a\u000209H\u0007J\u0010\u0010:\u001a\u00020\u00192\u0006\u00108\u001a\u00020;H\u0007R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u00140\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006C"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "commentStore", "Lorg/wordpress/android/fluxc/store/CommentStore;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/CommentStore;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/fluxc/store/AccountStore;)V", "getAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "getCommentStore", "()Lorg/wordpress/android/fluxc/store/CommentStore;", "getLikesContinuations", "", "", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/Store$OnChanged;", "getPostStore", "()Lorg/wordpress/android/fluxc/store/PostStore;", "clear", "", "getFailureState", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure;", "noNetworkDetected", "", "orderedLikes", "", "Lorg/wordpress/android/fluxc/model/LikeModel;", "errorMessage", "expectedNumLikes", "", "pageInfo", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;", "getLikes", "category", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeCategory;", "flow", "Lkotlinx/coroutines/flow/FlowCollector;", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState;", "fingerPrint", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeGroupFingerPrint;", "paginationParams", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PaginationParams;", "(Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeCategory;Lkotlinx/coroutines/flow/FlowCollector;Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeGroupFingerPrint;Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PaginationParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLikesForComment", "Lkotlinx/coroutines/flow/Flow;", "(Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeGroupFingerPrint;Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PaginationParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLikesForPost", "makeRequest", "(Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeCategory;Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeGroupFingerPrint;Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PaginationParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCommentLikesChanged", "event", "Lorg/wordpress/android/fluxc/store/CommentStore$OnCommentLikesChanged;", "onPostLikesChanged", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostLikesChanged;", "Companion", "FailureType", "GetLikesState", "LikeCategory", "LikeGroupFingerPrint", "PaginationParams", "PagingInfo", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GetLikesUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.CommentStore commentStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private java.util.Map<java.lang.String, kotlin.coroutines.Continuation<org.wordpress.android.fluxc.store.Store.OnChanged<?>>> getLikesContinuations;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.engagement.GetLikesUseCase.Companion Companion = null;
    private static final long PROGRESS_DELAY_MS = 600L;
    
    @javax.inject.Inject()
    public GetLikesUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @java.lang.SuppressWarnings(value = {"Unused"})
    org.wordpress.android.fluxc.store.CommentStore commentStore, @org.jetbrains.annotations.NotNull()
    @java.lang.SuppressWarnings(value = {"Unused"})
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.CommentStore getCommentStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.PostStore getPostStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getAccountStore() {
        return null;
    }
    
    public final void clear() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLikesForPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesUseCase.LikeGroupFingerPrint fingerPrint, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesUseCase.PaginationParams paginationParams, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLikesForComment(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesUseCase.LikeGroupFingerPrint fingerPrint, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesUseCase.PaginationParams paginationParams, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState>> continuation) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"ComplexMethod", "NestedBlockDepth", "LoopWithTooManyJumpStatements"})
    private final java.lang.Object getLikes(org.wordpress.android.ui.engagement.GetLikesUseCase.LikeCategory category, kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState> flow, org.wordpress.android.ui.engagement.GetLikesUseCase.LikeGroupFingerPrint fingerPrint, org.wordpress.android.ui.engagement.GetLikesUseCase.PaginationParams paginationParams, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object makeRequest(org.wordpress.android.ui.engagement.GetLikesUseCase.LikeCategory category, org.wordpress.android.ui.engagement.GetLikesUseCase.LikeGroupFingerPrint fingerPrint, org.wordpress.android.ui.engagement.GetLikesUseCase.PaginationParams paginationParams, kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.Store.OnChanged<?>> continuation) {
        return null;
    }
    
    private final org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Failure getFailureState(boolean noNetworkDetected, java.util.List<org.wordpress.android.fluxc.model.LikeModel> orderedLikes, java.lang.String errorMessage, int expectedNumLikes, org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo pageInfo) {
        return null;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onPostLikesChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostLikesChanged event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onCommentLikesChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.CommentStore.OnCommentLikesChanged event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState;", "", "()V", "Failure", "LikesData", "Loading", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Loading;", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$LikesData;", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class GetLikesState {
        
        private GetLikesState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Loading;", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Loading extends org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Loading INSTANCE = null;
            
            private Loading() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\fJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00c6\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012JH\u0010\u001d\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010!H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00d6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$LikesData;", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState;", "likes", "", "Lorg/wordpress/android/fluxc/model/LikeModel;", "expectedNumLikes", "", "hasMore", "", "pageInfo", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;", "iLike", "(Ljava/util/List;IZLorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;Ljava/lang/Boolean;)V", "getExpectedNumLikes", "()I", "getHasMore", "()Z", "getILike", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLikes", "()Ljava/util/List;", "getPageInfo", "()Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/util/List;IZLorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;Ljava/lang/Boolean;)Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$LikesData;", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class LikesData extends org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.fluxc.model.LikeModel> likes = null;
            private final int expectedNumLikes = 0;
            private final boolean hasMore = false;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo pageInfo = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Boolean iLike = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.LikesData copy(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.fluxc.model.LikeModel> likes, int expectedNumLikes, boolean hasMore, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo pageInfo, @org.jetbrains.annotations.Nullable()
            java.lang.Boolean iLike) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public LikesData(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.fluxc.model.LikeModel> likes, int expectedNumLikes, boolean hasMore, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo pageInfo, @org.jetbrains.annotations.Nullable()
            java.lang.Boolean iLike) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.LikeModel> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.LikeModel> getLikes() {
                return null;
            }
            
            public final int component2() {
                return 0;
            }
            
            public final int getExpectedNumLikes() {
                return 0;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getHasMore() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo getPageInfo() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Boolean component5() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Boolean getILike() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u00014BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u0012J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\'\u001a\u00020\nH\u00c6\u0003J\t\u0010(\u001a\u00020\fH\u00c6\u0003J\t\u0010)\u001a\u00020\u000eH\u00c6\u0003J\t\u0010*\u001a\u00020\u0010H\u00c6\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010 Jf\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u00c6\u0001\u00a2\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020\u000e2\b\u0010/\u001a\u0004\u0018\u000100H\u00d6\u0003J\t\u00101\u001a\u00020\fH\u00d6\u0001J\t\u00102\u001a\u000203H\u00d6\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure;", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState;", "failureType", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$FailureType;", "error", "Lorg/wordpress/android/ui/utils/UiString;", "cachedLikes", "", "Lorg/wordpress/android/fluxc/model/LikeModel;", "emptyStateData", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure$EmptyStateData;", "expectedNumLikes", "", "hasMore", "", "pageInfo", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;", "iLike", "(Lorg/wordpress/android/ui/engagement/GetLikesUseCase$FailureType;Lorg/wordpress/android/ui/utils/UiString;Ljava/util/List;Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure$EmptyStateData;IZLorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;Ljava/lang/Boolean;)V", "getCachedLikes", "()Ljava/util/List;", "getEmptyStateData", "()Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure$EmptyStateData;", "getError", "()Lorg/wordpress/android/ui/utils/UiString;", "getExpectedNumLikes", "()I", "getFailureType", "()Lorg/wordpress/android/ui/engagement/GetLikesUseCase$FailureType;", "getHasMore", "()Z", "getILike", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getPageInfo", "()Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Lorg/wordpress/android/ui/engagement/GetLikesUseCase$FailureType;Lorg/wordpress/android/ui/utils/UiString;Ljava/util/List;Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure$EmptyStateData;IZLorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;Ljava/lang/Boolean;)Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure;", "equals", "other", "", "hashCode", "toString", "", "EmptyStateData", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failure extends org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.engagement.GetLikesUseCase.FailureType failureType = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString error = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.fluxc.model.LikeModel> cachedLikes = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Failure.EmptyStateData emptyStateData = null;
            private final int expectedNumLikes = 0;
            private final boolean hasMore = false;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo pageInfo = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Boolean iLike = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Failure copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.engagement.GetLikesUseCase.FailureType failureType, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString error, @org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.fluxc.model.LikeModel> cachedLikes, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Failure.EmptyStateData emptyStateData, int expectedNumLikes, boolean hasMore, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo pageInfo, @org.jetbrains.annotations.Nullable()
            java.lang.Boolean iLike) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public Failure(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.engagement.GetLikesUseCase.FailureType failureType, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString error, @org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.fluxc.model.LikeModel> cachedLikes, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Failure.EmptyStateData emptyStateData, int expectedNumLikes, boolean hasMore, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo pageInfo, @org.jetbrains.annotations.Nullable()
            java.lang.Boolean iLike) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.FailureType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.FailureType getFailureType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getError() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.LikeModel> component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.LikeModel> getCachedLikes() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Failure.EmptyStateData component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Failure.EmptyStateData getEmptyStateData() {
                return null;
            }
            
            public final int component5() {
                return 0;
            }
            
            public final int getExpectedNumLikes() {
                return 0;
            }
            
            public final boolean component6() {
                return false;
            }
            
            public final boolean getHasMore() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo component7() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo getPageInfo() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Boolean component8() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Boolean getILike() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState$Failure$EmptyStateData;", "", "showEmptyState", "", "title", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "(ZLorg/wordpress/android/ui/utils/UiString$UiStringRes;)V", "getShowEmptyState", "()Z", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class EmptyStateData {
                private final boolean showEmptyState = false;
                @org.jetbrains.annotations.Nullable()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState.Failure.EmptyStateData copy(boolean showEmptyState, @org.jetbrains.annotations.Nullable()
                org.wordpress.android.ui.utils.UiString.UiStringRes title) {
                    return null;
                }
                
                @java.lang.Override()
                public boolean equals(@org.jetbrains.annotations.Nullable()
                java.lang.Object other) {
                    return false;
                }
                
                @java.lang.Override()
                public int hashCode() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.String toString() {
                    return null;
                }
                
                public EmptyStateData(boolean showEmptyState, @org.jetbrains.annotations.Nullable()
                org.wordpress.android.ui.utils.UiString.UiStringRes title) {
                    super();
                }
                
                public final boolean component1() {
                    return false;
                }
                
                public final boolean getShowEmptyState() {
                    return false;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final org.wordpress.android.ui.utils.UiString.UiStringRes component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                    return null;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;", "", "pageLength", "", "page", "(II)V", "getPage", "()I", "getPageLength", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PagingInfo {
        private final int pageLength = 0;
        private final int page = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo copy(int pageLength, int page) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PagingInfo(int pageLength, int page) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getPageLength() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getPage() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$FailureType;", "", "(Ljava/lang/String;I)V", "NO_NETWORK", "GENERIC", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum FailureType {
        /*public static final*/ NO_NETWORK /* = new NO_NETWORK() */,
        /*public static final*/ GENERIC /* = new GENERIC() */;
        
        FailureType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeCategory;", "", "(Ljava/lang/String;I)V", "getActionKey", "", "siteId", "", "entityId", "POST_LIKE", "COMMENT_LIKE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum LikeCategory {
        /*public static final*/ POST_LIKE /* = new POST_LIKE() */,
        /*public static final*/ COMMENT_LIKE /* = new COMMENT_LIKE() */;
        
        LikeCategory() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getActionKey(long siteId, long entityId) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeGroupFingerPrint;", "", "siteId", "", "postOrCommentId", "expectedNumLikes", "", "(JJI)V", "getExpectedNumLikes", "()I", "getPostOrCommentId", "()J", "getSiteId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LikeGroupFingerPrint {
        private final long siteId = 0L;
        private final long postOrCommentId = 0L;
        private final int expectedNumLikes = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.GetLikesUseCase.LikeGroupFingerPrint copy(long siteId, long postOrCommentId, int expectedNumLikes) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public LikeGroupFingerPrint(long siteId, long postOrCommentId, int expectedNumLikes) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getSiteId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getPostOrCommentId() {
            return 0L;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getExpectedNumLikes() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PaginationParams;", "", "requestNextPage", "", "pageLength", "", "(ZI)V", "getPageLength", "()I", "getRequestNextPage", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PaginationParams {
        private final boolean requestNextPage = false;
        private final int pageLength = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.GetLikesUseCase.PaginationParams copy(boolean requestNextPage, int pageLength) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PaginationParams(boolean requestNextPage, int pageLength) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getRequestNextPage() {
            return false;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getPageLength() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesUseCase$Companion;", "", "()V", "PROGRESS_DELAY_MS", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
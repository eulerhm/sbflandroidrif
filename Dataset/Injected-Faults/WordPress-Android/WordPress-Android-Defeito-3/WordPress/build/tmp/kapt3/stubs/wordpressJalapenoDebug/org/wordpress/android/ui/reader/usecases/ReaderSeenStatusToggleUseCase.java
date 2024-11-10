package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u001f B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ+\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ!\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\'\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00100\u001e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "apiCallsProvider", "Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "readerBlogTableWrapper", "Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/utils/PostSeenStatusApiCallsProvider;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;)V", "markPostAsSeen", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState;", "post", "Lorg/wordpress/android/models/ReaderPost;", "actionSource", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$ReaderPostSeenToggleSource;", "doNotTrack", "", "(Lorg/wordpress/android/models/ReaderPost;Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$ReaderPostSeenToggleSource;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markPostAsSeenIfNecessary", "", "(Lorg/wordpress/android/models/ReaderPost;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markPostAsUnseen", "(Lorg/wordpress/android/models/ReaderPost;Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$ReaderPostSeenToggleSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleSeenStatus", "Lkotlinx/coroutines/flow/Flow;", "PostSeenState", "ReaderPostSeenToggleSource", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderSeenStatusToggleUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider apiCallsProvider = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    private final org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper = null;
    
    @javax.inject.Inject()
    public ReaderSeenStatusToggleUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider apiCallsProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper) {
        super();
    }
    
    /**
     * Convenience method for toggling seen status based on the current state in local DB
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleSeenStatus(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.ReaderPostSeenToggleSource actionSource, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markPostAsSeenIfNecessary(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object markPostAsSeen(org.wordpress.android.models.ReaderPost post, org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.ReaderPostSeenToggleSource actionSource, boolean doNotTrack, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState> continuation) {
        return null;
    }
    
    private final java.lang.Object markPostAsUnseen(org.wordpress.android.models.ReaderPost post, org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.ReaderPostSeenToggleSource actionSource, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState;", "", "()V", "Error", "PostSeenStateChanged", "UserNotAuthenticated", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState$PostSeenStateChanged;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState$Error;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState$UserNotAuthenticated;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PostSeenState {
        
        private PostSeenState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState$PostSeenStateChanged;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState;", "isSeen", "", "userMessage", "Lorg/wordpress/android/ui/utils/UiString;", "(ZLorg/wordpress/android/ui/utils/UiString;)V", "()Z", "getUserMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PostSeenStateChanged extends org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState {
            private final boolean isSeen = false;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString userMessage = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState.PostSeenStateChanged copy(boolean isSeen, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString userMessage) {
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
            
            public PostSeenStateChanged(boolean isSeen, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString userMessage) {
                super();
            }
            
            public final boolean component1() {
                return false;
            }
            
            public final boolean isSeen() {
                return false;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getUserMessage() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState$Error;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState;", "message", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Error extends org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState {
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString message = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState.Error copy(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString message) {
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
            
            public Error() {
                super();
            }
            
            public Error(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString message) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getMessage() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState$UserNotAuthenticated;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$PostSeenState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UserNotAuthenticated extends org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase.PostSeenState.UserNotAuthenticated INSTANCE = null;
            
            private UserNotAuthenticated() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$ReaderPostSeenToggleSource;", "", "(Ljava/lang/String;I)V", "READER_POST_CARD", "READER_POST_DETAILS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ReaderPostSeenToggleSource {
        /*public static final*/ READER_POST_CARD /* = new @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$ReaderPostSeenToggleSource$READER_POST_CARD;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$ReaderPostSeenToggleSource;", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"}) READER_POST_CARD(){
    
    READER_POST_CARD() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
} */,
        /*public static final*/ READER_POST_DETAILS /* = new @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$ReaderPostSeenToggleSource$READER_POST_DETAILS;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase$ReaderPostSeenToggleSource;", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"}) READER_POST_DETAILS(){
    
    READER_POST_DETAILS() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
} */;
        
        ReaderPostSeenToggleSource() {
        }
    }
}
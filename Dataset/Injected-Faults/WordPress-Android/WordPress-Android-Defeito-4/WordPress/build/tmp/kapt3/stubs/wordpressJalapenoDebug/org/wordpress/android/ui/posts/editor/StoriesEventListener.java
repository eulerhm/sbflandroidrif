package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00d6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001NB?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#J\u0014\u0010%\u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#J\b\u0010&\u001a\u00020!H\u0003J4\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020(2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010.\u001a\u00020/J&\u00100\u001a\u00020!2\u0006\u0010)\u001a\u00020*2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\b\u00101\u001a\u0004\u0018\u000102J\u0010\u00103\u001a\u00020!2\u0006\u00104\u001a\u000205H\u0007J\u0010\u00106\u001a\u00020!2\u0006\u00104\u001a\u000207H\u0007J\u0010\u00108\u001a\u00020!2\u0006\u00104\u001a\u000209H\u0007J\u0010\u0010:\u001a\u00020!2\u0006\u00104\u001a\u00020;H\u0007J\u0010\u0010<\u001a\u00020!2\u0006\u00104\u001a\u00020=H\u0007J\u0010\u0010>\u001a\u00020!2\u0006\u00104\u001a\u00020?H\u0007J\u0010\u0010@\u001a\u00020!2\u0006\u00104\u001a\u00020AH\u0007J\u0010\u0010B\u001a\u00020!2\u0006\u00104\u001a\u00020CH\u0007J\u0006\u0010D\u001a\u00020!J\u000e\u0010E\u001a\u00020!2\u0006\u0010F\u001a\u00020GJ\u000e\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020\u001fJ&\u0010J\u001a\u00020!2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010K\u001a\u00020LJ\u0006\u0010M\u001a\u00020!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R2\u0010\u001b\u001a\f\u0012\b\u0012\u00060\u0019j\u0002`\u001a0\u00182\u0010\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0019j\u0002`\u001a0\u0018@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006O"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StoriesEventListener;", "Landroidx/lifecycle/LifecycleObserver;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "editorMedia", "Lorg/wordpress/android/ui/posts/editor/media/EditorMedia;", "loadStoryFromStoriesPrefsUseCase", "Lorg/wordpress/android/ui/stories/usecase/LoadStoryFromStoriesPrefsUseCase;", "storiesPrefs", "Lorg/wordpress/android/ui/stories/prefs/StoriesPrefs;", "storyRepositoryWrapper", "Lorg/wordpress/android/ui/stories/StoryRepositoryWrapper;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/ui/posts/editor/media/EditorMedia;Lorg/wordpress/android/ui/stories/usecase/LoadStoryFromStoriesPrefsUseCase;Lorg/wordpress/android/ui/stories/prefs/StoriesPrefs;Lorg/wordpress/android/ui/stories/StoryRepositoryWrapper;)V", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "<set-?>", "Ljava/util/HashSet;", "", "Lcom/wordpress/stories/compose/story/StoryIndex;", "storiesSavingInProgress", "getStoriesSavingInProgress", "()Ljava/util/HashSet;", "storySaveMediaListener", "Lorg/wordpress/android/editor/gutenberg/StorySaveMediaListener;", "onCancelSaveForMediaCollection", "", "mediaFiles", "Ljava/util/ArrayList;", "", "onCancelUploadForMediaCollection", "onDestroy", "onRequestMediaFilesEditorLoad", "", "activity", "Landroid/app/Activity;", "postId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "networkErrorOnLastMediaFetchAttempt", "blockId", "", "onRetryUploadForMediaCollection", "editorMediaUploadListener", "Lorg/wordpress/android/editor/EditorMediaUploadListener;", "onStoryFrameMediaIdChanged", "event", "Lorg/wordpress/android/ui/stories/media/StoryMediaSaveUploadBridge$StoryFrameMediaModelCreatedEvent;", "onStoryFrameMediaUploadedEvent", "Lorg/wordpress/android/ui/posts/editor/StoriesEventListener$StoryFrameMediaUploadedEvent;", "onStoryFrameSaveCompleted", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$FrameSaveCompleted;", "onStoryFrameSaveFailed", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$FrameSaveFailed;", "onStoryFrameSaveProgress", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$FrameSaveProgress;", "onStoryFrameSaveStart", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$FrameSaveStart;", "onStorySaveProcessFinished", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$StorySaveResult;", "onStorySaveStart", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$StorySaveProcessStart;", "pauseListening", "postStoryMediaUploadedEvent", "mediaModel", "Lorg/wordpress/android/fluxc/model/MediaModel;", "setSaveMediaListener", "newListener", "start", "editorMediaListener", "Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;", "startListening", "StoryFrameMediaUploadedEvent", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StoriesEventListener implements androidx.lifecycle.LifecycleObserver {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.ui.posts.editor.media.EditorMedia editorMedia = null;
    private final org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase loadStoryFromStoriesPrefsUseCase = null;
    private final org.wordpress.android.ui.stories.prefs.StoriesPrefs storiesPrefs = null;
    private final org.wordpress.android.ui.stories.StoryRepositoryWrapper storyRepositoryWrapper = null;
    private androidx.lifecycle.Lifecycle lifecycle;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private org.wordpress.android.ui.posts.EditPostRepository editPostRepository;
    private org.wordpress.android.editor.gutenberg.StorySaveMediaListener storySaveMediaListener;
    @org.jetbrains.annotations.NotNull()
    private java.util.HashSet<java.lang.Integer> storiesSavingInProgress;
    
    @javax.inject.Inject()
    public StoriesEventListener(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMedia editorMedia, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase loadStoryFromStoriesPrefsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.prefs.StoriesPrefs storiesPrefs, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.StoryRepositoryWrapper storyRepositoryWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashSet<java.lang.Integer> getStoriesSavingInProgress() {
        return null;
    }
    
    public final void startListening() {
    }
    
    public final void pauseListening() {
    }
    
    /**
     * Handles the [Lifecycle.Event.ON_DESTROY] event to cleanup the registration for dispatcher and removing the
     * observer for lifecycle   .
     */
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_DESTROY)
    private final void onDestroy() {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Lifecycle lifecycle, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener) {
    }
    
    public final void setSaveMediaListener(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.editor.gutenberg.StorySaveMediaListener newListener) {
    }
    
    public final void postStoryMediaUploadedEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel mediaModel) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStoryFrameSaveStart(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.FrameSaveStart event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStoryFrameSaveProgress(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.FrameSaveProgress event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStoryFrameSaveCompleted(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.FrameSaveCompleted event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStoryFrameMediaIdChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.media.StoryMediaSaveUploadBridge.StoryFrameMediaModelCreatedEvent event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStoryFrameSaveFailed(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.FrameSaveFailed event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStorySaveProcessFinished(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.StorySaveResult event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStorySaveStart(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.StorySaveProcessStart event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStoryFrameMediaUploadedEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.StoriesEventListener.StoryFrameMediaUploadedEvent event) {
    }
    
    public final boolean onRequestMediaFilesEditorLoad(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId postId, boolean networkErrorOnLastMediaFetchAttempt, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Object> mediaFiles, @org.jetbrains.annotations.NotNull()
    java.lang.String blockId) {
        return false;
    }
    
    public final void onCancelUploadForMediaCollection(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Object> mediaFiles) {
    }
    
    public final void onRetryUploadForMediaCollection(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Object> mediaFiles, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.editor.EditorMediaUploadListener editorMediaUploadListener) {
    }
    
    public final void onCancelSaveForMediaCollection(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Object> mediaFiles) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StoriesEventListener$StoryFrameMediaUploadedEvent;", "", "localId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "assignedMediaId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "oldUrl", "", "newUrl", "(Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;Ljava/lang/String;Ljava/lang/String;)V", "getAssignedMediaId", "()Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "getLocalId", "()Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "getNewUrl", "()Ljava/lang/String;", "getOldUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class StoryFrameMediaUploadedEvent {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localId = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId assignedMediaId = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String oldUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String newUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.editor.StoriesEventListener.StoryFrameMediaUploadedEvent copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId assignedMediaId, @org.jetbrains.annotations.NotNull()
        java.lang.String oldUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String newUrl) {
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
        
        public StoryFrameMediaUploadedEvent(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId assignedMediaId, @org.jetbrains.annotations.NotNull()
        java.lang.String oldUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String newUrl) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId getLocalId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId getAssignedMediaId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getOldUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getNewUrl() {
            return null;
        }
    }
}
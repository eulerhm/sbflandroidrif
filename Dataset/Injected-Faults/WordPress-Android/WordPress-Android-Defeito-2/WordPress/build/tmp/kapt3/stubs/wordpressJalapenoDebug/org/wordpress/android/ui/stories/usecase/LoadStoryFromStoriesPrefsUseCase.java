package org.wordpress.android.ui.stories.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001aB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eJ\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u001c\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eJ*\u0010\u0015\u001a\u00020\u00132\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u00182\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00190\u000eH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/stories/usecase/LoadStoryFromStoriesPrefsUseCase;", "", "storyRepositoryWrapper", "Lorg/wordpress/android/ui/stories/StoryRepositoryWrapper;", "storiesPrefs", "Lorg/wordpress/android/ui/stories/prefs/StoriesPrefs;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "(Lorg/wordpress/android/ui/stories/StoryRepositoryWrapper;Lorg/wordpress/android/ui/stories/prefs/StoriesPrefs;Lorg/wordpress/android/fluxc/store/MediaStore;)V", "areAllStorySlidesEditable", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "mediaIds", "Ljava/util/ArrayList;", "", "getMediaIdsFromStoryBlockBridgeMediaFiles", "mediaFiles", "loadOrReCreateStoryFromStoriesPrefs", "Lorg/wordpress/android/ui/stories/usecase/LoadStoryFromStoriesPrefsUseCase$ReCreateStoryResult;", "loadStoryFromMemoryOrRecreateFromPrefs", "recreateStoryFrameItemsFromRemoteSiteFlattenedMediaUrls", "storyIndex", "", "Lcom/wordpress/stories/compose/story/StoryIndex;", "", "ReCreateStoryResult", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class LoadStoryFromStoriesPrefsUseCase {
    private final org.wordpress.android.ui.stories.StoryRepositoryWrapper storyRepositoryWrapper = null;
    private final org.wordpress.android.ui.stories.prefs.StoriesPrefs storiesPrefs = null;
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    
    @javax.inject.Inject()
    public LoadStoryFromStoriesPrefsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.StoryRepositoryWrapper storyRepositoryWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.prefs.StoriesPrefs storiesPrefs, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.String> getMediaIdsFromStoryBlockBridgeMediaFiles(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Object> mediaFiles) {
        return null;
    }
    
    public final boolean areAllStorySlidesEditable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.String> mediaIds) {
        return false;
    }
    
    private final org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase.ReCreateStoryResult loadOrReCreateStoryFromStoriesPrefs(org.wordpress.android.fluxc.model.SiteModel site, java.util.ArrayList<java.lang.String> mediaIds) {
        return null;
    }
    
    private final org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase.ReCreateStoryResult recreateStoryFrameItemsFromRemoteSiteFlattenedMediaUrls(int storyIndex, org.wordpress.android.fluxc.model.SiteModel site, java.util.ArrayList<java.lang.Long> mediaIds) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase.ReCreateStoryResult loadStoryFromMemoryOrRecreateFromPrefs(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Object> mediaFiles) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\r\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J+\u0010\u0011\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stories/usecase/LoadStoryFromStoriesPrefsUseCase$ReCreateStoryResult;", "", "storyIndex", "", "Lcom/wordpress/stories/compose/story/StoryIndex;", "allStorySlidesAreEditable", "", "noSlidesLoaded", "(IZZ)V", "getAllStorySlidesAreEditable", "()Z", "getNoSlidesLoaded", "getStoryIndex", "()I", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReCreateStoryResult {
        private final int storyIndex = 0;
        private final boolean allStorySlidesAreEditable = false;
        private final boolean noSlidesLoaded = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase.ReCreateStoryResult copy(int storyIndex, boolean allStorySlidesAreEditable, boolean noSlidesLoaded) {
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
        
        public ReCreateStoryResult(int storyIndex, boolean allStorySlidesAreEditable, boolean noSlidesLoaded) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getStoryIndex() {
            return 0;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getAllStorySlidesAreEditable() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getNoSlidesLoaded() {
            return false;
        }
    }
}
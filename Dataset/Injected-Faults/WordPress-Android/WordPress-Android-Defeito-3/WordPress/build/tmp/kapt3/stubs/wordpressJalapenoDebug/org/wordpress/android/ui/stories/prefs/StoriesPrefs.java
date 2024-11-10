package org.wordpress.android.ui.stories.prefs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u0000 .2\u00020\u0001:\u0002./B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bH\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\nH\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bH\u0002J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\rH\u0002J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bJ\u0016\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u0018\u001a\u00020\bH\u0002J\b\u0010\u0019\u001a\u00020\bH\u0007J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bJ\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0002J\u0016\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\nJ\u0016\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bJ\u0016\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u001e\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\b2\u0006\u0010\'\u001a\u00020\bJ \u0010(\u001a\u0004\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\'\u001a\u00020\bJ\u0018\u0010)\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0002J\u001e\u0010+\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u001e\u0010,\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013J\u001e\u0010-\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/stories/prefs/StoriesPrefs;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "buildSlideKey", "", "siteId", "", "mediaId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "tempId", "Lorg/wordpress/android/ui/stories/prefs/StoriesPrefs$TempId;", "checkSlideIdExists", "", "localId", "checkSlideOriginalBackgroundMediaExists", "storyFrameItem", "Lcom/wordpress/stories/compose/story/StoryFrameItem;", "deleteSlideWithLocalId", "", "deleteSlideWithRemoteId", "deleteSlideWithTempId", "getIncrementalTempId", "getNewIncrementalTempId", "getSlideJson", "slideIdKey", "getSlideWithLocalId", "getSlideWithRemoteId", "getSlideWithTempId", "isUriAccessible", "uri", "Landroid/net/Uri;", "isValidSlide", "replaceLocalMediaIdKeyedSlideWithRemoteMediaIdKeyedSlide", "localIdKey", "", "remoteIdKey", "localSiteId", "replaceTempMediaIdKeyedSlideWithLocalMediaIdKeyedSlide", "saveSlide", "storySlideJson", "saveSlideWithLocalId", "saveSlideWithRemoteId", "saveSlideWithTempId", "Companion", "TempId", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class StoriesPrefs {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stories.prefs.StoriesPrefs.Companion Companion = null;
    private static final java.lang.String KEY_STORIES_SLIDE_INCREMENTAL_ID = "incremental_id";
    private static final java.lang.String KEY_PREFIX_STORIES_SLIDE_ID = "story_slide_id-";
    private static final java.lang.String KEY_PREFIX_TEMP_MEDIA_ID = "t-";
    private static final java.lang.String KEY_PREFIX_LOCAL_MEDIA_ID = "l-";
    private static final java.lang.String KEY_PREFIX_REMOTE_MEDIA_ID = "r-";
    
    @javax.inject.Inject()
    public StoriesPrefs(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final java.lang.String buildSlideKey(long siteId, org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId mediaId) {
        return null;
    }
    
    private final java.lang.String buildSlideKey(long siteId, org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId mediaId) {
        return null;
    }
    
    private final java.lang.String buildSlideKey(long siteId, org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId tempId) {
        return null;
    }
    
    @kotlin.jvm.Synchronized()
    @android.annotation.SuppressLint(value = {"ApplySharedPref"})
    public final synchronized long getNewIncrementalTempId() {
        return 0L;
    }
    
    private final long getIncrementalTempId() {
        return 0L;
    }
    
    public final boolean checkSlideIdExists(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId mediaId) {
        return false;
    }
    
    private final boolean checkSlideIdExists(long siteId, org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId tempId) {
        return false;
    }
    
    private final boolean checkSlideIdExists(long siteId, org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localId) {
        return false;
    }
    
    private final boolean checkSlideOriginalBackgroundMediaExists(long siteId, org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId mediaId) {
        return false;
    }
    
    private final boolean checkSlideOriginalBackgroundMediaExists(long siteId, org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId mediaId) {
        return false;
    }
    
    private final boolean checkSlideOriginalBackgroundMediaExists(long siteId, org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId mediaId) {
        return false;
    }
    
    private final boolean checkSlideOriginalBackgroundMediaExists(com.wordpress.stories.compose.story.StoryFrameItem storyFrameItem) {
        return false;
    }
    
    private final boolean isUriAccessible(android.net.Uri uri) {
        return false;
    }
    
    private final void saveSlide(java.lang.String slideIdKey, java.lang.String storySlideJson) {
    }
    
    public final boolean isValidSlide(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId mediaId) {
        return false;
    }
    
    public final boolean isValidSlide(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId tempId) {
        return false;
    }
    
    public final boolean isValidSlide(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localId) {
        return false;
    }
    
    private final java.lang.String getSlideJson(java.lang.String slideIdKey) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wordpress.stories.compose.story.StoryFrameItem getSlideWithRemoteId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId mediaId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wordpress.stories.compose.story.StoryFrameItem getSlideWithLocalId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId mediaId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wordpress.stories.compose.story.StoryFrameItem getSlideWithTempId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId tempId) {
        return null;
    }
    
    public final void saveSlideWithTempId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId tempId, @org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.story.StoryFrameItem storyFrameItem) {
    }
    
    public final void saveSlideWithLocalId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId mediaId, @org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.story.StoryFrameItem storyFrameItem) {
    }
    
    public final void saveSlideWithRemoteId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId mediaId, @org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.story.StoryFrameItem storyFrameItem) {
    }
    
    public final void deleteSlideWithTempId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId tempId) {
    }
    
    public final void deleteSlideWithLocalId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId mediaId) {
    }
    
    public final void deleteSlideWithRemoteId(long siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId mediaId) {
    }
    
    public final void replaceLocalMediaIdKeyedSlideWithRemoteMediaIdKeyedSlide(int localIdKey, long remoteIdKey, long localSiteId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wordpress.stories.compose.story.StoryFrameItem replaceTempMediaIdKeyedSlideWithLocalMediaIdKeyedSlide(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId tempId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId localId, long localSiteId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/stories/prefs/StoriesPrefs$TempId;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TempId {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String id = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stories.prefs.StoriesPrefs.TempId copy(@org.jetbrains.annotations.NotNull()
        java.lang.String id) {
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
        
        public TempId(@org.jetbrains.annotations.NotNull()
        java.lang.String id) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/stories/prefs/StoriesPrefs$Companion;", "", "()V", "KEY_PREFIX_LOCAL_MEDIA_ID", "", "KEY_PREFIX_REMOTE_MEDIA_ID", "KEY_PREFIX_STORIES_SLIDE_ID", "KEY_PREFIX_TEMP_MEDIA_ID", "KEY_STORIES_SLIDE_INCREMENTAL_ID", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
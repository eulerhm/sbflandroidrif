package org.wordpress.android.ui.stories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\"\u0010\u0007\u001a\u00060\bj\u0002`\t2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rJ\n\u0010\u000e\u001a\u00060\bj\u0002`\tJ\u001c\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\bj\u0002`\t2\b\b\u0002\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\fJ\b\u0010\u0014\u001a\u0004\u0018\u00010\fJ\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\u0012\u0010\u0018\u001a\u00020\u00172\n\u0010\u0019\u001a\u00060\bj\u0002`\tJ\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u00172\n\u0010\u0011\u001a\u00060\bj\u0002`\tJ\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\f\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/stories/StoryRepositoryWrapper;", "", "()V", "addStoryFrameItemToCurrentStory", "", "item", "Lcom/wordpress/stories/compose/story/StoryFrameItem;", "findStoryContainingStoryFrameItemsByIds", "", "Lcom/wordpress/stories/compose/story/StoryIndex;", "ids", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getCurrentStoryIndex", "getCurrentStorySaveProgress", "", "storyIndex", "oneItemActualProgress", "getCurrentStoryThumbnailUrl", "getCurrentStoryTitle", "getImmutableStories", "", "Lcom/wordpress/stories/compose/story/Story;", "getStoryAtIndex", "index", "loadStory", "setCurrentStoryTitle", "title", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StoryRepositoryWrapper {
    
    @javax.inject.Inject()
    public StoryRepositoryWrapper() {
        super();
    }
    
    public final void setCurrentStoryTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentStoryThumbnailUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCurrentStoryTitle() {
        return null;
    }
    
    public final int getCurrentStoryIndex() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wordpress.stories.compose.story.Story loadStory(int storyIndex) {
        return null;
    }
    
    public final void addStoryFrameItemToCurrentStory(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.story.StoryFrameItem item) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wordpress.stories.compose.story.Story getStoryAtIndex(int index) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wordpress.stories.compose.story.Story> getImmutableStories() {
        return null;
    }
    
    public final float getCurrentStorySaveProgress(int storyIndex, float oneItemActualProgress) {
        return 0.0F;
    }
    
    public final int findStoryContainingStoryFrameItemsByIds(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.String> ids) {
        return 0;
    }
}
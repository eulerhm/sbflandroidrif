package org.wordpress.android.ui.stories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001`\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stories/StoriesTrackerHelper;", "", "()V", "getCommonProperties", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "event", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$StorySaveResult;", "trackStoryPostSavedEvent", "", "frameQty", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "locallySaved", "", "trackStorySaveResultEvent", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StoriesTrackerHelper {
    
    @javax.inject.Inject()
    public StoriesTrackerHelper() {
        super();
    }
    
    public final void trackStorySaveResultEvent(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.StorySaveResult event) {
    }
    
    public final void trackStoryPostSavedEvent(int frameQty, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean locallySaved) {
    }
    
    private final java.util.HashMap<java.lang.String, java.lang.Object> getCommonProperties(com.wordpress.stories.compose.frame.StorySaveEvents.StorySaveResult event) {
        return null;
    }
    
    public final void trackStorySaveResultEvent(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.StorySaveResult event, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat) {
    }
}